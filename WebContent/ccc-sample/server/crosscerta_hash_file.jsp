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
	String SignedData = "";  //���ڼ���
	
		
	try 
	{
		out.println("���ϰ�� : " + CertPath + "kmPri.key");
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
		
		// �ش� ���� �б�
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
	// ������ 0 �����ϰ� ������� contentbuf�� �ؽ����� ä����
	String strHashValue  = "";

	nRet = hash.GetHash(filebuf, filebuf.length);

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
	String SignedData = "";  //���ڼ���
	
		
	try 
	{
		out.println("���ϰ�� : " + CertPath + "kmPri.key");
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
		
		// �ش� ���� �б�
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
	// ������ 0 �����ϰ� ������� contentbuf�� �ؽ����� ä����
	String strHashValue  = "";

	nRet = hash.GetHash(filebuf, filebuf.length);

	if(nRet==0) 
	{
		
		out.println("�ؽ��� ���� ��� : ����< br >") ;
		strHashValue = new String(hash.contentbuf);
		out.println("�ؽ��� : " + strHashValue);
		
	}
	else
	{
		out.println("�ؽ��� ���� ��� : ����< br >") ;
		
		
	}
% >

</font>
</pre>