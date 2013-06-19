<%@ page contentType="text/html;charset=utf-8"%>
<link href="../css/register.css" rel="stylesheet" type="text/css" />

<div id="mem_img"><img src="../00_member/images/join/img_03.gif" alt=""></div>
<div id="mem_title_1">
	<img src="../00_member/images/join/join_mem_company.gif" alt="회원약관동의">
	<img src="../00_member/images/join/join_comment.gif" alt="본 사이트 회원가입 시 회원님의 공인인증서가 필요합니다." />
</div>

<form name="fmParam" action="../mem/MemAction.do" method="post">
<input type="hidden" name="cmd"        value="MemRegBusC">
<input type="hidden" name="com_reg_yn" value="<%=pm.getCom_reg_yn()%>">
<input type="hidden" name="flag" 	   value="0">
<input type="hidden" name="mem_kind"   value="C">
<input type="hidden" name="user_dn">
<input type="hidden" name="user_cert">

<!-- 대표자 정보입력 타이틀2 : S -->
<div class="mem_title_2">
	<div class="mem_title_2_img"><img src="../00_member/images/join/join_ceo.gif" alt="대표자정보입력">
	<span class="mem_title_2_text">★ 는 필수입력사항입니다.</span></div>	
	
	<!-- 대표자 정보입력:시작 -->
	<div class="line_T2B1">
		<!--Write: 시작 -->
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		  <tr>
			<td width="100" class="form_detail"><span class="sfont_orange">★</span><img src="../00_member/images/join/detail_01name.gif" alt="이름"></td>
			<td width="570" class="td_view_detail" >
				<input name="user_name" type="text" value="<%=KJFUtil.print(pm.getUser_name())%>" size="20" tabindex="1" title="이름을 입력해주세요" readonly>
			</td>
		  </tr>

		  <tr>
			<td colspan="2" class="line_1_d1"></td>
		  </tr>

		  <tr>
			<td class="form_detail"><span class="sfont_orange">★</span><img src="../00_member/images/join/detail_02person.gif" alt="지역"></td>
			<td class="td_view_detail" >
				<input name="user_ssn1" type="text" value="<%=KJFUtil.print(pm.getUser_ssn1())%>" size="6" readonly> - 
				<input name="user_ssn2" type="password" value="<%=KJFUtil.print(pm.getUser_ssn2())%>" size="15" readonly>
			</td>
		  </tr>

		  <tr>
			<td colspan="2" class="line_1_d1"></td>
		  </tr>

		  <tr>
			<td class="form_detail"><span class="sfont_orange">★</span><img src="../00_member/images/join/detail_03id.gif" alt="아이디"></td>
			<td class="td_view_detail"><!--아이디 -->
				<input name="user_id" type="text" size="20" maxlength="16" title="아이디를 입력해주세요">
			  	<a href="javascript:checkDuplicateID(document.fmParam.user_id.value)"><img src="../00_member/images/join/btn_id_check.gif" alt="아이디 중복검사" align="absmiddle" tabindex="0"></a>
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
			<td class="form_detail"><span class="sfont_orange">★</span><img src="../00_member/images/join/detail_08tel.gif" alt="전화번호"></td>
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
			  <a href="javascript:post_search()"><input name="user_post_num" type="text" size="20" maxlength="20" title="우편번호를 입력해주세요" readonly>
			  <img src="../00_member/images/join/btn_zip.gif" alt="우편번호검사"  align="absmiddle"  tabindex="0"></a>
		    </td>
		  </tr>

		  <tr>
			<td colspan="2" class="line_1_d1"></td>
		  </tr>

		  <tr>
			<td class="form_detail"><span class="sfont_orange">★</span><img src="../00_member/images/join/detail_12address.gif" alt="주소"></td>
			<td class="td_view_detail"><input name="user_addr" type="text" size="40" maxlength="40" title="주소를 입력해주세요" readonly></td>
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
			<td class="td_view_detail"><a href="javascript:enterpriseTestimony()"><img src="../00_member/images/join/btn_certi.gif" alt="공인인증서 등록" align="absmiddle" tabindex="0"></a></td>
		  </tr>

	  </table>
		<!--Write:끝 -->
	</div>
	<!-- 대표자 정보입력 : E -->
	
</div>
<!-- 대표자 정보입력 타이틀 : E -->

<!--공사업정보 : S -->
<div class="mem_title_2">
	<div class="mem_title_2_img"><img src="../00_member/images/join/join_regi_infor.gif" alt="공사업 등록정보" />
	<span class="mem_title_2_text">★ 는 필수입력사항입니다.</span></div>
	
	
	<!-- 공사업 등록 여부 : S -->
	<div class="line_T2B1">
		<!--Write: 시작 -->
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="100" class="form_detail"><span class="sfont_orange">★</span><img src="../00_member/images/join/detail_regi_yes.gif" alt="이름"></td>
            <td width="570" class="td_view_detail" ><!--이름 -->
                <label><input name="com_reg" type="radio" class="radio" value="Y" <%=pm.getCom_reg_yn().equals("Y")? "checked":"" %> disabled>공사업 등록 </label>
              	<label><input name="com_reg" type="radio" class="radio" value="N" <%=pm.getCom_reg_yn().equals("N")? "checked":"" %> disabled>공사업 미등록 </label></td>
          </tr>
        </table>
		<!--Write:끝 -->
	</div>
	<!-- 공사업 등록 여부 : E -->
	
</div>
<!--공사업정보 : E -->

<!--기업정보 : S -->
<div class="mem_title_2">
	<div class="mem_title_2_img"><img src="../00_member/images/join/join_company.gif" alt="기업정보입력"/>
	<span class="mem_title_2_text">★ 는 필수입력사항입니다.</span></div>
	
	<!--기업정보입력:시작 -->
	<div id="com_input" class="line_T2B1">
		<!--Write: 시작 -->
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="100" class="form_detail"><span class="sfont_orange">★</span><img src="../00_member/images/join/detail_company_num.gif" alt="사업자 번호"></td>
            <td width="570" class="td_view_detail" ><input name="com_num" type="text" value="<%=KJFUtil.print(pm.getCom_num())%>" size="20" maxlength="20" title="사업자 번호를 입력해주세요" readonly /></td>
          </tr>

          <tr>
            <td colspan="2" class="line_1_d1"></td>
          </tr>

          <tr>
            <td class="form_detail"><span class="sfont_orange">★</span><img src="../00_member/images/join/detail_company.gif" alt="상호"></td>
            <td class="td_view_detail"><input name="com_name" type="text" value="<%=KJFUtil.print(pm.getCom_name())%>" size="20" maxlength="20" <%=pm.getCom_reg_yn().equals("Y")?"readonly":"" %> title="상호를 입력해주세요"></td>
          </tr>

          <tr>
            <td colspan="2" class="line_1_d1"></td>
          </tr>

          <tr>
            <td class="form_detail"><span class="sfont_orange">★</span><img src="../00_member/images/join/detail_regi_num.gif" alt="등록증 등록번호"></td>
            <td class="td_view_detail"><input name="reg_num" type="text" value="<%=KJFUtil.print(pm.getReg_num())%>" size="20" maxlength="20" <%=pm.getCom_reg_yn().equals("Y")?"readonly":"disabled" %> title="등록증 등록번호를 입력해주세요"></td>
          </tr>

          <tr>
            <td colspan="2" class="line_1_d1"></td>
          </tr>

          <tr>
            <td class="form_detail"><span class="sfont_orange">★</span><img src="../00_member/images/join/detail_company_zip.gif" alt="소재지 우편번호"></td>
            <td class="td_view_detail">
				<input name="office_post_num" type="text" value="<%=KJFUtil.print(pm.getOffice_post_num())%>" size="8" maxlength="7" title="소재지 우편번호를 입력해주세요" readonly>
				<% if(pm.getCom_reg_yn().equals("N")) { %>
				<a href="javascript:com_post_search()"><img src="../00_member/images/join/btn_zip.gif" alt="소재지 우편번호 검사" align="absmiddle" tabindex="0"></a>
				<% } %>
			</td>
          </tr>

          <tr>
            <td colspan="2" class="line_1_d1"></td>
          </tr>

          <tr>
            <td class="form_detail"><span class="sfont_orange">★</span><img src="../00_member/images/join/detail_company_address.gif" alt="소재지 주소"></td>
            <td class="td_view_detail"><input name="office_addr" type="text" value="<%=KJFUtil.print(pm.getOffice_addr())%>" size="40" maxlength="40" title="소재지 주소를 입력해주세요" readonly ></td>
          </tr>

          <tr>
            <td colspan="2" class="line_1_d1"></td>
          </tr>

          <tr>
            <td class="form_detail"><span class="sfont_orange"></span><img src="../00_member/images/join/detail_company_address_d.gif" alt="소재지 상세주소"></td>
            <td class="td_view_detail"><input name="office_addr_detail" type="text" value="<%=KJFUtil.print(pm.getOffice_addr_detail())%>" size="40" maxlength="40" <%=pm.getCom_reg_yn().equals("Y")?"readonly":"" %> ></td>
          </tr>

          <tr>
            <td colspan="2" class="line_1_d1"></td>
          </tr>

          <tr>
            <td class="form_detail"><span class="sfont_orange">★</span><img src="../00_member/images/join/detail_company_tel.gif" alt="소재지 전화번호"></td>
            <td class="td_view_detail"><input name="office_tel" type="text" value="<%=KJFUtil.print(pm.getOffice_tel())%>" size="16" maxlength="16" <%=pm.getCom_reg_yn().equals("Y")?"readonly":"" %> title="소재지 전화번호를 입력해주세요"></td>
          </tr>

          <tr>
            <td colspan="2" class="line_1_d1"></td>
          </tr>

          <tr>
            <td class="form_detail"><span class="sfont_orange">★</span><img src="../00_member/images/join/detail_company_fax.gif" alt="소재지 팩스번호"></td>
            <td class="td_view_detail"><input name="office_fax" type="text" value="<%=KJFUtil.print(pm.getOffice_fax())%>" size="16" maxlength="16" <%=pm.getCom_reg_yn().equals("Y")?"readonly":"" %> title="소재지 팩스번호를 입력해주세요"></td>
          </tr>
        </table>
		<!--Write:끝 -->
	</div>
	<!--기업정보입력 : E -->
	
	<!--기업정보입력 : E -->


	
	
</div>
<!--기업정보 : E -->

</form>


<!--버튼 :S -->
<div id="mem_kind_bg">
	<div id="mem_btn">
		<a href="javascript:fn_ComMemReg()"><img src="../00_member/images/btn_ok.gif" alt="확인"></a>
		<a href="../member/regist.do"><img src="../00_member/images/btn_cancel.gif" alt="취소"></a>
	</div>
</div>
<!--버튼 :E -->

