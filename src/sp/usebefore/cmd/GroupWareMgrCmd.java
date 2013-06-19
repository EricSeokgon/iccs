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

import org.apache.struts.action.ActionForm;

import sp.usebefore.UseBeforeParam;
import sp.webservice.UBAgentPortTypeProxy;

public class GroupWareMgrCmd implements KJFCommand {
	
//	private String FILE_PATH = null;
    private String send_file_name = "";
    private String send_invest_file_name ="";
	
    private String REG_NUM = "";		// 접수번호 (시스템)
    private String CIV_RECV_NUM = "";	// 민원접수접호
    private String APPLPER_NM = "";		// 신청인 건축주
    private String OPE_NAME = "";		// 시공자 상호명
    private String INSP_SPOT_ADDR = "";	// 시공현장 주소
    private String NAPPL_YN = "";		// 검사결과(적합/부적합)
    private String NAPPL_CAUSE ="";		// 검사결과(부적합 사유)
    private String OFFICE_TYPE = "";	// 건축물 용도
    private String WORK_ITEM = "";		// 검사대상공사
    private String RECV_DT = "";		// 접수일자
    private String WORK_ITEM_ETC = "";	// 방송공동수신 설비
    private String AREA = "";			// 면적
    private String NUM_FL = "";			// 층수
    private String BEF_PLANER_NAME = "";// 설계 업체명
    private String APPLPER_REP = ""; 	// 대표자
    private String COI_WRT_NUM = "";	// 공사업번호
    private String INSP_DT = "";		// 검사일
    
    public GroupWareMgrCmd() {
    }
 
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {
    	UseBeforeParam pm 		= (UseBeforeParam)form;
    	
    	// 사용전검사 기초데이터 설정
    	loadDataFromPm(request,pm);

    	ebms_Query(pm);
    	
    	//사용전검사 결과보고
    	ebmsCall(pm);
    	
    	if ("적합".equals(pm.getScNAPPL_YN())){
	    	//사용전검사 필증교부
	    	ebmsCallInvest(pm);
	        /*  전자결재 문서 호출 작업 끝 */
    	} else {
	    	//사용전검사 필증교부
	    	ebmsCall_NAP_NO(pm); // 민원인 기안문
	    	//ebmsCall_NAP_INNER_NO(pm);
	    	ebmsCall_NAP_SIDO_NO(pm); // 시도 기안문
    	}
    	request.setAttribute("pm", pm);
        return request.getParameter("cmd");
    }
 
    
    /**
     * 사용전검사 기초데이터 설정
     * @param form
     * @param filePath
     * @return
     * @throws Exception
     */
    public void loadDataFromPm(HttpServletRequest request , UseBeforeParam pm)throws Exception{
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
    }
    
    
  public void ebms_Query(UseBeforeParam pm) throws Exception {
    	
    	ReportDAO    rDAO        = new ReportDAO();
        ReportEntity rEntity     = null;

        String selectSQL=
    		" SELECT CIV_RECV_NUM, APPLPER_NM, OPE_NAME, INSP_SPOT_ADDR, " +
    		" INSP_SPOT_DETAILADDR ,PROC_STE, NAPPL_YN,PCC.CODE_NAME AS OFFICE_TYPE,WORK_ITEM,"+
			" WORK_ITEM_ETC,AREA,USEBEFINSP_DELINUM,INSP_SPOT_NM,RECV_DT,NAPPL_CAUSE, NUM_FL,BEF_PLANER_NAME, APPLPER_REP,COI_WRT_NUM,INSP_DT \n"; //추가
    	
    	String fromSQL = " FROM PT_UB_USEBEFORE PUU, PT_COM_CODE PCC  \n";
    	
    	String whereSQL = "WHERE RECV_NUM = '"	+ pm.getAdmNum()		+"' \n" +
    					  "  AND  SIDO_CODE = '"	+ pm.getScSIDO_CODE()	+"' \n" +
    					  "  AND  SIGUNGU_CODE = '"	+ pm.getScSIGUNGU_CODE()+"' \n" +
    					  "  AND PUU.USE = PCC.CODE(+) --AND PCC.P_CODE='BLDDIV'";
    	
    	rEntity    = rDAO.select(selectSQL+fromSQL+whereSQL); 
    	
    	if(rEntity != null){
        	if(rEntity.getRowCnt() > 0){
        		CIV_RECV_NUM 	= KJFUtil.print(rEntity.getValue(0, "CIV_RECV_NUM"));
        		APPLPER_NM 		= KJFUtil.print(rEntity.getValue(0, "APPLPER_NM"));
        	    OPE_NAME 		= KJFUtil.print(rEntity.getValue(0, "OPE_NAME"));
        	    INSP_SPOT_ADDR 	= KJFUtil.print(rEntity.getValue(0, "INSP_SPOT_ADDR"))+ "" + KJFUtil.print(rEntity.getValue(0, "INSP_SPOT_DETAILADDR"));
        	    NAPPL_YN 		= "1".equals(KJFUtil.print(rEntity.getValue(0, "NAPPL_YN")) )?"적합":"부적합";//KJFUtil.print(rEntity.getValue(0, "NAPPL_YN"));
        	    OFFICE_TYPE 	= KJFUtil.print(rEntity.getValue(0, "OFFICE_TYPE"));
        	    WORK_ITEM 		= KJFUtil.print(rEntity.getValue(0, "WORK_ITEM"),"").replace("//", "/");
        	    RECV_DT			= KJFDate.getChangDatePattern(KJFUtil.print(rEntity.getValue(0, "RECV_DT")),"yyyyMMdd","yyyy.MM.dd");
				
        	    //WORK_ITEM_ETC   = KJFUtil.print(rEntity.getValue(0, "WORK_ITEM_ETC"),"").replace("//", "/").replace("종합유선방송", "종합유선");
        	    WORK_ITEM_ETC   = KJFUtil.print(rEntity.getValue(0, "WORK_ITEM_ETC"),"").replace("//", "/");
        	    
        	    if (!"".equals(WORK_ITEM_ETC)){
	        	    if (WORK_ITEM_ETC.indexOf("방송공동수신설비(") >=0){
	        	    	WORK_ITEM_ETC = WORK_ITEM_ETC.replace("수신설비","수신")+"설비"; 
	        	    }
        	    }
        	    
			    AREA			= KJFUtil.print(rEntity.getValue(0, "AREA"));          //추가 
				NAPPL_CAUSE		= KJFUtil.print(rEntity.getValue(0, "NAPPL_CAUSE"));
				NUM_FL			= KJFUtil.print(rEntity.getValue(0, "NUM_FL"));
				BEF_PLANER_NAME	= KJFUtil.print(rEntity.getValue(0, "BEF_PLANER_NAME"));
				APPLPER_REP		= KJFUtil.print(rEntity.getValue(0, "APPLPER_REP"));
				COI_WRT_NUM		= KJFUtil.print(rEntity.getValue(0, "COI_WRT_NUM"));
				INSP_DT			= KJFDate.getChangDatePattern(KJFUtil.print(rEntity.getValue(0, "INSP_DT")),"yyyyMMdd","yyyy.MM.dd");//KJFUtil.print(rEntity.getValue(0, "INSP_DT"));
				
				pm.setScAPPLPER_NM(APPLPER_NM);
				pm.setScOPE_NAME(OPE_NAME);
				pm.setScNAPPL_YN(NAPPL_YN);
				pm.setScCIV_RECV_NUM(CIV_RECV_NUM.toUpperCase());
				
				if(CIV_RECV_NUM.length()>10){
					REG_NUM = Integer.toString( KJFUtil.str2int(pm.getScCIV_RECV_NUM().substring(11, pm.getScCIV_RECV_NUM().length())) );
				} else {
					REG_NUM = pm.getScCIV_RECV_NUM();
				}
				
				
		    	if(!KJFUtil.isEmpty(NUM_FL)){
		    		NUM_FL = NUM_FL+"/";
        		}
		    	
		       	if(!KJFUtil.isEmpty(WORK_ITEM)){
			    	if ("/".equals(WORK_ITEM.substring(0,1))){
			    		WORK_ITEM = WORK_ITEM.substring(1,WORK_ITEM.length());
			    	}
			    	if (WORK_ITEM.lastIndexOf("/") >=0){
			    		WORK_ITEM = WORK_ITEM.substring(0, WORK_ITEM.length()-1);
			    	}
			    	
			    	if(!"".equals(WORK_ITEM_ETC)){
			    		WORK_ITEM = WORK_ITEM +",";
			    	}
		    	}
		       	
        	}
    	}
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
				&& sendFile(pm, filePath + "attach_"+send_file_name + ".html", destFolder, "attach_"+send_file_name + ".html")){
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
    			&& makeFile(filePath + "attach_"+send_file_name + ".html", ozResultData, "utf-8")){
    		
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
     * @param UseBeforeParam pm
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
		//int result1 = 1;
		if(result1 == 1){
			flag = true;
		}
		
		return flag;
	}
    
    
    /**
     * 연계모듈의 헤더파일 정보 구조 데이터
     * @param UseBeforeParam pm
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
     * @param UseBeforeParam pm
     * @return String
     * @throws Exception
     */
    private String setExchangeFile(UseBeforeParam pm)throws Exception{
        
    	StringBuffer xmlData  = new StringBuffer();

    	if ("".equals(pm.getScAPPLPER_NM())){
    		send_file_name = "결과보고서()_"+REG_NUM;    		
    	} else {
    		send_file_name = "결과보고서("+pm.getScAPPLPER_NM().trim()+"("+pm.getScOPE_NAME().trim()+")"+pm.getScNAPPL_YN().trim()+")_"+REG_NUM;    		
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
    	//xmlData.append("			<TITLE>정보통신공사 사용전검사 결과 보고(" + CIV_RECV_NUM.toUpperCase() + ")</TITLE>\n");
    	xmlData.append("			<TITLE>정보통신공사 사용전검사 결과보고(" + REG_NUM + ")</TITLE>\n");
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
    	xmlData.append("	<BODY><![CDATA[");
    	xmlData.append("        정보통신공사 사용전검사 신청건(접수번호-"+REG_NUM+" : " + RECV_DT + ")에 대하여\n");
    	xmlData.append("정보통신공사업법 제36조 및 같은 법 시행령 제 36조 규정에 의하여 검사한 결과를 보고합니다.\n");
    	xmlData.append("        ▣ 검사내역\n");
    	xmlData.append("          - 신청인(건축주) : " + APPLPER_NM + "\n");
    	xmlData.append("          - 공사의 종류 : " + WORK_ITEM +","+ WORK_ITEM_ETC +"\n"); //수정
    	xmlData.append("          - 건축장소 : " + INSP_SPOT_ADDR + "\n");


    	xmlData.append("          - 용도 : "+ OFFICE_TYPE +"("+ NUM_FL +AREA + "㎡)\n");    //추가
    	xmlData.append("          - 설계 및 시공업체 : " + BEF_PLANER_NAME + "/" +OPE_NAME + "\n");
    	xmlData.append("          - 검사결과 : "+NAPPL_YN+"\n");
		
    	if("부적합".equals(NAPPL_YN )){
    		xmlData.append("      부적합 사유   : " + NAPPL_CAUSE + "\n"); //추가
    	}
    	
    	xmlData.append("	]]></BODY>\n");
    	xmlData.append("	<ATTACHMENTS>\n");
    	xmlData.append("		<ATTACHMENT filename=\""+send_file_name+".html\">사용전검사 결과 파일</ATTACHMENT>\n");
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
        content.append("	<iframe src=\"http://99.1.5.83/oz2/UBInvestResultNotiR.jsp?scRECV_NUM="+pm.getAdmNum()+"&SIDO_CODE="+pm.getScSIDO_CODE()+"&SIGUNGU_CODE="+ pm.getScSIGUNGU_CODE()+"\" width=\"100%\" height=\"600\"></iframe>\n");
        content.append("	</body>\n");
        content.append("</html>\n");
        
        return content.toString();
    }
    
    
/*******************************************************************************/

/*******************************************************************************/

    /**
     * 전자문서 연동 메인 함수(이것만 호출하면 됨)
     * @param pm
     * @throws Exception
     */
    public void ebmsCallInvest(UseBeforeParam pm ) throws Exception { 
    	    	
    	Random rand = new Random(System.currentTimeMillis());
    	pm.setSuccResult("1");
    	String destFolder = pm.getRecvUserID()
							+ pm.getSendSysID()
							+ pm.getRecvSysID()
							+ pm.getSendDateNoFomate()
							+ (rand.nextInt(9999) + 10000);

    	String dirPath = Config.props.get("EBMS_FILE_DIR");
    	String embsPath = dirPath + pm.getSendUserID() + KJFFile.FILE_S + destFolder;
    	String filePath = embsPath + KJFFile.FILE_S;
    	
    	if(!makeEbmsFileInvest(pm, filePath)){
    		KJFLog.log("파일 생성 실패");
    		pm.setSuccResult("0");
    	}else{
    		KJFLog.log("파일 생성 완료");
    		if(!sendEbmsFileInvest(pm, filePath, destFolder)){
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
     * 전자문서의 전자결재용 송신파일 생성
     * @param form
     * @param filePath
     * @return
     * @throws Exception
     */
    public boolean makeEbmsFileInvest(ActionForm form, String filePath)throws Exception{
    	UseBeforeParam pm = (UseBeforeParam)form;
    	boolean flag = false;
    	
    	String headerData 	= setHeaderFile(pm);	//header.inf 파일 내용
    	String exchangeData = setExchangeFileInvest(pm);	//exchange.xml 파일 내용
    	String ozResultData = setAttachFileInvest(pm);	//attach_UBInvestResult.html 파일 내용

    	//header.inf 및  exchange.xml 파일 생성
    	if( makeFile(filePath + "header.inf", headerData, "euc-kr") 
    			&& makeFile(filePath + "exchange.xml", exchangeData, "euc-kr")
    			&& makeFile(filePath + "attach_"+send_invest_file_name + ".html", ozResultData, "utf-8")){
    		
    		//파일생성 성공
    		flag = true;
    	}
    	
    	return flag;
    }
    /**
     * 생성된 파일을 시군구 서버로 전송한다.
     * @param ActionForm form
     * @param String filePath
     * @param String destFolder
     * @return boolean
     * @throws Exception
     */
    public boolean sendEbmsFileInvest(ActionForm form, String filePath, String destFolder)throws Exception{
    	UseBeforeParam pm = (UseBeforeParam)form;
    	boolean flag = false;
 
		if(sendFile(pm, filePath + "header.inf", destFolder, "header.inf")
				&& sendFile(pm, filePath + "exchange.xml", destFolder, "exchange.xml")
				&& sendFile(pm, filePath + "attach_"+send_invest_file_name + ".html", destFolder, "attach_"+send_invest_file_name + ".html")){
			flag = true;
			KJFLog.log("EBMS 파일 전송 완료");
		}else{
			flag = false;
			KJFLog.log("EBMS 파일 전송 실패");
		}

    	
    	return flag;
    }    
    /**
     * 연계모듈의 exchange.xml 파일 생성 구조 데이터
     * @param UseBeforeParam pm
     * @return String
     * @throws Exception
     */
    private String setExchangeFileInvest(UseBeforeParam pm)throws Exception{

    	StringBuffer xmlData  = new StringBuffer();
    	
   		send_invest_file_name = "정보통신공사사용전검사필증1부_"+REG_NUM;
   		
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
    	xmlData.append("			<TITLE>정보통신공사 사용전검사 필증 교부(접수번호-"+ REG_NUM +")</TITLE>\n");
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
    	xmlData.append("	<BODY><![CDATA[");
    	xmlData.append("        1. 평소 정보통신 업무에 적극 협조하여 주심을 감사 드립니다.\n");
    	xmlData.append("        2. 귀하께서 신청하신 아래 건축물(접수번호-"+REG_NUM+" : " + RECV_DT + ")에 대하여 정보\n");
    	xmlData.append("통신공사업법 제36조  및 같은 법 시행령 제 36조 규정에 의하여 검사한 결과 적합하여 붙임\n");
    	xmlData.append("과 같이 정보통신공사 사용전 검사 필증을 교부합니다.\n");    	
    	xmlData.append("        ▣ 교부내역\n");
    	xmlData.append("          - 신청인(건축주) : " + APPLPER_NM + "\n");
    	xmlData.append("          - 공사의 종류 : " + WORK_ITEM + WORK_ITEM_ETC +"\n"); //수정    	
    	xmlData.append("          - 건축장소 : " + INSP_SPOT_ADDR + " \n");  
    	xmlData.append("          - 용도 : "+ OFFICE_TYPE +"("+ NUM_FL + AREA + "㎡)\n");    //추가
		xmlData.append("          - 설계 및 시공업체 : " + BEF_PLANER_NAME + " " +OPE_NAME + "\n");
		xmlData.append("          - 검사결과 : " + NAPPL_YN + "\n");
    	if("부적합".equals(NAPPL_YN )){
    		xmlData.append("      	  - 부적합 내용   : " + NAPPL_CAUSE + "\n"); //추가
    	}
    	xmlData.append("	]]></BODY>\n");
    	xmlData.append("	<ATTACHMENTS>\n");
    	xmlData.append("		<ATTACHMENT filename=\"" + send_invest_file_name + ".html" + "\">사용전검사필증1부파일</ATTACHMENT>\n");
    	xmlData.append("	</ATTACHMENTS>\n");
    	xmlData.append("</EXCHANGE>\n");
    	
    	return xmlData.toString();
    }    
    /**
     * OZ 리보트 사용전검사필증 보고서 생성 데이터(첨부물)
     * @param pm
     * @return
     * @throws Exception
     */
    private String setAttachFileInvest(UseBeforeParam pm) throws Exception{
    	StringBuffer content = new StringBuffer();
    	
    	content.append("<html>\n");
        content.append("	<head>\n");
        content.append("		<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n");
        content.append("		<title>정보통신공사 사용전검사필증</title>\n");
        content.append("	</head>\n");
        content.append("	<body leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">\n");
        content.append("	<iframe src=\"http://99.1.5.83/oz2/UBInvestR.jsp?scRECV_NUM="+pm.getAdmNum()+"&SIDO_CODE="+pm.getScSIDO_CODE()+"&SIGUNGU_CODE="+ pm.getScSIGUNGU_CODE()+"\" width=\"100%\" height=\"600\"></iframe>\n");
        content.append("	</body>\n");
        content.append("</html>\n");
        
        return content.toString();
    } 
    
    
    /**
     * 
     *  전자결재 부적합시 기안문 양식
     *  1. 민원인 수신 ebmsCall_NAP_NO
     *  2. 시도 수신   ebmsCall_NAP_SIDO_NO
     */
 
    
    /**
     * 전자문서 연동 메인 함수(이것만 호출하면 됨)  [민원 기안문] 
     * @param pm
     * @throws Exception
     */
    public void ebmsCall_NAP_NO(UseBeforeParam pm ) throws Exception { 
    	    	
    	Random rand = new Random(System.currentTimeMillis());
    	String destFolder = pm.getRecvUserID()
							+ pm.getSendSysID()
							+ pm.getRecvSysID()
							+ pm.getSendDateNoFomate()
							+ (rand.nextInt(9999) + 10000);
    	
    	String dirPath = Config.props.get("EBMS_FILE_DIR");
    	String embsPath = dirPath + pm.getSendUserID() + KJFFile.FILE_S + destFolder;
    	String filePath = embsPath + KJFFile.FILE_S;
    	
    	if(!makeEbmsFile_NAP_NO(pm, filePath)){
    		KJFLog.log("파일 생성 실패3");
    	}else{
    		KJFLog.log("파일 생성 완료3");
    		if(!sendEbmsFile_NAP_NO(pm, filePath, destFolder)){
    			KJFLog.log("파일 전송 실패3");
        	}else{
        		KJFLog.log("파일 전송 완료3");
        	}
    	}
    	
    	while(true){
            try{
            	KJFLog.log("파일 전송3 eof.inf");
            	Thread.sleep(1000);            	
            }catch(Exception e){
                System.out.println(e.toString());
            }
            break;
        }
    	
    	if(sendFile(pm, dirPath + "eof.inf", destFolder, "eof.inf")){
    		KJFLog.log("파일 전송2 eof.inf완료");
    		File ebmsFiles = new File(embsPath);
    		
    		if(deleteEbmsFile(ebmsFiles)){
    			KJFLog.log(destFolder + " : ebms3 file deleted!");
    		}else{
    			KJFLog.log(destFolder + " : ebms3 file delete fail!");
    		}
    	}
    }
    
    /**
     * 전자문서의 전자결재용 송신파일 생성
     * @param form
     * @param filePath
     * @return
     * @throws Exception
     */
    public boolean makeEbmsFile_NAP_NO(ActionForm form, String filePath)throws Exception{
    	UseBeforeParam pm = (UseBeforeParam)form;
    	boolean flag = false;
    	
    	String headerData 	= setHeaderFile(pm);	//header.inf 파일 내용
    	String exchangeData = setExchangeFile_NAP_NO(pm);	//exchange.xml 파일 내용

    	if( makeFile(filePath + "header.inf", headerData, "euc-kr") 
    			&& makeFile(filePath + "exchange.xml", exchangeData, "euc-kr")
    			){
    		   		
    		//파일생성 성공
    		flag = true;
    	}
    	
    	return flag;
    }
    
    /**
     * 연계모듈의 exchange.xml 파일 생성 구조 데이터
     * @param UseBeforeParam pm
     * @return String
     * @throws Exception
     */
    private String setExchangeFile_NAP_NO(UseBeforeParam pm)throws Exception{
    	StringBuffer xmlData  = new StringBuffer();
   
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
    	xmlData.append("			<TITLE>정보통신공사 사용전검사 부적합 따른 보완 통보</TITLE>\n");
    	xmlData.append("			<CREATED_DATE>"	+ pm.getSendDate()		+ "</CREATED_DATE>\n");
    	xmlData.append("			<ATTACHNUM>0</ATTACHNUM>\n");
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
    	xmlData.append("	<BODY><![CDATA[");
    	xmlData.append("        1. 평소 정보통신 업무에 적극 협조하여 주심을 감사 드립니다.\n");
    	xmlData.append("        2. 귀하께서 신청하신 아래 건축물(민원접수번호-"+REG_NUM+" : "+RECV_DT+")에 대하여 정보\n");
    	xmlData.append("통신공사업법 제 36조 및  동법시행령 제26조의 규정에 의하여 검사한 결과 관련 기술에\n");
    	xmlData.append("부적합하게 시공된 부분이 있어 통보하오니 재시공후 재검사를 신청하시기 바랍니다.\n");       	
    	xmlData.append("        ▣ 사용전검사 신청 내역\n");
    	xmlData.append("          - 신청인(건축주) : " + APPLPER_NM + "\n");   	
    	xmlData.append("          - 공사의 종류 : " + WORK_ITEM + WORK_ITEM_ETC +"\n");
    	xmlData.append("          - 건축장소 : " + INSP_SPOT_ADDR + "\n");  
    	

    	xmlData.append("          - 용도 및 연면적 : " + OFFICE_TYPE +"("+ NUM_FL +AREA + "㎡)\n");    //추가
    	xmlData.append("          - 설계 및 공사업체 : " + OPE_NAME + "\n");
		xmlData.append("          - 검사결과 : " + NAPPL_YN + "\n");
		
    	if("부적합".equals(NAPPL_YN )){
    	xmlData.append("        ▣ 부적합 내용 : " + NAPPL_CAUSE + "\n"); //추가
    	}
    	
    	xmlData.append("	]]></BODY>\n");
    	xmlData.append("</EXCHANGE>\n");
    	
    	return xmlData.toString();
    }   
    
    /**
     * 생성된 파일을 시군구 서버로 전송한다.
     * @param ActionForm form
     * @param String filePath
     * @param String destFolder
     * @return boolean
     * @throws Exception
     */
    public boolean sendEbmsFile_NAP_NO(ActionForm form, String filePath, String destFolder)throws Exception{
    	UseBeforeParam pm = (UseBeforeParam)form;
    	boolean flag = false;

    	if(sendFile(pm, filePath + "header.inf", destFolder, "header.inf")
				&& sendFile(pm, filePath + "exchange.xml", destFolder, "exchange.xml")
				){
			flag = true;
			KJFLog.log("EBMS 파일 전송 완료");
		}else{
			flag = false;
			KJFLog.log("EBMS 파일 전송 실패");
		}
		
    	return flag;
    }    
    
    /**
     * 전자문서 연동 메인 함수(이것만 호출하면 됨)  [시도 기안문]
     * @param pm
     * @throws Exception
     */
    public void ebmsCall_NAP_SIDO_NO(UseBeforeParam pm ) throws Exception { 
    	    	
    	Random rand = new Random(System.currentTimeMillis());
    	String destFolder = pm.getRecvUserID()
							+ pm.getSendSysID()
							+ pm.getRecvSysID()
							+ pm.getSendDateNoFomate()
							+ (rand.nextInt(9999) + 10000);
    	
    	//String dirPath = "D:\\work\\attache\\iccsm\\usebefore\\_ebms_dir\\";
    	String dirPath = Config.props.get("EBMS_FILE_DIR");
    	String embsPath = dirPath + pm.getSendUserID() + KJFFile.FILE_S + destFolder;
    	String filePath = embsPath + KJFFile.FILE_S;
    	
    	if(!makeEbmsFile_NAP_SIDO_NO(pm, filePath)){
    		KJFLog.log("파일 생성 실패3 SIDO");
    	}else{
    		KJFLog.log("파일 생성 완료3");
    		if(!sendEbmsFile_NAP_SIDO_NO(pm, filePath, destFolder)){
    			KJFLog.log("파일 전송 실패3 SIDO");
        	}else{
        		KJFLog.log("파일 전송 완료3 SIDO");
        	}
    	}
    	
    	while(true){
            try{
            	KJFLog.log("파일 전송3 eof.inf");
            	Thread.sleep(1000);            	
            }catch(Exception e){
                System.out.println(e.toString());
            }
            break;
        }
    	
    	if(sendFile(pm, dirPath + "eof.inf", destFolder, "eof.inf")){
    		KJFLog.log("파일 전송 SIDO eof.inf완료");
    		File ebmsFiles = new File(embsPath);
    		
    		if(deleteEbmsFile(ebmsFiles)){
    			KJFLog.log(destFolder + " : ebms3 SIDO file deleted!");
    		}else{
    			KJFLog.log(destFolder + " : ebms3 SIDO file delete fail!");
    		}
    	}
    }
    
    /**
     * 전자문서의 전자결재용 송신파일 생성
     * @param form
     * @param filePath
     * @return
     * @throws Exception
     */
    public boolean makeEbmsFile_NAP_SIDO_NO(ActionForm form, String filePath)throws Exception{
    	UseBeforeParam pm = (UseBeforeParam)form;
    	boolean flag = false;
    	
    	String headerData 	= setHeaderFile(pm);	//header.inf 파일 내용
    	String exchangeData = setExchangeFile_NAP_SIDO_NO(pm);	//exchange.xml 파일 내용

    	if( makeFile(filePath + "header.inf", headerData, "euc-kr") 
    			&& makeFile(filePath + "exchange.xml", exchangeData, "euc-kr")
    			){
    		   		
    		//파일생성 성공
    		flag = true;
    	}
    	
    	return flag;
    }
    
    /**
     * 연계모듈의 exchange.xml 파일 생성 구조 데이터
     * @param UseBeforeParam pm
     * @return String
     * @throws Exception
     */
    private String setExchangeFile_NAP_SIDO_NO(UseBeforeParam pm)throws Exception{
    	StringBuffer xmlData  = new StringBuffer();
   
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
    	xmlData.append("			<TITLE>정보통신공사 사용전검사 부적합 업체 1차 알림</TITLE>\n");
    	xmlData.append("			<CREATED_DATE>"	+ pm.getSendDate()		+ "</CREATED_DATE>\n");
    	xmlData.append("			<ATTACHNUM>0</ATTACHNUM>\n");
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
    	xmlData.append("	<BODY><![CDATA[");
    	xmlData.append("        강서구 민원봉사과-"+REG_NUM+" : "+RECV_DT+"호로 접수된 정보통신공사 사용전 검사 신청건과\n");
    	xmlData.append(" 관련하여 정보통신공사업법 제36조  및 같은 법 시행령 제 36조 규정에 의하여 검사한 결과,\n");
    	xmlData.append("관련 기술기준에 부적합하게 시공된 부분이 있어 부적합 처리하였음을 알려드립니다.\n");
    	xmlData.append("        1. 업체명 : " + APPLPER_NM +" 대표자 "+ APPLPER_REP+" [등록번호 제"+COI_WRT_NUM+"호]\n");
    	xmlData.append("        2. 검사일 : " + INSP_DT + "\n");   	
    	xmlData.append("        3. 부적합현장 : " + INSP_SPOT_ADDR +"\n");

    	xmlData.append("        4. 건축물용도 : " + OFFICE_TYPE +"("+ NUM_FL + AREA + "㎡)\n");
    	xmlData.append("        5. 부적합 사유 : " + NAPPL_CAUSE + "\n");
		
    	xmlData.append("	]]></BODY>\n");
    	xmlData.append("</EXCHANGE>\n");
    	
    	return xmlData.toString();
    }   
    
    /**
     * 생성된 파일을 시군구 서버로 전송한다.
     * @param ActionForm form
     * @param String filePath
     * @param String destFolder
     * @return boolean
     * @throws Exception
     */
    public boolean sendEbmsFile_NAP_SIDO_NO(ActionForm form, String filePath, String destFolder)throws Exception{
    	UseBeforeParam pm = (UseBeforeParam)form;
    	boolean flag = false;

    	if(sendFile(pm, filePath + "header.inf", destFolder, "header.inf")
				&& sendFile(pm, filePath + "exchange.xml", destFolder, "exchange.xml")
				){
			flag = true;
			KJFLog.log("EBMS SIDO 파일 전송 완료");
		}else{
			flag = false;
			KJFLog.log("EBMS SIDO 파일 전송 실패");
		}
		
    	return flag;
    }   
}