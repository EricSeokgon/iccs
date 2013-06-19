/**
 * 파일명   : MigMgrCUDCmd.java
 * 설명     : CUD cmd
 * 이력사항
 * CH00     : 전원배
 */

package sp.mig.cmd;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFDate;
import kjf.util.KJFLog;
import kjf.util.KJFUtil;

import sp.mig.*;

import org.apache.struts.action.ActionForm;

public class MigMgrCUDCmd implements KJFCommand {

    public MigMgrCUDCmd() {
    }


    public String execute(HttpServletRequest request, ActionForm form) throws Exception {

    	String mode = request.getParameter("mode");
    	
    	//insertExe(request,form);
    	
    	//insertBbsExe(request,form,"0","PT_BBS_PDA_LOCAL_PUB","PT_BBS_REGIONALISM_PUB","REGIONALISM_PUB"); // 지방분권 자료실
    	//insertBbsExe(request,form,"1","PT_BBS_QNA_LOCAL_PUB","PT_BBS_REGIONALISM_PUB","REGIONALISM_PUB"); // 지방분권 의견나누기
    	
    	insertBbsExe(request,form,"0","PT_BBS_PDA_LEGAL_PUB","PT_BBS_LAW_RECHANGE_PUB","LAW_RECHANGE_PUB"); // 법령재개정 자료실
    	insertBbsExe(request,form,"1","PT_BBS_QNA_LEGAL_PUB","PT_BBS_LAW_RECHANGE_PUB","LAW_RECHANGE_PUB"); // 법령재개정 의견나누기	
    	
        return request.getParameter("cmd");
    }
    
    private void insertBbsExe(HttpServletRequest request,ActionForm form,String max_sw, String Bbs,String TargetBbs,String CT_ID)throws Exception{

    	ReportDAO    rDAO        = new ReportDAO();
        ReportEntity rEntity     = null;
        
        String selectSQL =  " SELECT BOARD_SEQ,INDEX1,INDEX2,DEPTH,USER_ID,USER_NAME," +
        					" USER_IP,SUBJECT,CONTENT,REPLIED_YN,SECRET_YN,NOTICE_YN,READ_NUM," +
        					" UPD_DT,INS_DT,WRT_ID ";
        
        String fromSQL =" FROM "+Bbs + " ORDER BY BOARD_SEQ";
        
        String BOARD_SEQ ="";String INDEX1 =""; String INDEX2 ="";String DEPTH =""; String USER_ID =""; String USER_NAME ="";
		String USER_IP="";String SUBJECT ="";String CONTENT ="";String REPLIED_YN =""; String SECRET_YN =""; String NOTICE_YN ="";
		String READ_NUM ="";String UPD_DT =""; String INS_DT =""; String WRT_ID = "";
		String setBOARD_SEQ = "";
		
        rEntity = rDAO.select(selectSQL + fromSQL);
        
        StringBuffer sbSQL = new StringBuffer(); 
        sbSQL.append(" INSERT INTO " + TargetBbs + " (   \n");
        sbSQL.append("        CT_ID,                    \n");
        sbSQL.append("        BOARD_SEQ,                \n");
        sbSQL.append("        INDEX1,                   \n");
        sbSQL.append("        INDEX2,                   \n");
        sbSQL.append("        DEPTH,                    \n");
        sbSQL.append("        USER_ID,                  \n");
        sbSQL.append("        USER_NAME,                \n");
        sbSQL.append("        USER_IP,                  \n");
        sbSQL.append("        SUBJECT,                  \n");
        sbSQL.append("        CONTENT,                  \n");
        sbSQL.append("        REPLIED_YN,               \n");
        sbSQL.append("        SECRET_YN,                \n");
        sbSQL.append("        NOTICE_YN,                \n");
        sbSQL.append("        READ_NUM,                 \n");
        sbSQL.append("        UPD_DT,                   \n");
        sbSQL.append("        INS_DT,                   \n");
        sbSQL.append("        WRT_ID                   \n");
        sbSQL.append("       )                          \n");
        sbSQL.append("   values (                       \n");
        sbSQL.append("            ?,?,?,?,?,            \n");
        sbSQL.append("            ?,?,?,?,?,             \n");
        sbSQL.append("            ?,?,?,?,?,        \n");
        sbSQL.append("            ?,?		            \n");
        sbSQL.append("          )                       \n");  
        
        for (int i =0; i < rEntity.getRowCnt(); i++){
        	BOARD_SEQ = rEntity.getValue(i,"BOARD_SEQ");
        	INDEX1 = rEntity.getValue(i,"INDEX1"); 	INDEX2 = rEntity.getValue(i,"INDEX2"); 
        	DEPTH = rEntity.getValue(i,"DEPTH"); 	USER_ID = rEntity.getValue(i,"USER_ID");  
        	USER_NAME = rEntity.getValue(i,"USER_NAME"); 	USER_IP = rEntity.getValue(i,"USER_IP");  
        	SUBJECT = rEntity.getValue(i,"SUBJECT"); 	CONTENT = rEntity.getValue(i,"CONTENT");  
        	REPLIED_YN = rEntity.getValue(i,"REPLIED_YN"); 	SECRET_YN = rEntity.getValue(i,"SECRET_YN");  
        	NOTICE_YN = rEntity.getValue(i,"NOTICE_YN"); 	READ_NUM = rEntity.getValue(i,"READ_NUM");  
        	UPD_DT = rEntity.getValue(i,"UPD_DT"); 			INS_DT = rEntity.getValue(i,"INS_DT"); 
        	WRT_ID = rEntity.getValue(i,"WRT_ID"); 
        	
 
        	if("1".equals(max_sw)){
	        	setBOARD_SEQ = getSeqID("BOARD_SEQ",TargetBbs,BOARD_SEQ);
	            int j = 0;

	            rDAO.setValue(++j, CT_ID);
	            rDAO.setValue(++j, setBOARD_SEQ);
	            rDAO.setValue(++j, setBOARD_SEQ);
	            rDAO.setValue(++j, INDEX2);
	            rDAO.setValue(++j, DEPTH);
	            rDAO.setValue(++j, USER_ID);
	            rDAO.setValue(++j, USER_NAME);
	            rDAO.setValue(++j, USER_IP);
	            rDAO.setValue(++j, SUBJECT);
	            rDAO.setValue(++j, CONTENT);
	            rDAO.setValue(++j, REPLIED_YN);
	            rDAO.setValue(++j, SECRET_YN);
	            rDAO.setValue(++j, NOTICE_YN);
	            
	            rDAO.setValue(++j, READ_NUM);                
	            rDAO.setValue(++j, UPD_DT);
	            rDAO.setValue(++j, INS_DT);
	            rDAO.setValue(++j, WRT_ID);
	            
	            insertCommentExe(BOARD_SEQ,BOARD_SEQ,Bbs.substring(7, Bbs.length()),CT_ID);
	            insertFileExe(BOARD_SEQ,BOARD_SEQ,Bbs.substring(7, Bbs.length()),CT_ID);
	            
	            
        	} else {

	            int j = 0;

	            rDAO.setValue(++j, CT_ID);
	            rDAO.setValue(++j, BOARD_SEQ);
	            rDAO.setValue(++j, INDEX1);
	            rDAO.setValue(++j, INDEX2);
	            rDAO.setValue(++j, DEPTH);
	            rDAO.setValue(++j, USER_ID);
	            rDAO.setValue(++j, USER_NAME);
	            rDAO.setValue(++j, USER_IP);
	            rDAO.setValue(++j, SUBJECT);
	            rDAO.setValue(++j, CONTENT);
	            
	            rDAO.setValue(++j, REPLIED_YN);
	            rDAO.setValue(++j, SECRET_YN);
	            rDAO.setValue(++j, NOTICE_YN);
	            
	            rDAO.setValue(++j, READ_NUM);                
	            rDAO.setValue(++j, UPD_DT);
	            rDAO.setValue(++j, INS_DT);
	            rDAO.setValue(++j, WRT_ID);
	            
	            insertCommentExeOnly(Bbs.substring(7, Bbs.length()),CT_ID);
	            insertFileExeOnly(Bbs.substring(7, Bbs.length()),CT_ID);
        	}
        		System.out.println("갯수"+i);
	            rDAO.execute(sbSQL.toString());

        	//KJFLog.log(i +" " + temp.toString());
        }
    }
    
    private void insertCommentExe(String BOARD_SEQ, String setBOARD_SEQ, String CT_ID,String setCT_ID)throws Exception{

    	ReportDAO    rDAO        = new ReportDAO();
        ReportEntity rEntity     = null;
        
        String UpSQL = " UPDATE PT_BBS_COM_COMMENT "+
        			   " SET CT_ID = ?, BOARD_SEQ = ?"+
        			   " WHERE CT_ID = ? AND BOARD_SEQ = ?";
        int i = 0;
        rDAO.setValue(++i, setCT_ID);
        rDAO.setValue(++i, setBOARD_SEQ);
        rDAO.setValue(++i, CT_ID);
        rDAO.setValue(++i, BOARD_SEQ);
        
        rDAO.execute(UpSQL);
    }
    private void insertCommentExeOnly(String CT_ID, String setCT_ID)throws Exception{

    	ReportDAO    rDAO        = new ReportDAO();
        ReportEntity rEntity     = null;
        
        String UpSQL = " UPDATE PT_BBS_COM_COMMENT "+
        			   " SET CT_ID = ?"+
        			   " WHERE CT_ID = ? ";
        int i = 0;
        rDAO.setValue(++i, setCT_ID);
        rDAO.setValue(++i, CT_ID);
        
        rDAO.execute(UpSQL);
    }
    private void insertFileExe(String BOARD_SEQ, String setBOARD_SEQ, String CT_ID,String setCT_ID)throws Exception{

    	ReportDAO    rDAO        = new ReportDAO();
        ReportEntity rEntity     = null;
        
        String UpSQL = " UPDATE PT_BBS_COM_FILES"+
        			   " SET CT_ID = ?, BOARD_SEQ = ?"+
        			   " WHERE CT_ID = ? AND BOARD_SEQ = ?";
        int i = 0;
        rDAO.setValue(++i, setCT_ID);
        rDAO.setValue(++i, setBOARD_SEQ);
        rDAO.setValue(++i, CT_ID);
        rDAO.setValue(++i, BOARD_SEQ);
        
        rDAO.execute(UpSQL);
    }
    private void insertFileExeOnly(String CT_ID,String setCT_ID)throws Exception{

    	ReportDAO    rDAO        = new ReportDAO();
        ReportEntity rEntity     = null;
        
        String UpSQL = " UPDATE PT_BBS_COM_FILES"+
        			   " SET CT_ID = ?"+
        			   " WHERE CT_ID = ? ";
        int i = 0;
        rDAO.setValue(++i, setCT_ID);
        rDAO.setValue(++i, CT_ID);

        rDAO.execute(UpSQL);
    }
    /**
     * 입력
     * @param HttpServletRequest
     * @return
     */
    private void insertExe(HttpServletRequest request,ActionForm form)throws Exception{

    	ReportDAO    rDAO        = new ReportDAO();
        ReportEntity rEntity     = null;
        
        String selectSQL =  " SELECT A.CUD_FLAG,A.SIDO_CODE,A.COI_WRT_NUM, A.MANA_NUM, A.ASSE_DT, A.ASSE_AOM, A.APPL_TERM_START_DT, A.APPL_TERM_END_DT, A.KICA_INS_DT," +
        					" (SELECT TMP_WRT_NUM FROM PT_R_COMPANY_MASTER WHERE COI_WRT_NUM = A.COI_WRT_NUM) AS TMP_WRT_NUM," +
        					" (CASE WHEN (SELECT SIDO_CODE FROM PT_R_COMPANY_MASTER WHERE SIDO_CODE IS NOT NULL AND COI_WRT_NUM = A.COI_WRT_NUM) = A.SIDO_CODE THEN 'SIDO_EQ' ELSE 'SIDO_U' END) AS SIDO_CHK, " +
        					" (CASE WHEN (SELECT MANA_NUM FROM PT_R_COMPANY_MASTER WHERE MANA_NUM IS NOT NULL AND COI_WRT_NUM = A.COI_WRT_NUM) = A.MANA_NUM THEN 'MANA_EQ' ELSE 'MANA_U' END) AS MANA_CHK ";
        
        String fromSQL =" FROM PT_TMP_WORK_CAPABILITY A";
        
        String temp = "";
        String temp_etc ="";
        
        rEntity = rDAO.select(selectSQL + fromSQL);
		String MANA_CHK = "";
		String SIDO_CHK = "";
        for (int i =0; i < rEntity.getRowCnt(); i++){
        	MANA_CHK = rEntity.getValue(i,"MANA_CHK");
        	SIDO_CHK = rEntity.getValue(i,"SIDO_CHK");
        	
        	if ("C".equals(rEntity.getValue(i, "CUD_FLAG"))){
        	String SEQ = getSeqID("SEQ","PT_R_WORK_CAPABILITY",rEntity.getValue(i,"TMP_WRT_NUM"));
        	temp = "INSERT INTO PT_R_WORK_CAPABILITY ( SEQ, TMP_WRT_NUM, ASSE_DT, ASSE_AOM, APPL_TERM_START_DT,APPL_TERM_END_DT, INS_DT ) " +
        		   " VALUES ('"+SEQ+"','"+rEntity.getValue(i,"TMP_WRT_NUM")+"','"+rEntity.getValue(i,"ASSE_DT").substring(0,4)+"','" +
        		   rEntity.getValue(i,"ASSE_AOM")+"','"+rEntity.getValue(i,"APPL_TERM_START_DT")+"','" +
        		   rEntity.getValue(i,"APPL_TERM_END_DT")+"','"+rEntity.getValue(i,"KICA_INS_DT")+"')";
        	} else if ("U".equals(rEntity.getValue(i, "CUD_FLAG"))){
        		
        		temp ="";
        	} else if ("D".equals(rEntity.getValue(i,"CUD_FLAG"))){
	        	temp = "DELETE FROM PT_R_WORK_CAPABILITY " +
	        		   " WHERE TMP_WRT_NUM = '"+rEntity.getValue(i,"TMP_WRT_NUM")+"'";	
        	}
        	if (!"".equals(temp)){
        	System.out.println(rEntity.getValue(i, "CUD_FLAG")+" "+rEntity.getValue(i, "KICA_INS_DT")+" "+rEntity.getValue(i, "COI_WRT_NUM")+" "+ i + " "+ temp);
        	}
        	//KJFLog.log(i +" " + temp.toString());
        }
    }
    /**
     * 문서 번호를 가져온다.
     * @param   String as_Table, String  as_KeyCol
     * @return   String
     */
     public  static String getSeqID( String  as_KeyCol, String as_Table, String as_KeyVal) throws  Exception{

         String sql = "";
         ReportDAO rDAO = new ReportDAO();

         sql =
         		" SELECT														\n" +
 	        	"     NVL( MAX( to_number("+as_KeyCol+") ), 0 ) + 1   SEQ_ID    \n" +
 	        	" FROM "+as_Table+"												\n";
         
         		//" WHERE BOARD_SEQ ='"+as_KeyVal+"'									\n" ;

         ReportEntity rEntity     = null;
         rEntity = rDAO.select(sql);
         return rEntity.getValue(0,"SEQ_ID");
     }     
}