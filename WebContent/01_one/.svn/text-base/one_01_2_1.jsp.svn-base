<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="kjf.ops.*" %>
<%@ page import="kjf.util.*" %>
<%@ page import="sp.regmgr.*" %>

<%
	ReportEntity rEntity = KJFUtil.REntPrint( (ReportEntity)request.getAttribute("rEntity") );	// 공사업등록 정보
	
	RegMgrParam pm = (RegMgrParam)request.getAttribute("pm");
	
	// 페이징 관련(필수)
	String nowPage    = KJFUtil.print(pm.getNowPage());
	String rowPerPage = KJFUtil.print(pm.getRowPerPage());
	String totalCount = KJFUtil.print(pm.getTotalCount());
	
	// 상단 플래쉬 링크 정보
	String top_pageNum  = "1";
	String top_sub      = "1";
	
	// 좌측 플래쉬 링크 정보
	String left_pageNum = "1";
	String left_sub     = "2";
	
	int int_rnum = KJFUtil.str2int(totalCount)-(KJFUtil.str2int(nowPage) *KJFUtil.str2int(rowPerPage) - KJFUtil.str2int(rowPerPage));
%>

<script language="JavaScript" type="text/javascript">

// 상세페이지
function viewDetail(chgbre_seq, recv_num) {
    var fm = document.fmParam;
    
    fm.action = "../regmgr/RegMgrAction.do?cmd=PubWorkRegReportView";
    fm.chgbre_seq.value = chgbre_seq;
    fm.recv_num.value   = recv_num;
    
    fm.submit();
}
</script>

<html lang="ko">
<head>
<title>공사업 등록기준 신고 페이지입니다.</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="robots" content="noindex,nofollow">
<meta name="author" content="부산광역시">
<meta name="keywords" content="정보통신공사업시스템">
<meta name="description" content="정보통신공사업시스템">
<meta http-equiv="imagetoolbar" content="no">

<!-- 공통 : S -->
<link href="../css/total.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="../com/js/topMenu.js" type="text/javascript"></script>
<script language="JavaScript" src="../flash/flash.js" type="text/javascript"></script>
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
					<a href="../"><img src="../images/common/icon_home.gif" alt="HOME" /></a> 원스톱민원센타 | 등록관리 | 공사업등록기준신고
				</p>
			</div>
			<!-- 현재위치 : E -->
	
			<!-- 서브타이틀 : S -->
			<div id="sub_title">
				<h3 class="cont01"><img src="../01_one/images/titile_01_2.gif" alt="공사업 등록 기준신고" /></h1>
			</div>
			<!-- 서브타이틀 : E -->
   
		  	<!-- 컨텐츠 : S -->
			<div id="contents">

				<form name=fmParam method="post" action="../regmgr/RegMgrAction.do.do" >
				<input type="hidden" name="cmd" value="PubWorkRegReportList">		
				<input type="hidden" name="nowPage" value="<%=nowPage%>">
				<input type="hidden" name="chgbre_seq">
				<input type="hidden" name="recv_num">

				<!-- 공사업 등록 기준신고 리스트 : S -->
				<h4>공사업 등록 기준신고 현황</h4>
				<table class="t2" summary="">
				    <caption></caption>
				    <colgroup>
				    <col style="width: 35px; text-align: center" />				    
				    <col style="" />
					<col style="width: 90px; text-align: center" />
				    <col style="width: 80px; text-align: center" />
					<col style="width: 80px; text-align: center" />
				    <col style="width: 100px; text-align: center" />
				    <col style="width: 90px; text-align: center" />
				    </colgroup>
				    <thead>
				        <tr>
				            <th scope="col">번호</th>
							<th scope="col">회사명</th>
							<th scope="col">접수번호</th>
							<th scope="col">접수일자</th>
				            <th scope="col">처리기한</th>	
							<th scope="col">담당자</th>				
							<th scope="col">진행상태</th>
				        </tr>
				    </thead>
				    <tbody>

						<% if (rEntity.getRowCnt() > 0 ) {%>
							<% for (int i = 0; i < rEntity.getRowCnt(); i++) { %>
							<tr>
						            <td align="center" class="letter0"><%=int_rnum--%></td>
									<td align="center"><%=KJFUtil.print(rEntity.getValue(i, "NAME")) %></td>
						            <td align="center" class="letter0"><a href="javascript:viewDetail('<%=rEntity.getValue(i, "CHGBRE_SEQ")%>','<%=rEntity.getValue(i, "RECV_NUM")%>')"><%=KJFUtil.print(rEntity.getValue(i, "RECV_NUM")) %></td>
								    <td align="center" class="letter0"><%=KJFUtil.print(KJFDate.getChangDatePattern(rEntity.getValue(i, "RECV_DT"), "yyyyMMdd", "yyyy/MM/dd"), "&nbsp;") %></td>
								    <td align="center" class="letter0"><%=KJFUtil.print(KJFDate.getChangDatePattern(rEntity.getValue(i, "PROC_LIM"), "yyyyMMdd", "yyyy/MM/dd"), "&nbsp;") %></td>
									<td align="center"><%=KJFUtil.print(rEntity.getValue(i, "NM")) %></td>
								    <td align="center" class="letter0"><%=KJFUtil.print(rEntity.getValue(i, "MOT_STE_NM")) %></td>
							</tr>
							<% } %>
						<% } else { %>
							<tr>
								<td colspan="7" align="center">등록된 정보가 없습니다.</td>
							</tr>
						<% } %>
				    </tbody>
				</table>
				<!-- 공사업 등록 기준신고 리스트 : E -->

				<!-- 페이징 : S -->
				<div class="center">
					<jsp:include page="../com/inc/paging.jsp" flush="true">
					  <jsp:param name="pagePerScreen" value="10"/>
					  <jsp:param name="rowPerPage" value="<%=rowPerPage%>"/>
					  <jsp:param name="totalCount" value="<%=totalCount%>"/>
					  <jsp:param name="nowPage" value="<%=nowPage %>"/>
					</jsp:include>	
				</div>
				<!-- 페이징 : E -->

				</form>
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
