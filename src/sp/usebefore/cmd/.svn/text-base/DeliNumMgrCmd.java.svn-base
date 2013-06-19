/**
 * 파일명   : DeliNumMgrCmd.java
 * 설명     : 공통코드관리 cmd
 * 이력사항
 * CH00     : 2009/12/02 전원배 최초작성
 */

package sp.usebefore.cmd;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.cfg.Config;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;


import kjf.util.KJFDBUtil;
import kjf.util.KJFUtil;
import kjf.util.KJFDate;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import sp.uent.UserEnt;
import sp.usebefore.UseBeforeParam;


public class DeliNumMgrCmd implements KJFCommand {
	
	UserEnt user;
    
    public DeliNumMgrCmd() {

    }

    public String execute(HttpServletRequest request, ActionForm form) throws Exception {
    	
        ReportDAO    rDAO        = new ReportDAO();
        ReportEntity cEntity     = null;
        ReportEntity oEntity     = null;
        ReportEntity rEntity     = null;
        
    	UserEnt user = (UserEnt)request.getSession().getAttribute("user");
    	
    	String cmd = request.getParameter("cmd");
    	
    	if (KJFUtil.isEmpty(user)){
    		request.setAttribute("DELI_NUM","0");
    	}  else {
	        String SIDO_CODE = user.getSIDO_CODE();
	        String SIGUNGU_CODE = user.getSIGUNGU_CODE();
	        String DELI_ITEM  = request.getParameter("DELI_ITEM");
	        String year = KJFUtil.print(request.getParameter("DELI_DT"),KJFDate.getCurTime("yyyy"));
	    	
	        String cntSQL=
	    		"	SELECT COUNT(*) AS CNT FROM  PT_UB_DELI_MASTER WHERE SIDO_CODE = '"+SIDO_CODE+"' AND SIGUNGU_CODE = '"+SIGUNGU_CODE+"' AND YEAR = '"+year+"' AND DELI_ITEM = '"+DELI_ITEM+"'   \n";
	    	
	    	cEntity    = rDAO.select(cntSQL);   
	    	
	    	if(cEntity.getValue(0,"CNT").equals("0")){
	    		String codeSQL = " SELECT SUBSTR(SIDO_NM,0,2) AS SIDO_NM , SIGUNGU_NM FROM PT_SIDO_CODE WHERE AREA_CODE = '"+SIGUNGU_CODE+"' ";
	    		oEntity = rDAO.select(codeSQL);   
	    		String DELI_SIGUNGU = oEntity.getValue(0,"SIGUNGU_NM");
	    		
	    		if("본청".equals(DELI_SIGUNGU)){
	    			DELI_SIGUNGU = oEntity.getValue(0,"SIDO_NM");
	    		}
	    		
	    		String insertSQL = "INSERT INTO PT_UB_DELI_MASTER (SIDO_CODE,SIGUNGU_CODE,YEAR,DELI_NUM,DELI_ITEM,DELI_SIGUNGU) VALUES   " +
	    				"('"+SIDO_CODE+"','"+SIGUNGU_CODE+"','"+year+"','00000','"+DELI_ITEM+"','"+DELI_SIGUNGU+"')";
	    		rDAO.execute(insertSQL);
	    		
	    	}
	    	
	    	String selectSQL = 
	    			" SELECT DELI_SIGUNGU||'-'||YEAR||'-'||LPAD(TO_NUMBER(DELI_NUM)+1,5,'0') AS DELI_NUM   \n" +
	    			"   FROM PT_UB_DELI_MASTER   \n" +
	    			"  WHERE SIDO_CODE = '"+SIDO_CODE+"' \n" +
	    			"    AND SIGUNGU_CODE = '"+SIGUNGU_CODE+"' \n" +
	    			"	 AND YEAR = '"+year+"' \n" +
	    			"	 AND DELI_ITEM = '"+DELI_ITEM+"'  ";
	    	
	    	rEntity = rDAO.select(selectSQL);  
	    	
	    	if (rEntity.getRowCnt() >= 0){
	    		updateExe(request,rEntity.getValue(0,"DELI_NUM" ),SIDO_CODE,SIGUNGU_CODE);
	    	}
	    	
	    	String updateSQL = "UPDATE PT_UB_DELI_MASTER SET DELI_NUM = LPAD(TO_NUMBER(DELI_NUM)+1,5,'0')  \n"+
								"  WHERE SIDO_CODE = '"+SIDO_CODE+"' \n" +
								"    AND SIGUNGU_CODE = '"+SIGUNGU_CODE+"' \n" +
								"	 AND YEAR = '"+year+"' \n" +
								"	 AND DELI_ITEM = '"+DELI_ITEM+"'  ";
	    	
	    	rDAO.execute(updateSQL);
	    	
	    	request.setAttribute("DELI_NUM", KJFUtil.print(rEntity.getValue(0, "DELI_NUM"),"0"));
    	}
        return cmd;
    }
    
    /**
     * 사용전검사 필증발급 번호 저장
     * 
     * @param CT_ID
     * @param BOARD_SEQ
     * @param  formFile
     * @throws Exception
     */
    private void updateExe(HttpServletRequest request, String USEBEFINSP_DELINUM,String SIDO_CODE, String SIGUNGU_CODE) throws Exception {

        ReportDAO rDAO = new ReportDAO();

		String scRECV_NUM		  = KJFUtil.print(request.getParameter("scRECV_NUM"));
	    
        StringBuffer sbSQL = new StringBuffer();         
        sbSQL.append(" UPDATE PT_UB_USEBEFORE  \n");
        sbSQL.append("        SET USEBEFINSP_DELINUM = ? , CER_DELI_YN ='1'           \n");
        sbSQL.append(" WHERE  RECV_NUM = ? AND SIDO_CODE= ? AND SIGUNGU_CODE = ?          \n");
       
        int i = 0;
        rDAO.setValue(++i, USEBEFINSP_DELINUM);
        rDAO.setValue(++i, scRECV_NUM);
        rDAO.setValue(++i, SIDO_CODE);
        rDAO.setValue(++i, SIGUNGU_CODE);
        
        rDAO.execute(sbSQL.toString());
    }

}