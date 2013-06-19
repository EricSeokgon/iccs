package sp.illegality;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kjf.action.KJFCommand;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import sp.action.KJFAction;
import sp.illegality.cmd.IllBusiSusListCmd;
import sp.illegality.cmd.IllBusiSusViewCmd;
import sp.illegality.cmd.IllCorOrderListCmd;
import sp.illegality.cmd.IllCorOrderViewCmd;
import sp.illegality.cmd.IllNegFineListCmd;
import sp.illegality.cmd.IllNegFineViewCmd;
import sp.illegality.cmd.IllProsecutionListCmd;
import sp.illegality.cmd.IllProsecutionViewCmd;
import sp.illegality.cmd.IllRegistCancelCmd;
import sp.illegality.cmd.IllWarningListCmd;
import sp.illegality.cmd.IllWarningViewCmd;
import sp.illegality.cmd.IllegalityStatusCmd;

/**
 * <p>Title       : IllegalityAction Class</p>
 * <p>Description : 위법관리 처리를 한다.</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : 공사업정보 위법관리 처리를 한다. 
 * 
 * @version 1.0
 * @author PKT
 */
public class IllegalityAction extends KJFAction {
    
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
      
        // 위법관리
        //commands.put("IllegalityStatus",    new IllegalityStatusCmd());     // 위법현황
        
        // 영업정지
        commands.put("IllBusiSusList",      new IllBusiSusListCmd());       // 영업정지 리스트
        commands.put("IllBusiSusView",      new IllBusiSusViewCmd());       // 영업정지 상세
        
        // 등록취소
        commands.put("IllRegistCancel",      new IllRegistCancelCmd());     // 등록취소
        
        // 과태료 부과
        commands.put("IllNegFineList",       new IllNegFineListCmd());      // 과태료부과 리스트
        commands.put("IllNegFineView",       new IllNegFineViewCmd());      // 과태료부과 상세
        
        // 경고조치
        commands.put("IllWarningList",       new IllWarningListCmd());      // 경고조치 리스트
        commands.put("IllWarningView",       new IllWarningViewCmd());      // 경고조치 상세
        
        // 시정명령
        commands.put("IllCorOrderList",     new IllCorOrderListCmd());      // 시정명령 리스트
        commands.put("IllCorOrderView",     new IllCorOrderViewCmd());      // 시정명령 상세
        
        // 형사고발
        commands.put("IllProsecutionList",  new IllProsecutionListCmd());   // 형사고발 리스트
        commands.put("IllProsecutionView",  new IllProsecutionViewCmd());   // 형사고발 상세      
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
