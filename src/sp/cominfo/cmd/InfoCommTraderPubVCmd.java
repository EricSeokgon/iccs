package sp.cominfo.cmd;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.cfg.Config;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFLog;
import kjf.util.KJFSelect;
import kjf.util.KJFSelectOPS;
import kjf.util.KJFUtil;
import kjf.util.LoginException;

import org.apache.struts.action.ActionForm;

import sp.cominfo.ComInfoParam;
import sp.regmgr.RegMgrParam;
import sp.uent.UserEnt;

/***************************************************************************
 * <p>Title       : InfoCommTraderVCmd Class</p>
 * <p>Description : 정보통신공사업자 상세보기 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : 정보통신공사업자 검색 처리를 한다. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class InfoCommTraderPubVCmd implements KJFCommand {
	
	UserEnt user;
    
	public InfoCommTraderPubVCmd() {        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {        
        
        UserEnt user = (UserEnt)request.getSession().getAttribute("user");
        
        // 로그인 유무 체크
        if (KJFUtil.isEmpty(user)) {
            throw new LoginException(request, "회원용 페이지 로그인 체크", "../member/login.do");
        }  
        
        // 검색조건 설정및 체크
        ComInfoParam pm = checkPm(request, form);        
        
        // 초기데이타 로드
        loadCondition(request, pm);
        
        
        // 공사업등록 정보  
        loadRegData(request, pm);

        // 상호및행정처분사항
        loadRefferData(request, pm);
        
        // 등록기준신고
        loadRegBasicData(request, pm);
        
        // 보유기술자 정보  
        loadEngineerData(request, pm);

        // 겸업 정보
        loadSubsidiaryData(request, pm);
        
        
        return request.getParameter("cmd");
    }
    
    /**
     * 검색조건 및 초기데이타 로드
     * 
     * @param HttpServletRequest
     * @return
     */
    private void loadCondition(HttpServletRequest request, ComInfoParam pm) throws Exception {
        
        // 시도코드
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
     * 정보통신공사업자 정보를 가져온다.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
//    public void loadData(HttpServletRequest request, ComInfoParam pm) throws Exception {
//        
//        ReportEntity rEntity = null;
//        
//        String scCode = KJFUtil.print(pm.getScCode());
//        String scText = KJFUtil.print(pm.getScText());
//        
//        if ( KJFUtil.isEmpty(scCode) || KJFUtil.isEmpty(scText)) {
//            request.setAttribute("pm", pm);
//            request.setAttribute("rEntity", rEntity);
//            return;
//        }                
//        
//        ReportDAO rDAO = new ReportDAO(); 
//        
//        int i = 1;
//        
//        StringBuffer sbSQL = new StringBuffer();        
//        sbSQL.append(" SELECT                       \n");
//        sbSQL.append("        TMP_WRT_NUM,          \n");   // 가등록번호
//        sbSQL.append("        COI_WRT_NUM,          \n");   // 공사업등록번호
//        sbSQL.append("        NAME,                 \n");   // 상호
//        sbSQL.append("        REP_NM_KOR,           \n");   // 대표자
//        sbSQL.append("        SIDO_CODE,            \n");   // 소재지
//        sbSQL.append("        ADDR_TEL_NUM          \n");   // 전화번호
//        sbSQL.append("   FROM PT_R_COMPANY_MASTER   \n");
//        
//        StringBuffer sbWhereSQL = new StringBuffer(); 
//        sbWhereSQL.append("  WHERE 1=1  \n");   // 공사업등록번호
//        
//        if (scCode.equals("001")) {         // 등록번호
//            sbWhereSQL.append("    AND COI_WRT_NUM = ?     \n");
//            rDAO.setValue(i++, scText);
//            
//        } else if (scCode.equals("002")) {  // 상호
//            sbWhereSQL.append("    AND NAME LIKE ?         \n");
//            rDAO.setValue(i++, "%" + scText + "%");
//            
//        } else if (scCode.equals("003")) {  // 대표자
//            sbWhereSQL.append("    AND REP_NM_KOR LIKE ?   \n");
//            rDAO.setValue(i++, "%" + scText + "%");
//        } 
//        
//        // 시도
//        if (!KJFUtil.isEmpty(pm.getScSidoCode())) {
//        	sbWhereSQL.append("    AND SIDO_CODE = ?   \n");
//            rDAO.setValue(i++, pm.getScSidoCode());            
//        }
//        
//        /* ************************** 페이징 관련 START **************************/
//        StringBuffer sbCntSQL = new StringBuffer();
//        sbCntSQL.append(" SELECT COUNT(*)  CNT          \n");
//        sbCntSQL.append("   FROM PT_R_COMPANY_MASTER    \n");
//        sbCntSQL.append(sbWhereSQL); 
//        
//        //전체 목록 수
//        String totalCount="";
//
//        //페이지별 목록 수
//        int rowPerPage = KJFUtil.str2int(pm.getRowPerPage());
//
//        //현재 페이지 번호
//        int nowPage = 1;
//        nowPage = KJFUtil.isEmpty(pm.getNowPage()) ? 1 : Integer.parseInt(pm.getNowPage());
//
//        rEntity = rDAO.select(sbCntSQL.toString());
//        
//        totalCount = rEntity.getValue(0, "CNT");
//        
//        if (rowPerPage == 0) rowPerPage = Integer.parseInt(totalCount);//추가
//        if ((rowPerPage*nowPage) - Integer.parseInt(totalCount) > rowPerPage) nowPage = 1;
//
//        pm.setTotalCount(totalCount);
//        pm.setNowPage(String.valueOf(nowPage));
//        /* *************************** 페이징 관련  END **************************/
//
//        rEntity = rDAO.select(sbSQL.toString() + sbWhereSQL.toString(), nowPage, rowPerPage);
//
//        /****** 검색조건 초기값 ***********/
//        request.setAttribute("pm", pm);
//        request.setAttribute("rEntity", rEntity);
//    }
    
    /**
     * 공사업 등록 정보를 가져온다.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadRegData(HttpServletRequest request, ComInfoParam pm) throws Exception {
    	
        ReportEntity rEntity = null;        
        
        ReportDAO rDAO = new ReportDAO();    
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();        
//        sbSQL.append(" SELECT                               \n");
//        sbSQL.append("        PT_H.MANA_NUM,                \n");   // 사업자 등록번호
//        sbSQL.append("        PT_H.COI_WRT_NUM,             \n");   // 등록번호
//        sbSQL.append("        PT_H.REP_SSN1,             	\n");   // 사업자 주민번호
//        sbSQL.append("        PT_H.RECV_NUM,                \n");   // 접수번호
//        sbSQL.append("        PT_S.RECV_DT,                 \n");   // 접수일자
//        sbSQL.append("        PT_H.MOT_STE,                 \n");   // 처리상태
//        sbSQL.append("        PT_C1.CODE_NAME AS MOT_STE_NM,\n");   // 처리상태
//        
//        sbSQL.append("        PT_M.NAME,                    \n");   // 상호
//        sbSQL.append("        PT_M.ADDR_TEL_NUM,            \n");   // 전화번호
//        sbSQL.append("        PT_M.REP_NM_KOR,              \n");   // 대표자
//        sbSQL.append("        PT_M.ADDR_FAX_NUM,            \n");   // 팩스번호
//        sbSQL.append("        PT_M.ADDR_POST_NUM,           \n");   // 소재지주소 우편번호
//        sbSQL.append("        PT_M.ADDR_ADDR,               \n");   // 소재지주소
//        sbSQL.append("        PT_M.ADDR_DETAIL_ADDR,        \n");   // 소재지주소상세
//        
//        sbSQL.append("        PT_M.PAY_CAP,                 \n");   // 납입자본금        
//        
//        // 진단구분
//        sbSQL.append("        DECODE(PT_M.COMPANY_DIAG_CLASS_CODE, 'P1', '신규등록', \n");   
//        sbSQL.append("                                             'P2', '기준신고', \n");  
//        sbSQL.append("                                             'P3', '양도양수', \n");  
//        sbSQL.append("                                                   '법인합병') AS COMPANY_DIAG_CLASS_CODE, \n");
//        
//        sbSQL.append("        PT_M.DIAG_ORG_CODE,                       \n");   // 진단자구분 코드
//        sbSQL.append("        PT_C2.CODE_NAME AS DIAG_ORG_CODE_NM,      \n");   // 진단자구분 코드 명
//        sbSQL.append("        PT_M.DIAG_NM_NM,                          \n");   // 진단자성명
//        sbSQL.append("        PT_M.DIAG_NM_WRT_NUM,                     \n");   // 진단자등록번호
//        
//        sbSQL.append("        PT_M.COMPANY_DIAG_BAS_DT,                 \n");   // 진단기준일
//        sbSQL.append("        PT_M.REA_CAP,                             \n");   // 실질자본금
//        sbSQL.append("        PT_M.COMPANY_DIAG_ISSUE_DT,               \n");   // 발급일자
//        
//        sbSQL.append("        PT_M.OFFICE_USE_CODE,                     \n");   // 부동산용도 코드
//        sbSQL.append("        PT_C3.CODE_NAME AS OFFICE_USE_CODE_NM,    \n");   // 부동산용도 명
//        sbSQL.append("        PT_M.OFFICE_AREA,                         \n");   // 부동산면적        
//        
//        // 자본금예치 구분
//        sbSQL.append("        DECODE(PT_M.TUP_CLASS_CODE, 'P1', '출자',   \n");   
//        sbSQL.append("                                    'P2', '예치',   \n");  
//        sbSQL.append("                                           '') AS TUP_CLASS_CODE, \n");
//        sbSQL.append("        PT_M.TUP_AOM                              \n");   // 자본금예치 금액
//        
//        sbSQL.append("   FROM (PT_R_COMPANY_MASTER PT_M                 \n");
//        
//        sbSQL.append("   LEFT JOIN PT_R_BASIC_CHANGE_HISTORY PT_H               \n");
//        sbSQL.append("        ON RTRIM(PT_M.TMP_WRT_NUM) = PT_H.TMP_WRT_NUM)    \n");    
//        
//        // 처리상태 코드 명
//        sbSQL.append("   LEFT JOIN (                                        \n");  
//        sbSQL.append("              SELECT P_CODE, CODE, CODE_NAME          \n"); 
//        sbSQL.append("                FROM PT_COM_CODE                      \n");   
//        sbSQL.append("               WHERE P_CODE = 'REGPROC'               \n");   
//        sbSQL.append("              ) PT_C1 ON PT_H.MOT_STE = PT_C1.CODE    \n"); 
//        
//        // 진단자 구분 코드명
//        sbSQL.append("   LEFT JOIN (                                        \n");  
//        sbSQL.append("              SELECT P_CODE, CODE, CODE_NAME          \n"); 
//        sbSQL.append("                FROM PT_COM_CODE                      \n");   
//        sbSQL.append("               WHERE P_CODE = 'DIGDIV'                \n");   
//        sbSQL.append("              ) PT_C2 ON PT_M.DIAG_ORG_CODE = PT_C2.CODE    \n"); 
//        
//        // 부동산용도 코드명
//        sbSQL.append("   LEFT JOIN (                                        \n");  
//        sbSQL.append("              SELECT P_CODE, CODE, CODE_NAME          \n"); 
//        sbSQL.append("                FROM PT_COM_CODE                      \n");   
//        sbSQL.append("               WHERE P_CODE = 'BLDDIV'                \n");   
//        sbSQL.append("              ) PT_C3 ON PT_M.OFFICE_USE_CODE = PT_C3.CODE    \n");
//        
//        sbSQL.append("   LEFT JOIN (                                            \n");   
//        sbSQL.append("               SELECT RECV_NUM,       \n");   // 접수번호
//        sbSQL.append("                      RECV_DT         \n");   // 접수일자
//        sbSQL.append("                 FROM PT_R_REGIST_STATEMENT               \n");        
//        sbSQL.append("              ) PT_S ON PT_H.RECV_NUM = PT_S.RECV_NUM     \n"); 
//      
//        sbSQL.append("  WHERE (PT_M.COI_WRT_NUM = ? AND PT_M.MANA_NUM = ?)      \n");   // 공사업등록번호, 사업자번호 
//        sbSQL.append("     OR PT_M.MANA_NUM = ?                                 \n");   // 사업자번호  

    	  
        String MANA_NUM = request.getParameter("seq");
        String COI_WRT_NUM = request.getParameter("seq2");
        
		sbSQL.append(" SELECT                               \n");
		sbSQL.append("        PT_M.MANA_NUM,                \n");   // 사업자 등록번호
		sbSQL.append("        PT_H.COI_WRT_NUM,             \n");   // 등록번호
		sbSQL.append("        PT_H.REP_SSN1,             	\n");   // 사업자 주민번호
		sbSQL.append("        PT_H.RECV_NUM,                \n");   // 접수번호
		
//		sbSQL.append("        PT_M.TMP_WRT_NUM,             \n");   // 가등록번호		
//		sbSQL.append("        PT_M.COI_WRT_NUM,             \n");   // 등록증-등록번호
//		sbSQL.append("        PT_M.MANA_NUM,            	\n");   // 사업자 번호 

		sbSQL.append("        PT_M.NAME,             		\n");   // 상호
		sbSQL.append("        ( SELECT COM_NUM FROM PT_R_REGIST_STATEMENT WHERE MANA_NUM = ?) AS COM_NUM,\n");   // 법인번호		
		sbSQL.append("        PT_M.WRT_DT,					\n");   // 등록일
		sbSQL.append("        PT_M.REP_NM_KOR,				\n");   // 대표자명
		//sbSQL.append("        PT_M.REP_SSN1,				\n");   // 대표자 주민번호
		//sbSQL.append("        PT_M.OFFICE_AREA,				\n");   // 사무실면적
		sbSQL.append("        PT_M.ADDR_POST_NUM,      		\n");   // 영업소재지 우편번호
		sbSQL.append("        PT_M.ADDR_ADDR,				\n");   // 영업소재지 주소
		sbSQL.append("        PT_M.ADDR_DETAIL_ADDR,   		\n");   // 영업소재지  상제주소
		sbSQL.append("        PT_M.ADDR_TEL_NUM,       		\n");   // 전화번호
		sbSQL.append("        PT_M.ADDR_FAX_NUM,			\n");   // 팩스번호
		sbSQL.append("        PT_R.ASSE_DT,					\n");   // 시공능력일자
		sbSQL.append("        PT_R.ASSE_AOM,				\n");   // 시공능력평가액
		sbSQL.append("        PT_M.PAY_CAP,					\n");   // 납입자본금
		sbSQL.append("        PT_M.REA_CAP,					\n");   // 실질자본금
		
	    // 진단구분
		sbSQL.append("        DECODE(PT_M.COMPANY_DIAG_CLASS_CODE, 'P1', '신규등록', \n");   
		sbSQL.append("                                             'P2', '기준신고', \n");  
		sbSQL.append("                                             'P3', '양도양수', \n");  
		sbSQL.append("                                                   '법인합병') AS COMPANY_DIAG_CLASS_CODE, \n");
		
		sbSQL.append("        PT_M.DIAG_ORG_CODE,                       \n");   // 진단자구분 코드
		sbSQL.append("        PT_C2.CODE_NAME AS DIAG_ORG_CODE_NM,      \n");   // 진단자구분 코드 명
		sbSQL.append("        PT_M.DIAG_NM_NM,                          \n");   // 진단자성명
		sbSQL.append("        PT_M.DIAG_NM_WRT_NUM,                     \n");   // 진단자등록번호
		  
		sbSQL.append("        PT_M.COMPANY_DIAG_BAS_DT,                 \n");   // 진단기준일
		//sbSQL.append("        PT_M.REA_CAP,                             \n");   // 실질자본금
		sbSQL.append("        PT_M.COMPANY_DIAG_ISSUE_DT,               \n");   // 발급일자
		  
		sbSQL.append("        PT_M.OFFICE_USE_CODE,                     \n");   // 부동산용도 코드
		sbSQL.append("        PT_C3.CODE_NAME AS OFFICE_USE_CODE_NM,    \n");   // 부동산용도 명
		sbSQL.append("        PT_M.OFFICE_AREA,                         \n");   // 부동산면적        
		  
		// 자본금예치 구분
		sbSQL.append("        DECODE(PT_M.TUP_CLASS_CODE, 'P1', '출자',   \n");   
		sbSQL.append("                                    'P2', '예치',   \n");  
		sbSQL.append("                                           '') AS TUP_CLASS_CODE, \n");
		sbSQL.append("        PT_M.TUP_AOM                              \n");   // 자본금예치 금액

		//sbSQL.append("		  FROM PT_R_COMPANY_MASTER PT_M \n");   
		sbSQL.append("   FROM (PT_R_COMPANY_MASTER PT_M                 \n");
	      
	    sbSQL.append("   LEFT JOIN PT_R_BASIC_CHANGE_HISTORY PT_H               \n");
	    sbSQL.append("        ON RTRIM(PT_M.TMP_WRT_NUM) = PT_H.TMP_WRT_NUM)    \n");
		  
		// 처리상태 코드 명
		sbSQL.append("   LEFT JOIN (                                        \n");  
		sbSQL.append("              SELECT P_CODE, CODE, CODE_NAME          \n"); 
		sbSQL.append("                FROM PT_COM_CODE                      \n");   
		sbSQL.append("               WHERE P_CODE = 'REGPROC'               \n");   
		sbSQL.append("              ) PT_C1 ON PT_H.MOT_STE = PT_C1.CODE    \n"); 
		  
		// 진단자 구분 코드명
		sbSQL.append("   LEFT JOIN (                                        \n");  
		sbSQL.append("              SELECT P_CODE, CODE, CODE_NAME          \n"); 
		sbSQL.append("                FROM PT_COM_CODE                      \n");   
		sbSQL.append("               WHERE P_CODE = 'DIGDIV'                \n");   
		sbSQL.append("              ) PT_C2 ON PT_M.DIAG_ORG_CODE = PT_C2.CODE    \n"); 
		  
		// 부동산용도 코드명
		sbSQL.append("   LEFT JOIN (                                        \n");  
		sbSQL.append("              SELECT P_CODE, CODE, CODE_NAME          \n"); 
		sbSQL.append("                FROM PT_COM_CODE                      \n");   
		sbSQL.append("               WHERE P_CODE = 'BLDDIV'                \n");   
		sbSQL.append("              ) PT_C3 ON PT_M.OFFICE_USE_CODE = PT_C3.CODE    \n");
		  
		sbSQL.append("   LEFT JOIN (                                            \n");   
		sbSQL.append("               SELECT RECV_NUM,       \n");   // 접수번호
		sbSQL.append("                      RECV_DT         \n");   // 접수일자
		sbSQL.append("                 FROM PT_R_REGIST_STATEMENT               \n");        
		sbSQL.append("              ) PT_S ON PT_H.RECV_NUM = PT_S.RECV_NUM     \n"); 
	    
		sbSQL.append("	LEFT JOIN (					\n");   
		sbSQL.append("	 		   	   SELECT ASSE_DT,ASSE_AOM,TMP_WRT_NUM FROM PT_R_WORK_CAPABILITY \n"); 
		sbSQL.append(" 				) PT_R ON  PT_R.TMP_WRT_NUM = PT_M.TMP_WRT_NUM 					\n");
		sbSQL.append("	WHERE (PT_M.COI_WRT_NUM = ? AND PT_M.MANA_NUM = ?)						 	\n");      
		sbSQL.append("	      OR PT_M.MANA_NUM = ?						 							\n");   

		rDAO.setValue(i++, MANA_NUM);
        rDAO.setValue(i++, COI_WRT_NUM);
        rDAO.setValue(i++, MANA_NUM);        
        rDAO.setValue(i++, MANA_NUM);
        
        rEntity = rDAO.select(sbSQL.toString());

        for (int j = 0; j < rEntity.getRowCnt(); j++ ) {
            rEntity.setValue(j, "rWorkNum",     getWork_Num(rEntity.getValue(j, "COI_WRT_NUM")));  // 등록번호                           
        } 
        /****** 검색조건 초기값 ***********/
        request.setAttribute("rEntity", rEntity);        
    }
    
    /**
     * 년도별 공사실적을 가져온다.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public ReportEntity getWork_Num(String TMP_WRT_NUM) throws Exception {
        ReportDAO rDAO = new ReportDAO();    
        ReportEntity rEntity = null;        
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT                               \n");        
        sbSQL.append("        YEAR,            				\n");   // 공사실적 년도
        sbSQL.append("        WORK_ROM           			\n");   // 공사실적 금액
        sbSQL.append(" FROM PT_R_WORK_RESULTS     			\n");        
        sbSQL.append(" WHERE TMP_WRT_NUM = ?      			\n");   // 공사업등록번호
        sbSQL.append(" ORDER BY YEAR ASC    				\n");   // 공사업등록번호
        rDAO.setValue(1, TMP_WRT_NUM);

        rEntity = rDAO.select(sbSQL.toString());

        return rEntity;                
    }
    
    /**
     * 상호 및 행정처분 사항를 가져온다.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadRefferData(HttpServletRequest request, ComInfoParam pm) throws Exception {
        ReportDAO rDAO = new ReportDAO();
        ReportEntity rEntity = null;        
      
        int i = 1;
        String COI_WRT_NUM = request.getParameter("seq");
        String TMP_WRT_NUM = request.getParameter("seq2");
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT                               \n");        
        sbSQL.append("        PT_M_M.VIOL_DT,          		\n");   // 상호 및 행정처분 위반일자
        sbSQL.append("        PT_M_M.DISPO_CONT,   			\n");   // 상호 및 행정처분 처분내용 코드
        sbSQL.append("        PT_C.CODE_NAME,      			\n");   // 상호 및 행정처분 처분내용
        sbSQL.append("        PT_M_M.DISPO_CAUSE,  			\n");   // 상호 및 행정처분 처분사유
        sbSQL.append("        PT_M_A.AUDI_EXEC_ORG 			\n");   // 상호 및 행정처분 청문기관

        sbSQL.append("        FROM PT_R_COMPANY_MASTER PT_M	\n");
        
        sbSQL.append(" 		  LEFT JOIN (					\n");
    	sbSQL.append("	 		SELECT TMP_WRT_NUM,VIOL_DT, VIOL_CONT_CODE, DISPO_CONT, DISPO_CAUSE FROM PT_M_MASTER	\n"); 
    	sbSQL.append("			) PT_M_M ON RTRIM(PT_M.TMP_WRT_NUM) = PT_M_M.TMP_WRT_NUM								\n");
    	sbSQL.append("		  LEFT JOIN (																				\n");
    	sbSQL.append("			SELECT TMP_WRT_NUM, AUDI_EXEC_ORG FROM PT_M_AUDI_REPORT									\n");
    	sbSQL.append("			) PT_M_A ON RTRIM(PT_M.TMP_WRT_NUM) = PT_M_A.TMP_WRT_NUM								\n");
    	sbSQL.append("		  LEFT JOIN (																				\n");
    	sbSQL.append("			SELECT CODE, CODE_NAME FROM PT_COM_CODE WHERE P_CODE = 'PTMPRO'							\n");
    	sbSQL.append("			) PT_C ON PT_M_M.DISPO_CONT = PT_C.CODE													\n");
    	sbSQL.append("	WHERE PT_M.COI_WRT_NUM = ? AND PT_M.MANA_NUM = ?   												\n");
    	
    	rDAO.setValue(i++, COI_WRT_NUM);
    	rDAO.setValue(i++, TMP_WRT_NUM);
	            
	    rEntity = rDAO.select(sbSQL.toString());
	
	    /****** 검색조건 초기값 ***********/
	    request.setAttribute("fEntity", rEntity);  
    } 	
    
    
    /**
     * 등록기준신고 사항를 가져온다.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadRegBasicData(HttpServletRequest request, ComInfoParam pm) throws Exception {
        ReportDAO rDAO = new ReportDAO();
        ReportEntity rEntity = null;        
      
        int i = 1;

        StringBuffer sbSQL = new StringBuffer();
        String COI_WRT_NUM = request.getParameter("seq");
        String TMP_WRT_NUM = request.getParameter("seq2");
        
        sbSQL.append(" SELECT                               \n");        
        sbSQL.append("        PT_R_B.WRT_DT,          		\n");   // 등록일자
        sbSQL.append("        PT_R_B.OFFI_PART,   			\n");   // 처리과
        sbSQL.append("        PT_R_B.RECV_NUM,      		\n");   // 접수번호
        sbSQL.append("        PT_R_B.COI_WRT_NUM,  			\n");   // 등록번호
        sbSQL.append("        PT_R_B.NAME, 					\n");   // 회사명
        sbSQL.append("        PT_R_B.RECV_DT 				\n");   // 신고일

        sbSQL.append("        FROM PT_R_COMPANY_MASTER PT_M	\n");
        
        sbSQL.append(" 		  INNER JOIN (					\n");
    	sbSQL.append("	 		SELECT TMP_WRT_NUM, WRT_DT, OFFI_PART, RECV_NUM, COI_WRT_NUM, NAME, RECV_DT FROM PT_R_BASIC_STATEMENT	\n"); 
    	sbSQL.append("			) PT_R_B ON PT_R_B.TMP_WRT_NUM = PT_M.TMP_WRT_NUM 								\n");
    	sbSQL.append("	WHERE PT_M.COI_WRT_NUM = ? AND PT_M.MANA_NUM = ?   												\n");
    	
    	rDAO.setValue(i++, COI_WRT_NUM);
    	rDAO.setValue(i++, TMP_WRT_NUM);
	            
	    rEntity = rDAO.select(sbSQL.toString());
	
	    /****** 검색조건 초기값 ***********/
	    request.setAttribute("bEntity", rEntity);  
    } 	  
    
    
    /**
     * 임원 정보를 가져온다.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
//    public void loadRefferData(HttpServletRequest request, RegMgrParam pm) throws Exception {
//    	
//        ReportEntity rEntity = null;        
//        
//        ReportDAO rDAO = new ReportDAO();    
//        
//        int i = 1;
//        
//        StringBuffer sbSQL = new StringBuffer();        
//        sbSQL.append(" SELECT                               \n");        
//        sbSQL.append("        PT_R.NM_KOR,            		\n");   // 임원정보 성명
//        sbSQL.append("        PT_R.SSN1,           			\n");   // 임원정보 주민번호
//        
//        // 임원정보 직급구분
//        sbSQL.append("        DECODE(PT_R.POS_CLASS, 'P1', '대표이사',         \n");   
//        sbSQL.append("                               'P2', '감사',             \n");  
//        sbSQL.append("                               'P3', '이사',             \n");  
//        sbSQL.append("                               '대표자') AS POS_CLASS,   \n");
//        
//        sbSQL.append("        PT_R.ENT_DT,                	\n");   // 임원정보 입사일자
//        sbSQL.append("        PT_R.RET_DT                  	\n");   // 임원정보 퇴사일자        
//        
//        sbSQL.append("   FROM (PT_R_COMPANY_MASTER PT_M INNER JOIN PT_R_REFFER PT_R     \n");
//        sbSQL.append("     ON RTRIM(PT_M.TMP_WRT_NUM) = PT_R.TMP_WRT_NUM)           	\n");
//      
//        sbSQL.append("  WHERE PT_M.COI_WRT_NUM = ?      \n");   // 공사업등록번호 
//        sbSQL.append("    AND PT_M.MANA_NUM = ?         \n");   // 사업자번호
//        
//        rDAO.setValue(i++, user.getREG_NUM());
//        rDAO.setValue(i++, user.getCOM_NUM());        
//        
//        rEntity = rDAO.select(sbSQL.toString());
//
//        /****** 검색조건 초기값 ***********/
//        request.setAttribute("fEntity", rEntity);        
//    }
//    
    
    /**
     * 보유기술자 정보를 가져온다.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadEngineerData(HttpServletRequest request, ComInfoParam pm) throws Exception {
        
        ReportEntity rEntity = null;        
        ReportDAO rDAO = new ReportDAO();    
        
        int i = 1;
        
        String COI_WRT_NUM = request.getParameter("seq");
        String TMP_WRT_NUM = request.getParameter("seq2");
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT                               \n");        
        sbSQL.append("        PT_E.ENGINEER_NM,             \n");   // 보유기술자 성명
        sbSQL.append("        PT_E.ENGINEER_SSN1,           \n");   // 보유기술자 주민번호
        sbSQL.append("        PT_E.ENGINEER_GRADE,          \n");   // 보유기술자 등급
        sbSQL.append("        PT_C1.CODE_NAME AS ENGINEER_GRADE_NM,      \n");   // 보유기술자 등급 이름
        sbSQL.append("        PT_E.CARE_BOOK_ISSUE_NUM,     \n");   // 보유기술자 발급번호
        sbSQL.append("        PT_E.CARE_BOOK_VAL_START_DT,  \n");   // 보유기술자 발급일자
        sbSQL.append("        PT_E.EMPL_YMD,                \n");   // 보유기술자 입사일자
        sbSQL.append("        PT_E.RET_YMD                  \n");   // 보유기술자 퇴사일자        
        
        sbSQL.append("   FROM PT_R_COMPANY_MASTER PT_M     \n");      
        
        sbSQL.append("   INNER JOIN (                                               \n");   
        sbSQL.append("               SELECT PT_E_C.ENGINEER_NM,                     \n");   // 보유기술자 성명
        sbSQL.append("                      PT_E_C.ENGINEER_SSN1,                   \n");   // 보유기술자 주민번호
        sbSQL.append("                      PT_E_M.ENGINEER_GRADE,                  \n");   // 보유기술자 등급 
        sbSQL.append("                      PT_E_M.CARE_BOOK_ISSUE_NUM,             \n");   // 보유기술자 발급번호
        sbSQL.append("                      PT_E_M.CARE_BOOK_VAL_START_DT,          \n");   // 보유기술자 발급일자
        sbSQL.append("                      PT_E_C.EMPL_YMD,                        \n");   // 보유기술자 입사일자
        sbSQL.append("                      PT_E_C.RET_YMD,                         \n");   // 보유기술자 퇴사일자    
        sbSQL.append("                      PT_E_C.TMP_WRT_NUM                      \n");
        
        sbSQL.append("                 FROM PT_R_ENGINEER_CHANGE PT_E_C, PT_R_ENGINEER_MASTER PT_E_M    \n");  
        
        sbSQL.append("                WHERE PT_E_C.ENGINEER_SSN1 = PT_E_M.ENGINEER_SSN1     \n"); 
        sbSQL.append("                  AND PT_E_C.ENGINEER_SSN2 = PT_E_M.ENGINEER_SSN2     \n"); 
        sbSQL.append("                  AND PT_E_C.RET_YMD IS NULL                          \n"); 
        
        sbSQL.append("              ) PT_E ON PT_E.TMP_WRT_NUM = PT_M.TMP_WRT_NUM           \n");
        
        sbSQL.append("   LEFT JOIN (                                                \n");   
        sbSQL.append("              SELECT P_CODE, CODE, CODE_NAME                  \n");   
        sbSQL.append("                FROM PT_COM_CODE                              \n"); 
        sbSQL.append("               WHERE P_CODE = 'ENGCLASS'                      \n");   
        sbSQL.append("              ) PT_C1 ON PT_E.ENGINEER_GRADE = PT_C1.CODE     \n"); 
      
        sbSQL.append("  WHERE PT_M.COI_WRT_NUM = ?      \n");   // 공사업등록번호 
        sbSQL.append("    AND PT_M.MANA_NUM = ?         \n");   // 사업자번호  
        
        rDAO.setValue(i++, COI_WRT_NUM);
        rDAO.setValue(i++, TMP_WRT_NUM);        
        
        rEntity = rDAO.select(sbSQL.toString());

        /****** 검색조건 초기값 ***********/
        request.setAttribute("eEntity", rEntity);        
    }
    
    
    /**
     * 겸업관리 정보를 가져온다.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadSubsidiaryData(HttpServletRequest request, ComInfoParam pm) throws Exception {
        
        ReportEntity rEntity = null;        
        
        ReportDAO rDAO = new ReportDAO();    
        
        int i = 1;
        
        String COI_WRT_NUM = request.getParameter("seq");
        String TMP_WRT_NUM = request.getParameter("seq2");
        
        StringBuffer sbSQL = new StringBuffer();
        
        sbSQL.append(" SELECT                               \n");
        sbSQL.append("        PT_S.INS_DT,              	\n");   // 겸업관리 등록일자
        sbSQL.append("        PT_C1.CODE_NAME,              \n");   // 타 시설공사업 코드명
        sbSQL.append("        PT_S.SUB_CODE,             	\n");   // 타 시설공사업 코드
        sbSQL.append("        PT_S.SUB_WRT_NUM            	\n");   // 타 시설공사업 등록번호
               
        sbSQL.append("   FROM PT_R_COMPANY_MASTER PT_M INNER JOIN PT_R_SUBSIDIARY PT_S     \n");
        sbSQL.append("     ON RTRIM(PT_M.TMP_WRT_NUM) = PT_S.TMP_WRT_NUM          		   \n");   
               
        sbSQL.append("   LEFT JOIN (                                        \n");  
        sbSQL.append("              SELECT P_CODE, CODE, CODE_NAME          \n"); 
        sbSQL.append("                FROM PT_COM_CODE                      \n");   
        sbSQL.append("               WHERE P_CODE = 'SUBSIDIARY'            \n");   
        sbSQL.append("              ) PT_C1 ON PT_S.SUB_CODE = PT_C1.CODE   \n"); 
      
        sbSQL.append("  WHERE PT_M.COI_WRT_NUM = ?      \n");   // 공사업등록번호 
        sbSQL.append("    AND PT_M.MANA_NUM = ?         \n");   // 사업자번호  
        
        rDAO.setValue(i++, COI_WRT_NUM);
        rDAO.setValue(i++, TMP_WRT_NUM);        
        
        rEntity = rDAO.select(sbSQL.toString());

        /****** 검색조건 초기값 ***********/
        request.setAttribute("sEntity", rEntity);        
    }
        
    /**
     * 폼 체크 메소드
     * 
     * @param request
     * @param form
     * @return
     * @throws Exception
     */
    private ComInfoParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {

        ComInfoParam pm = (ComInfoParam)form;

        // 파라미터 디버깅
        KJFLog.debug(pm.toString(request));
        
        // 페이징 라인
        if (KJFUtil.isEmpty(pm.getRowPerPage())) {            
            pm.setRowPerPage(Config.props.get("ROW_PER_PAGE"));
        }

        return pm;
    }

}
