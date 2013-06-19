<%@ page contentType="text/html;charset=utf-8"%>
<link href="../css/register.css" rel="stylesheet" type="text/css" />

<div id="mem_img"><img src="../00_member/images/join/img_03.gif" alt="" /></div>
<div id="mem_title_1">
	<img src="../00_member/images/join/join_meme_person.gif" alt="회원약관동의" />
	<img src="../00_member/images/join/join_comment.gif" alt="본 사이트 회원가입 시 회원님의 공인인증서가 필요합니다." />
</div>

<form name="fmParam" action="../mem/MemAction.do" method="post">
<input type="hidden" name="cmd" 	 value="MemRegPriC">
<input type="hidden" name="flag" 	 value="0">
<input type="hidden" name="mem_kind" value="P">
<input type="hidden" name="user_dn">
<input type="hidden" name="user_cert">

<!--개인정보입력 타이틀2 : S -->
<div class="mem_title_2">
	<div class="mem_title_2_img"><img src="../00_member/images/join/join_privacy.gif" alt="개인정보입력" />
	<span class="mem_title_2_text">★ 는 필수입력사항입니다.</span></div>
	
	
	<!--개인정보입력:시작 -->
	<div class="line_T2B1">
		<!--Write: 시작 -->
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		  <tr>
			<td width="100" class="form_detail">
				<span class="sfont_orange">★</span><img src="../00_member/images/join/detail_01name.gif" alt="이름">
			</td>
			<td width="570" class="td_view_detail" >
				<input name="user_name" type="text" value="<%=pm.getUser_name()%>" size="20" maxlength="20" tabindex="1" title="이름" readonly>
			</td>
		  </tr>

		  <tr>
			<td colspan="2" class="line_1_d1"></td>
		  </tr>

		  <tr>
			<td class="form_detail">
				<span class="sfont_orange">★</span><img src="../00_member/images/join/detail_02person.gif" alt="지역">
			</td>
			<td class="td_view_detail" >
				<input name="user_ssn1" type="text" size="6" maxlength="6" value="<%=pm.getUser_ssn1()%>" title="주민번호 앞자리" readonly> - 
				<input name="user_ssn2" type="password" size="15" maxlength="7" value="<%=pm.getUser_ssn2()%>" title="주민번호 뒤자리" readonly>
			</td>
		  </tr>

		  <tr>
			<td colspan="2" class="line_1_d1"></td>
		  </tr>

		  <tr>
			<td class="form_detail"><span class="sfont_orange">★</span><img src="../00_member/images/join/detail_03id.gif" alt="아이디"></td>
			<td class="td_view_detail">
				<input name="user_id" type="text" size="16" maxlength="16" title="아이디를 입력해 주세요">
			  	<a href="javascript:checkDuplicateID(document.fmParam.user_id.value)"><img src="../00_member/images/join/btn_id_check.gif" alt="아이디 중복검사" align="absmiddle"  tabindex="0"></a>
			</td>
		  </tr>

		  <tr>
			<td colspan="2" class="line_1_d1"></td>
		  </tr>

		  <tr>
			<td class="form_detail"><span class="sfont_orange">★</span><img src="../00_member/images/join/detail_04pw.gif" alt="비밀번호"></td>
			<td class="td_view_detail"><input name="user_passwd" type="password" size="20" maxlength="16" title="비밀번호를 입력해주세요"></td>
		  </tr>

		  <tr>
			<td colspan="2" class="line_1_d1"></td>
		  </tr>

		  <tr>
			<td class="form_detail"><span class="sfont_orange">★</span><img src="../00_member/images/join/detail_05pw_con.gif" alt="비밀번호 확인"></td>
			<td class="td_view_detail"><input name="user_passwd_ck" type="password" size="20" maxlength="16" title="비밀번호를 한번더 입력해주세요"></td>
		  </tr>

		  <tr>
			<td colspan="2" class="line_1_d1"></td>
		  </tr>

		  <tr>
			<td class="form_detail"><span class="sfont_orange">★</span><img src="../00_member/images/join/detail_06province.gif" alt="시도"></td>
			<td class="td_view_detail">
				<KTags:KJFSelect item='<%=(Vector)request.getAttribute("v_scSD_CD")%>'
								 name='sido_code'
								 value=''
								 script="class='input_bbs' onchange='fn_changeSGG_CD()' "/>
			</td>
		  </tr>

		  <tr>
			<td colspan="2" class="line_1_d1"></td>
		  </tr>

		  <tr>
			<td class="form_detail"><span class="sfont_orange">★</span><img src="../00_member/images/join/detail_07county.gif" alt="시군구"></td>
			<td class="td_view_detail">
				<div id="layer_sggcd" name="layer_sggcd">
				  	<select name="sigungu_code">
						<option>시.군.구 선택</option>
				  	</select>   
				</div>
			</td>
		  </tr>

		  <tr>
			<td colspan="2" class="line_1_d1"></td>
		  </tr>

		  <tr>
			<td class="form_detail"><span class="sfont_orange">★</span><img src="../00_member/images/join/detail_08tel.gif" alt="전화번호" width="54" height="10"></td>
			<td class="td_view_detail">
				<select name="user_tel1">
					<option value="02">서울 02</option>
					<option value="031">경기 031</option>
					<option value="032">인천 032</option>
					<option value="033">강원 033</option>		
					<option value="041">충남 041</option>	
					<option value="042">대전 042</option>
					<option value="043">충북 043</option>
					<option value="051">부산 051</option>
					<option value="052">울산 052</option>
					<option value="053">대구 053</option>
					<option value="054">경북 054</option>
					<option value="055">경남 055</option>
					<option value="061">전남 061</option>
					<option value="062">광주 062</option>
					<option value="063">전북 063</option>
					<option value="064">제주 064</option>
					<option value="070">인터넷 070</option>
				</select>
				-&nbsp;<input name="user_tel2" type="text" size="4" maxlength="4" title="전화번호를 입력해주세요">
				-&nbsp;<input name="user_tel3" type="text" size="4" maxlength="4" title="전화번호를 입력해주세요">
			</td>
		  </tr>

		  <tr>
			<td colspan="2" class="line_1_d1"></td>
		  </tr>

		  <tr>
			<td class="form_detail"><span class="sfont_orange">★</span><img src="../00_member/images/join/detail_09mobile.gif" alt="핸드폰번호"></td>
			<td class="td_view_detail">
				<select name="user_mobile1">
					<option value="010">010</option>
					<option value="011">011</option>
					<option value="016">016</option>
					<option value="017">017</option>		
					<option value="018">018</option>	
					<option value="019">019</option>		
				</select>
				-&nbsp;<input name="user_mobile2" type="text" size="4" maxlength="4" title="핸드폰번호를 입력해주세요">
				-&nbsp;<input name="user_mobile3" type="text" size="4" maxlength="4" title="핸드폰번호를 입력해주세요">
			</td>
		  </tr>

		  <tr>
			<td colspan="2" class="line_1_d1"></td>
		  </tr>

		  <tr>
			<td class="form_detail"><span class="sfont_orange">★</span><img src="../00_member/images/join/detail_10email.gif" alt="이메일"></td>
			<td class="td_view_detail"><input name="user_email" type="text" size="20" maxlength="20" title="이메일을 입력해주세요"></td>
		  </tr>

		  <tr>
			<td colspan="2" class="line_1_d1"></td>
		  </tr>

		  <tr>
			<td class="form_detail"><span class="sfont_orange">★</span><img src="../00_member/images/join/detail_11zip.gif" alt="우편번호"></td>
			<td class="td_view_detail">
			  <input name="user_post_num" type="text" size="7" maxlength="7" title="우편번호 검색 버튼을 클릭해주세요" readonly>
			  <a href="javascript:post_search()"><img src="../00_member/images/join/btn_zip.gif" alt="우편번호검색" align="absmiddle" tabindex="0"></a>
			</td>
		  </tr>

		  <tr>
			<td colspan="2" class="line_1_d1"></td>
		  </tr>

		  <tr>
			<td class="form_detail"><span class="sfont_orange">★</span><img src="../00_member/images/join/detail_12address.gif" alt="주소"></td>
			<td class="td_view_detail"><input name="user_addr" type="text" size="40" maxlength="40" title="우편번호 검색 버튼을 클릭해주세요" readonly ></td>
		  </tr>

		  <tr>
			<td colspan="2" class="line_1_d1"></td>
		  </tr>

		  <tr>
			<td class="form_detail"><span class="sfont_orange">★</span><img src="../00_member/images/join/detail_13add_d.gif" alt="상세주소"></td>
			<td class="td_view_detail"><input name="user_addr_detail" type="text" size="40" maxlength="40" title="상세주소를 입력해주세요"></td>
		  </tr>

		  <tr>
			<td colspan="2" class="line_1_d1"></td>
		  </tr>

		  <tr>
			<td class="form_detail"><span class="sfont_orange">★</span><img src="../00_member/images/join/detail_14_certi.gif" alt="공인인증"></td>
			<td class="td_view_detail"><a href="javascript:individualTestimony()"><img src="../00_member/images/join/btn_certi.gif" alt="공인인증서 등록" align="absmiddle" tabindex="0"></a></td>
		  </tr>
			
	  </table>
		<!--Write:끝 -->
	</div>
	<!--개인정보입력 : E -->
	
</div>
<!--개인정보입력 타이틀2 : E -->

</form>

<!--버튼 :S -->
<div id="mem_kind_bg">
	<div id="mem_btn">
		<a href="javascript:fn_PriMemReg()"><img src="../00_member/images/btn_ok.gif" alt="확인"></a>
		<a href="../member/regist.do"><img src="../00_member/images/btn_cancel.gif" alt="취소"></a>
	</div>
</div>
<!--버튼 :E -->
<!--******************************************************************************* -->
