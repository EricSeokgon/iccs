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


	
	String signeddata = request.getParameter("signed_data");		// ����� ��
	String src = request.getParameter("src");		// ����

			
	int nPrilen=0, nCertlen=0, nRet;
	
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
	

	// nRet=CVerifier.VerSignedData(CBase64.contentbuf, CBase64.contentlen); 
	// ���� ������ ���� �����ϱ�
	nRet = CVerifier.VerSignedDataNoContent(CBase64.contentbuf, CBase64.contentlen,  src.getBytes("KSC5601"), src.getBytes("KSC5601").length);
	
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
	if (nRet ==0)
	{
	
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

	}
	else
	{
		out.println("������ ���� ��� : ����<br>") ;
		out.println("�������� : " + CCertificate.errmessage + "<br>");
		out.println("�����ڵ� : " + CCertificate.errcode + "<br>");
	}
		
                 
																 

	
	// ������ ����
	
	String Policies = "1.2.410.200004.5.4.1.1|1.2.410.200004.5.4.1.2|1.2.410.200004.5.4.1.7|1.2.410.200004.5.4.1.3|1.2.410.200004.5.4.1.4|1.2.410.200004.5.4.1.5|1.2.410.200004.5.2.1.1|1.2.410.200005.1.1.1|1.2.410.200012.1.1.3|1.2.410.200005.1.1.4|1.2.410.200005.1.1.5|1.2.410.200004.5.4.1.200";
	

	CCertificate.errmessage = "";

	nRet=CCertificate.ValidateCert(CVerifier.certbuf, CVerifier.certlen, Policies, 1);
	if(nRet==0) 
	{
		out.println("������ ���� ��� : ����<br>") ;
	}
	else
	{
		out.println("������ ���� ��� : ����<br>") ;
		out.println("�������� : " + CCertificate.errmessage + "<br>");
		out.println("�����ڵ� : " + CCertificate.errcode + "<br>");
		out.println("�����ڵ� : " + Integer.toHexString(CCertificate.errcode) + "<br>");
		out.println("�������� : " + CCertificate.errdetailmessage + "<br>");
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


	
	String signeddata = request.getParameter("signed_data");		// ����� ��
	String src = request.getParameter("src");		// ����
		
	int nPrilen=0, nCertlen=0, nRet;
	
	Base64 CBase64 = new Base64();  
	nRet = CBase64.Decode(signeddata.getBytes("KSC5601"), signeddata.getBytes("KSC5601").length);
	
	//byte ccc[] = new byte[10];
	//nRet = CBase64.Decode(ccc, ccc.length);
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
	//byte uuu[] = new byte[100];
	nRet=CCertificate.ExtractCertInfo(CVerifier.certbuf, CVerifier.certlen);
	//nRet=CCertificate.ExtractCertInfo(uuu, CVerifier.certlen);
	if (nRet ==0)
	{
	
		out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" + "< br>");

		out.print("version;          "+  CCertificate.version               + "< br>");
		out.print("serial;           "+  CCertificate.serial                + "< br>");
		out.print("issuer;           "+  CCertificate.issuer                + "< br>");
		out.print("subject;          "+  CCertificate.subject               + "< br>");
		out.print("subjectAlgId;     "+  CCertificate.subjectAlgId          + "< br>");
		out.print("from;             "+  CCertificate.from                  + "< br>");
		out.print("to;               "+  CCertificate.to                    + "< br>");
		out.print("signatureAlgId;   "+  CCertificate.signatureAlgId        + "< br>");
		out.print("pubkey;           "+  CCertificate.pubkey                + "< br>");
		out.print("signature;        "+  CCertificate.signature             + "< br>");
		out.print("issuerAltName;    "+  CCertificate.issuerAltName         + "< br>");
		out.print("subjectAltName;   "+  CCertificate.subjectAltName        + "< br>");
		out.print("keyusage;         "+  CCertificate.keyusage              + "< br>");
		out.print("policy;           "+  CCertificate.policy                + "< br>");
		out.print("basicConstraint;  "+  CCertificate.basicConstraint       + "< br>");
		out.print("policyConstraint; "+  CCertificate.policyConstraint      + "< br>");
		out.print("distributionPoint;"+  CCertificate.distributionPoint     + "< br>");
		out.print("authorityKeyId;   "+  CCertificate.authorityKeyId        + "< br>");
		out.print("subjectKeyId;     "+  CCertificate.subjectKeyId          + "< br>");

	}
	else
	{
		out.println("������ ���� ��� : ����< br>") ;
		out.println("�������� : " + CCertificate.errmessage + "< br>");
		out.println("�����ڵ� : " + CCertificate.errcode + "< br>");
	}
		
                 
																 

	
	// ������ ����
	
	String Policies = "1.2.410.200004.5.4.1.1|1.2.410.200004.5.4.1.2|1.2.410.200004.5.4.1.7|1.2.410.200004.5.4.1.3|1.2.410.200004.5.4.1.4|1.2.410.200004.5.4.1.5|1.2.410.200004.5.2.1.1|1.2.410.200005.1.1.1|1.2.410.200012.1.1.3|1.2.410.200005.1.1.4|1.2.410.200005.1.1.5";
	

	CCertificate.errmessage = "";

	nRet=CCertificate.ValidateCert(CVerifier.certbuf, CVerifier.certlen, Policies, 1);


	
	if(nRet==0) 
	{
		out.println("������ ���� ��� : ����< br>") ;
	}
	else
	{
		out.println("������ ���� ��� : ����< br>") ;
		out.println("�������� : " + CCertificate.errmessage + "< br>");
		out.println("�����ڵ� : " + CCertificate.errcode + "< br>");
		out.println("�����ڵ� : " + Integer.toHexString(CCertificate.errcode) + "< br>");
	}
% >

</font>
</pre>