<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="kjf.ops.*" %>
<%@ page import="kjf.util.*" %>
<%@ page import="sp.stwork.*" %>

<%
	ReportEntity rEntity = KJFUtil.REntPrint( (ReportEntity)request.getAttribute("rEntity") );	// 착공전설계도 접수 정보
	
	StworkParam pm = (StworkParam)request.getAttribute("pm");
	
	// 페이징 관련(필수)
	String nowPage    = KJFUtil.print(pm.getNowPage());
	String rowPerPage = KJFUtil.print(pm.getRowPerPage());
	String totalCount = KJFUtil.print(pm.getTotalCount());
	
	// 상단 플래쉬 링크 정보
	String top_pageNum  = "1";
	String top_sub      = "2";
	
	// 좌측 플래쉬 링크 정보
	String left_pageNum = "2";
	String left_sub     = "1";
%>

<script language="JavaScript" type="text/javascript">

// 목록
function fn_goList(){
    var fm = document.fmParam;
    fm.action = "../stwork/StworkAction.do?cmd=StWorkPlanInfoList";
    fm.method = "post";
    
    fm.submit();
}
</script>

<html lang="ko">
<head>
<title>착공전 설계도 확인현황 페이지입니다.</title>
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
					<a href="../"><img src="../images/common/icon_home.gif" alt="HOME"></a> 원스톱민원센타 | 사용전검사 관리 | 착공전설계도확인현황
				</p>
			</div>
			<!-- 현재위치 : E -->
	
			<!-- 서브타이틀 : S -->
			<div id="sub_title">
				<h3 class="cont01"><img src="../01_one/images/titile_02_1.gif" alt="착공전 설계도 확인현황"></h1>
			</div>
			<!-- 서브타이틀 : E -->
   
		  	<!-- 컨텐츠 : S -->
			<div id="contents">
			
				<form name=fmParam method="post" action="../illegality/IllegalityAction.do" >
				<input type="hidden" name="cmd"        value="IllBusiSusView">		
				<input type="hidden" name="nowPage"    value="<%=nowPage%>">
				<input type="hidden" name="rowPerPage" value="<%=rowPerPage%>">
				
				<!--1착공전설계도 접수내용 : S -->
				<h4>착공전설계도 접수내용</h4>
				<div class="padd15">
					<!--01발주자 : S -->
					<p class="dot01">건축주</p>
					<table  class="t2" summary="건축주 나타내는 표">
						<caption class="caption">건축주</caption>
						<colgroup>
							<col style="width: 90px; " />
							<col style="" />
							<col style="width: 90px; text-align: center" />
							<col style="width:230px;" />
						</colgroup>
						<tbody>
						<tr>
						  <th>성명</th>
						  <td><%=KJFUtil.print(rEntity.getValue(0, "ORPE_NM")) %></td>
						  <th>연락처</th>
						  <td class="letter0"><%=KJFUtil.print(rEntity.getValue(0, "ORPE_TEL")) %></td>
						</tr>
						<tr>
						  <th>건축주소재지</th>
							  <td colspan="3" class="letter0">
								(<%=KJFUtil.setNumFormat(KJFUtil.print(rEntity.getValue(0, "ORPE_POSTNUM"))) %>)
		                      	<%=KJFUtil.print(rEntity.getValue(0, "ORPE_ADDR")) %>
		                      	<%=KJFUtil.print(rEntity.getValue(0, "ORPE_DETAILADDR")) %>
							  </td>
						  </tr>
						</tbody>
				 	  </table>
					  <!--01발주자 : E -->
					  
					  <!--02양도업체정보 : S -->
					  <p class="dot01">설계자</p>
						<table  class="t2" summary="설계자 나타내는 표">
                          <caption class="caption">설계자</caption>
						  <colgroup>
                          <col style="width: 90px; " />
                          <col style="" />
                          <col style="width: 90px; text-align: center" />
                          <col style="width:230px;" />
                          </colgroup>
                          <tbody>
                            <tr>
                              <th>성명</th>
                              <td><%=KJFUtil.print(rEntity.getValue(0, "PLANER_NM")) %></td>
                              <th>연락처</th>
                              <td class="letter0"><%=KJFUtil.print(rEntity.getValue(0, "PLANER_TEL")) %></td>
                            </tr>
							<tr>
                              <th>상호</th>
                              <td colspan="3" class="letter0"><%=KJFUtil.print(rEntity.getValue(0, "PLANER_NAME")) %></td>
                            </tr>
                            <tr>
                              <th>설계자소재지</th>
                              	<td colspan="3" class="letter0">
									(<%=KJFUtil.setNumFormat(KJFUtil.print(rEntity.getValue(0, "PLANER_POSTNUM"))) %>)
			                      	<%=KJFUtil.print(rEntity.getValue(0, "PLANER_ADDR")) %>
			                      	<%=KJFUtil.print(rEntity.getValue(0, "PLANER_DETAILADDR")) %>
								</td>
                            </tr>
                          </tbody>
                        </table>
						<!--02양도업체정보 : E -->
					  
					  <!--03건축정보 : S -->
					  <p class="dot01">건축정보</p>
						<table  class="t2" summary="건축정보를 나타내는 표">
							<caption class="caption">건축정보</caption>
							<colgroup>
								<col style="width: 90px; " />
								<col style="" />
								<col style="width: 90px; text-align: center" />
								<col style="width:230px;" />
							</colgroup>
							<tbody>
							<tr>
							  <th>접수일자</th>
							  <td class="letter0"><%=KJFUtil.print(KJFDate.getChangDatePattern(rEntity.getValue(0, "RECV_DT"), "yyyyMMdd", "yyyy/MM/dd"), "&nbsp;") %></td>
							  <th>건축허가번호</th>
							  <td class="letter0"><%=KJFUtil.print(rEntity.getValue(0, "STRU_COMMIT_NUM")) %></td>
							</tr>
							<tr>
							  <th>용도</th>
							  <td><%=KJFUtil.print(rEntity.getValue(0, "USE")) %></td>
							  <th>공사종류</th>
							  <td><%=KJFUtil.print(rEntity.getValue(0, "WORK_ITEM")) %></td>
							</tr>
							<tr>
							  <th>층수</th>
							  <td class="letter0"><%=KJFUtil.print(rEntity.getValue(0, "NUM_FL")) %> 층</td>
							  <th>연면적</th>
							  <td class="letter0"><%=KJFUtil.print(rEntity.getValue(0, "AREA")) %> ㎡</td>
							</tr>
							<tr>
							  <th>건축물소재지</th>
							  	<td colspan="3" class="letter0">
									(<%=KJFUtil.setNumFormat(KJFUtil.print(rEntity.getValue(0, "STRU_ADDR_POSTNUM"))) %>)
			                      	<%=KJFUtil.print(rEntity.getValue(0, "STRU_ADDR_ADDR")) %>
			                      	<%=KJFUtil.print(rEntity.getValue(0, "STRU_ADDR_DETAILADDR")) %>
								</td>
							  </tr>
						</tbody>
					  </table>
					  <!--03건축정보 : E -->
	  	    	</div>
		  	  <!--1착공전설계도 접수내용 : E -->
			  
			  <!--2착공전설계도 검토결과 : S -->
			  <h4>착공전설계도 검토결과</h4>
			  <table  class="t2" summary="착공전설계도 검토결과 나타내는 표">
					<caption class="caption">착공전설계도 검토결과</caption>
					<colgroup>
						<col style="width: 110px; " />
					</colgroup>
					<tbody>
					<tr>
					  <th>검토결과</th>
					  <td><%=KJFUtil.print(rEntity.getValue(0, "SUV_APPL_NM")) %></td>
					</tr>
					</tbody>
			  </table>	
			  <!--2착공전설계도 검토결과 : E -->

			  <!-- 목록버튼 : S -->
			  <div class="right">
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
