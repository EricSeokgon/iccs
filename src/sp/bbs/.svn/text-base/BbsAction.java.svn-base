package sp.bbs ;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kjf.action.KJFCommand;
import kjf.util.KJFUtil;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import sp.action.KJFAction;
import sp.bbs.cmd.BbsKCUDCmd;
import sp.bbs.cmd.BbsKListCmd;
import sp.bbs.cmd.BbsKPortalCmd;
import sp.bbs.cmd.BbsKViewCmd;
import sp.bbs.cmd.BbsKWriteCmd;
import sp.bbs.cmd.BbsKscFAQCdCmd;
import sp.bbs.cmd.BbsKscSggCdCmd;
import sp.bbs.cmd.BbsKFrameCmd;

/**
 * <p>Title       : BbsAction Class</p>
 * <p>Description : 게시판 정보 처리를 한다.</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : 게시판 정보 처리를 한다. 
 * 
 * @version 1.0
 * @author PKT
 */
public class BbsAction extends KJFAction{

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
            KJFCommand cmd = lookupCommand( (KJFUtil.print(request.getParameter("cmd"),"BbsKList")));

	        return cmd.execute(request, form) ;
	}

    /**
     * Command들을 등록한다.
     * @param
     * @return
     */
    private void initCommands(){

        commands = new HashMap<String,KJFCommand>();
        
        // 게시판 기본
        commands.put("BbsKList",        new BbsKListCmd());     // 게시판 리스트
        commands.put("BbsKWrite",       new BbsKWriteCmd());    // 게시판 글쓰기
        commands.put("BbsKView",        new BbsKViewCmd());     // 게시판 상세보기       
       
        commands.put("BbsKC",           new BbsKCUDCmd());      // 게시글 등록
        commands.put("BbsKR",           new BbsKCUDCmd());      // 게시글  답변
        commands.put("BbsKU",           new BbsKCUDCmd());      // 게시글  수정
        commands.put("BbsKD",           new BbsKCUDCmd());      // 게시글 삭제
        commands.put("BbsLD",           new BbsKCUDCmd());      // 게시글 삭제
        
        
        commands.put("BbsKCC",          new BbsKCUDCmd());     // 한줄답변 입력 
        commands.put("BbsKCU",          new BbsKCUDCmd());     // 한줄답변 수정
        commands.put("BbsKDC",          new BbsKCUDCmd());     // 한줄답변 삭제
        commands.put("BbsKFileDel",     new BbsKCUDCmd());      // 첨부파일 삭제
        
        // 시도/시군구 코드 
        commands.put("BbsLSggCd",       new BbsKscSggCdCmd());  // 시군구 코드 검색        
        commands.put("BbsWSggCd",       new BbsKscSggCdCmd());  // 시군구 코드 검색
        
        // 질의회신 분류 코드 
        commands.put("BbsLFAQCd",       new BbsKscFAQCdCmd());  // 질의회신 코드 검색        
        commands.put("BbsWFAQCd",       new BbsKscFAQCdCmd());  // 질의회신 코드 검색
        
        commands.put("BbsKPortal",      new BbsKPortalCmd());   // 포탈 리스트
        commands.put("BbsKFrame",      new BbsKFrameCmd());   // 포탈 리스트
        
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
