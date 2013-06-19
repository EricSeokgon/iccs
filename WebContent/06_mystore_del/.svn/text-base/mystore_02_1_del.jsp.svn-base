<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="kjf.ops.*" %>
<%@ page import="kjf.util.*" %>
<%@ page import="sp.mystore.*" %>

<%
	ReportEntity rEntity = KJFUtil.REntPrint( (ReportEntity)request.getAttribute("rEntity") );	// 자주가는 창고 등록정보

	MystoreParam pm = (MystoreParam)request.getAttribute("pm");

	//상단 플래쉬 링크 정보
	String top_pageNum  = "";
	String top_sub      = "";
	
	// 좌측 플래쉬 링크 정보
	String left_pageNum = "2";
	String left_sub     = "1";
%>
<script>

// 트리 선택처리
function myMenu(entryId){
	var fm = document.fmParam;
	
	var selObj = treelist.getObject(entryId);

	
	selEntryId = entryId;
	isFolder   = selObj.entry.isfolder;
	selectedId = selObj.entry.id;
	parentId   = selObj.entry.value.pid;
	fav_url    = selObj.entry.value.fav_url;

	selectedName = selObj.entry.desc;
	
	if(!isFolder) {

		var target = document.fmParam.favorite;

		for (var i = 0; i < target.length ; i++)
		{
			if ( (target.options[i].text == selectedName)) {
				alert("이미 선택/등록 하셨습니다.");
				return;
			}
		}
		
		addMenu(selectedName, fav_url);
	}
}

// 옵션 추가
function addMenu(name, value) {

	var value = name + '|' + value;
	
	document.fmParam.favorite.options.add(new Option(name, value));
	document.fmParam.favorite.selectedIndex = document.fmParam.favorite.options.length - 1;
}

// 셀렉트박스 선택
function onClickMenu() {
	var target = document.fmParam.favorite;
	
	if(target.options.length == 0) {
		alert("등록된 메뉴가 없습니다.");
		return;
	}
}

// 옵션 삭제
function removeMenu()
{
	var target = document.fmParam.favorite;

	if (target.selectedIndex < 0) {
		alert('삭제할 메뉴를 선택하세요.');
		return;
	}

	if (!confirm('정말로 메뉴를 삭제하시겠습니까?'))
	return;
	

	target.options.remove(target.selectedIndex);
	document.fmParam.favorite.selectedIndex = document.fmParam.favorite.options.length - 1;
}

function upMenu(selectedIndex) {
	
	var target = document.fmParam.favorite;

	if (selectedIndex < 0)
		return;

	if (selectedIndex == 0)
	{
		alert('더 이상 위로 이동할 수 없습니다.');
		return;
	}
	
	for (var i = selectedIndex - 1; i >= 0; i--)
	{		
		downMenu(i);
		return;
	}
}


function downMenu(selectedIndex)
{
	var target = document.fmParam.favorite;

	if (selectedIndex < 0)
		return;

	if (selectedIndex == target.options.length -1)
	{
		alert('더 이상 아래로 이동할 수 없습니다.');
		return;
	}

	var count = 0; 
	var lastChild = selectedIndex;
	
	for (var  i= selectedIndex + 1; i < target.options.length; i++) {
	
			count++;
			if (count == 1)
			var lastChild = i - 1;
			else if (count == 2)
			break;
	}

	if (count == 0)
		return;
	else
		moveMenus(selectedIndex, lastChild, i-lastChild-1);
}

function moveMenus(s1, s2, distant) {
	for (var i = s2; i >= s1; i--)
		moveMenu(i, distant);
}


function moveMenu(s1, distant)
{
	var target = document.fmParam.favorite;
	var text = target.options[s1].text;
	var value = target.options[s1].value;
	var selected = target.options[s1].selected;

	for (var i = s1; i < s1 + distant; i++)
	{
		target.options[i].text = target.options[i + 1].text;
		target.options[i].value = target.options[i + 1].value;
		target.options[i].selected = target.options[i + 1].selected;
	}

	target.options[s1 + distant].text = text;
	target.options[s1 + distant].value = value;
	target.options[s1 + distant].selected = selected;
}

function submitMenu() {
	
	var target = document.fmParam.favorite;

	var strMenus = new Array();
	
	for (var i = 0; i < target.options.length; i++) {		
		strMenus[i] = target.options[i].value + '|' + i;
	}
	
	document.fmParam.n_favorite.value = strMenus.join(',');

	if (target.options.length == 0) {
		alert("등록할 메뉴가 없습니다. 즐겨찾기를 등록해주세요.");
		return;
		
	} else {
	
		if (confirm('즐겨찾기를 등록 하시겠습니까?')) {		
			document.fmParam.submit();
		}
	}
}

</script>
<html>
<head>
<title>내가 자주가는 창구 페이지입니다.</title>

<!-- 공통 : S -->
<link href="../css/total.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="../com/js/topMenu.js" type="text/javascript"></script>
<script language="JavaScript" src="../flash/flash.js" type="text/javascript"></script>
<script language="JavaScript" src="../com/js/folderTree.js" type="text/javascript"></script>
<!-- 공통 : E -->

</head>
<body>

<!-- 전체 : S -->
<div id="wrapper">
	<div id="skipnavigation">
		<ul>
		<li><a tabindex="0" href="#contents">본문으로 바로가기</a></li>
		<li><a tabindex="0" href="#topmenu">대메뉴로 바로가기</a></li>
		<li><a tabindex="0" href="#leftmenu">소메뉴로 바로가기</a></li>
		</ul>
	</div>
		
	<!-- header : S-->
	<div id="header">
  		<div id="top_navi"><%@ include file="../inc/top.jsp" %></div>	
  		<div id="login"><%@ include file="../inc/top_login.jsp" %></div>
  	</div>
  	<!-- header : E -->
  
    <!-- 페이지 타이틀 : S-->
  	<div id="sub_visual"><%@ include file="sub_visual.jsp" %></div>
 	<!-- 페이지 타이틀 : E -->
 	 
  	<!-- BODY : S -->
	<div id="body">
  	
	  	<!-- left : S -->
	  	<div id="sub_left"><%@ include file="left_menu.jsp" %></div>
		<!-- left : E -->	
	
		<!-- 중간컨텐츠 : S -->
   		<div id="sub_contents"><a name="contents"></a>
			
			<!-- 현재위치 : S -->
			<div id="con_head">
				<p class="position">
					<a href="../"><img src="../images/common/icon_home.gif" alt="HOME" /></a> My민원창구 | 내가자주가는창구
				</p>
			</div>
			<!-- 현재위치 : E -->
	
			<!-- 서브타이틀 : S -->
			<div id="sub_title">
				<h3 class="cont01"><img src="../06_mystore/images/titile_02_1.gif" alt="내가 자주가는 창구" /></h3>
			</div>
			<!-- 서브타이틀 : E -->
   
		  	<!-- 컨텐츠 : S -->
			<div id="contents"><%@include file="inc_mystore_02_1.jsp"%></div>
		   	<!-- 컨텐츠 : E -->
		   	
		</div>
		<!-- 중간컨텐츠 : E -->
  	
  	
	  	<!--퀵메뉴 : S -->
		<div id="quick"><%@ include file="../inc/quick.jsp" %></div>			
		<!--퀵메뉴 : E -->
 	
 	</div>
 	<!-- BODY : E -->
 	
  	<!-- copyright : S -->
 	<div id="foot"><%@ include file="../inc/copy.jsp" %></div> 
 	<!-- copyright : E -->
 	
</div>
<!-- 전체 : E -->

</body>
</html>
