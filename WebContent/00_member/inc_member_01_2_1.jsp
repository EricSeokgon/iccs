<%@ page contentType="text/html;charset=utf-8"%>
<link href="../css/register.css" rel="stylesheet" type="text/css" />

<div id="mem_img"><img src="../00_member/images/join/img_02.gif" alt=""/></div>

<!--개인정보입력 타이틀2 : S -->
<div id="mem_center_wrapper">
  <div id="mem_center">

	<div id="mem_title_1"><img src="../00_member/images/join/join_person.gif" alt="회원본인확인" />
		<img src="../00_member/images/join/join_comment.gif" alt="본 사이트 회원가입 시 회원님의 공인인증서가 필요합니다." />
	</div>
	
    <!--개인/기업회원 : S -->
	<div id="mem_center_wrapper">

		<div id="search_center">
	  		<!--개인회원 : S -->
			<div id="search_id">
				<div class="mem_title_1_no"><img src="../00_member/images/join/join_person1.gif" alt="개인회원" /></div>
					
				<!--orange : S -->
				<div id="orange_bg">
					<!-- orange top 라운드 코너 : S -->
					<div class="orange_top"></div>
					<!-- orange top 라운드 코너 : E -->
					
					<!-- orange body : S -->
					<div id="orange_body_no">
						<div class="search_form">

							<form name="identityP_form" action="../mem/MemAction.do" method="post">
							<input type="hidden" name="cmd"	value="MemRegPrivate">

<div class="line_1">
									<span class="search_form_title"><img src="../00_member/images/search/detail_name.gif" alt="이름" /></span>
									<span><input name="user_name" type="text" size="16" maxlength="16" title="이름을 입력해주세요"></span>
								</div>
								
								<div class="line_1">
									<span class="search_form_title"><img src="../00_member/images/search/detail_num.gif" alt="주민등록번호" /></span>
									<span>
										<input name="user_ssn1" type="text" size="6" maxlength="6" title="주민번호 앞자리를 입력해주세요"> - 
										<input name="user_ssn2" type="password" size="12" maxlength="7" title="주민번호 뒷자리를 입력해주세요">
									</span>
								 </div>
								
								 <div style="padding-top:20px;">
									<a href="javascript:go_regist_Pri()"><img src="../00_member/images/btn_ok.gif" alt="확인"></a>
									<a href=""><img src="../00_member/images/btn_cancel.gif" alt="취소"></a>
								 </div>

							</form>							
						
						</div>
					</div>
					<!-- orange body : E -->
					
					<!-- orange bottom 라운드 코너 : S -->
					<div id="orange_bottom"></div>
					<!-- orange bottom 라운드 코너 : E -->
				</div>
				<!--orange : E -->	
			</div>
			<!--개인회원 : E -->
	
			<!--기업회원 : S -->
			<div id="search_pw">
				<div class="mem_title_1_no"><img src="../00_member/images/join/join_person2.gif" alt="아이디찾기" width="55" height="12" /></div>
				
				<!--orange : S -->
				<div id="orange_bg_center">
					<!-- orange top 라운드 코너 : S -->
					<div class="orange_top">
						<div id="orange_top_right"></div>
					</div>
					<!-- orange top 라운드 코너 : E -->
					
					<!-- orange body : S -->
					<div id="orange_body">
					  	<div class="search_form">

							<form name="identityB_form" action="../mem/MemAction.do" method="post">
							<input type="hidden" name="cmd"	value="MemRegBusiness">
							<input type="hidden" name="com_num">

								<div class="line_1">
									<span class="search_form_title"><img src="../00_member/images/search/detail_ceo_name.gif" alt="대표자 이름" /></span>
									<span><input name="user_name" type="text" size="16" maxlength="16" title="대표자 이름을 입력해주세요"></span>
								</div>

								<div class="line_1">
									<span class="search_form_title"><img src="../00_member/images/search/detail_num.gif" alt="대표자 주민등록번호" /></span>
									<span><!--주민등록번호 -->
												<input name="user_ssn1" type="text" size="6" maxlength="6" title="주민번호 앞자리를 입력해주세요"> - 
												<input name="user_ssn2" type="password" size="12" maxlength="7" title="주민번호 뒷자리를 입력해주세요"></span>
								</div>

								<div class="line_1">
									<span class="search_form_title"><img src="../00_member/images/search/detail_company_num.gif" alt="사업자등록번호" /></span>
									<span><!--사업자등록번호 -->
										<input name="corp_no1" type="text" size="3" maxlength="3" title="사업자등록번호 3자리를 입력해주세요"> - 
										<input name="corp_no2" type="text" size="2" maxlength="2" title="사업자등록번호 2자리를 입력해주세요"> - 
										<input name="corp_no3" type="text" size="5" maxlength="5" title="사업자등록번호 5자리를 입력해주세요"></span>
								</div>
								<div style="padding-top:15px; margin-bottom: 4px;">

										<a href="javascript:go_regist_Corp()"><img src="../00_member/images/btn_ok.gif" alt="확인"></a>
										<a href=""><img src="../00_member/images/btn_cancel.gif" alt="취소"></a>

								</div>
						
							</form>							
	
						</div>
					</div>
					<!-- orange body : E -->
					
					<!-- orange bottom 라운드 코너 : S -->
					<div id="orange_bottom_center">
						<div id="orange_bottom_right"></div>
					</div>
				<!-- orange bottom 라운드 코너 : E -->

				</div>
				<!--orange : E -->
			</div>
			<!--기업회원 : E -->
		</div>
		  
	</div>
	<!--개인/기업회원 : E -->

  </div>
</div>
