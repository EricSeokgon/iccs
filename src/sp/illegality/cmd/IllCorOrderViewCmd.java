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
 * <p>Title       : IllCorrectOrderCmd Class</p>
 * <p>Description : ������� �� Ŭ����</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : ������� �� ���� ó���� �Ѵ�. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class IllCorOrderViewCmd implements KJFCommand {
        
    public IllCorOrderViewCmd() {        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {        
        
        UserEnt user = (UserEnt)request.getSession().getAttribute("user");
        
        // �α��� ���� üũ
        if (KJFUtil.isEmpty(user)) {
            throw new LoginException(request, "ȸ���� ������ �α��� üũ", "../member/login.do");
        }  
        
        // �˻����� ������ üũ
        IllegalityParam pm = checkPm(request, form);
        request.setAttribute("pm", pm);
        
        // ������� ��� ���� 
        loadData(request, pm);
        
        // ������� ������  ���� 
        loadReportData(request, pm);
        
        return request.getParameter("cmd");
    }
    
    
    /**
     * ������� �� ������ �����´�.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadData(HttpServletRequest request, IllegalityParam pm) throws Exception {
        
        ReportEntity rEntity = null;        
        
        ReportDAO rDAO = new ReportDAO();      
        
        String tmp_wrt_num = KJFUtil.print(request.getParameter("tmp_wrt_num"));
        String wrt_num     = KJFUtil.print(request.getParameter("wrt_num"));
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT                                   \n");        
        sbSQL.append("        PT_CO.CORRECT_ORDER_START_DT,     \n");   // ������� ��������
        sbSQL.append("        PT_CO.CORRECT_ORDER_END_DT,       \n");   // ������� ��������
        sbSQL.append("        PT_CO.CORRECT_ORDER_CONT,         \n");   // ������� ����
        sbSQL.append("        PT_M.DISPO_DT,                    \n");   // ����ó�� ����
        sbSQL.append("        PT_M.DISPO_CAUSE,                 \n");   // ����ó�л���
        sbSQL.append("        PT_M.LEG_BAS                      \n");   // �����ٰ�
        
        sbSQL.append("   FROM (PT_M_MASTER PT_M INNER JOIN PT_M_CORRECT PT_CO   \n");
        sbSQL.append("     ON RTRIM(PT_M.TMP_WRT_NUM) = PT_CO.TMP_WRT_NUM       \n");
        sbSQL.append("    AND PT_M.WRT_NUM = PT_CO.WRT_NUM)                     \n");        
      
        sbSQL.append("  WHERE PT_M.TMP_WRT_NUM = ?  \n");   // ����Ϲ�ȣ  
        sbSQL.append("    AND PT_M.WRT_NUM     = ?  \n");   // ��Ϲ�ȣ  
        
        rDAO.setValue(i++, tmp_wrt_num);
        rDAO.setValue(i++, wrt_num);
        
        
        rEntity = rDAO.select(sbSQL.toString());

        /****** �˻����� �ʱⰪ ***********/
        request.setAttribute("rEntity", rEntity);
    }
    
    
    /**
     * ������� ������ ������ �����´�.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadReportData(HttpServletRequest request, IllegalityParam pm) throws Exception {
        
        ReportEntity rEntity = null;        
        
        ReportDAO rDAO = new ReportDAO();      
        
        String tmp_wrt_num = KJFUtil.print(request.getParameter("tmp_wrt_num"));
        String wrt_num     = KJFUtil.print(request.getParameter("wrt_num"));
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT                                           \n");        
        sbSQL.append("        PT_R.NOTE_CLASS_CODE,                     \n");   // �뺸����
        sbSQL.append("        PT_C1.CODE_NAME AS NOTE_CLASS_CODE_NM ,   \n");   // �뺸����
        sbSQL.append("        PT_R.SEND_DT,                             \n");   // �뺸����
        sbSQL.append("        PT_R.PROC_LIM,                            \n");   // ó������   
        
        // �뺸����
        sbSQL.append("        DECODE(PT_R.RECV_YN, 'Y', '����',         \n");   
        sbSQL.append("                             'N', '�ݼ�',         \n");  
        sbSQL.append("                             '') AS RECV_YN       \n");
        
        sbSQL.append("   FROM PT_M_BEF_REPORT PT_R                      \n");      
        
        sbSQL.append("   LEFT JOIN (                                    \n");  
        sbSQL.append("              SELECT P_CODE, CODE, CODE_NAME      \n"); 
        sbSQL.append("                FROM PT_COM_CODE                  \n");   
        sbSQL.append("               WHERE P_CODE = 'NOTICLASS'         \n");   
        sbSQL.append("              ) PT_C1 ON PT_R.NOTE_CLASS_CODE = PT_C1.CODE    \n");  
        
        sbSQL.append("  WHERE TMP_WRT_NUM = ?   \n");   // ����Ϲ�ȣ  
        sbSQL.append("    AND WRT_NUM     = ?   \n");   // ��Ϲ�ȣ
        
        rDAO.setValue(i++, tmp_wrt_num);
        rDAO.setValue(i++, wrt_num);
        
        rEntity = rDAO.select(sbSQL.toString());

        /****** �˻����� �ʱⰪ ***********/
        request.setAttribute("reportEntity", rEntity);
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
