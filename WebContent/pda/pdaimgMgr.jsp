<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.*"%>
<%@ page import="kjf.util.*" %>
<%@ page import="kjf.ops.*" %>
<%@ page isErrorPage="true" %>
<%
String city = request.getParameter("city");
String gugun = request.getParameter("gugun");
%>
<%out.print("IMG0|http://net.go.kr/pda/PdaAction.do?cmd=Pdaimg0Mgr&city="+city+"&gugun="+gugun+"|\nEND");%>
