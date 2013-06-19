<%@page contentType="text/html;charset=UTF-8" %>
<%@ page import="kjf.util.*" %>
<%@ page import="kjf.ops.*" %>
<%@ page import="sp.usebefore.UseBeforeParam" %>
<%
	response.setHeader ("Pragma", "No-cache");
	response.setDateHeader ("Expires", 0);
	response.setHeader ("Cache-Control", "no-cache");
	// 초기 변수 들 선언
	UseBeforeParam pm = (UseBeforeParam)request.getAttribute("pm");
	if("1".equals(pm.getSuccResult()))
		out.print("전자결재가 정상적으로 처리되었습니다.");
	else
		out.print("failure");
%>
