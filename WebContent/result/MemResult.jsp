<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="kjf.util.*" %>

<% 
	String cmd = KJFUtil.print(request.getParameter("cmd")); 
%>

<script language="javascript">

// 개인정보 수정
<% if (cmd.equals("MemInfoU")) { %>
	location.href = "../mem/MemAction.do?cmd=MemInfoUOK";

// 공사업 등록 여부  확인
<% } else if (cmd.equals("MemPubResNumU")) { %>
	location.href="../mem/MemAction.do?cmd=MemInfoV";
	
// 모바일 서비스 신청
<% } else if (cmd.equals("MemMobileRegU")) { %>
	location.href="../mem/MemAction.do?cmd=MemMobileRegOK";

// 비밀 번호 변경	
<% } else if (cmd.equals("MemPasswdChangeU")) {%>
	location.href="../mem/MemAction.do?cmd=MemPasswdChangeOK";

// 공인인증서 재등록
<% } else if (cmd.equals("MemCCReRegU")) {%>
	location.href="../mem/MemAction.do?cmd=MemCCReRegOK";

// 회원탈퇴
<% } else if (cmd.equals("MemInfoD")) {%>
	location.href="../login/LoginAction.do?cmd=LogOut";
<% }%>
</script>