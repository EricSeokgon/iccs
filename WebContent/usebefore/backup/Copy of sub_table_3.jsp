<%@ page contentType="text/html;charset=utf-8"%>

<DIV style="PADDING-RIGHT: 0px; OVERFLOW-Y: scroll; 
PADDING-LEFT: 0px; SCROLLBAR-FACE-COLOR: #55aaee; FONT-SIZE: 9pt; 
PADDING-BOTTOM: 0px; SCROLLBAR-HIGHLIGHT-COLOR: #000000; BORDER-LEFT: medium none; 
WIDTH: 100%; SCROLLBAR-SHADOW-COLOR: #000000; SCROLLBAR-3DLIGHT-COLOR: #ffffff; 
SCROLLBAR-ARROW-COLOR: #ffffff; PADDING-TOP: 0px; SCROLLBAR-TRACK-COLOR: #ffffff; 
BORDER-BOTTOM: medium none; SCROLLBAR-DARKSHADOW-COLOR: #ffffff; HEIGHT: 400px">

<table id="subtable_3" width="100%"  border="0" cellspacing="0" cellpadding="3" class="inputta"  >
	<tr>
		<th class="line" width="10%">검사항목</th>
		<th class="line" width="50%">검사내용 및 검사기준</th>
		<th class="line" width="10%">검사결과</th>
		<th class="line" width="10%">상세내용</th>
		<th class="line" width="20%">근거</th>
	</tr>
	<% 
	// 3.이동통신구내선로설비	
		String SEQ_3					= "";
		String CLASS_NAME_3 	= ""; 
		String ITEM_OUT_3			= "";
		String CONT_3					= "";
		String DETAIL_CONT_3 	=	"";
		String BAS_3 					= "";
		
		for(int i=0; i < rEntTable3.getRowCnt(); i++){
			SEQ_3 						= rEntTable3.getValue(i,"SEQ");
			CLASS_NAME_3 			= rEntTable3.getValue(i,"CLASS_NAME");
			CONT_3						= rEntTable3.getValue(i,"CONT");
			if ("Y".equals(WRKI003)){
				ITEM_OUT_3 ="적합";
			} else if ("N".equals(WRKI003)) {
				ITEM_OUT_3 ="해당없음";
			} else {
				ITEM_OUT_3				= rEntTable3.getValue(i,"ITEM_OUT");
			}
			DETAIL_CONT_3			= KJFUtil.print(rEntTable3.getValue(i,"DETAIL_CONT"),"");
			BAS_3 						= rEntTable3.getValue(i,"BAS");			
	%>
			<input type="hidden" name="SEQ_3" value="<%=SEQ_3%>" />
			<tr>
				<td class="line"><%=CLASS_NAME_3 %></td>
				<td class="line"><%=CONT_3 %></td>
				<td class="line">
            <KTags:KJFSelect item='<%=(Vector)request.getAttribute("v_scITEM_OUT")%>'
						 name='ITEM_OUT_3'
						 value='<%=KJFUtil.print(ITEM_OUT_3) %>'
						 script="class='input_bbs' "/>       
				</td>
				<td class="line">
				<input type="text" name ="DETAIL_CONT_3" value="<%=DETAIL_CONT_3%>" size="8" /></td>
				<td class="line"><%=BAS_3 %></td>
			</tr>
	<%		
		}
	%>
</table>
</DIV>
