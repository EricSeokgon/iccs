<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.Vector"%>
<%@ page import="kjf.ops.*"%>
<%@ page import="kjf.util.*"%>
<%@ page import="sp.comm.CommParam"%>

<%
	ReportEntity rEntity 	=(ReportEntity)request.getAttribute("rEntity");
%>

<html>
<head>
<title>우편번호 검색</title>
<link rel="stylesheet" href="../css/join_pop.css" type="text/css">
<script language="javascript" type="text/javascript" src="../com/js/kjs.js"></script>
<script>

<!--
function beforeKjs(){
	var fm = document.fmParam;
	
	return true;
}

function selZipCode(zipcode,addr){
	<% if(KJFUtil.isEmpty(request.getParameter("reqCol"))) {%>
		opener.document.fmParam.USER_ZIPCODE.value = zipcode;
		opener.document.fmParam.USER_ADDR.value = addr;
		opener.document.fmParam.USER_ADDR_ETC.focus();
	<% } else {
		String[] reqCol = request.getParameter("reqCol").split(",");
	%>
		opener.document.fmParam.<%=reqCol[0]%>.value = zipcode;
		opener.document.fmParam.<%=reqCol[1]%>.value = addr;
		opener.document.fmParam.<%=reqCol[2]%>.focus();
	<% } %>
	this.close();
}

-->
</script>

</head>

<body>
<div id="wrap" class="w400">
	<form name="fmParam" method="post" action="../comm/CommAction.do?cmd=CommZipSearch">
	<input type="hidden" name="reqCol" value="<%=KJFUtil.print(request.getParameter("reqCol"))%>">
	
	<fieldset>
			<legend>아이디 중복검사</legend>

			<div id="header">
				<h1><img src="../images/comm/postSearch/title_post.gif" alt="우편번호찾기"></h1>
				<div id="close"><a href="javascript:self.close()"><img src="../images/comm/dupIdCheck/btn_close.gif" alt="닫기"></a></div>
			</div>
			<div id="content" class="no_border">
				<div class="bx2 mb10">
					<h3 class="mb4"><img src="../images/comm/postSearch/coment_post_search.gif" alt="우편번호를 찾으시려면?"></h3>
					<p class="desc4">찾고자 하는 주소의 동/읍/면의 이름을 공백없이 입력하신 후<br>
					<strong>검색</strong>버튼을 누르세요.</p>
				</div>

				<div class="bx1">
					<div class="bx1_shadow bx1_shadow_iefix">
						<dl class="dl5 bx1_bottom">
							<dt></dt>
							<dd>
								<input type=text name=searchKey required="동(읍/면)을 입력해주십시오." maxlength="12" type="text" class="input_text w115"> <input name="" type="image" src="../images/bbs/btn_search.gif" alt="검색">
							</dd>
						</dl>
					</div>
				</div>
			</div>
		</fieldset>
		<div id="post_result" class="bx2 mb10">
				<p class="desc4">
				<%
					if (rEntity != null) {
					
						String sido 	= null;
						String gugun 	= null;
						String dong 	= null;
						String bunji 	= null;
						String zipcode 	= null;
						String addr 	= null;
						String addr1 	= null;
						
						for (int i = 0; i < rEntity.getRowCnt(); i++ ) {
							sido 	= rEntity.getValue(i,"SIDO");
							gugun	= rEntity.getValue(i,"GUGUN");
							dong 	= rEntity.getValue(i,"DONG");
							bunji 	= rEntity.getValue(i,"BUNJI");
							zipcode = rEntity.getValue(i,"ZIPCODE");
							
							addr = sido + " " + gugun + " " + dong + " " + bunji;
							addr1 = sido + " " + gugun + " " + dong;
						
				%> 
					<a href="javascript:selZipCode('<%=zipcode%>','<%=addr1%>')" class="sfont_orange">[<%=zipcode%>]<%=addr%> </a> <br>
				<%
						}
					}
				%>
				</p>
		</div>
		
	<div id="footer">
		<a href="javascript:self.close()"><img src="../images/comm/postSearch/btn_close.gif" alt="닫기"></a>
	</div>
	
	
	
	</table>
	</form>
</div>
</body>
</html>
