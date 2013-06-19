package sp.login.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kjf.action.KJFCommandResp;
import kjf.util.KJFUtil;

import org.apache.struts.action.ActionForm;

import sp.login.LoginWorker;

/****************************************************************************
 * <p>Title       : LoginCmd Class</p>
 * <p>Description : 로그인 처리 프로세스 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 *
 * NOTE : 로그인 처리를 한다.
 *
 * @version 1.0
 * @author  PKT
 ***************************************************************************/
public class LoginCmd implements KJFCommandResp {

    
	public LoginCmd() {
    }
    
    public String execute(HttpServletRequest request, HttpServletResponse response,  ActionForm form) throws Exception {
    	
        String beforeURL = getBeforeURL(request);
        String cmd       = request.getParameter("cmd");
         
        String encoding = KJFUtil.print(request.getParameter("encoding"),"N");
        
        if (!KJFUtil.isEmpty(beforeURL)) {
            if("Y".equals(encoding)){
            	cmd = "/com/redirect.jsp";
            	request.setAttribute("beforeURL", beforeURL);
            } else {
            	cmd = beforeURL;
            }
        }
        
        // 다른곳에 쓸일이 있어서 모듈로 빼놓았음
        LoginWorker worker = new LoginWorker();
        boolean result = worker.LoginProcess(request, response);  
        
        if (!result) {
            cmd = "/member/login.do";
        }
        
        System.out.println("beforeURL====="+beforeURL);
        
        return cmd;
    }
    
    /**
     * 이전페이지 URL 체크
     * 
     * @param request
     * @return String
     * @throws Exception
     */
    public String getBeforeURL(HttpServletRequest request) throws Exception {
        
        String beforeURL = request.getParameter("beforeURL");
               
        return beforeURL;
    }
}