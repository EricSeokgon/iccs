package sp.cominfo;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kjf.action.KJFCommand;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import sp.action.KJFAction;
import sp.cominfo.cmd.InfoAdmMeasureCmd;
import sp.cominfo.cmd.InfoAdmMeasurePubCmd;
import sp.cominfo.cmd.InfoCommTraderCmd;
import sp.cominfo.cmd.InfoCommTraderPubCmd;
import sp.cominfo.cmd.InfoCommTraderPubVCmd;
import sp.cominfo.cmd.InfoExecValuationPubCmd;

import sp.cominfo.cmd.InfoExecValuationCmd;

/**
 * <p>Title       : ComInfoAction Class</p>
 * <p>Description : ����������˻� ó���� �Ѵ�.</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : ����������˻� ó���� �Ѵ�. 
 * 
 * @version 1.0
 * @author PKT
 */
public class ComInfoAction extends KJFAction {
    
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
      
        // �����������ȸ
        // �Ϲݻ���ڿ�
        commands.put("InfoCommTrader",      new InfoCommTraderCmd());       // ������Ű������ �˻�
        commands.put("InfoExecValuation",   new InfoExecValuationCmd());    // �ð��ɷ���
        commands.put("InfoAdmMeasure",      new InfoAdmMeasureCmd());       // ����ó��
        //��������
        commands.put("InfoCommTraderPub",      new InfoCommTraderPubCmd());    // ������Ű������ �˻�(��������)
        commands.put("InfoCommTraderPubV",     new InfoCommTraderPubVCmd());   // ������Ű������ �˻�(��������)
        commands.put("InfoExecValuationPub",   new InfoExecValuationPubCmd());    // �ð��ɷ���
        commands.put("InfoAdmMeasurePub",      new InfoAdmMeasurePubCmd());       // ����ó��     

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
