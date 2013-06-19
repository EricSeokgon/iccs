package sp.mem.cmd;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.util.KJFLog;

import org.apache.struts.action.ActionForm;

import sp.mem.MemParam;

/****************************************************************************
 * <p>Title       : MemRegPrivateCmd Class</p>
 * <p>Description : 회원등록 성공 화면 처리 프로세스 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 *
 * NOTE : 회원등록 성공 화면 처리를 한다.
 *
 * @version 1.0
 * @author  PKT
 ***************************************************************************/
public class MemRegSuccessCmd implements KJFCommand {
    
    public MemRegSuccessCmd() {        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {    
               
        // 검색조건 설정및 체크
        MemParam pm = checkPm(request, form);
     
        // 검색조건 및 초기데이타 로드
        loadCondition(request, pm);
        
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
