package sp.statistics.cmd;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.*;

import org.apache.struts.action.ActionForm;

import sp.statistics.StatisticsParam;

/***************************************************************************
 * <p>Title       : StatisticalDataCmd Class</p>
 * <p>Description : 통계자료 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : 정보통신 공사업 통계자료 정보 처리를 한다. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class StatisticalDataCmd implements KJFCommand {
        
    public StatisticalDataCmd() {        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {        
                
        // 검색조건 설정및 체크
    	StatisticsParam pm = checkPm(request, form);
        request.setAttribute("pm", pm);
        
        // 지역 통계자료 정보
        loadStatisticalAreaData(request, pm);
        
        // 년도 통계자료 정보
        loadStatisticalYearData(request, pm);
        
        return request.getParameter("cmd");
    }
    
    
    /**
     * 지역 통계자료 정보를 가져온다.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadStatisticalAreaData(HttpServletRequest request, StatisticsParam pm) throws Exception {
        
        ReportEntity rEntity = null;        
        
        ReportDAO rDAO = new ReportDAO();   
                
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT                              		\n");        
       
        sbSQL.append("        PT_AREA.SIDO_NM,                	\n");   // 시도명 
        sbSQL.append("        PT_MST.SIDO_CODE,                 \n");   // 시도코드
        sbSQL.append("        COUNT(PT_MST.SIDO_CODE) AS CNT	\n");   // 통계
        
        sbSQL.append("   FROM PT_R_COMPANY_MASTER PT_MST     	\n");
        
        sbSQL.append("  INNER JOIN (                                     						\n");
        sbSQL.append("  			SELECT AREA_CODE, SIDO_NM    		 						\n");  
        sbSQL.append("  			  FROM PT_SIDO_CODE               	 						\n");  
        sbSQL.append("               WHERE SUBSTR(AREA_CODE, 1, 2) = SUBSTR(AREA_CODE, 3, 4)	\n");        
        sbSQL.append("             ) PT_AREA ON PT_AREA.AREA_CODE = PT_MST.SIDO_CODE  			\n");      
        sbSQL.append("  GROUP BY PT_MST.SIDO_CODE, PT_AREA.SIDO_NM      						\n");  
        sbSQL.append("  ORDER BY PT_AREA.SIDO_NM                                                \n");  
        
        rEntity = rDAO.select(sbSQL.toString());

        /****** 검색조건 초기값 ***********/
        request.setAttribute("rEntity", rEntity);        
    }
    
    
    /**
     * 년도 통계자료 정보를 가져온다.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadStatisticalYearData(HttpServletRequest request, StatisticsParam pm) throws Exception {
        
        ReportEntity rEntity = null;        
        
        ReportDAO rDAO = new ReportDAO();
        
        Calendar cal = Calendar.getInstance();
        
        System.out.println(pm.getSt_sido_code());
        System.out.println(cal.get(Calendar.YEAR));
        System.out.println(cal.get(Calendar.YEAR)-7);
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT                                				\n");        
        sbSQL.append("        SUBSTR(COMPANY_DIAG_BAS_DT, 0, 4) AS YEAR,	\n");   // 년도
        sbSQL.append("        COUNT(*) AS CNT               				\n");   // 통계
        
        sbSQL.append("   FROM PT_R_COMPANY_MASTER       \n");
        sbSQL.append("  WHERE 1=1                       \n");
                
        if (!KJFUtil.isEmpty(pm.getSt_sido_code())) {
            sbSQL.append("    AND SIDO_CODE = ?     \n");
            rDAO.setValue(i++, pm.getSt_sido_code());
        }
        
        sbSQL.append("  AND SUBSTR(COMPANY_DIAG_BAS_DT, 0, 4) BETWEEN ? AND ? \n");
                
        sbSQL.append("  GROUP BY SUBSTR(COMPANY_DIAG_BAS_DT, 0, 4)	\n");
        sbSQL.append("  ORDER BY SUBSTR(COMPANY_DIAG_BAS_DT, 0, 4)	\n");        
        
        rDAO.setValue(i++, Integer.toString((cal.get(Calendar.YEAR)-7)));
        rDAO.setValue(i++, Integer.toString(cal.get(Calendar.YEAR)));
        
        rEntity = rDAO.select(sbSQL.toString());

        /****** 검색조건 초기값 ***********/
        request.setAttribute("yEntity", rEntity);        
    }
    
    
    /**
     * 폼 체크 메소드
     * 
     * @param request
     * @param form
     * @return
     * @throws Exception
     */
    private StatisticsParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {

    	StatisticsParam pm = (StatisticsParam)form;

        // 파라미터 디버깅
        KJFLog.debug(pm.toString(request));
     
        return pm;
    }

}
