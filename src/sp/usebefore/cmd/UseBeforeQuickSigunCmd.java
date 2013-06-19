package sp.usebefore.cmd;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.cfg.Config;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFLog;
import kjf.util.KJFUtil;

import org.apache.struts.action.ActionForm;

import sp.usebefore.UseBeforeParam;

/***************************************************************************
 * <p>Title       : UseBeforeQuickStatus Class</p>
 * <p>Description : ����������˻� ������Ȳ Ŭ����</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : ����������˻� ������Ȳ�� ó���� �Ѵ�.(�˻����� ����Ʈ ���) 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class UseBeforeQuickSigunCmd implements KJFCommand {
    
    public UseBeforeQuickSigunCmd() {   
        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {        
            
        // �˻����� ������ üũ
        UseBeforeParam pm = checkPm(request, form);
        
        /****** �˻����� �ʱⰪ ***********/
        request.setAttribute("pm", pm);
        
        // ���� ������˻� ��Ȳ ���� 
        loadData(request, pm);
        
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
        
        // �ñ��� �ڵ尪���� �ι� decode �� ������ ���� 
        // KJFUtil.base64Decode(KJFUtil.base64Decode(gbcd)) ==> WjJKalpBPT0=
        String sigungu_code = KJFUtil.print(request.getParameter("getCode"),"");
        
        if (KJFUtil.isEmpty(sigungu_code) || "".equals(sigungu_code)) {
          return;
        }  else {
            pm.setScSIGUNGU_CODE(sigungu_code);
        	sigungu_code = KJFUtil.base64Decode(KJFUtil.base64Decode(sigungu_code));
        	if (!"gbcd".equals(sigungu_code)){
        		return;
        	}
        }
        
        String scRecvName = KJFUtil.print(pm.getScRecvName());  // ������ �̸�
        String scRecvNum  = KJFUtil.print(pm.getScRecvNum());   // ������ ������ȣ
        
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();
        sbSQL.append(" SELECT                   \n");
        sbSQL.append("        UB.CIV_RECV_NUM,     \n");   // �ý��� ������ȣ
        sbSQL.append("        UB.RECV_NUM,         \n");   // ����� ������ȣ
        sbSQL.append("        UB.APPLPER_NM,       \n");   // ��û��
        sbSQL.append("        UB.OPE_NAME,         \n");   // �ð���ü
        sbSQL.append("        UB.INSP_SPOT_ADDR,UB.INSP_SPOT_DETAILADDR ,     \n");   // �����ּ�
        sbSQL.append("        UB.RECV_DT,          \n");   // ��������    
        sbSQL.append(" 		  UB.INSP_WISH_YMD,    \n"); // �˻������
        sbSQL.append("        UB.INSP_DT,          \n");   // �˻���
        
        // �������
        sbSQL.append("        DECODE(UB.PROC_STE, '1', '����ó��',        \n");   
        sbSQL.append("                         '2', 'ó����',          \n");  
        sbSQL.append("                         '3', 'ó���Ϸ�',        \n");  
        sbSQL.append("                         '') AS PROC_STE,    \n");  // ó������
        sbSQL.append("        UB.SIDO_CODE,   \n"); // �õ��ڵ�
        sbSQL.append("        UB.SIGUNGU_CODE    \n"); // �ñ����ڵ�
        sbSQL.append("	FROM PT_UB_USEBEFORE UB \n");
        
        sbSQL.append(" WHERE UB.SIGUNGU_CODE = '"+sigungu_code+"' \n");
        

        if (!KJFUtil.isEmpty(scRecvName)){
        	sbSQL.append(" AND UB.APPLPER_NM = '"+scRecvName+"' \n");
        }
        if (!KJFUtil.isEmpty(scRecvNum)) {
            sbSQL.append(" AND ( CASE  WHEN LENGTH(UB.CIV_RECV_NUM) = 18 THEN substr(UB.CIV_RECV_NUM,12,LENGTH(UB.CIV_RECV_NUM)) \n");
            sbSQL.append("   ELSE UB.CIV_RECV_NUM END )  = '"+scRecvNum+"' \n");
        }
        
        //rDAO.setValue(i++, sigungu_code);

        /* ************************** ����¡ ���� START **************************/
        StringBuffer sbCntSQL = new StringBuffer();
        sbCntSQL.append(" SELECT COUNT(*)  CNT      \n");
        sbCntSQL.append("   FROM PT_UB_USEBEFORE    \n"); 
        sbCntSQL.append(" WHERE SIGUNGU_CODE = '"+sigungu_code+"' \n");
        
        if (!KJFUtil.isEmpty(scRecvName)){
        	sbCntSQL.append(" AND APPLPER_NM = '"+scRecvName+"' \n");
        }
        if (!KJFUtil.isEmpty(scRecvNum)) {
        	sbCntSQL.append(" AND ( CASE  WHEN LENGTH(CIV_RECV_NUM) = 18 THEN substr(CIV_RECV_NUM,12,LENGTH(CIV_RECV_NUM)) \n");
        	sbCntSQL.append("   ELSE CIV_RECV_NUM END )  = '"+scRecvNum+"' \n");
        }
        
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

        rEntity = rDAO.select(sbSQL.toString(), nowPage, rowPerPage);
       
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
