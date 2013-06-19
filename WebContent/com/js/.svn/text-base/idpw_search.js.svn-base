// 아이디 찾기
function id_search() {
	var fm = document.idSearForm;	
	
	var ssn = fm.user_ssn1.value + fm.user_ssn2.value;
	
	if (fm.mem_kind.value == "") {
		alert("회원 종류를 선택하여주십시요.");
		return;
	}
	
	if ( !isValid_name(fm.user_name.value) ) {
		fm.user_name.focus();
		return;
	}
	
	if ( !isValid_socno(ssn) ) {
		alert("주민번호가 올바르지 않습니다.");
		fm.user_ssn1.focus();
		return;
	}
	
	fm.submit();	
}

// 비밀번호 찾기
function pw_search() {
	
	var fm = document.pwSearForm;
	
	var ssn = fm.user_ssn1.value + fm.user_ssn2.value;
	
	if ( !isValid_name(fm.user_name.value) ) {
		fm.user_name.focus();
		fm.user_name.select();
		return;
	}
	
	if ( !isValid_id(fm.user_id.value) ) {
		fm.user_id.focus();
		fm.user_id.select();
		return;
	}
	
	if ( !isValid_socno(ssn) ) {
		alert("주민번호가 올바르지 않습니다.");
		fm.user_ssn1.focus();
		return;
	}
	
	fm.submit();	
}