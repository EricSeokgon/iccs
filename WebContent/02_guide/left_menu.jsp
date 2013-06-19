<%@ page contentType="text/html;charset=utf-8"%>
<a name="leftmenu"></a>
<h2 id="left_title"><img src="../02_guide/images/left_menu_title.gif" alt="길라잡이" border="0"></h2>
<div id="left_menu">
	<script>flash('../flash/left_navi_02.swf?pageNum=<%=left_pageNum%>&sub=<%=left_sub%>','154','350');</script>
</div>

<div id="left_bottom">
	<img src="../images/left_menu/left_menu_bottom.gif" border="0">
</div>

<div id="left_banner">
<%	if(ruleChk){ %>
<%@ include file="../inc/left_banner_pub.jsp" %>
<%	}else{ %>
<%@ include file="../inc/left_banner.jsp" %>
<%	} %>
</div>