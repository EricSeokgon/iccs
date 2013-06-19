<%@ page contentType="text/html;charset=utf-8"%>
<%@ page errorPage="../com/com/error.jsp" %>
<%@ page import="kjf.util.*" %>

<% 
	String cmd      = KJFUtil.print(request.getParameter("cmd")); 
	String user_id  = KJFUtil.print(request.getParameter("user_id"));
	String mem_kind = KJFUtil.print(request.getParameter("mem_kind"));
%>

<script language="javascript">
	<% if( cmd.equals("MemRegPriC") || cmd.equals("MemRegBusC") ) { %>
		location.href = "../mem/MemAction.do?cmd=MemRegSuccess&user_id=<%=user_id%>&mem_kind=<%=mem_kind%>";
	<% }%>
</script>