package sp.index;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kjf.action.KJFCommand;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import sp.action.KJFAction;
import sp.index.cmd.IndexMgrCmd;
import sp.index.cmd.IndexTongsinMgrCmd;

public class IndexAction extends KJFAction {

	/** 명령패턴 class들 **/
	private HashMap commands; 
        	
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

        commands = new HashMap();
 
        // 프로그램 등록
        commands.put("Index",	new IndexMgrCmd());
        commands.put("IndexTs",	new IndexTongsinMgrCmd());
        
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
