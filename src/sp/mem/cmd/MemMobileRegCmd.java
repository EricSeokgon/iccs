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
        
        // �˻����� ������ üũ
        MemParam pm = checkPm(request, form);        
        request.setAttribute("pm", pm);
        
        if (KJFUtil.isEmpty(user)) {
            throw new LoginException(request, "ȸ������ ������ �α��� üũ", "../member/login.do");
        }
        
        // ȸ�� ����� ���� ���� Load
        loadDetail(request, pm);
        
        return request.getParameter("cmd"); 
    }       
    
    
    /**
     * ȸ�� ����� ����  Load
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
     * �˻����� �ʱⰪ ������ üũ
     * 
     * @param request
     * @param form
     * @return BbsParam
     * @throws Exception
     */
    private MemParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {

        MemParam pm = (MemParam) form;

        // �Ķ���� �����
        KJFLog.debug(pm.toString(request));

        return pm;
    }
}
