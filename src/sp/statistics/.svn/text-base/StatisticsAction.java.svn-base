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
 * <p>Description : 통계 정보 처리를 한다.</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : 통계 정보 처리를 한다. 
 * 
 * @version 1.0
 * @author PKT
 */
public class StatisticsAction extends KJFAction {
    
    private HashMap<String, KJFCommand> commands; //명령패턴 class들

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
            
            KJFCommand cmd = lookupCommand(request.getParameter("cmd"));

            return cmd.execute(request, form) ;
    }

    /**
     * Command들을 등록한다.
     * @param
     * @return
     */
    private void initCommands(){

        commands = new HashMap<String,KJFCommand>();
         
        commands.put("StatisticalData", new StatisticalDataCmd());  // 통계정보
    }    


    /**
     * 요청받은 명령의 Command instance를 구한다.
     * @param cmd 명령명
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