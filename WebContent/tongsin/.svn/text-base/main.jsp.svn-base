<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="kjf.cfg.*" %>
<%@ page import="kjf.ops.*" %>
<%@ page import="kjf.util.*" %>
<%@ include file="../inc/user_inc.jsp" %>
<%	
	//상단 플래쉬 링크 정보
	String top_pageNum  = "";
	String top_sub      = "";
	// 좌측 플래쉬 링크 정보
	String left_pageNum = "";
	String left_sub     = "";
	
	ReportEntity NOTICE     = (ReportEntity) request.getAttribute("NOTICE");		// 공지사항
	ReportEntity PDS     		= (ReportEntity) request.getAttribute("PDS");					// 자료실
	ReportEntity ITINFO = (ReportEntity) request.getAttribute("ITINFO");	// IT최근소식
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="ko">
<head>

<!-- 공통 : S -->
	<%@ include file="inc/inc_head.jsp" %>
<!-- 공통 : E -->

</head>
<body onLoad="MM_preloadImages('../tongsin/images/common/menu01_on.gif','../tongsin/images/common/menu02_on.gif','../tongsin/images/common/menu03_on.gif','../tongsin/images/common/menu04_on.gif','../tongsin/images/common/menu05_on.gif','../tongsin/images/common/menu06_on.gif')">
<!-- 전체 : S -->

		
	<!-- header : S-->	
 <table width="950" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td>
     	<table width="100%" border="0" cellspacing="0" cellpadding="0">
   			<tr>
     			<td>
     				<div id="main_top_navi"><%@ include file="inc/main_top.jsp" %></div>	
		 			</td>
   			</tr>
  		</table>
  	</td>
   </tr>	
   <tr>
     <td height="30">&nbsp;</td>
   </tr>
	<!-- main flash : S -->
	 <tr>
	 	<td>
		  <table width="100%" border="0" cellspacing="0" cellpadding="0">
		  	<tr>
		    	<td align="center" valign="top" style="background:url(../tongsin/images/main/list_kan.gif) no-repeat top right;">
		      	<table width="260" border="0" cellspacing="0" cellpadding="0">
		        	<tr>
		          	<td height="25">
		          		<img src="../tongsin/images/main/title_notice.gif" alt="공지사항" width="47" height="13">
		            	<%@ include file="../tongsin/inc/inc_notice.jsp" %>				
		            </td>
		          </tr>
		          <tr>
		             <td height="25"><a href="javascript:go_bbs('BOD_NOTICE_11');"><img src="../tongsin/images/main/btn_more.gif" alt="더보기" width="55" height="17" border="0"></a></td>
		          </tr>
		        </table>
		      </td>
		      <td align="center" valign="top" style="background:url(../tongsin/images/main/list_kan.gif) no-repeat top right;">
		      	<table width="260" border="0" cellspacing="0" cellpadding="0">
		        	<tr>
		          	<td height="25">
		          		<img src="../tongsin/images/main/title_data.gif" alt="자료실" width="35" height="13">
		          	  <%@ include file="inc/inc_pds.jsp" %>
		          	</td>
		          </tr>
		          <tr>
		            <td height="25"><a href="javascript:go_bbs('BOD_DATA_4');"><img src="../tongsin/images/main/btn_more.gif" alt="더보기" width="55" height="17" border="0"></a></td>
		          </tr>
		        </table>
		      </td>
		      <td align="center" valign="top">
		      	<table width="260" border="0" cellspacing="0" cellpadding="0">
		        	<tr>
		          	<td height="25">
		          		<img src="../tongsin/images/main/title_news.gif" alt="최근 IT소식" width="57" height="13">
		          		<%@ include file="inc/inc_itinfo.jsp" %>
		          		</td>
		          </tr>
		          <tr>
		             <td height="25"><a href="javascript:go_bbs('BOD_DATA_4');"><img src="../tongsin/images/main/btn_more.gif" alt="더보기" width="55" height="17" border="0"></a></td>
		          </tr>
		         </table>
		       </td>
		     </tr>
		  </table>
		</td>
	 </tr>	 
   <tr>
     <td>&nbsp;</td>
   </tr>	   
   <tr>
     <td align="center"><table border="0" cellspacing="0" cellpadding="0">
       <tr>
         <td><a href="javascript:go_bbs('BOD_RNDS_DATA_1');"><img src="../tongsin/images/main/quick01.gif" alt="연구회 1반" width="231" height="104" border="0"></a></td>
         <td><a href="javascript:go_bbs('BOD_RNDS_DATA_2');"><img src="../tongsin/images/main/quick02.gif" alt="연구회 2반" width="230" height="104" border="0"></a></td>
         <td><a href="javascript:go_bbs('BOD_RNDS_DATA_3');"><img src="../tongsin/images/main/quick03.gif" alt="연구회 3반" width="230" height="104" border="0"></a></td>
         <td><a href="javascript:go_bbs('BOD_RNDS_DATA_4');"><img src="../tongsin/images/main/quick04.gif" alt="연구회 4반" width="231" height="104" border="0"></a></td>
       </tr>
     </table></td>
   </tr>
   
   <tr>
     <td><script type="text/javascript">flash('../tongsin/flash/main.swf','950','243');</script><noscript><p>JavaScript</p></noscript></td>
   </tr>
   <tr>
		<%@ include file="inc/copy.jsp" %>
	 </tr>    		
 </table>		
  	<!-- copyright : S -->
 	<!-- copyright : E -->
</body>
</html>
