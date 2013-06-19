<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="kjf.ops.*" %>
<%@ page import="kjf.util.*" %>
<%@ page import="sp.cominfo.*" %>
<%@ taglib uri="/KJFTags" prefix="KTags" %>

<%
	ReportEntity rEntity = KJFUtil.REntPrint( (ReportEntity)request.getAttribute("rEntity") );
	
	ComInfoParam pm = (ComInfoParam)request.getAttribute("pm");
	
	// 페이징 관련(필수)
	String nowPage    = KJFUtil.print(pm.getNowPage());
	String rowPerPage = KJFUtil.print(pm.getRowPerPage());
	String totalCount = KJFUtil.print(pm.getTotalCount());
	
	// 상단 플래쉬 링크 정보
	String top_pageNum  = "1";
	String top_sub      = "8";
	
	// 좌측 플래쉬 링크 정보
	String left_pageNum = "7";
	String left_sub     = "1";
	
	int int_rnum = KJFUtil.str2int(totalCount)-(KJFUtil.str2int(nowPage) *KJFUtil.str2int(rowPerPage) - KJFUtil.str2int(rowPerPage));
%>

<script language="JavaScript" type="text/javascript">

// 검색
function fn_search() {
    var fm = document.fmParam;

    if (fm.scText.value.trim() == "") {
    	fm.scText.focus();
    	fm.scText.select();
		alert("검색어 내용을 입력해주세요!");
		return;
    }
    
    fm.nowPage.value = 1;
    fm.submit();
}
// 조회
function fn_detail(seq,seq2){
	var fm = document.fmParam;
	fm.cmd.value = "InfoCommTraderPubV";	
	fm.seq.value = seq;
	fm.seq2.value = seq2;
	fm.submit();
}
</script>

<html lang="ko">
<head>
<title>정보통신공사업자 검색 페이지입니다.</title>
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
					<a href="../"><img src="../images/common/icon_home.gif" alt="HOME"></a> 공무원센터    |  공사업등록업체조회     |  정보통신공사업자검색
				</p>
			</div>
			<!-- 현재위치 : E -->
	
			<!-- 서브타이틀 : S -->
			<div id="sub_title">
				<h3 class="cont01"><img src="../09_center/images/titile_09_1.gif" alt="정보통신공사업자 검색 "></h1>
			</div>
			<!-- 서브타이틀 : E -->
   
		  	<!-- 컨텐츠 : S -->
			<div id="contents">

				<form name=fmParam method="post" action="../cominfo/ComInfoAction.do">
				<input type="hidden" name="nowPage" value="<%=nowPage%>">
				<input type="text" name="none" style="display:none">			
				<input type="hidden" name="cmd" value="InfoCommTraderPub">
				<input type="hidden" name="seq" value="">
				<input type="hidden" name="seq2" value="">

				<!-- 검색 : S -->
				<table  class="t1" summary="검색를 나타내는 표" style="margin-bottom: 40px;">
                    <caption class="caption">검색</caption>
					<colgroup>
						<col style="width: 80px; " />
					</colgroup>
					<tbody>
						<tr>
	                      <th>조건</th>
	                      <td>
	                      	<KTags:KJFSelect item='<%=(Vector)request.getAttribute("v_scSD_CD")%>'
											 name='scSidoCode'
											 value='<%=KJFUtil.print(pm.getScSidoCode()) %>'
											 script=""/>
		                    <select name='scCode' class='input_bbs'  >
								<option value='001' <%=KJFUtil.print(pm.getScCode()).equals("001")?"selected":""%>> 등록번호 </option>
								<option value='002' <%=KJFUtil.print(pm.getScCode()).equals("002")?"selected":""%>> 상호 </option>
								<option value='003' <%=KJFUtil.print(pm.getScCode()).equals("003")?"selected":""%>> 대표자 </option>
							</select>
							<input class="input_bbs" name="scText" size="30" maxlength="30" onKeyUp="if(event.keyCode == 13) fn_search();" value="<%=KJFUtil.print(pm.getScText())%>" title="검색 내용을 입력해주세요">
							<a href="javascript:fn_search()"><img src="../images/bbs/btn_search.gif" alt="검색" align="absmiddle"></a>
						  </td>
	                    </tr>
                    </tbody>
                </table>
				<!-- 검색 : E -->
				
				<!-- 정보통신공사업자 검색 : S -->
				<h4>정보통신공사업자 검색결과</h4>
				<span>총 <span class="font_orangeB letter0"><%=totalCount%></span>개의 검색결과가 있습니다. </span>
				<table class="t3" summary="정보통신공사업자 검색를 나타내는 표">
					<caption class="caption">정보통신공사업자 검색</caption>
					<colgroup>
						<col style="width:50px; text-align: center;" />
						<col style="width:100px; text-align: center;" />
						<col style="width:px;" />
						<col style="width:90px; text-align: center;" />
						<col style="width:60px; text-align: center;" />
						<col style="width:120px; text-align: center;" />
						
					</colgroup>
					<thead>
	                   	<tr>
							<th scope="row">번호</th>
	                     	<th scope="row">등록번호</th>
	                     	<th scope="row">상호</th>
	                     	<th scope="row">대표자</th>
	                     	<th scope="row">소재지</th>
	                     	<th scope="row">전화번호</th>
	                   	</tr>
                   </thead>
                   <tbody>
                   		<% if (rEntity.getRowCnt() > 0) { %>
	                   		<% for(int i=0; i < rEntity.getRowCnt(); i++) { %>
				       			<tr  style="cursor:pointer" onClick="fn_detail('<%=KJFUtil.print(rEntity.getValue(i, "MANA_NUM"))%>','<%=KJFUtil.print(rEntity.getValue(i, "COI_WRT_NUM"))%>')">
									 <td class="letter0" align="center"><%=int_rnum--%></td>
				                     <td class="letter0" align="center"><%=KJFUtil.print(rEntity.getValue(i, "COI_WRT_NUM")) %></td>
				                     <td><%=KJFUtil.print(rEntity.getValue(i, "NAME")) %></td>
				                     <td align="center"><%=KJFUtil.print(rEntity.getValue(i, "REP_NM_KOR")) %></td>
				                     <td align="center"><%=KJFArea.getSido_Name(rEntity.getValue(i, "SIDO_CODE")) %></td>
				                     <td class="letter0" align="center"><%=KJFUtil.print(rEntity.getValue(i, "ADDR_TEL_NUM")) %></td>
			                   </tr>
							<% } %>
						<% } else { %>
							<tr>
			            		<td colspan="6" align="center">검색된 정보가 없습니다.</td>
							</tr>
						<% } %>
					</tbody>
				</table>
				<!-- 정보통신공사업자 검색 : E -->
				
				<!-- 페이지 : S -->
				<div class="center">
					<jsp:include page="../com/inc/paging.jsp" flush="true">
					  <jsp:param name="pagePerScreen" value="10"/>
					  <jsp:param name="rowPerPage" value="<%=rowPerPage%>"/>
					  <jsp:param name="totalCount" value="<%=totalCount%>"/>
					  <jsp:param name="nowPage" value="<%=nowPage %>"/>
					</jsp:include>	
				</div>
				<!-- 페이지 : E -->

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
