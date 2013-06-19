package sp.stwork;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kjf.action.KJFCommand;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import sp.action.KJFAction;
import sp.stwork.cmd.StWorkPlanInfoCUDCmd;
import sp.stwork.cmd.StWorkPlanInfoListCmd;
import sp.stwork.cmd.StWorkPlanInfoViewCmd;

/****************************************************************************
 * <p>Title       : StworkAction Class</p>
 * <p>Description : 착공전설계도확인 처리를 하는 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 *
 * NOTE : 착공전설계도확인 정보를 처리한다.
 *
 * @version
 * @author PKT
 ***************************************************************************/
public class StworkAction extends KJFAction {
    
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
        
        // 착공전 설계도 확인
        commands.put("StWorkPlanInfoList",	new StWorkPlanInfoListCmd());	// 착공전 설계도 확인현황  리스트
        commands.put("StWorkPlanInfoView",	new StWorkPlanInfoViewCmd());	// 착공전 설계도 확인현황 상세
        
        commands.put("StWorkPlanInfoC",     new StWorkPlanInfoCUDCmd());    // 착공전설계도확인 등록
        commands.put("StWorkPlanInfoD",     new StWorkPlanInfoCUDCmd());    // 착공전설계도확인 삭제
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
            throw new Exception("Invalid Command Identifier"+cmd);
        }
    }

}
