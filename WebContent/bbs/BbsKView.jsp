<%@ page contentType="text/html;charset=utf-8"%>
<%//@ page errorPage="../com/error.jsp" %>
<%@ page import="java.net.*" %>
<%@ page import="kjf.ops.*" %>
<%@ page import="kjf.util.*" %>
<%@ page import="sp.bbs.*" %>
<%@ taglib uri="/KJFTags" prefix="KTags" %>

<%
	// 초기 변수 들 선언
	StatusEnt status = (StatusEnt)session.getAttribute("status");

	BbsParam pm = (BbsParam)request.getAttribute("pm");
	
	ReportEntity  rEntity    = KJFUtil.REntPrint((ReportEntity)request.getAttribute("rEntity"));
	ReportEntity  FileEntity = KJFUtil.REntPrint((ReportEntity)request.getAttribute("FileEntity"));
	ReportEntity  cEntity    = KJFUtil.REntPrint((ReportEntity)request.getAttribute("cEntity")); 		// 댓글 가져오기
	
	String CT_ID   = status.getCT_ID();
    String URL     = status.getURL();    
	String bbsType = status.getTYPE();
	
	String scSD_CD		= KJFUtil.print(pm.getScSD_CD());
	String scSGG_CD     = KJFUtil.print(pm.getScSGG_CD());
    String scSLCT_GUBUN = KJFUtil.print(pm.getScSLCT_GUBUN());
    String scTEXTVALUE  = KJFUtil.print(pm.getScTEXTVALUE());

	// 타이틀 표시 여부
	boolean isTitleDate = status.isTITLE_DATE_YN();
	boolean isTitleHit  = status.isTITLE_HIT_YN();
	boolean isTitleIp   = status.isTITLE_IP_YN();

	// 버튼 사용 여부
  	boolean IS_MODIFY   = false;
 	boolean IS_DELETE   = false; 	

    //추가 필드 처리
  	String SUBJ_1 = "";
	String SUBJ_2 = "";
	String SUBJ_3 = "";
	String SUBJ_4 = "";
	String SUBJ_5 = "";
	String SUBJ_6 = "";
	String SUBJ_7 = "";
	String SUBJ_8 = "";
	String SUBJ_9 = "";
	String SUBJ_10 = "";

	if(!KJFUtil.isEmpty(status.getSUBJ_1())) SUBJ_1 = status.getSUBJ_1();
	if(!KJFUtil.isEmpty(status.getSUBJ_2())) SUBJ_2 = status.getSUBJ_2();
	if(!KJFUtil.isEmpty(status.getSUBJ_3())) SUBJ_3 = status.getSUBJ_3();
	if(!KJFUtil.isEmpty(status.getSUBJ_4())) SUBJ_4 = status.getSUBJ_4();
	if(!KJFUtil.isEmpty(status.getSUBJ_5())) SUBJ_5 = status.getSUBJ_5();
	if(!KJFUtil.isEmpty(status.getSUBJ_6())) SUBJ_6 = status.getSUBJ_6();
	if(!KJFUtil.isEmpty(status.getSUBJ_7())) SUBJ_7 = status.getSUBJ_7();
	if(!KJFUtil.isEmpty(status.getSUBJ_8())) SUBJ_8 = status.getSUBJ_8();
	if(!KJFUtil.isEmpty(status.getSUBJ_9())) SUBJ_9 = status.getSUBJ_9();
	if(!KJFUtil.isEmpty(status.getSUBJ_10())) SUBJ_10 = status.getSUBJ_10();	

  	String status_user_id = KJFUtil.print(status.getUSER_ID());
  	
    // 관리자 , 쓰기 허용 일때만 버튼 생성
	if ( status.isADMIN() || status.isWRITE() ) {
		
		// 관리자 , 자신의 id인 글일때만 버튼 생성
		if( status.isADMIN() || status_user_id.equals(rEntity.getValue(0,"USER_ID")) ) {

			IS_MODIFY = true;
			IS_DELETE = true;
		 }
	}
    
	// 페이징 관련(필수)
	String nowPage    = KJFUtil.print(pm.getNowPage());
	String rowPerPage = KJFUtil.print(pm.getRowPerPage());

%>

<script language="JavaScript" src="../bbs/js/BbsComment.js" type="text/javascript"></script>
<script language="JavaScript" src="../bbs/js/BbsKView.js" type="text/javascript"></script>
<script language="JavaScript" src="../bbs/js/community.js" type="text/javascript"></script>
<script language="JavaScript" type="text/javascript">

	//코멘트 저장
	function fn_comment_save(){
		var fm = document.ViewParam;
	    <%
	    	if (status.isLOGIN()){
	    		out.print("fm.action='../bbs/BbsAction.do?cmd=BbsKCC';");
	    		out.print("fm.submit();");
	    		//out.print("kjsSubmit('fmParam');");
	    	} else {
	    		out.print("alert('로그인을 해 주십시오');");
	    		out.print("location.href='../login/LoginAction.do?cmd=LogOut';");
	    	}
	    %>
	}
	
	// 코멘트 삭제
	function fn_comment_del(seq){
	    var fm = document.ViewParam;
	    fm.action = "../bbs/BbsAction.do?cmd=BbsKDC&COMMENT_SEQ="+seq;
	     if (!confirm("한줄답변을 삭제 하시겠습니까?")){
	        return;
	    }
	    fm.submit();
	}
	
	// 댓글 수정 플래그
	var isCommentMod = true;
	var commentTemp;
	// 댓글수정 취소
	function fn_comment_cancel(num) {
		document.getElementById("Comment_" + num).innerHTML = commentTemp;
		isCommentMod = true;
	}
	
	// 댓글 수정
	function fn_comment_modify(seq, num) {
		if (isCommentMod) {
			commentTemp = document.getElementById("Comment_" + num).innerHTML;
			var writeHTML =
				"	<table width='450px' border='0'> " +
				"		<tr> " +
				"			<td width='70%'> " +
				"    			<textarea cols=30 name=COMMET_SUB rows=8 " +
				"                       onkeyup='javascript:modifyCommentCK(1000);' " +
				"						style='BORDER-BOTTOM-COLOR: #448FD0; BORDER-LEFT-COLOR: #448FD0; " +
				"							BORDER-RIGHT-COLOR: #448FD0; BORDER-TOP-COLOR: #448FD0; width:500px; overflow:auto; background-color: #EFF5FB'>" + commentTemp + "</textarea> " +
				" 			</td> " +
				"			<td width='30%'> " +
				"				<img src='../images/bbs/btn_comment_save.gif' border='0' style='cursor:pointer;' alt='수정' onclick='fn_comment_modify_save(\"" + seq + "\")'><img src='../images/bbs/btn_comment_cancel.gif' border='0' style='cursor:pointer;' alt='취소' onclick='fn_comment_cancel(\"" + num + "\")'><br><br>" +
				"				<input type=text class=box style=width:25 name='cmtcount' value='0' readonly><img width='3'>byte" +
				"			</td> " +
				"		</tr> " +
				"	</table> ";
			document.getElementById("Comment_" + num).innerHTML = writeHTML;
			isCommentMod = false;
		} else {
			alert("수정중인 작업이 있습니다.!");
		}
	}
	
	// 댓글 수정 저장
	function fn_comment_modify_save(seq) {
		var fm = document.ViewParam;
		fm.action = "../bbs/BbsAction.do?cmd=BbsKCU&COMMENT_SEQ=" + seq;
	     if (!confirm("한줄답변을 수정 하시겠습니까?")){
	        return;
	    }
	    fm.submit();
	}
	
	//댓글 길이를 구한다.
	function commentCK(length) {
		var frm = document.ViewParam;
	    if (!checkLengthKor(frm.COMMET, 0, length, length + " bytes를 넘을 수 없습니다.", true))
	    {
	        frm.COMMET.value = substringKor2(frm.COMMET.value, length);
	    }
	    frm.commentcount.value = strLenCk(frm.COMMET.value);
	    var m=200; var s=this.scrollHeight; if(s>=m)this.style.pixelHeight=s+4;
	}
	
	// 수정댓글 길이를 구한다.
	function modifyCommentCK(length) {
		var frm = document.ViewParam;
	    if (!checkLengthKor(frm.COMMET_SUB, 0, length, length + " bytes를 넘을 수 없습니다.", true))
	    {
	        frm.COMMET_SUB.value = substringKor2(frm.COMMET_SUB.value, length);
	    }
	    frm.cmtcount.value = strLenCk(frm.COMMET_SUB.value);
	}

	function Click(n) {
		var img;
		img=document.getElementById('bt' + n);
		if(Click_SUB(n) == true)
			img.src ='../images/bbs/btn_hide.gif';
		else
			img.src ='../images/bbs/btn_show.gif';
	}
	function Click_SUB(n) {
		var viewloop = document.getElementById('sub' + n);
		var img = document.getElementById('bt' + n);
		if (viewloop.style.display == 'none') {
			viewloop.style.display = '';
			img.alt='댓글 보이기';
			return true;
		} else {
			viewloop.style.display = 'none';
			img.alt='댓글 숨기기';
			return false;
		}
	}
</script>

<!-- 타이틀이 이보다 외부에 있어서 타이틀 값 전송 안됨 -->
<form name="ViewParam" method="post" >

<!-- 페이징 관련(필수)-->
<input type=hidden name="nowPage"    value="<%=nowPage%>">
<input type=hidden name="rowPerPage" value="<%=rowPerPage%>">

<!-- 게시판 기본 정보-->
<input type=hidden name="URL"       value="<%=URL%>">
<input type=hidden name="CT_ID"     value="<%=CT_ID%>">
<input type=hidden name="BOARD_SEQ" value="<%=rEntity.getValue(0,"BOARD_SEQ") %>">

<!-- 신규, 답변 구분-->
<input type=hidden name="mode">

<!-- 삭제용-->
<input type=hidden name="chk" value="0">

<!-- 검색 조건절-->
<input type=hidden name="scSLCT_GUBUN" value="<%=scSLCT_GUBUN%>">
<input type=hidden name="scTEXTVALUE"  value="<%=scTEXTVALUE%>">
<input type=hidden name="scSD_CD"      value="<%=scSD_CD%>">
<input type=hidden name="scSGG_CD"     value="<%=scSGG_CD%>">

	<!-- ========================= 게시판 스킨 START ======================== -->

	<!-- 일반 게시판  -->
	<% if (bbsType.equals("general_basic")) { %>
	<jsp:directive.include file="skin/general_basic/view.jsp" />

	<!-- FAQ 게시판 -->
	<% } else if(bbsType.equals("faq_basic")) { %>
	<jsp:directive.include file="skin/faq_basic/view.jsp" />

	<!-- 질의회신 게시판 -->
	<% } else if(bbsType.equals("faq_basic_pub")) { %>
	<jsp:directive.include file="skin/faq_basic_pub/view.jsp" />
	
	<!-- QNA 게시판 -->
	<% } else if(bbsType.equals("qna_basic")) { %>
	<jsp:directive.include file="skin/qna_basic/view.jsp" />


	<!-- 자료실 게시판  -->
	<% } else if(bbsType.equals("morgue_basic")) { %>
	<jsp:directive.include file="skin/morgue_basic/view.jsp" />
	<% } %>

	<!-- ========================== 게시판 스킨 END ========================= -->
</form>