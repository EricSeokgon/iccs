<%@ page contentType="text/html;charset=utf-8"%>

<!--개인정보수정 : S -->
<form name="fmParam" action="../mem/MemAction.do" method="post">
<input type="hidden" name="cmd"        value="MemInfoU">
<input type="hidden" name="com_reg_yn" value="<%=KJFUtil.print(rEntity.getValue(0, "COM_REG_YN"))%>">
	
	<!--개인정보입력 타이틀2 : S -->
	<div class="mem_title_2">
		<div class="mem_title_2_img"><img src="../00_member/images/join/join_mem_modify.gif" alt="개인정보 수정">
		<span class="mem_title_2_text">★ 는 필수입력사항입니다.</span></div>	
		
		<!--개인정보입력:시작 -->
		<div class="line_T2B1">
			<!--Write: 시작 -->
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			  <tr>
				<td class="form_detail"><span class="sfont_orange">★</span><img src="../00_member/images/join/detail_03id.gif" alt="아이디"></td>
				<td class="td_view_detail"><!--아이디 -->
					<input name="user_id" type="text" size="20" maxlength="16" value="<%=rEntity.getValue(0, "USER_ID")%>" title="아이디를 입력해주세요" disabled>
				</td>
			  </tr>
	
			  <tr>
				<td colspan="2" class="line_1_d1"></td>
			  </tr>
	
			  
	
			  <tr>
				<td width="100" class="form_detail"><span class="sfont_orange">★</span><img src="../00_member/images/join/detail_01name.gif" alt="이름"></td>
				<td width="570" class="td_view_detail" >
					<input name="user_name" type="text" value="<%=KJFUtil.print(rEntity.getValue(0, "USER_NAME"))%>" size="20" tabindex="1" title="이름을 입력해주세요" disabled>
				</td>
				
			  </tr>
	
			  <tr>
				<td colspan="2" class="line_1_d1"></td>
			  </tr>

			  <tr>
				<td class="form_detail"><span class="sfont_orange">★</span><img src="../00_member/images/join/detail_02person.gif" alt="지역"></td>
				<td class="td_view_detail" >
					<input name="user_ssn1" type="text" value="<%=KJFUtil.print(rEntity.getValue(0, "USER_SSN1"))%>" size="6" disabled> - 
					<input name="user_ssn2" type="password" value="<%=KJFUtil.print(rEntity.getValue(0, "USER_SSN2"))%>" size="15" disabled>
				</td>
			  </tr>
	
			  <tr>
				<td colspan="2" class="line_1_d1"></td>
			  </tr>
	
			  
			  <tr>
				<td class="form_detail"><span class="sfont_orange">★</span><img src="../00_member/images/join/detail_06province.gif" alt="시도"></td>
				<td class="td_view_detail">
					<KTags:KJFSelect item="<%=(Vector)request.getAttribute("v_scSD_CD")%>"
									 name="sido_code"
									 value="<%=KJFUtil.print(rEntity.getValue(0, "SIDO_CODE"))%>"
									 script="onchange='fn_changeSGG_CD()' "/>	
				</td>
			  </tr>
	
			  <tr>
				<td colspan="2" class="line_1_d1"></td>
			  </tr>
	
			  <tr>
				<td class="form_detail"><span class="sfont_orange">★</span><img src="../00_member/images/join/detail_07county.gif" alt="시군구"></td>
				<td class="td_view_detail">
					<div id="layer_sggcd" name="layer_sggcd">
						<KTags:KJFSelect item="<%=(Vector)request.getAttribute("v_scSGG_CD")%>"
									 name="sigungu_code"
									 value="<%=KJFUtil.print(rEntity.getValue(0, "SIGUNGU_CODE"))%>"
									 script=""/>
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
						<option value="02" <%=KJFUtil.print(rEntity.getValue(0, "USER_TEL1")).equals("02")?"selected":""%>>서울 02</option>
						<option value="031" <%=KJFUtil.print(rEntity.getValue(0, "USER_TEL1")).equals("031")?"selected":""%>>경기 031</option>
						<option value="032" <%=KJFUtil.print(rEntity.getValue(0, "USER_TEL1")).equals("032")?"selected":""%>>인천 032</option>
						<option value="033" <%=KJFUtil.print(rEntity.getValue(0, "USER_TEL1")).equals("033")?"selected":""%>>강원 033</option>		
						<option value="041" <%=KJFUtil.print(rEntity.getValue(0, "USER_TEL1")).equals("041")?"selected":""%>>충남 041</option>	
						<option value="042" <%=KJFUtil.print(rEntity.getValue(0, "USER_TEL1")).equals("042")?"selected":""%>>대전 042</option>
						<option value="043" <%=KJFUtil.print(rEntity.getValue(0, "USER_TEL1")).equals("043")?"selected":""%>>충북 043</option>
						<option value="051" <%=KJFUtil.print(rEntity.getValue(0, "USER_TEL1")).equals("051")?"selected":""%>>부산 051</option>
						<option value="052" <%=KJFUtil.print(rEntity.getValue(0, "USER_TEL1")).equals("052")?"selected":""%>>울산 052</option>
						<option value="053" <%=KJFUtil.print(rEntity.getValue(0, "USER_TEL1")).equals("053")?"selected":""%>>대구 053</option>
						<option value="054" <%=KJFUtil.print(rEntity.getValue(0, "USER_TEL1")).equals("054")?"selected":""%>>경북 054</option>
						<option value="055" <%=KJFUtil.print(rEntity.getValue(0, "USER_TEL1")).equals("055")?"selected":""%>>경남 055</option>
						<option value="061" <%=KJFUtil.print(rEntity.getValue(0, "USER_TEL1")).equals("061")?"selected":""%>>전남 061</option>
						<option value="062" <%=KJFUtil.print(rEntity.getValue(0, "USER_TEL1")).equals("062")?"selected":""%>>광주 062</option>
						<option value="063" <%=KJFUtil.print(rEntity.getValue(0, "USER_TEL1")).equals("063")?"selected":""%>>전북 063</option>
						<option value="064" <%=KJFUtil.print(rEntity.getValue(0, "USER_TEL1")).equals("064")?"selected":""%>>제주 064</option>
						<option value="070" <%=KJFUtil.print(rEntity.getValue(0, "USER_TEL1")).equals("070")?"selected":""%>>인터넷 070</option>
					</select>
					-&nbsp;<input name="user_tel2" type="text"  value="<%=KJFUtil.print(rEntity.getValue(0, "USER_TEL2"))%>" size="4" maxlength="4" title="전화번호를 입력해주세요">
					-&nbsp;<input name="user_tel3" type="text"  value="<%=KJFUtil.print(rEntity.getValue(0, "USER_TEL3"))%>" size="4" maxlength="4" title="전화번호를 입력해주세요">
				</td>
			  </tr>
	
			  <tr>
				<td colspan="2" class="line_1_d1"></td>
			  </tr>
	
			  <tr>
				<td class="form_detail"><span class="sfont_orange">★</span><img src="../00_member/images/join/detail_09mobile.gif" alt="핸드폰번호"></td>
				<td class="td_view_detail">
					<select name="user_mobile1">
						<option value="010" <%=KJFUtil.print(rEntity.getValue(0, "USER_MOBILE1")).equals("010")?"selected":""%>>010</option>
						<option value="011" <%=KJFUtil.print(rEntity.getValue(0, "USER_MOBILE1")).equals("011")?"selected":""%>>011</option>
						<option value="016" <%=KJFUtil.print(rEntity.getValue(0, "USER_MOBILE1")).equals("016")?"selected":""%>>016</option>
						<option value="017" <%=KJFUtil.print(rEntity.getValue(0, "USER_MOBILE1")).equals("017")?"selected":""%>>017</option>		
						<option value="018" <%=KJFUtil.print(rEntity.getValue(0, "USER_MOBILE1")).equals("018")?"selected":""%>>018</option>	
						<option value="019" <%=KJFUtil.print(rEntity.getValue(0, "USER_MOBILE1")).equals("019")?"selected":""%>>019</option>		
					</select>
					-&nbsp;<input name="user_mobile2" type="text"  value="<%=KJFUtil.print(rEntity.getValue(0, "USER_MOBILE2"))%>" size="4" maxlength="4" title="핸드폰번호를 입력해주세요">
					-&nbsp;<input name="user_mobile3" type="text"  value="<%=KJFUtil.print(rEntity.getValue(0, "USER_MOBILE3"))%>" size="4" maxlength="4" title="핸드폰번호를 입력해주세요">
				</td>
			  </tr>
	
			  <tr>
				<td colspan="2" class="line_1_d1"></td>
			  </tr>
	
			  <tr>
				<td class="form_detail"><span class="sfont_orange">★</span><img src="../00_member/images/join/detail_10email.gif" alt="이메일"></td>
				<td class="td_view_detail"><input name="user_email" type="text" value="<%=KJFUtil.print(rEntity.getValue(0, "USER_EMAIL"))%>" size="20" maxlength="20" title="이메일을 입력해주세요"></td>
			  </tr>
	
			  <tr>
				<td colspan="2" class="line_1_d1"></td>
			  </tr>
	
			  <tr>
				<td class="form_detail"><span class="sfont_orange">★</span><img src="../00_member/images/join/detail_11zip.gif" alt="우편번호"></td>
				<td class="td_view_detail">
				  <a href="javascript:post_search()"><input name="user_post_num" type="text" value="<%=KJFUtil.print(rEntity.getValue(0, "USER_POST_NUM"))%>" size="20" maxlength="20" title="우편번호를 입력해주세요" readonly>
				  <img src="../00_member/images/join/btn_zip.gif" alt="우편번호검사"  align="absmiddle"  tabindex="0"></a>
				</td>
			  </tr>
	
			  <tr>
				<td colspan="2" class="line_1_d1"></td>
			  </tr>
	
			  <tr>
				<td class="form_detail"><span class="sfont_orange">★</span><img src="../00_member/images/join/detail_12address.gif" alt="주소"></td>
				<td class="td_view_detail"><input name="user_addr" type="text" value="<%=KJFUtil.print(rEntity.getValue(0, "USER_ADDR"))%>" size="40" maxlength="40" title="주소를 입력해주세요" readonly></td>
			  </tr>
	
			  <tr>
				<td colspan="2" class="line_1_d1"></td>
			  </tr>
	
			  <tr>
				<td class="form_detail"><span class="sfont_orange">★</span><img src="../00_member/images/join/detail_13add_d.gif" alt="상세주소"></td>
				<td class="td_view_detail"><input name="user_addr_detail" type="text" value="<%=KJFUtil.print(rEntity.getValue(0, "USER_ADDR_DETAIL"))%>" size="40" maxlength="40" title="상세주소를 입력해주세요"></td>
			  </tr>

			  <tr>
				<td colspan="2" class="line_1_d1"></td>
			  </tr>
	
			  <tr>
				<td class="form_detail"><span class="sfont_orange">★</span><img src="../00_member/images/join/detail_pw_now.gif" alt="현재비밀번호"></td>
				<td class="td_view_detail"><input name="cur_passwd" type="password" value="" size="20" maxlength="20" title="비밀번호를 입력해주세요"></td>
			  </tr>
	
		  </table>
			<!--Write:끝 -->
		</div>
		<!--개인정보입력 : E -->
		
	</div>
	<!--개인정보입력 타이틀2 : E -->
	
	<% if(user.getCAPITAL().equals("E")) { %>
	<!--공사업정보 : S -->
	<div class="mem_title_2">
		<div class="mem_title_2_img"><img src="../00_member/images/join/join_regi_infor.gif" alt="공사업 등록정보" />
		<span class="mem_title_2_text">★ 는 필수입력사항입니다.</span></div>
		
		
		<!--개인정보입력:시작 -->
		<div class="line_T2B1">
			<!--Write: 시작 -->
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			  <tr>
				<td width="100" class="form_detail"><span class="sfont_orange">★</span><img src="../00_member/images/join/detail_regi_yes.gif" alt="이름"></td>
				<td width="570" class="td_view_detail" ><!--이름 -->
					<label><input name="com_reg" type="radio" class="radio" value="Y" <%=rEntity.getValue(0, "COM_REG_YN").equals("Y")? "checked":"" %> disabled>공사업 등록 </label>
					<label><input name="com_reg" type="radio" class="radio" value="N" <%=rEntity.getValue(0, "COM_REG_YN").equals("N")? "checked":"" %> disabled>공사업 미등록 </label>
					<%if (rEntity.getValue(0, "COM_REG_YN").equals("N")) { %>
						&nbsp;<a href="javascript:fn_checkPubRegNum()"><img src="../images/btn/btn_reg_chk.gif" alt="등록 확인" align="absmiddle"></a>
					<% } %>
					</td>
			  </tr>
			</table>
			<!--Write:끝 -->
		</div>
		<!--개인정보입력 : E -->
		
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
				<td width="570" class="td_view_detail" ><input name="com_num" type="text" value="<%=KJFUtil.print(rEntity.getValue(0, "COM_NUM"))%>" size="20" maxlength="20" title="사업자 번호를 입력해주세요" readonly /></td>
			  </tr>
	
			  <tr>
				<td colspan="2" class="line_1_d1"></td>
			  </tr>
	
			  <tr>
				<td class="form_detail"><span class="sfont_orange">★</span><img src="../00_member/images/join/detail_company.gif" alt="상호"></td>
				<td class="td_view_detail"><input name="com_name" type="text" value="<%=KJFUtil.print(rEntity.getValue(0, "COM_NAME"))%>" size="20" maxlength="20" <%=KJFUtil.print(rEntity.getValue(0, "COM_REG_YN")).equals("Y")?"readonly":"" %> title="상호를 입력해주세요"></td>
			  </tr>
	
			  <tr>
				<td colspan="2" class="line_1_d1"></td>
			  </tr>
	
			  <tr>
				<td class="form_detail"><span class="sfont_orange">★</span><img src="../00_member/images/join/detail_regi_num.gif" alt="등록증 등록번호"></td>
				<td class="td_view_detail"><input name="reg_num" type="text" value="<%=KJFUtil.print(rEntity.getValue(0, "REG_NUM"))%>" size="20" maxlength="20" title="등록증 등록번호를 입력해주세요" disabled></td>
			  </tr>
	
			  <tr>
				<td colspan="2" class="line_1_d1"></td>
			  </tr>
	
			  <tr>
				<td class="form_detail"><span class="sfont_orange">★</span><img src="../00_member/images/join/detail_company_zip.gif" alt="소재지 우편번호"></td>
				<td class="td_view_detail">
					<input name="office_post_num" type="text" value="<%=KJFUtil.print(rEntity.getValue(0, "OFFICE_POST_NUM"))%>" size="8" maxlength="7" title="소재지 우편번호를 입력해주세요" readonly>
					<% if(KJFUtil.print(rEntity.getValue(0, "COM_REG_YN")).equals("N")) { %>
					<a href="javascript:com_post_search()"><img src="../00_member/images/join/btn_zip.gif" alt="소재지 우편번호 검사" align="absmiddle" tabindex="0"></a>
					<% } %>
				</td>
			  </tr>
	
			  <tr>
				<td colspan="2" class="line_1_d1"></td>
			  </tr>
	
			  <tr>
				<td class="form_detail"><span class="sfont_orange">★</span><img src="../00_member/images/join/detail_company_address.gif" alt="소재지 주소"></td>
				<td class="td_view_detail"><input name="office_addr" type="text" value="<%=KJFUtil.print(rEntity.getValue(0, "OFFICE_ADDR"))%>" size="40" maxlength="40" title="소재지 주소를 입력해주세요" readonly ></td>
			  </tr>
	
			  <tr>
				<td colspan="2" class="line_1_d1"></td>
			  </tr>
	
			  <tr>
				<td class="form_detail"><span class="sfont_orange">★</span><img src="../00_member/images/join/detail_company_address_d.gif" alt="소재지 상세주소"></td>
				<td class="td_view_detail"><input name="office_addr_detail" type="text" value="<%=KJFUtil.print(rEntity.getValue(0, "OFFICE_ADDR_DETAIL"))%>" size="40" maxlength="40" <%=KJFUtil.print(rEntity.getValue(0, "COM_REG_YN")).equals("Y")?"readonly":"" %> title="소재지 상세주소를 입력해주세요"></td>
			  </tr>
	
			  <tr>
				<td colspan="2" class="line_1_d1"></td>
			  </tr>
	
			  <tr>
				<td class="form_detail"><span class="sfont_orange">★</span><img src="../00_member/images/join/detail_company_tel.gif" alt="소재지 전화번호"></td>
				<td class="td_view_detail"><input name="office_tel" type="text" value="<%=KJFUtil.print(rEntity.getValue(0, "OFFICE_TEL"))%>" size="16" maxlength="16" <%=KJFUtil.print(rEntity.getValue(0, "COM_REG_YN")).equals("Y")?"readonly":"" %> title="소재지 전화번호를 입력해주세요"></td>
			  </tr>
	
			  <tr>
				<td colspan="2" class="line_1_d1"></td>
			  </tr>
	
			  <tr>
				<td class="form_detail"><span class="sfont_orange">★</span><img src="../00_member/images/join/detail_company_fax.gif" alt="소재지 팩스번호"></td>
				<td class="td_view_detail"><input name="office_fax" type="text" value="<%=KJFUtil.print(rEntity.getValue(0, "OFFICE_FAX"))%>" size="16" maxlength="16" <%=KJFUtil.print(rEntity.getValue(0, "COM_REG_YN")).equals("Y")?"readonly":"" %> title="소재지 팩스번호를 입력해주세요"></td>
			  </tr>
			</table>
			<!--Write:끝 -->
		</div>
		<!--기업정보입력 : E -->
		
	</div>	
	<!--기업정보 : E -->
	<% } %>
	
	</form>
	
	
	<!--버튼 :S -->
	<div id="mem_kind_bg">
		<div id="mem_btn">
			<% if(user.getCAPITAL().equals("E")) { %>
				<a href="javascript:fn_ComMemModify()"><img src="../00_member/images/btn_modify.gif" alt="수정" width="52" height="26"></a>

			<% } else {%>
				<a href="javascript:fn_PriMemModify()"><img src="../00_member/images/btn_modify.gif" alt="수정" width="52" height="26"></a>
			<% } %>
			
			<a href="../mystore/MystoreAction.do?cmd=MyProState"><img src="../00_member/images/btn_cancel.gif" alt="취소"></a>
		</div>
	</div>
	<!--버튼 :E -->

</form>
<!--개인정보수정 : E -->