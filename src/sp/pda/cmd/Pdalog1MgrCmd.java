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
 * <p>Description : ���� �ο�������� ó�� Ŭ����</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : ���� �ο�������� ���� ó���� �Ѵ�. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class Pdalog1MgrCmd implements KJFCommand {
    
    public Pdalog1MgrCmd() {
        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {        
        
        
        // �˻����� ������ üũ
        PdaParam pm = checkPm(request, form);
        
        // 
        loadData(request, pm);
        
        return request.getParameter("cmd");
    }
    
    
    /**
     * �Խù� ����Ʈ�� �����´�.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadData(HttpServletRequest request, PdaParam pm) throws Exception {
    	ReportDAO    rDAO        = new ReportDAO();
        ReportEntity rEntity     = null;
        
        String SIDO_CODE = request.getParameter("city");
        String GUGUN = request.getParameter("gugun");
        String date = request.getParameter("date");
        
        StringBuilder selectSQL = new StringBuilder();
        StringBuilder fromSQL = new StringBuilder();
        StringBuilder whereSQL = new StringBuilder();
        
        
       selectSQL.append("SELECT UB.RECV_NUM, UB.SIDO_CODE,UB.SIGUNGU_CODE,CIV_RECV_NUM,APPLPER_NM,APPLPER_REP,  \n");
       selectSQL.append("       RTRIM(APPLPER_ADDR) AS APPLPER_ADDR, APPLPER_DETAILADDR,APPLPER_TELNUM,    \n");
       selectSQL.append("		 OPE_NAME,COI_WRT_NUM,OPE_REP,OPE_TELNUM,RTRIM(OPE_ADDR) AS OPE_ADDR ,OPE_DETAILADDR,  	\n");	
       selectSQL.append("		 RECV_DT,INSP_SPOT_NM,INSP_SPOT_ADDR,INSP_SPOT_DETAILADDR,MU.NM,WORK_ITEM    		\n");
        
         fromSQL.append("	FROM PT_UB_USEBEFORE UB, PT_MI_USER MU    \n");
        
        whereSQL.append("	WHERE UB.WRT_ID = MU.OFFI_ID   \n");
        whereSQL.append("	  AND UB.SIDO_CODE =  '"+SIDO_CODE+"'   \n");
        whereSQL.append("	  AND UB.SIGUNGU_CODE = '"+GUGUN+"'  \n");
        whereSQL.append("	  AND UB.USEBEFINSP_DELINUM is null  \n");        
        whereSQL.append("	  AND UB.NAPPL_YN is null  \n");
        whereSQL.append("	  AND UB.DEFI_YN = 'Y'  \n");
        
        
        rEntity = rDAO.select(selectSQL.toString()+fromSQL.toString()+whereSQL.toString());
        request.setAttribute("rEntity", rEntity);
    }
    
    
    /**
     * �� üũ �޼ҵ�
     * 
     * @param request
     * @param form
     * @return
     * @throws Exception
     */
    private PdaParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {

    	PdaParam pm = (PdaParam)form;

        // �Ķ���� �����
        KJFLog.debug(pm.toString(request));
        
        // ����¡ ����
        if (KJFUtil.isEmpty(pm.getRowPerPage())) {            
            pm.setRowPerPage(Config.props.get("ROW_PER_PAGE"));
        }

        return pm;
    }
}
