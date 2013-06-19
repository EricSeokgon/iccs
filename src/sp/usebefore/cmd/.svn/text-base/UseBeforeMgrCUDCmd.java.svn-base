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

import sp.uent.UserEnt;
import sp.usebefore.UseBeforeParam;
import org.apache.struts.action.ActionForm;

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
public class UseBeforeMgrCUDCmd implements KJFCommand {
    
	UserEnt user;
	private int smsre = 2;
	
    public UseBeforeMgrCUDCmd() {
        
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
        
        // 사용전 검사 등록  
        useBeforeUpDateExe(request,pm);
        // 구내통신선로설비
        WRKS_Exe(request,pm,"1");
        // 텔레비전공동시청안테나설비공사
        WRKS_Exe(request,pm,"2");
        // 이동통신구내선로설비공사
        WRKS_Exe(request,pm,"3");
        
        EQUIPMENT_Exe(request,pm);
        
        request.setAttribute("pm", pm);
        request.setAttribute("cmd", "UseBeforeMgrC");
        return "/usebefore/UseBeforeAction.do?cmd=UseBeforeMgrC&scRECV_NUM="+pm.getScRecv_num(); 
        //return  new UseBeforeMgrCCmd().execute(request, form);
        //return request.getParameter("cmd");
    }

    /**
     * 사용전 검사 입력
     * @param Dataset ds, int arg_row
     * @return
     */
    private void useBeforeUpDateExe(HttpServletRequest request,UseBeforeParam pm)throws Exception{
    	
			ReportDAO rDAO = new ReportDAO();      
	        StringBuffer sb = new StringBuffer();        
	        
    	   	String SIDO_CODE 	= user.getSIDO_CODE();
			String SIGUNGU_CODE = user.getSIGUNGU_CODE();
			String RECV_NUM 	= pm.getScRecv_num();
			String DELI_DT 		= KJFUtil.print(request.getParameter("DELI_DT"),"-");
			String INSP_DT 		= KJFUtil.print(request.getParameter("INSP_DT"),"-");			
			String WORK_ITEM_ETC = "";
	        String WRK001		= KJFUtil.print(request.getParameter("WRK001"),"");
	        String WRK002		= KJFUtil.print(request.getParameter("WRK002"),"");
	        String WRK003		= KJFUtil.print(request.getParameter("WRK003"),"");
	        String WRK004		= KJFUtil.print(request.getParameter("WRK004"),"");
	        
	        if ("".equals(WRK001) && "".equals(WRK002)  && "".equals(WRK003) && "".equals(WRK004)){
	        	WORK_ITEM_ETC = "";
	        } else {
	        	WORK_ITEM_ETC = "방송공동수신설비(";
	        	if (!"".equals(WRK001)){WRK001 =  WRK001 + ",";}
	        	if (!"".equals(WRK002)){WRK002 =  WRK002 + ",";}
	        	if (!"".equals(WRK003)){WRK003 =  WRK003 + ",";}
	        	if (!"".equals(WRK004)){WRK004 =  WRK004 + ",";}
	        	WORK_ITEM_ETC = WORK_ITEM_ETC + WRK001 + WRK002 + WRK003 + WRK004;
	        	WORK_ITEM_ETC = WORK_ITEM_ETC.substring(0,WORK_ITEM_ETC.length()-1) + ")";
	        }
	        
	        sb.append(" update PT_UB_USEBEFORE 		\n");
	        sb.append(" set PROC_STE = ?, \n");
	        sb.append("     USEBEFINSP_DELINUM = ?, \n");
	        sb.append(" 	CER_DELI_YN = ?, 		\n");	        
	        sb.append(" 	NAPPL_YN = ?, 			\n");
	        sb.append(" 	NAPPL_CAUSE = ?, 		\n");
	        sb.append(" 	DELI_DT = ?, 			\n");
	        sb.append(" 	WORK_ITEM_ETC = ?, 		\n");
	        sb.append(" 	UPD_DT = ?, 				\n");
	        sb.append(" 	INSP_DT = ? 				\n");	        
	        sb.append(" where 1=1 ");
	  	  	sb.append(" and SIDO_CODE = ?");	      
	  	  	sb.append(" and SIGUNGU_CODE = ?");
	  	  	sb.append(" and RECV_NUM = ?");
	        
	        int i = 1;
	        rDAO.setValue(i++, request.getParameter("PROC_STE"));
	        rDAO.setValue(i++, request.getParameter("USEBEFINSP_DELINUM"));
	        rDAO.setValue(i++, request.getParameter("CER_DELI_YN"));
	        rDAO.setValue(i++, request.getParameter("NAPPL_YN"));
	        rDAO.setValue(i++, request.getParameter("NAPPL_CAUSE"));	        
	        rDAO.setValue(i++, DELI_DT.replace("-", ""));
	        rDAO.setValue(i++, WORK_ITEM_ETC);
	        rDAO.setValue(i++, KJFDate.getCurTime("yyyy-MM-dd HH:mm:ss"));	  
	        rDAO.setValue(i++, INSP_DT.replace("-", ""));
	        rDAO.setValue(i++, SIDO_CODE);
	        rDAO.setValue(i++, SIGUNGU_CODE);
	        rDAO.setValue(i++, RECV_NUM);
	        
	        if (!"".equals(RECV_NUM)){
	        	rDAO.execute(sb.toString());
	        }
    }
    /**
     * 사용전검사  구내통신선로설비 등록
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    private void WRKS_Exe(HttpServletRequest request,UseBeforeParam pm,String key)throws Exception{
    	
    	ReportDAO    rDAO        = new ReportDAO(); 
        ReportEntity rEntity     = null;

    	String RECV_NUM 	= pm.getScRecv_num();
    	
    	if(KJFUtil.isEmpty(RECV_NUM)){
    		return;
    	}
	   	String SIDO_CODE 	= user.getSIDO_CODE();
		String SIGUNGU_CODE = user.getSIGUNGU_CODE();
    	String SEQ[] 		= null;
    	String ITEM_OUT[] 	= null;
    	String DETAIL_CONT[]= null;
    	SEQ  				= request.getParameterValues("SEQ_"+key);
    	ITEM_OUT  			= request.getParameterValues("ITEM_OUT_"+key);
    	DETAIL_CONT			= request.getParameterValues("DETAIL_CONT_"+key);
    	
		String SQL = 
			" SELECT COUNT(*) AS CNT FROM PT_UB_DETAIL  \n" +
			" WHERE RECV_NUM = '"+RECV_NUM+"'  \n" +
			"   AND SEQ = '"+SEQ[0]+"'  \n" +
			"   AND SIDO_CODE  = '"+SIDO_CODE+"'  \n" +
			"   AND SIGUNGU_CODE = '"+SIGUNGU_CODE+"'  \n" ;

		rEntity = rDAO.select(SQL);
		if(rEntity != null){

			if(KJFUtil.str2int(rEntity.getValue(0,"CNT")) == 0){
				SQL =		" insert into PT_UB_DETAIL(SRL,ITEM_OUT,SEQ,RECV_NUM,DETAIL_CONT,SIDO_CODE,SIGUNGU_CODE,WRT_ID,INS_DT) \n"+
							" values(?,?,?,?,?,?,?,?,?)";
				for (int i=0; i < SEQ.length; i++){
					int j = 1;
			    	String SRL = KJFDBUtil.getMaxID("SRL","PT_UB_DETAIL");			        
			        rDAO.setValue(j++, SRL);
			        rDAO.setValue(j++, ITEM_OUT[i]);
			        rDAO.setValue(j++, SEQ[i]);
			        rDAO.setValue(j++, RECV_NUM);
			        rDAO.setValue(j++, KJFUtil.print(DETAIL_CONT[i],""));
			        rDAO.setValue(j++, SIDO_CODE);
			        rDAO.setValue(j++, SIGUNGU_CODE);
			        rDAO.setValue(j++, user.getUSER_ID());	        
			        rDAO.setValue(j++, KJFDate.datetimeOnly(KJFDate.getCurTime()));
					rDAO.execute(SQL);
				}
			} else {
		    	SQL = 
		    				" update PT_UB_DETAIL  	\n" +
		    				" set ITEM_OUT = ?,		\n" +
		    				" 	  UPD_DT = ?,		\n" +
		    				"	  DETAIL_CONT = ?	\n" +		    				
		    				" WHERE RECV_NUM = ?  	\n" +
		    				"   AND SEQ = ?  		\n" +
		    				"   AND SIDO_CODE  = ?  \n" +
		    				"   AND SIGUNGU_CODE = ?\n" ;
		    	
				for (int i=0; i < SEQ.length; i++){
			        int u = 1;
			        rDAO.setValue(u++, ITEM_OUT[i]);
			        rDAO.setValue(u++, KJFDate.getCurTime("yyyy-MM-dd HH:mm:ss"));
			        rDAO.setValue(u++, KJFUtil.print(DETAIL_CONT[i],""));			        
			        rDAO.setValue(u++, RECV_NUM);
			        rDAO.setValue(u++, SEQ[i]);
			        rDAO.setValue(u++, SIDO_CODE);	        
			        rDAO.setValue(u++, SIGUNGU_CODE);
					
					rDAO.execute(SQL);
				}
			}
		}
    }
    /**
     * 사용전검사  기자재 등록
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    private void EQUIPMENT_Exe(HttpServletRequest request,UseBeforeParam pm)throws Exception{
    	
    	ReportDAO    rDAO        = new ReportDAO(); 
        ReportEntity rEntity     = null;

    	String RECV_NUM 	= pm.getScRecv_num();
    	
    	if(KJFUtil.isEmpty(RECV_NUM)){
    		return;
    	}

    	String SIDO_CODE 			= user.getSIDO_CODE();
		String SIGUNGU_CODE 		= user.getSIGUNGU_CODE();
		String CIV_RECV_NUM 		= request.getParameter("CIV_RECV_NUM");
		String TYPE_PROC_NO_CA 		= request.getParameter("TYPE_PROC_NO_CA");
		String TYPE_PROC_NM_CA 		= request.getParameter("TYPE_PROC_NM_CA");
		String FREQUENCY_SCOPE_CA 	= request.getParameter("FREQUENCY_SCOPE_CA");
        String WIRE_TYPE_CA 		= request.getParameter("WIRE_TYPE_CA");
        String TYPE_PROC_NO_MA 		= request.getParameter("TYPE_PROC_NO_MA");
        String TYPE_PROC_NM_MA 		= request.getParameter("TYPE_PROC_NM_MA");
        String FREQUENCY_SCOPE_MA 	= request.getParameter("FREQUENCY_SCOPE_MA");
        String WIRE_TYPE_MA 		= request.getParameter("WIRE_TYPE_MA");
        String TYPE_PROC_NO_SMA 	= request.getParameter("TYPE_PROC_NO_SMA");
        String TYPE_PROC_NM_SMA 	= request.getParameter("TYPE_PROC_NM_SMA");
        String FREQUENCY_SCOPE_SMA 	= request.getParameter("FREQUENCY_SCOPE_SMA");
        String WIRE_TYPE_SMA 		= request.getParameter("WIRE_TYPE_SMA");
        
		String SQL = 
			" SELECT COUNT(*) AS CNT FROM PT_UB_EQUIPMENT  \n" +
			" WHERE RECV_NUM = '"+RECV_NUM+"'  \n" +
			"   AND SIDO_CODE  = '"+SIDO_CODE+"'  \n" +
			"   AND SIGUNGU_CODE = '"+SIGUNGU_CODE+"'  \n" ;

		rEntity = rDAO.select(SQL);
		
		if(rEntity != null){
			  
			if(KJFUtil.str2int(rEntity.getValue(0,"CNT")) == 0){
				SQL =		" insert into PT_UB_EQUIPMENT(RECV_NUM,SIDO_CODE,SIGUNGU_CODE,CIV_RECV_NUM," +
							" 							  TYPE_PROC_NO_CA,TYPE_PROC_NM_CA,FREQUENCY_SCOPE_CA,WIRE_TYPE_CA," +
							" 							  TYPE_PROC_NO_MA,TYPE_PROC_NM_MA,FREQUENCY_SCOPE_MA,WIRE_TYPE_MA," +
							" 							  TYPE_PROC_NO_SMA,TYPE_PROC_NM_SMA,FREQUENCY_SCOPE_SMA,WIRE_TYPE_SMA," +
							"							  INS_DT) \n"+
							" values(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?)";
				
				int j = 1;		    				        
		        rDAO.setValue(j++, RECV_NUM);
		        rDAO.setValue(j++, SIDO_CODE);
		        rDAO.setValue(j++, SIGUNGU_CODE);
		        rDAO.setValue(j++, CIV_RECV_NUM);
		        rDAO.setValue(j++, TYPE_PROC_NO_CA);
		        rDAO.setValue(j++, TYPE_PROC_NM_CA);
		        rDAO.setValue(j++, FREQUENCY_SCOPE_CA);
		        rDAO.setValue(j++, WIRE_TYPE_CA);
		        rDAO.setValue(j++, TYPE_PROC_NO_MA);
		        rDAO.setValue(j++, TYPE_PROC_NM_MA);
		        rDAO.setValue(j++, FREQUENCY_SCOPE_MA);
		        rDAO.setValue(j++, WIRE_TYPE_MA);
		        rDAO.setValue(j++, TYPE_PROC_NO_SMA);
		        rDAO.setValue(j++, TYPE_PROC_NM_SMA);
		        rDAO.setValue(j++, FREQUENCY_SCOPE_SMA);
		        rDAO.setValue(j++, WIRE_TYPE_SMA);
		        rDAO.setValue(j++, KJFDate.datetimeOnly(KJFDate.getCurTime()));
				rDAO.execute(SQL);
				
			} else {
		    	SQL = 
		    				" update PT_UB_EQUIPMENT  		\n" +
		    				" set TYPE_PROC_NO_CA = ?,		\n" +
		    				" 	  TYPE_PROC_NM_CA = ?,		\n" +
		    				"	  FREQUENCY_SCOPE_CA = ?,	\n" +
		    				"	  WIRE_TYPE_CA = ?,			\n" +
		    				"     TYPE_PROC_NO_MA = ?,		\n" +
		    				" 	  TYPE_PROC_NM_MA = ?,		\n" +
		    				"	  FREQUENCY_SCOPE_MA = ?,	\n" +
		    				"	  WIRE_TYPE_MA = ?,			\n" +
		    				"     TYPE_PROC_NO_SMA = ?,		\n" +
		    				" 	  TYPE_PROC_NM_SMA = ?,		\n" +
		    				"	  FREQUENCY_SCOPE_SMA = ?,	\n" +
		    				"	  WIRE_TYPE_SMA = ?,		\n" +
		    				"	  UPD_DT = ?				\n" +
		    				" WHERE RECV_NUM = ?  	\n" +		    				
		    				"   AND SIDO_CODE  = ?  \n" +
		    				"   AND SIGUNGU_CODE = ?\n" ;
		    	
			        int u = 1;
			        rDAO.setValue(u++, TYPE_PROC_NO_CA);
			        rDAO.setValue(u++, TYPE_PROC_NM_CA);
			        rDAO.setValue(u++, FREQUENCY_SCOPE_CA);
			        rDAO.setValue(u++, WIRE_TYPE_CA);
			        rDAO.setValue(u++, TYPE_PROC_NO_MA);
			        rDAO.setValue(u++, TYPE_PROC_NM_MA);
			        rDAO.setValue(u++, FREQUENCY_SCOPE_MA);
			        rDAO.setValue(u++, WIRE_TYPE_MA);
			        rDAO.setValue(u++, TYPE_PROC_NO_SMA);
			        rDAO.setValue(u++, TYPE_PROC_NM_SMA);
			        rDAO.setValue(u++, FREQUENCY_SCOPE_SMA);
			        rDAO.setValue(u++, WIRE_TYPE_SMA);        
			        rDAO.setValue(u++, KJFDate.datetimeOnly(KJFDate.getCurTime()));
			        rDAO.setValue(u++, RECV_NUM);
			        rDAO.setValue(u++, SIDO_CODE);
			        rDAO.setValue(u++, SIGUNGU_CODE);
					
					rDAO.execute(SQL);
			}
		}
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

}
