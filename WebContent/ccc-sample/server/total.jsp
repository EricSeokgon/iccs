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

	Origin_Data = "abcd";
	
	Hash hash = new Hash();

	// GetHash(byte[] data, int datalen)
	// ������ 0 �����ϰ� ������� contentbuf�� �ؽ����� ä����
	int nRet;

	nRet = hash.GetHash(Origin_Data.getBytes(), Origin_Data.getBytes().length);

	if(nRet==0) 
	{
		
		out.println("�ؽ��� ���� ��� : ����<br>") ;
		strHashValue = new String(hash.contentbuf);
		out.println("�ؽ��� : " + strHashValue + "<br><br>");
		
	}
	else
	{
		out.println("�ؽ��� ���� ��� : ����<br>") ;
	}

	// Base64 Encoding
	String sInput = "�����ٶ�";
	Base64 base64 = new Base64();
	nRet = base64.Encode(sInput.getBytes("KSC5601"), sInput.getBytes("KSC5601").length);
	if (nRet != 0)
	{
		out.println("base���ڵ� �������� : " + base64.errmessage + "<br>");
		out.println("base���ڵ忡���ڵ� : " + base64.errcode);
		return;
	}
	String sBase64EncodeData = new String(base64.contentbuf, "KSC5601");
	out.println("Base64 Encoding ����<br>");
	out.println("Base64 Encoding) : " + sBase64EncodeData + "<br><br>");


	// Base64 Decoding
	String sEncData = "sKGzqrTZtvM=";
	out.println("base64 ���ڵ� ����Ÿ : " + sEncData + "<br>");

	nRet = base64.Decode(sEncData.getBytes(), sEncData.length());
	if (nRet != 0)
	{
		out.println("base64���ڵ� �������� : " + base64.errmessage + "<br>");
		out.println("base64���ڵ� �����ڵ� : " + base64.errcode + "<br>");
		return;
	}
	out.println("Base64 Decoding : ����<br>");
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

<pre>
<font color = red>

