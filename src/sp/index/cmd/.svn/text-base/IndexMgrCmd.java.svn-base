package sp.index.cmd;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.cfg.Config;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFLog;
import kjf.util.KJFUtil;

import org.apache.struts.action.ActionForm;

import sp.index.IndexParam;
import sp.uent.UserEnt;

/****************************************************************************
 * <p>Title       : IndexCmd Class</p>
 * <p>Description : 메인 처리를 하는 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 *
 * NOTE : 메인 페이지 조건에 따른 값을 Load한다.
 *
 * @version
 * @author PKT
 ***************************************************************************/
public class IndexMgrCmd implements KJFCommand {
	
	public String execute(HttpServletRequest request, ActionForm form) throws Exception {
		
		// 검색조건 설정및 체크
		IndexParam pm = checkPm(request, form);
		
		UserEnt user = (UserEnt)request.getSession().getAttribute("user");
		
		ReportEntity NOTICE;
		ReportEntity NEWS;
		ReportEntity REG_PUBLIC;
		
		//if (KJFUtil.isEmpty(user) || "U".equals(user.getCAPITAL()) || "UE".equals(user.getCAPITAL())){
		
			NOTICE     = selectBoarList(request, "NOTICE", true, true, 4);       // 공지사항
			NEWS       = selectBoarList(request, "NEWS", true, true, 4);         // 새뉴스
			
		/*} else {
			
			NOTICE     = selectBoarList(request, "NOTICE_PUB", true, true, 4);       // 공지사항
			NEWS       = selectBoarList(request, "NEWS_PUB", true, true, 4);         // 새뉴스
			
		}
		*/
		REG_PUBLIC = selectBoarList(request, "REG_PUBLIC", true, false, 4);  // 공사업등록공고
		
		request.setAttribute("NOTICE", NOTICE);           // 공지사항
		request.setAttribute("NEWS", NEWS);               // 지역뉴스
		request.setAttribute("REG_PUBLIC", REG_PUBLIC);   // 공사업등록공고
		
		request.setAttribute("pm", pm); 
		
		return request.getParameter("cmd");
    }
	
	
	/**
	 * 개별 게시판 리스트를 가져오는 메소드
	 * 
	 * @param request  HttpServletRequest
	 * @param CT_ID    게시판
	 * @param isSD_CD  시도 코드 사용여부
	 * @param isSGG_CD 시군구 코드 사용여부
	 * @param rowCnt   리스트 갯수
	 * @return ReportEntity
	 * @throws Exception
	 */
    public ReportEntity selectBoarList(
                                        HttpServletRequest request, 
                                        String CT_ID, 
                                        boolean isSD_CD, 
                                        boolean isSGG_CD, 
                                        int rowCnt) throws Exception {

        ReportDAO    rDAO    = new ReportDAO();
        ReportEntity rEntity = null;
        
        UserEnt user = (UserEnt)request.getSession().getAttribute("user");
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();
        sbSQL.append(" SELECT                               \n");
        sbSQL.append("        BOARD_SEQ,                    \n");
        sbSQL.append("        CT_ID,                        \n");
        sbSQL.append("        INDEX1,                       \n");
        sbSQL.append("        INDEX2,                       \n");
        sbSQL.append("        DEPTH,                        \n");
        sbSQL.append("        USER_ID,                      \n");
        sbSQL.append("        SUBJECT,                      \n");
        sbSQL.append("        CONTENT,                      \n");
        sbSQL.append("        UPD_DT,                       \n");
        sbSQL.append("        INS_DT,                       \n");
        sbSQL.append("        WRT_ID                        \n");
        sbSQL.append("   FROM PT_BBS_" + CT_ID + "          \n");
        sbSQL.append("  WHERE 1 = 1                         \n");
        /*
        if ( !KJFUtil.isEmpty(user)) {
            
            if (isSD_CD) {  // 시.도코드 사용시 
                if (!KJFUtil.isEmpty(user.getSIDO_CODE())) {
                    sbSQL.append("    AND SIDO_CODE = ?     \n");
                    rDAO.setValue(i++, user.getSIDO_CODE());
                }
            }
            
            if (isSGG_CD) { // 시.군.구 코드 사용시 
                if (!KJFUtil.isEmpty(user.getSIGUNGU_CODE())) {
                    sbSQL.append("    AND SIGUNGU_CODE = ?  \n");
                    rDAO.setValue(i++, user.getSIGUNGU_CODE());
                }
            }
        }
        */
        sbSQL.append("  ORDER BY INDEX1 DESC, INDEX2 ASC    \n");
        
        rEntity = rDAO.select(sbSQL.toString(), rowCnt);

        return rEntity;
    }
	
	
	/**
     * 폼 체크 메소드
     * 
     * @param  request
     * @param  form
     * @return IndexParam
     * @throws Exception
     */
    private IndexParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {

    	IndexParam pm = (IndexParam)form;

        // 파라미터 디버깅
        KJFLog.debug(pm.toString(request));
        
        // 페이징 라인
        if (KJFUtil.isEmpty(pm.getRowPerPage())) {            
            pm.setRowPerPage(Config.props.get("ROW_PER_PAGE"));
        }

        return pm;
    }
}
