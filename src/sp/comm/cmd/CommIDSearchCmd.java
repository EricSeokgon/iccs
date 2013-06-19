package sp.comm.cmd;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.util.KJFLog;

import org.apache.struts.action.ActionForm;

import sp.comm.CommParam;

public class CommIDSearchCmd implements KJFCommand {
    
    public CommIDSearchCmd() {
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {
        
        // 검색조건 설정및 체크
        CommParam pm = checkPm(request, form);   
        
        request.setAttribute("pm", pm);
        
        // 검색조건에 따른 List를 Load
        loadData(request, pm);
        
        return request.getParameter("cmd");
    }
    
    /************************************************************************
     * 시.군.구 코드 load
     * 
     * @param HttpServletRequest
     * @param KmsParam
     * @return void
     ***********************************************************************/    
    private void loadData(HttpServletRequest request, CommParam pm) throws Exception {         
       
       
    } 
    
    /************************************************************************
     * 검색조건 초기값 설정 및 체크
     * 
     * @param HttpServletRequest
     * @param ActionForm
     * @return KmsParam
     ************************************************************************/
    private CommParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {
        
        CommParam pm = (CommParam) form;
                
        // 파라미터 디버깅
        KJFLog.debug(pm.toString(request));
       
        return pm;
    }
}
