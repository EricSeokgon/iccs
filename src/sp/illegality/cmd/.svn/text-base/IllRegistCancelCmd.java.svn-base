package sp.illegality.cmd;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.cfg.Config;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFLog;
import kjf.util.KJFUtil;
import kjf.util.LoginException;

import org.apache.struts.action.ActionForm;

import sp.illegality.IllegalityParam;
import sp.uent.UserEnt;

/***************************************************************************
 * <p>Title       : IllRegistCancelCmd Class</p>
 * <p>Description : 등록취소 상세보기 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : 등록취소 상세보기 처리를 한다. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class IllRegistCancelCmd implements KJFCommand {
    
    UserEnt user;
    
    public IllRegistCancelCmd() {        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {        
        
        user = (UserEnt)request.getSession().getAttribute("user");
        
        // 로그인 유무 체크
        if (KJFUtil.isEmpty(user)) {
            throw new LoginException(request, "회원용 페이지 로그인 체크", "../member/login.do");
        }  
        
        // 검색조건 설정및 체크
        IllegalityParam pm = checkPm(request, form);
        
        // 실태조사 청문통지서 결과확인 정보 
        loadReportData(request, pm);
        
        // 실태조사 청문조사 결과확인 정보 
        loadDictionData(request, pm);
        
        request.setAttribute("pm", pm);
        
        return request.getParameter("cmd");
    }
    
    
    /**
     * 등록취소 청문통지서 정보를 가져온다.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadReportData(HttpServletRequest request, IllegalityParam pm) throws Exception {
        
        ReportEntity rEntity = null;        
        
        ReportDAO rDAO = new ReportDAO();      
      
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT                                   \n");   
        sbSQL.append("        PT_R.AUDI_SUBJ,                   \n");   // 청문제목
        sbSQL.append("        PT_R.AUDI_PER_NM,                 \n");   // 청문당사자
        sbSQL.append("        PT_R.AUDI_PER_ADDR,               \n");   // 당사자주소
        sbSQL.append("        PT_R.ADMI_DISPO_CAUSE,            \n");   // 처분사유
        sbSQL.append("        PT_R.LEG_BAS,                     \n");   // 법적근거        
        sbSQL.append("        PT_R.AUDI_EXEC_ORG,               \n");   // 실시기관
        sbSQL.append("        PT_R.AUDI_EXEC_PART,              \n");   // 실시부서
        sbSQL.append("        PT_R.AUDI_EXEC_ADDR,              \n");   // 청문장소
        sbSQL.append("        PT_R.AUDI_EXEC_DETAILADDR,        \n");   // 청문장소
        sbSQL.append("        PT_R.AUDI_EXEC_DT,                \n");   // 청문일자
        sbSQL.append("        PT_R.AUDI_EXEC_TIME,              \n");   // 청문일시
        sbSQL.append("        PT_R.AUDI_SUPINT_NM,              \n");   // 주재자성명
        sbSQL.append("        PT_R.AUDI_SUPINT_POS,             \n");   // 주재자직위
        sbSQL.append("        PT_R.AUDI_SUPINT_PART             \n");   // 주재자소속               
        
        sbSQL.append("   FROM (PT_R_COMPANY_MASTER PT_C INNER JOIN PT_M_MASTER PT_M \n");
        sbSQL.append("          ON RTRIM(PT_C.TMP_WRT_NUM) = PT_M.TMP_WRT_NUM)      \n");
        
        sbSQL.append("   INNER JOIN (                                               \n");   
        sbSQL.append("               SELECT *                                       \n");   
        sbSQL.append("                 FROM PT_M_AUDI_REPORT                        \n");  
        sbSQL.append("              ) PT_R  ON PT_M.TMP_WRT_NUM = PT_R.TMP_WRT_NUM  \n");
        sbSQL.append("                     AND PT_M.WRT_NUM     = PT_R.WRT_NUM      \n");
        
        sbSQL.append("  WHERE PT_C.COI_WRT_NUM = ?  \n");   // 공사업등록번호  
        sbSQL.append("    AND PT_C.MANA_NUM = ?     \n");   // 사업자 번호
          
        rDAO.setValue(i++, user.getREG_NUM());     
        rDAO.setValue(i++, user.getCOM_NUM());   
                
        rEntity = rDAO.select(sbSQL.toString());

        /****** 검색조건 초기값 ***********/        
        request.setAttribute("rEntity", rEntity);
    }
    
    
    /**
     * 등록취소 청문조서 정보를 가져온다.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadDictionData(HttpServletRequest request, IllegalityParam pm) throws Exception {
        
        ReportEntity rEntity = null;        
        
        ReportDAO rDAO = new ReportDAO();      
        
        String tmp_wrt_num = KJFUtil.print(request.getParameter("tmp_wrt_num"));
        String wrt_num     = KJFUtil.print(request.getParameter("wrt_num"));
        
        int i = 1;
             
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT                                   \n");   
        sbSQL.append("        PT_D.AUDI_SUPINT_NM,              \n");   // 주재자 성명 
        sbSQL.append("        PT_D.AUDI_SUPINT_POS,             \n");   // 주재자 직위
        sbSQL.append("        PT_D.AUDI_SUPINT_PART,            \n");   // 주재자 소속
        sbSQL.append("        PT_D.AUDI_ATTEND_PERSON_NM,       \n");   // 참석자 성명 
        sbSQL.append("        PT_D.AUDI_ATTEND_PERSON_POS,      \n");   // 참석자 직위
        sbSQL.append("        PT_D.AUDI_ATTEND_PERSON_PART,     \n");   // 참석자 소속
        sbSQL.append("        PT_D.AUDI_PER_ATTE_YN,            \n");   // 출석여부
        sbSQL.append("        PT_D.AUDI_PER_NATTE_CAUSE,        \n");   // 불출석사유        
        sbSQL.append("        PT_D.AUDI_OPN_YN,                 \n");   // 공개여부
        sbSQL.append("        PT_D.AUDI_NOPN_CAUSE,             \n");   // 비공개사유
        sbSQL.append("        PT_D.AUDI_EXEC_DT,                \n");   // 청문일자
        sbSQL.append("        PT_D.AUDI_EXEC_TIME,              \n");   // 청문일시
        sbSQL.append("        PT_D.AUDI_EXEC_LOC,               \n");   // 청문장소
        sbSQL.append("        PT_D.PER_STAT_CONT,               \n");   // 당사자 진술내용
        sbSQL.append("        PT_D.PER_PRES_EVID,               \n");   // 당사자  제출증거
        sbSQL.append("        PT_D.EVID_INV_POIN,               \n");   // 증거조사 진술내용
        sbSQL.append("        PT_D.EVID_INV_EVID                \n");   // 증거조사 제출증거
       
        sbSQL.append("   FROM (PT_R_COMPANY_MASTER PT_C INNER JOIN PT_M_MASTER PT_M \n");
        sbSQL.append("          ON RTRIM(PT_C.TMP_WRT_NUM) = PT_M.TMP_WRT_NUM)      \n");
        
        sbSQL.append("   INNER JOIN (                                               \n");   
        sbSQL.append("               SELECT *                                       \n");   
        sbSQL.append("                 FROM PT_M_AUDI_DICTION                       \n");  
        sbSQL.append("              ) PT_D  ON PT_M.TMP_WRT_NUM = PT_D.TMP_WRT_NUM  \n");
        sbSQL.append("                     AND PT_M.WRT_NUM     = PT_D.WRT_NUM      \n");
        
        sbSQL.append("  WHERE PT_C.COI_WRT_NUM = ?  \n");   // 공사업등록번호  
        sbSQL.append("    AND PT_C.MANA_NUM = ?     \n");   // 사업자 번호
          
        rDAO.setValue(i++, user.getREG_NUM());     
        rDAO.setValue(i++, user.getCOM_NUM());  
        
        rEntity = rDAO.select(sbSQL.toString());

        /****** 검색조건 초기값 ***********/
        request.setAttribute("dEntity", rEntity);
    }
    
    
    /**
     * 폼 체크 메소드
     * 
     * @param request
     * @param form
     * @return
     * @throws Exception
     */
    private IllegalityParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {

        IllegalityParam pm = (IllegalityParam)form;

        // 파라미터 디버깅
        KJFLog.debug(pm.toString(request));
        
        // 페이징 라인
        if (KJFUtil.isEmpty(pm.getRowPerPage())) {            
            pm.setRowPerPage(Config.props.get("ROW_PER_PAGE"));
        }

        return pm;
    }
}
