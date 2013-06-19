<%@ page contentType="text/html;charset=utf-8"%>

<!-- 자주가는 창구 이용방법 : S  -->
<h4><img src="../06_mystore/images/sub_title_02_1.gif" alt="자주가는 창구 이용방법" /></h4>
<p class="txt">
	사이트맵에 나타난 메뉴명을 선택하셔서 파일을<strong>클릭</strong> 하시면  <strong>자주가는 창구</strong>로 추가됩니다.<br>  
	자주가는 창구로 추가된 항목은<img src="../images/common/icon_up.gif" alt="위로" align="absmiddle"/>
	<img src="../images/common/icon_down.gif" alt="아래로" align="absmiddle"/>
	버튼을 누르시면, 선택한 메뉴의 순서를 이동할 수 있습니다. <br> 
	수정한 내용은 <strong>‘확인’</strong> 버튼을 누르셔야만 저장됩니다.
</p>
<!-- 자주가는 창구 이용방법 : E  -->

<form name="fmParam" method="post" action="../mystore/MystoreAction.do?cmd=MyUseStoreCUD">
<input type="hidden" name="n_favorite">

<!-- 자주가는 창구 관리 : S  -->
<h4><img src="../06_mystore/images/sub_title_02_2.gif" alt="회원탈퇴 관련사항" /></h4>
	<!-- 전체 : S  -->
	<div id="favorite_M">
		<!-- 사이트맵 : S  -->
		<div id="favorite_M_sitemap">
			<div class="center">
				<img src="../06_mystore/images/img_02_01.gif" alt="사이트맵 " />
			</div>
			<div id="favorite_M_sitemapW">
				
					<!-- 자주가는 창고 테이블 : S -->
					<table width="100%" height="180">
						<tr>
							<td valign="top"><div id="orgDiv"></div>						
								<script>
								
								var treelist = new TreeList();
								var images   = new TreeImages("../images/treeicon/favorite");
								var data;
								
								//초기 모든 폴더는 닫은 상태로 설정
								treelist.setOpenAll(false);
								treelist.clickOnItem = myMenu;
								treelist.setPaintingDiv("orgDiv");
								treelist.setImages(images);
								writeDoc = false;
								
								topFoldId = '0';
								data = {pid:'null', fav_url:'null'};
								var F_0 = new Folder(treelist, '0', '정보통신행정시스템', data);

								// ------------ 원스톱 민원시스템 : S ------------
								data = {pid:'F_ONE', fav_url:'about:blank'};
								var F_ONE = F_0.addFolder(new Folder(treelist, 'F_ONE', '원스톱민원시스템' ,data));

								// 폴더
								data = {pid:'F_ONE_1', fav_url:'about:blank'};
								var F_ONE_1 = F_ONE.addFolder(new Folder(treelist, 'F_ONE_1', '등록관리' ,data));
								data = {pid:'F_ONE_2', fav_url:'about:blank'};
								var F_ONE_2 = F_ONE.addFolder(new Folder(treelist, 'F_ONE_2', '착공전설계도확인' ,data));
								data = {pid:'F_ONE_3', fav_url:'about:blank'};
								var F_ONE_3 = F_ONE.addFolder(new Folder(treelist, 'F_ONE_3', '사용전검사관리' ,data));
								data = {pid:'F_ONE_4', fav_url:'about:blank'};
								var F_ONE_4 = F_ONE.addFolder(new Folder(treelist, 'F_ONE_4', '위법관리' ,data));
								data = {pid:'F_ONE_5', fav_url:'about:blank'};
								var F_ONE_5 = F_ONE.addFolder(new Folder(treelist, 'F_ONE_5', '공사업정보검색' ,data));

								// 등록관리 아이템 : S
								data = {pid:'I_ONE_1_1', fav_url:'../regmgr/RegMgrAction.do?cmd=PubWorkRegInfo'};
								var I_ONE_1_1 = F_ONE_1.addItem(new Item(treelist, 'I_ONE_1_1', '공사업 등록정보', data));
								data = {pid:'I_ONE_1_2', fav_url:'../regmgr/RegMgrAction.do?cmd=PubWorkRegReportList'};
								var I_ONE_1_2 = F_ONE_1.addItem(new Item(treelist, 'I_ONE_1_2', '공사업 등록기준 신고', data));
								data = {pid:'I_ONE_1_3', fav_url:'../regmgr/RegMgrAction.do?cmd=PubWorkTransfer'};
								var I_ONE_1_3 = F_ONE_1.addItem(new Item(treelist, 'I_ONE_1_3', '공사업 양도양수', data));
								data = {pid:'I_ONE_1_4', fav_url:'../regmgr/RegMgrAction.do?cmd=PubWorkAffiliation'};
								var I_ONE_1_4 = F_ONE_1.addItem(new Item(treelist, 'I_ONE_1_4', '공사업 합병', data));
								data = {pid:'I_ONE_1_5', fav_url:'../regmgr/RegMgrAction.do?cmd=PubWorkChReport'};
								var I_ONE_1_5 = F_ONE_1.addItem(new Item(treelist, 'I_ONE_1_5', '공사업 변경신고', data));
								// 등록관리 아이템 : E
								
								// 착공전설계도확인 아이템 : S
								data = {pid:'I_ONE_2_1', fav_url:'../stwork/StworkAction.do?cmd=StWorkPlanInfoList'};
								var I_ONE_2_1 = F_ONE_2.addItem(new Item(treelist, 'I_ONE_2_1', '착공전설계도확인 현황', data));
								// 착공전설계도확인 아이템 : E
								
								// 사용전검사관리 아이템 : S
								data = {pid:'I_ONE_3_1', fav_url:'../usebefore/UseBeforeAction.do?cmd=UseBeforeChkStatus'};
								var I_ONE_3_1 = F_ONE_3.addItem(new Item(treelist, 'I_ONE_3_1', '사용전검사  현황', data));
								// 사용전검사관리 아이템 : E
								
								// 위법관리 아이템 : S
								data = {pid:'I_ONE_4_1', fav_url:'../illegality/IllegalityAction.do?cmd=IllegalityStatus'};
								var I_ONE_4_1 = F_ONE_4.addItem(new Item(treelist, 'I_ONE_4_1', '위법현황', data));
								data = {pid:'I_ONE_4_2', fav_url:'../illegality/IllegalityAction.do?cmd=IllBusiSusList'};
								var I_ONE_4_2 = F_ONE_4.addItem(new Item(treelist, 'I_ONE_4_2', '영업정지', data));
								data = {pid:'I_ONE_4_3', fav_url:'../illegality/IllegalityAction.do?cmd=IllRegistCancel'};
								var I_ONE_4_3 = F_ONE_4.addItem(new Item(treelist, 'I_ONE_4_3', '등록취소', data));
								data = {pid:'I_ONE_4_4', fav_url:'../illegality/IllegalityAction.do?cmd=IllNegFineList'};
								var I_ONE_4_4 = F_ONE_4.addItem(new Item(treelist, 'I_ONE_4_4', '과태료부과', data));
								data = {pid:'I_ONE_4_5', fav_url:'../illegality/IllegalityAction.do?cmd=IllWarningList'};
								var I_ONE_4_5 = F_ONE_4.addItem(new Item(treelist, 'I_ONE_4_5', '경고조치', data));
								data = {pid:'I_ONE_4_6', fav_url:'../illegality/IllegalityAction.do?cmd=IllCorOrderList'};
								var I_ONE_4_6 = F_ONE_4.addItem(new Item(treelist, 'I_ONE_4_6', '시정명령', data));
								data = {pid:'I_ONE_4_7', fav_url:'../illegality/IllegalityAction.do?cmd=IllProsecutionList'};
								var I_ONE_4_7 = F_ONE_4.addItem(new Item(treelist, 'I_ONE_4_7', '형사고발', data));
								// 위법관리 아이템 : E
								
								// 공사업정보검색 아이템 : S
								data = {pid:'I_ONE_5_1', fav_url:'../cominfo/ComInfoAction.do?cmd=InfoCommTrader'};
								var I_ONE_5_1 = F_ONE_5.addItem(new Item(treelist, 'I_ONE_5_1', '정보통신공사업자 검색', data));
								data = {pid:'I_ONE_5_2', fav_url:'../cominfo/ComInfoAction.do?cmd=InfoExecValuation'};
								var I_ONE_5_2 = F_ONE_5.addItem(new Item(treelist, 'I_ONE_5_2', '시공능력평가', data));
								data = {pid:'I_ONE_5_3', fav_url:'../cominfo/ComInfoAction.do?cmd=InfoAdmMeasure'};
								var I_ONE_5_3 = F_ONE_5.addItem(new Item(treelist, 'I_ONE_5_3', '행정처분', data));
								// 공사업정보검색 아이템 : E
								
								// ------------ 원스톰 민원시스템 : E ------------								
								
								
								// ------------ 길라잡이 : S ------------
								data = {pid:'F_GUIDE', fav_url:'about:blank'};
								var F_GUIDE = F_0.addFolder(new Folder(treelist, 'F_GUIDE', '길라잡이' ,data));								
								
								// 폴더
								data = {pid:'F_GUIDE_1', fav_url:'about:blank'};
								var F_GUIDE_1 = F_GUIDE.addFolder(new Folder(treelist, 'F_GUIDE_1', '공사업등록/신고' ,data));
								data = {pid:'F_GUIDE_2', fav_url:'about:blank'};
								var F_GUIDE_2 = F_GUIDE.addFolder(new Folder(treelist, 'F_GUIDE_2', '착공전설계도확인' ,data));
								data = {pid:'F_GUIDE_3', fav_url:'about:blank'};
								var F_GUIDE_3 = F_GUIDE.addFolder(new Folder(treelist, 'F_GUIDE_3', '사용전검사' ,data));
								data = {pid:'F_GUIDE_4', fav_url:'about:blank'};
								var F_GUIDE_4 = F_GUIDE.addFolder(new Folder(treelist, 'F_GUIDE_4', '행정처분기준' ,data));
								data = {pid:'F_GUIDE_5', fav_url:'about:blank'};
								var F_GUIDE_5 = F_GUIDE.addFolder(new Folder(treelist, 'F_GUIDE_5', '과태료부과기준' ,data));
								data = {pid:'F_GUIDE_6', fav_url:'about:blank'};
								var F_GUIDE_6 = F_GUIDE.addFolder(new Folder(treelist, 'F_GUIDE_6', '정보통신기술자.감리원' ,data));
								data = {pid:'F_GUIDE_7', fav_url:'about:blank'};
								var F_GUIDE_7 = F_GUIDE.addFolder(new Folder(treelist, 'F_GUIDE_7', '법령정보' ,data));
								data = {pid:'F_GUIDE_8', fav_url:'about:blank'};
								var F_GUIDE_8 = F_GUIDE.addFolder(new Folder(treelist, 'F_GUIDE_8', '서식자료' ,data));

								// 공사업등록/신고 아이템 : S
								data = {pid:'F_GUIDE_1_1', fav_url:'../hms/HmsAction.do?cmd=HmsView&menu_id=211'};
								var F_GUIDE_1_1 = F_GUIDE_1.addItem(new Item(treelist, 'F_GUIDE_1_1', '등록안내', data));
								data = {pid:'F_GUIDE_1_2', fav_url:'../hms/HmsAction.do?cmd=HmsView&menu_id=212'};
								var F_GUIDE_1_2 = F_GUIDE_1.addItem(new Item(treelist, 'F_GUIDE_1_2', '신규등록', data));
								data = {pid:'F_GUIDE_1_3', fav_url:'../hms/HmsAction.do?cmd=HmsView&menu_id=213'};
								var F_GUIDE_1_3 = F_GUIDE_1.addItem(new Item(treelist, 'F_GUIDE_1_3', '등록기준신고', data));
								data = {pid:'F_GUIDE_1_4', fav_url:'../hms/HmsAction.do?cmd=HmsView&menu_id=214'};
								var F_GUIDE_1_4 = F_GUIDE_1.addItem(new Item(treelist, 'F_GUIDE_1_4', '양도양수', data));
								data = {pid:'F_GUIDE_1_5', fav_url:'../hms/HmsAction.do?cmd=HmsView&menu_id=215'};
								var F_GUIDE_1_5 = F_GUIDE_1.addItem(new Item(treelist, 'F_GUIDE_1_5', '합병', data));
								data = {pid:'F_GUIDE_1_6', fav_url:'../hms/HmsAction.do?cmd=HmsView&menu_id=216'};
								var F_GUIDE_1_6 = F_GUIDE_1.addItem(new Item(treelist, 'F_GUIDE_1_6', '변경신고', data));
								data = {pid:'F_GUIDE_1_7', fav_url:'../hms/HmsAction.do?cmd=HmsView&menu_id=217'};
								var F_GUIDE_1_7 = F_GUIDE_1.addItem(new Item(treelist, 'F_GUIDE_1_7', '폐업신고', data));
								data = {pid:'F_GUIDE_1_8', fav_url:'../hms/HmsAction.do?cmd=HmsView&menu_id=218'};
								var F_GUIDE_1_8 = F_GUIDE_1.addItem(new Item(treelist, 'F_GUIDE_1_8', '재교부', data));

								// 착공전설계도확인 아이템  : S
								data = {pid:'F_GUIDE_2_1', fav_url:'../hms/HmsAction.do?cmd=HmsView&menu_id=221'};
								var F_GUIDE_2_1 = F_GUIDE_2.addItem(new Item(treelist, 'F_GUIDE_2_1', '착공전설계도확인', data));
								// 착공전설계도확인 아이템  : E
								
								// 사용전검사 아이템  : S
								data = {pid:'F_GUIDE_3_1', fav_url:'../hms/HmsAction.do?cmd=HmsView&menu_id=231'};
								var F_GUIDE_3_1 = F_GUIDE_3.addItem(new Item(treelist, 'F_GUIDE_3_1', '사용전검사', data));
								// 사용전검사 아이템  : E
								
								// 행정처분기준 아이템  : S
								data = {pid:'F_GUIDE_4_1', fav_url:'../hms/HmsAction.do?cmd=HmsView&menu_id=241'};
								var F_GUIDE_4_1 = F_GUIDE_4.addItem(new Item(treelist, 'F_GUIDE_4_1', '행정처분기준', data));
								// 행정처분기준 아이템  : E
								
								// 과태료부과기준 아이템  : S
								data = {pid:'F_GUIDE_5_1', fav_url:'../hms/HmsAction.do?cmd=HmsView&menu_id=251'};
								var F_GUIDE_5_1 = F_GUIDE_5.addItem(new Item(treelist, 'F_GUIDE_5_1', '과태료부과기준', data));
								// 과태료부과기준 아이템  : E
								
								// 정보통신기술자.감리원 : S
								data = {pid:'F_GUIDE_6_1', fav_url:'../hms/HmsAction.do?cmd=HmsView&menu_id=261'};
								var F_GUIDE_6_1 = F_GUIDE_6.addItem(new Item(treelist, 'F_GUIDE_6_1', '정보통신기술자.감리원', data));
								// 정보통신기술자.감리원 : E
								
								// 법령정보  아이템 : S
								data = {pid:'F_GUIDE_7_1', fav_url:'../hms/HmsAction.do?cmd=HmsView&menu_id=271'};
								var F_GUIDE_7_1 = F_GUIDE_7.addItem(new Item(treelist, 'F_GUIDE_7_1', '빠른 법령 검색', data));
								data = {pid:'F_GUIDE_7_2', fav_url:'../hms/HmsAction.do?cmd=HmsView&menu_id=272'};
								var F_GUIDE_7_2 = F_GUIDE_7.addItem(new Item(treelist, 'F_GUIDE_7_2', '정보통신공사업법령', data));
								data = {pid:'F_GUIDE_7_3', fav_url:'../hms/HmsAction.do?cmd=HmsView&menu_id=273'};
								var F_GUIDE_7_3 = F_GUIDE_7.addItem(new Item(treelist, 'F_GUIDE_7_3', '입찰계약관련법령', data));
								data = {pid:'F_GUIDE_7_4', fav_url:'../hms/HmsAction.do?cmd=HmsView&menu_id=274'};
								var F_GUIDE_7_4 = F_GUIDE_7.addItem(new Item(treelist, 'F_GUIDE_7_4', '산업안전관련법령', data));
								data = {pid:'F_GUIDE_7_5', fav_url:'../hms/HmsAction.do?cmd=HmsView&menu_id=275'};
								var F_GUIDE_7_5 = F_GUIDE_7.addItem(new Item(treelist, 'F_GUIDE_7_5', '기타관련법령', data));
								// 법령정보 아이템 : E
								
								// 법령정보  아이템 : S
								data = {pid:'F_GUIDE_8_1', fav_url:'../bbs/BbsAction.do?cmd=BbsKList&CT_ID=FORM_MORGUE'};
								var F_GUIDE_8_1 = F_GUIDE_8.addItem(new Item(treelist, 'F_GUIDE_8_1', '서식자료실', data));
								// 법령정보  아이템 : E
								
								// ------------ 길라잡이 : E ------------
								
								
								// ------------ 커뮤니티 : S ------------
								data = {pid:'F_COMMUNITY', fav_url:'about:blank'};
								var F_COMMUNITY = F_0.addFolder(new Folder(treelist, 'F_COMMUNITY', '커뮤니티' ,data));

								// 폴더
								data = {pid:'F_COMMUNITY_1', fav_url:'about:blank'};
								var F_COMMUNITY_1 = F_COMMUNITY.addFolder(new Folder(treelist, 'F_COMMUNITY_1', '공지사항' ,data));
								data = {pid:'F_COMMUNITY_2', fav_url:'about:blank'};
								var F_COMMUNITY_2 = F_COMMUNITY.addFolder(new Folder(treelist, 'F_COMMUNITY_2', '지역뉴스' ,data));
								data = {pid:'F_COMMUNITY_3', fav_url:'about:blank'};
								var F_COMMUNITY_3 = F_COMMUNITY.addFolder(new Folder(treelist, 'F_COMMUNITY_3', '공사업공고' ,data));
								data = {pid:'F_COMMUNITY_4', fav_url:'about:blank'};
								var F_COMMUNITY_4 = F_COMMUNITY.addFolder(new Folder(treelist, 'F_COMMUNITY_4', '자료실' ,data));
								data = {pid:'F_COMMUNITY_5', fav_url:'about:blank'};
								var F_COMMUNITY_5 = F_COMMUNITY.addFolder(new Folder(treelist, 'F_COMMUNITY_5', 'FAQ' ,data));
								data = {pid:'F_COMMUNITY_6', fav_url:'about:blank'};
								var F_COMMUNITY_6 = F_COMMUNITY.addFolder(new Folder(treelist, 'F_COMMUNITY_6', '자유게시판' ,data));
								data = {pid:'F_COMMUNITY_7', fav_url:'about:blank'};
								var F_COMMUNITY_7 = F_COMMUNITY.addFolder(new Folder(treelist, 'F_COMMUNITY_7', '파트너쉽게시판' ,data));

								// 공지사항 아이템 : S
								data = {pid:'I_COMMUNITY_1_1', fav_url:'../bbs/BbsAction.do?cmd=BbsKList&CT_ID=NOTICE'};
								var I_COMMUNITY_1_1 = F_COMMUNITY_1.addItem(new Item(treelist, 'I_COMMUNITY_1_1', '공지사항', data));
								// 공지사항 아이템 : E
								
								// 지역뉴스 아이템 : S
								data = {pid:'I_COMMUNITY_2_1', fav_url:'../bbs/BbsAction.do?cmd=BbsKList&CT_ID=NEWS'};
								var I_COMMUNITY_2_1 = F_COMMUNITY_2.addItem(new Item(treelist, 'I_COMMUNITY_2_1', '지역뉴스', data));
								// 지역뉴스 아이템 : E
								
								// 공사업공고 아이템 : S
								data = {pid:'I_COMMUNITY_3_1', fav_url:'../bbs/BbsAction.do?cmd=BbsKList&CT_ID=REG_PUBLIC'};
								var I_COMMUNITY_3_1 = F_COMMUNITY_3.addItem(new Item(treelist, 'I_COMMUNITY_3_1', '공사업 등록 공고', data));
								data = {pid:'I_COMMUNITY_3_2', fav_url:'../bbs/BbsAction.do?cmd=BbsKList&CT_ID=CON_PUBLIC'};
								var I_COMMUNITY_3_2 = F_COMMUNITY_3.addItem(new Item(treelist, 'I_COMMUNITY_3_2', '공사업 변경사항 공고', data));
								data = {pid:'I_COMMUNITY_3_3', fav_url:'../bbs/BbsAction.do?cmd=BbsKList&CT_ID=DIS_PUBLIC'};
								var I_COMMUNITY_3_3 = F_COMMUNITY_3.addItem(new Item(treelist, 'I_COMMUNITY_3_3', '행정처분 공고', data));
								// 공사업공고 아이템 : E
								
								// 자료실 아이템 : S
								data = {pid:'I_COMMUNITY_4_1', fav_url:'../bbs/BbsAction.do?cmd=BbsKList&CT_ID=TEC_MORGUE'};
								var I_COMMUNITY_4_1 = F_COMMUNITY_4.addItem(new Item(treelist, 'I_COMMUNITY_4_1', '기술자료실', data));
								data = {pid:'I_COMMUNITY_4_2', fav_url:'../bbs/BbsAction.do?cmd=BbsKList&CT_ID=MEM_MORGUE'};
								var I_COMMUNITY_4_2 = F_COMMUNITY_4.addItem(new Item(treelist, 'I_COMMUNITY_4_2', '회원사자료실', data));
								// 자료실 아이템 : E
								
								// FAQ 아이템 : S
								data = {pid:'I_COMMUNITY_5_1', fav_url:'../bbs/BbsAction.do?cmd=BbsKList&CT_ID=FAQ_PUB'};
								var I_COMMUNITY_5_1 = F_COMMUNITY_5.addItem(new Item(treelist, 'I_COMMUNITY_5_1', 'FAQ', data));
								// FAQ 아이템 : E
								
								// 자유게시판 아이템 : S
								data = {pid:'I_COMMUNITY_6_1', fav_url:'../bbs/BbsAction.do?cmd=BbsKList&CT_ID=FREE'};
								var I_COMMUNITY_6_1 = F_COMMUNITY_6.addItem(new Item(treelist, 'I_COMMUNITY_6_1', '자유게시판', data));
								// 자유게시판 아이템 : E
								
								// 파트너쉽게시판 아이템 : S
								data = {pid:'I_COMMUNITY_7_1', fav_url:'../bbs/BbsAction.do?cmd=BbsKList&CT_ID=PARTNER'};
								var I_COMMUNITY_7_1 = F_COMMUNITY_7.addItem(new Item(treelist, 'I_COMMUNITY_7_1', '파트너쉽게시판', data));
								// 파트너쉽게시판 아이템 : E								
								
								// ------------ 커뮤니티 : E ------------
								
								
								// ------------ 민원시스템안내 : S ------------
								data = {pid:'F_GUIDANCE', fav_url:'about:blank'};
								var F_GUIDANCE = F_0.addFolder(new Folder(treelist, 'F_GUIDANCE', '민원시스템안내' ,data));

								// 폴더
								data = {pid:'F_GUIDANCE_1', fav_url:'about:blank'};
								var F_GUIDANCE_1 = F_GUIDANCE.addFolder(new Folder(treelist, 'F_GUIDANCE_1', '주요정책안내' ,data));
								data = {pid:'F_GUIDANCE_2', fav_url:'about:blank'};
								var F_GUIDANCE_2 = F_GUIDANCE.addFolder(new Folder(treelist, 'F_GUIDANCE_2', '사이버민원센터 운영' ,data));

								// 주요정책안내 아이템 : S
								data = {pid:'I_GUIDANCE_1_1', fav_url:'../hms/HmsAction.do?cmd=HmsView&menu_id=411'};
								var I_GUIDANCE_1_1 = F_GUIDANCE_1.addItem(new Item(treelist, 'I_GUIDANCE_1_1', '가이드라인', data));
								data = {pid:'I_GUIDANCE_1_2', fav_url:'../hms/HmsAction.do?cmd=HmsView&menu_id=412'};
								var I_GUIDANCE_1_2 = F_GUIDANCE_1.addItem(new Item(treelist, 'I_GUIDANCE_1_2', '공사협회 및 공제 조합', data));
								data = {pid:'I_GUIDANCE_1_3', fav_url:'../hms/HmsAction.do?cmd=HmsView&menu_id=413'};
								var I_GUIDANCE_1_3 = F_GUIDANCE_1.addItem(new Item(treelist, 'I_GUIDANCE_1_3', '정책안내 게시판', data));
								// 주요정책안내 아이템 : E
								
								// 사이버민원센터 운영 아이템 : S
								data = {pid:'I_GUIDANCE_2_1', fav_url:'../hms/HmsAction.do?cmd=HmsView&menu_id=421'};
								var I_GUIDANCE_2_1 = F_GUIDANCE_2.addItem(new Item(treelist, 'I_GUIDANCE_2_1', '원스톱민원시스템', data));
								data = {pid:'I_GUIDANCE_2_2', fav_url:'../hms/HmsAction.do?cmd=HmsView&menu_id=422'};
								var I_GUIDANCE_2_2 = F_GUIDANCE_2.addItem(new Item(treelist, 'I_GUIDANCE_2_2', '시스템 구성도 ', data));
								data = {pid:'I_GUIDANCE_2_3', fav_url:'../hms/HmsAction.do?cmd=HmsView&menu_id=423'};
								var I_GUIDANCE_2_3 = F_GUIDANCE_2.addItem(new Item(treelist, 'I_GUIDANCE_2_3', '향후 서비스 발전 방향', data));
								data = {pid:'I_GUIDANCE_2_4', fav_url:'../hms/HmsAction.do?cmd=HmsView&menu_id=424'};
								var I_GUIDANCE_2_4 = F_GUIDANCE_2.addItem(new Item(treelist, 'I_GUIDANCE_2_4', 'PDF 서비스', data));
								data = {pid:'I_GUIDANCE_2_5', fav_url:'../chart/ChartAction.do?cmd=ChartArea'};
								var I_GUIDANCE_2_5 = F_GUIDANCE_2.addItem(new Item(treelist, 'I_GUIDANCE_2_5', '통계자료', data));
								// 사이버민원센터 운영 아이템 : E
								
								// ------------ 민원시스템안내 : E ------------
								
								
								// ------------ 사업소개 : S ------------
								data = {pid:'F_INTRODUCE', fav_url:'about:blank'};
								var F_INTRODUCE = F_0.addFolder(new Folder(treelist, 'F_INTRODUCE', '사업소개' ,data));

								// 사업소개 아이템 : S
								data = {pid:'I_INTRODUCE_1', fav_url:'../hms/HmsAction.do?cmd=HmsView&menu_id=511'};
								var I_INTRODUCE_1 = F_INTRODUCE.addItem(new Item(treelist, 'F_INTRODUCE_1', '비전&전략' ,data));
								data = {pid:'F_INTRODUCE_2', fav_url:'../hms/HmsAction.do?cmd=HmsView&menu_id=521'};
								var I_INTRODUCE_2 = F_INTRODUCE.addItem(new Item(treelist, 'F_INTRODUCE_2', '구축배경및필요성 ' ,data));
								data = {pid:'F_INTRODUCE_3', fav_url:'../hms/HmsAction.do?cmd=HmsView&menu_id=531'};
								var I_INTRODUCE_3 = F_INTRODUCE.addItem(new Item(treelist, 'F_INTRODUCE_3', '주요사업안내 ' ,data));		
								// 사업소개 아이템 : E									
								// ------------ 사업소개 : E ------------
								
								
								// ------------ MY 민원창구 : S ------------
								data = {pid:'F_MYSTORE', fav_url:'about:blank'};
								var F_MYSTORE = F_0.addFolder(new Folder(treelist, 'F_MYSTORE', 'MY 민원창구' ,data));

								// MY 민원창구 아이템 : S
								data = {pid:'I_MYSOTRE_1', fav_url:'../mystore/MystoreAction.do?cmd=MyProState'};
								var I_MYSOTRE_1 = F_MYSTORE.addItem(new Item(treelist, 'I_MYSOTRE_1', '나의 민원진행상태', data));
								data = {pid:'I_MYSOTRE_2', fav_url:'../mystore/MystoreAction.do?cmd=MyUseStore'};
								var I_MYSOTRE_2 = F_MYSTORE.addItem(new Item(treelist, 'I_MYSOTRE_2', '내가 자주가는 창구', data));
								data = {pid:'I_MYSOTRE_3', fav_url:'../mem/MemAction.do?cmd=MemInfoV'};
								var I_MYSOTRE_3 = F_MYSTORE.addItem(new Item(treelist, 'I_MYSOTRE_3', '개인정보 수정', data));
								data = {pid:'I_MYSOTRE_4', fav_url:'../mem/MemAction.do?cmd=MemPasswdChange'};
								var I_MYSOTRE_4 = F_MYSTORE.addItem(new Item(treelist, 'I_MYSOTRE_4', '비밀번호 변경', data));
								data = {pid:'I_MYSOTRE_5', fav_url:'../mem/MemAction.do?cmd=MemMobileReg'};
								var I_MYSOTRE_5 = F_MYSTORE.addItem(new Item(treelist, 'I_MYSOTRE_5', '모바일 서비스 사용 신청', data));
								data = {pid:'I_MYSOTRE_6', fav_url:'../mem/MemAction.do?cmd=MemCCReReg'};
								var I_MYSOTRE_6 = F_MYSTORE.addItem(new Item(treelist, 'I_MYSOTRE_6', '공인인증서 재등록', data));
								data = {pid:'I_MYSOTRE_7', fav_url:'../mystore/MystoreAction.do?cmd=MySecede'};
								var I_MYSOTRE_7 = F_MYSTORE.addItem(new Item(treelist, 'I_MYSOTRE_7', '회원탈퇴', data));
								// MY 민원창구 아이템 : E								
								
								// ------------ MY 민원창구 : E ------------
								
								treelist.display();
								</script>									
							</td>
						</tr>
					</table>
					<!-- 자주가는 창고 테이블 : E-->
				
			</div>
		</div>
		<!-- 사이트맵 : S  -->
		
		<!-- 추가삭제버튼 : S  -->
		<div id="favorite_M_btn">
			<a href="javascript:removeMenu()"><img src="../06_mystore/images/img_02_del.gif" alt="삭제 " /></a>
		</div>


		<div id="favorite_M_my">
			<div class="center">
				<img src="../06_mystore/images/img_02_02.gif" alt="자주가는 창구 " />
			</div>
			<div id="favorite_M_myW" >
				<select name="favorite" size="18" class="mystore" onclick="onClickMenu(this);">
					<% if (rEntity.getRowCnt()!= 0) {
						for (int i = 0; i < rEntity.getRowCnt(); i++) { %>	
							<option value="<%=rEntity.getValue(i,"FAV_NAME")%>|<%=rEntity.getValue(i,"FAV_URL")%>"><%=rEntity.getValue(i,"FAV_NAME")%></option>
					<% 
						} 
					}
					%>				
				</select>
				
			</div>
			<div class="center">
					<a href="javascript:upMenu(document.fmParam.favorite.selectedIndex)"><img src="../images/common/icon_up.gif" alt="위로" /></a>
					<a href="javascript:downMenu(document.fmParam.favorite.selectedIndex)"><img src="../images/common/icon_down.gif" alt="아래로 " /></a>
			</div>
		</div>

		
	</div>
	<!-- 전체 : E  -->
	<!-- 버튼 : S -->
	<div class="center">
	  	<a href="javascript:submitMenu()"><img src="../00_member/images/btn_ok.gif" alt="확인"></a>
	  	<a href="../mystore/MystoreAction.do?cmd=MyProState"><img src="../00_member/images/btn_cancel.gif" alt="취소"></a>
	</div>
	<!-- 버튼 : E -->
<!-- 자주가는 창구 관리 : E  -->
</form>