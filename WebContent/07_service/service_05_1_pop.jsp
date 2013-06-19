<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="sp.service.*" %>
<%@ page import="kjf.ops.*" %>
<%@ page import="kjf.util.KJFFile" %>
<%@ taglib uri="/KJFTags" prefix="KTags" %>
<%@ include file="../inc/user_inc.jsp" %>
<%
	ServiceParam pm = (ServiceParam)request.getAttribute("pm");
	ReportEntity rEntity = (ReportEntity)request.getAttribute("rEntity");
	
	String NM 			= KJFUtil.print(rEntity.getValue(0,"NM"),"&nbsp;");
	String SIDO_NM 		= KJFUtil.print(rEntity.getValue(0,"SIDO_NM"),"&nbsp;");
	String SIGUN_NM 	= KJFUtil.print(rEntity.getValue(0,"SIGUN_NM"),"&nbsp;");
	String TEL 			= KJFUtil.print(rEntity.getValue(0,"TEL"),"&nbsp;");
	String PART 		= KJFUtil.print(rEntity.getValue(0,"PART"),"&nbsp;");
	String POS 			= KJFUtil.print(rEntity.getValue(0,"POS"),"&nbsp;");
	String GROUP_NM 	= KJFUtil.print(rEntity.getValue(0,"GROUP_NM"),"&nbsp;");
	String HOME_GROUP_NM = KJFUtil.print(rEntity.getValue(0,"HOME_GROUP_NM"),"&nbsp;");
	String MOBILE 		= KJFUtil.print(rEntity.getValue(0,"MOBILE"),"&nbsp;");
	String E_MAIL 		= KJFUtil.print(rEntity.getValue(0,"E_MAIL"),"&nbsp;");
	String OFFSEAL 		= KJFUtil.print(rEntity.getValue(0,"OFFSEAL"),"&nbsp;");
	String PHOTO 			= KJFUtil.print(rEntity.getValue(0,"PHOTO"),"&nbsp;");
	String SIDO_CODE 		= KJFUtil.print(rEntity.getValue(0,"SIDO_CODE"),"&nbsp;");
	String OFFI_ID 		= KJFUtil.print(rEntity.getValue(0,"OFFI_ID"),"&nbsp;");
	String Sub_Lo			= SIDO_CODE+KJFFile.FILE_S+OFFI_ID;

	if(ruleChk){
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="ko">
<head>
<title>담당자 상세 정보 페이지입니다.</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="robots" content="noindex,nofollow">
<meta name="author" content="부산광역시">
<meta name="keywords" content="정보통신공사업시스템">
<meta name="description" content="정보통신공사업시스템">
<meta http-equiv="imagetoolbar" content="no">

<!-- 공통 : S -->
<link href="../css/total.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="../com/js/comm.js" type="text/javascript"></script>
<script language="JavaScript" src="../com/js/topMenu.js" type="text/javascript"></script>
<script language="javascript" src="../com/js/httpRequest.js" type="text/javascript"></script>
<script language="javascript">
function init_content_img(obj , width) {
	var imgInfo = new Image();

	imgInfo.src = obj.src;
	org_width = imgInfo.width;
	org_heigth = imgInfo.height;

	if (org_width > eval(width)) {
		rate=eval(width)/org_width;
		obj.width = eval(width);
		obj.height = org_heigth*rate;
	}
}
</script>
<!--개인정보수정 : S -->
</head>
<body class="no_img" >
<form name="fmParam" >
	<br/><br/>
	<!--개인정보입력 타이틀2 : S -->
	<div class="mem_title_2">
		<div class="mem_title_2_img">
			<img src="../07_service/images/servant_title.gif" alt="담당자 상세정보">
		</div>	
		
		<!--개인정보입력:시작 -->
		<div class="line_T2B1">
			<!--Write: 시작 -->
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr> 
    			<td rowspan="10">
    				<% 	if("".equals(PHOTO)){ %>
    							<img src="../images/common/no_photo.gif">
    				<%	}	else {%>
 	    				<img src="../com/downLoad.jsp?Lo=ICCSM_FILE_DIR&Sub_Lo=<%=Sub_Lo%>&SYS_FILE_NAME=<%=PHOTO%>&FILE_REAL_NAME=<%=PHOTO%>"
					 onload='init_content_img(this , 630);' onclick='big_img1(this);' style='cursor:hand'>
					 <%  } %>
    				
					</td>
					<td class="form_detail">
					<img src="../00_member/images/join/detail_01name.gif" alt="이름">
					</td>
					<td class="td_view_detail" >
						<%=NM%>
					</td>
					<td class="form_detail">
					<img src="../00_member/images/join/detail_10email.gif" alt="이메일">
					</td>
					<td class="td_view_detail" >
						<%=E_MAIL%>
					</td>
  			</tr>

			  <tr>
					<td colspan="5" class="line_1_d1"></td>
			  </tr>
			  		  
			  <tr>
				<td class="form_detail">
				<img src="../07_service/images/servant_01.gif" alt="업무구분">
				</td>
				<td class="td_view_detail" >
					<%=GROUP_NM%>
				</td>
				<td class="form_detail">
				<img src="../07_service/images/servant_02.gif" alt="홈페이지관리">
				</td>
				<td class="td_view_detail" >
					<%=HOME_GROUP_NM%>
				</td>
			  </tr>
	
			  <tr>
				<td colspan="5" class="line_1_d1"></td>
			  </tr>
			  			  		  
			  <tr>
				<td class="form_detail">
				<img src="../00_member/images/join/detail_08tel.gif" alt="전화번호">
				</td>
				<td class="td_view_detail" >
					<%=TEL%>
				</td>			  
				<td class="form_detail">
				<img src="../00_member/images/join/detail_09mobile.gif" alt="핸드폰번호">
				</td>
				<td class="td_view_detail" >
					<%=MOBILE%>
				</td>
			  </tr>
			  	
			  <tr>
				<td colspan="5" class="line_1_d1"></td>
			  </tr>
				
			  <tr>
				<td class="form_detail">
				<img src="../00_member/images/join/detail_06province.gif" alt="시도">
				</td>
				<td class="td_view_detail" >
					<%=SIDO_NM%>
				</td>
				<td class="form_detail">
				<img src="../00_member/images/join/detail_07county.gif" alt="시군구">
				</td>
				<td class="td_view_detail" >
					<%=SIGUN_NM%>
				</td>
			  </tr>
	
			  <tr>
				<td colspan="5" class="line_1_d1"></td>
			  </tr>

			  <tr>
				<td class="form_detail">
				<img src="../07_service/images/servant_04.gif" alt="부서">
				</td>
				<td class="td_view_detail" >
					<%=PART%>
				</td>
				<td class="form_detail">
				<img src="../07_service/images/servant_05.gif" alt="직급">
				</td>
				<td class="td_view_detail" >
					<%=POS%>
				</td>
			  </tr>
		  </table>
			<!--Write:끝 -->
		</div>
		<!--개인정보입력 : E -->
		
	</div>
	<!--개인정보입력 타이틀2 : E -->
	
	<!--버튼 :S -->
	<!-- div id="mem_kind_bg">
		<div id="mem_btn">
			<img src="../00_member/images/btn_cancel.gif" alt="취소" onClick="window.colse();"></a>
		</div>
	</div -->
	<!--버튼 :E -->

</form>
<!--개인정보수정 : E -->

</body>
</html>
<%}%>