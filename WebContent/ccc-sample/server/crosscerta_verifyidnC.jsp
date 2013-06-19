<%@ page language="java" import="java.io.*,java.util.*,crosscert.*" %>
<%@ page contentType = "text/html; charset=euc-kr" %>
<%  
	/*-------------------------시작----------------------------*/ 
	response.setDateHeader("Expires",0); 
	response.setHeader("Prama","no-cache"); 

	if(request.getProtocol().equals("HTTP/1.1")) 
	{ 
		response.setHeader("Cache-Control","no-cache"); 
	} 
	/*------------------------- 끝----------------------------*/ 


	
	InputStream inCert=null;
	byte[]Certfilebuf;

	int nRet, nCertlen ;
	
	Properties props = System.getProperties(); // get list of properties
	String file_separator = (String)(props.get("file.separator"));
	String current_dir = "";
	String CertPath = "";
	String Full_path = request.getRealPath(request.getServletPath());
	if (file_separator.equals("\\"))	
	{
		current_dir = Full_path.substring(0,Full_path.lastIndexOf("\\")+1);
		CertPath = current_dir + "\\Cert\\"; 
	}
	else								
	{
		current_dir = Full_path.substring(0,Full_path.lastIndexOf("/")+1);
		CertPath = current_dir + "/Cert/"; 
	}
	

	// 암호화용 인증서 kmCert.der
	try 
	{ 

		inCert = new FileInputStream(new File(CertPath + "kmCert.der"));		
		
	}
	catch (FileNotFoundException e) 
	{
		System.out.println(e);
	}
	catch (IOException e) 
	{
		System.out.println(e);
	}
	
	
	nCertlen = inCert.available();
	Certfilebuf = new byte[nCertlen];
	nRet = inCert.read(Certfilebuf);

	inCert.close();


	// 읽어온 서버인증서 base64인코딩
	// Base64 Encoding
	Base64 base64 = new Base64();
	nRet = base64.Encode(Certfilebuf, nCertlen);
	if (nRet != 0)
	{
		out.println("base인코드 에러내용 : " + base64.errmessage + "<br>");
		out.println("base인코드에러코드 : " + base64.errcode);
		return;
	}
	String sBase64EncodeCert = new String(base64.contentbuf);

			



%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE> New Document </TITLE>
<script language="javascript" src="../init.js"></script>
<SCRIPT LANGUAGE="JavaScript">
<!--
/*
function init()
{
  var Ret;
 
  Ret =  document.CC_Object_id.SetCommonInfoFromVal("211.192.169.70",4502, 
                                               "211.192.169.180",389, 
                                               "211.192.169.180",389,
                                               "CN=ROOT-RSA-CRL,OU=ROOTCA,O=KISA,C=KR", 
                                               "no", 
                                               "1.2.410.200004.2.1|1.2.410.200004.5.1.1.5|1.2.410.200004.5.1.1.7|1.2.410.200004.5.2.1.1|1.2.410.200004.5.2.1.2|1.2.410.200004.5.3.1.1|1.2.410.200004.5.3.1.2|1.2.410.200004.5.3.1.4|1.2.410.200004.5.4.1.1|1.2.410.200004.5.4.1.2|1.2.410.200004.5.4.1.3|1.2.410.200004.5.4.1.4|1.2.410.200004.5.4.1.5|1.2.410.200004.5.4.1.7|1.2.410.200005.1.1.1|1.2.410.200005.1.1.5|1.2.410.200012.1.1.1|1.2.410.200012.1.1.3");
								
    if ( Ret != 0 )
    { 
         alert( "인증 초기 설정에 실패하였습니다." );
         return false;
    }
    else
    {
         return true;
    }
}
*/
function SignData()
{
    

	// 환경설정 함수 콜
	init();
	var ret;
	var signeddata, textin;
	var userdn, EncR;

	if( document.test.src.value == null || document.test.src.value == "" )
	{
		alert("서명할 데이타를 넣어주십시요");
		return;
	}



	// 인증서 선택창 초기화 및 선택된 인증서의 DN 추출
	// DN은 인증기관에서 유니크한 것임.
	userdn = document.CC_Object_id.GetUserDN();
	if( userdn == null || userdn == "" )
	{ 
		alert(" 사용자 DN 선택이 취소 되었습니다.");
		return;
	}
	else
	{

		// 전자서명 생성
		// BSTR *SignData(BSTR Source, BSTR HashAlgo, BSTR Password);
		// parameters : 
		//   Source : 전자서명할 메세지
		//   HashAlgo : 서명 알고리즘 ("SHA1", "MD5") ==> SHA1이 표준
		//   Password : 개인키 복호를 위한 패스워드
		// return value : 생성된 전자서명 값
		// 참 고 : 암호를 넣지 않았을 경우에는 암호 입력 다이얼로그 박스에 입력한다.
		signeddata = document.CC_Object_id.SignData( document.test.src.value, "SHA1", "");

		if( signeddata == null || signeddata == "" )
		{
			errmsg = document.CC_Object_id.GetErrorContent();
			errcode = document.CC_Object_id.GetErrorCode();
			alert( "SignData :"+errmsg );
			return;
		}
		else
		{

			getR = CC_Object_id.GetRFromKey(userdn, "");
			if (getR == "")
			{
				alert("주민번호/사업자번호를 확인할 수 없는 인증서입니다.");
				return;
			}

			
			enc_cert = "<%=sBase64EncodeCert%>";
			document.CC_Object_id.SetValidationFlag(0);//레지스트리 로컬
			EncR =  document.CC_Object_id.EncryptData(enc_cert, getR);
			document.CC_Object_id.SetValidationFlag(1); //ldap

			if (EncR == "")
			{
				errmsg = document.CC_Object_id.GetErrorContent();
				errcode = document.CC_Object_id.GetErrorCode();
				alert( "EncryptData : " + errmsg );
				return;
			}
			else
			{
				document.test.userREnc.value = EncR;
			}

			if (document.test.idn.value == "")
			{
				alert("주민 또는 사업자번호 입력 요망");
				return;
			}

			document.test.signeddata.value = signeddata;
			document.test.action = "crosscerta_verifyidn.jsp";
			document.test.method = "post";
			document.test.submit();
			
		}
	}
}

//-->
</SCRIPT>
</HEAD>

<BODY>
● 전자서명<br>
● 개요 : 인증서 선택창을 통해 선택된 서명용 인증서와 비밀키, 인증경로 정보를 이용해 평문에 대한 전자서명을 수행<br><br>
● 사용된 함수<br><br>
♣ SetCommonInfoFromVal() : 전자인증 웹모듈(CC_Object_id) 초기 환경설정<br>
♣ GetUserDN() : 인증서 선택창 초기화 및 선택된 인증서의 DN 추출<br>
♣ SignData(원본, 알고리즘, 비밀번호) : 전자서명 수행<br>
♣ GetRFromKey(DN, 비밀번호) : 식별번호 추출<br>
♣ EncryptData(인증서, 원문) : 인증서 암호화<br>
&nbsp;&nbsp;&nbsp;인증서는 서버에 존재하는 것을 base64 인코딩된 형태 


<form name="test">
<div align = center>


<table>
  <tr> 
	<td align="center"> 원문<br>
	  <textarea name="src" rows="20" cols="40">01:506-81-32479+02:(주)스마코+03:제조,도소매+04:경북 포항시 남구 연일읍 오천리 634-4번지+05:506-81-00017+06:포항종합제철(주)+07:제조,서비스,도소매,부동산+08:경북 포항시 남구 괴동동 1번지+10:10,790,420+11:1,079,042+12:2001-07-02+13:Cutting Fluid/Oil K MSDS DONG HO DAICOOL,200 L외+14:+15:+16:2001-07-02+30:</textarea>
	</td>
	<td align="center"> 서명값<br>
	  <textarea name="signeddata" rows="20" cols="40"></textarea>
	</td>
  <tr>
  <tr> 
	<td align="center"> 인증서로 암호화된 식별번호
	</td>
	<td align="center"> 
	  <textarea name="userREnc" rows="3" cols="40"></textarea>
	</td>
  <tr>
  <tr> 
	<td align="center"> 주민 또는 사업자번호
	</td>
	<td align="center"> 
	  <input type = text name = idn>
	</td>
  <tr>
	<td align="center"><input type = button value = "전자서명(전송)" OnClick = "SignData()"></td>
	<td align="center">&nbsp;</td>
  </tr>
</table>
</div>
</form>

<!--<iframe src="../refer.html" frameborder="0" width="450" height="300" marginwidth="0" marginheight="0" scrolling="auto"></iframe>//-->                  


</BODY>
</HTML>
<script language="javascript" src="../cc.js"></script>
<!-- 전자인증 모듈 //-->
<script language="javascript" src="../CC_Object.js"></script>


<pre>

<pre>
<font color = red>

=====================================================================================
 ** 소스 **
=====================================================================================

< % @ page language="java" import="java.io.*,java.util.*,<b><font size = 4>crosscert.*</font></b>" % >
< % @ page contentType = "text/html; charset=euc-kr" % >
< %  
	/*-------------------------시작----------------------------*/ 
	response.setDateHeader("Expires",0); 
	response.setHeader("Prama","no-cache"); 

	if(request.getProtocol().equals("HTTP/1.1")) 
	{ 
		response.setHeader("Cache-Control","no-cache"); 
	} 
	/*------------------------- 끝----------------------------*/ 


	
	InputStream inCert=null;
	byte[]Certfilebuf;

	int nRet, nCertlen ;
	
	Properties props = System.getProperties(); // get list of properties
	String file_separator = (String)(props.get("file.separator"));
	String current_dir = "";
	String CertPath = "";
	String Full_path = request.getRealPath(request.getServletPath());
	if (file_separator.equals("\\"))	
	{
		current_dir = Full_path.substring(0,Full_path.lastIndexOf("\\")+1);
		CertPath = current_dir + "\\Cert\\"; 
	}
	else								
	{
		current_dir = Full_path.substring(0,Full_path.lastIndexOf("/")+1);
		CertPath = current_dir + "/Cert/"; 
	}
	

	// 암호화용 인증서 kmCert.der
	try 
	{ 

		inCert = new FileInputStream(new File(CertPath + "kmCert.der"));		
		
	}
	catch (FileNotFoundException e) 
	{
		System.out.println(e);
	}
	catch (IOException e) 
	{
		System.out.println(e);
	}
	
	
	nCertlen = inCert.available();
	Certfilebuf = new byte[nCertlen];
	nRet = inCert.read(Certfilebuf);

	inCert.close();


	// 읽어온 서버인증서 base64인코딩
	// Base64 Encoding
	Base64 base64 = new Base64();
	nRet = base64.Encode(Certfilebuf, nCertlen);
	if (nRet != 0)
	{
		out.println("base인코드 에러내용 : " + base64.errmessage + "< br>");
		out.println("base인코드에러코드 : " + base64.errcode);
		return;
	}
	String sBase64EncodeCert = new String(base64.contentbuf);

			



% >

</font>
</pre>