function on_div_play() {
	if (document.getElementById('quick_mystore').style.display=="none")
	{
		document.getElementById('quick_mystore').style.display="block";
	}
	else
	{
		document.getElementById('quick_mystore').style.display="none";
	}
}
function on_div_play_main() {
	if (document.getElementById('quick_mystore_main').style.display=="none")
	{
		document.getElementById('quick_mystore_main').style.display="block";
	}
	else
	{
		document.getElementById('quick_mystore_main').style.display="none";
	}
}

function open_Viewer() {
	winOpenAtCenter("../popup/viewer.do", "", 615, 470);
}

function open_Mobile() {
	winOpenAtCenter("../popup/mobile.do", "", 615, 470);
}

function open_QuickUseBefore() {	
	winOpenAtCenter("../usebefore/UseBeforeAction.do?cmd=UseBeforeQuickStatus", "", 660, 470);
}

//팝업에 관련된 UI 에 독립적인 함수들 모음  ################################################################################
 function winOpenAtCenter(sURL, sWindowName, w, h, sScroll) {
	  // 화면 중앙으로 Popup 띄우기.. 스크롤바는 옵션..
	  // ex)
	  //      openWin("test.asp", "winTest", 400, 300);     ☞ 스크롤바 없음
	  //      openWin("test.asp", "winTest", 400, 300, "yes");  ☞ 스크롤바 있음
	  //      openWin("test.asp", "winTest", 400, 300, "auto"); ☞ 스크롤바 자동

	  var x = (screen.width - w) / 2;
	  var y = (screen.height - h) / 2;

	  if (sScroll==null) sScroll = "no";

	  var sOption = "";
	  sOption = sOption + "toolbar=no, channelmode=no, location=no, directories=no, resizable=no, menubar=no";
	  sOption = sOption + ", scrollbars=" + sScroll + ", left=" + x + ", top=" + y + ", width=" + w + ", height=" + h;

	  var win = window.open(sURL, sWindowName, sOption);
	  return win;
}

function winOpenAtCenter2(sURL, sWindowName, w, h, sScroll) {
	var win2 = winOpenAtCenter(sURL, sWindowName, w, h, sScroll);
};

function window_resize(winWidth, winHeight) {

	// 스크롤 없애기
	document.body.style.overflow='hidden';

	var clintAgent = navigator.userAgent;
	var foxHeight = 80;
	var ieHeight = 60;
	var ie7Height = 80;
	var ie8Height = 90;

	if ( clintAgent.indexOf("MSIE") != -1 )	// IE 일 경우
	{
		var res = isIE7();
		if( isIE7() ) {	window.resizeTo(winWidth, winHeight + ie7Height); }
		else if( isIE8() ) { window.resizeTo(winWidth, winHeight + ie8Height); }
		else { window.resizeTo(winWidth, winHeight + ieHeight); }
	}
	else	// IE 가 아닐 경우
	{
		window.resizeTo(winWidth, winHeight + foxHeight);
	}
}
// 팝업에 관련된 UI 에 독립적인 함수들 모음  ################################################################################