<%@ page contentType="text/html;charset=utf-8"%>

<!--게시판 : S -->
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="inputta" >
	<!-- 검색 : S -->
	<tr> 
		<th class="line" width="10%">지역구분</th>
    <td class="line" >&nbsp;<%=sEntity.getValue(0,"SIDO_NM")%>&nbsp;-&nbsp;<%=sEntity.getValue(0,"SIGUNGU_NM")%></td>
		<th class="line" >접수번호</th>
    <td class="line" ><input name="scCIV_RECV_NUM" type="text" size="15" id="scINSP_SPOT_NM" value="<%=scCIV_RECV_NUM %>" ></td>
		<th class="line" >적합여부</th>
    <td class="line" >
    	<input name="chkOK" type="checkbox" value="1" onClick="fn_chkOK();" <%="1".equals(scOK)?"checked":"" %> >적합
  		<input name="chkNO" type="checkbox" value="1" onClick="fn_chkNO('1');" <%="1".equals(scNO)?"checked":"" %> >부적합<br/>
  		<input name="chkNO_2" type="checkbox" value="1" onClick="fn_chkNO('2');" <%="1".equals(scNO_2)?"checked":"" %> >취하
  		<input name="chkNO_3" type="checkbox" value="1" onClick="fn_chkNO('3');" <%="1".equals(scNO_3)?"checked":"" %> >이첩
  	</td>
  </tr>
  <tr>
  	<th class="line" >시공업체</th>
    <td class="line" ><input name="scOPE_NAME" type="text" size="15" id="scOPE_NAME" value="<%=scOPE_NAME %>" ></td>
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
	<tr><td height="10px" colspan="2">&nbsp;</td></tr>

</table>
<!-- 검색 : E -->

	
	<!-- 리스트 타이틀  : S -->
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >	
	<tr>
	    <td width="100%" align="center">
			<table width="670px" height="26" border="0" cellpadding="0" cellspacing="0" background="../images/bbs/list_bg.gif">
		      	<tr>
			        <td width="55px" align="center">접수일자</td>
							<td width="150px" align="center">필증교부번호</td>
			    		<td width="80px" align="center">건축주</td>
			    		<td width="120px" align="center">시공업체</td>
			    		<td width="215px" align="center">현장주소</td>
			    		<td width="50px" align="center">상태</td>
		      	</tr>
		    </table>
		</td>
  	</tr>
	<!-- 리스트 타이틀  : E -->	

	<!-- 리스트 본문 : S --> 
  	<tr>
	    <td width="100%" align="center" valign="top">
			<table width="670px" border="0" cellpadding="0" cellspacing="0" class="underline">
		   	<tbody>
				<% 
					String RECV_NUM ="";
				  String curDate = "";
					for (int i = 0; i < rListEnt.getRowCnt(); i++) {
						RECV_NUM = rListEnt.getValue(i, "RECV_NUM"); //접수번호
						curDate =  KJFUtil.print(rListEnt.getValue(i, "RECV_DT"), "");
						if (KJFUtil.isEmpty(curDate)){
							curDate = "&nbsp;";
						} else {
							curDate = KJFDate.getChangDatePattern(curDate,"yyyyMMdd", "yy-MM-dd");
						}
				%>
	
				<tr onClick="fnView('<%=RECV_NUM%>');" style="cursor:hand">
					<td  width="55px" align="center"  style="padding-left:5px">
						<%=curDate%>
					</td>
					<td width="150px" align="center" >
						<%=KJFUtil.print(rListEnt.getValue(i, "USEBEFINSP_DELINUM"), "&nbsp;")%>
					</td>
					<td  width="80px" align="center" >
						<%=KJFUtil.print(rListEnt.getValue(i, "APPLPER_NM"), "&nbsp;")%>
					</td>
					<td width="120px" align="left" >
						<%=KJFUtil.print(rListEnt.getValue(i, "OPE_NAME"), "&nbsp;")%>
					</td>	
					<td width="215px" align="left" >
						<%=KJFUtil.print(rListEnt.getValue(i, "ADDR"), "&nbsp;")%>
					</td>	
					<td width="50px" align="center" >
						<%=KJFUtil.print(rListEnt.getValue(i, "NAPPL_YN"), "&nbsp;")%>
					</td>	
				</tr>		
			  <tr>
		      <td height="1" colspan="8" bgcolor="#ededed"></td>
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
  	<tr><td height="10px"><br/><br/><br/><br/><br/></td></tr>
	<!-- 페이징 : E -->
</table>
<!-- 게시판 : E -->
