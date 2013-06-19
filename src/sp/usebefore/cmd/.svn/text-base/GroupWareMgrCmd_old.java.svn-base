/**
 * 파일명   : GroupWareMgrCmd.java
 * 설명     : 전자결재 연동 cmd
 * 이력사항
 * CH00     : 2009/12/10 전원배 최초작성
 */

package sp.usebefore.cmd;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.cfg.Config;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFDate;
import kjf.util.KJFFile;
import kjf.util.KJFLog;
import kjf.util.KJFSec;
import kjf.util.KJFUtil;

import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;

import org.apache.struts.action.ActionForm;

import sp.usebefore.UseBeforeParam;
import sp.webservice.UBAgentPortTypeProxy;

public class GroupWareMgrCmd_old implements KJFCommand {
	
	private String FILE_PATH = null;
    
    public GroupWareMgrCmd_old() {
    }
 
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {
    	UseBeforeParam pm 		= (UseBeforeParam)form;
    	
        ReportDAO rDAO 			= new ReportDAO();
    	ReportEntity rEntity 	= null;
    	
 	    String sOFFI_ID 		= request.getParameter("scOFFI_ID");
 	    String scRECV_NUM 		= request.getParameter("RECV_NUM");
 	    StringBuilder sQuery 	= new StringBuilder();
       
		sQuery.append(" SELECT  AA.RECV_ID, ");
		sQuery.append("         AA.SIDO_CODE, ");
		sQuery.append("         AA.SIGUNGU_CODE, ");
		sQuery.append("         AA.OFFI_ID, ");
		sQuery.append("         AA.E_MAIL, ");
		sQuery.append("         BB.SEND_SYS_ID, ");
		sQuery.append("         BB.RECV_SYS_ID, ");
		sQuery.append("         BB.SERVER_ADDR, ");
		sQuery.append("         CC.SIGUNGU_NM ");
       
		sQuery.append(" FROM    PT_MI_USER AA LEFT JOIN PT_S_SYSVAR_MASTER BB ");
		sQuery.append("         ON AA.SIDO_CODE = BB.SIDO_CODE AND AA.SIGUNGU_CODE = BB.SIGUNGU_CODE ");
		sQuery.append("         LEFT JOIN PT_SIDO_CODE CC ON AA.SIGUNGU_CODE = CC.AREA_CODE  ");
		sQuery.append(" WHERE   AA.OFFI_ID = '" + sOFFI_ID + "' ");

   	
		rEntity    = rDAO.select(sQuery.toString());
    	
		String admNum 				= scRECV_NUM;
		String scSERVER_ADDR 		= rEntity.getValue(0, "SERVER_ADDR");
		
        String scSIDO_CODE 		= rEntity.getValue(0,"SIDO_CODE");
        String scSIGUNGU_CODE 	= rEntity.getValue(0,"SIGUNGU_CODE");
        
        String sendSysID		= rEntity.getValue(0,"SEND_SYS_ID");
        String sendUserID		= rEntity.getValue(0,"OFFI_ID");
        String sendUserEmail	= rEntity.getValue(0,"E_MAIL");
        String sendOrgName		= rEntity.getValue(0,"SIGUNGU_NM");
        String recvSysID		= rEntity.getValue(0,"RECV_SYS_ID");
        String recvUserID		= rEntity.getValue(0,"RECV_ID");
        
        String scServerAddr		= KJFUtil.print(scSERVER_ADDR,"");	//시군구 서버 정보
        //String scServerAddr		= "http://localhost";
        
        /*  전자결재 문서  작업 */
        TimeZone tz = TimeZone.getTimeZone("Asia/Seoul");
    	TimeZone.setDefault(tz);
    	
        String ubID = ""+new Date().getTime();	//본 작업 수행시 고유번호 생성
        
        String curDate			= KJFDate.getCurDatetime();
    	String curDateNoFormate = KJFDate.getChangDatePattern(curDate,"yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss");
    	
    	
    	pm.setSendSysID(sendSysID);
    	pm.setSendSysName("정보통신사용전검사연계시스템");
    	pm.setSendUserID(sendUserID);
    	pm.setSendUserEmail(sendUserEmail);
    	pm.setSendOrgName(sendOrgName);
    	pm.setSendDate(curDate);
    	pm.setSendDateNoFomate(curDateNoFormate);
    	pm.setRecvSysID(recvSysID);
    	pm.setRecvUserID(recvUserID);
    	pm.setScServerAddr(scServerAddr);
    	pm.setAdmNum(scRECV_NUM);
    	
    	pm.setScSIDO_CODE(scSIDO_CODE);
    	pm.setScSIGUNGU_CODE(scSIGUNGU_CODE);
    	pm.setUbID(ubID);
    	
    	ebmsCall(pm);
        /*  전자결재 문서 호출 작업 끝 */
    	
//        request.setAttribute("TO_DATE", ubID);  
//        request.setAttribute("SIGUNGU_CODE", scSIGUNGU_CODE);
//        request.setAttribute("SERVER_ADDR", SERVER_ADDR);        
//        request.setAttribute("dl", dl);  
    	request.setAttribute("pm", pm);
        return request.getParameter("cmd");
    }
 
    /**
     * 전자문서 연동 메인 함수(이것만 호출하면 됨
     * @param pm
     * @throws Exception
     */
    public void ebmsCall(UseBeforeParam pm ) throws Exception { 
    	    	
    	Random rand = new Random(System.currentTimeMillis());
    	pm.setSuccResult("1");
    	String destFolder = pm.getRecvUserID()
							+ pm.getSendSysID()
							+ pm.getRecvSysID()
							+ pm.getSendDateNoFomate()
							+ (rand.nextInt(9999) + 10000);
    	
//    	String dirPath = "D:\\work\\Jsp_Project3\\iccsm\\usebefore\\_ebms_dir\\";
    	String dirPath = Config.props.get("EBMS_FILE_DIR");
    	String embsPath = dirPath + pm.getSendUserID() + KJFFile.FILE_S + destFolder;
    	String filePath = embsPath + KJFFile.FILE_S;
    	
    	if(!makeEbmsFile(pm, filePath)){
    		KJFLog.log("파일 생성 실패");
    		pm.setSuccResult("0");
    	}else{
    		KJFLog.log("파일 생성 완료");
    		if(!sendEbmsFile(pm, filePath, destFolder)){
    			KJFLog.log("파일 전송 실패");
    			pm.setSuccResult("0");
        	}else{
        		KJFLog.log("파일 전송 완료");
        	}
    	}
    	
    	while(true){
            try{
            	KJFLog.log("파일 전송 eof.inf");
            	Thread.sleep(1000);            	
            }catch(Exception e){
            	pm.setSuccResult("0");
                System.out.println(e.toString());
            }
            break;
        }
    	
    	if(sendFile(pm, dirPath + "eof.inf", destFolder, "eof.inf")){
    		KJFLog.log("파일 전송 eof.inf완료");
    		File ebmsFiles = new File(embsPath);
    		
    		if(deleteEbmsFile(ebmsFiles)){
    			KJFLog.log(destFolder + " : ebms file deleted!");
    		}else{
    			pm.setSuccResult("0");
    			KJFLog.log(destFolder + " : ebms file delete fail!");
    		}
    	}
    	

    }
    
    
    /**
     * 생성된 파일을 시군구 서버로 전송한다.
     * @param ActionForm form
     * @param String filePath
     * @param String destFolder
     * @return boolean
     * @throws Exception
     */
    public boolean sendEbmsFile(ActionForm form, String filePath, String destFolder)throws Exception{
    	UseBeforeParam pm = (UseBeforeParam)form;
    	boolean flag = false;
    	
    		
		if(sendFile(pm, filePath + "header.inf", destFolder, "header.inf")
				&& sendFile(pm, filePath + "exchange.xml", destFolder, "exchange.xml")
				&& sendFile(pm, filePath + "attach_UBInvestResult_" + pm.getUbID() + ".html", destFolder, "attach_UBInvestResult_" + pm.getUbID() + ".html")){
			flag = true;
			KJFLog.log("EBMS 파일 전송 완료");
		}else{
			flag = false;
			KJFLog.log("EBMS 파일 전송 실패");
		}

    	
    	return flag;
    }
    
    /**
     * 전자문서의 전자결재용 송신파일 생성
     * @param form
     * @param filePath
     * @return
     * @throws Exception
     */
    public boolean makeEbmsFile(ActionForm form, String filePath)throws Exception{
    	UseBeforeParam pm = (UseBeforeParam)form;
    	boolean flag = false;
    	
    	String headerData 	= setHeaderFile(pm);	//header.inf 파일 내용
    	String exchangeData = setExchangeFile(pm);	//exchange.xml 파일 내용
    	String ozResultData = setAttachFile(pm);	//attach_UBInvestResult.html 파일 내용
    	
    	//header.inf 및  exchange.xml 파일 생성
    	if( makeFile(filePath + "header.inf", headerData, "euc-kr") 
    			&& makeFile(filePath + "exchange.xml", exchangeData, "euc-kr")
    			&& makeFile(filePath + "attach_UBInvestResult_" + pm.getUbID() + ".html", ozResultData, "utf-8")){
    		
    		//파일생성 성공
    		flag = true;
    	}
    	
    	return flag;
    }
    
    
    private boolean deleteEbmsFile(File path) {
        if( path.exists() ) {
          File[] files = path.listFiles();
          for(int i=0; i<files.length; i++) {
             if(files[i].isDirectory()) {
            	 deleteEbmsFile(files[i]);
             }
             else {
               files[i].delete();
             }
          }
        }
        return( path.delete() );
      }
    
    
    /**
     * 실제 파일 생성  함수
     * @param file
     * @param data
     * @param charset
     * @return
     */
    private boolean makeFile(String file, String data, String charset){
    	boolean flag = false;
    	
    	try{
    		
    		String f = file;
    		
    		BufferedWriter fw = null;
    		
    		File filePath = new File(f);
    		File fileDir = filePath.getParentFile();  
    		fileDir.mkdirs();
    		
    		fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), charset)); 
    		fw.write(data);    		
    	    fw.close();
    	    
    	    flag = true;
    	   
	    }catch(Exception e){
	    	KJFLog.log(e.toString());
	    	e.printStackTrace();
	    }
	    
	    return flag;
    }
    
    /**
     * 웹서비스를 이용하여 시군구 서버로 파일 전송 성공하면 true 반환
     * @param UbParam pm
     * @param String sourceFile
     * @param String destFolder
     * @param String destFileName
     * @return boolean
     * @throws ExceptionsendFile
     */
    private boolean sendFile(UseBeforeParam pm, String sourceFile, String destFolder, String destFileName) throws Exception {
		boolean flag = false;
		String key= KJFSec.encode(pm.getScSIGUNGU_CODE());
		
		UBAgentPortTypeProxy ub = new UBAgentPortTypeProxy(pm.getScServerAddr() + "/iccsWs/services/UBAgent");
		//UBAgentPortTypeProxy ub = new UBAgentPortTypeProxy("http://localhost/iccsWs/services/UBAgent");
		
		File file1 = new File(sourceFile);
		
		
		int result1 = ub.setEbmsFile(key
								,KJFFile.readFromFile(file1)
								,destFolder
								,destFileName);
		
		if(result1 == 1){
			flag = true;
		}
		
		return flag;
	}
    
    
    /**
     * 연계모듈의 헤더파일 정보 구조 데이터
     * @param UbParam pm
     * @return String
     * @throws Exception
     */
    private String setHeaderFile(UseBeforeParam pm)throws Exception{
    	StringBuffer content = new StringBuffer();
    	
    	content.append("type=send\n");
    	content.append("date=" 				+ pm.getSendDate()		+ "\n");
    	content.append("sender=" 			+ pm.getSendSysID()		+ "\n");
    	content.append("receiver=" 			+ pm.getRecvSysID()		+ "\n");
    	content.append("sender_userid=" 	+ pm.getSendUserID() 	+ "\n");
    	content.append("receiver_userid=" 	+ pm.getRecvUserID()	+ "\n");
    	content.append("sender_email=" 		+ pm.getSendUserEmail()	+ "\n");
    	content.append("sender_orgname="	+ pm.getSendOrgName()	+ "\n");
    	content.append("sender_systemname=" + pm.getSendSysName()	+ "\n");
    	content.append("administrative_num="+ pm.getAdmNum()		+ "\n");
    	
    	return content.toString();
    }
    
    /**
     * 연계모듈의 exchange.xml 파일 생성 구조 데이터
     * @param UbParam pm
     * @return String
     * @throws Exception
     */
    private String setExchangeFile(UseBeforeParam pm)throws Exception{
    	ReportDAO    rDAO        = new ReportDAO();
        ReportEntity rEntity     = null;
        
    	StringBuffer xmlData  = new StringBuffer();
    	
    	String CIV_RECV_NUM = "";
    	String APPLPER_NM = "";
    	String OPE_NAME = "";
    	String INSP_SPOT_ADDR = "";
    	String PROC_STE = "";
    	String NAPPL_YN = "";
    	String OFFICE_TYPE = "";
    	String WORK_ITEM = "";
    	
		String WORK_ITEM_ETC = "";
		String AREA = "";
		String USEBEFINSP_DELINUM = "";
		String INSP_SPOT_NM = "";
		
//    	String selectSQL=
//    		" SELECT CIV_RECV_NUM, APPLPER_NM, OPE_NAME, INSP_SPOT_ADDR, " +
//    		" INSP_SPOT_DETAILADDR ,PROC_STE, NAPPL_YN,PCC.CODE_NAME AS OFFICE_TYPE,WORK_ITEM   \n";

    	String selectSQL=
    		" SELECT CIV_RECV_NUM, APPLPER_NM, OPE_NAME, INSP_SPOT_ADDR, " +
    		" INSP_SPOT_DETAILADDR ,PROC_STE, NAPPL_YN,PCC.CODE_NAME AS OFFICE_TYPE,WORK_ITEM,"+
			" WORK_ITEM_ETC,AREA,USEBEFINSP_DELINUM,INSP_SPOT_NM \n"; //추가
    	
    	String fromSQL = " FROM PT_UB_USEBEFORE PUU, PT_COM_CODE PCC  \n";
    	
    	String whereSQL = "WHERE RECV_NUM = '"	+ pm.getAdmNum()		+"' \n" +
    					  "  AND  SIDO_CODE = '"	+ pm.getScSIDO_CODE()	+"' \n" +
    					  "  AND  SIGUNGU_CODE = '"	+ pm.getScSIGUNGU_CODE()+"' \n" +
    					  "  AND PUU.USE = PCC.CODE(+) AND PCC.P_CODE='BLDDIV'";
    					
    	
    	rEntity    = rDAO.select(selectSQL+fromSQL+whereSQL); 
    	
    	if(rEntity != null){
        	if(rEntity.getRowCnt() > 0){
        		CIV_RECV_NUM 	= KJFUtil.print(rEntity.getValue(0, "CIV_RECV_NUM"));
        		APPLPER_NM 		= KJFUtil.print(rEntity.getValue(0, "APPLPER_NM"));
        	    OPE_NAME 		= KJFUtil.print(rEntity.getValue(0, "OPE_NAME"));
        	    INSP_SPOT_ADDR 	= KJFUtil.print(rEntity.getValue(0, "INSP_SPOT_ADDR"))+ "" + KJFUtil.print(rEntity.getValue(0, "INSP_SPOT_DETAILADDR"));
        	    PROC_STE 		= "2".equals(KJFUtil.print(rEntity.getValue(0, "PROC_STE")) )?"적합":"부적합";
        	    NAPPL_YN 		= KJFUtil.print(rEntity.getValue(0, "NAPPL_YN"));
        	    OFFICE_TYPE 	= KJFUtil.print(rEntity.getValue(0, "OFFICE_TYPE"));
        	    WORK_ITEM 		= KJFUtil.print(rEntity.getValue(0, "WORK_ITEM"));

				WORK_ITEM_ETC   = KJFUtil.print(rEntity.getValue(0, "WORK_ITEM_ETC")); //추가
			    AREA			= KJFUtil.print(rEntity.getValue(0, "AREA"));          //추가 
			    USEBEFINSP_DELINUM = KJFUtil.print(rEntity.getValue(0, "USEBEFINSP_DELINUM")); //추가
				INSP_SPOT_NM    = KJFUtil.print(rEntity.getValue(0, "INSP_SPOT_NM"));  //추가
				
        	}
    	}
        	
    	xmlData.append("<?xml version=\"1.0\" encoding=\"euc-kr\"?>\n");
    	xmlData.append("<!DOCTYPE EXCHANGE SYSTEM \"exchange.dtd\">\n");
    	xmlData.append("<EXCHANGE>\n");
    	xmlData.append("	<HEADER>\n");
    	xmlData.append("		<COMMON>\n");
    	xmlData.append("			<SENDER>\n");
    	xmlData.append("				<SERVERID>" + pm.getSendSysID()		+ "</SERVERID>\n");
    	xmlData.append(" 				<USERID>"   + pm.getSendUserID() 	+ "</USERID>\n");
    	xmlData.append("				<EMAIL>"	+ pm.getSendUserEmail()	+ "</EMAIL>\n");
    	xmlData.append("			</SENDER>\n");
    	xmlData.append("			<RECEIVER>\n");
    	xmlData.append("				<SERVERID>" + pm.getRecvSysID()		+ "</SERVERID>\n");
    	xmlData.append("				<USERID>" 	+ pm.getRecvUserID()	+ "</USERID>\n");
    	xmlData.append("			</RECEIVER>\n");
    	xmlData.append("			<TITLE>사용전검사 검사결과(" + CIV_RECV_NUM.toUpperCase() + ")</TITLE>\n");
    	xmlData.append("			<CREATED_DATE>"	+ pm.getSendDate()		+ "</CREATED_DATE>\n");
    	xmlData.append("			<ATTACHNUM>1</ATTACHNUM>\n");
    	xmlData.append("			<ADMINISTRATIVE_NUM>"+ pm.getAdmNum()		+ "</ADMINISTRATIVE_NUM>\n");
    	xmlData.append("		</COMMON>\n");
    	xmlData.append("		<DIRECTION>\n");
    	xmlData.append("			<TO_DOCUMENT_SYSTEM>\n");
    	xmlData.append("				<MODIFICATION_FLAG>\n");
    	xmlData.append("					<MODIFIABLE modifyflag=\"yes\"/>\n");
    	xmlData.append("				</MODIFICATION_FLAG>\n");
    	xmlData.append("			</TO_DOCUMENT_SYSTEM>\n");
    	xmlData.append("		</DIRECTION>\n");
    	xmlData.append("	</HEADER>\n");
    	xmlData.append("	<BODY><![CDATA[\n");
    	xmlData.append("    정보통신공사업법 제36조의 규정에 의거, 아래 건축물에 대하여 정보통신공사의\n");
    	xmlData.append("     사용전검사를 실시한 결과입니다.\n");
    	xmlData.append(" 	\n\n");
    	xmlData.append("    ○ 검사내역 요약\n");
//    	xmlData.append("      민원 접수 번호 : " + CIV_RECV_NUM.toUpperCase() + " \n");
//    	xmlData.append("      신청인(건축주) : " + APPLPER_NM + " \n");
//    	xmlData.append("      시 공 업 체   : " + OPE_NAME + " \n");
//    	xmlData.append("      건 축 장 소   : " + INSP_SPOT_ADDR + " \n");
//    	xmlData.append("      검 사 결 과   : " + PROC_STE + " \n");
//    	xmlData.append("      건 물 용 도   : " + OFFICE_TYPE + " \n");
//    	xmlData.append("      공사의 종류   : " + WORK_ITEM + " \n");
    	xmlData.append("      민원 접수 번호 : " + CIV_RECV_NUM.toUpperCase() + " \n");
    	xmlData.append("      신청인(건축주) : " + APPLPER_NM + " \n");
    	xmlData.append("      시 공 업 체   : " + OPE_NAME + " \n");
    	xmlData.append("      건 축 장 소   : " + INSP_SPOT_ADDR + " \n");
		xmlData.append("      연   면  적   : " + INSP_SPOT_NM + " \n");    //추가
    	xmlData.append("      검 사 결 과   : " + PROC_STE + " \n");
    	xmlData.append("      건 물 용 도   : " + OFFICE_TYPE + " \n");
    	xmlData.append("      공사의 종류   : " + WORK_ITEM +","+ WORK_ITEM_ETC +" \n"); //수정
		xmlData.append("      교 부 번 호   : " + USEBEFINSP_DELINUM + " \n"); //추가
		
    	xmlData.append("     \n");
    	xmlData.append("     끝\n");
    	xmlData.append("	]]></BODY>\n");
    	xmlData.append("	<ATTACHMENTS>\n");
    	xmlData.append("		<ATTACHMENT filename=\"UBInvestResult_" + pm.getUbID() + ".html" + "\">사용전검사 결과 파일</ATTACHMENT>\n");
//    	xmlData.append("		<ADMINISTRATIVE_DB>\n");
//    	xmlData.append("			<XMLFILE filename=\"sample.xml\">전보대상자</XMLFILE>\n");
//    	xmlData.append("			<XSLFILE filename=\"sample.xsl\">전보대상자의스타일시트</XSLFILE>\n");
//    	xmlData.append("		</ADMINISTRATIVE_DB>\n");
    	xmlData.append("	</ATTACHMENTS>\n");
    	xmlData.append("</EXCHANGE>\n");
    	
    	return xmlData.toString();
    }
    
    /**
     * OZ 리보트 결과 보고서 생성 데이터(첨부물)
     * @param pm
     * @return
     * @throws Exception
     */
    private String setAttachFile(UseBeforeParam pm) throws Exception{
    	StringBuffer content = new StringBuffer();
    	
    	content.append("<html>\n");
        content.append("	<head>\n");
        content.append("		<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n");
        content.append("		<title>사용전검사 결과 보고서</title>\n");
        content.append("	</head>\n");
        content.append("	<body leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">\n");
        content.append("	<iframe src=\"http://iccsm.net.go.kr/oz2/UBInvestResultNotiR.jsp?scRECV_NUM="+pm.getAdmNum()+"&SIDO_CODE="+pm.getScSIDO_CODE()+"&SIGUNGU_CODE="+ pm.getScSIGUNGU_CODE()+"\" width=\"100%\" height=\"600\"></iframe>\n");
        content.append("	</body>\n");
        content.append("</html>\n");
        
        return content.toString();
    }
    
}