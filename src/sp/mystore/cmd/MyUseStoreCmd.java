package sp.mystore.cmd;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.cfg.Config;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFLog;
import kjf.util.KJFUtil;
import kjf.util.LoginException;

import org.apache.struts.action.ActionForm;

import sp.mystore.MystoreParam;
import sp.uent.UserEnt;

/***************************************************************************
 * <p>Title       : MyInfomationCmd Class</p>
 * <p>Description : MY�ο�â�� ó�� Ŭ����</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE :  MY�ο�â������ ó���� �Ѵ�. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class MyUseStoreCmd implements KJFCommand {
    
	UserEnt user;
	
    public MyUseStoreCmd() {
        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {        
        
        user = (UserEnt)request.getSession().getAttribute("user");
        
        // �α��� ���� üũ
        if (KJFUtil.isEmpty(user)) {
            throw new LoginException(request, "ȸ���� ������ �α��� üũ", "../member/login.do");
        } 
        
        // �˻����� ������ üũ
        MystoreParam pm = checkPm(request, form);
        
        // ���ְ��� â�� ���� 
        loadData(request, pm);
                
        return request.getParameter("cmd");
    }
    
    
    /**
     * ���ְ��� â�� ������ �����´�.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadData(HttpServletRequest request, MystoreParam pm) throws Exception {
        
    	 ReportEntity rEntity = null;        
         
         ReportDAO rDAO = new ReportDAO();    
         
         int i = 1;
       
         StringBuffer sbSQL = new StringBuffer();        
         sbSQL.append(" SELECT                    	\n");        
         sbSQL.append("        USER_ID,           	\n");   // �����   ID
         sbSQL.append("        USER_NAME,         	\n");   // �����  ��
         sbSQL.append("        FAV_NAME,         	\n");   // ���ְ��� ����Ʈ��
         sbSQL.append("        FAV_URL,          	\n");   // ���ְ��� ����Ʈ
         sbSQL.append("        ORDER_NUM,         	\n");   // ����
         sbSQL.append("        INS_DT              	\n");   // �����         
         sbSQL.append("   FROM PT_HOM_FAVORITE    	\n");         
         sbSQL.append("  WHERE USER_ID = ?          \n");   // �����ID
         sbSQL.append("  ORDER BY ORDER_NUM         \n");   // ����
         
         rDAO.setValue(i++, user.getUSER_ID());
         
         rEntity = rDAO.select(sbSQL.toString());

         /****** �˻����� �ʱⰪ ***********/
         request.setAttribute("rEntity", rEntity);        
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
