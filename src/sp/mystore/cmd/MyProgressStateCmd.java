package sp.mystore.cmd;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.cfg.Config;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFLog;
import kjf.util.KJFUtil;
import kjf.util.LoginException;

import org.apache.struts.action.ActionForm;

import sp.mystore.MystoreParam;
import sp.uent.UserEnt;

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
public class MyProgressStateCmd implements KJFCommand {
    
    UserEnt user;
    
    public MyProgressStateCmd() {
        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {        
        
        user = (UserEnt)request.getSession().getAttribute("user");
        
        // �α��� ���� üũ
        if (KJFUtil.isEmpty(user)) {
            throw new LoginException(request, "ȸ���� ������ �α��� üũ", "../member/login.do");
        }  
        
        // �˻����� ������ üũ
        MystoreParam pm = checkPm(request, form);
        request.setAttribute("pm", pm);
        
        // �ο��������  �������
        loadRegCountData(request, pm, "ALL"); // ��ü���� 
        loadRegCountData(request, pm, "REC"); // ��������
        loadRegCountData(request, pm, "COM"); // �Ϸ� ���� 
        
        // �ο�������� ������˻�
        loadUseBeforeData(request, pm);
        
        // �ο�������� �������˻�
        loadStworkData(request, pm);
        
        // �ο��������  ��������
        loadIllCountData(request, pm, "ALL"); // ��ü����
        loadIllCountData(request, pm, "REC"); // �������� 
        loadIllCountData(request, pm, "COM"); // �Ϸ� ����
        
        return request.getParameter("cmd");
    }
    
    
    /**
     * ���� �ο� ������� ��������� ������ �����´�.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadRegCountData(HttpServletRequest request, MystoreParam pm, String regDiv) throws Exception {
        
        ReportEntity rEntity = null;
        ReportDAO rDAO = new ReportDAO();      
        
        String regproc = "STD003";
        
        int i = 1;

        StringBuffer sbSQL = new StringBuffer();   
        sbSQL.append("SELECT                                                \n");
        sbSQL.append("       COUNT(PT_R_REGIST.RECV_NUM) AS REGIST_ALL_CNT, \n");
        sbSQL.append("       COUNT(PT_R_BASIC.RECV_NUM) AS BASIC_ALL_CNT,   \n");
        sbSQL.append("       COUNT(PT_R_ASSI.RECV_NUM) AS ASSI_ALL_CNT,     \n");
        sbSQL.append("       COUNT(PT_R_UNION.RECV_NUM) AS UNION_ALL_CNT    \n");

        // ����� ���泻��
        sbSQL.append("  FROM PT_R_BASIC_CHANGE_HISTORY PT_H                 \n");
        
        // �������
        sbSQL.append("  LEFT JOIN (                                         \n");
        sbSQL.append("              SELECT RECV_NUM                         \n");
        sbSQL.append("                FROM PT_R_REGIST_STATEMENT            \n");
        sbSQL.append("            ) PT_R_REGIST ON PT_H.RECV_NUM = PT_R_REGIST.RECV_NUM \n");
        sbSQL.append("            AND PT_H.MANA_NUM = ?                     \n");
        
        rDAO.setValue(i++, user.getCOM_NUM());
        
        // ������
        if (regDiv.equals("REC")) {
            sbSQL.append("            AND PT_H.MOT_STE != ?     \n");
            rDAO.setValue(i++, regproc);
            
        // ��ϿϷ�
        } else if (regDiv.equals("COM")) {
            sbSQL.append("            AND PT_H.MOT_STE = ?      \n");
            rDAO.setValue(i++, regproc);
        }
        
        // ���ؽŰ� 
        sbSQL.append("  LEFT JOIN (                                                     \n");
        sbSQL.append("              SELECT RECV_NUM                                     \n");
        sbSQL.append("              FROM PT_R_BASIC_STATEMENT PT_R_BASIC                \n");
        sbSQL.append("            ) PT_R_BASIC ON PT_H.RECV_NUM = PT_R_BASIC.RECV_NUM   \n");
        sbSQL.append("            AND PT_H.COI_WRT_NUM = ?                              \n");
        sbSQL.append("            AND PT_H.MANA_NUM = ?                                 \n");
        
        rDAO.setValue(i++, user.getREG_NUM());
        rDAO.setValue(i++, user.getCOM_NUM());
        
        // ������
        if (regDiv.equals("REC")) {
            sbSQL.append("            AND PT_H.MOT_STE != ?      \n");
            rDAO.setValue(i++, regproc);
            
        // ��ϿϷ�
        } else if (regDiv.equals("COM")) {
            sbSQL.append("            AND PT_H.MOT_STE = ?       \n");
            rDAO.setValue(i++, regproc);
        }
        
        // �絵���
        sbSQL.append("  LEFT JOIN (                                             \n");
        sbSQL.append("              SELECT RECV_NUM                             \n");
        sbSQL.append("              FROM PT_R_ASSI_TRANS_STATEMENT PT_R_ASSI INNER JOIN PT_R_COMPANY_MASTER PT_M \n");
        sbSQL.append("                ON PT_M.TMP_WRT_NUM = PT_R_ASSI.ASSI_TMP_WRT_NUM                  \n");
        sbSQL.append("             WHERE (PT_M.COI_WRT_NUM = ? AND PT_M.MANA_NUM = ?)                   \n");
        sbSQL.append("                OR TRAN_COMNUM = ?                                                \n");
        sbSQL.append("            ) PT_R_ASSI ON PT_H.RECV_NUM = PT_R_ASSI.RECV_NUM                     \n");
        
        rDAO.setValue(i++, user.getREG_NUM());
        rDAO.setValue(i++, user.getCOM_NUM());
        rDAO.setValue(i++, user.getCOM_NUM());
        
        // ������
        if (regDiv.equals("REC")) {
            sbSQL.append("            AND PT_H.MOT_STE != ?     \n");
            rDAO.setValue(i++, regproc);
            
        // ��ϿϷ�
        } else if (regDiv.equals("COM")) {
            sbSQL.append("            AND PT_H.MOT_STE = ?      \n");
            rDAO.setValue(i++, regproc);
        }
        
        // �պ�
        sbSQL.append("  LEFT JOIN (                                         \n");
        sbSQL.append("              SELECT RECV_NUM                         \n");
        sbSQL.append("               FROM PT_R_UNION_STATEMENT PT_U         \n");
        sbSQL.append("               INNER JOIN PT_R_COMPANY_MASTER PT_M1   \n");
        sbSQL.append("                       ON PT_M1.TMP_WRT_NUM = PT_U.ASC_TMP_WRT_NUM        \n");
        sbSQL.append("               INNER JOIN PT_R_COMPANY_MASTER PT_M2                       \n");
        sbSQL.append("                       ON PT_M2.TMP_WRT_NUM = PT_U.HANDED_TMP_WRT_NUM     \n");
        sbSQL.append("              WHERE (PT_M1.COI_WRT_NUM = ? AND PT_M1.MANA_NUM = ?)        \n");
        sbSQL.append("                 OR (PT_M2.COI_WRT_NUM = ? AND PT_M2.MANA_NUM = ?)        \n");
        sbSQL.append("                 OR (PT_U.ESTA_MANA_NUM = ?)                              \n");
        sbSQL.append("            ) PT_R_UNION ON PT_H.RECV_NUM = PT_R_ASSI.RECV_NUM            \n");
        
        rDAO.setValue(i++, user.getREG_NUM());
        rDAO.setValue(i++, user.getCOM_NUM());
        rDAO.setValue(i++, user.getREG_NUM());
        rDAO.setValue(i++, user.getCOM_NUM());
        rDAO.setValue(i++, user.getCOM_NUM());
        
        // ������
        if (regDiv.equals("REC")) {
            sbSQL.append("            AND PT_H.MOT_STE = ?                      \n");
            rDAO.setValue(i++, regproc);
            
        // ��ϿϷ�
        } else if (regDiv.equals("COM")) {
            sbSQL.append("            AND PT_H.MOT_STE != ?                     \n");
            rDAO.setValue(i++, regproc);
        }        
        
        rEntity = rDAO.select(sbSQL.toString());

        /****** �˻����� �ʱⰪ ***********/        
        if (regDiv.equals("REC")) {
            request.setAttribute("rRecEntity", rEntity);
            
        } else if (regDiv.equals("COM")) {
            request.setAttribute("rComEntity", rEntity);
            
        } else {
            request.setAttribute("rAllEntity", rEntity);
        }
        
    }
    
    /**
     * ���� �ο� ������� ���������赵Ȯ�� ������ �����´�.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadStworkData(HttpServletRequest request, MystoreParam pm) throws Exception {
        
        ReportEntity rEntity = null;
        ReportDAO rDAO = new ReportDAO();      
        
        String regproc = "3";  // �������
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();   
        sbSQL.append("SELECT                                             \n");
        sbSQL.append("        COUNT(PT_U_ALL.RECV_NUM) AS PT_U_ALL_CNT,  \n");
        sbSQL.append("        COUNT(PT_U_REC.RECV_NUM) AS PT_U_REC_CNT,  \n");
        sbSQL.append("        COUNT(PT_U_COM.RECV_NUM) AS PT_U_COM_CNT   \n");

        // ���������赵Ȯ��
        sbSQL.append("   FROM PT_HOME_CONSTRUCTION PT_H                 \n");
        
        // ========== ��ü : S ==========
        sbSQL.append("  LEFT JOIN PT_UB_CONSTRUCTION PT_U_ALL               \n");
        sbSQL.append("         ON PT_H.RECV_NUM = PT_U_ALL.RECV_NUM         \n");
        // ========== ��ü : E ==========
        
        // ========== ������ : S ==========
        sbSQL.append("  LEFT JOIN PT_UB_CONSTRUCTION PT_U_REC                  \n");
        sbSQL.append("         ON PT_H.RECV_NUM = PT_U_REC.RECV_NUM         \n"); 
        sbSQL.append("        AND PT_U_REC.PROC_STE != ?                    \n");
        
        rDAO.setValue(i++, regproc);
        // ========== ������ : E ==========
        
        // ========== �Ϸ� : S ==========
        sbSQL.append("  LEFT JOIN PT_UB_CONSTRUCTION PT_U_COM                  \n");
        sbSQL.append("         ON PT_H.RECV_NUM = PT_U_COM.RECV_NUM         \n");   
        sbSQL.append("        AND PT_U_COM.PROC_STE = ?                     \n");
        
        rDAO.setValue(i++, regproc);
        // ========== �Ϸ� : E ==========
        
        sbSQL.append("  WHERE PT_H.USER_ID = ?                              \n");

        rDAO.setValue(i++, user.getUSER_ID());

        rEntity = rDAO.select(sbSQL.toString());
      
        request.setAttribute("cEntity", rEntity);
    }
    
    
    /**
     * ���� �ο� ������� ������˻� ������ �����´�.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadUseBeforeData(HttpServletRequest request, MystoreParam pm) throws Exception {
        
        ReportEntity rEntity = null;
        ReportDAO rDAO = new ReportDAO();      
        
        String regproc = "3";  // �������
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();   
        sbSQL.append("SELECT                                            \n");
        sbSQL.append("        COUNT(PT_U_ALL.RECV_NUM) AS PT_U_ALL_CNT,  \n");
        sbSQL.append("        COUNT(PT_U_REC.RECV_NUM) AS PT_U_REC_CNT,  \n");
        sbSQL.append("        COUNT(PT_U_COM.RECV_NUM) AS PT_U_COM_CNT   \n");

        // ����ó�� ������
        sbSQL.append("   FROM PT_HOME_USEBEFORE PT_H \n");
        
        // ========== ��ü : S ==========
        sbSQL.append("  LEFT JOIN PT_UB_USEBEFORE PT_U_ALL                  \n");
        sbSQL.append("         ON PT_H.RECV_NUM = PT_U_ALL.RECV_NUM         \n");
        // ========== ��ü : E ==========
        
        // ========== ������ : S ==========
        sbSQL.append("  LEFT JOIN PT_UB_USEBEFORE PT_U_REC                  \n");
        sbSQL.append("         ON PT_H.RECV_NUM = PT_U_REC.RECV_NUM         \n"); 
        sbSQL.append("        AND PT_U_REC.PROC_STE != ?                    \n");
        
        rDAO.setValue(i++, regproc);
        // ========== ������ : E ==========
        
        // ========== �Ϸ� : S ==========
        sbSQL.append("  LEFT JOIN PT_UB_USEBEFORE PT_U_COM                  \n");
        sbSQL.append("         ON PT_H.RECV_NUM = PT_U_COM.RECV_NUM         \n");   
        sbSQL.append("        AND PT_U_COM.PROC_STE = ?                     \n");
        
        rDAO.setValue(i++, regproc);
        // ========== �Ϸ� : E ==========
        
        sbSQL.append("  WHERE PT_H.USER_ID = ?                              \n");

        rDAO.setValue(i++, user.getUSER_ID());

        rEntity = rDAO.select(sbSQL.toString());
      
        request.setAttribute("uEntity", rEntity);
    }
    
    
    /**
     * ���� �ο� ������� �������� ������ �����´�.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadIllCountData(HttpServletRequest request, MystoreParam pm, String regDiv) throws Exception {
        
        ReportEntity rEntity = null;
        ReportDAO rDAO = new ReportDAO();      
        
        String regproc = "C00003";  // �������
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();   
        sbSQL.append("SELECT                                                \n");
        sbSQL.append("        COUNT(PT_M_SUS.WRT_NUM) AS PT_M_SUS_ALL_CNT,  \n");
        sbSQL.append("        COUNT(PT_M_CAN.WRT_NUM) AS PT_M_CAN_ALL_CNT,  \n");
        sbSQL.append("        COUNT(PT_M_NEG.WRT_NUM) AS PT_M_NEG_ALL_CNT,  \n");
        sbSQL.append("        COUNT(PT_M_WAR.WRT_NUM) AS PT_M_WAR_ALL_CNT,  \n");
        sbSQL.append("        COUNT(PT_M_COR.WRT_NUM) AS PT_M_COR_ALL_CNT,  \n");
        sbSQL.append("        COUNT(PT_M_PRO.WRT_NUM) AS PT_M_PRO_ALL_CNT   \n");

        // ����ó�� ������
        sbSQL.append("   FROM PT_R_COMPANY_MASTER PT_C \n");
        
        // ========== �������� : S ==========
        sbSQL.append("  LEFT JOIN PT_M_MASTER PT_M_SUS                      \n");
        sbSQL.append("         ON PT_C.TMP_WRT_NUM = PT_M_SUS.TMP_WRT_NUM   \n");
        sbSQL.append("        AND PT_M_SUS.DISPO_CONT = 'M00002'            \n");        
        
        // ������
        if (regDiv.equals("REC")) {
            sbSQL.append("            AND PT_M_SUS.MOT_STE != ?     \n");
            rDAO.setValue(i++, regproc);
            
        // ��ϿϷ�
        } else if (regDiv.equals("COM")) {
            sbSQL.append("            AND PT_M_SUS.MOT_STE = ?      \n");
            rDAO.setValue(i++, regproc);
        }
        // ========== �������� : E ==========
        
        // ========== ������ : S ==========
        sbSQL.append("  LEFT JOIN PT_M_MASTER PT_M_CAN                      \n");
        sbSQL.append("         ON PT_C.TMP_WRT_NUM = PT_M_CAN.TMP_WRT_NUM   \n");
        sbSQL.append("        AND PT_M_CAN.DISPO_CONT = 'M00001'            \n");
        
        // ������
        if (regDiv.equals("REC")) {
            sbSQL.append("            AND PT_M_CAN.MOT_STE != ?      \n");
            rDAO.setValue(i++, regproc);
            
        // ��ϿϷ�
        } else if (regDiv.equals("COM")) {
            sbSQL.append("            AND PT_M_CAN.MOT_STE = ?       \n");
            rDAO.setValue(i++, regproc);
        }
        // ========== ������ : E ==========
        
        // ========== ���·�ΰ� : S ========== 
        sbSQL.append("  LEFT JOIN PT_M_MASTER PT_M_NEG                      \n");
        sbSQL.append("         ON PT_C.TMP_WRT_NUM = PT_M_NEG.TMP_WRT_NUM   \n");
        sbSQL.append("        AND PT_M_NEG.DISPO_CONT = 'M00003'            \n");
        
        // ������
        if (regDiv.equals("REC")) {
            sbSQL.append("            AND PT_M_NEG.MOT_STE != ?     \n");
            rDAO.setValue(i++, regproc);
            
        // ��ϿϷ�
        } else if (regDiv.equals("COM")) {
            sbSQL.append("            AND PT_M_NEG.MOT_STE = ?      \n");
            rDAO.setValue(i++, regproc);
        }
        // ========== ���·�ΰ� : E ========== 
        
        // ==========  �����ġ : S ========== 
        sbSQL.append("  LEFT JOIN PT_M_MASTER PT_M_WAR                      \n");
        sbSQL.append("         ON PT_C.TMP_WRT_NUM = PT_M_WAR.TMP_WRT_NUM   \n");
        sbSQL.append("        AND PT_M_WAR.DISPO_CONT = 'M00006'            \n");
        
        // ������
        if (regDiv.equals("REC")) {
            sbSQL.append("            AND PT_M_WAR.MOT_STE = ?      \n");
            rDAO.setValue(i++, regproc);
            
        // ��ϿϷ�
        } else if (regDiv.equals("COM")) {
            sbSQL.append("            AND PT_M_WAR.MOT_STE != ?     \n");
            rDAO.setValue(i++, regproc);
        }        
        // ==========  �����ġ : E ========== 
        
        // ========== ������� : S ========== 
        sbSQL.append("  LEFT JOIN PT_M_MASTER PT_M_COR                      \n");
        sbSQL.append("         ON PT_C.TMP_WRT_NUM = PT_M_COR.TMP_WRT_NUM   \n");
        sbSQL.append("        AND PT_M_COR.DISPO_CONT = 'M00004'            \n");
        
        // ������
        if (regDiv.equals("REC")) {
            sbSQL.append("            AND PT_M_COR.MOT_STE = ?      \n");
            rDAO.setValue(i++, regproc);
            
        // ��ϿϷ�
        } else if (regDiv.equals("COM")) {
            sbSQL.append("            AND PT_M_COR.MOT_STE != ?     \n");
            rDAO.setValue(i++, regproc);
        }    
        // ========== ������� : E ========== 
        
        // ========== ������ : S ========== 
        sbSQL.append("  LEFT JOIN PT_M_MASTER PT_M_PRO                      \n");
        sbSQL.append("         ON PT_C.TMP_WRT_NUM = PT_M_PRO.TMP_WRT_NUM   \n");
        sbSQL.append("        AND PT_M_PRO.DISPO_CONT = 'M00005'            \n");
        
        // ������
        if (regDiv.equals("REC")) {
            sbSQL.append("            AND PT_M_PRO.MOT_STE = ?      \n");
            rDAO.setValue(i++, regproc);
            
        // ��ϿϷ�
        } else if (regDiv.equals("COM")) {
            sbSQL.append("            AND PT_M_PRO.MOT_STE != ?     \n");
            rDAO.setValue(i++, regproc);
        }    
        // ========== ������ : E ========== 
        
        sbSQL.append("  WHERE PT_C.COI_WRT_NUM = ?  \n");
        sbSQL.append("    AND PT_C.MANA_NUM = ?     \n");

        rDAO.setValue(i++, user.getREG_NUM());
        rDAO.setValue(i++, user.getCOM_NUM());

        rEntity = rDAO.select(sbSQL.toString());

        /****** �˻����� �ʱⰪ ***********/        
        if (regDiv.equals("REC")) {
            request.setAttribute("mRecEntity", rEntity);
            
        } else if (regDiv.equals("COM")) {
            request.setAttribute("mComEntity", rEntity);
            
        } else {
            request.setAttribute("mAllEntity", rEntity);
        }        
    }
    
   
    /**
     * �� üũ �޼ҵ�
     * 
     * @param request
     * @param form
     * @return
     * @throws Exception
     */
    private MystoreParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {

        MystoreParam pm = (MystoreParam)form;

        // �Ķ���� �����
        KJFLog.debug(pm.toString(request));
        
        // ����¡ ����
        if (KJFUtil.isEmpty(pm.getRowPerPage())) {            
            pm.setRowPerPage(Config.props.get("ROW_PER_PAGE"));
        }

        return pm;
    }
}
