<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="tsa.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

</body>
</html>

<%
	
	jCert4 tsa2 = new jCert4();
	boolean success = tsa2.tsa(); // TSA ��û �� ���� ���� �� True
	
	
	if(success == true){
		out.println("TSA ��û �� ���� ���� �� True");
		String sec;
		String gentime;
		jCert4 time = new jCert4();
		sec = time.realTime();
		//out.println("sec: " + sec);
		
		if(sec.length() == 3){
			String sec1 = sec.substring(0);
			String sec2 = sec.substring(1,4);
			out.println("����ð�: "+sec1+"��" + sec2);
			out.println("<br>");
			
		}else{
			String sec1 = sec.substring(0,1);
			String sec2 = sec.substring(1,4);
			out.println("����ð�: "+sec1+"��" + sec2);
			out.println("<br>");
		}
		
		gentime = time.gentime();
		out.println("gentime====>>>" + gentime);
		
	%>
	<br>
	TSA ����
	<%
	}else{
		int code = tsa2.errcode();
		String msg = tsa2.errmsg();
	%>
	TSA ����
	�����ڵ� : <%=code %><br>
	���� �޽��� : <%=msg %><br>

	<%
	}
	%>