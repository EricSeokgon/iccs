package sp.regmgr;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kjf.action.KJFCommand;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import sp.action.KJFAction;
import sp.regmgr.cmd.PubWorkAffiliationCmd;
import sp.regmgr.cmd.PubWorkChReportCmd;
import sp.regmgr.cmd.PubWorkRegInfoCmd;
import sp.regmgr.cmd.PubWorkRegReportListCmd;
import sp.regmgr.cmd.PubWorkRegReportViewCmd;
import sp.regmgr.cmd.PubWorkTransferCmd;

/****************************************************************************
 * <p>Title       : RegMgrAction Class</p>
 * <p>Description : 등록관리 처리를 하는 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 *
 * NOTE : 등록관리 정보를 처리한다.
 *
 * @version
 * @author PKT
 ***************************************************************************/
public class RegMgrAction extends KJFAction {
    
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
      
        // 등록 관리
        commands.put("PubWorkRegInfo",          new PubWorkRegInfoCmd());       // 공사업 등록정보
        commands.put("PubWorkRegReportList",    new PubWorkRegReportListCmd()); // 공사업 등록기준 신고 리스트
        commands.put("PubWorkRegReportView",    new PubWorkRegReportViewCmd()); // 공사업 등록기준 신고 상세
        commands.put("PubWorkTransfer",         new PubWorkTransferCmd());      // 공사업 양도양수
        commands.put("PubWorkAffiliation",      new PubWorkAffiliationCmd());   // 공사업 합병
        commands.put("PubWorkChReport",         new PubWorkChReportCmd());      // 공사업 변경신고
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
