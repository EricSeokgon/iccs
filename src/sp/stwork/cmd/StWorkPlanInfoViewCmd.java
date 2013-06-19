package sp.stwork.cmd;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.cfg.Config;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFLog;
import kjf.util.KJFUtil;
import kjf.util.LoginException;

import org.apache.struts.action.ActionForm;

import sp.stwork.StworkParam;
import sp.uent.UserEnt;

/***************************************************************************
 * <p>Title       : StWorkPlanInfoViewCmd Class</p>
 * <p>Description : ������ ���赵 Ȯ����Ȳ �� ó�� Ŭ����</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : ������ ���赵 Ȯ����Ȳ �� ó���� �Ѵ�. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class StWorkPlanInfoViewCmd implements KJFCommand {
    
    UserEnt user;
    
    public StWorkPlanInfoViewCmd() {        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {        
        
        user = (UserEnt)request.getSession().getAttribute("user");
        
        // �α��� ���� üũ
        if (KJFUtil.isEmpty(user)) {
            throw new LoginException(request, "ȸ���� ������ �α��� üũ", "../member/login.do");
        }  
        
        // �˻����� ������ üũ
        StworkParam pm = checkPm(request, form);
        request.setAttribute("pm", pm);
        
        // ������ ���赵 Ȯ����Ȳ ���� 
        loadData(request, pm);
        
        return request.getParameter("cmd");
    }
    
    
    /**
     * ������ ���赵Ȯ�� ������ �����´�.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadData(HttpServletRequest request, StworkParam pm) throws Exception {
        
        ReportEntity rEntity = null;        
        
        ReportDAO rDAO = new ReportDAO();
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT                           \n");
        sbSQL.append("        PT_U.ORPE_NM,             \n");   // ������ ����
        sbSQL.append("        PT_U.ORPE_TEL,            \n");   // ������ ��ȭ��ȣ
        sbSQL.append("        PT_U.ORPE_POSTNUM,        \n");   // ������  �����ȣ
        sbSQL.append("        PT_U.ORPE_ADDR,           \n");   // ������ �ּ���
        sbSQL.append("        PT_U.ORPE_DETAILADDR,     \n");   // ������ �ּ��� ��
        
        sbSQL.append("        PT_U.PLANER_NM,           \n");   // ������ �̸�
        sbSQL.append("        PT_U.PLANER_NAME,         \n");   // ������ ��ȣ
        sbSQL.append("        PT_U.PLANER_TEL,          \n");   // ������ ����ó
        sbSQL.append("        PT_U.PLANER_POSTNUM,      \n");   // ������ �����ȣ
        sbSQL.append("        PT_U.PLANER_ADDR,         \n");   // ������ �ּ� 
        sbSQL.append("        PT_U.PLANER_DETAILADDR,   \n");   // ������ ���ּ�        
        
        sbSQL.append("        PT_U.RECV_DT,             \n");   // �������� ��������
        sbSQL.append("        PT_U.STRU_COMMIT_NUM,     \n");   // �������� �����㰡��ȣ
        sbSQL.append("        PT_C2.CODE_NAME AS USE,   \n");   // �������� �뵵
        sbSQL.append("        PT_U.WORK_ITEM,           \n");   // �������� ��������
        sbSQL.append("        PT_U.NUM_FL,              \n");   // �������� ����
        sbSQL.append("        PT_U.AREA,                \n");   // �������� ������
        sbSQL.append("        PT_U.STRU_ADDR_POSTNUM,   \n");   // �������� �����ȣ        
        sbSQL.append("        PT_U.STRU_ADDR_ADDR,      \n");   // �������� �ּ�
        sbSQL.append("        PT_U.STRU_ADDR_DETAILADDR, \n");   // �������� �ּ� ��
        
        sbSQL.append("        PT_U.SUV_APPL,             \n");   // �������� �ּ� ��
        
        sbSQL.append("        PT_C1.CODE_NAME AS SUV_APPL_NM    \n");   // �������� �ּ� ��
        
        sbSQL.append("   FROM PT_UB_CONSTRUCTION PT_U INNER JOIN PT_HOME_CONSTRUCTION PT_H    \n");      
        sbSQL.append("     ON PT_U.RECV_NUM = PT_H.RECV_NUM     \n");
        
        sbSQL.append("   LEFT JOIN (                                        \n");   
        sbSQL.append("              SELECT P_CODE, CODE, CODE_NAME          \n");   
        sbSQL.append("                FROM PT_COM_CODE                      \n"); 
        sbSQL.append("               WHERE P_CODE = 'SUVAPPL'               \n");   
        sbSQL.append("              ) PT_C1 ON PT_U.SUV_APPL = PT_C1.CODE   \n"); 
        
        sbSQL.append("   LEFT JOIN (                                        \n");   
        sbSQL.append("              SELECT P_CODE, CODE, CODE_NAME          \n");   
        sbSQL.append("                FROM PT_COM_CODE                      \n"); 
        sbSQL.append("               WHERE P_CODE = 'BLDDIV'                \n");   
        sbSQL.append("              ) PT_C2 ON PT_U.USE = PT_C2.CODE        \n");
        
        sbSQL.append("  WHERE PT_H.USER_ID = ?                  \n");
        sbSQL.append("    AND PT_H.RECV_NUM = ?                 \n");   // ������ȣ
        
        rDAO.setValue(i++, user.getUSER_ID());
        rDAO.setValue(i++, KJFUtil.print(pm.getRecv_num()));    

        rEntity = rDAO.select(sbSQL.toString());
        
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
    private StworkParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {

        StworkParam pm = (StworkParam)form;

        // �Ķ���� �����
        KJFLog.debug(pm.toString(request));
        
        // ����¡ ����
        if (KJFUtil.isEmpty(pm.getRowPerPage())) {            
            pm.setRowPerPage(Config.props.get("ROW_PER_PAGE"));
        }

        return pm;
    }
}
