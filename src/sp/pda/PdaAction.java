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
 * <p>Description : My�ο�â�� ó���� �ϴ� Ŭ����</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 *
 * NOTE : My�ο�â�� ������ ó���Ѵ�.
 *
 * @version
 * @author PKT
 ***************************************************************************/
public class PdaAction extends KJFAction {
    
    /** ������� class�� **/
    private HashMap<String, KJFCommand> commands; 
            
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

        commands = new HashMap<String, KJFCommand>();
      
        // ���α׷� ���
        commands.put("Pdalog1Mgr",   	new Pdalog1MgrCmd()); // ����� �˻� ����Ʈ
        commands.put("Pdaimg0Mgr",   	new Pdaimg0MgrCmd()); // ����û ����
        commands.put("Pdaimg1Mgr",   	new Pdaimg1MgrCmd()); // ���� ����
        commands.put("Pdaimg2Mgr",   	new Pdaimg2MgrCmd()); // ���� �̹��� �ޱ�
        commands.put("PdaApprMgr",   	new PdaApprMgrCmd()); // ����û ����
        commands.put("Pdalog1Mgr",   	new Pdalog1MgrCmd()); // ����� �˻� ����Ʈ
        commands.put("PdaSingimgMgr",   new PdaSingimgMgrCmd()); // ���� �̹��� ����
        commands.put("PdaApprMgrCUD",   new PdaApprMgrCUDCmd()); // ����� �˻� ��������
        commands.put("PdaSearchComMgr",   new PdaSearchComMgrCmd()); // �ð���������ȸ
        commands.put("PdaSearchEngMgr",   new PdaSearchEngMgrCmd()); // �����������ȸ
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
