<%@ page contentType="text/html;charset=utf-8"%>
<%//@ page errorPage="../com/error.jsp" %>
<%@ page import="java.net.*" %>
<%@ page import="java.util.*" %>
<%@ page import="kjf.ops.*" %>
<%@ page import="kjf.util.*" %>
<%@ page import="sp.bbs.*" %>
<%@ page import="com.fredck.FCKeditor.*" %>
<%@ taglib uri="/KJFTags" prefix="KTags" %>
<%@page import="devpia.dextupload.*"%>

<%
	//파일의 업로드 진행 상태를 나타내기위한 부분
	int nID = Progress.GetProgressID();
	Integer ID = new Integer(nID);
	String ProgressID = ID.toString();
%>

<%
	// 초기 변수 들 선언
	StatusEnt status = (StatusEnt)session.getAttribute("status");

	BbsParam pm = (BbsParam)request.getAttribute("pm");
	
	// 페이징 관련(필수)
	String nowPage    = KJFUtil.print(pm.getNowPage());
	String rowPerPage = KJFUtil.print(pm.getRowPerPage());
	
	ReportEntity rEntity    = KJFUtil.REntPrint( (ReportEntity)request.getAttribute("rEntity"));
	ReportEntity FileEntity = KJFUtil.REntPrint((ReportEntity)request.getAttribute("FileEntity"));

    String CT_ID   = status.getCT_ID();
    String URL     = status.getURL();    
	String bbsType = status.getTYPE();
	
    // 검색 조건절
    String scSD_CD		= KJFUtil.print(pm.getScSD_CD());
	String scSGG_CD     = KJFUtil.print(pm.getScSGG_CD());
    String scSLCT_GUBUN = KJFUtil.print(pm.getScSLCT_GUBUN());
    String scTEXTVALUE  = KJFUtil.print(pm.getScTEXTVALUE());
    
    String scFAQ_L_CATE	= KJFUtil.print(pm.getScFAQ_L_CATE(),KJFUtil.print(rEntity.getValue(0,"ETC_2"),"ALL"));
    String scFAQ_S_CATE	= KJFUtil.print(pm.getScFAQ_S_CATE(),KJFUtil.print(rEntity.getValue(0,"ETC_3"),"ALL"));
    
    // 신규, 수정 답변 구분
    String mode	= KJFUtil.print(request.getParameter("mode"), KJFUtil.print(request.getAttribute("mode"), "new"));    

    String cmd	= "BbsKC";    
    if(mode.equals("modify")) cmd = "BbsKU";
    if(mode.equals("reply"))  cmd = "BbsKR";

    // 타이틀 표시 여부
	boolean isTitleDate = status.isTITLE_DATE_YN();
	boolean isTitleHit 	= status.isTITLE_HIT_YN();
	boolean isTitleIp 	= status.isTITLE_IP_YN();

	String maxFileUpSize = Integer.toString(status.getFILE_SIZE());

	// 추가 필드 처리
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
	
	String SIDO_CODE    = status.getSIDO_CODE();
	String SIDO_NM    	= "";	
	String SIGUNGU_CODE = status.getSIGUNGU_CODE();
	String SIGUNGU_NM 	= "";
	
    String USER_NAME  = status.getUSER_NAME();
    String USER_EMAIL = status.getUSER_EMAIL();
    String USER_TEL   = status.getUSER_Tel();
    String SUBJECT    = "";
    String CONTENT    = "";
    String NOTICE_YN  = "";
    String SECRET_YN  = "";
    String NOGUBUN    = "";
	String ETC_1      = "";	
	String ETC_2      = "";	
	String ETC_3      = "";  
	String ETC_4      = "";	
	String ETC_5      = "";	
		
	boolean REPLY_ENABLED	= false;
	
    if (mode.equals("modify")) {
        
      SIDO_CODE	 = rEntity.getValue(0, "SIDO_CODE");
      SIGUNGU_CODE = rEntity.getValue(0, "SIGUNGU_CODE");	
        
    	USER_NAME    = rEntity.getValue(0, "USER_NAME");
    	USER_EMAIL   = rEntity.getValue(0, "USER_EMAIL");
    	USER_TEL     = rEntity.getValue(0, "USER_TEL");

    	SUBJECT      = rEntity.getValue(0, "SUBJECT");
    	CONTENT      = rEntity.getValue(0, "CONTENT");
    	NOTICE_YN    = rEntity.getValue(0, "NOTICE_YN");
    	SECRET_YN    = rEntity.getValue(0, "SECRET_YN");
    	
    } else if (mode.equals("reply")) {
    	SUBJECT      = "RE :" + rEntity.getValue(0,"SUBJECT");
    	SIDO_CODE	 = rEntity.getValue(0, "SIDO_CODE").trim();
    	SIDO_NM	 	 = rEntity.getValue(0, "SIDO_NM");
    	SIGUNGU_CODE = rEntity.getValue(0, "SIGUNGU_CODE").trim();
    	SIGUNGU_NM 	 = rEntity.getValue(0, "SIGUNGU_NM");    	
      SECRET_YN    = rEntity.getValue(0, "SECRET_YN");
      REPLY_ENABLED = true;
        
    }
%>

<SCRIPT language="JavaScript" type="text/javascript">
<!--

/***************************************************************************
* Title   : 저장 
* Content : 작성된 게시글을 저장 처리한다.  
****************************************************************************/
function fn_save(cmd) {
    var fm = document.fmWrite;

    fm.action = "../bbs/BbsAction.do?cmd=" + cmd;
    
	<%if (status.getATT_NUM() > 0) { // 파일 첨부 갯수가 0개 이상이면  나타낸다.  %>
    		fm.encoding  ="multipart/form-data";
	<%}%>	

	<% if(status.isSD_DIV_YN()) { %>
		if (fm.SIDO_CODE.value.trim() == "") {
			alert("시.도를 선택해 주세요!");
			fm.SIDO_CODE.focus();
			return;
		}
	<% } %>

	<% if(status.isSGG_DIV_YN()) { %>
		if (fm.SIGUNGU_CODE.value.trim() == "") {
			alert("시.군.구를 선택해 주세요!");
			fm.SIGUNGU_CODE.focus();
			return;
		}
	<% } %>

	<%	if("faq_basic_pub".equals(status.getTYPE())){ %>
		if (fm.scFAQ_L_CATE.value.trim() == "ALL"){
			alert("대분류를  선택해 주세요!");
			fm.scFAQ_L_CATE.focus();
			return;
		}
		if (fm.scFAQ_S_CATE.value.trim() == "ALL"){
			alert("소분류를  선택해 주세요!");
			fm.scFAQ_S_CATE.focus();
			return;
		}
		fm.ETC_2.value = fm.scFAQ_L_CATE.value;
		fm.ETC_3.value = fm.scFAQ_S_CATE.value;
	<%	}%>
	
    kjsSubmit('fmWrite');
}


/***************************************************************************
* Title   : 저장 
* Content : 작성된 게시글을 저장 처리한다.  
****************************************************************************/
function fn_dxtsave(cmd) {

	
		var fm = document.fmWrite;
	    fm.action = "../bbs/DextUpload.jsp?<%=ProgressID%>";

		fm.cmd.value=cmd;
	    
		<%if (status.getATT_NUM() > 0) { // 파일 첨부 갯수가 0개 이상이면  나타낸다.  %>
	    		fm.encoding  ="multipart/form-data";
		<%}%>	

		<% if(status.isSD_DIV_YN()) { %>
			if (fm.SIDO_CODE.value.trim() == "") {
				alert("시.도를 선택해 주세요!");
				fm.SIDO_CODE.focus();
				return;
			}
		<% } %>

		<% if(status.isSGG_DIV_YN()) { %>
			if (fm.SIGUNGU_CODE.value.trim() == "") {
				alert("시.군.구를 선택해 주세요!");
				fm.SIGUNGU_CODE.focus();
				return;
			}
		<% } %>

		<%	if("faq_basic_pub".equals(status.getTYPE())){ %>
			if (fm.scFAQ_L_CATE.value.trim() == "ALL"){
				alert("대분류를  선택해 주세요!");
				fm.scFAQ_L_CATE.focus();
				return;
			}
			if (fm.scFAQ_S_CATE.value.trim() == "ALL"){
				alert("소분류를  선택해 주세요!");
				fm.scFAQ_S_CATE.focus();
				return;
			}
			fm.ETC_2.value = fm.scFAQ_L_CATE.value;
			fm.ETC_3.value = fm.scFAQ_S_CATE.value;
		<%	}%>


		if(fm.attachYn.value == "Y") {
			ShowProgress();
		}
		
	    kjsSubmit('fmWrite');
	
}

function ChkFile(FileObj) {

	var fm = document.fmWrite;
	if (FileObj.value != "") { 
		fm.attachYn.value = "Y";
		
	} else { 
		fm.attachYn.value = "N";
	} 
/*
	var fsize = 0;
	var tmp_fsize = 0;
	var fileIndex = 0;

	for(i = 0;i < fm.elements.length;i++){
		if(fm.elements[i].type == "file" && fm.elements[i].name == "BBS_FILE"){
			fileIndex++;
		}
	}

	if(fileIndex == 1) {
		fname = fm.BBS_FILE.value;
	} else {
		fname = fm.BBS_FILE[fileIndex-1].value;
	}

	tmp_fsize = parseInt(FileCheckSize.FileSize(fname));

	fsize =  Number(fm.fileSize.value)*1024*1024 + Number(tmp_fsize);

	if(fileIndex == 1) {
		//fsize = tmp_fsize;
	}
	
	document.getElementById('nowSize').innerHTML = fn_fileSize(fsize);
	fm.fileSize.value = fn_SizeUpdate(fsize);  //MB 로 변환
	fm.fileViewSize.value = fn_fileSize(fsize);
*/
}

//파일 사이즈를 각 단위별로 반환.
function fn_SizeUpdate(fSize){
	var str = "";
	str = fn_round(fSize/1024/1024 ,2);
	return str;
}

//소숫점 반올림 표시
function fn_round(num, pos) {
	var posV = Math.pow(10, (pos ? pos : 2))
	return Math.round(num*posV)/posV
}

//파일 사이즈를 각 단위별로 반환.
function fn_fileSize(fSize){
	var str = "";

	if(fSize>1048576) str = fn_round(fSize/1024/1024 ,2) + " MB";
	else if(fSize>1024) str = fn_round(fSize/1024 ,2) + " KB";
	else str = (fSize) + " B";

	return str;
}

/***************************************************************************
* Title   : 목록 
* Content : 리스트 목록으로 처리한다.
****************************************************************************/
function fn_goList01(cmd) {
    var fm = document.fmWrite;
    
    fm.action = "../bbs/BbsAction.do?cmd=" + cmd;
	
	<%if (status.getATT_NUM() > 0) { // 파일 첨부 갯수가 0개 이상이면  나타낸다.  %>
    	fm.encoding  ="multipart/form-data";
	<%}%>
    
    fm.submit();
}

/***************************************************************************
* Title   : 첨부파일 
* Content : 첨부된 파일을 삭제 처리한다.   
****************************************************************************/
function fn_att_del(SYS_FILE_NAME) {
    var fm = document.fmWrite;

    fm.SYS_FILE_NAME.value = SYS_FILE_NAME;
    
    fm.action = "../bbs/BbsAction.do?cmd=BbsKFileDel";
	
	<%if( status.getATT_NUM() > 0 ){ //파일 첨부 갯수가 0개 이상이면  나타낸다.  %>
    	fm.encoding  ="multipart/form-data";
 	<%}%>
 	
 	if (confirm("선택한 파일을 삭제하시겠습니까?")) {
    	fm.method = "post";
    	fm.submit();
    }
}

//파일 첨부할때 필드 추가 제거 하는 스크립트 시작
var rowIndex = 1;

/***************************************************************************
* Title   : 피일 입력폼 추가  
* Content : 첨부파일 입력폼을  추가 처리한다.   
****************************************************************************/
function addFile(form, max, k) {
	
	if (rowIndex > (max - k)) return false;

	var row = document.getElementById("attachFile").insertRow(-1); 
    var cell = row.insertCell(-1); 

    cell.innerHTML = "<tr><td><input type='file' class='file' name='BBS_FILE' size='50' onchange='ChkFile(this);' ></td></tr>"; 
    rowIndex++; 
	form.fileCnt.value = rowIndex;
}

/***************************************************************************
* Title   : 파일 입력폼 빼기  
* Content : 첨부파일 입력폼을 삭제 처리한다.   
****************************************************************************/
function deleteFile(form) {

	
	if (rowIndex < 2) {
		return false;
		
	} else {
		document.getElementById("attachFile").deleteRow(-1);		
		rowIndex--;
	}
	form.fileCnt.value = rowIndex;

	
	//fn_fileViewResize();
	
}
// 파일 첨부할때 필드 추가 제거 하는 스크립트 끝

function fn_fileViewResize() {

	var fm = document.fmWrite;
	
	var fsize = 0;
	var tmp_fsize = 0;
	var fileIndex = 0;

	for(i = 0;i < fm.elements.length;i++){
		if(fm.elements[i].type == "file" && fm.elements[i].name == "BBS_FILE"){
			fileIndex++;
		}
	}

	for(i = 0;i < fileIndex;i++){

		if(fileIndex == 1) {
			fname = fm.BBS_FILE.value;
		} else {
			fname = fm.BBS_FILE[i].value;
		}
		

		tmp_fsize = parseInt(FileCheckSize.FileSize(fname));

		fsize =  Number(fsize)+ Number(tmp_fsize);
	}
	
	document.getElementById('nowSize').innerHTML = fn_fileSize(fsize);

	fm.fileSize.value = fn_SizeUpdate(fsize);  //MB 로 변환

	fm.fileViewSize.value = fn_fileSize(fsize);

	//firstFile++;
	
}

/***************************************************************************
* Title   : 시.군.구 코드를 검색한다   
* Content : 시.군.구 코드 Ajax검색 처리를 한다. 
****************************************************************************/
function fn_changeSGG_CD() {

	var fm = document.fmWrite;
	
	if (fm.SIDO_CODE.value == "") {
		document.getElementById("layer_sggcd").innerHTML = 
			"<select name='SIGUNGU_CODE' class='input_bbs' > " +
			" 	<option value=''  selected > 시.군.구 선택 </option> " +
			" </select> ";
		
	} else {		
		var SIDO_CODE = fm.SIDO_CODE.value;

		sendRequest("../bbs/BbsAction.do", "cmd=BbsWSggCd&scSD_CD=" + SIDO_CODE, fn_SggCodeView, "POST");
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
* Title   : 질의회신 코드를 검색한다   
* Content : 질의회신 소분류 코드 Ajax검색 처리를 한다. 
****************************************************************************/
function fn_changeFAQ_CD() {

	var fm = document.fmWrite;

	if (fm.scFAQ_L_CATE.value == "ALL") {
		document.getElementById("layer_faqcd").innerHTML = 
			"<select name='scFAQ_S_CATE' class='input_bbs' > " +
			" 	<option value='ALL'  selected > 소분류 선택 </option> " +
			" </select> ";
		
	} else {		
		var FAQ_L_CATE = fm.scFAQ_L_CATE.value;

		sendRequest("../bbs/BbsAction.do", "cmd=BbsWFAQCd&ScFAQ_L_CATE=" + FAQ_L_CATE, fn_FAQCodeView, "POST");
	}
}

/***************************************************************************
* Title   : 질의회신 코드를 검색한다   
* Content : 질의회신 소분류 코드 Ajax검색 리스트 화면  처리를 한다. 
****************************************************************************/
function fn_FAQCodeView() {
	document.getElementById("layer_faqcd").innerHTML = httpRequest.responseText;
}

//-->
</SCRIPT>

<SCRIPT language="javascript">
function ShowProgress() {
			strAppVersion = navigator.appVersion;

			winpos = "left=" + ((window.screen.width-380)/2) + ",top=" + ((window.screen.height-110)/2);
			winstyle="width=600,height=350,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=no,copyhistory=no," + winpos;
			window.open("ProgressBar.jsp?<%=ProgressID%>",null,winstyle);	
					
			//if (strAppVersion.indexOf('MSIE') != -1 && strAppVersion.substr(strAppVersion.indexOf('MSIE')+5,1) > 4) {
			//	winstyle = "dialogWidth=600px; dialogHeight:350px; center:yes";
			//	window.showModelessDialog("ProgressBar.jsp?<%=ProgressID%>",null,winstyle);	  
			//}
			//else {
			//	winpos = "left=" + ((window.screen.width-380)/2) + ",top=" + ((window.screen.height-110)/2);
			//	winstyle="width=600,height=350,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=no,copyhistory=no," + winpos;
			//	window.open("ProgressBar.jsp?<%=ProgressID%>",null,winstyle);	     
			//}
					
		return true; 
	}
</SCRIPT>

<!-- 타이틀이 이보다 외부에 있어서 타이틀 값 전송 안됨 -->
<form name="fmWrite" method="post" >
<input type=hidden name="cmd" value="">
<input type=hidden name="attachYn" value="">

<!-- 
<input type=hidden name="fileSize" value="">
<input type=hidden name="fileViewSize" value="">
 -->
 
<!-- 페이징 관련(필수)-->
<input type=hidden name="nowPage"    value="<%=nowPage%>">
<input type=hidden name="rowPerPage" value="<%=rowPerPage%>">

<!-- 게시판 기본 정보-->
<input type=hidden name="URL"       value="<%=URL%>">
<input type=hidden name="CT_ID"     value="<%=CT_ID%>">
<input type=hidden name="BOARD_SEQ" value="<%=rEntity.getValue(0,"BOARD_SEQ") %>">
<input type=hidden name="INDEX1"    value="<%=rEntity.getValue(0,"INDEX1") %>">
<input type=hidden name="INDEX2"    value="<%=rEntity.getValue(0,"INDEX2") %>">
<input type=hidden name="DEPTH"     value="<%=rEntity.getValue(0,"DEPTH") %>">

<!-- 신규, 수정 답변 구분-->
<input type=hidden name="mode" value="<%=mode%>">

<!-- 첨부파일 삭제용-->
<input type=hidden name="SYS_FILE_NAME">

<!-- 검색 조건절-->
<input type=hidden name="scSLCT_GUBUN" value="<%=scSLCT_GUBUN%>">
<input type=hidden name="scTEXTVALUE"  value="<%=scTEXTVALUE%>">
<input type=hidden name="scSD_CD"      value="<%=scSD_CD%>">
<input type=hidden name="scSGG_CD"     value="<%=scSGG_CD%>">

<!-- ============================ 게시판 스킨 START ========================== -->

	<!-- 일반 게시판  -->
	<% if(bbsType.equals("general_basic")) { %>
	<jsp:directive.include file="skin/general_basic/write.jsp" />

	<!-- FAQ 게시판  -->
	<% } else if(bbsType.equals("faq_basic")) { %>
	<jsp:directive.include file="skin/faq_basic/write.jsp" />

	<!-- 질의회신 게시판 -->
	<% } else if(bbsType.equals("faq_basic_pub")) { %>
	<jsp:directive.include file="skin/faq_basic_pub/write.jsp" />
	
	<!-- QNA 게시판 -->
	<% } else if(bbsType.equals("qna_basic")) { %>
	<jsp:directive.include file="skin/qna_basic/write.jsp" />

	<!-- 자료실 게시판  -->
	<% } else if(bbsType.equals("morgue_basic")) { %>
	<jsp:directive.include file="skin/morgue_basic/write.jsp" />
	<% } %>

<!-- ============================ 게시판 스킨 END ============================ -->

</form>
<!-- 
<OBJECT ID="FileCheckSize"
	CLASSID="CLSID:4EEA9D91-8F19-45A2-9D24-6E462F3F3C5D"
	CODEBASE="../com/cab/FileSizeCheck.cab#version=1,0,0,1"
	width="0"
	height="0">
</OBJECT>
 -->