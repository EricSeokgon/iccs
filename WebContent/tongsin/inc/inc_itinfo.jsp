<%@ page contentType="text/html;charset=utf-8"%>

<ul class="detail">
	<% if ( ITINFO != null && ITINFO.getRowCnt() != 0) { %>
		<% for (int i = 0; i < ITINFO.getRowCnt(); i++ ) { %>
			<li>
				<a href="javascript:go_bbs('<%=ITINFO.getValue(i, "CT_ID")%>', '<%= ITINFO.getValue(i, "BOARD_SEQ") %>');" >
				<span class="subject">
					<%=KJFUtil.java2html(KJFUtil.getTitleLimit(ITINFO.getValue(i, "SUBJECT"), 25))%>
					<%if (KJFDate.isNew(3, ITINFO.getValue(i,"INS_DT")) ) { %>
						<img src='../tongsin/images/main/icon_new.gif' alt="새 글">
					<% } %>	
				</span></a>
				<span class="date"><%=KJFDate.getTimeAsPattern(KJFDate.str2date(ITINFO.getValue(i, "INS_DT"), ""), "yyyy.MM.dd")%></span>
			</li>
		<% } %>
		
	<% } else { %>
		<li class="detail_none">등록된 글이 없습니다.</li>
	<% } %>
</ul>