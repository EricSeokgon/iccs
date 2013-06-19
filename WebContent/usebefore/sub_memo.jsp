<%@ page contentType="text/html;charset=utf-8"%>

<!--메모 : S -->
<span id="sub_memo">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="inputta" >
	<tr> 
		<th class="line" width="5%">선택</th>	
		<th class="line" width="65%">메모내용</th>
		<th class="line" width="15%">작성자</th>
    <th class="line" width="15%">작성일자</th>
	 </tr>
	 <%
	 	String SEQ = "";
	 	String MEMO_CONT = "";
	  String WRT_NAME = "";
	  String INS_DT = "";
	  String MEMO_DT = "";
	  String WRT_TIME = "";
	 	if (mEntity != null){
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
		<td class="line" align="center"><%=WRT_NAME %></td>
		<td class="line"><%=INS_DT %></td>
	</tr>
	<% }}else { %>
	<tr>
		<td class="line" colspan="4">&nbsp;</td>
	</tr>
	
	<%} %>
</table>
</span>
<br/><br/>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="inputta">
	<tr> 
		<th class="line" width="10%">작성일자</th>
    <td class="line">							
    	<input name="MEMO_DT" type=text id="scEndDate" value="" size="10" readonly>
			<div id="div_end" style="position:absolute;  width:167px; height:187px; z-index:1; visibility: hidden; " >
				<iframe src='../com/calander.jsp?frm=fmParam&fName=MEMO_DT' scrolling="no" frameborder="0" width="160" height="190"></iframe>
			</div>
			<img src="../images/box/cal.gif" onClick="MM_showHideLayers('div_end','','show')" align="absmiddle" style="cursor:hand" >
			 <input name="WRT_TIME" type=text value="" size="4" maxlength="4"> ex) 01시 10분 : 0110 입력
    </td>
		<th class="line" width="15%">작성자</th>
    <td class="line" width="30%"><%=user.getUSER_NAME()%></td>
   </tr>
   <tr>
     <th  class="line">메모내용</th>
     <td  class="line" colspan="3"><textarea name="MEMO_CONT" cols="80" rows="5" maxlength="500"></textarea></td>
	 </tr>
	 <tr><td height="1px" colspan="4">&nbsp;</td></tr>
	 <tr>	 
	 	<th colspan="4" align="right"> 
	 	 <span  id="tb_clear"><img src="../images/box/btn_insert.gif" onClick="fn_Memo_clear()"></span>
	 	 <span  id="tb_enable"><img src="../images/box/btn_modify_off.gif" /> </span>
	 	 <span  id="tb_save"><img src="../images/box/btn_save.gif" onClick="fn_Memo_save()"/> </span>
	 	 <span  id="tb_cancel"><img src="../images/box/btn_cancel.gif" onClick="fn_Memo_cancel()"/></span>	 		 	
	 	 <span  id="tb_delete"><img src="../images/box/btn_delet.gif" onClick="fn_Memo_delete()"/></span>	 	 
	 	</th>
	 </tr>
	 <tr><td height="50px"  colspan="4">&nbsp;</td></tr>
</table>

