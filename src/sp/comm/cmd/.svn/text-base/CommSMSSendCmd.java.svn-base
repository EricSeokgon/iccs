package sp.comm.cmd;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFLog;
import kjf.util.KJFUtil;
import kjf.util.LoginException;

import org.apache.struts.action.ActionForm;

import sp.comm.CommParam;
import sp.uent.UserEnt;
import sp.util.sms.SmsMd;

/**
 * <p>Title       : CommSmsSendCmd Class</p>
 * <p>Description : 모바일 사용신청 인증번호 SMS 전송 처리 한다.</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : 모바일 사용신청 인증번호 SMS 전송 처리를 한다. 
 * 
 * @version 1.0
 * @author  PKT
 */
public class CommSMSSendCmd implements KJFCommand {
    
    UserEnt user;
    
    public CommSMSSendCmd() {
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {
        
        user = (UserEnt)request.getSession().getAttribute("user");
        
        // 로그인 유무 체크
        if (KJFUtil.isEmpty(user)) {
            throw new LoginException(request, "회원용 페이지 로그인 체크", "../member/login.do");
        }  
        
        // 검색조건 설정및 체크
        CommParam pm = checkPm(request, form);   
        
        request.setAttribute("pm", pm);
        
        // SMS 전송
        sendSMS(request, pm);
        
        return request.getParameter("cmd");
    }
    
    /************************************************************************
     * SMS 전송
     * 
     * @param HttpServletRequest
     * @param KmsParam
     * @return void
     ***********************************************************************/    
    private void sendSMS(HttpServletRequest request, CommParam pm) throws Exception {
        
        String validation_num = request.getParameter("validation_num"); // 인증번호
        
        String user_id        = user.getUSER_ID();                      // 사용자 ID
        String Local_cd       = user.getSIDO_CODE();                    // 지역코드
        String Area_cd        = user.getSIDO_CODE();                    // 소속지역코드
        String from_tel       = loadFromTel(request, pm);               // 발신자
        String to_tel         = user.getUSER_MOBILE();                  // 송신자
        String msg            = "정보통신공사업 행정시스템 모바일신청 인증번호[" + validation_num + "]입니다.";
        
        SmsMd sm = new SmsMd();
        int result = sm.SendMsg(user_id, Local_cd, Area_cd, from_tel, to_tel, msg);
        //사용자 ID, 16개시도 코드, 사용하는 시도시군구 코드, 발신자번호, 송신자번호, 메세지)
        //전화번호는 특수문자 없이 번호만 입력 (중요!!!);
        
        // 전송 성공
        if (result == 1) {
            request.setAttribute("result", "success");
            System.out.println("success");
        
        // 전송 실패
        } else {
            request.setAttribute("result", "failure");
            System.out.println("failure");
        }
              
    }     
    
    
    /************************************************************************
     * SMS 전송 시도 전화번호 Load
     * 
     * @param HttpServletRequest
     * @param KmsParam
     * @return void
     ***********************************************************************/    
    private String loadFromTel(HttpServletRequest request, CommParam pm) throws Exception {
        
        ReportDAO    rDAO    = new ReportDAO();
        ReportEntity rEntity = null;      
        
        String result = "0518882240";
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT TEL                   \n");
        sbSQL.append("   FROM PT_SIDO_CODE          \n");
        sbSQL.append("  WHERE SUBSTR(AREA_CODE, 1, 2) = SUBSTR(AREA_CODE, 3, 4)    \n");        
        sbSQL.append("    AND AREA_CODE = ?         \n");
        
        rDAO.setValue(i++, user.getSIDO_CODE());
      
        rEntity = rDAO.select(sbSQL.toString());
                
        if (rEntity.getRowCnt() > 0) {
            result = rEntity.getValue(0, "TEL").replaceAll("-", "");
        }
        
        return result;              
    }     
    
    
    
    /************************************************************************
     * 검색조건 초기값 설정 및 체크
     * 
     * @param HttpServletRequest
     * @param ActionForm
     * @return KmsParam
     ************************************************************************/
    private CommParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {
        
        CommParam pm = (CommParam) form;
                
        // 파라미터 디버깅
        KJFLog.debug(pm.toString(request));
       
        return pm;
    }  

}
