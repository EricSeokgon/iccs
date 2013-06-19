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


	
	String signeddata = request.getParameter("signed_data");		// 서명된 값
	String src = request.getParameter("src");		// 원문

			
	int nPrilen=0, nCertlen=0, nRet;
	
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
	

	// nRet=CVerifier.VerSignedData(CBase64.contentbuf, CBase64.contentlen); 
	// 원문 미포함 서명 검증하기
	nRet = CVerifier.VerSignedDataNoContent(CBase64.contentbuf, CBase64.contentlen,  src.getBytes("KSC5601"), src.getBytes("KSC5601").length);
	
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
		out.println("인증서 검증 결과 : 실패<br>") ;
		out.println("에러내용 : " + CCertificate.errmessage + "<br>");
		out.println("에러코드 : " + CCertificate.errcode + "<br>");
	}
		
                 
																 

	
	// 인증서 검증
	
	String Policies = "1.2.410.200004.5.4.1.1|1.2.410.200004.5.4.1.2|1.2.410.200004.5.4.1.7|1.2.410.200004.5.4.1.3|1.2.410.200004.5.4.1.4|1.2.410.200004.5.4.1.5|1.2.410.200004.5.2.1.1|1.2.410.200005.1.1.1|1.2.410.200012.1.1.3|1.2.410.200005.1.1.4|1.2.410.200005.1.1.5|1.2.410.200004.5.4.1.200";
	

	CCertificate.errmessage = "";

	nRet=CCertificate.ValidateCert(CVerifier.certbuf, CVerifier.certlen, Policies, 1);
	if(nRet==0) 
	{
		out.println("인증서 검증 결과 : 성공<br>") ;
	}
	else
	{
		out.println("인증서 검증 결과 : 실패<br>") ;
		out.println("에러내용 : " + CCertificate.errmessage + "<br>");
		out.println("에러코드 : " + CCertificate.errcode + "<br>");
		out.println("에러코드 : " + Integer.toHexString(CCertificate.errcode) + "<br>");
		out.println("에러내용 : " + CCertificate.errdetailmessage + "<br>");
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


	
	String signeddata = request.getParameter("signed_data");		// 서명된 값
	String src = request.getParameter("src");		// 원문
		
	int nPrilen=0, nCertlen=0, nRet;
	
	Base64 CBase64 = new Base64();  
	nRet = CBase64.Decode(signeddata.getBytes("KSC5601"), signeddata.getBytes("KSC5601").length);
	
	//byte ccc[] = new byte[10];
	//nRet = CBase64.Decode(ccc, ccc.length);
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
		out.println("인증서 검증 결과 : 실패< br>") ;
		out.println("에러내용 : " + CCertificate.errmessage + "< br>");
		out.println("에러코드 : " + CCertificate.errcode + "< br>");
	}
		
                 
																 

	
	// 인증서 검증
	
	String Policies = "1.2.410.200004.5.4.1.1|1.2.410.200004.5.4.1.2|1.2.410.200004.5.4.1.7|1.2.410.200004.5.4.1.3|1.2.410.200004.5.4.1.4|1.2.410.200004.5.4.1.5|1.2.410.200004.5.2.1.1|1.2.410.200005.1.1.1|1.2.410.200012.1.1.3|1.2.410.200005.1.1.4|1.2.410.200005.1.1.5";
	

	CCertificate.errmessage = "";

	nRet=CCertificate.ValidateCert(CVerifier.certbuf, CVerifier.certlen, Policies, 1);


	
	if(nRet==0) 
	{
		out.println("인증서 검증 결과 : 성공< br>") ;
	}
	else
	{
		out.println("인증서 검증 결과 : 실패< br>") ;
		out.println("에러내용 : " + CCertificate.errmessage + "< br>");
		out.println("에러코드 : " + CCertificate.errcode + "< br>");
		out.println("에러코드 : " + Integer.toHexString(CCertificate.errcode) + "< br>");
	}
% >

</font>
</pre>