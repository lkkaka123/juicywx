package com.juicywx;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.juicywx.auth.EncoderHandler;
import com.juicywx.business.SessionService;


public class WxImp extends HttpServlet {
	private static String TOKEN = "juicywx";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/plain");
		String echoStr = req.getParameter("echostr");
		if(checkSignature(req)==true&&echoStr!=null){
			resp.getWriter().print(echoStr);
		}else{
			resp.getWriter().print("Welcome to juicywx");
		}
	}
	private boolean checkSignature(HttpServletRequest req){
		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		if(signature==null||timestamp==null||nonce==null)
			return false;
		String[] array = new String[]{TOKEN,timestamp,nonce};
		Arrays.sort(array);
		String tmpStr = "";
		for (String str : array) {
			tmpStr = tmpStr + str;
		}
		tmpStr = EncoderHandler.Sha1(tmpStr);
		
		if(tmpStr.equals(signature))
			return true;
		else
			return false;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/plain");
		req.setCharacterEncoding("UTF-8");  
		resp.setCharacterEncoding("UTF-8");  
//		SessionService.getInstance().setSession(req.getSession());
		
		MessageHandler handler = new MessageHandler();
		handler.dispath(req);
		String respStr = handler.getmReply();
		if(respStr==null)
			respStr="Wrong";
		resp.getWriter().print(respStr);
	}

}
