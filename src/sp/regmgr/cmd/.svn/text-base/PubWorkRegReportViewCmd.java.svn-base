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
 * <p>Title       : PubWorkRegReportViewCmd Class</p>
 * <p>Description : 공사업 등록기준 신고 상세 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : 공사업 등록기준 신고 상세 처리를 한다. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class PubWorkRegReportViewCmd implements KJFCommand {
    
    UserEnt user;
    
    public PubWorkRegReportViewCmd() {        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {        
        
        user = (UserEnt)request.getSession().getAttribute("user");
        
        // 로그인 유무 체크
        if (KJFUtil.isEmpty(user)) {
            throw new LoginException(request, "회원용 페이지 로그인 체크", "../member/login.do");
        }  
        
        // 검색조건 설정및 체크
        RegMgrParam pm = checkPm(request, form);
        request.setAttribute("pm", pm);
        
        // 공사업 등록기준 신고 정보 
        loadData(request, pm);
        
        // 타시설공사업 정보
        loadSubCompanyData(request, pm);
        
        return request.getParameter("cmd");
    }
    
    
    /**
     * 공사업 등록 기준신고 정보를 가져온다.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadData(HttpServletRequest request, RegMgrParam pm) throws Exception {
        
        ReportEntity rEntity = null;        
        
        ReportDAO rDAO = new ReportDAO();   
        
        String chgbre_seq = KJFUtil.print(request.getParameter("chgbre_seq"));
        String recv_num   = KJFUtil.print(request.getParameter("recv_num")); 
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT                               \n");        
        sbSQL.append("        PT_H.NAME,                    \n");   // 업체명
        sbSQL.append("        PT_H.REP_NM_KOR,              \n");   // 대표자
        sbSQL.append("        PT_H.COI_WRT_NUM,             \n");   // 법인번호
        sbSQL.append("        PT_H.MANA_NUM,                \n");   // 사업자번호
        sbSQL.append("        PT_H.ADDR_TEL_NUM,            \n");   // 전화번호
        sbSQL.append("        PT_H.ADDR_FAX_NUM,            \n");   // 팩스번호
        sbSQL.append("        PT_H.ADDR_POST_NUM,           \n");   // 소재지주소 우편번호
        sbSQL.append("        PT_H.ADDR_ADDR,               \n");   // 소재지주소
        sbSQL.append("        PT_H.ADDR_DETAIL_ADDR,        \n");   // 소재지주소상세
        
        sbSQL.append("        PT_H.RECV_NUM,                \n");   // 접수번호 
        sbSQL.append("        PT_S.RECV_DT,                 \n");   // 접수일자
        sbSQL.append("        PT_S.PROC_LIM,                \n");   // 처리기한
        sbSQL.append("        PT_H.MOT_STE,                 \n");   // 진행상태 코드
        sbSQL.append("        PT_C1.CODE_NAME AS MOT_STE_NM \n");   // 진행상태 코드 명
        
        sbSQL.append("   FROM (PT_R_BASIC_CHANGE_HISTORY PT_H INNER JOIN PT_R_BASIC_STATEMENT PT_S      \n");
        sbSQL.append("     ON RTRIM(PT_H.RECV_NUM) = PT_S.RECV_NUM)                                     \n");      
        
        // 진행상태 코드 명
        sbSQL.append("   LEFT JOIN (                                        \n");  
        sbSQL.append("              SELECT P_CODE, CODE, CODE_NAME          \n"); 
        sbSQL.append("                FROM PT_COM_CODE                      \n");   
        sbSQL.append("               WHERE P_CODE = 'REGPROC'               \n");   
        sbSQL.append("              ) PT_C1 ON PT_H.MOT_STE = PT_C1.CODE    \n"); 
      
        sbSQL.append("  WHERE PT_H.CHGBRE_SEQ = ?       \n");   // 변경내역 일련번호 
        sbSQL.append("    AND PT_H.RECV_NUM = ?         \n");   // 접수번호
        
        rDAO.setValue(i++, chgbre_seq);
        rDAO.setValue(i++, recv_num);        
        
        rEntity = rDAO.select(sbSQL.toString());

        /****** 검색조건 초기값 ***********/
        request.setAttribute("rEntity", rEntity);        
    }
    
    
    /**
     * 타시설공사업 정보를 가져온다.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadSubCompanyData(HttpServletRequest request, RegMgrParam pm) throws Exception {
        
        ReportEntity rEntity = null;        
        
        ReportDAO rDAO = new ReportDAO();   
        
        String recv_num   = KJFUtil.print(request.getParameter("recv_num")); 
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT                                   \n");        
        sbSQL.append("        PT_C1.CODE_NAME AS SUB_CODE_NM,   \n");   // 타 시설공사업 코드명
        sbSQL.append("        PT_S.SUB_CODE,                    \n");   // 타 시설공사업 코드
        sbSQL.append("        PT_S.SUB_WRT_NUM                  \n");   // 타 시설공사업 등록번호  
        
        sbSQL.append("   FROM PT_R_BASIC_CHANGE_HISTORY PT_H INNER JOIN PT_R_SUBSIDIARY PT_S    \n");
        sbSQL.append("     ON RTRIM(PT_H.TMP_WRT_NUM) = PT_S.TMP_WRT_NUM                        \n"); 
        
        sbSQL.append("   LEFT JOIN (                                        \n");  
        sbSQL.append("              SELECT P_CODE, CODE, CODE_NAME          \n"); 
        sbSQL.append("                FROM PT_COM_CODE                      \n");   
        sbSQL.append("               WHERE P_CODE = 'SUBSIDIARY'            \n");   
        sbSQL.append("              ) PT_C1 ON PT_S.SUB_CODE = PT_C1.CODE   \n"); 
        
        sbSQL.append("  WHERE PT_H.RECV_NUM = ?             \n");   // 접수번호
        
        rDAO.setValue(i++, recv_num);
        
        rEntity = rDAO.select(sbSQL.toString());
  
        /****** 검색조건 초기값 ***********/
        request.setAttribute("cEntity", rEntity);        
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
