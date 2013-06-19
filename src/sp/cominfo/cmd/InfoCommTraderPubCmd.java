package sp.cominfo.cmd;

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

import org.apache.struts.action.ActionForm;

import sp.cominfo.ComInfoParam;
import sp.uent.UserEnt;

/***************************************************************************
 * <p>Title       : InfoCommTraderCmd Class</p>
 * <p>Description : ������Ű������ �˻� Ŭ����</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : ������Ű������ �˻� ó���� �Ѵ�. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class InfoCommTraderPubCmd implements KJFCommand {
    
    public InfoCommTraderPubCmd() {        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {        
        
        UserEnt user = (UserEnt)request.getSession().getAttribute("user");
        
        // �α��� ���� üũ
        if (KJFUtil.isEmpty(user)) {
            throw new LoginException(request, "ȸ���� ������ �α��� üũ", "../member/login.do");
        }  
        
        // �˻����� ������ üũ
        ComInfoParam pm = checkPm(request, form);        
        
        // �ʱⵥ��Ÿ �ε�
        loadCondition(request, pm);
        
        // ������Ű������ �˻�
        loadData(request, pm);
        
        return request.getParameter("cmd");
    }
    
    /**
     * �˻����� �� �ʱⵥ��Ÿ �ε�
     * 
     * @param HttpServletRequest
     * @return
     */
    private void loadCondition(HttpServletRequest request, ComInfoParam pm) throws Exception {
        
        // �õ��ڵ�
        StringBuffer sbSQL = new StringBuffer();
        sbSQL = new StringBuffer();
        sbSQL.append(" SELECT AREA_CODE, SIDO_NM    \n");
        sbSQL.append("   FROM PT_SIDO_CODE          \n");
        sbSQL.append("  WHERE SUBSTR(AREA_CODE, 1, 2) = SUBSTR(AREA_CODE, 3, 4)    \n");
        sbSQL.append("  ORDER BY SIDO_NM     \n");           
        
        Vector<KJFSelect> v_scSD_CD = KJFSelectOPS.getSel(sbSQL.toString(), "", "��.�� ����");
        request.setAttribute("v_scSD_CD", v_scSD_CD); 
    }
    
    
    /**
     * ������Ű������ ������ �����´�.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadData(HttpServletRequest request, ComInfoParam pm) throws Exception {
        
        ReportEntity rEntity = null;
        
        String scCode = KJFUtil.print(pm.getScCode());
        String scText = KJFUtil.print(pm.getScText());
        
        if ( KJFUtil.isEmpty(scCode) || KJFUtil.isEmpty(scText)) {
            request.setAttribute("pm", pm);
            request.setAttribute("rEntity", rEntity);
            return;
        }                
        
        ReportDAO rDAO = new ReportDAO(); 
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT                       \n");
        sbSQL.append("        TMP_WRT_NUM,          \n");   // ����Ϲ�ȣ
        sbSQL.append("        COI_WRT_NUM,          \n");   // �������Ϲ�ȣ
        sbSQL.append("        MANA_NUM,          	\n");   // ����ڵ�Ϲ�ȣ        
        sbSQL.append("        NAME,                 \n");   // ��ȣ
        sbSQL.append("        REP_NM_KOR,           \n");   // ��ǥ��
        sbSQL.append("        SIDO_CODE,            \n");   // ������
        sbSQL.append("        ADDR_TEL_NUM          \n");   // ��ȭ��ȣ
        sbSQL.append("   FROM PT_R_COMPANY_MASTER   \n");
        
        StringBuffer sbWhereSQL = new StringBuffer(); 
        sbWhereSQL.append("  WHERE 1=1  \n");   // �������Ϲ�ȣ
        
        if (scCode.equals("001")) {         // ��Ϲ�ȣ
            sbWhereSQL.append("    AND COI_WRT_NUM = ?     \n");
            rDAO.setValue(i++, scText);
            
        } else if (scCode.equals("002")) {  // ��ȣ
            sbWhereSQL.append("    AND NAME LIKE ?         \n");
            rDAO.setValue(i++, "%" + scText + "%");
            
        } else if (scCode.equals("003")) {  // ��ǥ��
            sbWhereSQL.append("    AND REP_NM_KOR LIKE ?   \n");
            rDAO.setValue(i++, "%" + scText + "%");
        } 
        
        // �õ�
        if (!KJFUtil.isEmpty(pm.getScSidoCode())) {
        	sbWhereSQL.append("    AND SIDO_CODE = ?   \n");
            rDAO.setValue(i++, pm.getScSidoCode());            
        }
        
        /* ************************** ����¡ ���� START **************************/
        StringBuffer sbCntSQL = new StringBuffer();
        sbCntSQL.append(" SELECT COUNT(*)  CNT          \n");
        sbCntSQL.append("   FROM PT_R_COMPANY_MASTER    \n");
        sbCntSQL.append(sbWhereSQL); 
        
        //��ü ��� ��
        String totalCount="";

        //�������� ��� ��
        int rowPerPage = KJFUtil.str2int(pm.getRowPerPage());

        //���� ������ ��ȣ
        int nowPage = 1;
        nowPage = KJFUtil.isEmpty(pm.getNowPage()) ? 1 : Integer.parseInt(pm.getNowPage());

        rEntity = rDAO.select(sbCntSQL.toString());
        
        totalCount = rEntity.getValue(0, "CNT");
        
        if (rowPerPage == 0) rowPerPage = Integer.parseInt(totalCount);//�߰�
        if ((rowPerPage*nowPage) - Integer.parseInt(totalCount) > rowPerPage) nowPage = 1;

        pm.setTotalCount(totalCount);
        pm.setNowPage(String.valueOf(nowPage));
        /* *************************** ����¡ ����  END **************************/

        rEntity = rDAO.select(sbSQL.toString() + sbWhereSQL.toString(), nowPage, rowPerPage);

        /****** �˻����� �ʱⰪ ***********/
        request.setAttribute("pm", pm);
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
    private ComInfoParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {

        ComInfoParam pm = (ComInfoParam)form;

        // �Ķ���� �����
        KJFLog.debug(pm.toString(request));
        
        // ����¡ ����
        if (KJFUtil.isEmpty(pm.getRowPerPage())) {            
            pm.setRowPerPage(Config.props.get("ROW_PER_PAGE"));
        }

        return pm;
    }

}
