package sp.cominfo.cmd;

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
 * <p>Title       : InfoCommTraderCmd Class</p>
 * <p>Description : 정보통신공사업자 검색 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : 정보통신공사업자 검색 처리를 한다. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class InfoCommTraderPubCmd implements KJFCommand {
    
    public InfoCommTraderPubCmd() {        
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
        
        // 정보통신공사업자 검색
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
    }
    
    
    /**
     * 정보통신공사업자 정보를 가져온다.
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
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT                       \n");
        sbSQL.append("        TMP_WRT_NUM,          \n");   // 가등록번호
        sbSQL.append("        COI_WRT_NUM,          \n");   // 공사업등록번호
        sbSQL.append("        MANA_NUM,          	\n");   // 사업자등록번호        
        sbSQL.append("        NAME,                 \n");   // 상호
        sbSQL.append("        REP_NM_KOR,           \n");   // 대표자
        sbSQL.append("        SIDO_CODE,            \n");   // 소재지
        sbSQL.append("        ADDR_TEL_NUM          \n");   // 전화번호
        sbSQL.append("   FROM PT_R_COMPANY_MASTER   \n");
        
        StringBuffer sbWhereSQL = new StringBuffer(); 
        sbWhereSQL.append("  WHERE 1=1  \n");   // 공사업등록번호
        
        if (scCode.equals("001")) {         // 등록번호
            sbWhereSQL.append("    AND COI_WRT_NUM = ?     \n");
            rDAO.setValue(i++, scText);
            
        } else if (scCode.equals("002")) {  // 상호
            sbWhereSQL.append("    AND NAME LIKE ?         \n");
            rDAO.setValue(i++, "%" + scText + "%");
            
        } else if (scCode.equals("003")) {  // 대표자
            sbWhereSQL.append("    AND REP_NM_KOR LIKE ?   \n");
            rDAO.setValue(i++, "%" + scText + "%");
        } 
        
        // 시도
        if (!KJFUtil.isEmpty(pm.getScSidoCode())) {
        	sbWhereSQL.append("    AND SIDO_CODE = ?   \n");
            rDAO.setValue(i++, pm.getScSidoCode());            
        }
        
        /* ************************** 페이징 관련 START **************************/
        StringBuffer sbCntSQL = new StringBuffer();
        sbCntSQL.append(" SELECT COUNT(*)  CNT          \n");
        sbCntSQL.append("   FROM PT_R_COMPANY_MASTER    \n");
        sbCntSQL.append(sbWhereSQL); 
        
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

        rEntity = rDAO.select(sbSQL.toString() + sbWhereSQL.toString(), nowPage, rowPerPage);

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
