/**
 * 파일명   : GCertRegCmd.java 
 * 설명     : 로그인 프로세스  
 * 이력사항
 * CH00     :2006/04/19 김경덕 최초작성 
 */

package sp.login.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kjf.action.KJFCommandResp;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFUtil;

import org.apache.struts.action.ActionForm;


/****************************************************************************
 * <p>Title       : GCertRegCmd Class</p>
 * <p>Description : 공인인증서를 등록하는 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 *
 * NOTE :공인 인증서 등록
 *
 * @version
 * @author YYS
 ***************************************************************************/
public class GCertRegCmd implements KJFCommandResp {
	
	
	private String next;
	
    public GCertRegCmd() {
    }
    
    public GCertRegCmd(String next_url) {
    	next = next_url; 
    }
    
    public String execute(HttpServletRequest request, HttpServletResponse response,  ActionForm form) throws Exception {
    	
    	String strID =   request.getParameter("id");
 
    	
	    String selectSQL =
	    	
	     	 "SELECT              	  	\n"+	
	     	 "	OFFI_DN     			\n"+		
	     	 "FROM   PT_MI_USER  		\n"+	
	     	 "WHERE OFFI_ID =?   		\n";	
	    
	    ReportDAO    rDAO        = new ReportDAO();
	    ReportEntity rEntity     = null;
	    
	    int cnt=1;
	    rDAO.setValue(cnt++, strID);
	    rEntity = rDAO.select(selectSQL); 
	    
	    request.setAttribute("OFFI_DN", KJFUtil.print(rEntity.getValue(0,"OFFI_DN")));
    
        return next;
    }
    

    
}