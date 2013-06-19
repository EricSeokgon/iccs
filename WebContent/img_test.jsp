							
<%@ page contentType="text/html; charset=utf-8" %>
<%//@ page errorPage="error.jsp" %>
<%@ page import="java.io.*" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page import="kjf.cfg.*" %>
<%@ page import="kjf.util.*" %>
					
<%
  out.println(Config.props.get("REMOTE_FILE_DIR"));

%>							
<img src="com/downLoad.jsp?Lo=REMOTE_FILE_DIR&Sub_Lo=User&SYS_FILE_NAME=bsbs_admin.GIF&FILE_REAL_NAME=bsbs_admin.GIF" width="80" height="59" border="0">