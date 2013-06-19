package sp.pda.cmd;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.cfg.Config;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFDBUtil;
import kjf.util.KJFDate;
import kjf.util.KJFLog;
import kjf.util.KJFUtil;

import org.apache.struts.action.ActionForm;

import sp.pda.PdaParam;
/***************************************************************************
 * <p>Title       : MyProgressStateCmd Class</p>
 * <p>Description : 나의 민원진행상태 처리 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : 나의 민원진행상태 정보 처리를 한다. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class PdaApprMgrCUDCmd implements KJFCommand {
    
    public PdaApprMgrCUDCmd() {
        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {        
        
        
        // 검색조건 설정및 체크
        PdaParam pm = checkPm(request, form);
        
        // 
        loadData(request, pm);
        
        return request.getParameter("cmd");
    }
    
    
    /**
     * 게시물 리스트를 가져온다.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadData(HttpServletRequest request, PdaParam pm) throws Exception {
           
    	ReportDAO    rDAO        = new ReportDAO(); 
        ReportEntity rEntity     = null;
        
        String appr = KJFUtil.print(request.getParameter("appr"));
        String RECV_NUM = "";        
        String NAPPL_YN = "";  
        String SIDO = "";
        String SIGUNGU = "";
        String INSP_NUM = "";
        String data = KJFDate.getCurTime("yyyyMMdd");
        
        String result= "";
                
        //System.out.println("appr2 : "+KJFUtil.rplc(appr.trim(),"|","#"));
        String[] appr2 = KJFUtil.rplc(appr.trim(),"|","#").split("#");
        
        StringBuilder ExSQL = new StringBuilder();
        ExSQL.append(" SELECT COUNT(*) AS CNT FROM PT_UB_USEBEFORE WHERE SIDO_CODE = '"+appr2[1]+"' AND SIGUNGU_CODE='"+appr2[2]+"' AND RECV_NUM='"+appr2[5]+"' AND USEBEFINSP_DELINUM is not null ");
        rEntity = rDAO.select(ExSQL.toString());           
        String CNT = rEntity.getValue(0,"CNT");
        
        RECV_NUM = appr2[5];      
        
        String YN = "해당없음";
      
	        for(int k = 7;k <= appr2.length-1; k++){
	        	if(!"END".equals(appr2[k])){	        		
	        		if("9".equals(appr2[6])){  YN = "1";
	     		    }else if("2".equals(appr2[6])){  YN = "2";}	        		
	        		SchData(appr2[1],appr2[2],appr2[3],appr2[5],YN,appr2[k],appr2[k+1],appr2[k+2]);
	        		//System.out.println("appr2 : "+appr2[1]+"|"+appr2[2]+"|"+appr2[3]+"|"+appr2[4]+"|"+appr2[5]+"|"+appr2[k]+"|"+appr2[k+1]+"|"+appr2[k+2]);
	        		SIDO = appr2[1];   
	        		SIGUNGU = appr2[2];   
	        		RECV_NUM = appr2[5]; 
	        		NAPPL_YN = appr2[6];	 	    	   
	        	}  	k = k+2;
	        }
	       
	       
	       if("O".equals(NAPPL_YN)){  NAPPL_YN = "1";
	   		}else if("X".equals(NAPPL_YN)){	NAPPL_YN = "2";
	   		}else if("N".equals(NAPPL_YN)){	NAPPL_YN = "3";	}
	       
	       INSP_NUM = RECV_NUM+"-"+data; 
	       
	       if("0".equals(CNT)){
	        	result= "";
	        
		       StringBuilder insertSQL = new StringBuilder();
		       insertSQL.append(" UPDATE PT_UB_USEBEFORE SET NAPPL_YN = '"+YN+"', INSP_ITEM = 'P', USEBEFINSP_DELINUM = '"+DeliData(SIDO,SIGUNGU)+"', INSP_NUM = '"+INSP_NUM+"'  WHERE RECV_NUM = '"+RECV_NUM+"' AND SIDO_CODE = '"+appr2[1]+"' AND SIGUNGU_CODE='"+appr2[2]+"' ");
		       rDAO.execute(insertSQL.toString());
		     
		       StringBuilder selectSQL = new StringBuilder();
		       selectSQL.append(" SELECT INSP_NUM , USEBEFINSP_DELINUM, APPLPER_TELNUM  FROM PT_UB_USEBEFORE WHERE RECV_NUM = '"+RECV_NUM+"' AND SIDO_CODE = '"+appr2[1]+"' AND SIGUNGU_CODE='"+appr2[2]+"' ");
		       rEntity = rDAO.select(selectSQL.toString());
		      
		       request.setAttribute("rEntity", rEntity);
	       }else{
	    	   
	    	   StringBuilder upSQL = new StringBuilder();
	    	   upSQL.append(" UPDATE PT_UB_USEBEFORE SET INSP_ITEM = 'P' WHERE RECV_NUM = '"+RECV_NUM+"' AND SIDO_CODE = '"+appr2[1]+"' AND SIGUNGU_CODE='"+appr2[2]+"' ");
		       rDAO.execute(upSQL.toString()); 
		       
	    	   result= "APPRERR|";
	       }
      
        request.getSession().setAttribute("result", result);
    }
    
    
    public void SchData(String SIDO, String SIGUNGU, String WRT_ID, String RECV_NUM, String NAPPL_YN, String SEQ, String ITEM_OUT, String DETAIL_CONT) throws Exception {
    	
    	ReportDAO    rDAO        = new ReportDAO(); 
        ReportEntity rEntity     = null;
        SEQ = ""+KJFUtil.str2int(SEQ);
    	StringBuilder selectSQL = new StringBuilder();
    	selectSQL.append(" SELECT COUNT(*) AS CNT FROM PT_UB_DETAIL WHERE RECV_NUM = '"+RECV_NUM+"'  AND SEQ = '"+SEQ+"' AND SIDO_CODE  = '"+SIDO+"' AND SIGUNGU_CODE = '"+SIGUNGU+"'");
    	
    	
    	rEntity = rDAO.select(selectSQL.toString());
    	
    	if("O".equals(ITEM_OUT)){ 
    		ITEM_OUT = "적합";
    	}else if("X".equals(ITEM_OUT)){	
    		ITEM_OUT = "부적합";
    	}else if("N".equals(ITEM_OUT)){	
    		ITEM_OUT = "해당업음";  
    	}
    	
        if(rEntity != null){
        	if(KJFUtil.str2int(rEntity.getValue(0,"CNT")) == 0){  
        		InsertData(SIDO,SIGUNGU,RECV_NUM,NAPPL_YN,WRT_ID,SEQ,ITEM_OUT,DETAIL_CONT);
	        }else{ 	
	        	UpData(SIDO,SIGUNGU,RECV_NUM,NAPPL_YN,WRT_ID,SEQ,ITEM_OUT,DETAIL_CONT);
	        }
        }
    	
        
    }
    
    /**
     * @param SIDO
     * @param SIGUNGU
     * @param RECV_NUM
     * @param NAPPL_YN
     * @param SEQ
     * @param ITEM_OUT
     * @param DETAIL_CONT
     * @throws Exception
     */
    public void InsertData(String SIDO, String SIGUNGU, String RECV_NUM, String NAPPL_YN, String WRT_ID, String SEQ, String ITEM_OUT, String DETAIL_CONT) throws Exception {
    	
    	ReportDAO    rDAO        = new ReportDAO(); 
    	StringBuilder InsertSQL = new StringBuilder();
    	
    	String SRL = KJFDBUtil.getMaxID("SRL","PT_UB_DETAIL");
    	
    	InsertSQL.append("INSERT INTO PT_UB_DETAIL (SRL,SEQ,SIDO_CODE,SIGUNGU_CODE,RECV_NUM,DETAIL_CONT,ITEM_OUT,WRT_ID,INS_DT)");
    	InsertSQL.append("VALUES("+SRL+","+SEQ+",'"+SIDO+"','"+SIGUNGU+"','"+RECV_NUM+"','"+DETAIL_CONT+"','"+ITEM_OUT+"','"+WRT_ID+"','"+KJFDate.datetimeOnly(KJFDate.getCurTime())+"')");
    	rDAO.execute(InsertSQL.toString());
    	
    }
    
    public void UpData(String SIDO, String SIGUNGU, String RECV_NUM, String NAPPL_YN, String WRT_ID, String SEQ, String ITEM_OUT, String DETAIL_CONT) throws Exception {
    	ReportDAO    rDAO        = new ReportDAO(); 
    	StringBuilder updataSQL = new StringBuilder();
    	
    	updataSQL.append("UPDATE PT_UB_DETAIL SET ITEM_OUT = '"+ITEM_OUT+"', DETAIL_CONT='"+DETAIL_CONT+"' , UPD_DT = '"+KJFDate.datetimeOnly(KJFDate.getCurTime())+"' WHERE SIDO_CODE = '"+SIDO+"' AND SIGUNGU_CODE = '"+SIGUNGU+"' AND RECV_NUM ='"+RECV_NUM+"' AND SEQ="+SEQ);
    	rDAO.execute(updataSQL.toString());
    }
    
    public String DeliData(String SIDO_CODE, String SIGUNGU_CODE) throws Exception {
    	ReportDAO    rDAO        = new ReportDAO();
        ReportEntity cEntity     = null;
        ReportEntity oEntity     = null;
        ReportEntity rEntity     = null;
        
        String year = KJFDate.getCurTime("yyyy");
        String DELI_ITEM = "U";
    	
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
    	
    	String updateSQL = "UPDATE PT_UB_DELI_MASTER SET DELI_NUM = LPAD(TO_NUMBER(DELI_NUM)+1,5,'0')  \n"+
							"  WHERE SIDO_CODE = '"+SIDO_CODE+"' \n" +
							"    AND SIGUNGU_CODE = '"+SIGUNGU_CODE+"' \n" +
							"	 AND YEAR = '"+year+"' \n" +
							"	 AND DELI_ITEM = '"+DELI_ITEM+"'  ";
    	
    	rDAO.execute(updateSQL);
    	
    	
    	return rEntity.getValue(0,"DELI_NUM");
    }
    
    /**
     * 폼 체크 메소드
     * 
     * @param request
     * @param form
     * @return
     * @throws Exception
     */
    private PdaParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {

    	PdaParam pm = (PdaParam)form;

        // 파라미터 디버깅
        KJFLog.debug(pm.toString(request));
        
        // 페이징 라인
        if (KJFUtil.isEmpty(pm.getRowPerPage())) {            
            pm.setRowPerPage(Config.props.get("ROW_PER_PAGE"));
        }

        return pm;
    }
}
