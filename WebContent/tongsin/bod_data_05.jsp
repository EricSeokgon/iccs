<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../inc/user_inc.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
 
 <!-- 공통 : S -->
	<%@ include file="inc/inc_head.jsp" %>
 <!-- 공통 : E -->
 
 </head>

 <body onLoad="MM_preloadImages('../tongsin/images/common/menu01_on.gif','../tongsin/images/common/menu02_on.gif','../tongsin/../tongsin/images/common/menu03_on.gif','../tongsin/images/common/menu04_on.gif','../tongsin/images/common/menu05_on.gif','../tongsin/images/common/menu06_on.gif','../tongsin/images/sub/left_menu11_on.gif')">
 <table width="950" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td>
     <div id="main_top_navi"><%@ include file="../tongsin/inc/main_top.jsp" %></div>	
</td>
   </tr>
   <tr>
     <td><script type="text/javascript">flash('../tongsin/flash/sub_skin.swf','950','145');</script><noscript><p>JavaScript</p></noscript></td>
   </tr>
   <tr>
     <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
       <tr>
         <td width="245" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
           <tr>
             <td><img src="../tongsin/images/sub/left_title03.gif" alt="자료실" width="245" height="67"></td>
           </tr>
           <tr>
             <td style="padding-left:10px;"><table border="0" cellspacing="0" cellpadding="0">
               <tr>
                 <td><a href="javascript:go_bbs('BOD_DATA_1');" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('3m1','','../tongsin/images/sub/left_menu31_on.gif',1)"><img src="../tongsin/images/sub/left_menu31.gif" alt="연구관련자료" name="3m1" width="195" height="31" border="0"></a></td>
               </tr>
               <tr>
                 <td><a href="javascript:go_bbs('BOD_DATA_2');" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('3m2','','../tongsin/images/sub/left_menu32_on.gif',1)"><img src="../tongsin/images/sub/left_menu32.gif" alt="동영상자료" name="3m2" width="195" height="31" border="0"></a></td>
               </tr>
               <tr>
                 <td><a href="javascript:go_bbs('BOD_DATA_3');" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('3m3','','../tongsin/images/sub/left_menu33_on.gif',1)"><img src="../tongsin/images/sub/left_menu33.gif" alt="교육강의자료" name="3m3" width="195" height="31" border="0"></a></td>
               </tr>
               <tr>
                 <td><a href="javascript:go_bbs('BOD_DATA_4');" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('3m4','','../tongsin/images/sub/left_menu34_on.gif',1)"><img src="../tongsin/images/sub/left_menu34.gif" alt="최근IT소식" name="3m4" width="195" height="31" border="0"></a></td>
               </tr>
               <tr>
                 <td><a href="javascript:go_bbs('BOD_DATA_5');" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('3m5','','../tongsin/images/sub/left_menu35_on.gif',1)"><img src="../tongsin/images/sub/left_menu35_on.gif" alt="ubiquitous" name="3m5" width="195" height="31" border="0"></a></td>
               </tr>
               <tr>
                 <td><a href="javascript:go_bbs('BOD_DATA_6');" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('3m6','','../tongsin/images/sub/left_menu36_on.gif',1)"><img src="../tongsin/images/sub/left_menu36.gif" alt="기타자료" name="3m6" width="195" height="31" border="0"></a></td>
               </tr>
               <tr>
                 <td><a href="javascript:go_bbs('BOD_DATA_7');" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('3m7','','../tongsin/images/sub/left_menu37_on.gif',1)"><img src="../tongsin/images/sub/left_menu37.gif" alt="IT연수보고자료" name="3m7" width="195" height="31" border="0"></a></td>
               </tr>

               
             </table></td>
           </tr>
           

         </table></td>
         <td valign="top"><table width="690" border="0" cellspacing="0" cellpadding="0">
           <tr>
             <td width="691" height="30">&nbsp;</td>
           </tr>
           <tr>
             <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
               <tr>
                 <td><img src="../tongsin/images/sub/title35.gif" alt="ubiquitous" width="116" height="18"></td>
                 <td align="right"><a href="/">HOME</a>  &gt; 자료실 &gt; ubiquitous </td>
               </tr>
             </table></td>
           </tr>
           <tr>
             <td>&nbsp;</td>
           </tr>
           <tr>
           
							<div id="contents"><%@include file="../bbs/BbsK.jsp"%></div>
							
							
           </tr>
         </table></td>
       </tr>
     </table></td>
   </tr>
   <tr>
     <td>&nbsp;</td>
   </tr>
   <tr>
     <%@ include file="../tongsin/inc/copy.jsp" %>
   </tr>
 </table>
  
</body>
</html>
