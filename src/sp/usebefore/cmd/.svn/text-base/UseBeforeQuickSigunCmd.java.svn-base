package sp.usebefore.cmd;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.cfg.Config;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFLog;
import kjf.util.KJFUtil;

import org.apache.struts.action.ActionForm;

import sp.usebefore.UseBeforeParam;

/***************************************************************************
 * <p>Title       : UseBeforeQuickStatus Class</p>
 * <p>Description : 빠른사용전검사 접수현황 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : 빠른사용전검사 접수현황을 처리를 한다.(검색없이 리스트 출력) 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class UseBeforeQuickSigunCmd implements KJFCommand {
    
    public UseBeforeQuickSigunCmd() {   
        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {        
            
        // 검색조건 설정및 체크
        UseBeforeParam pm = checkPm(request, form);
        
        /****** 검색조건 초기값 ***********/
        request.setAttribute("pm", pm);
        
        // 빠른 사용전검사 현황 정보 
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
        
        // 시군구 코드값으로 두번 decode 한 값으로 지정 
        // KJFUtil.base64Decode(KJFUtil.base64Decode(gbcd)) ==> WjJKalpBPT0=
        String sigungu_code = KJFUtil.print(request.getParameter("getCode"),"");
        
        if (KJFUtil.isEmpty(sigungu_code) || "".equals(sigungu_code)) {
          return;
        }  else {
            pm.setScSIGUNGU_CODE(sigungu_code);
        	sigungu_code = KJFUtil.base64Decode(KJFUtil.base64Decode(sigungu_code));
        	if (!"gbcd".equals(sigungu_code)){
        		return;
        	}
        }
        
        String scRecvName = KJFUtil.print(pm.getScRecvName());  // 접수자 이름
        String scRecvNum  = KJFUtil.print(pm.getScRecvNum());   // 접수자 접수번호
        
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();
        sbSQL.append(" SELECT                   \n");
        sbSQL.append("        UB.CIV_RECV_NUM,     \n");   // 시스템 접수번호
        sbSQL.append("        UB.RECV_NUM,         \n");   // 사용자 접수번호
        sbSQL.append("        UB.APPLPER_NM,       \n");   // 신청인
        sbSQL.append("        UB.OPE_NAME,         \n");   // 시공업체
        sbSQL.append("        UB.INSP_SPOT_ADDR,UB.INSP_SPOT_DETAILADDR ,     \n");   // 현장주소
        sbSQL.append("        UB.RECV_DT,          \n");   // 접수일자    
        sbSQL.append(" 		  UB.INSP_WISH_YMD,    \n"); // 검사희망일
        sbSQL.append("        UB.INSP_DT,          \n");   // 검사일
        
        // 진행상태
        sbSQL.append("        DECODE(UB.PROC_STE, '1', '접수처리',        \n");   
        sbSQL.append("                         '2', '처리중',          \n");  
        sbSQL.append("                         '3', '처리완료',        \n");  
        sbSQL.append("                         '') AS PROC_STE,    \n");  // 처리상태
        sbSQL.append("        UB.SIDO_CODE,   \n"); // 시도코드
        sbSQL.append("        UB.SIGUNGU_CODE    \n"); // 시군구코드
        sbSQL.append("	FROM PT_UB_USEBEFORE UB \n");
        
        sbSQL.append(" WHERE UB.SIGUNGU_CODE = '"+sigungu_code+"' \n");
        

        if (!KJFUtil.isEmpty(scRecvName)){
        	sbSQL.append(" AND UB.APPLPER_NM = '"+scRecvName+"' \n");
        }
        if (!KJFUtil.isEmpty(scRecvNum)) {
            sbSQL.append(" AND ( CASE  WHEN LENGTH(UB.CIV_RECV_NUM) = 18 THEN substr(UB.CIV_RECV_NUM,12,LENGTH(UB.CIV_RECV_NUM)) \n");
            sbSQL.append("   ELSE UB.CIV_RECV_NUM END )  = '"+scRecvNum+"' \n");
        }
        
        //rDAO.setValue(i++, sigungu_code);

        /* ************************** 페이징 관련 START **************************/
        StringBuffer sbCntSQL = new StringBuffer();
        sbCntSQL.append(" SELECT COUNT(*)  CNT      \n");
        sbCntSQL.append("   FROM PT_UB_USEBEFORE    \n"); 
        sbCntSQL.append(" WHERE SIGUNGU_CODE = '"+sigungu_code+"' \n");
        
        if (!KJFUtil.isEmpty(scRecvName)){
        	sbCntSQL.append(" AND APPLPER_NM = '"+scRecvName+"' \n");
        }
        if (!KJFUtil.isEmpty(scRecvNum)) {
        	sbCntSQL.append(" AND ( CASE  WHEN LENGTH(CIV_RECV_NUM) = 18 THEN substr(CIV_RECV_NUM,12,LENGTH(CIV_RECV_NUM)) \n");
        	sbCntSQL.append("   ELSE CIV_RECV_NUM END )  = '"+scRecvNum+"' \n");
        }
        
        //전체 목록 수
        String totalCount="";

        //페이지별 목록 수
        int rowPerPage = KJFUtil.str2int(pm.getRowPerPage());

        //현재 페이지 번호
        int nowPage = 1;
        nowPage = KJFUtil.isEmpty(pm.getNowPage()) ? 1 : Integer.parseInt(pm.getNowPage());

        rEntity = rDAO.select(sbCntSQL.toString());
        
        totalCount = rEntity.getValue(0, "CNT");
        
        if (rowPerPage == 0) rowPerPage = Integer.parseInt(totalCount);//추가
        if ((rowPerPage*nowPage) - Integer.parseInt(totalCount) > rowPerPage) nowPage = 1;

        pm.setTotalCount(totalCount);
        pm.setNowPage(String.valueOf(nowPage));
        /* *************************** 페이징 관련  END **************************/

        rEntity = rDAO.select(sbSQL.toString(), nowPage, rowPerPage);
       
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
