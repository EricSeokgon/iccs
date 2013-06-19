<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="kjf.ops.*" %>
<%@ page import="kjf.util.*" %>
<%@ page import="sp.bbs.*" %>
<%@ page import="sp.uent.*" %>
<%@ page import="java.net.URLEncoder" %>

<%
	ReportEntity rEntity = KJFUtil.REntPrint( (ReportEntity)request.getAttribute("ListEntity") );	// 공무원센터 커뮤니티
	ReportEntity rEtcEntity = KJFUtil.REntPrint( (ReportEntity)request.getAttribute("ListEtcEntity") );	// 공무원센터 커뮤니티

	BbsParam pm = (BbsParam)request.getAttribute("pm");
	
	UserEnt  user=(UserEnt)session.getAttribute("user");
	
	String p_user_id = user.getUSER_ID();
	String p_user_pw = user.getUSER_PASSWORD();
	
	String go_url = "";
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
<link href="../css/form.css" rel="stylesheet" type="text/css" />
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<link href="../css/layout_frame.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">

	function go_bbs_view(ct_id, board_seq) {
		var p_url = "../bbs/BbsAction.do?cmd=BbsKView&CT_ID=" + ct_id + "&BOARD_SEQ=" + board_seq;
		result = window.open(p_url, "view_info" , "location=yes,  fullscreen , toolbar=yes, scrollbars=yes, resizable=yes, status=yes,menubar=yes,resizable=yes,left=0,top=0");
		result.focus();
	}	

</script>

<!-- 공통 : E -->
</head>
<body>
<!-- 전체 : S -->
  	<!-- BODY : S -->
	<div id="body">
		  	<!-- 컨텐츠 : S -->
			<div id="contents2">
			<!--01공사업등록정보 : S -->	
				<div id="cen02">	
				<!--1 업체기본정보 : S -->
				<br/>
				<h4><font size="3">센터커뮤니티</font></h4>
				<ul>
					<% if ( rEntity != null && rEntity.getRowCnt() != 0) { %>
						<% for (int i = 0; i < rEntity.getRowCnt(); i++ ) { 
								go_url = "/bbs/BbsAction.do?cmd=BbsKView&CT_ID="+rEntity.getValue(i, "CT_ID")+"&BOARD_SEQ="+rEntity.getValue(i, "BOARD_SEQ");
								go_url = URLEncoder.encode(go_url);
								
						%>
							<li>
								<a href="../login/LoginAction.do?cmd=Login&id=<%=p_user_id %>&password=<%=p_user_pw %>&encoding=Y&beforeURL=<%=go_url %>" target="_blank">
								<span class="subject2">
									 <%="<span class='t_name'>["+rEntity.getValue(i, "TITLE_HEADER")+"]</span>"+KJFUtil.java2html(KJFUtil.getTitleLimit(rEntity.getValue(i, "SUBJECT"), 27))%>
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
				<br/><br/><br/>
				<br/><br/><br/><br/><br/><br/>
				<h4><font size="3">법·제도</font></h4>
				<ul>
					<% if ( rEtcEntity != null && rEtcEntity.getRowCnt() != 0) { %>
						<% for (int i = 0; i < rEntity.getRowCnt(); i++ ) { 
								go_url = "/bbs/BbsAction.do?cmd=BbsKView&CT_ID="+rEntity.getValue(i, "CT_ID")+"&BOARD_SEQ="+rEntity.getValue(i, "BOARD_SEQ");
								go_url = URLEncoder.encode(go_url);
						%>
							<li>
								<a href="../login/LoginAction.do?cmd=Login&id=<%=p_user_id %>&password=<%=p_user_pw %>&encoding=Y&beforeURL=<%=go_url %>" target="_blank">
								<span class="subject2">
									 <%="<span class='t_name'>["+rEtcEntity.getValue(i, "TITLE_HEADER")+"]</span>"+KJFUtil.java2html(KJFUtil.getTitleLimit(rEtcEntity.getValue(i, "SUBJECT"), 27))%>
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


</body>
</html>
