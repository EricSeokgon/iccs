package sp.comm.cmd;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;

import sp.comm.CommParam;
import kjf.action.KJFCommand;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFLog;
import kjf.util.KJFUtil;

/***************************************************************************
 * <p>Title       : CommPubRecCheckCmd Class</p>
 * <p>Description : �������� Ȯ�� Ŭ����</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : �������� Ȯ�� ó���� �Ѵ�. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class CommPubRecCheckCmd implements KJFCommand {
    
    public CommPubRecCheckCmd() {
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {
        
        // �˻����� ������ üũ
        CommParam pm = checkPm(request, form);   
        
        request.setAttribute("pm", pm);
        
        // ����� ���̵� üũ
        if ( isPubReg(request, pm) ) {
            request.setAttribute("result", "success");
        
        } else {
            request.setAttribute("result", "failure");
        }
        
        
        return request.getParameter("cmd");
    }
    
    /**
     * ����� ���̵� üũ
     * @param HttpServletRequest, MemParam
     * @return
     */
    private boolean isPubReg(HttpServletRequest request, CommParam pm)throws Exception{
        
         ReportDAO    rDAO    = new ReportDAO();
         ReportEntity rEntity = null;      
         
         String mana_num = request.getParameter("mana_num");
         
         StringBuffer sbSQL = new StringBuffer();        
         sbSQL.append(" SELECT COUNT(*) AS CNT           \n");
         sbSQL.append("   FROM PT_R_COMPANY_MASTER       \n");
         sbSQL.append("  WHERE MANA_NUM = ?              \n");
         
         rDAO.setValue(1, mana_num);
       
         rEntity = rDAO.select(sbSQL.toString());
         
         if(KJFUtil.str2int(rEntity.getValue(0, "CNT")) > 0) {             
             return true;
         }

         return false;
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
