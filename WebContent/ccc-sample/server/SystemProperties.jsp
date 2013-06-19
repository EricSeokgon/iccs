<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=euc-kr" %>

<%

	Date today = new Date(System.currentTimeMillis());
	out.println("Today: " + today.toString());
	out.println("<br>");


	// START getprops
	Properties props = System.getProperties(); // get list of properties
	// Print properties using Enumeration
	for (Enumeration enum1 = props.propertyNames(); enum1.hasMoreElements();) {
		String key = (String)enum1.nextElement();
		out.println(key + " = " + (String)(props.get(key)));
			out.println("<br>");
	}
	// END getprops


	// START getprop
	// get user's home directory
	String homeDir = System.getProperty("user.home");
	// If 'outDir' not found, use 'homeDir' as default
	String outDir = System.getProperty("testdir", homeDir);
	// END getprop
	out.println("homeDir: " + homeDir);
	out.println("<br>");
	out.println("outDir: " + outDir);
	out.println("<br>");



%>