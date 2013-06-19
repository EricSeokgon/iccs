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
 * <p>Description : 로그인 관련 정보를 맵핑 처리 한다.</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : 로그인 관련 정보를 맵핑 처리를 한다. 
 * 
 * @version 1.0
 * @author PKT
 */
public class LoginAction extends KJFAction {
	
    /** 명령패턴 class들 **/
	private HashMap<String, KJFCommandResp> commands; 
        	
    /**
     * 개별Command들을 실행한다.<br>
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
     * Command들을 등록한다.
     * 
     * @param 
     * @return       
     */
    private void initCommands() {

        commands = new HashMap<String, KJFCommandResp>();
      
        // 프로그램 등록
        commands.put("Login",   new LoginCmd());                // 로그인
        commands.put("CCLogin", new CertificateLoginCmd());     // 전자 인증 로그인
        commands.put("LogOut",  new LogOutCmd());               // 로그아웃
        
        commands.put("GCertReg", new GCertRegCmd("/login/GCertReg.jsp"));//인증서 등록화면
        commands.put("GCertRegCUD", new GCertRegCUDCmd("/login/LoginAction.do?cmd=GCertReg"));//인증서 등록화면
        //commands.put("GCertRegCUD", new GCertRegCUDCmd("/login/GCertReg.jsp"));//인증서 등록화면 
        commands.put("PdaLoginMgr",	new PdaLoginMgrCmd());//PDA 로그인
    }	
	
	
    /**
     * 요청받은 명령의 Command instance를 구한다.
     * 
     * @param cmd 명령명
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
