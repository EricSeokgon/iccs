<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="sp.usebefore.UseBeforeParam" %>
<%@ page import="kjf.ops.*" %>
<%@ page import="kjf.util.*" %>
<%
		ReportEntity rEntity  = KJFUtil.REntPrint((ReportEntity)request.getAttribute("rEntity"));
%>
<html>
<head>
<title>인증센터</title>
<!-- 공통 : S -->

<link href="../css/register.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="../com/js/kjs.js" type="text/javascript"></script>
<script language="JavaScript" src="../com/js/comm.js" type="text/javascript"></script>
<script language="JavaScript" src="../com/js/common_util.js" type="text/javascript"></script>
<script language="JavaScript" src="../com/js/join.js" type="text/javascript"></script>
<script language="JavaScript">
function go_regist_Pri_ub() {
	
	var fm = document.identityP_form;
	
	//if (check_identityPrivate()) {	//  체크			
	//	if(individualTestimony_ub()) {	// 인증확인
	//		fm.scRecvName.value = fm.user_name.value;
			fm.submit();
	//	}
	//}	
}
/**
 * 숫자만으로 구성된 문자열인지 체크합니다.
 */
function isdigit(str) 
{
    if (str.search(/[^0-9]/g)==-1) return true;
    else return false;
}

</script>
<!-- 공인인증서 확인 : S -->
<script language="JavaScript" src="../com/certificate/js/CC_Testimony.js" type="text/javascript"></script>
<!-- 공인인증서 확인 : E -->
<!-- 공통 : E -->

</head>
<body>
<!-- 키보드 보안 : S -->
<SCRIPT SRC="http://update.spaceinter.com/easykeytec/gov/busan/easykeytec.js" LANGUAGE="JAVASCRIPT"></SCRIPT>
<!-- 키보드 보안 : S -->

<!-- 전자인증 모듈 : S-->
<script type="text/javascript" src="../com/certificate/js/certificate.js"></script>
<script type="text/javascript" src="../com/certificate/js/init.js"></script>

<object title="전자인증모듈" id="CC_Object_id"
      classid="CLSID:A099920B-630C-426B-91EC-737685CEEE17"
	  codebase="../com/certificate/cab/AxCrossCert.cab#Version=2,6,1,47"
      width= Document.body.clientWidth
      height= Document.body.clientHeight>
</object>
<!-- 전자인증 모듈 : E-->

					<!-- orange body : S -->
					<div id="orange_body_no">
						<div class="search_form">

							<form name="identityP_form" action="../usebefore/UseBeforeAction.do" method="post">
								<input type="hidden" name="cmd" value="UseBeforeLog">
								<input type="hidden" name="scCIV_RECV_NUM" value="<%=rEntity.getValue(0,"CIV_RECV_NUM")%>">
								<input type="hidden" name="scRecvNum" value="<%=rEntity.getValue(0,"RECV_NUM")%>">
								<input type="hidden" name="scSIDO_CODE" value="<%=rEntity.getValue(0,"SIDO_CODE")%>">
								<input type="hidden" name="scSIGUNGU_CODE" value="<%=rEntity.getValue(0,"SIGUNGU_CODE")%>">
								<input type="hidden" name="user_ssn" value="">
								<input type="hidden" name="scRecvName" value="">
								<input type="hidden" name="user_dn" value="">
								<div class="line_1">
									<span class="search_form_title"><label for="user_name"><img src="../00_member/images/search/detail_name.gif" alt="이름" /></label></span>
									<span>
									<input name="user_name" value="<%=rEntity.getValue(0,"APPLPER_NM") %>" id="user_name" type="text" size="20" maxlength="20" title="이름을 입력해주세요"  >
									</span>
								</div>
								
								<div class="line_1">
									<span class="search_form_title"><img src="../00_member/images/search/detail_num.gif" alt="주민등록번호" width="53" height="10" /></span>
									<span>
										<input name="user_ssn1" number onkeyup="ee_on_keyup(this)" type="text" value="" size="6" maxlength="6" title="주민번호 앞자리를 입력해주세요"> - 
										<input name="user_ssn2" number onkeyup="ee_on_keyup(this)" type="password" value="" size="12" maxlength="7" title="주민번호 뒷자리를 입력해주세요">
									</span>
								 </div>
								
								 <div style="padding-top:20px; margin-bottom: 4px;">
									<a href="javascript:go_regist_Pri_ub()"><img src="../00_member/images/btn_ok.gif" alt="확인"></a>
									<a href="javascript:close();"><img src="../images/common/favorite_btn_close_big.gif" alt="닫기"></a>
								 </div>
								 
						 	 </form>							
						
						</div>
					</div>
					<!-- orange body : E -->

	
</body>
</html>