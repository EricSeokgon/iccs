<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="kjf.ops.*" %>
<%@ page import="kjf.util.*" %>
<%@ page import="sp.usebefore.*" %>

<%

String imgUri = request.getContextPath();
	ReportEntity rEntity = KJFUtil.REntPrint( (ReportEntity)request.getAttribute("rEntity") );	// 공사업등록 정보
	ReportEntity fEntity = KJFUtil.REntPrint( (ReportEntity)request.getAttribute("fEntity") );	// 상호 및 행정처분 사항
	ReportEntity bEntity = KJFUtil.REntPrint( (ReportEntity)request.getAttribute("bEntity") );	// 등록기준신고 사항
	ReportEntity eEntity = KJFUtil.REntPrint( (ReportEntity)request.getAttribute("eEntity") );	// 보유기술자 정보
	ReportEntity sEntity = KJFUtil.REntPrint( (ReportEntity)request.getAttribute("sEntity") );	// 타시설공사업 정보
	
	UseBeforeParam pm = (UseBeforeParam)request.getAttribute("pm");
	
	// 상단 플래쉬 링크 정보
	String top_pageNum  = "1";
	String top_sub      = "1";
	
	// 좌측 플래쉬 링크 정보
	String left_pageNum = "1";
	String left_sub     = "1";
	
	String OFFI_PART 	= "";
	String RECV_NUM 	= "";
	String COI_WRT_NUM 	= "";
	String NAME 	= "";
	
%>

<html lang="ko">
<head>
<title>공사업 상제정보 페이지입니다.</title>
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
<body class="no_img">
<noscript><p>자바스크립트를 지원해야 올바르게 동작하는 페이지입니다.</p></noscript>

		  	<!-- 컨텐츠 : S -->
			<div id="contents2" >
			<!--01공사업등록정보 : S -->
			<br/>
				<div id="popup_use_top_close" >
   				<a href="javascript:close();">
   					<img src="<%=imgUri%>/images/common/favorite_btn_close.gif" alt="닫기"></a>
   		  </div>
   		  
 				<!--1 업체기본정보 : S -->
				<h4>업체기본정보</h4>  		  
			  	<table class="t2" summary="업체기본정보 접수일자 처리상태 상호 전화번호 대표자 팩스번호 소재지주소를 나타내는 표">
          <caption class="caption">업체기본정보</caption>
					<colgroup>
						<col style="width: 70px; text-align: center" />
						<col style="width: 70px;" />
						<col style="width: 50px; text-align: center" />
						<col style="width: 90px;" />
						<col style="width: 50px; text-align: center" />
						<col style="width: 80px;" />						
					</colgroup>
					<tbody>

			<%
					if (rEntity.getRowCnt() > 0) { 
			%>
					  <tr> 
					    <th >등록번호<br/>
					      (사업자번호)</th>
					    <td class="letter0"><%=KJFUtil.print(rEntity.getValue(0, "COI_WRT_NUM")) %><br/>(<%=KJFUtil.print(rEntity.getValue(0, "MANA_NUM")) %>)</td>
					    <th >상호<br/>
					      (법인번호)</th>
					    <td  class="letter0"><%=KJFUtil.print(rEntity.getValue(0, "NAME")) %></td>
					    <th>등록일자</th>
					    <td  class="letter0"><%=KJFUtil.print(KJFDate.getChangDatePattern(rEntity.getValue(0, "WRT_DT"), "yyyyMMdd", "yyyy/MM/dd"), "&nbsp;") %></td>
					  </tr>
					  <tr> 
					    <th>대표자</th>
					    <td class="letter0"><%=KJFUtil.print(rEntity.getValue(0, "REP_NM_KOR")) %></td>
					    <th>주민번호</th>
					    <td class="letter0">
					    <% if (!"".equals(rEntity.getValue(0,"REP_SSN1"))){
					    	out.print(rEntity.getValue(0,"REP_SSN1")+"- XXXXXXX");
					    } else {
					    	out.print("&nbsp");
					    }
					    %>
					    </td>
					    <th>사무실면적</th>
					    <td class="letter0"><%=KJFUtil.print(rEntity.getValue(0, "OFFICE_AREA")) %></td>
					  </tr>
					  <tr> 
					    <th rowspan="2">영업소재지</th>
					    <td colspan="3" rowspan="2" class="letter0">
							(<%=KJFUtil.setNumFormat(KJFUtil.print(rEntity.getValue(0, "ADDR_POST_NUM"))) %>)
							<%=KJFUtil.print(rEntity.getValue(0, "ADDR_ADDR")) %>
							<%=KJFUtil.print(rEntity.getValue(0, "ADDR_DETAIL_ADDR")) %>					    
					    </td>
					    <th>전화번호</th>
					    <td class="letter0"><%=KJFUtil.print(rEntity.getValue(0, "ADDR_TEL_NUM")) %></td>
					  </tr>
					  <tr> 
					    <th>팩스번호</th>
					    <td class="letter0"><%=KJFUtil.print(rEntity.getValue(0, "ADDR_FAX_NUM")) %></td>
					  </tr>
					  <tr> 
					    <th >시공능력평가액(원)</th>
					    <td  class="letter0"><%=KJFUtil.money(KJFUtil.str2long(rEntity.getValue(0, "ASSE_AOM"))) %></td>
					    <th >납입자본금(원)</th>
					    <td  class="letter0"><%=KJFUtil.money(KJFUtil.str2long(rEntity.getValue(0, "PAY_CAP"))) %></td>
					    <th >실질자본금(원)</th>
					    <td  class="letter0"><%=KJFUtil.money(KJFUtil.str2long(rEntity.getValue(0, "REA_CAP"))) %> </td>
					  </tr>  
		            	<% } else { %>
							<tr>
							  <td align="center" colspan="6">등록된 업체기본 정보가 없습니다.</td>
							</tr>
						<% } %>
					</tbody>
                  </table>
			  	<!--1업체기본정보 : E -->
			  	
				<!--2 공사실적 : S -->
				<h4>공사실적</h4>
			  	<table class="t2" summary="년도별로 공사실적을  나타내는 표">
					<caption class="caption">공사실적</caption>
					<colgroup>
						<col style="width: 50px; text-align: center" />
						<col style="width: 100px;" />
						<col style="width: 50px; text-align: center" />
						<col style="width: 100px;" />
					</colgroup>
					<thead>

                    </thead>
                    <tbody>
						<% if (rEntity.getRowCnt() > 0 ) {
								ReportEntity rWorkNum = (ReportEntity)rEntity.getObjValue(0,"rWorkNum");
						 		for (int i = 0; i < rWorkNum.getRowCnt(); i++) { %>
							<tr>
							  <th>년도</th>
							  <td class="letter0"><%=KJFUtil.print(rWorkNum.getValue(i,"YEAR"),"&nbsp;")%></td>
							  <th>금액</th>
							  <td class="letter0"><%=KJFUtil.money(KJFUtil.str2long(rWorkNum.getValue(i, "WORK_ROM")))%></td>
							</tr>
						<% 	} %>
						<% } else { %>
							<tr>
								<td align="center"  colspan="7">등록된 공사실적 정보가 없습니다.</td>
							</tr>
						<% } %>
					</tbody>
                  </table>			  	
				<!--2공사실적 : E -->

				<!--3 : S -->
				<h4>상호 및 행정처분 사항</h4>
			  	<table class="t2" summary="상호 및 행정처분 사항을 나타내는 표">
					<caption class="caption">상호 및 행정처분 사항</caption>
					<colgroup>
						<col style="width: 80px; text-align: center" />
						<col style="width: 100px;" />
						<col style="width: 80px; text-align: center" />
						<col style="width: 100px;" />
					</colgroup>
					<thead>
                    <tr>
                      <th scope="row">위반일자</th>
                      <th scope="row">내용</th>
                      <th scope="row">사유</th>
                      <th scope="row">시행기관</th>
                    </tr>
                    </thead>
                    <tbody>
						<% if (fEntity.getRowCnt() > 0 ) {%>
							<%for (int i = 0; i < fEntity.getRowCnt(); i++) {
							    
							%>
							<tr>
					          <td class="letter0"><%=KJFUtil.print(KJFDate.getChangDatePattern(fEntity.getValue(i, "VIOL_DT"), "yyyyMMdd", "yyyy/MM/dd"), "&nbsp;") %></td>
		                      <td class="letter0"><%=KJFUtil.print(fEntity.getValue(i,"CODE_NAME"),"&nbsp;") %></td>
		                      <td class="letter0"><%=KJFUtil.print(fEntity.getValue(i,"DISPO_CAUSE"),"&nbsp;") %></td>
		                      <td class="letter0"><%=KJFUtil.print(fEntity.getValue(i,"AUDI_EXEC_ORG"),"&nbsp;") %></td>
							</tr>
							<% } %>
						<% } else { %>
							<tr>
								<td align="center"  colspan="4">등록된 상호 및 행정처분 사항이  없습니다.</td>
							</tr>
						<% } %>
					</tbody>
                  </table>			  	
				<!--3 상호 및 행정처분 사항 : E -->
								
				
				<!--4 : S -->
				<h4>등록기준신고</h4>
			  	<table class="t2" summary="등록기준신고 사항을 나타내는 표">
					<caption class="caption">등록기준신고</caption>
					<colgroup>
						<col style="width: 80px; text-align: center" />
						<col style="width: 230px;" />
						<col style="width: 100px; text-align: center" />
					</colgroup>
					<thead>
                    <tr>
                      <th scope="row">등록기준신고일</th>
                      <th scope="row">내용</th>
                      <th scope="row">신고예정일(신고일기준)</th>
                    </tr>
                    </thead>
                    <tbody>
						<% if (bEntity.getRowCnt() > 0 ) {%>
						<% for (int i = 0; i < bEntity.getRowCnt(); i++) {
							 OFFI_PART 	=  bEntity.getValue(i,"OFFI_PART");
							 RECV_NUM 	=  bEntity.getValue(i,"RECV_NUM");
							 COI_WRT_NUM 	= bEntity.getValue(i,"COI_WRT_NUM");
							 NAME 	= 	bEntity.getValue(i,"NAME");
							%>
							<tr>
					          <td class="letter0"><%=KJFUtil.print(KJFDate.getChangDatePattern(bEntity.getValue(i, "WRT_DT"), "yyyyMMdd", "yyyy/MM/dd"), "&nbsp;") %></td>
		                      <td class="letter0"><%=OFFI_PART +"-"+RECV_NUM+"("+COI_WRT_NUM+"-"+NAME %></td>
		                      <td class="letter0"><%=KJFUtil.print(KJFDate.getChangDatePattern(bEntity.getValue(i, "RECV_DT"), "yyyyMMdd", "yyyy/MM/dd"), "&nbsp;") %></td>
							</tr>
							<% } %>
						<% } else { %>
							<tr>
								<td align="center"  colspan="7">등록기준신고 사항이 없습니다.</td>
							</tr>
						<% } %>
					</tbody>
                  </table>			  	
				<!--4 등록기준신고 : E -->
				
				<!--5 겸업정보 : S -->
				<h4 class="continue">겸업 사항</h4>
				<table class="t2" summary="">
				    <caption>타 시설공사업</caption>
				    <colgroup>
				    	<col style="width: 50px; text-align: center;" />
						<col style="width: ''; text-align: center;" />
						<col style="width: ''; text-align: center;" />
				    </colgroup>
				    <thead>
				        <tr>
				            <th scope="col">번호</th>
				            <th scope="col">타 시설공사업</th>
				            <th scope="col">타 시설공사업 등록번호</th>
				        </tr>
				    </thead>
				    <tbody>
						<% if (sEntity.getRowCnt() > 0 ) {%>
							<% for (int i = 0; i < sEntity.getRowCnt(); i++) { %>
							<tr>
					            <td align="center"><%=(i+1)%></td>
					            <td align="center" class="letter0"><%=KJFUtil.print(sEntity.getValue(i, "CODE_NAME")) %></td>
							    <td align="center" class="letter0"><%=KJFUtil.print(sEntity.getValue(i, "SUB_WRT_NUM")) %></td>
							</tr>
							<% } %>

						<% } else { %>
							<tr>
								<td align="center"  colspan="3">등록된 겸업정보가 없습니다.</td>
							</tr>
						<% } %>
					</tbody>
				</table>
				<!--5 겸업정보 : S -->
				
				<!--6 보유기술자 : S -->
				<h4>보유기술자</h4>
			  	  <table class="t3" summary="보유기술자 성명 주민번호 등급 발급번호 발급일자 입사일자 퇴사일자를 나타내는 표">
					<caption class="caption">보유기술자</caption>
					<colgroup>
						<col style="width: 20px; text-align: center;" />
						<col style="width: 50px; text-align: center;" />
						<col style="width: 90px; text-align: center;" />
						<col style="width: 50px; text-align: center;" />
						<col style="width: 60px; text-align: center;" />
					</colgroup>
					<thead>
                    <tr>
                      <th scope="row">번호</th>
                      <th scope="row">성명</th>
                      <th scope="row">주민번호</th>
                      <th scope="row">등급</th>
                      <!-- th scope="row">발급번호</th>
                      <th scope="row">발급일자</th -->
                      <th scope="row">입사일자</th>
                    </tr>
                    </thead>
                    <tbody>
						<% if (eEntity.getRowCnt() > 0 ) {%>
							<%for (int i = 0; i < eEntity.getRowCnt(); i++) {
							    
							%>
							<tr>
					          <td class="letter0"><%=(i + 1)%></td>
		                      <td><%=KJFUtil.print(eEntity.getValue(i, "ENGINEER_NM")) %></td>
		                      <td class="letter0"><%=KJFUtil.print(eEntity.getValue(i, "ENGINEER_SSN1")) %>-XXXXXXX</td>
		                      <td><%=KJFUtil.print(eEntity.getValue(i, "ENGINEER_GRADE_NM")) %></td>
		                      <!--  <td class="letter0"><%//KJFUtil.print(eEntity.getValue(i, "CARE_BOOK_ISSUE_NUM")) %></td>
		                      <td class="letter0"><%//KJFUtil.print(KJFDate.getChangDatePattern(eEntity.getValue(i, "CARE_BOOK_VAL_START_DT"), "yyyyMMdd", "yyyy/MM/dd"), "&nbsp;") %></td>
		                      -->
		                      <td class="letter0"><%=KJFUtil.print(KJFDate.getChangDatePattern(eEntity.getValue(i, "EMPL_YMD"), "yyyyMMdd", "yyyy/MM/dd"), "&nbsp;") %></td>
							</tr>
							<% } %>
						<% } else { %>
							<tr>
								<td align="center"  colspan="5">등록된 기술자 정보가 없습니다.</td>
							</tr>
						<% } %>
					</tbody>
        </table>
				<!--6 보유기술자 : E -->
				<br/>
			<div id="popup_use_close">
				<a href="javascript:close();"><img src="<%=imgUri%>/images/common/favorite_btn_close.gif" alt="닫기"></a>
			</div>
			<!--01공사업등록정보 : E -->

			</div>
		   	<!-- 컨텐츠 : E -->

</body>
</html>
