<%@ page contentType="text/html;charset=utf-8"%>
<link href="../css/register.css" rel="stylesheet" type="text/css" />

<div id="mem_title_1">
	<h4><img src="../00_member/images/search/search_comment.gif" alt="아이디와 비밀번호를 알려드립니다." /></h4>
	<img src="../00_member/images/search/search_com_detail.gif" alt="비밀번호는 회원가입 시 등록된 핸드폰 번호로 임시비밀번호를 알려드립니다." />
</div>

<!-- 비밀번호 결과 : S -->
<div id="mem_center_wrapper">
  <div id="mem_center">
		<div class="mem_title_1_no">
			<img src="../00_member/images/join/join_search_pw.gif" alt="비밀번호찾기" />
		</div>
		
	    <!--orange : S -->
		<div id="orange_bg">
			<!-- orange top 라운드 코너 : S -->
		  	<div class="orange_top">
		  		<div id="orange_top_right"></div>
		  	</div>
	   		<!-- orange top 라운드 코너 : E -->
   		
	   		<!-- orange body : S -->
			
			<% if (PwdResult.equals("SUCCESS")) { %>
		   		<div id="orange_body"> 
					<strong>회원가입 시 등록해 주신 <font class="font_orange">핸드폰 번호</font>로<br />
		   		  	<font class="font_orange">임시비밀번호</font>가 전송되었습니다.</strong>
				</div>
	
				<!-- orange bottom 라운드 코너 : S -->				
		   		<div id="orange_bottom">
			   		<div id="orange_bottom_right"></div>
		   		</div>
		   		<!-- orange bottom 라운드 코너 : E -->
			</div>
			<!--orange : E -->
	
			<!-- 버튼 : S -->
			<div class="center" style="padding-top: 20px;">
		      <a href="../member/login.do"><img src="../00_member/images/btn_login.gif" alt="로그인"></a>
		      <a href="../"><img src="../00_member/images/btn_main.gif" alt="메인으로 바로가기"></a>
			</div>

			<% } else if (PwdResult.equals("NONE")) {%>			
				<div id="orange_body">
					<strong>회원님의 <font class="font_orange">회원정보</font>가 없습니다.</strong>			  		
				</div>		
	
				<!-- orange bottom 라운드 코너 : S -->
		   		<div id="orange_bottom">
			   		<div id="orange_bottom_right"></div>
		   		</div>
		   		<!-- orange bottom 라운드 코너 : E -->
			</div>
			<!--orange : E -->
			<!-- 버튼 : S -->
			<div class="center" style="padding-top: 20px;">
		      <a href="../member/login.do"><img src="../00_member/images/btn_regi.gif" alt="회원가입"></a>
		      <a href="../member/find.do"><img src="../00_member/images/btn_search_idpw.gif" alt="아이디 /비밀번호 찾기"></a>
			</div>
			
			<% } else { %>
				<div id="orange_body">
					<strong>회원님의 임시비밀번호 <font class="font_orangeB">SMS 전송을 실패</font>하였습니다.</strong>
					<div id="orange_body_line"></div>
				    <p class="sfont_gray66">가입된 회원님의 <strong>핸드폰 번호</strong>가 옳지 않거나 <br/>
					<strong>시스템의 장애</strong>로 인하여 전송이 되지않을 수 있습니다.</p>
				</div>			
	
				<!-- orange bottom 라운드 코너 : S -->				
		   		<div id="orange_bottom">
			   		<div id="orange_bottom_right"></div>
		   		</div>
		   		<!-- orange bottom 라운드 코너 : E -->
			</div>
			<!--orange : E -->
			<!-- 버튼 : S -->
			<div class="center" style="padding-top: 20px;">
		      <a href="../member/find.do"><img src="../images/btn/btn_retry.gif" alt="재시도"></a>
		      <a href="../"><img src="../00_member/images/btn_main.gif" alt="메인으로 바로가기"></a>
			</div>
			<!-- 버튼 : E -->		
			<% } %>
	   		<!-- orange body : E -->	   		
  </div>
</div>
<!-- 비밀번호 결과 : E -->
