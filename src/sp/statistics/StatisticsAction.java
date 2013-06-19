package sp.statistics;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kjf.action.KJFCommand;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import sp.action.KJFAction;
import sp.statistics.cmd.StatisticalDataCmd;

/**
 * <p>Title       : StatisticsAction Class</p>
 * <p>Description : ��� ���� ó���� �Ѵ�.</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : ��� ���� ó���� �Ѵ�. 
 * 
 * @version 1.0
 * @author PKT
 */
public class StatisticsAction extends KJFAction {
    
    private HashMap<String, KJFCommand> commands; //������� class��

    /**
     * ����Command���� �����Ѵ�.
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
            
            KJFCommand cmd = lookupCommand(request.getParameter("cmd"));

            return cmd.execute(request, form) ;
    }

    /**
     * Command���� ����Ѵ�.
     * @param
     * @return
     */
    private void initCommands(){

        commands = new HashMap<String,KJFCommand>();
         
        commands.put("StatisticalData", new StatisticalDataCmd());  // �������
    }    


    /**
     * ��û���� ����� Command instance�� ���Ѵ�.
     * @param cmd ��ɸ�
     * @return Command class instance
     */
    private KJFCommand lookupCommand(String cmd) throws Exception {
        
        if (commands.containsKey(cmd)) {
            return (KJFCommand)commands.get(cmd);
            
        } else {
            throw new Exception("Invalid Command Identifier:" + cmd );
        }
    }
}