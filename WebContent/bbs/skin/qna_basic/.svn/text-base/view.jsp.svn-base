<%@ page contentType="text/html;charset=utf-8"%>
			    		
<table width="670" border="0" align="center" cellpadding="0" cellspacing="0">
  	<tr> 
    	<td>
			<table border="0" cellpadding="0" cellspacing="0">
	          	<tr>
	            	<td><img src="../images/bbs/view_top.gif" height="2" alt=""></td>
	          	</tr>
				
				<!--제목 : S -->
	          	<tr>
	            	<td class="view_title"><%=rEntity.getValue(0,"SUBJECT") %></td>
	          	</tr>
				<!--제목 : E -->				

	          	<tr>
	            	<td><img src="../images/bbs/view_dot_line.gif" height="1" alt=""></td>
	          	</tr>
	
				<!-- 지역 : S -->	
				<% if (status.isSD_DIV_YN()) { %>
	          	<tr>
	            	<td>
						<table width="670" height="30" border="0" cellpadding="0" cellspacing="0">
		              		<tr>
		                		<td width="60" align="center" class="td_view_title"><img src="../images/bbs/view_region.gif" alt="지역"></td>
								<td class="td_view_detail">
									<%=rEntity.getValue(0, "SD_NM") %>
									<% if(status.isSGG_DIV_YN()) { %>
									&nbsp;<%=rEntity.getValue(0, "SGG_NM") %>
									<% } %>
								</td>
		              		</tr>					
	            		</table>
					</td>
	          	</tr>
				<!-- 지역 : E -->				

	          	<tr>
	            	<td><img src="../images/bbs/view_dot_line.gif" height="1" alt=""></td>
	         	</tr>
				<% } %>
			
				<!--등록, 조회수 : S -->
	          	<tr>
	            	<td>
						<table width="670" height="30" border="0" cellpadding="0" cellspacing="0">
	              			<tr>
								
								<!--작성자 자리 -->
	                			<td width="60" align="center" class="td_view_title"><img src="../images/bbs/view_name.gif" alt="작성자"></td>
	                			<td width="200" class="td_view_detail letter0"><%=rEntity.getValue(0,"USER_NAME") %></td>

								<!--등록일자리 -->
	                			<td width="60" align="center" class="td_view_title"><img src="../images/bbs/view_date.gif" alt="등록일"></td>
	                			<td class="td_view_detail letter0"><%=rEntity.getValue(0,"INS_DT") %></td>
	                			
	                			<!--조회수자리 -->
								<td width="60" align="center" class="td_view_title"><img src="../images/bbs/view_hit.gif" alt="조회수"></td>
								<td width="80" align="center" class="td_view_detail letter0"><%=rEntity.getValue(0,"READ_NUM")%></td>
	              			</tr>
	            		</table>
					</td>
	          	</tr>
				<!--등록, 조회수 : E -->
	
			  	<tr>
	            	<td><img src="../images/bbs/view_dot_line.gif" height="1" alt=""></td>
	          	</tr>
	          	
				<!-- 첨부파일 : S -->
				<!-- ========================= 첨부파일 보기 START ======================= -->
				<% if(status.getATT_NUM() > 0 && FileEntity.getRowCnt() > 0) {%>
				<tr>
	            	<td>
						<table width="670" height="30" border="0" cellpadding="0" cellspacing="0">
	              			<tr>
	                			<td width="60" align="center" class="td_view_title"><img src="../images/bbs/view_file.gif" alt="첨부파일"></td>
	                			<td class="td_view_detail">

									<!--첨부파일내용:시작 -->
									<table border="0" cellpadding="5" cellspacing="0">
										<% for (int i = 0; i < FileEntity.getRowCnt(); i++ ) {%>
				                  		<tr>
				                    		<td class="td_view_detail"><img src="../images/bbs/fileicon/<%=KJFFile.getDownFileExt(FileEntity.getValue(i,"SYS_FILE_NAME"))%>.gif" border="0"></td>
				                    		<td class="td_view_detail"><%=FileEntity.getValue(i,"FILE_REAL_NAME")%></td>
				                    		<td class="td_view_detail"><p class="sfont_gray99">|&nbsp;&nbsp;<%=KJFFile.fileSize(KJFUtil.str2long(FileEntity.getValue(i,"FILE_SIZE"))) %></p></td>
				                    		<td>
												<a href="../com/downLoad.jsp?Lo=BBS_FILE_DIR&Sub_Lo=<%=CT_ID%>
												&SYS_FILE_NAME=<%=FileEntity.getValue(i, "SYS_FILE_NAME") %>
												&FILE_REAL_NAME=<%=java.net.URLEncoder.encode(FileEntity.getValue(i,"FILE_REAL_NAME"))%>">
												<img src="../images/bbs/btn_down.gif" alt="다운로드"></a>
											</td>
				                  		</tr>
					  					<%}%>	
	                				</table>
									<!--첨부파일내용:끝 -->

								</td>
	              			</tr>					
	            		</table>
					</td>
				</tr>

          		<tr>
            		<td><img src="../images/bbs/view_top.gif" height="2" alt=""></td>
          		</tr>
				<% } %>
				<!-- 첨부파일 : E -->

			</table>
		</td>
	</tr>

	<!-- 내용 : S -->
  	<tr> 
    	<td class="td_detail">
			<%if (FileEntity.getRowCnt() > 0) { %>	
   			<table width="100%"  border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;">
  	 				<tr>
  	 					<td align="center">			
						<% for(int i = 0; i < FileEntity.getRowCnt(); i++ ) {
						    
							String fileName = FileEntity.getValue(i, "SYS_FILE_NAME"); 
							String extension = fileName.substring(fileName.lastIndexOf("."));
								   
							if (extension.equals(".gif") || extension.equals(".jpg") || extension.equals("bmp") || 
								extension.equals(".GIF") || extension.equals(".JPG") || extension.equals("BMP")) {
							%>
								<img src="../com/downLoad.jsp?Lo=BBS_FILE_DIR&Sub_Lo=<%=CT_ID%>
								&SYS_FILE_NAME=<%=FileEntity.getValue(i,"SYS_FILE_NAME") %>
								&FILE_REAL_NAME=<%=java.net.URLEncoder.encode(FileEntity.getValue(i,"FILE_REAL_NAME"))%>" 
								onload='init_content_img(this , 630);' onclick='big_img1(this);' style='cursor:hand'>					 
								<%if ( i < FileEntity.getRowCnt() - 1) {  %>
									<br>
								<%}%>
								<%}%>
							<%}%>
					</td>
				</tr>
			</table>
			<br>	
			<%} %>
  	 			<%=KJFUtil.FCKeditorView_no_iframe(rEntity.getValue(0, "CONTENT")) %>
		</td>
  	</tr>
	<!-- 내용 : E -->

  	<tr>
    	<td height="2"><img src="../images/bbs/view_bottom.gif"></td>
  	</tr>

		<!-- 댓글  START-->        
		<%if(status.isONE_LINE()){ %>
		<tr><td>
			<table>
	      <tr>
	        <td colspan="4" ><img src="../images/bbs/btn_hide.gif" width="74" height="22" align="absmiddle" alt="" id="bt1" onclick="Click('1')" /></td>
	        <td colspan="2"  align="right">전체댓글:(<span><%=cEntity.getRowCnt()%>개</span>)</td>
	      </tr>
	      <tr>
	       <td colspan="6"  class="td_view_title">
	       	<table width="100%" border="0" cellpadding="0" cellspacing="0">
	         	<tr>
	         	  <td width="10%">&nbsp;<img src="../images/bbs/reply_comment.gif" />&nbsp;</td>	          	
	            <td width="65%">
					  		<textarea name="COMMET" cols="50" rows="3" style='BORDER-BOTTOM-COLOR: #bfd8f2; 
					  				BORDER-LEFT-COLOR: #bfd8f2; BORDER-RIGHT-COLOR: #bfd8f2; BORDER-TOP-COLOR: #bfd8f2; 
					  				width:520px; overflow:auto; background-color: #FFFFFF' 
					  				onkeyup='javascript:commentCK(200);' required="한줄 답변 내용을 입력해 주십시오"></textarea>
	            </td>
             	<td width="25%">
               	<a href="javascript:fn_comment_save();">
               		<img src="../images/bbs/stu_btn_save.gif" width="84" height="40" border="0" />
               	</a>
             	</td>
          	</tr>
          	<tr><td colspan="3" width="100%" align="center">
 								<table border="0" cellspacing="0" cellpadding="0">
	             		<tr>
		                 	<td >※ 100자 이내</td>
		                 	<td >※ HTMLTAG 사용불가</td>
		                 	<td ><input type=text style="width:25px" name="commentcount" value="0" readonly>byte/200 byte</td>
	                </tr>
	             </table>
	            </td></tr> 
         	</table>
         </td>
        </tr>
        <tr>
	          <td align="center" colspan="6" height="1px"></td>
        </tr>

	        <tr>
	          <td colspan="6" align="center" width="100%" class="td_view_title">
	           	<table id="sub1" width="630px" border="0" cellpadding="0" cellspacing="0" style="display:block">
								<% 	if (cEntity != null && cEntity.getRowCnt() > 0) {
									for (int i = 0; i < cEntity.getRowCnt(); i++ ) {%>
									<tr>
								  	<td align="center" width="30px" height="25px"><img src="../images/bbs/icon_name.gif" width="10" height="13" /></td>
								    <td width="100px"><%=cEntity.getValue(i, "WRT_NM") %></td>
								    <td align="right" width="300px"><%=cEntity.getValue(i, "INS_DT")%></td>
								    <td width="50px" align="center">
										<%if (status.isADMIN() || cEntity.getValue(i, "WRT_ID").equals(status.getUSER_ID()) ) { %>
											<img src="../images/bbs/card_modify.gif" width="14" height="14" onClick="fn_comment_modify('<%=KJFUtil.java2html(cEntity.getValue(i, "COMMENT_SEQ")) %>', '<%=i%>')" border="0" style="cursor:pointer;">
											<img src="../images/bbs/icon_comment_del.gif" alt="삭제" onClick="fn_comment_del('<%=KJFUtil.java2html(cEntity.getValue(i, "COMMENT_SEQ")) %>')" border="0" style="cursor:pointer;">
										<%}else{%>&nbsp;<%}%>
										</td>
								  </tr>
								  <tr>																		  
										<td colspan="4" class="Comment_01" id="Comment_<%=i%>" style="word-break:break-all">&nbsp;<%=KJFUtil.java2html(cEntity.getValue(i, "COMMENT_MEMO"))%></td>
									</tr>
									<tr>
										<td colspan="4"><img src="../images/bbs/reply_dot_line.gif" width="633" height="1"></td>
									</tr>						
						
					<%			} 
							} else {
					%>
						<tr>
							<td colspan="4" align="center" height="30">등록된 댓글이 없습니다.</td>
						</tr>
					<%	}%>	
					</table>
	  		</td>
			</tr>
		  <tr><td colspan="6" class="Comment_02"></td></tr>
		</table>
				<!-- 댓글  END-->
		</td>
	</tr>	
	<%}%>



  	<tr> 
    	<td>&nbsp;</td>
  	</tr>

	<!-- 버튼 : S -->
  	<tr> 
    	<td height="50" valign="top"> 
			<table width="100%" border="0" cellspacing="2" cellpadding="0">
        		<tr>
          			<td>		  				
						<%  if (IS_MODIFY ) { // 수정   %>
			    		<img src="../images/bbs/btn_modify.gif" alt="수정" class="cursor" style="cursor:pointer" onClick="fn_Write('modify');">
			    		
			    		<% } %>

						<%  if (status.isREPLY()) { // 답변  %>
						<img src="../images/bbs/btn_reply.gif" alt="답변" class="cursor" style="cursor:pointer" onClick="fn_Write('reply');">
			    		<% } %>
						
						<%  if (IS_DELETE ) { //삭제  %> 
			    		<img src="../images/bbs/btn_delete.gif" alt="삭제" class="cursor" style="cursor:pointer" onClick="fn_del();">
			    		<% } %>						
					</td>
          			
					<!--목록버튼 -->
					<td align="right">
						<img src="../images/bbs/btn_list.gif" alt="목록" border="0" class="cursor" style="cursor:pointer" onClick="fn_goList('BbsKList');">
					</td>
        		</tr>
      		</table>
 
	  	</td>
  	</tr>
	<!-- 버튼 : E -->

</table>