<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../inc/user_inc.jsp" %>

<!-- 공통 부분 -->
<h1 id="logo"><a href="../index.jsp"><img src="../images/common/logo.gif" alt="정보통신공사업시스템" border="0"></a></h1>

<!-- menu : S -->
<div id="menu"><a name="topmenu"></a>
	<% 
	if (ruleChk){
		if (gpkiChk){
			if (top_pageNum.equals("") || top_sub.equals("")) {
				out.print("<script type=\"text/javascript\">flash('../flash/atop_navi_pub.swf','560','85');</script><noscript><p>JavaScript</p></noscript>");		
			} else {
				out.print("<script type=\"text/javascript\">flash('../flash/atop_navi_pub.swf?pageNum="+top_pageNum+"&sub="+top_sub+"','550','85');</script><noscript><p>JavaScript</p></noscript>");
			} 
		} else {
			if (top_pageNum.equals("") || top_sub.equals("")) {
				out.print("<script type=\"text/javascript\">flash('../flash/top_navi_pub.swf','560','85');</script><noscript><p>JavaScript</p></noscript>");		
			} else {
				out.print("<script type=\"text/javascript\">flash('../flash/top_navi_pub.swf?pageNum="+top_pageNum+"&sub="+top_sub+"','550','85');</script><noscript><p>JavaScript</p></noscript>");
			} 			
		}
	}else{
			if (top_pageNum.equals("") || top_sub.equals("")) {
				out.print("<script type=\"text/javascript\">flash('../flash/top_navi.swf','560','85');</script><noscript><p>JavaScript</p></noscript>");
			} else {
				out.print("<script type=\"text/javascript\">flash('../flash/top_navi.swf?pageNum="+top_pageNum+"&sub="+top_sub+"','550','85');</script><noscript><p>JavaScript</p></noscript>");
			} 
	}
	%>	
		
</div>
<!-- menu : E -->

<!-- 오른 영역 : S -->
<div id="menu_small" >

	<!-- Home 버튼 : S -->
	<div id="top_small_btn">
		<a href="javascript:fn_goHome();"><img src="../images/common/top_small_home.gif" alt="HOME"></a>
		<img src="../images/common/top_small_bar.gif" alt="" class="bar">

	<% if(!ruleChk){ %>	
		<a href="javascript:fn_goSiteMap()"><img src="../images/common/top_small_sitemap.gif" alt="사이트맵"></a>
		<img src="../images/common/top_small_bar.gif" alt="" class="bar">
	 	<a href="javascript:fn_goMystore()"><img src="../images/common/top_small_mynet.gif" alt="My네트"></a> 
	<% 
		}else { 
			out.print("<a href='../hms/HmsAction.do?cmd=HmsView&menu_id=771'><img src=\"../images/common/top_small_sitemap.gif\" alt=\"사이트맵\"></a>");
		}
	%>

	</div>
	<!-- Home 버튼 : E -->
	
	<!-- 찾기 : S -->
	<form name="searchfrm" action="../search/SearchAction.do?cmd=UnifiedSearch" method="post">
	<div id="top_search">
		<input type="text" name="scTextValue" title="검색어를 입력해 주십시오" onkeyup="if(event.keyCode == 13) fn_UnifiedSearch();" class="search" />
		<a href="javascript:fn_UnifiedSearch()"><img src="../images/common/top_small_search_btn.gif" alt="검색" align="absmiddle"></a>
	</div>
	</form>
	<!-- 찾기 : E -->
	
</div>
<!-- 오른 영역  : S -->
<%
	if (user != null) {
		if (!"N".equals(user.getCAPITAL())){
%>
<!-- 배너 : S -->
<!-- 공무원용으로 사용 -->
<div id="menu_banner" style="display:none">
	<a href="http://99.1.5.83/" target="_blank"><img src="../images/common/top_banner1.gif" border="0" alt="업무시스템 바로가기" title="업무시스템으로 이동합니다."></a>
	<a href="http://iccs.pktcorp.com/" target="_blank"><img src="../images/common/top_banner2.gif" border="0" alt="사스템 검토게시판 바로가기" title="사스템 검토게시판 이동합니다."></a>
</div>
<%}}%>