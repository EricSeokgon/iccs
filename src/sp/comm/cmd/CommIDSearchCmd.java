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
        
        // �˻����� ������ üũ
        CommParam pm = checkPm(request, form);   
        
        request.setAttribute("pm", pm);
        
        // �˻����ǿ� ���� List�� Load
        loadData(request, pm);
        
        return request.getParameter("cmd");
    }
    
    /************************************************************************
     * ��.��.�� �ڵ� load
     * 
     * @param HttpServletRequest
     * @param KmsParam
     * @return void
     ***********************************************************************/    
    private void loadData(HttpServletRequest request, CommParam pm) throws Exception {         
       
       
    } 
    
    /************************************************************************
     * �˻����� �ʱⰪ ���� �� üũ
     * 
     * @param HttpServletRequest
     * @param ActionForm
     * @return KmsParam
     ************************************************************************/
    private CommParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {
        
        CommParam pm = (CommParam) form;
                
        // �Ķ���� �����
        KJFLog.debug(pm.toString(request));
       
        return pm;
    }
}
