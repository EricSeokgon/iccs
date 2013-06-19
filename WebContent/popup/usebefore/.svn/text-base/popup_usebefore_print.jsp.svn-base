<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="kjf.ops.*" %>
<%@ page import="kjf.util.*" %>
<%@ page import="sp.usebefore.UseBeforeParam" %>
<%
		UseBeforeParam pm = (UseBeforeParam)request.getAttribute("pm");
		String SIDO_CODE = "";
		String SIGUNGU_CODE = "";
		String RECV_NUM = "";
		SIDO_CODE  = pm.getScSIDO_CODE();
		SIGUNGU_CODE = pm.getScSIGUNGU_CODE();
		RECV_NUM = pm.getScRecvNum();		
%>

<html><head><title></title>
<script language="JavaScript" src="../com/js/ozViewerPop.js" type="text/javascript"></script>
<script langage="javascript">
function fnLoad(){
 args = new Array();

	var fm = document.fmParam;

	var ozFile 		= "/BeforeInvest/InvestR.ozr";
	var ozOdiname 	= "Q)InvestR";
		
	<%
	String ip = request.getRemoteHost();
	String ip_result = "N";
	if ("99".equals(ip.substring(0,ip.indexOf(".")).trim())){
		ip_result ="Y";
	}
	%>
	args[0] = "RECV_NUM=<%=RECV_NUM%>";
	args[1] = "SIDO_CODE=<%=SIDO_CODE%>";
	args[2] = "SIGUNGU_CODE=<%=SIGUNGU_CODE%>";
	args[3] = "IP_CHECK=<%=ip_result%>";

	ozViewerCreatePop(ozFile,ozOdiname,args);

}
</script>
</head><body onload="fnLoad()">
</body>
</html>