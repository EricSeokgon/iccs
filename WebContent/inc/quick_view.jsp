<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="kjf.ops.*" %>
<%@ page import="kjf.util.*" %>
<%@ page import="sp.mystore.*" %>
<%@ page import="sp.uent.*" %>

<%	
	UserEnt user = (UserEnt)request.getSession().getAttribute("user");

	ReportEntity rEntity = KJFUtil.REntPrint( (ReportEntity)request.getAttribute("rEntity") );	// 자주가는 창고 등록정보
%>
<script>
// 자주가는 창고 등록 페이지 가기
function fn_go_favorite(fav_url) {
	parent.location.href = fav_url;
}
</script>
<!-- 공통 : S -->
<link href="../css/total.css" rel="stylesheet" type="text/css" />
<!-- 공통 : E -->
<body class="no_img">
<div id="favorite_white">	
	<% if (!KJFUtil.isEmpty(user)) { %>
		<% if(rEntity.getRowCnt() > 0) { %>
			<!--리스트 : S -->
			<ul>
				<% for(int i = 0; i < rEntity.getRowCnt(); i++) {  %>
				<li><span><a href="javascript:fn_go_favorite('<%=rEntity.getValue(i, "FAV_URL")%>')"><%=rEntity.getValue(i, "FAV_NAME")%></a></span></li>
				<% } %>
			</ul>
			<!--리스트 : E -->
		<% } else { %>
			<!--등록메뉴없을경우 : S -->
			<div id="no"><strong>등록된 서비스가 없습니다.</strong>
			<a href="javascript:fn_go_favorite('../mystore/MystoreAction.do?cmd=MyUseStore')"><img src="../images/common/favorite_btn_go.gif" alt="메뉴설정하러 가기"></a></div>
			<!--등록메뉴없을경우 : E -->
		<% } %>
	<% } else { %>
			<!-- 로그인 체크 : S -->
			<div id="no"><strong><div style="padding:0 0 10px 0;">로그인이 필요한 서비스입니다.</div></strong>
			<a href="javascript:fn_go_favorite('../member/login.do')"><img src="../images/common/favorite_btn_login.gif" alt="로그인 하기"></a></div>
			<!-- 로그인 체크 : E -->
	<% } %>
</div>
</body>