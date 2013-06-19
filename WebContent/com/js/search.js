function fn_search() {
	var fm = document.searchParam;
	
	if (fm.scTextValue.value.trim() == "") {
    	fm.scTextValue.focus();
    	fm.scTextValue.select();
		alert("검색어 내용을 입력해주세요!");
		return;
    }
	
	fm.submit();
}

function fn_GoSearch(cmd) {
	
	var fm = document.fmParam;

	fm.cmd.value = cmd;
	fm.nowPage.value = 1;
	fm.submit();
}