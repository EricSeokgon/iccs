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


	int nPrilen=0, nCertlen=0, nRet;
	InputStream inPri=null;
	InputStream inCert=null;
	
	//OutputStream out=null;
	byte[] Prifilebuf;
	byte[] Certfilebuf;
	
	
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
	
	String sInput = "01:506-81-32479+02:(��)������+03:����,���Ҹ�+04:��� ���׽� ���� ������ ��õ�� 634-4����+05:506-81-00017+06:����������ö(��)+07:����,����,���Ҹ�,�ε���+08:��� ���׽� ���� ������ 1����+10:10,790,420+11:1,079,042+12:2001-07-02+13:Cutting Fluid/Oil K MSDS DONG HO DAICOOL,200 L��+14:+15:+16:2001-07-02+30:";
	
	out.println("�������� : " + sInput.length() );
	
	try 
	{
		inPri =  new FileInputStream(new File(CertPath + "signCert.der"));
		inCert = new FileInputStream(new File(CertPath + "signPri.key"));
		
	}
	catch (FileNotFoundException e) 
	{
		out.println(e);
		return;
	}
	catch (IOException e) 
	{
		out.println(e);
		return;
	}
	
	try 
	{
		nPrilen=inPri.available();
		Prifilebuf=new byte[nPrilen];
		
		nRet=inPri.read(Prifilebuf);

		out.print(nRet);
		
		// ����Ű ����
		PrivateKey privateKey = new PrivateKey();
		
		
		nRet=privateKey.DecryptPriKey("88888888", Prifilebuf, nPrilen);
		if (nRet != 0)
		{
			out.print(privateKey.errmessage + "<br>");
			return;
		}
		else
		{
			out.println("����Ű ���� : " + privateKey.prikeylen);

			nCertlen=inCert.available();
			Certfilebuf = new byte[nCertlen];
		
			nRet=inCert.read(Certfilebuf);
		
			// ���ڼ���
			Signer signer = new Signer();
			
			nRet=signer.GetSignedData(privateKey.prikeybuf, privateKey.prikeylen, Certfilebuf, nCertlen, sInput.getBytes("KSC5601"), sInput.getBytes("KSC5601").length);
			if (nRet != 0)
			{
				out.print(signer.errmessage + "<br>");
				return;
			}
			
			out.println("���ڼ��� ���� : " + signer.signedlen);
		}
					
		

	}
	catch(IOException e1) 
	{
		out.println(e1); 
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


	int nPrilen=0, nCertlen=0, nRet;
	InputStream inPri=null;
	InputStream inCert=null;
	
	//OutputStream out=null;
	byte[] Prifilebuf;
	byte[] Certfilebuf;
	
	
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
	
	String sInput = "01:506-81-32479+02:(��)������+03:����,���Ҹ�+04:��� ���׽� ���� ������ ��õ�� 634-4����+05:506-81-00017+06:����������ö(��)+07:����,����,���Ҹ�,�ε���+08:��� ���׽� ���� ������ 1����+10:10,790,420+11:1,079,042+12:2001-07-02+13:Cutting Fluid/Oil K MSDS DONG HO DAICOOL,200 L��+14:+15:+16:2001-07-02+30:";
	
	out.println("�������� : " + sInput.length() );
	
	try 
	{
		inPri =  new FileInputStream(new File(CertPath + "signCert.der"));
		inCert = new FileInputStream(new File(CertPath + "signPri.key"));
		
	}
	catch (FileNotFoundException e) 
	{
		out.println(e);
		return;
	}
	catch (IOException e) 
	{
		out.println(e);
		return;
	}
	
	try 
	{
		nPrilen=inPri.available();
		Prifilebuf=new byte[nPrilen];
		
		nRet=inPri.read(Prifilebuf);

		out.print(nRet);
		
		// ����Ű ����
		PrivateKey privateKey = new PrivateKey();
		
		
		nRet=privateKey.DecryptPriKey("88888888", Prifilebuf, nPrilen);
		if (nRet != 0)
		{
			out.print(privateKey.errmessage + "< br>");
			return;
		}
		else
		{
			out.println("����Ű ���� : " + privateKey.prikeylen);

			nCertlen=inCert.available();
			Certfilebuf = new byte[nCertlen];
		
			nRet=inCert.read(Certfilebuf);
		
			// ���ڼ���
			Signer signer = new Signer();
			
			nRet=signer.GetSignedData(privateKey.prikeybuf, privateKey.prikeylen, Certfilebuf, nCertlen, sInput.getBytes("KSC5601"), sInput.getBytes("KSC5601").length);
			if (nRet != 0)
			{
				out.print(signer.errmessage + "< br>");
				return;
			}
			
			out.println("���ڼ��� ���� : " + signer.signedlen);
		}
					
		

	}
	catch(IOException e1) 
	{
		out.println(e1); 
	}
% >

</font>
</pre>