<%@ page language="java" import="java.io.*,java.util.*,crosscert.*" %>
<%@ page contentType = "text/html; charset=euc-kr" %>

<%  
	/*-------------------------����----------------------------*/ 
	response.setDateHeader("Expires",0); 
	response.setHeader("Prama","no-cache"); 

	if(request.getProtocol().equals("HTTP/1.1")) 
	{ 
		response.setHeader("Cache-Control","no-cache"); 
	} 
	/*------------------------- ��----------------------------*/ 
		
	int nRet = 0;
	String sEncData = "sKGzqrTZtvM=";
	out.println("base64 ���ڵ� ����Ÿ : " + sEncData + "<br>");
	// Base64 Decoding
	Base64 base64 = new Base64();
	nRet = base64.Decode(sEncData.getBytes(), sEncData.length());
	if (nRet != 0)
	{
		out.println("base64���ڵ� �������� : " + base64.errmessage + "<br>");
		out.println("base64���ڵ� �����ڵ� : " + base64.errcode + "<br>");
		return;
	}
	out.println("Base64 Decoding : ����<br>");
	String OrignData = new String(base64.contentbuf);
	out.println("Base64 Decoding : " + OrignData + "<br>");
	
		
		
		

%>
<script language="javascript" src="../cc.js"></script>

<pre>
<font color = red>



=====================================================================================
 ** �ҽ� **
=====================================================================================


< % @ page language="java" import="java.io.*,java.util.*,<b><font size = 4>crosscert.*</font></b>" % >
< % @ page contentType = "text/html; charset=euc-kr" % >

< %  
	/*-------------------------����----------------------------*/ 
	response.setDateHeader("Expires",0); 
	response.setHeader("Prama","no-cache"); 

	if(request.getProtocol().equals("HTTP/1.1")) 
	{ 
		response.setHeader("Cache-Control","no-cache"); 
	} 
	/*------------------------- ��----------------------------*/ 
		
	int nRet = 0;
	String sEncData = "sKGzqrTZtvM=";
	out.println("base64 ���ڵ� ����Ÿ : " + sEncData + "< br>");
	// Base64 Decoding
	Base64 base64 = new Base64();
	nRet = base64.Decode(sEncData.getBytes(), sEncData.length());
	if (nRet != 0)
	{
		out.println("base64���ڵ� �������� : " + base64.errmessage + "< br>");
		out.println("base64���ڵ� �����ڵ� : " + base64.errcode + "< br>");
		return;
	}
	out.println("Base64 Decoding : ����< br>");
	String OrignData = new String(base64.contentbuf);
	out.println("Base64 Decoding : " + OrignData + "< br>");
	
		
		
		

% >
</font>
</pre>