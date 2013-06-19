function flash(value, width, height){
	document.writeln('<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0" width="'+width+'" height="'+height+'">');
	document.writeln('<param name="movie" value="'+value+'">');
	document.writeln('<param name="quality" value="high">');
	document.writeln('<param name="wmode" value="transparent">');
	document.writeln('<param name="menu" value="false">');
	document.writeln('<embed src="'+value+'" width="'+width+'" height="'+height+'" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" wmode="transparent" menu="false"></embed>');
	document.writeln('</object>');
}

function movie(value, width, height){	//
	document.writeln('<object id="WMP" classid="CLSID:22d6f312-b0f6-11d0-94ab-0080c74c7e95" standby="Loading Microsoft Windows Media Player..." width="'+width+'" height="'+height+'">');
	document.writeln('<param name="FileName" value="'+value+'">');
	document.writeln('<param name="AutoStart" value="true">');
	document.writeln('<param name="AutoSize" value="false">');
	document.writeln('<param name="transparentAtStart" value="True">');
	document.writeln('<param name="transparentAtStop" value="True">');
	document.writeln('<param name="ShowControls" value="false">');
	document.writeln('<param name="ShowStatusBar" value="false">');
	document.writeln('<param name="loop" value="-1">');
	document.writeln('<param name="Volume" value="-600">');
	document.writeln('<param name="PLUGINSPAGE" value="http://www.microsoft.com/korea/windows/windowsmedia/">');
	document.writeln('<embed src="'+value+'" width="'+width+'" height="'+height+'" autostart="False" loop="-1" filename="'+value+'" autosize="false" showcontrols="false" showstatusbar="false" volume="-600" pluginspage="http://www.microsoft.com/korea/windows/windowsmedia/"></embed>');
	document.writeln('</object>');
}

function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}

function MM_showHideLayers() { //v6.0
	var i,p,v,obj,args=MM_showHideLayers.arguments;
	for (i=0; i<(args.length-2); i+=3) if ((obj=MM_findObj(args[i]))!=null) { v=args[i+2];
    if (obj.style) { obj=obj.style; v=(v=='show')?'visible':(v=='hide')?'hidden':v; }
    obj.visibility=v; }
}

function submenu(menuName) {

	if(menuName=="m0") {
		location.href="../member/regist.do";
	} else if(menuName=="m01") { 
		location.href="../member/regist.do";
	} else if(menuName=="m02") { 
		location.href="../member/find.do";

	} else if(menuName=="m9") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKPortal";
		//location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=NEW_CEN_PUB";		
	} else if(menuName=="m91") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKPortal";	
		//location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=NEW_CEN_PUB";	
	} else if(menuName=="m912") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=NOTICE_CEN_PUB";		
	} else if(menuName=="m913") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=FREE_CEN_PUB";
	} else if(menuName=="m914") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=REGIONALISM_PUB";
	} else if(menuName=="m915") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=TONGSIN_ORG_PUB";
	} else if(menuName=="m916") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=USE_TONGSIN_PUB";
	} else if(menuName=="m917") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=DISIGN_TONGSIN_PUB";	
	} else if(menuName=="m918") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=PDA_CEN_PUB";	
	} else if(menuName=="m919") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=IMPROVE_BBS_PUB";		
		
	} else if(menuName=="m92") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=LAW_RECHANGE_PUB";
		// 입법절차안내 http://www.net.go.kr/hms/HmsAction.do?cmd=HmsView&menu_id=812
	} else if(menuName=="m921") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=LAW_RECHANGE_PUB";
		//location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=811";
	} else if(menuName=="m922") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=WORKLOAD_CEN_PUB";		
	} else if(menuName=="m923") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=FAQ_CEN_PUB";
		
	} else if(menuName=="m93") {
		location.href="../cominfo/ComInfoAction.do?cmd=InfoCommTraderPub";
	} else if(menuName=="m931") {
		location.href="../cominfo/ComInfoAction.do?cmd=InfoCommTraderPub";		
	} else if(menuName=="m932") { 
		location.href="../cominfo/ComInfoAction.do?cmd=InfoExecValuationPub";		
	} else if(menuName=="m933") { 
		location.href="../cominfo/ComInfoAction.do?cmd=InfoAdmMeasurePub";	
		
	} else if(menuName=="m94") { 
		location.href="../usebefore/UseBeforeAction.do?cmd=UseBeforeMgr";
	} else if(menuName=="m941") { 
		location.href="../usebefore/UseBeforeAction.do?cmd=UseBeforeMgr";	
	} else if(menuName=="m942") { 
		location.href="../usebefore/UseBeforeAction.do?cmd=UseBeforeMgrRes";	
		/*
	} else if(menuName=="m95") { 
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=812";
	} else if(menuName=="m951") { 
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=812";		
	} else if(menuName=="m952") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=QNA_LEGAL_PUB";		
	} else if(menuName=="m953") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=PDA_LEGAL_PUB";
		
	} else if(menuName=="m96") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=WORKLOAD_CEN_PUB";
	} else if(menuName=="m97") {
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=FAQ_CEN_PUB";
		
	} else if(menuName=="m98") { 
		location.href="../cominfo/ComInfoAction.do?cmd=InfoCommTraderPub";
	} else if(menuName=="m981") { 
		location.href="../cominfo/ComInfoAction.do?cmd=InfoCommTraderPub";
	} else if(menuName=="m982") { 
		location.href="../cominfo/ComInfoAction.do?cmd=InfoExecValuationPub";
	} else if(menuName=="m983") { 
		location.href="../cominfo/ComInfoAction.do?cmd=InfoAdmMeasurePub";		
	} else if(menuName=="m984") { 
		location.href="../usebefore/UseBeforeAction.do?cmd=UseBeforeMgr";
	} else if(menuName=="m985") { 
		location.href="../usebefore/UseBeforeAction.do?cmd=UseBeforeMgrRes";
*/
	} else if(menuName=="m2") { 
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=211";
	} else if(menuName=="m21") { 
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=211";
	} else if(menuName=="m211") { 
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=211";
	} else if(menuName=="m212") { 
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=212";
	} else if(menuName=="m213") { 
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=213";
	} else if(menuName=="m214") { 
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=214";
	} else if(menuName=="m215") { 
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=215";
	} else if(menuName=="m216") { 
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=216";
	} else if(menuName=="m217") { 
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=217";
	} else if(menuName=="m218") { 
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=218";
		
	} else if(menuName=="m22") { 
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=221";
	} else if(menuName=="m221") { 
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=221";
		
	} else if(menuName=="m23") { 
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=231";
	} else if(menuName=="m231") { 
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=231";
		
	} else if(menuName=="m24") { 
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=241";
	} else if(menuName=="m241") { 
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=241";
		
	} else if(menuName=="m25") { 
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=251";
	} else if(menuName=="m251") { 
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=251";
		
	} else if(menuName=="m26") { 
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=261";
	} else if(menuName=="m261") { 
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=261";
		
	} else if(menuName=="m27") { 
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=271";
	} else if(menuName=="m271") { 
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=271";
	} else if(menuName=="m272") { 
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=272";
	} else if(menuName=="m273") { 
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=273";
	} else if(menuName=="m274") { 
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=274";
	} else if(menuName=="m275") { 
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=275";
		
	} else if(menuName=="m28") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=FORM_MORGUE";

/*		
	} else if(menuName=="m3") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=NOTICE";
	} else if(menuName=="m31") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=NOTICE";
	} else if(menuName=="m32") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=NEWS";
	} else if(menuName=="m33") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=REG_PUBLIC";
	} else if(menuName=="m331") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=REG_PUBLIC";
	} else if(menuName=="m332") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=CON_PUBLIC";
	} else if(menuName=="m333") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=DIS_PUBLIC";
	} else if(menuName=="m34") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=TEC_MORGUE";
	} else if(menuName=="m341") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=TEC_MORGUE";
	} else if(menuName=="m35") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=FAQ";
	} else if(menuName=="m36") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=FREE";
	} else if(menuName=="m37") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=PARTNER";
*/
	} else if(menuName=="m5") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=NOTICE";
	} else if(menuName=="m51") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=NOTICE";
	} else if(menuName=="m52") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=NEWS";
	} else if(menuName=="m53") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=REG_PUBLIC";
	} else if(menuName=="m531") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=REG_PUBLIC";
	} else if(menuName=="m532") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=CON_PUBLIC";
	} else if(menuName=="m533") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=DIS_PUBLIC";
	} else if(menuName=="m54") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=TEC_MORGUE";
	} else if(menuName=="m541") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=TEC_MORGUE";
	} else if(menuName=="m55") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=FAQ_PUB";
	} else if(menuName=="m56") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=FREE";
	} else if(menuName=="m57") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=PARTNER";


	} else if(menuName=="m4") { 
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=411";
	} else if(menuName=="m41") { 
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=411";
	} else if(menuName=="m411") { 
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=411";
	} else if(menuName=="m412") { 
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=412";
	} else if(menuName=="m413") {
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=POLICY";
	} else if(menuName=="m414") { 
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=414";

	} else if(menuName=="m42") { 
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=421";
	} else if(menuName=="m421"){ 
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=421";
	} else if(menuName=="m422"){ 
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=422";
	} else if(menuName=="m423"){ 
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=423";
	} else if(menuName=="m424"){ 
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=424";
	} else if(menuName=="m425"){ 
		location.href="../statistics/StatisticsAction.do?cmd=StatisticalData";
	
	} else if(menuName=="m43") { 
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=431";
	} else if(menuName=="m431") { 
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=431";
	} else if(menuName=="m431") { 
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=431";
	} else if(menuName=="m432"){ 
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=432";
	} else if(menuName=="m433") { 
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=433";
		
		
	} else if(menuName=="m7") { 
		location.href="../07_service/service_01_1.jsp";
	} else if(menuName=="m71") { 
		location.href="../07_service/service_01_1.jsp";
	} else if(menuName=="m72"){ 
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=721";
	} else if(menuName=="m73") { 
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=731";
	} else if(menuName=="m74") { 
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=741";
	} else if(menuName=="m75") { 
		location.href="../service/ServiceAction.do?cmd=CivilCenterGuide";
	} else if(menuName=="m76") { 
		location.href="../hms/HmsAction.do?cmd=HmsView&menu_id=771";
		
	} else if(menuName=="m101") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=BOD_DATA_1";
	} else if(menuName=="m102") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=BOD_DATA_2";
	} else if(menuName=="m103") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=BOD_DATA_3";
	} else if(menuName=="m104") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=BOD_DATA_4";
	} else if(menuName=="m105") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=BOD_DATA_5";
	} else if(menuName=="m106") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=BOD_DATA_6";
	} else if(menuName=="m107") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=BOD_DATA_7";
	} else if(menuName=="m108") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=BOD_RNDS_DATA_1";
	} else if(menuName=="m109") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=BOD_RNDS_DATA_2";
	} else if(menuName=="m110") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=BOD_RNDS_DATA_3";
	} else if(menuName=="m111") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=BOD_RNDS_DATA_4";
	} else if(menuName=="m112") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=BOD_RNDS_DATA_5";		
		
		
	} else if(menuName=="quick1") { 
		location.href="javascript:on_div_play_main();";
	} else if(menuName=="quick2") { 
		location.href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=FAQ_PUB";
	} else if(menuName=="quick3") { 
		location.href="javascript:open_Mobile()";
	} else if(menuName=="quick4") { 
		location.href="javascript:open_Viewer()";
	} else if(menuName=="quick5") { 
		location.href="http://99.1.5.83";		
	} 
}