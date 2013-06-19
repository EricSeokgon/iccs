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
 * <p>Title       : PubWorkTransferCmd Class</p>
 * <p>Description : 공사업 양도양수 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : 공사업 양도양수 처리를 한다. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class PubWorkTransferCmd implements KJFCommand {
    
	UserEnt user;
	
    public PubWorkTransferCmd() {        
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
        
        // 공사업 양도양수 정보 
        loadData(request, pm);
        
        return request.getParameter("cmd");
    }
    
    
    /**
     * 공사업 양도양수 정보를 가져온다.
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
        sbSQL.append(" SELECT                                 \n");        
        sbSQL.append("        PT_A.RECV_NUM,                  \n");   // 접수현황 접수번호
        sbSQL.append("        PT_A.RECV_DT,                   \n");   // 접수현황 접수일자
        sbSQL.append("        PT_M.NAME,      				  \n");   // 접수현황 양도업체
        sbSQL.append("        PT_A.TRAN_NAME,              	  \n");   // 접수현황 양수업체
        sbSQL.append("        PT_H.MOT_STE,            		  \n");   // 접수현황 진행상태 코드
        sbSQL.append("        PT_C1.CODE_NAME AS MOT_STE_NM,  \n");   // 접수현황 진행상태 코드명
        sbSQL.append("        PT_A.INS_DT,              	  \n");   // 접수현황 등록일자
        
        sbSQL.append("        PT_M.COI_WRT_NUM,          	  \n");   // 양도업체정보 등록번호
        sbSQL.append("        PT_M.REP_NM_KOR,     			  \n");   // 양도업체정보 대표자
        sbSQL.append("        PT_M.ADDR_TEL_NUM,         	  \n");   // 양도업체정보 전화번호
        sbSQL.append("        PT_M.MANA_NUM,   				  \n");   // 양도업체정보 사업자번호
        sbSQL.append("        PT_M.ADDR_POST_NUM,   		  \n");   // 양도업체정보 우편번호
        sbSQL.append("        PT_M.ADDR_ADDR,   			  \n");   // 양도업체정보 현주소
        sbSQL.append("        PT_M.ADDR_DETAIL_ADDR,   		  \n");   // 양도업체정보 현주소 상세
        
        sbSQL.append("        PT_A.TRAN_WRT_NUM,           	  \n");   // 양수업체정보 등록번호
        sbSQL.append("        PT_A.TRAN_REP, 				  \n");   // 양수업체정보 대표자
        sbSQL.append("        PT_A.TRAN_TELNUM,            	  \n");   // 양수업체정보 전화번호
        sbSQL.append("        PT_A.TRAN_COMNUM,				  \n");   // 양수업체정보 사업자번호
        sbSQL.append("        PT_A.TRAN_POST_NUM,   		  \n");   // 양수업체정보 우편번호
        sbSQL.append("        PT_A.TRAN_ADDR,   			  \n");   // 양수업체정보 현주소
        sbSQL.append("        PT_A.TRAN_DETAIL_ADDR   		  \n");   // 양수업체정보 현주소 상세
        
        sbSQL.append("   FROM ( PT_R_ASSI_TRANS_STATEMENT PT_A INNER JOIN PT_R_BASIC_CHANGE_HISTORY PT_H     \n");
        sbSQL.append("   	    ON PT_A.RECV_NUM = PT_H.RECV_NUM )    			\n");
        
        sbSQL.append("   INNER JOIN PT_R_COMPANY_MASTER PT_M               	 	\n");
        sbSQL.append("           ON PT_M.TMP_WRT_NUM = PT_A.ASSI_TMP_WRT_NUM    \n");
        
        // 접수현황 진행상태 코드 명
        sbSQL.append("   LEFT JOIN (                                        \n");  
        sbSQL.append("              SELECT P_CODE, CODE, CODE_NAME          \n"); 
        sbSQL.append("                FROM PT_COM_CODE                      \n");   
        sbSQL.append("               WHERE P_CODE = 'REGPROC'               \n");   
        sbSQL.append("              ) PT_C1 ON PT_H.MOT_STE = PT_C1.CODE    \n"); 
      
      
        sbSQL.append("  WHERE (PT_M.COI_WRT_NUM = ? AND PT_M.MANA_NUM = ?)     	\n");   // 양도업체(공사업등록번호, 사업자번호)
        sbSQL.append("     OR (PT_A.TRAN_WRT_NUM = ? AND PT_A.TRAN_COMNUM = ?)	\n");   // 양수업체(공사업등록번호, 사업자번호)
        
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
