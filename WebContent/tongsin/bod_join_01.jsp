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
             <td><img src="../tongsin/images/sub/left_title05.gif" alt="업무연락" width="245" height="67"></td>
           </tr>
           <tr>
             <td style="padding-left:10px;"><table border="0" cellspacing="0" cellpadding="0">
               <tr>
                 <td><a href="javascript:go_bbs('BOD_JOIN_01');" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('5m1','','../tongsin/images/sub/left_menu51_on.gif',1)"><img src="../tongsin/images/sub/left_menu51_on.gif" alt="업무연락" name="5m1" width="195" height="31" border="0"></a></td>
               </tr>
               <tr>
                 <td><a href="javascript:go_bbs('BOD_JOIN_02');" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('5m2','','../tongsin/images/sub/left_menu52_on.gif',1)"><img src="../tongsin/images/sub/left_menu52.gif" alt="소식지" name="5m1" width="195" height="31" border="0"></a></td>
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
                 <td><img src="../tongsin/images/sub/title51.gif" alt="업무연락" width="74" height="20"></td>
                 <td align="right"><a href="/">HOME</a>  &gt; 업무연락  </td>
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
