/**
 * ���ϸ�   : BbsWorker.java
 * ����	    : �Ϲ� JSP ���������� �����  ���� �Լ��� ����
 * �̷»���
 * CH00     :2006/07/07 ���ν�  �����ۼ� 
 */

package sp.bbs;

import java.util.Vector;

import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFSelect;


public class BbsJsp{
	
	private String[][] selType = {{"","��ü"},{"1","�ϹݰԽ���"},{"2","�ٹ��Խ���"}}; // �Խ��� Ÿ��
	private String[][] selUseYn = {{"Y","���"},{"N","�̻��"}}; //��� ����
		
	public Vector getBbsType(){
		return BbsSelect(this.selType);
	}
	
	public Vector getBbsUseYn(){
		return BbsSelect(this.selUseYn);
	}
	
	public String getBbsTypeValue(String key){
		return BbsSelectKey(this.selType,key);
	}
	
	public String getBbsUseYnValue(String key){
		return BbsSelectKey(this.selUseYn,key);
	}
	
	
	/**
	 * 1���� �迭�� �Ѿ�� ���� ���ͷ� ��ȯ
	 * @param selItem
	 * @return
	 */
	public Vector BbsSelect (String[] selItem){
		
		Vector vec = new Vector();
		
		for(int i=0; i<selItem.length; i++){
			KJFSelect sel			= new KJFSelect();
			
			sel.setCode(Integer.toString(i));
			sel.setCode_nm(selItem[i]);
			
			vec.add(sel);
		}
		return vec;
	}
	
	/**
	 * 2���� �迭�� �Ѿ�� ���� ���ͷ� ��ȯ
	 * @param selItem
	 * @return
	 */
	public Vector BbsSelect (String[][] selItem){
		
		Vector vec = new Vector();
		
		for(int i=0; i<selItem.length; i++){
			KJFSelect sel			= new KJFSelect();
			
			sel.setCode(selItem[i][0]);
			sel.setCode_nm(selItem[i][1]);
			
			vec.add(sel);
		}
		return vec;
	}
	
	/**
	 * 1���� �迭�� �Ѿ�� ������ Ű�� �����ϴ� ���� ��ȯ
	 * @param selItem
	 * @return String
	 */
	public String BbsSelectKey (String[] selItem,String key){
		
		String value = null;
		
		for(int i=0; i<selItem.length; i++){
			
			if(Integer.toString(i).equals(key)) 
				value = selItem[i];
		}		
		return value;
	}
	
	/**
	 * 2���� �迭�� �Ѿ�� ������ Ű�� �����ϴ� ���� ��ȯ
	 * @param selItem
	 * @return String
	 */
	public String BbsSelectKey (String[][] selItem,String key){
		
		String value = null;
		
		for(int i=0; i<selItem.length; i++){
			
			if(selItem[i][0].equals(key)) 
				value = selItem[i][1];

		}
		return value;
	}
	
	
	/** 
	    * �Խ��Ǳ⺻������ �����´�.
	    * @param   String coopration_cd
	    * @return   String
	    */
	public  ReportEntity loadListFromDB(String CT_ID) throws  Exception{
		
		
		
		String sql =
			"\n SELECT							 	" + 
			"\n		CT_ID,						 	" + 
			"\n		BOARD_NAME,			 			" + 
			"\n		TYPE,                       	" + 
			"\n		LOGIN_YN,                		" + 
			"\n		ATTACH_YN,             			" + 
			"\n		REPLY_YN,               		" + 
			"\n		ONE_LINE_YN,          			" +
			"\n		WRITE_YN,                   	" + 		        	
			"\n		USE_YN,                   		" +         	
			"\n		UPD_DT,                   		" + 
			"\n		REG_ID,                    		" + 
			"\n		INP_DT,                    		" +
			"\n		DIV_PG,    	            		" +
			"\n		ATT_NUM,                 		" + 
			"\n		ROWPERPAGE,                		" + 
			"\n		FILE_SIZE,                 		" +
			"\n		ONE_ONE_YN,                		" + 
			"\n		VIEW_LIST_YN	           		" + 
			"\n	FROM   PT_BBS_COM_CT   				" + 
			"\n WHERE CT_ID='"+CT_ID+"'        		" ;
		
		
		
		ReportDAO    rDAO        = new ReportDAO();
		ReportEntity rEntity     = null;
		
		
		rEntity = rDAO.select(sql);
		
		
		return rEntity;
	}
	
    public ReportEntity mainboardNoticeList(String CT_ID) throws Exception {
        ReportDAO    rDAO        = new ReportDAO();
        ReportEntity rEntity     = null;

        String selectSQL =
        	"SELECT            		\n"+ 
        	"	TOP 4          		\n"+ 
        	"	CT_ID,				\n"+
        	"	BOARD_SEQ, 			\n"+
        	"	INDEX1,         	\n"+ 
        	"	INDEX2,         	\n"+	
        	"	DEPTH,         		\n"+ 
        	"	USER_ID,			\n"+
        	"	USER_NAME, 			\n"+
        	"	USER_EMAIL,			\n"+ 
        	"	USER_PASS,  		\n"+	
        	"	USER_IP,       		\n"+ 
        	"	SUBJECT,     		\n"+
        	"	CONTENT,    		\n"+
        	"	READ_NUM,   		\n"+ 
        	"	REPLIED_YN, 		\n"+
        	"	DIV_PG,				\n"+ 
        	"	USER_TEL, 			\n"+        	
        	"	UPD_DT,        		\n"+ 
        	"	INP_DT,         	\n"+
        	"	REG_ID          	\n";	

        String fromSQL=
            "FROM                           \n"+
            "    PT_BBS_"+CT_ID+"           \n";
       
        String whereSQL = 
        	"WHERE CT_ID='"+CT_ID+"'                  						\n";
        
        String orderSQL=
            "ORDER BY                                   \n" +
            "        INDEX1 DESC, INDEX2 ASC            \n" ;
        
        String threeSQL =
        	"SELECT            		\n"+ 
        	"	CT_ID,				\n"+
        	"	BOARD_SEQ, 			\n"+
        	"	INDEX1,         	\n"+ 
        	"	INDEX2,         	\n"+	
        	"	DEPTH,         		\n"+ 
        	"	USER_ID,			\n"+
        	"	USER_NAME, 			\n"+
        	"	USER_EMAIL,			\n"+ 
        	"	USER_PASS,  		\n"+	
        	"	USER_IP,       		\n"+ 
        	"	SUBJECT,     		\n"+
        	"	CONTENT,    		\n"+
        	"	READ_NUM,   		\n"+ 
        	"	REPLIED_YN, 		\n"+
        	"	DIV_PG,				\n"+ 
        	"	USER_TEL, 			\n"+        	
        	"	UPD_DT,        		\n"+ 
        	"	INP_DT,         	\n"+
        	"	REG_ID          	\n"+
        	"	FROM ("+ selectSQL+fromSQL+whereSQL+orderSQL + ")"+
        	"	AS BBS";
        	
		rEntity = rDAO.select(threeSQL);
		
		return rEntity;
    }
    
}
