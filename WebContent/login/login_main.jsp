<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="kjf.cfg.*" %>
<%@ page import="kjf.util.*" %>
<%@ page import="sp.uent.*" %>

<%
	UserEnt user = (UserEnt)request.getSession().getAttribute("user");

	String strID     = KJFUtil.print(request.getParameter("id"));
	String beforeURL = KJFUtil.print(request.getParameter("beforeURL"));
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="ko">
<head>
<title><%=Config.props.get("HOMEPAGE_TITLE")%> 로그인 페이지입니다.</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="robots" content="noindex,nofollow">
<meta name="author" content="부산광역시">
<meta name="keywords" content="정보통신공사업시스템">
<meta name="description" content="정보통신공사업시스템">
<meta http-equiv="imagetoolbar" content="no">
<!-- 공통 : S -->
<link href="../css/member.css" rel="stylesheet" type="text/css" >
<script type="text/javascript">

// 포커스
function fn_onFocus() {
	frm = document.all.loginForm;
	frm.id.focus();
}

// 로그인
function login_sendit() {
	frm = document.all.loginForm;

	if (frm.id.value.trim() == "") {
		alert("아이디를 입력 하여 주십시요");
		frm.id.value="";
		frm.id.focus();
		return;
	}
	
	if (frm.password.value.trim() == "") {
		alert("암호를 입력 하여 주십시요");
		frm.password.value="";
		frm.password.focus();
		return;
	}
	
	frm.submit();
}
</script>
<script type="text/javascript" src="../com/js/com.js" type="text/javascript"></script>
<!-- 공통 : E -->

<!-- 전자인증 모듈 : S -->
<script type="text/javascript"  src="../com/certificate/js/certificate.js"></script>
<script type="text/javascript"  src="../com/certificate/js/init.js"></script>
<!-- 전자인증 모듈 : E -->

</head>
<body onload="fn_onFocus()">
<noscript><p>자바스크립트를 지원해야 올바르게 동작하는 페이지입니다.</p></noscript>

<!-- 전체 : S -->
<div id="wrapper" >
	<div id="skipnavigation">
		<ul>
		<li><a tabindex="0" href="#contents">본문으로 바로가기</a></li>
		<li><a tabindex="0" href="#topmenu">대메뉴로 바로가기</a></li>
		</ul>
	</div>
		
	<!-- header : S-->
	<div id="header">
  		<div id="login"><img src="../images/member/title_login.gif" alt="로그인"></div>	
  		<div id="logo"><a href="../"><img src="../images/member/logo.gif" alt="정보통신공사업시스템"></a></div>
  	</div>
  	<!-- header : E -->
   	 
  	<!-- BODY : S -->
	<div id="body"><a name="contents"></a>  	
	  	
	  	<!-- green top 라운드 코너 : S -->
	  	<div id="green_top">
	  		<div id="green_top_right"></div>
	  	</div>
   		<!-- green top 라운드 코너 : E -->
   		
   		<!-- green body : S -->
   		<div id="green_body">
   			
	   		<div id="white">
		   		<!-- white top 라운드 코너 : S -->
			  	<div id="white_top">
			  		<div id="white_top_right"></div>
			  	</div>
		   		<!-- white top 라운드 코너 : E -->
		   		
		   		<!-- white body : S -->
		   		<div id="white_body">

				<%if(user == null) {%>
			   		<!-- 회원로그인 : S -->
			   		<div id="login_mem">
				   		<span class="login_title" ><img src="../images/member/login_01.gif" alt="회원로그인"></span>
				   		<span class="login_title" ><img src="../images/member/login_02.gif" alt="회원님은 아이디와 비밀번호를 입력 후 로그인 하여주시고, 회원이 아니신 분은 회원가입을 하여주시기 바랍니다."></span>
						
						<!-- input form : S -->
						<div id="input_form">

							<form name="loginForm" action="../login/LoginAction.do" method="post">
							<input type="hidden" name="cmd" value="Login">
							<input type="hidden" name="beforeURL" value="<%=beforeURL%>" >
							<div id="inputbox">
								<div id="input_id"><input type="text" name="id" title="아이디를 입력하세요 " value="<%=strID%>" class="input_box" /></div>
			   					<div id="input_pw"><input type="password" name="password" title="비밀번호를 입력하세요" onkeyup="if(event.keyCode == 13) login_sendit();" class="input_box" /></div>
			   				</div>
		   					<div id="btn_login"><a href="javascript:login_sendit()"><img src="../images/member/btn_login.gif" alt="로그인" class='cursor'></a></div>
		   					</form>							
						</div>
						<!-- input form : E -->
						
						<!-- 회원가입/아이디비번찾기 : S -->
						<div id="regi_form">
							<table cellspacing="3" cellpadding="0">
								<tr>
									<td><img src="../images/member/comment_regi.gif" alt="아직 회원가입을 하지 않으셨다면?"></td>
									<td><a href="../member/regist.do"><img src="../images/member/btn_register.gif" alt="회원가입"></a></td>
								</tr>
								<tr>
									<td><img src="../images/member/comment_id.gif" alt="아이디와 비밀번호를 잊으셨나요?"></td>
									<td><a href="../member/find.do"><img src="../images/member/btn_idpw.gif" alt="아이디 및 비밀번호 찾기"></a></td>
								</tr>
							</table>
		   				</div>
						<!-- 회원가입/아이디비번찾기 : E -->
						
					</div>
					<!-- 회원로그인 : E -->
					
			   		<!-- white body 중앙라인 : S -->
			   		<div id="white_center_line"></div>
			   		<!-- white body 중앙라인 : E -->
			   		
			   		<!-- 공인인증서 로그인 : S -->
			   		<div id="login_ceri">
				   		<span class="login_title" ><img src="../images/member/certificate_01.gif" alt="공인인증서 로그인"></span>
				   		<span class="login_title" ><img src="../images/member/certificate_02.gif" alt="인터넷뱅킹 가입하신 고객님은 공인인증서로 로그인하실 수 있습니다."></span>
					</div>
					
					<form name="loginfrm" action="../login/LoginAction.do" method="post">
					<input type="hidden" name="getR">
					<input type="hidden" name="userdn">
					<input type="hidden" name="user_cert">
					<input type="hidden" name="cmd">
					<input type="hidden" name="beforeURL" value="<%=beforeURL%>" >

					<div id="btn_login_cert">
						<a href="javascript:SignData()"><img src="../images/member/btn_login_cert.gif" alt="공인인증서로그인"></a>
					</div>
					
					</form>
					<!-- 공인인증서 로그인 : E -->

					<%}%>
		   		</div>
		   		<!-- white body : E -->
		   		
		   		<!-- white bottom 라운드 코너 : S -->
		   		<div id="white_bottom">
			   		<div id="white_bottom_right">
			   		</div>
		   		</div>
		   		<!-- white bottom 라운드 코너 : E -->
		   	</div>   		


   		</div>
   		<!-- green body : E -->
   		
   		<!-- green bottom 라운드 코너 : S -->
   		<div id="green_bottom">
	   		<div id="green_bottom_right">
	   		</div>
   		</div>
   		<!-- green bottom 라운드 코너 : E -->
   		
 	</div>
 	<!-- BODY : E -->
 	
  	<!-- copyright : S -->
 	<div id="footer">
 		<a href="/iccs/"><img src="../images/member/copy_logo.gif" alt="정보통신공사업시스템"></a>
 		<span class="sfont_gray66">COPYRIGHT (C)2009  정보통신공사업시스템.  All rights reserved.</span>
 	</div> 
 	<!-- copyright : E -->
 	
</div>
<!-- 전체 : E -->

<object title="전자인증모듈" id="CC_Object_id"
      classid="CLSID:A099920B-630C-426B-91EC-737685CEEE17"
	  codebase="../com/certificate/cab/AxCrossCert.cab#Version=2,5,0,13"
      width= Document.body.clientWidth
      height= Document.body.clientHeight>
</object>
</body>
</html>



