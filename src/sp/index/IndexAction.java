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

	/** ������� class�� **/
	private HashMap commands; 
        	
    /**************************************************************************
     * ����Command���� �����Ѵ�.<br>
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
     * Command���� ����Ѵ�.
     * 
     * @param 
     * @return       
     *************************************************************************/
    private void initCommands() {

        commands = new HashMap();
 
        // ���α׷� ���
        commands.put("Index",	new IndexMgrCmd());
        commands.put("IndexTs",	new IndexTongsinMgrCmd());
        
    }	
	
	
    /**************************************************************************
     * ��û���� ����� Command instance�� ���Ѵ�.
     * 
     * @param cmd ��ɸ�
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
