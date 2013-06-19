package sp.usebefore.cmd;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.cfg.Config;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFLog;
import kjf.util.KJFUtil;
import kjf.util.LoginException;

import org.apache.struts.action.ActionForm;

import sp.usebefore.UseBeforeParam;

import sp.uent.UserEnt;

/***************************************************************************
 * <p>Title       : PubWorkRegInfoCmd Class</p>
 * <p>Description : ����� ������� Ŭ����</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : ����� ������� ó���� �Ѵ�. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class UseBeforeRegInfoCmd implements KJFCommand {
    
	UserEnt user;
	
    public UseBeforeRegInfoCmd() {        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {        
        
        user = (UserEnt)request.getSession().getAttribute("user");
        
        // �α��� ���� üũ
        if (KJFUtil.isEmpty(user)) {
            throw new LoginException(request, "ȸ���� ������ �α��� üũ", "../member/login.do");
        }  
        
        // �˻����� ������ üũ
        UseBeforeParam pm = checkPm(request, form);
        request.setAttribute("pm", pm);
        
        
        // �������� ����  
        loadRegData(request, pm);

        // ��ȣ������ó�л���
        loadRefferData(request, pm);
        
        // ��ϱ��ؽŰ�
        loadRegBasicData(request, pm);
        
        // ��������� ����  
        loadEngineerData(request, pm);

        // ��� ����
        loadSubsidiaryData(request, pm);


        
        return request.getParameter("cmd");
    }
    
    
    /**
     * ����� ��� ������ �����´�.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadRegData(HttpServletRequest request, UseBeforeParam pm) throws Exception {
    	
        ReportEntity rEntity = null;        
        
        ReportDAO rDAO = new ReportDAO();    
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();        
        String COI_WRT_NUM = KJFUtil.print(request.getParameter("str"),"");
      
		sbSQL.append(" SELECT                               \n");
		sbSQL.append("        PT_M.MANA_NUM,                \n");   // ����� ��Ϲ�ȣ
		sbSQL.append("        PT_H.COI_WRT_NUM,             \n");   // ��Ϲ�ȣ
		sbSQL.append("        PT_H.REP_SSN1,             	\n");   // ����� �ֹι�ȣ
		sbSQL.append("        PT_H.RECV_NUM,                \n");   // ������ȣ
		
		sbSQL.append("        PT_M.TMP_WRT_NUM,             \n");   // ��Ϲ�ȣ		
		sbSQL.append("        PT_M.NAME,             		\n");   // ��ȣ
		sbSQL.append("        ( SELECT COM_NUM FROM PT_R_REGIST_STATEMENT PT_RS WHERE PT_RS.MANA_NUM = PT_M.MANA_NUM) AS COM_NUM,\n");   // ���ι�ȣ		
		sbSQL.append("        PT_M.WRT_DT,					\n");   // �����
		sbSQL.append("        PT_M.REP_NM_KOR,				\n");   // ��ǥ�ڸ�
		sbSQL.append("        PT_M.OFFICE_AREA,				\n");   // �繫�Ǹ���
		sbSQL.append("        PT_M.ADDR_POST_NUM,      		\n");   // ���������� �����ȣ
		sbSQL.append("        PT_M.ADDR_ADDR,				\n");   // ���������� �ּ�
		sbSQL.append("        PT_M.ADDR_DETAIL_ADDR,   		\n");   // ����������  �����ּ�
		sbSQL.append("        PT_M.ADDR_TEL_NUM,       		\n");   // ��ȭ��ȣ
		sbSQL.append("        PT_M.ADDR_FAX_NUM,			\n");   // �ѽ���ȣ
		sbSQL.append("        (SELECT ASSE_DT FROM (SELECT PT_C.ASSE_DT FROM PT_R_WORK_CAPABILITY PT_C WHERE PT_C.COI_WRT_NUM = ? ORDER BY ASSE_DT DESC) WHERE ROWNUM =1) AS  ASSE_DT,					\n");   // �ð��ɷ�����
		sbSQL.append("        (SELECT ASSE_AOM FROM (SELECT PT_C.ASSE_AOM FROM PT_R_WORK_CAPABILITY PT_C WHERE PT_C.COI_WRT_NUM = ? ORDER BY ASSE_DT DESC) WHERE ROWNUM =1) AS  ASSE_AOM,				\n");   // �ð��ɷ��򰡾�
		sbSQL.append("        PT_M.PAY_CAP,					\n");   // �����ں���
		sbSQL.append("        PT_M.REA_CAP					\n");   // �����ں���
		
		sbSQL.append("   FROM (PT_R_COMPANY_MASTER PT_M                 \n");
	      
	    sbSQL.append("   LEFT JOIN PT_R_BASIC_CHANGE_HISTORY PT_H               \n");
	    sbSQL.append("        ON RTRIM(PT_M.TMP_WRT_NUM) = PT_H.TMP_WRT_NUM)    \n");
		sbSQL.append("	 WHERE PT_M.COI_WRT_NUM = ?					 \n");      

		rDAO.setValue(i++, COI_WRT_NUM);
        rDAO.setValue(i++, COI_WRT_NUM); 
        rDAO.setValue(i++, COI_WRT_NUM);    
        
        rEntity = rDAO.select(sbSQL.toString());

       	rEntity.setValue(0, "rWorkNum",     getWork_Num(rEntity.getValue(0, "TMP_WRT_NUM")));  // ��_��Ϲ�ȣ                           

       	/****** �˻����� �ʱⰪ ***********/
        request.setAttribute("rEntity", rEntity);        
    }
    
    /**
     * �⵵�� ��������� �����´�.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public ReportEntity getWork_Num(String TMP_WRT_NUM) throws Exception {
        ReportDAO rDAO = new ReportDAO();    
        ReportEntity rEntity = null;        
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT                               \n");        
        sbSQL.append("        YEAR,            				\n");   // ������� �⵵
        sbSQL.append("        WORK_ROM           			\n");   // ������� �ݾ�
        sbSQL.append(" FROM PT_R_WORK_RESULTS     			\n");        
        sbSQL.append(" WHERE TMP_WRT_NUM = ?      			\n");   // �������Ϲ�ȣ
        sbSQL.append(" ORDER BY YEAR ASC    				\n");   // �������Ϲ�ȣ
        rDAO.setValue(1, TMP_WRT_NUM);

        rEntity = rDAO.select(sbSQL.toString());
        
        return rEntity;                
    }
    
    /**
     * ��ȣ �� ����ó�� ���׸� �����´�.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadRefferData(HttpServletRequest request, UseBeforeParam pm) throws Exception {
        ReportDAO rDAO = new ReportDAO();
        ReportEntity rEntity = null;        
      
        int i = 1;
        
        String COI_WRT_NUM = KJFUtil.print(request.getParameter("str"),"");
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT                               \n");        
        sbSQL.append("        PT_M_M.VIOL_DT,          		\n");   // ��ȣ �� ����ó�� ��������
        sbSQL.append("        PT_M_M.DISPO_CONT,   			\n");   // ��ȣ �� ����ó�� ó�г��� �ڵ�
        sbSQL.append("        PT_C.CODE_NAME,      			\n");   // ��ȣ �� ����ó�� ó�г���
        sbSQL.append("        PT_M_M.DISPO_CAUSE,  			\n");   // ��ȣ �� ����ó�� ó�л���
        sbSQL.append("        PT_M_A.AUDI_EXEC_ORG 			\n");   // ��ȣ �� ����ó�� û�����

        sbSQL.append("        FROM PT_R_COMPANY_MASTER PT_M	\n");
        
        sbSQL.append(" 		  LEFT JOIN (					\n"); 
    	sbSQL.append("	 		SELECT TMP_WRT_NUM,VIOL_DT, VIOL_CONT_CODE, DISPO_CONT, DISPO_CAUSE FROM PT_M_MASTER	\n"); 
    	sbSQL.append("			) PT_M_M ON RTRIM(PT_M.TMP_WRT_NUM) = PT_M_M.TMP_WRT_NUM								\n");
    	sbSQL.append("		  LEFT JOIN (																				\n");
    	sbSQL.append("			SELECT TMP_WRT_NUM, AUDI_EXEC_ORG FROM PT_M_AUDI_REPORT									\n");
    	sbSQL.append("			) PT_M_A ON RTRIM(PT_M.TMP_WRT_NUM) = PT_M_A.TMP_WRT_NUM								\n");
    	sbSQL.append("		  LEFT JOIN (																				\n");
    	sbSQL.append("			SELECT CODE, CODE_NAME FROM PT_COM_CODE WHERE P_CODE = 'PTMPRO'							\n");
    	sbSQL.append("			) PT_C ON PT_M_M.DISPO_CONT = PT_C.CODE													\n");
    	sbSQL.append("	WHERE PT_M.COI_WRT_NUM = ?    												\n");
    	
    	rDAO.setValue(i++, COI_WRT_NUM);
        
	    rEntity = rDAO.select(sbSQL.toString());
	
	    /****** �˻����� �ʱⰪ ***********/
	    request.setAttribute("fEntity", rEntity);  
    } 	
    
    
    /**
     * ��ϱ��ؽŰ� ���׸� �����´�.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadRegBasicData(HttpServletRequest request, UseBeforeParam pm) throws Exception {
        ReportDAO rDAO = new ReportDAO();
        ReportEntity rEntity = null;        
      
        int i = 1;
        
        String COI_WRT_NUM = KJFUtil.print(request.getParameter("str"),"");
        
        StringBuffer sbSQL = new StringBuffer();
        
        sbSQL.append(" SELECT                               \n");        
        sbSQL.append("        PT_R_B.WRT_DT,          		\n");   // �������
        sbSQL.append("        PT_R_B.OFFI_PART,   			\n");   // ó����
        sbSQL.append("        PT_R_B.RECV_NUM,      		\n");   // ������ȣ
        sbSQL.append("        PT_R_B.COI_WRT_NUM,  			\n");   // ��Ϲ�ȣ
        sbSQL.append("        PT_R_B.NAME, 					\n");   // ȸ���
        sbSQL.append("        PT_R_B.RECV_DT 				\n");   // �Ű���

        sbSQL.append("        FROM PT_R_COMPANY_MASTER PT_M	\n");
        
        sbSQL.append(" 		  INNER JOIN (					\n");
    	sbSQL.append("	 		SELECT TMP_WRT_NUM, WRT_DT, OFFI_PART, RECV_NUM, COI_WRT_NUM, NAME, RECV_DT FROM PT_R_BASIC_STATEMENT	\n"); 
    	sbSQL.append("			) PT_R_B ON PT_R_B.TMP_WRT_NUM = PT_M.TMP_WRT_NUM 								\n");
    	sbSQL.append("	WHERE PT_M.COI_WRT_NUM = ? 												\n");
    	
    	rDAO.setValue(i++, COI_WRT_NUM);
	            
	    rEntity = rDAO.select(sbSQL.toString());
	
	    /****** �˻����� �ʱⰪ ***********/
	    request.setAttribute("bEntity", rEntity);  
    } 	  
    
 
    /**
     * ��������� ������ �����´�.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadEngineerData(HttpServletRequest request, UseBeforeParam pm) throws Exception {
        
        ReportEntity rEntity = null;        
        
        ReportDAO rDAO = new ReportDAO();    
        
        int i = 1;
        
        String COI_WRT_NUM = KJFUtil.print(request.getParameter("str"),"");
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT                               \n");        
        sbSQL.append("        PT_E.ENGINEER_NM,             \n");   // ��������� ����
        sbSQL.append("        PT_E.ENGINEER_SSN1,           \n");   // ��������� �ֹι�ȣ
        sbSQL.append("        PT_E.ENGINEER_GRADE,          \n");   // ��������� ���
        sbSQL.append("        PT_C1.CODE_NAME AS ENGINEER_GRADE_NM,      \n");   // ��������� ��� �̸�
        sbSQL.append("        PT_E.EMPL_YMD                \n");   // ��������� �Ի�����
        
        sbSQL.append("   FROM PT_R_COMPANY_MASTER PT_M     \n");      
        
        sbSQL.append("   INNER JOIN (                                               \n");   
        sbSQL.append("               SELECT PT_E_M.NM_KOR AS ENGINEER_NM,                     \n");   // ��������� ����
        sbSQL.append("                      PT_E_C.ENGINEER_SSN1,                   \n");   // ��������� �ֹι�ȣ
        sbSQL.append("                      PT_E_M.ENGINEER_GRADE,                  \n");   // ��������� ��� 
        sbSQL.append("                      PT_E_C.EMPL_YMD,                        \n");   // ��������� �Ի�����
        sbSQL.append("                      PT_E_C.TMP_WRT_NUM                      \n");
        
        sbSQL.append("                 FROM PT_R_ENGINEER_CHANGE PT_E_C, PT_R_ENGINEER_MASTER PT_E_M    \n");  
        
        sbSQL.append("                WHERE PT_E_C.ENGINEER_SSN1 = PT_E_M.ENGINEER_SSN1     \n"); 
        sbSQL.append("                  AND PT_E_C.ENGINEER_SSN2 = PT_E_M.ENGINEER_SSN2     \n"); 
        sbSQL.append("                  AND PT_E_C.RET_YMD IS NULL                          \n"); 
        
        sbSQL.append("              ) PT_E ON PT_E.TMP_WRT_NUM = PT_M.TMP_WRT_NUM           \n");
        
        sbSQL.append("   LEFT JOIN (                                                \n");   
        sbSQL.append("              SELECT P_CODE, CODE, CODE_NAME                  \n");   
        sbSQL.append("                FROM PT_COM_CODE                              \n"); 
        sbSQL.append("               WHERE P_CODE = 'ENGCLASS'                      \n");   
        sbSQL.append("              ) PT_C1 ON PT_E.ENGINEER_GRADE = PT_C1.CODE     \n"); 
      
        sbSQL.append("  WHERE PT_M.COI_WRT_NUM = ?      \n");   // �������Ϲ�ȣ 
       // sbSQL.append("    AND PT_M.MANA_NUM = ?         \n");   // ����ڹ�ȣ  
        
        rDAO.setValue(i++, COI_WRT_NUM);
       // rDAO.setValue(i++, user.getCOM_NUM());        
        
        rEntity = rDAO.select(sbSQL.toString());

        /****** �˻����� �ʱⰪ ***********/
        request.setAttribute("eEntity", rEntity);        
    }
    
    
    /**
     * ������� ������ �����´�.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadSubsidiaryData(HttpServletRequest request, UseBeforeParam pm) throws Exception {
        
        ReportEntity rEntity = null;        
        
        ReportDAO rDAO = new ReportDAO();    
        
        int i = 1;
        
        String COI_WRT_NUM = KJFUtil.print(request.getParameter("str"),"");
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT                               \n");
        sbSQL.append("        PT_S.INS_DT,              	\n");   // ������� �������
        sbSQL.append("        PT_C1.CODE_NAME,              \n");   // Ÿ �ü������ �ڵ��
        sbSQL.append("        PT_S.SUB_CODE,             	\n");   // Ÿ �ü������ �ڵ�
        sbSQL.append("        PT_S.SUB_WRT_NUM            	\n");   // Ÿ �ü������ ��Ϲ�ȣ
               
        sbSQL.append("   FROM PT_R_COMPANY_MASTER PT_M INNER JOIN PT_R_SUBSIDIARY PT_S     \n");
        sbSQL.append("     ON RTRIM(PT_M.TMP_WRT_NUM) = PT_S.TMP_WRT_NUM          		   \n");   
               
        sbSQL.append("   LEFT JOIN (                                        \n");  
        sbSQL.append("              SELECT P_CODE, CODE, CODE_NAME          \n"); 
        sbSQL.append("                FROM PT_COM_CODE                      \n");   
        sbSQL.append("               WHERE P_CODE = 'SUBSIDIARY'            \n");   
        sbSQL.append("              ) PT_C1 ON PT_S.SUB_CODE = PT_C1.CODE   \n"); 
      
        sbSQL.append("  WHERE PT_M.COI_WRT_NUM = ?      \n");   // �������Ϲ�ȣ 
        //sbSQL.append("    AND PT_M.MANA_NUM = ?         \n");   // ����ڹ�ȣ  
        
        rDAO.setValue(i++, COI_WRT_NUM);
        //rDAO.setValue(i++, user.getCOM_NUM());        
        
        rEntity = rDAO.select(sbSQL.toString());

        /****** �˻����� �ʱⰪ ***********/
        request.setAttribute("sEntity", rEntity);        
    }
    
    
    /**
     * �� üũ �޼ҵ�
     * 
     * @param request
     * @param form
     * @return
     * @throws Exception
     */
    private UseBeforeParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {

    	UseBeforeParam pm = (UseBeforeParam)form;

        // �Ķ���� �����
        KJFLog.debug(pm.toString(request));
        
        // ����¡ ����
        if (KJFUtil.isEmpty(pm.getRowPerPage())) {            
            pm.setRowPerPage(Config.props.get("ROW_PER_PAGE"));
        }

        return pm;
    }
}
