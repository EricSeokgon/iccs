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
 * <p>Title       : UseBeforeChkContentCmd Class</p>
 * <p>Description : 사용전검사 접수내용 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : 사용전검사 접수내용 처리를 한다. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class UseBeforeChkContentCmd implements KJFCommand {
    
	UserEnt user;
	
    public UseBeforeChkContentCmd() {   
        
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
        
        // 사용전검사 접수내용 정보 
        loadData(request, pm);
        
        return request.getParameter("cmd");
    }
    
    
    /**
     * 사용전검사 접수현황 정보를 가져온다.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadData(HttpServletRequest request, UseBeforeParam pm) throws Exception {
        
    	ReportEntity rEntity = null;        
        
        ReportDAO rDAO = new ReportDAO();
        
        String recv_num = KJFUtil.print(pm.getScRecvNum());
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT              			    \n");
        sbSQL.append("        PT_U.RECV_NUM,    		\n");   // 접수번호
        sbSQL.append("        PT_U.CIV_RECV_NUM,        \n");   // 접수번호
        sbSQL.append("        PT_U.APPLPER_NM,   		\n");   // 신청인 건축주
        sbSQL.append("        PT_U.APPLPER_REP,     	\n");   // 신청인 대표자
        sbSQL.append("        PT_U.APPLPER_TELNUM,  	\n");   // 신청인 연락처
        sbSQL.append("        PT_U.APPLPER_POSTNUM,  	\n");   // 신청인 우편번호
        sbSQL.append("        PT_U.APPLPER_ADDR,  		\n");   // 신청인 주소        
        sbSQL.append("        PT_U.APPLPER_DETAILADDR,	\n");   // 신청인 상세주소
        
        sbSQL.append("        PT_U.OPE_NAME,   			\n");   // 시공자 상호명
        sbSQL.append("        PT_U.OPE_REP,     		\n");   // 시공자 대표자명
        sbSQL.append("        PT_U.OPE_TELNUM,  		\n");   // 시공자 연락처
        sbSQL.append("        PT_U.COI_WRT_NUM,     	\n");   // 공사업등록번호
        sbSQL.append("        PT_U.OPE_POSTNUM,    		\n");   // 시공자 우편번호
        sbSQL.append("        PT_U.OPE_ADDR,    		\n");   // 시공자 주소 
        sbSQL.append("        PT_U.OPE_DETAILADDR,  	\n");   // 시공자 상세주소
        sbSQL.append("        PT_U.OFFI_NM,     		\n");   // 담당자명
        sbSQL.append("        PT_U.OFFI_TELNUM,     	\n");   // 담당자 연락처        
             
        sbSQL.append("        PT_U.WORK_ITEM,   		\n");   // 기타사항 공사종류
        sbSQL.append("        PT_U.SW_DT,     			\n");   // 기타사항 착공일
        sbSQL.append("        PT_U.INSP_WISH_YMD,  		\n");   // 기타사항 검사희망일
        sbSQL.append("        PT_U.EW_DT,     			\n");   // 기타사항 완공일
        sbSQL.append("        PT_U.INSP_FEE,    		\n");   // 기타사항 검사수수료
        sbSQL.append("        PT_U.RECV_DT,     		\n");   // 기타사항 접수일        
                
        sbSQL.append("        PT_U.INSP_SPOT_NM,         \n");   // 시공현장정보 검사현장명칭
        sbSQL.append("        PT_U.INSP_SPOT_POSTNUM,    \n");   // 시공현장정보 우편번호
        sbSQL.append("        PT_U.INSP_SPOT_ADDR,       \n");   // 시공현장정보 주소
        sbSQL.append("        PT_U.INSP_SPOT_DETAILADDR, \n");   // 시공현장정보 주소 상세                
        
        sbSQL.append("        PT_C1.CODE_NAME AS USE,    \n");   // 건축물정보 건축물용도
        sbSQL.append("        PT_U.AREA,                 \n");   // 건축물정보 면적
        sbSQL.append("        PT_U.INSP_APPL_WORK,       \n");   // 건축물정보 검사대상공사
        sbSQL.append("        PT_U.NUM_FL,               \n");   // 건축물정보 층수
        
        sbSQL.append("        PT_U.INSP_APPL_DT,         \n");   // 검사상세정보 검사신청일
        sbSQL.append("        PT_U.INSP_DT,			     \n");   // 검사상세정보 검사예정일
        sbSQL.append("        PT_U.SIGUNGU_CODE			 \n");   // 검사상세정보 시군구코드
        sbSQL.append("   FROM PT_UB_USEBEFORE PT_U                      \n");  
        
//        sbSQL.append("   FROM PT_UB_USEBEFORE PT_U INNER JOIN PT_HOME_USEBEFORE PT_H    \n");      
//        sbSQL.append("     ON PT_U.RECV_NUM = PT_H.RECV_NUM                             \n");
        
        sbSQL.append("   LEFT JOIN (                                    \n");  
        sbSQL.append("              SELECT P_CODE, CODE, CODE_NAME      \n"); 
        sbSQL.append("                FROM PT_COM_CODE                  \n");   
        sbSQL.append("               WHERE P_CODE = 'BLDDIV'            \n");   
        sbSQL.append("              ) PT_C1 ON PT_U.USE = PT_C1.CODE    \n");
        sbSQL.append("	WHERE PT_U.APPLPER_NM = ?          				\n");         
        sbSQL.append("    	  AND PT_U.RECV_NUM = ?   				\n");
            	
//        sbSQL.append("  WHERE PT_H.USER_ID = ?                  \n");
//        sbSQL.append("    AND PT_H.RECV_NUM = ?                 \n");   // 시스템 접수번호        
        sbSQL.append("  ORDER BY PT_U.CIV_RECV_NUM              \n");   // 사용자 접수번호
        
        rDAO.setValue(i++, user.getUSER_NAME());  
        rDAO.setValue(i++, recv_num);  

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
