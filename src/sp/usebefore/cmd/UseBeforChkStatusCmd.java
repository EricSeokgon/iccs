package sp.usebefore.cmd;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.cfg.Config;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFLog;
import kjf.util.KJFUtil;
import kjf.util.LoginException;

import org.apache.struts.action.ActionForm;

import sp.uent.UserEnt;
import sp.usebefore.UseBeforeParam;

/***************************************************************************
 * <p>Title       : UseBeforChkStatusCmd Class</p>
 * <p>Description : 사용전검사 접수현황 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : 사용전검사 접수현황 처리를 한다. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class UseBeforChkStatusCmd implements KJFCommand {
    
	UserEnt user;
	
    public UseBeforChkStatusCmd() {   
        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {        
        
        user = (UserEnt)request.getSession().getAttribute("user");
        
        // 로그인 유무 체크
        if (KJFUtil.isEmpty(user)) {
            throw new LoginException(request, "회원용 페이지 로그인 체크", "../member/login.do");
        }  
        
        // 검색조건 설정및 체크
        UseBeforeParam pm = checkPm(request, form);
        
        // 사용전검사 검색 정보 
        //loadRecData(request, pm);
        
        // 사용전검사 현황 정보 
       // loadDataStat(request, pm);
        
        loadData(request, pm);
        
        return request.getParameter("cmd");
    }
    
    /**
     * 사용전검사 접수현황 정보를 가져온다.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadData(HttpServletRequest request, UseBeforeParam pm) throws Exception {
        
    	ReportEntity rEntity = null;        
        ReportDAO rDAO = new ReportDAO();      
        
        String recv_num = KJFUtil.print(request.getParameter("scRecv_num"));
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer(); 
        sbSQL.append(" SELECT UB.RECV_NUM,				 		\n");	// 사용자 접수번호
        sbSQL.append(" 		  UB.CIV_RECV_NUM ,                \n");	// 시스템 접수번호
        sbSQL.append("        UB.APPLPER_NM,                  \n");   // 신청인
        sbSQL.append("        UB.OPE_NAME,                    \n");   // 시공업체
        sbSQL.append("        UB.INSP_SPOT_NM,                \n");   // 현장명칭
        sbSQL.append("        UB.RECV_DT,                     \n");   // 접수일자     
        
        // 진행상태
        sbSQL.append("        DECODE(UB.PROC_STE, '1', '신규등록',        \n");   
        sbSQL.append("                         '2', '처리중',          \n");  
        sbSQL.append("                         '3', '처리완료',        \n");  
        sbSQL.append("                         '') AS PROC_STE,    \n");
        sbSQL.append("		  SM.ONLINE_CERT_USE_YN,   \n"); // 필증발급 유무
        sbSQL.append("		  SM.SERVER_YN  \n"); // 서버사용 유무
        sbSQL.append("	FROM PT_UB_USEBEFORE UB   ,PT_S_SYSVAR_MASTER SM \n");    
     
        sbSQL.append("  WHERE UB.APPLPER_NM 		= ?            	\n");
        sbSQL.append("		  AND UB.SIDO_CODE  	= ?				\n");
        sbSQL.append("		  AND UB.SIGUNGU_CODE  = ?				\n");
        
        if (!"".equals(recv_num)){
        	sbSQL.append("  AND ( CASE  WHEN LENGTH(UB.CIV_RECV_NUM) = 18 THEN substr(UB.CIV_RECV_NUM,12,LENGTH(UB.CIV_RECV_NUM)) \n");
        	sbSQL.append("   ELSE UB.CIV_RECV_NUM END )   = ? ");
        }
        
        sbSQL.append("   AND SM.SIGUNGU_CODE = UB.SIGUNGU_CODE ");
        sbSQL.append("  ORDER BY UB.CIV_RECV_NUM 	   	        \n");   // 접수번호
        
        rDAO.setValue(i++, user.getUSER_NAME()); 
        rDAO.setValue(i++, user.getSIDO_CODE());  
        rDAO.setValue(i++, user.getSIGUNGU_CODE());  
        
        if (!"".equals(recv_num)){
        	rDAO.setValue(i++, recv_num); 
        }
        /* ************************** 페n이징 관련 START **************************/
        StringBuffer sbCntSQL = new StringBuffer();
        sbCntSQL.append(" SELECT COUNT(*)  CNT  	\n");
        sbCntSQL.append("   FROM PT_UB_USEBEFORE \n");      
        sbCntSQL.append("  WHERE  APPLPER_NM 		= ?            	\n");
        sbCntSQL.append("		  AND SIDO_CODE  	= ?				\n");
        sbCntSQL.append("		  AND SIGUNGU_CODE  = ?				\n");
        
        if (!"".equals(recv_num)){
        	sbCntSQL.append("  AND ( CASE  WHEN LENGTH(CIV_RECV_NUM) = 18 THEN substr(CIV_RECV_NUM,12,LENGTH(CIV_RECV_NUM)) \n");
        	sbCntSQL.append("   ELSE CIV_RECV_NUM END )   = ? ");
        }
        
        //전체 목록 수
        String totalCount="";

        //페이지별 목록 수
        int rowPerPage = KJFUtil.str2int(pm.getRowPerPage());

        //현재 페이지 번호
        int nowPage = 1;
        nowPage = KJFUtil.isEmpty(pm.getNowPage()) ? 1 : Integer.parseInt(pm.getNowPage());

        rEntity = rDAO.select(sbCntSQL.toString());
        
        totalCount = rEntity.getValue(0, "CNT");
        
        if (rowPerPage == 0) rowPerPage = Integer.parseInt(totalCount);//추가
        if ((rowPerPage*nowPage) - Integer.parseInt(totalCount) > rowPerPage) nowPage = 1;

        pm.setTotalCount(totalCount);
        pm.setNowPage(String.valueOf(nowPage));
        /* *************************** 페이징 관련  END **************************/

        rEntity = rDAO.select(sbSQL.toString(), nowPage, rowPerPage);
        /****** 검색조건 초기값 ***********/
        request.setAttribute("pm", pm);
        request.setAttribute("rEntity", rEntity);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * 사용전검사 접수 정보를 가져온다.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadRecData(HttpServletRequest request, UseBeforeParam pm) throws Exception {
        
    	ReportEntity rEntity = null;        
        
        ReportDAO rDAO = new ReportDAO();      
        
        String scCode     = KJFUtil.print(pm.getScCode());		// 조건
        String scRecv_Num = KJFUtil.print(pm.getScRecvNum());	// 접수번호
        
        if (KJFUtil.isEmpty(scRecv_Num)) {
        	return;
        }
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT              		\n");
        sbSQL.append("        CIV_RECV_NUM,     \n");   // 시스템 접수번호
        sbSQL.append("        RECV_NUM,         \n");   // 사용자 접수번호
        sbSQL.append("        APPLPER_NM,   	\n");   // 신청인
        sbSQL.append("        OPE_NAME,     	\n");   // 시공업체
        sbSQL.append("        INSP_SPOT_NM,  	\n");   // 현장명칭
        sbSQL.append("        RECV_DT,     		\n");   // 접수일자     
        sbSQL.append("        PROC_STE			\n");   // 진행상태        
        sbSQL.append("   FROM PT_UB_USEBEFORE	\n");         
        sbSQL.append("  WHERE CIV_RECV_NUM = ? 	\n");  
        sbSQL.append("    AND SIDO_CODE = ?     \n");
        //sbSQL.append("    AND SIGUNGU_CODE = ?  \n");
        
        rDAO.setValue(i++, scRecv_Num);
        rDAO.setValue(i++, user.getSIDO_CODE());
       // rDAO.setValue(i++, user.getSIGUNGU_CODE());
        
        if (scCode.equals("001")) {			// 접수자
        	
        	sbSQL.append("    AND (APPLPER_NM = ? OR APPLPER_NM = ? OR APPLPER_REP = ? OR APPLPER_REP = ?)	\n");
        	
        	rDAO.setValue(i++, user.getUSER_NAME());
        	rDAO.setValue(i++, user.getCOM_NAME());
        	rDAO.setValue(i++, user.getUSER_NAME());
        	rDAO.setValue(i++, user.getCOM_NAME());
        	
        } else if (scCode.equals("002")) {	// 시공자
        	
        	sbSQL.append("    AND COI_WRT_NUM = ? 	   				\n");
        	sbSQL.append("    AND (OPE_NAME = ? OR OPE_NAME = ? OR OPE_REP = ? OR OPE_REP = ?)	    \n");
        	
        	rDAO.setValue(i++, user.getREG_NUM());
        	rDAO.setValue(i++, user.getUSER_NAME());
        	rDAO.setValue(i++, user.getCOM_NAME());
        	rDAO.setValue(i++, user.getUSER_NAME());
            rDAO.setValue(i++, user.getCOM_NAME());
        }
        
        rEntity = rDAO.select(sbSQL.toString());
        
        request.setAttribute("recEntity", rEntity);
    }
    
    
    /**
     * 사용전검사 접수현황 정보를 가져온다.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadDataStat(HttpServletRequest request, UseBeforeParam pm) throws Exception {
        
    	ReportEntity rEntity = null;        
        
        ReportDAO rDAO = new ReportDAO();      
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT                                   \n");
        sbSQL.append("        PT_U.RECV_NUM,                    \n");   // 시스템 접수번호
        sbSQL.append("        PT_U.CIV_RECV_NUM,                \n");   // 사용자 접수번호
        sbSQL.append("        PT_U.APPLPER_NM,                  \n");   // 신청인
        sbSQL.append("        PT_U.OPE_NAME,                    \n");   // 시공업체
        sbSQL.append("        PT_U.INSP_SPOT_NM,                \n");   // 현장명칭
        sbSQL.append("        PT_U.RECV_DT,                     \n");   // 접수일자     
        
        // 진행상태
        sbSQL.append("        DECODE(PT_U.PROC_STE, '1', '신규등록',        \n");   
        sbSQL.append("                              '2', '처리중',          \n");  
        sbSQL.append("                              '3', '처리완료',        \n");  
        sbSQL.append("                                   '') AS PROC_STE    \n");
        
        sbSQL.append("   FROM PT_UB_USEBEFORE PT_U INNER JOIN PT_HOME_USEBEFORE PT_H 	\n");      
        sbSQL.append("     ON PT_U.RECV_NUM = PT_H.RECV_NUM     \n");
     
        sbSQL.append("  WHERE PT_H.USER_ID = ?                  \n");
        sbSQL.append("  ORDER BY PT_U.CIV_RECV_NUM 	   	        \n");   // 접수번호
        
        rDAO.setValue(i++, user.getUSER_ID());   
        
        /* ************************** 페이징 관련 START **************************/
        StringBuffer sbCntSQL = new StringBuffer();
        sbCntSQL.append(" SELECT COUNT(*)  CNT  	\n");
        sbCntSQL.append("   FROM PT_UB_USEBEFORE PT_U INNER JOIN PT_HOME_USEBEFORE PT_H  \n");      
        sbCntSQL.append("     ON PT_U.RECV_NUM = PT_H.RECV_NUM      \n");
        sbCntSQL.append("  WHERE PT_H.USER_ID = ?                   \n");
        
        //전체 목록 수
        String totalCount="";

        //페이지별 목록 수
        int rowPerPage = KJFUtil.str2int(pm.getRowPerPage());

        //현재 페이지 번호
        int nowPage = 1;
        nowPage = KJFUtil.isEmpty(pm.getNowPage()) ? 1 : Integer.parseInt(pm.getNowPage());

        rEntity = rDAO.select(sbCntSQL.toString());
        
        totalCount = rEntity.getValue(0, "CNT");
        
        if (rowPerPage == 0) rowPerPage = Integer.parseInt(totalCount);//추가
        if ((rowPerPage*nowPage) - Integer.parseInt(totalCount) > rowPerPage) nowPage = 1;

        pm.setTotalCount(totalCount);
        pm.setNowPage(String.valueOf(nowPage));
        /* *************************** 페이징 관련  END **************************/

        rEntity = rDAO.select(sbSQL.toString(), nowPage, rowPerPage);
        
        /****** 검색조건 초기값 ***********/
        request.setAttribute("pm", pm);
        request.setAttribute("rEntity", rEntity);
    }
    
  
    /**
     * 폼 체크 메소드
     * 
     * @param request
     * @param form
     * @return
     * @throws Exception
     */
    private UseBeforeParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {

        UseBeforeParam pm = (UseBeforeParam)form;

        // 파라미터 디버깅
        KJFLog.debug(pm.toString(request));
        
        // 페이징 라인
        if (KJFUtil.isEmpty(pm.getRowPerPage())) {            
            pm.setRowPerPage(Config.props.get("ROW_PER_PAGE"));
        }

        return pm;
    }
}
