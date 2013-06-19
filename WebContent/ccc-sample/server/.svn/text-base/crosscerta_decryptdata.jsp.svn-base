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

	String EncString = "MIHxAgECMYGuMIGrAgECgBRxJEsC1Xq/Ct6AqZqI3S5JKW+5YjANBgkqhkiG9w0BAQEFAASBgCu0HGTUgNnogSIMawpn5KU6bjE+Y6AMClIO1fdu2gxGkDtLKcwpQRBH/YGx0Z8zC6lvpK9TWGOL+P9DDwRVog8L5xOJ//PYHAekrY2AGowYtKjEDJthfExYEWC5gg313zWoUvw0yOzyN3LSA+wKHsDq/tCjI6Qxhp/uhyH6BujFMDsGCSqGSIb3DQEHATAcBggqgxqMmkQBBAQQ9UgOh1GzE1JnBERMNFqXX4AQyZuKlaZtTLU8SbtTMwtE1Q==";

	InputStream inPri=null;
	InputStream inCert=null;
	byte[] Prifilebuf, Certfilebuf;

	int nRet,nPrilen ,nCertlen ;
	
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

	// 복호용 개인키 추출
	try 
	{	
		inPri =  new FileInputStream(new File(CertPath + "kmPri.key"));
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
	
	
		
	
		nPrilen = inPri.available();
		Prifilebuf = new byte[nPrilen];
			
		nRet = inPri.read(Prifilebuf);
		
		nCertlen = inCert.available();
		Certfilebuf = new byte[nCertlen];
		
		nRet = inCert.read(Certfilebuf);
			
		// 개인키 추출
		PrivateKey CPrivateKey = new PrivateKey();
		nRet = CPrivateKey.DecryptPriKey("88888888", Prifilebuf, nPrilen);
		
		if (nRet != 0)
		{
			out.println("에러내용 : " + CPrivateKey.errmessage + "<br>");
			out.println("에러코드 : " + CPrivateKey.errcode + "<br>");
			return;
		}


		// Base64 Decoding
		Base64 CBase64 = new Base64();
		nRet = CBase64.Decode(EncString.getBytes(), EncString.length());
		if (nRet != 0)
		{
			out.println("base64디코드 에러내용 : " + CBase64.errmessage);
			out.println("base64디코드 에러코드 : " + CBase64.errcode);
			return;
		}

		Decrypt decrypt = new Decrypt();
		nRet = decrypt.DecEnvelopedData(CPrivateKey.prikeybuf, CPrivateKey.prikeylen, Certfilebuf, nCertlen, CBase64.contentbuf, CBase64.contentlen);
		if (nRet != 0)
		{
			out.println("복호화1 에러내용 : " + decrypt.errmessage);
			out.println("복호화1 에러코드 : " + decrypt.errcode);
			//return;
		}
		else
		{
			out.println("복호문(PKCS) 결과 : " + Integer.toHexString(nRet) + "<br>");
			String sOrgData2 = new String( decrypt.contentbuf, "KSC5601");
			out.println("원문 : " + sOrgData2 + "<br>");
			out.println("원문길이 : String - " + sOrgData2.length() + ", byte - " + decrypt.contentlen );
		}
		//out.println("cn=테스트법인,ou=법인,ou=한국전자인증,ou=licensedCA,o=CrossCert,c=KR 인증서 복호화 성공<br> " );
		

		
		%>
<script language="javascript" src="../cc.js"></script>

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

	String EncString = "MIHxAgECMYGuMIGrAgECgBRxJEsC1Xq/Ct6AqZqI3S5JKW+5YjANBgkqhkiG9w0BAQEFAASBgCu0HGTUgNnogSIMawpn5KU6bjE+Y6AMClIO1fdu2gxGkDtLKcwpQRBH/YGx0Z8zC6lvpK9TWGOL+P9DDwRVog8L5xOJ//PYHAekrY2AGowYtKjEDJthfExYEWC5gg313zWoUvw0yOzyN3LSA+wKHsDq/tCjI6Qxhp/uhyH6BujFMDsGCSqGSIb3DQEHATAcBggqgxqMmkQBBAQQ9UgOh1GzE1JnBERMNFqXX4AQyZuKlaZtTLU8SbtTMwtE1Q==";

	InputStream inPri=null;
	InputStream inCert=null;
	byte[] Prifilebuf, Certfilebuf;

	int nRet,nPrilen ,nCertlen ;
	
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

	// 복호용 개인키 추출
	try 
	{	
		inPri =  new FileInputStream(new File(CertPath + "kmPri.key"));
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
	
	
		
	
		nPrilen = inPri.available();
		Prifilebuf = new byte[nPrilen];
			
		nRet = inPri.read(Prifilebuf);
		
		nCertlen = inCert.available();
		Certfilebuf = new byte[nCertlen];
		
		nRet = inCert.read(Certfilebuf);
			
		// 개인키 추출
		PrivateKey CPrivateKey = new PrivateKey();
		nRet = CPrivateKey.DecryptPriKey("88888888", Prifilebuf, nPrilen);
		
		if (nRet != 0)
		{
			out.println("에러내용 : " + CPrivateKey.errmessage + "< br>");
			out.println("에러코드 : " + CPrivateKey.errcode + "< br>");
			return;
		}


		// Base64 Decoding
		Base64 CBase64 = new Base64();
		nRet = CBase64.Decode(EncString.getBytes(), EncString.length());
		if (nRet != 0)
		{
			out.println("base64디코드 에러내용 : " + CBase64.errmessage);
			out.println("base64디코드 에러코드 : " + CBase64.errcode);
			return;
		}

		Decrypt decrypt = new Decrypt();
		nRet = decrypt.DecEnvelopedData(CPrivateKey.prikeybuf, CPrivateKey.prikeylen, Certfilebuf, nCertlen, CBase64.contentbuf, CBase64.contentlen);
		if (nRet != 0)
		{
			out.println("복호화1 에러내용 : " + decrypt.errmessage);
			out.println("복호화1 에러코드 : " + decrypt.errcode);
			//return;
		}
		else
		{
			out.println("복호문(PKCS) 결과 : " + Integer.toHexString(nRet) + "< br>");
			String sOrgData2 = new String( decrypt.contentbuf, "KSC5601");
			out.println("원문 : " + sOrgData2 + "< br>");
			out.println("원문길이 : String - " + sOrgData2.length() + ", byte - " + decrypt.contentlen );
		}
		//out.println("cn=테스트법인,ou=법인,ou=한국전자인증,ou=licensedCA,o=CrossCert,c=KR 인증서 복호화 성공< br> " );
		

		
		% >


</font>
</pre>

