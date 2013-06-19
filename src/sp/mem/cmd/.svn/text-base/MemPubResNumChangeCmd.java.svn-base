package sp.mem.cmd;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFLog;
import kjf.util.KJFUtil;
import kjf.util.LoginException;

import org.apache.struts.action.ActionForm;

import sp.mem.MemParam;
import sp.uent.UserEnt;

/****************************************************************************
 * <p>Title       : MemPubResNumChangeCmd Class</p>
 * <p>Description : 공사업업체 등록여부 확인처리 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 *
 * NOTE : 공사업업체 등록여부 확인처리를 한다.
 *
 * @version 1.0
 * @author  PKT
 ***************************************************************************/
public class MemPubResNumChangeCmd implements KJFCommand {
    
    UserEnt user;
    
    public MemPubResNumChangeCmd() {
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {    
        
        // 검색조건 설정및 체크
        MemParam pm = checkPm(request, form);
        
        user = (UserEnt)request.getSession().getAttribute("user");
        
        if (KJFUtil.isEmpty(user)) {
            throw new LoginException(request, "회원전용 페이지 로그인 체크", "../member/login.do");
        }
    
        // 검색조건 및 초기데이타 로드
        updateCompanyInfo(request);
        
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
    private void updateCompanyInfo(HttpServletRequest request) throws Exception {
         
        MemParam form = loadCompanyInfo(request);
        
        ReportDAO rDAO = new ReportDAO();
        
        int i = 1;
        
        if (form.getCom_reg_yn().equals("Y")) {
     
            StringBuffer sbSQL = new StringBuffer();        
            sbSQL.append(" UPDATE PT_HOM_USER SET           \n");
            sbSQL.append("        COM_NAME = ?,             \n");
            sbSQL.append("        REG_NUM = ?,              \n");
            sbSQL.append("        OFFICE_POST_NUM = ?,      \n");
            sbSQL.append("        OFFICE_ADDR = ?,          \n");
            sbSQL.append("        OFFICE_ADDR_DETAIL = ?,   \n");
            sbSQL.append("        OFFICE_TEL = ?,           \n");
            sbSQL.append("        OFFICE_FAX = ?,           \n");
            sbSQL.append("        COM_REG_YN = ?            \n");
            sbSQL.append("  WHERE USER_ID = ?       \n");
            sbSQL.append("    AND USER_NAME = ?     \n");
           
            rDAO.setValue(i++, form.getCom_name());
            rDAO.setValue(i++, form.getReg_num());
            rDAO.setValue(i++, form.getOffice_post_num());
            rDAO.setValue(i++, form.getOffice_addr());
            rDAO.setValue(i++, form.getOffice_addr_detail());
            rDAO.setValue(i++, form.getOffice_tel());
            rDAO.setValue(i++, form.getOffice_fax());
            rDAO.setValue(i++, form.getCom_reg_yn());
            
            rDAO.setValue(i++, user.getUSER_ID());
            rDAO.setValue(i++, user.getUSER_NAME());
    
            rDAO.execute(sbSQL.toString());
        }
    }
    
    /**
     * 검색조건 및 초기데이타 로드
     * 
     * @param HttpServletRequest
     * @return
     */
    private MemParam loadCompanyInfo(HttpServletRequest request) throws Exception {
        
        ReportDAO    rDAO    = new ReportDAO();        
        ReportEntity rEntity = null;
        
        MemParam pm = new  MemParam();
        
        String com_num = request.getParameter("com_num");
        
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
        
        rDAO.setValue(1, com_num);
        
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

        // 파라미터 디버깅
        KJFLog.debug(pm.toString(request));

        return pm;
    }
}
