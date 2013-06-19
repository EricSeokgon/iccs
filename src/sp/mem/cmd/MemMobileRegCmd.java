package sp.mem.cmd;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFLog;
import kjf.util.KJFUtil;
import kjf.util.LoginException;

import org.apache.struts.action.ActionForm;

import sp.mem.MemParam;
import sp.uent.UserEnt;

public class MemMobileRegCmd implements KJFCommand {
    
    UserEnt user;
    
    public MemMobileRegCmd() {        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {    
        
        user = (UserEnt)request.getSession().getAttribute("user");
        
        // 검색조건 설정및 체크
        MemParam pm = checkPm(request, form);        
        request.setAttribute("pm", pm);
        
        if (KJFUtil.isEmpty(user)) {
            throw new LoginException(request, "회원전용 페이지 로그인 체크", "../member/login.do");
        }
        
        // 회원 모바일 서비스 정보 Load
        loadDetail(request, pm);
        
        return request.getParameter("cmd"); 
    }       
    
    
    /**
     * 회원 모바일 서비스  Load
     * 
     * @param request
     * @param pm
     * @throws Exception
     */
    private void loadDetail(HttpServletRequest request, MemParam pm) throws Exception {
                
        ReportDAO    rDAO    = new ReportDAO();
        ReportEntity rEntity = null;      
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT USER_ID,              \n");
        sbSQL.append("        USER_NAME,            \n");
        sbSQL.append("        USER_MOBILE1,         \n");
        sbSQL.append("        USER_MOBILE2,         \n");
        sbSQL.append("        USER_MOBILE3,         \n");
        sbSQL.append("        USER_MOBILE_YN,       \n");
        sbSQL.append("        CAPITAL               \n");
        sbSQL.append("   FROM PT_HOM_USER           \n");        
        sbSQL.append("  WHERE USER_ID = ?           \n");
        sbSQL.append("    AND USER_NAME = ?         \n");
        
        rDAO.setValue(i++, user.getUSER_ID());
        rDAO.setValue(i++, user.getUSER_NAME());
      
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
