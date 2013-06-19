package sp.cominfo.cmd;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.cfg.Config;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFDate;
import kjf.util.KJFLog;
import kjf.util.KJFSelect;
import kjf.util.KJFSelectOPS;
import kjf.util.KJFUtil;
import kjf.util.LoginException;

import org.apache.struts.action.ActionForm;

import sp.cominfo.ComInfoParam;
import sp.uent.UserEnt;

/***************************************************************************
 * <p>Title       : InfoExecValuationCmd Class</p>
 * <p>Description : 시공능력평가 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : 시공능력평가 처리를 한다. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class InfoExecValuationPubCmd implements KJFCommand {
    
    public InfoExecValuationPubCmd() {        
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
        
        // 시공능력평가 정보 
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
    }
    
    
    /**
     * 시공능력평가 정보를 가져온다.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadData(HttpServletRequest request, ComInfoParam pm) throws Exception {
        
        ReportEntity rEntity = null;
        
        String scCode = KJFUtil.print(pm.getScCode());
        String scText = KJFUtil.print(pm.getScText());
        
        if ( KJFUtil.isEmpty(scCode) || KJFUtil.isEmpty(scText)) {
            request.setAttribute("pm", pm);
            request.setAttribute("rEntity", rEntity);
            return;
        }                
        
        ReportDAO rDAO = new ReportDAO(); 
        
        int i = 1;
        
        StringBuffer selectSQL = new StringBuffer();        
        selectSQL.append(" SELECT                            \n");
        selectSQL.append("        PT_M.TMP_WRT_NUM,          \n");   // 가등록번호
        selectSQL.append("        PT_M.COI_WRT_NUM,          \n");   // 공사업등록번호
        selectSQL.append("        PT_M.NAME,                 \n");   // 상호
        selectSQL.append("        PT_M.REP_NM_KOR,           \n");   // 대표자
        selectSQL.append("        PT_M.SIDO_CODE,            \n");   // 소재지
        selectSQL.append("        PT_M.ADDR_TEL_NUM,         \n");   // 전화번호
        selectSQL.append("        PT_C.ASSE_DT,              \n");   // 시공능력일자
        selectSQL.append("        PT_C.ASSE_AOM              \n");   // 시공능력평가액        
        
        StringBuffer fromSQL = new StringBuffer();   
        fromSQL.append("   FROM PT_R_COMPANY_MASTER PT_M                                    \n");      
        fromSQL.append("   INNER JOIN (                                                     \n");  
        fromSQL.append("               SELECT TMP_WRT_NUM, COI_WRT_NUM, ASSE_DT, ASSE_AOM   \n"); 
        fromSQL.append("                 FROM PT_R_WORK_CAPABILITY                          \n");
        
        if (!KJFUtil.isEmpty(pm.getScYear())) {
           fromSQL.append("                WHERE SUBSTR(ASSE_DT , 1, 4) = ?                 \n");
           rDAO.setValue(i++, isAsseDate());
        }
        
        fromSQL.append("              ) PT_C ON PT_M.TMP_WRT_NUM = PT_C.TMP_WRT_NUM         \n");
        fromSQL.append("                    AND PT_M.COI_WRT_NUM = PT_C.COI_WRT_NUM         \n");
        
        StringBuffer whereSQL = new StringBuffer(); 
        whereSQL.append("  WHERE 1=1  \n");   // 공사업등록번호  
        
        if (scCode.equals("001")) {         // 등록번호
            whereSQL.append("    AND PT_M.COI_WRT_NUM = ?     \n");
            rDAO.setValue(i++, scText);
            
        } else if (scCode.equals("002")) {  // 상호
            whereSQL.append("    AND PT_M.NAME LIKE ?         \n");
            rDAO.setValue(i++, "%" + scText + "%");
            
        } else if (scCode.equals("003")) {  // 대표자
            whereSQL.append("    AND PT_M.REP_NM_KOR LIKE ?   \n");
            rDAO.setValue(i++, "%" + scText + "%");
        }         
        
        // 평가액 From
        if (!KJFUtil.isEmpty(pm.getScFromAsse())) {
            whereSQL.append("    AND PT_C.ASSE_AOM >= ?   \n");
            rDAO.setValue(i++, pm.getScFromAsse());            
        }
        
        // 평가액 To
        if (!KJFUtil.isEmpty(pm.getScToAsse())) {
            whereSQL.append("    AND PT_C.ASSE_AOM <= ?   \n");
            rDAO.setValue(i++, pm.getScToAsse());            
        }
        
        // 시도
        if (!KJFUtil.isEmpty(pm.getScSidoCode())) {
            whereSQL.append("    AND PT_M.SIDO_CODE = ?   \n");
            rDAO.setValue(i++, pm.getScSidoCode());            
        }
        
        // 년도
        if (!KJFUtil.isEmpty(pm.getScYear())) {
            whereSQL.append("    AND SUBSTR(PT_C.ASSE_DT , 1, 4) = ?   \n");
            rDAO.setValue(i++, pm.getScYear());            
        }
        
        /* ************************** 페이징 관련 START **************************/
        StringBuffer sbCntSQL = new StringBuffer();
        sbCntSQL.append(" SELECT COUNT(*)  CNT              \n");
        sbCntSQL.append(fromSQL);
        sbCntSQL.append(whereSQL); 
        
        //전체 목록 수
        String totalCount="";

        //페이지별 목록 수
        int rowPerPage = KJFUtil.str2int(pm.getRowPerPage());

        //현재 페이지 번호
        int nowPage = 1;
        nowPage = KJFUtil.isEmpty(pm.getNowPage()) ? 1 : Integer.parseInt(pm.getNowPage());

        rEntity = rDAO.select(sbCntSQL.toString());
        
        totalCount = rEntity.getValue(0, "CNT");
        
        if (rowPerPage == 0) rowPerPage = Integer.parseInt(totalCount);//추가
        if ((rowPerPage*nowPage) - Integer.parseInt(totalCount) > rowPerPage) nowPage = 1;

        pm.setTotalCount(totalCount);
        pm.setNowPage(String.valueOf(nowPage));
        /* *************************** 페이징 관련  END **************************/

        rEntity = rDAO.select(selectSQL.toString() + fromSQL.toString() + whereSQL.toString(), nowPage, rowPerPage);

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
        sel.setCode_nm("년도선택");                 
        result.add(sel);
        
        for (int i = cal.get(Calendar.YEAR); i > (cal.get(Calendar.YEAR) - yearTerm); i--) {
            
            sel= new KJFSelect();               
            sel.setCode(Integer.toString(i));                  
            sel.setCode_nm(Integer.toString(i));                 
            result.add(sel);            
        }
        
        return result;
    }
    
    private String isAsseDate() throws Exception {
        
        Calendar cal = Calendar.getInstance();
        
        String todayYear  = Integer.toString(cal.get(Calendar.YEAR)); 
        
        String today     = "";                                      // 오늘 날짜 
        String acce_date = todayYear + "-" + "06" + "-" + "31";     // 공시일 일자        
        
        Date todaydate = new Date();   // 시스템 날짜를 사용할  mydate객체 생성
        Date accedate  = new Date();   // 게시물에 등록일을  사용할 indate객체 생성
          
        // 출력 형태를 가지고 있는 객체를 생성
        SimpleDateFormat myToday = new SimpleDateFormat("yyyy-MM-dd");        
        
        accedate  = KJFDate.str2date(acce_date, "yyyy-MM-dd");     
        
        today     = myToday.format(todaydate);
        acce_date = myToday.format(accedate);           
          
        // 오늘이 공시일 이상일 경우 올해년도 이하일경우 전년도
        if ( 0 < KJFDate.getDaysDiff(today, acce_date)) {
            return Integer.toString(cal.get(Calendar.YEAR));
              
        } else {
            return Integer.toString(cal.get(Calendar.YEAR)-1);
        }
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
