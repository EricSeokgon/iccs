/**
 * 파일명   : BbsWorker.java
 * 설명	    : 일반 JSP 페이지에서 사용할  공통 함수를 정의
 * 이력사항
 * CH00     :2006/07/07 오두식  최초작성 
 */

package sp.bbs;

import java.util.Vector;

import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFSelect;


public class BbsJsp{
	
	private String[][] selType = {{"","전체"},{"1","일반게시판"},{"2","앨범게시판"}}; // 게시판 타입
	private String[][] selUseYn = {{"Y","사용"},{"N","미사용"}}; //사용 유무
		
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
	 * 1차원 배열로 넘어온 값을 벡터로 반환
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
	 * 2차원 배열로 넘어온 값을 벡터로 반환
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
	 * 1차원 배열로 넘어온 값에서 키에 대응하는 값을 반환
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
	 * 2차원 배열로 넘어온 값에서 키에 대응하는 값을 반환
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
	    * 게시판기본정보를 가져온다.
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
