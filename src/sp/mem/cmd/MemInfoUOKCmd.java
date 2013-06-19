package sp.mem.cmd;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.util.KJFLog;
import kjf.util.KJFUtil;
import kjf.util.LoginException;

import org.apache.struts.action.ActionForm;

import sp.mem.MemParam;
import sp.uent.UserEnt;

/****************************************************************************
 * <p>Title       : MemInfoVCmd Class</p>
 * <p>Description : 사용자정보 수정 성공 처리 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 *
 * NOTE : 사용자정보 수정 성공 처리를 한다.
 *
 * @version 1.0
 * @author  PKT
 ***************************************************************************/
public class MemInfoUOKCmd implements KJFCommand {
    
    UserEnt user;
    
    public MemInfoUOKCmd() {        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {    
        
        user = (UserEnt)request.getSession().getAttribute("user");
        
        // 검색조건 설정및 체크
        MemParam pm = checkPm(request, form);        
        request.setAttribute("pm", pm);
        
        if (KJFUtil.isEmpty(user)) {
            throw new LoginException(request, "회원전용 페이지 로그인 체크", "../member/login.do");
        }
        
        return request.getParameter("cmd"); 
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
