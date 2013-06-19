<%@page contentType="text/html;charset=UTF-8" %><%
response.setHeader ("Pragma", "No-cache");
response.setDateHeader ("Expires", 0);
response.setHeader ("Cache-Control", "no-cache");
if(request.getAttribute("DELI_NUM")!=null)
	out.print(request.getAttribute("DELI_NUM"));
else
	out.print("failure");
%>