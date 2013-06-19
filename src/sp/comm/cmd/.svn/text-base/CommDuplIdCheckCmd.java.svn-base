package sp.comm.cmd;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFLog;
import kjf.util.KJFUtil;

import org.apache.struts.action.ActionForm;

import sp.comm.CommParam;

/***************************************************************************
 * <p>Title       : CommDuplIdCheckCmd Class</p>
 * <p>Description : 아이디 중복 검사 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : 아이디 중복 검사 처리를 한다. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class CommDuplIdCheckCmd implements KJFCommand {
    
    public CommDuplIdCheckCmd() {
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {
        
        // 검색조건 설정및 체크
        CommParam pm = checkPm(request, form); 
        
        request.setAttribute("pm", pm);
        
        // 사용자 아이디 체크
        if ( isUserID(request, pm) || isAdminID(request, pm)) {
            request.setAttribute("isUserID", "N");
            System.out.println("N");
        
        } else {
            request.setAttribute("isUserID", "Y");
            System.out.println("Y");
        }        
        
        return request.getParameter("cmd");
    }
    
    /**
     * 사용자 아이디 체크
     * @param HttpServletRequest, MemParam
     * @return
     */
    private boolean isUserID(HttpServletRequest request, CommParam pm)throws Exception{
        
         ReportDAO    rDAO    = new ReportDAO();
         ReportEntity rEntity = null;      
         
         String user_id = request.getParameter("id");
                  
         StringBuffer sbSQL = new StringBuffer();        
         sbSQL.append(" SELECT COUNT(*) AS CNT           \n");
         sbSQL.append("   FROM PT_HOM_USER               \n");
         sbSQL.append("  WHERE LOWER(USER_ID) = ?        \n");
         
         rDAO.setValue(1, user_id.toLowerCase());
       
         rEntity = rDAO.select(sbSQL.toString());
         
         if(KJFUtil.str2int(rEntity.getValue(0, "CNT")) > 0) {             
             return true;
         }

         return false;
    }
    
    /**
     * 관리자 아이디 체크
     * @param HttpServletRequest, MemParam
     * @return
     */
    private boolean isAdminID(HttpServletRequest request, CommParam pm)throws Exception{
        
         ReportDAO    rDAO    = new ReportDAO();
         ReportEntity rEntity = null;      
         
         String user_id = request.getParameter("id");
                  
         StringBuffer sbSQL = new StringBuffer();        
         sbSQL.append(" SELECT COUNT(*) AS CNT          \n");
         sbSQL.append("   FROM PT_MI_USER               \n");
         sbSQL.append("  WHERE LOWER(OFFI_ID) = ?       \n");
         
         rDAO.setValue(1, user_id.toLowerCase());
       
         rEntity = rDAO.select(sbSQL.toString());
         
         if (KJFUtil.str2int(rEntity.getValue(0, "CNT")) > 0) {             
             return true;
         }

         return false;
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
