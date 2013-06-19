<%@ page contentType="text/html;charset=utf-8"%>

<ul class="detail">
	<% if ( PDS != null && PDS.getRowCnt() != 0) { %>
		<% for (int i = 0; i < PDS.getRowCnt(); i++ ) { %>
			<li>
				<a href="javascript:go_bbs('<%=PDS.getValue(i, "CT_ID")%>', '<%= PDS.getValue(i, "BOARD_SEQ") %>');" >
				<span class="subject">
					<%=KJFUtil.java2html(KJFUtil.getTitleLimit(PDS.getValue(i, "SUBJECT"), 25))%>
					<%if (KJFDate.isNew(3, PDS.getValue(i,"INS_DT")) ) { %>
					<img src='../tongsin/images/main/icon_new.gif' alt="새 글">
					<% } %>	
				</span></a>
				<span class="date"><%=KJFDate.getTimeAsPattern(KJFDate.str2date(PDS.getValue(i, "INS_DT"), ""), "yyyy.MM.dd")%></span>
			</li>
		<% } %>
		
	<% } else { %>
		<li class="detail_none">등록된 글이 없습니다.</li>
	<% } %>
</ul>