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

/****************************************************************************
 * <p>Title       : MemCCReRegCmd Class</p>
 * <p>Description : 공인인증 재등록 화면 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 *
 * NOTE : 공인인증 재등록 화면 처리를 한다.
 *
 * @version 1.0
 * @author  PKT
 ***************************************************************************/
public class MemCCReRegCmd implements KJFCommand {
    
	UserEnt user;
	
    public MemCCReRegCmd() {        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {    
               
        // 검색조건 설정및 체크
        MemParam pm = checkPm(request, form);
        
        user = (UserEnt)request.getSession().getAttribute("user");
        
        if (KJFUtil.isEmpty(user)) {
            throw new LoginException(request, "회원전용 페이지 로그인 체크", "../member/login.do");
        }
        
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
        
        ReportDAO    rDAO    = new ReportDAO();        
        ReportEntity rEntity = null;
        
        
        if ( "U".equals(user.getCAPITAL()) ) {
            StringBuffer sbSQL = new StringBuffer();
            sbSQL = new StringBuffer();
            sbSQL.append(" SELECT USER_ID,              \n");   // 아이디
            sbSQL.append("        USER_NAME,            \n");   // 이름
            sbSQL.append("        USER_SSN1,            \n");   // 주민번호1
            sbSQL.append("        USER_SSN2             \n");   // 주민번호2
            sbSQL.append("   FROM PT_HOM_USER           \n");
            sbSQL.append("  WHERE USER_ID = ?           \n");
            sbSQL.append("    AND USER_NAME = ?         \n");
            
            rDAO.setValue(1, user.getUSER_ID());
            rDAO.setValue(2, user.getUSER_NAME());
            
            rEntity = rDAO.select(sbSQL.toString());        
        }
        
        request.setAttribute("rEntity", rEntity);
    }
    
    
    /**
     * 회원 ID정보 Load
     * 
     * @param request
     * @param pm
     * @throws Exception
     */
    private void loadDetail(HttpServletRequest request, MemParam pm) throws Exception {
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
