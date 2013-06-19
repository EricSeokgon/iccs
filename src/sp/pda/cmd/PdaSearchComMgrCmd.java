package sp.pda.cmd;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;

import sp.pda.PdaParam;
import kjf.action.KJFCommand;
import kjf.cfg.Config;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFLog;
import kjf.util.KJFUtil;

/****************************************************************************
 *  
 * NOTE : 시공업자 정보 조회 
 *
 ****************************************************************************/
public class PdaSearchComMgrCmd implements KJFCommand {
	
	public PdaSearchComMgrCmd() {
        
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
        
        String NAME = request.getParameter("COM_NM");
        //String GUGUN = request.getParameter("gugun");
        //String id = request.getParameter("id");
        //NAME ="남";
        
        System.out.println(NAME);
        
        StringBuilder selectSQL = new StringBuilder();
        StringBuilder fromSQL = new StringBuilder();
        StringBuilder whereSQL = new StringBuilder();
              
        selectSQL.append("SELECT  NAME, REP_NM_KOR, COI_WRT_NUM, REP_MOBILE, ADDR_TEL_NUM, ADDR_FAX_NUM, ADDR_POST_NUM, ADDR_ADDR, ADDR_DETAIL_ADDR, MANA_NUM \n");
        
        fromSQL.append(" FROM PT_R_COMPANY_MASTER  \n");
        
        whereSQL.append(" WHERE 1 = 1 ");
        
        
        if (!KJFUtil.isEmpty(NAME)){
        	whereSQL.append(" AND NAME LIKE '%"+NAME+"%' \n");
        	whereSQL.append(" OR COI_WRT_NUM LIKE '%"+NAME+"%' \n");        	        
        }else{
        	whereSQL.append(" AND NAME = '111-111' \n");
        }
                      
        
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
