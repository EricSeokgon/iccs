<%@ page contentType="text/html;charset=utf-8"%>

<!--게시판 : S -->
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="inputta" >
	<!-- 검색 : S -->
	<tr> 
		<th class="line" width="10%">지역구분</th>
    <td class="line" >&nbsp;<%=sEntity.getValue(0,"SIDO_NM")%>&nbsp;-&nbsp;<%=sEntity.getValue(0,"SIGUNGU_NM")%></td>
		<th class="line" >현장명칭</th>
    <td class="line" ><input name="scINSP_SPOT_NM" type="text" size="15" id="scINSP_SPOT_NM" value="" ></td>
		<th class="line" >건축주</th>
    <td class="line" ><input name="scAPPLPER_NM" type="text" size="15" id="scAPPLPER_NM" value="" ></td>
  </tr>
  <tr>
  	<th class="line" >상호</th>
    <td class="line" ><input name="scOPE_NAME" type="text" size="15" id="scOPE_NAME" value="" ></td>
		<th class="line" >검사일자</th>
    <td class="line" >
			<input name="scRECV_ST" type=text id="scStartDate" value="" size="10" readonly>
			<div id="div_start" style="position:absolute;  width:167px; height:187px; z-index:1; visibility: hidden; " >
				<iframe src='../com/calander.jsp?frm=fmParam&fName=scRECV_ST' scrolling="no" frameborder="0" width="160" height="190"></iframe>
			</div>
			<img src="../images/box/cal.gif" onClick="MM_showHideLayers('div_start','','show')" align="absmiddle" style="cursor:hand" >
       ~
			<input name="scRECV_ET" type=text id="scStartDate" value="" size="10" readonly>
			<div id="div_end" style="position:absolute;  width:167px; height:187px; z-index:1; visibility: hidden; " >
				<iframe src='../com/calander.jsp?frm=fmParam&fName=scRECV_ET' scrolling="no" frameborder="0" width="160" height="190"></iframe>
			</div>
			<img src="../images/box/cal.gif" onClick="MM_showHideLayers('div_end','','show')" align="absmiddle" style="cursor:hand" >
    </td>
		<th class="line" colspan="2">
  		<input name="RPTCHK" type="checkbox" value="su" >합격
  		<input name="RPTCHK" type="checkbox" value="dsu" >불합격
  		<!-- img src="../images/box/btn_print.gif" onclick="fn_print();" -->
			<a href="javascript:fn_search()"><img src="../images/bbs/btn_search.gif" alt="go" style="cursor:pointer"></a>
		</th>
	</tr>
</table>
<!-- 검색 : E -->
	<tr><td height="5px">&nbsp;</td></tr>
	
	<!-- 리스트 타이틀  : S -->
	<tr>
	    <td width="100%" align="center">
			<table width="100%" height="26" border="0" cellpadding="0" cellspacing="0" background="../images/bbs/list_bg.gif">
		      	<tr>
		      	  <td width="5%" align="center">선택</td>
			        <td width="15%" align="center">접수번호</td>
							<td width="15%" align="center">건축주</td>
			    		<td width="10%" align="center">시공업체</td>
			    		<td align="center">현장명칭</td>
			    		<td width="10%" align="center">접수일자</td>
			    		<td width="10%" align="center">처리기한</td>
			    		<td width="10%" align="center">상태</td>
			    		<td width="15%" align="center">설계도검토유무</td>
		      	</tr>
		    </table>
		</td>
  	</tr>
	<!-- 리스트 타이틀  : E -->	


	<!-- 리스트 본문 : S --> 
  	<tr>
	    <td align="center" valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="underline">
		   	<tbody>
				<% for (int i = 0; i < rListEnt.getRowCnt(); i++) {
						String RECV_NUM = rListEnt.getValue(i, "RECV_NUM"); //접수번호
				%>
	
				<tr >
				  <td width="5%" height="26" align="center"><input type="checkbox" name="recv_num" value="<%=RECV_NUM%>" onClick="fn_print_check()" ></td>
					<td onClick="fnView('<%=RECV_NUM%>');" style="cursor:hand" width="15%" height="26" align="center" class="sfont_gray99" style="padding-left:5px">
						<%=KJFUtil.print(rListEnt.getValue(i, "CIV_RECV_NUM"), "&nbsp;")%>
					</td>
					<td  onClick="fnView('<%=RECV_NUM%>');" style="cursor:hand" width="15%" align="center" class="sfont_gray99">
						<%=KJFUtil.print(rListEnt.getValue(i, "APPLPER_NM"), "&nbsp;")%>
					</td>
					<td onClick="fnView('<%=RECV_NUM%>');" style="cursor:hand" width="10%" align="center" class="sfont_gray99">
						<%=KJFUtil.print(rListEnt.getValue(i, "OPE_NAME"), "&nbsp;")%>
					</td>
					<td onClick="fnView('<%=RECV_NUM%>');" style="cursor:hand" align="center" class="sfont_gray99">
						<%=KJFUtil.print(rListEnt.getValue(i, "INSP_SPOT_NM"), "&nbsp;")%>
					</td>	
					<td width="10%" align="center" class="sfont_gray99">
						<%=KJFUtil.print(rListEnt.getValue(i, "RECV_DT"), "&nbsp;")%>
					</td>	
					<td width="10%" align="center" class="sfont_gray99">
						<%=KJFUtil.print(rListEnt.getValue(i, "PROC_LIM"), "&nbsp;")%>
					</td>	
					<td width="10%" align="center" class="sfont_gray99">
						<%=KJFUtil.print(rListEnt.getValue(i, "PROC_STE"), "&nbsp;")%>
					</td>	
					<td width="10%" align="center" class="sfont_gray99">
						<%=KJFUtil.print(rListEnt.getValue(i, "SUV_APPL"), "&nbsp;")%>
					</td>	
				
				</tr>		
			  <tr>
		      <td height="1" colspan="8" bgcolor="#ededed"></td>
		    </tr>	
				<% } %>

				<% if (rListEnt.getRowCnt() < 1) { %>
				<tr>
		        	<td align="center" colspan="8" class="td_none">등록된 정보가 없습니다.</td>
		        </tr>
				<% } %>
		    </tbody>
		    </table>
		</td>
  	</tr>
	<!-- 리스트 본문 : E --> 


	<!-- 버튼 : S --> 
	<%//  if ( status.isWRITE() ) { // 관리자 || 쓰기허용 %>	
	<!-- tr>
	  	<td align="center">
			<table width="650" height="35" border="0" align="center" cellpadding="0" cellspacing="0">
		    	<tbody>
		       		<tr>		  
						<td>&nbsp;</td>       		
		         		<td width="60" align="right">
							<Img src="../images/bbs/btn_write.gif" alt="글쓰기" border="0" onClick="javascript:fn_Write01('BbsKWrite');" style="cursor:pointer"></a>
			  			</td>
		       		</tr>
		     	</tbody>
		   	</table>
		</td>
	 </tr -->
	 <%//} %>			
	<!-- 버튼 : E -->	
  	

	<!-- 페이징 : S --> 
	<tr>
	    <td height="45" align="center">
			<jsp:include page="../com/inc/paging.jsp" flush="true">
			  <jsp:param name="pagePerScreen" value="10"/>
			  <jsp:param name="rowPerPage" value="<%=rowPerPage%>"/>
			  <jsp:param name="totalCount" value="<%=totalCount%>"/>
			  <jsp:param name="nowPage" value="<%=nowPage %>"/>
			</jsp:include>	
		</td>
  	</tr>
	<!-- 페이징 : E -->
</table>
<!-- 게시판 : E -->
