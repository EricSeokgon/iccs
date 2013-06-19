<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="kjf.util.*" %>
<%@ page import="kjf.cfg.Config" %>
<%@ page import="java.net.URLDecoder" %>

<%

	String beforeURL			= (String)request.getAttribute("beforeURL");

	beforeURL = java.net.URLDecoder.decode(beforeURL);

	response.sendRedirect("/iccs"+beforeURL);

%>
