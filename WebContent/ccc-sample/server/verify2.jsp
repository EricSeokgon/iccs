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
		
		out.print(signeddata + "<br>");
	int nPrilen=0, nCertlen=0, nRet;

	OutputStream out1=null;

	String policies = "";
	
	String signeddata1 = signeddata;
	
	Base64 CBase64 = new Base64();  

	Verifier CVerifier = new Verifier();

	//인증서 정보 추출 결과	
	Certificate CCertificate = new Certificate();
	String tmp = "";

	String UserDn = "";


			
			
	
	
		

		nRet = CBase64.Decode(signeddata.getBytes("KSC5601"), signeddata.getBytes("KSC5601").length);
		
		if(nRet==0) 
		{
			//out1 = new FileOutputStream("d:/test/log.txt", true);
			//tmp = "서명값 Base64 Decode 결과 : 성공" +kkk;
			//out1.write(tmp.getBytes("KSC5601"));
			//tmp = "\n";
			//out1.write(tmp.getBytes());
			//out1.close();
			out.println("서명값 Base64 Decode 결과 : 성공<br>") ;
		}
		else
		{
			//out1 = new FileOutputStream("d:/test/log.txt", true);
			//tmp = "서명값 Base64 Decode 결과 : 실패" +kkk;
			//out1.write(tmp.getBytes("KSC5601"));
			//tmp = "\n";
			//out1.write(tmp.getBytes());
			//out1.close();
			//out.println(kkk + "<font color= red>서명값 Base64 Decode 결과 : 실패<br>") ;
			out.println("에러내용 : " + CBase64.errmessage + "<br>");
			out.println("에러코드 : " + CBase64.errcode + "</font><br>");
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
			tmp = "전자서명 검증 결과 : 성공" +kkk;
			out1.write(tmp.getBytes("KSC5601"));
			tmp = "\n";
			out1.write(tmp.getBytes());
			out1.close();
*/
			String veriPath = "c:/";
			String sOrgData = new String(CVerifier.contentbuf, "KSC5601");
			out.println("전자서명 검증 결과 : 성공<br>") ;
			out.println("<html><body><textarea  rows=20 cols=40> : " + sOrgData + "</textarea><br>");

			
			out1 = new FileOutputStream(veriPath + "서명원본.txt", false);
			out1.write(CVerifier.contentbuf);
			out1.close();

			out1 = new FileOutputStream(veriPath + "서명시사용한인증서.der", false);
			out1.write(CVerifier.certbuf);
			out1.close();
	
		}
		else
		{   
/*			
			out1 = new FileOutputStream("d:/test/log.txt", true);
			tmp = "전자서명 검증 결과 : 실패" +kkk;
			out1.write(tmp.getBytes("KSC5601"));
			tmp = "\n";
			out1.write(tmp.getBytes());
			out1.close();
*/
			out.println("<font color= red>전자서명 검증 결과 : 실패<br>") ;
			out.println("에러내용 : " + CVerifier.errmessage + "<br>");
			out.println("에러코드 : " + CVerifier.errcode + "</font><br>");
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

			System.out.println("<font color= red>전자서명 검증 결과 : 실패<br>") ;
			System.out.println("에러내용 : " + CVerifier.errmessage + "<br>");
			System.out.println("에러코드 : " + CVerifier.errcode + "</font><br>");
			
		}

		
		nRet=CCertificate.ExtractCertInfo(CVerifier.certbuf, CVerifier.certlen);
		if (nRet ==0)
		{   
/*			
			out1 = new FileOutputStream("d:/test/log.txt", true);
			tmp = "인증서 추출 결과 : 성공 +kkk";
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
			tmp = "인증서 추출 결과 : 실패" +kkk;
			out1.write(tmp.getBytes("KSC5601"));
			tmp = "\n";
			out1.write(tmp.getBytes());
			out1.close();

			out.println(kkk + "<font color= red>인증서 검증 결과 : 실패<br>") ;
*/
			out.println("에러내용 : " + CCertificate.errmessage + "<br>");
			out.println("에러코드 : " + CCertificate.errcode + "</font><br>");
			//System.out.println(kkk + "<font color= red>인증서 검증 결과 : 실패<br>") ;
			System.out.println("에러내용 : " + CCertificate.errmessage + "<br>");
			System.out.println("에러코드 : " + CCertificate.errcode + "</font><br>");

		}

										 

		
		// 인증서 검증
		
		
		 /* 개인상호연동용(범용) */
		/*
		policies +="1.2.410.200004.5.2.1.2"    + "|";          // 한국정보인증               개인                                             
		policies +="1.2.410.200004.5.1.1.5"    + "|";          // 한국증권전산               개인                                             
		policies +="1.2.410.200005.1.1.1"      + "|";          // 금융결제원                 개인                                             
		policies +="1.2.410.200004.5.3.1.4"    + "|";          // 한국전산원           개인(국가기관, 공공기관 및 법인의 소속직원 등)   
		policies +="1.2.410.200004.5.4.1.1"    + "|";          // 한국전자인증               개인                                             
		policies +="1.2.410.200012.1.1.1"      + "|";          // 한국무역정보통신           개인                                             
		*/
		//
		 /* 법인상호연동용(범용) */ //

		
		policies +="1.2.410.200004.5.2.1.1"    + "|";          // 한국정보인증               법인
		policies +="1.2.410.200004.5.1.1.7"    + "|";          // 한국증권전산               법인, 단체, 개인사업자
		policies +="1.2.410.200005.1.1.5"      + "|";          // 금융결제원                 법인, 임의단체, 개인사업자
		policies +="1.2.410.200004.5.3.1.1"    + "|";          // 한국전산원                 기관(국가기관 및 비영리기관)
		policies +="1.2.410.200004.5.3.1.2"    + "|";          // 한국전산원                 법인(국가기관 및 비영리기관을  제외한 공공기관, 법인)
		policies +="1.2.410.200004.5.4.1.2"    + "|";          // 한국전자인증               법인, 단체, 개인사업자
		policies +="1.2.410.200012.1.1.3"      + "|";          // 한국무역정보통신           법인
		policies +="1.1.100.1004.5.4.1|"; // 학진 전용
		policies +="1.2.410.200004.5.4.1.5"    + "|";          // 한국전자인증               법인, 단체,

		
		policies = "";
		CCertificate.errmessage = "";
		
		nRet=CCertificate.ValidateCert(CVerifier.certbuf, CVerifier.certlen, policies, 1);


		
		if(nRet==0) 
		{
			out.println("인증서 검증 결과 : 성공<br>") ;
/*	
			out1 = new FileOutputStream("d:/test/log.txt", true);
			tmp = "인증서 검증 결과 : 성공" +kkk;
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
			tmp = "인증서 검증 결과 : 실패" +kkk;
			out1.write(tmp.getBytes("KSC5601"));
			tmp = "\n";
			out1.write(tmp.getBytes());
			out1.close();

			out.println(kkk + "<font color= red>인증서 검증 결과 : 실패<br>") ;
*/
			out.println("에러내용 : " + CCertificate.errmessage + "<br>");
			out.println("에러내용 : " + CCertificate.errdetailmessage + "<br>");
			out.println("에러코드 : " + CCertificate.errcode + "<br>");
			out.println("에러코드 : " + Integer.toHexString(CCertificate.errcode) + "<br></font>");

			System.out.println("<font color= red>인증서 검증 결과 : 실패<br>") ;
			System.out.println("에러내용 : " + CCertificate.errmessage + "<br>");
			System.out.println("에러내용 : " + CCertificate.errdetailmessage + "<br>");
			System.out.println("에러코드 : " + CCertificate.errcode + "<br>");
			System.out.println("에러코드 : " + Integer.toHexString(CCertificate.errcode) + "<br></font>");
		}


		String ServerCertDN = "cn=기술지원테스트서버,ou=테스트,ou=외부업체용,ou=licensedCA,o=CrossCert,c=KR";
			
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
				System.out.println("OSCP 검증 dn : " + UserDn) ;

				out.println(jjj + "/"+ cnt + "<br>") ;
				out.println("OSCP 검증 dn : " + UserDn) ;
				out.println("OSCP 검증 결과 : 성공" + "<br>") ;
				out.println("OSCP 검증 결과 : 성공" + "<br>") ;
			}
			else
			{
				System.out.println(jjj + "/"+ cnt) ;
				System.out.println("OSCP 검증 결과 : 실패") ;
				System.out.println("OSCP 검증 dn : " + UserDn) ;
				System.out.println("CVerifier.certbuf errcode : " + Integer.toHexString(CCertificate.errcode));
				System.out.println("CVerifier.certbuf errmessage : " + CCertificate.errmessage);
				System.out.println("CVerifier.certbuf errmessage : " + CCertificate.errdetailmessage);

				out.println(jjj + "/"+ cnt) ;
				out.println("OSCP 검증 dn : " + UserDn) ;
				out.println("OSCP 검증 결과 : 실패<br>") ;
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
	
	if(nRet==0) 
	{
		out.println("서명값 Base64 Decode 결과 : 성공< br >") ;
	}
	else
	{
		out.println("서명값 Base64 Decode 결과 : 실패< br >") ;
		out.println("에러내용 : " + CBase64.errmessage + "< br >");
		out.println("에러코드 : " + CBase64.errcode + "< br >");
	}
		
	Verifier CVerifier = new Verifier();
	
	nRet=CVerifier.VerSignedData(CBase64.contentbuf, CBase64.contentlen); 

	
	if(nRet==0) 
	{
		String sOrgData = new String(CVerifier.contentbuf, "KSC5601");
		out.println("전자서명 검증 결과 : 성공< br >") ;
		out.println("원문 : " + sOrgData + "< br >");
	}
	else
	{
		out.println("전자서명 검증 결과 : 실패< br >") ;
		out.println("에러내용 : " + CVerifier.errmessage + "< br >");
		out.println("에러코드 : " + CVerifier.errcode + "< br >");
		return;
	}

	//인증서 정보 추출 결과	
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
		out.println("인증서 검증 결과 : 실패< br >") ;
		out.println("에러내용 : " + CCertificate.errmessage + "< br >");
		out.println("에러코드 : " + CCertificate.errcode + "< br >");
	}
		
                 
																 

	
	// 인증서 검증
	
	String policies = "";
	 /* 개인상호연동용(범용) */
	/*
	policies +="1.2.410.200004.5.2.1.2"    + "|";          // 한국정보인증               개인                                             
	policies +="1.2.410.200004.5.1.1.5"    + "|";          // 한국증권전산               개인                                             
	policies +="1.2.410.200005.1.1.1"      + "|";          // 금융결제원                 개인                                             
	policies +="1.2.410.200004.5.3.1.4"    + "|";          // 한국전산원           개인(국가기관, 공공기관 및 법인의 소속직원 등)   
	policies +="1.2.410.200004.5.4.1.1"    + "|";          // 한국전자인증               개인                                             
	policies +="1.2.410.200012.1.1.1"      + "|";          // 한국무역정보통신           개인                                             
	*/
	//
	 /* 법인상호연동용(범용) */ //

	
	policies +="1.2.410.200004.5.2.1.1"    + "|";          // 한국정보인증               법인
	policies +="1.2.410.200004.5.1.1.7"    + "|";          // 한국증권전산               법인, 단체, 개인사업자
	policies +="1.2.410.200005.1.1.5"      + "|";          // 금융결제원                 법인, 임의단체, 개인사업자
	policies +="1.2.410.200004.5.3.1.1"    + "|";          // 한국전산원                 기관(국가기관 및 비영리기관)
	policies +="1.2.410.200004.5.3.1.2"    + "|";          // 한국전산원                 법인(국가기관 및 비영리기관을  제외한 공공기관, 법인)
	policies +="1.2.410.200004.5.4.1.2"    + "|";          // 한국전자인증               법인, 단체, 개인사업자
	policies +="1.2.410.200012.1.1.3"      + "|";          // 한국무역정보통신           법인

	

	CCertificate.errmessage = "";

	nRet=CCertificate.ValidateCert(CVerifier.certbuf, CVerifier.certlen, policies, 1);


	
	if(nRet==0) 
	{
		out.println("인증서 검증 결과 : 성공< br >") ;
	}
	else
	{
		out.println("인증서 검증 결과 : 실패< br >") ;
		out.println("에러내용 : " + CCertificate.errmessage + "< br >");
		out.println("에러코드 : " + CCertificate.errcode + "< br >");
		out.println("에러코드 : " + Integer.toHexString(CCertificate.errcode) + "< br >");
	}
% >

</font>
</pre>