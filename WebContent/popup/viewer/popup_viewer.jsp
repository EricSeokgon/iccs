<%@ page contentType="text/html;charset=utf-8"%>
<%
	String imgUri = request.getContextPath();
%>

<html>
<head>
<title>뷰어다운로드</title>
<!-- 공통 : S -->
<link href="<%=imgUri%>/css/total.css" rel="stylesheet" type="text/css" />
<!-- 공통 : E -->

</head>
<body>

	<!-- 뷰어 다운로드 전체 615x470: S <img src="<%=imgUri%>/images/common/viewer_download.gif" alt="" />-->
	<div id="popup">
		<div id="popup_title"><img src="../images/common/popup_title_viewer.gif" alt="뷰어 프로그램 다운로드"></div>
		<div id="popup_close"><a href="javascript:close();"><img src="../images/common/popup_close.gif" alt="닫기"></a></div>
		<div id="popup_contents">
			<div>
				<img src="../images/common/viewer_01.gif" alt="뷰어란 파일로 된 문서들을 보기만 지원하는 프로그램입니다. 뷰어로는 문서내용 보기만 가능하며 문서의 내용을 수정하거나 삭제하는 편집은 할 수 없습니다.">
				<img src="../images/common/viewer_02.gif" alt="정보통신공사업시스템에서 사용하는 문서는 한글2007, 워드, 파워포인트, 엑셀, PDF(아크로벳리더) 5가지입니다.">
				<img src="../images/common/viewer_03.gif" alt="사용하시는 컴퓨터에 해당 뷰어가 설치되어 있지 않은 경우 뷰어를 다운로드 받아 각 개인 컴퓨터에 설치하셔야 합니다.">
				<img src="../images/common/viewer_04.gif" alt="뷰어는 사용하시는 컴퓨터에 한 번만 설치하시면 됩니다.">
				<img src="../images/common/viewer_05.gif" alt="아이콘표시가 있는 첨부파일보기를 클릭하여 내용이 보이지 않는다면 각 해당파일이 설치되지 않았기 때문이므로, 해당뷰어를 설치하시어 내용을 조회하실 수 있습니다.">
			</div>
			<div id="viewer_btn_title">
				<img src="../images/common/viewer_download.gif" alt="Viewer Download">
			</div>
			<div id="viewer_btn">
				<a href="http://www.haansoft.com/hnc/down/down_viewer.action?boardcode=TAEMB&largecode=NVI&svstate=Y" target="blank"><img src="../images/common/viewer_btn_hwp.gif" alt="한글Viwer 다운로드"></a>
				<img src="../images/common/viewer_bar.gif" alt="">
				<a href="http://www.microsoft.com/downloads/details.aspx?FamilyID=3657ce88-7cfa-457a-9aec-f4f827f20cac&DisplayLang=ko" target="blank"><img src="../images/common/viewer_btn_doc.gif" alt="MS Word Viwer 다운로드"></a>
				<img src="../images/common/viewer_bar.gif" alt="">
				<a href="http://www.microsoft.com/downloads/details.aspx?familyid=048DC840-14E1-467D-8DCA-19D2A8FD7485&displaylang=ko" target="blank"><img src="../images/common/viewer_btn_ppt.gif" alt="MS Power Point Viewer 다운로드"></a>
				<img src="../images/common/viewer_bar.gif" alt="">
				<a href="http://www.microsoft.com/downloads/details.aspx?FamilyID=c8378bf4-996c-4569-b547-75edbd03aaf0&DisplayLang=ko" target="blank"><img src="../images/common/viewer_btn_xls.gif" alt="Ms Excel Viewer 다운로드"></a>
				<img src="../images/common/viewer_bar.gif" alt="">
				<a href="http://www.adobe.com/kr/products/acrobat/readstep2.html" target="blank"><img src="../images/common/viewer_btn_pdf.gif" alt="Acrobat Reader 다운로드"></a>
			</div>
		</div>
		<div id="popup_btn_close"><a href="javascript:close();"><img src="../images/common/favorite_btn_close.gif" alt="닫기"></a></div>
	</div>
	<!-- 뷰어 다운로드 전체 : E -->

</body>
</html>