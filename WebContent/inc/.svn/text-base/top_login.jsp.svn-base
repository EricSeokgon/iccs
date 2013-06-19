<%@ page contentType="text/html;charset=utf-8"%>

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

<form name="loginfrm" action="../login/LoginAction.do" method="post" onSubmit='return login_sendit()'>

<%if (user == null) {%>
<input type="hidden" name="getR">
<input type="hidden" name="userdn">
<input type="hidden" name="user_cert">
<input type="hidden" name="cmd" value="Login">

	<!-- 로그인 : S -->
	<div id="login_form">
		<input type="text" name="id" maxlength="16" title="아이디를 입력하세요" class="idbox" />
		<input type="password" name="password" maxlength="16" title="비밀번호를 입력하세요" onkeyup="if(event.keyCode == 13) login_sendit();" class="pwbox" />
		<ul>
			<li><a href="javascript:login_sendit()"><img src="../images/common/top_login_btn_ok.gif" alt="로그인" class="image"></a></li>
			<li><a href="javascript:SignData()"><img src="../images/common/top_login_btn_clogin.gif" alt="공인인증서로그인" class="image"></a></li>
			<li><a href="../member/regist.do"><img src="../images/common/top_login_btn_regi.gif" alt="회원가입" class="image"></a></li>
			<li><a href="../member/find.do"><img src="../images/common/top_login_btn_search.gif" alt="아이디비밀번호찾기" class="image"></a></li>
		</ul>
	</div>
	<!-- 로그인 : E -->

<%} else {%>
	<!-- 로그인 후 : S -->
	<input type="hidden" name="cmd" value="LogOut">
	<div id="login_after">
		<ul>
			<li><img alt="" src="../images/common/top_login_dot.gif" />
			<li><%=user.getUSER_NAME()%>님! 환영합니다.</li>
	 		<li><a href="javascript:logout_sendit()"><img src="../images/common/top_login_btn_logout.gif" alt="로그아웃" class="image"></a></li>
<!-- 		<li><a href="javascript:fn_goMystore()"><img src="../images/common/top_login_btn_mypage.gif" alt="My민원창구" class="image"></a></li> -->
		</ul>
	</div>
	<!--  로그인 후 : E -->
<%} %> 

</form>