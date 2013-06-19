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

		
	int nRet = 0;
	Encrypt encrypt = new Encrypt();
	
	String sInput = "0000000000";
	out.println("원문 : " + sInput + "<br>");
	out.println("대칭키 : 88888888<br>");
	// 암호화 (SEED)
	nRet = encrypt.EncryptData(sInput.getBytes("KSC5601"), sInput.getBytes("KSC5601").length, "8888888888888888");
	if (nRet != 0)
	{
		out.println("암호화2 에러내용 : " + encrypt.errmessage + "<br>");
		out.println("암호화2 에러코드 : " + encrypt.errcode + "<br>");
		return;
	}
	
	out.println("대칭키 암호화(SEED) 성공<br>");
	out.println("암호문(SEED) 길이 : " + encrypt.envelopedlen + "<br>");
	
	// Base64 Encoding
	Base64 base64 = new Base64();
	nRet = base64.Encode(encrypt.envelopedbuf, encrypt.envelopedlen);
	if (nRet != 0)
	{
		out.println("base인코드 에러내용 : " + base64.errmessage + "<br>");
		out.println("base인코드에러코드 : " + base64.errcode);
		return;
	}
	String sEncData = new String(base64.contentbuf, "KSC5601");
	out.println("대칭키 암호문(SEED) ( Base64 Encoding) : " + sEncData + "<br>" + sEncData.length());

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

		
	int nRet = 0;
	Encrypt encrypt = new Encrypt();
	
	String sInput = "0000000000";
	out.println("원문 : " + sInput + "< br>");
	out.println("대칭키 : 88888888< br>");
	// 암호화 (SEED)
	nRet = encrypt.EncryptData(sInput.getBytes("KSC5601"), sInput.getBytes("KSC5601").length, "8888888888888888");
	if (nRet != 0)
	{
		out.println("암호화2 에러내용 : " + encrypt.errmessage + "< br>");
		out.println("암호화2 에러코드 : " + encrypt.errcode + "< br>");
		return;
	}
	
	out.println("대칭키 암호화(SEED) 성공< br>");
	out.println("암호문(SEED) 길이 : " + encrypt.envelopedlen + "< br>");
	
	// Base64 Encoding
	Base64 base64 = new Base64();
	nRet = base64.Encode(encrypt.envelopedbuf, encrypt.envelopedlen);
	if (nRet != 0)
	{
		out.println("base인코드 에러내용 : " + base64.errmessage + "< br>");
		out.println("base인코드에러코드 : " + base64.errcode);
		return;
	}
	String sEncData = new String(base64.contentbuf, "KSC5601");
	out.println("대칭키 암호문(SEED) ( Base64 Encoding) : " + sEncData + "< br>" + sEncData.length());

	% >
</font>
</pre>