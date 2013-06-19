<%@ page contentType="text/html; charset=utf-8" %><%@ page import="java.util.*"%><%@ page import="kjf.util.*" %><%@ page import="kjf.ops.*" %><%@ page import="java.util.Vector" %><%@ page import="java.io.*" %><%ReportEntity rEntity =(ReportEntity)request.getAttribute("rEntity");String result=(String)request.getSession().getAttribute("result");%><%if(rEntity != null){if(rEntity.getRowCnt() > 0){
		out.print(rEntity.getValue(0,"INSP_NUM")+"|");
		out.print(rEntity.getValue(0,"USEBEFINSP_DELINUM")+"\n");
	}
}else{
	if(!KJFUtil.isEmpty(result)){
		out.print(result);
	}else{
		out.print("||");
	}
}
%>
