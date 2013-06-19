<%@ page contentType="text/html;charset=utf-8"%>

<%
	String isUserID = (String) request.getAttribute("isUserID");
	String user_id  = request.getParameter("id");

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Cache-Control" content="No-Cache">
<meta http-equiv="Pragma" content="No-Cache">
<title>아이디 중복검사</title>

<link rel="stylesheet" href="../css/join_pop.css" type="text/css">
<script type="text/javascript" src="../com/js/common_util.js"></script>
<script type="text/javascript" src="../com/js/join.js"></script>

<!-- </head> 태그가  body 내용 안에 들어가야 합니다. -->

<script type="text/javascript">

function assign_id()
{
	opener.document.fmParam.user_id.value = "<%=user_id%>";
	opener.document.fmParam.flag.value = 1;
	opener.document.fmParam.user_id.readOnly = true;
	opener.document.fmParam.user_passwd.readOnly = false;
	opener.document.fmParam.user_passwd.focus();
	opener.document.fmParam.user_passwd.select();

	self.close();
}


function checkDupID()
{
    if( document.forms[0].id.value == "")
    {
        alert("아이디를 입력하세요.");
        document.forms[0].id.focus();
        return false;
    }
    else
    {
        retVal = isValid_id(document.forms[0].id.value);
        if( !retVal )
        {
	    	document.forms[0].id.select();
            return false;
        }
    }
    return true;
}
</script>


<!-- Document Size : 400*229 -->
<body onload="<%=isUserID.equals("Y")?"window_resize(410,272)":"window_resize(410,230)"%>">
<div id="wrap" class="w400">
	<form method="post" name="checkdupFrm" action="../comm/CommAction.do" onsubmit="return checkDupID()">
	<input type=hidden name="cmd" value="CommDuplIDCheck">
		<!-- 
		
		 -->
		<% if (isUserID.equals("Y")) { %>
		<fieldset>
			<legend>아이디 중복검사</legend>

			<div id="header">
				<h1><img src="../images/comm/dupIdCheck/title_id.gif" alt="아이디 중복검사"></h1>
				<div id="close"><a href="javascript:self.close()"><img src="../images/comm/dupIdCheck/btn_close.gif" alt="닫기"></a></div>
			</div>
			<div id="content" class="no_border">
				<div class="bx1_shadow mb20">
					<div class="bx1_shadow_iefix bx1_bottom">
						<p class="desc9"><strong><%=user_id%></strong>는 사용할 수 있는 아이디 입니다.<br>

						<a href="javascript:assign_id();"><img src="../images/comm/dupIdCheck/btn_use.gif" alt="사용하기"></a></p>
					</div>
				</div>

				<div class="bx2 mb10">
					<h3 class="mb4"><img src="../images/comm/dupIdCheck/comment_another_id.gif" alt="다른 아이디를 사용하려면?"></h3>
					<p class="desc4">아래 다른 아이디를 입력하고 중복확인을 클릭하세요.</p>
				</div>

				<div class="bx1">
					<div class="bx1_shadow bx1_shadow_iefix">
						<dl class="dl5 bx1_bottom">
							<dt>다른 아이디 입력</dt>
							<dd>
								<input name="id" maxlength="12" type="text" class="input_text w115"> <input name="" type="image" src="../images/comm/dupIdCheck/btn_overlap_confirm2.gif" alt="중복확인">
							</dd>
						</dl>
					</div>
				</div>
			</div>
		</fieldset>
		<% } else { %>
		<fieldset>
			<legend>아이디 중복검사</legend>

			<div id="header">
				<h1><img src="../images/comm/dupIdCheck/title_id.gif" alt="아이디 중복검사"></h1>
				<div id="close"><a href="javascript:self.close()"><img src="../images/comm/dupIdCheck/btn_close.gif" alt="닫기"></a></div>
			</div>
			<div id="content">
				<h2 class="mb7"><strong><%=user_id%></strong>는 이미 사용중인 아이디 입니다.</h2>
				<div class="bx1 mb9">
					<div class="bx1_shadow">

						<dl class="dl1 bx1_bottom">
							<dt>다른 아이디 입력</dt>
							<dd class="">
								<input name="id" maxlength="12" type="text" class="input_text w115">
							</dd>
						</dl>
					</div>
				</div>

				<p class="desc3">위에 다른 아이디를 입력하고 중복확인을 누르세요.</p>
			</div>
			<div id="footer">
				<input name="" type="image" src="../images/comm/dupIdCheck/btn_overlap_confirm.gif" alt="중복확인">
				<a href="javascript:self.close()"><img src="../images/comm/dupIdCheck/btn_cancel.gif" alt="취소"></a>
			</div>
		</fieldset>
		<% } %>
	</form>

</div>
</body>


</html>
