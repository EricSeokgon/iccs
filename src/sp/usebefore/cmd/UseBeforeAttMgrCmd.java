package sp.usebefore.cmd;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.swing.ImageIcon;

import kjf.action.KJFCommand;
import kjf.cfg.Config;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFDBUtil;
import kjf.util.KJFDate;
import kjf.util.KJFFile;
import kjf.util.KJFLog;
import kjf.util.KJFUtil;
import kjf.util.LoginException;
import kjf.util.MsgException;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import sp.uent.UserEnt;
import sp.usebefore.UseBeforeParam;

/**
 * <p>Title       : UseBeforeAttacheCUDCmd Class</p>
 * <p>Description : 사용전검사관리 첨부파일</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : 사용전검사관리 첨부파일 등록, 수정, 삭제 처리를 한다.
 * 
 * @version 1.0
 * @author  PKT
 */
public class UseBeforeAttMgrCmd implements KJFCommand {
	
	UserEnt user;
	int attache_num = 10;
    int fileSize = 10;   // default 10M
    
    public UseBeforeAttMgrCmd() {
    }

    public String execute(HttpServletRequest request, ActionForm form) throws Exception {

        String cmd = request.getParameter("cmd");
        
        user = (UserEnt)request.getSession().getAttribute("user");
        
        // 로그인 유무 체크
        if (KJFUtil.isEmpty(user)) {
            throw new LoginException(request, "회원용 페이지 로그인 체크", "../member/login.do");
        }  
        
        // 검색조건 설정및 체크
        UseBeforeParam pm = checkPm(request, form);
        
    	// 사용전검사 첨부파일 조회
    	LoadFileData(request,pm);

    	request.setAttribute("pm", pm);
        return request.getParameter("cmd");
    }
    /**
     * 사용전검사 첨부파일 조회
     * 
     * @param request
     * @param form
     * @throws Exception
     */	
	  private void LoadFileData(HttpServletRequest request, UseBeforeParam pm) throws Exception {
	        
	        ReportDAO rDAO = new ReportDAO();
	        
	        ReportEntity rEntity = null;
	        
	        String RECV_NUM = pm.getScRecv_num();
	        String SIDO_CODE = user.getSIDO_CODE();
	        String SIGUNGU_CODE = user.getSIGUNGU_CODE();
	        
	        StringBuffer sbSQL = new StringBuffer();        
	        sbSQL.append(" SELECT                   \n");
	        sbSQL.append("        '0' AS CHECKER,    \n");
	        sbSQL.append("        SEQ,            \n");
	        sbSQL.append("        RECV_NUM,        \n");
	        sbSQL.append("        FILE_NM,   \n");
	        sbSQL.append("        FILE_SIZE,        \n");
	        sbSQL.append("        INS_DT,           \n");
	        sbSQL.append("        SYS_FILE_NAME, SIDO_CODE,SIGUNGU_CODE, \n");
	        sbSQL.append("        nvl((select server_addr from pt_s_sysvar_master where sigungu_code = '"+SIGUNGU_CODE+"' and server_addr is not null),'') as server_addr \n"); 
	        sbSQL.append("   FROM PT_UB_FILE     \n");
	        sbSQL.append("  WHERE RECV_NUM = ?         \n");
	        sbSQL.append("    AND SIDO_CODE = ?     \n");
	        sbSQL.append("    AND SIGUNGU_CODE = ?     \n");

	        rDAO.setValue(1, RECV_NUM);
	        rDAO.setValue(2, SIDO_CODE);
	        rDAO.setValue(3, SIGUNGU_CODE);
	        
	        rEntity = rDAO.select(sbSQL.toString());
	        
	        request.setAttribute("FileEntity", rEntity);
	    }    
	
    
    /**
     * Max ID를 가져온다.
     * @param   String as_Table, String  as_KeyCol
     * @return   String
     */
     public  static String getMaxID( String  as_KeyCol, String as_Table) throws  Exception{

         String sql = "";
         ReportDAO rDAO = new ReportDAO();

         switch (rDAO.db_type){

         case 2: //Ms-sql
         	sql =
 	        	" SELECT														\n" +
 	        	"     ISNULL(MAX(convert(numeric,"+as_KeyCol+")),0)+1  MAX_ID   \n" +
 	        	" FROM "+as_Table+"												\n" ;
 	        break;
 	    default:
         	sql =
 	        	//" SELECT SEQ_PT_SM_USER_LOG.NEXTVAL AS MAX_ID FROM DUAL           		\n" ;
         		" SELECT														\n" +
 	        	"     NVL( MAX( to_number("+as_KeyCol+") ), 0 ) + 1   MAX_ID    \n" +
 	        	" FROM "+as_Table+"												\n" ;

 	    	break;
         }
         
         ReportEntity rEntity     = null;
         
         rEntity = rDAO.select(sql);

         return rEntity.getValue(0,"MAX_ID");
     }   
     
     /**
      * 폼 체크 메소드
      * 
      * @param request
      * @param form
      * @return
      * @throws Exception
      */
     private UseBeforeParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {
         UseBeforeParam pm = (UseBeforeParam)form;
        
         pm.setScRecv_num(KJFUtil.print(pm.getScRecv_num(),request.getParameter("scRECV_NUM")));
         
         // 파라미터 디버깅
         KJFLog.debug(pm.toString(request));
         return pm;
     }     
}