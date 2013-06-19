<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="kjf.ops.*" %>
<%@ page import="kjf.util.*" %>
<%@ page import="sp.regmgr.*" %>

<%
	ReportEntity rEntity = KJFUtil.REntPrint( (ReportEntity)request.getAttribute("rEntity") );	// 공사업 등록 기준신고 정보
	ReportEntity cEntity = KJFUtil.REntPrint( (ReportEntity)request.getAttribute("cEntity") );	// 타시설공사업 정보
	
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
%>

<script language="JavaScript" type="text/javascript">

// 리스트 목록으로 처리한다./
function fn_goList(){
    var fm = document.fmParam;
    fm.action = "../regmgr/RegMgrAction.do?cmd=PubWorkRegReportList";
    fm.method = "post";
    
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
				
				<form name=fmParam method="post" action="../regmgr/RegMgrAction.do" >
				<input type="hidden" name="cmd"        value="PubWorkRegReportView">		
				<input type="hidden" name="nowPage"    value="<%=nowPage%>">
				<input type="hidden" name="rowPerPage" value="<%=rowPerPage%>">
				
				<!--1신고업체기본정보 : S -->
				<h4>신고업체기본정보</h4>
			  	<table  class="t2" summary="신고업체기본정보 접수일자 처리상태 상호 전화번호 대표자 팩스번호 소재지주소를 나타내는 표">
                    <caption class="caption">신고업체기본정보</caption>
					<colgroup>
						<col style="width: 140px; " />
						<col style="" />
						<col style="width: 90px; text-align: center" />
						<col style="" />
					</colgroup>
					<tbody>
					<tr>
                      <th>업체명</th>
                      <td><%=KJFUtil.print(rEntity.getValue(0, "NAME")) %></td>
                      <th>대표자</th>
                      <td><%=KJFUtil.print(rEntity.getValue(0, "REP_NM_KOR")) %></td>
                    </tr>
					<tr>
                      <th>공사업 등록번호</th>
                      <td class="letter0"><%=KJFUtil.print(rEntity.getValue(0, "COI_WRT_NUM")) %></td>
                      <th>사업자번호</th>
                      <td class="letter0"><%=KJFUtil.print(rEntity.getValue(0, "MANA_NUM")) %></td>
                    </tr>
					<tr>
                      <th>전화번호</th>
                      <td class="letter0"><%=KJFUtil.print(rEntity.getValue(0, "ADDR_TEL_NUM")) %></td>
                      <th>팩스번호</th>
                      <td class="letter0"><%=KJFUtil.print(rEntity.getValue(0, "ADDR_FAX_NUM")) %></td>
                    </tr>
					<tr>
                      <th>주소지</th>
                      <td colspan="3" class="letter0">
					  	(<%=KJFUtil.setNumFormat(KJFUtil.print(rEntity.getValue(0, "ADDR_POST_NUM"))) %>)
						<%=KJFUtil.print(rEntity.getValue(0, "ADDR_ADDR")) %>
						<%=KJFUtil.print(rEntity.getValue(0, "ADDR_DETAIL_ADDR")) %>
					  </td>
                    </tr>
					</tbody>
                  </table>
			  	<!--1신고업체기본정보 : E -->
				
				<!-- 신고정보 : S -->
				<h4 class="continue">타 시설공사업</h4>
				<table class="t2" summary="">
				    <caption>타 시설공사업</caption>
				    <colgroup>
						<col style="width: 40px; text-align: center" />	
						<col style="text-align: center" />
						<col style="text-align: center" />
						
					</colgroup>
				    <thead>
				        <tr>
				            <th scope="col">번호</th>
				            <th scope="col">타 시설공사업</th>
				            <th scope="col">타 시설공사업 등록번호</th>
				        </tr>
				    </thead>
				    <tbody>
						<% if (cEntity.getRowCnt() > 0 ) {%>
							<% for (int i = 0; i < cEntity.getRowCnt(); i++) { %>
							<tr>
					            <td align="center"><%=(i+1)%></td>
					            <td align="center" class="letter0"><%=KJFUtil.print(cEntity.getValue(i, "SUB_CODE_NM")) %></td>
							    <td align="center" class="letter0"><%=KJFUtil.print(cEntity.getValue(i, "SUB_WRT_NUM")) %></td>
							</tr>
							<% } %>

						<% } else { %>
							<tr>
								<td colspan="3">등록된 정보가 없습니다.</td>
							</tr>
						<% } %>
					</tbody>
				</table>
				<!-- 신고정보 : E -->
			  	
			  	<!-- 신고정보 : S -->
				<h4 class="continue">신고정보</h4>
				<table class="t2" summary="">
				    <caption>신고정보</caption>
				    <colgroup><col style="width: 120px; text-align: center" /></colgroup>
				    <thead>
				        <tr>
				            <th scope="col">접수번호</th>
				            <th scope="col">접수일자</th>
				            <th scope="col">처리기한</th>
				            <th scope="col">진행상태</th>
				        </tr>
				    </thead>
				    <tbody>
						<tr>
				            <td align="center"><%=KJFUtil.print(rEntity.getValue(0, "RECV_NUM"), "&nbsp;") %></td>
				            <td align="center" class="letter0"><%=KJFUtil.print(KJFDate.getChangDatePattern(rEntity.getValue(0, "RECV_DT"), "yyyyMMdd", "yyyy/MM/dd"), "&nbsp;") %></td>
						    <td align="center" class="letter0"><%=KJFUtil.print(KJFDate.getChangDatePattern(rEntity.getValue(0, "PROC_LIM"), "yyyyMMdd", "yyyy/MM/dd"), "&nbsp;") %></td>
						    <td align="center"><%=KJFUtil.print(rEntity.getValue(0, "MOT_STE_NM"), "&nbsp;") %></td>
						</tr>
					</tbody>
				</table>
				<!-- 신고정보 : E -->
				
				<!-- 목록버튼 : S -->
				<div class="right">
					<a href="javascript:fn_goList()"><img src="../images/bbs/btn_list.gif" alt="목록"></a>
				</div>
				<!-- 목록버튼 : E -->
				
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
