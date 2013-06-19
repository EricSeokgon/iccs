package sp.hms;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kjf.action.KJFCommand;
import kjf.util.KJFUtil;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import sp.action.KJFAction;
import sp.hms.cmd.HmsContentCUDCmd;
import sp.hms.cmd.HmsContentCmd;

/**
 * <p>Title       : HmsAction Class</p>
 * <p>Description : HTML 처리를 한다.</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : HTML 화면 처리를 한다. 
 * 
 * @version 1.0
 * @author PKT
 */
public class HmsAction extends KJFAction {
    
    /** 명령패턴 class들 **/
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
                                HttpServletResponse response) throws Exception {

        initCommands();
        KJFCommand cmd = lookupCommand( (KJFUtil.print(request.getParameter("cmd"), "HmsView")));

        return cmd.execute(request, form) ;
    }


    /**
     * Command들을 등록한다.
     * @param
     * @return
     */
    private void initCommands() {
        commands = new HashMap<String, KJFCommand>();      
        
        commands.put("HmsView",     new HmsContentCmd());      // HTML 메뉴코드 검색   
        commands.put("HmsWrite",    new HmsContentCmd());      // HTML 메뉴코드 검색   
        commands.put("HmsContentC", new HmsContentCUDCmd());   // HTML 메뉴코드 검색   
        
        
    }    


    /**
     * 요청받은 명령의 Command instance를 구한다.
     * @param cmd 명령명
     * @return Command class instance
     */
    private KJFCommand lookupCommand(String cmd) throws Exception {
        
        if (commands.containsKey(cmd)) {
            return commands.get(cmd);
            
        } else {
            throw new Exception("Invalid Command Identifier:" + cmd );
        }
    }
}
