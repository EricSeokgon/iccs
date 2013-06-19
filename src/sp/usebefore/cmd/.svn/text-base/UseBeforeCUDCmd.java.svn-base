package sp.usebefore.cmd;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFDBUtil;
import kjf.util.KJFDate;
import kjf.util.KJFLog;
import kjf.util.KJFUtil;
import kjf.util.LoginException;

import org.apache.struts.action.ActionForm;

import sp.uent.UserEnt;
import sp.usebefore.UseBeforeParam;

/***************************************************************************
 * <p>Title       : UseBeforeCUDCmd Class</p>
 * <p>Description : 사용전검사 등록, 수정, 삭제 처리 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : 사용전검사 등록, 수정, 삭제 처리를 한다. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class UseBeforeCUDCmd implements KJFCommand {
    
	UserEnt user;
	
    public UseBeforeCUDCmd() {
        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {        
        
        String cmd = request.getParameter("cmd");
        
        user = (UserEnt)request.getSession().getAttribute("user");
        
        // 로그인 유무 체크
        if (KJFUtil.isEmpty(user)) {
            throw new LoginException(request, "회원용 페이지 로그인 체크", "../member/login.do");
        }  
        
        // 검색조건 설정및 체크
        UseBeforeParam pm = checkPm(request, form);
        
        
        // 사용전 검사 등록  
        if (cmd.equals("UseBeforeC" )) {
           
            // 사용전검사등록 체크
            if ( isUserBeforeChk(request, pm) ) {
                request.setAttribute("result", "N");
                
                System.out.println("등록됨");
            } else {
                insertUserBeforeExe(request, pm);
                request.setAttribute("result", "Y");
                System.out.println("등록안됨");
            }        
            
         // 사용전 검사 삭제
        } else if (cmd.equals("UseBeforeD")) {
            deleteUserBeforeExe(request, pm);   // 기업회원등록   
        }
        
        
        request.setAttribute("pm", pm);
        return request.getParameter("cmd");
    }
    
    
    /**
     * 사용전검사 등록체크
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public boolean isUserBeforeChk(HttpServletRequest request, UseBeforeParam pm) throws Exception {
        
    	ReportEntity rEntity = null;        
        
        ReportDAO rDAO = new ReportDAO();      
             
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT COUNT(*) AS CNT	\n");   // 진행상태        
        sbSQL.append("   FROM PT_HOME_USEBEFORE	\n");         
        sbSQL.append("  WHERE RECV_NUM = ? 		\n");  
       	sbSQL.append("    AND USER_ID = ? 		\n");
       	
    	rDAO.setValue(i++, pm.getRecv_num().trim());
    	rDAO.setValue(i++, user.getUSER_ID());   	
        
        rEntity = rDAO.select(sbSQL.toString());        
        
        if(KJFUtil.str2int(rEntity.getValue(0, "CNT")) > 0) {             
            return true;
        }

        return false;
        
    }
    
    
    /**
     * 사용전검사 등록
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void insertUserBeforeExe(HttpServletRequest request, UseBeforeParam pm) throws Exception {
                
        ReportDAO rDAO = new ReportDAO();      
                
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" INSERT INTO PT_HOME_USEBEFORE (		\n");
        sbSQL.append("        SEQ,               			\n");
        sbSQL.append("        RECV_NUM,             		\n");
        sbSQL.append("        USER_ID,           			\n");
        sbSQL.append("        INS_DT             			\n");
        sbSQL.append("       )                      		\n");
        sbSQL.append("   values (?,?,?,?)                   \n");   

        int i = 1;
        
        rDAO.setValue(i++, KJFDBUtil.getMaxID("SEQ", "PT_HOME_USEBEFORE"));
        rDAO.setValue(i++, pm.getRecv_num());
        rDAO.setValue(i++, user.getUSER_ID());
        rDAO.setValue(i++, KJFDate.getCurTime("yyyy-MM-dd HH:mm:ss"));

        rDAO.execute(sbSQL.toString());
    }
    
    
    /**
     * 사용전검사 삭제
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void deleteUserBeforeExe(HttpServletRequest request, UseBeforeParam pm) throws Exception {
                
        ReportDAO rDAO = new ReportDAO();      
                
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" DELETE                       \n");
        sbSQL.append("   FROM PT_HOME_USEBEFORE     \n");
        sbSQL.append("  WHERE RECV_NUM = ?          \n");  
        sbSQL.append("    AND USER_ID = ?           \n");
        
        int i = 1;
        rDAO.setValue(i++, pm.getRecv_num());
        rDAO.setValue(i++, user.getUSER_ID());      

        rDAO.execute(sbSQL.toString());
    }
    
    
    /**
     * 폼 체크 메소드
     * 
     * @param request
     * @param form
     * @return
     * @throws Exception
     */
    private UseBeforeParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {

        UseBeforeParam pm = (UseBeforeParam)form;

        // 파라미터 디버깅
        KJFLog.debug(pm.toString(request));
        
        return pm;
    }

}
