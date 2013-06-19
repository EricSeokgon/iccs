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


	request.setCharacterEncoding("EUC-KR"); 
	String signeddata = request.getParameter("signed_data");		// 서명된 값
	String user_cert = request.getParameter("user_cert");		    // 인증서
	
	String src = request.getParameter("src");		        // 원문	
	
	
	out.print("서명값 : " + signeddata + "<br>");
	out.print("서명값 길이 : " + signeddata.length() + "<br>");
	out.print("인증서 : " + user_cert + "<br>");
	out.print("인증서길이 : " + user_cert.length() + "<br>");
	out.print("원본 : " + src + "<br><br><br>");
	out.print("원본길이 : " + src.length() + "<br>");
	
		
	int nPrilen=0, nCertlen=0, nRet;
	

	// 서명값과 인증서 base64디코딩

	byte[] DecSignedData = null;   // base64디코딩한 서명값
	int DecSignedDataLen = 0;   // base64디코딩한 서명값 길이

	byte[] DecUserCert = null;     // base64디코딩한 인증서
	int DecUserCertLen = 0;     // base64디코딩한 인증서길이
	

	Base64 CBase64 = new Base64();  

	// 서명값 base64인코딩
	nRet = CBase64.Decode(signeddata.getBytes(), signeddata.getBytes().length);
	if(nRet==0) 
	{
		out.println("서명값 Base64 Decode 결과 : 성공<br>") ;
		out.println("서명값 길이 : " + CBase64.contentlen + "<br>") ;
		DecSignedData = new byte[CBase64.contentlen];
		System.arraycopy(CBase64.contentbuf, 0, DecSignedData , 0, CBase64.contentlen);
		DecSignedDataLen = CBase64.contentlen;
		
	
	}
	else
	{
		out.println("서명값 Base64 Decode 결과 : 실패<br>") ;
		out.println("에러내용 : " + CBase64.errmessage + "<br>");
		out.println("에러코드 : " + CBase64.errcode + "<br>");
	}


	// 인증서 base64인코딩
	nRet = CBase64.Decode(user_cert.getBytes(), user_cert.getBytes().length);
	if(nRet==0) 
	{
		out.println("인증서 Base64 Decode 결과 : 성공<br>") ;
		out.println("인증서 길이 : " + CBase64.contentlen + "<br>") ;
		DecUserCert = new byte[CBase64.contentlen];
		System.arraycopy(CBase64.contentbuf, 0, DecUserCert, 0, CBase64.contentlen);
		DecUserCertLen = CBase64.contentlen;
		
	}
	else
	{
		out.println("서명값 Base64 Decode 결과 : 실패<br>") ;
		out.println("에러내용 : " + CBase64.errmessage + "<br>");
		out.println("에러코드 : " + CBase64.errcode + "<br>");
	}
		
	
	
	
	/*
		VerSignedDataP1(byte[] 인증서, int 인증서길이, byte[] 서명값, int 서명값길이, byte[] 원문, int 원문길이)
	int VerSignedDataP1(byte[] certbuf, int certlen, byte[] signedbuf, int signedlen, byte[] contentbuf, int contentlen);
	*/

	out.print("DecUserCertLen : " + DecUserCertLen + "<br>");
	out.print("DecSignedDataLen : " + DecSignedDataLen + "<br>");
	out.print("src.getBytes().length : " + src.getBytes().length + "<br>");
	out.print("++++++++++ 검증 시작 +++++++++++++++++++++++++++++++++++++++<br>");
	Verifier CVerifier = new Verifier();

	
	
	nRet=CVerifier.VerSignedDataP1(DecUserCert, DecUserCertLen, DecSignedData, DecSignedDataLen, src.getBytes(), src.getBytes().length);
	
	if(nRet==0) 
	{
		
		out.println("전자서명 검증 결과 : 성공<br>") ;
		
	}
	else
	{
		out.println("전자서명 검증 결과 : 실패<br>") ;
		out.println("에러내용 : " + CVerifier.errmessage + "<br>");
		out.println("에러내용 : " + CVerifier.errdetailmessage + "<br>");
		
		out.println("에러코드 : " + CVerifier.errcode + "<br>");
		return;
	}

	//인증서 정보 추출 결과	
	Certificate CCertificate = new Certificate();
	nRet=CCertificate.ExtractCertInfo(DecUserCert, DecUserCertLen);
	

	out.println("인증서 정보 추출 결과 : " + Integer.toHexString(nRet) + "<br>");
	
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
                                                                 

	
	// 인증서 검증
	
	String Policies = "1.2.410.200004.5.4.1.1|1.2.410.200004.5.4.1.2|1.2.410.200004.5.4.1.3|1.2.410.200004.5.4.1.4|1.2.410.200004.5.4.1.5|1.2.410.200004.5.2.1.1|1.2.410.200005.1.1.1|1.2.410.200012.1.1.3|1.2.410.200005.1.1.4|1.2.410.200005.1.1.5";
	
	nRet=CCertificate.ValidateCert(DecUserCert, DecUserCertLen, Policies, 1);
	
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


	request.setCharacterEncoding("EUC-KR"); 
	String signeddata = request.getParameter("signed_data");		// 서명된 값
	String user_cert = request.getParameter("user_cert");		    // 인증서
	
	String src = request.getParameter("src");		        // 원문	
	
	
	out.print("서명값 : " + signeddata + "< br>");
	out.print("서명값 길이 : " + signeddata.length() + "< br>");
	out.print("인증서 : " + user_cert + "< br>");
	out.print("인증서길이 : " + user_cert.length() + "< br>");
	out.print("원본 : " + src + "< br>< br>< br>");
	out.print("원본길이 : " + src.length() + "< br>");
	
		
	int nPrilen=0, nCertlen=0, nRet;
	

	// 서명값과 인증서 base64디코딩

	byte[] DecSignedData = null;   // base64디코딩한 서명값
	int DecSignedDataLen = 0;   // base64디코딩한 서명값 길이

	byte[] DecUserCert = null;     // base64디코딩한 인증서
	int DecUserCertLen = 0;     // base64디코딩한 인증서길이
	

	Base64 CBase64 = new Base64();  

	// 서명값 base64인코딩
	nRet = CBase64.Decode(signeddata.getBytes(), signeddata.getBytes().length);
	if(nRet==0) 
	{
		out.println("서명값 Base64 Decode 결과 : 성공< br>") ;
		out.println("서명값 길이 : " + CBase64.contentlen + "< br>") ;
		DecSignedData = new byte[CBase64.contentlen];
		System.arraycopy(CBase64.contentbuf, 0, DecSignedData , 0, CBase64.contentlen);
		DecSignedDataLen = CBase64.contentlen;
		
	
	}
	else
	{
		out.println("서명값 Base64 Decode 결과 : 실패< br>") ;
		out.println("에러내용 : " + CBase64.errmessage + "< br>");
		out.println("에러코드 : " + CBase64.errcode + "< br>");
	}


	// 인증서 base64인코딩
	nRet = CBase64.Decode(user_cert.getBytes(), user_cert.getBytes().length);
	if(nRet==0) 
	{
		out.println("인증서 Base64 Decode 결과 : 성공< br>") ;
		out.println("인증서 길이 : " + CBase64.contentlen + "< br>") ;
		DecUserCert = new byte[CBase64.contentlen];
		System.arraycopy(CBase64.contentbuf, 0, DecUserCert, 0, CBase64.contentlen);
		DecUserCertLen = CBase64.contentlen;
		
	}
	else
	{
		out.println("서명값 Base64 Decode 결과 : 실패< br>") ;
		out.println("에러내용 : " + CBase64.errmessage + "< br>");
		out.println("에러코드 : " + CBase64.errcode + "< br>");
	}
		
	
	
	
	/*
		VerSignedDataP1(byte[] 인증서, int 인증서길이, byte[] 서명값, int 서명값길이, byte[] 원문, int 원문길이)
	int VerSignedDataP1(byte[] certbuf, int certlen, byte[] signedbuf, int signedlen, byte[] contentbuf, int contentlen);
	*/

	out.print("DecUserCertLen : " + DecUserCertLen + "< br>");
	out.print("DecSignedDataLen : " + DecSignedDataLen + "< br>");
	out.print("src.getBytes().length : " + src.getBytes().length + "< br>");
	out.print("++++++++++ 검증 시작 +++++++++++++++++++++++++++++++++++++++< br>");
	Verifier CVerifier = new Verifier();

	
	
	nRet=CVerifier.VerSignedDataP1(DecUserCert, DecUserCertLen, DecSignedData, DecSignedDataLen, src.getBytes(), src.getBytes().length);
	
	if(nRet==0) 
	{
		
		out.println("전자서명 검증 결과 : 성공< br>") ;
		
	}
	else
	{
		out.println("전자서명 검증 결과 : 실패< br>") ;
		out.println("에러내용 : " + CVerifier.errmessage + "< br>");
		out.println("에러내용 : " + CVerifier.errdetailmessage + "< br>");
		
		out.println("에러코드 : " + CVerifier.errcode + "< br>");
		return;
	}

	//인증서 정보 추출 결과	
	Certificate CCertificate = new Certificate();
	nRet=CCertificate.ExtractCertInfo(DecUserCert, DecUserCertLen);
	

	out.println("인증서 정보 추출 결과 : " + Integer.toHexString(nRet) + "< br>");
	
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
                                                                 

	
	// 인증서 검증
	
	String Policies = "1.2.410.200004.5.4.1.1|1.2.410.200004.5.4.1.2|1.2.410.200004.5.4.1.3|1.2.410.200004.5.4.1.4|1.2.410.200004.5.4.1.5|1.2.410.200004.5.2.1.1|1.2.410.200005.1.1.1|1.2.410.200012.1.1.3|1.2.410.200005.1.1.4|1.2.410.200005.1.1.5";
	
	nRet=CCertificate.ValidateCert(DecUserCert, DecUserCertLen, Policies, 1);
	
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

% >

</font>
</pre>