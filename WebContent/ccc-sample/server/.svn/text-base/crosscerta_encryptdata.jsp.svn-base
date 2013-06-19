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
						
		

		Encrypt encrypt = new Encrypt();
		
		nRet = encrypt.EncEnvelopedData(Certfilebuf, nCertlen, "가나다".getBytes("KSC5601"), "가나다".getBytes("KSC5601").length);
		if (nRet != 0)
		{
			System.out.println("암호화1 에러내용 : " + encrypt.errmessage);
			System.out.println("암호화1 에러코드 : " + encrypt.errcode);
			return;
		}
		out.println("<br><br> " );
		
		out.println("암호문(PKCS) 길이 : " + encrypt.envelopedlen  + "<br>" );


		// 바이너리 테이타 base64인코딩
		String EncString = "";

		Base64 CBase64 = new Base64();
							  //바이트 배열     //바이트 배열길이
		nRet = CBase64.Encode(encrypt.envelopedbuf, encrypt.envelopedlen);
		if(nRet==0) 
		{
			out.println("암호화값 Base64 Encode 결과 : 성공<br>") ;
			EncString = new String(CBase64.contentbuf);
			out.println("암호화값 Base64 Decode 값 : " + EncString + "<br>끝") ;
		}
		else
		{
			out.println("암호화값 Base64 Decode 결과 : 실패<br>") ;
			out.println("에러내용 : " + CBase64.errmessage + "<br>");
			out.println("에러코드 : " + CBase64.errcode + "<br>");
		}
	
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
						
		

		Encrypt encrypt = new Encrypt();
		
		nRet = encrypt.EncEnvelopedData(Certfilebuf, nCertlen, "가나다".getBytes("KSC5601"), "가나다".getBytes("KSC5601").length);
		if (nRet != 0)
		{
			System.out.println("암호화1 에러내용 : " + encrypt.errmessage);
			System.out.println("암호화1 에러코드 : " + encrypt.errcode);
			return;
		}
		out.println("< br>< br> " );
		//out.println("cn=테스트법인,ou=법인,ou=한국전자인증,ou=licensedCA,o=CrossCert,c=KR 인증서 암호화 성공 원문 가나다 < br> " );
		out.println("암호문(PKCS) 길이 : " + encrypt.envelopedlen  + "< br>" );


		// 바이너리 테이타 base64인코딩
		String EncString = "";

		Base64 CBase64 = new Base64();
							  //바이트 배열     //바이트 배열길이
		nRet = CBase64.Encode(encrypt.envelopedbuf, encrypt.envelopedlen);
		if(nRet==0) 
		{
			out.println("암호화값 Base64 Encode 결과 : 성공< br>") ;
			EncString = new String(CBase64.contentbuf);
			out.println("암호화값 Base64 Decode 값 : " + EncString + "< br>끝") ;
		}
		else
		{
			out.println("암호화값 Base64 Decode 결과 : 실패< br>") ;
			out.println("에러내용 : " + CBase64.errmessage + "< br>");
			out.println("에러코드 : " + CBase64.errcode + "< br>");
		}
	
		% >

</font>
</pre>