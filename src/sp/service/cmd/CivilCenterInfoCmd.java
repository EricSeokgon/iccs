package sp.service.cmd;

import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.cfg.Config;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFLog;
import kjf.util.KJFSelect;
import kjf.util.KJFSelectOPS;
import kjf.util.KJFUtil;
import kjf.util.LoginException;
import kjf.util.MsgException;

import org.apache.struts.action.ActionForm;

import sp.service.AreaBaseBean;
import sp.service.AreaChargeBean;
import sp.service.AreaInfoBean;
import sp.service.ServiceParam;
import sp.uent.UserEnt;


/***************************************************************************
 * <p>Title       : CivilCenterInfoCmd Class</p>
 * <p>Description : ������ �ο����;ȳ� ����� �������� Ŭ����</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : ������ �ο����;ȳ� ����� ���� ���� ó���� �Ѵ�. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class CivilCenterInfoCmd implements KJFCommand {
    
	UserEnt user;
	
    public CivilCenterInfoCmd() {        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {        
               
        user = (UserEnt)request.getSession().getAttribute("user");
        
        // �α��� ���� üũ
        if (KJFUtil.isEmpty(user)) {
            throw new LoginException(request, "ȸ���� ������ �α��� üũ", "../member/login.do");
        } 
        
        // �˻����� ������ üũ
        ServiceParam pm = checkPm(request, form);
        request.setAttribute("pm", pm);
        
        // �˻����� �� �ʱⵥ��Ÿ �ε�
        loadCondition(request, pm);
        
        
        if ("".equals(user.getCAPITAL()) || "U".equals(user.getCAPITAL()) || "UE".equals(user.getCAPITAL())){
        	throw new MsgException(request, "�ش� �޴��� ���� ��ȸ ������ �����ϴ�.","Y", "../index/IndexAction.do?cmd=Index");
        } else {
        	// ����� �������� 
        	loadData(request);
        }
        return request.getParameter("cmd");
    }
    
    /**
     * �˻����� �� �ʱⵥ��Ÿ �ε�
     * 
     * @param HttpServletRequest
     * @return
     */
    private void loadCondition(HttpServletRequest request, ServiceParam pm) throws Exception {

    }
    
    /**
     * ����� ���� ���� �ε�
     * 
     * @param request
     * @param pm
     * @return
     * @throws Exception
     */
    public void loadData(HttpServletRequest request) throws Exception {
        
        ReportDAO rDAO       = new ReportDAO();
        ReportEntity rEntity = null;
        
        String Offi_Id = KJFUtil.print(request.getParameter("str"),"");
        
        int i = 1;
 
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT OFFI_ID,            \n");
        sbSQL.append("        NM,              \n");
        sbSQL.append("        (SELECT SIDO_NM FROM PT_SIDO_CODE WHERE AREA_CODE = SIDO_CODE) AS SIDO_NM,     \n");
        sbSQL.append("        (SELECT SIGUNGU_NM FROM PT_SIDO_CODE WHERE AREA_CODE = SIGUNGU_CODE) SIGUN_NM, \n");
        sbSQL.append("        TEL,                   \n");
        sbSQL.append("        PART,                   \n");
        sbSQL.append("        POS,                   \n");
        sbSQL.append("        (SELECT AUTH_GROUP_NAME FROM PT_AUTH_GROUP WHERE AUTH_GROUP_CODE = GROUP_CODE AND USE_YN = 'Y') AS GROUP_NM,      \n");
        sbSQL.append("        (SELECT AUTH_GROUP_NAME FROM PT_AUTH_GROUP WHERE AUTH_GROUP_CODE = HOME_GROUP AND USE_YN = 'Y') AS HOME_GROUP_NM, \n");
        sbSQL.append("        MOBILE,                   \n");
        sbSQL.append("        E_MAIL,                   \n");
        sbSQL.append("        OFFSEAL,                  \n");
        sbSQL.append("        TEL1,TEL2,TEL3            \n");
        sbSQL.append("        MOBILE1,MOBILE2,MOBILE3,   \n");
        sbSQL.append("        PHOTO,FAX2,FAX2,FAX3,FAX,SIDO_CODE  \n");
        sbSQL.append("   FROM PT_MI_USER                \n");
        sbSQL.append("  WHERE OFFI_ID = ?               \n");
        
        rDAO.setValue(i++, Offi_Id);
        
        rEntity = rDAO.select(sbSQL.toString());
        
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
    private ServiceParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {

        ServiceParam pm = (ServiceParam)form;

        // �Ķ���� �����
        KJFLog.debug(pm.toString(request));
        
        // ����¡ ����
        if (KJFUtil.isEmpty(pm.getRowPerPage())) {
            pm.setRowPerPage(Config.props.get("ROW_PER_PAGE"));
        }

        return pm;
    }
}
