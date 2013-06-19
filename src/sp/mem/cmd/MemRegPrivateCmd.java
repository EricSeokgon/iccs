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
import kjf.util.MsgException;

import org.apache.struts.action.ActionForm;

import sp.mem.MemParam;

/****************************************************************************
 * <p>Title       : MemRegPrivateCmd Class</p>
 * <p>Description : 개인회원등록 화면 처리 프로세스 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 *
 * NOTE : 개인회원등록 화면 처리를 한다.
 *
 * @version 1.0
 * @author  PKT
 ***************************************************************************/
public class MemRegPrivateCmd implements KJFCommand {
    
    public MemRegPrivateCmd() {
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {    
               
        // 검색조건 설정및 체크
        MemParam pm = checkPm(request, form);
        
        // 회원가입 체크
        checkDuplicate(request, pm);
        
        // 검색조건 및 초기데이타 로드
        loadCondition(request, pm);
        
        request.setAttribute("pm", pm);
        
        return request.getParameter("cmd"); 
    }
    
    
    /**
     * 회원가입 중복 체크
     * 
     * @param request
     * @param form
     * @return
     * @throws Exception
     */
    private void checkDuplicate(HttpServletRequest request, MemParam form) throws Exception {
        
        ReportDAO rDAO = new ReportDAO();
        
        ReportEntity rEntity = null;
        
        int i = 1;
        
        String ssn1 = KJFUtil.print(request.getParameter("user_ssn1"));
        String ssn2 = KJFUtil.print(request.getParameter("user_ssn2"));
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT COUNT(*) AS CNT   \n");
        sbSQL.append("   FROM PT_HOM_USER               \n");
        sbSQL.append("  WHERE USER_SSN1 = ?             \n");
        sbSQL.append("    AND USER_SSN2 = ?             \n");
        sbSQL.append("    AND CAPITAL = ?               \n");
        
        rDAO.setValue(i++, ssn1); 
        rDAO.setValue(i++, ssn2); 
        rDAO.setValue(i++, "U"); 
        
        rEntity = rDAO.select(sbSQL.toString());
        
        if (KJFUtil.str2int(rEntity.getValue(0, "CNT")) > 0) {
            throw new MsgException(request, "회원가입된 정보", "FALSE", "../mem/MemAction.do?cmd=MemRegDupl");
        }
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
