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
 * <p>Title       : PubWorkAffiliationCmd Class</p>
 * <p>Description : ����� �պ� Ŭ����</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : ����� �պ� ���� ó���� �Ѵ�. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class PubWorkAffiliationCmd implements KJFCommand {
    
	UserEnt user;
	
    public PubWorkAffiliationCmd() {        
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
        
        // ����� ������� 
        loadData(request, pm);
        
        return request.getParameter("cmd");
    }
    
    
    /**
     * ����� �պ� ������ �����´�.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadData(HttpServletRequest request, RegMgrParam pm) throws Exception {
    	
    	ReportEntity rEntity = null;        
        
        ReportDAO rDAO = new ReportDAO();    
        
        int i = 1;              
                
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT                               					\n");        
        sbSQL.append("        PT_U.RECV_NUM,                					\n");   // ������Ȳ ������ȣ
        sbSQL.append("        PT_U.RECV_DT,                 					\n");   // ������Ȳ ��������
        sbSQL.append("        PT_H.MOT_STE,                                     \n");   // ������Ȳ ������� �ڵ�
        sbSQL.append("        PT_C1.CODE_NAME AS MOT_STE_NM,                    \n");   // ������Ȳ ������� �ڵ��
        sbSQL.append("        PT_U.PROC_LIM,      								\n");   // ������Ȳ ó������
        sbSQL.append("        PT_M1.NAME AS ASC_NAME,      						\n");   // ������Ȳ �պ�ȸ���
        sbSQL.append("        PT_M2.NAME AS HANDED_NAME,  						\n");   // ������Ȳ ���պ�ȸ���
        sbSQL.append("        PT_U.ESTA_NAME,             						\n");   // ������Ȳ ���Ӽ���ȸ���
        
        sbSQL.append("        PT_M1.COI_WRT_NUM AS ASC_COI_WRT_NUM,          	\n");   // �պ���ü���� ��Ϲ�ȣ
        sbSQL.append("        PT_M1.REP_NM_KOR AS ASC_REP_NM_KOR,     			\n");   // �պ���ü���� ��ǥ��
        sbSQL.append("        PT_M1.ADDR_TEL_NUM AS ASC_ADDR_TEL_NUM,         	\n");   // �պ���ü���� ��ȭ��ȣ
        sbSQL.append("        PT_M1.MANA_NUM AS ASC_MANA_NUM,  					\n");   // �պ���ü���� ����ڹ�ȣ
        sbSQL.append("        PT_M1.ADDR_POST_NUM AS ASC_ADDR_POST_NUM,   		\n");   // �պ���ü���� �����ȣ
        sbSQL.append("        PT_M1.ADDR_ADDR AS ASC_ADDR_ADDR, 				\n");   // �պ���ü���� ���ּ�
        sbSQL.append("        PT_M1.ADDR_DETAIL_ADDR AS ASC_ADDR_DETAIL_ADDR,	\n");   // �պ���ü���� ���ּ� ��
        
        sbSQL.append("        PT_M2.COI_WRT_NUM AS HANDED_COI_WRT_NUM,          	\n");   // ���պ���ü���� ��Ϲ�ȣ
        sbSQL.append("        PT_M2.REP_NM_KOR AS HANDED_REP_NM_KOR, 				\n");   // ���պ���ü���� ��ǥ��
        sbSQL.append("        PT_M2.ADDR_TEL_NUM AS HANDED_ADDR_TEL_NUM,         	\n");   // ���պ���ü���� ��ȭ��ȣ
        sbSQL.append("        PT_M2.MANA_NUM AS HANDED_MANA_NUM,					\n");   // ���պ���ü���� ����ڹ�ȣ
        sbSQL.append("        PT_M2.ADDR_POST_NUM AS HANDED_ADDR_POST_NUM,  		\n");   // ���պ���ü���� �����ȣ
        sbSQL.append("        PT_M2.ADDR_ADDR AS HANDED_ADDR_ADDR,  				\n");   // ���պ���ü���� ���ּ�
        sbSQL.append("        PT_M2.ADDR_DETAIL_ADDR AS HANDED_ADDR_DETAIL_ADDR, 	\n");   // ���պ���ü���� ���ּ� ��
        
        sbSQL.append("        PT_U.ESTA_WRT_NUM,           						\n");   // �պ� �� ���� �Ǵ� ������ ���� ��Ϲ�ȣ
        sbSQL.append("        PT_U.ESTA_REP_NM_KOR, 							\n");   // �պ� �� ���� �Ǵ� ������ ���� ��ǥ��
        sbSQL.append("        PT_U.ESTA_ADDR_TEL_NUM,           				\n");   // �պ� �� ���� �Ǵ� ������ ���� ��ȭ��ȣ
        sbSQL.append("        PT_U.ESTA_MANA_NUM,								\n");   // �պ� �� ���� �Ǵ� ������ ���� ����ڹ�ȣ
        sbSQL.append("        PT_U.ESTA_ADDR_POST_NUM,  				        \n");   // �պ� �� ���� �Ǵ� ������ ���� �����ȣ
        sbSQL.append("        PT_U.ESTA_ADDR_ADDR,  							\n");   // �պ� �� ���� �Ǵ� ������ ���� ���ּ�
        sbSQL.append("        PT_U.ESTA_ADDR_DETAIL_ADDR 						\n");   // �պ� �� ���� �Ǵ� ������ ���� ���ּ� ��
        
        sbSQL.append("   FROM ( PT_R_UNION_STATEMENT PT_U INNER JOIN PT_R_BASIC_CHANGE_HISTORY PT_H     \n");
        sbSQL.append("   	    ON PT_U.RECV_NUM = PT_H.RECV_NUM )    				\n");
        
        sbSQL.append("   INNER JOIN PT_R_COMPANY_MASTER PT_M1               		\n");	// �պ���ü����
        sbSQL.append("           ON PT_M1.TMP_WRT_NUM = PT_U.ASC_TMP_WRT_NUM    	\n");
        
        sbSQL.append("   INNER JOIN PT_R_COMPANY_MASTER PT_M2               		\n");	// ���պ���ü����
        sbSQL.append("           ON PT_M2.TMP_WRT_NUM = PT_U.HANDED_TMP_WRT_NUM		\n");      
        
        // ������Ȳ ������� �ڵ� ��
        sbSQL.append("   LEFT JOIN (                                        \n");  
        sbSQL.append("              SELECT P_CODE, CODE, CODE_NAME          \n"); 
        sbSQL.append("                FROM PT_COM_CODE                      \n");   
        sbSQL.append("               WHERE P_CODE = 'REGPROC'               \n");   
        sbSQL.append("              ) PT_C1 ON PT_H.MOT_STE = PT_C1.CODE    \n");
      
        sbSQL.append("  WHERE (PT_M1.COI_WRT_NUM = ? AND PT_M1.MANA_NUM = ?)     	\n");   // �պ���ü����(�������Ϲ�ȣ, ����ڹ�ȣ)
        sbSQL.append("     OR (PT_M2.COI_WRT_NUM = ? AND PT_M2.MANA_NUM = ?)		\n");   // ���պ���ü����(�������Ϲ�ȣ, ����ڹ�ȣ)
        sbSQL.append("     OR (PT_U.ESTA_WRT_NUM = ? AND PT_U.ESTA_MANA_NUM = ?)	\n");   // �պ� �� ���� �Ǵ� ������ ����(�������Ϲ�ȣ, ����ڹ�ȣ)
        
        rDAO.setValue(i++, user.getREG_NUM());
        rDAO.setValue(i++, user.getCOM_NUM());   
        rDAO.setValue(i++, user.getREG_NUM());
        rDAO.setValue(i++, user.getCOM_NUM());
        rDAO.setValue(i++, user.getREG_NUM());
        rDAO.setValue(i++, user.getCOM_NUM());
        
        rEntity = rDAO.select(sbSQL.toString());

        /****** �˻����� �ʱⰪ ***********/
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
