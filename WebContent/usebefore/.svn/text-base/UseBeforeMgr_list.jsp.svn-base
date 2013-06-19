<%@ page contentType="text/html;charset=utf-8"%>

<!--게시판 : S -->
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="inputta" >
	<!-- 검색 : S -->
	<tr> 
		<th class="line" width="10%">지역구분</th>
    <td class="line" >&nbsp;<%=sEntity.getValue(0,"SIDO_NM")%>&nbsp;-&nbsp;<%=sEntity.getValue(0,"SIGUNGU_NM")%></td>
		<th class="line" >현장주소</th>
    <td class="line" ><input name="scINSP_SPOT_ADDR" type="text" size="30" id="scINSP_SPOT_ADDR" value="<%=KJFUtil.print(pm.getScINSP_SPOT_ADDR(),"") %>" ></td>
		<th class="line" >건축주</th>
    <td class="line" ><input name="scAPPLPER_NM" type="text" size="15" id="scAPPLPER_NM" value="<%=KJFUtil.print(pm.getScAPPLPER_NM(),"") %>" ></td>
  </tr>
  <tr>
  	<th class="line" >시공업체</th>
    <td class="line" ><input name="scOPE_NAME" type="text" size="15" id="scOPE_NAME" value="<%=KJFUtil.print(pm.getScOPE_NAME(),"") %>" ></td>
		<th class="line" >접수일자</th>
    <td class="line" >
			<input name="scRECV_ST" type=text id="scStartDate" value="<%=RECV_ST %>" size="10" readonly>
			<div id="div_start" style="position:absolute;  width:167px; height:187px; z-index:1; visibility: hidden; " >
				<iframe src='../com/calander.jsp?frm=fmParam&fName=scRECV_ST' scrolling="no" frameborder="0" width="160" height="190"></iframe>
			</div>
			<img src="../images/box/cal.gif" onClick="MM_showHideLayers('div_start','','show')" align="absmiddle" style="cursor:hand" >
       ~
			<input name="scRECV_ET" type=text id="scEndDate" value="<%=RECV_ET %>" size="10" readonly>
			<div id="div_end" style="position:absolute;  width:167px; height:187px; z-index:1; visibility: hidden; " >
				<iframe src='../com/calander.jsp?frm=fmParam&fName=scRECV_ET' scrolling="no" frameborder="0" width="160" height="190"></iframe>
			</div>
			<img src="../images/box/cal.gif" onClick="MM_showHideLayers('div_end','','show')" align="absmiddle" style="cursor:hand" >
    </td>
		<th colspan="2" style="border-bottom:1px solid #448fd0;">
			<a href="javascript:fn_search()"><img src="../images/bbs/btn_search.gif" alt="go" style="cursor:pointer"></a>
		</th>
	</tr>
	<tr><td colspan="2"  height="10px">&nbsp;</td></tr>
</table>
<!-- 검색 : E -->
	<!-- 리스트 타이틀  : S -->
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
	<tr>
	    <td width="100%" align="center">
				<table width="665px" height="26" border="0" cellpadding="0" cellspacing="0" background="../images/bbs/list_bg.gif">
		      	<tr>
			        <td width="100px" align="center">접수번호</td>
							<td width="80px" align="center">건축주</td>
			    		<td width="120px" align="center">시공업체</td>
			    		<td width="180px" align="center">현장주소</td>
			    		<td width="55px" align="center">접수일자</td>
			    		<td width="55px" align="center">처리기한</td>
			    		<td width="60px" align="center">상태</td>
		      	</tr>
		    </table>
			</td>
  </tr>
	<!-- 리스트 타이틀  : E -->	


	<!-- 리스트 본문 : S --> 
  	<tr>
	    <td width="100%" align="center" valign="top">
			<table width="665px" border="0" cellpadding="0" cellspacing="0" class="underline">
		   	<tbody>
				<% 
					String RECV_NUM ="";			String curDate = "";
					String PROC_LIM ="";
					for (int i = 0; i < rListEnt.getRowCnt(); i++) {
						RECV_NUM = rListEnt.getValue(i, "RECV_NUM"); //접수번호
						curDate =  KJFUtil.print(rListEnt.getValue(i, "RECV_DT"), "");						
						if (KJFUtil.isEmpty(curDate)){	curDate = "&nbsp;";
						} else {curDate = KJFDate.getChangDatePattern(curDate,"yyyyMMdd", "yy-MM-dd");}
						PROC_LIM =  KJFUtil.print(rListEnt.getValue(i, "PROC_LIM"), "");						
						if (KJFUtil.isEmpty(PROC_LIM)){	PROC_LIM = "&nbsp;";
						} else {PROC_LIM = KJFDate.getChangDatePattern(PROC_LIM,"yyyyMMdd", "yy-MM-dd");}
				%>
	
				<tr onClick="fnView('<%=RECV_NUM%>');" style="cursor:hand">
					<td  width="100px" height="26" align="center"  style="padding-left:5px">
						<%=KJFUtil.print(rListEnt.getValue(i, "CIV_RECV_NUM"), "&nbsp;")%>
					</td>
					<td width="80px" align="center" >
						<%=KJFUtil.print(rListEnt.getValue(i, "APPLPER_NM"), "&nbsp;")%>
					</td>
					<td  width="120px" align="left" >
						<%=KJFUtil.print(rListEnt.getValue(i, "OPE_NAME"), "&nbsp;")%>
					</td>
					<td width="180px" align="left" >
						<%=KJFUtil.print(rListEnt.getValue(i, "INSP_SPOT_ADDR"), "&nbsp;")%>
						&nbsp;<%=KJFUtil.print(rListEnt.getValue(i, "INSP_SPOT_DETAILADDR"), "&nbsp;")%>
					</td>	
					<td width="55px" align="center" >
						<%=curDate%>
					</td>	
					<td width="55px" align="center" >
						<%=PROC_LIM%>
					</td>	
					<td width="60px" align="center" >
						<%=KJFUtil.print(rListEnt.getValue(i, "PROC_STE"), "&nbsp;")%>
					</td>	
				</tr>		
			  <tr>
		      <td height="1" colspan="7" bgcolor="#ededed"></td>
		    </tr>	
				<% } %>

				<% if (rListEnt.getRowCnt() < 1) { %>
				<tr>
		        	<td align="center" colspan="7" class="td_none" ><br/><br/><br/><br/>등록된 정보가 없습니다.<br/><br/><br/><br/><br/><br/></td>
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
	<tr><td height="10px"><br/><br/><br/><br/><br/></td></tr>
	
</table>
<!-- 게시판 : E -->
