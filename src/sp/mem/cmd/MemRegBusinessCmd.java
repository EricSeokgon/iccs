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
 * <p>Title       : MemRegBusinessCmd Class</p>
 * <p>Description : 기업회원등록 화면 처리 프로세스 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 *
 * NOTE : 기업회원등록화면 처리를 한다.
 *
 * @version 1.0
 * @author  PKT
 ***************************************************************************/
public class MemRegBusinessCmd implements KJFCommand {
    
    public MemRegBusinessCmd() {
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
        
        String com_num = KJFUtil.print(request.getParameter("com_num"));
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT COUNT(*) AS CNT   \n");
        sbSQL.append("   FROM PT_HOM_USER               \n");
        sbSQL.append("  WHERE COM_NUM = ?               \n");
        
        rDAO.setValue(i++, com_num);
        
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
     * 검색조건 및 초기데이타 로드
     * 
     * @param HttpServletRequest
     * @return
     */
    private MemParam loadCompanyInfo(HttpServletRequest request, MemParam pm) throws Exception {
        
        ReportDAO    rDAO    = new ReportDAO();        
        ReportEntity rEntity = null;
        
        StringBuffer sbSQL = new StringBuffer();
        sbSQL = new StringBuffer();
        sbSQL.append(" SELECT NAME,                 \n");   // 상호
        sbSQL.append("        COI_WRT_NUM,          \n");   // 등록증 등록번호
        sbSQL.append("        ADDR_POST_NUM,        \n");   // 소재지 우편번호
        sbSQL.append("        ADDR_ADDR,            \n");   // 소재지 주소
        sbSQL.append("        ADDR_DETAIL_ADDR,     \n");   // 소재지 주소상세
        sbSQL.append("        ADDR_TEL_NUM,         \n");   // 소재지 전화번호      
        sbSQL.append("        ADDR_FAX_NUM          \n");   // 소재지 팩스번호           
        sbSQL.append("   FROM PT_R_COMPANY_MASTER   \n");
        sbSQL.append("  WHERE MANA_NUM = ?          \n");
        
        rDAO.setValue(1, pm.getCom_num());
        
        rEntity = rDAO.select(sbSQL.toString());
        
        if (rEntity.getRowCnt() > 0) {
            
            pm.setCom_name(rEntity.getValue(0, "NAME"));                        // 상호          
            pm.setReg_num(rEntity.getValue(0, "COI_WRT_NUM"));                  // 등록증 등록번호
            pm.setOffice_post_num(rEntity.getValue(0, "ADDR_POST_NUM"));        // 소재지 우편번호
            pm.setOffice_addr(rEntity.getValue(0, "ADDR_ADDR"));                // 소재지 주소
            pm.setOffice_addr_detail(rEntity.getValue(0, "ADDR_DETAIL_ADDR"));  // 소재지 주소상세
            pm.setOffice_tel(rEntity.getValue(0, "ADDR_TEL_NUM"));              // 소재지 전화번호 
            pm.setOffice_fax(rEntity.getValue(0, "ADDR_FAX_NUM"));              // 소재지 팩스번호
            pm.setCom_reg_yn("Y");                                              // 공사업등록여부
            
        } else {
            pm.setCom_reg_yn("N");  // 공사업등록여부
        }
        
        
        return pm;
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
        
        pm = loadCompanyInfo(request, pm);

        // 파라미터 디버깅
        KJFLog.debug(pm.toString(request));

        return pm;
    }
}
