package sp.usebefore.cmd;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFDBUtil;
import kjf.util.KJFDate;
import kjf.util.KJFLog;
import kjf.util.KJFUtil;
import kjf.util.LoginException;

import org.apache.struts.action.ActionForm;

import sp.uent.UserEnt;
import sp.usebefore.UseBeforeParam;

/***************************************************************************
 * <p>Title       : UseBeforeCUDCmd Class</p>
 * <p>Description : 사용전검사 등록, 수정, 삭제 처리 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : 사용전검사 등록, 수정, 삭제 처리를 한다. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class UseBeforeMgrCCmd_201002 implements KJFCommand {
    
	UserEnt user;
	
    public UseBeforeMgrCCmd_201002() {
        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {        
        
        String cmd = request.getParameter("cmd");
        user = (UserEnt)request.getSession().getAttribute("user");
        
        // 로그인 유무 체크
        if (KJFUtil.isEmpty(user)) {
            throw new LoginException(request, "회원용 페이지 로그인 체크", "../member/login.do");
        }
        
        // 검색조건 설정및 체크
        UseBeforeParam pm = checkPm(request, form);

        loadCondition(request,pm);

        // 사용전검사 조회
        loadList(request,pm);
        // 사용전검사 메모 조회
        loadMemoList(request,pm);
        
        // 사용전검사 기자재 조회
        loadEquipData(request,pm);
        
        // 사용전검사 현장검사관리  
    	loadUserBeforeExe(request,pm,"1");
    	loadUserBeforeExe(request,pm,"2");
    	loadUserBeforeExe(request,pm,"3");

    	request.setAttribute("pm", pm);
        return request.getParameter("cmd");
    }
    
    /**
     * 사용전검사 조회
     * 
     * @param request
     * @param form
     * @throws Exception
     */
	private void loadList(HttpServletRequest request, UseBeforeParam pm)throws Exception{
		
        ReportDAO rDAO = new ReportDAO();      
        ReportEntity rEntity = null;
        
        //String scRECV_NUM 		= request.getParameter("scRECV_NUM");    
        String scRECV_NUM		= pm.getScRecv_num();
        String SIDO_CODE 		= user.getSIDO_CODE();		// request.getParameter("SIDO_CODE");
    	String SIGUNGU_CODE 	= user.getSIGUNGU_CODE(); 	// request.getParameter("SIGUNGU_CODE");

        
    	String selectSQL=
    		"	SELECT UU.*, CC.CODE_NAME, SM.SERVER_ADDR, SM.SEND_SYS_ID,SM.RECV_SYS_ID, SM.SERVER_YN" +
    		"   FROM PT_UB_USEBEFORE UU LEFT JOIN PT_COM_CODE CC ON CC.CODE = UU.USE AND CC.P_CODE = 'BLDDIV' " +
    		"   LEFT JOIN PT_S_SYSVAR_MASTER SM ON SM.SIGUNGU_CODE = UU.SIGUNGU_CODE \n";
    	
    	String whereSQL = "WHERE UU.RECV_NUM = '"+scRECV_NUM+"'   \n" +
    					   " AND UU.SIDO_CODE = '"+SIDO_CODE+"'   \n" +
    					   " AND UU.SIGUNGU_CODE = '"+SIGUNGU_CODE+"'  ";
    	
    	rEntity    = rDAO.select(selectSQL+whereSQL);  
	    
	    /****** 검색조건 초기값 ***********/
	    request.setAttribute("pm", pm);       
	    request.setAttribute("rEntity", rEntity);
	}
	   /**
     * 사용전검사 현장검사관리 조회 
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadUserBeforeExe(HttpServletRequest request, UseBeforeParam pm, String key ) throws Exception {
                
        ReportDAO rDAO = new ReportDAO();      
        ReportEntity rEntTable1 = null;
        ReportEntity rEntTable2 = null;
        ReportEntity rEntTable3 = null;
        ReportEntity rEntTableCnt1 = null;
        ReportEntity rEntTableCnt2 = null;
        ReportEntity rEntTableCnt3 = null;      
        
        //String scRECV_NUM = request.getParameter("scRECV_NUM");
        String scRECV_NUM		=  pm.getScRecv_num();
        StringBuffer sbSQL = new StringBuffer();   
        StringBuffer sbSQLCnt = new StringBuffer(); 
        
        sbSQL.append(" SELECT UDM.SEQ, \n");
        
        if ("2".equals(key)){
        	sbSQL.append(" MIDCLAS||' '|| SMACLAS AS  CLASS_NAME, \n");
        } 
        sbSQL.append(" 	CASE WHEN INSTR(MIDCLAS,'-') > 0 THEN RTRIM(REPLACE(MIDCLAS,'-','')) \n");
        sbSQL.append("									 ELSE SMACLAS END CLASS_NAME1, \n");
        sbSQL.append("  CASE WHEN MIDCLAS IS NULL THEN '' ELSE SMACLAS END CLASS_NAME2, \n");
        sbSQL.append("  SRL, BAS, UDM.CONT, \n"); 
        sbSQL.append("  REPLACE(NVL(UCD.DETAIL_CONT,UDM.DETAIL_CONT),'   ','') AS DETAIL_CONT ,UCD.ITEM_OUT \n");
        sbSQL.append(" FROM PT_UB_DETAIL_MASTER UDM , PT_UB_DETAIL UCD    \n");
        
        
        sbSQLCnt.append(" SELECT CLASS_NAME1,COUNT(CLASS_NAME1) AS CNT FROM ( \n");
        sbSQLCnt.append(" 	SELECT CASE WHEN INSTR(MIDCLAS,'-') > 0 THEN RTRIM(REPLACE(MIDCLAS,'-','')) \n");
        sbSQLCnt.append("									 ELSE SMACLAS END CLASS_NAME1 \n");
        sbSQLCnt.append("   FROM PT_UB_DETAIL_MASTER UDM , PT_UB_DETAIL UCD    \n");
        
    	
    	if ("1".equals(key)){
    		sbSQL.append("    WHERE UDM.ITEM = '2' AND SUBSTR(LARCLAS,0,1) = '1'	\n");
    		sbSQLCnt.append("    WHERE UDM.ITEM = '2' AND SUBSTR(LARCLAS,0,1) = '1'	\n");
    	} else if ("2".equals(key)){
    		sbSQL.append("    WHERE UDM.ITEM = '2' AND SUBSTR(LARCLAS,0,1) = '2'	\n");
    		sbSQLCnt.append("    WHERE UDM.ITEM = '2' AND SUBSTR(LARCLAS,0,1) = '2'	\n");
    	} else if ("3".equals(key)){
    		sbSQL.append("    WHERE UDM.ITEM = '2' AND SUBSTR(LARCLAS,0,1) = '3'	\n");
    		sbSQLCnt.append("    WHERE UDM.ITEM = '2' AND SUBSTR(LARCLAS,0,1) = '3'	\n");
    	}
        
        if(!KJFUtil.isEmpty(scRECV_NUM)){
        	sbSQL.append(" AND UCD.RECV_NUM(+) = '"+scRECV_NUM+"'  \n");
        	sbSQLCnt.append(" AND UCD.RECV_NUM(+) = '"+scRECV_NUM+"'  \n");
        } else {
        	sbSQL.append(" AND UCD.RECV_NUM(+) = '0'	\n");
        	sbSQLCnt.append(" AND UCD.RECV_NUM(+) = '0'	\n");
        }     
        
       	sbSQL.append("  AND UDM.SEQ = UCD.SEQ(+)	\n");
       	sbSQL.append("  ORDER BY TO_NUMBER(ORDER_SEQ) ASC \n");
  
       	sbSQLCnt.append("  AND UDM.SEQ = UCD.SEQ(+)	\n");
       	sbSQLCnt.append("  ORDER BY TO_NUMBER(ORDER_SEQ) ASC ) GROUP BY CLASS_NAME1 \n");
       	
       	request.setAttribute("pm", pm);
       	
       	if ("1".equals(key)){
       		rEntTable1 = rDAO.select(sbSQL.toString());
       		request.setAttribute("rEntTable1", rEntTable1);
       		rEntTableCnt1 = rDAO.select(sbSQLCnt.toString());
       		request.setAttribute("rEntTableCnt1", rEntTableCnt1);
       	} else if ("2".equals(key)){
       		rEntTable2 = rDAO.select(sbSQL.toString());
       		request.setAttribute("rEntTable2", rEntTable2);
       		rEntTableCnt2 = rDAO.select(sbSQLCnt.toString());
       		request.setAttribute("rEntTableCnt2", rEntTableCnt2);
       	} else if ("3".equals(key)){
       		rEntTable3 = rDAO.select(sbSQL.toString());
       		request.setAttribute("rEntTable3", rEntTable3);
       		rEntTableCnt3 = rDAO.select(sbSQLCnt.toString());
       		request.setAttribute("rEntTableCnt3", rEntTableCnt3);
       	}
    }	
	
    /**
     * 사용전검사 현장검사관리 조회
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadUserBeforeExe_before(HttpServletRequest request, UseBeforeParam pm, String key ) throws Exception {
                
        ReportDAO rDAO = new ReportDAO();      
        ReportEntity rEntTable1 = null;
        ReportEntity rEntTable2 = null;
        ReportEntity rEntTable3 = null;
        
        //String scRECV_NUM = request.getParameter("scRECV_NUM");
        String scRECV_NUM		=  pm.getScRecv_num();
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT UDM.SEQ, MIDCLAS||' '|| SMACLAS AS  CLASS_NAME, SRL, BAS, \n");
        sbSQL.append("  UDM.CONT ,REPLACE(NVL(UCD.DETAIL_CONT,UDM.DETAIL_CONT),'   ','') AS DETAIL_CONT ,UCD.ITEM_OUT \n");
        sbSQL.append(" FROM PT_UB_DETAIL_MASTER UDM , PT_UB_DETAIL UCD    \n");
        
    	if ("1".equals(key)){
    		sbSQL.append("    WHERE UDM.ITEM = '2' AND SUBSTR(LARCLAS,0,1) = '1'	\n");
    	} else if ("2".equals(key)){
    		sbSQL.append("    WHERE UDM.ITEM = '2' AND SUBSTR(LARCLAS,0,1) = '2'	\n");
    	} else if ("3".equals(key)){
    		sbSQL.append("    WHERE UDM.ITEM = '2' AND SUBSTR(LARCLAS,0,1) = '3'	\n");        		
    	}
        
        if(!KJFUtil.isEmpty(scRECV_NUM)){
        	sbSQL.append(" AND UCD.RECV_NUM(+) = '"+scRECV_NUM+"'  \n");
        } else {
        		sbSQL.append(" AND UCD.RECV_NUM(+) = '0'	\n");
        }     
        
       	sbSQL.append("  AND UDM.SEQ = UCD.SEQ(+)	\n");
       	sbSQL.append("  ORDER BY TO_NUMBER(ORDER_SEQ) ASC \n");
       	
       	request.setAttribute("pm", pm);
       	
       	if ("1".equals(key)){
       		rEntTable1 = rDAO.select(sbSQL.toString());
       		request.setAttribute("rEntTable1", rEntTable1);
       	} else if ("2".equals(key)){
       		rEntTable2 = rDAO.select(sbSQL.toString());
       		request.setAttribute("rEntTable2", rEntTable2);
       	} else if ("3".equals(key)){
       		rEntTable3 = rDAO.select(sbSQL.toString());
       		request.setAttribute("rEntTable3", rEntTable3);
       	}
    }
   
    /**
     * 사용전검사 메모 조회
     * 
     * @param request
     * @param form
     * @throws Exception
     */
	private void loadMemoList(HttpServletRequest request, UseBeforeParam pm)throws Exception{
		
        ReportDAO rDAO = new ReportDAO();      
        ReportEntity rEntity = null;
        
        //String scRECV_NUM 		=  request.getParameter("scRECV_NUM");
        String scRECV_NUM		=  pm.getScRecv_num();
        String SIDO_CODE 		=  user.getSIDO_CODE();
    	String SIGUNGU_CODE 	=  user.getSIGUNGU_CODE();
        
		String selectSQL=
    		"	SELECT SEQ, MEMO_DT, WRT_TIME, MEMO_CONT, WRT_NAME, INS_DT FROM  PT_UB_MEMO \n";
    	
    	String whereSQL = "WHERE RECV_NUM = '"+scRECV_NUM+"'   \n" +
    					   " AND SIDO_CODE = '"+SIDO_CODE+"'   \n" +
    					   " AND SIGUNGU_CODE = '"+SIGUNGU_CODE+"'  ";
    	
    	rEntity    = rDAO.select(selectSQL+whereSQL);  
	    
	    /****** 검색조건 초기값 ***********/
	    request.setAttribute("pm", pm);       
	    request.setAttribute("mEntity", rEntity);
	}  
	
	/**
     * 사용전검사 기자재 조회
     * 
     * @param request
     * @param form
     * @throws Exception
     */
	  private void loadEquipData(HttpServletRequest request, UseBeforeParam pm) throws Exception {
	        ReportDAO rDAO = new ReportDAO();
	        
	        ReportEntity eEntity = null;
	        
	        String RECV_NUM = pm.getScRecv_num();
	        String SIDO_CODE = user.getSIDO_CODE();
	        String SIGUNGU_CODE = user.getSIGUNGU_CODE();
	        
	        StringBuffer sbSQL = new StringBuffer();        
	        sbSQL.append(" SELECT                   \n");
	        sbSQL.append("    	RECV_NUM, SIDO_CODE, SIGUNGU_CODE ,CIV_RECV_NUM ,	\n");		
	        sbSQL.append(" 		TYPE_PROC_NO_CA,TYPE_PROC_NM_CA,FREQUENCY_SCOPE_CA,WIRE_TYPE_CA, \n");
	        sbSQL.append(" 		TYPE_PROC_NO_MA,TYPE_PROC_NM_MA,FREQUENCY_SCOPE_MA,WIRE_TYPE_MA, \n");			
	        sbSQL.append(" 		TYPE_PROC_NO_SMA ,TYPE_PROC_NM_SMA,FREQUENCY_SCOPE_SMA, WIRE_TYPE_SMA,INS_DT,'U' AS EQU_CMD \n");
	        sbSQL.append(" FROM PT_UB_EQUIPMENT   \n");
	        sbSQL.append(" WHERE RECV_NUM = ? \n");
	        sbSQL.append("  	 AND SIDO_CODE = ? \n");
	        sbSQL.append(" 		 AND SIGUNGU_CODE = ? \n");
	        
	        rDAO.setValue(1, RECV_NUM);
	        rDAO.setValue(2, SIDO_CODE);
	        rDAO.setValue(3, SIGUNGU_CODE);
	        
	        eEntity = rDAO.select(sbSQL.toString());
	        
	        request.setAttribute("eEntity", eEntity);
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
        
        pm.setScRecv_num(request.getParameter("scRECV_NUM"));
        // 파라미터 디버깅
        KJFLog.debug(pm.toString(request));
        return pm;
    }

    /**
     * 검색조건 및 초기데이타 로드
     * @param HttpServletRequest
     * @return
     */
    private void loadCondition(HttpServletRequest request,UseBeforeParam pm)throws Exception{
        /*********** 검사결과 목록 생성 START ***************/
        String[][] scFIELD = {{"해당없음","해당없음"},{"적합","적합"},{"부적합","부적합"}};
        request.setAttribute("v_scITEM_OUT", KJFUtil.makeSelect(scFIELD));
        /*********** 조회조건 목록 생성 END ***************/
        
        /*********** 처리상태 목록 생성 START ***************/
        String[][] scPROC_STE = {{"1","신규등록"},{"2","처리중"},{"3","처리완료"}};
        request.setAttribute("v_scPROC_STE", KJFUtil.makeSelect(scPROC_STE));
        /*********** 조회조건 목록 생성 END ***************/ 
        
       
    }//end loadCondition	
}
