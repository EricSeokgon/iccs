<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="kjf.ops.*" %>
<%@ page import="kjf.util.*" %>

<%
	ReportEntity rEntity = KJFUtil.REntPrint( (ReportEntity)request.getAttribute("rEntity") );

	String ssn1 = "";
	String ssn2 = "";
	
	if ( rEntity.getRowCnt() > 0 ) {
	    ssn1 = rEntity.getValue(0, "USER_SSN1");
	    ssn2 = rEntity.getValue(0, "USER_SSN2");
	}

	//상단 플래쉬 링크 정보
	String top_pageNum  = "0";
	String top_sub      = "0";
	
	// 좌측 플래쉬 링크 정보
	String left_pageNum = "4";
	String left_sub     = "5";
%>

<html>
<head>
<title>공인인증서 재등록 페이지입니다.</title>

<!-- 공통 : S -->
<link href="../css/total.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="../com/js/topMenu.js" type="text/javascript"></script>
<script language="JavaScript" src="../flash/flash.js" type="text/javascript"></script>
<!-- 공통 : E -->

<!-- 공인인증서 등록 : S -->
<script language="JavaScript" src="../com/certificate/js/CC_ReEnroll.js" type="text/javascript"></script>
<!-- 공인인증서 등록 : E -->

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
					<a href="../"><img src="../images/common/icon_home.gif" alt="HOME"></a> My민원창구 | 공인인증서재등록
				</p>
			</div>
			<!-- 현재위치 : E -->
	
			<!-- 서브타이틀 : S -->
			<div id="sub_title">
				<h3 class="cont01"><img src="../01_one/images/titile_06_5.gif" alt="공인인증서재등록"></h3>
			</div>
			<!-- 서브타이틀 : E -->
   
		  	<!-- 컨텐츠 : S -->
	  	  	<div id="contents">
				<!-- 공인인증서 재등록 : S -->
				<h4><img src="../01_one/images/sub_title_05_1.gif" alt="공인인증서란?"></h4>
				<p class="txt">
					인터넷 상에서 발생하는 개인 서비스를 안심하고 사용할 수 있도록 해주는 <strong>사이버 인감증명서</strong> 입니다.<br>
					공인인증서는 하드디스크에 저장하여 사용하는 것보다는 <strong>이동식 저장 장치에 저장</strong>하는 것이 더욱 안전하고 어느PC에서든 편리하게 이용하실 수 있습니다.
				</p>
				<h4><img src="../01_one/images/sub_title_05_2.gif" alt="이용안내"></h4>
				<div id="orange_650">
					<p class="txt">
						최초 회원가입 시 등록된 공인인증서의 <strong>유효기간이 만료</strong>되어 발행기관에서 <strong>공인인증서를 갱신</strong>한 경우,<br> 
						타기관 공인인증서로 본 홈페이지에 로그인 하고자 할 경우, 본 홈페이지에 변경된 공인인증서를 재등록 해야 합니다.<br>
						아래 <strong>공인인증서 재등록 버튼을 클릭</strong>하시면 됩니다.</p>
					<div id="orange_650_bt" ></div>	
				</div>	
				
				<!-- 버튼 : S -->
										
				<form name="fmParam" action="../mem/MemAction.do" method="post">
				<input type="hidden" name="cmd" value="MemCCReRegU">
				<input type="hidden" name="com_num" value="<%=user.getCOM_NUM()%>">
				<input type="hidden" name="user_ssn1" value="<%=ssn1%>">
				<input type="hidden" name="user_ssn2" value="<%=ssn2%>">				
				<input type="hidden" name="user_dn">
				<input type="hidden" name="user_cert">
				
				<div class="center">
					<%if(user.getCAPITAL().equals("E")) { %>
						<a href="javascript:enterpriseReEnroll()"><img src="../01_one/images/btn_cert_re.gif" alt="공인인증서 재등록"></a>
					<% }  else { %>
						<a href="javascript:individualReEnroll()"><img src="../01_one/images/btn_cert_re.gif" alt="공인인증서 재등록"></a>
					<% } %>	
					<a href="../mystore/MystoreAction.do?cmd=MyProState"><img src="../00_member/images/btn_cancel.gif" alt="취소"></a>
				</div>
				
				</form>
				<!-- 버튼 : E -->
				
				<!-- 공인인증서 재등록 : S -->
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
