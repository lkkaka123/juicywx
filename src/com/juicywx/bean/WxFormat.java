package com.juicywx.bean;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;

import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;


public class WxFormat {

	public static Message prase(InputStream input) {
		Digester digester = new Digester();
		digester.setValidating(false);
		digester.addObjectCreate("xml", Message.class);
		digester.addBeanPropertySetter("xml/FromUserName","fromUserName");
		digester.addBeanPropertySetter("xml/ToUserName", "toUserName");
		digester.addBeanPropertySetter("xml/CreateTime", "createTime");
		digester.addBeanPropertySetter("xml/MsgType","msgType");
		digester.addBeanPropertySetter("xml/Event","event");
		digester.addBeanPropertySetter("xml/EventKey", "eventKey");
		digester.addBeanPropertySetter("xml/Ticket","ticket");
		digester.addBeanPropertySetter("xml/Latitude","latitude");
		digester.addBeanPropertySetter("xml/Longitude","longitude");
		digester.addBeanPropertySetter("xml/Precision","precision");
		digester.addBeanPropertySetter("xml/PicUrl","picUrl");
		digester.addBeanPropertySetter("xml/Content","content");
		digester.addBeanPropertySetter("xml/MediaId","mediaId");
		digester.addBeanPropertySetter("xml/Format","format");
		digester.addBeanPropertySetter("xml/ThumbMediaId","thumbMediaId");
		digester.addBeanPropertySetter("xml/Location_X","location_X");
		digester.addBeanPropertySetter("xml/Location_Y","location_Y");
		digester.addBeanPropertySetter("xml/Scale","scale");
		Message msg = null;
        try {
            msg = (Message) digester.parse(input);
        } catch (IOException e) {
        	e.printStackTrace();
        } catch (SAXException e) {
        	e.printStackTrace();
        }
        return msg;
	}
	
	private static XStream xstream = new XStream(new XppDriver() {  
        public HierarchicalStreamWriter createWriter(Writer out) {  
            return new PrettyPrintWriter(out) {  
              
                boolean cdata = true;  
            
                protected void writeText(QuickWriter writer, String text) {  
                    if (cdata) {  
                        writer.write("<![CDATA[");  
                        writer.write(text);  
                        writer.write("]]>");  
                    } else {  
                        writer.write(text);  
                    }  
                }  
                
            };  
        }  
    });  
	public static String replyToXml(Reply reply){
		String type = reply.getMsgType();
		xstream.autodetectAnnotations(true);  
		xstream.alias("xml", reply.getClass());
		xstream.alias("item", new Article().getClass());
		String str="";
		if(Reply.TEXT.equals(type)){
			TextReply tp = (TextReply)reply;
			str=xstream.toXML(tp);
		}
		
		return str;
	}

}
