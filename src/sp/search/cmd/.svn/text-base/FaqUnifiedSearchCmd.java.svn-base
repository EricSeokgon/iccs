package sp.search.cmd;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.cfg.Config;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFLog;
import kjf.util.KJFUtil;

import org.apache.struts.action.ActionForm;

import sp.search.SearchBean;
import sp.search.SearchParam;
import sp.search.SearchWorker;

/**
 * <p>Title       : FaqUnifiedSearchCmd Class</p>
 * <p>Description : FAQ검색 처리 한다.</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : FAQ검색 정보를 처리를 한다. 
 * 
 * @version 1.0
 * @author  PKT
 */
public class FaqUnifiedSearchCmd implements KJFCommand {
        
    public FaqUnifiedSearchCmd() {        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {  
        
        // 검색조건 설정및 체크
        SearchParam pm = checkPm(request, form);
        
        request.setAttribute("pm", pm);
        
        loadList(request, pm);
        
        return request.getParameter("cmd");
    }
    
    /**
     * FAQ검색 정보 Load
     * 
     * @param request
     * @param pm
     * @throws Exception
     */
    private void loadList(HttpServletRequest request, SearchParam pm)throws Exception {
                  
        ReportEntity rEntity = null;
        String scTextValue = KJFUtil.print(pm.getScTextValue());  // 내용
        
        
        if ( KJFUtil.isEmpty(scTextValue) ) {
            request.setAttribute("pm", pm);
            request.setAttribute("rEntity", rEntity);
            return;
        }
        
        ReportDAO rDAO = new ReportDAO();      
        
        int i = 1;
        
        StringBuffer selectSQL = new StringBuffer();        
        selectSQL.append("SELECT *                          \n");
        
        StringBuffer fromSQL = new StringBuffer();      
        fromSQL.append("  FROM (                          \n");
        fromSQL.append("        SELECT *                  \n");
        fromSQL.append("          FROM PT_BBS_FAQ_HOM     \n");
        fromSQL.append("         WHERE (SUBJECT Like ? OR CONTENT like ?)     \n");
        
        rDAO.setValue(i++, "%" + scTextValue + "%");
        rDAO.setValue(i++, "%" + scTextValue + "%");  
        
        fromSQL.append("        UNION ALL                 \n");
        
        fromSQL.append("        SELECT *                  \n");
        fromSQL.append("          FROM PT_BBS_FAQ_PUB     \n");
        fromSQL.append("         WHERE (SUBJECT Like ? OR CONTENT like ?)     \n");
        
        rDAO.setValue(i++, "%" + scTextValue + "%");
        rDAO.setValue(i++, "%" + scTextValue + "%");  
        
        fromSQL.append("        UNION ALL                 \n");

        fromSQL.append("        SELECT *                  \n");
        fromSQL.append("          FROM PT_BBS_FAQ_USE     \n");
        fromSQL.append("         WHERE (SUBJECT Like ? OR CONTENT like ?)     \n");
        
        rDAO.setValue(i++, "%" + scTextValue + "%");
        rDAO.setValue(i++, "%" + scTextValue + "%");  
        
        fromSQL.append("  ) PT_BBS_FAQ                    \n");
        
        String orderbySQL = "  ORDER BY INS_DT                 \n";
                
        /* ************************** 페이징 관련 START **************************/
        StringBuffer sbCntSQL = new StringBuffer();
        sbCntSQL.append(" SELECT COUNT(*)  CNT  \n");
        
        //전체 목록 수
        String totalCount="";

        //페이지별 목록 수
        int rowPerPage = KJFUtil.str2int(pm.getRowPerPage());

        //현재 페이지 번호
        int nowPage = 1;
        nowPage = KJFUtil.isEmpty(pm.getNowPage()) ? 1 : Integer.parseInt(pm.getNowPage());

        rEntity = rDAO.select(sbCntSQL.toString() + fromSQL.toString());
        
        totalCount = rEntity.getValue(0, "CNT");
        
        if (rowPerPage == 0) rowPerPage = Integer.parseInt(totalCount);//추가
        if ((rowPerPage*nowPage) - Integer.parseInt(totalCount) > rowPerPage) nowPage = 1;

        pm.setTotalCount(totalCount);
        pm.setNowPage(String.valueOf(nowPage));
        /* *************************** 페이징 관련  END **************************/

        rEntity = rDAO.select(selectSQL.toString() + fromSQL.toString() + orderbySQL, nowPage, rowPerPage);

        /****** 검색조건 초기값 ***********/
        request.setAttribute("pm", pm);
        request.setAttribute("rEntity", rEntity);      
    }
    
    /**
     * 폼 체크 메소드
     * 
     * @param request
     * @param form
     * @return
     * @throws Exception
     */
    private SearchParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {

        SearchParam pm = (SearchParam)form;

        // 파라미터 디버깅
        KJFLog.debug(pm.toString(request));
        
        // 페이징 라인
        if (KJFUtil.isEmpty(pm.getRowPerPage())) {            
            pm.setRowPerPage(Config.props.get("ROW_PER_PAGE"));
        }

        return pm;
    }

}
