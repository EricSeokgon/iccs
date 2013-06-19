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
	
	int int_rnum = KJFUtil.str2int(totalCount)-(KJFUtil.str2int(nowPage) * KJFUtil.str2int(rowPerPage) - KJFUtil.str2int(rowPerPage));
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

    fm.scRecvName.value = fm.scRecvName.value.replace(/(\s*)/g,"");
	
    fm.scRecvNum.focus();
    fm.scRecvNum.select();
    
    fm.nowPage.value = 1;
    fm.submit();
}
function open_RegPopup(str1,str2){	
  var fm = document.fmParam;  
	winOpenAtCenter("../usebefore/UseBeforeAction.do?cmd=UseBeforeRegSign&scRecvNum="+str1+"&scSIGUNGU_CODE="+str2, "", 300, 150);
}
</script>

<html lang="ko">
<head>
<title>사용전검사현황 빠른검색</title>
<!-- 공통 : S -->

<link href="<%=imgUri%>/css/total.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="../com/js/com.js" type="text/javascript"></script>
<script type="text/javascript" src="../com/js/quick.js"></script>
<!-- 공통 : E -->

</head>
<body>

	<!-- 사용전검사 전체 615x590: S  -->
	<div id="popup_use">
		<div id="popup_title"><img src="<%=imgUri%>/images/common/popup_title_usebefore.gif" alt="사용전검사현황 빠른검색"></div>
		<div id="popup_close"><a href="javascript:close();"><img src="<%=imgUri%>/images/common/popup_close.gif" alt="닫기"></a></div>
		<div id="popup_contents_use">
				
				<form name=fmParam method="post" action="../usebefore/UseBeforeAction.do" >
				<input type="hidden" name="nowPage" value="<%=nowPage%>">
				<input type="hidden" name="cmd" value="UseBeforeQuickStatus">
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
							<input name="scRecvName" size="25" maxlength="25"  value="<%=KJFUtil.print(pm.getScRecvName())%>" title="신청자 이름을 입력해주세요">
						  </td>
	                      <th>접수번호<br/></th>
	                      <td align="center">
	                      	<input name="scRecvNum" size="17" maxlength="17" onkeyup="if(event.keyCode == 13) fn_search();" value="<%=KJFUtil.print(pm.getScRecvNum())%>" title="접수번호를 입력해주세요">
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
				    <col style="width: 50px; text-align: center" />
				    <col style="width: 50px; text-align: center" />				    
				    <col style="width: 100px; text-align: center" />
				    <col style="" />
				    <col style="width: 50px; " />
						<col style="width: 50px; text-align: center" />
				    <col style="width: 50px; text-align: center" />
				    <col style="width: 60px; text-align: center" />
				    </colgroup>
				    <thead>
				        <tr>
									<th scope="col">접수번호</th>
									<th scope="col">신청인</th>
				          <th scope="col">시공업체</th>
									<th scope="col">현장주소</th>
									<th scope="col">접수일자</th>		
									<th scope="col">검사<BR />희망일</th>		
									<th scope="col">검사<BR />완료일</th>		
									<th scope="col">진행상태</th>
				        </tr>
				    </thead>
				    <tbody>
						<% if (rEntity.getRowCnt() > 0 ) {
								 String proc_ste = ""; String ap_nm ="";
								 String sido_code = ""; String sigungu_code = "";	
								 String civ_recv_num = "";String recv_num = "";
								 String server_yn = "";	 String ub_print_chk = "";
								 String online_cert_use_yn = "N";
								 for (int i = 0; i < rEntity.getRowCnt(); i++) {
									 proc_ste = KJFUtil.print(rEntity.getValue(i,"PROC_STE"));
									 ap_nm = KJFUtil.print(rEntity.getValue(i, "APPLPER_NM"));
									 civ_recv_num = KJFUtil.print(rEntity.getValue(i, "CIV_RECV_NUM"));
									 server_yn = KJFUtil.print(rEntity.getValue(i, "SERVER_YN"),"0");
									 online_cert_use_yn = KJFUtil.print(rEntity.getValue(i, "ONLINE_CERT_USE_YN"),"N");
									
									 if ("처리완료".equals(proc_ste) && "1".equals(server_yn) && "Y".equals(online_cert_use_yn)){
										 ub_print_chk ="1";
									 } else if ("처리완료".equals(proc_ste) && "0".equals(server_yn)) {
										 ub_print_chk ="2";
									 } else { 
										 ub_print_chk ="3"; 
									 }
									 
						%>
							<tr>
					        <td align="center" class="letter0"><%=pm.getScRecvNum() %></td>
							    <td align="center"><%=ap_nm %></td>
							    <td align="center" class="letter0"><%=KJFUtil.print(rEntity.getValue(i, "OPE_NAME")) %></td>
							    <td align="center" class="letter0"><%=KJFUtil.print(rEntity.getValue(i, "INSP_SPOT_ADDR"))+KJFUtil.print(rEntity.getValue(i, "INSP_SPOT_DETAILADDR")) %></td>
									<td align="center" class="letter0"><%=KJFUtil.print(KJFDate.getChangDatePattern(rEntity.getValue(i, "RECV_DT"), "yyyyMMdd", "yy/MM/dd"), "&nbsp;") %></td>
									<td align="center" class="letter0"><%=KJFUtil.print(KJFDate.getChangDatePattern(rEntity.getValue(i, "INSP_WISH_YMD"), "yyyyMMdd", "yy/MM/dd"), "&nbsp;") %></td>
									<td align="center" class="letter0"><%=KJFUtil.print(KJFDate.getChangDatePattern(rEntity.getValue(i, "INSP_DT"), "yyyyMMdd", "yy/MM/dd"), "&nbsp;") %></td>
									
									<td align="center" class="letter0">
								<% 
										if("1".equals(ub_print_chk)){
											sigungu_code = KJFUtil.print(rEntity.getValue(i, "SIGUNGU_CODE"));
											out.println(proc_ste + "<img src='../images/btn/btn_cert_public.gif' onClick=\"javascript:open_RegPopup('"+civ_recv_num+"','"+sigungu_code+"')\" /> ");	
										} else if("2".equals(ub_print_chk)){
											out.println(proc_ste + "<img src='../images/btn/btn_cert_public.gif' onClick=\"javascript:alert('서비스 지역이 아닙니다');\" /> ");
										} else {
											out.println(proc_ste);
										}
								%>
								</td>
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