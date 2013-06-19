<%@ page contentType="text/html;charset=utf-8"%>

<DIV style="PADDING-RIGHT: 0px; OVERFLOW-Y: scroll; 
PADDING-LEFT: 0px; SCROLLBAR-FACE-COLOR: #55aaee; FONT-SIZE: 9pt; 
PADDING-BOTTOM: 0px; SCROLLBAR-HIGHLIGHT-COLOR: #000000; BORDER-LEFT: medium none; 
WIDTH: 100%; SCROLLBAR-SHADOW-COLOR: #000000; SCROLLBAR-3DLIGHT-COLOR: #ffffff; 
SCROLLBAR-ARROW-COLOR: #ffffff; PADDING-TOP: 0px; SCROLLBAR-TRACK-COLOR: #ffffff; 
BORDER-BOTTOM: medium none; SCROLLBAR-DARKSHADOW-COLOR: #ffffff; HEIGHT: 400px">

<table id="subtable_2" width="100%"  border="0" cellspacing="0" cellpadding="3" class="inputta"  >
	<tr>
		<th class="line" width="10%">검사항목</th>
		<th class="line" width="50%">검사내용 및 검사기준</th>
		<th class="line" width="10%">검사결과</th>
		<th class="line" width="10%">상세내용</th>
		<th class="line" width="20%">근거</th>
	</tr>
	<% 
		// 2.방송공동수신설비
		String SEQ_2 	= ""; 
		String CLASS_NAME_2 	= ""; 
		String ITEM_OUT_2			= "";
		String ITEM_OUT_2_ITEM = "";
		
		String CONT_2					= "";
		String DETAIL_CONT_2 	=	"";
		String BAS_2 					= "";
		
		for(int i=0; i<rEntTable2.getRowCnt(); i++){
			SEQ_2 						= rEntTable2.getValue(i,"SEQ");
			CLASS_NAME_2 			= rEntTable2.getValue(i,"CLASS_NAME");
			CONT_2						= rEntTable2.getValue(i,"CONT");			
			DETAIL_CONT_2			= KJFUtil.print(rEntTable2.getValue(i,"DETAIL_CONT"),"");
			BAS_2 						= rEntTable2.getValue(i,"BAS");
			
			ITEM_OUT_2_ITEM = KJFUtil.print(rEntTable2.getValue(i,"ITEM_OUT"),"");

			if ("Y".equals(WRKI002)){
				if ("".equals(ITEM_OUT_2_ITEM)){	
					if (CLASS_NAME_2.indexOf("공통사항") > 0){
							ITEM_OUT_2 ="적합";
					} else if ( (CLASS_NAME_2.indexOf("지상파TV") > 0)|| 
											(CLASS_NAME_2.indexOf("위성방송") > 0)|| 
											(CLASS_NAME_2.indexOf("FM라디오방송")>0)
						){
							if("Y".equals(WRK001) || "Y".equals(WRK002) ||  "Y".equals(WRK003)){
								ITEM_OUT_2 ="적합";
							} else {
								ITEM_OUT_2 ="해당없음";
							}
					} else if (CLASS_NAME_2.indexOf("종합유선방송설비")>0){
						ITEM_OUT_2 ="적합";
					}
				} else {
					ITEM_OUT_2	= ITEM_OUT_2_ITEM;
				}
			} else if ("N".equals(WRKI002)) {
					ITEM_OUT_2 ="해당없음";
			}
	%>
			<input type="hidden" name="SEQ_2" value="<%=SEQ_2%>" />
			<tr>
				<td class="line"><%=CLASS_NAME_2 %></td>
				<td class="line"><%=CONT_2 %></td>
				<td class="line">
            <KTags:KJFSelect item='<%=(Vector)request.getAttribute("v_scITEM_OUT")%>'
						 name='ITEM_OUT_2'
						 value='<%=KJFUtil.print(ITEM_OUT_2) %>'
						 script="class='input_bbs' "/>       
				</td>
				<td class="line">
				<input type="text" name ="DETAIL_CONT_2" value="<%=DETAIL_CONT_2%>" size="8" /></td>
				<td class="line"><%=BAS_2 %></td>
			</tr>
	<%		
		}
	%>
</table>
</DIV>
