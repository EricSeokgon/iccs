package sp.stwork.cmd;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.cfg.Config;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFLog;
import kjf.util.KJFUtil;
import kjf.util.LoginException;

import org.apache.struts.action.ActionForm;

import sp.stwork.StworkParam;
import sp.uent.UserEnt;

/***************************************************************************
 * <p>Title       : StWorkPlanInfoViewCmd Class</p>
 * <p>Description : 착공전 설계도 확인현황 상세 처리 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : 착공전 설계도 확인현황 상세 처리를 한다. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class StWorkPlanInfoViewCmd implements KJFCommand {
    
    UserEnt user;
    
    public StWorkPlanInfoViewCmd() {        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {        
        
        user = (UserEnt)request.getSession().getAttribute("user");
        
        // 로그인 유무 체크
        if (KJFUtil.isEmpty(user)) {
            throw new LoginException(request, "회원용 페이지 로그인 체크", "../member/login.do");
        }  
        
        // 검색조건 설정및 체크
        StworkParam pm = checkPm(request, form);
        request.setAttribute("pm", pm);
        
        // 착공전 설계도 확인현황 정보 
        loadData(request, pm);
        
        return request.getParameter("cmd");
    }
    
    
    /**
     * 착공전 설계도확인 정보를 가져온다.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadData(HttpServletRequest request, StworkParam pm) throws Exception {
        
        ReportEntity rEntity = null;        
        
        ReportDAO rDAO = new ReportDAO();
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT                           \n");
        sbSQL.append("        PT_U.ORPE_NM,             \n");   // 발주자 성명
        sbSQL.append("        PT_U.ORPE_TEL,            \n");   // 발주자 전화번호
        sbSQL.append("        PT_U.ORPE_POSTNUM,        \n");   // 발주자  우편번호
        sbSQL.append("        PT_U.ORPE_ADDR,           \n");   // 발주자 주소지
        sbSQL.append("        PT_U.ORPE_DETAILADDR,     \n");   // 발주자 주소지 상세
        
        sbSQL.append("        PT_U.PLANER_NM,           \n");   // 설계자 이름
        sbSQL.append("        PT_U.PLANER_NAME,         \n");   // 설계자 상호
        sbSQL.append("        PT_U.PLANER_TEL,          \n");   // 설계자 연락처
        sbSQL.append("        PT_U.PLANER_POSTNUM,      \n");   // 설계자 우편번호
        sbSQL.append("        PT_U.PLANER_ADDR,         \n");   // 설계자 주소 
        sbSQL.append("        PT_U.PLANER_DETAILADDR,   \n");   // 설계자 상세주소        
        
        sbSQL.append("        PT_U.RECV_DT,             \n");   // 건축정보 접수일자
        sbSQL.append("        PT_U.STRU_COMMIT_NUM,     \n");   // 건축정보 건축허가번호
        sbSQL.append("        PT_C2.CODE_NAME AS USE,   \n");   // 건축정보 용도
        sbSQL.append("        PT_U.WORK_ITEM,           \n");   // 건축정보 공사종류
        sbSQL.append("        PT_U.NUM_FL,              \n");   // 건축정보 층수
        sbSQL.append("        PT_U.AREA,                \n");   // 건축정보 연면적
        sbSQL.append("        PT_U.STRU_ADDR_POSTNUM,   \n");   // 건축정보 우편번호        
        sbSQL.append("        PT_U.STRU_ADDR_ADDR,      \n");   // 건축정보 주소
        sbSQL.append("        PT_U.STRU_ADDR_DETAILADDR, \n");   // 건축정보 주소 상세
        
        sbSQL.append("        PT_U.SUV_APPL,             \n");   // 건축정보 주소 상세
        
        sbSQL.append("        PT_C1.CODE_NAME AS SUV_APPL_NM    \n");   // 건축정보 주소 상세
        
        sbSQL.append("   FROM PT_UB_CONSTRUCTION PT_U INNER JOIN PT_HOME_CONSTRUCTION PT_H    \n");      
        sbSQL.append("     ON PT_U.RECV_NUM = PT_H.RECV_NUM     \n");
        
        sbSQL.append("   LEFT JOIN (                                        \n");   
        sbSQL.append("              SELECT P_CODE, CODE, CODE_NAME          \n");   
        sbSQL.append("                FROM PT_COM_CODE                      \n"); 
        sbSQL.append("               WHERE P_CODE = 'SUVAPPL'               \n");   
        sbSQL.append("              ) PT_C1 ON PT_U.SUV_APPL = PT_C1.CODE   \n"); 
        
        sbSQL.append("   LEFT JOIN (                                        \n");   
        sbSQL.append("              SELECT P_CODE, CODE, CODE_NAME          \n");   
        sbSQL.append("                FROM PT_COM_CODE                      \n"); 
        sbSQL.append("               WHERE P_CODE = 'BLDDIV'                \n");   
        sbSQL.append("              ) PT_C2 ON PT_U.USE = PT_C2.CODE        \n");
        
        sbSQL.append("  WHERE PT_H.USER_ID = ?                  \n");
        sbSQL.append("    AND PT_H.RECV_NUM = ?                 \n");   // 접수번호
        
        rDAO.setValue(i++, user.getUSER_ID());
        rDAO.setValue(i++, KJFUtil.print(pm.getRecv_num()));    

        rEntity = rDAO.select(sbSQL.toString());
        
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
    private StworkParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {

        StworkParam pm = (StworkParam)form;

        // 파라미터 디버깅
        KJFLog.debug(pm.toString(request));
        
        // 페이징 라인
        if (KJFUtil.isEmpty(pm.getRowPerPage())) {            
            pm.setRowPerPage(Config.props.get("ROW_PER_PAGE"));
        }

        return pm;
    }
}
