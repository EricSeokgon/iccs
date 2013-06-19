package sp.illegality.cmd;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.cfg.Config;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFLog;
import kjf.util.KJFUtil;
import kjf.util.LoginException;

import org.apache.struts.action.ActionForm;

import sp.illegality.IllegalityParam;
import sp.uent.UserEnt;

/***************************************************************************
 * <p>Title       : IllRegistCancelCmd Class</p>
 * <p>Description : ������ �󼼺��� Ŭ����</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : ������ �󼼺��� ó���� �Ѵ�. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class IllRegistCancelCmd implements KJFCommand {
    
    UserEnt user;
    
    public IllRegistCancelCmd() {        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {        
        
        user = (UserEnt)request.getSession().getAttribute("user");
        
        // �α��� ���� üũ
        if (KJFUtil.isEmpty(user)) {
            throw new LoginException(request, "ȸ���� ������ �α��� üũ", "../member/login.do");
        }  
        
        // �˻����� ������ üũ
        IllegalityParam pm = checkPm(request, form);
        
        // �������� û�������� ���Ȯ�� ���� 
        loadReportData(request, pm);
        
        // �������� û������ ���Ȯ�� ���� 
        loadDictionData(request, pm);
        
        request.setAttribute("pm", pm);
        
        return request.getParameter("cmd");
    }
    
    
    /**
     * ������ û�������� ������ �����´�.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadReportData(HttpServletRequest request, IllegalityParam pm) throws Exception {
        
        ReportEntity rEntity = null;        
        
        ReportDAO rDAO = new ReportDAO();      
      
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT                                   \n");   
        sbSQL.append("        PT_R.AUDI_SUBJ,                   \n");   // û������
        sbSQL.append("        PT_R.AUDI_PER_NM,                 \n");   // û�������
        sbSQL.append("        PT_R.AUDI_PER_ADDR,               \n");   // ������ּ�
        sbSQL.append("        PT_R.ADMI_DISPO_CAUSE,            \n");   // ó�л���
        sbSQL.append("        PT_R.LEG_BAS,                     \n");   // �����ٰ�        
        sbSQL.append("        PT_R.AUDI_EXEC_ORG,               \n");   // �ǽñ��
        sbSQL.append("        PT_R.AUDI_EXEC_PART,              \n");   // �ǽúμ�
        sbSQL.append("        PT_R.AUDI_EXEC_ADDR,              \n");   // û�����
        sbSQL.append("        PT_R.AUDI_EXEC_DETAILADDR,        \n");   // û�����
        sbSQL.append("        PT_R.AUDI_EXEC_DT,                \n");   // û������
        sbSQL.append("        PT_R.AUDI_EXEC_TIME,              \n");   // û���Ͻ�
        sbSQL.append("        PT_R.AUDI_SUPINT_NM,              \n");   // �����ڼ���
        sbSQL.append("        PT_R.AUDI_SUPINT_POS,             \n");   // ����������
        sbSQL.append("        PT_R.AUDI_SUPINT_PART             \n");   // �����ڼҼ�               
        
        sbSQL.append("   FROM (PT_R_COMPANY_MASTER PT_C INNER JOIN PT_M_MASTER PT_M \n");
        sbSQL.append("          ON RTRIM(PT_C.TMP_WRT_NUM) = PT_M.TMP_WRT_NUM)      \n");
        
        sbSQL.append("   INNER JOIN (                                               \n");   
        sbSQL.append("               SELECT *                                       \n");   
        sbSQL.append("                 FROM PT_M_AUDI_REPORT                        \n");  
        sbSQL.append("              ) PT_R  ON PT_M.TMP_WRT_NUM = PT_R.TMP_WRT_NUM  \n");
        sbSQL.append("                     AND PT_M.WRT_NUM     = PT_R.WRT_NUM      \n");
        
        sbSQL.append("  WHERE PT_C.COI_WRT_NUM = ?  \n");   // �������Ϲ�ȣ  
        sbSQL.append("    AND PT_C.MANA_NUM = ?     \n");   // ����� ��ȣ
          
        rDAO.setValue(i++, user.getREG_NUM());     
        rDAO.setValue(i++, user.getCOM_NUM());   
                
        rEntity = rDAO.select(sbSQL.toString());

        /****** �˻����� �ʱⰪ ***********/        
        request.setAttribute("rEntity", rEntity);
    }
    
    
    /**
     * ������ û������ ������ �����´�.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadDictionData(HttpServletRequest request, IllegalityParam pm) throws Exception {
        
        ReportEntity rEntity = null;        
        
        ReportDAO rDAO = new ReportDAO();      
        
        String tmp_wrt_num = KJFUtil.print(request.getParameter("tmp_wrt_num"));
        String wrt_num     = KJFUtil.print(request.getParameter("wrt_num"));
        
        int i = 1;
             
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT                                   \n");   
        sbSQL.append("        PT_D.AUDI_SUPINT_NM,              \n");   // ������ ���� 
        sbSQL.append("        PT_D.AUDI_SUPINT_POS,             \n");   // ������ ����
        sbSQL.append("        PT_D.AUDI_SUPINT_PART,            \n");   // ������ �Ҽ�
        sbSQL.append("        PT_D.AUDI_ATTEND_PERSON_NM,       \n");   // ������ ���� 
        sbSQL.append("        PT_D.AUDI_ATTEND_PERSON_POS,      \n");   // ������ ����
        sbSQL.append("        PT_D.AUDI_ATTEND_PERSON_PART,     \n");   // ������ �Ҽ�
        sbSQL.append("        PT_D.AUDI_PER_ATTE_YN,            \n");   // �⼮����
        sbSQL.append("        PT_D.AUDI_PER_NATTE_CAUSE,        \n");   // ���⼮����        
        sbSQL.append("        PT_D.AUDI_OPN_YN,                 \n");   // ��������
        sbSQL.append("        PT_D.AUDI_NOPN_CAUSE,             \n");   // ���������
        sbSQL.append("        PT_D.AUDI_EXEC_DT,                \n");   // û������
        sbSQL.append("        PT_D.AUDI_EXEC_TIME,              \n");   // û���Ͻ�
        sbSQL.append("        PT_D.AUDI_EXEC_LOC,               \n");   // û�����
        sbSQL.append("        PT_D.PER_STAT_CONT,               \n");   // ����� ��������
        sbSQL.append("        PT_D.PER_PRES_EVID,               \n");   // �����  ��������
        sbSQL.append("        PT_D.EVID_INV_POIN,               \n");   // �������� ��������
        sbSQL.append("        PT_D.EVID_INV_EVID                \n");   // �������� ��������
       
        sbSQL.append("   FROM (PT_R_COMPANY_MASTER PT_C INNER JOIN PT_M_MASTER PT_M \n");
        sbSQL.append("          ON RTRIM(PT_C.TMP_WRT_NUM) = PT_M.TMP_WRT_NUM)      \n");
        
        sbSQL.append("   INNER JOIN (                                               \n");   
        sbSQL.append("               SELECT *                                       \n");   
        sbSQL.append("                 FROM PT_M_AUDI_DICTION                       \n");  
        sbSQL.append("              ) PT_D  ON PT_M.TMP_WRT_NUM = PT_D.TMP_WRT_NUM  \n");
        sbSQL.append("                     AND PT_M.WRT_NUM     = PT_D.WRT_NUM      \n");
        
        sbSQL.append("  WHERE PT_C.COI_WRT_NUM = ?  \n");   // �������Ϲ�ȣ  
        sbSQL.append("    AND PT_C.MANA_NUM = ?     \n");   // ����� ��ȣ
          
        rDAO.setValue(i++, user.getREG_NUM());     
        rDAO.setValue(i++, user.getCOM_NUM());  
        
        rEntity = rDAO.select(sbSQL.toString());

        /****** �˻����� �ʱⰪ ***********/
        request.setAttribute("dEntity", rEntity);
    }
    
    
    /**
     * �� üũ �޼ҵ�
     * 
     * @param request
     * @param form
     * @return
     * @throws Exception
     */
    private IllegalityParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {

        IllegalityParam pm = (IllegalityParam)form;

        // �Ķ���� �����
        KJFLog.debug(pm.toString(request));
        
        // ����¡ ����
        if (KJFUtil.isEmpty(pm.getRowPerPage())) {            
            pm.setRowPerPage(Config.props.get("ROW_PER_PAGE"));
        }

        return pm;
    }
}
