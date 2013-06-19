<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="kjf.ops.*" %>
<%@ page import="kjf.util.*" %>
<%@ page import="sp.stwork.*" %>

<%
	ReportEntity recEntity = KJFUtil.REntPrint( (ReportEntity)request.getAttribute("recEntity") );	// 착공전설계도 접수 정보
	ReportEntity rEntity   = KJFUtil.REntPrint( (ReportEntity)request.getAttribute("rEntity") );	// 착공전설계도 리스트 정보
	
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
	
	int int_rnum = KJFUtil.str2int(totalCount)-(KJFUtil.str2int(nowPage) *KJFUtil.str2int(rowPerPage) - KJFUtil.str2int(rowPerPage));
%>

<script language="JavaScript" type="text/javascript">

// 검색
function fn_search() {
    var fm = document.fmParam;
    
    if (fm.scStru_Num.value.trim() == "") {
    	fm.scStru_Num.focus();
    	fm.scStru_Num.select();
		alert("건축허가번호를 입력해주세요!");
		return;
    }
    
    fm.nowPage.value = 1;
    fm.submit();
}

//  상세보기
function viewDetail(recv_num) {
    var fm = document.fmParam;
    
    fm.action = "../stwork/StworkAction.do?cmd=StWorkPlanInfoView";
    fm.recv_num.value = recv_num;
    fm.submit();
}

// 착공전설계도 삭제
function fn_delStwork(recv_num) {
	
    if (confirm("선택된 항목을 삭제 하시겠습니까?")) {
    	var fm = document.fmParam;
        
        fm.action = "../stwork/StworkAction.do?cmd=StWorkPlanInfoD";
        fm.recv_num.value = recv_num;
        
    	fm.submit();
    }  
}

// 검색된정보 등록
function fn_regStwork(recv_num) {
	sendRequest("../stwork/StworkAction.do", "cmd=StWorkPlanInfoC&recv_num=" + recv_num, fn_StworkView, "POST");
}

// 등록 결과
function fn_StworkView() {
	var result = httpRequest.responseText;

	if (result == "Y") {
		location.href="../stwork/StworkAction.do?cmd=StWorkPlanInfoList";
	} else if(result == "N") {
		alert("이미 등록된 정보 입니다.");
	} else {
		alert("처리중 오류가 발생하였습니다 다시 실행해 주십시오.");
	}
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
<script language="JavaScript" src="../com/js/com.js" type="text/javascript"></script>
<script language="JavaScript" src="../com/js/topMenu.js" type="text/javascript"></script>
<script language="JavaScript" src="../flash/flash.js" type="text/javascript"></script>
<script language="javascript" src="../com/js/httpRequest.js" type="text/javascript"></script>
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
				
				<form name=fmParam method="post" action="../stwork/StworkAction.do?cmd=StWorkPlanInfoList" >
				<input type="hidden" name="nowPage" value="<%=nowPage%>">
				<input type="hidden" name="recv_num">

				<h4>착공전 설계도 확인 접수검색</h4>
				<p class="txt">
					<span class="font_greenB">건축허가번호</span>를 이용  검색 후 결과를 확인하시고, <span class="font_greenB">[추가]버튼</span>을 클릭하시면 아래 접수현황리스트에 추가됩니다.
				</p>

				<!--검색 : S -->
				<table  class="t1" summary="검색를 나타내는 표">
                    <caption class="caption">검색</caption>
					<colgroup>
						<col style="width: 90px; " />
						<col style="width: 130px; " />
						<col style="width: 90px; " />
						<col style="width: 140px; " />
						<col style="width: 90px; " />
						<col style="width: px; " />
					</colgroup>
					<tbody>
					
						<tr>
	                      <th>이름</th>
	                      <td><%=KJFUtil.print(user.getUSER_NAME()) %></td>
	                      <th>기업명</th>
	                      <td><%=KJFUtil.print(user.getCOM_NAME()) %></td>
						  <th>회원종류</th>
	                      <td align="center">
	                      		<% if ( KJFUtil.print(user.getCAPITAL()).equals("U") ) { %>
									개인회원
								<% } else if ( KJFUtil.print(user.getCAPITAL()).equals("E") ) { %>
									기업회원
								<% } %>
						  </td>
	                    </tr>

						<tr>
	                      <th>접수자</th>
	                      <td>
								<input type="radio" class="radio" name="scCode" value="001" <%="001".equals(KJFUtil.print(pm.getScCode()))?"checked":"".equals(KJFUtil.print(pm.getScCode()))?"checked":""%> />건축주
								<input type="radio" class="radio" name="scCode" value="002" <%="002".equals(KJFUtil.print(pm.getScCode()))?"checked":""%> />설계자	
								<!--  
								<select name='scCode' class='input_bbs'>
									<option value='001' <%=KJFUtil.print(pm.getScCode()).equals("001")?"selected":""%>> 건축주 </option>
									<option value='002' <%=KJFUtil.print(pm.getScCode()).equals("002")?"selected":""%>> 설계자 </option>
								</select>
								-->
						  </td>
	                      <th>건축허가번호</th>
	                      <td colspan="3">
	                      	<input class="input_bbs" name="scStru_Num" size="28" maxlength="28" onkeyup="if(event.keyCode == 13) fn_search();" value="<%=KJFUtil.print(pm.getScStru_Num())%>" title="접수번호를 입력해주세요">
	                      	<a href="javascript:fn_search()"><img src="../images/bbs/btn_search.gif" alt="검색" align="absmiddle"></a>
	               		  </td>
	                    </tr>
					
					</tbody>
                </table>

				<% if (recEntity.getRowCnt() > 0 ) {%>
				<!-- 착공전 설계도 확인 접수현황 리스트 : S -->
				<table class="t1" summary="">
				    <caption></caption>
				    <colgroup>
				    <col style="width: 90px; text-align: center" />				    
				    <col style="width: 90px; text-align: center" />
				    <col style="text-align: center" />
				    <col style="width: 90px; text-align: center" />
				    <col style="width: 70px; text-align: center" />
				    <col style="width: 70px; text-align: center" />
				    </colgroup>
				    <thead>
				        <tr>
							<th scope="col">접수번호</th>
							<th scope="col">건축주</th>
				            <th scope="col">설계자</th>
							<th scope="col">건축허가번호</th>
							<th scope="col">접수일자</th>						
							<th scope="col">추가</th>
				        </tr>
				    </thead>
				    <tbody>
						
						<% for (int i = 0; i < recEntity.getRowCnt(); i++) { %>
						<tr>
				            <td align="center" class="letter0"><%=KJFUtil.print(recEntity.getValue(i, "CIV_RECV_NUM")) %></td>
						    <td align="center"><%=KJFUtil.print(recEntity.getValue(i, "ORPE_NM")) %></td>
						    <td align="center"><%=KJFUtil.print(recEntity.getValue(i, "PLANER_NM")) %></td>
						    <td align="center" class="letter0"><%=KJFUtil.print(recEntity.getValue(i, "STRU_COMMIT_NUM")) %></td>
							<td align="center" class="letter0"><%=KJFUtil.print(KJFDate.getChangDatePattern(recEntity.getValue(0, "RECV_DT"), "yyyyMMdd", "yyyy/MM/dd"), "&nbsp;") %></td>
							<td align="center" class="letter0">
								<a href="javascript:fn_regStwork('<%=KJFUtil.print(recEntity.getValue(i, "RECV_NUM"))%>')">
								<img src="../images/btn/btn_add.gif" alt="추가" title="검색된 사용전 검사 추가"></a>
							</td>
						</tr>
						<% } %>
						
				    </tbody>
				</table>
				<!-- 착공전 설계도 확인 접수현황 리스트 : E -->
				<% } %>

				<div class="right" style="padding-bottom: 0px;">&nbsp;</div>
				<!--검색 : E -->
				
				<!--착공전 설계도 확인 접수현황 : s -->
              	<h4>착공전 설계도 확인 접수현황</h4>
				<table class="t2" summary="착공전설계도 접수현황">
				    <caption>착공전설계도 접수현황</caption>
				    <colgroup>
				    	<col style="width: 35px; text-align: center" />		
						<col style="width: 90px; text-align: center" />
						<col style="width: 90px; text-align: center" />
						<col style="text-align: center;" />
						<col style="width: 80px; text-align: center" />
						<col style="width: 70px; text-align: center" />
						<col style="width: 90px; text-align: center" />
					</colgroup>
				    <thead>
				        <tr>
				        	<th scope="col">번호</th>
				            <th scope="col">접수번호</th>
							<th scope="col">건축주</th>
				            <th scope="col">설계자</th>		
							<th scope="col">접수일자</th>				
							<th scope="col">진행상태</th>
							<th scope="col">삭제</th>
				        </tr>
				    </thead>
				    <tbody>
				    
				    	<% if (rEntity.getRowCnt() > 0 ) {%>
							<% for (int i = 0; i < rEntity.getRowCnt(); i++) { %>
							<tr>
								<td align="center" class="letter0"><%=int_rnum--%></td>
					            <td align="center" class="letter0"><a href="javascript:viewDetail('<%=rEntity.getValue(i, "RECV_NUM")%>')"><%=KJFUtil.print(rEntity.getValue(i, "CIV_RECV_NUM")) %></a></td>
					            <td align="center" ><%=KJFUtil.print(rEntity.getValue(i, "ORPE_NM")) %></td>
							    <td align="center" ><%=KJFUtil.print(rEntity.getValue(i, "PLANER_NM")) %></td>
							    <td align="center"  class="letter0"><%=KJFUtil.print(KJFDate.getChangDatePattern(rEntity.getValue(i, "RECV_DT"), "yyyyMMdd", "yyyy/MM/dd"), "&nbsp;") %></td>
							    <td align="center" ><%=KJFUtil.print(rEntity.getValue(i, "PROC_STE")) %></td>
								<td align="center" >
									<a href="javascript:fn_delStwork('<%=KJFUtil.print(rEntity.getValue(i, "RECV_NUM")) %>')">
									<img src="../images/btn/btn_delete.gif" alt="삭제" title="삭제"></a>
								</td>
							</tr>
							<% } %>
						<% } else { %>
							<tr>
								<td colspan="7" align="center">등록된 정보가 없습니다.</td>
							</tr>
						<% } %>
						
				    </tbody>
				</table>
				
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
