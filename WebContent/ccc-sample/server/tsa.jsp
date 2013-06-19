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


	

	///////////////////////////////////////////////////////////
		
		

			out.println("<font color=red> <br>=========================================TSP====================================<br></font>");
			CCTsp cCCTsp = new CCTsp();
			Base64 base = new Base64();
			int nRet = 0;
			//String ServerCertDN = "cn=에스에이치공사,ou=서버,ou=한국전자인증,ou=AccreditedCA,o=CrossCert,c=KR";
			//String ServerCertDN = "cn=기술지원테스트서버,ou=테스트,ou=외부업체용,ou=licensedCA,o=CrossCert,c=KR";
		    //String ServerCertPwd = "88888888";

			String ServerCertDN = "cn=주식회사보광훼미리마트,ou=서버,ou=한국전자인증,ou=AccreditedCA,o=CrossCert,c=KR";
			//String ServerCertDN = "~!!@@@";
			String ServerCertPwd = "15773663";
			String ST_request_origindata = "sdfsdfsf";
			
			Date date = null;
			date = new Date();
			long a = date.getTime();
			out.println("start time: " + a);


			nRet = cCCTsp.GetTimeStampToken(ST_request_origindata.getBytes(),  ST_request_origindata.getBytes().length,ServerCertDN.getBytes(), ServerCertDN.getBytes().length,ServerCertPwd.getBytes(),  ServerCertPwd.getBytes().length,0);

			//nRet = cCCTsp.GetTimeStampToken("adfaf".getBytes(),  ST_request_origindata.getBytes().length,ServerCertDN.getBytes(), ServerCertDN.getBytes().length,ServerCertPwd.getBytes(),  ServerCertPwd.getBytes().length,0);


			if ( nRet !=0)
			{
				out.println("GetTimeStampToken  실패  " + nRet);
				out.println("GetTimeStampToken Class errmessage ==> : " + cCCTsp.errmessage);
				out.println("GetTimeStampToken Class errdetailmessage ==> : " + cCCTsp.errdetailmessage);
				
				//return;
			}
			else
			{
				out.println("타임스탬프 요청  성공 : " + nRet);
				
				out.println("<br>타임스템스 토큰  : <br><textarea cols = 120 rows = 20>" + new String(cCCTsp.contentbuf) + "</textarea><br>");
				date = null;
				date = new Date();
				long b =date.getTime();
				out.println("end time : " + b);
				out.println("total time : " + (b-a));

				ST_request_origindata = "sdfsdfsf";


				//base64 encode

				nRet = base.Decode(cCCTsp.contentbuf,cCCTsp.contentlen);
				
				FileOutputStream fos = null;
				try{
					fos = new FileOutputStream("tsaorigin.ori",false);
					fos.write(base.contentbuf);
					fos.close();
				}catch(Exception e){}


				String ctsa = new String(cCCTsp.contentbuf) ; 
				nRet = cCCTsp.VerifyTimeStampToken(ST_request_origindata.getBytes(), 
												ST_request_origindata.getBytes().length,
												ctsa.getBytes(), 
												ctsa.getBytes().length,
												1);
				
				if ( nRet !=0)
				{
					out.println("VerifyTimeStampToken  검증실패  " + nRet+"<br>");
					out.println("VerifyTimeStampToken Class errmessage ==> : " + cCCTsp.errmessage+"<br>");
					out.println("VerifyTimeStampToken Class errdetailmessage ==> : " + cCCTsp.errdetailmessage+"<br>");
					
					
					//return;
				}
				else
				{
					out.println("VerifyTimeStampToken 검증 성공 : " + nRet+"<br>");
				
				}


				out.println("GetTimeStampTokenInfo  길이  " + cCCTsp.contentlen);
				//nRet = cCCTsp.GetTimeStampTokenInfo(cCCTsp.contentbuf,   cCCTsp.contentlen, 1);
				nRet = cCCTsp.GetTimeStampTokenInfo(ctsa.getBytes(),   ctsa.getBytes().length, 1);
				if ( nRet !=0)
				{
					out.println("GetTimeStampTokenInfo  토큰정보추출실패  " + nRet+"<br>");
					out.println("GetTimeStampTokenInfo Class errmessage ==> : " + cCCTsp.errmessage+"<br>");
					out.println("GetTimeStampTokenInfo Class errdetailmessage ==> : " + cCCTsp.errdetailmessage+"<br>");
					
					
					//return;
				}
				else 
				{
					Hash hash = new Hash();
				
					nRet = hash.GetHash(ST_request_origindata.getBytes(),ST_request_origindata.getBytes().length);
					nRet = base.Decode(hash.contentbuf,hash.contentbuf.length);
					out.println("<br>hash.contentbuf.length : " + hash.contentbuf.length + " hash.contentlen : " + hash.contentlen);
					out.println("<br>base.contentbuf.length : " + base.contentbuf.length + " base.contentlen : " + base.contentlen);
					
				
					out.println("<br>GetTimeStampTokenInfo 토큰정보추출 성공 : " + nRet+"<br>");
					out.println("[gentime]      " + cCCTsp.gentime);
					String hashvalue= "";
					for(int i = 0 ; i<cCCTsp.hashvalue.length();i=i+2){
						hashvalue = hashvalue + Integer.parseInt(""+cCCTsp.hashvalue.charAt(i)+cCCTsp.hashvalue.charAt(i+1),16) + " ";
					}
					
					out.println("<br>[hashvalue] " + new String(hash.contentbuf));
					out.println("<br>[hashvalue] " + cCCTsp.hashvalue);
					for(int i=0;i<base.contentbuf.length;i++){
						hashvalue =hashvalue + " " + (int)base.contentbuf[i];
					}
					out.println("<br>[hash value] " + hashvalue );

					out.println("<br>[policy]       " + cCCTsp.policy);
					out.println("<br>[serialnumber] " + cCCTsp.serialnumber);
					out.println("<br>[hashalgorism] " + cCCTsp.hashalgorithm);
				}
			}
			
			
	
			out.println("<font color=red> <br>=========================================END====================================<br></font>");

			//인증서 정보 추출 결과	
			Certificate CCertificate = new Certificate();
			nRet=CCertificate.ExtractCertInfo(cCCTsp.certbuf, cCCTsp.certlen);
			//nRet=CCertificate.ExtractCertInfo(signeddata.getBytes("KSC5601"), signeddata.getBytes("KSC5601").length);
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
				out.println("인증서 추출 결과 : 실패<br>") ;
				out.println("에러내용 : " + CCertificate.errmessage + "<br>");
				out.println("에러코드 : " + CCertificate.errcode + "<br>");
			}
				
						 
																 

	
			// 인증서 검증
			
			//String Policies = "1.2.410.200004.5.4.1.1|1.2.410.200004.5.4.1.2|1.2.410.200004.5.4.1.7|1.2.410.200004.5.4.1.3|1.2.410.200004.5.4.1.4|1.2.410.200004.5.4.1.5|1.2.410.200004.5.2.1.1|1.2.410.200005.1.1.1|1.2.410.200012.1.1.3|1.2.410.200005.1.1.4|1.2.410.200005.1.1.5|1.2.410.200004.5.4.1.200";
			
			// 개인상호연동용(범용)                            //
			String  Policies;
			
			Policies= "1.2.410.200004.5.2.1.2"    + "|";          // 한국정보인증               개인                                             
			Policies+="1.2.410.200004.5.1.1.5"    + "|";          // 한국증권전산               개인                                             
			Policies+="1.2.410.200005.1.1.1"      + "|";          // 금융결제원                 개인                                             
			Policies+="1.2.410.200004.5.3.1.4"    + "|";          // 한국전산원           개인(국가기관, 공공기관 및 법인의 소속직원 등)   
			Policies+="1.2.410.200004.5.4.1.1"    + "|";          // 한국전자인증               개인                                             
			Policies+="1.2.410.200012.1.1.1"      + "|";          // 한국무역정보통신           개인  
			
			// 개인 용도제한용 인증서정책(OID)                 용도                    공인인증기관
			Policies+= "1.2.410.200004.5.4.1.101|";        // 은행거래용/보험용       한국전자인증
			Policies+= "1.2.410.200004.5.4.1.102|";        // 증권거래용              한국전자인증
			Policies+= "1.2.410.200004.5.4.1.103|";        // 신용카드용              한국전자인증
			Policies+= "1.2.410.200004.5.4.1.104|";        // 전자민원용              한국전자인증
			Policies+= "1.2.410.200004.5.2.1.7.1|";        // 은행거래용/보험용       한국정보인증
			Policies+= "1.2.410.200004.5.2.1.7.2|";        // 증권거래용/보험용       한국정보인증
			Policies+= "1.2.410.200004.5.2.1.7.3|";        // 신용카드용              한국정보인증
			Policies+= "1.2.410.200004.5.1.1.9|";          // 증권거래용/보험용       한국증전산
			Policies+= "1.2.410.200004.5.1.1.9.2|";        // 신용카드용              한국증전산
			Policies+= "1.2.410.200005.1.1.4|";            // 은행거래용/보험용       금융결제원
			Policies+= "1.2.410.200005.1.1.6.2|";          // 신용카드용              금융결제원
			Policies+= "1.2.410.200012.1.1.101|";          // 은행거래용/보험용       한국무역정보통신
			Policies+= "1.2.410.200012.1.1.103|";          // 증권거래용/보험용       한국무역정보통신
			Policies+= "1.2.410.200012.1.1.105";           // 신용카드용              한국무역정보통신
			
			// 법인상호연동용(범용)                             //
			Policies+="1.2.410.200004.5.2.1.1"    + "|";          // 한국정보인증               법인
			Policies+="1.2.410.200004.5.1.1.7"    + "|";          // 한국증권전산               법인, 단체, 개인사업자
			Policies+="1.2.410.200005.1.1.5"      + "|";          // 금융결제원                 법인, 임의단체, 개인사업자
			Policies+="1.2.410.200004.5.3.1.1"    + "|";          // 한국전산원                 기관(국가기관 및 비영리기관)
			Policies+="1.2.410.200004.5.3.1.2"    + "|";          // 한국전산원                 법인(국가기관 및 비영리기관을  제외한 공공기관, 법인)
			Policies+="1.2.410.200004.5.4.1.2"    + "|";          // 한국전자인증               법인, 단체, 개인사업자
			Policies+="1.2.410.200012.1.1.3"      + "|";          // 한국무역정보통신           법인  

			CCertificate.errmessage = "";

			out.println("<font color=red> <br>=======================================인증서 검증====================================<br></font>");
			
			nRet=CCertificate.ValidateCert(cCCTsp.certbuf, cCCTsp.certlen, "", 1);
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


