<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="kjf.ops.*" %>
<%@ page import="kjf.util.*" %>
<%@ page import="sp.mem.*" %>

<%
	ReportEntity rEntity = KJFUtil.REntPrint( (ReportEntity) request.getAttribute("rEntity") );

	//상단 플래쉬 링크 정보
	String top_pageNum  = "0";
	String top_sub      = "0";
	
	// 좌측 플래쉬 링크 정보
	String left_pageNum = "4";
	String left_sub     = "4";
%>

<script language="javascript">

function Lpad(str, len) {
 	str = str + "";
 	
	while(str.length < len) {
  		str = "0" + str;
 	}
 	
 	return str;
}

var iMinute = 5;	// 시간 지정 분
var iSecond = iMinute * 60 ;
var timerchecker = null;

initTimer = function() {
	
 	rMinute = parseInt(iSecond / 60);
 	rSecond = iSecond % 60;
 	
 	if(iSecond > 0) {
  		timer.innerHTML = Lpad(rMinute, 2) + " : " + Lpad(rSecond, 2) + " 남았습니다.";
  		iSecond--;
  		timerchecker = setTimeout("initTimer()", 1000); // 1초 간격으로 체크
  		
 	} else {
  		clearTimeout(timerchecker);
  		var fm = document.fmParam;
		fm.action = "../mem/MemAction.do?cmd=MemMobileTimeOver";
		fm.submit();
 	}
}

// SMS 인증요청
function fn_sendSMS() {

	var fm = document.fmParam;

	if (fm.validation_flag.value.trim() == "1") {
		alert("이미 SMS 인증요청을 하셨습니다.");
		return;
	}
	
	if (confirm("SMS 인증요청을 하시겠습니까?")) {

		if (fm.user_name.value.trim() == "") {
			 alert("회원님의 사용자 정보가 없습니다.");
			 return;
	    }
	    
		if (fm.mobile.value.trim() == "") {
			 alert("회원님의 핸드폰 번호가 없습니다.");
			 return;
		}

		if ( fm.mobile.value) {
			if ( !isNumeric(fm.mobile.value) || fm.mobile.value.length < 10) {
				alert("올바른 핸드폰 번호가 아닙니다");
				fm.mobile.focus();
				return;
			}
		}
	  
		fm.validation_flag.value = "1";
		fn_sendSMSValidationNum();
    }
}

function fn_sendSMSValidationNum() {

	var fm = document.fmParam;
	
	if (fm.validation_num_temp.value.trim() == "") {
		alert("인증번호 생성을 실패하였습니다.");
		return;
		
	} else {		
		var validation_num = fm.validation_num_temp.value;

		sendRequest("../comm/CommAction.do", "cmd=CommSMSSend&validation_num=" + validation_num, fn_SMSValidationNumView, "POST");
	}
}

function fn_SMSValidationNumView() {
	var result = httpRequest.responseText;

	if (result == "success") {
		alert("인증번호가 성공적으로 전송되었습니다.\n\n시스템 상태에 따라 전송이 지연 될 수  있습니다.");
		initTimer();		
		
	} else {
		alert("인증번호 전송을 실패하였습니다. \n\n가입된 회원님의 핸드폰 번호가 잘못되었거나,\n\n시스템 장애로 인하여 전송되지않을 수 있습니다.");
		var fm = document.fmParam;
		fm.action = "../mem/MemAction.do?cmd=MemMobileReg";
		fm.submit();
	}
}

// 모바일 서비스 신청
function fn_mobileServiceSend() {

	var fm = document.fmParam;

	if (fm.validation_flag.value == "0") {
		 alert("SMS 인증요청 버튼을 클릭해주세요");
		 return;
    } 

	if (fm.validation_num.value.trim() == "") {
		 alert("인증 번호를 입력해 주세요");
		 fm.validation_num.select();
		 fm.validation_num.focus();
		 return;
    }	
	
	if (fm.validation_num_temp.value.trim() != fm.validation_num.value.trim()) {
		 alert("인증번호가 일치하지 않습니다.");
		 return
    } 

    fm.submit();
}

</script>

<html>
<head>
<title>모바일 서비스 사용신청 페이지입니다.</title>

<!-- 공통 : S -->
<link href="../css/total.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="../com/js/com.js" type="text/javascript"></script>
<script language="JavaScript" src="../com/js/common_util.js" type="text/javascript"></script>
<script language="JavaScript" src="../com/js/topMenu.js" type="text/javascript"></script>
<script language="JavaScript" src="../flash/flash.js" type="text/javascript"></script>
<script language="javascript" src="../com/js/httpRequest.js" type="text/javascript"></script>
<!-- 공통 : E -->

</head>
<body>

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
					<a href="../"><img src="../images/common/icon_home.gif" alt="HOME"></a> My민원창구 | 모바일서비스사용신청
				</p>
			</div>
			<!-- 현재위치 : E -->
	
			<!-- 서브타이틀 : S -->
			<div id="sub_title">
				<h3 class="cont01"><img src="../01_one/images/titile_06_4.gif" alt="모바일 서비스 사용신청"></h3>
			</div>
			<!-- 서브타이틀 : E -->
   
		  	<!-- 컨텐츠 : S -->
	  	  	<div id="contents">

				<form name="fmParam" action="../mem/MemAction.do?cmd=MemMobileRegU" method="post">
				<input type="hidden" name="validation_num_temp"	value="<%=KJFUtil.getTempNum(10)%>">
				<input type="hidden" name="validation_flag"	    value="0">

				<!-- 모바일 서비스 사용신청 : S -->
				<h4><img src="../01_one/images/sub_title_04_1.gif" alt="모바일 서비스란?"></h4>
				<p class="txt">
					본 서비스는 모바일(핸드폰, PDA)를 이용하여 개인의 행정 정보 및 관련 데이터를 조회/예약을 사용 할 수 있도록 함을 전제로 하며, 모바일 서비스 동의 및 핸드폰 인증을 거친 후 언제 어디서든 모바일(핸드폰, PDA)를 이용하여 개인 정보를 보실 수 있습니다.
				</p>

				<% if ( KJFUtil.print(rEntity.getValue(0, "USER_MOBILE_YN")).equals("Y") ) { %>
					<!-- 신청하기 : S -->
					<h4><img src="../01_one/images/sub_title_04_2.gif" alt="모바일 서비스 신청하기"></h4>
					<div id="orange_650">
	
						<div class="mobile_form">
						<span style="margin-left:20px; margin-right: 120px;">
							<label for="user_name">
								<span class="name">
									<img src="../00_member/images/search/detail_name.gif" alt="이름" />
								</span>
							</label>
							<input name="user_name" id="user_name" value="<%=user.getUSER_NAME()%>" type="text" size="15" maxlength="15" readonly>
						</span>
	
						<span>
							<label for="user_ssn">
								<span class="name">
									<img src="../00_member/images/search/detail_mobile.gif" alt="핸드폰 번호" />
								</span>
							</label>									
							<input name="mobile" id="mobile" type="text" value="<%=user.getUSER_MOBILE()%>" size="15" maxlength="15" readonly>
							<!--  
							<label for="kind_1"><input type="radio" id="kind_1" name="mobile_kind" class="radio" value="S" checked>SKT</label>
			  				<label for="kind_2"><input type="radio" id="kind_2" name="mobile_kind" class="radio" value="K">KTF</label>
							<label for="kind_3"><input type="radio" id="kind_3" name="mobile_kind" class="radio" value="L">LGT</label>		
							-->	
						</span>
						</div>
	
					  	<div class="center" style="border:none; margin-bottom:0">												
							회원님은 <span class="font_orangeB">이미 모바일 서비스 신청</span>을 하셨습니다.
							
						</div>
						<div id="orange_650_bt" ></div>	
					</div>
					<!-- 신청하기 : S -->
					
					<!-- 버튼 : S -->										
					<div class="center">
						<a href="javascript:fn_mobileServiceSend()"><img src="../00_member/images/btn_ok.gif" alt="확인"></a>
						<a href="#"><img src="../00_member/images/btn_cancel.gif" alt="취소"></a>
					</div>				
					<!-- 버튼 : E -->				
				
			
				<% } else { %>
					<!-- 신청하기 : S -->
					<h4><img src="../01_one/images/sub_title_04_2.gif" alt="모바일 서비스 신청하기"></h4>
					<div id="orange_650">
	
						<div class="mobile_form">
							<span style="margin-left:20px; margin-right: 120px;">
							<label for="user_name">
								<span class="name">
									<img src="../00_member/images/search/detail_name.gif" alt="이름" />
								</span>
							</label>
							<input name="user_name" id="user_name" value="<%=user.getUSER_NAME()%>" type="text" size="15" maxlength="15" readonly>
							</span>
							
							
							<span>
							<label for="user_ssn">
								<span class="name">
									<img src="../00_member/images/search/detail_mobile.gif" alt="핸드폰 번호" />
								</span>
							</label>									
							<input name="mobile" id="mobile" type="text" value="<%=user.getUSER_MOBILE()%>" size="15" maxlength="15" readonly>
							<!--  
							<label for="kind_1"><input type="radio" id="kind_1" name="mobile_kind" class="radio" value="S" checked>SKT</label>
			  				<label for="kind_2"><input type="radio" id="kind_2" name="mobile_kind" class="radio" value="K">KTF</label>
							<label for="kind_3"><input type="radio" id="kind_3" name="mobile_kind" class="radio" value="L">LGT</label>		
							-->	
							</span>
						</div>
	
					  	<div class="center" style="border:none; margin-bottom:0">												
							<a href="javascript:fn_sendSMS()"><img src="../images/btn/btn_sms_num.gif" alt="SMS 인증요청" border="0" align="absmiddle"></a>
							
						</div>
						<div id="orange_650_bt" ></div>	
					</div>
					<!-- 신청하기 : S -->
					
					<!-- 인증번호 입력 : S -->
					<h4><img src="../01_one/images/sub_title_04_3.gif" alt="인증번호 입력"></h4>
					<div id="orange_650">
						<div class="mobile_form">
							<label for="user_name">
								<span class="name"><img src="../00_member/images/search/detail_sms_num.gif" alt="인증번호 입력" /></span>
							</label>
							</span>
							<input name="validation_num" id="validation_num" type="text" size="20" maxlength="20">&nbsp;
							<span id="timer" class="font_orangeB"></span>
						</div>
						<p class="txt">
							<strong>SMS인증요청</strong>을 클릭하신 후 <strong>휴대폰으로 전송된 인증번호를 입력</strong>하여 주십시오.<br>
							인증요청 클릭 후 5분 안에 입력하시길 바랍니다. <br>
							5분 이후에는 다시 SMS인증요청 을 클릭하여 새로 인증번호를 받아서 이용하시길 바랍니다.
						</p>
					  	<div id="orange_650_bt" ></div>	
					</div>
					<!-- 인증번호 입력 : E -->
					
					<!-- 버튼 : S -->										
					<div class="center">
						<a href="javascript:fn_mobileServiceSend()"><img src="../00_member/images/btn_ok.gif" alt="확인"></a>
						<a href="#"><img src="../00_member/images/btn_cancel.gif" alt="취소"></a>
					</div>				
					<!-- 버튼 : E -->
				<% } %>
				<!-- 모바일 서비스 사용신청 : E -->
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
