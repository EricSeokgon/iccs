/***************************************************************************
* Title   : 시.군.구 코드를 검색한다   
* Content : 시.군.구 코드 Ajax검색 처리를 한다. 
****************************************************************************/
function fn_changeSGG_CD() {

	var fm = document.fmParam;
	
	if (fm.sido_code.value == "") {
		document.getElementById("layer_sggcd").innerHTML = 
			"<select name='sigungu_code' class='input_bbs' > " +
			" 	<option value='' selected> 시.군.구 선택 </option> " +
			" </select> ";
		
	} else {		
		var sido_code = fm.sido_code.value;

		sendRequest("../comm/CommAction.do", "cmd=CommSigunguCode&scSD_CD=" + sido_code, fn_SggCodeView, "POST");
	}
}

/***************************************************************************
* Title   : 시.군.구 코드 화면 처리 화면
* Content : 시.군.구 코드 Ajax로 검색 리스트를 화면 처리 한다. 
****************************************************************************/
function fn_SggCodeView() {
	document.getElementById("layer_sggcd").innerHTML = httpRequest.responseText;
}


/***************************************************************************
* Title   : 공사업등록 확인 처리
* Content : 공사업등록 확인을 Ajax로 처리 한다. 
****************************************************************************/
function fn_checkPubRegNum() {

	var fm = document.fmParam;
	
	var com_num = fm.com_num.value;

	if (confirm("등록이 되었을 시 자동 처리됩니다. \n확인을을 하시겠습니까?")) {
		sendRequest("../comm/CommAction.do", "cmd=CommPubRegCheck&mana_num=" + com_num, fn_resultPubRegNum, "POST");
	}
}

/***************************************************************************
* Title   : 공사업등록 확인결과 화면 처리 화면
* Content : 공사업등록 확인결과를 Ajax로 처리 한다. 
****************************************************************************/
function fn_resultPubRegNum() {
	
	
	
	var result = httpRequest.responseText;
	
	if (result == "success") {
		
		var fm = document.fmParam;
		
		fm.cmd.value = "MemPubResNumU";
		fm.submit();
	} else {
		alert("공사업 등록정보가 없습니다.");
	}
}
