<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page language="java" import="com.juicywx.business.WXService" %>
<%@page language="java" import="com.juicywx.business.*" %>
<%@page language="java" import="com.juicywx.data.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>QR code</title>
</head>
<body>
	<%
		String ticket = WXService.getInstance().getQRCodeTicket("123");
		String ticket_two = WXService.getInstance().getQRCodeTicket("321");
	%>
	QR picture here:<br/>
	<a target="_blank" href="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=<%=ticket%>">click here</a>
	<img src="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=<%=ticket%>" />
	<h2>减积分</h2>
	<a target="_blank" href="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=<%=ticket_two%>">click here</a>
	<img src="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=<%=ticket_two%>" />
	
</body>
</html>