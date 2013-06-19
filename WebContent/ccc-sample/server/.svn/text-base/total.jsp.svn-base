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

	
	String Origin_Data = "";
	String strHashValue = "";

	Origin_Data = "abcd";
	
	Hash hash = new Hash();

	// GetHash(byte[] data, int datalen)
	// 성공시 0 리턴하고 멤버변수 contentbuf에 해쉬값이 채워짐
	int nRet;

	nRet = hash.GetHash(Origin_Data.getBytes(), Origin_Data.getBytes().length);

	if(nRet==0) 
	{
		
		out.println("해쉬값 추출 결과 : 성공<br>") ;
		strHashValue = new String(hash.contentbuf);
		out.println("해쉬값 : " + strHashValue + "<br><br>");
		
	}
	else
	{
		out.println("해쉬값 추출 결과 : 실패<br>") ;
	}

	// Base64 Encoding
	String sInput = "가나다라";
	Base64 base64 = new Base64();
	nRet = base64.Encode(sInput.getBytes("KSC5601"), sInput.getBytes("KSC5601").length);
	if (nRet != 0)
	{
		out.println("base인코드 에러내용 : " + base64.errmessage + "<br>");
		out.println("base인코드에러코드 : " + base64.errcode);
		return;
	}
	String sBase64EncodeData = new String(base64.contentbuf, "KSC5601");
	out.println("Base64 Encoding 성공<br>");
	out.println("Base64 Encoding) : " + sBase64EncodeData + "<br><br>");


	// Base64 Decoding
	String sEncData = "sKGzqrTZtvM=";
	out.println("base64 인코딩 테이타 : " + sEncData + "<br>");

	nRet = base64.Decode(sEncData.getBytes(), sEncData.length());
	if (nRet != 0)
	{
		out.println("base64디코드 에러내용 : " + base64.errmessage + "<br>");
		out.println("base64디코드 에러코드 : " + base64.errcode + "<br>");
		return;
	}
	out.println("Base64 Decoding : 성공<br>");
	String OrignData = new String(base64.contentbuf);
	out.println("Base64 Decoding : " + OrignData + "<br><br>");

	InputStream inPri=null;
	InputStream inCert=null;
	byte[] Prifilebuf, Certfilebuf;

	int nPrilen ,nCertlen ;
	
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

<pre>
<font color = red>

