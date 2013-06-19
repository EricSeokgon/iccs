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
 * <p>Description : 공사업정보검색 처리를 한다.</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : 공사업정보검색 처리를 한다. 
 * 
 * @version 1.0
 * @author PKT
 */
public class ComInfoAction extends KJFAction {
    
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
      
        // 공사업정보조회
        // 일반사용자용
        commands.put("InfoCommTrader",      new InfoCommTraderCmd());       // 정보통신공사업자 검색
        commands.put("InfoExecValuation",   new InfoExecValuationCmd());    // 시공능력평가
        commands.put("InfoAdmMeasure",      new InfoAdmMeasureCmd());       // 행정처분
        //공무원용
        commands.put("InfoCommTraderPub",      new InfoCommTraderPubCmd());    // 정보통신공사업자 검색(공무원용)
        commands.put("InfoCommTraderPubV",     new InfoCommTraderPubVCmd());   // 정보통신공사업자 검색(공무원용)
        commands.put("InfoExecValuationPub",   new InfoExecValuationPubCmd());    // 시공능력평가
        commands.put("InfoAdmMeasurePub",      new InfoAdmMeasurePubCmd());       // 행정처분     

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
