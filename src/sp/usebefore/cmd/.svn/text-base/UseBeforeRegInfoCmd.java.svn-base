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

import sp.usebefore.UseBeforeParam;

import sp.uent.UserEnt;

/***************************************************************************
 * <p>Title       : PubWorkRegInfoCmd Class</p>
 * <p>Description : 공사업 등록정보 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : 공사업 등록정보 처리를 한다. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class UseBeforeRegInfoCmd implements KJFCommand {
    
	UserEnt user;
	
    public UseBeforeRegInfoCmd() {        
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
     * 공사업 등록 정보를 가져온다.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadRegData(HttpServletRequest request, UseBeforeParam pm) throws Exception {
    	
        ReportEntity rEntity = null;        
        
        ReportDAO rDAO = new ReportDAO();    
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();        
        String COI_WRT_NUM = KJFUtil.print(request.getParameter("str"),"");
      
		sbSQL.append(" SELECT                               \n");
		sbSQL.append("        PT_M.MANA_NUM,                \n");   // 사업자 등록번호
		sbSQL.append("        PT_H.COI_WRT_NUM,             \n");   // 등록번호
		sbSQL.append("        PT_H.REP_SSN1,             	\n");   // 사업자 주민번호
		sbSQL.append("        PT_H.RECV_NUM,                \n");   // 접수번호
		
		sbSQL.append("        PT_M.TMP_WRT_NUM,             \n");   // 등록번호		
		sbSQL.append("        PT_M.NAME,             		\n");   // 상호
		sbSQL.append("        ( SELECT COM_NUM FROM PT_R_REGIST_STATEMENT PT_RS WHERE PT_RS.MANA_NUM = PT_M.MANA_NUM) AS COM_NUM,\n");   // 법인번호		
		sbSQL.append("        PT_M.WRT_DT,					\n");   // 등록일
		sbSQL.append("        PT_M.REP_NM_KOR,				\n");   // 대표자명
		sbSQL.append("        PT_M.OFFICE_AREA,				\n");   // 사무실면적
		sbSQL.append("        PT_M.ADDR_POST_NUM,      		\n");   // 영업소재지 우편번호
		sbSQL.append("        PT_M.ADDR_ADDR,				\n");   // 영업소재지 주소
		sbSQL.append("        PT_M.ADDR_DETAIL_ADDR,   		\n");   // 영업소재지  상제주소
		sbSQL.append("        PT_M.ADDR_TEL_NUM,       		\n");   // 전화번호
		sbSQL.append("        PT_M.ADDR_FAX_NUM,			\n");   // 팩스번호
		sbSQL.append("        (SELECT ASSE_DT FROM (SELECT PT_C.ASSE_DT FROM PT_R_WORK_CAPABILITY PT_C WHERE PT_C.COI_WRT_NUM = ? ORDER BY ASSE_DT DESC) WHERE ROWNUM =1) AS  ASSE_DT,					\n");   // 시공능력일자
		sbSQL.append("        (SELECT ASSE_AOM FROM (SELECT PT_C.ASSE_AOM FROM PT_R_WORK_CAPABILITY PT_C WHERE PT_C.COI_WRT_NUM = ? ORDER BY ASSE_DT DESC) WHERE ROWNUM =1) AS  ASSE_AOM,				\n");   // 시공능력평가액
		sbSQL.append("        PT_M.PAY_CAP,					\n");   // 납입자본금
		sbSQL.append("        PT_M.REA_CAP					\n");   // 실질자본금
		
		sbSQL.append("   FROM (PT_R_COMPANY_MASTER PT_M                 \n");
	      
	    sbSQL.append("   LEFT JOIN PT_R_BASIC_CHANGE_HISTORY PT_H               \n");
	    sbSQL.append("        ON RTRIM(PT_M.TMP_WRT_NUM) = PT_H.TMP_WRT_NUM)    \n");
		sbSQL.append("	 WHERE PT_M.COI_WRT_NUM = ?					 \n");      

		rDAO.setValue(i++, COI_WRT_NUM);
        rDAO.setValue(i++, COI_WRT_NUM); 
        rDAO.setValue(i++, COI_WRT_NUM);    
        
        rEntity = rDAO.select(sbSQL.toString());

       	rEntity.setValue(0, "rWorkNum",     getWork_Num(rEntity.getValue(0, "TMP_WRT_NUM")));  // 가_등록번호                           

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
    public void loadRefferData(HttpServletRequest request, UseBeforeParam pm) throws Exception {
        ReportDAO rDAO = new ReportDAO();
        ReportEntity rEntity = null;        
      
        int i = 1;
        
        String COI_WRT_NUM = KJFUtil.print(request.getParameter("str"),"");
        
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
    	sbSQL.append("	WHERE PT_M.COI_WRT_NUM = ?    												\n");
    	
    	rDAO.setValue(i++, COI_WRT_NUM);
        
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
    public void loadRegBasicData(HttpServletRequest request, UseBeforeParam pm) throws Exception {
        ReportDAO rDAO = new ReportDAO();
        ReportEntity rEntity = null;        
      
        int i = 1;
        
        String COI_WRT_NUM = KJFUtil.print(request.getParameter("str"),"");
        
        StringBuffer sbSQL = new StringBuffer();
        
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
    	sbSQL.append("	WHERE PT_M.COI_WRT_NUM = ? 												\n");
    	
    	rDAO.setValue(i++, COI_WRT_NUM);
	            
	    rEntity = rDAO.select(sbSQL.toString());
	
	    /****** 검색조건 초기값 ***********/
	    request.setAttribute("bEntity", rEntity);  
    } 	  
    
 
    /**
     * 보유기술자 정보를 가져온다.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadEngineerData(HttpServletRequest request, UseBeforeParam pm) throws Exception {
        
        ReportEntity rEntity = null;        
        
        ReportDAO rDAO = new ReportDAO();    
        
        int i = 1;
        
        String COI_WRT_NUM = KJFUtil.print(request.getParameter("str"),"");
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT                               \n");        
        sbSQL.append("        PT_E.ENGINEER_NM,             \n");   // 보유기술자 성명
        sbSQL.append("        PT_E.ENGINEER_SSN1,           \n");   // 보유기술자 주민번호
        sbSQL.append("        PT_E.ENGINEER_GRADE,          \n");   // 보유기술자 등급
        sbSQL.append("        PT_C1.CODE_NAME AS ENGINEER_GRADE_NM,      \n");   // 보유기술자 등급 이름
        sbSQL.append("        PT_E.EMPL_YMD                \n");   // 보유기술자 입사일자
        
        sbSQL.append("   FROM PT_R_COMPANY_MASTER PT_M     \n");      
        
        sbSQL.append("   INNER JOIN (                                               \n");   
        sbSQL.append("               SELECT PT_E_M.NM_KOR AS ENGINEER_NM,                     \n");   // 보유기술자 성명
        sbSQL.append("                      PT_E_C.ENGINEER_SSN1,                   \n");   // 보유기술자 주민번호
        sbSQL.append("                      PT_E_M.ENGINEER_GRADE,                  \n");   // 보유기술자 등급 
        sbSQL.append("                      PT_E_C.EMPL_YMD,                        \n");   // 보유기술자 입사일자
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
       // sbSQL.append("    AND PT_M.MANA_NUM = ?         \n");   // 사업자번호  
        
        rDAO.setValue(i++, COI_WRT_NUM);
       // rDAO.setValue(i++, user.getCOM_NUM());        
        
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
    public void loadSubsidiaryData(HttpServletRequest request, UseBeforeParam pm) throws Exception {
        
        ReportEntity rEntity = null;        
        
        ReportDAO rDAO = new ReportDAO();    
        
        int i = 1;
        
        String COI_WRT_NUM = KJFUtil.print(request.getParameter("str"),"");
        
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
        //sbSQL.append("    AND PT_M.MANA_NUM = ?         \n");   // 사업자번호  
        
        rDAO.setValue(i++, COI_WRT_NUM);
        //rDAO.setValue(i++, user.getCOM_NUM());        
        
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
