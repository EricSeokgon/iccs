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
 * NOTE : 빠른사용전검사 접수현황을 처리를 한다. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class UseBeforeQuickStatusCmd implements KJFCommand {
    
    public UseBeforeQuickStatusCmd() {   
        
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
        
        String scRecvName = KJFUtil.print(pm.getScRecvName());  // 접수자 이름
        String scRecvNum  = KJFUtil.print(pm.getScRecvNum());   // 접수자 접수번호
        
        if (KJFUtil.isEmpty(scRecvName) || KJFUtil.isEmpty(scRecvNum)) {
            return;
        }
        
        int i = 1;
        StringBuffer selSQL = new StringBuffer();

        selSQL.append( " SELECT COUNT(*) AS CNT FROM PT_UB_USEBEFORE UB   ,PT_S_SYSVAR_MASTER SM ");
        selSQL.append(" WHERE APPLPER_NM = ? AND ( CASE  WHEN LENGTH(UB.CIV_RECV_NUM) = 18 THEN substr(UB.CIV_RECV_NUM,12,LENGTH(UB.CIV_RECV_NUM)) \n");
        selSQL.append("   ELSE UB.CIV_RECV_NUM END )  = ?  \n");        
        selSQL.append(" AND UB.SIGUNGU_CODE = SM.SIGUNGU_CODE ");
        //selSQL.append( " WHERE UB.CIV_RECV_NUM = ? AND UB.SIGUNGU_CODE = SM.SIGUNGU_CODE ");
        
        //rDAO.setValue(1,scRecvNum);
        rDAO.setValue(1, scRecvName);
        rDAO.setValue(2,scRecvNum);
        
        
        rEntity = rDAO.select(selSQL.toString());
        
        String selCnt = rEntity.getValue(0, "CNT");
        StringBuffer sbSQL = new StringBuffer();
        
        if ("0".equals(selCnt)){
	        sbSQL.append(" SELECT                   \n");
	        sbSQL.append("        CIV_RECV_NUM,     \n");   // 시스템 접수번호
	        sbSQL.append("        RECV_NUM,         \n");   // 사용자 접수번호
	        sbSQL.append("        APPLPER_NM,       \n");   // 신청인
	        sbSQL.append("        OPE_NAME,         \n");   // 시공업체
	        sbSQL.append("        INSP_SPOT_ADDR,INSP_SPOT_DETAILADDR ,     \n");   // 현장주소
	        sbSQL.append("        RECV_DT,          \n");   // 접수일자    
	        sbSQL.append(" 		  INSP_WISH_YMD,    \n"); // 검사희망일
	        sbSQL.append("        INSP_DT,          \n");   // 검사일
	        
	        // 진행상태
	        sbSQL.append("        DECODE(PROC_STE, '1', '접수처리',        \n");   
	        sbSQL.append("                         '2', '처리중',          \n");  
	        sbSQL.append("                         '3', '처리완료',        \n");  
	        sbSQL.append("                         '') AS PROC_STE,    \n");  // 처리상태
	        sbSQL.append("        SIDO_CODE,    \n"); // 시도코드
	        sbSQL.append("        SIGUNGU_CODE,    \n"); // 시군구코드
	        sbSQL.append("		  'N' as ONLINE_CERT_USE_YN,   \n"); // 필증발급 유무
	        sbSQL.append("		  'N' as SERVER_YN  \n"); // 서버사용 유무
	        sbSQL.append("	FROM PT_UB_USEBEFORE  \n");
	        
	        sbSQL.append(" WHERE REPLACE(APPLPER_NM,' ','') = ?   AND \n");
	        sbSQL.append(" ( CASE  WHEN LENGTH(CIV_RECV_NUM) = 18 THEN substr(CIV_RECV_NUM,12,LENGTH(CIV_RECV_NUM)) \n");
	        sbSQL.append("   ELSE CIV_RECV_NUM END )  = ? \n");
	        
	        rDAO.setValue(i++, scRecvName);
	        rDAO.setValue(i++,scRecvNum);
	        
        }else {
        
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
	        sbSQL.append("        UB.SIDO_CODE,    \n"); // 시도코드
	        sbSQL.append("        UB.SIGUNGU_CODE,    \n"); // 시군구코드
	        sbSQL.append("		  SM.ONLINE_CERT_USE_YN,   \n"); // 필증발급 유무
	        sbSQL.append("		  SM.SERVER_YN  \n"); // 서버사용 유무
	        sbSQL.append("	FROM PT_UB_USEBEFORE UB   ,PT_S_SYSVAR_MASTER SM \n");
	        	        
	        // 201001 수정
	        //sbSQL.append("  WHERE (APPLPER_NM = ? OR APPLPER_REP = ? OR OPE_NAME = ? OR OPE_REP = ?)    \n");
	        //sbSQL.append("    AND (SUBSTR(APPLPER_TELNUM, LENGTH(APPLPER_TELNUM)-3, LENGTH(APPLPER_TELNUM)) = ?  OR SUBSTR(OPE_TELNUM, LENGTH(OPE_TELNUM)-3, LENGTH(OPE_TELNUM)) = ?)   \n");
	        
	//        sbSQL.append("  WHERE APPLPER_NM = ?   AND  \n");
	//        sbSQL.append("     	  LPAD(CIV_RECV_NUM,7,'0') = ?   \n");
	        
	        sbSQL.append(" WHERE REPLACE(UB.APPLPER_NM,' ','') = ?   AND \n");
	        sbSQL.append(" ( CASE  WHEN LENGTH(UB.CIV_RECV_NUM) = 18 THEN substr(UB.CIV_RECV_NUM,12,LENGTH(UB.CIV_RECV_NUM)) \n");
	        sbSQL.append("   ELSE UB.CIV_RECV_NUM END )  = ? \n");
	        sbSQL.append("   AND SM.SIGUNGU_CODE = UB.SIGUNGU_CODE ");
	        
	        rDAO.setValue(i++, scRecvName);
	        //rDAO.setValue(i++, KJFUtil.f_code_make(scRecvNum,7));
	        rDAO.setValue(i++,scRecvNum);

        }
        
        /* ************************** 페이징 관련 START **************************/
        StringBuffer sbCntSQL = new StringBuffer();
        sbCntSQL.append(" SELECT COUNT(*)  CNT      \n");
        sbCntSQL.append("   FROM PT_UB_USEBEFORE    \n"); 
        // 201001 수정
        //sbCntSQL.append("  WHERE (APPLPER_NM = ? OR APPLPER_REP = ? OR OPE_NAME = ? OR OPE_REP = ?)    \n");
        //sbCntSQL.append("    AND (SUBSTR(APPLPER_TELNUM, LENGTH(APPLPER_TELNUM)-3, LENGTH(APPLPER_TELNUM)) = ?  OR SUBSTR(OPE_TELNUM, LENGTH(OPE_TELNUM)-3, LENGTH(OPE_TELNUM)) = ?)   \n");
        sbCntSQL.append(" WHERE REPLACE(APPLPER_NM,' ','') = ?   AND \n");
        sbCntSQL.append(" ( CASE  WHEN LENGTH(CIV_RECV_NUM) = 18 THEN substr(CIV_RECV_NUM,12,LENGTH(CIV_RECV_NUM)) \n");
        sbCntSQL.append("   ELSE CIV_RECV_NUM END )   = ? ");
            
        
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
