package sp.mem.cmd;

import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFDate;
import kjf.util.KJFLog;
import kjf.util.KJFUtil;
import kjf.util.LoginException;
import kjf.util.MsgException;

import org.apache.struts.action.ActionForm;

import sp.mem.MemParam;
import sp.uent.UserEnt;
import sp.util.CertificateVerify;
import crosscert.Base64;
import crosscert.Certificate;

/****************************************************************************
 * <p>Title       : MemRegCUDCmd Class</p>
 * <p>Description : ȸ�� ���, ���� ���� ó�� ���μ��� Ŭ����</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 *
 * NOTE : ȸ�� ���, ���� ���� ó���� �Ѵ�.
 *
 * @version 1.0
 * @author  PKT
 ***************************************************************************/
public class MemRegCUDCmd implements KJFCommand {
    
    UserEnt user;
    
    public MemRegCUDCmd() {        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {    
               
        String cmd = request.getParameter("cmd");
        
        user = (UserEnt)request.getSession().getAttribute("user");
        
        // �˻����� ������ üũ
        MemParam pm = checkPm(request, form);
        
        // ����ȸ�� ���         
        if (cmd.equals("MemRegPriC" )) {
            insertPriMemExe(request, pm);   // ����ȸ�����
    
         // ���ȸ�� ���
        } else if (cmd.equals("MemRegBusC")) {
            insertBusMemExe(request, pm);   // ���ȸ�����
            
        // ȸ������ ����
        } else if (cmd.equals("MemInfoU")) {          
            
            if (KJFUtil.isEmpty(user)) {
                throw new LoginException(request, "ȸ������ ������ �α��� üũ", "../member/login.do");
            }
            
            if (isPasswordCheck(request, pm)) {
                updateExe(request, pm);
            } else {
                throw new MsgException(request, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
            }
        
         // ȸ�� Ż��
        } else if (cmd.equals("MemInfoD")) {     
            
            if (KJFUtil.isEmpty(user)) {
                throw new LoginException(request, "ȸ������ ������ �α��� üũ", "../member/login.do");
            }
            
            deleteExe(request, pm);
            
        // �������� ����
        } else if (cmd.equals("MemCCReRegU")) {
            
            if (KJFUtil.isEmpty(user)) {
                throw new LoginException(request, "ȸ������ ������ �α��� üũ", "../member/login.do");
            }
            
            updateCertificate(request, pm);
            
        // ��й�ȣ ����
        } else if (cmd.equals("MemPasswdChangeU")) {
            
            if (KJFUtil.isEmpty(user)) {
                throw new LoginException(request, "ȸ������ ������ �α��� üũ", "../member/login.do");
            }
            
            if (isPasswordCheck(request, pm)) {
                updatePassword(request, pm);  // ��й�ȣ ����
            
            } else {
                throw new MsgException(request, "���� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
            }
            
            
        // ����� ���� ���
        } else if (cmd.equals("MemMobileRegU")) {
            
            if (KJFUtil.isEmpty(user)) {
                throw new LoginException(request, "ȸ������ ������ �α��� üũ", "../member/login.do");
            }
            
            updateMobileService(request, pm);   
        }
        
        request.setAttribute("pm", pm);
        
        return request.getParameter("cmd"); 
    }
    
    
    /**
     * ����ȸ�� ���
     * 
     * @param request
     * @param pm
     * @throws Exception
     */
    private void insertPriMemExe(HttpServletRequest request, MemParam pm) throws Exception {
                
        ReportDAO rDAO = new ReportDAO();
        
        StringBuffer sbSQL = new StringBuffer(); 
        sbSQL.append(" INSERT INTO PT_HOM_USER (    \n");
        sbSQL.append("        USER_ID,               \n");
        sbSQL.append("        USER_NAME,             \n");
        sbSQL.append("        USER_PASSWD,           \n");
        sbSQL.append("        USER_SSN1,             \n");
        sbSQL.append("        USER_SSN2,             \n");
        
        sbSQL.append("        USER_POST_NUM,         \n");
        sbSQL.append("        USER_ADDR,             \n");
        sbSQL.append("        USER_ADDR_DETAIL,      \n");
        sbSQL.append("        USER_TEL1,              \n");
        sbSQL.append("        USER_TEL2,              \n");
        
        sbSQL.append("        USER_TEL3,              \n");        
        sbSQL.append("        USER_MOBILE1,           \n");
        sbSQL.append("        USER_MOBILE2,           \n");
        sbSQL.append("        USER_MOBILE3,           \n");
        sbSQL.append("        USER_MOBILE_YN,         \n");
        
        sbSQL.append("        USER_DN,               \n");
        sbSQL.append("        USER_EMAIL,            \n");        
        sbSQL.append("        COM_REG_YN,            \n");
        sbSQL.append("        COM_NAME,              \n");
        sbSQL.append("        COM_NUM,               \n");
        sbSQL.append("        REG_NUM,               \n");
        
        sbSQL.append("        OFFICE_POST_NUM,       \n");
        sbSQL.append("        OFFICE_ADDR,           \n");
        sbSQL.append("        OFFICE_ADDR_DETAIL,    \n");
        sbSQL.append("        OFFICE_TEL,            \n");
        sbSQL.append("        OFFICE_FAX,            \n");
        
        sbSQL.append("        SIDO_CODE,             \n");
        sbSQL.append("        SIGUNGU_CODE,          \n");
        sbSQL.append("        CAPITAL,               \n");
        sbSQL.append("        USE_YN,                \n");
        sbSQL.append("        INS_DT,                \n");
        sbSQL.append("        UPD_DT                \n");
        sbSQL.append("       )                      \n");
        sbSQL.append("   values (                   \n");
        sbSQL.append("            ?,?,?,?,?,        \n");
        sbSQL.append("            ?,?,?,?,?,        \n");
        sbSQL.append("            ?,?,?,?,?,        \n");
        sbSQL.append("            ?,?,?,?,?,?,      \n");
        sbSQL.append("            ?,?,?,?,?,        \n");
        sbSQL.append("            ?,?,?,?,?,?       \n");
        sbSQL.append("          )                   \n");   

        int i = 0;

        rDAO.setValue(++i, pm.getUser_id());
        rDAO.setValue(++i, pm.getUser_name());
        rDAO.setValue(++i, pm.getUser_passwd());
        rDAO.setValue(++i, pm.getUser_ssn1());
        rDAO.setValue(++i, pm.getUser_ssn2());
        
        rDAO.setValue(++i, pm.getUser_post_num());
        rDAO.setValue(++i, pm.getUser_addr());
        rDAO.setValue(++i, pm.getUser_addr_detail());
        rDAO.setValue(++i, pm.getUser_tel1());
        rDAO.setValue(++i, pm.getUser_tel2());
        
        rDAO.setValue(++i, pm.getUser_tel3());
        rDAO.setValue(++i, pm.getUser_mobile1());
        rDAO.setValue(++i, pm.getUser_mobile2());
        rDAO.setValue(++i, pm.getUser_mobile3());
        rDAO.setValue(++i, "N");
        
        rDAO.setValue(++i, CCRegProcess(request));
        rDAO.setValue(++i, pm.getUser_email());
        rDAO.setValue(++i, pm.getCom_reg_yn());
        rDAO.setValue(++i, pm.getCom_name());
        rDAO.setValue(++i, pm.getCom_num());
        rDAO.setValue(++i, pm.getReg_num());
        
        rDAO.setValue(++i, pm.getOffice_post_num());
        rDAO.setValue(++i, pm.getOffice_addr());
        rDAO.setValue(++i, pm.getOffice_addr_detail());
        rDAO.setValue(++i, pm.getOffice_tel());
        rDAO.setValue(++i, pm.getOffice_fax());
        
        rDAO.setValue(++i, pm.getSido_code());
        rDAO.setValue(++i, pm.getSigungu_code());
        rDAO.setValue(++i, "U");
        rDAO.setValue(++i, "Y");
        rDAO.setValue(++i, KJFDate.getCurTime("yyyy-MM-dd HH:mm:ss"));
        rDAO.setValue(++i, KJFDate.getCurTime("yyyy-MM-dd HH:mm:ss"));

        rDAO.execute(sbSQL.toString());
    }
    
    /**
     * ���ȸ�� ���
     * 
     * @param request
     * @param pm
     * @throws Exception
     */
    private void insertBusMemExe(HttpServletRequest request, MemParam pm) throws Exception {
        
        ReportDAO rDAO = new ReportDAO();
        
        StringBuffer sbSQL = new StringBuffer(); 
        sbSQL.append(" INSERT INTO PT_HOM_USER (    \n");
        sbSQL.append("        USER_ID,               \n");
        sbSQL.append("        USER_NAME,             \n");
        sbSQL.append("        USER_PASSWD,           \n");
        sbSQL.append("        USER_SSN1,             \n");
        sbSQL.append("        USER_SSN2,             \n");
        
        sbSQL.append("        USER_POST_NUM,         \n");
        sbSQL.append("        USER_ADDR,             \n");
        sbSQL.append("        USER_ADDR_DETAIL,      \n");
        sbSQL.append("        USER_TEL1,              \n");
        sbSQL.append("        USER_TEL2,              \n");
        
        sbSQL.append("        USER_TEL3,              \n");        
        sbSQL.append("        USER_MOBILE1,           \n");
        sbSQL.append("        USER_MOBILE2,           \n");
        sbSQL.append("        USER_MOBILE3,           \n");
        sbSQL.append("        USER_MOBILE_YN,         \n");
        
        sbSQL.append("        USER_DN,               \n");
        sbSQL.append("        USER_EMAIL,            \n");
        sbSQL.append("        COM_REG_YN,            \n");
        sbSQL.append("        COM_NAME,              \n");
        sbSQL.append("        COM_NUM,               \n");
        sbSQL.append("        REG_NUM,               \n");
        
        sbSQL.append("        OFFICE_POST_NUM,       \n");
        sbSQL.append("        OFFICE_ADDR,           \n");
        sbSQL.append("        OFFICE_ADDR_DETAIL,    \n");
        sbSQL.append("        OFFICE_TEL,            \n");
        sbSQL.append("        OFFICE_FAX,            \n");
        
        sbSQL.append("        SIDO_CODE,             \n");
        sbSQL.append("        SIGUNGU_CODE,          \n");
        sbSQL.append("        CAPITAL,               \n");
        sbSQL.append("        USE_YN,                \n");
        sbSQL.append("        INS_DT,                \n");
        sbSQL.append("        UPD_DT                \n");
        sbSQL.append("       )                      \n");
        sbSQL.append("   values (                   \n");
        sbSQL.append("            ?,?,?,?,?,        \n");
        sbSQL.append("            ?,?,?,?,?,        \n");
        sbSQL.append("            ?,?,?,?,?,        \n");
        sbSQL.append("            ?,?,?,?,?,?,      \n");
        sbSQL.append("            ?,?,?,?,?,        \n");
        sbSQL.append("            ?,?,?,?,?,?       \n");
        sbSQL.append("          )                   \n");   

        int i = 0;

        rDAO.setValue(++i, pm.getUser_id());
        rDAO.setValue(++i, pm.getUser_name());
        rDAO.setValue(++i, pm.getUser_passwd());
        rDAO.setValue(++i, pm.getUser_ssn1());
        rDAO.setValue(++i, pm.getUser_ssn2());
        
        rDAO.setValue(++i, pm.getUser_post_num());
        rDAO.setValue(++i, pm.getUser_addr());
        rDAO.setValue(++i, pm.getUser_addr_detail());
        rDAO.setValue(++i, pm.getUser_tel1());
        rDAO.setValue(++i, pm.getUser_tel2());
        
        rDAO.setValue(++i, pm.getUser_tel3());
        rDAO.setValue(++i, pm.getUser_mobile1());
        rDAO.setValue(++i, pm.getUser_mobile2());
        rDAO.setValue(++i, pm.getUser_mobile3());
        rDAO.setValue(++i, "N");
        
        rDAO.setValue(++i, CCRegProcess(request));
        rDAO.setValue(++i, pm.getUser_email());
        rDAO.setValue(++i, pm.getCom_reg_yn());
        rDAO.setValue(++i, pm.getCom_name());
        rDAO.setValue(++i, pm.getCom_num());
        rDAO.setValue(++i, pm.getReg_num());
        
        rDAO.setValue(++i, pm.getOffice_post_num());
        rDAO.setValue(++i, pm.getOffice_addr());
        rDAO.setValue(++i, pm.getOffice_addr_detail());
        rDAO.setValue(++i, pm.getOffice_tel());
        rDAO.setValue(++i, pm.getOffice_fax());
        
        rDAO.setValue(++i, pm.getSido_code());
        rDAO.setValue(++i, pm.getSigungu_code());
        rDAO.setValue(++i, "UE");
        rDAO.setValue(++i, "Y");
        rDAO.setValue(++i, KJFDate.getCurTime("yyyy-MM-dd HH:mm:ss"));
        rDAO.setValue(++i, KJFDate.getCurTime("yyyy-MM-dd HH:mm:ss"));

        rDAO.execute(sbSQL.toString());
    }


    /**
     * �������� ����
     * 
     * @param request
     * @param pm
     * @throws Exception
     */
    private void updateExe(HttpServletRequest request, MemParam pm) throws Exception {
        
        ReportDAO rDAO = new ReportDAO();
        
        String com_reg_yn = KJFUtil.print(request.getParameter("com_reg_yn"));
        
        int i = 1;
        
        String beforeMobile_num = loadMobileInfo(request, pm);
        String afterMobile_num  = pm.getUser_mobile1() + pm.getUser_mobile2() + pm.getUser_mobile3();
            
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" UPDATE PT_HOM_USER SET           \n");
        sbSQL.append("        USER_POST_NUM = ?,        \n");   // �����ȣ
        sbSQL.append("        USER_ADDR = ?,            \n");   // �ּ�
        sbSQL.append("        USER_ADDR_DETAIL = ?,     \n");   // ���ּ�
        sbSQL.append("        USER_TEL1 = ?,            \n");   // ��ȭ��ȣ
        sbSQL.append("        USER_TEL2 = ?,            \n");   // ��ȭ��ȣ
        sbSQL.append("        USER_TEL3 = ?,            \n");   // ��ȭ��ȣ
        
        sbSQL.append("        USER_MOBILE1 = ?,         \n");   // �ڵ�����ȣ
        sbSQL.append("        USER_MOBILE2 = ?,         \n");   // �ڵ�����ȣ
        sbSQL.append("        USER_MOBILE3 = ?,         \n");   // �ڵ�����ȣ
        
        sbSQL.append("        USER_EMAIL = ?,           \n");   // �̸���
        sbSQL.append("        SIDO_CODE = ?,            \n");   // �õ� �ڵ�
        sbSQL.append("        SIGUNGU_CODE = ?,         \n");   // �ñ��� �ڵ�
        
        rDAO.setValue(i++, pm.getUser_post_num());
        rDAO.setValue(i++, pm.getUser_addr());
        rDAO.setValue(i++, pm.getUser_addr_detail());
        rDAO.setValue(i++, pm.getUser_tel1());
        rDAO.setValue(i++, pm.getUser_tel2());
        rDAO.setValue(i++, pm.getUser_tel3());
        
        rDAO.setValue(i++, pm.getUser_mobile1());
        rDAO.setValue(i++, pm.getUser_mobile2());
        rDAO.setValue(i++, pm.getUser_mobile3());
        rDAO.setValue(i++, pm.getUser_email());
        rDAO.setValue(i++, pm.getSido_code());
        rDAO.setValue(i++, pm.getSigungu_code());
        
        // ����� ���� ������� �˻�
        if (!beforeMobile_num.equals(afterMobile_num)) {
            sbSQL.append("        USER_MOBILE_YN = ?,           \n");   // ����� �������
            rDAO.setValue(i++, "N");
        }
        
        if (user.getCAPITAL().equals("E") && com_reg_yn.equals("N")) {
        
            sbSQL.append("        COM_NAME = ?,             \n");   // ��ȣ
            sbSQL.append("        OFFICE_POST_NUM = ?,      \n");   // ������ �����ȣ
            sbSQL.append("        OFFICE_ADDR = ?,          \n");   // ������ �ּ�
            sbSQL.append("        OFFICE_ADDR_DETAIL = ?,   \n");   // ������ ���ּ� 
            sbSQL.append("        OFFICE_TEL = ?,           \n");   // ������ ��ȭ��ȣ
            sbSQL.append("        OFFICE_FAX = ?,           \n");   // ������ �ѽ���ȣ
            
            rDAO.setValue(i++, pm.getCom_name());
            rDAO.setValue(i++, pm.getOffice_post_num());
            rDAO.setValue(i++, pm.getOffice_addr());
            rDAO.setValue(i++, pm.getOffice_addr_detail());
            rDAO.setValue(i++, pm.getOffice_tel());
            rDAO.setValue(i++, pm.getOffice_fax());
            
        } else if (user.getCAPITAL().equals("E") && com_reg_yn.equals("Y")) {
            
            ReportEntity rEntity = loadCompanyInfo(request, pm);
            
            if (rEntity.getRowCnt() > 0) {
                
                sbSQL.append("        COM_NAME = ?,             \n");   // ��ȣ
                sbSQL.append("        OFFICE_POST_NUM = ?,      \n");   // ������ �����ȣ
                sbSQL.append("        OFFICE_ADDR = ?,          \n");   // ������ �ּ�
                sbSQL.append("        OFFICE_ADDR_DETAIL = ?,   \n");   // ������ ���ּ� 
                sbSQL.append("        OFFICE_TEL = ?,           \n");   // ������ ��ȭ��ȣ
                sbSQL.append("        OFFICE_FAX = ?,           \n");   // ������ �ѽ���ȣ
                
                rDAO.setValue(i++, rEntity.getValue(0, "NAME"));
                rDAO.setValue(i++, rEntity.getValue(0, "ADDR_POST_NUM"));
                rDAO.setValue(i++, rEntity.getValue(0, "ADDR_ADDR"));
                rDAO.setValue(i++, rEntity.getValue(0, "ADDR_DETAIL_ADDR"));
                rDAO.setValue(i++, rEntity.getValue(0, "ADDR_TEL_NUM"));
                rDAO.setValue(i++, rEntity.getValue(0, "ADDR_FAX_NUM"));
            }
        }
        
        sbSQL.append("        UPD_DT = ?                \n");   // ������
        sbSQL.append("  WHERE USER_ID = ?               \n");   // ���̵�
        sbSQL.append("    AND USER_NAME = ?             \n");   // ����ڸ�
        sbSQL.append("    AND CAPITAL = ?               \n");   // ȸ�� ����
               
        rDAO.setValue(i++, KJFDate.getCurTime("yyyy-MM-dd HH:mm:ss"));
        rDAO.setValue(i++, user.getUSER_ID());
        rDAO.setValue(i++, user.getUSER_NAME());
        rDAO.setValue(i++, user.getCAPITAL());
        

        rDAO.execute(sbSQL.toString());
    }
    
    
    /**
     * �˻����� �� �ʱⵥ��Ÿ �ε�
     * 
     * @param HttpServletRequest
     * @return
     */
    private String loadMobileInfo(HttpServletRequest request, MemParam pm) throws Exception {
        
        ReportDAO    rDAO    = new ReportDAO();        
        ReportEntity rEntity = null;
        
        String Mobile_Num = "";
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();
        sbSQL.append(" SELECT USER_MOBILE1,     \n");   
        sbSQL.append("        USER_MOBILE2,     \n");  
        sbSQL.append("        USER_MOBILE3      \n"); 
        sbSQL.append("   FROM PT_HOM_USER       \n");
        sbSQL.append("  WHERE USER_ID = ?       \n");
        sbSQL.append("    AND USER_NAME = ?     \n");
       
        rDAO.setValue(i++, user.getUSER_ID());
        rDAO.setValue(i++, user.getUSER_NAME());
        
        rEntity = rDAO.select(sbSQL.toString());
        
        if(rEntity.getRowCnt() > 0) {             
            Mobile_Num =  rEntity.getValue(0, "USER_MOBILE1") 
                            + rEntity.getValue(0, "USER_MOBILE2") 
                                + rEntity.getValue(0, "USER_MOBILE3");
        }
        
        return Mobile_Num;
    }
    
    
    /**
     * �˻����� �� �ʱⵥ��Ÿ �ε�
     * 
     * @param HttpServletRequest
     * @return
     */
    private ReportEntity loadCompanyInfo(HttpServletRequest request, MemParam pm) throws Exception {
        
        ReportDAO    rDAO    = new ReportDAO();        
        ReportEntity rEntity = null;
        
        StringBuffer sbSQL = new StringBuffer();
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
        
        return rEntity;
    }
   

   /**
    * ȸ��Ż��� ȸ������ ���� ����
    * 
    * @param request
    * @throws Exception
    */
    private void deleteExe(HttpServletRequest request, MemParam pm) throws Exception {
        
        deleteUserInfoExe(request, pm);     // ����� ���� ����
        deleteConstructionExe(request, pm); // ������ ���� ����
        deleteUseBeforeExe(request, pm);    // ����� ���� ����
        deleteFavorite(request, pm);        // ���ְ��� â�� ����
    }
    
    
    /**
     * ȸ�� ���� ����
     * 
     * @param request
     * @throws Exception
     */
     private void deleteUserInfoExe(HttpServletRequest request, MemParam pm) throws Exception {
         
         ReportDAO rDAO = new ReportDAO();
         
         int i = 1;
         
         StringBuffer sbSQL = new StringBuffer();        
         sbSQL.append(" UPDATE PT_HOM_USER       \n");
         sbSQL.append("    SET USE_YN = ?        \n");
         sbSQL.append("  WHERE USER_ID = ?       \n");
         sbSQL.append("    AND USER_NAME = ?     \n");
        
         rDAO.setValue(i++, "N");
         rDAO.setValue(i++, user.getUSER_ID());
         rDAO.setValue(i++, user.getUSER_NAME());

         rDAO.execute(sbSQL.toString());     
     }
    
    
    /**
     * ����� ��������� ���� ����
     * 
     * @param request
     * @throws Exception
     */
     private void deleteConstructionExe(HttpServletRequest request, MemParam pm) throws Exception {
         
         ReportDAO rDAO = new ReportDAO();
         
         int i = 1;
         
         StringBuffer sbSQL = new StringBuffer();        
         sbSQL.append("  DELETE                         \n");
         sbSQL.append("    FROM PT_HOME_CONSTRUCTION    \n");
         sbSQL.append("   WHERE USER_ID = ?             \n");
       
         rDAO.setValue(i++, user.getUSER_ID());

         rDAO.execute(sbSQL.toString());     
     }
     
     
     /**
      * ����� ������˻��� ���� ����
      * 
      * @param request
      * @throws Exception
      */
      private void deleteUseBeforeExe(HttpServletRequest request, MemParam pm) throws Exception {
          
          ReportDAO rDAO = new ReportDAO();
          
          int i = 1;
          
          StringBuffer sbSQL = new StringBuffer();        
          sbSQL.append("  DELETE                         \n");
          sbSQL.append("    FROM PT_HOME_USEBEFORE       \n");
          sbSQL.append("   WHERE USER_ID = ?             \n");
        
          rDAO.setValue(i++, user.getUSER_ID());

          rDAO.execute(sbSQL.toString());     
      }
      
      
      /**
       * ����� ������˻��� ���� ����
       * 
       * @param request
       * @throws Exception
       */
       private void deleteFavorite(HttpServletRequest request, MemParam pm) throws Exception {
           
           ReportDAO rDAO = new ReportDAO();
           
           int i = 1;
           
           StringBuffer sbSQL = new StringBuffer();        
           sbSQL.append("  DELETE                       \n");
           sbSQL.append("    FROM PT_HOM_FAVORITE       \n");
           sbSQL.append("   WHERE USER_ID = ?           \n");
         
           rDAO.setValue(i++, user.getUSER_ID());

           rDAO.execute(sbSQL.toString());     
       }
     
    
    /**
     * �������� ����
     * 
     * @param request
     * @throws Exception
     */
     private void updateCertificate(HttpServletRequest request, MemParam pm) throws Exception {
         
         ReportDAO rDAO = new ReportDAO();
         
         int i = 1;
         
         StringBuffer sbSQL = new StringBuffer();        
         sbSQL.append(" UPDATE PT_HOM_USER       \n");
         sbSQL.append("    SET USER_DN = ?       \n");
         sbSQL.append("  WHERE USER_ID = ?       \n");
         sbSQL.append("    AND USER_NAME = ?     \n");
        
         rDAO.setValue(i++, CCRegProcess(request));
         rDAO.setValue(i++, user.getUSER_ID());
         rDAO.setValue(i++, user.getUSER_NAME());

         rDAO.execute(sbSQL.toString());      
     }
     
     /**
      * ��й�ȣ üũ
      * 
      * @param request
      * @param pm
      * @return
      * @throws Exception
      */
     private boolean isPasswordCheck(HttpServletRequest request, MemParam pm) throws Exception {
         
         ReportDAO    rDAO    = new ReportDAO();        
         ReportEntity rEntity = null;
         
         int i = 1;
         
         StringBuffer sbSQL = new StringBuffer();
         sbSQL.append(" SELECT COUNT(USER_ID) AS CNT    \n"); 
         sbSQL.append("   FROM PT_HOM_USER              \n");
         sbSQL.append("  WHERE USER_ID = ?              \n");
         sbSQL.append("    AND USER_PASSWD = ?          \n");
         
         rDAO.setValue(i++, user.getUSER_ID());
         rDAO.setValue(i++, request.getParameter("cur_passwd"));
         
         rEntity = rDAO.select(sbSQL.toString());
         
         if(KJFUtil.str2int(rEntity.getValue(0, "CNT")) > 0) {             
             return true;
         }
         
         return false;
     }
          
     /**
      * ��й�ȣ ����
      * 
      * @param request
      * @throws Exception
      */
      private void updatePassword(HttpServletRequest request, MemParam pm) throws Exception {
          
          ReportDAO rDAO = new ReportDAO();
          
          int i = 1;
          
          StringBuffer sbSQL = new StringBuffer();        
          sbSQL.append(" UPDATE PT_HOM_USER       \n");
          sbSQL.append("    SET USER_PASSWD = ?   \n");
          sbSQL.append("  WHERE USER_ID = ?       \n");
          sbSQL.append("    AND USER_NAME = ?     \n");
          sbSQL.append("    AND USER_PASSWD = ?   \n");
          
         
          rDAO.setValue(i++, request.getParameter("new_passwd"));
          rDAO.setValue(i++, user.getUSER_ID());
          rDAO.setValue(i++, user.getUSER_NAME());
          rDAO.setValue(i++, request.getParameter("cur_passwd"));

          rDAO.execute(sbSQL.toString());      
      }
     
     
     /**
      * ����� ���� ���
      * 
      * @param request
      * @throws Exception
      */
      private void updateMobileService(HttpServletRequest request, MemParam pm) throws Exception {
          
          ReportDAO rDAO = new ReportDAO();
          
          String  validation_num_temp = KJFUtil.print(request.getParameter("validation_num_temp"));
          String validation_num       = KJFUtil.print(request.getParameter("validation_num"));
          
          int i = 1;
          
          if (validation_num_temp.equals(validation_num)) {
              
              StringBuffer sbSQL = new StringBuffer();        
              sbSQL.append(" UPDATE PT_HOM_USER         \n");
              sbSQL.append("    SET USER_MOBILE_YN = ?  \n");
              sbSQL.append("  WHERE USER_ID = ?         \n");
              sbSQL.append("    AND USER_NAME = ?       \n");
             
              rDAO.setValue(i++, "Y");
              rDAO.setValue(i++, user.getUSER_ID());
              rDAO.setValue(i++, user.getUSER_NAME());
              
              rDAO.execute(sbSQL.toString());
          } else {
              throw new MsgException("����� ���� ��û ����� �����Ͽ����ϴ�.\n\n �ٽ� ��û���ֽʽÿ�.");
          }
          
      }
    
    
    /**
     * ���������� ��� ���μ���
     * 
     * @param request
     * @param response
     * @throws Exception
     */
    private String CCRegProcess(HttpServletRequest request) throws  Exception {
        
        String user_dn    = KJFUtil.print(request.getParameter("user_dn"),"");     //DN��
        String user_cert = request.getParameter("user_cert");  // ������
                 
 /*
  *  	2010-01-27 �ӽ� ó�� ȸ������ ���������� ��Ͻ� ����ó��
  *  	NPKI �������� 75�� �������� ���� 76�� ������ �ϰ�� ������ ���� �߻�        
  */
        InetAddress inet = InetAddress.getLocalHost();
        if("99.1.5.76".equals(inet.getHostAddress())){
            if (user_dn.indexOf("cn=") >=0 && 
                	user_dn.indexOf("ou=") >= 0 && 
                	user_dn.indexOf("o=") >= 0 && 
                	user_dn.length() >= 10)
            {
                	return user_dn;
            } else {
    	           	System.out.print("������ ����� �ٽ� �Ͽ� �ֽʽÿ�.");
    	           	throw new MsgException("������ ����� �ٽ� �Ͽ� �ֽʽÿ�.");
            }
        } else {
        
	        boolean boolCertChk = true;
	        String ErrMsg = "";
	        String ErrCode = "";
	        
	        System.out.print("������ : " + user_cert + "<br>");
	        System.out.print("���������� : " + user_cert.length() + "<br>");
	     
	        int nRet;
	
	     
	        //  ������ base64���ڵ�
	        byte[] DecUserCert = null;  // base64���ڵ��� ������
	        int DecUserCertLen = 0;     // base64���ڵ��� ����������         
	       
	        
	        
	        Base64 CBase64 = new Base64();  
	      
	        // ������ base64���ڵ�
	        nRet = CBase64.Decode(user_cert.getBytes(), user_cert.getBytes().length);
	     
	        if (nRet == 0) {
	        	
	            //System.out.println("������ Base64 Decode ��� : ����<br>") ;
	            //out.println("������ ���� : " + CBase64.contentlen + "<br>") ;
	            DecUserCert = new byte[CBase64.contentlen];
	            System.arraycopy(CBase64.contentbuf, 0, DecUserCert, 0, CBase64.contentlen);
	            DecUserCertLen = CBase64.contentlen;
	            boolCertChk = true;
	
	            // ������ ���� ���� ���  
	            Certificate CCertificate = new Certificate();
	            nRet=CCertificate.ExtractCertInfo(DecUserCert, DecUserCertLen);
	                        
	            // ������ ���� ���� ���  ���
	            CertificateVerify.printCCertificateResult(CCertificate);                                                                              
	
	            // ������ ����
	            String policies = CertificateVerify.getPoliciesInfo();
	            
	            nRet = CCertificate.ValidateCert(DecUserCert, DecUserCertLen, policies, 1);
	          
	            if(nRet == 0) {   
	                
	                System.out.println("������ ���� ��� : ����") ;
	                boolCertChk = true;
	
	                // ****************************************************************************************
	                // ������ ���� ����� �����ϸ� ����ڿ� �ش� ���̺� DN���� �߰��ϱ� ����
	                // Update ���� �����Ѵ�!@@@
	                // Update   ����� ���̺�   set    DN="CCertificate.subject"    where   ������ ���̵�
	                // �̷� ���·� ������� DN���� ����� �ָ� �ȴ�!@@@
	                // ****************************************************************************************
	                    
	            } else {
	                
	                System.out.println("������ ���� ��� : ����") ;
	                System.out.println("�������� : " + CCertificate.errmessage);
	                System.out.println("�����ڵ� : " + CCertificate.errcode);
	                System.out.println("������ ��å : " + policies) ;
	                System.out.println("����� ������ ��å : " + CCertificate.policy) ;
	                
	                ErrMsg  = "������ ���� ��� ���� ���� [ �������� : " + CCertificate.errmessage + " ]";
	                ErrCode = "�����ڵ� [ " + CCertificate.errcode + " ]";
	                
	                boolCertChk = false;
	            }
	            
	        } else {
	            
	            System.out.println("������ Base64 Decode ��� : ����") ;
	            System.out.println("�������� : " + CBase64.errmessage);
	            System.out.println("�����ڵ� : " + CBase64.errcode);
	            
	            boolCertChk = false;
	        }
	
	        if (boolCertChk == false) {
	           	System.out.print(ErrMsg + "\n\n " + ErrCode);
	           	System.out.print("������ ����� �ٽ� �Ͽ� �ֽʽÿ�.");
	           	throw new MsgException("������ ����� �ٽ� �Ͽ� �ֽʽÿ�.");
	        } else {
	            return user_dn;
	        }  
        } 
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
