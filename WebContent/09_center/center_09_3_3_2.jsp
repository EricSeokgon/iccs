<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="kjf.ops.*" %>
<%@ page import="kjf.util.*" %>
<%@ page import="sp.illegality.*" %>

<%
	ReportEntity rEntity      = KJFUtil.REntPrint( (ReportEntity)request.getAttribute("rEntity") );
	ReportEntity reportEntity = KJFUtil.REntPrint( (ReportEntity)request.getAttribute("reportEntity") );
	
	IllegalityParam pm = (IllegalityParam)request.getAttribute("pm");
	
	// 페이징 관련(필수)
	String nowPage    = KJFUtil.print(pm.getNowPage());
	String rowPerPage = KJFUtil.print(pm.getRowPerPage());
	String totalCount = KJFUtil.print(pm.getTotalCount());
	
	// 상단 플래쉬 링크 정보
	String top_pageNum  = "1";
	String top_sub      = "8";
	
	// 좌측 플래쉬 링크 정보
	String left_pageNum = "7";
	String left_sub     = "3";
%>

<script language="JavaScript" type="text/javascript">

/***************************************************************************
* Title   : 목록 
* Content : 리스트 목록으로 처리한다.
****************************************************************************/
function fn_goList(){
    var fm = document.fmParam;
    fm.action = "../illegality/IllegalityAction.do?cmd=IllNegFineList";
    fm.method = "post";
    
    fm.submit();
}
</script>

<html lang="ko">
<head>
<title>등록취소 페이지입니다.</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="robots" content="noindex,nofollow">
<meta name="author" content="부산광역시">
<meta name="keywords" content="정보통신공사업시스템">
<meta name="description" content="정보통신공사업시스템">
<meta http-equiv="imagetoolbar" content="no">

<!-- 공통 : S -->
<link href="../css/total.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="../com/js/topMenu.js" type="text/javascript"></script>
<script language="JavaScript" src="../flash/flash_pub.js" type="text/javascript"></script>
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
					<a href="../"><img src="../images/common/icon_home.gif" alt="HOME"></a> 원스톱민원센타 | 위법관리 | 과태료부과
				</p>
			</div>
			<!-- 현재위치 : E -->
	
			<!-- 서브타이틀 : S -->
			<div id="sub_title">
				<h3 class="cont01"><img src="../09_center/images/titile_09_3_3.gif" alt="과태료부과"></h1>
			</div>
			<!-- 서브타이틀 : E -->
   
		  	<!-- 컨텐츠 : S -->
			<div id="contents">

				<form name=fmParam method="post" action="../illegality/IllegalityAction.do" >
				<input type="hidden" name="cmd"        value="IllBusiSusView">		
				<input type="hidden" name="nowPage"    value="<%=nowPage%>">
				<input type="hidden" name="rowPerPage" value="<%=rowPerPage%>">

			<!-- 과태료부과 : S -->
				<!-- 행정처분결과 상세 결과 : S -->
				<h4>행정처분결과</h4>
				<table class="t2" summary="">
				    <caption></caption>
				    <colgroup><col style="width: 120px; text-align: center; " /></colgroup>
				    <tbody>
				        <tr>
				            <th>행정처분일자</th>
				            <td class="letter0"><%=KJFUtil.print(KJFDate.getChangDatePattern(rEntity.getValue(0, "DISPO_DT"), "yyyyMMdd", "yyyy/MM/dd"), "&nbsp;") %></td>
				        </tr>
				        <tr>
				            <th>과태료</th>
				            <td class="letter0"><%=KJFUtil.money(KJFUtil.str2long(rEntity.getValue(0, "NEFI"))) %>원</td>
				        </tr>
				    	<tr>
				            <th>납부기한</th>
				            <td class="letter0"><%=KJFUtil.print(rEntity.getValue(0, "PAY_LIM"), "&nbsp;") %></td>
				        </tr>
				        <tr>
				            <th>행정처분사유</th>
				            <td><%=KJFUtil.print(rEntity.getValue(0, "DISPO_CAUSE"), "&nbsp;") %></td>
				        </tr>
				        <tr>
				            <th>법적근거</th>
				            <td><%=KJFUtil.print(rEntity.getValue(0, "LEG_BAS"), "&nbsp;") %></td>
				        </tr>
				    </tbody>
				</table>
				<!-- 행정처분결과 상세 결과 : E -->
				
				<!-- 행정처분 통지관리 : S -->
				<h4 class="continue">행정처분 통지관리</h4>
				<table class="t2" summary="">
				    <caption></caption>
				    <colgroup>
						<col style="width: 35px; text-align: center" />
						<col style="width: ''; text-align: center" />
						<col style="width: 90px; text-align: center" />
						<col style="width: 90px; text-align: center" />
						<col style="width: 90px; text-align: center" />
					</colgroup>
				    <thead>
				        <tr>
							<th scope="col">번호</th>
				            <th scope="col">통보종류</th>
				            <th scope="col">통보일자</th>
				            <th scope="col">처리기한</th>
				            <th scope="col">통보여부</th>
				        </tr>
				    </thead>
				    <tbody>
						<% if (reportEntity.getRowCnt() > 0 ) {%>
							<% for (int i = 0; i < reportEntity.getRowCnt(); i++) { %>
							<tr>
								<td align="center"><%=(i + 1) %></td>
					            <td align="center"><%=KJFUtil.print(reportEntity.getValue(i, "NOTE_CLASS_CODE_NM")) %></td>
					            <td align="center" class="letter0"><%=KJFUtil.print(KJFDate.getChangDatePattern(reportEntity.getValue(i, "SEND_DT"), "yyyyMMdd", "yyyy/MM/dd"), "&nbsp;") %></td>
							    <td align="center" class="letter0"><%=KJFUtil.print(KJFDate.getChangDatePattern(reportEntity.getValue(i, "PROC_LIM"), "yyyyMMdd", "yyyy/MM/dd"), "&nbsp;") %></td>
							    <td align="center"><%=KJFUtil.print(reportEntity.getValue(i, "RECV_YN")) %></td>
							</tr>
							<% } %>

						<% } else { %>
							<tr>
								<td colspan="5" align="center">등록된 정보가 없습니다.</td>
							</tr>
						<% } %>
					</tbody>
				</table>
				<!-- 행정처분 통지관리 : E -->
				
				<!-- 목록버튼 : S -->
				<div class="right">
					<a href="javascript:fn_goList()"><img src="../images/bbs/btn_list.gif" alt="목록"></a>
				</div>
				<!-- 목록버튼 : E -->
			<!-- 과태료부과 : E -->
				
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
