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

	
	String Origin_Data = "";
	String strHashValue = "";

	Origin_Data = "sdfsdfsf";
	
	Hash hash = new Hash();

	// GetHash(byte[] data, int datalen)
	// ������ 0 �����ϰ� ������� contentbuf�� �ؽ����� ä����
	int nRet;

	nRet = hash.GetHash(Origin_Data.getBytes(), Origin_Data.getBytes().length);

	if(nRet==0) 
	{
		
		out.println("�ؽ��� ���� ��� : ����<br>") ;
		strHashValue = new String(hash.contentbuf);
		out.println("�ؽ��� : " + strHashValue);
		
	}
	else
	{
		out.println("�ؽ��� ���� ��� : ����<br>") ;
		
		
	}
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

	
	String Origin_Data = "";
	String strHashValue = "";

	Origin_Data = "01:506-81-32479+02:(��)������+03:����,���Ҹ�+04:��� ���׽� ���� ������ ��õ�� 634-4����+05:506-81-00017+06:����������ö(��)+07:����,����,���Ҹ�,�ε���+08:��� ���׽� ���� ������ 1����+10:10,790,420+11:1,079,042+12:2001-07-02+13:Cutting Fluid/Oil K MSDS DONG HO DAICOOL,200 L��+14:+15:+16:2001-07-02+30:";
	
	Hash hash = new Hash();

	// GetHash(byte[] data, int datalen)
	// ������ 0 �����ϰ� ������� contentbuf�� �ؽ����� ä����
	int nRet;

	nRet = hash.GetHash(Origin_Data.getBytes(), Origin_Data.getBytes().length);

	if(nRet==0) 
	{
		
		out.println("�ؽ��� ���� ��� : ����< br>") ;
		strHashValue = new String(hash.contentbuf);
		out.println("�ؽ��� : " + strHashValue);
		
	}
	else
	{
		out.println("�ؽ��� ���� ��� : ����< br>") ;
		
		
	}
% >

</font>
</pre>