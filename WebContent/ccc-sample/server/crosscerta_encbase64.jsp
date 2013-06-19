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
	
	String sInput = "주식회사보광훼미리마트";
	out.println("원문 : " + sInput + "<br>");
		
	// Base64 Encoding
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
	out.println("Base64 Encoding) : " + sBase64EncodeData + "<br>");

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
	
	String sInput = "가나다라";
	out.println("원문 : " + sInput + "< br>");
		
	// Base64 Encoding
	Base64 base64 = new Base64();
	nRet = base64.Encode(sInput.getBytes("KSC5601"), sInput.getBytes("KSC5601").length);
	if (nRet != 0)
	{
		out.println("base인코드 에러내용 : " + base64.errmessage + "< br>");
		out.println("base인코드에러코드 : " + base64.errcode);
		return;
	}
	String sBase64EncodeData = new String(base64.contentbuf, "KSC5601");
	out.println("Base64 Encoding 성공< br>");
	out.println("Base64 Encoding) : " + sBase64EncodeData + "< br>");

% >
</font>
</pre>