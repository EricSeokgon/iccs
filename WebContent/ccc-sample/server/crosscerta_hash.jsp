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

	Origin_Data = "sdfsdfsf";
	
	Hash hash = new Hash();

	// GetHash(byte[] data, int datalen)
	// 성공시 0 리턴하고 멤버변수 contentbuf에 해쉬값이 채워짐
	int nRet;

	nRet = hash.GetHash(Origin_Data.getBytes(), Origin_Data.getBytes().length);

	if(nRet==0) 
	{
		
		out.println("해쉬값 추출 결과 : 성공<br>") ;
		strHashValue = new String(hash.contentbuf);
		out.println("해쉬값 : " + strHashValue);
		
	}
	else
	{
		out.println("해쉬값 추출 결과 : 실패<br>") ;
		
		
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

	
	String Origin_Data = "";
	String strHashValue = "";

	Origin_Data = "01:506-81-32479+02:(주)스마코+03:제조,도소매+04:경북 포항시 남구 연일읍 오천리 634-4번지+05:506-81-00017+06:포항종합제철(주)+07:제조,서비스,도소매,부동산+08:경북 포항시 남구 괴동동 1번지+10:10,790,420+11:1,079,042+12:2001-07-02+13:Cutting Fluid/Oil K MSDS DONG HO DAICOOL,200 L외+14:+15:+16:2001-07-02+30:";
	
	Hash hash = new Hash();

	// GetHash(byte[] data, int datalen)
	// 성공시 0 리턴하고 멤버변수 contentbuf에 해쉬값이 채워짐
	int nRet;

	nRet = hash.GetHash(Origin_Data.getBytes(), Origin_Data.getBytes().length);

	if(nRet==0) 
	{
		
		out.println("해쉬값 추출 결과 : 성공< br>") ;
		strHashValue = new String(hash.contentbuf);
		out.println("해쉬값 : " + strHashValue);
		
	}
	else
	{
		out.println("해쉬값 추출 결과 : 실패< br>") ;
		
		
	}
% >

</font>
</pre>