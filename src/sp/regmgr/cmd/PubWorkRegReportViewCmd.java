package sp.regmgr.cmd;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.cfg.Config;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFLog;
import kjf.util.KJFUtil;
import kjf.util.LoginException;

import org.apache.struts.action.ActionForm;

import sp.regmgr.RegMgrParam;
import sp.uent.UserEnt;

/***************************************************************************
 * <p>Title       : PubWorkRegReportViewCmd Class</p>
 * <p>Description : ����� ��ϱ��� �Ű� �� Ŭ����</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : ����� ��ϱ��� �Ű� �� ó���� �Ѵ�. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class PubWorkRegReportViewCmd implements KJFCommand {
    
    UserEnt user;
    
    public PubWorkRegReportViewCmd() {        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {        
        
        user = (UserEnt)request.getSession().getAttribute("user");
        
        // �α��� ���� üũ
        if (KJFUtil.isEmpty(user)) {
            throw new LoginException(request, "ȸ���� ������ �α��� üũ", "../member/login.do");
        }  
        
        // �˻����� ������ üũ
        RegMgrParam pm = checkPm(request, form);
        request.setAttribute("pm", pm);
        
        // ����� ��ϱ��� �Ű� ���� 
        loadData(request, pm);
        
        // Ÿ�ü������ ����
        loadSubCompanyData(request, pm);
        
        return request.getParameter("cmd");
    }
    
    
    /**
     * ����� ��� ���ؽŰ� ������ �����´�.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadData(HttpServletRequest request, RegMgrParam pm) throws Exception {
        
        ReportEntity rEntity = null;        
        
        ReportDAO rDAO = new ReportDAO();   
        
        String chgbre_seq = KJFUtil.print(request.getParameter("chgbre_seq"));
        String recv_num   = KJFUtil.print(request.getParameter("recv_num")); 
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT                               \n");        
        sbSQL.append("        PT_H.NAME,                    \n");   // ��ü��
        sbSQL.append("        PT_H.REP_NM_KOR,              \n");   // ��ǥ��
        sbSQL.append("        PT_H.COI_WRT_NUM,             \n");   // ���ι�ȣ
        sbSQL.append("        PT_H.MANA_NUM,                \n");   // ����ڹ�ȣ
        sbSQL.append("        PT_H.ADDR_TEL_NUM,            \n");   // ��ȭ��ȣ
        sbSQL.append("        PT_H.ADDR_FAX_NUM,            \n");   // �ѽ���ȣ
        sbSQL.append("        PT_H.ADDR_POST_NUM,           \n");   // �������ּ� �����ȣ
        sbSQL.append("        PT_H.ADDR_ADDR,               \n");   // �������ּ�
        sbSQL.append("        PT_H.ADDR_DETAIL_ADDR,        \n");   // �������ּһ�
        
        sbSQL.append("        PT_H.RECV_NUM,                \n");   // ������ȣ 
        sbSQL.append("        PT_S.RECV_DT,                 \n");   // ��������
        sbSQL.append("        PT_S.PROC_LIM,                \n");   // ó������
        sbSQL.append("        PT_H.MOT_STE,                 \n");   // ������� �ڵ�
        sbSQL.append("        PT_C1.CODE_NAME AS MOT_STE_NM \n");   // ������� �ڵ� ��
        
        sbSQL.append("   FROM (PT_R_BASIC_CHANGE_HISTORY PT_H INNER JOIN PT_R_BASIC_STATEMENT PT_S      \n");
        sbSQL.append("     ON RTRIM(PT_H.RECV_NUM) = PT_S.RECV_NUM)                                     \n");      
        
        // ������� �ڵ� ��
        sbSQL.append("   LEFT JOIN (                                        \n");  
        sbSQL.append("              SELECT P_CODE, CODE, CODE_NAME          \n"); 
        sbSQL.append("                FROM PT_COM_CODE                      \n");   
        sbSQL.append("               WHERE P_CODE = 'REGPROC'               \n");   
        sbSQL.append("              ) PT_C1 ON PT_H.MOT_STE = PT_C1.CODE    \n"); 
      
        sbSQL.append("  WHERE PT_H.CHGBRE_SEQ = ?       \n");   // ���泻�� �Ϸù�ȣ 
        sbSQL.append("    AND PT_H.RECV_NUM = ?         \n");   // ������ȣ
        
        rDAO.setValue(i++, chgbre_seq);
        rDAO.setValue(i++, recv_num);        
        
        rEntity = rDAO.select(sbSQL.toString());

        /****** �˻����� �ʱⰪ ***********/
        request.setAttribute("rEntity", rEntity);        
    }
    
    
    /**
     * Ÿ�ü������ ������ �����´�.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadSubCompanyData(HttpServletRequest request, RegMgrParam pm) throws Exception {
        
        ReportEntity rEntity = null;        
        
        ReportDAO rDAO = new ReportDAO();   
        
        String recv_num   = KJFUtil.print(request.getParameter("recv_num")); 
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT                                   \n");        
        sbSQL.append("        PT_C1.CODE_NAME AS SUB_CODE_NM,   \n");   // Ÿ �ü������ �ڵ��
        sbSQL.append("        PT_S.SUB_CODE,                    \n");   // Ÿ �ü������ �ڵ�
        sbSQL.append("        PT_S.SUB_WRT_NUM                  \n");   // Ÿ �ü������ ��Ϲ�ȣ  
        
        sbSQL.append("   FROM PT_R_BASIC_CHANGE_HISTORY PT_H INNER JOIN PT_R_SUBSIDIARY PT_S    \n");
        sbSQL.append("     ON RTRIM(PT_H.TMP_WRT_NUM) = PT_S.TMP_WRT_NUM                        \n"); 
        
        sbSQL.append("   LEFT JOIN (                                        \n");  
        sbSQL.append("              SELECT P_CODE, CODE, CODE_NAME          \n"); 
        sbSQL.append("                FROM PT_COM_CODE                      \n");   
        sbSQL.append("               WHERE P_CODE = 'SUBSIDIARY'            \n");   
        sbSQL.append("              ) PT_C1 ON PT_S.SUB_CODE = PT_C1.CODE   \n"); 
        
        sbSQL.append("  WHERE PT_H.RECV_NUM = ?             \n");   // ������ȣ
        
        rDAO.setValue(i++, recv_num);
        
        rEntity = rDAO.select(sbSQL.toString());
  
        /****** �˻����� �ʱⰪ ***********/
        request.setAttribute("cEntity", rEntity);        
    }
    
    
    /**
     * �� üũ �޼ҵ�
     * 
     * @param request
     * @param form
     * @return
     * @throws Exception
     */
    private RegMgrParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {

        RegMgrParam pm = (RegMgrParam)form;

        // �Ķ���� �����
        KJFLog.debug(pm.toString(request));
        
        // ����¡ ����
        if (KJFUtil.isEmpty(pm.getRowPerPage())) {            
            pm.setRowPerPage(Config.props.get("ROW_PER_PAGE"));
        }

        return pm;
    }
}
