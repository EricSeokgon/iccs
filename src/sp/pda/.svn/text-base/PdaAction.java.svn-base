package sp.pda;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kjf.action.KJFCommand;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import sp.action.KJFAction;
import sp.pda.cmd.*;

/****************************************************************************
 * <p>Title       : IndexCmd Class</p>
 * <p>Description : My민원창구 처리를 하는 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 *
 * NOTE : My민원창구 정보를 처리한다.
 *
 * @version
 * @author PKT
 ***************************************************************************/
public class PdaAction extends KJFAction {
    
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

        return cmd.execute(request, form) ;
    } 
    
    
    /**************************************************************************
     * Command들을 등록한다.
     * 
     * @param 
     * @return       
     *************************************************************************/
    private void initCommands() {

        commands = new HashMap<String, KJFCommand>();
      
        // 프로그램 등록
        commands.put("Pdalog1Mgr",   	new Pdalog1MgrCmd()); // 사용전 검사 리스트
        commands.put("Pdaimg0Mgr",   	new Pdaimg0MgrCmd()); // 구군청 직인
        commands.put("Pdaimg1Mgr",   	new Pdaimg1MgrCmd()); // 개인 직인
        commands.put("Pdaimg2Mgr",   	new Pdaimg2MgrCmd()); // 사인 이미지 받기
        commands.put("PdaApprMgr",   	new PdaApprMgrCmd()); // 구군청 직인
        commands.put("Pdalog1Mgr",   	new Pdalog1MgrCmd()); // 사용전 검사 리스트
        commands.put("PdaSingimgMgr",   new PdaSingimgMgrCmd()); // 사인 이미지 저장
        commands.put("PdaApprMgrCUD",   new PdaApprMgrCUDCmd()); // 사용전 검사 내용저장
        commands.put("PdaSearchComMgr",   new PdaSearchComMgrCmd()); // 시공자정보조회
        commands.put("PdaSearchEngMgr",   new PdaSearchEngMgrCmd()); // 기술자정보조회
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
