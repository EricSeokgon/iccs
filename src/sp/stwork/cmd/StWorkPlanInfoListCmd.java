package sp.stwork.cmd;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.cfg.Config;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFLog;
import kjf.util.KJFUtil;
import kjf.util.LoginException;

import org.apache.struts.action.ActionForm;

import sp.stwork.StworkParam;
import sp.uent.UserEnt;

/***************************************************************************
 * <p>Title       : StWorkPlanInfoCmd Class</p>
 * <p>Description : 착공전 설계도 확인현황 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : 착공전 설계도 확인현황 리스트 처리를 한다. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class StWorkPlanInfoListCmd implements KJFCommand {
    
	UserEnt user;
	
    public StWorkPlanInfoListCmd() {        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {        
        
        user = (UserEnt)request.getSession().getAttribute("user");
        
        // 로그인 유무 체크
        if (KJFUtil.isEmpty(user)) {
            throw new LoginException(request, "회원용 페이지 로그인 체크", "../member/login.do");
        }  
        
        // 검색조건 설정및 체크
        StworkParam pm = checkPm(request, form);
        
        // 착공전 설계도 확인 검색 정보 
        loadRecData(request, pm);
        
        // 착공전 설계도 확인현황 정보 
        loadData(request, pm);
        
        return request.getParameter("cmd");
    }
    
    /**
     * 착공전 설계도 정보를 가져온다.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadRecData(HttpServletRequest request, StworkParam pm) throws Exception {
        
        ReportEntity rEntity = null;        
        
        ReportDAO rDAO = new ReportDAO();      
        
        String scCode     = KJFUtil.print(pm.getScCode());      // 조건
        String scStru_Num = KJFUtil.print(pm.getScStru_Num());  // 접수번호
        
        if (KJFUtil.isEmpty(scStru_Num)) {
            return;
        }
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT                       \n");
        sbSQL.append("        CIV_RECV_NUM,         \n");   // 시스템 접수번호
        sbSQL.append("        RECV_NUM,             \n");   // 사용자 접수번호
        sbSQL.append("        ORPE_NM,              \n");   // 발주자
        sbSQL.append("        PLANER_NAME,          \n");   // 설계자 상호
        sbSQL.append("        PLANER_NM,            \n");   // 설계자
        sbSQL.append("        STRU_COMMIT_NUM,      \n");   // 건축허가번호
        sbSQL.append("        RECV_DT,              \n");   // 접수일자
        
        // 진행상태
        sbSQL.append("        DECODE(PROC_STE, '1', '신규등록',        \n");   
        sbSQL.append("                         '2', '처리중',          \n");  
        sbSQL.append("                         '3', '처리완료',        \n");  
        sbSQL.append("                         '') AS PROC_STE         \n");
        
        sbSQL.append("   FROM PT_UB_CONSTRUCTION    \n");         
        sbSQL.append("  WHERE STRU_COMMIT_NUM = ?   \n");  
        sbSQL.append("    AND SIDO_CODE = ?         \n");
        //sbSQL.append("    AND SIGUNGU_CODE = ?      \n");
        
        rDAO.setValue(i++, scStru_Num);
        rDAO.setValue(i++, user.getSIDO_CODE());
       // rDAO.setValue(i++, user.getSIGUNGU_CODE());
        
        if (scCode.equals("001")) {         // 발주자
            
            sbSQL.append("    AND (ORPE_NM = ? OR ORPE_NM = ?)    \n");
            
            rDAO.setValue(i++, user.getUSER_NAME());
            rDAO.setValue(i++, user.getCOM_NAME());
            
        } else if (scCode.equals("002")) {  // 설계자
            
            sbSQL.append("    AND (PLANER_NM = ? OR PLANER_NM = ?)    \n");
            
            rDAO.setValue(i++, user.getUSER_NAME());
            rDAO.setValue(i++, user.getCOM_NAME());
        }
        
        rEntity = rDAO.select(sbSQL.toString());
        
        request.setAttribute("recEntity", rEntity);
    }
    
    
    /**
     * 착공전 설계도 확인현황 리스트 정보를 가져온다.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadData(HttpServletRequest request, StworkParam pm) throws Exception {
        
        ReportEntity rEntity = null;        
        
        ReportDAO rDAO = new ReportDAO();      
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT                                   \n");
        sbSQL.append("        PT_U.RECV_NUM,                    \n");   // 시스템 접수번호
        sbSQL.append("        PT_U.CIV_RECV_NUM,                \n");   // 사용자 접수번호
        sbSQL.append("        PT_U.ORPE_NM,                     \n");   // 발주자
        sbSQL.append("        PT_U.PLANER_NM,                   \n");   // 설계자
        sbSQL.append("        PT_U.STRU_COMMIT_NUM,             \n");   // 건축물허가번호
        sbSQL.append("        PT_U.RECV_DT,                     \n");   // 접수일자     
        
        // 진행상태
        sbSQL.append("        DECODE(PT_U.PROC_STE, '1', '신규등록',        \n");   
        sbSQL.append("                              '2', '처리중',          \n");  
        sbSQL.append("                              '3', '처리완료',        \n");  
        sbSQL.append("                                   '') AS PROC_STE    \n");
        
        sbSQL.append("   FROM PT_UB_CONSTRUCTION PT_U INNER JOIN PT_HOME_CONSTRUCTION PT_H    \n");      
        sbSQL.append("     ON PT_U.RECV_NUM = PT_H.RECV_NUM     \n");
        sbSQL.append("  WHERE PT_H.USER_ID = ?                  \n");
        sbSQL.append("  ORDER BY PT_U.CIV_RECV_NUM              \n");   // 접수번호
        
        rDAO.setValue(i++, user.getUSER_ID());
        
        /* ************************** 페이징 관련 START **************************/
        StringBuffer sbCntSQL = new StringBuffer();
        sbCntSQL.append(" SELECT COUNT(*)  CNT      \n");
        sbCntSQL.append("   FROM PT_UB_CONSTRUCTION PT_U INNER JOIN PT_HOME_CONSTRUCTION PT_H  \n");      
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
    private StworkParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {

        StworkParam pm = (StworkParam)form;

        // 파라미터 디버깅
        KJFLog.debug(pm.toString(request));
        
        // 페이징 라인
        if (KJFUtil.isEmpty(pm.getRowPerPage())) {            
            pm.setRowPerPage(Config.props.get("ROW_PER_PAGE"));
        }

        return pm;
    }
}
