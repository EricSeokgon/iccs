<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="kjf.ops.*" %>
<%@ page import="kjf.util.*" %>
<%@ page import="sp.usebefore.*" %>
<%
	ReportEntity rEntity = KJFUtil.REntPrint( (ReportEntity)request.getAttribute("rEntity") );
	
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

<script language="JavaScript" type="text/javascript">

// 사용전검사관리
function fn_goUseBeforeChkMgr(){
    var fm = document.fmParam;
    fm.action = "../usebefore/UseBeforeAction.do?cmd=UseBeforeChkMgr";
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

</script>

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
				<input type="hidden" name="scRecvNum"   value="<%=rEntity.getValue(0,"RECV_NUM")%>">		
				<input type="hidden" name="scRecvName"   value="<%=rEntity.getValue(0,"APPLPER_NM")%>">
				<input type="hidden" name="scSIGUNGU_CODE"   value="<%=rEntity.getValue(0,"SIGUNGU_CODE")%>">
				<input type="hidden" name="nowPage"    value="<%=nowPage%>">
				<input type="hidden" name="rowPerPage" value="<%=rowPerPage%>">
				
				<!--1기본정보 : S -->
				<h4>기본정보</h4>
					<!--신청인 : S -->
					<div class="padd10">
						<p class="dot01">신청인</p>
				  		<table  class="t2" summary="건축주 대표자 연락처 주소를 나타내는 표">
		                    <caption class="caption">신청인</caption>
							<colgroup>
								<col style="width: 90px; " />
								<col style="width: 230px;" />
								<col style="width: 90px; text-align: center" />
								<col style="width: 230px;" />
							</colgroup>
							<tbody>
							<tr>
		                      <th>건축주</th>
		                      <td><%=KJFUtil.print(rEntity.getValue(0, "APPLPER_NM")) %></td>
		                      <th>대표자</th>
		                      <td><%=KJFUtil.print(rEntity.getValue(0, "APPLPER_REP")) %></td>
		                    </tr>
							<tr>
		                      <th>연락처</th>
		                      <td colspan="3" class="letter0"><%=KJFUtil.print(rEntity.getValue(0, "APPLPER_TELNUM")) %></td>
		                    </tr>
							<tr>
		                      <th>주소지</th>
		                      <td colspan="3" class="letter0">
								(<%=KJFUtil.setNumFormat(KJFUtil.print(rEntity.getValue(0, "APPLPER_POSTNUM"))) %>)
								<%=KJFUtil.print(rEntity.getValue(0, "APPLPER_ADDR")) %>
								<%=KJFUtil.print(rEntity.getValue(0, "APPLPER_DETAILADDR")) %></td>
		                    </tr>
						</tbody>
	                  </table>
	                  <!--신청인 : S -->
	                  
	                  <!--시공자 : S -->
						 	<p class="dot01">시공자</p>
					  	 	<table  class="t2" summary="를 나타내는 표">
			                    <caption class="caption">시공자</caption>
								<colgroup>
									<col style="width: 90px; " />
									<col style="width: 230px;" />
									<col style="width: 90px; text-align: center" />
									<col style="width: 230px;" />
								</colgroup>
								<tbody>
								<tr>
			                      <th>상호명</th>
			                      <td><%=KJFUtil.print(rEntity.getValue(0, "OPE_NAME")) %></td>
			                      <th>대표자</th>
			                      <td><%=KJFUtil.print(rEntity.getValue(0, "OPE_REP")) %></td>
			                    </tr>
								<tr>
			                      <th>연락처</th>
			                      <td class="letter0"><%=KJFUtil.print(rEntity.getValue(0, "OPE_TELNUM")) %></td>
			                      <th>공사업등록번호</th>
			                      <td class="letter0"><%=KJFUtil.print(rEntity.getValue(0, "COI_WRT_NUM")) %></td>
			                    </tr>
								<tr>
			                      <th>주소지</th>
			                      <td colspan="3" class="letter0">
									(<%=KJFUtil.setNumFormat(KJFUtil.print(rEntity.getValue(0, "OPE_POSTNUM"))) %>)
									<%=KJFUtil.print(rEntity.getValue(0, "OPE_ADDR")) %>
									<%=KJFUtil.print(rEntity.getValue(0, "OPE_DETAILADDR")) %></td>
			                    </tr>
			                    <tr>
			                      <th>담당자명</th>
			                      <td><%=KJFUtil.print(rEntity.getValue(0, "OFFI_NM")) %></td>
			                      <th>담당자 연락처</th>
			                      <td class="letter0"><%=KJFUtil.print(rEntity.getValue(0, "OFFI_TELNUM")) %></td>
			                    </tr>
							</tbody>
		                  </table>
	                  <!--시공자 : E -->
	                  
	                  <!--기타사항 : S -->
	                  	<p class="dot01">기타사항</p>
					  	 	<table class="t3" summary="기타사항를 나타내는 표">
							<caption class="caption">기타사항</caption>
							<colgroup>
								<col style="text-align: center;" />
								<col style="text-align: center;" />
								<col style="text-align: center;" />
								<col style="text-align: center;" />
								<col style="text-align: center;" />
								<col style="text-align: center;" />
							</colgroup>
							<thead>
		                    <tr>
		                      <th scope="row">공사종류</th>
		                      <th scope="row">착공일</th>
		                      <th scope="row">검사희망일</th>
		                      <th scope="row">완공일</th>
		                      <th scope="row">검사수수료</th>
		                      <th scope="row">접수일</th>
		                    </tr>
		                    </thead>
		                    <tbody>
		                    <tr>
		                      <td><%=KJFUtil.print(rEntity.getValue(0, "WORK_ITEM")) %></td>
		                      <td class="letter0"><%=KJFUtil.print(KJFDate.getChangDatePattern(rEntity.getValue(0, "SW_DT"), "yyyyMMdd", "yyyy/MM/dd"), "&nbsp;") %></td>
		                      <td class="letter0"><%=KJFUtil.print(KJFDate.getChangDatePattern(rEntity.getValue(0, "INSP_WISH_YMD"), "yyyyMMdd", "yyyy/MM/dd"), "&nbsp;") %></td>
		                      <td class="letter0"><%=KJFUtil.print(KJFDate.getChangDatePattern(rEntity.getValue(0, "EW_DT"), "yyyyMMdd", "yyyy/MM/dd"), "&nbsp;") %></td>
		                      <td class="letter0"><%=KJFUtil.money(KJFUtil.str2long(rEntity.getValue(0, "INSP_FEE")))%>원</td>
		                      <td class="letter0"><%=KJFUtil.print(KJFDate.getChangDatePattern(rEntity.getValue(0, "RECV_DT"), "yyyyMMdd", "yyyy/MM/dd"), "&nbsp;") %></td>
		                    </tr>
							</tbody>
		                  </table>
	                  <!--기타사항 : E -->
                  </div>
			  	  <!--1기본정보 : E -->
					
				  <!-- 2건축정보 : S -->
		  		  <h4>건축정보</h4>
				  <!--시공현장정보 : S -->
				  <div class="padd10">
					<p class="dot01">시공현장정보</p>
			  		<table  class="t2" summary="건축주 대표자 연락처 주소를 나타내는 표">
	                    <caption class="caption">시공현장정보</caption>
						<colgroup>
							<col style="width: 90px; " />
							<col style="" />
							<col style="width: 90px; text-align: center" />
							<col style="" />
						</colgroup>
						<tbody>
						<tr>
	                      <th>검사현장명칭</th>
	                      <td><%=KJFUtil.print(rEntity.getValue(0, "INSP_SPOT_NM")) %></td>
	                    </tr>
						<tr>
	                      <th>주소지</th>
	                      <td colspan="3" class="letter0">
							(<%=KJFUtil.setNumFormat(KJFUtil.print(rEntity.getValue(0, "INSP_SPOT_POSTNUM"))) %>)
							<%=KJFUtil.print(rEntity.getValue(0, "INSP_SPOT_ADDR")) %>
							<%=KJFUtil.print(rEntity.getValue(0, "INSP_SPOT_DETAILADDR")) %>
						  </td>
	                    </tr>
					</tbody>
                  </table>
                  <!--시공현장정보 : S -->
                  
                  <!--건축물정보 : S -->
					 	<p class="dot01">건축물정보</p>
				  	 	<table  class="t2" summary="를 나타내는 표">
		                    <caption class="caption">건축물정보</caption>
							<colgroup>
								<col style="width: 90px; text-align: center" />
								<col style="width: 230px;" />
								<col style="width: 90px; text-align: center" />
								<col style="width: 230px;" />
							</colgroup>
							<tbody>
							<tr>
		                      <th>건축물용도</th>
		                      <td><%=KJFUtil.print(rEntity.getValue(0, "USE")) %></td>
		                      <th>면적</th>
		                      <td class="letter0"><%=KJFUtil.print(rEntity.getValue(0, "AREA")) %> ㎡</td>
		                    </tr>
							<tr>
		                      <th>검사대상공사</th>
		                      <td><%=KJFUtil.print(rEntity.getValue(0, "INSP_APPL_WORK")) %></td>
		                      <th>층수</th>
		                      <td class="letter0"><%=KJFUtil.print(rEntity.getValue(0, "NUM_FL")) %> 층</td>
		                    </tr>
						</tbody>
	                  </table>
                  <!--건축물정보 : E -->
                  
                  <!--검사상세정보 : S -->
                  	<p class="dot01">검사상세정보</p>
				  	 	<table class="t2" summary="검사상세정보를 나타내는 표">
						<caption class="caption">검사상세정보</caption>
						<colgroup>
							<col style="width: 90px; text-align: center" />
							<col style="" />
							<col style="width: 90px; text-align: center" />
							<col style="" />
						</colgroup>
	                    <tbody>
	                    <tr>
	                      <th>검사신청일</th>
	                      <td class="letter0"><%=KJFUtil.print(KJFDate.getChangDatePattern(rEntity.getValue(0, "INSP_APPL_DT"), "yyyyMMdd", "yyyy/MM/dd"), "&nbsp;") %></td>
	                      <th>검사예정일</th>
	                      <td class="letter0"><%=KJFUtil.print(KJFDate.getChangDatePattern(rEntity.getValue(0, "INSP_DT"), "yyyyMMdd", "yyyy/MM/dd"), "&nbsp;") %></td>
	                    </tr>
						</tbody>
	                  </table>
                    <!--검사상세정보 : E -->
                   </div>
				   <!-- 2건축정보 : E -->
				   
				   <!-- 01사용전검사 접수내용 : E -->
			  	
			  	  <!-- 목록버튼 : S -->
				  <div class="right">
					<a href="javascript:fn_goUseBeforeChkMgr()"><img src="../images/btn/btn_check_mgr.gif" alt="사용전검사관리"></a>
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
