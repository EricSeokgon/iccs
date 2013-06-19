package sp.pda.cmd;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.cfg.Config;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFLog;
import kjf.util.KJFUtil;
import kjf.util.LoginException;

import org.apache.struts.action.ActionForm;

import sp.pda.PdaParam;
/***************************************************************************
 * <p>Title       : MyProgressStateCmd Class</p>
 * <p>Description : 나의 민원진행상태 처리 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : 나의 민원진행상태 정보 처리를 한다. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class PdaApprMgrCmd implements KJFCommand {
    
    public PdaApprMgrCmd() {
        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {        
        
        
        // 검색조건 설정및 체크
        PdaParam pm = checkPm(request, form);
        
        // 
        loadData(request, pm);
        
        return request.getParameter("cmd");
    }
    
    
    /**
     * 게시물 리스트를 가져온다.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadData(HttpServletRequest request, PdaParam pm) throws Exception {
    	ReportDAO    rDAO        = new ReportDAO();
        ReportEntity rEntity     = null;
        
       
        
        StringBuilder selectSQL = new StringBuilder();
        StringBuilder fromSQL = new	 StringBuilder();
        StringBuilder whereSQL = new StringBuilder();
        
       selectSQL.append("SELECT SEQ,LARCLAS,MIDCLAS,SMACLAS  		\n");
        
         fromSQL.append("  FROM PT_UB_DETAIL_MASTER	    \n");
        
       whereSQL.append("  WHERE ITEM = '2'  \n");
       whereSQL.append(" ORDER BY SEQ ASC  \n");
       
        rEntity = rDAO.select(selectSQL.toString()+fromSQL.toString()+whereSQL.toString());
        request.setAttribute("rEntity", rEntity);
    }
    
    
    /**
     * 폼 체크 메소드
     * 
     * @param request
     * @param form
     * @return
     * @throws Exception
     */
    private PdaParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {

    	PdaParam pm = (PdaParam)form;

        // 파라미터 디버깅
        KJFLog.debug(pm.toString(request));
        
        // 페이징 라인
        if (KJFUtil.isEmpty(pm.getRowPerPage())) {            
            pm.setRowPerPage(Config.props.get("ROW_PER_PAGE"));
        }

        return pm;
    }
}
