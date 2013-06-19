package sp.comm;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kjf.action.KJFCommand;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import sp.action.KJFAction;
import sp.comm.cmd.CommDuplIdCheckCmd;
import sp.comm.cmd.CommPubRecCheckCmd;
import sp.comm.cmd.CommSMSSendCmd;
import sp.comm.cmd.CommSigunguCodeCmd;
import sp.comm.cmd.CommZipSearchCmd;

/**
 * <p>Title       : CommAction Class</p>
 * <p>Description : 공통 정보 처리를 한다.</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : 공통 정보 처리를 한다. 
 * 
 * @version 1.0
 * @author PKT
 */
public class CommAction extends KJFAction {
    
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
         
        commands.put("CommSigunguCode", new CommSigunguCodeCmd());  // 시군구 코드 검색
        commands.put("CommZipSearch",   new CommZipSearchCmd());    // 우편번호 검색       
        
        commands.put("CommDuplIDCheck", new CommDuplIdCheckCmd());  // 아이디 중복 검사
        commands.put("CommPubRegCheck", new CommPubRecCheckCmd());  // 공사업등록 확인 
        
        commands.put("CommSMSSend",     new CommSMSSendCmd());      // SMS전송 
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
