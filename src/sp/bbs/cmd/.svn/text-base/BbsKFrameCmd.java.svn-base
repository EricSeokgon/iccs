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
 * <p>Description : 게시판 List를 Load 처리 한다.</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : 게시판 List 정보를 처리를 한다. 
 * 
 * @version 1.0
 * @author  PKT
 */
public class BbsKFrameCmd implements KJFCommand {
    
    /** 게시판 정보 */
    UserEnt user;
    public BbsKFrameCmd() {        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {
    	
        // 검색조건 설정및 체크
        BbsParam pm = checkPm(request, form);
        request.setAttribute("pm", pm);
        
        // 검색조건 및 초기데이타 로드
        loadCondition(request, pm);
        
        // 게시판 List Load
        loadList(request, pm);       

        // 게시판 List Load
        loadListEtc(request, pm);  
        
        return request.getParameter("cmd");
    }
    
    
    /**
     * 검색조건 및 초기데이타 로드
     * 
     * @param HttpServletRequest
     * @return
     */
    private void loadCondition(HttpServletRequest request, BbsParam pm) throws Exception {
        
    
        
    }
    
    
    /**
     * 게시판 List Load
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

         
        /****** 검색조건 초기값 ***********/
        request.setAttribute("pm", pm);
        request.setAttribute("ListEntity", rEntity);
    }    
    
    /**
     * 게시판 List Load
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

         
        /****** 검색조건 초기값 ***********/
        request.setAttribute("pm", pm);
        request.setAttribute("ListEtcEntity", rEntity);
    }    
    
    /**
     * 게시판 List Load
     * 
     * @param request
     * @param pm
     * @throws Exception
     */
    public String loadSubBbsLoad(HttpServletRequest request, BbsParam pm, String Tables, String sw) throws Exception {
        String SummarySQL    = "";        

        String header = "";
        
        if ("PT_BBS_NEW_CEN_PUB".equals(Tables)){header = "'새소식' as TITLE_HEADER, ";}
        else if ("PT_BBS_NOTICE_CEN_PUB".equals(Tables)){header = "'공지사항' as TITLE_HEADER, ";}
        else if ("PT_BBS_FREE_CEN_PUB".equals(Tables)){header = "'자유게시판' as TITLE_HEADER, ";}
        else if ("PT_BBS_REGIONALISM_PUB".equals(Tables)){header = "'지방분권' as TITLE_HEADER, ";}
        else if ("PT_BBS_TONGSIN_ORG_PUB".equals(Tables)){header = "'정보통신공사업' as TITLE_HEADER, ";}
        else if ("PT_BBS_USE_TONGSIN_PUB".equals(Tables)){header = "'사용전검사' as TITLE_HEADER, ";}
        else if ("PT_BBS_DISIGN_TONGSIN_PUB".equals(Tables)){header = "'착공전설계도확인' as TITLE_HEADER, ";}
        else if ("PT_BBS_PDA_CEN_PUB".equals(Tables)){header = "'자료실' as TITLE_HEADER, ";}
        else if ("PT_BBS_LAW_RECHANGE_PUB".equals(Tables)){header = "'법령·제개정' as TITLE_HEADER, ";}
        else if ("PT_BBS_WORKLOAD_CEN_PUB".equals(Tables)){header = "'업무편람' as TITLE_HEADER, ";}
        else if ("PT_BBS_FAQ_CEN_PUB".equals(Tables)){header = "'질의회신' as TITLE_HEADER, ";}
        
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
     * 검색조건 초기값 설정및 체크
     * 
     * @param request
     * @param form
     * @return BbsParam
     * @throws Exception
     */
    private BbsParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {

        BbsParam pm = (BbsParam)form;

        // 파라미터 디버깅
        KJFLog.debug(pm.toString(request));
      
        return pm;
    }
}
