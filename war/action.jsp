<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<%@page language="java" import="com.juicywx.business.*" %>
<%@page language="java" import="com.juicywx.data.*" %>
<%@page language="java" import="com.juicywx.async.*" %>
<%@page language="java" import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<%
String time = request.getParameter("timestamp");
String openId=request.getParameter("openId");
String action=request.getParameter("action");
if(action==null){
	%>
	null
	<% 
	return ;
}
if(action.equals("first")){
%>
	<a href='action.jsp?action=second&timestamp=<%=time%>&openId=<%=openId%>'>
		confirm
	</a>
<%
}else if(action.equals("second")){
	
	boolean gs = GuestService.addGuestExp(openId, 1);
	if(gs!=false){
		GuestService.getVIPInfo(openId);
		Guest guest = GuestService.getVIPInfo(openId);
		int exp=guest.Exp-guest.Use;
		WXService.getInstance().sendKFText(openId, "你还有"+exp+"积分");
		 %>
		 	updated  <%=new Date().toGMTString() %>
			 <%
			 
	}else{
	 %>
	 	error  <%=new Date().toGMTString() %>
	 <%
	}
}
%>
</body>
</html>