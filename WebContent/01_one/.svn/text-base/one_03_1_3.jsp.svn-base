<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="kjf.ops.*" %>
<%@ page import="kjf.util.*" %>
<%@ page import="sp.usebefore.*" %>
<%@ include file="../inc/user_inc.jsp" %>
<%
	ReportEntity rEntity = KJFUtil.REntPrint( (ReportEntity)request.getAttribute("rEntity") );	// 검사결과
	ReportEntity pEntity = KJFUtil.REntPrint( (ReportEntity)request.getAttribute("pEntity") );	// 구내통신선로설비
	ReportEntity bEntity = KJFUtil.REntPrint( (ReportEntity)request.getAttribute("bEntity") );	// 방송공동수신설비
	ReportEntity mEntity = KJFUtil.REntPrint( (ReportEntity)request.getAttribute("mEntity") );	// 이동통신구내선로설비
	
	UseBeforeParam pm = (UseBeforeParam)request.getAttribute("pm");
	
	// 페이징 관련(필수)
	String nowPage    = KJFUtil.print(pm.getNowPage());
	String rowPerPage = KJFUtil.print(pm.getRowPerPage());
	String totalCount = KJFUtil.print(pm.getTotalCount());

	// 상단 플래쉬 링크 정보
	String top_pageNum  = "1";
	String top_sub      = "3";
	
	// 좌측 플래쉬 링크 정보
	String left_pageNum = "3";
	String left_sub     = "1";

%>

<html lang="ko">
<head>
<title>사용전검사 현황 페이지입니다.</title>
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
<script language="JavaScript" src="../com/js/ozViewerPop.js" type="text/javascript"></script>
<script language="JavaScript" type="text/javascript">

//사용전검사 접수내용
function fn_goUseBeforeChkContent(){
    var fm = document.fmParam;
    fm.action = "../usebefore/UseBeforeAction.do?cmd=UseBeforeChkContent";
    fm.method = "post";    
    fm.submit();
}

// 감리대상 건축물관리
function fn_goUseBeforeBuildMgr(){
    var fm = document.fmParam;
    fm.action = "../usebefore/UseBeforeAction.do?cmd=UseBeforeBuildMgr";
    fm.method = "post";
    
    fm.submit();
}

// 목록
function fn_goList(){
    var fm = document.fmParam;
    fm.action = "../usebefore/UseBeforeAction.do?cmd=UseBeforeChkStatus";
    fm.method = "post";
    
    fm.submit();
}
//츨력 처리
function fn_print(){
 args = new Array();

	var fm = document.fmParam;

	var ozFile 		= "/BeforeInvest/InvestR_home.ozr";
	var ozOdiname 	= "Q)InvestR_home";
	<%
		String ip = request.getRemoteHost();
		String ip_result = "N";
		if ("99".equals(ip.substring(0,ip.indexOf(".")).trim())){ //행망일경우
			ip_result ="Y";
		}
	%>
	args[0] = "RECV_NUM=" + fm.scRecvNum.value;
	args[1] = "SIDO_CODE=" + "<%=user.getSIDO_CODE()%>";
	args[2] = "SIGUNGU_CODE=" +  "<%=user.getSIGUNGU_CODE()%>";
	args[3] = "IP_CHECK=" +  "<%=ip_result%>";


	ozViewerCreatePop(ozFile,ozOdiname,args);

	fm.cmd.value = "UseBeforeLogOnly";
	fm.target = "ozLog";
	fm.submit();

}
</script>

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
  		<div id="top_navi"><%@ include file="../inc/main_top.jsp" %></div>	
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
					<a href="../"><img src="../images/common/icon_home.gif" alt="HOME"></a> 원스톱민원센타 | 사용전검사 관리 | 사용전검사현황
				</p>
			</div>
			<!-- 현재위치 : E -->
	
			<!-- 서브타이틀 : S -->
			<div id="sub_title">
				<h3 class="cont01"><img src="../01_one/images/titile_02_3.gif" alt="사용전검사  현황"></h3>
			</div>
			<!-- 서브타이틀 : E -->
   
		  	<!-- 컨텐츠 : S -->
			<div id="contents">
				
				<form name="fmParam" method="post" action="../usebefore/UseBeforeAction.do" >
				<input type="hidden" name="scRecvNum"   value="<%=pm.getScRecvNum()%>">
				<input type="hidden" name="scRecvName"   value="<%=pm.getScRecvName()%>">
				<input type="hidden" name="cmd" value="UseBeforeLogOnly">
								
				<input type="hidden" name="nowPage"    value="<%=nowPage%>">
				<input type="hidden" name="rowPerPage" value="<%=rowPerPage%>">

				
				<!-- 02사용전검사관리 : S -->
				<h4>현장검사관리</h4>
				<div class="padd10">
						
					<!-- 검사결과 : S -->
						 <%
	           			String PROC_STE = KJFUtil.print(rEntity.getValue(0, "PROC_STE"),"");
	            		String NAPPL_YN = KJFUtil.print(rEntity.getValue(0, "NAPPL_YN"),"");
	            		String SERVER_YN = KJFUtil.print(rEntity.getValue(0, "SERVER_YN"),"");
	            		String UB_PRINT_CHK ="";
	            		if ("처리완료".equals(PROC_STE) && "1".equals(SERVER_YN)){
	            			if ("1".equals(NAPPL_YN)){
	            				UB_PRINT_CHK = "합격&nbsp;<img src='../images/btn/btn_cert_public.gif' alt='사용전필증출력' align='bsmiddle' onClick='fn_print();'>";
					 					} else if("2".equals(NAPPL_YN)){
					 						UB_PRINT_CHK = "부적합";
					 					}
	            		} else {
	            			 if ("0".equals(SERVER_YN)){
	            				 UB_PRINT_CHK =PROC_STE+"&nbsp;<img src='../images/btn/btn_cert_public.gif' alt='사용전필증출력' align='bsmiddle' onClick=\"javascript:alert('서비스 지역이 아닙니다');\" />";
							 			 } else {
							 				 UB_PRINT_CHK =PROC_STE+"&nbsp;<img src='../images/btn/btn_cert_public.gif' alt='사용전필증출력' align='bsmiddle' onClick=\"javascript:alert('처리완료시에 발급가능합니다.');\" />";
							 			 }
	            		}
	            %>
					<h4>검사결과</h4>
					<table class="t2" summary="검사결과를 나타내는 표">
						<caption class="caption">검사결과</caption>
						<colgroup>
							<col style="width: 90px; text-align: center" />
						</colgroup>
	                    <tbody>
	                    <tr>
	                      <th>검사결과</th>
	           	          <td> <%=UB_PRINT_CHK %> </td>
	                    </tr>
						</tbody>
	                  </table>
					<!-- 검사결과 : E -->
					<!-- 02사용전검사관리 : E -->

					<!-- 1구내통신선로설비 : S -->
					<p class="dot01">구내통신선로설비</p>
					<table class="t3" summary="기타사항를 나타내는 표">
						<caption class="caption">기타사항</caption>
						<colgroup>
							<col style="width: 50px;" />
							<col style="width: 180px;" />
							<col style="" />
							<col style="width: 80px; text-align: center;" />
						</colgroup>
						<thead>
	                    <tr>
						  <th scope="row">번호</th>
	                      <th scope="row">검사항목</th>
	                      <th scope="row">검사내용 및 검사기준</th>
	                      <th scope="row">검사결과</th>
	                    </tr>
	                    </thead>
	                    <tbody>
							<% if (pEntity.getRowCnt() > 0 ) {%>
								<% for (int i = 0; i < pEntity.getRowCnt(); i++) { %>
								<tr>
									<td align="center" class="letter0"><%=(i + 1)%></td>
						            <td align="center" class="letter0"><%=KJFUtil.print(pEntity.getValue(i, "SMACLAS")) %></td>
									<td class="letter0"><%=KJFUtil.print(pEntity.getValue(i, "CONT")) %></td>
									<td align="center" class="letter0"><%=KJFUtil.print(pEntity.getValue(i, "ITEM_OUT")) %></td>
								</tr>
								<% } %>
							<% } else { %>
								<tr>
									<td align="center" colspan="4">검사 정보가 없습니다.</td>
								</tr>
							<% } %>
						</tbody>
	             	</table>
					<!-- 1구내통신선로설비 : E -->
					
					<!-- 2방송공동수신설비 : S -->
					<p class="dot01">방송공동수신설비</p>
					<table class="t3" summary="기타사항를 나타내는 표">
							<caption class="caption">기타사항</caption>
							<colgroup>
								<col style="width: 50px;" />
								<col style="width: 180px;" />
								<col style="" />
								<col style="width: 80px; text-align: center;" />
							</colgroup>
							<thead>
		                    <tr>
							  <th scope="row">번호</th>
		                      <th scope="row">검사항목</th>
		                      <th scope="row">검사내용 및 검사기준</th>
		                      <th scope="row">검사결과</th>
		                    </tr>
		                    </thead>
		                    <tbody>
			                    <% if (bEntity.getRowCnt() > 0 ) {%>
									<% for (int i = 0; i < bEntity.getRowCnt(); i++) { %>
									<tr>
										<td align="center" class="letter0"><%=(i + 1)%></td>
							            <td align="center" class="letter0"><%=KJFUtil.print(bEntity.getValue(i, "SMACLAS")) %></td>
										<td class="letter0"><%=KJFUtil.print(bEntity.getValue(i, "CONT")) %></td>
										<td align="center" class="letter0"><%=KJFUtil.print(bEntity.getValue(i, "ITEM_OUT")) %></td>
									</tr>
									<% } %>
								<% } else { %>
									<tr>
										<td align="center" colspan="4">검사 정보가 없습니다.</td>
									</tr>
								<% } %>
							</tbody>
		            </table>
					<!-- 2방송공동수신설비 : E -->
					
					<!-- 3이동통신구내선로설비 : S -->
					<p class="dot01">이동통신구내선로설비</p>
					<table class="t3" summary="기타사항를 나타내는 표">
						<caption class="caption">기타사항</caption>
						<colgroup>
							<col style="width: 50px;" />
							<col style="width: 180px;" />
							<col style="" />
							<col style="width: 80px; text-align: center;" />
						</colgroup>
						<thead>
	                    <tr>
						  <th scope="row">번호</th>
	                      <th scope="row">검사항목</th>
	                      <th scope="row">검사내용 및 검사기준</th>
	                      <th scope="row">검사결과</th>
	                    </tr>
	                    </thead>
	                    <tbody>
		                    <% if (mEntity.getRowCnt() > 0 ) {%>
								<% for (int i = 0; i < mEntity.getRowCnt(); i++) { %>
								<tr>
									<td align="center" class="letter0"><%=(i + 1)%></td>
						            <td align="center" class="letter0"><%=KJFUtil.print(mEntity.getValue(i, "SMACLAS")) %></td>
									<td class="letter0"><%=KJFUtil.print(mEntity.getValue(i, "CONT")) %></td>
									<td align="center" class="letter0"><%=KJFUtil.print(mEntity.getValue(i, "ITEM_OUT")) %></td>
								</tr>
								<% } %>
							<% } else { %>
								<tr>
									<td align="center" colspan="4">검사 정보가 없습니다.</td>
								</tr>
							<% } %>
						</tbody>
		            </table>
					<!-- 3이동통신구내선로설비 : E -->					
					
			  	  </div>

			  	  <!-- 목록버튼 : S -->
				  <div class="right">
					<a href="javascript:fn_goUseBeforeChkContent()"><img src="../images/btn/btn_accept_content.gif" alt="사용전검사 접수내용"></a>
					<!-- a href="javascript:fn_goUseBeforeBuildMgr()"><img src="../images/btn/btn_build_mgr.gif" alt="감리대상건축물관리"></a -->
					<a href="javascript:fn_goList()"><img src="../images/bbs/btn_list.gif" alt="목록"></a>
				  </div>
				<!-- 목록버튼 : E -->
				
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
