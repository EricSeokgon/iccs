<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="kjf.util.*" %>

<% 
	String cmd = KJFUtil.print(request.getParameter("cmd")); 
%>

<script language="javascript">

// 자주가는 창고 등록
<% if (cmd.equals("MyUseStoreCUD")) { %>
	location.href = "../mystore/MystoreAction.do?cmd=MyUseStore";
<% }%>
</script>