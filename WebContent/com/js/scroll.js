// 스크립트는 반드시 스크롤될 내용 아래쪽에 넣습니다. 안그럼 오류가 발생합니다.
// 스크롤바 따라다니는 메뉴

var stmnLEFT = 920; // 스크롤메뉴의 좌측 위치
var stmnGAP1 = -50; // 페이지 헤더부분의 여백 (이보다 위로는 올라가지 않음)
var stmnGAP2 = 0; // 스크롤시 브라우저 상단과 약간 띄움. 필요없으면 0으로 세팅
var stmnBASE = -100; // 스크롤메뉴 초기 시작위치 (아무렇게나 해도 상관은 없지만 stmnGAP1과 약간 차이를 주는게 보기 좋음)
var stmnActivateSpeed = 1; // 움직임을 감지하는 속도 (숫자가 클수록 늦게 알아차림)
var stmnScrollSpeed = 30; // 스크롤되는 속도 (클수록 늦게 움직임)
var stmnTimer;

function RefreshStaticMenu() {
  var stmnStartPoint, stmnEndPoint, stmnRefreshTimer;
  stmnStartPoint = parseInt(quick.style.top, 10);
  stmnEndPoint = document.body.scrollTop + stmnGAP2;

  if (stmnEndPoint < stmnGAP1) stmnEndPoint = stmnGAP1;
  stmnRefreshTimer = stmnActivateSpeed;

  if ( stmnStartPoint != stmnEndPoint ) {
    stmnScrollAmount = Math.ceil( Math.abs( stmnEndPoint - stmnStartPoint ) / 15 );
    quick.style.top = parseInt(quick.style.top, 10) + ( ( stmnEndPoint < stmnStartPoint ) ? - stmnScrollAmount : stmnScrollAmount ) - 5;
    stmnRefreshTimer = stmnScrollSpeed;
  }

  stmnTimer = setTimeout ("RefreshStaticMenu();", stmnRefreshTimer);
}

function InitializeStaticMenu() {
  quick.style.top = document.body.scrollTop + stmnBASE;
  RefreshStaticMenu();
  quick.style.left = stmnLEFT;
}