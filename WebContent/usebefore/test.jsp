<%@ page contentType="text/html;charset=euc-kr" %>
<%@ page language="java"%>

<%@ page import="sp.webservice.UBAgentPortTypeProxy" %>
<%@ page import="sp.webservice.EchoPortTypeProxy" %>

<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="javax.servlet.http.HttpServletResponse" %>

<%
	String default_charset = "utf-8";
	

	String sido_code = "bsbs";
	String sigg_code = "bsgs";

	EchoPortTypeProxy ec =new EchoPortTypeProxy("http://localhost/iccsWs/services/Echo");
	System.out.println("#sendFile#");
	System.out.println("##############"+ec.getWecome("abc"));
%>