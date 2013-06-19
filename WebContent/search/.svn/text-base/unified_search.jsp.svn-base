<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="kjf.util.*" %>
<%@ page import="sp.search.*" %>

<%
	SearchParam pm = (SearchParam)request.getAttribute("pm");

	List<SearchBean> boardXmlList = (List)request.getAttribute("boardXmlList");
	List<SearchBean> formXmlList  = (List)request.getAttribute("formXmlList");
	List<SearchBean> webXmlList   = (List)request.getAttribute("webXmlList");
	
	int boardXmlCnt = (Integer)request.getAttribute("boardXmlCnt");
	int formXmlCnt  = (Integer)request.getAttribute("formXmlCnt");
	int webXmlCnt   = (Integer)request.getAttribute("webXmlCnt");
	
	// 페이징 관련(필수)
	String nowPage    = KJFUtil.print(pm.getNowPage());
	String rowPerPage = KJFUtil.print(pm.getRowPerPage());
	String totalCount = KJFUtil.print(pm.getTotalCount());

	// 상단 플래쉬 링크 정보
	String top_pageNum  = "";
	String top_sub      = "";
	
	// 좌측 플래쉬 링크 정보
	String left_pageNum = "";
	String left_sub     = "";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="ko">
<head>
<title>통합검색 페이지입니다.</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="robots" content="noindex,nofollow">
<meta name="author" content="부산광역시">
<meta name="keywords" content="정보통신공사업시스템">
<meta name="description" content="정보통신공사업시스템">
<meta http-equiv="imagetoolbar" content="no">

<!-- 공통 : S -->
<link href="../css/search.css" rel="stylesheet" type="text/css" >
<script src="../com/js/com.js" type="text/javascript"></script>
<script src="../com/js/common_util.js" type="text/javascript"></script>
<script src="../com/js/search.js" type="text/javascript"></script>
<script src="../com/js/topMenu.js" type="text/javascript"></script>
<script src="../flash/flash.js" type="text/javascript"></script>
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
		</ul>
	</div>
		
	<!-- header : S-->
	<div id="header">
  		<div id="top_navi"><%@ include file="../inc/top.jsp" %></div>	
  		<div id="login"><%@ include file="../inc/top_login.jsp" %></div>
  	</div>
  	<!-- header : E -->
  
    <!-- 서브 비주얼 : S-->
  	<div id="sub_visual"><img src="../images/layout/sub_visual.jpg" /></div>
 	<!-- 서브 비주얼 : E -->
 	 
  	<!-- BODY : S -->
	<div id="body">
  	
	  	<!-- 중간컨텐츠 : S -->
   		<div id="pull_contents">
			<a name="contents"></a>			
			
			<!-- 현재위치 : S -->
			<div id="con_head">
				<p class="position">
					<a href="../"><img src="../images/common/icon_home.gif" alt="HOME" /></a> 통합검색
				</p>
			</div>
			<!-- 현재위치 : E -->
			
			
			<!-- tab : S -->
			<div id="tab">
				<ul>
					<li class='tab_title'><img src="../images/search/tab_total_over.gif" alt="통합검색"></li>
					<li class='tab_title'><a href="javascript:fn_GoSearch('WebPageSearch')"><img src="../images/search/tab_page.gif" alt="웹페이지 검색"></a></li>
					<li class='tab_title'><a href="javascript:fn_GoSearch('BoardSearch')"><img src="../images/search/tab_notice.gif" alt="게시물 검색"></a></li>
					<li class='tab_title'><a href="javascript:fn_GoSearch('FormSearch')"><img src="../images/search/tab_data.gif" alt="서식자료 검색"></a></li>
				</ul>	
			</div>
			<!-- tab : E -->
			
			
			
			<!-- input box : S -->
			<form name="searchParam" action="../search/SearchAction.do" method="post">
			<input type="hidden" name="cmd"	value="UnifiedSearch">
			<input type="text" name="none" style="display:none">
			
			<div id="input_search"">
				<input type="text" name="scTextValue" value="<%=KJFUtil.print(pm.getScTextValue())%>" onkeyup="if(event.keyCode == 13) fn_search();" title="검색값을 입력해주세요" class="search" >
				<a href="javascript:fn_search()"><img src="../images/search/input_search_btn.gif" alt="검색" align="absmiddle"></a>
			</div>
			
			</form>
			<!-- input box : E -->
			
			<form name="fmParam" action="../search/SearchAction.do" method="post">
			<input type="hidden" name="scTextValue" value="<%=KJFUtil.print(pm.getScTextValue())%>">
			<input type="hidden" name="cmd">
			<input type="hidden" name="nowPage">
			
			<!-- 검색결과 : S -->
			<div id="search_result">검색어  &quot;<span class='font_orange'><%=KJFUtil.print(pm.getScTextValue())%></span>&quot;에 대한 검색 결과 <span class='font_orange'>총 <%= boardXmlCnt + formXmlCnt +  webXmlCnt %>건</span>이 검색되었습니다.</div>
			<!-- 검색결과 : E -->
			
			<ul>
				<li class='p10'>
				  	<!-- 웹페이지검색 : S -->
					<div id="search_page">
						<div class="search_list_01">
							<span class='search_list_text'>웹페이지 검색  - <span class="font_orange"><%=webXmlCnt%></span>건</span>
							<div class="search_all_view"><a href="javascript:fn_GoSearch('WebPageSearch')"><img src="../images/search/btn_all_view.gif" alt="웹페이지 검색 전체 결과보기"></a></div><br />
						</div>
						<ul class="result_page">
							<% if(webXmlList.size() > 0) { %>	
								<% for(int i = 0; i < webXmlList.size(); i++) {  %>
									<%
										SearchBean scBean = webXmlList.get(i);
									%>
									<li class="list_style1">
										<a href="../hms/HmsAction.do?cmd=HmsView&menu_id=<%=scBean.getMenu_id()%>">홈 
										<% if( !KJFUtil.print(scBean.getL_menu()).equals("") ) out.print( " > " + KJFUtil.print(scBean.getL_menu()) );%>
										<% if( !KJFUtil.print(scBean.getM_menu()).equals("") ) out.print( " > " +  KJFUtil.print(scBean.getM_menu()) );%>
										<% if( !KJFUtil.print(scBean.getS_munu()).equals("") ) out.print( " > " +  KJFUtil.print(scBean.getS_munu()) );%></a>
										<a href="../hms/HmsAction.do?cmd=HmsView&menu_id=<%=scBean.getMenu_id()%>" target="_blank"><img src="../images/search/btn_new_view.gif" alt="새창"></a>
									</li>
								<% } %>
								
							<% } else { %>
								<li class="list_style1"><span class='list_none'>검색된 결과가 없습니다. 다른 검색어를 사용하시기 바랍니다.</span></li>
							<% } %>
						</ul>
					</div>
					<!-- 웹페이지검색 : E -->
				</li>
			
				<li class='p10'>
				  	<!-- 게시물검색 : S -->
					<div id="search_contents">
						<div class='search_list_01'>
							<span class='search_list_text'>게시물 검색  - <span class="font_orange"><%=boardXmlCnt%></span>건</span>
							<div class="search_all_view"><a href="javascript:fn_GoSearch('BoardSearch')"><img src="../images/search/btn_all_view.gif" alt="게시물 검색 전체 결과보기"></a></div>
						</div>
						<ul class="result_page">
							<% if(boardXmlList.size() > 0) { %>	
								<% for(int i = 0; i < boardXmlList.size(); i++) {  %>
									<%
										SearchBean scBean = boardXmlList.get(i);
									%>
									<li class="list_style2">
										<a href="../bbs/BbsAction.do?cmd=BbsKView&CT_ID=<%=KJFUtil.print(scBean.getCt_id())%>&BOARD_SEQ=<%=KJFUtil.print(scBean.getBoard_seq())%>"><%=KJFUtil.print(scBean.getSubject()) %></a>
										<a href="../bbs/BbsAction.do?cmd=BbsKView&CT_ID=<%=KJFUtil.print(scBean.getCt_id())%>&BOARD_SEQ=<%=KJFUtil.print(scBean.getBoard_seq())%>" target="_blank"><img src="../images/search/btn_new_view.gif" alt="새창"></a>
										<ul class="detail_style1">
											<li><%=KJFUtil.print(scBean.getContent())%></li>
											<li class="page_url1">http://www.net.go.kr/bbs/BbsAction.do?cmd=BbsKView&CT_ID=<%=KJFUtil.print(scBean.getCt_id())%>&BOARD_SEQ=<%=KJFUtil.print(scBean.getBoard_seq())%></li>
										</ul>
									</li>
								<% } %>
								
							<% } else { %>
								<li class="list_style2"><span class='list_none'>검색된 결과가 없습니다. 다른 검색어를 사용하시기 바랍니다.</span></li>
							<% } %>
						</ul>
					</div>
				   	<!-- 게시물검색 : E -->
				</li>
			
				<li class='p10'>
				  	<!-- 서식자료검색 : S -->
					<div id="search_data">
						<div class='search_list_01'>
							<span class='search_list_text'>서식자료 검색  - <span class="font_orange"><%=formXmlCnt%></span>건</span>
							<div class="search_all_view"><a href="javascript:fn_GoSearch('FormSearch')"><img src="../images/search/btn_all_view.gif" alt="서식자료 전체 결과보기"></a></div>
						</div>
						<ul class="result_page">

							<% if(formXmlList.size() > 0) { %>	
								<% for(int i = 0; i < formXmlList.size(); i++) {  %>
									<%
										SearchBean scBean = formXmlList.get(i);
									%>
									<li class="list_style3">
										<a href="../bbs/BbsAction.do?cmd=BbsKView&CT_ID=<%=KJFUtil.print(scBean.getCt_id())%>&BOARD_SEQ=<%=KJFUtil.print(scBean.getBoard_seq())%>"><%=KJFUtil.print(scBean.getSubject()) %></a>
										<a href="../bbs/BbsAction.do?cmd=BbsKView&CT_ID=<%=KJFUtil.print(scBean.getCt_id())%>&BOARD_SEQ=<%=KJFUtil.print(scBean.getBoard_seq())%>" target="_blank"><img src="../images/search/btn_new_view.gif"></a>
										<ul class="detail_style1">
											<li><%=KJFUtil.print(scBean.getContent())%></li>
											<li class="page_url2">http://www.net.go.kr/bbs/BbsAction.do?cmd=BbsKView&CT_ID=<%=KJFUtil.print(scBean.getCt_id())%>&BOARD_SEQ=<%=KJFUtil.print(scBean.getBoard_seq())%></li>
										</ul>
									</li>
								<% } %>
								
							<% } else { %>
								<li class="list_style3"><span class='list_none'>검색된 결과가 없습니다. 다른 검색어를 사용하시기 바랍니다.</span></li>
							<% } %>
						</ul>
					</div>
				   	<!-- 서식자료검색 : E -->
				</li>
			</ul>
			</form>
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
