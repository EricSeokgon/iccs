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
 * <p>Description : 회원 등록, 수정 삭제 처리 프로세스 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 *
 * NOTE : 회원 등록, 수정 삭제 처리를 한다.
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
        
        // 검색조건 설정및 체크
        MemParam pm = checkPm(request, form);
        
        // 개인회원 등록         
        if (cmd.equals("MemRegPriC" )) {
            insertPriMemExe(request, pm);   // 개인회원등록
    
         // 기업회원 등록
        } else if (cmd.equals("MemRegBusC")) {
            insertBusMemExe(request, pm);   // 기업회원등록
            
        // 회원정보 수정
        } else if (cmd.equals("MemInfoU")) {          
            
            if (KJFUtil.isEmpty(user)) {
                throw new LoginException(request, "회원전용 페이지 로그인 체크", "../member/login.do");
            }
            
            if (isPasswordCheck(request, pm)) {
                updateExe(request, pm);
            } else {
                throw new MsgException(request, "비밀번호가 일치하지 않습니다.");
            }
        
         // 회원 탈퇴
        } else if (cmd.equals("MemInfoD")) {     
            
            if (KJFUtil.isEmpty(user)) {
                throw new LoginException(request, "회원전용 페이지 로그인 체크", "../member/login.do");
            }
            
            deleteExe(request, pm);
            
        // 공인인증 재등록
        } else if (cmd.equals("MemCCReRegU")) {
            
            if (KJFUtil.isEmpty(user)) {
                throw new LoginException(request, "회원전용 페이지 로그인 체크", "../member/login.do");
            }
            
            updateCertificate(request, pm);
            
        // 비밀번호 변경
        } else if (cmd.equals("MemPasswdChangeU")) {
            
            if (KJFUtil.isEmpty(user)) {
                throw new LoginException(request, "회원전용 페이지 로그인 체크", "../member/login.do");
            }
            
            if (isPasswordCheck(request, pm)) {
                updatePassword(request, pm);  // 비밀번호 변경
            
            } else {
                throw new MsgException(request, "현재 비밀번호가 일치하지 않습니다.");
            }
            
            
        // 모바일 서비스 등록
        } else if (cmd.equals("MemMobileRegU")) {
            
            if (KJFUtil.isEmpty(user)) {
                throw new LoginException(request, "회원전용 페이지 로그인 체크", "../member/login.do");
            }
            
            updateMobileService(request, pm);   
        }
        
        request.setAttribute("pm", pm);
        
        return request.getParameter("cmd"); 
    }
    
    
    /**
     * 개인회원 등록
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
     * 기업회원 등록
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
     * 개인정보 수정
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
        sbSQL.append("        USER_POST_NUM = ?,        \n");   // 우편번호
        sbSQL.append("        USER_ADDR = ?,            \n");   // 주소
        sbSQL.append("        USER_ADDR_DETAIL = ?,     \n");   // 상세주소
        sbSQL.append("        USER_TEL1 = ?,            \n");   // 전화번호
        sbSQL.append("        USER_TEL2 = ?,            \n");   // 전화번호
        sbSQL.append("        USER_TEL3 = ?,            \n");   // 전화번호
        
        sbSQL.append("        USER_MOBILE1 = ?,         \n");   // 핸드폰번호
        sbSQL.append("        USER_MOBILE2 = ?,         \n");   // 핸드폰번호
        sbSQL.append("        USER_MOBILE3 = ?,         \n");   // 핸드폰번호
        
        sbSQL.append("        USER_EMAIL = ?,           \n");   // 이메일
        sbSQL.append("        SIDO_CODE = ?,            \n");   // 시도 코드
        sbSQL.append("        SIGUNGU_CODE = ?,         \n");   // 시군구 코드
        
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
        
        // 모바일 서비스 사용유무 검사
        if (!beforeMobile_num.equals(afterMobile_num)) {
            sbSQL.append("        USER_MOBILE_YN = ?,           \n");   // 모바일 사용유무
            rDAO.setValue(i++, "N");
        }
        
        if (user.getCAPITAL().equals("E") && com_reg_yn.equals("N")) {
        
            sbSQL.append("        COM_NAME = ?,             \n");   // 상호
            sbSQL.append("        OFFICE_POST_NUM = ?,      \n");   // 소재지 우편번호
            sbSQL.append("        OFFICE_ADDR = ?,          \n");   // 소재지 주소
            sbSQL.append("        OFFICE_ADDR_DETAIL = ?,   \n");   // 소재지 상세주소 
            sbSQL.append("        OFFICE_TEL = ?,           \n");   // 소재지 전화번호
            sbSQL.append("        OFFICE_FAX = ?,           \n");   // 소재지 팩스번호
            
            rDAO.setValue(i++, pm.getCom_name());
            rDAO.setValue(i++, pm.getOffice_post_num());
            rDAO.setValue(i++, pm.getOffice_addr());
            rDAO.setValue(i++, pm.getOffice_addr_detail());
            rDAO.setValue(i++, pm.getOffice_tel());
            rDAO.setValue(i++, pm.getOffice_fax());
            
        } else if (user.getCAPITAL().equals("E") && com_reg_yn.equals("Y")) {
            
            ReportEntity rEntity = loadCompanyInfo(request, pm);
            
            if (rEntity.getRowCnt() > 0) {
                
                sbSQL.append("        COM_NAME = ?,             \n");   // 상호
                sbSQL.append("        OFFICE_POST_NUM = ?,      \n");   // 소재지 우편번호
                sbSQL.append("        OFFICE_ADDR = ?,          \n");   // 소재지 주소
                sbSQL.append("        OFFICE_ADDR_DETAIL = ?,   \n");   // 소재지 상세주소 
                sbSQL.append("        OFFICE_TEL = ?,           \n");   // 소재지 전화번호
                sbSQL.append("        OFFICE_FAX = ?,           \n");   // 소재지 팩스번호
                
                rDAO.setValue(i++, rEntity.getValue(0, "NAME"));
                rDAO.setValue(i++, rEntity.getValue(0, "ADDR_POST_NUM"));
                rDAO.setValue(i++, rEntity.getValue(0, "ADDR_ADDR"));
                rDAO.setValue(i++, rEntity.getValue(0, "ADDR_DETAIL_ADDR"));
                rDAO.setValue(i++, rEntity.getValue(0, "ADDR_TEL_NUM"));
                rDAO.setValue(i++, rEntity.getValue(0, "ADDR_FAX_NUM"));
            }
        }
        
        sbSQL.append("        UPD_DT = ?                \n");   // 수정일
        sbSQL.append("  WHERE USER_ID = ?               \n");   // 아이디
        sbSQL.append("    AND USER_NAME = ?             \n");   // 사용자명
        sbSQL.append("    AND CAPITAL = ?               \n");   // 회원 구분
               
        rDAO.setValue(i++, KJFDate.getCurTime("yyyy-MM-dd HH:mm:ss"));
        rDAO.setValue(i++, user.getUSER_ID());
        rDAO.setValue(i++, user.getUSER_NAME());
        rDAO.setValue(i++, user.getCAPITAL());
        

        rDAO.execute(sbSQL.toString());
    }
    
    
    /**
     * 검색조건 및 초기데이타 로드
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
     * 검색조건 및 초기데이타 로드
     * 
     * @param HttpServletRequest
     * @return
     */
    private ReportEntity loadCompanyInfo(HttpServletRequest request, MemParam pm) throws Exception {
        
        ReportDAO    rDAO    = new ReportDAO();        
        ReportEntity rEntity = null;
        
        StringBuffer sbSQL = new StringBuffer();
        sbSQL.append(" SELECT NAME,                 \n");   // 상호
        sbSQL.append("        COI_WRT_NUM,          \n");   // 등록증 등록번호
        sbSQL.append("        ADDR_POST_NUM,        \n");   // 소재지 우편번호
        sbSQL.append("        ADDR_ADDR,            \n");   // 소재지 주소
        sbSQL.append("        ADDR_DETAIL_ADDR,     \n");   // 소재지 주소상세
        sbSQL.append("        ADDR_TEL_NUM,         \n");   // 소재지 전화번호      
        sbSQL.append("        ADDR_FAX_NUM          \n");   // 소재지 팩스번호           
        sbSQL.append("   FROM PT_R_COMPANY_MASTER   \n");
        sbSQL.append("  WHERE MANA_NUM = ?          \n");
        
        rDAO.setValue(1, pm.getCom_num());
        
        rEntity = rDAO.select(sbSQL.toString());
        
        return rEntity;
    }
   

   /**
    * 회원탈퇴시 회원관련 정보 삭제
    * 
    * @param request
    * @throws Exception
    */
    private void deleteExe(HttpServletRequest request, MemParam pm) throws Exception {
        
        deleteUserInfoExe(request, pm);     // 사용자 정보 삭제
        deleteConstructionExe(request, pm); // 착공전 정보 삭제
        deleteUseBeforeExe(request, pm);    // 사용전 정보 삭제
        deleteFavorite(request, pm);        // 자주가는 창고 삭제
    }
    
    
    /**
     * 회원 정보 삭제
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
     * 사용자 착공전등록 정보 삭제
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
      * 사용자 사용전검사등록 정보 삭제
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
       * 사용자 사용전검사등록 정보 삭제
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
     * 공인인증 재등록
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
      * 비밀번호 체크
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
      * 비밀번호 변경
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
      * 모바일 서비스 등록
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
              throw new MsgException("모바일 서비스 신청 등록을 실패하였습니다.\n\n 다시 신청해주십시오.");
          }
          
      }
    
    
    /**
     * 공인인증서 등록 프로세서
     * 
     * @param request
     * @param response
     * @throws Exception
     */
    private String CCRegProcess(HttpServletRequest request) throws  Exception {
        
        String user_dn    = KJFUtil.print(request.getParameter("user_dn"),"");     //DN값
        String user_cert = request.getParameter("user_cert");  // 인증서
                 
 /*
  *  	2010-01-27 임시 처리 회원가입 공인인증서 등록시 예외처리
  *  	NPKI 인증서가 75번 서버에만 있음 76번 접속자 일경우 인증서 에러 발생        
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
    	           	System.out.print("인증서 등록을 다시 하여 주십시오.");
    	           	throw new MsgException("인증서 등록을 다시 하여 주십시오.");
            }
        } else {
        
	        boolean boolCertChk = true;
	        String ErrMsg = "";
	        String ErrCode = "";
	        
	        System.out.print("인증서 : " + user_cert + "<br>");
	        System.out.print("인증서길이 : " + user_cert.length() + "<br>");
	     
	        int nRet;
	
	     
	        //  인증서 base64디코딩
	        byte[] DecUserCert = null;  // base64디코딩한 인증서
	        int DecUserCertLen = 0;     // base64디코딩한 인증서길이         
	       
	        
	        
	        Base64 CBase64 = new Base64();  
	      
	        // 인증서 base64인코딩
	        nRet = CBase64.Decode(user_cert.getBytes(), user_cert.getBytes().length);
	     
	        if (nRet == 0) {
	        	
	            //System.out.println("인증서 Base64 Decode 결과 : 성공<br>") ;
	            //out.println("인증서 길이 : " + CBase64.contentlen + "<br>") ;
	            DecUserCert = new byte[CBase64.contentlen];
	            System.arraycopy(CBase64.contentbuf, 0, DecUserCert, 0, CBase64.contentlen);
	            DecUserCertLen = CBase64.contentlen;
	            boolCertChk = true;
	
	            // 인증서 정보 추출 결과  
	            Certificate CCertificate = new Certificate();
	            nRet=CCertificate.ExtractCertInfo(DecUserCert, DecUserCertLen);
	                        
	            // 인증서 정보 추출 결과  출력
	            CertificateVerify.printCCertificateResult(CCertificate);                                                                              
	
	            // 인증서 검증
	            String policies = CertificateVerify.getPoliciesInfo();
	            
	            nRet = CCertificate.ValidateCert(DecUserCert, DecUserCertLen, policies, 1);
	          
	            if(nRet == 0) {   
	                
	                System.out.println("인증서 검증 결과 : 성공") ;
	                boolCertChk = true;
	
	                // ****************************************************************************************
	                // 인증서 검증 결과가 성공하면 사용자에 해당 테이블에 DN값을 추가하기 위해
	                // Update 문을 수행한다!@@@
	                // Update   사용자 테이블   set    DN="CCertificate.subject"    where   세션의 아이디
	                // 이런 형태로 사용자의 DN값을 등록해 주면 된다!@@@
	                // ****************************************************************************************
	                    
	            } else {
	                
	                System.out.println("인증서 검증 결과 : 실패") ;
	                System.out.println("에러내용 : " + CCertificate.errmessage);
	                System.out.println("에러코드 : " + CCertificate.errcode);
	                System.out.println("인증서 정책 : " + policies) ;
	                System.out.println("사용자 인증서 정책 : " + CCertificate.policy) ;
	                
	                ErrMsg  = "인증서 검증 결과 검증 실패 [ 에러내용 : " + CCertificate.errmessage + " ]";
	                ErrCode = "에러코드 [ " + CCertificate.errcode + " ]";
	                
	                boolCertChk = false;
	            }
	            
	        } else {
	            
	            System.out.println("인증서 Base64 Decode 결과 : 실패") ;
	            System.out.println("에러내용 : " + CBase64.errmessage);
	            System.out.println("에러코드 : " + CBase64.errcode);
	            
	            boolCertChk = false;
	        }
	
	        if (boolCertChk == false) {
	           	System.out.print(ErrMsg + "\n\n " + ErrCode);
	           	System.out.print("인증서 등록을 다시 하여 주십시오.");
	           	throw new MsgException("인증서 등록을 다시 하여 주십시오.");
	        } else {
	            return user_dn;
	        }  
        } 
    }
    
    
    /**
     * 검색조건 초기값 설정및 체크
     * 
     * @param request
     * @param form
     * @return BbsParam
     * @throws Exception
     */
    private MemParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {

        MemParam pm = (MemParam) form;

        // 파라미터 디버깅
        KJFLog.debug(pm.toString(request));

        return pm;
    }
}
