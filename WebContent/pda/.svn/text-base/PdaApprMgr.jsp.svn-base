<%@ page contentType="text/html; charset=utf-8" %><%@ page import="java.util.*"%><%@ page import="kjf.util.*" %><%@ page import="kjf.ops.*" %><%@ page import="java.util.Vector" %><%@ page import="java.io.*" %><%ReportEntity rEntity =(ReportEntity)request.getAttribute("rEntity");String sido = request.getParameter("city");%><%if(rEntity != null){if(rEntity.getRowCnt() > 0){
	for(int i = 0; rEntity.getRowCnt() > i; i++){
		int SEQ = KJFUtil.str2int(rEntity.getValue(i,"SEQ"));
		String SSEQ = ""+SEQ;
		if(SEQ < 10){
			SSEQ = "0"+SEQ;
		}
		out.print(SSEQ+"|");
		out.print(rEntity.getValue(i,"LARCLAS")+"|");
		out.print(rEntity.getValue(i,"MIDCLAS").replaceAll("-","")+"|");
		out.print(rEntity.getValue(i,"SMACLAS")+"|\n");
	}	
	out.print("END");
}}%>
