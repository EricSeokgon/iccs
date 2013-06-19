package sp.usebefore.cmd;

import java.util.Vector;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFDBUtil;
import kjf.util.KJFDate;
import kjf.util.KJFLog;
import kjf.util.KJFSelectOPS;
import kjf.util.KJFUtil;
import kjf.util.LoginException;
import kjf.cfg.Config;

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
public class UseBeforeMgrCmd implements KJFCommand {
    
	UserEnt user;
	
    public UseBeforeMgrCmd() {
        
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
        
        String UB_SERVER[]= null;
        String ub_temp = "";
//        ub_temp = Config.props.get("UB_ENDPOINT");
//        if (ub_temp.indexOf("/") >= 0){
//        	UB_SERVER = ub_temp.split("/");
//	    	for (int i =0 ; i < UB_SERVER.length; i++){
//	    		if (UB_SERVER[i].equals(user.getSIGUNGU_CODE()) && user.isUseUbSys()){
//	    			loadDate(request,pm);
//	    		}
//	    	}
//        } else {
    		//if (ub_temp.equals(user.getSIGUNGU_CODE())  && user.isUseUbSys() ){
    			loadDate(request,pm);
    		//}
//        }
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
    public void loadDate(HttpServletRequest request, UseBeforeParam pm) throws Exception {
        
    	ReportDAO rDAO 				= new ReportDAO();      
    	ReportEntity rEntity 		= null;
    	ReportEntity sEntity 		= null;
	   	String cmd					= request.getParameter("cmd");
    	String SIDO_CODE 			= user.getSIDO_CODE();
		String SIGUNGU_CODE 		= user.getSIGUNGU_CODE();
		
		String scINSP_SPOT_ADDR 		= request.getParameter("scINSP_SPOT_ADDR");
		String scRECV_ST			= request.getParameter("scRECV_ST");
		String scRECV_ET			= request.getParameter("scRECV_ET");
		String scAPPLPER_NM			= request.getParameter("scAPPLPER_NM");
		String scOPE_NAME			= request.getParameter("scOPE_NAME");
        
    	String selectSQL=
    		"	 SELECT '0' AS CHECKER, UU.CIV_RECV_NUM, INSP_SPOT_NM, INSP_SPOT_ADDR, RTRIM(INSP_SPOT_DETAILADDR) AS INSP_SPOT_DETAILADDR  , APPLPER_NM, OPE_NAME,UU.RECV_NUM,       \n" +
    		"	        COI_WRT_NUM, UU.RECV_DT, OFFI_NM,PROC_LIM,          \n" +
    		"   	    CASE UU.PROC_STE WHEN '1' THEN '신규입력' WHEN '2' THEN '처리중' WHEN '3' THEN '처리완료' ELSE ' ' END AS  PROC_STE,   \n"+
    		"   	    CASE UC.SUV_APPL WHEN '1' THEN '적합' WHEN '2' THEN '비적합' WHEN '3' THEN '감리대상' ELSE '미처리' END AS SUV_APPL   \n";
    	
    	String fromSQL = "  FROM PT_UB_USEBEFORE UU LEFT OUTER JOIN  PT_UB_CONSTRUCTION UC    \n" +
    					 "    ON  UU.SW_BEF_REPO_DELINUM = UC.SW_BEF_REPO_DELINUM     \n";
    	
    	String whereSQL = "WHERE 1 = 1    \n" +
    					  "	 AND UU.NAPPL_YN is null   \n" +
    					  "  AND UU.DEFI_YN = 'Y'    \n" + 
    					  "	 AND UU.SIDO_CODE = '"+SIDO_CODE+"'  \n"+
    					  "	 AND UU.SIGUNGU_CODE = '"+SIGUNGU_CODE+"'  \n";
		
   	  	
    	
    	if(!KJFUtil.isEmpty(scINSP_SPOT_ADDR)){
    		whereSQL += "AND UU.INSP_SPOT_ADDR LIKE '%"+scINSP_SPOT_ADDR+"%'";
    	}
    	
    	if(!KJFUtil.isEmpty(scRECV_ST) && !KJFUtil.isEmpty(scRECV_ET)){
    		whereSQL += "AND UU.RECV_DT BETWEEN '"+scRECV_ST.replaceAll("-","")+"' AND '"+scRECV_ET.replaceAll("-","")+"'  \n";
    	} else {
    		SimpleDateFormat sdf = new SimpleDateFormat ( "yyyyMMdd" );
    		Date toDay = new Date();
    		Date beforeDay = new Date();
    		beforeDay.setTime (toDay.getTime ( ) - ( (long) 1000 * 60 * 60 * (24 * 14) ) );
    		scRECV_ST = sdf.format(beforeDay.getTime());
    		scRECV_ET = sdf.format(toDay.getTime());
    		whereSQL += "AND UU.RECV_DT BETWEEN '"+scRECV_ST+"' AND '"+scRECV_ET+"'  \n";    		
    	}
    	
    	if(!KJFUtil.isEmpty(scAPPLPER_NM)){
    		whereSQL += "AND UU.APPLPER_NM LIKE '"+scAPPLPER_NM+"%'  \n";
    	}
    	
    	if(!KJFUtil.isEmpty(scOPE_NAME)){
    		whereSQL += "AND UU.OPE_NAME LIKE '"+scOPE_NAME+"%'  \n";
    	}
    	
    	
    	String orderSQL = "ORDER BY UU.RECV_DT ASC ";
    		   
    	
    	
    	String sidoSQL = " SELECT SIDO_NM, SIGUNGU_NM FROM PT_SIDO_CODE \n" +
					 "  WHERE AREA_CODE = '" + user.getSIGUNGU_CODE()+ "' \n" +
					 "  ORDER BY AREA_CODE";
    	
    	sEntity    = rDAO.select(sidoSQL);
    	
    	// 시군구의 서버 정보를 가져온다. (사용전검사)
    	loadSys(request,pm);
       
        /* ************************** 페이징 관련 START **************************/
        StringBuffer sbCntSQL = new StringBuffer();
        sbCntSQL.append(" SELECT COUNT(*)  CNT  \n");
        sbCntSQL.append(fromSQL.toString());
        sbCntSQL.append(whereSQL.toString());        
        
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

        rEntity    = rDAO.select(selectSQL+fromSQL+whereSQL+orderSQL, nowPage, rowPerPage);

        /****** 검색조건 초기값 ***********/
        request.setAttribute("pm", pm);
        request.setAttribute("sEntity", sEntity);
        request.setAttribute("ListEntity", rEntity);
        
    }
    /**
     * 서버 정보를 로드
     * @param HttpServletRequest
     * @return
     */
    private void loadSys(HttpServletRequest request, UseBeforeParam pm)throws Exception{
    	ReportDAO rDAO 				= new ReportDAO();      
    	ReportEntity rEntity 		= null;
    	String SIDO_CODE 			= user.getSIDO_CODE();
		String SIGUNGU_CODE 		= user.getSIGUNGU_CODE();
		
    	String selectSQL = " SELECT SIDO_CODE, SIGUNGU_CODE, COUNT(*) AS CNT ";
    	String fromSQL = " FROM PT_S_SYSVAR_MASTER";
    	String whereSQL  =" WHERE 1 = 1 ";
        		whereSQL += " AND SIDO_CODE = '" + SIDO_CODE + "'";
        		whereSQL += " AND SIGUNGU_CODE = '" + SIGUNGU_CODE + "'";
        		whereSQL += " group by sido_code, sigungu_code";
        rEntity = rDAO.select(selectSQL+fromSQL+whereSQL);
        request.setAttribute("rsEntity", rEntity);
    }

    /**
     * 검색조건 및 초기데이타 로드
     * @param HttpServletRequest
     * @return
     */
    private void loadCondition(HttpServletRequest request,UseBeforeParam pm)throws Exception{
    	// 시도-시군구정보
//    	String sql = 
//            " SELECT AREA_CODE, SIDO_NM " +
//			"   FROM PT_SIDO_CODE " +
//			"  WHERE AREA_CODE = " +
//			"  ORDER BY AREA_CODE";
//		Vector v_sc_type = KJFSelectOPS.getSel(sql);
//		request.setAttribute("v_sc_type", v_sc_type);
       
    }//end loadCondition
    
    /**
     * 폼 체크 메소드
     * 
     * @param request
     * @param form
     * @return
     * @throws Exception
     */
    private UseBeforeParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {

        UseBeforeParam pm = (UseBeforeParam)form;        // 페이징 라인
        
        pm.setScRecv_num(request.getParameter("scRECV_NUM"));
        
        if (KJFUtil.isEmpty(pm.getRowPerPage())) {
            pm.setRowPerPage("10");
        }
        
        // 파라미터 디버깅
        KJFLog.debug(pm.toString(request));
        
        return pm;
    }

}
