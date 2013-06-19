package sp.login.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kjf.action.KJFCommandResp;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFUtil;

import org.apache.struts.action.ActionForm;

import sp.login.LoginWorker;

/****************************************************************************
 * <p>Title       : LoginCmd Class</p>
 * <p>Description : 로그인 처리 프로세스 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 *
 * NOTE : 로그인 처리를 한다.
 *
 * @version 1.0
 * @author  PKT
 ***************************************************************************/
public class PdaLoginMgrCmd implements KJFCommandResp {

    
	public PdaLoginMgrCmd() {
    }
    
    public String execute(HttpServletRequest request, HttpServletResponse response,  ActionForm form) throws Exception {
    	
    	String cmd       = request.getParameter("cmd");
    	loginprocess(request,"admin");
        return cmd;
    }
    
    /**
     * 이전페이지 URL 체크
     * 
     * @param request
     * @return String
     * @throws Exception
     */
    public void loginprocess(HttpServletRequest request, String isWhere) throws  Exception{
    	
    	ReportDAO    rDAO        = new ReportDAO();
        ReportEntity rEntity     = null;
        ReportEntity idEntity     = null;
        ReportEntity pwEntity     = null;
         
        String result = "";
        boolean yn = true;
        
        String scUSER_ID = request.getParameter("id");
        String scPASSWD = request.getParameter("pass");
    	
        String CNT = "SELECT COUNT(*) AS CNT FROM PT_MI_USER WHERE OFFI_ID =? ";
        int cnt=1;
        rDAO.setValue(cnt++, scUSER_ID);
        idEntity = rDAO.select(CNT);
        
        if(KJFUtil.str2int(idEntity.getValue(0,"CNT")) == 0){
        	result =  "IDERR|";
        	yn = false;
        }
        if(yn){
	        CNT = "SELECT COUNT(*) AS CNT FROM PT_MI_USER WHERE OFFI_ID =?  AND PASS = ?"; 	
	        cnt=1;
	        rDAO.setValue(cnt++, scUSER_ID);
	        rDAO.setValue(cnt++, scPASSWD);
	        pwEntity = rDAO.select(CNT);
	        
	        if(KJFUtil.str2int(pwEntity.getValue(0,"CNT")) == 0){
	        	result =  "PASSERR|";
	        	yn = false;
	        }
	        
	        if(yn){
		        String selectSQL =
		
		          	 "SELECT MU.SIDO_CODE, SC.SIDO_NM, MU.SIGUNGU_CODE,  \n" +
		          	 "	     SC.SIGUNGU_NM, MU.NM,PART,'1.1' AS VERSION    \n"+	
		          	 "FROM PT_MI_USER  MU, PT_SIDO_CODE SC  		\n"+	
		          	 "WHERE OFFI_ID =?   		\n"+
		          	 "  AND PASS = ? 	  \n" +
		          	 "  AND MU.SIGUNGU_CODE = SC.AREA_CODE  \n";	
		        
		        int cnt2=1;
		        rDAO.setValue(cnt2++, scUSER_ID);
		        rDAO.setValue(cnt2++, scPASSWD);
		        rEntity = rDAO.select(selectSQL);
		        request.setAttribute("rEntity", rEntity);
	        }
        }
        request.getSession().setAttribute("result", result);

		
        
    }
}