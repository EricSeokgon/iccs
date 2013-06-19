<%@ page contentType="text/html;charset=utf-8"%>
<%@ page errorPage="../com/com/error.jsp" %>
<%@ page import="kjf.util.*" %>

<% 
	String cmd     = KJFUtil.print(request.getParameter("cmd")); 
	String menu_id = KJFUtil.print(request.getParameter("menu_id")); 
%>

<script language="javascript">
	<% if(cmd.equals("HmsContentC")) { %>
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=" + <%=menu_id%>;
	<% }%>
</script>