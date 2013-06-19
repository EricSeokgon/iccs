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


	String idn = request.getParameter("idn");		// 식별번호 (주민/사업자번호)
	String signeddata = request.getParameter("signeddata");		// 서명된 값
	
	String userREnc = request.getParameter("userREnc");		// 암호화된 R값

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
		out.println("서명값 Base64 Decode 결과 : 성공<br>") ;
	}
	else
	{
		out.println("서명값 Base64 Decode 결과 : 실패<br>") ;
		out.println("에러내용 : " + CBase64.errmessage + "<br>");
		out.println("에러코드 : " + CBase64.errcode + "<br>");
	}
		
	Verifier CVerifier = new Verifier();
	nRet=CVerifier.VerSignedData(CBase64.contentbuf, CBase64.contentlen); 

	
	if(nRet==0) 
	{
		String sOrgData = new String(CVerifier.contentbuf, "KSC5601");
		out.println("전자서명 검증 결과 : 성공<br>") ;
		out.println("원문 : " + sOrgData + "<br>");

	}
	else
	{
		out.println("전자서명 검증 결과 : 실패<br>") ;
		out.println("에러내용 : " + CVerifier.errmessage + "<br>");
		out.println("에러코드 : " + CVerifier.errcode + "<br>");
		return;
	}

	//인증서 정보 추출 결과	
	Certificate CCertificate = new Certificate();
	nRet=CCertificate.ExtractCertInfo(CVerifier.certbuf, CVerifier.certlen);

	out.println("인증서 정보 추출 결과 : " + Integer.toHexString(nRet));
	
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
	
	// 인증서 검증
	String Policies = "1.2.410.200004.5.4.1.1|1.2.410.200004.5.4.1.2|1.2.410.200004.5.4.1.3|1.2.410.200004.5.4.1.4|1.2.410.200004.5.4.1.5|1.2.410.200004.5.2.1.1";

	nRet=CCertificate.ValidateCert(CVerifier.certbuf, CVerifier.certlen, Policies, 0);

	if(nRet==0) 
	{
		out.println("인증서 검증 결과 : 성공<br>") ;
	}
	else
	{
		out.println("인증서 검증 결과 : 실패<br>") ;
		out.println("에러내용 : " + CCertificate.errmessage + "<br>");
		out.println("에러코드 : " + CCertificate.errcode + "<br>");
	}

	// 식별번호 검증
	InputStream inPri=null;
	InputStream inCert=null;
	byte[] Prifilebuf, Certfilebuf;

	// 복호용 개인키 추출
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
			
		// 개인키 추출
		PrivateKey CPrivateKey = new PrivateKey();
		nRet = CPrivateKey.DecryptPriKey("88888888", Prifilebuf, nPrilen);
		
		if (nRet != 0)
		{
			out.println("에러내용 : " + CPrivateKey.errmessage + "<br>");
			out.println("에러코드 : " + CPrivateKey.errcode + "<br>");
			return;
		}
						
		out.println("서버인증서개인키 길이 : " + CPrivateKey.prikeylen + "<br>" );
		out.println("서버인증서 길이 : " + nCertlen + "<br>" );

		out.println("암호화된R값 : " + userREnc + "<br>" );
		
		//Base64 DBase64 = new Base64();  
		nRet = CBase64.Decode(userREnc.getBytes("KSC5601"), userREnc.getBytes("KSC5601").length);
			
		// 암호화된R값을 복호화
		Decrypt CDecrypt = new Decrypt();
		nRet = CDecrypt.DecEnvelopedData(CPrivateKey.prikeybuf, CPrivateKey.prikeylen, Certfilebuf, nCertlen, CBase64.contentbuf, CBase64.contentlen);

		String sRandom = new String( CDecrypt.contentbuf, "KSC5601");
		
		out.println("암호화된 R값 복호화 : " + sRandom + "<br>");
	
	
		nRet=CCertificate.VerifyVID(CVerifier.certbuf, CVerifier.certlen, CDecrypt.contentbuf, CDecrypt.contentlen, idn);

		
	
		if(nRet==0) 
		{
			out.println("식별번호 검증 결과 : 성공<br>") ;
		}
		else
		{
			out.println("식별번호 검증 결과 : 실패<br>") ;
			out.println("에러내용 : " + CCertificate.errmessage + "<br>");
			out.println("에러코드 : " + CCertificate.errcode + "<br>");
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


	String idn = request.getParameter("idn");		// 식별번호 (주민/사업자번호)
	String signeddata = request.getParameter("signeddata");		// 서명된 값
	
	String userREnc = request.getParameter("userREnc");		// 암호화된 R값

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
		out.println("서명값 Base64 Decode 결과 : 성공< br>") ;
	}
	else
	{
		out.println("서명값 Base64 Decode 결과 : 실패< br>") ;
		out.println("에러내용 : " + CBase64.errmessage + "< br>");
		out.println("에러코드 : " + CBase64.errcode + "< br>");
	}
		
	Verifier CVerifier = new Verifier();
	nRet=CVerifier.VerSignedData(CBase64.contentbuf, CBase64.contentlen); 

	
	if(nRet==0) 
	{
		String sOrgData = new String(CVerifier.contentbuf, "KSC5601");
		out.println("전자서명 검증 결과 : 성공< br>") ;
		out.println("원문 : " + sOrgData + "< br>");

	}
	else
	{
		out.println("전자서명 검증 결과 : 실패< br>") ;
		out.println("에러내용 : " + CVerifier.errmessage + "< br>");
		out.println("에러코드 : " + CVerifier.errcode + "< br>");
		return;
	}

	//인증서 정보 추출 결과	
	Certificate CCertificate = new Certificate();
	nRet=CCertificate.ExtractCertInfo(CVerifier.certbuf, CVerifier.certlen);

	out.println("인증서 정보 추출 결과 : " + Integer.toHexString(nRet));
	
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
	
	// 인증서 검증
	String Policies = "1.2.410.200004.5.4.1.1|1.2.410.200004.5.4.1.2|1.2.410.200004.5.4.1.3|1.2.410.200004.5.4.1.4|1.2.410.200004.5.4.1.5|1.2.410.200004.5.2.1.1";

	nRet=CCertificate.ValidateCert(CVerifier.certbuf, CVerifier.certlen, Policies, 0);

	if(nRet==0) 
	{
		out.println("인증서 검증 결과 : 성공< br>") ;
	}
	else
	{
		out.println("인증서 검증 결과 : 실패< br>") ;
		out.println("에러내용 : " + CCertificate.errmessage + "< br>");
		out.println("에러코드 : " + CCertificate.errcode + "< br>");
	}

	// 식별번호 검증
	InputStream inPri=null;
	InputStream inCert=null;
	byte[] Prifilebuf, Certfilebuf;

	// 복호용 개인키 추출
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
			
		// 개인키 추출
		PrivateKey CPrivateKey = new PrivateKey();
		nRet = CPrivateKey.DecryptPriKey("88888888", Prifilebuf, nPrilen);
		
		if (nRet != 0)
		{
			out.println("에러내용 : " + CPrivateKey.errmessage + "< br>");
			out.println("에러코드 : " + CPrivateKey.errcode + "< br>");
			return;
		}
						
		out.println("서버인증서개인키 길이 : " + CPrivateKey.prikeylen + "< br>" );
		out.println("서버인증서 길이 : " + nCertlen + "< br>" );

		out.println("암호화된R값 : " + userREnc + "< br>" );
		
		//Base64 DBase64 = new Base64();  
		nRet = CBase64.Decode(userREnc.getBytes("KSC5601"), userREnc.getBytes("KSC5601").length);
			
		// 암호화된R값을 복호화
		Decrypt CDecrypt = new Decrypt();
		nRet = CDecrypt.DecEnvelopedData(CPrivateKey.prikeybuf, CPrivateKey.prikeylen, Certfilebuf, nCertlen, CBase64.contentbuf, CBase64.contentlen);

		String sRandom = new String( CDecrypt.contentbuf, "KSC5601");
		
		out.println("암호화된 R값 복호화 : " + sRandom + "< br>");
	
	
		nRet=CCertificate.VerifyVID(CVerifier.certbuf, CVerifier.certlen, CDecrypt.contentbuf, CDecrypt.contentlen, idn);

		
	
		if(nRet==0) 
		{
			out.println("식별번호 검증 결과 : 성공< br>") ;
		}
		else
		{
			out.println("식별번호 검증 결과 : 실패< br>") ;
			out.println("에러내용 : " + CCertificate.errmessage + "< br>");
			out.println("에러코드 : " + CCertificate.errcode + "< br>");
		}

	}
	catch(IOException e1) 
	{
		System.out.println(e1); 
	}
	

	
% >

</font>
</pre>