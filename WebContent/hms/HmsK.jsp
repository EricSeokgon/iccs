<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="kjf.util.*" %>
<%@ page import="sp.hms.*" %>

<%
	String cmd = KJFUtil.print(request.getParameter("cmd"), "BbsKList");
%>

<% if(cmd.equals("HmsView")) { %>
	<jsp:include page="../hms/HmsView.jsp" />
<% } %>

<% if(cmd.equals("HmsWrite")) { %>
	<jsp:include page="../hms/HmsWrite.jsp" />
<% } %>

