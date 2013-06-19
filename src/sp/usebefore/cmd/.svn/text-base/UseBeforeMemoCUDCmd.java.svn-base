package sp.usebefore.cmd;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFDBUtil;
import kjf.util.KJFDate;
import kjf.util.KJFLog;

import kjf.util.KJFUtil;
import kjf.util.LoginException;

import sp.uent.UserEnt;
import sp.usebefore.UseBeforeParam;
import org.apache.struts.action.ActionForm;

/***************************************************************************
 * <p>Title       : UseBeforeCUDCmd Class</p>
 * <p>Description : 사용전검사 등록, 수정, 삭제 처리 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : 사용전검사 등록, 수정, 삭제 처리를 한다. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class UseBeforeMemoCUDCmd implements KJFCommand {
    
	UserEnt user;
	
    public UseBeforeMemoCUDCmd() {
        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {        
        
        String cmd = request.getParameter("cmd");
        String mode = request.getParameter("mode");
        user = (UserEnt)request.getSession().getAttribute("user");
        
        // 로그인 유무 체크
        if (KJFUtil.isEmpty(user)) {
            throw new LoginException(request, "회원용 페이지 로그인 체크", "../member/login.do");
        }  
        
        // 검색조건 설정및 체크
        UseBeforeParam pm = checkPm(request, form);
        
        if ("C".equals(mode)){
            insertExe(request,pm);    
        } else if ("U".equals(mode)){
        	updateExe(request,pm);  
        } else if ("D".equals(mode)){
        	deleteExe(request,pm);
        }
        
        loadData(request,pm);
        request.setAttribute("pm", pm);
        return request.getParameter("cmd");
    }

    /**
     * 사용전 검사 입력
     * @param Dataset ds, int arg_row
     * @return
     */
    private void insertExe(HttpServletRequest request,UseBeforeParam pm)throws Exception{
    	
			ReportDAO rDAO = new ReportDAO();      
	        StringBuffer sb = new StringBuffer(); 
	        
    	   	String SIDO_CODE 	= user.getSIDO_CODE();
			String SIGUNGU_CODE = user.getSIGUNGU_CODE();
			String scRECV_NUM 	= pm.getScRecv_num();
			
			String MEMO_DT		= request.getParameter("MEMO_DT");			
			String WRT_TIME		= request.getParameter("WRT_TIME");
			String MEMO_CONT		= request.getParameter("MEMO_CONT");			
			String SEQ			= getMaxSeq("PT_UB_MEMO",scRECV_NUM);
			
	        sb.append("insert into PT_UB_MEMO ");
	        sb.append(" ( MEMO_DT, MEMO_CONT, INS_DT, WRT_ID, SEQ, ");
	        sb.append("   RECV_NUM, SIDO_CODE, SIGUNGU_CODE, WRT_TIME, WRT_NAME )");
	        sb.append(" values (?,?,?,?,?, ?,?,?,?,?) ");
	        
	        
	        int i = 1;
	        rDAO.setValue(i++, MEMO_DT.replace("-", ""));
	        rDAO.setValue(i++, MEMO_CONT);
	        rDAO.setValue(i++, KJFDate.getCurTime("yyyy-MM-dd HH:mm:ss"));
	        rDAO.setValue(i++, user.getUSER_ID());
	        rDAO.setValue(i++, SEQ);	        
	        rDAO.setValue(i++, scRECV_NUM);
	        rDAO.setValue(i++, SIDO_CODE);
	        rDAO.setValue(i++, SIGUNGU_CODE);
	        rDAO.setValue(i++, WRT_TIME);
	        rDAO.setValue(i++, user.getUSER_NAME());
	        
	       rDAO.execute(sb.toString());
    }
    
    /**
     * 사용전 검사 수정
     * @param Dataset ds, int arg_row
     * @return
     */
    private void updateExe(HttpServletRequest request,UseBeforeParam pm)throws Exception{
    	
			ReportDAO rDAO = new ReportDAO();      
	        StringBuffer sb = new StringBuffer(); 
	        
    	   	String SIDO_CODE 	= user.getSIDO_CODE();
			String SIGUNGU_CODE = user.getSIGUNGU_CODE();
			String scRECV_NUM 	= pm.getScRecv_num();
			
			String MEMO_DT		= request.getParameter("MEMO_DT");			
			String WRT_TIME		= request.getParameter("WRT_TIME");
			String MEMO_CONT	= request.getParameter("MEMO_CONT");			
			String SEQ			= request.getParameter("SEQ");
			
	        sb.append(" update PT_UB_MEMO ");
	        sb.append(" set MEMO_DT = ?, ");
	        sb.append(" WRT_TIME = ?, ");
	        sb.append(" UPD_DT = ?, ");
	        sb.append(" WRT_ID = ?, ");
	        sb.append(" MEMO_CONT = ? ");
	        sb.append(" where RECV_NUM =? and SEQ = ?");
	        
	        int i = 1;
	        rDAO.setValue(i++, MEMO_DT.replace("-", ""));
	        rDAO.setValue(i++, WRT_TIME);
	        rDAO.setValue(i++, KJFDate.getCurTime("yyyy-MM-dd HH:mm:ss"));
	        rDAO.setValue(i++, user.getUSER_ID());
	        rDAO.setValue(i++, MEMO_CONT);	        
	        rDAO.setValue(i++, scRECV_NUM);
	        rDAO.setValue(i++, SEQ);
	        
	       rDAO.execute(sb.toString());
    }


    /**
     * 사용전 검사 조회
     * @param Dataset ds, int arg_row
     * @return
     */
    private void loadData(HttpServletRequest request,UseBeforeParam pm)throws Exception{
    	
			ReportDAO rDAO 			= new ReportDAO();      
			ReportEntity mEntity 	= new ReportEntity();
	        StringBuffer sb 		= new StringBuffer(); 
	        
			String scRECV_NUM 		= pm.getScRecv_num();
			
	        sb.append(" SELECT SEQ, MEMO_DT, WRT_TIME, MEMO_CONT, WRT_NAME, INS_DT FROM  PT_UB_MEMO ");
	        sb.append(" WHERE RECV_NUM = ? ORDER BY SEQ ");
	        
	        rDAO.setValue(1, scRECV_NUM);
	        
	        mEntity = rDAO.select(sb.toString());
	        
	        request.setAttribute("mEntity", mEntity);
    }
    
    /**
     * 삭제
     * @param HttpServletRequest
     * @return
     */
    private void deleteExe(HttpServletRequest request, UseBeforeParam pm)throws Exception{
		ReportDAO rDAO 			= new ReportDAO();        
    	String strtemp			= request.getParameter("chk");
    	String str[] 			= strtemp.substring(0, strtemp.length()-1).split(",");
    	String scRECV_NUM 		= pm.getScRecv_num();
    	
    	String SQL		= " DELETE FROM PT_UB_MEMO WHERE RECV_NUM = '"+scRECV_NUM+"' AND SEQ = ? ";
    	
    	for(int i = 0; i < str.length; i++){
    		rDAO.setValue(1, str[i]);	        
    		rDAO.execute(SQL);
    	}

    }     
    /**
     * _SEQ 최대값을 가져오는 메소드 
     * 
     * @param BOARD_ID
     * @param CT_ID
     * @return String 
     * @throws Exception
     */
     private String getMaxSeq(String TABNAME, String scRECV_NUM) throws Exception {
         
         ReportDAO    rDAO    = new ReportDAO();
         ReportEntity rEntity = null;
         
         StringBuffer sbSQL = new StringBuffer();        
         sbSQL.append(" SELECT " + KJFDBUtil.makeSql_nullFunc() + "  \n");
         sbSQL.append("        ( MAX( SEQ ), 0 ) + 1  MAXSEQ    \n");
         sbSQL.append("   FROM " + TABNAME + "                      \n");
         sbSQL.append("  WHERE RECV_NUM = ?                           \n");
         
         rDAO.setValue(1, scRECV_NUM);     

         rEntity = rDAO.select(sbSQL.toString());

         return rEntity.getValue(0, "MAXSEQ");
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
        pm.setScRecv_num(request.getParameter("scRECV_NUM"));
        
        // 파라미터 디버깅
        KJFLog.debug(pm.toString(request));
        
        return pm;
    }

}
