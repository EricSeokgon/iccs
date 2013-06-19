<%@ page contentType="text/html;charset=utf-8"%>
<%//@ page errorPage="../com/error.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import="kjf.ops.*" %>
<%@ page import="kjf.util.*" %>
<%@ page import="sp.bbs.*" %>
<%@ taglib uri="/KJFTags" prefix="KTags" %>

<%
	// 초기 변수 들 선언
	StatusEnt status = (StatusEnt)session.getAttribute("status");
		
	BbsParam pm = (BbsParam)request.getAttribute("pm");
	
	ReportEntity NoticeEntity = (ReportEntity)request.getAttribute("NoticeEntity");
	ReportEntity ListEntity   = (ReportEntity)request.getAttribute("ListEntity");

	String url_us = KJFUtil.print(request.getParameter("URL"), (String)request.getAttribute("url_us"));
	String cmd    = KJFUtil.print(request.getParameter("cmd"));
	
	// 페이징 관련(필수)
	String nowPage    = KJFUtil.print(pm.getNowPage());
	String rowPerPage = KJFUtil.print(pm.getRowPerPage());
	String totalCount = KJFUtil.print(pm.getTotalCount());
	// 게시판 필수 코드
	String CT_ID     = status.getCT_ID();
	String BOARD_SEQ = KJFUtil.print(request.getParameter("BOARD_SEQ"));    
    String URL       = "";
    
    if ("/bbs/BbsK.jsp".equals(url_us)) {
    	URL = "/bbs/BbsK.jsp";
    } else {
    	URL	= status.getURL();
    }
              			
	String bbsType = status.getTYPE();
//	String bbsSKIN = status.getSKIN();
	
	int CutStr = KJFUtil.str2int(status.getCUT_TITLE());

	// 검색 코드
	String scSD_CD      = KJFUtil.print(pm.getScSD_CD());
	String scSGG_CD     = KJFUtil.print(pm.getScSGG_CD());	
    String scSLCT_GUBUN = KJFUtil.print(pm.getScSLCT_GUBUN());
    String scTEXTVALUE  = KJFUtil.print(pm.getScTEXTVALUE());
    
    String scFAQ_L_CATE	= KJFUtil.print(pm.getScFAQ_L_CATE());
    String scFAQ_S_CATE	= KJFUtil.print(pm.getScFAQ_S_CATE());
    
	boolean isAdmin		= status.isADMIN();

	//  타이틀 표시 여부
	boolean isTitleDate = status.isTITLE_DATE_YN();
	boolean isTitleHit  = status.isTITLE_HIT_YN();
	boolean isTitleIp   = status.isTITLE_IP_YN();
	
	String modify_go_url = "showPassForm('confirmPassModify')";
	
	String SUBJ_1 = "";
	String SUBJ_2 = "";
	String SUBJ_3 = "";
	String SUBJ_4 = "";
	String SUBJ_5 = "";
	String SUBJ_6 = "";
	String SUBJ_7 = "";
	String SUBJ_8 = "";
	String SUBJ_9 = "";
	String SUBJ_10 = "";
	
	if(!KJFUtil.isEmpty(status.getSUBJ_1())) SUBJ_1 = status.getSUBJ_1();
	if(!KJFUtil.isEmpty(status.getSUBJ_2())) SUBJ_2 = status.getSUBJ_2();
	if(!KJFUtil.isEmpty(status.getSUBJ_3())) SUBJ_3 = status.getSUBJ_3();
	if(!KJFUtil.isEmpty(status.getSUBJ_4())) SUBJ_4 = status.getSUBJ_4();
	if(!KJFUtil.isEmpty(status.getSUBJ_5())) SUBJ_5 = status.getSUBJ_5();
	if(!KJFUtil.isEmpty(status.getSUBJ_6())) SUBJ_6 = status.getSUBJ_6();
	if(!KJFUtil.isEmpty(status.getSUBJ_7())) SUBJ_7 = status.getSUBJ_7();
	if(!KJFUtil.isEmpty(status.getSUBJ_8())) SUBJ_8 = status.getSUBJ_8();
	if(!KJFUtil.isEmpty(status.getSUBJ_9())) SUBJ_9 = status.getSUBJ_9();
	if(!KJFUtil.isEmpty(status.getSUBJ_10())) SUBJ_10 = status.getSUBJ_10();

	int int_rnum = KJFUtil.str2int(totalCount)-(KJFUtil.str2int(nowPage) *KJFUtil.str2int(rowPerPage) - KJFUtil.str2int(rowPerPage));
%>

<script language="JavaScript" src="../bbs/js/BbsKList.js" type="text/javascript"></script>

<!-- 타이틀이 이보다 외부에 있어서 타이틀 값 전송 안됨-->
<form name="fmParam" method="post">

<!-- 페이징 관련(필수)-->
<input type=hidden name="nowPage" value="<%=nowPage%>">
<!-- rowPerPage는 셀렉트 박스에 있으므로 필요 없다.-->

<input type=hidden name="mode">

<!-- 게시판 기본 정보-->
<input type=hidden name="URL"   value="<%=URL%>">
<input type=hidden name="CT_ID" value="<%=CT_ID%>">
<input type=hidden name="BOARD_SEQ">

<!-- ========================= 게시판 스킨 START ======================== -->

	<!-- 일반 게시판 -->
	<% if(bbsType.equals("general_basic")) { %>
	<jsp:directive.include file="skin/general_basic/list.jsp" />
	
	<!-- FAQ 게시판 -->
	<% } else if(bbsType.equals("faq_basic")) { %>
	<jsp:directive.include file="skin/faq_basic/list.jsp" />

	<!-- 질의회신 게시판 -->
	<% } else if(bbsType.equals("faq_basic_pub")) { %>
	<jsp:directive.include file="skin/faq_basic_pub/list.jsp" />
	
	<!-- QNA 게시판 -->
	<% } else if(bbsType.equals("qna_basic")) { %>
	<jsp:directive.include file="skin/qna_basic/list.jsp" />

	<!-- 자료실 게시판 -->
	<% } else if(bbsType.equals("morgue_basic")) { %>
	<jsp:directive.include file="skin/morgue_basic/list.jsp" />
	<% } %>	
	
	
	
<!-- ========================= 게시판 스킨 END ======================== -->

</form>
