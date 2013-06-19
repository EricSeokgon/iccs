<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.Vector" %>
<%@ page import="java.io.*" %>
<%@ page import="kjf.ops.*" %>
<%@ page import="kjf.util.*" %>
<%@ page import="sp.uent.*" %>

<%@ taglib uri="/KJFTags" prefix="KTags" %>
<%@ page import="sp.usebefore.UseBeforeParam" %>

<%
	// 초기 변수 들 선언
	UseBeforeParam pm = (UseBeforeParam)request.getAttribute("pm");
	//======================================================================
	// 신규인경우에는 초기화가 되지 않으면 error가  나기 때문에 초기화가 필요하다.
	//======================================================================
	
	// 사용전검사관리 첨부파일 조회
	ReportEntity FileEntity  = KJFUtil.REntPrint((ReportEntity)request.getAttribute("FileEntity"));
	
	String mode 			= KJFUtil.print(request.getParameter("mode"));
	

	// 페이징 관련(필수)
	//String nowPage          = KJFUtil.print(pm.getNowPage());
	//String rowPerPage       = KJFUtil.print(pm.getRowPerPage());
	//String totalCount      	= KJFUtil.print(pm.getTotalCount());
	
	int attache_num =10;			// 첨부파일 등록 갯수 서블릿에서도 제어함
	
%>
<html>
<head>
<title>첨부파일</title>

<!-- 공통 : S -->
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="../com/js/com.js" type="text/javascript"></script>

<SCRIPT LANGUAGE="JavaScript">
<!--
function fn_Attache_save(){
	var fm = document.fmParam;
	fm.encoding  = "multipart/form-data";
	fm.submit();
}

/***************************************************************************
* Title   : 첨부파일 
* Content : 첨부데이터 삭제   
****************************************************************************/
function fn_Attache_delete() {
	var fm = document.fmParam;
	fm.encoding  = "multipart/form-data";
    var chkarray = "";
	if(chkBoxCheck('chk')){ // 선택된 항목이 있는지 체크
		var isOK = confirm('선택한 항목을 삭제하시겠습니까?');
		if(isOK){
				fm.mode.value = "D";
				fm.submit();
		}
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

    cell.innerHTML = "<tr><td><input type='file' class='file' name='BBS_FILE[" + rowIndex + "]' size='50'></td></tr>"; 
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
}
// 파일 첨부할때 필드 추가 제거 하는 스크립트 끝
//-->
</script>
</head>
<body>
<!--메모 : S -->
	<form name=fmParam method="post" action="../usebefore/UseBeforeAction.do" >
		<input type="hidden" name="scRECV_NUM" value="<%=pm.getScRecv_num()%>" />
		<input type="hidden" name="cmd" value="UseBeforeAttMgrCUD" />
		<input type="hidden" name="mode" value="C" />
		
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="inputta" >
			<tr> 
				<th class="line" align="center" width="5%">선택</th>	
				<th class="line" align="center" width="65%">파일명</th>
				<th class="line" align="center" width="15%">파일용량</th>
		    <th class="line" align="center" width="15%">등록일자</th>
			 </tr>
			 <%
			 	String SEQ 				= "";
			 	String FILE_NM 		= "";
			 	String FILE_SIZE 	= "";
			 	String INS_DT			= "";
			 	String SYS_FILE_NAME	= "";
			 	String SIGUNGU_SERVER	= "";
			 	String SIDO_CODE		= "";
			 	String SIGUNGU_CODE		= "";
				for(int i=0; i<FileEntity.getRowCnt(); i++){
					SEQ 							= FileEntity.getValue(i,"SEQ");
					FILE_NM 					= FileEntity.getValue(i,"FILE_NM");
					FILE_SIZE					= FileEntity.getValue(i,"FILE_SIZE");
					INS_DT						= FileEntity.getValue(i,"INS_DT");
					SYS_FILE_NAME 				= FileEntity.getValue(i, "SYS_FILE_NAME");
					SIGUNGU_SERVER				= "http://localhost";//FileEntity.getValue(i, "SERVER_ADDR");
					SIDO_CODE					= FileEntity.getValue(i, "SIDO_CODE");
					SIGUNGU_CODE				= FileEntity.getValue(i, "SIGUNGU_CODE");					
			 %>
			 <tr>
				<td class="line" width="5%" height="5" align="center"><input type="checkbox" name="chk" value="<%=SEQ%>" ></td>
				<td class="line" align="center" width="65%"><%=FILE_NM %></td>
				<td class="line" align="center" width="15%"><%=FILE_SIZE %></td>
		    <td class="line" align="center" width="15%"><%=INS_DT %></td>
		   </tr>
			 <% } %>
		</table>
		<br/><br/>
		
		<table cellpadding="0" cellspacing="0" >
	
			<% 
			if ((attache_num - FileEntity.getRowCnt()) > 0) { %>
	    	<tr>
	    		<td class="line">
	    			<table name="attachFile" id="attachFile" border="0" cellpadding="0" cellspacing="0">
	
						<!--필드 추가 제거 할때 쓰임-->
		            	<input type="hidden" NAME="fileCnt" VALUE="1"/>
	
		          		<tr>			              	
		                	<td><input type="file" class="file" name="BBS_FILE[0]" size="50" /></td>
	                		<% if (attache_num - FileEntity.getRowCnt() > 1) { %>
	                		<td>&nbsp;<img src="../images/bbs/btn_add.gif" alt="파일 추가" border="0" onclick="addFile(fmParam, '<%=attache_num - FileEntity.getRowCnt()%>', 1);" style="cursor:pointer"></td>
	                		<td>&nbsp;<img src="../images/bbs/btn_remove.gif" alt="파일 빼기" border="0" onClick="deleteFile(fmParam);" style="cursor:pointer"></a></td>
	                		<% } %>	                
		              	</tr>		              
		            </table>
	    		</td>
	    	</tr>
	    	<%}%>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="inputta">
				 <tr>	 
				 	<th colspan="4" align="right"> 
				 	 <span  id="tb_save"><img src="../images/box/btn_save.gif" onClick="fn_Attache_save()"/> </span>
				 	 <span  id="tb_delete"><img src="../images/box/btn_delet.gif" onClick="fn_Attache_delete()"/></span>
				 	</th>
				 </tr>
				 <tr><td height="50px"  colspan="4">&nbsp;</td></tr>
			</table>
	</form>			
<!-- 전체 : E -->

</body>
</html>
