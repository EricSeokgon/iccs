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
 * <p>Description : 행정처분 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : 행정처분 처리를 한다. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class InfoAdmMeasurePubCmd implements KJFCommand {
    
    public InfoAdmMeasurePubCmd() {        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {        
        
        UserEnt user = (UserEnt)request.getSession().getAttribute("user");
        
        // 로그인 유무 체크
        if (KJFUtil.isEmpty(user)) {
            throw new LoginException(request, "회원용 페이지 로그인 체크", "../member/login.do");
        }  
        
        // 검색조건 설정및 체크
        ComInfoParam pm = checkPm(request, form);
        
        // 초기데이타 로드
        loadCondition(request, pm);
        
        // 행정처분 정보 
        loadData(request, pm);
        
        return request.getParameter("cmd");
    }
    
    /**
     * 검색조건 및 초기데이타 로드
     * 
     * @param HttpServletRequest
     * @return
     */
    private void loadCondition(HttpServletRequest request, ComInfoParam pm) throws Exception {
        
        // 시도코드
        StringBuffer sbSQL = new StringBuffer();
        sbSQL = new StringBuffer();
        sbSQL.append(" SELECT AREA_CODE, SIDO_NM    \n");
        sbSQL.append("   FROM PT_SIDO_CODE          \n");
        sbSQL.append("  WHERE SUBSTR(AREA_CODE, 1, 2) = SUBSTR(AREA_CODE, 3, 4)    \n");
        sbSQL.append("  ORDER BY SIDO_NM     \n");           
        
        Vector<KJFSelect> v_scSD_CD = KJFSelectOPS.getSel(sbSQL.toString(), "", "시.도 선택");
        request.setAttribute("v_scSD_CD", v_scSD_CD);
        
        // 검색 년도
        Vector<KJFSelect> v_scYear = getYear(11);
        request.setAttribute("v_scYear", v_scYear);
        
        // 검색 월
        getSelMonth();
        Vector<KJFSelect> v_scMonth = getSelMonth();
        request.setAttribute("v_scMonth", v_scMonth);
    }
    
    
    /**
     * 행정처분 정보를 가져온다.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadData(HttpServletRequest request, ComInfoParam pm) throws Exception {
        
    	ReportEntity rEntity = null;
    	
    	String scCode = KJFUtil.print(pm.getScCode());	// 조건
        String scText = KJFUtil.print(pm.getScText());	// 내용
        
        String scFromYear  = KJFUtil.print(pm.getScFromYear());		// 처분기간  From Year
        String scFromMonth = KJFUtil.print(pm.getScFromMonth());	// 처분기간  From Month
        String scToYear    = KJFUtil.print(pm.getScToYear());		// 처분기간  To Year
        String scToMonth   = KJFUtil.print(pm.getScToMonth());		// 처분기간  To Month
        
        String scSidoCode  = KJFUtil.print(pm.getScSidoCode());		// 시도코드	
        
        if ( KJFUtil.isEmpty(scCode) || KJFUtil.isEmpty(scText)) {
            request.setAttribute("pm", pm);
            request.setAttribute("rEntity", rEntity);
            return;
        }
        
        ReportDAO rDAO = new ReportDAO();      
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT                                   \n");
        sbSQL.append("        PT_C.COI_WRT_NUM,                 \n");   // 공사업등록번호
        sbSQL.append("        PT_C.NAME,                 		\n");   // 상호
        sbSQL.append("        PT_C.SIDO_CODE,                 	\n");   // 소재지
        sbSQL.append("        PT_C.MANA_NUM,                 	\n");   // 사업자등록번호        
        sbSQL.append("        PT_M.VIOL_DT,                     \n");   // 위법일자     
        sbSQL.append("        PT_C1.CODE_NAME AS VIOL_CONT,     \n");   // 위법내용     
        sbSQL.append("        PT_M.DISPO_DT,                    \n");   // 처분일자        
        sbSQL.append("        PT_C2.CODE_NAME AS TMPDISPO_CONT, \n");   // 처분구분
        sbSQL.append("        PT_C2.CODE AS DISPO_CODE          \n");   // 처분구분코드
        
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
        
        if (scCode.equals("001")) {         // 등록번호
            whereSQL.append("    AND PT_C.COI_WRT_NUM = ?     \n");
            rDAO.setValue(i++, scText);
            
        } else if (scCode.equals("002")) {  // 상호
            whereSQL.append("    AND PT_C.NAME LIKE ?         \n");
            rDAO.setValue(i++, "%" + scText + "%");
            
        }
        
        // 처분기간 From
        if (!KJFUtil.isEmpty(scFromYear) && !KJFUtil.isEmpty(scFromMonth)) {
        	whereSQL.append("    AND SUBSTR(PT_M.DISPO_DT, 1, 6) >= ?         \n");
            rDAO.setValue(i++, (scFromYear + scFromMonth));
        }
        
        // 처분기간 To
        if (!KJFUtil.isEmpty(scToYear) && !KJFUtil.isEmpty(scToMonth)) {
        	whereSQL.append("    AND SUBSTR(PT_M.DISPO_DT, 1, 6) <= ?         \n");
            rDAO.setValue(i++, (scToYear + scToMonth));
        }
        
        // 시도
        if (!KJFUtil.isEmpty(scSidoCode)) {
        	whereSQL.append("    AND PT_C.SIDO_CODE = ?     \n");
            rDAO.setValue(i++, scSidoCode);
        }
        
        /* ************************** 페이징 관련 START **************************/
        StringBuffer sbCntSQL = new StringBuffer();
        sbCntSQL.append(" SELECT COUNT(*)  CNT  \n");
        sbCntSQL.append("   FROM (PT_R_COMPANY_MASTER PT_C INNER JOIN PT_M_MASTER PT_M     \n");
        sbCntSQL.append("         ON RTRIM(PT_C.TMP_WRT_NUM) = PT_M.TMP_WRT_NUM)           \n");  
        
        //전체 목록 수
        String totalCount="";

        //페이지별 목록 수
        int rowPerPage = KJFUtil.str2int(pm.getRowPerPage());

        //현재 페이지 번호
        int nowPage = 1;
        nowPage = KJFUtil.isEmpty(pm.getNowPage()) ? 1 : Integer.parseInt(pm.getNowPage());

        rEntity = rDAO.select(sbCntSQL.toString() + whereSQL.toString());
        
        totalCount = rEntity.getValue(0, "CNT");
        
        if (rowPerPage == 0) rowPerPage = Integer.parseInt(totalCount);//추가
        if ((rowPerPage*nowPage) - Integer.parseInt(totalCount) > rowPerPage) nowPage = 1;

        pm.setTotalCount(totalCount);
        pm.setNowPage(String.valueOf(nowPage));
        /* *************************** 페이징 관련  END **************************/

        rEntity = rDAO.select(sbSQL.toString() + whereSQL.toString(), nowPage, rowPerPage);

        /****** 검색조건 초기값 ***********/
        request.setAttribute("pm", pm);
        request.setAttribute("rEntity", rEntity);
    }
    
    
    /**
     * 검색 년도를 구한다
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
            sel.setCode_nm(Integer.toString(i) + " 년");                 
            result.add(sel);            
        }
        
        return result;
    }
    
    /**
     * 검색 월을 구한다
     * 
     * @return
     * @throws Exception
     */
    private Vector<KJFSelect> getSelMonth() throws Exception {
    	String[][] selType = 
    		{
    			{"", "====="},
    			{"01", "01 월"},
    			{"02", "02 월"},
    			{"03", "03 월"},
    			{"04", "04 월"},
    			{"05", "05 월"},
    			{"06", "06 월"},
    			{"07", "07 월"}, 
    			{"08", "08 월"}, 
    			{"09", "09 월"}, 
    			{"10", "10 월"}, 
    			{"11", "11 월"}, 
    			{"12", "12 월"}
    		};

        return KJFUtil.makeSelect(selType);
    }
    
    
    /**
     * 폼 체크 메소드
     * 
     * @param request
     * @param form
     * @return
     * @throws Exception
     */
    private ComInfoParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {

        ComInfoParam pm = (ComInfoParam)form;

        // 파라미터 디버깅
        KJFLog.debug(pm.toString(request));
        
        // 페이징 라인
        if (KJFUtil.isEmpty(pm.getRowPerPage())) {            
            pm.setRowPerPage(Config.props.get("ROW_PER_PAGE"));
        }

        return pm;
    }

}
