package sp.mystore.cmd;

import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.cfg.Config;
import kjf.ops.ReportDAO;
import kjf.util.KJFDate;
import kjf.util.KJFLog;
import kjf.util.KJFUtil;
import kjf.util.LoginException;

import org.apache.struts.action.ActionForm;

import sp.mystore.MystoreParam;
import sp.uent.UserEnt;

/***************************************************************************
 * <p>Title       : MyUseStoreCUDCmd Class</p>
 * <p>Description : ���� ���� ���� â�� ���, ����, ���� ó�� Ŭ����</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : ���� ���� ���� â�� ���, ����, ���� ó���� �Ѵ�. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class MyUseStoreCUDCmd implements KJFCommand {
    
	UserEnt user;
	
    public MyUseStoreCUDCmd() {        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {        
        
        user = (UserEnt)request.getSession().getAttribute("user");
        
        // �α��� ���� üũ
        if (KJFUtil.isEmpty(user)) {
            throw new LoginException(request, "ȸ���� ������ �α��� üũ", "../member/login.do");
        } 
        
        // �˻����� ������ üũ
        MystoreParam pm = checkPm(request, form);        
        
        insertData(request, pm);	// ���ְ��� â�� ���� ����       
                
        return request.getParameter("cmd");
    }
    
    
    /**
     * ���ְ��� â�� ����
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void insertData(HttpServletRequest request, MystoreParam pm) throws Exception {
    	
    	 StringTokenizer tokens = new StringTokenizer(request.getParameter("n_favorite") , "," );
         StringTokenizer values = null;
         String rowStr = "";        
         
         deleteDate(request, pm);	// ���ְ��� â�� ���� ����
         
         while (tokens.hasMoreElements()) {
        	 
             rowStr = tokens.nextToken();
             values = new StringTokenizer( rowStr , "|" );            
             
             String fav_name       = values.nextToken().trim();
             String fav_url        = values.nextToken().trim();      
             String order_num      = values.nextToken().trim();  
                          
             System.out.println("fav_name: " + fav_name);
             System.out.println("fav_url: " + fav_url);
             System.out.println("order_num: " + order_num);
             
             ReportDAO rDAO = new ReportDAO();      
             
             StringBuffer sbSQL = new StringBuffer();        
             sbSQL.append(" INSERT INTO PT_HOM_FAVORITE (		\n");
             sbSQL.append("        USER_ID,               		\n");
             sbSQL.append("        USER_NAME,             		\n");
             sbSQL.append("        FAV_NAME,           			\n");
             sbSQL.append("        FAV_URL,             		\n");
             sbSQL.append("        ORDER_NUM,             		\n");
             sbSQL.append("        INS_DT             			\n");
             sbSQL.append("       )                      		\n");
             sbSQL.append("   values (?,?,?,?,?,?)          	\n");   

             int i = 1;
             
             rDAO.setValue(i++, user.getUSER_ID());
             rDAO.setValue(i++, user.getUSER_NAME());
             rDAO.setValue(i++, fav_name);
             rDAO.setValue(i++, fav_url);
             rDAO.setValue(i++, order_num);
             rDAO.setValue(i++, KJFDate.getCurTime("yyyy-MM-dd HH:mm:ss"));

             rDAO.execute(sbSQL.toString());
             
         }
    }
    
    /**
     * ���ְ��� â�� ����
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void deleteDate(HttpServletRequest request, MystoreParam pm) throws Exception {
        
    	 ReportDAO rDAO = new ReportDAO();      
         
         StringBuffer sbSQL = new StringBuffer();        
         sbSQL.append(" DELETE                       \n");
         sbSQL.append("   FROM PT_HOM_FAVORITE     	 \n");
         sbSQL.append("  WHERE USER_ID = ?           \n");
         
         int i = 1;

         rDAO.setValue(i++, user.getUSER_ID());      

         rDAO.execute(sbSQL.toString());
    }
    
    
    /**
     * �� üũ �޼ҵ�
     * 
     * @param request
     * @param form
     * @return
     * @throws Exception
     */
    private MystoreParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {

        MystoreParam pm = (MystoreParam)form;

        // �Ķ���� �����
        KJFLog.debug(pm.toString(request));
        
        // ����¡ ����
        if (KJFUtil.isEmpty(pm.getRowPerPage())) {            
            pm.setRowPerPage(Config.props.get("ROW_PER_PAGE"));
        }

        return pm;
    }

}
