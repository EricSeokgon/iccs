package sp.login ; 

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kjf.action.KJFCommandResp;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import sp.action.KJFAction;
import sp.login.cmd.CertificateLoginCmd;
import sp.login.cmd.GCertRegCUDCmd;
import sp.login.cmd.GCertRegCmd;
import sp.login.cmd.LogOutCmd;
import sp.login.cmd.LoginCmd;
import sp.login.cmd.PdaLoginMgrCmd;

/**
 * <p>Title       : LoginAction Class</p>
 * <p>Description : �α��� ���� ������ ���� ó�� �Ѵ�.</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : �α��� ���� ������ ���� ó���� �Ѵ�. 
 * 
 * @version 1.0
 * @author PKT
 */
public class LoginAction extends KJFAction {
	
    /** ������� class�� **/
	private HashMap<String, KJFCommandResp> commands; 
        	
    /**
     * ����Command���� �����Ѵ�.<br>
     * 
     * @param ActionMapping mapping, <br>
     *        ActionForm form,<br>
     *        HttpServletRequest request,<br>
     *        HttpServletResponse response<br>
     * @return ActionForward    
     */
	public String executeAction(ActionMapping mapping,
					            ActionForm form,
					            HttpServletRequest request,
					            HttpServletResponse response) throws Exception {
		
		initCommands();		
        
        KJFCommandResp cmd = lookupCommand(request.getParameter("cmd"));         

        return  cmd.execute(request, response, form)  ; 
	} 
	
	
    /**
     * Command���� ����Ѵ�.
     * 
     * @param 
     * @return       
     */
    private void initCommands() {

        commands = new HashMap<String, KJFCommandResp>();
      
        // ���α׷� ���
        commands.put("Login",   new LoginCmd());                // �α���
        commands.put("CCLogin", new CertificateLoginCmd());     // ���� ���� �α���
        commands.put("LogOut",  new LogOutCmd());               // �α׾ƿ�
        
        commands.put("GCertReg", new GCertRegCmd("/login/GCertReg.jsp"));//������ ���ȭ��
        commands.put("GCertRegCUD", new GCertRegCUDCmd("/login/LoginAction.do?cmd=GCertReg"));//������ ���ȭ��
        //commands.put("GCertRegCUD", new GCertRegCUDCmd("/login/GCertReg.jsp"));//������ ���ȭ�� 
        commands.put("PdaLoginMgr",	new PdaLoginMgrCmd());//PDA �α���
    }	
	
	
    /**
     * ��û���� ����� Command instance�� ���Ѵ�.
     * 
     * @param cmd ��ɸ�
     * @return Command class instance
     */
    private KJFCommandResp lookupCommand(String cmd) throws Exception {
        
        if (commands.containsKey(cmd)) {
            return (KJFCommandResp)commands.get(cmd);
        } else {
            throw new Exception("Invalid Command Identifier");
        }
    }
    
}
