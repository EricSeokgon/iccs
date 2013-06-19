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
 * <p>Title       : PubWorkAffiliationCmd Class</p>
 * <p>Description : 공사업 합병 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : 공사업 합병 정보 처리를 한다. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class PubWorkAffiliationCmd implements KJFCommand {
    
	UserEnt user;
	
    public PubWorkAffiliationCmd() {        
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
        
        // 공사업 등록정보 
        loadData(request, pm);
        
        return request.getParameter("cmd");
    }
    
    
    /**
     * 공사업 합병 정보를 가져온다.
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
        sbSQL.append(" SELECT                               					\n");        
        sbSQL.append("        PT_U.RECV_NUM,                					\n");   // 접수현황 접수번호
        sbSQL.append("        PT_U.RECV_DT,                 					\n");   // 접수현황 접수일자
        sbSQL.append("        PT_H.MOT_STE,                                     \n");   // 접수현황 진행상태 코드
        sbSQL.append("        PT_C1.CODE_NAME AS MOT_STE_NM,                    \n");   // 접수현황 진행상태 코드명
        sbSQL.append("        PT_U.PROC_LIM,      								\n");   // 접수현황 처리기한
        sbSQL.append("        PT_M1.NAME AS ASC_NAME,      						\n");   // 접수현황 합병회사명
        sbSQL.append("        PT_M2.NAME AS HANDED_NAME,  						\n");   // 접수현황 피합병회사명
        sbSQL.append("        PT_U.ESTA_NAME,             						\n");   // 접수현황 존속설립회사명
        
        sbSQL.append("        PT_M1.COI_WRT_NUM AS ASC_COI_WRT_NUM,          	\n");   // 합병업체정보 등록번호
        sbSQL.append("        PT_M1.REP_NM_KOR AS ASC_REP_NM_KOR,     			\n");   // 합병업체정보 대표자
        sbSQL.append("        PT_M1.ADDR_TEL_NUM AS ASC_ADDR_TEL_NUM,         	\n");   // 합병업체정보 전화번호
        sbSQL.append("        PT_M1.MANA_NUM AS ASC_MANA_NUM,  					\n");   // 합병업체정보 사업자번호
        sbSQL.append("        PT_M1.ADDR_POST_NUM AS ASC_ADDR_POST_NUM,   		\n");   // 합병업체정보 우편번호
        sbSQL.append("        PT_M1.ADDR_ADDR AS ASC_ADDR_ADDR, 				\n");   // 합병업체정보 현주소
        sbSQL.append("        PT_M1.ADDR_DETAIL_ADDR AS ASC_ADDR_DETAIL_ADDR,	\n");   // 합병업체정보 현주소 상세
        
        sbSQL.append("        PT_M2.COI_WRT_NUM AS HANDED_COI_WRT_NUM,          	\n");   // 피합병업체정보 등록번호
        sbSQL.append("        PT_M2.REP_NM_KOR AS HANDED_REP_NM_KOR, 				\n");   // 피합병업체정보 대표자
        sbSQL.append("        PT_M2.ADDR_TEL_NUM AS HANDED_ADDR_TEL_NUM,         	\n");   // 피합병업체정보 전화번호
        sbSQL.append("        PT_M2.MANA_NUM AS HANDED_MANA_NUM,					\n");   // 피합병업체정보 사업자번호
        sbSQL.append("        PT_M2.ADDR_POST_NUM AS HANDED_ADDR_POST_NUM,  		\n");   // 피합병업체정보 우편번호
        sbSQL.append("        PT_M2.ADDR_ADDR AS HANDED_ADDR_ADDR,  				\n");   // 피합병업체정보 현주소
        sbSQL.append("        PT_M2.ADDR_DETAIL_ADDR AS HANDED_ADDR_DETAIL_ADDR, 	\n");   // 피합병업체정보 현주소 상세
        
        sbSQL.append("        PT_U.ESTA_WRT_NUM,           						\n");   // 합병 후 존속 또는 설립된 법인 등록번호
        sbSQL.append("        PT_U.ESTA_REP_NM_KOR, 							\n");   // 합병 후 존속 또는 설립된 법인 대표자
        sbSQL.append("        PT_U.ESTA_ADDR_TEL_NUM,           				\n");   // 합병 후 존속 또는 설립된 법인 전화번호
        sbSQL.append("        PT_U.ESTA_MANA_NUM,								\n");   // 합병 후 존속 또는 설립된 법인 사업자번호
        sbSQL.append("        PT_U.ESTA_ADDR_POST_NUM,  				        \n");   // 합병 후 존속 또는 설립된 법인 우편번호
        sbSQL.append("        PT_U.ESTA_ADDR_ADDR,  							\n");   // 합병 후 존속 또는 설립된 법인 현주소
        sbSQL.append("        PT_U.ESTA_ADDR_DETAIL_ADDR 						\n");   // 합병 후 존속 또는 설립된 법인 현주소 상세
        
        sbSQL.append("   FROM ( PT_R_UNION_STATEMENT PT_U INNER JOIN PT_R_BASIC_CHANGE_HISTORY PT_H     \n");
        sbSQL.append("   	    ON PT_U.RECV_NUM = PT_H.RECV_NUM )    				\n");
        
        sbSQL.append("   INNER JOIN PT_R_COMPANY_MASTER PT_M1               		\n");	// 합병업체정보
        sbSQL.append("           ON PT_M1.TMP_WRT_NUM = PT_U.ASC_TMP_WRT_NUM    	\n");
        
        sbSQL.append("   INNER JOIN PT_R_COMPANY_MASTER PT_M2               		\n");	// 피합병업체정보
        sbSQL.append("           ON PT_M2.TMP_WRT_NUM = PT_U.HANDED_TMP_WRT_NUM		\n");      
        
        // 접수현황 진행상태 코드 명
        sbSQL.append("   LEFT JOIN (                                        \n");  
        sbSQL.append("              SELECT P_CODE, CODE, CODE_NAME          \n"); 
        sbSQL.append("                FROM PT_COM_CODE                      \n");   
        sbSQL.append("               WHERE P_CODE = 'REGPROC'               \n");   
        sbSQL.append("              ) PT_C1 ON PT_H.MOT_STE = PT_C1.CODE    \n");
      
        sbSQL.append("  WHERE (PT_M1.COI_WRT_NUM = ? AND PT_M1.MANA_NUM = ?)     	\n");   // 합병업체정보(공사업등록번호, 사업자번호)
        sbSQL.append("     OR (PT_M2.COI_WRT_NUM = ? AND PT_M2.MANA_NUM = ?)		\n");   // 피합병업체정보(공사업등록번호, 사업자번호)
        sbSQL.append("     OR (PT_U.ESTA_WRT_NUM = ? AND PT_U.ESTA_MANA_NUM = ?)	\n");   // 합병 후 존속 또는 설립된 법인(공사업등록번호, 사업자번호)
        
        rDAO.setValue(i++, user.getREG_NUM());
        rDAO.setValue(i++, user.getCOM_NUM());   
        rDAO.setValue(i++, user.getREG_NUM());
        rDAO.setValue(i++, user.getCOM_NUM());
        rDAO.setValue(i++, user.getREG_NUM());
        rDAO.setValue(i++, user.getCOM_NUM());
        
        rEntity = rDAO.select(sbSQL.toString());

        /****** 검색조건 초기값 ***********/
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
