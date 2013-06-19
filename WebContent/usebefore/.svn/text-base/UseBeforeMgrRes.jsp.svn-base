<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.Vector" %>
<%@ page import="java.io.*" %>
<%@ page import="kjf.ops.*" %>
<%@ page import="kjf.util.*" %>
<%@ page import="sp.uent.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ taglib uri="/KJFTags" prefix="KTags" %>
<%@ page import="sp.usebefore.UseBeforeParam" %>

<%@ include file="../inc/user_inc.jsp" %>
<% 
	// 상단 플래쉬 링크 정보
	String top_pageNum  = "1";
	String top_sub      = "4";
	
	// 좌측 플래쉬 링크 정보
	String left_pageNum = "4";
	String left_sub     = "2";
	
%>

<%
	// 초기 변수 들 선언
	UseBeforeParam pm = (UseBeforeParam)request.getAttribute("pm");
	//======================================================================
	// 신규인경우에는 초기화가 되지 않으면 error가  나기 때문에 초기화가 필요하다.
	//======================================================================
	
	// 사용전검사관리 조회
	ReportEntity rListEnt  = KJFUtil.REntPrint((ReportEntity)request.getAttribute("ListEntity"));
	// 시도명 가져오기
	ReportEntity sEntity  = KJFUtil.REntPrint((ReportEntity)request.getAttribute("sEntity"));	

	//======================================================================
		
  String mode 			= KJFUtil.print(request.getParameter("mode"));
	

	// 페이징 관련(필수)
	String nowPage          = KJFUtil.print(pm.getNowPage());
	String rowPerPage       = KJFUtil.print(pm.getRowPerPage());
	String totalCount      	= KJFUtil.print(pm.getTotalCount());
	// 사용전검사 관리
	
	String scOK							= KJFUtil.print(pm.getScOK(),"");
	String scNO							= KJFUtil.print(pm.getScNO(),"");
	String scNO_2							= KJFUtil.print(pm.getScNO_2(),"");
	String scNO_3							= KJFUtil.print(pm.getScNO_3(),"");
	
	String scCIV_RECV_NUM		= KJFUtil.print(pm.getScCIV_RECV_NUM(),"");
	String scOPE_NAME				= KJFUtil.print(pm.getScOPE_NAME(),"");

	SimpleDateFormat sdf = new SimpleDateFormat ( "yyyy-MM-dd" );
	Date toDay = new Date ();
	Date beforeDay = new Date ();
	beforeDay.setTime (toDay.getTime() - ((long) 1000 * 60 * 60 * (24 * 14) ));
	
	String RECV_ST						= KJFUtil.print(pm.getScRECV_ST(), sdf.format(beforeDay.getTime()));
	String RECV_ET						= KJFUtil.print(pm.getScRECV_ET(), sdf.format(toDay));
	
	System.out.println(user.getSIGUNGU_CODE());
	
	//gpki 로그인한 계정
	if (gpkiChk){
%>

<html>
<head>
<title>사용전검사합격관리 검색 페이지 입니다.</title>

<!-- 공통 : S -->
<link href="../css/total.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="../com/js/com.js" type="text/javascript"></script>
<script language="JavaScript" src="../com/js/topMenu.js" type="text/javascript"></script>
<script language="javascript" src="../com/js/httpRequest.js" type="text/javascript"></script>

<SCRIPT LANGUAGE="JavaScript">
<!--
/***************************************************************************
* Title   : 사용전검사  
* Content : 사용전검사 내용을 저장    
****************************************************************************/
function fnView(str){
	var fm = document.fmParam;
	fm.cmd.value = "UseBeforeMgrResC";
	fm.scRECV_NUM.value = str; 	
	fm.submit();
}

// 달력
function click_where() {
        MM_showHideLayers('div_start','','hide');
        MM_showHideLayers('div_end','','hide');
}

function fn_search(){
	var fm = document.fmParam;
	fm.cmd.value = "UseBeforeMgrRes";
	fm.submit();	
}


// 츨력 처리
function fn_print(){
	var fm = document.fmParam;
	var chkStr = "";
	var chkBoxNums = 0;
	var chkCnt = 0;
	var recv_num = "";
	for(i = 0;i < fm.elements.length;i++){
		if(fm.elements[i].type == "checkbox" && fm.elements[i].name == "RPTCHK"  &&  fm.elements[i].checked == true ){
        	chkBoxNums ++;
        	chkCnt = i;
    }
	  if(fm.elements[i].type == "checkbox" && fm.elements[i].name == "recv_num"  &&  fm.elements[i].checked == true){
					recv_num = fm.elements[i].value;
		  }
	}	

	if(chkBoxNums == 0){
		alert("출력 할 항목이 없습니다.");
		return false;
	} else if (chkBoxNums == 1){
			if(fm.elements[chkCnt].type == "checkbox" && fm.elements[chkCnt].name == "RPTCHK" &&  fm.elements[chkCnt].checked == true && fm.elements[chkCnt].value == "su"){				
	        	chkStr = "su";
	    }
			if(fm.elements[chkCnt].type == "checkbox" && fm.elements[chkCnt].name == "RPTCHK" &&  fm.elements[chkCnt].checked == true  && fm.elements[chkCnt].value == "dsu"){				
	        	chkStr = "dsu";
	    } 
	} else if (chkBoxNums >= 2){
		chkStr = "all";
	}

	centerwin("UseBeforePop.jsp?chkStr="+chkStr+"&recv_num="+recv_num,"", "300", "200", "1");
}

function fn_print_check(){
	var fm = document.fmParam;
	var chkrnum = "";
	var chkRecvNums = 0;
	
	for(i = 0;i < fm.elements.length;i++){
		if(fm.elements[i].type == "checkbox" && fm.elements[i].name == "recv_num"  &&  fm.elements[i].checked == true ){
					chkRecvNums ++;
        	chkCnt = i;
    }
	}	
	
	if(chkRecvNums == 0){
		alert("출력할 항목을 선택해 주십시오");
	} else if(chkRecvNums >= 2){
		alert("출력할 항목을 한개만 선택해 주십시오");
		for(i = 0;i < fm.elements.length;i++){
			if(fm.elements[i].type == "checkbox" && fm.elements[i].name == "recv_num"  &&  fm.elements[i].checked == true ){
					fm.elements[i].checked = false;
	    }
		}
	}
}

function fn_chkOK(){
	var fm = document.fmParam;

	if (fm.chkOK.checked){
		fm.scOK.value = "1"
	} else {
		fm.scOK.value = "0"
	}
}
function fn_chkNO(str){
	var fm = document.fmParam;
	if (str == "1"){
		if (fm.chkNO.checked){
			fm.scNO.value = "1"
		} else {
			fm.scNO.value = "0"
		}
	} else {
		var chkNo = eval("fm.chkNO_"+str);
		var scNo = eval("fm.scNO_"+str);
		if (chkNo.checked){
			scNo.value = "1"
		} else {
			scNo.value = "0"
		}
	}
	
}

/-->
</SCRIPT>
<%
		out.print("<script language=\"JavaScript\" src=\"../flash/flash_pub.js\" type=\"text/javascript\"></script><noscript><p>JavaScript</p></noscript>");
%>
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
  		<div id="top_navi"><%@ include file="../inc/main_top.jsp" %></div>	
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
					<a href="../"><img src="../images/common/icon_home.gif" alt="HOME"></a> 공무원센터    |  공사업등록업체조회     |  사용전검사완료목록
				</p>
			</div>
			<!-- 현재위치 : E -->
	
			<!-- 서브타이틀 : S -->
			<div id="sub_title">
				<h3 class="cont01"><img src="../images/box/titile_09_2.gif" alt="사용전검사완료목록"></h3>
			</div>
			<!-- 서브타이틀 : E -->
   
		  	<!-- 컨텐츠 : S -->
			<div id="contents" >
				<form name=fmParam method="post" action="../usebefore/UseBeforeAction.do" >
					<input type="hidden" name="cmd" value="UseBeforeMgrRes" />
					<input type="hidden" name="scRECV_NUM" value="<%=pm.getScRecv_num() %>" />
					<input type="hidden" name="scOK" value="<%=pm.getScOK() %>" />
					<input type="hidden" name="scNO" value="<%=pm.getScNO() %>" />
					<input type="hidden" name="scNO_2" value="<%=pm.getScNO_2() %>" />
					<input type="hidden" name="scNO_3" value="<%=pm.getScNO_3() %>" />
					<!-- 페이징 관련(필수)-->
					<input type=hidden name="nowPage" value="<%=nowPage%>">

					<!-- ======================== =================  ========================== -->
					<!-- ======================== 본문 디자인 부분 START ========================== -->
					<!-- ======================== ================= =========================== -->			

					<table width="100%" border="0"  cellspacing="0" cellpadding="0">
						<tr>
							<td align="right">
							
							</td>
						</tr>
					</table>
						<%@ include file="../usebefore/UseBeforeMgrRes_list.jsp" %>
					<!-- ========================  =================  ========================== -->
					<!-- ========================  본문 디자인 부분 END   ========================== -->
					<!-- ========================  =================  ========================== -->				
				</form>	
			</div>
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
<%} %>
