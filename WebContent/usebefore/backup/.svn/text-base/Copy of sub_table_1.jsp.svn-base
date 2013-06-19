<%@ page contentType="text/html;charset=utf-8"%>

<DIV style="PADDING-RIGHT: 0px; OVERFLOW-Y: scroll; 
PADDING-LEFT: 0px; SCROLLBAR-FACE-COLOR: #55aaee; FONT-SIZE: 9pt; 
PADDING-BOTTOM: 0px; SCROLLBAR-HIGHLIGHT-COLOR: #000000; BORDER-LEFT: medium none; 
WIDTH: 100%; SCROLLBAR-SHADOW-COLOR: #000000; SCROLLBAR-3DLIGHT-COLOR: #ffffff; 
SCROLLBAR-ARROW-COLOR: #ffffff; PADDING-TOP: 0px; SCROLLBAR-TRACK-COLOR: #000000; 
BORDER-BOTTOM: medium none; SCROLLBAR-DARKSHADOW-COLOR: #ffffff; HEIGHT: 400px">

<table id="subtable_1"  width="100%"  border="0" cellspacing="0" cellpadding="3" class="inputta"  >
	<tr>
		<th class="line" width="10%">검사항목</th>
		<th class="line" width="50%">검사내용 및 검사기준</th>
		<th class="line" width="10%">검사결과</th>
		<th class="line" width="10%">상세내용</th>
		<th class="line" width="20%">근거</th>
	</tr>
	<%
		// 1.구내통신선로설비
		String SEQ_1 				= "";
		String CLASS_NAME_1	= ""; 
		String ITEM_OUT_1		= "";
		String CONT_1				= "";
		String DETAIL_CONT_1	=	"";
		String BAS_1				= "";

		for(int i=0; i<rEntTable1.getRowCnt(); i++){
			SEQ_1 						= rEntTable1.getValue(i,"SEQ");
			CLASS_NAME_1 			= rEntTable1.getValue(i,"CLASS_NAME");
			CONT_1						= rEntTable1.getValue(i,"CONT");
			ITEM_OUT_1				= KJFUtil.print(rEntTable1.getValue(i,"ITEM_OUT"),"");
			
			if ("".equals(ITEM_OUT_1)){
				if ("Y".equals(WRKI001)){
					ITEM_OUT_1 ="적합";
				} else if ("N".equals(WRKI001)) {
					ITEM_OUT_1 ="해당없음";
				} 
			} 
			
			DETAIL_CONT_1			= KJFUtil.print(rEntTable1.getValue(i,"DETAIL_CONT"),"");
			BAS_1 						= rEntTable1.getValue(i,"BAS");
	%>
			<input type="hidden" name="SEQ_1" value="<%=SEQ_1%>" />
			<tr>
				<td class="line" ><%=CLASS_NAME_1 %></td>
				<td class="line" ><%=CONT_1 %></td>
				<td class="line" >
            <KTags:KJFSelect item='<%=(Vector)request.getAttribute("v_scITEM_OUT")%>'
						 name='ITEM_OUT_1'
						 value='<%=KJFUtil.print(ITEM_OUT_1) %>'
						 script="class='input_bbs' "/>       
				</td>
				<td class="line" >
				<input type="text" name ="DETAIL_CONT_1" value="<%=DETAIL_CONT_1%>" size="8" />
				</td>
				<td class="line" ><%=BAS_1 %></td>
			</tr>
	<%		
		}
	%>
</table>
</DIV>
