<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="com.gpki.gpkiapi.cert.*" %>
<%@ page import="com.gpki.gpkiapi.cms.*" %>
<%@ page import="com.gpki.gpkiapi.util.*" %>
<%@ page import="com.dsjdf.jdf.Logger" %>
<%@ include file='../gpkisecureweb/gpkisecureweb.jsp'%>

<%
			String id = gpkirequest.getParameter("id") ;
            X509Certificate cert = null; 
            byte[] signData = null;
            byte[] privatekey_random = null;
            String signType = "";
            String queryString = "";
// 			GPKIHttpServletRequest gpkirequest = new GPKIHttpServletRequest(request);
            
            cert = gpkirequest.getSignerCert(); 
            String OFFI_DN = cert.getSubjectDN();


%>

<html>
<head>
</head>

<body onload=javascript:document.popForm.submit();>
<form action="../login/LoginAction.do?cmd=GCertRegCUD"  method="post" name="popForm">
<input type="hidden" name="OFFI_DN" value='<%=OFFI_DN%>'>
<input type="hidden" name="id" value='<%=id%>'>
</form>
</table>
</body>
</html>
