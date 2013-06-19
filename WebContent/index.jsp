<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="kjf.cfg.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<html lang="ko">
<head>
	<title><%=Config.props.get("HOMEPAGE_TITLE")%></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="robots" content="noindex,nofollow">
	<meta name="author" content="부산광역시">
	<meta name="keywords" content="정보통신공사업시스템">
	<meta name="description" content="정보통신공사업시스템">
	<meta http-equiv="imagetoolbar" content="no">
	<link rel="shortcut icon" href="./images/favicon/favicon.ico" />
</head> 

<frameset title="정보통신공사업시스템  메인 페이지" rows="0,*">
  <frame title="내용없는 프레임" name="hidden_frame" scrolling="no" frameborder="0" noresize>
  <frame title="콘텐츠 프레임" name="main_frame" scrolling="auto" src="index/IndexAction.do?cmd=Index" frameborder="0" noresize>
   <noframes title="정보통신공사업시스템 메인 페이지">
    <p><a href="index/IndexAction.do?cmd=Index">프레임을 사용하지 않은 페이지로 연결 합니다.</a></p>   
  </noframes>
</frameset>
</html>