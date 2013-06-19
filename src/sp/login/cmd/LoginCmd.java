package sp.login.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kjf.action.KJFCommandResp;
import kjf.util.KJFUtil;

import org.apache.struts.action.ActionForm;

import sp.login.LoginWorker;

/****************************************************************************
 * <p>Title       : LoginCmd Class</p>
 * <p>Description : �α��� ó�� ���μ��� Ŭ����</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 *
 * NOTE : �α��� ó���� �Ѵ�.
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
        
        // �ٸ����� ������ �־ ���� ��������
        LoginWorker worker = new LoginWorker();
        boolean result = worker.LoginProcess(request, response);  
        
        if (!result) {
            cmd = "/member/login.do";
        }
        
        System.out.println("beforeURL====="+beforeURL);
        
        return cmd;
    }
    
    /**
     * ���������� URL üũ
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