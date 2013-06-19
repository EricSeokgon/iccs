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
 * <p>Title       : StWorkPlanInfoCmd Class</p>
 * <p>Description : ������ ���赵 Ȯ����Ȳ Ŭ����</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : ������ ���赵 Ȯ����Ȳ ����Ʈ ó���� �Ѵ�. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class StWorkPlanInfoListCmd implements KJFCommand {
    
	UserEnt user;
	
    public StWorkPlanInfoListCmd() {        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {        
        
        user = (UserEnt)request.getSession().getAttribute("user");
        
        // �α��� ���� üũ
        if (KJFUtil.isEmpty(user)) {
            throw new LoginException(request, "ȸ���� ������ �α��� üũ", "../member/login.do");
        }  
        
        // �˻����� ������ üũ
        StworkParam pm = checkPm(request, form);
        
        // ������ ���赵 Ȯ�� �˻� ���� 
        loadRecData(request, pm);
        
        // ������ ���赵 Ȯ����Ȳ ���� 
        loadData(request, pm);
        
        return request.getParameter("cmd");
    }
    
    /**
     * ������ ���赵 ������ �����´�.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadRecData(HttpServletRequest request, StworkParam pm) throws Exception {
        
        ReportEntity rEntity = null;        
        
        ReportDAO rDAO = new ReportDAO();      
        
        String scCode     = KJFUtil.print(pm.getScCode());      // ����
        String scStru_Num = KJFUtil.print(pm.getScStru_Num());  // ������ȣ
        
        if (KJFUtil.isEmpty(scStru_Num)) {
            return;
        }
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT                       \n");
        sbSQL.append("        CIV_RECV_NUM,         \n");   // �ý��� ������ȣ
        sbSQL.append("        RECV_NUM,             \n");   // ����� ������ȣ
        sbSQL.append("        ORPE_NM,              \n");   // ������
        sbSQL.append("        PLANER_NAME,          \n");   // ������ ��ȣ
        sbSQL.append("        PLANER_NM,            \n");   // ������
        sbSQL.append("        STRU_COMMIT_NUM,      \n");   // �����㰡��ȣ
        sbSQL.append("        RECV_DT,              \n");   // ��������
        
        // �������
        sbSQL.append("        DECODE(PROC_STE, '1', '�űԵ��',        \n");   
        sbSQL.append("                         '2', 'ó����',          \n");  
        sbSQL.append("                         '3', 'ó���Ϸ�',        \n");  
        sbSQL.append("                         '') AS PROC_STE         \n");
        
        sbSQL.append("   FROM PT_UB_CONSTRUCTION    \n");         
        sbSQL.append("  WHERE STRU_COMMIT_NUM = ?   \n");  
        sbSQL.append("    AND SIDO_CODE = ?         \n");
        //sbSQL.append("    AND SIGUNGU_CODE = ?      \n");
        
        rDAO.setValue(i++, scStru_Num);
        rDAO.setValue(i++, user.getSIDO_CODE());
       // rDAO.setValue(i++, user.getSIGUNGU_CODE());
        
        if (scCode.equals("001")) {         // ������
            
            sbSQL.append("    AND (ORPE_NM = ? OR ORPE_NM = ?)    \n");
            
            rDAO.setValue(i++, user.getUSER_NAME());
            rDAO.setValue(i++, user.getCOM_NAME());
            
        } else if (scCode.equals("002")) {  // ������
            
            sbSQL.append("    AND (PLANER_NM = ? OR PLANER_NM = ?)    \n");
            
            rDAO.setValue(i++, user.getUSER_NAME());
            rDAO.setValue(i++, user.getCOM_NAME());
        }
        
        rEntity = rDAO.select(sbSQL.toString());
        
        request.setAttribute("recEntity", rEntity);
    }
    
    
    /**
     * ������ ���赵 Ȯ����Ȳ ����Ʈ ������ �����´�.
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
        sbSQL.append(" SELECT                                   \n");
        sbSQL.append("        PT_U.RECV_NUM,                    \n");   // �ý��� ������ȣ
        sbSQL.append("        PT_U.CIV_RECV_NUM,                \n");   // ����� ������ȣ
        sbSQL.append("        PT_U.ORPE_NM,                     \n");   // ������
        sbSQL.append("        PT_U.PLANER_NM,                   \n");   // ������
        sbSQL.append("        PT_U.STRU_COMMIT_NUM,             \n");   // ���๰�㰡��ȣ
        sbSQL.append("        PT_U.RECV_DT,                     \n");   // ��������     
        
        // �������
        sbSQL.append("        DECODE(PT_U.PROC_STE, '1', '�űԵ��',        \n");   
        sbSQL.append("                              '2', 'ó����',          \n");  
        sbSQL.append("                              '3', 'ó���Ϸ�',        \n");  
        sbSQL.append("                                   '') AS PROC_STE    \n");
        
        sbSQL.append("   FROM PT_UB_CONSTRUCTION PT_U INNER JOIN PT_HOME_CONSTRUCTION PT_H    \n");      
        sbSQL.append("     ON PT_U.RECV_NUM = PT_H.RECV_NUM     \n");
        sbSQL.append("  WHERE PT_H.USER_ID = ?                  \n");
        sbSQL.append("  ORDER BY PT_U.CIV_RECV_NUM              \n");   // ������ȣ
        
        rDAO.setValue(i++, user.getUSER_ID());
        
        /* ************************** ����¡ ���� START **************************/
        StringBuffer sbCntSQL = new StringBuffer();
        sbCntSQL.append(" SELECT COUNT(*)  CNT      \n");
        sbCntSQL.append("   FROM PT_UB_CONSTRUCTION PT_U INNER JOIN PT_HOME_CONSTRUCTION PT_H  \n");      
        sbCntSQL.append("     ON PT_U.RECV_NUM = PT_H.RECV_NUM      \n");
        sbCntSQL.append("  WHERE PT_H.USER_ID = ?                   \n");
        
        //��ü ��� ��
        String totalCount="";

        //�������� ��� ��
        int rowPerPage = KJFUtil.str2int(pm.getRowPerPage());

        //���� ������ ��ȣ
        int nowPage = 1;
        nowPage = KJFUtil.isEmpty(pm.getNowPage()) ? 1 : Integer.parseInt(pm.getNowPage());

        rEntity = rDAO.select(sbCntSQL.toString());
        
        totalCount = rEntity.getValue(0, "CNT");
        
        if (rowPerPage == 0) rowPerPage = Integer.parseInt(totalCount);//�߰�
        if ((rowPerPage*nowPage) - Integer.parseInt(totalCount) > rowPerPage) nowPage = 1;

        pm.setTotalCount(totalCount);
        pm.setNowPage(String.valueOf(nowPage));
        /* *************************** ����¡ ����  END **************************/

        rEntity = rDAO.select(sbSQL.toString(), nowPage, rowPerPage);
        
        /****** �˻����� �ʱⰪ ***********/
        request.setAttribute("pm", pm);
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
