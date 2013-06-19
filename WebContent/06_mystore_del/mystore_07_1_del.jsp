<%@ page contentType="text/html;charset=utf-8"%>

<%
	//상단 플래쉬 링크 정보
	String top_pageNum  = "0";
	String top_sub      = "0";
	
	// 좌측 플래쉬 링크 정보
	String left_pageNum = "7";
	String left_sub     = "1";
%>

<script language="javascript">

// 회원탈퇴
function fn_secede() {
	if (confirm("회원탈퇴를 하시겠습니까?")) {
        location.href="../mem/MemAction.do?cmd=MemInfoD";
    }
}
</script>

<html>
<head>
<title>회원탈퇴 페이지입니다.</title>

<!-- 공통 : S -->
<link href="../css/total.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="../com/js/topMenu.js" type="text/javascript"></script>
<script language="JavaScript" src="../flash/flash.js" type="text/javascript"></script>
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
					<a href="../"><img src="../images/common/icon_home.gif" alt="HOME"></a> My민원창구 | 회원탈퇴
				</p>
			</div>
			<!-- 현재위치 : E -->
	
			<!-- 서브타이틀 : S -->
			<div id="sub_title">
				<h3 class="cont01"><img src="../06_mystore/images/titile_07_1.gif" alt="회원탈퇴"></h3>
			</div>
			<!-- 서브타이틀 : E -->
   
		  	<!-- 컨텐츠 : S -->
	  	  	<div id="contents">
				<h4><img src="../06_mystore/images/sub_title_06_1.gif" alt="회원탈퇴안내" /></h4>
				<p class="txt">아래 회원탈퇴 관련사항을 확인하시고 회원탈최하기 버튼을 클릭하시면 회원탈퇴 처리가 완료됩니다.</p>
				<h4><img src="../06_mystore/images/sub_title_06_2.gif" alt="회원탈퇴 관련사항" /></h4>
				<div id="orange_650">
				
					<ul>
						<li class="padd10"><strong>01.</strong> 해지된 ID는 재사용이 불가능합니다.</li>
						<li class="padd10"><strong>02.</strong> 마이민원창구 및 개인파일 등은 자동 삭제처리되며 복구 불가능합니다. </li>
						<li class="padd10"><strong>03.</strong> 본인확인제 실시로 회원님의 본인확인정보 및 등록한 정보(게시물 등)는 6개월간 보존됩니다.</li>
					</ul>
				
				<div id="orange_650_bt"></div>
				</div>
					
				<div class="padd15">
				  <dl>
				    <dt class="arrow05">본인확인제란?</dt>
					  <dd>
					  	이용자가 게시판에 정보를 게시하려고 할 경우 해당 게시판 관리·운영자가 이용자의 본인여부를 확인하는 제도로써 <br>
					 	 인터넷상 명예훼손, 언어폭력 등 익명성, 비대면성에 기반한 역기능이 심화됨에 따라 책임있는 공론형성(자기책임의식 제고),<br>
						인터넷 이용문화 바로세움, 명예훼손분쟁조정, 민·형사상 소제기시 가해자 정보제공으로 피해자를 구제하기 위한 것입니다. 
				    	<p class="sfont_gray66"><strong>근거 : 정보통신망 이용촉진 및 정보보호 등에 관한 법률</strong><p> 
				      </dd>
				  </dl>
				</div> 
				
				<!-- 버튼 : S -->
				<div id="mem_btn">
				  	<a href="javascript:fn_secede()"><img src="../00_member/images/btn_withdraw.gif" alt="회원탈퇴"></a>
				  	<a href=""><img src="../00_member/images/btn_cancel.gif" alt="취소"></a>
				</div>
				<!-- 버튼 : E -->
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
