package sp.mem.cmd;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFLog;

import org.apache.struts.action.ActionForm;

import sp.mem.MemParam;
import sp.util.sms.SmsMd;

/****************************************************************************
 * <p>Title       : MemPWSearchCmd Class</p>
 * <p>Description : 비밀번호검색 처리 프로세스 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 *
 * NOTE : 비밀번호검색 처리를 한다.
 *
 * @version 1.0
 * @author  PKT
 ***************************************************************************/
public class MemPWSearchCmd implements KJFCommand {
    
    public MemPWSearchCmd() {        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {    
               
        // 검색조건 설정및 체크
        MemParam pm = checkPm(request, form);
        
        // 검색조건 및 초기데이타 로드
        searchPwd(request, pm);
        
        request.setAttribute("pm", pm);
        
        return request.getParameter("cmd");
    }   
    
    
    /**
     * 검색조건 및 초기데이타 로드
     * 
     * @param HttpServletRequest
     * @return
     */
    private void searchPwd(HttpServletRequest request, MemParam pm) throws Exception {
        
        ReportEntity rEntity = loadUserInfo(request, pm);
        
        if (rEntity.getRowCnt() > 0) {
            
            String tempPwd = getTempPassword(10);       // 임시 비밀번호 생성
            
            if (sendSMS(request, rEntity, tempPwd) ) {  // 임시 비밀번호 SMS 발송
                
                changePassword(request, tempPwd);       // 테이블 비밀번호 변경      
                request.setAttribute("PwdResult", "SUCCESS");
            } else {
             
                request.setAttribute("PwdResult", "SMS_FAIL");
                return;
            }            
            
        } else {
            request.setAttribute("PwdResult", "NONE");
        }
        
    }
    
    
    /**
     * 회원 비밀번호 정보 Load
     * 
     * @param request
     * @param pm
     * @throws Exception
     */
    private ReportEntity loadUserInfo(HttpServletRequest request, MemParam pm) throws Exception {
        
        ReportDAO    rDAO    = new ReportDAO();
        ReportEntity rEntity = null;      
        
        int i = 1;

        String id   = request.getParameter("user_id");   
        String name = request.getParameter("user_name");
        String ssn1 = request.getParameter("user_ssn1");
        String ssn2 = request.getParameter("user_ssn2");     
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT USER_ID,          \n");
        sbSQL.append("        USER_NAME,        \n");
        sbSQL.append("        USER_EMAIL,       \n");
        sbSQL.append("        USER_MOBILE1,     \n");
        sbSQL.append("        USER_MOBILE2,     \n");
        sbSQL.append("        USER_MOBILE3,     \n");
        sbSQL.append("        SIDO_CODE         \n");
        sbSQL.append("   FROM PT_HOM_USER       \n");        
        sbSQL.append("  WHERE USER_ID = ?       \n");
        sbSQL.append("    AND USER_NAME = ?     \n");
        sbSQL.append("    AND USER_SSN1 = ?     \n");
        sbSQL.append("    AND USER_SSN2 = ?     \n");
        
        rDAO.setValue(i++, id);
        rDAO.setValue(i++, name);
        rDAO.setValue(i++, ssn1);
        rDAO.setValue(i++, ssn2);        
      
        rEntity = rDAO.select(sbSQL.toString());
             
        return rEntity;
    }
    
    
    /**
     * 임시 비빌번호 생성
     * 
     * @param length
     * @return String
     * @throws Exception
     */
    private String getTempPassword(int length) throws Exception {
                
        Random random = new Random();
         
        String rndValue = "abcdefghijklmnopqrstuvwxyz0123456789";
        
        StringBuffer sTemp = new StringBuffer();         

        for(int i = 1; i <= length; i++) {
            
            int nRnd = random.nextInt(36);
         
            sTemp.append(rndValue.substring(nRnd, nRnd+1));
        }
        
        return sTemp.toString();
    }
    
    
    /**
     * 비밀번호 변경
     * 
     * @param request
     * @param tempPwd
     * @throws Exception
     */
    private void changePassword(HttpServletRequest request, String tempPwd) throws Exception {
        
        ReportDAO rDAO = new ReportDAO();
        
        String user_id   = request.getParameter("user_id");   
        String user_name = request.getParameter("user_name");
        String user_ssn1 = request.getParameter("user_ssn1");
        String user_ssn2 = request.getParameter("user_ssn2");
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" UPDATE PT_HOM_USER SET       \n");
        sbSQL.append("        USER_PASSWD = ?       \n");       
        sbSQL.append("  WHERE USER_ID = ?           \n");
        sbSQL.append("    AND USER_NAME = ?         \n");
        sbSQL.append("    AND USER_SSN1 = ?         \n");
        sbSQL.append("    AND USER_SSN2 = ?         \n");
        
        rDAO.setValue(i++, tempPwd);
        rDAO.setValue(i++, user_id);
        rDAO.setValue(i++, user_name);
        rDAO.setValue(i++, user_ssn1);
        rDAO.setValue(i++, user_ssn2);
        rDAO.execute(sbSQL.toString());
    }
    
    
    /*************************************************************************
     * 회원비밀번호 SMS 전송 메소드 
     * 
     * @param HttpServletRequest
     * @param KmsParam
     ************************************************************************/
    private boolean sendSMS(HttpServletRequest request, ReportEntity rEntity, String tempPwd)throws Exception{
        
        String user_id        = rEntity.getValue(0, "USER_ID");                 // 사용자 ID
        String Local_cd       = rEntity.getValue(0, "SIDO_CODE");               // 지역코드
        String Area_cd        = rEntity.getValue(0, "SIDO_CODE");               // 소속지역코드
        String from_tel       = loadFromTel(rEntity.getValue(0, "SIDO_CODE"));  // 발신자
        String to_tel         = rEntity.getValue(0, "USER_MOBILE1")             // 송신자
                                    + rEntity.getValue(0, "USER_MOBILE2") 
                                        + rEntity.getValue(0, "USER_MOBILE3");
        String msg            = "정보통신공사업 행정시스템 임시비밀번호[" + tempPwd + "]입니다.";
        
        SmsMd sm = new SmsMd();
        int result = sm.SendMsg(user_id, Local_cd, Area_cd, from_tel, to_tel, msg);
        
        // 전송 성공
        if (result == 1) {
            System.out.println("SMS : success");
        
        // 전송 실패
        } else {
            System.out.println("SMS : failure");
            return false;
        }
        
        return true;
        
    }
    
    /************************************************************************
     * SMS 전송 시도 전화번호 Load
     * 
     * @param HttpServletRequest
     * @param KmsParam
     * @return void
     ***********************************************************************/    
    private String loadFromTel(String sido_code) throws Exception {
        
        ReportDAO    rDAO    = new ReportDAO();
        ReportEntity rEntity = null;      
        
        String result = "0518882240";
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT TEL                   \n");
        sbSQL.append("   FROM PT_SIDO_CODE          \n");
        sbSQL.append("  WHERE SUBSTR(AREA_CODE, 1, 2) = SUBSTR(AREA_CODE, 3, 4)    \n");        
        sbSQL.append("    AND AREA_CODE = ?         \n");
        
        rDAO.setValue(i++, sido_code);
      
        rEntity = rDAO.select(sbSQL.toString());
                
        if (rEntity.getRowCnt() > 0) {
            result = rEntity.getValue(0, "TEL").replaceAll("-", "");
        }
        
        return result;              
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
