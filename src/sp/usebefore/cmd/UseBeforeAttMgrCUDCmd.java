package sp.usebefore.cmd;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.cfg.Config;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFDBUtil;
import kjf.util.KJFDate;
import kjf.util.KJFFile;
import kjf.util.KJFLog;
import kjf.util.KJFSec;
import kjf.util.KJFUtil;
import kjf.util.LoginException;
import kjf.util.MsgException;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import sp.bbs.cmd.BbsKWriteCmd;
import sp.uent.UserEnt;
import sp.usebefore.UseBeforeParam;

import sp.webservice.EchoPortTypeProxy;
import sp.webservice.UBAgentPortTypeProxy;

/**
 * <p>Title       : UseBeforeAttacheCUDCmd Class</p>
 * <p>Description : 사용전검사관리 첨부파일</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : 사용전검사관리 첨부파일 등록, 수정, 삭제 처리를 한다.
 * 
 * @version 1.0
 * @author  PKT
 */
public class UseBeforeAttMgrCUDCmd implements KJFCommand {
	
	UserEnt user;
	int attache_num = 10;
    int fileSize = 10;   // default 10M
    
    public UseBeforeAttMgrCUDCmd() {
    }

    public String execute(HttpServletRequest request, ActionForm form) throws Exception {

        String cmd = request.getParameter("cmd");
        String mode = request.getParameter("mode");
        user = (UserEnt)request.getSession().getAttribute("user");
        
        // 로그인 유무 체크
        if (KJFUtil.isEmpty(user)) {
            throw new LoginException(request, "회원용 페이지 로그인 체크", "../member/login.do");
        }  
        
        // 검색조건 설정및 체크
        UseBeforeParam pm = checkPm(request, form);
        
        if ("C".equals(mode)){
        	checkUseBefore_FILE(request, form);
        	setUseBefore_FILE(request, form, pm);
        } else if ("D".equals(mode)){
        	deleteExe(request,pm);
        }
        
        return request.getParameter("cmd");
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
     * 첨부파일 저장
     * 
     * @param CT_ID
     * @param BOARD_SEQ
     * @param  formFile
     * @throws Exception
     */
    private void insertExe(HttpServletRequest request, FormFile formFile, UseBeforeParam pm, String SYS_FILE_NAME) throws Exception {

        ReportDAO rDAO = new ReportDAO();
        
        String FILE_NM = formFile.getFileName();                              // 파일 실명
        String str            = FILE_NM.substring(FILE_NM.lastIndexOf("."));  // 파일 확장명
        String FILE_SIZE      = formFile.getFileSize() + "";                  // 파일 사이즈
        String WRT_ID		  = user.getUSER_ID();
        String INS_DT		  = KJFDate.getCurTime("yyyy-MM-dd HH:mm:ss");
		String SEQ 			  = KJFDBUtil.getMaxID("SEQ","PT_UB_FILE");
		String RECV_NUM		  = KJFUtil.print(pm.getScRecv_num(),request.getParameter("scRECV_NUM"));
		String SIDO_CODE	  = user.getSIDO_CODE();
		String SIGUNGU_CODE	  = user.getSIGUNGU_CODE();
	    
        StringBuffer sbSQL = new StringBuffer();         
        sbSQL.append(" INSERT INTO PT_UB_FILE  (  \n");
        sbSQL.append("        FILE_NM,            \n");
        sbSQL.append("        FILE_SIZE,          \n");
        sbSQL.append("        WRT_ID,             \n");
        sbSQL.append("        INS_DT,             \n");
        sbSQL.append("        SEQ,                \n");
        sbSQL.append("        RECV_NUM,           \n");
        sbSQL.append("        SIDO_CODE,          \n");
        sbSQL.append("        SIGUNGU_CODE,       \n");
        sbSQL.append("        SYS_FILE_NAME  )     \n");
        sbSQL.append(" VALUES (                   \n");
        sbSQL.append("          ?,?,?,?,?,?,?,?,?    \n");
        sbSQL.append("         )                  \n");
     
        int i = 0;

        rDAO.setValue(++i, FILE_NM);
        rDAO.setValue(++i, FILE_SIZE);
        rDAO.setValue(++i, WRT_ID);
        rDAO.setValue(++i, INS_DT);
        rDAO.setValue(++i, SEQ);
        rDAO.setValue(++i, RECV_NUM);
        rDAO.setValue(++i, SIDO_CODE);
        rDAO.setValue(++i, SIGUNGU_CODE);
        rDAO.setValue(++i, SYS_FILE_NAME);        
        rDAO.execute(sbSQL.toString());
    }
    /**
     * 첨부파일 저장
     * 
     * @param CT_ID
     * @param BOARD_SEQ
     * @param  formFile
     * @throws Exception
     */
    private void deleteExe(HttpServletRequest request,  UseBeforeParam pm) throws Exception {

        ReportDAO rDAO = new ReportDAO();
        
        String SEQ[]		  = request.getParameterValues("chk");           // 파일 실명
		String RECV_NUM		  = KJFUtil.print(pm.getScRecv_num(),request.getParameter("scRECV_NUM"));
		String SIDO_CODE	  = user.getSIDO_CODE();
		String SIGUNGU_CODE	  = user.getSIGUNGU_CODE();
	    
        StringBuffer sbSQL = new StringBuffer();         
        sbSQL.append(" DELETE FROM PT_UB_FILE    \n");
        sbSQL.append(" WHERE           \n");
        sbSQL.append("       RECV_NUM = ? AND     \n");
        sbSQL.append("       SIDO_CODE = ? AND    \n");
        sbSQL.append("       SIGUNGU_CODE = ? AND \n");
        sbSQL.append("       SEQ = ? 		      \n");
        
        rDAO.setValue(1, RECV_NUM);
        rDAO.setValue(2, SIDO_CODE);
        rDAO.setValue(3, SIGUNGU_CODE);
        
        for (int i =0; i< SEQ.length; i++){
	        rDAO.setValue(4, SEQ[i]);
	        rDAO.execute(sbSQL.toString());
        }
     }
    
    
    /**
     * 첨부파일 업로드
     *
     * @param request
     * @param form
     * @throws Exception
     */
    private void setUseBefore_FILE(HttpServletRequest request, ActionForm form,UseBeforeParam pm) throws Exception {
        FormFile[] BBS_FILE = ((UseBeforeParam)form).getBBS_FILE();
        String sigungu_code = user.getSIGUNGU_CODE();
        String SERVER_ADDR = "http://localhost";//getSERVER_ADDR(request,sigungu_code);
        String SYS_FILE_NAME  = "";
        String str ="";
        String FILE_REAL_NAME = "";    
        //if (checkTarServer(request,form) && SERVER_ADDR != "0"){  
        if (SERVER_ADDR != "0"){
	        for (int i = 0; i < attache_num; i++) {
	        	//String SEQ = KJFDBUtil.getMaxID("SEQ","PT_UB_FILE");	        	
	            if ( BBS_FILE[i] != null  && !KJFUtil.isEmpty(BBS_FILE[i].getFileName()) ) {
	            	//지역 서버로 전송
	            	FILE_REAL_NAME = BBS_FILE[i].getFileName();
	            	str           = FILE_REAL_NAME.substring(FILE_REAL_NAME.lastIndexOf("."));  // 파일 확장명
	            	SYS_FILE_NAME = sigungu_code+ "_" + new Date().getTime()+ str ;               // 현재이름을 바꾼다.파일을 현재시간 + 파일이름
	            	//if(sendFile(BBS_FILE[i].getFileName(),BBS_FILE[i],SERVER_ADDR)){
	            	if(sendFile(SYS_FILE_NAME,BBS_FILE[i],SERVER_ADDR)){
	            		insertExe(request,BBS_FILE[i], pm, SYS_FILE_NAME);
	            	} 
	            }
	        }
        }
    }
    
    /**
     * 시군구 지역 서버 프로퍼티
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    private boolean checkTarServer(HttpServletRequest request, ActionForm form) throws Exception {
        
	    String UB_SERVER[]= null;
	    String ub_temp = "";
	    ub_temp = Config.props.get("UB_ENDPOINT");
	    UB_SERVER = ub_temp.split("/");
	    String uSIGUNGU_CODE = user.getSIGUNGU_CODE();
	    String ubSIGUNGU_CODE = "";
	    boolean b_target_server = false;
	    for (int i = 0; i < UB_SERVER.length; i++){
	    	ubSIGUNGU_CODE = UB_SERVER[i];
	    	if (uSIGUNGU_CODE.equals(ubSIGUNGU_CODE)){
	    		b_target_server = true;
	    	}
	    }  
	  return b_target_server;
    }

    /**
     * 시군구 지역 서버 프로퍼티
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public static String getSERVER_ADDR(HttpServletRequest request,  String sigungu_code) throws Exception {
        ReportDAO rDAO = new ReportDAO();
        ReportEntity rEnt;
        
        String SERVER_ADDR ="";
        rEnt = rDAO.select("select server_addr from pt_s_sysvar_master where sigungu_code = '"+sigungu_code+"' and server_addr is not null" );
        SERVER_ADDR = KJFUtil.print(rEnt.getValue(0,"SERVER_ADDR").trim(),"0");

        return SERVER_ADDR;
    }
    
    /**
     * 웹서비스를 이용하여 시군구 서버로 파일 전송 성공하면 true 반환
     * @param UbParam pm
     * @param String sourceFile
     * @param String destFolder
     * @param String destFileName
     * @return boolean
     * @throws Exception
     */
    private boolean sendFile(String filename,FormFile formFile, String SERVER_ADDR) throws Exception {
		boolean flag = false;
		String sido_code = user.getSIDO_CODE();
		String sigg_code = user.getSIGUNGU_CODE();
		String key= KJFSec.encode(sigg_code);

		//EchoPortTypeProxy ec =new EchoPortTypeProxy("http://localhost/iccsWs/services/Echo");
		//System.out.println("#sendFile#");
		//System.out.println("##############"+ec.getWecome("abc"));
		
		UBAgentPortTypeProxy ub = new UBAgentPortTypeProxy(SERVER_ADDR + "/iccsWs/services/UBAgent");
		//UBAgentPortTypeProxy ub = new UBAgentPortTypeProxy("http://localhost/iccsWs/services/UBAgent");
		
		//File files = new File(sourceFile);

		int result = ub.setFile(key,formFile.getFileData(),sigg_code,filename);
		
		if(result == 1){
			flag = true;
		}
		System.out.println("발송 값 : "+result);
		System.out.println("0 : 전송실패");
		System.out.println("1 : 완료 ");
		//break;
		return flag;
	} 
   /**
     * 사이즈 체크 및 금지 파일 업로드 체크
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    private void checkUseBefore_FILE(HttpServletRequest request, ActionForm form) throws Exception {
        
        FormFile[] BBS_FILE = ((UseBeforeParam)form).getBBS_FILE();

        int sizeOfFiles = 0;

        // 전체 사이즈 체크
        for (int i = 0; i < attache_num; i++) {

            if ( BBS_FILE[i] != null  && !KJFUtil.isEmpty(BBS_FILE[i].getFileName()) ) {

                sizeOfFiles += BBS_FILE[i].getFileSize();
                // 금지파일 체크
                KJFFile.checkNotFile(BBS_FILE[i].getFileName());
            }
        }

        if ( sizeOfFiles  > 1024 * 1024 * fileSize) {
             throw new MsgException("파일 용량이 " + fileSize + "M 를 초과 하였습니다.");
        }       
    }
    
    /**
     * Max ID를 가져온다.
     * @param   String as_Table, String  as_KeyCol
     * @return   String
     */
     public  static String getMaxID( String  as_KeyCol, String as_Table) throws  Exception{
         String sql = "";
         ReportDAO rDAO = new ReportDAO();

         switch (rDAO.db_type){

         case 2: //Ms-sql
         	sql =
 	        	" SELECT														\n" +
 	        	"     ISNULL(MAX(convert(numeric,"+as_KeyCol+")),0)+1  MAX_ID   \n" +
 	        	" FROM "+as_Table+"												\n" ;
 	        break;
 	    default:
         	sql =
 	        	//" SELECT SEQ_PT_SM_USER_LOG.NEXTVAL AS MAX_ID FROM DUAL           		\n" ;
         		" SELECT														\n" +
 	        	"     NVL( MAX( to_number("+as_KeyCol+") ), 0 ) + 1   MAX_ID    \n" +
 	        	" FROM "+as_Table+"												\n" ;

 	    	break;
         }
         ReportEntity rEntity     = null;
         rEntity = rDAO.select(sql);
         return rEntity.getValue(0,"MAX_ID");
     }   
}