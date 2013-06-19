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

//사용전검사 접수내용
function fn_goUseBeforeChkContent(){
    var fm = document.fmParam;
    fm.action = "../usebefore/UseBeforeAction.do?cmd=UseBeforeChkContent";
    fm.method = "post";
    
    fm.submit();
}

// 사용전검사관리
function fn_goUseBeforeChkMgr(){
    var fm = document.fmParam;
    fm.action = "../usebefore/UseBeforeAction.do?cmd=UseBeforeChkMgr";
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
				<input type="hidden" name="recv_num"   value="<%=pm.getRecv_num()%>">	
				<input type="hidden" name="nowPage"    value="<%=nowPage%>">
				<input type="hidden" name="rowPerPage" value="<%=rowPerPage%>">
				
				<!-- 03감리대상건축물관리 : S -->
				<h4>감리자</h4>
				<!--감리자 : S -->
		  		<table  class="t2" summary="감리자를 나타내는 표">
                    <caption class="caption">감리자</caption>
					<colgroup>
						<col style="width: 100px; " />
						<col style="width: 220px;" />
						<col style="width: 90px; text-align: center" />
						<col style="width: 230px;" />
					</colgroup>
					<tbody>
					<tr>
                      <th>상호명</th>
                      <td><%=KJFUtil.print(rEntity.getValue(0, "SUV_NAME")) %></td>
                      <th>감리원</th>
                      <td><%=KJFUtil.print(rEntity.getValue(0, "SUV_NM")) %></td>
                    </tr>
                    <tr>
                      <th>연락처</th>
                      <td class="letter0"><%=KJFUtil.print(rEntity.getValue(0, "SUV_TELNUM")) %></td>
                      <th>휴대전화</th>
                      <td class="letter0"><%=KJFUtil.print(rEntity.getValue(0, "SUV_MOBILE")) %></td>
                    </tr>
					<tr>
                      <th>활동주체신고번호</th>
                      <td colspan="3" class="letter0"><%=KJFUtil.print(rEntity.getValue(0, "SUV_STANUM")) %></td>
                    </tr>
                    <tr>
                      <th>주소지</th>
                      <td colspan="3" class="letter0">
						(<%=KJFUtil.setNumFormat(KJFUtil.print(rEntity.getValue(0, "SUV_POSTNUM"))) %>)
						<%=KJFUtil.print(rEntity.getValue(0, "SUV_ADDR")) %>
						<%=KJFUtil.print(rEntity.getValue(0, "SUV_DETAILADDR")) %>
					  </td>
                    </tr>
				</tbody>
                 </table>
                 <!--감리자 : E -->
                 
                 <!--시공자 : S -->
				<h4>시공자</h4>
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
	                      <td><%=KJFUtil.print(rEntity.getValue(0, "SIWORK_NAME")) %></td>
	                      <th>대표자</th>
	                      <td><%=KJFUtil.print(rEntity.getValue(0, "SIWORK_REP")) %></td>
	                    </tr>
						<tr>
	                      <th>연락처</th>
	                      <td class="letter0"><%=KJFUtil.print(rEntity.getValue(0, "SIWORK_TELNUM")) %></td>
	                      <th>공사업번호</th>
	                      <td class="letter0"><%=KJFUtil.print(rEntity.getValue(0, "COI_WRT_NUM")) %></td>
	                    </tr>
						<tr>
	                      <th>주소지</th>
	                      <td colspan="3" class="letter0">
							(<%=KJFUtil.setNumFormat(KJFUtil.print(rEntity.getValue(0, "SIWORK_POSTNUM"))) %>)
							<%=KJFUtil.print(rEntity.getValue(0, "SIWORK_ADDR")) %>
							<%=KJFUtil.print(rEntity.getValue(0, "SIWORK_DETAILADDR")) %>
						  </td>
	                    </tr>
					</tbody>
                  </table>
                 <!--시공자 : E -->
                 
                 <!--건축정보 : S -->
                 <h4>건축정보</h4>
			  	 <table  class="t2" summary="건축정보를 나타내는 표">
                    <caption class="caption">건축정보</caption>
					<colgroup>
						<col style="width: 90px; " />
						<col style="width: 230px;" />
						<col style="width: 90px; text-align: center" />
						<col style="width: 230px;" />
					</colgroup>
					<tbody>
						<tr>
	                      <th>현장명칭</th>
	                      <td colspan="3"><%=KJFUtil.print(rEntity.getValue(0, "SPOTNM")) %></td>
	                    </tr>
	                    <tr>
	                      <th>공사종류</th>
	                      <td><%=KJFUtil.print(rEntity.getValue(0, "WORK_ITEM")) %></td>
	                      <th>용도</th>
	                      <td><%=KJFUtil.print(rEntity.getValue(0, "USE")) %></td>
	                    </tr>
						<tr>
	                      <th>건축면적</th>
	                      <td class="letter0"><%=KJFUtil.print(rEntity.getValue(0, "STRU_AREA")) %> ㎡</td>
	                      <th>연면적</th>
	                      <td class="letter0"><%=KJFUtil.print(rEntity.getValue(0, "AREA")) %> ㎡</td>
	                    </tr>
	                    <tr>
	                      <th>착공일</th>
	                      <td class="letter0"><%=KJFUtil.print(KJFDate.getChangDatePattern(rEntity.getValue(0, "SW_DT"), "yyyyMMdd", "yyyy/MM/dd"), "&nbsp;") %></td>
	                      <th>완공일</th>
	                      <td class="letter0"><%=KJFUtil.print(KJFDate.getChangDatePattern(rEntity.getValue(0, "EW_DT"), "yyyyMMdd", "yyyy/MM/dd"), "&nbsp;") %></td>
	                    </tr>
						<tr>
	                      <th>현장주소</th>
	                      <td colspan="3" class="letter0">
							(<%=KJFUtil.setNumFormat(KJFUtil.print(rEntity.getValue(0, "SPOT_POSTNUM"))) %>)
							<%=KJFUtil.print(rEntity.getValue(0, "SPOT_ADDR")) %>
							<%=KJFUtil.print(rEntity.getValue(0, "SPOT_DETAILADDR")) %>
						  </td>
	                    </tr>
					</tbody>
                 </table>
                 <!--기타사항 : E -->
				 <!-- 03감리대상건축물관리 : E -->
			  	
			  	  <!-- 목록버튼 : S -->
				  <div class="right">
					<a href="javascript:fn_goUseBeforeChkContent()"><img src="../images/btn/btn_accept_content.gif" alt="사용전검사 접수내용"></a>
					<a href="javascript:fn_goUseBeforeChkMgr()"><img src="../images/btn/btn_check_mgr.gif" alt="사용전검사관리"></a>
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
