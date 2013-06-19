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
 * <p>Title       : MemRegBusinessCmd Class</p>
 * <p>Description : ���ȸ����� ȭ�� ó�� ���μ��� Ŭ����</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 *
 * NOTE : ���ȸ�����ȭ�� ó���� �Ѵ�.
 *
 * @version 1.0
 * @author  PKT
 ***************************************************************************/
public class MemRegBusinessCmd implements KJFCommand {
    
    public MemRegBusinessCmd() {
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
        
        String com_num = KJFUtil.print(request.getParameter("com_num"));
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT COUNT(*) AS CNT   \n");
        sbSQL.append("   FROM PT_HOM_USER               \n");
        sbSQL.append("  WHERE COM_NUM = ?               \n");
        
        rDAO.setValue(i++, com_num);
        
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
     * �˻����� �� �ʱⵥ��Ÿ �ε�
     * 
     * @param HttpServletRequest
     * @return
     */
    private MemParam loadCompanyInfo(HttpServletRequest request, MemParam pm) throws Exception {
        
        ReportDAO    rDAO    = new ReportDAO();        
        ReportEntity rEntity = null;
        
        StringBuffer sbSQL = new StringBuffer();
        sbSQL = new StringBuffer();
        sbSQL.append(" SELECT NAME,                 \n");   // ��ȣ
        sbSQL.append("        COI_WRT_NUM,          \n");   // ����� ��Ϲ�ȣ
        sbSQL.append("        ADDR_POST_NUM,        \n");   // ������ �����ȣ
        sbSQL.append("        ADDR_ADDR,            \n");   // ������ �ּ�
        sbSQL.append("        ADDR_DETAIL_ADDR,     \n");   // ������ �ּһ�
        sbSQL.append("        ADDR_TEL_NUM,         \n");   // ������ ��ȭ��ȣ      
        sbSQL.append("        ADDR_FAX_NUM          \n");   // ������ �ѽ���ȣ           
        sbSQL.append("   FROM PT_R_COMPANY_MASTER   \n");
        sbSQL.append("  WHERE MANA_NUM = ?          \n");
        
        rDAO.setValue(1, pm.getCom_num());
        
        rEntity = rDAO.select(sbSQL.toString());
        
        if (rEntity.getRowCnt() > 0) {
            
            pm.setCom_name(rEntity.getValue(0, "NAME"));                        // ��ȣ          
            pm.setReg_num(rEntity.getValue(0, "COI_WRT_NUM"));                  // ����� ��Ϲ�ȣ
            pm.setOffice_post_num(rEntity.getValue(0, "ADDR_POST_NUM"));        // ������ �����ȣ
            pm.setOffice_addr(rEntity.getValue(0, "ADDR_ADDR"));                // ������ �ּ�
            pm.setOffice_addr_detail(rEntity.getValue(0, "ADDR_DETAIL_ADDR"));  // ������ �ּһ�
            pm.setOffice_tel(rEntity.getValue(0, "ADDR_TEL_NUM"));              // ������ ��ȭ��ȣ 
            pm.setOffice_fax(rEntity.getValue(0, "ADDR_FAX_NUM"));              // ������ �ѽ���ȣ
            pm.setCom_reg_yn("Y");                                              // �������Ͽ���
            
        } else {
            pm.setCom_reg_yn("N");  // �������Ͽ���
        }
        
        
        return pm;
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
        
        pm = loadCompanyInfo(request, pm);

        // �Ķ���� �����
        KJFLog.debug(pm.toString(request));

        return pm;
    }
}
