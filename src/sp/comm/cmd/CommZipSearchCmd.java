package sp.comm.cmd;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;

import sp.comm.CommParam;
import kjf.action.KJFCommand;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFLog;
import kjf.util.KJFUtil;

public class CommZipSearchCmd implements KJFCommand {
    
    public CommZipSearchCmd() {
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
        
        String searchKey = KJFUtil.print(request.getParameter("searchKey"), "");
        
        if("".equals(searchKey)) return;
        
        ReportDAO    rDAO    = new ReportDAO();
        ReportEntity rEntity = null;
        
        StringBuffer sbSQL = new StringBuffer("\n");
        sbSQL.append(" SELECT SEQ, ZIPCODE, SIDO, GUGUN, DONG, BUNJI    \n");
        sbSQL.append("   FROM PT_COM_ZIPCODE                            \n");
        sbSQL.append("  WHERE GUGUN LIKE ?                              \n");
        sbSQL.append("     OR DONG LIKE ?                               \n");
        sbSQL.append("  ORDER BY SEQ                                    \n");
        
        rDAO.setValue(1, "%" + searchKey + "%");
        rDAO.setValue(2, "%" + searchKey + "%");
    
        rEntity = rDAO.select(sbSQL.toString());
        
        /****** 검색조건 초기값 ***********/
        request.setAttribute("pm", pm);       
        request.setAttribute("rEntity", rEntity);
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
