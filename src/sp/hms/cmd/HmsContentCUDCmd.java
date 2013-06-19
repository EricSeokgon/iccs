package sp.hms.cmd;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFDBUtil;
import kjf.util.KJFDate;
import kjf.util.KJFUtil;
import kjf.util.LoginException;

import org.apache.struts.action.ActionForm;

import sp.uent.UserEnt;

/***************************************************************************
 * <p>Title       : HmsContentCmd Class</p>
 * <p>Description : HTML 컨텐츠 관리 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : HTML 컨텐츠 등록,수정,삭제 처리를 한다. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class HmsContentCUDCmd implements KJFCommand {
    
    public HmsContentCUDCmd() {        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception { 
        
        String cmd = request.getParameter("cmd"); 
        
        UserEnt user = (UserEnt)request.getSession().getAttribute("user");
        
        // 관리자 유무 체크
        if ( user == null || !user.isAdmin() ) {
            throw new LoginException(request, "관리자용 페이지 로그인 체크", "../member/login.do");
        }  
        
        if(cmd.equals("HmsContentC")) { // HTML 컨텐츠 등록
            changeAllHistoruUseCode(request);
            insertHistoryExe(request);
        }
        
        return request.getParameter("cmd");
    }
    
    /************************************************************************
     * HTML 히스토리 전체 사용버전 변경 메소드 
     * 
     * @param HttpServletRequest
     * @return done
     ***********************************************************************/
    private void changeAllHistoruUseCode(HttpServletRequest request) throws Exception {
        
        String MENU_ID = request.getParameter("menu_id");
        
        if (KJFUtil.isEmpty(MENU_ID)) {
            return;
        }
        
        ReportDAO rDAO = new ReportDAO();
      
        StringBuffer sbSQL = new StringBuffer();         
        sbSQL.append(" UPDATE PT_HMS_HISTORY SET    \n");        
        sbSQL.append("        USE_YN  = ?           \n");        
        sbSQL.append("  WHERE MENU_ID = ?           \n");

        int i = 0;

        rDAO.setValue(++i, "N");
        rDAO.setValue(++i, MENU_ID);
        
        rDAO.execute(sbSQL.toString());
    }
    
    /************************************************************************
     * HTML 히스토리 등록 처리 메소드 
     * 
     * @param HttpServletRequest
     * @return done
     ***********************************************************************/
    private void insertHistoryExe(HttpServletRequest request) throws Exception {
        
        ReportDAO rDAO = new ReportDAO();
        String MENU_ID = request.getParameter("menu_id"); 
        
        if (KJFUtil.isEmpty(MENU_ID)) {
            return;
        }
        
        UserEnt userEnt = (UserEnt)request.getSession().getAttribute("user");
        
        String SEQ     = KJFDBUtil.getMaxID("SEQ", "PT_HMS_HISTORY");       
        String Version = getVersion(MENU_ID);
        
        StringBuffer sbSQL = new StringBuffer(); 
        sbSQL.append(" INSERT INTO PT_HMS_HISTORY (     \n");
        sbSQL.append("       MENU_ID,                   \n");
        sbSQL.append("       SEQ,                       \n");
        sbSQL.append("       VERSION,                   \n");        
        sbSQL.append("       CONTENT,                   \n");
        
        sbSQL.append("       USE_YN,                    \n");
        sbSQL.append("       WRT_ID,                    \n");
        sbSQL.append("       WRT_NM,                    \n");
        sbSQL.append("       INS_DT                     \n");
        sbSQL.append("       )                          \n");
        
        sbSQL.append("   values (                       \n");
        sbSQL.append("            ?,?,?,?,              \n");
        sbSQL.append("            ?,?,?,?               \n");
        sbSQL.append("          )                       \n");   

        int i = 0;
    
        rDAO.setValue(++i, MENU_ID);
        rDAO.setValue(++i, SEQ);        
        rDAO.setValue(++i, Version);
        rDAO.setValue(++i, request.getParameter("CONTENT"));      
        
        rDAO.setValue(++i, "Y");
        rDAO.setValue(++i, userEnt.getUSER_ID());
        rDAO.setValue(++i, userEnt.getUSER_NAME());
        rDAO.setValue(++i, KJFDate.getCurTime("yyyy-MM-dd HH:mm:ss"));

        rDAO.execute(sbSQL.toString());
    }
    
    
    /************************************************************************
     * HTML 히스토리 버전정보를 가져오는 메소드 
     * 
     * @param HttpServletRequest
     * @return done
     ***********************************************************************/
    private String getVersion(String MENU_ID) throws Exception {
        
        ReportDAO rDAO = new ReportDAO();
        ReportEntity rEntity = null;
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append("  SELECT NVL( MAX( to_number(VERSION) ), 1 ) + 0.1 MAX_ID \n");
        sbSQL.append("    FROM PT_HMS_HISTORY       \n");
        sbSQL.append("   WHERE MENU_ID = ?          \n");
        
        rDAO.setValue(1, MENU_ID);
        
        rEntity = rDAO.select(sbSQL.toString());

        return rEntity.getValue(0, "MAX_ID");
    }
}
