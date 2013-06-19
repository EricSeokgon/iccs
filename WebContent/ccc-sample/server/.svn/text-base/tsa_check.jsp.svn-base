<%@ page language="java" import="java.io.*,java.util.*,java.text.*,crosscert.*" %>
<%@ page contentType = "text/html; charset=euc-kr" %>

<%  
	request.setCharacterEncoding("euc-kr");
	/*-------------------------시작----------------------------*/ 
	response.setDateHeader("Expires",0); 
	response.setHeader("Prama","no-cache"); 

	if(request.getProtocol().equals("HTTP/1.1")) 
	{ 
		response.setHeader("Cache-Control","no-cache"); 
	} 
	/*------------------------- 끝----------------------------*/ 

	String signeddata = request.getParameter("signed_data");		// 서명된 값
	String tsadata  = ""	;								//tsa데이터

	
	int nRet;
	boolean boolCertChk = true;
	String ErrMsg = "";
	String ErrCode = "";
	
	Base64 CBase64 = new Base64();  
	nRet = CBase64.Decode(signeddata.getBytes(), signeddata.getBytes().length);

	if(nRet==0) 
	{
		
		//out.println("서명값 Base64 Decode 결과 : 성공<br>") ;
		
		Verifier CVerifier = new Verifier();
		nRet=CVerifier.VerSignedData(CBase64.contentbuf, CBase64.contentlen); 
		if(nRet==0) 
		{
			String sOrgData = new String(CVerifier.contentbuf);
			out.println("전자서명 검증 결과 : 성공<br>") ;
			out.println("원문 : " + sOrgData + "<br>");
			
			//인증서 정보 추출 결과	
			Certificate CCertificate = new Certificate();
			nRet=CCertificate.ExtractCertInfo(CVerifier.certbuf, CVerifier.certlen);
			if(nRet==0) 
			{
				//out.println("인증서 정보 추출 결과 : " + Integer.toHexString(nRet));
				/*
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
				*/

				
				String policies = "";


				 /* 개인상호연동용(범용) */                            //
				policies +="1.2.410.200004.5.2.1.2"    + "|";          // 한국정보인증               개인                                             
				policies +="1.2.410.200004.5.1.1.5"    + "|";          // 한국증권전산               개인                                             
				policies +="1.2.410.200005.1.1.1"      + "|";          // 금융결제원                 개인                                             
				policies +="1.2.410.200004.5.3.1.4"    + "|";          // 한국전산원           개인(국가기관, 공공기관 및 법인의 소속직원 등)   
				policies +="1.2.410.200004.5.4.1.1"    + "|";          // 한국전자인증               개인                                             
				policies +="1.2.410.200012.1.1.1"      + "|";          // 한국무역정보통신           개인   
				
				// 개인 용도제한용 인증서정책(OID)                 용도                    공인인증기관
				policies += "1.2.410.200004.5.4.1.101"    + "|";        // 은행거래용/보험용       한국전자인증
				policies += "1.2.410.200004.5.4.1.102"    + "|";        // 증권거래용              한국전자인증
				policies += "1.2.410.200004.5.4.1.103"    + "|";        // 신용카드용              한국전자인증
				policies += "1.2.410.200004.5.4.1.104"    + "|";        // 전자민원용              한국전자인증
				policies += "1.2.410.200004.5.2.1.7.1"    + "|";        // 은행거래용/보험용       한국정보인증
				policies += "1.2.410.200004.5.2.1.7.2"    + "|";        // 증권거래용/보험용       한국정보인증
				policies += "1.2.410.200004.5.2.1.7.3"    + "|";        // 신용카드용              한국정보인증
				policies += "1.2.410.200004.5.1.1.9"      + "|";          // 증권거래용/보험용       한국증전산
				policies += "1.2.410.200004.5.1.1.9.2"    + "|";        // 신용카드용              한국증전산
				policies += "1.2.410.200005.1.1.4"        + "|";            // 은행거래용/보험용       금융결제원
				policies += "1.2.410.200005.1.1.6.2"      + "|";          // 신용카드용              금융결제원
				policies += "1.2.410.200012.1.1.101"      + "|";          // 은행거래용/보험용       한국무역정보통신
				policies += "1.2.410.200012.1.1.103"      + "|";          // 증권거래용/보험용       한국무역정보통신
				policies += "1.2.410.200012.1.1.105"      + "|";           // 신용카드용              한국무역정보통신

				policies += "1.2.410.200004.5.2.1.5001"      + "|";           // 국세청              정보인증
																	   //
				 /* 법인상호연동용(범용) */                            //
				policies +="1.2.410.200004.5.2.1.1"    + "|";          // 한국정보인증               법인
				policies +="1.2.410.200004.5.1.1.7"    + "|";          // 한국증권전산               법인, 단체, 개인사업자
				policies +="1.2.410.200005.1.1.5"      + "|";          // 금융결제원                 법인, 임의단체, 개인사업자
				policies +="1.2.410.200004.5.3.1.1"    + "|";          // 한국전산원                 기관(국가기관 및 비영리기관)
				policies +="1.2.410.200004.5.3.1.2"    + "|";          // 한국전산원                 법인(국가기관 및 비영리기관을  제외한 공공기관, 법인)
				policies +="1.2.410.200004.5.4.1.2"    + "|";          // 한국전자인증               법인, 단체, 개인사업자
				policies +="1.2.410.200012.1.1.3"      + "|";          // 한국무역정보통신           법인    
							

				policies +="1.2.410.200004.5.4.1.3"    + "|";          // 한국전자인증               개인
						     
				
				// 인증서 검증
				nRet = CCertificate.ValidateCert(CVerifier.certbuf, CVerifier.certlen, 
			                                 policies , 1);
				
					if (nRet != 0)
					{
					
							boolCertChk = false;
							ErrMsg = "인증서 검증 실패 [ 에러내용 : " + CCertificate.errmessage + " ]";
							ErrCode = "에러코드 [ " + CCertificate.errcode + " ]";
					
					}
					else
					{
					
						out.println("인증서 검증 결과 : 성공<br>") ;
					}


					

			////////////////////////////tsa /////////////////////////
			out.println("<br>-----------TSA----------<br>");
			CCTsp cCCTsp = new CCTsp();
					   
			//String ServerCertDN = "cn=에스에이치공사,ou=서버,ou=한국전자인증,ou=AccreditedCA,o=CrossCert,c=KR";
			//String ServerCertDN = "cn=기술지원테스트서버,ou=테스트,ou=외부업체용,ou=licensedCA,o=CrossCert,c=KR";
			
			//String ServerCertPwd = "88888888";

			String ServerCertDN = "cn=주식회사보광훼미리마트,ou=서버,ou=한국전자인증,ou=AccreditedCA,o=CrossCert,c=KR";
			String ServerCertPwd = "15773663";

			String ST_request_origindata = sOrgData;
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
			Date c_date = new Date();
			String  c_time = formatter.format(c_date);
			out.println("<b>요청시간: " + c_time + "</b><br>");

			long start=System.currentTimeMillis();//시작시간 : 1970년부터 현재까지 걸린시간은 1/1000초(밀리초)로 반환
			

			nRet = cCCTsp.GetTimeStampToken(ST_request_origindata.getBytes(),
											ST_request_origindata.getBytes().length,
											ServerCertDN.getBytes(), 
											ServerCertDN.getBytes().length,
											ServerCertPwd.getBytes(), 
											ServerCertPwd.getBytes().length,
											0);

			if ( nRet != 0 )//타임스탬프 요청 실패
			{
				
				out.println("GetTimeStampToken  실패  " + nRet + "<br>");
				out.println("GetTimeStampToken Class errmessage ==> : " + cCCTsp.errmessage +"<br>");
				out.println("GetTimeStampToken Class errdetailmessage ==> : " + cCCTsp.errdetailmessage +"<br>");
				
				ErrCode = ""+nRet;
				ErrMsg = "errmessage : " + cCCTsp.errmessage + "\n errdetailmessage : " + cCCTsp.errdetailmessage;
				boolCertChk = false;
				
				//return;
			}
			else
			{
				tsadata = new String(cCCTsp.contentbuf);
				out.println("<br>타임스탬프 요청  성공 : " + nRet +"<br>");
				out.println("타임스템스 토큰  : " + tsadata +"<br>");
				
				  long end=0;

				  for(long i=0;i<100000000;i++);
				  

					Date f_date = new Date();
					String  f_time = formatter.format(f_date);
					out.println("<b><br>최종시간: " + f_time + "</b>");

				  end  =System.currentTimeMillis();     //끝시간
				  long total = 0;
				  total = end -start;

				  String str = total + "";
				  
				  if(str.length() == 3){
					  String sec = "0";				  
					  String sec2 = str.substring(0);			
  					  out.println("<b><font color=red>");
					  out.println("<br>응답시간: "+sec+"초" + sec2 + "<br>");  
					  out.println("</font></b>");
				  }else if(str.length() == 4){
					  String sec = str.substring(0,1);				  
					  String sec2 = str.substring(1);				  
					  out.println("<b><font color=red>");
					  out.println("<br>응답시간: "+sec+"초" + sec2 + "<br>");  
					  out.println("</font></b>");
				  }else if(str.length() == 5){
					  String sec = str.substring(0,2);			  
					  String sec2 = str.substring(2);					  
					  out.println("<b><font color=red>");
					  out.println("<br>응답시간: "+sec+"초" + sec2 + "<br>");  
					  out.println("</font></b>");
				  }


				// 타임스탬프 검증하기.....

				nRet = cCCTsp.VerifyTimeStampToken(ST_request_origindata.getBytes(), 
												ST_request_origindata.getBytes().length,
												cCCTsp.contentbuf, 
												cCCTsp.contentlen,
												1);
				if ( nRet !=0 )//타임 스탬프 검증 실패
				{
					out.println("VerifyTimeStampToken  검증실패  " + nRet +"<br>");
					out.println("VerifyTimeStampToken Class errmessage ==> : " + cCCTsp.errmessage +"<br>");
					out.println("VerifyTimeStampToken Class errdetailmessage ==> : " + cCCTsp.errdetailmessage +"<br>");

					///에러 코드와 에러 메시지
					ErrCode = ""+nRet;
					ErrMsg = "errmessage : " + cCCTsp.errmessage + "\n errdetailmessage : " + cCCTsp.errdetailmessage;
					boolCertChk = false;
					
					//return;
				}
				else
				{
					out.println("<br> VerifyTimeStampToken 검증 성공 : " + nRet +"<br>");

					// 타임스탬프 정보 추출하기
					out.println("GetTimeStampTokenInfo  길이  " + cCCTsp.contentlen);
					nRet = cCCTsp.GetTimeStampTokenInfo(cCCTsp.contentbuf, 
													cCCTsp.contentlen,
													1);
					if ( nRet !=0)
					{
						out.println("GetTimeStampTokenInfo  토큰정보추출실패  " + nRet+"<br>");
						out.println("GetTimeStampTokenInfo Class errmessage ==> : " + cCCTsp.errmessage+"<br>");
						out.println("GetTimeStampTokenInfo Class errdetailmessage ==> : " + cCCTsp.errdetailmessage+"<br>");
						
						
						//return;
					}
					else 
					{
					
						out.println("<br>GetTimeStampTokenInfo 토큰정보추출 성공 : " + nRet+"<br>");
						out.println("[gentime]      " + cCCTsp.gentime);
						out.println("<br>[hashvalue] " + cCCTsp.hashvalue);
						out.println("<br>[policy]       " + cCCTsp.policy);
						out.println("<br>[serialnumber] " + cCCTsp.serialnumber);
						out.println("<br>[hashalgorism] " + cCCTsp.hashalgorithm);
					}// 타임스탬프 추출정보 If문 끝
				}// 타임스탬프 검증 If문 끝
			} // 타임스탬프 요청 If문 끝
		}
			else
			{				
				boolCertChk = false;
				ErrMsg = "인증서 추출 실패 [ 에러내용 : " + CCertificate.errmessage + " ]";
				ErrCode = "에러코드 [ " + CCertificate.errcode + " ]";  
				//out.println("인증서 추출 결과 : 실패<br>") ;
				//out.println("에러내용 : " + CCertificate.errmessage + "<br>");
				//out.println("에러코드 : " + CCertificate.errcode + "<br>");
			}
		}//
		else
		{
			
			boolCertChk = false;
			ErrMsg = "전자서명 검증 결과 실패 [ 에러내용 : " + CVerifier.errmessage + " ]";
			ErrCode = "에러코드 [ " + CVerifier.errcode + " ]";  
			//out.println("전자서명 검증 결과 : 실패<br>") ;
			//out.println("에러내용 : " + CVerifier.errmessage + "<br>");
			//out.println("에러코드 : " + CVerifier.errcode + "<br>");
			//return;
		}
	}// 
	else
	{
		
		boolCertChk = false;
		ErrMsg = "Base64 디코딩 실패 [ 에러내용 : " + CBase64.errmessage + " ]";
		ErrCode = "에러코드 [ " + CBase64.errcode + " ]";  
		//out.println("서명값 Base64 Decode 결과 : 실패<br>") ;
		//out.println("에러내용 : " + CBase64.errmessage + "<br>");
		//out.println("에러코드 : " + CBase64.errcode + "<br>");
	}
		
	out.println("<font color=red> <br>=========================================END====================================<br></font>");

	if (boolCertChk == false)
	{
%>
		<SCRIPT LANGUAGE="JavaScript">
		<!--
			alert("<%=ErrMsg%>\n\n<%=ErrCode%>");
			history.back();
		//-->
		</SCRIPT>
<%
	}
	else
	{
		out.println("<br>");
		out.print("성공!!!!");
	}

%>
