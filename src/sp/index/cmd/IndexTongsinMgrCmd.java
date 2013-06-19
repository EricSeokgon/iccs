package sp.index.cmd;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.cfg.Config;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFLog;
import kjf.util.KJFUtil;

import org.apache.struts.action.ActionForm;

import sp.index.IndexParam;
import sp.uent.UserEnt;

/****************************************************************************
 * <p>Title       : IndexCmd Class</p>
 * <p>Description : ���� ó���� �ϴ� Ŭ����</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 *
 * NOTE : ���� ������ ���ǿ� ���� ���� Load�Ѵ�.
 *
 * @version
 * @author PKT
 ***************************************************************************/
public class IndexTongsinMgrCmd implements KJFCommand {
	
	public String execute(HttpServletRequest request, ActionForm form) throws Exception {
		
		// �˻����� ������ üũ
		IndexParam pm = checkPm(request, form);
		
		UserEnt user = (UserEnt)request.getSession().getAttribute("user");
		
		ReportEntity NOTICE;
		ReportEntity PDS;
		ReportEntity ITINFO;
		
		if (KJFUtil.isEmpty(user) || "U".equals(user.getCAPITAL()) || "UE".equals(user.getCAPITAL())){
			NOTICE = new ReportEntity();
			PDS = new ReportEntity();
			ITINFO = new ReportEntity();
		} else {
			NOTICE     = selectBoarList(request, "BOD_NOTICE_11", true, true, 4);       // ��������
			PDS        = selectBoarList(request, "BOD_DATA_1", true, true, 4);         	// �ڷ��
			ITINFO     = selectBoarList(request, "BOD_DATA_4", true, true, 4);         	// IT�ֱټҽ�
		}
		
		request.setAttribute("NOTICE", NOTICE);           // ��������
		request.setAttribute("PDS", PDS);               // ��������
		request.setAttribute("ITINFO", ITINFO);   // IT�ֱټҽ�
		
		request.setAttribute("pm", pm); 
		
		return request.getParameter("cmd");
    }
	
	
	/**
	 * ���� �Խ��� ����Ʈ�� �������� �޼ҵ�
	 * 
	 * @param request  HttpServletRequest
	 * @param CT_ID    �Խ���
	 * @param isSD_CD  �õ� �ڵ� ��뿩��
	 * @param isSGG_CD �ñ��� �ڵ� ��뿩��
	 * @param rowCnt   ����Ʈ ����
	 * @return ReportEntity
	 * @throws Exception
	 */
    public ReportEntity selectBoarList(
                                        HttpServletRequest request, 
                                        String CT_ID, 
                                        boolean isSD_CD, 
                                        boolean isSGG_CD, 
                                        int rowCnt) throws Exception {

        ReportDAO    rDAO    = new ReportDAO();
        ReportEntity rEntity = null;
        
        UserEnt user = (UserEnt)request.getSession().getAttribute("user");
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();
        sbSQL.append(" SELECT                               \n");
        sbSQL.append("        BOARD_SEQ,                    \n");
        sbSQL.append("        CT_ID,                        \n");
        sbSQL.append("        INDEX1,                       \n");
        sbSQL.append("        INDEX2,                       \n");
        sbSQL.append("        DEPTH,                        \n");
        sbSQL.append("        USER_ID,                      \n");
        sbSQL.append("        SUBJECT,                      \n");
        sbSQL.append("        CONTENT,                      \n");
        sbSQL.append("        UPD_DT,                       \n");
        sbSQL.append("        INS_DT,                       \n");
        sbSQL.append("        WRT_ID                        \n");
        sbSQL.append("   FROM PT_BBS_" + CT_ID + "          \n");
        sbSQL.append("  WHERE 1 = 1                         \n");

        sbSQL.append("  ORDER BY INDEX1 DESC, INDEX2 ASC    \n");
        
        rEntity = rDAO.select(sbSQL.toString(), rowCnt);

        return rEntity;
    }
	
	
	/**
     * �� üũ �޼ҵ�
     * 
     * @param  request
     * @param  form
     * @return IndexParam
     * @throws Exception
     */
    private IndexParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {

    	IndexParam pm = (IndexParam)form;

        // �Ķ���� �����
        KJFLog.debug(pm.toString(request));
        
        // ����¡ ����
        if (KJFUtil.isEmpty(pm.getRowPerPage())) {            
            pm.setRowPerPage(Config.props.get("ROW_PER_PAGE"));
        }

        return pm;
    }
}
