<%@ page contentType="text/html;charset=utf-8"%>
<%
	String imgUri = request.getContextPath();
%>

<html>
<head>
<title>모바일 이용안내</title>
<!-- 공통 : S -->

<link href="<%=imgUri%>/css/total.css" rel="stylesheet" type="text/css" />

<!-- 공통 : E -->

</head>
<body>

	<!-- 모바일 이용안내 전체 615x470: S <img src="<%=imgUri%>/images/common/mobile_use.gif" alt="" /> -->
	<div id="popup_mobile">
		<div id="popup_title"><img src="../images/common/popup_title_mobile.gif" alt="모바일 이용안내"></div>
		<div id="popup_close"><a href="javascript:close();"><img src="../images/common/popup_close.gif" alt="닫기"></a></div>
		<div id="popup_contents">
			<img src="../images/common/mobile_use.gif" alt="서비스 준비중입니다." style="margin-top: 90px;">
		</div>
		<div id="popup_btn_close"><a href="javascript:close();"><img src="../images/common/favorite_btn_close.gif" alt="닫기"></a></div>
	</div>
	<!-- 모바일 이용안내 전체 : E -->

</body>
</html>