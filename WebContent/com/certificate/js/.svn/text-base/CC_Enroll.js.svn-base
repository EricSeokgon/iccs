// 개인인증서 등록
function individualTestimony() {

	chkbool = contolchk();
	if (chkbool == false) {
		return;
	}
	
	// 환경설정 함수 콜
	init();
	var ret;
	var signeddata;
	var userdn;
	var ssn = document.fmParam.user_ssn1.value + document.fmParam.user_ssn2.value;

	// 인증서 선택창 초기화 및 선택된 인증서의 DN 추출
	// DN은 인증기관에서 유니크한 것임.
	userdn = document.CC_Object_id.GetUserDN();
	
	if( userdn == null || userdn == "" ) { 
		//alert(" 사용자 DN 선택이 취소 되었습니다.");
		return;
		
	} else {
		UserCert = document.CC_Object_id.CC_get_cert_local(userdn);
		
		if (UserCert =="") {
			alert("인증서 추출 실패");
			return;
			
		} else {
			UserCert = Repalce_cert(UserCert);				
		}		

		getR = CC_Object_id.GetRFromKey(userdn, "");
		
		if (getR == "") {
			alert("주민번호를 확인할 수 없는 인증서입니다.");
			return;
		}

		ret = CC_Object_id.ValidCert_VID(userdn, getR, ssn);
		//alert(ret);		

		if (ret != "0") {
			alert("본인확인에 실패했습니다. 선택한 인증서의 주민번호와 일치하지 않습니다.\n\n해당 인증기관에 문의하시기 바랍니다.");
			return;
			
		} else {
			//alert("본인확인 성공!@@");
			
			document.fmParam.user_cert.value = UserCert;
			document.fmParam.user_dn.value = userdn;
		}		

		

	}//DN 추출 If문 끝
}

// 기업인증서 등록
function enterpriseTestimony() {

	// 환경설정 함수 콜
	init();
	var ret;
	var signeddata;
	var userdn;
	var ssn = document.fmParam.com_num.value;

	// 인증서 선택창 초기화 및 선택된 인증서의 DN 추출
	// DN은 인증기관에서 유니크한 것임.
	userdn = document.CC_Object_id.GetUserDN();
	
	if( userdn == null || userdn == "" ) { 
		//alert(" 사용자 DN 선택이 취소 되었습니다.");
		return;
		
	} else {
		UserCert = document.CC_Object_id.CC_get_cert_local(userdn);
		
		if (UserCert =="") {
			alert("인증서 추출 실패");
			return;
			
		} else {
			UserCert = Repalce_cert(UserCert);				
		}		

		getR = CC_Object_id.GetRFromKey(userdn, "");
		
		if (getR == "") {
			alert("사업자번호를 확인할 수 없는 인증서입니다.");
			return;
		}

		ret = CC_Object_id.ValidCert_VID(userdn, getR, ssn);
		//alert(ret);		

		if (ret != "0") {
			alert("본인확인에 실패했습니다. 선택한 인증서의 사업자번호와 일치하지 않습니다.\n\n해당 인증기관에 문의하시기 바랍니다.");
			return;		
			
		} else {
			//alert("본인확인 성공!@@");
			
			document.fmParam.user_cert.value = UserCert;
			document.fmParam.user_dn.value = userdn;
		}				

		

	}//DN 추출 If문 끝
}

function contolchk() {
	
	try {
		var xObj = new ActiveXObject("AxCrossCert.AxCrossCert");
		
		//alert(xObj)
		
		if(xObj)
			Installed = true;
		else
			Installed = false;
		
	} catch(ex) {
			Installed = false;
	}

	if(Installed == false) {
		
		var tp = (screen.height - 450)/2;
		var lp = (screen.width - 600)/2;
		var mid_str = "toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=1,resizable=1,copyhistory=0,width=600,height=450,left=" + lp + ",top=" + tp;
		window.open("../certificate/install.do", "",mid_str);
	}

	return Installed;
}

function Repalce_cert(str_cert) {
	
	var parse_cert = "";
	
	if (str_cert == "")
		return str;
	
	else {
		parse_cert = str_cert.replace(/\n/g, "");
		parse_cert = parse_cert.replace(/-----BEGIN CERTIFICATE-----/g, "");
		parse_cert = parse_cert.replace(/-----END CERTIFICATE-----/g, "");
	}
	
	return parse_cert;
}