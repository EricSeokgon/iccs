package sp.usebefore;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kjf.action.KJFCommand;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import sp.action.KJFAction;
import sp.usebefore.cmd.DeliNumMgrCmd;
import sp.usebefore.cmd.GroupWareMgrCmd;
import sp.usebefore.cmd.UseBeforChkStatusCmd;
import sp.usebefore.cmd.UseBeforeAttMgrCUDCmd;
import sp.usebefore.cmd.UseBeforeAttMgrCmd;
import sp.usebefore.cmd.UseBeforeBuildMgrCmd;
import sp.usebefore.cmd.UseBeforeCUDCmd;
import sp.usebefore.cmd.UseBeforeChkContentCmd;
import sp.usebefore.cmd.UseBeforeChkMgrCmd;
import sp.usebefore.cmd.UseBeforeLogCmd;
import sp.usebefore.cmd.UseBeforeMemoCUDCmd;
import sp.usebefore.cmd.UseBeforeMgrResCCmd;
import sp.usebefore.cmd.UseBeforeMgrResCUDCmd;
import sp.usebefore.cmd.UseBeforeMgrResCmd;
import sp.usebefore.cmd.UseBeforeQuickSigunCmd;

import sp.usebefore.cmd.UseBeforeMgrCCmd;
import sp.usebefore.cmd.UseBeforeMgrCUDCmd;
import sp.usebefore.cmd.UseBeforeMgrCmd;

import sp.usebefore.cmd.UseBeforeQuickStatusCmd;
import sp.usebefore.cmd.UseBeforeRegInfoCmd;

/****************************************************************************
 * <p>Title       : UseBeforeActon Class</p>
 * <p>Description : 사용전검사관리 처리를 하는 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 *
 * NOTE : 사용전검사관리 정보를 처리한다.
 *
 * @version
 * @author PKT
 ***************************************************************************/
public class UseBeforeAction extends KJFAction {
    
    /** 명령패턴 class들 **/
    private HashMap<String, KJFCommand> commands; 
            
    /**************************************************************************
     * 개별Command들을 실행한다.<br>
     * 
     * @param ActionMapping mapping, <br>
     *        ActionForm form,<br>
     *        HttpServletRequest request,<br>
     *        HttpServletResponse response<br>
     * @return ActionForward    
     *************************************************************************/
    public String executeAction(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        
        initCommands();     
        
        KJFCommand cmd = lookupCommand(request.getParameter("cmd"));

        return cmd.execute(request, form);
    } 
    
    
    /**************************************************************************
     * Command들을 등록한다.
     * 
     * @param 
     * @return       
     *************************************************************************/
    private void initCommands() {

        commands = new HashMap<String, KJFCommand>();
        
        // 사용전 검사 확인
        commands.put("UseBeforeChkStatus", 		new UseBeforChkStatusCmd());	// 사용전검사 접수현황
        commands.put("UseBeforeChkContent",		new UseBeforeChkContentCmd());	// 사용전검사 접수내용
        commands.put("UseBeforeChkMgr",     	new UseBeforeChkMgrCmd());		// 사용전검사 관리
        commands.put("UseBeforeBuildMgr", 		new UseBeforeBuildMgrCmd());	// 감리대상 건축물관리
        commands.put("UseBeforeC",              new UseBeforeCUDCmd());         // 사용전검사 등록
        commands.put("UseBeforeD",              new UseBeforeCUDCmd());         // 사용전검사 등록
        
        commands.put("UseBeforeQuickStatus",    new UseBeforeQuickStatusCmd()); // 빠른사용전검사현황 팝업
        commands.put("UseBeforeQuickSigun",     new UseBeforeQuickSigunCmd()); 	// 빠른사용전검사현황 팝업(검색없이 리스트 출력)
        
        commands.put("UseBeforeLog",   			new UseBeforeLogCmd()); // 필증발급 로그 남기기
        commands.put("UseBeforeLogOnly",   		new UseBeforeLogCmd()); // 필증발급 로그 남기기        
        commands.put("UseBeforeRegSign",   		new UseBeforeLogCmd()); // 필증발급 로그 남기기
        
        
        commands.put("UseBeforeMgr", 			new UseBeforeMgrCmd());			// 사용전검사관리
        commands.put("UseBeforeMgrC",           new UseBeforeMgrCCmd());    	// 사용전검사관리 
        commands.put("UseBeforeMgrCUD",         new UseBeforeMgrCUDCmd());  	// 사용전검사관리        
        
        
        commands.put("UseBeforeMemoCUD",         new UseBeforeMemoCUDCmd());  	// 사용전검사관리 메모입력
        commands.put("DeliNumMgr", 				new DeliNumMgrCmd());			// 사용전검사관리 필증발급
        commands.put("UseBeforeMgrRes", 		new UseBeforeMgrResCmd());		// 사용전검사합격관리
        commands.put("UseBeforeMgrResC", 		new UseBeforeMgrResCCmd());		// 사용전검사합격관리
        commands.put("UseBeforeMgrResCUD",      new UseBeforeMgrResCUDCmd());  	// 사용전검사합격관리        
        
        commands.put("UseBeforeAttMgrCUD", 		new UseBeforeAttMgrCUDCmd());	// 사용전검사관리 첨부파일
        commands.put("UseBeforeAttMgr", 		new UseBeforeAttMgrCmd());		// 사용전검사관리 첨부파일
        commands.put("GroupWareMgr", 			new GroupWareMgrCmd());			// 사용전검사관리 첨부파일
        
        
        
        commands.put("UseBeforeRegInfo", 			new UseBeforeRegInfoCmd());			// 공사업조회     
    }   
    
    
    /**************************************************************************
     * 요청받은 명령의 Command instance를 구한다.
     * 
     * @param cmd 명령명
     * @return Command class instance
     *************************************************************************/
    private KJFCommand lookupCommand(String cmd) throws Exception {
        
        if (commands.containsKey(cmd)) {
            return (KJFCommand)commands.get(cmd);
            
        } else {
            throw new Exception("Invalid Command Identifier");
        }
    }

}
