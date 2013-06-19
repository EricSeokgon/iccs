<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="kjf.ops.*" %>
<%@ page import="kjf.util.*" %>
<%@ page import="sp.bbs.*" %>

<%
	ReportEntity rEntity = KJFUtil.REntPrint( (ReportEntity)request.getAttribute("ListEntity") );	// 공무원센터 커뮤니티
	ReportEntity rEtcEntity = KJFUtil.REntPrint( (ReportEntity)request.getAttribute("ListEtcEntity") );	// 공무원센터 커뮤니티

	BbsParam pm = (BbsParam)request.getAttribute("pm");
	
	// 상단 플래쉬 링크 정보
	String top_pageNum  = "1";
	String top_sub      = "1";
	
	// 좌측 플래쉬 링크 정보
	String left_pageNum = "1";
	String left_sub     = "1";

	
%>

<html lang="ko">
<head>
<title>공사업 등록정보 페이지입니다.</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="robots" content="noindex,nofollow">
<meta name="author" content="부산광역시">
<meta name="keywords" content="정보통신공사업시스템">
<meta name="description" content="정보통신공사업시스템">
<meta http-equiv="imagetoolbar" content="no">

<!-- 공통 : S -->
<link href="../css/total.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="../com/js/topMenu.js" type="text/javascript"></script><noscript><p>JavaScript</p></noscript>
<script language="JavaScript" src="../com/js/main.js" type="text/javascript"></script><noscript><p>JavaScript</p></noscript>
<script language="JavaScript" src="../flash/flash_pub.js" type="text/javascript"></script><noscript><p>JavaScript</p></noscript>
<!-- 공통 : E -->
</head>
<body>
<noscript><p>자바스크립트를 지원해야 올바르게 동작하는 페이지입니다.</p></noscript>
<!-- 전체 : S -->
<div id="wrapper">
	<div id="skipnavigation">
		<ul>
		<li><a tabindex="0" href="#contents">본문으로 바로가기</a></li>
		<li><a tabindex="0" href="#topmenu">대메뉴로 바로가기</a></li>
		<li><a tabindex="0" href="#leftmenu">소메뉴로 바로가기</a></li>
		</ul>
	</div>
		
	<!-- header : S-->
	<div id="header">
  		<div id="top_navi"><%@ include file="../inc/top.jsp" %></div>	
  		<div id="login"><%@ include file="../inc/top_login.jsp" %></div>
  	</div>
  	<!-- header : E -->
  
    <!-- 페이지 타이틀 : S-->
  	<div id="sub_visual"><%@ include file="sub_visual.jsp" %></div>
 	<!-- 페이지 타이틀 : E -->
 	 
  	<!-- BODY : S -->
	<div id="body">
  	
	  	<!-- left : S -->
	  	<div id="sub_left"><%@ include file="left_menu.jsp" %></div>
		<!-- left : E -->	
	
		<!-- 중간컨텐츠 : S -->
   		<div id="sub_contents"><a name="contents"></a>
			
			<!-- 현재위치 : S -->
			<div id="con_head">
				<p class="position">
					<a href="../"><img src="../images/common/icon_home.gif" alt="HOME" /></a> 공무원센터 
				</p>
			</div>
			<!-- 현재위치 : E -->
	
			<!-- 서브타이틀 : S -->
			<div id="sub_title">
				<h3 class="cont01"><img src="../09_center/images/titile_01_0.gif" alt="공무원센타" /></h3>
			</div>
			<!-- 서브타이틀 : E -->
   
		  	<!-- 컨텐츠 : S -->
			<div id="contents">
			<!--01공사업등록정보 : S -->
			
			
<div id="cen02">	
				<!--1 업체기본정보 : S -->
				<h4>센터커뮤니티</h4>
<ul>
	<% if ( rEntity != null && rEntity.getRowCnt() != 0) { %>
		<% for (int i = 0; i < rEntity.getRowCnt(); i++ ) { %>
			<li>
				<a href="javascript:go_bbs_view('<%=rEntity.getValue(i, "CT_ID")%>', '<%= rEntity.getValue(i, "BOARD_SEQ") %>');" >
				<span class="subject2">
					 <%="<span class='t_name'>["+rEntity.getValue(i, "TITLE_HEADER")+"]</span>"+KJFUtil.java2html(KJFUtil.getTitleLimit(rEntity.getValue(i, "SUBJECT"), 100))%>
					<%if (KJFDate.isNew(3, rEntity.getValue(i,"INS_DT")) ) { %>
						<img src='../images/bbs/ico_new.gif' alt="새 글">
					<% } %>	
				</span></a>
				<span class="date2"><%=KJFDate.getTimeAsPattern(KJFDate.str2date(rEntity.getValue(i, "INS_DT"), ""), "yyyy.MM.dd")%></span>
			</li>
		<% } %>
		
	<% } else { %>
		<li class="detail_none2">등록된 글이 없습니다.</li>
	<% } %>
</ul>		
</div>
		<div id="cen02">			
	<!--1 업체기본정보 : S -->
				<h4>법·제도</h4>
<ul>
	<% if ( rEtcEntity != null && rEtcEntity.getRowCnt() != 0) { %>
		<% for (int i = 0; i < rEntity.getRowCnt(); i++ ) { %>
			<li>
				<a href="javascript:go_bbs_view('<%=rEtcEntity.getValue(i, "CT_ID")%>', '<%= rEtcEntity.getValue(i, "BOARD_SEQ") %>');" >
				<span class="subject2">
					 <%="<span class='t_name'>["+rEtcEntity.getValue(i, "TITLE_HEADER")+"]</span>"+KJFUtil.java2html(KJFUtil.getTitleLimit(rEtcEntity.getValue(i, "SUBJECT"), 100))%>
					<%if (KJFDate.isNew(3, rEtcEntity.getValue(i,"INS_DT")) ) { %>
						<img src='../images/bbs/ico_new.gif' alt="새 글">
					<% } %>	
				</span></a>
				<span class="date2"><%=KJFDate.getTimeAsPattern(KJFDate.str2date(rEtcEntity.getValue(i, "INS_DT"), ""), "yyyy.MM.dd")%></span>
			</li>
		<% } %>
		
	<% } else { %>
		<li class="detail_none2">등록된 글이 없습니다.</li>
	<% } %>
</ul>	
</div>

			</div>
		   	<!-- 컨텐츠 : E -->
		   	
		</div>
		<!-- 중간컨텐츠 : E -->
  	
  	
	  	<!--퀵메뉴 : S -->
		<div id="quick"><%@ include file="../inc/quick.jsp" %></div>			
		<!--퀵메뉴 : E -->
 	
 	</div>
 	<!-- BODY : E -->
 	
  	<!-- copyright : S -->
 	<div id="foot"><%@ include file="../inc/copy.jsp" %></div> 
 	<!-- copyright : E -->
 	
</div>
<!-- 전체 : E -->

</body>
</html>
