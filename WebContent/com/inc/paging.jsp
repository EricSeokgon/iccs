<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="kjf.util.*" %>
<%@ taglib uri="/KJFTags" prefix="KTags" %>

<%!
	final static String PREV_MARK = "<img src='../images/bbs/page_pre.gif' border='0' align='absmiddle'>";
	final static String NEXT_MARK = "<img src='../images/bbs/page_next.gif' border='0' align='absmiddle'>";
%>
<%
	int pagePerScreen = 10; 	// 한 스크린 당 페이지 갯수
	int row_perPage   = 10; 	// 한 페이지당 목록갯수

	int totalScreen     = 1; 	// 전체 스크린 수
	int totalPage       = 1; 	// 전체 페이지 수
	int nowScreen       = 1; 	// 현재 스크린
	int n_page          = 1; 	// 현재 페이지
	int total_cnt       = 1; 	// 전체 검색결과 수
	int firstPageNumber = 1;  	// 현재 스크린의 시작번호
	int lastPageNumber  = 1; 	// 현재 스크린의 마지막번호

	// 파라미터 값으로 셋팅
	try {
		row_perPage = request.getParameter("rowPerPage") == null? 10 : Integer.parseInt(request.getParameter("rowPerPage"));
		total_cnt   = request.getParameter("totalCount") == null?  1 : Integer.parseInt(request.getParameter("totalCount"));
		n_page      = request.getParameter("nowPage")    == null?  1 : Integer.parseInt(request.getParameter("nowPage"));
	
	} catch (Exception ex) {
	}

	// 전체 페이지 수 구함.
	if (row_perPage == 0) {
		totalPage = 1;
		
	} else {
		totalPage = (int)Math.ceil((double)total_cnt / (double)row_perPage);
		
		if (totalPage == 0) totalPage = 1;
	}

	// 전체 스크린 수 구함
	totalScreen = (int)Math.ceil((double)totalPage / (double)pagePerScreen);
	
	if (totalScreen == 0) totalScreen = 1;

	// 현재 스크린 구함
	nowScreen = (int)Math.ceil((double)n_page / (double)pagePerScreen);

	// 현재 스크린의 시작번호
	firstPageNumber = (nowScreen * pagePerScreen) - (pagePerScreen - 1);
	
	// 현재 스크린의 마지막번호
	if (nowScreen == totalScreen) {
		lastPageNumber = totalPage;
		
	} else {
		lastPageNumber = nowScreen * pagePerScreen;
	}
%>

<div class="center" >
  			<span>
				<% if (nowScreen == 1) { %>
				<img src="../images/bbs/page_first.gif" alt="처음" />
				<% } else {%>
				<img src="../images/bbs/page_first.gif" alt="처음" onClick="javascript:movePage('1')" style="cursor:pointer" />
				<% } %>
				
		  	</span>
  			
			<span>
				<% if (nowScreen == 1) { %>
				<img src="../images/bbs/page_pre.gif" alt="이전" />
				
				<% } else {%>
				<img src="../images/bbs/page_pre.gif" alt="이전" onClick="javascript:movePage('<%=((nowScreen - 1) * 10)%>')" style="cursor:pointer" />
				<% } %>
		  	</span>

			<span align="center" width="15"  style="padding:0 5 0 5;">
					<% for (int i = firstPageNumber; i <= lastPageNumber ; i++) { %>				
						    
						<% if (i == n_page) { %>
							<span  class="page_now letter0"><%=i%></span>
							
						<% } else { %>
							<span><a href="javascript:movePage('<%=i%>')" class="page letter0"><%=i%></a></span>
						<% } %>

					<% } %>					
			</span>

	  		<span>
				<% if(nowScreen == totalScreen) { %>
				<img src="../images/bbs/page_next.gif" alt="다음" />
				<% } else { %>
				<img src="../images/bbs/page_next.gif" alt="다음" onClick="javascript:movePage('<%=(nowScreen * 10 + 1)%>')" style="cursor:pointer" />
				<% } %>
		  </span>

	  		<span align="right" >
				<% if(nowScreen == totalScreen) { %>
				<img src="../images/bbs/page_last.gif" alt="마지막" />
				<% } else { %>
				<img src="../images/bbs/page_last.gif" alt="마지막" onClick="javascript:movePage('<%=totalPage%>')" style="cursor:pointer" />
				<% } %>		
		  </span>

</div>
