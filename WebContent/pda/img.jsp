<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.net.*"%>
<%@ page import="kjf.ops.*" %>
<%@ page isErrorPage="true" %>
<%
try{
	   URL url = new URL("http://localhost/iccs/pda/imgtest.jsp");
	   URLConnection uc = url.openConnection ();
	   InputStream is = uc.getInputStream ();
	   
	   BufferedInputStream bs = new BufferedInputStream(is);

	   FileOutputStream fos = new FileOutputStream ("D:\\cytyseal\\a.bmp");

	   int n = 0;
	   while ((n=bs.read())!=-1)
	   {
	    fos.write(n);
	   }
	   
	   fos.close();

	}catch(Exception e){}
	finally {
		
	}

%>
