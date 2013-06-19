<%@ page contentType="text/html;charset=utf-8"%>
<a name="leftmenu"></a>
<h2 id="left_title"><img src="../01_one/images/left_menu_title.gif" alt="원스톱민원센타" border="0"></h2>
<div id="left_menu">
<% 
	if (ruleChk){ 
		if (gpkiChk){
			out.print("<script>flash('../flash/aleft_navi_01_pub.swf?pageNum="+left_pageNum+"&sub="+left_sub+"','154','310');</script>");
		}else {
			out.print("<script>flash('../flash/left_navi_01_pub.swf?pageNum="+left_pageNum+"&sub="+left_sub+"','154','310');</script>");
		}		
	} else { 
		out.print("<script>flash('../flash/left_navi_01.swf?pageNum="+left_pageNum+"&sub="+left_sub+"','154','280');</script>");
	}
%>
</div>
<div id="left_bottom">
<img src="../images/left_menu/left_menu_bottom.gif" border="0">
</div>

<div id="left_banner">
	<%@ include file="../inc/left_banner.jsp" %>
</div>