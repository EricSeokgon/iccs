<%@ page contentType="text/html;charset=utf-8"%>
<ul class="title">
	<li><a href="javascript:go_bbs('NOTICE');"><img src="../images/main/title_notice_over.gif" alt="공지사항" /></a></li>
	<li><a href="#" onFocus="MM_showHideLayers('notice','','hide','news','','show','public','','hide')" onMouseOver="MM_showHideLayers('notice','','hide','news','','show','public','','hide')"><img src="../images/main/title_news.gif" alt="지역뉴스" /></a></li>
	<li><a href="#" onFocus="MM_showHideLayers('notice','','hide','news','','hide','public','','show')" onMouseOver="MM_showHideLayers('notice','','hide','news','','hide','public','','show')"><img src="../images/main/title_public.gif" alt="공사업공고" /></a></li>
</ul>

<ul class="detail">
	<% if ( NOTICE != null && NOTICE.getRowCnt() != 0) { %>
		<% for (int i = 0; i < NOTICE.getRowCnt(); i++ ) { %>
			<li>
				<a href="javascript:go_bbs('<%=NOTICE.getValue(i, "CT_ID")%>', '<%= NOTICE.getValue(i, "BOARD_SEQ") %>');" >
				<span class="subject">
					<%=KJFUtil.java2html(KJFUtil.getTitleLimit(NOTICE.getValue(i, "SUBJECT"), 25))%>
					<%if (KJFDate.isNew(3, NOTICE.getValue(i,"INS_DT")) ) { %>
						<img src='../images/bbs/ico_new.gif' alt="새 글">
					<% } %>	
				</span></a>
				<span class="date"><%=KJFDate.getTimeAsPattern(KJFDate.str2date(NOTICE.getValue(i, "INS_DT"), ""), "yyyy.MM.dd")%></span>
			</li>
		<% } %>
		
	<% } else { %>
		<li class="detail_none">등록된 글이 없습니다.</li>
	<% } %>
</ul>