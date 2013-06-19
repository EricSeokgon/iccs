package sp.usebefore.cmd;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.cfg.Config;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFDBUtil;
import kjf.util.KJFDate;
import kjf.util.KJFLog;
import kjf.util.KJFUtil;

import org.apache.struts.action.ActionForm;

import sp.uent.UserEnt;
import sp.usebefore.UseBeforeParam;

/***************************************************************************
 * <p>Title       : UseBeforeQuickStatus Class</p>
 * <p>Description : ����������˻� ������Ȳ Ŭ����</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : ����������˻� ������Ȳ�� ó���� �Ѵ�. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class UseBeforeLogCmd implements KJFCommand {
    
    public UseBeforeLogCmd() {   
        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {        
            
        // �˻����� ������ üũ
        UseBeforeParam pm = checkPm(request, form);
        String cmd = request.getParameter("cmd");
        
        /****** �˻����� �ʱⰪ ***********/
        request.setAttribute("pm", pm);
        
        if ("UseBeforeLog".equals(cmd)){
        	// �α� �����
        	loadData(request, pm);
        } else if("UseBeforeLogOnly".equals(cmd)){
        	loadDataToUser(request, pm);
        } else if("UseBeforeRegSign".equals(cmd)){
        	loadDataUb(request, pm);
        }
        
        return request.getParameter("cmd");
    }
    
    
    /**
     * ������˻� ������Ȳ ������ �����´�.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadData(HttpServletRequest request, UseBeforeParam pm) throws Exception {
        
        ReportEntity rEntity = null;   
        
        ReportDAO rDAO = new ReportDAO();
        
        String USER_NAME  		= KJFUtil.print(pm.getScRecvName());  // ������ �̸�
        String CIV_RECV_NUM   	= KJFUtil.print(pm.getScRecvNum());  // ������ ������ȣ
        String USER_SSN  		= KJFUtil.print(request.getParameter("user_ssn"),"");  
        String USER_DN  		= KJFUtil.print(request.getParameter("user_dn"),"");  
        String USER_IP 	  		= request.getRemoteAddr();
        String INS_DT     		= KJFDate.getCurTime("yyyy-MM-dd HH:mm:ss");
        
        String result = "0";
        
        String UB_LOG_SEQ = getMaxSeq("PT_UB_USEBEFORE_PRINT_LOG");
      
        if (KJFUtil.isEmpty(USER_NAME) || KJFUtil.isEmpty(USER_SSN) || KJFUtil.isEmpty(USER_DN)) {
            return;
        }
        
        int i = 1;

        String aa[] = USER_DN.split(",");
        String temp = aa[0].replace("cn=","");
        int temp_length = 0;
        
        if (temp.indexOf("(") > 0){
        	temp_length = temp.indexOf("(");
        	USER_NAME = temp.substring(0,temp_length);
        } else if (temp.indexOf("-") > 0){
        	temp_length = temp.indexOf("-");
        	USER_NAME = temp.substring(0,temp_length);        	
        }
        
        StringBuffer sbSQL = new StringBuffer();
        sbSQL.append(" INSERT INTO  PT_UB_USEBEFORE_PRINT_LOG   \n");
        sbSQL.append("       ( UB_LOG_SEQ,		\n");
        sbSQL.append("		  USER_GROUP,     	\n");  // �����ڱ׷�
        sbSQL.append("		  USER_NAME,     	\n");  // �����ڸ�
        sbSQL.append("        CIV_RECV_NUM,     \n");  // ������ȣ
        sbSQL.append("        USER_NAT_NUM,     \n");  // �����ڹι�
        sbSQL.append("        USER_IP,         	\n");  // ���ӹ�ȣ
        sbSQL.append("        INSP_DT,     		\n");  // ���ӽð�
        sbSQL.append("        SIGUNGU_CODE,		\n");  // �����߱� �ñ����ڵ�
        sbSQL.append("        USER_DN)      	\n");  // ������ ������ DN
        
        sbSQL.append("  VALUES (?,?,?,?,crypto.Encrypt(?),?,?,?,? )      \n");  
        
        rDAO.setValue(i++, UB_LOG_SEQ);
        rDAO.setValue(i++, "NOT");
        rDAO.setValue(i++, USER_NAME);
        rDAO.setValue(i++, CIV_RECV_NUM);
        rDAO.setValue(i++, USER_SSN);
        rDAO.setValue(i++, USER_IP);
        rDAO.setValue(i++, INS_DT);
        rDAO.setValue(i++, pm.getScSIGUNGU_CODE());
        rDAO.setValue(i++, USER_DN);
        
        rDAO.execute(sbSQL.toString());
        
        if (!KJFUtil.isEmpty(USER_SSN)){
        	result = "1";
        }         
        request.setAttribute("result", result);
    }
    
    /**
     * ������˻� ������Ȳ ������ �����´�.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadDataToUser(HttpServletRequest request, UseBeforeParam pm) throws Exception {
        
    	UserEnt user = (UserEnt) request.getSession().getAttribute("user");
        
        ReportEntity rEntity = null;   
        ReportDAO rDAO = new ReportDAO();

        String USER_NAME  		= KJFUtil.print(pm.getScRecvName(),"");  // ������ �̸�
        String CIV_RECV_NUM   	= KJFUtil.print(pm.getScRecvNum(),"");  // ������ ������ȣ
        
        String USER_SSN  		= loadDataUser(request,pm);         // Ȩ������
        String USER_ID  		= loadDataUserPub(request,pm); 		// ������
        
        String USER_IP 	  = request.getRemoteAddr();
        String INS_DT     = KJFDate.getCurTime("yyyy-MM-dd HH:mm:ss");
        
        String result = "0";
        
        String UB_LOG_SEQ = getMaxSeq("PT_UB_USEBEFORE_PRINT_LOG");
        
        System.out.println(USER_NAME+"="+USER_SSN+"="+USER_ID);
       
        if (KJFUtil.isEmpty(USER_NAME) || (KJFUtil.isEmpty(USER_SSN) && KJFUtil.isEmpty(USER_ID))) {
            return;
        }
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();
        sbSQL.append(" INSERT INTO  PT_UB_USEBEFORE_PRINT_LOG   \n");
        sbSQL.append("       ( UB_LOG_SEQ,		\n");  // ����
        sbSQL.append("        USER_GROUP,       \n");    //  ������ �׷�(�����/���/������)
        sbSQL.append("        USER_ID,      	\n");  // ������ID
        sbSQL.append("		  USER_NAME,     	\n");  // �����ڸ�
        sbSQL.append("        USER_IP,         	\n");  // IP
        sbSQL.append("        USER_DN,         	\n");  // ������ ������ DN
        sbSQL.append("        USER_NAT_NUM,     \n");  // �����߱� �ο���ȣ
        sbSQL.append("        COM_NAME,     	\n");  // ��ȣ��
        sbSQL.append("        COM_NUM,     		\n");  // ����ڹ�ȣ
        sbSQL.append("        REG_NUM,     		\n");  // �������Ϲ�ȣ
        sbSQL.append("        CIV_RECV_NUM,     \n");  // �ο�������ȣ
        sbSQL.append("        SIGUNGU_CODE,     \n");  // �ñ����ڵ�
        sbSQL.append("        INSP_DT )      	\n");  // ���ӽð�

        sbSQL.append("  VALUES (?,?,?,?,? ,?,?,?,?,? ,?,?,? )      \n");  
        
        rDAO.setValue(i++, UB_LOG_SEQ);
        rDAO.setValue(i++, user.getCAPITAL());
        rDAO.setValue(i++, user.getUSER_ID());
        rDAO.setValue(i++, user.getUSER_NAME());
        rDAO.setValue(i++, USER_IP);
        rDAO.setValue(i++, request.getAttribute("USER_DN"));
        rDAO.setValue(i++, USER_SSN);
        rDAO.setValue(i++, user.getCOM_NAME());
        rDAO.setValue(i++, user.getCOM_NUM());
        rDAO.setValue(i++, user.getREG_NUM());
        rDAO.setValue(i++, CIV_RECV_NUM);
        rDAO.setValue(i++, user.getSIGUNGU_CODE());
        rDAO.setValue(i++, INS_DT);
        
        if(!"".equals(user.getUSER_ID())){
        	rDAO.execute(sbSQL.toString());
        }
    }
    
    private String loadDataUser(HttpServletRequest request, UseBeforeParam pm) throws Exception {
        
        ReportEntity rEntity = null;   
        ReportDAO rDAO = new ReportDAO();
        
        String result ="";
        String user_dn = "";
        rDAO.setValue(1, pm.getScRecvName());
        rEntity = rDAO.select("SELECT crypto.Encrypt(USER_SSN1||USER_SSN2) as JDN, USER_DN FROM PT_HOM_USER WHERE USER_NAME = ?");
        
        if (rEntity.getRowCnt() > 0){
        	result = rEntity.getValue(0,"JDN");
        	user_dn = rEntity.getValue(0, "USER_DN");
        	request.setAttribute("USER_DN", user_dn);
        }
        return result;
    }
    
  private String loadDataUserPub(HttpServletRequest request, UseBeforeParam pm) throws Exception {
        
        ReportEntity rEntity = null;   
        ReportDAO rDAO = new ReportDAO();
        
        String result ="";
        
        rDAO.setValue(1, pm.getScRecvName());
        rEntity = rDAO.select("SELECT OFFI_ID FROM PT_MI_USER WHERE NM = ?");

        if (rEntity.getRowCnt() > 0){
        	result = rEntity.getValue(0,"OFFI_ID");
        }
        
        return result;
    }  
 
    /**
     * ������˻� ������Ȳ ������ �����´�.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadDataUb(HttpServletRequest request, UseBeforeParam pm) throws Exception {
        
        ReportEntity rEntity = null;   
        
        ReportDAO rDAO = new ReportDAO();
        
        String CIV_RECV_NUM   = KJFUtil.print(pm.getScRecvNum());  // ������ ������ȣ
        String SIGUNGU_CODE   = KJFUtil.print(pm.getScSIGUNGU_CODE());  // ������ �ñ����ڵ�
        //String SIGUNGU_CODE = pm.getScSIGUNGU_CODE();
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();
        sbSQL.append(" SELECT SIDO_CODE, SIGUNGU_CODE, APPLPER_NM, RECV_NUM, CIV_RECV_NUM ");
        sbSQL.append(" FROM PT_UB_USEBEFORE ");
        sbSQL.append(" WHERE CIV_RECV_NUM = ? AND SIGUNGU_CODE = ? ");
                    	
        rDAO.setValue(i++, CIV_RECV_NUM);
        rDAO.setValue(i++, SIGUNGU_CODE);

        rEntity = rDAO.select(sbSQL.toString());
        request.setAttribute("rEntity", rEntity);
    }
    /**
     * BOARD_SEQ �ִ밪�� �������� �޼ҵ� 
     * 
     * @param BOARD_ID
     * @param CT_ID
     * @return String 
     * @throws Exception
     */
     private String getMaxSeq(String BOARD_ID) throws Exception {
         
         ReportDAO    rDAO    = new ReportDAO();
         ReportEntity rEntity = null;
         
         StringBuffer sbSQL = new StringBuffer();        
         sbSQL.append(" SELECT " + KJFDBUtil.makeSql_nullFunc() + "  \n");
         sbSQL.append("        ( MAX( UB_LOG_SEQ), 0 ) + 1  MAXSEQ    \n");
         sbSQL.append("   FROM " + BOARD_ID + "                      \n");

         rEntity = rDAO.select(sbSQL.toString());

         return rEntity.getValue(0, "MAXSEQ");
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
        // KJFLog.debug(pm.toString(request));
        
        // ����¡ ����
        if (KJFUtil.isEmpty(pm.getRowPerPage())) {            
            pm.setRowPerPage(Config.props.get("ROW_PER_PAGE"));
        }

        return pm;
    }

}
