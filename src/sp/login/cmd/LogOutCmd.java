package sp.login.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kjf.action.KJFCommandResp;

import org.apache.struts.action.ActionForm;

import sp.login.LoginWorker;

/****************************************************************************
 * <p>Title       : LoginCmd Class</p>
 * <p>Description : 로그아웃 처리를 하는 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 *
 * NOTE : 로그아웃 처리를 한다.
 *
 * @version 1.0
 * @author  PKT
 ***************************************************************************/
public class LogOutCmd implements KJFCommandResp {

	public LogOutCmd() {
    }
    
    public String execute(HttpServletRequest request, HttpServletResponse response,  ActionForm form) throws Exception {        

    	LoginWorker worker = new LoginWorker();
    	
    	worker.LogOutProcess(request, response);
    	
    	return request.getParameter("cmd");
    }
}