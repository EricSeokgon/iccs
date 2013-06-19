/**
 * ���ϸ�   : GCertRegCmd.java 
 * ����     : �α��� ���μ���  
 * �̷»���
 * CH00     :2006/04/19 ���� �����ۼ� 
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
 * <p>Description : ������������ ����ϴ� Ŭ����</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 *
 * NOTE :���� ������ ���
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