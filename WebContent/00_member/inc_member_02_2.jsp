<%@ page contentType="text/html;charset=utf-8"%>
<link href="../css/register.css" rel="stylesheet" type="text/css" />

<div id="mem_title_1">
	<h4><img src="../00_member/images/search/search_comment.gif" alt="아이디와 비밀번호를 알려드립니다." /></h4>
	<img src="../00_member/images/search/search_com_detail.gif" alt="비밀번호는 회원가입 시 등록된 핸드폰 번호로 임시비밀번호를 알려드립니다." />
</div>

<!--아이디찾기 결과 : S -->
<div id="mem_center_wrapper">
  <div id="mem_center">
		<div class="mem_title_1_no"><img src="../00_member/images/join/join_search_id.gif" alt="아이디찾기" /></div>
		
	    <!--orange : S -->
		<div id="orange_bg">
			<!-- orange top 라운드 코너 : S -->
		  	<div class="orange_top">
		  		<div id="orange_top_right"></div>
		  	</div>
	   		<!-- orange top 라운드 코너 : E -->
   		
			
	   		<!-- orange body : S -->
		
			<% if (rEntity.getRowCnt() > 0) { %>
		   		<div id="orange_body">
					<strong><font class="font_orange"><%=rEntity.getValue(0, "USER_NAME")%></font> 회원님의 아이디는 <font class="font_orange"><%=KJFUtil.cutOff(rEntity.getValue(0, "USER_ID"), 3)%></font>이며<br />
		   		  	<%=KJFDate.getTimeAsPattern(KJFDate.str2date(rEntity.getValue(0, "INS_DT"), ""), "yyyy년MM월dd일")%> 가입하셨습니다.<strong>
		  		</div>

				<!-- orange bottom 라운드 코너 : S -->
		   		<div id="orange_bottom">
			   		<div id="orange_bottom_right"></div>
		   		</div>
		   		<!-- orange bottom 라운드 코너 : E -->
			</div>
			<!--orange : E -->

			<div class="center" style="padding-top: 20px;">
		      <a href="../member/login.do"><img src="../00_member/images/btn_login.gif" alt="로그인"></a>
		      <a href="../"><img src="../00_member/images/btn_main.gif" alt="메인으로 가기" width="100" height="26"></a>
			</div>
			<% } else {%>
				<div id="orange_body">
					<strong>회원님의 <font class="font_orange">아이디</font> 정보는 존재하지 않습니다.</strong>			    
				</div>
	
				<!-- orange bottom 라운드 코너 : S -->
		   		<div id="orange_bottom">
			   		<div id="orange_bottom_right"></div>
		   		</div>
		   		<!-- orange bottom 라운드 코너 : E -->
			</div>
			<!--orange : E -->

			<div class="center" style="padding-top: 20px;">
		      <a href="../member/regist.do"><img src="../00_member/images/btn_regi.gif" alt="회원가입"></a>
		      <a href="../member/find.do"><img src="../00_member/images/btn_search_idpw.gif" alt="ID/PW 찾기"></a>
			</div>
			<% } %>
	   		<!-- orange body : E -->

  </div>
</div>
<!--아이디찾기 결과 : E -->
