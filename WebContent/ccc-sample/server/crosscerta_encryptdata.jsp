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
	// ��ȣ�� ����Ű ����
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
			
		// ����Ű ����
		PrivateKey CPrivateKey = new PrivateKey();
		nRet = CPrivateKey.DecryptPriKey("88888888", Prifilebuf, nPrilen);
		
		if (nRet != 0)
		{
			out.println("�������� : " + CPrivateKey.errmessage + "<br>");
			out.println("�����ڵ� : " + CPrivateKey.errcode + "<br>");
			return;
		}
						
		

		Encrypt encrypt = new Encrypt();
		
		nRet = encrypt.EncEnvelopedData(Certfilebuf, nCertlen, "������".getBytes("KSC5601"), "������".getBytes("KSC5601").length);
		if (nRet != 0)
		{
			System.out.println("��ȣȭ1 �������� : " + encrypt.errmessage);
			System.out.println("��ȣȭ1 �����ڵ� : " + encrypt.errcode);
			return;
		}
		out.println("<br><br> " );
		
		out.println("��ȣ��(PKCS) ���� : " + encrypt.envelopedlen  + "<br>" );


		// ���̳ʸ� ����Ÿ base64���ڵ�
		String EncString = "";

		Base64 CBase64 = new Base64();
							  //����Ʈ �迭     //����Ʈ �迭����
		nRet = CBase64.Encode(encrypt.envelopedbuf, encrypt.envelopedlen);
		if(nRet==0) 
		{
			out.println("��ȣȭ�� Base64 Encode ��� : ����<br>") ;
			EncString = new String(CBase64.contentbuf);
			out.println("��ȣȭ�� Base64 Decode �� : " + EncString + "<br>��") ;
		}
		else
		{
			out.println("��ȣȭ�� Base64 Decode ��� : ����<br>") ;
			out.println("�������� : " + CBase64.errmessage + "<br>");
			out.println("�����ڵ� : " + CBase64.errcode + "<br>");
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
	// ��ȣ�� ����Ű ����
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
			
		// ����Ű ����
		PrivateKey CPrivateKey = new PrivateKey();
		nRet = CPrivateKey.DecryptPriKey("88888888", Prifilebuf, nPrilen);
		
		if (nRet != 0)
		{
			out.println("�������� : " + CPrivateKey.errmessage + "< br>");
			out.println("�����ڵ� : " + CPrivateKey.errcode + "< br>");
			return;
		}
						
		

		Encrypt encrypt = new Encrypt();
		
		nRet = encrypt.EncEnvelopedData(Certfilebuf, nCertlen, "������".getBytes("KSC5601"), "������".getBytes("KSC5601").length);
		if (nRet != 0)
		{
			System.out.println("��ȣȭ1 �������� : " + encrypt.errmessage);
			System.out.println("��ȣȭ1 �����ڵ� : " + encrypt.errcode);
			return;
		}
		out.println("< br>< br> " );
		//out.println("cn=�׽�Ʈ����,ou=����,ou=�ѱ���������,ou=licensedCA,o=CrossCert,c=KR ������ ��ȣȭ ���� ���� ������ < br> " );
		out.println("��ȣ��(PKCS) ���� : " + encrypt.envelopedlen  + "< br>" );


		// ���̳ʸ� ����Ÿ base64���ڵ�
		String EncString = "";

		Base64 CBase64 = new Base64();
							  //����Ʈ �迭     //����Ʈ �迭����
		nRet = CBase64.Encode(encrypt.envelopedbuf, encrypt.envelopedlen);
		if(nRet==0) 
		{
			out.println("��ȣȭ�� Base64 Encode ��� : ����< br>") ;
			EncString = new String(CBase64.contentbuf);
			out.println("��ȣȭ�� Base64 Decode �� : " + EncString + "< br>��") ;
		}
		else
		{
			out.println("��ȣȭ�� Base64 Decode ��� : ����< br>") ;
			out.println("�������� : " + CBase64.errmessage + "< br>");
			out.println("�����ڵ� : " + CBase64.errcode + "< br>");
		}
	
		% >

</font>
</pre>