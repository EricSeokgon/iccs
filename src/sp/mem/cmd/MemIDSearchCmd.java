package sp.mem.cmd;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFLog;

import org.apache.struts.action.ActionForm;

import sp.mem.MemParam;

/****************************************************************************
 * <p>Title       : MemIDSearchCmd Class</p>
 * <p>Description : ID검색 처리 프로세스 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 *
 * NOTE : ID검색 처리를 한다.
 *
 * @version 1.0
 * @author  PKT
 ***************************************************************************/
public class MemIDSearchCmd implements KJFCommand {
    
    public MemIDSearchCmd() {        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {
        
        // 검색조건 설정및 체크
        MemParam pm = checkPm(request, form);
        
        // 검색조건 및 초기데이타 로드
        loadCondition(request, pm);
        
        // 회원 정보 데이타 로드
        loadDetail(request, pm);
        
        request.setAttribute("pm", pm);
        
        return request.getParameter("cmd"); 
    }   
    
    
    /**
     * 검색조건 및 초기데이타 로드
     * 
     * @param HttpServletRequest
     * @return
     */
    private void loadCondition(HttpServletRequest request, MemParam pm) throws Exception {
        
    }
    
    
    /**
     * 회원 ID정보 Load
     * 
     * @param request
     * @param pm
     * @throws Exception
     */
    private void loadDetail(HttpServletRequest request, MemParam pm) throws Exception {
        
        ReportDAO    rDAO    = new ReportDAO();
        ReportEntity rEntity = null;      
        
        int i = 1;
        String name    = request.getParameter("user_name");
        String ssn1    = request.getParameter("user_ssn1");
        String ssn2    = request.getParameter("user_ssn2");
        String capital = request.getParameter("mem_kind");        
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT USER_ID,              \n");
        sbSQL.append("        USER_NAME,            \n");
        sbSQL.append("        USER_EMAIL,           \n");
        sbSQL.append("        INS_DT                \n");
        sbSQL.append("   FROM PT_HOM_USER           \n");
        sbSQL.append("  WHERE USER_NAME = ?         \n");
        sbSQL.append("    AND USER_SSN1 = ?         \n");
        sbSQL.append("    AND USER_SSN2 = ?         \n");
        sbSQL.append("    AND CAPITAL = ?           \n");
        
        rDAO.setValue(i++, name);
        rDAO.setValue(i++, ssn1);
        rDAO.setValue(i++, ssn2);
        rDAO.setValue(i++, capital);        
      
        rEntity = rDAO.select(sbSQL.toString());
        
        request.setAttribute("rEntity", rEntity);
    }
    
    
    /**
     * 검색조건 초기값 설정및 체크
     * 
     * @param request
     * @param form
     * @return BbsParam
     * @throws Exception
     */
    private MemParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {

        MemParam pm = (MemParam) form;

        // 파라미터 디버깅
        KJFLog.debug(pm.toString(request));

        return pm;
    }
}
