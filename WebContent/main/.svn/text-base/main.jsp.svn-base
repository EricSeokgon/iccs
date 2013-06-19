<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="kjf.cfg.*" %>
<%@ page import="kjf.ops.*" %>
<%@ page import="kjf.util.*" %>
<%@ include file="../inc/user_inc.jsp" %>
<%	
	//상단 플래쉬 링크 정보
	String top_pageNum  = "";
	String top_sub      = "";
	// 좌측 플래쉬 링크 정보
	String left_pageNum = "";
	String left_sub     = "";
	
	ReportEntity NOTICE     = (ReportEntity) request.getAttribute("NOTICE");		// 공지사항
	ReportEntity NEWS     	= (ReportEntity) request.getAttribute("NEWS");			// 새소식
	ReportEntity REG_PUBLIC = (ReportEntity) request.getAttribute("REG_PUBLIC");	// 공사업등록 공고
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="ko">
<head>

<title><%=Config.props.get("HOMEPAGE_TITLE")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="robots" content="noindex,nofollow">
<meta name="author" content="부산광역시">
<meta name="keywords" content="정보통신공사업시스템">
<meta name="description" content="정보통신공사업시스템">
<meta http-equiv="imagetoolbar" content="no">

<!-- 공통 : S -->
<link href="../css/total.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../com/js/quick.js"></script>
<script language="JavaScript" src="../com/js/main.js" type="text/javascript"></script><noscript><p>JavaScript</p></noscript>
<script language="JavaScript" src="../com/js/topMenu.js" type="text/javascript"></script><noscript><p>JavaScript</p></noscript>
<script language="JavaScript" src="../flash/func_url.js" type="text/javascript"></script>

<%	
	if(ruleChk){
			out.print("<script language=\"JavaScript\" src=\"../flash/flash_pub.js\" type=\"text/javascript\"></script><noscript><p>JavaScript</p></noscript>");
	} else {
		out.print("<script language=\"JavaScript\" src=\"../flash/flash.js\" type=\"text/javascript\"></script><noscript><p>JavaScript</p></noscript>");
	}
%>

<!-- 공통 : E -->

<!-- 팝업띄우기 : S -->
<script language="JavaScript" type="text/JavaScript">

// 팝업창 쿠키
function getCookie( name ) {
	var nameOfCookie = name + "=";
	var x = 0;
	
    while ( x <= document.cookie.length ) {
    	var y = (x+nameOfCookie.length);
        
        if ( document.cookie.substring( x, y ) == nameOfCookie ) {
        if ( (endOfCookie=document.cookie.indexOf( ";", y )) == -1 )
            endOfCookie = document.cookie.length;
            return unescape( document.cookie.substring( y, endOfCookie ) );
        }
        
    	x = document.cookie.indexOf( " ", x ) + 1;
        
        if ( x == 0 )
        	break;
    }
    
	return "";
}

if (getCookie("PopId_develop") != "done") {
	<%	
	if(ruleChk){
	//out.print("winOpenAtCenter('../popup/develop/popup_new.jsp', '', 640, 550);");
	//out.print("winOpenAtCenter('../popup/develop/popup.jsp', '', 580, 470);");
	}
	%>
}
</script>
<!-- 팝업띄우기 : E -->

</head>
<body id="main">
<noscript><p>자바스크립트를 지원해야 올바르게 동작하는 페이지입니다.</p></noscript>
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
	<div id="main_header">
  		<div id="main_top_navi"><%@ include file="../inc/main_top.jsp" %></div>	
  		<div id="main_login"><%@ include file="../inc/top_login.jsp" %></div>
  	</div>
  	<!-- header : E -->
   	 
  	<!-- BODY : S -->
	<div id="main_body"><a name="leftmenu"></a>
  	
	  	<!--퀵메뉴 : S -->
		<!--<div id="main_quick"><img src="../images/main/quick.gif"/></div> -->		
		<!--퀵메뉴 : E -->	
		
		<!--자주가는 창구 새창 : S -->
		<div id="quick_mystore_main" style="display:none;">
				<div id="favorite_title"><img src="../images/common/favorite_title.gif" alt="자주가는 창구"></div>
				<div id="favorite_close"><a href="javascript:on_div_play_main();"><img src="../images/common/favorite_btn_close2.gif" alt="닫기"></a></div>
				<div id="favorite_white_scroll"><iframe src="../mystore/MystoreAction.do?cmd=MyUseStoreView" width="100%" height="100%" id="QuickFrame" frameborder="0" scrolling="auto"></iframe></div>
				<div id="favorite_comment"><img src="../images/common/favorite_comment.gif" alt="각 메뉴를 클릭하시면 해당 페이지로 이동합니다.
		변경버튼을 클릭하시면 메뉴를 변경할수 있습니다."></div>
				<div id="favorite_btn"><a href="javascript:on_div_play_main();"><img src="../images/common/favorite_btn_close.gif" alt="닫기"></a>
				<a href="../mystore/MystoreAction.do?cmd=MyUseStore"><img src="../images/common/favorite_btn_modify.gif" alt="수정"></a></div>
		</div>
		<!--자주가는 창구 새창 : E -->
		
		<!-- main flash : S -->
   		<div id="main_flash">
   		<% 
   		if (ruleChk){
   			if (gpkiChk){
   				out.print("<script type=\"text/javascript\">flash('../flash/avs_pub.swf?flash_xml=../flash/navi_url_pub.xml','950','390');</script><noscript><p>JavaScript</p></noscript>");
   			} else {
   				out.print("<script type=\"text/javascript\">flash('../flash/vs_pub.swf?flash_xml=../flash/navi_url_pub.xml','950','390');</script><noscript><p>JavaScript</p></noscript>");
   			}
   		} else {
				out.print("<script type=\"text/javascript\">flash('../flash/vs.swf?flash_xml=../flash/navi_url.xml','950','390');</script><noscript><p>JavaScript</p></noscript>");
   		}
   		%>
		
		</div>
		
		<!-- main falsh : E -->
			
		<!-- main contents : S --><a name="contents"></a>
		<div id="main_contents">
			<!-- left banner : S -->
			<div id="main_banner">
				<span class="banner_01"><a href="../bbs/BbsAction.do?cmd=BbsKList&CT_ID=FORM_MORGUE"><img alt="정보통신민원서식 바로가기" src="../images/main/main_banner_01.gif" /></a></span>
				<span class="banner_02"><a href="../hms/HmsAction.do?cmd=HmsView&menu_id=424"><img src="../images/main/main_banner_02.gif" alt="PDF서비스 바로가기" /></a></span>
				<span class="banner_03"><a href="javascript:open_QuickUseBefore()"><img src="../images/main/main_banner_03.gif" alt="모바일 이용안내 바로가기" /></a></span>
			</div>
			<!-- left banner : E -->
			
			<!-- notice : S -->
			<div id="notice">
				<%@ include file="../main/inc_notice.jsp" %>				
			</div>
			<!-- notice : E -->
			
			<!-- 지역news : S -->
			<div id="news">
				<%@ include file="../main/inc_news.jsp" %>
			</div>
			<!-- 지역news : E -->
			
			<!-- 공사업공고 : S -->
			<div id="public">
				<%@ include file="../main/inc_reg_public.jsp" %>
			</div>
			<!-- 공사업공고 : E -->
			
			<!-- right banner : S http://www.tongsin.re.kr-->
			<div id="right_banner">
			<%
				if (!KJFUtil.isEmpty(user)){
					out.print("<a href=\"../tongsin\" onClick=\"window.open(this.href,'target',''); return false;\">");
				} else {
				  out.print("<a href=\"#alert\" onClick=\"javascript:alert('로그인을 해 주십시오')\">");	
				}%>
			<!-- <img alt="정보통신 혁신연구회 홈페이지 바로가기" title="정보통신 혁신연구회 홈페이지로 이동합니다." src="../images/main/right_banner.jpg" border="0" /> -->
			</a>
			
			<script>
				function FamilyGo(page){ 
					if(page !="") 
						window.open(page); 
				} 
			</script> 
				</script><noscript><p>스크립트</p></noscript> 
						<!-- select name="test1" style="width:180px; height:20px;" onChange="if(this.value != '') window.open(this.value,'','');" -->
				<div id="select"> 
					<form name="fm" method="post" action="#"> 
						<div class="s_r"> 
						<SELECT name="familysite" class="input"> 
						  <option selected="selected">관련기관바로가기</option> 
						  <option value="http://fish.incheon.go.kr/" target="_blank">한국정보통신공사협회</option> 
						  <option value="http://fish.gyeonggi.go.kr/" target="_blank">한국정보통신공사협회</option> 
						  <option value="http://www.jnfri.or.kr" target="_blank">전라남도 해양바이오연구원</option> 
						  <option value="http://www.gbsusan.or.kr/" target="_blank">경상북도 수산자원개발연구소</option> 
						  <option value="http://www.gsndsusan.go.kr/" target="_blank">경상남도 수산자원연구소</option> 
						  <option value="http://www.jejubada.re.kr/" target="_blank">제주특별자치도 해양수산자원연구소</option> 
						</select> 	
						<a href="javascript:FamilyGo(document.fm.familysite.value);"><img src="../images/common/select_go.gif" alt="바로가기" border="0"/></a>	   
						</div> 
					</form> 
				</div>
			<!-- right banner : E -->
			
			</div>
			<!-- right banner : E -->
			
		</div>
		<!-- main contents : E -->
 	</div>
 	<!-- main body : E -->
 	
  	<!-- copyright : S -->
 	<div id="foot"><%@ include file="../inc/copy.jsp" %></div> 
 	<!-- copyright : E -->
 	
</div>
<!-- 전체 : E -->



</body>
</html>
