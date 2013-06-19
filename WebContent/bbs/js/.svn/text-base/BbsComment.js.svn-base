//댓글 수정 플래그
var isCommentMod = true;
var commentTemp;

/***************************************************************************
* Title   : 댓글 저장
* Content : 댓글 저장 처리를 한다.  
****************************************************************************/
function fn_comment_save(){
    var fm = document.ViewParam;

    fm.action = "../bbs/BbsAction.do?cmd=BbsKCC";
    kjsSubmit('ViewParam');
}

/***************************************************************************
* Title   : 댓글 삭제
* Content : 댓글 삭제 처리를 한다.  
****************************************************************************/
function fn_comment_del(seq){
    var fm = document.ViewParam;
    fm.action = "../bbs/BbsAction.do?cmd=BbsKCD&COMMENT_SEQ=" + seq;
    
    if (!confirm("한줄답변을 삭제 하시겠습니까?")){
        return;
    }

    fm.submit();
}

/***************************************************************************
* Title   : 댓글 수정
* Content : 댓글 삭제 처리를 한다.  
****************************************************************************/
function fn_comment_modify(seq) {

	if (isCommentMod) {

		commentTemp = document.getElementById("Comment_" + seq).innerHTML.replaceAll("<BR>", "\n");

		var writeHTML =
			"	<table width='100%' border='0'> " +
			"		<tr> " +
			"			<td> " +
			"    			<textarea cols=30 name=COMMENT_SUB rows=3 " +
			"                       onkeyup='javascript:modifyCommentCK(200);' " +
			"						style='BORDER-BOTTOM-COLOR: #448FD0; BORDER-LEFT-COLOR: #448FD0; " +
			"							BORDER-RIGHT-COLOR: #448FD0; BORDER-TOP-COLOR: #448FD0; width:310px; overflow:auto; background-color: #EFF5FB'>" + commentTemp + "</textarea> " +
			" 			</td> " +
			"			<td > " +
			"				<img src='../images/bbs/btn_comment_save.gif' border='0' style='cursor:hand;' alt='수정' onclick='fn_comment_modify_save(\"" + seq + "\")'><img src='../images/bbs/btn_comment_cancel.gif' border='0' style='cursor:hand;' alt='취소' onclick='fn_comment_cancel(\"" + seq + "\")'><br><br>" +
			"				<input type=text class=box style=width:25 name='cmtcount' value='0' readonly><img width='3'>byte" +
			"			</td> " +
			"		</tr> " +
			"	</table> ";

		document.getElementById("Comment_" + seq).innerHTML = writeHTML;

		isCommentMod = false;

	} else {
		alert("수정중인 작업이 있습니다.!");
	}
}

/***************************************************************************
* Title   : 댓글 수정 저장
* Content : 댓글 수정 저장 처리를 한다.  
****************************************************************************/
function fn_comment_modify_save(seq) {
	var fm = document.ViewParam;
	fm.action = "../bbs/BbsAction.do?cmd=BbsKCU&COMMENT_SEQ=" + seq;
	
    if (!confirm("한줄답변을 수정 하시겠습니까?")){
        return;
    }

    fm.submit();
}

/***************************************************************************
* Title   : 댓글수정 취소
* Content : 댓글수정 취소 처리를 한다.  
****************************************************************************/
function fn_comment_cancel(seq) {

	document.getElementById("Comment_" + seq).innerHTML = commentTemp;

	isCommentMod = true;
}


/***************************************************************************
* Title   : 댓글 길이 체크
* Content : 댓글 길이를 구한다.
****************************************************************************/
function commentCK(length) {
	var frm = document.ViewParam;

    if (!checkLengthKor(frm.COMMENT_MEMO, 0, length, length + " bytes를 넘을 수 없습니다.", true))
    {
        frm.COMMENT_MEMO.value = substringKor2(frm.COMMENT_MEMO.value, length);
    }

    frm.commentcount.value = strLenCk(frm.COMMENT_MEMO.value);
}

/***************************************************************************
* Title   : 수정댓글 길이 체크
* Content : 수정 처리된 댓글의 길이를 구한다.
****************************************************************************/
function modifyCommentCK(length) {
	var frm = document.ViewParam;

    if (!checkLengthKor(frm.COMMENT_SUB, 0, length, length + " bytes를 넘을 수 없습니다.", true))
    {
        frm.COMMENT_SUB.value = substringKor2(frm.COMMENT_SUB.value, length);
    }

    frm.cmtcount.value = strLenCk(frm.COMMENT_SUB.value);
}