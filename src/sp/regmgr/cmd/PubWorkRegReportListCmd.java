package sp.regmgr.cmd;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.cfg.Config;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFLog;
import kjf.util.KJFUtil;
import kjf.util.LoginException;

import org.apache.struts.action.ActionForm;

import sp.regmgr.RegMgrParam;
import sp.uent.UserEnt;

/***************************************************************************
 * <p>Title       : PubWorkRegReportListCmd Class</p>
 * <p>Description : 공사업 등록기준 신고 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : 공사업 등록기준 신고 리스트 처리를 한다. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class PubWorkRegReportListCmd implements KJFCommand {
    
	UserEnt user;
	
    public PubWorkRegReportListCmd() {        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {        
        
        user = (UserEnt)request.getSession().getAttribute("user");
        
        // 로그인 유무 체크
        if (KJFUtil.isEmpty(user)) {
            throw new LoginException(request, "회원용 페이지 로그인 체크", "../member/login.do");
        }  
        
        // 검색조건 설정및 체크
        RegMgrParam pm = checkPm(request, form);
        
        // 공사업 등록기준 신고 정보 
        loadData(request, pm);
        
        return request.getParameter("cmd");
    }
    
    
    /**
     * 공사업 등록기준 정보를 가져온다.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadData(HttpServletRequest request, RegMgrParam pm) throws Exception {
        
        ReportEntity rEntity = null;        
        
        ReportDAO rDAO = new ReportDAO();      
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT                                   \n");
        sbSQL.append("        PT_M.COI_WRT_NUM,                 \n");   // 공사업등록번호
        sbSQL.append("        PT_H.CHGBRE_SEQ,                  \n");   // 변경일련번호
        sbSQL.append("        PT_M.NAME,                        \n");   // 회사명
        sbSQL.append("        PT_S.RECV_NUM,                    \n");   // 접수번호
        sbSQL.append("        PT_S.RECV_DT,                     \n");   // 접수일자
        sbSQL.append("        PT_S.PROC_LIM,                    \n");   // 처리기한
        sbSQL.append("        PT_MI.NM,                         \n");   // 담당자     
        sbSQL.append("        PT_H.MOT_STE,                     \n");   // 진행상태 코드
        sbSQL.append("        PT_C1.CODE_NAME AS MOT_STE_NM     \n");   // 진행상태 코드 명
        
        sbSQL.append("   FROM (PT_R_COMPANY_MASTER PT_M INNER JOIN PT_R_BASIC_CHANGE_HISTORY PT_H     \n");
        sbSQL.append("     ON RTRIM(PT_M.TMP_WRT_NUM) = PT_H.TMP_WRT_NUM)            \n");              
        
        sbSQL.append("   INNER JOIN (                                               \n");   
        sbSQL.append("               SELECT RECV_NUM,       \n");   // 접수번호
        sbSQL.append("                      RECV_DT,        \n");   // 접수일자
        sbSQL.append("                      PROC_LIM,       \n");   // 처리기한
        sbSQL.append("                      WRT_ID          \n");   // 담당자        
        sbSQL.append("                 FROM PT_R_BASIC_STATEMENT                \n");    
        sbSQL.append("              ) PT_S ON PT_H.RECV_NUM = PT_S.RECV_NUM     \n");
        
        sbSQL.append("   LEFT JOIN (                                            \n");   
        sbSQL.append("              SELECT OFFI_ID, NM                          \n");   
        sbSQL.append("                FROM PT_MI_USER                           \n");  
        sbSQL.append("              ) PT_MI ON PT_S.WRT_ID = PT_MI.OFFI_ID      \n"); 
        
        // 처리상태 코드 명
        sbSQL.append("   LEFT JOIN (                                        \n");  
        sbSQL.append("              SELECT P_CODE, CODE, CODE_NAME          \n"); 
        sbSQL.append("                FROM PT_COM_CODE                      \n");   
        sbSQL.append("               WHERE P_CODE = 'REGPROC'               \n");   
        sbSQL.append("              ) PT_C1 ON PT_H.MOT_STE = PT_C1.CODE    \n"); 
               
        sbSQL.append("  WHERE PT_M.COI_WRT_NUM = ?  \n");   // 공사업등록번호  
        sbSQL.append("    AND PT_M.MANA_NUM = ?     \n");   // 사업자 번호
        
        rDAO.setValue(i++, user.getREG_NUM());     
        rDAO.setValue(i++, user.getCOM_NUM());    
        
        /* ************************** 페이징 관련 START **************************/
        StringBuffer sbCntSQL = new StringBuffer();
        sbCntSQL.append(" SELECT COUNT(*)  CNT  \n");
        sbCntSQL.append("   FROM (PT_R_COMPANY_MASTER PT_M INNER JOIN PT_R_BASIC_CHANGE_HISTORY PT_H    \n");
        sbCntSQL.append("     ON RTRIM(PT_M.TMP_WRT_NUM) = PT_H.TMP_WRT_NUM)       \n");      
        sbCntSQL.append("   INNER JOIN (                                           \n");   
        sbCntSQL.append("               SELECT RECV_NUM                            \n");   // 접수번호
        sbCntSQL.append("                 FROM PT_R_BASIC_STATEMENT                \n");        
        sbCntSQL.append("              ) PT_S ON PT_H.RECV_NUM = PT_S.RECV_NUM     \n"); 
        sbCntSQL.append("  WHERE PT_M.COI_WRT_NUM = ?                              \n");   // 공사업등록번호  
        sbCntSQL.append("    AND PT_M.MANA_NUM = ?                                 \n");   // 사업자 번호         
        
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

        rEntity = rDAO.select(sbSQL.toString(), nowPage, rowPerPage);

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
    private RegMgrParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {

        RegMgrParam pm = (RegMgrParam)form;

        // 파라미터 디버깅
        KJFLog.debug(pm.toString(request));
        
        // 페이징 라인
        if (KJFUtil.isEmpty(pm.getRowPerPage())) {            
            pm.setRowPerPage(Config.props.get("ROW_PER_PAGE"));
        }

        return pm;
    }
}
