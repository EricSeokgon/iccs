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



	int nFilelen=0, nRet;
	InputStream inFile=null;
	byte[] filebuf = null;
	
	
	
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
	//current_dir = request.getServletPath().substring(0,request.getServletPath().lastIndexOf("/")+1);
	String SignedData = "";  //전자서명값
	
		
	try 
	{
		out.println("파일경로 : " + CertPath + "kmPri.key");
		out.println("<br>");
		inFile =  new FileInputStream(new File(CertPath + "signPri.key"));

		
	}
	catch (FileNotFoundException e) 
	{
		out.println(e);
		return;
	}
	catch (IOException e) 
	{
		out.println(e);
		return;
	}

	try 
	{
		
		// 해당 파일 읽기
		nFilelen = inFile.available();
		filebuf = new byte[nFilelen];
		
		nRet = inFile.read(filebuf);
		inFile.close();

	}
	catch(IOException e1) 
	{
		out.println(e1); 
	}


	
	Hash hash = new Hash();

	// GetHash(byte[] data, int datalen)
	// 성공시 0 리턴하고 멤버변수 contentbuf에 해쉬값이 채워짐
	String strHashValue  = "";

	nRet = hash.GetHash(filebuf, filebuf.length);

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

	
	int nFilelen=0, nRet;
	InputStream inFile=null;
	byte[] filebuf = null;
	
	
	
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
	//current_dir = request.getServletPath().substring(0,request.getServletPath().lastIndexOf("/")+1);
	String SignedData = "";  //전자서명값
	
		
	try 
	{
		out.println("파일경로 : " + CertPath + "kmPri.key");
		out.println("< br >");
		inFile =  new FileInputStream(new File(CertPath + "signPri.key"));

		
	}
	catch (FileNotFoundException e) 
	{
		out.println(e);
		return;
	}
	catch (IOException e) 
	{
		out.println(e);
		return;
	}

	try 
	{
		
		// 해당 파일 읽기
		nFilelen = inFile.available();
		filebuf = new byte[nFilelen];
		
		nRet = inFile.read(filebuf);
		inFile.close();

	}
	catch(IOException e1) 
	{
		out.println(e1); 
	}


	
	Hash hash = new Hash();

	// GetHash(byte[] data, int datalen)
	// 성공시 0 리턴하고 멤버변수 contentbuf에 해쉬값이 채워짐
	String strHashValue  = "";

	nRet = hash.GetHash(filebuf, filebuf.length);

	if(nRet==0) 
	{
		
		out.println("해쉬값 추출 결과 : 성공< br >") ;
		strHashValue = new String(hash.contentbuf);
		out.println("해쉬값 : " + strHashValue);
		
	}
	else
	{
		out.println("해쉬값 추출 결과 : 실패< br >") ;
		
		
	}
% >

</font>
</pre>