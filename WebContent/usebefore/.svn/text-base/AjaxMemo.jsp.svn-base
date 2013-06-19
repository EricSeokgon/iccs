<%@page contentType="text/html;charset=UTF-8" %>
<%@ page import="kjf.util.*" %>
<%@ page import="kjf.ops.*" %>
<%
	response.setHeader ("Pragma", "No-cache");
	response.setDateHeader ("Expires", 0);
	response.setHeader ("Cache-Control", "no-cache");
%>
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="inputta">
		<tr> 
			<th class="line" width="5%">선택</th>	
			<th class="line" width="65%">메모내용</th>
			<th class="line" width="15%">작성자</th>
	    <th class="line" width="15%">작성일자</th>
		 </tr>
<%
	if(request.getAttribute("mEntity")!=null){
		ReportEntity mEntity  = KJFUtil.REntPrint((ReportEntity)request.getAttribute("mEntity"));
	 	String SEQ = "";
	 	String MEMO_CONT = "";
	  String WRT_NAME = "";
	  String INS_DT = "";
		String MEMO_DT 	= "";
		String WRT_TIME 	= "";	  
	 	if (mEntity != null && mEntity.getRowCnt() > 0){
		  for (int i=0; i<mEntity.getRowCnt(); i++){
				SEQ 			= mEntity.getValue(i,"SEQ");
				MEMO_CONT = mEntity.getValue(i,"MEMO_CONT");
				WRT_NAME 	= mEntity.getValue(i,"WRT_NAME");
				INS_DT 		= mEntity.getValue(i,"INS_DT");
	 			MEMO_DT 	= mEntity.getValue(i,"MEMO_DT");
	 			WRT_TIME 	= mEntity.getValue(i,"WRT_TIME");				
%>
		<tr>
			<td class="line"><input type="checkbox" name="chk" value="<%=SEQ%>"></td>
			<td class="line" onclick="fn_Memo_update('<%=MEMO_DT %>','<%=WRT_TIME %>','<%=MEMO_CONT %>','<%=SEQ %>');" style="cursor:hand"><%=MEMO_CONT %></td>
			<td class="line"><%=WRT_NAME %></td>
			<td class="line"><%=INS_DT %></td>
		</tr>
<% 		}
		}else { %>
		<tr>
			<td class="line" colspan="4">&nbsp;</td>
		</tr>
		
<%	} %>
	</table>
<%	
	} else{
		out.print("failure");
	}
%>