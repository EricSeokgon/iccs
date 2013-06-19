package sp.usebefore.cmd;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.cfg.Config;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFLog;
import kjf.util.KJFUtil;
import kjf.util.LoginException;

import org.apache.struts.action.ActionForm;

import sp.uent.UserEnt;
import sp.usebefore.UseBeforeParam;

/***************************************************************************
 * <p>Title       : UseBeforeChkMgrCmd Class</p>
 * <p>Description : ������˻� ���� �� Ŭ����</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : ������˻� ���� �� ó���� �Ѵ�. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class UseBeforeChkMgrCmd implements KJFCommand {
    
	UserEnt user;
	
    public UseBeforeChkMgrCmd() {   
        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {        
        
        user = (UserEnt)request.getSession().getAttribute("user");
        
        // �α��� ���� üũ
        if (KJFUtil.isEmpty(user)) {
            throw new LoginException(request, "ȸ���� ������ �α��� üũ", "../member/login.do");
        }  
        
        // �˻����� ������ üũ
        UseBeforeParam pm = checkPm(request, form);
        request.setAttribute("pm", pm);
        
        // �˻� ��� 
        loadData(request, pm);
        
        // ������ż��μ��� ���� 
        loadPremiseData(request, pm);
        
        // ��۰������ż��� ����
        loadBroadcastingData(request, pm);
        
        // �̵���ű������μ��� ����
        loadMobileData(request, pm);
        
        return request.getParameter("cmd");
    }
    
    /**
     * ������˻���� �˻���� ������ �����´�.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadData(HttpServletRequest request, UseBeforeParam pm) throws Exception {
        
        ReportEntity rEntity = null;        
        
        ReportDAO rDAO = new ReportDAO();
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT                       \n");
        sbSQL.append("        DECODE(UB.PROC_STE, '1', '����ó��',        \n");   
        sbSQL.append("                         '2', 'ó����',          \n");  
        sbSQL.append("                         '3', 'ó���Ϸ�',        \n");  
        sbSQL.append("                         '') AS PROC_STE,    \n");  // ó������
        sbSQL.append("         UB.NAPPL_YN , \n");  // �˻���        
        sbSQL.append(" (SELECT SERVER_YN FROM PT_S_SYSVAR_MASTER WHERE SIGUNGU_CODE = UB.SIGUNGU_CODE) AS SERVER_YN "); // ���輭�� ����
        
        sbSQL.append("   FROM PT_UB_USEBEFORE UB      \n");         
        sbSQL.append("  WHERE RECV_NUM = ?  AND SIGUNGU_CODE =?  AND USEBEFINSP_DELINUM IS NOT NULL \n");   // ������ȣ
        
        rDAO.setValue(i++, KJFUtil.print(pm.getScRecvNum()));    
        rDAO.setValue(i++, KJFUtil.print(pm.getScSIGUNGU_CODE())); 
        
        rEntity = rDAO.select(sbSQL.toString());
        
        request.setAttribute("rEntity", rEntity);
    }
    
    
    /**
     * ������˻� ���� ������ż��μ��� ������ �����´�.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadPremiseData(HttpServletRequest request, UseBeforeParam pm) throws Exception {
        
        ReportEntity rEntity = null;        
        
        ReportDAO rDAO = new ReportDAO();
        
        String recv_num = KJFUtil.print(pm.getScRecvNum());
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT                       \n");
        sbSQL.append("        UDM.SEQ,              \n");   // �˻��ȣ
        sbSQL.append("        LARCLAS,              \n");   // �˻�
        sbSQL.append("        MIDCLAS,              \n");   // �˻�
        sbSQL.append("        SMACLAS,              \n");   // �˻��׸�
        sbSQL.append("        UDM.CONT,             \n");   // �˻系�� �� �˻����
        sbSQL.append("        UCD.ITEM_OUT          \n");   // �˻���
        
        sbSQL.append("   FROM PT_UB_DETAIL_MASTER UDM , PT_UB_DETAIL UCD   \n");    
        
        sbSQL.append("  WHERE UDM.ITEM = ?                  \n");   // ����� ����
        sbSQL.append("    AND SUBSTR(LARCLAS,0,1) = ?       \n");   // ������ż��μ��� ��ȣ
        sbSQL.append("    AND UCD.RECV_NUM(+) = ?           \n");   // ������ȣ
        sbSQL.append("    AND UDM.SEQ = UCD.SEQ(+)          \n");   
        sbSQL.append("  ORDER BY TO_NUMBER(SEQ) ASC         \n");
        
        rDAO.setValue(i++, "2");        // ����� ����
        rDAO.setValue(i++, "1");        // ������ż��μ��� ��ȣ
        rDAO.setValue(i++, recv_num);   // ������ȣ        

        rEntity = rDAO.select(sbSQL.toString());
        
        request.setAttribute("pEntity", rEntity);
    }
    
    /**
     * ������˻� ���� ��۰������ż��� ������ �����´�.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadBroadcastingData(HttpServletRequest request, UseBeforeParam pm) throws Exception {
        
        ReportEntity rEntity = null;        
        
        ReportDAO rDAO = new ReportDAO();
        
        String recv_num = KJFUtil.print(pm.getScRecvNum());
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT                       \n");
        sbSQL.append("        UDM.SEQ,              \n");   // �˻��ȣ
        sbSQL.append("        LARCLAS,              \n");   // �˻�
        sbSQL.append("        MIDCLAS,              \n");   // �˻�
        sbSQL.append("        SMACLAS,              \n");   // �˻��׸�
        sbSQL.append("        UDM.CONT,             \n");   // �˻系�� �� �˻����
        sbSQL.append("        UCD.ITEM_OUT          \n");   // �˻���
        
        sbSQL.append("   FROM PT_UB_DETAIL_MASTER UDM , PT_UB_DETAIL UCD   \n");    
        
        sbSQL.append("  WHERE UDM.ITEM = ?                  \n");   // ����� ����
        sbSQL.append("    AND SUBSTR(LARCLAS,0,1) = ?       \n");   // ��۰������ż��� ��ȣ
        sbSQL.append("    AND UCD.RECV_NUM(+) = ?           \n");   // ������ȣ
        sbSQL.append("    AND UDM.SEQ = UCD.SEQ(+)          \n");   
        sbSQL.append("  ORDER BY TO_NUMBER(SEQ) ASC         \n");
        
        rDAO.setValue(i++, "2");        // ����� ����
        rDAO.setValue(i++, "2");        // ��۰������ż��� ��ȣ
        rDAO.setValue(i++, recv_num);   // ������ȣ        

        rEntity = rDAO.select(sbSQL.toString());
        
        request.setAttribute("bEntity", rEntity);
    }
    
    
    /**
     * ������˻� �̵���ű������μ��� ������ �����´�.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadMobileData(HttpServletRequest request, UseBeforeParam pm) throws Exception {
        
        ReportEntity rEntity = null;        
        
        ReportDAO rDAO = new ReportDAO();
        
        String recv_num = KJFUtil.print(pm.getScRecvNum());
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT                       \n");
        sbSQL.append("        UDM.SEQ,              \n");   // �˻��ȣ
        sbSQL.append("        LARCLAS,              \n");   // �˻�
        sbSQL.append("        MIDCLAS,              \n");   // �˻�
        sbSQL.append("        SMACLAS,              \n");   // �˻��׸�
        sbSQL.append("        UDM.CONT,             \n");   // �˻系�� �� �˻����
        sbSQL.append("        UCD.ITEM_OUT          \n");   // �˻���
        
        sbSQL.append("   FROM PT_UB_DETAIL_MASTER UDM , PT_UB_DETAIL UCD   \n");    
        
        sbSQL.append("  WHERE UDM.ITEM = ?                  \n");   // ����� ����
        sbSQL.append("    AND SUBSTR(LARCLAS,0,1) = ?       \n");   // �̵���ű������μ��� ��ȣ
        sbSQL.append("    AND UCD.RECV_NUM(+) = ?           \n");   // ������ȣ
        sbSQL.append("    AND UDM.SEQ = UCD.SEQ(+)          \n");   
        sbSQL.append("  ORDER BY TO_NUMBER(SEQ) ASC         \n");
        
        rDAO.setValue(i++, "2");        // ����� ����
        rDAO.setValue(i++, "3");        // �̵���ű������μ��� ��ȣ
        rDAO.setValue(i++, recv_num);   // ������ȣ        

        rEntity = rDAO.select(sbSQL.toString());
        
        request.setAttribute("mEntity", rEntity);
    }
    
    
    /**
     * �� üũ �޼ҵ�
     * 
     * @param request
     * @param form
     * @return
     * @throws Exception
     */
    private UseBeforeParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {

        UseBeforeParam pm = (UseBeforeParam)form;

        // �Ķ���� �����
        KJFLog.debug(pm.toString(request));
        
        // ����¡ ����
        if (KJFUtil.isEmpty(pm.getRowPerPage())) {            
            pm.setRowPerPage(Config.props.get("ROW_PER_PAGE"));
        }

        return pm;
    }
}
