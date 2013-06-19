<%@ page contentType="text/html; charset=utf-8" %><%@ page import="java.util.*"%><%@ page import="kjf.util.*" %><%@ page import="kjf.ops.*" %><%@ page import="java.util.Vector" %><%@ page import="java.io.*" %><%ReportEntity rEntity =(ReportEntity)request.getAttribute("rEntity");String result=(String)request.getSession().getAttribute("result");out.print(result);%><%
			if(rEntity != null){if(rEntity.getRowCnt() > 0){			
				out.print("LOG0|"+rEntity.getValue(0,"SIDO_CODE")+"|");
				out.print(rEntity.getValue(0,"SIDO_NM")+"|");
				out.print(rEntity.getValue(0,"SIGUNGU_CODE")+"|");
				out.print(rEntity.getValue(0,"SIGUNGU_NM")+"|");
				out.print(rEntity.getValue(0,"NM")+"|");
				out.print(rEntity.getValue(0,"PART")+"|");
				out.print(rEntity.getValue(0,"VERSION")+"|\n");
			}}
%>