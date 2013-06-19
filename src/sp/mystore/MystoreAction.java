package sp.mystore;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kjf.action.KJFCommand;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import sp.action.KJFAction;
import sp.mystore.cmd.MyProgressStateCmd;
import sp.mystore.cmd.MySecedeCmd;
import sp.mystore.cmd.MyUseStoreCUDCmd;
import sp.mystore.cmd.MyUseStoreCmd;
import sp.mystore.cmd.MyUseStoreViewCmd;

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
public class MystoreAction extends KJFAction {
    
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
        commands.put("MyProState",   	new MyProgressStateCmd()); 	// 나의 민원진행상태
        commands.put("MyUseStoreView",	new MyUseStoreViewCmd());  	// 내가 자주가는 창구 보기
        commands.put("MyUseStore",   	new MyUseStoreCmd());      	// 내가 자주가는 창구
        commands.put("MyUseStoreCUD", 	new MyUseStoreCUDCmd());	// 내가 자주가는 창구 저장
        commands.put("MySecede",     	new MySecedeCmd());        	// 회원탈퇴
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
