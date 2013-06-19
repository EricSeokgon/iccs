<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.sql.*, java.io.*, java.net.*, java.util.*" %>
<%@ include file="../gpkisecureweb/gpkisecureweb.jsp" %>
<%@ page import="com.gpki.servlet.GPKIHttpServletResponse" %>
<%@ page import="kjf.util.*" %>

<%
	String challenge = gpkiresponse.getChallenge();

	String strID =  request.getParameter("id");

	String OFFI_DN = (String)request.getAttribute("OFFI_DN") ;
	String msg="(인증서가 등록되어 있지 않습니다.)";
	if(!KJFUtil.isEmpty(OFFI_DN)) msg="(인증서가 등록되어 있지 않습니다.)";

%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="../css/member.css" rel="stylesheet" type="text/css" >
<script language='javascript' src='../gpkisecureweb/var.js'></script>
<script language='javascript' src='../gpkisecureweb/GPKIFunc.js'></script>
<script language='javascript' src='../gpkisecureweb/object.js'></script>

<script language="JavaScript">

//-->
</script>
</head>

<body leftmargin="0" topmargin="0" >

<form action="../login/GCertRegProc.jsp" method="post" name="popForm">
<input type="hidden" name="challenge" value="<%=challenge%>">
<input type="hidden" name="id" value="<%=strID%>">
<table border = "0">
	<tr>
		<td rowspan="2">
			<img src="../images/login/abtn_ok.gif" onClick="Login(popForm);" border="0"/>
		</td>
		<td><%=msg %><br/>
			<a href="../gpkisecureweb/install.html" target='_blank'>
				<img src="../images/login/abtn_program.gif" border="0" />
			</a>
		</td>
	</tr>
</table>		
</form>
</body>
</html>
