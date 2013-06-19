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
 * <p>Title       : UseBeforeChkMgrCmd Class</p>
 * <p>Description : 사용전검사 관리 상세 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : 사용전검사 관리 상세 처리를 한다. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class UseBeforeChkMgrCmd implements KJFCommand {
    
	UserEnt user;
	
    public UseBeforeChkMgrCmd() {   
        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {        
        
        user = (UserEnt)request.getSession().getAttribute("user");
        
        // 로그인 유무 체크
        if (KJFUtil.isEmpty(user)) {
            throw new LoginException(request, "회원용 페이지 로그인 체크", "../member/login.do");
        }  
        
        // 검색조건 설정및 체크
        UseBeforeParam pm = checkPm(request, form);
        request.setAttribute("pm", pm);
        
        // 검사 결과 
        loadData(request, pm);
        
        // 구내통신선로설비 정보 
        loadPremiseData(request, pm);
        
        // 방송공동수신설비 정보
        loadBroadcastingData(request, pm);
        
        // 이동통신구내선로설비 정보
        loadMobileData(request, pm);
        
        return request.getParameter("cmd");
    }
    
    /**
     * 사용전검사관리 검색결과 정보를 가져온다.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadData(HttpServletRequest request, UseBeforeParam pm) throws Exception {
        
        ReportEntity rEntity = null;        
        
        ReportDAO rDAO = new ReportDAO();
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT                       \n");
        sbSQL.append("        DECODE(UB.PROC_STE, '1', '접수처리',        \n");   
        sbSQL.append("                         '2', '처리중',          \n");  
        sbSQL.append("                         '3', '처리완료',        \n");  
        sbSQL.append("                         '') AS PROC_STE,    \n");  // 처리상태
        sbSQL.append("         UB.NAPPL_YN , \n");  // 검사결과        
        sbSQL.append(" (SELECT SERVER_YN FROM PT_S_SYSVAR_MASTER WHERE SIGUNGU_CODE = UB.SIGUNGU_CODE) AS SERVER_YN "); // 연계서버 유무
        
        sbSQL.append("   FROM PT_UB_USEBEFORE UB      \n");         
        sbSQL.append("  WHERE RECV_NUM = ?  AND SIGUNGU_CODE =?  AND USEBEFINSP_DELINUM IS NOT NULL \n");   // 접수번호
        
        rDAO.setValue(i++, KJFUtil.print(pm.getScRecvNum()));    
        rDAO.setValue(i++, KJFUtil.print(pm.getScSIGUNGU_CODE())); 
        
        rEntity = rDAO.select(sbSQL.toString());
        
        request.setAttribute("rEntity", rEntity);
    }
    
    
    /**
     * 사용전검사 관리 구내통신선로설비 정보를 가져온다.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadPremiseData(HttpServletRequest request, UseBeforeParam pm) throws Exception {
        
        ReportEntity rEntity = null;        
        
        ReportDAO rDAO = new ReportDAO();
        
        String recv_num = KJFUtil.print(pm.getScRecvNum());
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT                       \n");
        sbSQL.append("        UDM.SEQ,              \n");   // 검사번호
        sbSQL.append("        LARCLAS,              \n");   // 검사
        sbSQL.append("        MIDCLAS,              \n");   // 검사
        sbSQL.append("        SMACLAS,              \n");   // 검사항목
        sbSQL.append("        UDM.CONT,             \n");   // 검사내용 및 검사기준
        sbSQL.append("        UCD.ITEM_OUT          \n");   // 검사결과
        
        sbSQL.append("   FROM PT_UB_DETAIL_MASTER UDM , PT_UB_DETAIL UCD   \n");    
        
        sbSQL.append("  WHERE UDM.ITEM = ?                  \n");   // 사용전 구분
        sbSQL.append("    AND SUBSTR(LARCLAS,0,1) = ?       \n");   // 구내통신선로설비 번호
        sbSQL.append("    AND UCD.RECV_NUM(+) = ?           \n");   // 접수번호
        sbSQL.append("    AND UDM.SEQ = UCD.SEQ(+)          \n");   
        sbSQL.append("  ORDER BY TO_NUMBER(SEQ) ASC         \n");
        
        rDAO.setValue(i++, "2");        // 사용전 구분
        rDAO.setValue(i++, "1");        // 구내통신선로설비 번호
        rDAO.setValue(i++, recv_num);   // 접수번호        

        rEntity = rDAO.select(sbSQL.toString());
        
        request.setAttribute("pEntity", rEntity);
    }
    
    /**
     * 사용전검사 관리 방송공동수신설비 정보를 가져온다.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadBroadcastingData(HttpServletRequest request, UseBeforeParam pm) throws Exception {
        
        ReportEntity rEntity = null;        
        
        ReportDAO rDAO = new ReportDAO();
        
        String recv_num = KJFUtil.print(pm.getScRecvNum());
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT                       \n");
        sbSQL.append("        UDM.SEQ,              \n");   // 검사번호
        sbSQL.append("        LARCLAS,              \n");   // 검사
        sbSQL.append("        MIDCLAS,              \n");   // 검사
        sbSQL.append("        SMACLAS,              \n");   // 검사항목
        sbSQL.append("        UDM.CONT,             \n");   // 검사내용 및 검사기준
        sbSQL.append("        UCD.ITEM_OUT          \n");   // 검사결과
        
        sbSQL.append("   FROM PT_UB_DETAIL_MASTER UDM , PT_UB_DETAIL UCD   \n");    
        
        sbSQL.append("  WHERE UDM.ITEM = ?                  \n");   // 사용전 구분
        sbSQL.append("    AND SUBSTR(LARCLAS,0,1) = ?       \n");   // 방송공동수신설비 번호
        sbSQL.append("    AND UCD.RECV_NUM(+) = ?           \n");   // 접수번호
        sbSQL.append("    AND UDM.SEQ = UCD.SEQ(+)          \n");   
        sbSQL.append("  ORDER BY TO_NUMBER(SEQ) ASC         \n");
        
        rDAO.setValue(i++, "2");        // 사용전 구분
        rDAO.setValue(i++, "2");        // 방송공동수신설비 번호
        rDAO.setValue(i++, recv_num);   // 접수번호        

        rEntity = rDAO.select(sbSQL.toString());
        
        request.setAttribute("bEntity", rEntity);
    }
    
    
    /**
     * 사용전검사 이동통신구내선로설비 정보를 가져온다.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadMobileData(HttpServletRequest request, UseBeforeParam pm) throws Exception {
        
        ReportEntity rEntity = null;        
        
        ReportDAO rDAO = new ReportDAO();
        
        String recv_num = KJFUtil.print(pm.getScRecvNum());
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT                       \n");
        sbSQL.append("        UDM.SEQ,              \n");   // 검사번호
        sbSQL.append("        LARCLAS,              \n");   // 검사
        sbSQL.append("        MIDCLAS,              \n");   // 검사
        sbSQL.append("        SMACLAS,              \n");   // 검사항목
        sbSQL.append("        UDM.CONT,             \n");   // 검사내용 및 검사기준
        sbSQL.append("        UCD.ITEM_OUT          \n");   // 검사결과
        
        sbSQL.append("   FROM PT_UB_DETAIL_MASTER UDM , PT_UB_DETAIL UCD   \n");    
        
        sbSQL.append("  WHERE UDM.ITEM = ?                  \n");   // 사용전 구분
        sbSQL.append("    AND SUBSTR(LARCLAS,0,1) = ?       \n");   // 이동통신구내선로설비 번호
        sbSQL.append("    AND UCD.RECV_NUM(+) = ?           \n");   // 접수번호
        sbSQL.append("    AND UDM.SEQ = UCD.SEQ(+)          \n");   
        sbSQL.append("  ORDER BY TO_NUMBER(SEQ) ASC         \n");
        
        rDAO.setValue(i++, "2");        // 사용전 구분
        rDAO.setValue(i++, "3");        // 이동통신구내선로설비 번호
        rDAO.setValue(i++, recv_num);   // 접수번호        

        rEntity = rDAO.select(sbSQL.toString());
        
        request.setAttribute("mEntity", rEntity);
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
