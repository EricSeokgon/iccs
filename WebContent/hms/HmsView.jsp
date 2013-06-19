<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="kjf.ops.*" %>
<%@ page import="kjf.util.*" %>
<%@ page import="sp.uent.*" %>

<%
	ReportEntity rEntity = KJFUtil.REntPrint( (ReportEntity)request.getAttribute("rEntity") );

	UserEnt user = (UserEnt)request.getSession().getAttribute("user");
	
	String CONTENT = "";
	String URL     = "";
	String VERSION = "";
	String MENU_ID = "";
	
	if (rEntity.getRowCnt() > 0) {
	    CONTENT = rEntity.getValue(0, "CONTENT");
	    URL     = rEntity.getValue(0, "URL");
	    VERSION = rEntity.getValue(0, "VERSION");
	    MENU_ID = rEntity.getValue(0, "MENU_ID");
	}	                              
%>

<div id="hms_view"><%=KJFUtil.FCKeditorView_no_iframe(CONTENT) %></div>

<% if(user != null && user.isAdmin()) {%>
<div id='hms_modify'>

	<div id='page_name'>
		<img src="../images/hms/view_01.gif" border="0" alt="페이지명">
		<%=URL%>
	</div>

	<div id='page_version'>
		<img src="../images/hms/view_02.gif" alt="페이지버전">
		<%=VERSION%>
	</div>

	<div id="hms_btn">
		<a href="../hms/HmsAction.do?cmd=HmsWrite&menu_id=<%=MENU_ID%>"><img src="../images/hms/bt_modify.gif" border="0" alt="페이지수정"></a>
	</div>

</div>
<%} %>