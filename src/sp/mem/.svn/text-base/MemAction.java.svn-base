package sp.mem; 

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kjf.action.KJFCommand;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import sp.action.KJFAction;
import sp.mem.cmd.MemCCReRegCmd;
import sp.mem.cmd.MemCCReRegOKCmd;
import sp.mem.cmd.MemIDSearchCmd;
import sp.mem.cmd.MemInfoUOKCmd;
import sp.mem.cmd.MemInfoVCmd;
import sp.mem.cmd.MemMobileRegCmd;
import sp.mem.cmd.MemMobileRegOKCmd;
import sp.mem.cmd.MemMobileTimeOverCmd;
import sp.mem.cmd.MemPWSearchCmd;
import sp.mem.cmd.MemPasswdChangeCmd;
import sp.mem.cmd.MemPasswdChangeOKCmd;
import sp.mem.cmd.MemPubResNumChangeCmd;
import sp.mem.cmd.MemRegBusinessCmd;
import sp.mem.cmd.MemRegCUDCmd;
import sp.mem.cmd.MemRegDuplCmd;
import sp.mem.cmd.MemRegPrivateCmd;
import sp.mem.cmd.MemRegSuccessCmd;

/**
 * <p>Title       : 회원관리 Action</p>
 * <p>Description : 회원관리 처리를 한다.</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : 회원관리 처리를 한다. 
 * 
 * @version 1.0
 * @author  PKT
 */
public class MemAction extends KJFAction{
	
    /** 명령패턴 class들 */
	private HashMap<String, KJFCommand> commands;
	
    /**
     * 개별Command들을 실행한다.
     * @param ActionMapping mapping,
     *         ActionForm form,
     *         HttpServletRequest request,
     *         HttpServletResponse response
     * @return  ActionForward    
     */
	public String executeAction(ActionMapping mapping,
							            ActionForm form,
							            HttpServletRequest request,
							            HttpServletResponse response) throws Exception{
					
			initCommands();		
            KJFCommand cmd = lookupCommand(request.getParameter("cmd"));         
	
	        return cmd.execute(request, form) ; 
	} 
	
	
    /**
     * Command들을 등록한다.
     * @param 
     * @return       
     */
    private void initCommands(){

        commands = new HashMap<String, KJFCommand>();
      
        // 회원관리
        commands.put("MemRegPrivate",       new MemRegPrivateCmd());        // 개인 회원정보 등록 화면
        commands.put("MemRegBusiness",      new MemRegBusinessCmd());       // 기업 회원정보 등록 처리
        
        commands.put("MemRegPriC",          new MemRegCUDCmd());            // 개인 회원 가입 처리
        commands.put("MemRegBusC",          new MemRegCUDCmd());            // 기업 회원 가입 처리        
        commands.put("MemRegDupl",          new MemRegDuplCmd());           // 회원가입된 정보
        commands.put("MemRegSuccess",       new MemRegSuccessCmd());        // 회원가입 완료
        
        commands.put("MemIDSearch",         new MemIDSearchCmd());          // 아이디 찾기
        commands.put("MemPWSearch",         new MemPWSearchCmd());          // 비밀번호 찾기
        
        commands.put("MemPasswdChange",     new MemPasswdChangeCmd());      // 비밀번호 변경
        commands.put("MemPasswdChangeU",    new MemRegCUDCmd());            // 비밀번호 변경
        commands.put("MemPasswdChangeOK",   new MemPasswdChangeOKCmd());    // 비밀번호 변경 완료
        
        commands.put("MemMobileReg",        new MemMobileRegCmd());         // 모바일서비스
        commands.put("MemMobileRegU",       new MemRegCUDCmd());            // 모바일서비스 등록 
        commands.put("MemMobileRegOK",      new MemMobileRegOKCmd());       // 모바일 서비스 등록 완료
        commands.put("MemMobileTimeOver",   new MemMobileTimeOverCmd());    // 모바일 시간종료
        
        commands.put("MemCCReReg",          new MemCCReRegCmd());      	    // 공인인증 재등록등록화면
        commands.put("MemCCReRegU",         new MemRegCUDCmd());      	    // 공인인증 재등록 처리
        commands.put("MemCCReRegOK",        new MemCCReRegOKCmd());         // 공인인증 재등록 처리
               
        commands.put("MemInfoV",            new MemInfoVCmd());             // 회워정보 보기         
        commands.put("MemInfoU",            new MemRegCUDCmd());            // 회원정보 수정
        commands.put("MemInfoUOK",          new MemInfoUOKCmd());           // 회원정보 수정 완료 
        
        commands.put("MemInfoD",            new MemRegCUDCmd());            // 회원탈퇴 처리
        
        commands.put("MemPubResNumU",       new MemPubResNumChangeCmd());   // 공사업업체 등록여부 확인처리
    }	
	
	
    /**
     * 요청받은 명령의 Command instance를 구한다.
     * @param cmd 명령명
     * @return Command class instance
     */
    private KJFCommand lookupCommand(String cmd) throws Exception {
        
        if (commands.containsKey(cmd)) {
            return (KJFCommand)commands.get(cmd);
            
        } else {
            throw new Exception("Invalid Command Identifier");
        }
    }
}
