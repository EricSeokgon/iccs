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


	String idn = request.getParameter("idn");		// �ĺ���ȣ (�ֹ�/����ڹ�ȣ)
	String signeddata = request.getParameter("signeddata");		// ����� ��
	
	String userREnc = request.getParameter("userREnc");		// ��ȣȭ�� R��

	int nPrilen=0, nCertlen=0, nRet;
	
	
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
	
		  
	String signeddata1 = "";
	Base64 CBase64 = new Base64();  
	nRet = CBase64.Decode(signeddata.getBytes("KSC5601"), signeddata.getBytes("KSC5601").length);

	if(nRet==0) 
	{
		out.println("���� Base64 Decode ��� : ����<br>") ;
	}
	else
	{
		out.println("���� Base64 Decode ��� : ����<br>") ;
		out.println("�������� : " + CBase64.errmessage + "<br>");
		out.println("�����ڵ� : " + CBase64.errcode + "<br>");
	}
		
	Verifier CVerifier = new Verifier();
	nRet=CVerifier.VerSignedData(CBase64.contentbuf, CBase64.contentlen); 

	
	if(nRet==0) 
	{
		String sOrgData = new String(CVerifier.contentbuf, "KSC5601");
		out.println("���ڼ��� ���� ��� : ����<br>") ;
		out.println("���� : " + sOrgData + "<br>");

	}
	else
	{
		out.println("���ڼ��� ���� ��� : ����<br>") ;
		out.println("�������� : " + CVerifier.errmessage + "<br>");
		out.println("�����ڵ� : " + CVerifier.errcode + "<br>");
		return;
	}

	//������ ���� ���� ���	
	Certificate CCertificate = new Certificate();
	nRet=CCertificate.ExtractCertInfo(CVerifier.certbuf, CVerifier.certlen);

	out.println("������ ���� ���� ��� : " + Integer.toHexString(nRet));
	
	out.println("subject : " + CCertificate.subject +"<br>");
	out.println("from : " + CCertificate.from +"<br>");
	out.println("to : " + CCertificate.to +"<br>");
	out.println("signatureAlgId : " + CCertificate.signatureAlgId +"<br>");
	out.println("pubkey : " + CCertificate.pubkey +"<br>");
	out.println("signature : " + CCertificate.signature +"<br>");
	out.println("issuerAltName : " + CCertificate.issuerAltName +"<br>");
	out.println("subjectAltName : " + CCertificate.subjectAltName +"<br>");
	out.println("keyusage : " + CCertificate.keyusage +"<br>");
	out.println("policy : " + CCertificate.policy +"<br>");
	out.println("basicConstraint : " + CCertificate.basicConstraint +"<br>");
	out.println("policyConstraint : " + CCertificate.policyConstraint +"<br>");
	out.println("distributionPoint : " + CCertificate.distributionPoint +"<br>");
	out.println("authorityKeyId : " + CCertificate.authorityKeyId +"<br>");
	out.println("subjectKeyId : " + CCertificate.subjectKeyId +"<br>");
	
	// ������ ����
	String Policies = "1.2.410.200004.5.4.1.1|1.2.410.200004.5.4.1.2|1.2.410.200004.5.4.1.3|1.2.410.200004.5.4.1.4|1.2.410.200004.5.4.1.5|1.2.410.200004.5.2.1.1";

	nRet=CCertificate.ValidateCert(CVerifier.certbuf, CVerifier.certlen, Policies, 0);

	if(nRet==0) 
	{
		out.println("������ ���� ��� : ����<br>") ;
	}
	else
	{
		out.println("������ ���� ��� : ����<br>") ;
		out.println("�������� : " + CCertificate.errmessage + "<br>");
		out.println("�����ڵ� : " + CCertificate.errcode + "<br>");
	}

	// �ĺ���ȣ ����
	InputStream inPri=null;
	InputStream inCert=null;
	byte[] Prifilebuf, Certfilebuf;

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
	

	try 
	{
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
						
		out.println("��������������Ű ���� : " + CPrivateKey.prikeylen + "<br>" );
		out.println("���������� ���� : " + nCertlen + "<br>" );

		out.println("��ȣȭ��R�� : " + userREnc + "<br>" );
		
		//Base64 DBase64 = new Base64();  
		nRet = CBase64.Decode(userREnc.getBytes("KSC5601"), userREnc.getBytes("KSC5601").length);
			
		// ��ȣȭ��R���� ��ȣȭ
		Decrypt CDecrypt = new Decrypt();
		nRet = CDecrypt.DecEnvelopedData(CPrivateKey.prikeybuf, CPrivateKey.prikeylen, Certfilebuf, nCertlen, CBase64.contentbuf, CBase64.contentlen);

		String sRandom = new String( CDecrypt.contentbuf, "KSC5601");
		
		out.println("��ȣȭ�� R�� ��ȣȭ : " + sRandom + "<br>");
	
	
		nRet=CCertificate.VerifyVID(CVerifier.certbuf, CVerifier.certlen, CDecrypt.contentbuf, CDecrypt.contentlen, idn);

		
	
		if(nRet==0) 
		{
			out.println("�ĺ���ȣ ���� ��� : ����<br>") ;
		}
		else
		{
			out.println("�ĺ���ȣ ���� ��� : ����<br>") ;
			out.println("�������� : " + CCertificate.errmessage + "<br>");
			out.println("�����ڵ� : " + CCertificate.errcode + "<br>");
		}

	}
	catch(IOException e1) 
	{
		System.out.println(e1); 
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


	String idn = request.getParameter("idn");		// �ĺ���ȣ (�ֹ�/����ڹ�ȣ)
	String signeddata = request.getParameter("signeddata");		// ����� ��
	
	String userREnc = request.getParameter("userREnc");		// ��ȣȭ�� R��

	int nPrilen=0, nCertlen=0, nRet;
	
	
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
	
		  
	String signeddata1 = "";
	Base64 CBase64 = new Base64();  
	nRet = CBase64.Decode(signeddata.getBytes("KSC5601"), signeddata.getBytes("KSC5601").length);

	if(nRet==0) 
	{
		out.println("���� Base64 Decode ��� : ����< br>") ;
	}
	else
	{
		out.println("���� Base64 Decode ��� : ����< br>") ;
		out.println("�������� : " + CBase64.errmessage + "< br>");
		out.println("�����ڵ� : " + CBase64.errcode + "< br>");
	}
		
	Verifier CVerifier = new Verifier();
	nRet=CVerifier.VerSignedData(CBase64.contentbuf, CBase64.contentlen); 

	
	if(nRet==0) 
	{
		String sOrgData = new String(CVerifier.contentbuf, "KSC5601");
		out.println("���ڼ��� ���� ��� : ����< br>") ;
		out.println("���� : " + sOrgData + "< br>");

	}
	else
	{
		out.println("���ڼ��� ���� ��� : ����< br>") ;
		out.println("�������� : " + CVerifier.errmessage + "< br>");
		out.println("�����ڵ� : " + CVerifier.errcode + "< br>");
		return;
	}

	//������ ���� ���� ���	
	Certificate CCertificate = new Certificate();
	nRet=CCertificate.ExtractCertInfo(CVerifier.certbuf, CVerifier.certlen);

	out.println("������ ���� ���� ��� : " + Integer.toHexString(nRet));
	
	out.println("subject : " + CCertificate.subject +"< br>");
	out.println("from : " + CCertificate.from +"< br>");
	out.println("to : " + CCertificate.to +"< br>");
	out.println("signatureAlgId : " + CCertificate.signatureAlgId +"< br>");
	out.println("pubkey : " + CCertificate.pubkey +"< br>");
	out.println("signature : " + CCertificate.signature +"< br>");
	out.println("issuerAltName : " + CCertificate.issuerAltName +"< br>");
	out.println("subjectAltName : " + CCertificate.subjectAltName +"< br>");
	out.println("keyusage : " + CCertificate.keyusage +"< br>");
	out.println("policy : " + CCertificate.policy +"< br>");
	out.println("basicConstraint : " + CCertificate.basicConstraint +"< br>");
	out.println("policyConstraint : " + CCertificate.policyConstraint +"< br>");
	out.println("distributionPoint : " + CCertificate.distributionPoint +"< br>");
	out.println("authorityKeyId : " + CCertificate.authorityKeyId +"< br>");
	out.println("subjectKeyId : " + CCertificate.subjectKeyId +"< br>");
	
	// ������ ����
	String Policies = "1.2.410.200004.5.4.1.1|1.2.410.200004.5.4.1.2|1.2.410.200004.5.4.1.3|1.2.410.200004.5.4.1.4|1.2.410.200004.5.4.1.5|1.2.410.200004.5.2.1.1";

	nRet=CCertificate.ValidateCert(CVerifier.certbuf, CVerifier.certlen, Policies, 0);

	if(nRet==0) 
	{
		out.println("������ ���� ��� : ����< br>") ;
	}
	else
	{
		out.println("������ ���� ��� : ����< br>") ;
		out.println("�������� : " + CCertificate.errmessage + "< br>");
		out.println("�����ڵ� : " + CCertificate.errcode + "< br>");
	}

	// �ĺ���ȣ ����
	InputStream inPri=null;
	InputStream inCert=null;
	byte[] Prifilebuf, Certfilebuf;

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
	

	try 
	{
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
						
		out.println("��������������Ű ���� : " + CPrivateKey.prikeylen + "< br>" );
		out.println("���������� ���� : " + nCertlen + "< br>" );

		out.println("��ȣȭ��R�� : " + userREnc + "< br>" );
		
		//Base64 DBase64 = new Base64();  
		nRet = CBase64.Decode(userREnc.getBytes("KSC5601"), userREnc.getBytes("KSC5601").length);
			
		// ��ȣȭ��R���� ��ȣȭ
		Decrypt CDecrypt = new Decrypt();
		nRet = CDecrypt.DecEnvelopedData(CPrivateKey.prikeybuf, CPrivateKey.prikeylen, Certfilebuf, nCertlen, CBase64.contentbuf, CBase64.contentlen);

		String sRandom = new String( CDecrypt.contentbuf, "KSC5601");
		
		out.println("��ȣȭ�� R�� ��ȣȭ : " + sRandom + "< br>");
	
	
		nRet=CCertificate.VerifyVID(CVerifier.certbuf, CVerifier.certlen, CDecrypt.contentbuf, CDecrypt.contentlen, idn);

		
	
		if(nRet==0) 
		{
			out.println("�ĺ���ȣ ���� ��� : ����< br>") ;
		}
		else
		{
			out.println("�ĺ���ȣ ���� ��� : ����< br>") ;
			out.println("�������� : " + CCertificate.errmessage + "< br>");
			out.println("�����ڵ� : " + CCertificate.errcode + "< br>");
		}

	}
	catch(IOException e1) 
	{
		System.out.println(e1); 
	}
	

	
% >

</font>
</pre>