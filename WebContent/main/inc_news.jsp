<%@ page contentType="text/html;charset=utf-8"%>

<ul class="title">
	<li><a href="#" onFocus="MM_showHideLayers('notice','','show','news','','hide','public','','hide')" onMouseOver="MM_showHideLayers('notice','','show','news','','hide','public','','hide')"><img alt="공지사항" src="../images/main/title_notice.gif"/></a></li>
	<li><a href="javascript:go_bbs('NEWS')"><img src="../images/main/title_news_over.gif" alt="지역뉴스" /></a></li>
	<li><a href="#" onFocus="MM_showHideLayers('notice','','hide','news','','hide','public','','show')" onMouseOver="MM_showHideLayers('notice','','hide','news','','hide','public','','show')"><img alt="공사업공고" src="../images/main/title_public.gif"/></a></li>
</ul>

<ul class="detail">
	<% if ( NEWS != null && NEWS.getRowCnt() != 0) { %>

		<% for (int i = 0; i < NEWS.getRowCnt(); i++ ) { %>
			<li>
				<a href="javascript:go_bbs_view('<%=NEWS.getValue(i, "CT_ID")%>', '<%= NEWS.getValue(i, "BOARD_SEQ") %>');" >
				<span class="subject">
					<%=KJFUtil.java2html(KJFUtil.getTitleLimit(NEWS.getValue(i, "SUBJECT"), 25))%>
					<%if (KJFDate.isNew(3, NEWS.getValue(i,"INS_DT")) ) { %>
						<img src='../images/bbs/ico_new.gif' alt="새 글">
					<% } %>	
				</span></a>
				<span class="date"><%=KJFDate.getTimeAsPattern(KJFDate.str2date(NEWS.getValue(i, "INS_DT"), ""), "yyyy.MM.dd")%></span>
			</li>
		<% } %>

	<% } else { %>
		<li class="detail_none">등록된 글이 없습니다.</li>
	<% } %>
</ul>