<%@ page contentType="text/html;charset=utf-8"%>

<ul class="title">
	<li><a href="#" onFocus="MM_showHideLayers('notice','','show','news','','hide','public','','hide')" onMouseOver="MM_showHideLayers('notice','','show','news','','hide','public','','hide')"><img alt="공지사항" src="../images/main/title_notice.gif"/></a></li>
	<li><a href="#" onFocus="MM_showHideLayers('notice','','hide','news','','show','public','','hide')" onMouseOver="MM_showHideLayers('notice','','hide','news','','show','public','','hide')"><img alt="지역뉴스" src="../images/main/title_news.gif"/></a></li>
	<li><a href="javascript:go_bbs('REG_PUBLIC')"><img src="../images/main/title_public_over.gif" alt="공사업공고" /></a></li>
</ul>

<ul class="detail">
	<% if ( REG_PUBLIC != null && REG_PUBLIC.getRowCnt() != 0) { %>
		<% for (int i = 0; i < REG_PUBLIC.getRowCnt(); i++ ) { %>
			<li>
				<a href="javascript:go_bbs_view('<%=REG_PUBLIC.getValue(i, "CT_ID")%>', '<%= REG_PUBLIC.getValue(i, "BOARD_SEQ") %>');" >
				<span class="subject">
					<%=KJFUtil.java2html(KJFUtil.getTitleLimit(REG_PUBLIC.getValue(i, "SUBJECT"), 25))%>
					<%if (KJFDate.isNew(3, REG_PUBLIC.getValue(i,"INS_DT")) ) { %>
						<img src='../images/bbs/ico_new.gif'>
					<% } %>
				</span></a>
				<span class="date"><%=KJFDate.getTimeAsPattern(KJFDate.str2date(REG_PUBLIC.getValue(i, "INS_DT"), ""), "yyyy.MM.dd")%></span>
			</li>
		<% } %>
		
	<% } else { %>
		<li class="detail_none">등록된 글이 없습니다.</li>
	<% } %>
</ul>