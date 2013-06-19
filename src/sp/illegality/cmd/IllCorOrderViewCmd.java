package sp.illegality.cmd;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.cfg.Config;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFLog;
import kjf.util.KJFUtil;
import kjf.util.LoginException;

import org.apache.struts.action.ActionForm;

import sp.illegality.IllegalityParam;
import sp.uent.UserEnt;

/***************************************************************************
 * <p>Title       : IllCorrectOrderCmd Class</p>
 * <p>Description : 시정명령 상세 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : 시정명령 상세 정보 처리를 한다. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class IllCorOrderViewCmd implements KJFCommand {
        
    public IllCorOrderViewCmd() {        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {        
        
        UserEnt user = (UserEnt)request.getSession().getAttribute("user");
        
        // 로그인 유무 체크
        if (KJFUtil.isEmpty(user)) {
            throw new LoginException(request, "회원용 페이지 로그인 체크", "../member/login.do");
        }  
        
        // 검색조건 설정및 체크
        IllegalityParam pm = checkPm(request, form);
        request.setAttribute("pm", pm);
        
        // 시정명령 결과 정보 
        loadData(request, pm);
        
        // 시정명령 통지서  정보 
        loadReportData(request, pm);
        
        return request.getParameter("cmd");
    }
    
    
    /**
     * 시정명령 상세 정보를 가져온다.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadData(HttpServletRequest request, IllegalityParam pm) throws Exception {
        
        ReportEntity rEntity = null;        
        
        ReportDAO rDAO = new ReportDAO();      
        
        String tmp_wrt_num = KJFUtil.print(request.getParameter("tmp_wrt_num"));
        String wrt_num     = KJFUtil.print(request.getParameter("wrt_num"));
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT                                   \n");        
        sbSQL.append("        PT_CO.CORRECT_ORDER_START_DT,     \n");   // 시정명령 시작일자
        sbSQL.append("        PT_CO.CORRECT_ORDER_END_DT,       \n");   // 시정명령 종료일자
        sbSQL.append("        PT_CO.CORRECT_ORDER_CONT,         \n");   // 시정명령 내용
        sbSQL.append("        PT_M.DISPO_DT,                    \n");   // 행정처분 일자
        sbSQL.append("        PT_M.DISPO_CAUSE,                 \n");   // 행정처분사유
        sbSQL.append("        PT_M.LEG_BAS                      \n");   // 법적근거
        
        sbSQL.append("   FROM (PT_M_MASTER PT_M INNER JOIN PT_M_CORRECT PT_CO   \n");
        sbSQL.append("     ON RTRIM(PT_M.TMP_WRT_NUM) = PT_CO.TMP_WRT_NUM       \n");
        sbSQL.append("    AND PT_M.WRT_NUM = PT_CO.WRT_NUM)                     \n");        
      
        sbSQL.append("  WHERE PT_M.TMP_WRT_NUM = ?  \n");   // 가등록번호  
        sbSQL.append("    AND PT_M.WRT_NUM     = ?  \n");   // 등록번호  
        
        rDAO.setValue(i++, tmp_wrt_num);
        rDAO.setValue(i++, wrt_num);
        
        
        rEntity = rDAO.select(sbSQL.toString());

        /****** 검색조건 초기값 ***********/
        request.setAttribute("rEntity", rEntity);
    }
    
    
    /**
     * 시정명령 통지서 정보를 가져온다.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadReportData(HttpServletRequest request, IllegalityParam pm) throws Exception {
        
        ReportEntity rEntity = null;        
        
        ReportDAO rDAO = new ReportDAO();      
        
        String tmp_wrt_num = KJFUtil.print(request.getParameter("tmp_wrt_num"));
        String wrt_num     = KJFUtil.print(request.getParameter("wrt_num"));
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT                                           \n");        
        sbSQL.append("        PT_R.NOTE_CLASS_CODE,                     \n");   // 통보종류
        sbSQL.append("        PT_C1.CODE_NAME AS NOTE_CLASS_CODE_NM ,   \n");   // 통보종류
        sbSQL.append("        PT_R.SEND_DT,                             \n");   // 통보일자
        sbSQL.append("        PT_R.PROC_LIM,                            \n");   // 처리기한   
        
        // 통보여부
        sbSQL.append("        DECODE(PT_R.RECV_YN, 'Y', '수신',         \n");   
        sbSQL.append("                             'N', '반송',         \n");  
        sbSQL.append("                             '') AS RECV_YN       \n");
        
        sbSQL.append("   FROM PT_M_BEF_REPORT PT_R                      \n");      
        
        sbSQL.append("   LEFT JOIN (                                    \n");  
        sbSQL.append("              SELECT P_CODE, CODE, CODE_NAME      \n"); 
        sbSQL.append("                FROM PT_COM_CODE                  \n");   
        sbSQL.append("               WHERE P_CODE = 'NOTICLASS'         \n");   
        sbSQL.append("              ) PT_C1 ON PT_R.NOTE_CLASS_CODE = PT_C1.CODE    \n");  
        
        sbSQL.append("  WHERE TMP_WRT_NUM = ?   \n");   // 가등록번호  
        sbSQL.append("    AND WRT_NUM     = ?   \n");   // 등록번호
        
        rDAO.setValue(i++, tmp_wrt_num);
        rDAO.setValue(i++, wrt_num);
        
        rEntity = rDAO.select(sbSQL.toString());

        /****** 검색조건 초기값 ***********/
        request.setAttribute("reportEntity", rEntity);
    }
    
    
    /**
     * 폼 체크 메소드
     * 
     * @param request
     * @param form
     * @return
     * @throws Exception
     */
    private IllegalityParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {

        IllegalityParam pm = (IllegalityParam)form;

        // 파라미터 디버깅
        KJFLog.debug(pm.toString(request));
        
        // 페이징 라인
        if (KJFUtil.isEmpty(pm.getRowPerPage())) {            
            pm.setRowPerPage(Config.props.get("ROW_PER_PAGE"));
        }

        return pm;
    }

}
