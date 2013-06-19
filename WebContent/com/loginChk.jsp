<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="kjf.util.*" %>
<%@ page isErrorPage="true" %>

<%
	String imgUri    = request.getContextPath();
	String beforeURL = KJFUtil.print((String)request.getAttribute("beforeURL"));
	String returnURL = KJFUtil.print((String)request.getAttribute("returnURL"), "../member/login.do");
	String ex        = (String)request.getAttribute("ex");
%>

<script language="JavaScript" type="text/javascript">
function fn_login() {
    var fm = document.fmParam;
    fm.submit();
}
</script>

<html>
<head>
<title>로그인 요청페이지입니다.</title>

<!-- 공통 : S -->
<link href="../css/member.css" rel="stylesheet" type="text/css">
<!-- 공통 : E -->

</head>
<body>

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
  		<div id="logo"><a href="../"><img src="../images/member/logo.gif" alt="정보통신공사업시스템"></a></div>
  	</div>
  	<!-- header : E -->
   	 
   	<form name=fmParam method="post" action="<%=returnURL%>">
	<input type="hidden" name="beforeURL" value="<%=beforeURL%>">
				
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
		   		<div id="white_body_center">
			   		<!-- 회원로그인 : S -->
			   		<div id="login_request">
				   		<span class="" ><img src="../images/member/img_01.gif" alt=""></span>
				   		<span class="" ><img src="../images/member/img_login.gif" alt="서비스를 이용하시려면 로그인이 필요합니다."></span>
								
					</div>
					<!-- 회원로그인 : E -->
					
			   		<!-- white body 중앙라인 : S -->
		   			<div id="white_ver_line">
	   				</div>
			   		<!-- white body 중앙라인 : E -->
			   		
			   		<!-- white body 중앙라인 : S -->
		   			<div id="login_request_btn">
		   				<a href="javascript:history.go(-1);"><img src="../images/member/btn_pre.gif" alt="이전페이지"></a>
		   				<a href="javascript:fn_login()"><img src="../images/member/btn_small_login.gif" alt="로그인"></a>
	   				</div>
			   		<!-- white body 중앙라인 : E -->
		   		</div>
		   		<!-- white body : E -->
		   		
		   		<!-- white bottom 라운드 코너 : S -->
		   		<div id="white_bottom">
			   		<div id="white_bottom_right"></div>
		   		</div>
		   		<!-- white bottom 라운드 코너 : E -->
		   	</div>  

   		</div>
   		<!-- green body : E -->
   		
   		<!-- green bottom 라운드 코너 : S -->
   		<div id="green_bottom">
	   		<div id="green_bottom_right"></div>
   		</div>
   		<!-- green bottom 라운드 코너 : E -->
   		
 	</div>
 	<!-- BODY : E -->
 	</form>
 	
  	<!-- copyright : S -->
 	<div id="footer">
 	<img src="../images/member/copy_logo.gif" alt="정보통신공사업시스템" >
 	<span class="sfont_gray66">COPYRIGHT (C)2009  정보통신공사업시스템.  All rights reserved.</span>
 	</div> 
 	<!-- copyright : E -->
 	
</div>
<!-- 전체 : E -->

</body>
</html>
