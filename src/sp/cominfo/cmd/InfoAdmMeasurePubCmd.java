package sp.cominfo.cmd;

import java.util.Calendar;
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
 * <p>Title       : InfoAdmMeasureCmd Class</p>
 * <p>Description : ����ó�� Ŭ����</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : ����ó�� ó���� �Ѵ�. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class InfoAdmMeasurePubCmd implements KJFCommand {
    
    public InfoAdmMeasurePubCmd() {        
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
        
        // ����ó�� ���� 
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
        
        // �˻� �⵵
        Vector<KJFSelect> v_scYear = getYear(11);
        request.setAttribute("v_scYear", v_scYear);
        
        // �˻� ��
        getSelMonth();
        Vector<KJFSelect> v_scMonth = getSelMonth();
        request.setAttribute("v_scMonth", v_scMonth);
    }
    
    
    /**
     * ����ó�� ������ �����´�.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadData(HttpServletRequest request, ComInfoParam pm) throws Exception {
        
    	ReportEntity rEntity = null;
    	
    	String scCode = KJFUtil.print(pm.getScCode());	// ����
        String scText = KJFUtil.print(pm.getScText());	// ����
        
        String scFromYear  = KJFUtil.print(pm.getScFromYear());		// ó�бⰣ  From Year
        String scFromMonth = KJFUtil.print(pm.getScFromMonth());	// ó�бⰣ  From Month
        String scToYear    = KJFUtil.print(pm.getScToYear());		// ó�бⰣ  To Year
        String scToMonth   = KJFUtil.print(pm.getScToMonth());		// ó�бⰣ  To Month
        
        String scSidoCode  = KJFUtil.print(pm.getScSidoCode());		// �õ��ڵ�	
        
        if ( KJFUtil.isEmpty(scCode) || KJFUtil.isEmpty(scText)) {
            request.setAttribute("pm", pm);
            request.setAttribute("rEntity", rEntity);
            return;
        }
        
        ReportDAO rDAO = new ReportDAO();      
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT                                   \n");
        sbSQL.append("        PT_C.COI_WRT_NUM,                 \n");   // �������Ϲ�ȣ
        sbSQL.append("        PT_C.NAME,                 		\n");   // ��ȣ
        sbSQL.append("        PT_C.SIDO_CODE,                 	\n");   // ������
        sbSQL.append("        PT_C.MANA_NUM,                 	\n");   // ����ڵ�Ϲ�ȣ        
        sbSQL.append("        PT_M.VIOL_DT,                     \n");   // ��������     
        sbSQL.append("        PT_C1.CODE_NAME AS VIOL_CONT,     \n");   // ��������     
        sbSQL.append("        PT_M.DISPO_DT,                    \n");   // ó������        
        sbSQL.append("        PT_C2.CODE_NAME AS TMPDISPO_CONT, \n");   // ó�б���
        sbSQL.append("        PT_C2.CODE AS DISPO_CODE          \n");   // ó�б����ڵ�
        
        sbSQL.append("   FROM (PT_R_COMPANY_MASTER PT_C INNER JOIN PT_M_MASTER PT_M     \n");
        sbSQL.append("     ON RTRIM(PT_C.TMP_WRT_NUM) = PT_M.TMP_WRT_NUM) ");
        
        sbSQL.append("   LEFT JOIN (                                    \n");  
        sbSQL.append("              SELECT P_CODE, CODE, CODE_NAME      \n"); 
        sbSQL.append("                FROM PT_COM_CODE                  \n");   
        sbSQL.append("               WHERE P_CODE = 'PTMCONT'           \n");   
        sbSQL.append("              ) PT_C1 ON PT_M.VIOL_CONT_CODE = PT_C1.CODE     \n");  
        
        sbSQL.append("   LEFT JOIN (                                    \n");   
        sbSQL.append("              SELECT P_CODE, CODE, CODE_NAME      \n");   
        sbSQL.append("                FROM PT_COM_CODE                  \n"); 
        sbSQL.append("               WHERE P_CODE = 'PTMPRO'            \n");   
        sbSQL.append("              ) PT_C2 ON PT_M.DISPO_CONT = PT_C2.CODE     \n");   
        
        StringBuffer whereSQL = new StringBuffer();
        whereSQL.append("  WHERE PT_M.DISPO_DT IS NOT NULL				\n");
        
        if (scCode.equals("001")) {         // ��Ϲ�ȣ
            whereSQL.append("    AND PT_C.COI_WRT_NUM = ?     \n");
            rDAO.setValue(i++, scText);
            
        } else if (scCode.equals("002")) {  // ��ȣ
            whereSQL.append("    AND PT_C.NAME LIKE ?         \n");
            rDAO.setValue(i++, "%" + scText + "%");
            
        }
        
        // ó�бⰣ From
        if (!KJFUtil.isEmpty(scFromYear) && !KJFUtil.isEmpty(scFromMonth)) {
        	whereSQL.append("    AND SUBSTR(PT_M.DISPO_DT, 1, 6) >= ?         \n");
            rDAO.setValue(i++, (scFromYear + scFromMonth));
        }
        
        // ó�бⰣ To
        if (!KJFUtil.isEmpty(scToYear) && !KJFUtil.isEmpty(scToMonth)) {
        	whereSQL.append("    AND SUBSTR(PT_M.DISPO_DT, 1, 6) <= ?         \n");
            rDAO.setValue(i++, (scToYear + scToMonth));
        }
        
        // �õ�
        if (!KJFUtil.isEmpty(scSidoCode)) {
        	whereSQL.append("    AND PT_C.SIDO_CODE = ?     \n");
            rDAO.setValue(i++, scSidoCode);
        }
        
        /* ************************** ����¡ ���� START **************************/
        StringBuffer sbCntSQL = new StringBuffer();
        sbCntSQL.append(" SELECT COUNT(*)  CNT  \n");
        sbCntSQL.append("   FROM (PT_R_COMPANY_MASTER PT_C INNER JOIN PT_M_MASTER PT_M     \n");
        sbCntSQL.append("         ON RTRIM(PT_C.TMP_WRT_NUM) = PT_M.TMP_WRT_NUM)           \n");  
        
        //��ü ��� ��
        String totalCount="";

        //�������� ��� ��
        int rowPerPage = KJFUtil.str2int(pm.getRowPerPage());

        //���� ������ ��ȣ
        int nowPage = 1;
        nowPage = KJFUtil.isEmpty(pm.getNowPage()) ? 1 : Integer.parseInt(pm.getNowPage());

        rEntity = rDAO.select(sbCntSQL.toString() + whereSQL.toString());
        
        totalCount = rEntity.getValue(0, "CNT");
        
        if (rowPerPage == 0) rowPerPage = Integer.parseInt(totalCount);//�߰�
        if ((rowPerPage*nowPage) - Integer.parseInt(totalCount) > rowPerPage) nowPage = 1;

        pm.setTotalCount(totalCount);
        pm.setNowPage(String.valueOf(nowPage));
        /* *************************** ����¡ ����  END **************************/

        rEntity = rDAO.select(sbSQL.toString() + whereSQL.toString(), nowPage, rowPerPage);

        /****** �˻����� �ʱⰪ ***********/
        request.setAttribute("pm", pm);
        request.setAttribute("rEntity", rEntity);
    }
    
    
    /**
     * �˻� �⵵�� ���Ѵ�
     * 
     * @param yearTerm
     * @return
     * @throws Exception
     */
    private Vector<KJFSelect> getYear(int yearTerm)  throws Exception {
        
        Vector<KJFSelect> result = new Vector<KJFSelect>();
        
        Calendar cal = Calendar.getInstance();
        
        KJFSelect sel = new KJFSelect();                 
        sel.setCode("");                  
        sel.setCode_nm("======");                 
        result.add(sel);
        
        for (int i = cal.get(Calendar.YEAR); i > (cal.get(Calendar.YEAR) - yearTerm); i--) {
            
            sel= new KJFSelect();               
            sel.setCode(Integer.toString(i));                  
            sel.setCode_nm(Integer.toString(i) + " ��");                 
            result.add(sel);            
        }
        
        return result;
    }
    
    /**
     * �˻� ���� ���Ѵ�
     * 
     * @return
     * @throws Exception
     */
    private Vector<KJFSelect> getSelMonth() throws Exception {
    	String[][] selType = 
    		{
    			{"", "====="},
    			{"01", "01 ��"},
    			{"02", "02 ��"},
    			{"03", "03 ��"},
    			{"04", "04 ��"},
    			{"05", "05 ��"},
    			{"06", "06 ��"},
    			{"07", "07 ��"}, 
    			{"08", "08 ��"}, 
    			{"09", "09 ��"}, 
    			{"10", "10 ��"}, 
    			{"11", "11 ��"}, 
    			{"12", "12 ��"}
    		};

        return KJFUtil.makeSelect(selType);
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
