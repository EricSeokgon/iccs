// ��ũ��Ʈ�� �ݵ�� ��ũ�ѵ� ���� �Ʒ��ʿ� �ֽ��ϴ�. �ȱ׷� ������ �߻��մϴ�.
// ��ũ�ѹ� ����ٴϴ� �޴�

var stmnLEFT = 920; // ��ũ�Ѹ޴��� ���� ��ġ
var stmnGAP1 = -50; // ������ ����κ��� ���� (�̺��� ���δ� �ö��� ����)
var stmnGAP2 = 0; // ��ũ�ѽ� ������ ��ܰ� �ణ ���. �ʿ������ 0���� ����
var stmnBASE = -100; // ��ũ�Ѹ޴� �ʱ� ������ġ (�ƹ����Գ� �ص� ����� ������ stmnGAP1�� �ణ ���̸� �ִ°� ���� ����)
var stmnActivateSpeed = 1; // �������� �����ϴ� �ӵ� (���ڰ� Ŭ���� �ʰ� �˾�����)
var stmnScrollSpeed = 30; // ��ũ�ѵǴ� �ӵ� (Ŭ���� �ʰ� ������)
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