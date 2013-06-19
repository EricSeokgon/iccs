<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="kjf.ops.*" %>
<%@ page import="kjf.util.*" %>
<%@ page import="sp.statistics.*" %>
<%@ include file="../inc/user_inc.jsp" %>
<%
	//상단 플래쉬 링크 정보
	String top_pageNum  = "";
	String top_sub      = "";
	// 좌측 플래쉬 링크 정보
	String left_pageNum = "";
	String left_sub     = "";
	
	if (ruleChk){
		top_pageNum  = "4";
		top_sub      = "2";
		left_pageNum = "2";
		left_sub     = "5";
	} else {
		top_pageNum  = "4";
		top_sub      = "1";
		left_pageNum = "2";
		left_sub     = "5";
	}
%>
<%
	ReportEntity rEntity = KJFUtil.REntPrint( (ReportEntity)request.getAttribute("rEntity") );	// 공사업 등록현황 지역
	ReportEntity yEntity = KJFUtil.REntPrint( (ReportEntity)request.getAttribute("yEntity") );	// 공사업 등록현황 년도
	
	StatisticsParam pm = (StatisticsParam)request.getAttribute("pm");
%>
<script language="JavaScript" type="text/javascript">

// 통계보기
function fn_st_view() {
    var fm = document.fmParam;
    fm.submit();
}

//통계보기
function fn_st_view_year(sido_code) {
    var fm = document.fmParam;

    fm.st_sido_code.value = sido_code;
    fm.submit();
}
</script>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>통계자료 페이지입니다.</title>

<!-- 공통 : S -->
<link href="../css/total.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="../com/js/topMenu.js" type="text/javascript"></script>
<%	
	if(ruleChk){
		out.print("<script language=\"JavaScript\" src=\"../flash/flash_pub.js\" type=\"text/javascript\"></script><noscript><p>JavaScript</p></noscript>");
	} else {
		out.print("<script language=\"JavaScript\" src=\"../flash/flash.js\" type=\"text/javascript\"></script><noscript><p>JavaScript</p></noscript>");
	}
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
              <p class="position"> <a href="../"><img src="../images/common/icon_home.gif" alt="HOME"></a> 민원시스템안내 | 사이버민원센터 운영 | 통계자료 </p>
            </div>
		  <!-- 현재위치 : E -->
            <!-- 서브타이틀 : S -->
            <div id="sub_title">
              <h3 class="cont01"><img src="../04_guidance/images/titile_02_5.gif" alt="통계자료"></h3>
            </div>
		  	<!-- 서브타이틀 : E -->
            
            <!-- 컨텐츠 : S -->
            <div id="contents">

			  <form name=fmParam method="post" action="../statistics/StatisticsAction.do?cmd=StatisticalData" >
			  <input type="hidden" name="st_sido_code" value="<%=KJFUtil.print(pm.getSt_sido_code())%>">

              <!--통계자료 : S -->
              <!--1 정보통신공사업 등록현황 : S -->
              <h4><img alt="정보통신공사업 등록현황" src="../04_guidance/images/sub02_05_01.gif" /></h4>
              <div class="right">
                <select name="st_view_area">
                  <option value='' <%=KJFUtil.print(pm.getSt_view_area()).equals("")?"selected":""%>>전체보기</option>
                  <option value='GRAPH' <%=KJFUtil.print(pm.getSt_view_area()).equals("GRAPH")?"selected":""%>>그래프 보기</option>
                  <option value='TABLE' <%=KJFUtil.print(pm.getSt_view_area()).equals("TABLE")?"selected":""%>>표 보기</option>
                </select>
				<a href="javascript:fn_st_view()"><img src="../images/bbs/btn_go.gif" alt="go" align="absmiddle" style="cursor:pointer"></a>
              </div>

			  <div class="padd10">
				  
				  <% if ( KJFUtil.print(pm.getSt_view_area()).equals("") || KJFUtil.print(pm.getSt_view_area()).equals("GRAPH") ) { %>
				  <p class="dot01">그래프 보기</p>
				  <div class="center"><img src="../chart/ChartAction.do?cmd=ChartArea"/></div>
				  <% } %>
				  
				  <% if ( KJFUtil.print(pm.getSt_view_area()).equals("") || KJFUtil.print(pm.getSt_view_area()).equals("TABLE") ) { %>
				  <p class="dot01">표 보기</p>
				  <table class="t3" summary="표 보기">
					<caption>
					  표 보기
					</caption>
					<colgroup>
					<col style="text-align: center" />
					<col style="text-align: center" />
					<col style="text-align: center" />
					<col style="text-align: center" />
					<col style="text-align: center" />
					<col style="text-align: center" />
					<col style="text-align: center" />
					<col style="text-align: center" />
					</colgroup>
					<thead>
					  <tr>						
						<% if (rEntity.getRowCnt() >= 16 ) {%>
							<% for (int i = 0; i < 8; i++) { %>
							<th scope="col"><%=KJFArea.getSido_Name(KJFUtil.print(rEntity.getValue(i, "SIDO_CODE"))) %></th>
							<% } %>

						<% } else { %>
							<th scope="col">강원</th>
							<th scope="col">경기</th>
							<th scope="col">경남</th>
							<th scope="col">경북</th>
							<th scope="col">광주</th>
							<th scope="col">대구</th>
							<th scope="col">대전</th>
							<th scope="col">부산</th>
						<% } %>
					  </tr>
					</thead>
					<tbody>
					  <tr>
						<% if (rEntity.getRowCnt() >= 16 ) {%>
							<% for (int i = 0; i < 8; i++) { %>
							<td class="letter0"><%=KJFUtil.print(rEntity.getValue(i, "CNT")) %></th>
							<% } %>

						<% } else { %>
							<td class="letter0">&nbsp;</td>
							<td class="letter0">&nbsp;</td>
							<td class="letter0">&nbsp;</td>
							<td class="letter0">&nbsp;</td>
							<td class="letter0">&nbsp;</td>
							<td class="letter0">&nbsp;</td>
							<td class="letter0">&nbsp;</td>
							<td class="letter0">&nbsp;</td>
						<% } %>
					  </tr>
					</tbody>
					<thead>
					  <tr>
						<% if (rEntity.getRowCnt() >= 16 ) {%>
							<% for (int i = 8; i < 16; i++) { %>
							<th scope="col"><%=KJFArea.getSido_Name(KJFUtil.print(rEntity.getValue(i, "SIDO_CODE"))) %></th>
							<% } %>

						<% } else { %>
							<th scope="col">서울</th>
							<th scope="col">울산</th>
							<th scope="col">인천</th>
							<th scope="col">전남</th>
							<th scope="col">전북</th>
							<th scope="col">제주</th>
							<th scope="col">충남</th>
							<th scope="col">충북</th>
						<% } %>
					  </tr>
					</thead>
					<tbody>
					  <tr>
						<% if (rEntity.getRowCnt() >= 16 ) {%>
							<% for (int j = 8; j < 16; j++) { %>
							<td class="letter0"><%=KJFUtil.print(rEntity.getValue(j, "CNT")) %></th>
							<% } %>

						<% } else { %>
							<td class="letter0">&nbsp;</td>
							<td class="letter0">&nbsp;</td>
							<td class="letter0">&nbsp;</td>
							<td class="letter0">&nbsp;</td>
							<td class="letter0">&nbsp;</td>
							<td class="letter0">&nbsp;</td>
							<td class="letter0">&nbsp;</td>
							<td class="letter0">&nbsp;</td>
						<% } %>
					  </tr>
					</tbody>
				  </table>
				  <% } %>
			  </div>
              <!--1 정보통신공사업 등록현황 : E -->

              <!--2 연간누적현황 : S -->
              <h4><img alt="연간누적현황" src="../04_guidance/images/sub02_05_02.gif" /></h4>
              <div class="right">
                <select name="st_view_year">
                  <option value='' <%=KJFUtil.print(pm.getSt_view_year()).equals("")?"selected":""%>>전체보기</option>
                  <option value='GRAPH' <%=KJFUtil.print(pm.getSt_view_year()).equals("GRAPH")?"selected":""%>>그래프 보기</option>
                  <option value='TABLE' <%=KJFUtil.print(pm.getSt_view_year()).equals("TABLE")?"selected":""%>>표 보기</option>
                </select>
				<a href="javascript:fn_st_view()"><img src="../images/bbs/btn_go.gif" alt="go" align="absmiddle" style="cursor:pointer"></a>
              </div>
              <div class="padd10">
					
				  <% if ( KJFUtil.print(pm.getSt_view_year()).equals("") || KJFUtil.print(pm.getSt_view_year()).equals("GRAPH") ) { %>
				  <p class="dot01">그래프 보기</p>
				  <div class="center"><img src="../chart/ChartAction.do?cmd=ChartYear&st_sido_code=<%=KJFUtil.print(pm.getSt_sido_code())%>"/></div>
			      <% } %>
				  
				  <% if ( KJFUtil.print(pm.getSt_view_year()).equals("") || KJFUtil.print(pm.getSt_view_year()).equals("TABLE") ) { %>
				  <p class="dot01">표 보기</p>
				  <table class="t3" summary="표 보기">
					<caption>
					  표 보기
					</caption>
					<colgroup>
					<col style="text-align: center; letter-spacing:0em;" />
					<col style="text-align: center; letter-spacing:0em;" />
					<col style="text-align: center; letter-spacing:0em;" />
					<col style="text-align: center; letter-spacing:0em;" />
					<col style="text-align: center; letter-spacing:0em;" />
					<col style="text-align: center; letter-spacing:0em;" />
					<col style="text-align: center; letter-spacing:0em;" />
					<col style="text-align: center; letter-spacing:0em;" />
					</colgroup>
					<thead>
					  <tr>
						<% if (yEntity.getRowCnt() > 0 ) {%>
							<% for (int i = 0; i < yEntity.getRowCnt(); i++) { %>
							<th scope="col"><%=KJFUtil.print(yEntity.getValue(i, "YEAR")) %></th>
							<% } %>

						<% } else { %>
							<th scope="col">년도</th>
							<th scope="col">년도</th>
							<th scope="col">년도</th>
							<th scope="col">년도</th>
							<th scope="col">년도</th>
							<th scope="col">년도</th>
							<th scope="col">년도</th>
							<th scope="col">년도</th>
						<% } %>
					  </tr>
					</thead>
					<tbody>
					  <tr>
						<% if (yEntity.getRowCnt() > 0 ) {%>
							<% for (int i = 0; i < yEntity.getRowCnt(); i++) { %>
							<td><%=KJFUtil.print(yEntity.getValue(i, "CNT")) %></td>
							<% } %>

						<% } else { %>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						<% } %>
					  </tr>
					
					</tbody>
				  </table>
				  <% } %>
				  
				  <div id="statistics_btn">
				  <p class="dot02">아래 버튼을 클릭하시면 <strong>해당 지역</strong>의 정보를 볼 수 있습니다.</p>
					<ul>
					  <li><a href="javascript:fn_st_view_year('gwgw')" <%=KJFUtil.print(pm.getSt_sido_code()).equals("gwgw")?"class='open'":""%>>강원</a></li>
					  <li><a href="javascript:fn_st_view_year('gggg')" <%=KJFUtil.print(pm.getSt_sido_code()).equals("gggg")?"class='open'":""%>>경기</a></li>
					  <li><a href="javascript:fn_st_view_year('gngn')" <%=KJFUtil.print(pm.getSt_sido_code()).equals("gngn")?"class='open'":""%>>경남</a></li>
					  <li><a href="javascript:fn_st_view_year('gbgb')" <%=KJFUtil.print(pm.getSt_sido_code()).equals("gbgb")?"class='open'":""%>>경북</a></li>
					  <li><a href="javascript:fn_st_view_year('gjgj')" <%=KJFUtil.print(pm.getSt_sido_code()).equals("gjgj")?"class='open'":""%>>광주</a></li>
					  <li><a href="javascript:fn_st_view_year('dgdg')" <%=KJFUtil.print(pm.getSt_sido_code()).equals("dgdg")?"class='open'":""%>>대구</a></li>
					  <li><a href="javascript:fn_st_view_year('djdj')" <%=KJFUtil.print(pm.getSt_sido_code()).equals("djdj")?"class='open'":""%>>대전</a></li>
					  <li><a href="javascript:fn_st_view_year('bsbs')" <%=KJFUtil.print(pm.getSt_sido_code()).equals("bsbs")?"class='open'":""%>>부산</a></li>

					  <li><a href="javascript:fn_st_view_year('susu')" <%=KJFUtil.print(pm.getSt_sido_code()).equals("susu")?"class='open'":""%>>서울</a></li>
					  <li><a href="javascript:fn_st_view_year('usus')" <%=KJFUtil.print(pm.getSt_sido_code()).equals("usus")?"class='open'":""%>>울산</a></li>
					  <li><a href="javascript:fn_st_view_year('icic')" <%=KJFUtil.print(pm.getSt_sido_code()).equals("icic")?"class='open'":""%>>인천</a></li>
					  <li><a href="javascript:fn_st_view_year('jnjn')" <%=KJFUtil.print(pm.getSt_sido_code()).equals("jnjn")?"class='open'":""%>>전남</a></li>
					  <li><a href="javascript:fn_st_view_year('jbjb')" <%=KJFUtil.print(pm.getSt_sido_code()).equals("jbjb")?"class='open'":""%>>전북</a></li>
					  <li><a href="javascript:fn_st_view_year('jjjj')" <%=KJFUtil.print(pm.getSt_sido_code()).equals("jjjj")?"class='open'":""%>>제주</a></li>
					  <li><a href="javascript:fn_st_view_year('cncn')" <%=KJFUtil.print(pm.getSt_sido_code()).equals("cncn")?"class='open'":""%>>충남</a></li>
					  <li><a href="javascript:fn_st_view_year('cbcb')" <%=KJFUtil.print(pm.getSt_sido_code()).equals("cbcb")?"class='open'":""%>>충북</a></li>

					</ul>
				  </div>
			  </div>
              <!--2 연간누적현황 : E -->
              <!--통계자료 : E -->

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
