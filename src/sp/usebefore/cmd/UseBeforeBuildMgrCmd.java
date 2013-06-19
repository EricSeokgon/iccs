package sp.usebefore.cmd;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.cfg.Config;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFLog;
import kjf.util.KJFUtil;
import kjf.util.LoginException;

import org.apache.struts.action.ActionForm;

import sp.uent.UserEnt;
import sp.usebefore.UseBeforeParam;

/***************************************************************************
 * <p>Title       : UseBeforeBuildMgrCmd Class</p>
 * <p>Description : 감리대상 건축물관리 상세 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE :감리대상 건축물관리 상세 처리를 한다. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class UseBeforeBuildMgrCmd implements KJFCommand {
    
	UserEnt user;
	
    public UseBeforeBuildMgrCmd() {   
        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {        
        
        user = (UserEnt)request.getSession().getAttribute("user");
        
        // 로그인 유무 체크
        if (KJFUtil.isEmpty(user)) {
            throw new LoginException(request, "회원용 페이지 로그인 체크", "../member/login.do");
        }  
        
        // 검색조건 설정및 체크
        UseBeforeParam pm = checkPm(request, form);
        request.setAttribute("pm", pm);
        
        // 감리대상 건축물관리 정보 
        loadData(request, pm);
        
        return request.getParameter("cmd");
    }
    
    
    /**
     * 감리대상 건축물관리 정보를 가져온다.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadData(HttpServletRequest request, UseBeforeParam pm) throws Exception {
        
        ReportEntity rEntity = null;        
        
        ReportDAO rDAO = new ReportDAO();
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT                       \n");
        sbSQL.append("        PT_U.SUV_NAME,             \n");   // 감리자 상호명
        sbSQL.append("        PT_U.SUV_NM,               \n");   // 감리자 감리원
        sbSQL.append("        PT_U.SUV_TELNUM,           \n");   // 감리자 연락처
        sbSQL.append("        PT_U.SUV_MOBILE,           \n");   // 감리자 휴대전화
        sbSQL.append("        PT_U.SUV_STANUM,           \n");   // 감리자 활동주체신고번호
        sbSQL.append("        PT_U.SUV_POSTNUM,          \n");   // 감리자  우편번호
        sbSQL.append("        PT_U.SUV_ADDR,             \n");   // 감리자 주소지
        sbSQL.append("        PT_U.SUV_DETAILADDR,       \n");   // 감리자 주소지 상세
                
        sbSQL.append("        PT_U.SIWORK_NAME,          \n");   // 시공자 상호명
        sbSQL.append("        PT_U.SIWORK_REP,           \n");   // 시공자 대표자명
        sbSQL.append("        PT_U.SIWORK_TELNUM,        \n");   // 시공자 연락처
        sbSQL.append("        PT_U.COI_WRT_NUM,          \n");   // 시공자 공사업등록번호
        sbSQL.append("        PT_U.SIWORK_POSTNUM,       \n");   // 시공자 우편번호
        sbSQL.append("        PT_U.SIWORK_ADDR,          \n");   // 시공자 주소 
        sbSQL.append("        PT_U.SIWORK_DETAILADDR,    \n");   // 시공자 상세주소        
             
        sbSQL.append("        PT_U.SPOTNM,               \n");   // 건축정보 현장명칭
        sbSQL.append("        PT_U.WORK_ITEM,            \n");   // 건축정보 공사종류
        sbSQL.append("        PT_U.USE,                  \n");   // 건축정보 용도
        sbSQL.append("        PT_U.STRU_AREA,            \n");   // 건축정보 건축면적
        sbSQL.append("        PT_U.AREA,                 \n");   // 건축정보 연면적
        sbSQL.append("        PT_U.SW_DT,                \n");   // 건축정보 착공일
        sbSQL.append("        PT_U.EW_DT,                \n");   // 건축정보 완공일
        sbSQL.append("        PT_U.SPOT_POSTNUM,         \n");   // 건축정보 우편번호        
        sbSQL.append("        PT_U.SPOT_ADDR,            \n");   // 건축정보 현장주소
        sbSQL.append("        PT_U.SPOT_DETAILADDR       \n");   // 건축정보 현장주소 상세
        
        
        sbSQL.append("   FROM PT_UB_SUVSPOT PT_U INNER JOIN PT_HOME_USEBEFORE PT_H    \n");      
        sbSQL.append("     ON PT_U.RECV_NUM = PT_H.RECV_NUM     \n");
        
        sbSQL.append("  WHERE PT_H.USER_ID = ?                  \n");
        sbSQL.append("    AND PT_U.RECV_NUM = ?                 \n");   // 시스템 접수번호
        
        rDAO.setValue(i++, user.getUSER_ID());  
        rDAO.setValue(i++, KJFUtil.print(pm.getScRecvNum()));    

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
    private UseBeforeParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {

        UseBeforeParam pm = (UseBeforeParam)form;

        // 파라미터 디버깅
        KJFLog.debug(pm.toString(request));
        
        // 페이징 라인
        if (KJFUtil.isEmpty(pm.getRowPerPage())) {            
            pm.setRowPerPage(Config.props.get("ROW_PER_PAGE"));
        }

        return pm;
    }

}
