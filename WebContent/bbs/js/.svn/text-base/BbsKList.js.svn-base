/***************************************************************************
* Title   : 페이징
* Content : 선택된 페이지 이동 처리를 한다.  
****************************************************************************/
function movePage(page){
    var fm = document.fmParam;
    
    fm.action = "../bbs/BbsAction.do?cmd=BbsKList";
    fm.nowPage.value= page;
    
    fm.submit();
}

/***************************************************************************
* Title   : 페이지 목록수 
* Content : 선택된 페이지 목록수 처리를 한다.  
****************************************************************************/
function changeRowPerPage(obj){
    var fm = document.fmParam;
    
    fm.action = "../bbs/BbsAction.do?cmd=BbsKList";
    fm.rowPerPage.value= obj.value;
    fm.nowPage.value="1";
    
    fm.submit();
}

/***************************************************************************
* Title   : 수정 
* Content : 선택된 글 수정 처리를 한다.  
****************************************************************************/
function fn_Write01(cmd){
    var fm = document.fmParam;
    
    fm.action = "../bbs/BbsAction.do?cmd=" + cmd;
    
    fm.submit();
}

/***************************************************************************
* Title   : 상세보기 
* Content : 선택된 글 상세보기 처리를 한다.  
****************************************************************************/
function viewDetail(BOARD_SEQ) {
    var fm = document.fmParam;
    
    fm.action = "../bbs/BbsAction.do?cmd=BbsKView";
    fm.BOARD_SEQ.value= BOARD_SEQ;    
    
    fm.submit();
}

/***************************************************************************
* Title   : 검색  
* Content : 등록된 글 검색 처리를 한다. 
****************************************************************************/
function fn_search() {
    var fm = document.fmParam;
    
    fm.action = "../bbs/BbsAction.do?cmd=BbsKList";
    fm.nowPage.value = 1;
    fm.submit();
}

/***************************************************************************
* Title   : 시.군.구 코드를 검색한다   
* Content : 시.군.구 코드 Ajax검색 처리를 한다. 
****************************************************************************/
function fn_changeSGG_CD() {

	var fm = document.fmParam;
	
	if (fm.scSD_CD.value.trim() == "") {
		document.getElementById("layer_sggcd").innerHTML = 
			"<select name='scSGG_CD' class='input_bbs' > " +
			" 	<option value='ALL'  selected > 시.군.구 선택 </option> " +
			" </select> ";
		
	} else {		
		var scSD_CD = fm.scSD_CD.value;

		sendRequest("../bbs/BbsAction.do", "cmd=BbsLSggCd&scSD_CD=" + scSD_CD, fn_SggCodeView, "POST");
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
* Title   : FAQ
* Content : FAQ 화면 처리 한다. 
****************************************************************************/
var preNum;
var flag=1;
function changeLayer(sNum)
{
	if(sNum==preNum && flag==1){
		//alert('a');
		temp = eval("document.all."+sNum);
		if(temp){
			temp.style.display='none';
			flag=0;
		}
	}
	else if(sNum==preNum && flag==0){
		//alert('b');
		temp = eval("document.all."+sNum);
		if(temp){
			temp.style.display='';
			flag=1;
		}
	}
	else if(sNum!=preNum){
		//alert('c');
		temp = eval("document.all."+preNum);
		if(temp)
			temp.style.display='none';

		temp1 = eval("document.all."+sNum);
		if(temp1)
			temp1.style.display='';
		flag=1;


	}
	preNum=sNum;
}
/***************************************************************************
* Title   : 질의회신 코드를 검색한다   
* Content : 질의회신 소분류 코드 Ajax검색 처리를 한다. 
****************************************************************************/
function fn_changeFAQ_CD() {

	var fm = document.fmParam;

	if (fm.scFAQ_L_CATE.value == "ALL") {
		document.getElementById("layer_faqcd").innerHTML = 
			"<select name='scFAQ_S_CATE' class='input_bbs' > " +
			" 	<option value='ALL'  selected > 소분류 선택 </option> " +
			" </select> ";
		
	} else {		
		var FAQ_L_CATE = fm.scFAQ_L_CATE.value;

		sendRequest("../bbs/BbsAction.do", "cmd=BbsLFAQCd&ScFAQ_L_CATE=" + FAQ_L_CATE, fn_FAQCodeView, "POST");
	}
}

/***************************************************************************
* Title   : 질의회신 코드를 검색한다   
* Content : 질의회신 소분류 코드 Ajax검색 리스트 화면  처리를 한다. 
****************************************************************************/
function fn_FAQCodeView() {
	document.getElementById("layer_faqcd").innerHTML = httpRequest.responseText;
}
/***************************************************************************
* Title   : FAQ 쓰기  
* Content : 작성된 게시글을 등록 처리한다. 
****************************************************************************/
function fn_Write(mode, board_seq) {
    var fm = document.fmParam;
    
    fm.action = "../bbs/BbsAction.do?cmd=BbsKWrite";
    fm.mode.value      = mode;
    fm.BOARD_SEQ.value = board_seq;
  
    fm.submit();
}

/***************************************************************************
* Title   : FAQ 삭제 
* Content : 작성된 게시글을 삭제 처리한다. 
****************************************************************************/
function fn_delete(board_seq) {
	var fm = document.fmParam;
	
	fm.action = "../bbs/BbsAction.do?cmd=BbsKD";
	fm.BOARD_SEQ.value = board_seq;

    if (!confirm("선택된 항목을 삭제 하시겠습니까?\n삭제된 게시물은 영구히 삭제됩니다.")) {
        return;
    }

    fm.submit();
}