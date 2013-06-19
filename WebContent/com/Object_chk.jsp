<%@ page contentType = "text/html; charset=utf-8" %>

<html>
<head>
<title>인증서관리프로그램 설치</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>

<body bgcolor="white" text="black" link="blue" vlink="purple" alink="red">

<div align="center">
  	<table border cellspacing="0" width="406" height="386">
    	<tr>
        	<td width="964">        	
          		<table width="551" border="0" cellspacing="0" cellpadding="0">
            		<tr>
              			<td>
                			<table width="100%" border="0" cellspacing="0" cellpadding="0">
                  				<tr>                    
				                  	<td width="10%"><img src="../images/CCimages/cs_01.jpg" width="50" height="136" border="0"></td>				                    
				                  	<td width="79%"><img src="../images/CCimages/cs_02.jpg" width="443" height="136" border="0"></td>				                    
				                  	<td width="11%"><img src="../images/CCimages/cs_03.jpg" width="58" height="136" border="0"></td>
				           		</tr>
                  				<tr>                    
                  					<td width="10%"><img src="../images/CCimages/cs_04.jpg" width="50" height="11" border="0"></td>                    
                  					<td width="79%"><img src="../images/CCimages/load.gif" width="443" height="11" border="0" align="middle"></td>                    
                  					<td width="11%"><img src="../images/CCimages/cs_05.jpg" width="58" height="11" border="0"></td>
                  				</tr>
                  				<tr>                    
                  					<td width="10%"><img src="../images/CCimages/cs_06.jpg" width="50" height="19" border="0"></td>                    
                  					<td width="79%"><img src="../images/CCimages/cs_07.jpg" width="443" height="19" border="0"></td>                    
                  					<td width="11%"><img src="../images/CCimages/cs_08.jpg" width="58" height="19" border="0"></td>
                  				</tr>
                			</table>
              			</td>
            		</tr>
            
            		<tr>
              			<td>                
              				<table width="100%" border="0" cellspacing="0" cellpadding="0">                  
                				<tr>                    
                  					<td align="center"><br>
                    					<table width="80%" border="0" cellspacing="0" cellpadding="0">                        
                      						<tr>                          
					                        	<td width="7%" align="center"><img src="../images/CCimages/cs_dot01.gif" width="11" height="11" border="0"></td>
					                        	<td width="93%" height="20"><font size="2" color="#333333">사용자가   최초 접속하는 경우 컴퓨터 및 네트웍의 </font></td>
					                      	</tr>
                        
						                 	<tr>                          
						                        <td width="7%" align="center">&nbsp;</td>
						                        <td width="93%" height="20"><font size="2" color="#333333">상황에 따라 많은 시간이 소요되는 경우가 있습니다.</font></td>
						                	</tr>
                        
						                  	<tr>                          
						                        <td width="7%" align="center"><img src="../images/CCimages/cs_dot01.gif" width="11" height="11" border="0"></td>
						                        <td width="93%" height="20"><font color="#333333" size="2">인증서관리 프로그램의 설치여부를 묻는</font><font size="2" color="#FF6600">
						                        <b><font color="#FF6633">&quot;보안경고&quot;</font></b> </font><font color="#000066" size="2"><font color="#333333">창이 뜨면</font></font></td>
						                 	</tr>
                        
						               		<tr>                          
						                    	<td width="7%" align="center">&nbsp;</td>
						                        <td width="93%" height="20">
						                        	<font size="2" color="#333333">반드시</font><font size="2"> <font color="#FF6633"><b>&quot;</b></font></font><b>
						                        	<font color="#FF6633" size="2">예&quot;</font></b><font color="#333333" size="2">를 클릭하십시요.</font><font color="#FF6633" size="2"> 
						                           	<b>&nbsp;&quot;아니오&quot;</b></font><font color="#333333" size="2">를 클릭하시는 경우는 </font></td>
						                 	</tr>
                        
						                 	<tr>                          
						                        <td width="7%" align="center">&nbsp;</td>
						                        <td width="93%" height="20"><font color="#333333" size="2">프로그래밍 설치되지 않습니다.</font>                    </td>
						                  	</tr>
                        
					                      	<tr>                          
					                        	<td width="7%" align="center"><img src="../images/CCimages/cs_dot01.gif" width="11" height="11" border="0"></td>
					                        	<td width="93%" height="20"><font color="#FF6633" size="2">화면이 장시간 정지 시에는 프로그램을  수동설치하셔야 합니다.</font></td>
					                      	</tr>
                        
					                      	<tr>                          
					                        	<td width="7%" align="center">&nbsp;</td>
					                       		<td width="93%" height="20" align="center"><br>
					                            	<a href="../com/downLoad.jsp?Lo=INSTALL_FILE_DIR&Sub_Lo=&SYS_FILE_NAME=install.exe&FILE_REAL_NAME=install.exe"><img src="../images/CCimages/cs_download.gif" width="196" height="21" border="0"></a>
												</td>
					                      	</tr>
                      
                    					</table>
                    					<br><br><br>                    
                  					</td>
                				</tr>                
              				</table>
              			</td>
            		</tr>            
          		</table>   
      		</td>
    	</tr>
	</table>
</div>

<object id="CC_Object_id"
      classid="CLSID:A099920B-630C-426B-91EC-737685CEEE17"
	  codebase="../com/certificate/cab/AxCrossCert.cab#Version=2,5,0,13"
      width= Document.body.clientWidth
      height= Document.body.clientHeight>
</object>

<SCRIPT LANGUAGE="JavaScript">
<!--
if (CC_Object_id.object == null) {
	//location="./manual_install.html" //수동설치
} else {
	opener.location.reload() 
	self.close();
}
-->
</SCRIPT>
</body>
</html>
