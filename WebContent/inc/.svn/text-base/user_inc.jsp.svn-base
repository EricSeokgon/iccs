<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="sp.uent.*" %>
<%@ page import="kjf.util.KJFUtil" %>
<%	
	UserEnt user = (UserEnt)request.getSession().getAttribute("user");
	String ruleCapital = ""; //일반사용자, 공무원사용자 구분[b/c/d/e/m]
	boolean ruleChk = false;   
	boolean gpkiChk = false; 
	if (!KJFUtil.isEmpty(user)){
		ruleCapital = user.getCAPITAL();
		if ( "U".equals(ruleCapital) || 
			 "UE".equals(ruleCapital) || 
			 "".equals(ruleCapital)
			){
			ruleChk = false;
		} else {
			ruleChk = true;
		}
		
		if (user.isUseUbSys()){
			gpkiChk = true;
		} else {
			gpkiChk = false;
		}
	}

%>
