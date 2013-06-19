<%@ page language="java" import="java.io.*,java.util.*,crosscert.*" %>
<%@ page contentType = "text/html; charset=euc-kr" %>
<%
	

	InputStream FIS_XMLOrginFile=null;

	int nXmlFileBuf = 0;
	byte XmlFileBuf[] = null;
	int nRet = 0;

	Properties props = System.getProperties(); // get list of properties
	String file_separator = (String)(props.get("file.separator"));
	String current_dir = "";
	String CertPath = "";
	String Full_path = request.getRealPath(request.getServletPath());
	if (file_separator.equals("\\"))	
	{
		current_dir = Full_path.substring(0,Full_path.lastIndexOf("\\"));
		CertPath = current_dir + ""; 
	}
	else								
	{
		current_dir = Full_path.substring(0,Full_path.lastIndexOf("/"));
		CertPath = current_dir; 
	}
	

	try 
	{
		FIS_XMLOrginFile =  new FileInputStream(new File(CertPath + "\\test_xml.xml"));	
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


	nXmlFileBuf = FIS_XMLOrginFile.available();
	XmlFileBuf = new byte[nXmlFileBuf];
		
	nRet = FIS_XMLOrginFile.read(XmlFileBuf);

	FIS_XMLOrginFile.close();


	String DataArea = "";

	String src = new String(XmlFileBuf);

	String firstdiv = "<DataArea>";
	String lastdiv = "</TaxInvoice>";

	int pos,pos1;
	int DataAreaLen = 0;
	pos = src.indexOf(firstdiv);
	pos1 = src.indexOf(lastdiv);
	
	DataArea = src.substring(pos,pos1);

	/* ����Ű�� ���� */
	StringTokenizer token = null; 
	String line = "";
	String strdelimiter = "\r\n";
	String temp_src = "";

	token = new StringTokenizer ( DataArea, strdelimiter);
	while ( token.hasMoreTokens ( ) )
	{
		line = token.nextToken ( );
		temp_src += line;
	}
	DataArea = temp_src;

	
	
	Hash hash = new Hash();
	
	String strHashValue = "";

	nRet = hash.GetHash(DataArea.getBytes(), DataArea.getBytes().length);

	if(nRet==0) 
	{
		
		out.println("�����ؽ��� ���� ��� : ����<br>") ;
		strHashValue = new String(hash.contentbuf);
		out.println("�����ؽ��� : " + strHashValue);
		
	}
	else
	{
		out.println("�ؽ��� ���� ��� : ����< br >") ;
	}

/*
	
	out.print("#####<br>");
	for(int i = 0 ; i< DataArea.length(); i++)
	{
		
		out.print(DataArea.charAt(i));
		out.print("<br>");
	}
	out.print("end<br>");
*/
%>

<html>
<head>
<title>�����ϱ�</title>
<head>

<script language="javascript" src="../init.js"></script>
<SCRIPT LANGUAGE="JavaScript">
<!--
/*
function init()
{
  var Ret;
 
  Ret =  document.CC_Object_id.SetCommonInfoFromVal("211.192.169.70",4502, 
                                               "211.192.169.180",389, 
                                               "211.192.169.180",389,
                                               "CN=ROOT-RSA-CRL,OU=ROOTCA,O=KISA,C=KR", 
                                               "no", 
                                               "1.2.410.200004.2.1|1.2.410.200004.5.1.1.5|1.2.410.200004.5.1.1.7|1.2.410.200004.5.2.1.1|1.2.410.200004.5.2.1.2|1.2.410.200004.5.3.1.1|1.2.410.200004.5.3.1.2|1.2.410.200004.5.3.1.4|1.2.410.200004.5.4.1.1|1.2.410.200004.5.4.1.2|1.2.410.200004.5.4.1.3|1.2.410.200004.5.4.1.4|1.2.410.200004.5.4.1.5|1.2.410.200004.5.4.1.7|1.2.410.200005.1.1.1|1.2.410.200005.1.1.5|1.2.410.200012.1.1.1|1.2.410.200012.1.1.3");
								
    if ( Ret != 0 )
    { 
         alert( "���� �ʱ� ������ �����Ͽ����ϴ�." );
         return false;
    }
    else
    {
         return true;
    }
}
*/


function SignData()
{


	// ȯ�漳�� �Լ� ��
	init();
	var ret;
	var signeddata, textin;
	var userdn;
    var hasheddata;


	document.test.src.value = document.test.src.value.replace(/\r\n/g, "");
    hasheddata = document.CC_Object_id.GetHashData(document.test.src.value, "SHA1");
    if( hasheddata == null || hasheddata == "" )
	{
              errmsg = document.CC_Object_id.GetErrorContent();
              errcode = document.CC_Object_id.GetErrorCode();

              alert("�ؽ����� ��µ� �����߽��ϴ�. : " + errmsg);
		return;
	}
	
	
	document.test.hash_data.value = hasheddata;


	userdn = document.CC_Object_id.GetUserDN();
	if( userdn == null || userdn == "" )
	{
		alert(" ����� DN ������ ��� �Ǿ����ϴ�.");
		return;
	}
	else
	{


		signeddata = document.CC_Object_id.genSignature(userdn, "", "SHA1RSA", hasheddata)

		if( signeddata == null || signeddata == "" )
		{
			errmsg = document.CC_Object_id.GetErrorContent();
			errcode = document.CC_Object_id.GetErrorCode();
			alert( "SignData :"+errmsg );
			return;
		}
		else
		{
			UserCert = document.CC_Object_id.CC_get_cert_local(userdn)
			if (UserCert =="")
			{
				alert("������ ���� ����");
				return;

			}
			else
			{
				UserCert = Repalce_cert(UserCert);
			}
			getR = CC_Object_id.GetRFromKey(userdn, "");
			if (getR == "")
			{
				alert("�ֹι�ȣ/����ڹ�ȣ�� Ȯ���� �� ���� �������Դϴ�.");
				return;
			}

			document.test.signed_data.value = signeddata;
			document.test.user_cert.value = UserCert;
			document.test.action = "crosscerta_verifyp1.jsp";
			document.test.method = "post";
			//document.test.submit();

		}
	}
}

function Repalce_cert(str_cert)
{
	var parse_cert = "";
	if (str_cert == "")
		return str;
	else
	{
		parse_cert = str_cert.replace(/\n/g, "");
		parse_cert = parse_cert.replace(/-----BEGIN CERTIFICATE-----/g, "");
		parse_cert = parse_cert.replace(/-----END CERTIFICATE-----/g, "");
	}
	return parse_cert;
}


function send()
{
	
	document.test.action = "crosscerta_verifyp1.jsp";
	document.test.method = "post";
	document.test.submit();
}

//-->
</SCRIPT>
</HEAD>

<BODY>

<form name="test">


<div align = center>


<table>
  <tr> 
	<td align="center"> ����(�������� �ؽ����� ������)<br>
	  <textarea name='src' rows='20' cols='40'><%=DataArea%></textarea>
	</td>
	<td align="center"><br><br><br>
	  �ؽ���<br>
	  <textarea name="hash_data" rows="1" cols="40"></textarea><br><br>
	  ����<br>
	  <textarea name="signed_data" rows="7" cols="40"></textarea><br><br>
	  ����� ����� ������<br>
	  <textarea name="user_cert" rows="9" cols="40"></textarea>
	</td>
  <tr> 
	<td align="center"><input type = button value = "���ڼ���" OnClick = "SignData()"><br>���ڼ���(PKCS #1)</td>
	<td align="center"><input type = button value = "����(crosscerta_verifyp1.jsp)" OnClick = "send()"></td>
  </tr>

</table>
</form>


<!-- �������� ��� //-->
<script language="javascript" src="../CC_Object.js"></script>

