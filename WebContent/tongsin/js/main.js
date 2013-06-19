function go_bbs(ct_id) {	
	location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=" + ct_id;
}

function go_bbs_view(ct_id, board_seq) {
	location.href="../bbs/BbsAction.do?cmd=BbsKView&CT_ID=" + ct_id + "&BOARD_SEQ=" + board_seq;
}	
	