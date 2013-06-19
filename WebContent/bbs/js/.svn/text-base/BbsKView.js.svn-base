/***************************************************************************
* Title   : 상세보기 
* Content : 선택된 글 상세보기 처리를 한다.  
****************************************************************************/
function viewDetail(BOARD_SEQ) {
    var fm = document.ViewParam;
    
    fm.action = "../bbs/BbsAction.do?cmd=BbsKView";
    fm.BOARD_SEQ.value= BOARD_SEQ;    
    
    fm.submit();
}

/***************************************************************************
* Title   : 쓰기  
* Content : 작성된 게시글을 등록 처리한다. 
****************************************************************************/
function fn_Write(mode) {
    var fm = document.ViewParam;
    
    fm.action = "../bbs/BbsAction.do?cmd=BbsKWrite";
    fm.mode.value=mode;
    fm.submit();//kjsSubmit('ViewParam'); 
}

/***************************************************************************
* Title   : 삭제 
* Content : 선택된 내용을 삭제  처리한다.
****************************************************************************/
function fn_del(){
    var fm = document.ViewParam;
    
    fm.action = "../bbs/BbsAction.do?cmd=BbsKD";

    if (!confirm("선택된 항목을 삭제 하시겠습니까?\n삭제된 게시물은 영구히 삭제됩니다.")) {
        return;
    }

    fm.submit();//kjsSubmit('ViewParam'); 
}
/***************************************************************************
* Title   : 답글  저장 
* Content : 답글 등록 처리한다. 
****************************************************************************/
function fn_reply() {
	var fm = document.ViewParam;

    fm.action = "../bbs/BbsAction.do?cmd=BbsKWrite";
    fm.mode.value = "reply";
    fm.submit();
}

/***************************************************************************
* Title   : 출력
* Content : 본문 내용 출력 처리한다.
****************************************************************************/
function fn_print(){
	//centerwin('../com/pagePrint.jsp','printwin', '660', '500', '1');
	printwin = window.open("../com/pagePrint.jsp","print","left=10px;top=10px;height=500,width=660,scrollbars=yes,toolbar=yes,menubar=yes");
	printwin.focus();
}

/***************************************************************************
* Title   : 목록 
* Content : 리스트 목록으로 처리한다.
****************************************************************************/
function fn_goList(cmd){
    var fm= document.ViewParam;
    fm.action = "../bbs/BbsAction.do?cmd="+cmd;
    fm.method = "post";
    
    fm.submit();
}

/***************************************************************************
* Title   : 패스워드  
* Content : 패스워드 입력창  처리한다.
****************************************************************************/
function showPassForm(value) {
	var fm = document.ViewParam;

	if (value == 'confirmPassModify') {
		fn_Write('modify');
	} else if(value == 'confirmPassDelete') {
		fn_del();
	}
}

/***************************************************************************
* Title   : 이미지 크기지정  
* Content : 로딩시 이미지 크기를 지정한다. 
****************************************************************************/
function init_content_img(obj , width) {
	var imgInfo = new Image();

	imgInfo.src = obj.src;
	org_width = imgInfo.width;
	org_heigth = imgInfo.height;

	if (org_width > eval(width)) {
		rate=eval(width)/org_width;
		obj.width = eval(width);
		obj.height = org_heigth*rate;
	}
}

/***************************************************************************
* Title   : 이미지 팝업창  
* Content : 선택된 이미지를 팝업창으로 보여준다. 
****************************************************************************/
function big_img1(obj) {
    var imgInfo = new Image();
    
    imgInfo.src = obj.src;

	// 새창의 크기
	if(imgInfo.width <= 800) {cw = imgInfo.width;}else {cw = 800;}
	if(imgInfo.height <= 600) {ch = imgInfo.height;}else {ch = 600;}

	// 스크린의 크기
	sw = screen.availWidth;
	sh = screen.availHeight;
	
	// 열 창의 포지션
	px = (sw-cw)/2;
	py = (sh-ch)/2;

	var imgsrc = obj.src;

	result = window.open('about:blank' , "big_img" , "scrollbars=no,resizable=yes, width=" + cw + " , height=" + ch + ",left=" + px + ",top=" + py + "");
	result.document.writeln("<body scroll='auto' leftmargin=0 topmargin=0><img src=" + imgsrc + " onClick=self.close() ></body>");
  	result.document.close();
}
