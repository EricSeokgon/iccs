package sp.bbs.cmd;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFDate;
import kjf.util.KJFLog;
import kjf.util.KJFSelect;
import kjf.util.KJFSelectOPS;
import kjf.util.KJFUtil;
import kjf.util.LoginException;
import kjf.util.MsgException;


import org.apache.struts.action.ActionForm;

import sp.bbs.BbsParam;
import sp.bbs.BbsSetConf;
import sp.bbs.StatusEnt;
import sp.uent.UserEnt;

/**
 * <p>Title       : BbsKListCmd Class</p>
 * <p>Description : �Խ��� List�� Load ó�� �Ѵ�.</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : �Խ��� List ������ ó���� �Ѵ�. 
 * 
 * @version 1.0
 * @author  PKT
 */
public class BbsKFrameCmd implements KJFCommand {
    
    /** �Խ��� ���� */
    UserEnt user;
    public BbsKFrameCmd() {        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {
    	
        // �˻����� ������ üũ
        BbsParam pm = checkPm(request, form);
        request.setAttribute("pm", pm);
        
        // �˻����� �� �ʱⵥ��Ÿ �ε�
        loadCondition(request, pm);
        
        // �Խ��� List Load
        loadList(request, pm);       

        // �Խ��� List Load
        loadListEtc(request, pm);  
        
        return request.getParameter("cmd");
    }
    
    
    /**
     * �˻����� �� �ʱⵥ��Ÿ �ε�
     * 
     * @param HttpServletRequest
     * @return
     */
    private void loadCondition(HttpServletRequest request, BbsParam pm) throws Exception {
        
    
        
    }
    
    
    /**
     * �Խ��� List Load
     * 
     * @param request
     * @param pm
     * @throws Exception
     */
    private void loadList(HttpServletRequest request, BbsParam pm) throws Exception {

        ReportDAO rDAO = new ReportDAO();
        
        ReportEntity rEntity = null;
        
        String SummarySQL = "SELECT * FROM(SELECT * FROM (";
        SummarySQL = SummarySQL + loadSubBbsLoad(request,pm,"PT_BBS_NEW_CEN_PUB","0");
        SummarySQL = SummarySQL + loadSubBbsLoad(request,pm,"PT_BBS_NOTICE_CEN_PUB","0");
        SummarySQL = SummarySQL + loadSubBbsLoad(request,pm,"PT_BBS_FREE_CEN_PUB","0");
        SummarySQL = SummarySQL + loadSubBbsLoad(request,pm,"PT_BBS_REGIONALISM_PUB","0");
        SummarySQL = SummarySQL + loadSubBbsLoad(request,pm,"PT_BBS_TONGSIN_ORG_PUB","0");
        SummarySQL = SummarySQL + loadSubBbsLoad(request,pm,"PT_BBS_USE_TONGSIN_PUB","0");
        SummarySQL = SummarySQL + loadSubBbsLoad(request,pm,"PT_BBS_DISIGN_TONGSIN_PUB","0");
        SummarySQL = SummarySQL + loadSubBbsLoad(request,pm,"PT_BBS_PDA_CEN_PUB","1");
        SummarySQL = SummarySQL + " ) ORDER BY INS_DT DESC) WHERE ROWNUM <= 8 ";
        rEntity = rDAO.select(SummarySQL);

         
        /****** �˻����� �ʱⰪ ***********/
        request.setAttribute("pm", pm);
        request.setAttribute("ListEntity", rEntity);
    }    
    
    /**
     * �Խ��� List Load
     * 
     * @param request
     * @param pm
     * @throws Exception
     */
    private void loadListEtc(HttpServletRequest request, BbsParam pm) throws Exception {

        ReportDAO rDAO = new ReportDAO();
        
        ReportEntity rEntity = null;
        
        String SummarySQL = "SELECT * FROM(SELECT * FROM (";
        SummarySQL = SummarySQL + loadSubBbsLoad(request,pm,"PT_BBS_LAW_RECHANGE_PUB","0");
        SummarySQL = SummarySQL + loadSubBbsLoad(request,pm,"PT_BBS_WORKLOAD_CEN_PUB","0");
        SummarySQL = SummarySQL + loadSubBbsLoad(request,pm,"PT_BBS_FAQ_CEN_PUB","1");
        SummarySQL = SummarySQL + " ) ORDER BY INS_DT DESC) WHERE ROWNUM <= 8 ";
                
        rEntity = rDAO.select(SummarySQL);

         
        /****** �˻����� �ʱⰪ ***********/
        request.setAttribute("pm", pm);
        request.setAttribute("ListEtcEntity", rEntity);
    }    
    
    /**
     * �Խ��� List Load
     * 
     * @param request
     * @param pm
     * @throws Exception
     */
    public String loadSubBbsLoad(HttpServletRequest request, BbsParam pm, String Tables, String sw) throws Exception {
        String SummarySQL    = "";        

        String header = "";
        
        if ("PT_BBS_NEW_CEN_PUB".equals(Tables)){header = "'���ҽ�' as TITLE_HEADER, ";}
        else if ("PT_BBS_NOTICE_CEN_PUB".equals(Tables)){header = "'��������' as TITLE_HEADER, ";}
        else if ("PT_BBS_FREE_CEN_PUB".equals(Tables)){header = "'�����Խ���' as TITLE_HEADER, ";}
        else if ("PT_BBS_REGIONALISM_PUB".equals(Tables)){header = "'����б�' as TITLE_HEADER, ";}
        else if ("PT_BBS_TONGSIN_ORG_PUB".equals(Tables)){header = "'������Ű����' as TITLE_HEADER, ";}
        else if ("PT_BBS_USE_TONGSIN_PUB".equals(Tables)){header = "'������˻�' as TITLE_HEADER, ";}
        else if ("PT_BBS_DISIGN_TONGSIN_PUB".equals(Tables)){header = "'���������赵Ȯ��' as TITLE_HEADER, ";}
        else if ("PT_BBS_PDA_CEN_PUB".equals(Tables)){header = "'�ڷ��' as TITLE_HEADER, ";}
        else if ("PT_BBS_LAW_RECHANGE_PUB".equals(Tables)){header = "'���ɡ�������' as TITLE_HEADER, ";}
        else if ("PT_BBS_WORKLOAD_CEN_PUB".equals(Tables)){header = "'�������' as TITLE_HEADER, ";}
        else if ("PT_BBS_FAQ_CEN_PUB".equals(Tables)){header = "'����ȸ��' as TITLE_HEADER, ";}
        
        // Select Query
        StringBuffer sbSelectSQL = new StringBuffer();        
        sbSelectSQL.append(" SELECT "+header+" CT_ID, BOARD_SEQ, SUBJECT,USER_NAME,INS_DT  ");
       
        // From Query
        StringBuffer sbFromSQL = new StringBuffer();
        sbFromSQL.append("  FROM ( SELECT CT_ID, BOARD_SEQ, SUBJECT,USER_NAME,INS_DT FROM "+Tables+" \n");
        sbFromSQL.append("  ORDER BY INS_DT DESC, BOARD_SEQ DESC) \n");
        
        // Where Query
        StringBuffer sbWhereSQL = new StringBuffer();        
        sbWhereSQL.append(" WHERE ROWNUM <= 8              \n");   
        
//        sbWhereSQL.append(" AND SUBSTR(INS_DT,0,10) BETWEEN TO_CHAR(SYSDATE-3,'YYYY-MM-DD') AND TO_CHAR(SYSDATE,'YYYY-MM-DD') \n");
                    
        String UnionSQL = " UNION ALL \n";
        
        if ("1".equals(sw)){
        	SummarySQL = sbSelectSQL.toString() + sbFromSQL.toString() + sbWhereSQL.toString() ;
        } else {
        	SummarySQL = sbSelectSQL.toString() + sbFromSQL.toString() + sbWhereSQL.toString() + UnionSQL;
        }
        
        return SummarySQL.toString();
       
    }    
    /**
     * �˻����� �ʱⰪ ������ üũ
     * 
     * @param request
     * @param form
     * @return BbsParam
     * @throws Exception
     */
    private BbsParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {

        BbsParam pm = (BbsParam)form;

        // �Ķ���� �����
        KJFLog.debug(pm.toString(request));
      
        return pm;
    }
}
