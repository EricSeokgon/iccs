package sp.login;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kjf.cfg.Config;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFDBUtil;
import kjf.util.KJFDate;
import kjf.util.KJFLog;
import kjf.util.KJFSec;
import kjf.util.KJFUtil;
import kjf.util.MsgException;
import sp.uent.UserEnt;
import sp.util.CertificateVerify;

import sp.util.Crypto;

import crosscert.Base64;
import crosscert.Certificate;



/**
 * login 모듈의 공통 함수를 정의
 * 
 * @author PKT
 * @version 1.0 2006/10/25
 */
public class LoginWorker {
    /************************************************************************
    * GPKI 로그인 체크
    * 
    * @param   HttpServletRequest request, String strID, String strPass
    * @return   void
    ***********************************************************************/
	public  ReportEntity chekGPKILogin(HttpServletRequest request) throws  Exception {
		
		String OFFI_DN =  request.getParameter("OFFI_DN");
		
		if(KJFUtil.isEmpty(OFFI_DN)) return null;
		
        ReportDAO    rDAO    = new ReportDAO();
        ReportEntity rEntity = null;        
        
        StringBuffer sbSQL = new StringBuffer();
        sbSQL.append(" SELECT                   \n");
        sbSQL.append("        OFFI_ID,          \n");
        sbSQL.append("        PASS             \n");
        sbSQL.append("   FROM PT_MI_USER        \n"); 
        sbSQL.append("  WHERE OFFI_DN = ?       \n");

        rDAO.setValue(1, OFFI_DN);
        
        rEntity = rDAO.select(sbSQL.toString());
        
        if(rEntity.getRowCnt()< 1)  return null;
        
        return rEntity;
		
	}
    
    /************************************************************************
    * 로그인 프로세서
    * 
    * @param   String strID, String strPass
    * @return   boolean
    ***********************************************************************/
    public boolean LoginProcess(HttpServletRequest request, HttpServletResponse response) throws  Exception {

        String    strID   = KJFUtil.print(request.getParameter("id"));
        String strPass = KJFUtil.print(request.getParameter("password"));
        
      
        //사용전 검사 때문에 공무원이 GPKI로 로그인이 되었는지 체크
        ReportEntity rEntity = chekGPKILogin( request);
        if(rEntity != null){
            strID = rEntity.getValue(0, "OFFI_ID");
            strPass = rEntity.getValue(0, "PASS");
        }
       
               
        UserEnt user;
 
        if (isloginAdmin(strID, strPass)) {
            user = new UserEnt(strID, "admin"); 
        
        } else if (isloginPublic(strID,strPass)){
			// 2009-10-05 추가 공무원용 테이블이 pt_mi_user 사용함으로 추가를 함        	
        	user = new UserEnt(strID, "public");
        	//사용전 검사용 으로 추가
        	if(rEntity != null ){
        		user.setUseUbSys(true); //사용전 검사시스템  사용
        	}
        	
        	
        } else if(isloginUser(strID, strPass)) {   
            user = new UserEnt(strID, "user");     
            
        } else {
            return false;
        }       
        
        // 접속로그 저장
        if(!strID.equals(Config.props.get("SYS_ID"))){
            user.setLogNum(accessLogInsert(request,strID));
        }
        
        request.getSession().setAttribute("user", user);

        // 쿠키와 함께 이용..
        if(Config.props.getBoolean("IS_USE_COOKIE") ) {
            UsingCookie(response,  user );
        }
        
        return true;
    }
    
    /**************************************************************************
     * 사용자 로그인 체크 메소드 
     * 
     * @param strID
     * @return
     * @throws Exception
     **************************************************************************/
    public boolean isInUserID(String strID) throws  Exception {
    	
    	KJFLog.log("isInUserID");
      	
        boolean result = false;

        ReportDAO rDAO       = new ReportDAO();
        ReportEntity rEntity = null;
        
        StringBuffer sbSQL = new StringBuffer();
        sbSQL.append(" SELECT COUNT(*) CNT  \n");
        sbSQL.append("   FROM PT_HOM_USER   \n");
        sbSQL.append("  WHERE USER_ID = ?   \n");
        sbSQL.append("    AND USE_YN = ?    \n");
        
        rDAO.setValue(1, strID);
        rDAO.setValue(2, "Y");
        
        rEntity = rDAO.select(sbSQL.toString());
         
 		if (KJFUtil.str2int(rEntity.getValue(0, "CNT")) > 0) {
			result = true;	
		}
				 
        return result;
    }
    
    
    /**************************************************************************
     * 관리자 로그인 체크 메소드 
     * 
     * @param strID
     * @return
     * @throws Exception
     *************************************************************************/
    public boolean isInAdminID(String strID) throws  Exception {
    	
    	KJFLog.log("isInAdminID");
    	
        boolean result = false;

        ReportDAO rDAO       = new ReportDAO();
        ReportEntity rEntity = null;

        StringBuffer sbSQL = new StringBuffer();
        sbSQL.append(" SELECT COUNT(*) CNT  \n");
        sbSQL.append("   FROM PT_MI_USER    \n");
        sbSQL.append("  WHERE USER_ID = ?   \n");
        sbSQL.append("    AND USE_YN = ?    \n");
        
        rDAO.setValue(1, strID);
        rDAO.setValue(2, "Y");
        
        rEntity = rDAO.select(sbSQL.toString());
         
 		if (KJFUtil.str2int(rEntity.getValue(0, "CNT")) > 0){
			result = true;	
		}
 		
		if ((strID.equals(Config.props.get("SYS_ID")))) {
			result = true;
		}
		
        return result;
    }
    
    
   /**************************************************************************
    * 로그인 사용자 체크 메소드 
    * 
    * @param strID
    * @param strPass
    * @return
    * @throws Exception
    *************************************************************************/
    public boolean isloginUser(String strID, String strPass ) throws  Exception {
    	
    	KJFLog.log("isloginUser");
    	
		boolean result = false;
        
	    ReportDAO rDAO       = new ReportDAO();
	    ReportEntity rEntity = null;
        
	    StringBuffer sbSQL = new StringBuffer();
	    
        sbSQL.append(" SELECT COUNT(*) CNT      \n");
        sbSQL.append("   FROM PT_HOM_USER       \n");
        sbSQL.append("  WHERE USER_ID = ?       \n");
        sbSQL.append("    AND USER_PASSWD = ?   \n");
        sbSQL.append("    AND USE_YN = ?        \n");
		
		rDAO.setValue(1, strID.toString());
		rDAO.setValue(2, strPass.toString());
		rDAO.setValue(3, "Y");
		

		rEntity = rDAO.select(sbSQL.toString());

		if (KJFUtil.str2int(rEntity.getValue(0,"CNT")) > 0
                 || (strID.equals(Config.props.get("SYS_ID"))
                         &&  strPass.equals(Config.props.get("SYS_PASS")))
                     ) {
             result = true;
        }
		
	    return result;
    }
    
    
    /**************************************************************************
     * 관리자 로그인 처리 메소드 
     * 
     * @param strID
     * @param strPass
     * @return
     * @throws Exception
     *************************************************************************/
    public boolean isloginAdmin(String strID, String strPass) throws  Exception {
    	
    	KJFLog.log("isloginAdmin START");
    	        
	    ReportDAO rDAO       = new ReportDAO();
	    ReportEntity rEntity = null;
        
	    StringBuffer sbSQL = new StringBuffer();
        sbSQL.append(" SELECT COUNT(*) CNT      \n");
        sbSQL.append("   FROM PT_MI_USER        \n");
        sbSQL.append("  WHERE HOME_GROUP ='A' AND OFFI_ID = ?       \n");
        sbSQL.append("    AND PASS = ?          \n");
        
		rDAO.setValue(1, strID);
        rDAO.setValue(2, strPass);
        
		rEntity = rDAO.select(sbSQL.toString());
		
		if (KJFUtil.str2int(rEntity.getValue(0, "CNT")) > 0) {
		    return true;	
		}
		
	    return false;
    }
	
    /**************************************************************************
     * 사용자 로그인 처리 메소드 
     * 
     * @param strID
     * @param strPass
     * @return
     * @throws Exception
     *************************************************************************/
    public boolean isloginPublic(String strID, String strPass) throws  Exception {
    	
    	KJFLog.log("isloginPubAdmin START");
    	        
	    ReportDAO rDAO       = new ReportDAO();
	    ReportEntity rEntity = null;
        
	    StringBuffer sbSQL = new StringBuffer();
        sbSQL.append(" SELECT COUNT(*) CNT      \n");
        sbSQL.append("   FROM PT_MI_USER        \n");
        sbSQL.append("  WHERE (HOME_GROUP <> 'A' OR HOME_GROUP IS NULL) \n");
        sbSQL.append("    AND OFFI_ID = ?  AND PASS = ?          \n");
        
		rDAO.setValue(1, strID);
        rDAO.setValue(2, strPass);
        
		rEntity = rDAO.select(sbSQL.toString());
		
		if (KJFUtil.str2int(rEntity.getValue(0, "CNT")) > 0) {
		    return true;	
		}
		
	    return false;
    }
	
    /**************************************************************************
     * 관리자 가져오기
     * 
     * @param user_id
     * @return boolean
     *************************************************************************/
     public ReportEntity loadUserFromDBAdmin(String user_id) throws Exception {

         KJFLog.log("loadUserFromDBAdmin");
    	 
         ReportDAO    rDAO    = new ReportDAO();
         ReportEntity rEntity = null;        
         
         StringBuffer sbSQL = new StringBuffer();
         sbSQL.append(" SELECT                   \n");
         sbSQL.append("        OFFI_ID,          \n");
         sbSQL.append("        PASS,             \n");
         sbSQL.append("        NM,               \n");
         sbSQL.append("        TEL,              \n");
         sbSQL.append("        MOBILE,           \n");
         sbSQL.append("        E_MAIL,           \n");
         sbSQL.append("        SIDO_CODE,        \n");
         sbSQL.append("        SIGUNGU_CODE,     \n");
         sbSQL.append("        HOME_GROUP        \n");
         sbSQL.append("   FROM PT_MI_USER        \n"); 
         sbSQL.append("  WHERE OFFI_ID = ?       \n");

         rDAO.setValue(1, user_id);
         
         rEntity = rDAO.select(sbSQL.toString());
         
         return rEntity;
     }     
    
     /**************************************************************************
      * 공무원 정보 가져오기
      * 
      * @param user_id
      * @return boolean
      *************************************************************************/
      public ReportEntity loadUserFromDBPublic(String user_id) throws Exception {

          KJFLog.log("loadUserFromDBPublic");
     	 
          ReportDAO    rDAO    = new ReportDAO();
          ReportEntity rEntity = null;        
          
          StringBuffer sbSQL = new StringBuffer();
          sbSQL.append(" SELECT                   \n");
          sbSQL.append("        OFFI_ID,          \n");
          sbSQL.append("        PASS,             \n");
          sbSQL.append("        NM,               \n");
          sbSQL.append("        TEL,              \n");
          sbSQL.append("        MOBILE,           \n");
          sbSQL.append("        E_MAIL,           \n");
          sbSQL.append("        SIDO_CODE,        \n");
          sbSQL.append("        SIGUNGU_CODE,     \n");
          sbSQL.append("        HOME_GROUP,        \n");
          sbSQL.append("        GROUP_CODE        \n");
          sbSQL.append("   FROM PT_MI_USER        \n"); 
          sbSQL.append("  WHERE OFFI_ID = ?       \n");

          rDAO.setValue(1, user_id);
          
          rEntity = rDAO.select(sbSQL.toString());
          
          return rEntity;
      }     
    /**************************************************************************
     * 홈페이지 사용자 가져오기
     * 
     * @param user_id
     * @return
     * @throws Exception
     *************************************************************************/
     public ReportEntity loadUserFromDBUser(String user_id) throws Exception {
    	 
    	 KJFLog.log("loadUserFromDBUser");
    	 
    	 ReportDAO    rDAO    = new ReportDAO();
         ReportEntity rEntity = null;
         
    	 StringBuffer sbSQL = new StringBuffer();
         sbSQL.append(" SELECT                   \n");
         sbSQL.append("        USER_ID,          \n");
         sbSQL.append("        USER_PASSWD,      \n");
         sbSQL.append("        USER_NAME,        \n");
         sbSQL.append("        USER_TEL1,        \n");
         sbSQL.append("        USER_TEL2,        \n");
         sbSQL.append("        USER_TEL3,        \n");
         sbSQL.append("        USER_MOBILE1,     \n");
         sbSQL.append("        USER_MOBILE2,     \n");
         sbSQL.append("        USER_MOBILE3,     \n");
         sbSQL.append("        USER_EMAIL,       \n");
         sbSQL.append("        SIDO_CODE,        \n");
         sbSQL.append("        SIGUNGU_CODE,     \n");
         sbSQL.append("        COM_NAME,         \n");
         sbSQL.append("        REG_NUM,          \n");
         sbSQL.append("        COM_NUM,          \n");
         sbSQL.append("        CAPITAL           \n");
         sbSQL.append("   FROM PT_HOM_USER       \n");         
         sbSQL.append("  WHERE USER_ID = ?       \n");
         sbSQL.append("    AND USE_YN = ?        \n");

         rDAO.setValue(1, user_id);
         rDAO.setValue(2, "Y");
         
         rEntity = rDAO.select(sbSQL.toString());
    		
         return rEntity;
     }     
     
     
     /**
      * 공인인증서 로그인 프로세서
      * 
      * @param request
      * @param response
      * @throws Exception
      */
     public void CCLoginProcess(HttpServletRequest request, HttpServletResponse response) throws  Exception {
         
         String userdn    = request.getParameter("userdn");     //DN값
         String user_cert = request.getParameter("user_cert");  // 인증서
         String getR      = request.getParameter("getR");       // getR값
         
         String ssn = "";
         ReportEntity rEntity = null;

         // 인증서에서 추출한 DN값을 넘겨 받아서 DB를 검색한다.
         // DB 검색결과 DN값이 등록이 되어 있지 않으면 인증서 등록 페이지로 이동한다.
         
         boolean DN_Status = false;
         
         DN_Status = isUser_DN(userdn);

         // DB에 DN값이 있으면 true이고 해당 사업자 번호를 가져 온다.
         if(DN_Status == false) {
             
             // 회원가입페이지로 넘김
             throw new MsgException(request, "회원가입이 되어있지않습니다.", "FALSE", "../member/regist.do");
             
         } else {
             
             // 성공이면 사업자 번호 또는 주민번호를 가지고 옴
             rEntity = loadUserInfo(userdn);
             
             String capital = KJFUtil.print(rEntity.getValue(0, "CAPITAL"));
             
             // 개인회원(U)이면 주민번호
             if (capital.equals("U")) {
                 ssn = KJFUtil.print(rEntity.getValue(0, "USER_SSN1")) + KJFUtil.print(rEntity.getValue(0, "USER_SSN2"));
                
             // 기업회원(E)이면 사업자번호
             } else {
                 ssn = KJFUtil.print(rEntity.getValue(0, "COM_NUM"));
             }             
         }
                      
         boolean boolCertChk = true;
         String ErrMsg = "";
         String ErrCode = "";
         
         int nRet;

         //  인증서 base64디코딩
         byte[] DecUserCert = null;  // base64디코딩한 인증서
         int DecUserCertLen = 0;     // base64디코딩한 인증서길이         

         Base64 CBase64 = new Base64();  
         // 인증서 base64인코딩
         nRet = CBase64.Decode(user_cert.getBytes(), user_cert.getBytes().length);
         
         if (nRet == 0) {
             
             //out.println("인증서 Base64 Decode 결과 : 성공<br>") ;
             //out.println("인증서 길이 : " + CBase64.contentlen + "<br>") ;
             DecUserCert = new byte[CBase64.contentlen];
             System.arraycopy(CBase64.contentbuf, 0, DecUserCert, 0, CBase64.contentlen);
             DecUserCertLen = CBase64.contentlen;
             boolCertChk = true;

             // 인증서 정보 추출 결과  
             Certificate CCertificate = new Certificate();
             nRet=CCertificate.ExtractCertInfo(DecUserCert, DecUserCertLen);
             
             System.out.println("인증서 정보 추출 결과 : " + Integer.toHexString(nRet) + "<br>");
             
             // 인증서 정보 추출 결과  출력
             CertificateVerify.printCCertificateResult(CCertificate);                                                                              

             // 인증서 검증
             String policies = CertificateVerify.getPoliciesInfo();
             
             nRet=CCertificate.ValidateCert(DecUserCert, DecUserCertLen, policies, 1);
         
             String decode_ssl1 = rEntity.getValue(0, "USER_SSN1");
             String decode_ssl2 = rEntity.getValue(0, "USER_SSN2");
             
             ssn = Crypto.Decode(decode_ssl1) + Crypto.Decode(decode_ssl2);
             
             if(nRet == 0) {   
                 
                 System.out.println("인증서 검증 결과 : 성공") ;
                 boolCertChk = true;

                 // 식별번호 검증             
                 nRet = CCertificate.VerifyVID(DecUserCert, DecUserCertLen, getR.getBytes(), getR.length(), ssn);
                 
                 if (nRet == 0) {                     
                     System.out.println("식별번호 검증 결과 : 성공") ;
                     
                 } else {
                     
                     boolCertChk = false;
                     
                     
                     System.out.println("식별번호 검증 결과 : 실패") ;
                     ErrMsg = "식별번호 검증 실패 [ 에러내용 : " + CCertificate.errmessage + " ]";
                     ErrCode = "에러코드 [ " + CCertificate.errcode + " ]";  

                     System.out.println("인증서 로그인 실패~!!@@@@");
                 }
                     
             } else {
                 
                 System.out.println("인증서 검증 결과 : 실패") ;
                 System.out.println("에러내용 : " + CCertificate.errmessage);
                 System.out.println("에러코드 : " + CCertificate.errcode);
                 
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
             System.out.print("로그인을 다시 하여 주십시오");
             
             throw new MsgException("로그인을 다시 하여 주십시오.");
                            
         } else {
             System.out.println("인증서 로그인 성공");
             System.out.println("시스템 이용하기!@@@");
             
             UserEnt user;
              
             String strID   = KJFUtil.print(rEntity.getValue(0, "USER_ID"));
             String strPass = KJFUtil.print(rEntity.getValue(0, "USER_PASSWD"));
              
             //strPass = Crypto.Decode(strPass);
             strPass = strPass;
             
             if(isloginUser(strID, strPass)) {    
                 user = new UserEnt(strID, "user");
              
             } else {
                 return;
             }
             
             // 접속로그 저장
             if(!strID.equals(Config.props.get("SYS_ID"))){
                 user.setLogNum(accessLogInsert(request,strID));
             }
             
             request.getSession().setAttribute("user", user);
         }
 
     }
     
     /**************************************************************************
      * 공인인증 번호 유무
      * 
      * @param user_id
      * @return
      * @throws Exception
      *************************************************************************/
      public boolean isUser_DN(String user_dn) throws Exception {
          
          KJFLog.log("isUser_DN");
          
          ReportDAO rDAO       = new ReportDAO();
          ReportEntity rEntity = null;
          
          StringBuffer sbSQL = new StringBuffer();
          sbSQL.append(" SELECT COUNT(*) CNT      \n");
          sbSQL.append("   FROM PT_HOM_USER           \n");
          sbSQL.append("  WHERE USER_DN = ?       \n");
          sbSQL.append("    AND USE_YN = ?        \n");
          
          rDAO.setValue(1, user_dn);
          rDAO.setValue(2, "Y");
          
          rEntity = rDAO.select(sbSQL.toString());
          
          if (KJFUtil.str2int(rEntity.getValue(0, "CNT")) > 0) {
              return true;    
          }
          
          return false;
      } 
      
      /*************************************************************************
       * 전자인증 번호로 사용자 정보 로드
       * 
       * @param user_dn
       * @return
       * @throws Exception
       ************************************************************************/
      public ReportEntity loadUserInfo(String user_dn) throws Exception {
          
          KJFLog.log("loadUserFromDBUser");
          
          ReportDAO    rDAO    = new ReportDAO();
          ReportEntity rEntity = null;
          
          StringBuffer sbSQL = new StringBuffer();
          sbSQL.append(" SELECT                   \n");
          sbSQL.append("        USER_ID,          \n");
          sbSQL.append("        USER_PASSWD,      \n");
          sbSQL.append("        USER_SSN1,        \n");
          sbSQL.append("        USER_SSN2,        \n");
          sbSQL.append("        COM_NUM,          \n");
          sbSQL.append("        CAPITAL           \n");
          sbSQL.append("   FROM PT_HOM_USER       \n");
          sbSQL.append("  WHERE USER_DN = ?       \n");
          sbSQL.append("    AND USE_YN = ?        \n");

          rDAO.setValue(1, user_dn);
          rDAO.setValue(2, "Y");
          
          rEntity = rDAO.select(sbSQL.toString());
             
          return rEntity;
      }     
      
       
     /************************************************************************** 
      * 로그아웃 프로세서
      * 
      * @param request
      * @param response
      * @throws Exception
      ************************************************************************ */
     public void LogOutProcess(HttpServletRequest request, HttpServletResponse response) throws  Exception {

          UserEnt user = (UserEnt)request.getSession().getAttribute("user");    
          
          if (user != null) {      
              
              /* 종료 시간 저장*/
              accessLogUpdate(user.getUSER_ID(),user.getLogNum());
              
              /* 세션 초기화 */
              request.getSession().invalidate();
         }
    
         // 쿠키와 함께 이용..
         if ( Config.props.getBoolean("IS_USE_COOKIE") ) {
         	UsingCookieLogOut(response);
         }   
     }
     
     
     /************************************************************************
      * 접속 로그 저장
      * 
      * @param request
      * @param user_id
      * @return logNum 로그 번호
      * @throws Exception
      ***********************************************************************/
     private String accessLogInsert(HttpServletRequest request,String USER_ID) throws Exception {
        
         ReportDAO rDAO = new ReportDAO();
         
         String LOG_NUM      = KJFDBUtil.getMaxID("LOG_NUM", "PT_HOM_USER_LOG");
         String ACCESS_TIME  = KJFDate.datetimeOnly(KJFDate.getCurTime());
         String ACCESS_IP    = request.getRemoteAddr();
         String BROWSER      = request.getHeader("User-Agent") == null?"":request.getHeader("User-Agent");

         if(BROWSER.length() > 128) BROWSER = KJFUtil.getTitleLimit(BROWSER, 120);
         
         StringBuffer sbSQL = new StringBuffer(); 
         sbSQL.append(" INSERT INTO PT_HOM_USER_LOG (    \n");
         sbSQL.append("        LOG_NUM,                  \n");
         sbSQL.append("        USER_ID,                  \n");
         sbSQL.append("        ACCESS_TIME,              \n");
         sbSQL.append("        END_TIME,                 \n");
         sbSQL.append("        ACCESS_IP,                \n");
         sbSQL.append("        BROWSER                   \n");
         sbSQL.append("       )                          \n");
         sbSQL.append("   values (?,?,?,?,?,?)           \n");   

         int i = 0;

         rDAO.setValue(++i, LOG_NUM);       // 로그 번호
         rDAO.setValue(++i, USER_ID);       // 사용자 아이디
         rDAO.setValue(++i, ACCESS_TIME);   // 접속 시간.
         rDAO.setValue(++i, "");            // 접속 시간.
         rDAO.setValue(++i, ACCESS_IP);     // 접속 IP.
         rDAO.setValue(++i, BROWSER);       // 브라우저 종류
         
         rDAO.execute(sbSQL.toString());
         
         return LOG_NUM;
     }

     
     /************************************************************************
      * 종료시각 저장
      * 
      * @param user_id
      * @param logNum
      * @throws Exception
      ***********************************************************************/
     private void accessLogUpdate(String user_id, String logNum) throws Exception {
         
         ReportDAO rDAO = new ReportDAO();
         
         StringBuffer sbSQL = new StringBuffer();         
         sbSQL.append(" UPDATE PT_HOM_USER_LOG SET    \n");         
         sbSQL.append("        END_TIME = ?           \n");         
         sbSQL.append("  WHERE USER_ID = ?            \n");
         sbSQL.append("    AND LOG_NUM = ?            \n");

         int i = 0;
         
         rDAO.setValue(++i,KJFDate.datetimeOnly(KJFDate.getCurTime()));
         rDAO.setValue(++i, user_id);
         rDAO.setValue(++i, logNum);

         rDAO.execute(sbSQL.toString());         
     }
 	
	
    /**************************************************************************
     * 세션 체크 메소드 
     * 
     * @param request
     * @throws Exception
     *************************************************************************/
	public static void checkSession(HttpServletRequest request) throws  Exception {
	   	
	    //색션에서 사용자객체를 구한다.
	    @SuppressWarnings("unused")
        UserEnt user = (UserEnt) request.getSession().getAttribute("user");
   }
   
      
   /***************************************************************************
    * Cookie() 를 사용하기 위하여.. 만들어 놓윽 것임...
    * 
    * @param response
    * @param user
    * @throws Exception
    **************************************************************************/
	public  void UsingCookie(HttpServletResponse response, UserEnt user) throws  Exception {	
		
	    Cookie cookie;

	    // 세션 조정을 위한 목적.
        String strLoginUser = KJFSec.encode( user.getUSER_ID());
      
        KJFLog.log(COOKIE_LOGINED_NAME + " id(" + user.getUSER_ID() +") = " + strLoginUser);

        cookie = new Cookie( COOKIE_LOGINED_NAME, strLoginUser);

        cookie.setPath("/");
        cookie.setMaxAge( -1);
        cookie.setVersion(0);
        cookie.setSecure(false);		
		
		response.addCookie(cookie);		
	}
	
	
    /**************************************************************************
     * 쿠키 로그아웃 처리 메소드 
     * 
     * @param response
     * @throws Exception
     *************************************************************************/
	public  void UsingCookieLogOut(HttpServletResponse response ) throws  Exception {		

	    Cookie cookie = new Cookie(COOKIE_LOGINED_NAME, "");

        cookie.setPath("/");
        cookie.setMaxAge(0);
        cookie.setVersion(0);
        cookie.setSecure(false);
		
		response.addCookie(cookie);		
	}
     
    // 쿠키에 저장되는 사용자의 LoginUser를 구하는 키값.
	private static final String COOKIE_LOGINED_NAME = Config.props.get("COOKIE_LOGINED_NAME") + "_login_in_serial_number";   

}