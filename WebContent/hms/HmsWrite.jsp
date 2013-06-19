<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="kjf.ops.*" %>
<%@ page import="kjf.util.*" %>
<%@ page import="sp.uent.*" %>
<%@ page import="com.fredck.FCKeditor.*" %>

<%
	ReportEntity rEntity = KJFUtil.REntPrint( (ReportEntity)request.getAttribute("rEntity") );

	UserEnt user = (UserEnt)request.getSession().getAttribute("user");
	
	String CONTENT = "";
	String URL     = "";
	String VERSION = "";
	String MENU_ID = "";
	
	if (rEntity.getRowCnt() > 0) {
	    CONTENT = rEntity.getValue(0, "CONTENT");
	    URL     = rEntity.getValue(0, "URL");
	    VERSION = rEntity.getValue(0, "VERSION");
	    MENU_ID = rEntity.getValue(0, "MENU_ID");
	}	                              
%>

<script language="JavaScript">
<!--

/***************************************************************************
* Title   : 저장 
* Content : 작성된 게시글을 저장 처리한다.  
****************************************************************************/
function fn_save() {
    var fm = document.fmParam;  

	fm.cmd.value = "HmsContentC";
	fm.submit();
}

/***************************************************************************
* Title   : 목록 
* Content : 리스트 목록으로 처리한다.
****************************************************************************/
function fn_cancel() {
    var fm = document.fmParam;

    fm.cmd.value = "HmsView";
    fm.submit();
}

//-->
</script>

<form name="fmParam" method="post" action="../hms/HmsAction.do" >
<input type="hidden" name="cmd">
<input type="hidden" name="menu_id" value="<%=MENU_ID%>">

<div id='hms_write'>
	<%
		FCKeditor oFCKeditor ;
		oFCKeditor = new FCKeditor( request, "EditorDefault" ) ;
		oFCKeditor.setHeight("600");
		oFCKeditor.setBasePath( "../com/FCKeditor/" ) ;
		oFCKeditor.setToolbarSet("Hms");
		oFCKeditor.setValue(CONTENT);
		oFCKeditor.setInstanceName("CONTENT");
		out.println( oFCKeditor.create() ) ;
	%> 
</div>

<div id='hms_write_btn'>
	<img src="../images/hms/bt_save.gif"   onClick="fn_save()" border="0" alt="저장" class='cursor'></a> 
	<img src="../images/hms/bt_cancel.gif" onClick="fn_cancel()" border="0" alt="취소"  class='cursor'></a>
</div>
</form>