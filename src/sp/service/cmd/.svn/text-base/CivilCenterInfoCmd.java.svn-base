package sp.service.cmd;

import java.util.ArrayList;
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
import kjf.util.MsgException;

import org.apache.struts.action.ActionForm;

import sp.service.AreaBaseBean;
import sp.service.AreaChargeBean;
import sp.service.AreaInfoBean;
import sp.service.ServiceParam;
import sp.uent.UserEnt;


/***************************************************************************
 * <p>Title       : CivilCenterInfoCmd Class</p>
 * <p>Description : 지역별 민원센터안내 담당자 세부정보 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : 지역별 민원센터안내 담당자 세부 정보 처리를 한다. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class CivilCenterInfoCmd implements KJFCommand {
    
	UserEnt user;
	
    public CivilCenterInfoCmd() {        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {        
               
        user = (UserEnt)request.getSession().getAttribute("user");
        
        // 로그인 유무 체크
        if (KJFUtil.isEmpty(user)) {
            throw new LoginException(request, "회원용 페이지 로그인 체크", "../member/login.do");
        } 
        
        // 검색조건 설정및 체크
        ServiceParam pm = checkPm(request, form);
        request.setAttribute("pm", pm);
        
        // 검색조건 및 초기데이타 로드
        loadCondition(request, pm);
        
        
        if ("".equals(user.getCAPITAL()) || "U".equals(user.getCAPITAL()) || "UE".equals(user.getCAPITAL())){
        	throw new MsgException(request, "해당 메뉴에 대한 조회 권한이 없습니다.","Y", "../index/IndexAction.do?cmd=Index");
        } else {
        	// 담당자 세부정보 
        	loadData(request);
        }
        return request.getParameter("cmd");
    }
    
    /**
     * 검색조건 및 초기데이타 로드
     * 
     * @param HttpServletRequest
     * @return
     */
    private void loadCondition(HttpServletRequest request, ServiceParam pm) throws Exception {

    }
    
    /**
     * 담당자 세부 정보 로드
     * 
     * @param request
     * @param pm
     * @return
     * @throws Exception
     */
    public void loadData(HttpServletRequest request) throws Exception {
        
        ReportDAO rDAO       = new ReportDAO();
        ReportEntity rEntity = null;
        
        String Offi_Id = KJFUtil.print(request.getParameter("str"),"");
        
        int i = 1;
 
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT OFFI_ID,            \n");
        sbSQL.append("        NM,              \n");
        sbSQL.append("        (SELECT SIDO_NM FROM PT_SIDO_CODE WHERE AREA_CODE = SIDO_CODE) AS SIDO_NM,     \n");
        sbSQL.append("        (SELECT SIGUNGU_NM FROM PT_SIDO_CODE WHERE AREA_CODE = SIGUNGU_CODE) SIGUN_NM, \n");
        sbSQL.append("        TEL,                   \n");
        sbSQL.append("        PART,                   \n");
        sbSQL.append("        POS,                   \n");
        sbSQL.append("        (SELECT AUTH_GROUP_NAME FROM PT_AUTH_GROUP WHERE AUTH_GROUP_CODE = GROUP_CODE AND USE_YN = 'Y') AS GROUP_NM,      \n");
        sbSQL.append("        (SELECT AUTH_GROUP_NAME FROM PT_AUTH_GROUP WHERE AUTH_GROUP_CODE = HOME_GROUP AND USE_YN = 'Y') AS HOME_GROUP_NM, \n");
        sbSQL.append("        MOBILE,                   \n");
        sbSQL.append("        E_MAIL,                   \n");
        sbSQL.append("        OFFSEAL,                  \n");
        sbSQL.append("        TEL1,TEL2,TEL3            \n");
        sbSQL.append("        MOBILE1,MOBILE2,MOBILE3,   \n");
        sbSQL.append("        PHOTO,FAX2,FAX2,FAX3,FAX,SIDO_CODE  \n");
        sbSQL.append("   FROM PT_MI_USER                \n");
        sbSQL.append("  WHERE OFFI_ID = ?               \n");
        
        rDAO.setValue(i++, Offi_Id);
        
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
    private ServiceParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {

        ServiceParam pm = (ServiceParam)form;

        // 파라미터 디버깅
        KJFLog.debug(pm.toString(request));
        
        // 페이징 라인
        if (KJFUtil.isEmpty(pm.getRowPerPage())) {
            pm.setRowPerPage(Config.props.get("ROW_PER_PAGE"));
        }

        return pm;
    }
}
