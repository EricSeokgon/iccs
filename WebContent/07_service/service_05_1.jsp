<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="kjf.util.*" %>
<%@ page import="sp.service.*" %>
<%@ taglib uri="/KJFTags" prefix="KTags" %>
<%@ include file="../inc/user_inc.jsp" %>
<%
	ServiceParam pm = (ServiceParam)request.getAttribute("pm");
	
	ArrayList sidoList    = (ArrayList)request.getAttribute("sidoList");
	ArrayList sigunguList = (ArrayList)request.getAttribute("sigunguList");

	// 상단 플래쉬 링크 정보
	String top_pageNum  = "";
	String top_sub      = "";
	
	// 좌측 플래쉬 링크 정보
	String left_pageNum = "5";
	String left_sub     = "1";
%>

<SCRIPT language="JavaScript" type="text/javascript">
<!--

// 지역정보 검색처리
function fn_areaSearch() {
	var fm = document.fmParam;

	fm.sido_code.value = fm.area_code.value;
	
	fm.submit();
}

// 시군구 지역정보 검색
function fn_sigunguSearch() {
	var fm = document.fmParam;

	fm.area_code.value = fm.sido_code.value;
	
	fm.submit();
}

<%
	if (ruleChk){ 
		out.print("function fn_DetailView(str) {	");
		out.print(" winOpenAtCenter('../service/ServiceAction.do?cmd=CivilCenterInfo&str='+str, '', 600, 250);");
		out.print("}");
	}
%>

//-->
</SCRIPT>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="ko">
<head>
<title>지역별 민원센터 안내 페이지입니다.</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="robots" content="noindex,nofollow">
<meta name="author" content="부산광역시">
<meta name="keywords" content="정보통신공사업시스템">
<meta name="description" content="정보통신공사업시스템">
<meta http-equiv="imagetoolbar" content="no">

<!-- 공통 : S -->
<link href="../css/total.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="../com/js/comm.js" type="text/javascript"></script>
<script language="JavaScript" src="../com/js/topMenu.js" type="text/javascript"></script>
<script language="javascript" src="../com/js/httpRequest.js" type="text/javascript"></script>
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
				<%=ruleChk %>
				<%		if(ruleChk){	%>
					<a href="../"><img src="../images/common/icon_home.gif" alt="HOME"></a> 이용안내 | 지방공무원소개
				<%		} else {	%>
					<a href="../"><img src="../images/common/icon_home.gif" alt="HOME"></a> 이용안내 | 지역별 민원센터안내
				<%	} %>	
				</p>
			</div>
			<!-- 현재위치 : E -->
	
			<!-- 서브타이틀 : S -->
			<div id="sub_title">
			
			<%		if(ruleChk){	%>
				<h3 class="cont01"><img src="../07_service/images/titile_07_1.gif" alt="지방공무원소개"></h3>		
			<%	} else {%>
				<h3 class="cont01"><img src="../07_service/images/titile_05_1.gif" alt="지역별 민원센터안내 "></h3>
			<%	} %>				
			</div>
			<!-- 서브타이틀 : E -->
   
		  	<!-- 컨텐츠 : S -->

			<form name="fmParam" action="../service/ServiceAction.do" method="post">
			<input type="hidden" name="cmd" value="CivilCenterGuide">

			<div id="contents">
				<!-- 시,도 : S -->
				<h4><img alt="" src="../07_service/images/sub05_01_01.gif" /><img alt="" src="../07_service/images/sub05_01_02.gif" /></h4>
				
				<div class="select">
					<KTags:KJFSelect item='<%=(Vector)request.getAttribute("v_scSD_CD")%>'
											 name='area_code'
											 value='<%=KJFUtil.print(pm.getArea_code())%>'
											 script=""/>
					<span><a href="javascript:fn_areaSearch()"><img src="../images/bbs/btn_go.gif" alt="go" align="absmiddle"></a></span>
				</div>

				<table class="t4" summary="">
				    <caption></caption>
					<colgroup>
						<col style="width: 40px; text-align: center" />
					</colgroup>
				    <thead>
				        <tr>
				            <th scope="col">시·도</th>
				            <th scope="col">주소/홈페이지</th>
				            <th scope="col">소속</th>
				            <th scope="col">이름</th>
				            <th scope="col">전화</th>
				            <th scope="col">E-mail</th>
				        </tr>
				    </thead>
				    <tbody>
					<% if(sidoList.size() > 0) { %>
					
						<% for (int i = 0; i < sidoList.size(); i++) {  
						
						    AreaBaseBean baseBean = (AreaBaseBean)sidoList.get(i);
						    AreaInfoBean areaBean = new AreaInfoBean();
						    
						    ArrayList areaInfoList = baseBean.getAreaInfoArray();
						    
						    for (int j = 0; j < areaInfoList.size(); j++) {
						        areaBean = (AreaInfoBean)areaInfoList.get(j);
						    }
						    
						    ArrayList chargeList = areaBean.getChargeArray();
						%>
							<tr>
					            <th scope="row" rowspan="<%=chargeList.size()%>"><font class="font_orangeB"><%=KJFArea.getSido_Name(baseBean.getSido_code())%></font></th>
					            <td scope="col" rowspan="<%=chargeList.size()%>">
					            	<!--주소자리 --><%=KJFUtil.print(areaBean.getAddress(), "&nbsp")%><br>
									<!--홈페이지URL --><a href="<%=KJFUtil.print(areaBean.getHompage(), "..")%>" target="_blank"><%=KJFUtil.print(areaBean.getHompage(), "&nbsp")%></a>
								</td>
					         
							
							<% if (chargeList.size() > 0) { %>
								<% for (int k = 0; k < chargeList.size(); k++) {
						        		AreaChargeBean chargeBean = (AreaChargeBean)chargeList.get(k);
								%>								
									<%if (k > 0){ %>
									<% if(ruleChk){%>
									<tr style="cursor:pointer" onClick="javascript:fn_DetailView('<%=chargeBean.getoffiId()%>')">
									<% } else { %>
									<tr>
									<% } %>
									<% } %>
	
							            <td align="center"><%=KJFUtil.print(chargeBean.getPart(), "&nbsp")%></td>
							            <td align="center"><%=KJFUtil.print(chargeBean.getName(), "&nbsp")%></td>
							            <td align="center" class="letter0"><%=KJFUtil.print(chargeBean.getTel(), "&nbsp") %></td>
							            <td align="center"><%=KJFUtil.print(chargeBean.getEmail(), "&nbsp")%></td>
	
									<% if(k == chargeList.size() -1) { %>
							        </tr>
									<%} %>
	
								<% } %>

							<% } else { %>
									<td>&nbsp;</td>
						            <td>&nbsp;</td>
						            <td>&nbsp;</td>
						            <td>&nbsp;</td>
								</tr>
							<% } %>

						<% } %>
						
					<% } else { %>
				    	<tr>
				    		<td colspan="6">등록된 정보가 없습니다.</td>
				    	</tr>
				    <% } %>
				    </tbody>
				</table>
				<!-- 시,도 : E -->
				
				<!--시군구 : S -->
				<h4 class="continue"><img alt="" src="../07_service/images/sub05_02_01.gif" /><img alt="" src="../07_service/images/sub05_02_02.gif" /></h4>
				
				<div class="select">
					<KTags:KJFSelect item='<%=(Vector)request.getAttribute("v_scSD_CD")%>'
											 name='sido_code'
											 value='<%=KJFUtil.print(pm.getSido_code())%>'
											 script="onchange='fn_changeSGG_CD()' "/>

					<span id="layer_sggcd" name="layer_sggcd">
					  	<KTags:KJFSelect item='<%=(Vector)request.getAttribute("v_scSGG_CD")%>'
											 name='sigungu_code'
											 value='<%=KJFUtil.print(pm.getSigungu_code()) %>'
											 script=""/>
					</span>
					<span><a href="javascript:fn_sigunguSearch()"><img src="../images/bbs/btn_go.gif" alt="go" align="absmiddle"></a></span>
				</div>

		    	<table class="t4" summary="">
				    <caption></caption>
					<colgroup>
						<col style="width: 40px; text-align: center" />
					</colgroup>
				    <thead>
				        <tr>
				            <th scope="col">시·도</th>
				            <th scope="col">시군구</th>
				            <th scope="col">주소/홈페이지</th>
				            <th scope="col">소속</th>
				            <th scope="col">이름</th>
				            <th scope="col">전화</th>
				            <th scope="col">E-mail</th>
				        </tr>
				    </thead>
				    <tbody>
					<% if(sigunguList.size() > 0) { %>
					
						<% for (int i = 0; i < sigunguList.size(); i++) { 
						    AreaBaseBean baseBean = (AreaBaseBean)sigunguList.get(i);
						    AreaInfoBean areaBean = new AreaInfoBean();				
						    
						    ArrayList areaInfoList = baseBean.getAreaInfoArray();
						    
						    int totalCnt = 0;
						%>
						
							<% for (int j = 0; j < areaInfoList.size(); j++) {
							    areaBean = (AreaInfoBean)areaInfoList.get(j);	
							    
							    int temp = areaBean.getChargeArray().size();
							    if (temp == 0) temp = 1;
							    
								totalCnt += temp;
							} %>         

							
							<% 
							areaBean = new AreaInfoBean();
							
							for (int j = 0; j < areaInfoList.size(); j++) {
						        areaBean = (AreaInfoBean)areaInfoList.get(j);
						        
						        ArrayList chargeList = areaBean.getChargeArray();
						    %>

								<% if(j == 0) { %>
								 <tr>
						            <th scope="row" rowspan="<%=totalCnt%>" class="sido"><font class="font_orangeB"><%=KJFArea.getSido_Name(baseBean.getSido_code())%></font></th>
						       	<% } %>
							    <th scope="col" rowspan="<%=chargeList.size()%>"><strong><%=areaBean.getSigungu_name()%></strong></td>
					            <td scope="col" rowspan="<%=chargeList.size()%>">
					            	<!--주소자리 --><%=KJFUtil.print(areaBean.getAddress(), "&nbsp")%><br>
									<!--홈페이지URL --><a href="<%=KJFUtil.print(areaBean.getHompage(), "..")%>" target="_blank"><%=KJFUtil.print(areaBean.getHompage(), "&nbsp")%></a>
								</td>

								<% if (chargeList.size() > 0) { %>
									<% for (int k = 0; k < chargeList.size(); k++) {
							        		AreaChargeBean chargeBean = (AreaChargeBean)chargeList.get(k);
									%>								
										<%if (k > 0){ %>
										<tr>
										<% } %>
		
								            <td align="center"><%=KJFUtil.print(chargeBean.getPart(), "&nbsp")%></td>
								            <td align="center"><%=KJFUtil.print(chargeBean.getName(), "&nbsp")%></td>
								            <td align="center" class="letter0"><%=KJFUtil.print(chargeBean.getTel(), "&nbsp") %></td>
								            <td align="center"><%=KJFUtil.print(chargeBean.getEmail(), "&nbsp")%></td>
		
										<% if(k == chargeList.size() -1) { %>
								        </tr>
										<%} %>
		
									<% } %>
	
								<% } else { %>
										<td>&nbsp;</td>
							            <td>&nbsp;</td>
							            <td>&nbsp;</td>
							            <td>&nbsp;</td>
									</tr>
								<% } %>
							<%} %>
					
						<% } %>
						
				    <% } else { %>
				    	<tr>
				    		<td colspan="7">등록된 정보가 없습니다.</td>
				    	</tr>
				    <% } %>
				    </tbody>
				</table>
				<!--시군구 : E -->
			</div>
			</form>
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
