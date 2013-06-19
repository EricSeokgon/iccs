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


	

	///////////////////////////////////////////////////////////
		
		

			out.println("<font color=red> <br>=========================================TSP====================================<br></font>");
			CCTsp cCCTsp = new CCTsp();
			Base64 base = new Base64();
			int nRet = 0;
			//String ServerCertDN = "cn=��������ġ����,ou=����,ou=�ѱ���������,ou=AccreditedCA,o=CrossCert,c=KR";
			//String ServerCertDN = "cn=��������׽�Ʈ����,ou=�׽�Ʈ,ou=�ܺξ�ü��,ou=licensedCA,o=CrossCert,c=KR";
		    //String ServerCertPwd = "88888888";

			String ServerCertDN = "cn=�ֽ�ȸ�纸���ѹ̸���Ʈ,ou=����,ou=�ѱ���������,ou=AccreditedCA,o=CrossCert,c=KR";
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
				out.println("GetTimeStampToken  ����  " + nRet);
				out.println("GetTimeStampToken Class errmessage ==> : " + cCCTsp.errmessage);
				out.println("GetTimeStampToken Class errdetailmessage ==> : " + cCCTsp.errdetailmessage);
				
				//return;
			}
			else
			{
				out.println("Ÿ�ӽ����� ��û  ���� : " + nRet);
				
				out.println("<br>Ÿ�ӽ��۽� ��ū  : <br><textarea cols = 120 rows = 20>" + new String(cCCTsp.contentbuf) + "</textarea><br>");
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
					out.println("VerifyTimeStampToken  ��������  " + nRet+"<br>");
					out.println("VerifyTimeStampToken Class errmessage ==> : " + cCCTsp.errmessage+"<br>");
					out.println("VerifyTimeStampToken Class errdetailmessage ==> : " + cCCTsp.errdetailmessage+"<br>");
					
					
					//return;
				}
				else
				{
					out.println("VerifyTimeStampToken ���� ���� : " + nRet+"<br>");
				
				}


				out.println("GetTimeStampTokenInfo  ����  " + cCCTsp.contentlen);
				//nRet = cCCTsp.GetTimeStampTokenInfo(cCCTsp.contentbuf,   cCCTsp.contentlen, 1);
				nRet = cCCTsp.GetTimeStampTokenInfo(ctsa.getBytes(),   ctsa.getBytes().length, 1);
				if ( nRet !=0)
				{
					out.println("GetTimeStampTokenInfo  ��ū�����������  " + nRet+"<br>");
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
					
				
					out.println("<br>GetTimeStampTokenInfo ��ū�������� ���� : " + nRet+"<br>");
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

			//������ ���� ���� ���	
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
				out.println("������ ���� ��� : ����<br>") ;
				out.println("�������� : " + CCertificate.errmessage + "<br>");
				out.println("�����ڵ� : " + CCertificate.errcode + "<br>");
			}
				
						 
																 

	
			// ������ ����
			
			//String Policies = "1.2.410.200004.5.4.1.1|1.2.410.200004.5.4.1.2|1.2.410.200004.5.4.1.7|1.2.410.200004.5.4.1.3|1.2.410.200004.5.4.1.4|1.2.410.200004.5.4.1.5|1.2.410.200004.5.2.1.1|1.2.410.200005.1.1.1|1.2.410.200012.1.1.3|1.2.410.200005.1.1.4|1.2.410.200005.1.1.5|1.2.410.200004.5.4.1.200";
			
			// ���λ�ȣ������(����)                            //
			String  Policies;
			
			Policies= "1.2.410.200004.5.2.1.2"    + "|";          // �ѱ���������               ����                                             
			Policies+="1.2.410.200004.5.1.1.5"    + "|";          // �ѱ���������               ����                                             
			Policies+="1.2.410.200005.1.1.1"      + "|";          // ����������                 ����                                             
			Policies+="1.2.410.200004.5.3.1.4"    + "|";          // �ѱ������           ����(�������, ������� �� ������ �Ҽ����� ��)   
			Policies+="1.2.410.200004.5.4.1.1"    + "|";          // �ѱ���������               ����                                             
			Policies+="1.2.410.200012.1.1.1"      + "|";          // �ѱ������������           ����  
			
			// ���� �뵵���ѿ� ��������å(OID)                 �뵵                    �����������
			Policies+= "1.2.410.200004.5.4.1.101|";        // ����ŷ���/�����       �ѱ���������
			Policies+= "1.2.410.200004.5.4.1.102|";        // ���ǰŷ���              �ѱ���������
			Policies+= "1.2.410.200004.5.4.1.103|";        // �ſ�ī���              �ѱ���������
			Policies+= "1.2.410.200004.5.4.1.104|";        // ���ڹο���              �ѱ���������
			Policies+= "1.2.410.200004.5.2.1.7.1|";        // ����ŷ���/�����       �ѱ���������
			Policies+= "1.2.410.200004.5.2.1.7.2|";        // ���ǰŷ���/�����       �ѱ���������
			Policies+= "1.2.410.200004.5.2.1.7.3|";        // �ſ�ī���              �ѱ���������
			Policies+= "1.2.410.200004.5.1.1.9|";          // ���ǰŷ���/�����       �ѱ�������
			Policies+= "1.2.410.200004.5.1.1.9.2|";        // �ſ�ī���              �ѱ�������
			Policies+= "1.2.410.200005.1.1.4|";            // ����ŷ���/�����       ����������
			Policies+= "1.2.410.200005.1.1.6.2|";          // �ſ�ī���              ����������
			Policies+= "1.2.410.200012.1.1.101|";          // ����ŷ���/�����       �ѱ������������
			Policies+= "1.2.410.200012.1.1.103|";          // ���ǰŷ���/�����       �ѱ������������
			Policies+= "1.2.410.200012.1.1.105";           // �ſ�ī���              �ѱ������������
			
			// ���λ�ȣ������(����)                             //
			Policies+="1.2.410.200004.5.2.1.1"    + "|";          // �ѱ���������               ����
			Policies+="1.2.410.200004.5.1.1.7"    + "|";          // �ѱ���������               ����, ��ü, ���λ����
			Policies+="1.2.410.200005.1.1.5"      + "|";          // ����������                 ����, ���Ǵ�ü, ���λ����
			Policies+="1.2.410.200004.5.3.1.1"    + "|";          // �ѱ������                 ���(������� �� �񿵸����)
			Policies+="1.2.410.200004.5.3.1.2"    + "|";          // �ѱ������                 ����(������� �� �񿵸������  ������ �������, ����)
			Policies+="1.2.410.200004.5.4.1.2"    + "|";          // �ѱ���������               ����, ��ü, ���λ����
			Policies+="1.2.410.200012.1.1.3"      + "|";          // �ѱ������������           ����  

			CCertificate.errmessage = "";

			out.println("<font color=red> <br>=======================================������ ����====================================<br></font>");
			
			nRet=CCertificate.ValidateCert(cCCTsp.certbuf, cCCTsp.certlen, "", 1);
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


