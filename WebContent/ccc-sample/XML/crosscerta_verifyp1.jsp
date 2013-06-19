<%@ page language="java" import="java.io.*,java.util.*,crosscert.*" %>
<%@ page contentType = "text/html; charset=euc-kr" %>
<%


	String signed_data = request.getParameter("signed_data");
	String user_cert = request.getParameter("user_cert");
	String hash_data = request.getParameter("hash_data");
	String origin = hash_data;
	int nRet;


	InputStream FIS_nXmlFileBuf = null;
	InputStream FIS_XmlFileSignBuf = null;

	
	/* xml ���� */
	int nXmlFileBuf = 0;
	byte XmlFileBuf[] = null;
	String strXmlFile = "";

	
	/* Signature XML temp */
	int nXmlFileSignBuf = 0;
	byte XmlFileSignBuf[] = null;
	String strXmlFileSign = "";


	Properties props = System.getProperties(); // get list of properties
	String file_separator = (String)(props.get("file.separator"));
	String current_dir = "";
	String CertPath = "";
	String Full_path = request.getRealPath(request.getServletPath());
	if (file_separator.equals("\\"))	
	{
		current_dir = Full_path.substring(0,Full_path.lastIndexOf("\\"));
		CertPath = current_dir + ""; 
	}
	else								
	{
		current_dir = Full_path.substring(0,Full_path.lastIndexOf("/"));
		CertPath = current_dir; 
	}


	try 
	{
		FIS_nXmlFileBuf =  new FileInputStream(new File(CertPath + "\\test_xml.xml"));	
		FIS_XmlFileSignBuf =  new FileInputStream(new File(CertPath + "\\xml-sig_temp.xml"));	
		
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

	
	/* ���� XML */
	nXmlFileBuf = FIS_nXmlFileBuf.available();
	XmlFileBuf = new byte[nXmlFileBuf];
	nRet = FIS_nXmlFileBuf.read(XmlFileBuf);
	FIS_nXmlFileBuf.close();

	strXmlFile = new String(XmlFileBuf);


	/* Signature XML temp */
	nXmlFileSignBuf = FIS_XmlFileSignBuf.available();
	XmlFileSignBuf = new byte[nXmlFileSignBuf];
	nRet = FIS_XmlFileSignBuf.read(XmlFileSignBuf);
	FIS_XmlFileSignBuf.close();
	strXmlFileSign = new String(XmlFileSignBuf);
	
	
	strXmlFileSign = usf_replace(strXmlFileSign,"#HASH#", hash_data );
	strXmlFileSign = usf_replace(strXmlFileSign, "#SIGNDATA#", signed_data );
	strXmlFileSign = usf_replace(strXmlFileSign, "#CERT#", user_cert);


	strXmlFile = usf_replace(strXmlFile, "<Signature/>", strXmlFileSign); 
	
	//echo "<br>������ ���� ��� : ����<br>";
	out.print("<font color = red>< Signature > �±� �������</font><br>");
	out.print("<textarea rows = 5 cols = '100%'>" + strXmlFileSign + "</textarea>");
	//out.print("</body></html>");

	//echo "<br>������ ���� ��� : ����<br>"
	out.print("<br>");;
	out.print("<font color = red>  xml ����   ��ü �������</font><br>");
	out.print("<textarea rows = 5 cols = '100%'>" + strXmlFile + "</textarea>");
	//out.print("</body></html>");


	// ������ ������ base64���ڵ�

	byte[] DecSignedData = null;   // base64���ڵ��� ����
	int DecSignedDataLen = 0;   // base64���ڵ��� ���� ����

	byte[] DecUserCert = null;     // base64���ڵ��� ������
	int DecUserCertLen = 0;     // base64���ڵ��� ����������
	

	Base64 CBase64 = new Base64();  

	// ���� base64���ڵ�
	nRet = CBase64.Decode(signed_data.getBytes(), signed_data.getBytes().length);
	if(nRet==0) 
	{
		out.println("<br>") ;
		out.println("���� Base64 Decode ��� : ����<br>") ;
		out.println("���� ���� : " + CBase64.contentlen + "<br>") ;
		DecSignedData = new byte[CBase64.contentlen];
		System.arraycopy(CBase64.contentbuf, 0, DecSignedData , 0, CBase64.contentlen);
		DecSignedDataLen = CBase64.contentlen;
		
	
	}
	else
	{
		out.println("���� Base64 Decode ��� : ����<br>") ;
		out.println("�������� : " + CBase64.errmessage + "<br>");
		out.println("�����ڵ� : " + CBase64.errcode + "<br>");
	}


	// ������ base64���ڵ�
	nRet = CBase64.Decode(user_cert.getBytes(), user_cert.getBytes().length);
	if(nRet==0) 
	{
		out.println("������ Base64 Decode ��� : ����<br>") ;
		out.println("������ ���� : " + CBase64.contentlen + "<br>") ;
		DecUserCert = new byte[CBase64.contentlen];
		System.arraycopy(CBase64.contentbuf, 0, DecUserCert, 0, CBase64.contentlen);
		DecUserCertLen = CBase64.contentlen;
		
	}
	else
	{
		out.println("���� Base64 Decode ��� : ����<br>") ;
		out.println("�������� : " + CBase64.errmessage + "<br>");
		out.println("�����ڵ� : " + CBase64.errcode + "<br>");
	}

	

	out.print("DecUserCertLen : " + DecUserCertLen + "<br>");
	out.print("DecSignedDataLen : " + DecSignedDataLen + "<br>");
	out.print("hash_data.getBytes().length : " + hash_data.getBytes().length + "<br>");
	out.print("++++++++++ ���� ���� +++++++++++++++++++++++++++++++++++++++<br>");
	Verifier CVerifier = new Verifier();

	
	
	nRet=CVerifier.VerSignedDataP1(DecUserCert, DecUserCertLen, DecSignedData, DecSignedDataLen, hash_data.getBytes(), hash_data.getBytes().length);
	
	if(nRet==0) 
	{
		
		out.println("���ڼ��� ���� ��� : ����<br>") ;
		
	}
	else
	{
		out.println("���ڼ��� ���� ��� : ����<br>") ;
		out.println("�������� : " + CVerifier.errmessage + "<br>");
		out.println("�������� : " + CVerifier.errdetailmessage + "<br>");
		
		out.println("�����ڵ� : " + CVerifier.errcode + "<br>");
		return;
	}

	//������ ���� ���� ���	
	Certificate CCertificate = new Certificate();
	nRet=CCertificate.ExtractCertInfo(DecUserCert, DecUserCertLen);
	

	out.println("������ ���� ���� ��� : " + Integer.toHexString(nRet) + "<br>");
	
	out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" + "<br>");

	out.print("version;          "+  CCertificate.version               + "<br>");
	out.print("serial;           "+  CCertificate.serial                + "<br>");
	out.print("issuer;           "+  CCertificate.issuer                + "<br>");
	out.print("subject;          "+  CCertificate.subject               + "<br>");
	out.print("subjectAlgId;     "+  CCertificate.subjectAlgId          + "<br>");
	out.print("from;             "+  CCertificate.from                  + "<br>");
	out.print("to;               "+  CCertificate.to                    + "<br>");
	out.print("signatureAlgId;   "+  CCertificate.signatureAlgId        + "<br>");
	out.print("pubkey;           "+  CCertificate.pubkey                + "<br>");
	out.print("signature;        "+  CCertificate.signature             + "<br>");
	out.print("issuerAltName;    "+  CCertificate.issuerAltName         + "<br>");
	out.print("subjectAltName;   "+  CCertificate.subjectAltName        + "<br>");
	out.print("keyusage;         "+  CCertificate.keyusage              + "<br>");
	out.print("policy;           "+  CCertificate.policy                + "<br>");
	out.print("basicConstraint;  "+  CCertificate.basicConstraint       + "<br>");
	out.print("policyConstraint; "+  CCertificate.policyConstraint      + "<br>");
	out.print("distributionPoint;"+  CCertificate.distributionPoint     + "<br>");
	out.print("authorityKeyId;   "+  CCertificate.authorityKeyId        + "<br>");
	out.print("subjectKeyId;     "+  CCertificate.subjectKeyId          + "<br>");
    out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" + "<br>");                                                             

	
	// ������ ����
	
	String Policies = "1.2.410.200004.5.4.1.1|1.2.410.200004.5.4.1.2|1.2.410.200004.5.4.1.3|1.2.410.200004.5.4.1.4|1.2.410.200004.5.4.1.5|1.2.410.200004.5.2.1.1|1.2.410.200005.1.1.1|1.2.410.200012.1.1.3|1.2.410.200005.1.1.4|1.2.410.200005.1.1.5";
	
	nRet=CCertificate.ValidateCert(DecUserCert, DecUserCertLen, Policies, 1);
	
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

%>

<%!

	public String usf_replace(String src, String oldstr, String newstr) 
	{ 
		
		if (src == null) return null; 

		StringBuffer dest = new StringBuffer(""); 

		try 
		{ 
			int  len = oldstr.length(); 
			int  srclen = src.length(); 
			int  pos = 0; 
			int  oldpos = 0; 

			while ((pos = src.indexOf(oldstr, oldpos)) >= 0) 
			{ 
				dest.append(src.substring(oldpos, pos)); 
				dest.append(newstr); 
				oldpos = pos + len; 
			} 

			if (oldpos < srclen) 
				dest.append(src.substring(oldpos, srclen)); 

		} 
		catch ( Exception e ) 
		{ 
			e.printStackTrace(); 
		} 
		

		return dest.toString(); 
	} 

%>

<script language="javascript" src="../cc.js"></script>