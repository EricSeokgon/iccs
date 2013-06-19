package sp.regmgr.cmd;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.cfg.Config;
import kjf.util.KJFLog;
import kjf.util.KJFUtil;
import kjf.util.LoginException;

import org.apache.struts.action.ActionForm;

import sp.regmgr.RegMgrParam;
import sp.uent.UserEnt;

/***************************************************************************
 * <p>Title       : PubWorkChReportCmd Class</p>
 * <p>Description : 공사업 변경신고 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : 공사업 변경신고 정보 처리를 한다. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class PubWorkChReportCmd implements KJFCommand {
    
	UserEnt user;
	
    public PubWorkChReportCmd() {        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {        
        
        user = (UserEnt)request.getSession().getAttribute("user");
        
        // 로그인 유무 체크
        if (KJFUtil.isEmpty(user)) {
            throw new LoginException(request, "회원용 페이지 로그인 체크", "../member/login.do");
        }  
        
        // 검색조건 설정및 체크
        RegMgrParam pm = checkPm(request, form);
        
        // 공사업 변경신고 정보 
        loadData(request, pm);
        
        return request.getParameter("cmd");
    }
    
    
    /**
     * DB 정보를 가져온다.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadData(HttpServletRequest request, RegMgrParam pm) throws Exception {
        
    }
    
    
    /**
     * 폼 체크 메소드
     * 
     * @param request
     * @param form
     * @return
     * @throws Exception
     */
    private RegMgrParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {

        RegMgrParam pm = (RegMgrParam)form;

        // 파라미터 디버깅
        KJFLog.debug(pm.toString(request));
        
        // 페이징 라인
        if (KJFUtil.isEmpty(pm.getRowPerPage())) {            
            pm.setRowPerPage(Config.props.get("ROW_PER_PAGE"));
        }

        return pm;
    }
}
