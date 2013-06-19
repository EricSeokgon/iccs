package sp.chart;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kjf.action.KJFCommandResp;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import sp.action.KJFAction;
import sp.chart.cmd.ChartAreaCmd;
import sp.chart.cmd.ChartYearCmd;

/**
 * <p>Title       : ChartAction Class</p>
 * <p>Description : 차트 정보 처리를 한다.</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : 차트 정보 처리를 한다. 
 * 
 * @version 1.0
 * @author PKT
 */
public class ChartAction extends KJFAction {
    
    /** 명령패턴 class들 **/
    private HashMap<String, KJFCommandResp> commands; 

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
        KJFCommandResp cmd = lookupCommand(request.getParameter("cmd"));         

        return  cmd.execute(request, response, form); 
    }


    /**
     * Command들을 등록한다.
     * @param
     * @return
     */
    private void initCommands() {
        commands = new HashMap<String, KJFCommandResp>();      
        
        commands.put("ChartArea",     new ChartAreaCmd());  // 지역 차트 
        commands.put("ChartYear",     new ChartYearCmd());  // 년도 차트
    }    


    /**
     * 요청받은 명령의 Command instance를 구한다.
     * @param cmd 명령명
     * @return Command class instance
     */
    private KJFCommandResp lookupCommand(String cmd) throws Exception {
        
        if (commands.containsKey(cmd)) {
            return commands.get(cmd);
            
        } else {
            throw new Exception("Invalid Command Identifier:" + cmd );
        }
    }

}
