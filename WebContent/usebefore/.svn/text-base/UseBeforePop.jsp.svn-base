<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.*,java.lang.*" %>
<%@ page import="kjf.util.*" %>
<%@ page import="sp.uent.*" %>
<%@ taglib uri="/KJFTags" prefix="KTags" %>
<%
		String RPTResult = (String)request.getParameter("chkStr");
		String RECV_NUM = (String)request.getParameter("recv_num");
		UserEnt user = (UserEnt)request.getSession().getAttribute("user");
%>
<html>
<head>
<title>사용전검사 출력</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<!-- 공퉁 부분 -->
<link href="../css/total.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="../com/js/ozViewerPop.js" type="text/javascript"></script>
<script language="JavaScript" src="../com/js/com.js" type="text/javascript"></script>
<script language="JavaScript" src="../com/js/kjs.js" type="text/javascript"></script>
<!-- 공퉁 부분 -->

<!-- 스크립트 영역 -->
<script language="javascript">

//츨력 처리
function fn_print(){
	args = new Array();

	var fm = document.fmParam;
  var chkBoxNums = 0;
  var chkCnt = "";
	var ozFile 		= "";
	var ozOdiname 	= "";
	for(i = 0;i < fm.elements.length;i++){
		if(fm.elements[i].type == "radio" && fm.elements[i].name == "RPTRdo"  &&  fm.elements[i].checked == true ){
        	chkCnt = i;
    }
	}	
	
	if (chkCnt == 1){
		ozFile 		= "/BeforeInvest/ConComCertiAlarm.ozr";
		ozOdiname 	= "Q)ConComCertiAlarm";
	}	else if (chkCnt == 2){
		ozFile 		= "/BeforeInvest/InvestR.ozr";
		ozOdiname 	= "Q)InvestR";
  }	else if (chkCnt == 3){
		ozFile 		= "/BeforeInvest/UBInvestResultNotiR.ozr";
		ozOdiname 	= "Q)UbInvestResultNotiR";
	}	else if (chkCnt == 4){
		ozFile 		= "/BeforeInvest/UBInvestInner.ozr";
		ozOdiname 	= "Q)UbInvestInner";
	}	else if (chkCnt == 5){
		ozFile 		= "/BeforeInvest/UBInvestResultStR.ozr";
		ozOdiname 	= "Q)UbInvestResultStR";
 }	else if (chkCnt == 6){
	 ozFile 		= "/BeforeInvest/DefConsComAlarm.ozr";
	 ozOdiname 	= "Q)DefConsComAlarm";
 }	else if (chkCnt == 7){
	 ozFile 		= "/BeforeInvest/DefConsComSupAlarm.ozr";
	 ozOdiname 	= "Q)DefConsComSupAlarm";
 }	

	args[0] = "RECV_NUM=" + "<%=RECV_NUM%>";
	args[1] = "SIDO_CODE=" + "<%=user.getSIDO_CODE()%>";
	args[2] = "SIGUNGU_CODE=" +  "<%=user.getSIGUNGU_CODE()%>";

	ozViewerCreatePop(ozFile,ozOdiname,args);

}
</script>

</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" >
<!--폼은 가능한 하나로 처리한다. -->
<form name=fmParam method="post" action="../usebefore/UseBeforeAction" >
<INPUT type="hidden" name="cmd" value="">

<table width="100%" border="0" cellspacing="0" cellpadding="3" class="inputta">
	<tr>
		<th class="line" align ="left" >사용전검사 출력
		</th>
	</tr>
</table>

<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr><td height="8"></td>
	</tr>
</table>

<jsp:include page="bgBox_top_inc.jsp" flush="true"/>
<table width="100%" border="0" cellspacing="0" cellpadding="3" class="inputTable" style="display:<%="su".equals(RPTResult)?"block":"none"%>">
	<tr>
		<th class="line" align ="left" >
			<input type="radio" name="RPTRdo" value="1"  />사용전검사 필증교부알림
		</th>	
	</tr>
	<tr>
		<th class="line" align ="left">
			<input type="radio" name="RPTRdo" value="2"  />사용전검사 필증
		</th>
	</tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="3" class="inputTable" style="display:<%="all".equals(RPTResult)?"block":"none"%>">
	<tr>
		<th class="line" align ="left" >
			<input type="radio" name="RPTRdo" value="3"  />사용전검사 결과보고서
		</th>	
	</tr>
	<tr>
		<th class="line" align ="left">
			<input type="radio" name="RPTRdo" value="4"  />사용전검사 내부보고서
		</th>
	</tr>
	<tr>
		<th class="line" align ="left">
			<input type="radio" name="RPTRdo" value="5"  />사용전검사 대장
		</th>
	</tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="3" class="inputTable" style="display:<%="dsu".equals(RPTResult)?"block":"none"%>">
	<tr>
		<th class="line" align ="left" >
			<input type="radio" name="RPTRdo" value="6"  />사용전검사 부실시공업체 알림
		</th>	
	</tr>
	<tr>
		<th class="line" align ="left">
			<input type="radio" name="RPTRdo" value="7"  />사용전검사 부적합에 따른 보완통보
		</th>
	</tr>
</table>
<jsp:include page="bgBox_bottom_inc.jsp" flush="true"/>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr><td height="8"><img src="../images/box/btn_print.gif" onclick="fn_print();"></td>
	</tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr><td height="8"></td>
	</tr>
</table>
</form>
</body>
</html>