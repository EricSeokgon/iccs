package sp.chart.cmd;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kjf.action.KJFCommandResp;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFArea;
import kjf.util.KJFLog;
import kjf.util.KJFUtil;

import org.apache.struts.action.ActionForm;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import sp.chart.ChartParam;
import sp.chart.ChartWorker;

/***************************************************************************
 * <p>Title       : ChartAreaCmd Class</p>
 * <p>Description : 정보통신공사업 연간 누적현황 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : 정보통신공사업 연간 누적현황 정보 처리를 한다. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class ChartYearCmd implements KJFCommandResp {
    
    public ChartYearCmd() {        
    }
    
    public String execute(HttpServletRequest request, HttpServletResponse response,  ActionForm form) throws Exception { 
       
        // 검색조건 설정및 체크
        ChartParam pm = checkPm(request, form);      
        request.setAttribute("pm", pm);
        
        ChartWorker worker = new ChartWorker();
        
        worker.ChartProcess(request, response, pm);
        
        return request.getParameter("cmd");
    }
    
    
    /**
     * HTML 컨텐츠 정보를 불러온다
     * 
     * @param request
     * @param form
     * @return
     * @throws Exception
     */
    private CategoryDataset createDataset(HttpServletRequest request) throws Exception {        
        
        ReportDAO    rDAO        = new ReportDAO();
        ReportEntity rEntity     = null;
        
        Calendar cal = Calendar.getInstance();
        
        String sido_code = KJFUtil.print(request.getParameter("st_sido_code"));
        
        int cnt = 1;
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT                                               \n");        
        sbSQL.append("        SUBSTR(COMPANY_DIAG_BAS_DT, 0, 4) AS YEAR,    \n");   // 년도
        sbSQL.append("        COUNT(*) AS CNT                               \n");   // 통계
        
        sbSQL.append("   FROM PT_R_COMPANY_MASTER                           \n");
        sbSQL.append("  WHERE 1=1                                           \n");  
        
        if (!KJFUtil.isEmpty(sido_code)) {
            sbSQL.append("    AND SIDO_CODE = ?     \n");
            rDAO.setValue(cnt++, sido_code);
        }
        
        sbSQL.append("    AND SUBSTR(COMPANY_DIAG_BAS_DT, 0, 4) BETWEEN ? AND ? \n");
        sbSQL.append("  GROUP BY SUBSTR(COMPANY_DIAG_BAS_DT, 0, 4)  \n");
        sbSQL.append("  ORDER BY SUBSTR(COMPANY_DIAG_BAS_DT, 0, 4)  \n");        
        
        rDAO.setValue(cnt++, (cal.get(Calendar.YEAR)-7));
        rDAO.setValue(cnt++, cal.get(Calendar.YEAR));
        
        rEntity = rDAO.select(sbSQL.toString());
        
        // create the dataset...
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        if (rEntity.getRowCnt() > 0) {
            
            for (int i = 0; i < rEntity.getRowCnt(); i++) {                                    
                dataset.addValue(KJFUtil.str2int(rEntity.getValue(i, "CNT")), rEntity.getValue(i, "YEAR"), rEntity.getValue(i, "YEAR"));             
            }
        }       
        
        return dataset;        
    }
    
    /**
     * 검색조건 초기값 설정및 체크
     * 
     * @param request
     * @param form
     * @return BbsParam
     * @throws Exception
     */
    private ChartParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {

        ChartParam pm = (ChartParam) form;
        
        String chartTitle = "연간 누적현황 전체";
        
        if (KJFUtil.isEmpty(pm.getChartHeight())){
            pm.setChartHeight("330");
        }
        
        if (KJFUtil.isEmpty(pm.getChartWidth())){
            pm.setChartWidth("660");
        }
        
        if (!KJFUtil.isEmpty(KJFUtil.print(request.getParameter("st_sido_code")))) {
            chartTitle = "연간 누적현황 " + KJFArea.getSido_Name(KJFUtil.print(request.getParameter("st_sido_code")));
        }
        
        pm.setChartTitle(chartTitle);
        pm.setDomainAxisLabel("");
        pm.setRangeAxisLabel("");
        pm.setChartLegend(false);
        pm.setChartTooltips(false);
        pm.setChartUrl(false);
        
        pm.setDataSet(createDataset(request));

        // 파라미터 디버깅
        KJFLog.debug(pm.toString(request));

        return pm;
    } 

}
