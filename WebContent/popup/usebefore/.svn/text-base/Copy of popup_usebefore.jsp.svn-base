<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="kjf.ops.*" %>
<%@ page import="kjf.util.*" %>
<%@ page import="sp.usebefore.*" %>
<%
	String imgUri = request.getContextPath();
	
	ReportEntity rEntity = KJFUtil.REntPrint( (ReportEntity)request.getAttribute("rEntity") );
	
	UseBeforeParam pm = (UseBeforeParam)request.getAttribute("pm");
	
	// 페이징 관련(필수)
	String nowPage    = KJFUtil.print(pm.getNowPage());
	String rowPerPage = KJFUtil.print(pm.getRowPerPage());
	String totalCount = KJFUtil.print(pm.getTotalCount());
	
	int int_rnum = KJFUtil.str2int(totalCount)-(KJFUtil.str2int(nowPage) *KJFUtil.str2int(rowPerPage) - KJFUtil.str2int(rowPerPage));
%>

<script language="JavaScript" type="text/javascript">

// 검색
function fn_search() {
    var fm = document.fmParam;
    
    if (fm.scRecvName.value.trim() == "") {
    	fm.scRecvName.focus();
    	fm.scRecvName.select();
		alert("신청자 이름을 입력해주세요!");
		return;
    }

    if (fm.scRecvTel.value.trim() == "") {
    	fm.scRecvTel.focus();
    	fm.scRecvTel.select();
		alert("전화번호 뒷자리 4자리를 입력해주세요!");
		return;
    }
    
    fm.nowPage.value = 1;
    fm.submit();
}
</script>

<html>
<head>
<title>사용전검사현황 빠른검색</title>
<!-- 공통 : S -->

<link href="<%=imgUri%>/css/total.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="<%=imgUri%>/com/js/com.js" type="text/javascript"></script>

<!-- 공통 : E -->

</head>
<body>

	<!-- 사용전검사 전체 615x590: S  -->
	<div id="popup_use">
		<div id="popup_title"><img src="<%=imgUri%>/images/common/popup_title_usebefore.gif" alt="사용전검사현황 빠른검색"></div>
		<div id="popup_close"><a href="javascript:close();"><img src="<%=imgUri%>/images/common/popup_close.gif" alt="닫기"></a></div>
		<div id="popup_contents_use">
				
				<form name=fmParam method="post" action="../usebefore/UseBeforeAction.do?cmd=UseBeforeQuickStatus" >
				<input type="hidden" name="nowPage" value="<%=nowPage%>">
				<input type="text" name="none" style="display:none">

				<!--검색 : S -->
				<table  class="t1" summary="검색를 나타내는 표">
                    <caption class="caption">검색</caption>
					<colgroup>
						<col style="width: 90px; " />
						<col style="width: px; " />
						<col style="width: 90px; " />
						<col style="width: px; " />
					</colgroup>
					<tbody>
						<tr>
	                      <th>신청자 명</th>
	                      <td align="center">
							<input name="scRecvName" size="20" maxlength="20"  value="<%=KJFUtil.print(pm.getScRecvName())%>" title="신청자 이름을 입력해주세요">
						  </td>
	                      <th>전화번호<br/>(뒷자리 4자리)</th>
	                      <td align="center">
	                      	<input name="scRecvTel" size="10" maxlength="4" onkeyup="if(event.keyCode == 13) fn_search();" value="<%=KJFUtil.print(pm.getScRecvTel())%>" title="접수번호를 입력해주세요">
	                      	<a href="javascript:fn_search()"><img src="../images/bbs/btn_search.gif" alt="검색" align="absmiddle"></a>
	               		  </td>
	                    </tr>
					
					</tbody>
                </table>
				<!--검색 : E -->

				<!-- 사용전검사 접수현황 리스트 : S -->
				<h4>사용전검사 접수현황</h4>
				<table class="t2" summary="번호 접수번호 신청인 시공업체 현장명칭 접수일자 진행상태를 나타내는 표">
				    <caption>사용전검사 접수현황을 나타내는 표</caption>
				    <colgroup>
				    <col style="width: 35px; text-align: center" />
				    <col style="width: 70px; text-align: center" />				    
				    <col style="width: 50px; text-align: center" />
				    <col style="" />
				    <col style="width: 100px; " />
					<col style="width: 60px; text-align: center" />
				    <col style="width: 60px; text-align: center" />
				    <col style="width: 60px; text-align: center" />
				    </colgroup>
				    <thead>
				        <tr>
				            <th scope="col">번호</th>
							<th scope="col">접수번호</th>
							<th scope="col">신청인</th>
				            <th scope="col">시공업체</th>
							<th scope="col">현장명칭</th>
							<th scope="col">접수일자</th>		
							<th scope="col">검사예정일</th>				
							<th scope="col">진행상태</th>
				        </tr>
				    </thead>
				    <tbody>
						<% if (rEntity.getRowCnt() > 0 ) {%>
							<% for (int i = 0; i < rEntity.getRowCnt(); i++) { %>
							<tr>
					            <td align="center" class="letter0"><%=int_rnum--%></td>
					            <td align="center" class="letter0"><%=KJFUtil.print(rEntity.getValue(i, "CIV_RECV_NUM")) %></td>
							    <td align="center"><%=KJFUtil.print(rEntity.getValue(i, "APPLPER_NM")) %></td>
							    <td align="center" class="letter0"><%=KJFUtil.print(rEntity.getValue(i, "OPE_NAME")) %></td>
							    <td align="center" class="letter0"><%=KJFUtil.print(rEntity.getValue(i, "INSP_SPOT_NM")) %></td>
								<td align="center" class="letter0"><%=KJFUtil.print(KJFDate.getChangDatePattern(rEntity.getValue(i, "RECV_DT"), "yyyyMMdd", "yyyy/MM/dd"), "&nbsp;") %></td>
								<td align="center" class="letter0"><%=KJFUtil.print(KJFDate.getChangDatePattern(rEntity.getValue(i, "INSP_DT"), "yyyyMMdd", "yyyy/MM/dd"), "&nbsp;") %></td>
								<td align="center" class="letter0"><%=KJFUtil.print(rEntity.getValue(i, "PROC_STE")) %></td>
							</tr>
							<% } %>
						<% } else { %>
							<tr>
								<td  align="center" colspan="8">등록된 정보가 없습니다.</td>
							</tr>
						<% } %>
						
				    </tbody>
				</table>
				<!-- 사용전검사 접수현황 리스트 : E -->

				<!-- 페이징 : S -->
				<div class="center">
					<jsp:include page="../../com/inc/paging.jsp" flush="true">
					  <jsp:param name="pagePerScreen" value="10"/>
					  <jsp:param name="rowPerPage" value="<%=rowPerPage%>"/>
					  <jsp:param name="totalCount" value="<%=totalCount%>"/>
					  <jsp:param name="nowPage" value="<%=nowPage %>"/>
					</jsp:include>	
				</div>
				<!-- 페이징 : E -->
				
			</form>
		</div>
		<div id="popup_use_close"><a href="javascript:close();"><img src="<%=imgUri%>/images/common/favorite_btn_close.gif" alt="닫기"></a></div>
	</div>
	<!-- 사용전검사 전체 : E -->

</body>
</html>