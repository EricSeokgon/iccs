// 약관동의
function check_accept() {

	if( document.getElementById('check1').checked && 
		document.getElementById('check2').checked  ) {
		return true;
	} else {
		
		alert("이용약관과 개인정보 수집 및 이용에 대한 안내를 모두 동의해 주세요.");
		return false;
	}
}

// 회원가입입력화면으로 이동
function go_regist() {
	
	if (check_accept()) {		
		location.href="../member/identity.do";
	}
}

// 개인회원 등록
function go_regist_Pri() {
	
	var fm = document.identityP_form;
	
	if (check_identityPrivate()) {	// 개인회원정보 체크
			
		if(individualTestimony()) {	// 인증확인
			fm.submit();
		}
	}	
}

// 기업회원 등록
function go_regist_Corp() {
	
	var fm = document.identityB_form;
	
	var com_num = fm.corp_no1.value + fm.corp_no2.value + fm.corp_no3.value;
	if (com_num == "1234567899"){
		fm.com_num.value = com_num;
		fm.submit();
	} else {
	if (t()) {	// 기업회원정보 체크
		
		if(enterpriseTestimony()) {	// 인증확인
			fm.com_num.value = com_num;
			fm.submit();
		}
	}
	}
}

//개인회원 실명확인
function check_identityPrivate() {
	var fm = document.identityP_form;
	
	var ssn = fm.user_ssn1.value + fm.user_ssn2.value;
	
	if ( !isValid_name(fm.user_name.value) ) {
		fm.user_name.focus();
		return false;
	}
	
	if ( !isValid_socno(ssn) ) {
		alert("주민번호가 올바르지 않습니다.");
		fm.user_ssn1.focus();
		return false;
	}
	
	return true;
}

// 기업회원 실명확인
function check_identityBusiness() {
	var fm = document.identityB_form;
	
	var ssn     = fm.user_ssn1.value + fm.user_ssn2.value;
	var com_num = fm.corp_no1.value + fm.corp_no2.value + fm.corp_no3.value;
	
	if ( !isValid_name(fm.user_name.value) ) {
		fm.user_name.focus();
		return false;
	}
	
	if ( !isValid_socno(ssn) ) {
		alert("주민번호가 올바르지 않습니다.");
		fm.user_ssn1.focus();
		return false;
	}
	
	if (!isValid_bizr_no(com_num)) {
		alert("사업자 번호가 올바르지 않습니다.");
		fm.corp_no1.focus();
		return false;
	}
	
	return true;
}

//우편번호검색
function post_search() {
	winOpenAtCenter('../comm/CommAction.do?cmd=CommZipSearch&reqCol=user_post_num,user_addr,user_addr_detail', "postSearch",  400, 330);
}

// 소재지 우편번호검색
function com_post_search() {
	winOpenAtCenter('../comm/CommAction.do?cmd=CommZipSearch&reqCol=office_post_num,office_addr,office_addr_detail', "ComPostSearch",  400, 330);
}

//아이디 중복 체크
function checkDuplicateID( id_value ) {
	
	var fm = document.fmParam;
	var res;
    var cWin;
    
    if( !id_value ) {
        alert("아이디를 입력해주세요");
        fm.user_id.focus();
    } else {
    	res = isValid_id(id_value);
        if( !res ) {
        	//alert("유효하지 않은 아이디입니다.");
        	fm.user_id.focus();
            return;
        }

        var template = ("../comm/CommAction.do?cmd=CommDuplIDCheck&id=" + id_value);
        winOpenAtCenter( template,  "checkDuplicateID", 401,303, "auto");

        return;
    }
}

//개인회원 등록 처리
function fn_PriMemReg() {
	
	if (check_privateInfo()) {
		var fm = document.fmParam;	    
	    fm.submit();
	}
}

// 기업회원 등록 처리
function fn_ComMemReg() {
	
	if (check_privateInfo() && check_companyInfo()) {
		var fm = document.fmParam;	    
	    fm.submit();
	}
}

// 개인회원 수정 처리
function fn_PriMemModify() {
	
	if (check_modifyInfo()) {
		var fm = document.fmParam;	    
	    fm.submit();
	}
	
}

// 기업회원 수정 처리
function fn_ComMemModify() {
	
	if (check_modifyInfo() && check_companyInfo()) {
		var fm = document.fmParam;	    
	    fm.submit();
	}
}

// 개인회원 정보입력 체크
function check_privateInfo() {
	
	var fm = document.fmParam;
	
	if (checkSpace(fm.user_name.value)) {
		alert("이름 입력해주세요");
		return false;
	}
	
	if (checkSpace(fm.user_ssn1.value)) {
		alert("주민번호 앞자리를 입력해주세요");
		return false;
	}
	
	if (checkSpace(fm.user_ssn2.value)) {
		alert("주민번호 뒷자리를 입력해주세요");
		return false;
	}
	
	if (!isValid_id(fm.user_id.value)) {
		fm.user_id.focus();
		return false;
	}
	
	if (fm.flag.value != "1") {
		alert("아이디 중복체크를 해주세요");
		fm.user_id.focus();
		return false;
	}
	
	if (!isValid_passwd(fm.user_passwd.value)) {
		fm.user_passwd.focus();
		return false;
	}
	
	if (trim(fm.user_passwd_ck.value) == "") {
		alert("비밀번호를 한번 더 입력하세요");
		fm.user_passwd_ck.focus();
		return false;
	}
	
	if (fm.user_passwd.value != fm.user_passwd_ck.value) {
		alert("비밀번호가 일치하지 않습니다");
		fm.user_passwd_ck.focus();
		return false;
	}
	
	if( fm.user_id.value == fm.user_passwd.value ) {
        alert("아이디와 비밀번호가 같습니다.\n보안 상의 이유로 아이디와 같은 비밀번호는 허용하지 않습니다.");
        fm.user_passwd.focus();
        return false;
    }
	
	if (trim(fm.sido_code.value) == "") {
		alert("소속 시도를 선택해주세요");
		fm.sido_code.focus();
		return false;
	}
	
	if (trim(fm.sigungu_code.value) == "") {
		alert("소속 시군구를 선택해주세요");
		fm.sigungu_code.focus();
		return false;
	}

	if (trim(fm.user_tel1.value) == "") {
		alert("전화번호를 입력해주세요");
		fm.user_tel1.focus();
		return false;
	}	
	
	if (trim(fm.user_tel2.value) == "") {
		alert("전화번호를 입력해주세요");
		fm.user_tel2.focus();
		return false;
	}
	
	if (trim(fm.user_tel3.value) == "") {
		alert("전화번호를 입력해주세요");
		fm.user_tel3.focus();
		return false;
	}
	
	if ( fm.user_tel2.value || fm.user_tel3.value) {
		if ( !isNumeric(fm.user_tel2.value) || fm.user_tel2.value.length < 3) {
			alert("올바른 숫자를 입력하세요");
			fm.user_tel2.focus();
			fm.user_tel2.select();
			return false;
		} else if ( !isNumeric(fm.user_tel3.value) || fm.user_tel3.value.length != 4) {
			alert("올바른 숫자를 입력하세요");
			fm.user_tel3.focus();
			fm.user_tel3.select();
			return false;
		}
	}
	
	if (trim(fm.user_mobile1.value) == "") {
		alert("핸드폰번호를 입력해주세요");
		fm.user_mobile1.focus();
		return false;
	}
	
	if (trim(fm.user_mobile2.value) == "") {
		alert("핸드폰번호를 입력해주세요");
		fm.user_mobile2.focus();
		return false;
	}
	
	if (trim(fm.user_mobile3.value) == "") {
		alert("핸드폰번호를 입력해주세요");
		fm.user_mobile3.focus();
		return false;
	}
	
	if ( fm.user_mobile2.value || fm.user_mobile3.value) {
		if ( !isNumeric(fm.user_mobile2.value) || fm.user_mobile2.value.length < 3) {
			alert("올바른 숫자를 입력하세요");
			fm.user_mobile2.focus();
			fm.user_mobile2.select();
			return false;
		} else if ( !isNumeric(fm.user_mobile3.value) || fm.user_mobile3.value.length != 4) {
			alert("올바른 숫자를 입력하세요");
			fm.user_mobile3.focus();
			fm.user_mobile3.select();
			return false;
		}
	}
	
	if ( !isValid_email(fm.user_email.value) ) {
		fm.user_email.focus();
		return false;
	}
	
	if (trim(fm.user_post_num.value) == "") {
		alert("우편번호를 입력해주세요");
		return false;
	}
	
	if (trim(fm.user_addr.value) == "") {
		alert("주소를 입력해주세요");
		return false;
	}
	
	if (trim(fm.user_addr_detail.value) == "") {
		alert("상세주소를 입력해주세요");
		fm.user_addr_detail.focus();
		return false;
	}
	
	if (trim(fm.user_dn.value) == "" && trim(fm.user_cert.value) == "") {
		alert("인증서 등록을 해주세요");
		return false;
	}
	
	return true;
}


//기업회원 정보입력 체크
function check_companyInfo() {
	
	var fm = document.fmParam;
	
	if (trim(fm.com_num.value) == "") {
		alert("사업자번호를 입력해주세요");
		fm.com_num.focus();
		return false;
	}
	
	if (trim(fm.com_name.value) == "") {
		alert("상호를 입력해주세요");
		fm.com_name.focus();
		return false;
	}
	
//	if (trim(fm.reg_num.value) == "") {
//		alert("등록증 등록번호를 입력해주세요");
//		fm.reg_num.focus();
//		return false;
//	}
	
	if (trim(fm.office_post_num.value) == "") {
		alert("소재지 우편번호를 입력해주세요");
		fm.office_post_num.focus();
		return false;
	}
	
	if (trim(fm.office_addr.value) == "") {
		alert("소재지 주소를 입력해주세요");
		fm.office_addr.focus();
		return false;
	}

	
	/*
	if (trim(fm.office_addr_detail.value) == "") {강호일 7/7
		alert("소재지 상세주소를 입력해주세요");
		fm.office_addr_detail.focus();
		return false;
	}
	*/
	
	if (trim(fm.office_tel.value) == "") {
		alert("소재지 전화번호를 입력해주세요");
		fm.office_tel.focus();
		return false;
	}
	
	if (trim(fm.office_fax.value) == "") {
		alert("소재지 팩스번호를 입력해주세요");
		fm.office_fax.focus();
		return false;
	}	
	
	return true;
}

//개인회원 정보수정 체크
function check_modifyInfo() {
	var fm = document.fmParam;	
	
	if (!isValid_id(fm.user_id.value)) {
		return false;
	}
	
	if ( !isValid_name(fm.user_name.value) ) {
		return false;
	}
	
	if (trim(fm.sido_code.value) == "") {
		alert("소속 시도를 선택해주세요");
		fm.sido_code.focus();
		return false;
	}
	
	if (trim(fm.sigungu_code.value) == "") {
		alert("소속 시군구를 선택해주세요");
		fm.sigungu_code.focus();
		return false;
	}
	
	if (trim(fm.user_tel1.value) == "") {
		alert("전화번호를 입력해주세요");
		fm.user_tel1.focus();
		return false;
	}	
	
	if (trim(fm.user_tel2.value) == "") {
		alert("전화번호를 입력해주세요");
		fm.user_tel2.focus();
		return false;
	}
	
	if (trim(fm.user_tel3.value) == "") {
		alert("전화번호를 입력해주세요");
		fm.user_tel3.focus();
		return false;
	}
	
	if ( fm.user_tel2.value || fm.user_tel3.value) {
		if ( !isNumeric(fm.user_tel2.value) || fm.user_tel2.value.length < 3) {
			alert("올바른 숫자를 입력하세요");
			fm.user_tel2.focus();
			fm.user_tel2.select();
			return false;
		} else if ( !isNumeric(fm.user_tel3.value) || fm.user_tel3.value.length != 4) {
			alert("올바른 숫자를 입력하세요");
			fm.user_tel3.focus();
			fm.user_tel3.select();
			return false;
		}
	}
	
	if (trim(fm.user_mobile1.value) == "") {
		alert("핸드폰번호를 입력해주세요");
		fm.user_mobile1.focus();
		return false;
	}
	
	if (trim(fm.user_mobile2.value) == "") {
		alert("핸드폰번호를 입력해주세요");
		fm.user_mobile2.focus();
		return false;
	}
	
	if (trim(fm.user_mobile3.value) == "") {
		alert("핸드폰번호를 입력해주세요");
		fm.user_mobile3.focus();
		return false;
	}
	
	if ( fm.user_mobile2.value || fm.user_mobile3.value) {
		if ( !isNumeric(fm.user_mobile2.value) || fm.user_mobile2.value.length < 3) {
			alert("올바른 숫자를 입력하세요");
			fm.user_mobile2.focus();
			fm.user_mobile2.select();
			return false;
		} else if ( !isNumeric(fm.user_mobile3.value) || fm.user_mobile3.value.length != 4) {
			alert("올바른 숫자를 입력하세요");
			fm.user_mobile3.focus();
			fm.user_mobile3.select();
			return false;
		}
	}
	
	if ( !isValid_email(fm.user_email.value) ) {
		fm.user_email.focus();
		return false;
	}
	
	if (trim(fm.user_post_num.value) == "") {
		alert("우편번호를 입력해주세요");
		return false;
	}
	
	if (trim(fm.user_addr.value) == "") {
		alert("주소를 입력해주세요");
		return false;
	}
	
	if (trim(fm.user_addr_detail.value) == "") {
		alert("상세주소를 입력해주세요");
		fm.user_addr_detail.focus();
		return false;
	}
	
	if (trim(fm.cur_passwd.value) == "") {
		alert("비밀번호를  입력해주세요");
		fm.cur_passwd.select();
		fm.cur_passwd.focus();
		return false;
	}
	
	return true;
}