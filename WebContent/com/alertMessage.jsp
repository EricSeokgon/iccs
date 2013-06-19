<%@ page contentType="text/html; charset=utf-8" %>
<%@ page isErrorPage="true" %>
<%@ page import="kjf.util.*" %>
<%
	String returnURL = KJFUtil.print((String)request.getAttribute("returnURL"));
	String ex 		 = (String)request.getAttribute("ex");	
	String isMSG     = KJFUtil.print((String)request.getAttribute("isMSG"));
%>
<html>
<head>
<title>message</title>
</head>
<body topmargin=0 leftmargin=0 rightmargin=0>
<script language="javascript">
<!--
<% if ( !isMSG.equalsIgnoreCase("FALSE") ) {%>
	alert("<%=ex%>");
<%}%>

<% if(!KJFUtil.isEmpty(returnURL)) { %>
		if(self != top) top.document.location.href="<%=returnURL%>";
		else document.location.href="<%=returnURL%>";
	
<% } else { %>
		history.go(-1);
<% } %>
//-->
</script>
</body>
</html>
