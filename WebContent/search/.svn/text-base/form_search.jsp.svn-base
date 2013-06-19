<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="kjf.util.*" %>
<%@ page import="sp.search.*" %>

<%
	SearchParam pm = (SearchParam)request.getAttribute("pm");

	List<SearchBean> xmllist = (List)request.getAttribute("xmllist");
	
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

<html>
<head>
<title>서식자료 검색 페이지입니다.</title>

<!-- 공통 : S -->
<link href="../css/search.css" rel="stylesheet" type="text/css" >
<script language="JavaScript" src="../com/js/com.js" type="text/javascript"></script>
<script language="JavaScript" src="../com/js/common_util.js" type="text/javascript"></script>
<script language="JavaScript" src="../com/js/search.js" type="text/javascript"></script>
<script language="JavaScript" src="../com/js/topMenu.js" type="text/javascript"></script>
<script language="JavaScript" src="../flash/flash.js" type="text/javascript"></script>
<!-- 공통 : E -->

</head>
<body>

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
   		<div id="pull_contents"><a name="contents"></a>
			
			
			
				<!-- 현재위치 : S -->
				<div id="con_head">
					<p class="position">
						<a href="../"><img src="../images/common/icon_home.gif" alt="HOME" /></a> 통합검색 | 서식자료 검색
					</p>
				</div>
				<!-- 현재위치 : E -->
			
			
				<!-- tab : S -->
				<div id="tab">
					<ul>
						<li class='tab_title'><a href="javascript:fn_GoSearch('UnifiedSearch')"><img src="../images/search/tab_total.gif" alt="통합검색"></a></li>
						<li class='tab_title'><a href="javascript:fn_GoSearch('WebPageSearch')"><img src="../images/search/tab_page.gif" alt="웹페이지 검색"></a></li>
						<li class='tab_title'><a href="javascript:fn_GoSearch('BoardSearch')"><img src="../images/search/tab_notice.gif" alt="게시물 검색"></a></li>
						<li class='tab_title'><img src="../images/search/tab_data_over.gif" alt="서식자료 검색"></li>
					</ul>	
				</div>
				<!-- tab : E -->
			
				<!-- input box : S -->
				<form name="searchParam" action="../search/SearchAction.do?cmd=UnifiedSearch" method="post">
				<input type="text" name="none" style="display:none">
				<div id="input_search"">
					<input type="text" name=scTextValue value="<%=KJFUtil.print(pm.getScTextValue())%>" onkeyup="if(event.keyCode == 13) fn_search();" title="검색값을 입력해주세요" class="search" >
					<a href="javascript:fn_search()"><img src="../images/search/input_search_btn.gif" alt="검색" align="absmiddle"></a>
				</div>
				</form>
				<!-- input box : E -->
				
				<!-- 서식자료 검색 : S -->
				<form name="fmParam" action="../search/SearchAction.do" method="post">
				<input type="hidden" name="scTextValue" value="<%=KJFUtil.print(pm.getScTextValue())%>">
				<input type="hidden" name="nowPage" value="<%=nowPage%>">
				<input type="hidden" name="cmd"	value="FormSearch">

				<ul>
				<li class='p10'>				  	
					<div id="search_page">
						<div class="search_list_01">
							<span class='search_list_text'>서식자료 검색  - <span class="font_orange"><%=KJFUtil.print(pm.getTotalCount(), "0")%></span> 건</span>
							<div class="search_all_view"></div><br />
						</div>
						<ul class="result_page">
	
							<% if(xmllist.size() > 0) { %>
		
								<% for(int i = 0; i < xmllist.size(); i++) {  %>
									<%
										SearchBean scBean = xmllist.get(i);
									%>
									<li class="list_style3">
										<a href="../bbs/BbsAction.do?cmd=BbsKView&CT_ID=<%=KJFUtil.print(scBean.getCt_id())%>&BOARD_SEQ=<%=KJFUtil.print(scBean.getBoard_seq())%>"><%=KJFUtil.print(scBean.getSubject()) %></a>
										<a href="../bbs/BbsAction.do?cmd=BbsKView&CT_ID=<%=KJFUtil.print(scBean.getCt_id())%>&BOARD_SEQ=<%=KJFUtil.print(scBean.getBoard_seq())%>" target="_blank"><img src="../images/search/btn_new_view.gif" alt="새창"></a>
										<ul class="detail_style1">
											<li><%=KJFUtil.print(scBean.getSubject()) %></li>
											<li class="page_url2">http://www.net.go.kr/bbs/BbsAction.do?cmd=BbsKView&CT_ID=<%=KJFUtil.print(scBean.getCt_id())%>&BOARD_SEQ=<%=KJFUtil.print(scBean.getBoard_seq())%></li>
										</ul>
									</li>
								<% } %>
							
							<% } else { %>
								<li class="list_style2"><span class='list_none'>검색된 결과가 없습니다. 다른 검색어를 사용하시기 바랍니다.</span></li>
							<% } %>
						</ul>
					</div>
					
				</li>
				
				<li id='search_result_paging'>	
					<jsp:include page="../com/inc/paging.jsp" flush="true">
					  <jsp:param name="pagePerScreen" value="10"/>
					  <jsp:param name="rowPerPage" value="<%=rowPerPage%>"/>
					  <jsp:param name="totalCount" value="<%=totalCount%>"/>
					  <jsp:param name="nowPage" value="<%=nowPage %>"/>
					</jsp:include>					
				</li>
			</ul>
			
			</form>
			<!-- 서식자료 검색 : E -->
		   	
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
