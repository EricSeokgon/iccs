/**
 * 파일명   : GCertRegCUDCmd.java 
 * 설명     : 로그인 프로세스  
 * 이력사항
 * CH00     :2006/04/19 김경덕 최초작성 
 */

package sp.login.cmd;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kjf.action.KJFCommandResp;
import kjf.ops.ReportDAO;
import kjf.util.KJFSec;

import org.apache.struts.action.ActionForm;


/****************************************************************************
 * <p>Title       : GCertRegCUDCmd Class</p>
 * <p>Description : 공인인증서를 등록하는 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 *
 * NOTE :공인 인증서 등록
 *
 * @version
 * @author YYS
 ***************************************************************************/
public class GCertRegCUDCmd implements KJFCommandResp {
	
	
	private String next;
	
    public GCertRegCUDCmd() {
    }
    
    public GCertRegCUDCmd(String next_url) {
    	next = next_url; 
    }
    
    public String execute(HttpServletRequest request, HttpServletResponse response,  ActionForm form) throws Exception {
    	
    	
    	//String strID =  (String)request.getAttribute("id");
    	//String OFFI_DN =  (String)request.getAttribute("subDN");
    	
    	
    	String strID =  request.getParameter("id");
    	String OFFI_DN =  request.getParameter("OFFI_DN");
//        String OFFI_DN =  KJFSec.decode(URLDecoder.decode(request.getParameter("subDN"),"utf-8"));
    	
    	ReportDAO    rDAO        = new ReportDAO();
    	String UpdataSql = "UPDATE PT_MI_USER  SET OFFI_DN = ? WHERE OFFI_ID=?";
    	int cnt=1;
    	rDAO.setValue(cnt++,OFFI_DN);
    	rDAO.setValue(cnt++, strID);  	
    	rDAO.execute(UpdataSql);
  

    
        return next;
    }
    

    
}