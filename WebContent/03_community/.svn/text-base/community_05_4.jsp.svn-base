<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="kjf.ops.*" %>
<%@ page import="kjf.util.*" %>
<%@ page import="sp.search.*" %>
<%@ include file="../inc/user_inc.jsp" %>

<%
	ReportEntity rEntity = KJFUtil.REntPrint( (ReportEntity)request.getAttribute("rEntity") );	// FAQ 전체정보

	SearchParam pm = (SearchParam)request.getAttribute("pm");
	
	// 페이징 관련(필수)
	String nowPage    = KJFUtil.print(pm.getNowPage());
	String rowPerPage = KJFUtil.print(pm.getRowPerPage());
	String totalCount = KJFUtil.print(pm.getTotalCount());
	
	// 상단 플래쉬 링크 정보
	String top_pageNum  = "3";
	String top_sub      = "5";
	
	// 좌측 플래쉬 링크 정보
	String left_pageNum = "5";
	String left_sub     = "1";
	
	int int_rnum = KJFUtil.str2int(totalCount)-(KJFUtil.str2int(nowPage) *KJFUtil.str2int(rowPerPage) - KJFUtil.str2int(rowPerPage));
%>

<script language="JavaScript" type="text/javascript">

// 검색
function fn_search() {
    var fm = document.fmParam;

    if (fm.scTextValue.value.trim() == "") {
    	fm.scTextValue.focus();
    	fm.scTextValue.select();
		alert("검색어 내용을 입력해주세요!");
		return;
    }
    
    fm.nowPage.value = 1;
    fm.submit();
}

// FAQ 화면 처리 한다. 
var preNum;
var flag=1;
function changeLayer(sNum)
{
	if(sNum==preNum && flag==1){
		//alert('a');
		temp = eval("document.all."+sNum);
		if(temp){
			temp.style.display='none';
			flag=0;
		}
	}
	else if(sNum==preNum && flag==0){
		//alert('b');
		temp = eval("document.all."+sNum);
		if(temp){
			temp.style.display='';
			flag=1;
		}
	}
	else if(sNum!=preNum){
		//alert('c');
		temp = eval("document.all."+preNum);
		if(temp)
			temp.style.display='none';

		temp1 = eval("document.all."+sNum);
		if(temp1)
			temp1.style.display='';
		flag=1;


	}
	preNum=sNum;
}
</script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="ko">
<head>
<title>FAQ 통합검색 페이지입니다.</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="robots" content="noindex,nofollow">
<meta name="author" content="부산광역시">
<meta name="keywords" content="정보통신공사업시스템">
<meta name="description" content="정보통신공사업시스템">
<meta http-equiv="imagetoolbar" content="no">

<!-- 공통 : S -->
<link href="../css/total.css" rel="stylesheet" type="text/css" />
<script src="../com/js/com.js" type="text/javascript"></script>
<script src="../com/js/topMenu.js" type="text/javascript"></script>
<%	
	if(ruleChk){
		out.print("<script language=\"JavaScript\" src=\"../flash/flash_pub.js\" type=\"text/javascript\"></script><noscript><p>JavaScript</p></noscript>");
	} else {
		out.print("<script language=\"JavaScript\" src=\"../flash/flash.js\" type=\"text/javascript\"></script><noscript><p>JavaScript</p></noscript>");
	}
%>
<!-- 공통 : E -->

</head>
<body>
<noscript><p>자바스크립트를 지원해야 올바르게 동작하는 페이지입니다.</p></noscript>
<!-- 전체 : S -->
<div id="wrapper">
	<div id="skipnavigation">
		<ul>
		<li><a tabindex="0" href="#contents">본문으로 바로가기</a></li>
		<li><a tabindex="0" href="#topmenu">대메뉴로 바로가기</a></li>
		<li><a tabindex="0" href="#leftmenu">소메뉴로 바로가기</a></li>
		</ul>
	</div>
		
	<!-- header : S-->
	<div id="header">
  		<div id="top_navi"><%@ include file="../inc/main_top.jsp" %></div>	
  		<div id="login"><%@ include file="../inc/top_login.jsp" %></div>
  	</div>
  	<!-- header : E -->
  
    <!-- 페이지 타이틀 : S-->
  	<div id="sub_visual"><%@ include file="sub_visual.jsp" %></div>
 	<!-- 페이지 타이틀 : E -->
 	 
  	<!-- BODY : S -->
	<div id="body">
  	
	  	<!-- left : S -->
	  	<div id="sub_left"><%@ include file="left_menu.jsp" %></div>
		<!-- left : E -->	
	
		<!-- 중간컨텐츠 : S -->
   		<div id="sub_contents"><a name="contents"></a>
			
			<!-- 현재위치 : S -->
			<div id="con_head">
				<p class="position">
					<a href="../"><img src="../images/common/icon_home.gif" alt="HOME" /></a> | 커뮤니티 | FAQ | FAQ통합검색
				</p>
			</div>
			<!-- 현재위치 : E -->
	
			<!-- 서브타이틀 : S -->
			<div id="sub_title">
				<h3 class="cont01"><img src="../03_community/images/titile_05_1.gif" alt="FAQ" /></h3>
			</div>
			<!-- 서브타이틀 : E -->
   
		  	<!-- 컨텐츠 : S -->
			<div id="contents">
				<!-- tab : S -->
				<div id="faq_tab">
					<ul>
						<li><a href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=FAQ_PUB">정보통신공사업 관련</a></li>
						<li><a href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=FAQ_USE">사용전검사/착공전설계도확인</a></li>
						<li><a href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=FAQ_HOM">홈페이지관련</a></li>
						<li class='open'>FAQ통합검색</li>
					</ul>	
				</div>
				<!-- tab : E -->
				
				<form name=fmParam method="post" action="../search/SearchAction.do?cmd=FaqUnifiedSearch">
				<input type="hidden" name="nowPage" value="<%=nowPage%>">
				<input type="text" name="none" style="display:none">	
				
				<!--FAQ검색:시작 -->
				<table width="670" border="0" cellspacing="0" cellpadding="0">
	      			<tr>
	        			<td align="right"><img src="../images/bbs/faq_title.gif" /></td>
	      			</tr>
	      			<tr>
	        			<td><img src="../images/bbs/box_top.gif" width="670" height="8" /></td>
	      			</tr>
	      			<tr>
	        			<td height="50" background="../images/bbs/box_bg.gif">
							<!--검색:시작 -->
							<div>								
								<table border="0" align="center">
		          					<tr>
		            					<td width="25"><img src="../images/bbs/faq_glass.gif" /></td>
										<td><input name="scTextValue" type="text" onkeyup="if(event.keyCode == 13) fn_search();" size="35" maxlength="35" value="<%=KJFUtil.print(pm.getScTextValue())%>" title="검색값을 입력해주세요" /></td>
										<td><a href="javascript:fn_search()"><img src="../images/bbs/btn_search.gif" alt="검색" class="cursor" /></a></td>
		          					</tr>
		        				</table>
	        				</div>
							<!--검색:끝 -->
						</td>
	      			</tr>
	      			<tr>
	        			<td><img src="../images/bbs/box_bottom.gif" width="670" height="8" /></td>
	      			</tr>
	    		</table>
				<!--FAQ검색:끝 -->
					
				<!-- 검색결과 : S -->
				<!--FAQ 리스트:시작 -->
				<table width="670" border="0" cellpadding="0" cellspacing="0">
					<tr>
				    	<td height="25"></td>
				  	</tr>

	      			<tr>
	        			<td height="25" class="sfont_gray66B" style="padding:0 0 0 20;">
	        				<span class='search_list_text'>FAQ 통합검색 결과  - <span class="font_orange"><%=KJFUtil.print(rEntity.getRowCnt(), "0")%></span> 건</span>
						</td>
	      			</tr>
	      			<tr>
		        		<td>

							<table border="0" cellpadding="0" cellspacing="0" class="underline">
			       				<tr>
			         				<td><img src="../images/bbs/view_top.gif" width="670" height="2" /></td>
			       				</tr>
								
								<% if(rEntity.getRowCnt() > 0) { %>
									<!--- ============ 등록된 일반글  리스트 START ============= -->
									<%for (int i = 0; i < rEntity.getRowCnt(); i++) {%>
				          				<tr>
				            				<td>
												<table width="670" border="0" cellpadding="4" cellspacing="0" >
				              						
													<tr>
														<td width="40" align="center"><img src="../images/bbs/faq_q.gif" alt="질문" width="20" height="20" /></td>				
														<td>
															<a href="javascript:changeLayer('Q_<%= rEntity.getValue(i,"CT_ID") %><%= rEntity.getValue(i,"BOARD_SEQ") %>')">
															<%=KJFUtil.java2html(KJFUtil.getTitleLimit(rEntity.getValue(i,"SUBJECT"), 50))%></a>
													  	</td>			    
												  		<td width="40">
							
														</td>
												    </tr>
									
												    <tr id="Q_<%= rEntity.getValue(i,"CT_ID") %><%= rEntity.getValue(i,"BOARD_SEQ") %>" style="display:none; background-color: #f7f7f7;">
												      	<td align="center" valign="top" style="padding-top:8px;"><img src="../images/bbs/faq_a.gif" alt="답변" /></td>
														<td class="td_faq_a"><%=KJFUtil.FCKeditorView_no_iframe(rEntity.getValue(i, "CONTENT")) %></td>
														<td class="sfont_grayCCB">&nbsp;</td>
												    </tr>
			    
				            					</table>
											</td>
				          				</tr>
			
				          				<tr>
				            				<td><img src="../images/bbs/faq_underline.gif" width="670" height="1" /></td>
				          				</tr>								
									<% } %>
								<% } else { %>
									<tr>
			            				<td align="center" height="30px" >검색된 정보가 없습니다.</td>
			          				</tr>	
								<% } %>
								
			       			</table>
						</td>
					</tr>
			
	      			<tr>
	        			<td><img src="../images/bbs/faq_underline.gif" width="670" height="1" /></td>
	      			</tr>
	    		</table>
				<!-- 검색결과 : E -->
					
				<!-- 페이징 : S -->
				<div class="center">
					<jsp:include page="../com/inc/paging.jsp" flush="true">
					  <jsp:param name="pagePerScreen" value="10"/>
					  <jsp:param name="rowPerPage" value="<%=rowPerPage%>"/>
					  <jsp:param name="totalCount" value="<%=totalCount%>"/>
					  <jsp:param name="nowPage" value="<%=nowPage %>"/>
					</jsp:include>	
				</div>
				<!-- 페이징 : E -->
				</form>

			</div>
		   	<!-- 컨텐츠 : E -->
		   	
		</div>
		<!-- 중간컨텐츠 : E -->
  	
  	
	  	<!--퀵메뉴 : S -->
		<div id="quick">
		<%@ include file="../inc/quick.jsp" %>
		</div>			
		<!--퀵메뉴 : E -->
 	
 	</div>
 	<!-- BODY : E -->
 	
  	<!-- copyright : S -->
 	<div id="foot"><%@ include file="../inc/copy.jsp" %></div> 
 	<!-- copyright : E -->
 	
</div>
<!-- 전체 : E -->

</body>
</html>
