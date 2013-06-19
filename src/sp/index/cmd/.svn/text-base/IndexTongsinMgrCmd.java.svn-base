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
public class IndexTongsinMgrCmd implements KJFCommand {
	
	public String execute(HttpServletRequest request, ActionForm form) throws Exception {
		
		// 검색조건 설정및 체크
		IndexParam pm = checkPm(request, form);
		
		UserEnt user = (UserEnt)request.getSession().getAttribute("user");
		
		ReportEntity NOTICE;
		ReportEntity PDS;
		ReportEntity ITINFO;
		
		if (KJFUtil.isEmpty(user) || "U".equals(user.getCAPITAL()) || "UE".equals(user.getCAPITAL())){
			NOTICE = new ReportEntity();
			PDS = new ReportEntity();
			ITINFO = new ReportEntity();
		} else {
			NOTICE     = selectBoarList(request, "BOD_NOTICE_11", true, true, 4);       // 공지사항
			PDS        = selectBoarList(request, "BOD_DATA_1", true, true, 4);         	// 자료실
			ITINFO     = selectBoarList(request, "BOD_DATA_4", true, true, 4);         	// IT최근소식
		}
		
		request.setAttribute("NOTICE", NOTICE);           // 공지사항
		request.setAttribute("PDS", PDS);               // 지역뉴스
		request.setAttribute("ITINFO", ITINFO);   // IT최근소식
		
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
