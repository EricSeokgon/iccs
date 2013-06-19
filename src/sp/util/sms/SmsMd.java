package sp.util.sms;

import kjf.ops.ReportDAO;
import kjf.util.KJFDate;
import sp.util.sms.local.Busan;

public class SmsMd {
    
    public int SendMsg(String user_id, 
    		           String Local_cd, 
    		           String Area_cd, 
    		           String from_tel, 
    		           String to_tel, 
    		           String msg) throws Exception{
    	int result =0;
    	
    	if(Local_cd.equalsIgnoreCase("bsbs"))
    		result = new Busan().SendMsg(from_tel, to_tel, msg);
    	//...이후 추가
    	
    	
    	Smslog( user_id, Local_cd, Area_cd, from_tel, to_tel, msg, result+"");
	
    	return result;
    }
    
    
    public void Smslog(String user_id, 
    				  String Local_cd, 
    				  String Area_cd, 
    				  String from_tel, 
    				  String to_tel, 
    				  String msg,
    				  String result) throws Exception{
        
        ReportDAO rDAO = new ReportDAO();      
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" INSERT INTO PT_SMS_LOG (         \n");
        sbSQL.append("        USER_ID,                  \n");
        sbSQL.append("        LOCAL_CD,                 \n");
        sbSQL.append("        AREA_CD,                  \n");
        sbSQL.append("        FROM_TEL,                 \n");
        sbSQL.append("        TO_TEL,                   \n");
        
        sbSQL.append("        MSG,                      \n");
        sbSQL.append("        RESULT,                   \n");
        sbSQL.append("        UPD_DT,                   \n");
        sbSQL.append("        INS_DT                    \n");
        sbSQL.append("       )                          \n");
        sbSQL.append("   values (                       \n");   
        sbSQL.append("           ?,?,?,?,?,             \n");
        sbSQL.append("           ?,?,?,?                \n");
        sbSQL.append("           )                      \n");

        int i = 1;
        
        rDAO.setValue(i++, user_id);
        rDAO.setValue(i++, Local_cd);
        rDAO.setValue(i++, Area_cd);
        rDAO.setValue(i++, from_tel);
        rDAO.setValue(i++, to_tel);
        rDAO.setValue(i++, msg);
        rDAO.setValue(i++, result);
        rDAO.setValue(i++, KJFDate.getCurTime("yyyy-MM-dd HH:mm:ss"));
        rDAO.setValue(i++, KJFDate.getCurTime("yyyy-MM-dd HH:mm:ss"));

        rDAO.execute(sbSQL.toString());    	
    }
}
