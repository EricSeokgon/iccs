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
	//String signeddata = "MIIFsQIBATEJMAcGBSsOAwIaMAsGCSqGSIb3DQEHAaCCBJswggSXMIIEAKADAgECAgMC3icwDQYJKoZIhvcNAQEFBQAwTDELMAkGA1UEBhMCS1IxEjAQBgNVBAoTCUNyb3NzQ2VydDETMBEGA1UECxMKbGljZW5zZWRDQTEUMBIGA1UEAxMLQ3Jvc3NDZXJ0Q0EwHhcNMDQxMDEzMDUwNzAwWhcNMDUxMDEzMDUwNjU5WjCBijELMAkGA1UEBhMCS1IxEjAQBgNVBAoTCUNyb3NzQ2VydDETMBEGA1UECxMKbGljZW5zZWRDQTEbMBkGA1UECwwS7ZWc6rWt7KCE7J6Q7J247KadMQ8wDQYDVQQLDAbrspXsnbgxJDAiBgNVBAMMG+yjvOyLne2ajOyCrO2YhOuMgO2ZiOyHvO2VkTCBnzANBgkqhkiG9w0BAQEFAAOBjQAwgYkCgYEAtgPdm3B1gxyriTTQ5NaRsmLfCh2pyHEo8OifjoPS0Spvhs+Kroe/OtW70e8bOzRODGae6M/nwo15Sd6/GAy4LAtl95z8RAby17lES0e25gycgnJE8cdc29zvwq2SpE7jnLTJ6Z4VAczHuF2FYdCVTlL2qfkSck+PGhd/GF/138sCAwEAAaOCAkYwggJCMB8GA1UdIwQYMBaAFNPBk3vWH5ZPHCxocqzsTIVhTNLcMB0GA1UdDgQWBBSK33dncyLfUtAajOEdWaM6yD2sojAOBgNVHQ8BAf8EBAMCBsAwfAYDVR0gBHUwczBxBgoqgxqMmkQFBAEFMGMwLQYIKwYBBQUHAgEWIWh0dHA6Ly9nY2EuY3Jvc3NjZXJ0LmNvbS9jcHMuaHRtbDAyBggrBgEFBQcCAjAmHiTHdAAgx3jJncEcspQAIKz1x3gAIMd4yZ3BHAAgx4WyyLLkAC4wagYDVR0RBGMwYaBfBgkqgxqMmkQKAQGgUjBQDBvso7zsi53tmozsgqztmITrjIDtmYjsh7ztlZEwMTAvBgoqgxqMmkQKAQEBMCEwBwYFKw4DAhqgFgQUC+hFPjatTh6MpM3b66VRZrxwfqswYgYDVR0fBFswWTBXoFWgU4ZRbGRhcDovL2Rpci5jcm9zc2NlcnQuY29tOjM4OS9jbj1zMWRwNHAxMjAsb3U9Y3JsZHAsb3U9bGljZW5zZWRDQSxvPUNyb3NzQ2VydCxjPUtSMIGhBggrBgEFBQcBAQSBlDCBkTBWBggrBgEFBQcwAoZKbGRhcDovL2Rpci5jcm9zc2NlcnQuY29tOjM4OS9jbj1Dcm9zc0NlcnRDQSxvdT1saWNlbnNlZENBLG89Q3Jvc3NDZXJ0LGM9S1IwNwYIKwYBBQUHMAGGK2h0dHA6Ly9vY3NwMS5jcm9zc2NlcnQuY29tOjE0MjAzL09DU1BTZXJ2ZXIwDQYJKoZIhvcNAQEFBQADgYEAjIWp4vdKbHgBIny9T1fPvdbWBVXd3DjCSVhcvP8cOb8yyqjIlEhanDvC/uT88ACcgWb1Bz6Oijod1Ndr4NCB8vOxXHJCWnwwWZOyhCTzcem83ifbWUvOeOWea6B9mCiJXlIsPE4doB79951YvQTrnAEtp14NLx3DkTEyWuFs3f8xgfQwgfECAQEwUzBMMQswCQYDVQQGEwJLUjESMBAGA1UEChMJQ3Jvc3NDZXJ0MRMwEQYDVQQLEwpsaWNlbnNlZENBMRQwEgYDVQQDEwtDcm9zc0NlcnRDQQIDAt4nMAcGBSsOAwIaMAsGCSqGSIb3DQEBAQSBgATmRCcrxlPjW3u9Ap9vwYdDij7ZQDKo9oTZshmnrWq+LENsw3Xg5wU50ymVOyDKQUfmGlPrkBlMsq+RdKZ35m6pFEROj0dRzUHMLsVJNdmu9h058S1S7CNk0YX9fW9kXjMjfuM47paCKBf4mk4V7jxl4Pt5NYQclryTXcm979Rd";		// 서명된 값

	//String signeddata = "MIIFlgIBATEJMAcGBSsOAwIaMAsGCSqGSIb3DQEHAaCCBIAwggR8MIID5aADAgECAgMC/+MwDQYJKoZIhvcNAQEFBQAwTDELMAkGA1UEBhMCS1IxEjAQBgNVBAoTCUNyb3NzQ2VydDETMBEGA1UECxMKbGljZW5zZWRDQTEUMBIGA1UEAxMLQ3Jvc3NDZXJ0Q0EwHhcNMDQxMTE1MDUzNjAwWhcNMDUwNDIyMDcwMTU5WjB9MQswCQYDVQQGEwJLUjESMBAGA1UEChMJQ3Jvc3NDZXJ0MRMwEQYDVQQLEwpsaWNlbnNlZENBMRswGQYDVQQLDBLtlZzqta3soITsnpDsnbjspp0xDzANBgNVBAsMBuuyleyduDEXMBUGA1UEAwwO7Jeg65287J24KOyjvCkwgZ8wDQYJKoZIhvcNAQEBBQADgY0AMIGJAoGBAKmIVddAoFauYSX5NAOwKUwoy/ZxmjngoWSe9q9mcoR3JWIa9WA+2LXYb/3fYvkrj6oVthckoLqn/4PoUOmpJd/6sQh4JmLyAEGHVmK/2tDNEs/yMdiyPCYBhJSs+q9+65TmSkeUFghe/KOKWEBXU92WBptMqo/2a+y6vnRBaOajAgMBAAGjggI5MIICNTAfBgNVHSMEGDAWgBTTwZN71h+WTxwsaHKs7EyFYUzS3DAdBgNVHQ4EFgQUO68ePi9DKJeQY/7ZQjvKkQfKDokwDgYDVR0PAQH/BAQDAgbAMHwGA1UdIAR1MHMwcQYKKoMajJpEBQQBAjBjMC0GCCsGAQUFBwIBFiFodHRwOi8vZ2NhLmNyb3NzY2VydC5jb20vY3BzLmh0bWwwMgYIKwYBBQUHAgIwJh4kx3QAIMd4yZ3BHLKUACCs9cd4ACDHeMmdwRwAIMeFssiy5AAuMF0GA1UdEQRWMFSgUgYJKoMajJpECgEBoEUwQwwO7Jeg65287J24KOyjvCkwMTAvBgoqgxqMmkQKAQEBMCEwBwYFKw4DAhqgFgQUWHsh0t04vXEyesX+igBfg1HRp3IwYgYDVR0fBFswWTBXoFWgU4ZRbGRhcDovL2Rpci5jcm9zc2NlcnQuY29tOjM4OS9jbj1zMWRwNHAxMjksb3U9Y3JsZHAsb3U9bGljZW5zZWRDQSxvPUNyb3NzQ2VydCxjPUtSMIGhBggrBgEFBQcBAQSBlDCBkTBWBggrBgEFBQcwAoZKbGRhcDovL2Rpci5jcm9zc2NlcnQuY29tOjM4OS9jbj1Dcm9zc0NlcnRDQSxvdT1saWNlbnNlZENBLG89Q3Jvc3NDZXJ0LGM9S1IwNwYIKwYBBQUHMAGGK2h0dHA6Ly9vY3NwMS5jcm9zc2NlcnQuY29tOjE0MjAzL09DU1BTZXJ2ZXIwDQYJKoZIhvcNAQEFBQADgYEAIeo4uXGCus+z1MQlKycWk9cpYElsiaUGKkcVQdAXbIABfq2SPYZMb2ITXoszDApx1ys8MgKky3zBw7lMvNirOfWqp/7HyiGRyGCa5jBBMvh04wMBikJPUeJtfSSWi+iutng2vh4N1TA40d+rEwa/X9Pd6EMX3oCEINJVd4UGl+MxgfQwgfECAQEwUzBMMQswCQYDVQQGEwJLUjESMBAGA1UEChMJQ3Jvc3NDZXJ0MRMwEQYDVQQLEwpsaWNlbnNlZENBMRQwEgYDVQQDEwtDcm9zc0NlcnRDQQIDAv/jMAcGBSsOAwIaMAsGCSqGSIb3DQEBAQSBgAMyoO0Ny2q/oVmUukMiLFWIjlGF5R7cMiLyNC36STV8qE9nPgyFmqJPulk55qRHkJMxPeqeJyGIPIMfdrS53+GN7zemjMUDY6u76dmfP17VwWYBEVt35VKyn291yiI78FgnVki7c3AuY5W3fgdnYbUHKLe9lYJZIoHRWZyqLc2H";		// 서명된 값
	//String src = request.getParameter("src");		// 원문

			
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
	
	String Policies = "1.2.410.200004.5.4.1.1|1.2.410.200004.5.4.1.2|1.2.410.200004.5.4.1.7|1.2.410.200004.5.4.1.3|1.2.410.200004.5.4.1.4|1.2.410.200004.5.4.1.5|1.2.410.200004.5.2.1.1|1.2.410.200005.1.1.1|1.2.410.200012.1.1.3|1.2.410.200005.1.1.4|1.2.410.200005.1.1.5|1.2.410.200004.5.4.1.200|1.2.410.200004.5.1.1.7|1.2.410.200004.5.4.2.26|1.2.410.200004.5.4.2.31|";
	

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