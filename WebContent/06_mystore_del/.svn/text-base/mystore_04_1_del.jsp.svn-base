<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="kjf.ops.*" %>
<%@ page import="kjf.util.*" %>
<%@ taglib uri="/KJFTags" prefix="KTags" %>

<%
	ReportEntity rEntity = (ReportEntity)request.getAttribute("rEntity");
	
	//상단 플래쉬 링크 정보
	String top_pageNum  = "0";
	String top_sub      = "0";
	
	// 좌측 플래쉬 링크 정보
	String left_pageNum = "4";
	String left_sub     = "1";
%>

<script language="JavaScript" type="text/javascript">

function fn_ChangePasswd() {	

	var fm = document.fmParam;	
		
	if (!isValid_passwd(fm.cur_passwd.value)) {
		fm.cur_passwd.select();
		fm.cur_passwd.focus();
		return;
	}

	if (!isValid_passwd(fm.new_passwd.value)) {
		fm.new_passwd.select();
		fm.new_passwd.focus();
		return;
	}
	
	if (fm.new_passwd.value != fm.new_passwd_ch.value) {
		alert("비밀번호가 일치하지 않습니다");
		fm.new_passwd_ch.select();
		fm.new_passwd_ch.focus();
		return;
	}

	if (confirm("비밀번호를 변경 하시겠습니까?")) {
		fm.submit();
	}
	
}
</script>
	
<html>
<head>
<title>비밀번호 변경 페이지입니다.</title>

<!-- 공통 : S -->
<link href="../css/total.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="../com/js/common_util.js" type="text/javascript"></script>
<script language="JavaScript" src="../com/js/join.js" type="text/javascript"></script>
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
					<a href="../"><img src="../images/common/icon_home.gif" alt="HOME"></a> My민원창구 | 비밀번호 변경
				</p>
			</div>
			<!-- 현재위치 : E -->
	
			<!-- 서브타이틀 : S -->
			<div id="sub_title">
				<h3 class="cont01"><img src="../06_mystore/images/titile_04_1.gif" alt="비밀번호 변경"></h3>
			</div>
			<!-- 서브타이틀 : E -->
   
		  	<!-- 컨텐츠 : S -->
			<div id="contents">

				<form name="fmParam" action="../mem/MemAction.do?cmd=MemPasswdChangeU" method="post">

				<!-- 비밀번호 변경 : S -->
				<div class="mem_title_2">
					<h4><img src="../06_mystore/images/sub_title_03_1.gif" alt="비밀번호 변경"></h4>
					
					<!--개인정보입력:시작 -->
					<div class="line_T2B1">
						<!--Write: 시작 -->
						<table width="100%" border="0" cellspacing="0" cellpadding="0" summary="비">
						  <tr>
							<td class="form_detail"><span class="sfont_orange">★</span><img src="../00_member/images/join/detail_pw_now.gif" alt="현재 비밀번호"></td>
							<td class="td_view_detail"><!--현재 비밀번호 -->
								<input name="cur_passwd" type="password" size="20" maxlength="16" tabindex="1" value="" title="현재 비밀번호를 입력해주세요">
							</td>
						  </tr>
				
						  <tr>
							<td colspan="2" class="line_1_d1"></td>
						  </tr>
				
						  
				
						  <tr>
							<td width="100" class="form_detail"><span class="sfont_orange">★</span><img src="../00_member/images/join/detail_pw_new.gif" alt="새 비밀번호"></td>
							<td width="570" class="td_view_detail" >
								<input name="new_passwd" type="password" value="" size="20" maxlength="16" tabindex="2" title="새 비밀번호를 입력해주세요">
							</td>
							
						  </tr>
				
						  <tr>
							<td colspan="2" class="line_1_d1"></td>
						  </tr>
						  
						  <tr>
							<td width="100" class="form_detail"><span class="sfont_orange">★</span><img src="../00_member/images/join/detail_pw_new2.gif" alt="새 비밀번호 확인"></td>
							<td width="570" class="td_view_detail" >
								<input name="new_passwd_ch" type="password" value="" size="20" maxlength="16" tabindex="3" title="새 비밀번호를 한번 더 입력해주세요">
							</td>
							
						  </tr>
				
						  <tr>
							<td colspan="2" class="line_1_d1"></td>
						  </tr>
						</table>
					</div>
			</div>

			<!--버튼 :S -->
			<div id="mem_kind_bg">
				<div id="mem_btn">
					<a href="javascript:fn_ChangePasswd()"><img src="../00_member/images/btn_ok.gif" alt="확인"></a>
					<a href=""><img src="../00_member/images/btn_cancel.gif" alt="취소"></a>
				</div>
			</div>
			<!--버튼 :E -->

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
