package sp.mem.cmd;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFLog;
import kjf.util.KJFSelect;
import kjf.util.KJFSelectOPS;
import kjf.util.KJFUtil;
import kjf.util.MsgException;

import org.apache.struts.action.ActionForm;

import sp.mem.MemParam;

/****************************************************************************
 * <p>Title       : MemRegPrivateCmd Class</p>
 * <p>Description : ����ȸ����� ȭ�� ó�� ���μ��� Ŭ����</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 *
 * NOTE : ����ȸ����� ȭ�� ó���� �Ѵ�.
 *
 * @version 1.0
 * @author  PKT
 ***************************************************************************/
public class MemRegPrivateCmd implements KJFCommand {
    
    public MemRegPrivateCmd() {
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {    
               
        // �˻����� ������ üũ
        MemParam pm = checkPm(request, form);
        
        // ȸ������ üũ
        checkDuplicate(request, pm);
        
        // �˻����� �� �ʱⵥ��Ÿ �ε�
        loadCondition(request, pm);
        
        request.setAttribute("pm", pm);
        
        return request.getParameter("cmd"); 
    }
    
    
    /**
     * ȸ������ �ߺ� üũ
     * 
     * @param request
     * @param form
     * @return
     * @throws Exception
     */
    private void checkDuplicate(HttpServletRequest request, MemParam form) throws Exception {
        
        ReportDAO rDAO = new ReportDAO();
        
        ReportEntity rEntity = null;
        
        int i = 1;
        
        String ssn1 = KJFUtil.print(request.getParameter("user_ssn1"));
        String ssn2 = KJFUtil.print(request.getParameter("user_ssn2"));
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT COUNT(*) AS CNT   \n");
        sbSQL.append("   FROM PT_HOM_USER               \n");
        sbSQL.append("  WHERE USER_SSN1 = ?             \n");
        sbSQL.append("    AND USER_SSN2 = ?             \n");
        sbSQL.append("    AND CAPITAL = ?               \n");
        
        rDAO.setValue(i++, ssn1); 
        rDAO.setValue(i++, ssn2); 
        rDAO.setValue(i++, "U"); 
        
        rEntity = rDAO.select(sbSQL.toString());
        
        if (KJFUtil.str2int(rEntity.getValue(0, "CNT")) > 0) {
            throw new MsgException(request, "ȸ�����Ե� ����", "FALSE", "../mem/MemAction.do?cmd=MemRegDupl");
        }
    }
    
    
    /**
     * �˻����� �� �ʱⵥ��Ÿ �ε�
     * 
     * @param HttpServletRequest
     * @return
     */
    private void loadCondition(HttpServletRequest request, MemParam pm) throws Exception {
        
        //  ��.�� ����
        StringBuffer sbSQL = new StringBuffer();
        sbSQL = new StringBuffer();
        sbSQL.append(" SELECT AREA_CODE, SIDO_NM    \n");
        sbSQL.append("   FROM PT_SIDO_CODE          \n");
        sbSQL.append("  WHERE SUBSTR(AREA_CODE, 1, 2) = SUBSTR(AREA_CODE, 3, 4)    \n");
        sbSQL.append("  ORDER BY SIDO_NM     \n");           
        
        Vector<KJFSelect> v_scSD_CD = KJFSelectOPS.getSel(sbSQL.toString(), "", "��.�� ����");
        request.setAttribute("v_scSD_CD", v_scSD_CD);    
    }
    
    
    /**
     * �˻����� �ʱⰪ ������ üũ
     * 
     * @param request
     * @param form
     * @return BbsParam
     * @throws Exception
     */
    private MemParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {

        MemParam pm = (MemParam) form;

        // �Ķ���� �����
        KJFLog.debug(pm.toString(request));

        return pm;
    }
}
