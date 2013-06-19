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


	
	String sEncData = request.getParameter("enc_data");		// 암호화된 값
	String algolithmFlag = request.getParameter("algolithmFlag");		// 원문
	String passwd = request.getParameter("passwd"); // 대칭키

	int nFlag = Integer.parseInt(algolithmFlag);
	int nRet = 0;


	Base64 base64 = new Base64();
	nRet = base64.Decode(sEncData.getBytes(), sEncData.length());
	if (nRet != 0)
	{
		out.println("base64디코드 에러내용 : " + base64.errmessage + "<br>");
		out.println("base64디코드 에러코드 : " + base64.errcode + "<br>");
		return;
	}
	out.println("암호문(SEED) 길이 (Base64 Decoding) : " + base64.contentlen + "<br>");

	
	// 복호화 (SEED)
	Decrypt decrypt = new Decrypt();
	
	nRet = decrypt.DecryptData2(base64.contentbuf, base64.contentlen, passwd, nFlag);
	if (nRet != 0)
	{
		out.println("복호화2 에러내용 : " + decrypt.errmessage + "<br>");
		out.println("복호화2 에러코드 : " + decrypt.errcode + "<br>");
		return;
	}

	
	out.println("복호문(PKCS) 결과 : " + Integer.toHexString(nRet) + "<br>");
	String sOrgData2 = new String( decrypt.contentbuf, "KSC5601");
	out.println("원문 : " + sOrgData2 + "<br>");
	out.println("원문길이 : String - " + sOrgData2.length() + ", byte - " + decrypt.contentlen );
/**/
		

%>
