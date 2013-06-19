package sp.search;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kjf.action.KJFCommand;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import sp.action.KJFAction;
import sp.search.cmd.BoardSearchCmd;
import sp.search.cmd.FaqUnifiedSearchCmd;
import sp.search.cmd.FormSearchCmd;
import sp.search.cmd.UnifiedSearchCmd;
import sp.search.cmd.WebPageSearchCmd;

/**
 * <p>Title       : BbsAction Class</p>
 * <p>Description : 통합검색 처리를 한다.</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : X-Ten 검색 처리를 한다. 
 * 
 * @version 1.0
 * @author PKT
 */
public class SearchAction extends KJFAction {
    
    /** 명령패턴 class들 **/
    private HashMap<String, KJFCommand> commands; 
            
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
        
        KJFCommand cmd = lookupCommand(request.getParameter("cmd"));

        return cmd.execute(request, form) ;
    } 
    
    
    /**
     * Command들을 등록한다.
     * 
     * @param 
     * @return       
     */
    private void initCommands() {

        commands = new HashMap<String, KJFCommand>();
      
        // 프로그램 등록
        commands.put("UnifiedSearch",   	new UnifiedSearchCmd());    // 통합검색
        commands.put("WebPageSearch",  	 	new WebPageSearchCmd());    // 웹페이지 검색
        commands.put("BoardSearch",     	new BoardSearchCmd());      // 게시판 검색
        commands.put("FormSearch",      	new FormSearchCmd());       // 서식자료 검색
        commands.put("FaqUnifiedSearch",	new FaqUnifiedSearchCmd());	// FAQ 통합검색
    }   
    
    
    /**
     * 요청받은 명령의 Command instance를 구한다.
     * 
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
