<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.*"%>
<%@ page import="kjf.util.*" %>
<%@ page import="kjf.ops.*" %>
<%@ page isErrorPage="true" %>
<%
String city = request.getParameter("city");
String gugun = request.getParameter("gugun");
String id = request.getParameter("id");
%>
<%out.print("IMG1|http://net.go.kr/pda/PdaAction.do?cmd=Pdaimg1Mgr&city="+city+"&gugun="+gugun+"&id="+id+"|\nEND");%>
