package sp.mem.cmd;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFLog;
import kjf.util.KJFSelect;
import kjf.util.KJFSelectOPS;
import kjf.util.KJFUtil;
import kjf.util.LoginException;

import org.apache.struts.action.ActionForm;

import sp.mem.MemParam;
import sp.uent.UserEnt;

/****************************************************************************
 * <p>Title       : MemInfoVCmd Class</p>
 * <p>Description : 사용자정보 처리 프로세스 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 *
 * NOTE : 사용자정보 처리를 한다.
 *
 * @version 1.0
 * @author  PKT
 ***************************************************************************/
public class MemInfoVCmd implements KJFCommand {
    
    UserEnt user;
    
    public MemInfoVCmd() {        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {    
               
        // 검색조건 설정및 체크
        MemParam pm = checkPm(request, form);
        
        user = (UserEnt)request.getSession().getAttribute("user");
        
        if (KJFUtil.isEmpty(user)) {
            throw new LoginException(request, "회원전용 페이지 로그인 체크", "../member/login.do");
        }
        
        // 검색조건 및 초기데이타 로드
        loadCondition(request, pm);
        
        // 회원 정보 데이타 로드
        loadDetail(request, pm);
        
        request.setAttribute("pm", pm);
        
        return request.getParameter("cmd"); 
    }   
    
    
    /**
     * 검색조건 및 초기데이타 로드
     * 
     * @param HttpServletRequest
     * @return
     */
    private void loadCondition(HttpServletRequest request, MemParam pm) throws Exception {
        
        
        
        //  시.도 구분
        StringBuffer sbSQL = new StringBuffer();
        
        sbSQL = new StringBuffer();
        sbSQL.append(" SELECT AREA_CODE, SIDO_NM    \n");
        sbSQL.append("   FROM PT_SIDO_CODE          \n");
        sbSQL.append("  WHERE SUBSTR(AREA_CODE, 1, 2) = SUBSTR(AREA_CODE, 3, 4)    \n");
        sbSQL.append("  ORDER BY SIDO_NM     \n");           
        
        Vector<KJFSelect> v_scSD_CD = KJFSelectOPS.getSel(sbSQL.toString(), "", "시.도 선택");
        request.setAttribute("v_scSD_CD", v_scSD_CD);
        
        sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT AREA_CODE, RTRIM(SIGUNGU_NM, '본청')   \n");
        sbSQL.append("   FROM PT_SIDO_CODE                           \n");
        sbSQL.append("  WHERE SUBSTR(AREA_CODE, 1, 2) = SUBSTR('" + user.getSIDO_CODE() + "', 1, 2) \n");
        sbSQL.append("    AND SUBSTR(AREA_CODE, 1, 2) != SUBSTR(AREA_CODE, 3, 4)                \n");
        sbSQL.append("  ORDER BY SIGUNGU_NM      \n");
    
        Vector<KJFSelect> v_scSGG_CD = KJFSelectOPS.getSel(sbSQL.toString(), "", "시.군.구 선택");
        request.setAttribute("v_scSGG_CD", v_scSGG_CD);     
    }
    
    
    /**
     * 회원정보 Load
     * 
     * @param request
     * @param pm
     * @throws Exception
     */
    private void loadDetail(HttpServletRequest request, MemParam pm) throws Exception {
                
        ReportDAO    rDAO    = new ReportDAO();
        ReportEntity rEntity = null;      
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT USER_ID,              \n");
        sbSQL.append("        USER_NAME,            \n");
        sbSQL.append("        USER_SSN1,            \n");
        sbSQL.append("        USER_SSN2,            \n");
        sbSQL.append("        USER_POST_NUM,        \n");
        sbSQL.append("        USER_ADDR,            \n");
        sbSQL.append("        USER_ADDR_DETAIL,     \n");
        sbSQL.append("        USER_TEL1,            \n");
        sbSQL.append("        USER_TEL2,            \n");
        sbSQL.append("        USER_TEL3,            \n");
        sbSQL.append("        USER_MOBILE1,         \n");
        sbSQL.append("        USER_MOBILE2,         \n");
        sbSQL.append("        USER_MOBILE3,         \n");
        sbSQL.append("        USER_EMAIL,           \n");
        sbSQL.append("        COM_REG_YN,           \n");
        sbSQL.append("        COM_NAME,             \n");
        sbSQL.append("        COM_NUM,              \n");
        sbSQL.append("        REG_NUM,              \n");
        sbSQL.append("        OFFICE_POST_NUM,      \n");
        sbSQL.append("        OFFICE_ADDR,          \n");
        sbSQL.append("        OFFICE_ADDR_DETAIL,   \n");
        sbSQL.append("        OFFICE_TEL,           \n");
        sbSQL.append("        OFFICE_FAX,           \n");
        sbSQL.append("        SIDO_CODE,            \n");
        sbSQL.append("        SIGUNGU_CODE,         \n");
        sbSQL.append("        CAPITAL               \n");
        sbSQL.append("   FROM PT_HOM_USER           \n");        
        sbSQL.append("  WHERE USER_ID = ?           \n");
        sbSQL.append("    AND USER_NAME = ?         \n");
        
        rDAO.setValue(i++, user.getUSER_ID());
        rDAO.setValue(i++, user.getUSER_NAME());
      
        rEntity = rDAO.select(sbSQL.toString());
                
        request.setAttribute("rEntity", rEntity);        
    }
    
    
    /**
     * 검색조건 초기값 설정및 체크
     * 
     * @param request
     * @param form
     * @return BbsParam
     * @throws Exception
     */
    private MemParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {

        MemParam pm = (MemParam) form;

        // 파라미터 디버깅
        KJFLog.debug(pm.toString(request));

        return pm;
    }
}
