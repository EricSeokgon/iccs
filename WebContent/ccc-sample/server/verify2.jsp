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
		
		out.print(signeddata + "<br>");
	int nPrilen=0, nCertlen=0, nRet;

	OutputStream out1=null;

	String policies = "";
	
	String signeddata1 = signeddata;
	
	Base64 CBase64 = new Base64();  

	Verifier CVerifier = new Verifier();

	//������ ���� ���� ���	
	Certificate CCertificate = new Certificate();
	String tmp = "";

	String UserDn = "";


			
			
	
	
		

		nRet = CBase64.Decode(signeddata.getBytes("KSC5601"), signeddata.getBytes("KSC5601").length);
		
		if(nRet==0) 
		{
			//out1 = new FileOutputStream("d:/test/log.txt", true);
			//tmp = "���� Base64 Decode ��� : ����" +kkk;
			//out1.write(tmp.getBytes("KSC5601"));
			//tmp = "\n";
			//out1.write(tmp.getBytes());
			//out1.close();
			out.println("���� Base64 Decode ��� : ����<br>") ;
		}
		else
		{
			//out1 = new FileOutputStream("d:/test/log.txt", true);
			//tmp = "���� Base64 Decode ��� : ����" +kkk;
			//out1.write(tmp.getBytes("KSC5601"));
			//tmp = "\n";
			//out1.write(tmp.getBytes());
			//out1.close();
			//out.println(kkk + "<font color= red>���� Base64 Decode ��� : ����<br>") ;
			out.println("�������� : " + CBase64.errmessage + "<br>");
			out.println("�����ڵ� : " + CBase64.errcode + "</font><br>");
		}
/*
int kkk;
byte temp_c;
int mod_cnt = 5;
	for (kkk = 0; kkk< CBase64.contentlen;kkk++)
	{	
		
		out1 = new FileOutputStream("d:/test/log.txt", true);
		tmp = "start : " + kkk;
		out1.write(tmp.getBytes("KSC5601"));
		tmp = "\n";
		out1.write(tmp.getBytes());
		out1.close();
	if (kkk % mod_cnt ==0) 
		{
			temp_c = CBase64.contentbuf[kkk]; 
			CBase64.contentbuf[kkk] = 45;

			
		}
		
		out1 = new FileOutputStream("d:/test/signedData.txt" + kkk, true);	
		out1.write(CBase64.contentbuf);
		out1.close();
		
*/
		nRet=CVerifier.VerSignedData(CBase64.contentbuf, CBase64.contentlen); 
/*
if (kkk % mod_cnt ==0) 
		{
		CBase64.contentbuf[kkk] = temp_c;
		}
*/
		if(nRet==0) 
		{
/*			
			out1 = new FileOutputStream("d:/test/log.txt", true);
			tmp = "���ڼ��� ���� ��� : ����" +kkk;
			out1.write(tmp.getBytes("KSC5601"));
			tmp = "\n";
			out1.write(tmp.getBytes());
			out1.close();
*/
			String veriPath = "c:/";
			String sOrgData = new String(CVerifier.contentbuf, "KSC5601");
			out.println("���ڼ��� ���� ��� : ����<br>") ;
			out.println("<html><body><textarea  rows=20 cols=40> : " + sOrgData + "</textarea><br>");

			
			out1 = new FileOutputStream(veriPath + "�������.txt", false);
			out1.write(CVerifier.contentbuf);
			out1.close();

			out1 = new FileOutputStream(veriPath + "����û����������.der", false);
			out1.write(CVerifier.certbuf);
			out1.close();
	
		}
		else
		{   
/*			
			out1 = new FileOutputStream("d:/test/log.txt", true);
			tmp = "���ڼ��� ���� ��� : ����" +kkk;
			out1.write(tmp.getBytes("KSC5601"));
			tmp = "\n";
			out1.write(tmp.getBytes());
			out1.close();
*/
			out.println("<font color= red>���ڼ��� ���� ��� : ����<br>") ;
			out.println("�������� : " + CVerifier.errmessage + "<br>");
			out.println("�����ڵ� : " + CVerifier.errcode + "</font><br>");
/*
			out1 = new FileOutputStream("d:/test/log.txt", true);
			tmp = CVerifier.errmessage + "[" + kkk + "]";
			out1.write(tmp.getBytes("KSC5601"));
			tmp = "\n";
			out1.write(tmp.getBytes());
			out1.close();

			out1 = new FileOutputStream("d:/test/log.txt", true);
			tmp = CVerifier.errdetailmessage + "[" + kkk + "]";
			out1.write(tmp.getBytes("KSC5601"));
			tmp = "\n";
			out1.write(tmp.getBytes());
			out1.close();
*/

			System.out.println("<font color= red>���ڼ��� ���� ��� : ����<br>") ;
			System.out.println("�������� : " + CVerifier.errmessage + "<br>");
			System.out.println("�����ڵ� : " + CVerifier.errcode + "</font><br>");
			
		}

		
		nRet=CCertificate.ExtractCertInfo(CVerifier.certbuf, CVerifier.certlen);
		if (nRet ==0)
		{   
/*			
			out1 = new FileOutputStream("d:/test/log.txt", true);
			tmp = "������ ���� ��� : ���� +kkk";
			out1.write(tmp.getBytes("KSC5601"));
			tmp = "\n";
			out1.write(tmp.getBytes());
			out1.close();
*/
			out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" + "<br>");

			out.print("version;          "+  CCertificate.version               + "<br>");
			out.print("serial;           "+  CCertificate.serial                + "<br>");
			out.print("issuer;           "+  CCertificate.issuer                + "<br>");
			out.print("subject;          "+  CCertificate.subject               + "<br>");
			UserDn = CCertificate.subject;
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
/*			
			out1 = new FileOutputStream("d:/test/log.txt", true);
			tmp = "������ ���� ��� : ����" +kkk;
			out1.write(tmp.getBytes("KSC5601"));
			tmp = "\n";
			out1.write(tmp.getBytes());
			out1.close();

			out.println(kkk + "<font color= red>������ ���� ��� : ����<br>") ;
*/
			out.println("�������� : " + CCertificate.errmessage + "<br>");
			out.println("�����ڵ� : " + CCertificate.errcode + "</font><br>");
			//System.out.println(kkk + "<font color= red>������ ���� ��� : ����<br>") ;
			System.out.println("�������� : " + CCertificate.errmessage + "<br>");
			System.out.println("�����ڵ� : " + CCertificate.errcode + "</font><br>");

		}

										 

		
		// ������ ����
		
		
		 /* ���λ�ȣ������(����) */
		/*
		policies +="1.2.410.200004.5.2.1.2"    + "|";          // �ѱ���������               ����                                             
		policies +="1.2.410.200004.5.1.1.5"    + "|";          // �ѱ���������               ����                                             
		policies +="1.2.410.200005.1.1.1"      + "|";          // ����������                 ����                                             
		policies +="1.2.410.200004.5.3.1.4"    + "|";          // �ѱ������           ����(�������, ������� �� ������ �Ҽ����� ��)   
		policies +="1.2.410.200004.5.4.1.1"    + "|";          // �ѱ���������               ����                                             
		policies +="1.2.410.200012.1.1.1"      + "|";          // �ѱ������������           ����                                             
		*/
		//
		 /* ���λ�ȣ������(����) */ //

		
		policies +="1.2.410.200004.5.2.1.1"    + "|";          // �ѱ���������               ����
		policies +="1.2.410.200004.5.1.1.7"    + "|";          // �ѱ���������               ����, ��ü, ���λ����
		policies +="1.2.410.200005.1.1.5"      + "|";          // ����������                 ����, ���Ǵ�ü, ���λ����
		policies +="1.2.410.200004.5.3.1.1"    + "|";          // �ѱ������                 ���(������� �� �񿵸����)
		policies +="1.2.410.200004.5.3.1.2"    + "|";          // �ѱ������                 ����(������� �� �񿵸������  ������ �������, ����)
		policies +="1.2.410.200004.5.4.1.2"    + "|";          // �ѱ���������               ����, ��ü, ���λ����
		policies +="1.2.410.200012.1.1.3"      + "|";          // �ѱ������������           ����
		policies +="1.1.100.1004.5.4.1|"; // ���� ����
		policies +="1.2.410.200004.5.4.1.5"    + "|";          // �ѱ���������               ����, ��ü,

		
		policies = "";
		CCertificate.errmessage = "";
		
		nRet=CCertificate.ValidateCert(CVerifier.certbuf, CVerifier.certlen, policies, 1);


		
		if(nRet==0) 
		{
			out.println("������ ���� ��� : ����<br>") ;
/*	
			out1 = new FileOutputStream("d:/test/log.txt", true);
			tmp = "������ ���� ��� : ����" +kkk;
			out1.write(tmp.getBytes("KSC5601"));
			tmp = "\n";
			out1.write(tmp.getBytes());
			out1.close();
*/
		}
		else
		{
/*			
			out1 = new FileOutputStream("d:/test/log.txt", true);
			tmp = "������ ���� ��� : ����" +kkk;
			out1.write(tmp.getBytes("KSC5601"));
			tmp = "\n";
			out1.write(tmp.getBytes());
			out1.close();

			out.println(kkk + "<font color= red>������ ���� ��� : ����<br>") ;
*/
			out.println("�������� : " + CCertificate.errmessage + "<br>");
			out.println("�������� : " + CCertificate.errdetailmessage + "<br>");
			out.println("�����ڵ� : " + CCertificate.errcode + "<br>");
			out.println("�����ڵ� : " + Integer.toHexString(CCertificate.errcode) + "<br></font>");

			System.out.println("<font color= red>������ ���� ��� : ����<br>") ;
			System.out.println("�������� : " + CCertificate.errmessage + "<br>");
			System.out.println("�������� : " + CCertificate.errdetailmessage + "<br>");
			System.out.println("�����ڵ� : " + CCertificate.errcode + "<br>");
			System.out.println("�����ڵ� : " + Integer.toHexString(CCertificate.errcode) + "<br></font>");
		}


		String ServerCertDN = "cn=��������׽�Ʈ����,ou=�׽�Ʈ,ou=�ܺξ�ü��,ou=licensedCA,o=CrossCert,c=KR";
			
		String ServerCertPwd = "88888888";
		int jjj = 0;
		int cnt = 50;
		for(jjj = 0;jjj < cnt; jjj++)
		{
			nRet = CCertificate.ValidateCertOCSP(CVerifier.certbuf, CVerifier.certlen, 
												 policies , 3, ServerCertPwd.getBytes(), 
												 ServerCertDN.getBytes());
			
			if(nRet==0) 
			{
				System.out.println(jjj + "/"+ cnt) ;
				System.out.println("OSCP ���� dn : " + UserDn) ;

				out.println(jjj + "/"+ cnt + "<br>") ;
				out.println("OSCP ���� dn : " + UserDn) ;
				out.println("OSCP ���� ��� : ����" + "<br>") ;
				out.println("OSCP ���� ��� : ����" + "<br>") ;
			}
			else
			{
				System.out.println(jjj + "/"+ cnt) ;
				System.out.println("OSCP ���� ��� : ����") ;
				System.out.println("OSCP ���� dn : " + UserDn) ;
				System.out.println("CVerifier.certbuf errcode : " + Integer.toHexString(CCertificate.errcode));
				System.out.println("CVerifier.certbuf errmessage : " + CCertificate.errmessage);
				System.out.println("CVerifier.certbuf errmessage : " + CCertificate.errdetailmessage);

				out.println(jjj + "/"+ cnt) ;
				out.println("OSCP ���� dn : " + UserDn) ;
				out.println("OSCP ���� ��� : ����<br>") ;
				out.println("CVerifier.certbuf errcode : " + Integer.toHexString(CCertificate.errcode) + "<br>");
				out.println("CVerifier.certbuf errmessage : " + CCertificate.errmessage + "<br>");
				out.println("CVerifier.certbuf errmessage : " + CCertificate.errdetailmessage + "<br>");

			}
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
	
	if(nRet==0) 
	{
		out.println("���� Base64 Decode ��� : ����< br >") ;
	}
	else
	{
		out.println("���� Base64 Decode ��� : ����< br >") ;
		out.println("�������� : " + CBase64.errmessage + "< br >");
		out.println("�����ڵ� : " + CBase64.errcode + "< br >");
	}
		
	Verifier CVerifier = new Verifier();
	
	nRet=CVerifier.VerSignedData(CBase64.contentbuf, CBase64.contentlen); 

	
	if(nRet==0) 
	{
		String sOrgData = new String(CVerifier.contentbuf, "KSC5601");
		out.println("���ڼ��� ���� ��� : ����< br >") ;
		out.println("���� : " + sOrgData + "< br >");
	}
	else
	{
		out.println("���ڼ��� ���� ��� : ����< br >") ;
		out.println("�������� : " + CVerifier.errmessage + "< br >");
		out.println("�����ڵ� : " + CVerifier.errcode + "< br >");
		return;
	}

	//������ ���� ���� ���	
	Certificate CCertificate = new Certificate();
	
	nRet=CCertificate.ExtractCertInfo(CVerifier.certbuf, CVerifier.certlen);
	if (nRet ==0)
	{
	
		out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" + "< br >");

		out.print("version;          "+  CCertificate.version               + "< br >");
		out.print("serial;           "+  CCertificate.serial                + "< br >");
		out.print("issuer;           "+  CCertificate.issuer                + "< br >");
		out.print("subject;          "+  CCertificate.subject               + "< br >");
		out.print("subjectAlgId;     "+  CCertificate.subjectAlgId          + "< br >");
		out.print("from;             "+  CCertificate.from                  + "< br >");
		out.print("to;               "+  CCertificate.to                    + "< br >");
		out.print("signatureAlgId;   "+  CCertificate.signatureAlgId        + "< br >");
		out.print("pubkey;           "+  CCertificate.pubkey                + "< br >");
		out.print("signature;        "+  CCertificate.signature             + "< br >");
		out.print("issuerAltName;    "+  CCertificate.issuerAltName         + "< br >");
		out.print("subjectAltName;   "+  CCertificate.subjectAltName        + "< br >");
		out.print("keyusage;         "+  CCertificate.keyusage              + "< br >");
		out.print("policy;           "+  CCertificate.policy                + "< br >");
		out.print("basicConstraint;  "+  CCertificate.basicConstraint       + "< br >");
		out.print("policyConstraint; "+  CCertificate.policyConstraint      + "< br >");
		out.print("distributionPoint;"+  CCertificate.distributionPoint     + "< br >");
		out.print("authorityKeyId;   "+  CCertificate.authorityKeyId        + "< br >");
		out.print("subjectKeyId;     "+  CCertificate.subjectKeyId          + "< br >");

	}
	else
	{
		out.println("������ ���� ��� : ����< br >") ;
		out.println("�������� : " + CCertificate.errmessage + "< br >");
		out.println("�����ڵ� : " + CCertificate.errcode + "< br >");
	}
		
                 
																 

	
	// ������ ����
	
	String policies = "";
	 /* ���λ�ȣ������(����) */
	/*
	policies +="1.2.410.200004.5.2.1.2"    + "|";          // �ѱ���������               ����                                             
	policies +="1.2.410.200004.5.1.1.5"    + "|";          // �ѱ���������               ����                                             
	policies +="1.2.410.200005.1.1.1"      + "|";          // ����������                 ����                                             
	policies +="1.2.410.200004.5.3.1.4"    + "|";          // �ѱ������           ����(�������, ������� �� ������ �Ҽ����� ��)   
	policies +="1.2.410.200004.5.4.1.1"    + "|";          // �ѱ���������               ����                                             
	policies +="1.2.410.200012.1.1.1"      + "|";          // �ѱ������������           ����                                             
	*/
	//
	 /* ���λ�ȣ������(����) */ //

	
	policies +="1.2.410.200004.5.2.1.1"    + "|";          // �ѱ���������               ����
	policies +="1.2.410.200004.5.1.1.7"    + "|";          // �ѱ���������               ����, ��ü, ���λ����
	policies +="1.2.410.200005.1.1.5"      + "|";          // ����������                 ����, ���Ǵ�ü, ���λ����
	policies +="1.2.410.200004.5.3.1.1"    + "|";          // �ѱ������                 ���(������� �� �񿵸����)
	policies +="1.2.410.200004.5.3.1.2"    + "|";          // �ѱ������                 ����(������� �� �񿵸������  ������ �������, ����)
	policies +="1.2.410.200004.5.4.1.2"    + "|";          // �ѱ���������               ����, ��ü, ���λ����
	policies +="1.2.410.200012.1.1.3"      + "|";          // �ѱ������������           ����

	

	CCertificate.errmessage = "";

	nRet=CCertificate.ValidateCert(CVerifier.certbuf, CVerifier.certlen, policies, 1);


	
	if(nRet==0) 
	{
		out.println("������ ���� ��� : ����< br >") ;
	}
	else
	{
		out.println("������ ���� ��� : ����< br >") ;
		out.println("�������� : " + CCertificate.errmessage + "< br >");
		out.println("�����ڵ� : " + CCertificate.errcode + "< br >");
		out.println("�����ڵ� : " + Integer.toHexString(CCertificate.errcode) + "< br >");
	}
% >

</font>
</pre>