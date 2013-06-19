package sp.usebefore;

import javax.servlet.http.HttpServletRequest;

import kjf.util.KJFUtil;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

@SuppressWarnings("serial")
public class UseBeforeParam extends ActionForm {
   
	// 검색 조건 - 접두사 sc (serch condition)
    private String scCode;     	 	// 검색 코드
    private String scRecv_num;  	// 검색 접수번호
    private String recv_num;    	// 접수번호
    
    private String scRecvNum;   	// 검색 접수번호
    private String scRecvName;  	// 접수자 이름
    private String scRECV_ST;		// 검사일자 시작일
    private String scRECV_ET;		// 검사일자 종료일
    
    private String scOK;			// 합격
    private String scNO;			// 불합격
    private String scNO_2;			// 취하
    private String scNO_3;			// 이첩
    private String scCIV_RECV_NUM;	// 접수번호
    private String scINSP_SPOT_NM; 	//현장명칭
    
    private String scINSP_SPOT_ADDR; //현장주소
    private String scINSP_SPOT_DETAILADDR; //현장주소 상세정보
    private String scAPPLPER_NM;	//건축주
    private String scOPE_NAME;		//상호
    private String scNAPPL_YN;		//적합 및 비적합
    
    private String ScServerAddr;	// 지역 서버 주소    또는 전자결재 시군구 서버 정보
    private String scSIGUNGU_CODE;	// 시군구 코드 또는 전자결재 시군구 코드
   
    //전자결재용
    private String sendSysID;		//송신시스템 ID
    private String sendSysName;		//송신시스템명
    private String sendOrgName;		//송신시스템의 기관명
    private String sendUserID;		//송신자 ID
    private String sendUserEmail;	//송신자 이메일
    private String sendDate;		//송신일자 yyyy-MM-dd hh:MM:ss type
    private String sendDateNoFomate;//송신일자 yyyyMMddhhMMss + 난수(5자리) type
   	private String recvSysID;		//수신시스템 ID
    private String recvUserID;		//수신자 ID
    private String admNum;			//행정시스템의 고유식별 코드
    private String scSIDO_CODE;		//시도 코드
    private String ubID;				//이작업을 위한 호출당 고유번호(시간코드)
    private String succResult;		//전자결재 파일 전송 성공 유무 ajax 값 처리
    
    // 첨부 파일
    private FormFile[] BBS_FILE = new FormFile[10] ;
    
	// 페이징 관련 
    private String nowPage;
    private String totalCount;
    private String rowPerPage;
    
    /**
     * request로 부터 parameter의 해당 value를 얻어<br>
     * paramName : value 형식으로 출력
     * 
     * @param  request
     * @return String
     */
     public String toString(HttpServletRequest request){
        return KJFUtil.reportParameters(request) ;
     }
     
     public String getScCode() {
		return scCode;
	}
     
	public void setScCode(String scCode) {
		this.scCode = scCode;
	}

	public String getScRecv_num() {
        return scRecv_num;
    }

    public void setScRecv_num(String scRecv_num) {
        this.scRecv_num = scRecv_num;
    }    

    public String getRecv_num() {
		return recv_num;
	}

	public void setRecv_num(String recv_num) {
		this.recv_num = recv_num;
	}

    public String getScRecvName() {
        return scRecvName;
    }

    public void setScRecvName(String scRecvName) {
        this.scRecvName = scRecvName;
    }

    public String getNowPage() {
         return nowPage;
     }

     public void setNowPage(String nowPage) {
         this.nowPage = nowPage;
     }

     public String getTotalCount() {
         return totalCount;
     }

     public void setTotalCount(String totalCount) {
         this.totalCount = totalCount;
     }

     public String getRowPerPage() {
         return rowPerPage;
     }

     public void setRowPerPage(String rowPerPage) {
         this.rowPerPage = rowPerPage;
     }

	public String getScRECV_ST() {
		return scRECV_ST;
	}

	public void setScRECV_ST(String scRECV_ST) {
		this.scRECV_ST = scRECV_ST;
	}

	public String getScRECV_ET() {
		return scRECV_ET;
	}

	public void setScRECV_ET(String scRECV_ET) {
		this.scRECV_ET = scRECV_ET;
	}

	public String getScOK() {
		return scOK;
	}

	public void setScOK(String scOK) {
		this.scOK = scOK;
	}

	public String getScNO() {
		return scNO;
	}

	public void setScNO(String scNO) {
		this.scNO = scNO;
	}

	public String getScCIV_RECV_NUM() {
		return scCIV_RECV_NUM;
	}

	public void setScCIV_RECV_NUM(String scCIV_RECV_NUM) {
		this.scCIV_RECV_NUM = scCIV_RECV_NUM;
	}

	public String getScOPE_NAME() {
		return scOPE_NAME;
	}

	public void setScOPE_NAME(String scOPE_NAME) {
		this.scOPE_NAME = scOPE_NAME;
	}

	public FormFile[] getBBS_FILE() {
		return BBS_FILE;
	}

	public void setBBS_FILE(FormFile[] bbs_file) {
		BBS_FILE = bbs_file;
	}

	public String getScSIGUNGU_CODE() {
		return scSIGUNGU_CODE;
	}

	public void setScSIGUNGU_CODE(String scSIGUNGU_CODE) {
		this.scSIGUNGU_CODE = scSIGUNGU_CODE;
	}

	public String getScServerAddr() {
		return ScServerAddr;
	}

	public void setScServerAddr(String scServerAddr) {
		ScServerAddr = scServerAddr;
	}

	public String getSendSysID() {
		return sendSysID;
	}

	public void setSendSysID(String sendSysID) {
		this.sendSysID = sendSysID;
	}

	public String getSendSysName() {
		return sendSysName;
	}

	public void setSendSysName(String sendSysName) {
		this.sendSysName = sendSysName;
	}

	public String getSendOrgName() {
		return sendOrgName;
	}

	public void setSendOrgName(String sendOrgName) {
		this.sendOrgName = sendOrgName;
	}

	public String getSendUserID() {
		return sendUserID;
	}

	public void setSendUserID(String sendUserID) {
		this.sendUserID = sendUserID;
	}

	public String getSendUserEmail() {
		return sendUserEmail;
	}

	public void setSendUserEmail(String sendUserEmail) {
		this.sendUserEmail = sendUserEmail;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public String getSendDateNoFomate() {
		return sendDateNoFomate;
	}

	public void setSendDateNoFomate(String sendDateNoFomate) {
		this.sendDateNoFomate = sendDateNoFomate;
	}

	public String getRecvSysID() {
		return recvSysID;
	}

	public void setRecvSysID(String recvSysID) {
		this.recvSysID = recvSysID;
	}

	public String getRecvUserID() {
		return recvUserID;
	}

	public void setRecvUserID(String recvUserID) {
		this.recvUserID = recvUserID;
	}

	public String getAdmNum() {
		return admNum;
	}

	public void setAdmNum(String admNum) {
		this.admNum = admNum;
	}

	public String getScSIDO_CODE() {
		return scSIDO_CODE;
	}

	public void setScSIDO_CODE(String scSIDO_CODE) {
		this.scSIDO_CODE = scSIDO_CODE;
	}

	public String getUbID() {
		return ubID;
	}

	public void setUbID(String ubID) {
		this.ubID = ubID;
	}

	public String getSuccResult() {
		return succResult;
	}

	public void setSuccResult(String succResult) {
		this.succResult = succResult;
	}

	public String getScINSP_SPOT_NM() {
		return scINSP_SPOT_NM;
	}

	public void setScINSP_SPOT_NM(String scINSP_SPOT_NM) {
		this.scINSP_SPOT_NM = scINSP_SPOT_NM;
	}

	public String getScAPPLPER_NM() {
		return scAPPLPER_NM;
	}

	public void setScAPPLPER_NM(String scAPPLPER_NM) {
		this.scAPPLPER_NM = scAPPLPER_NM;
	}

	public String getScNAPPL_YN() {
		return scNAPPL_YN;
	}

	public void setScNAPPL_YN(String scNAPPL_YN) {
		this.scNAPPL_YN = scNAPPL_YN;
	}

	public String getScRecvNum() {
		return scRecvNum;
	}

	public void setScRecvNum(String scRecvNum) {
		this.scRecvNum = scRecvNum;
	}

	public String getScINSP_SPOT_ADDR() {
		return scINSP_SPOT_ADDR;
	}

	public void setScINSP_SPOT_ADDR(String scINSP_SPOT_ADDR) {
		this.scINSP_SPOT_ADDR = scINSP_SPOT_ADDR;
	}

	public String getScINSP_SPOT_DETAILADDR() {
		return scINSP_SPOT_DETAILADDR;
	}

	public void setScINSP_SPOT_DETAILADDR(String scINSP_SPOT_DETAILADDR) {
		this.scINSP_SPOT_DETAILADDR = scINSP_SPOT_DETAILADDR;
	}

	public String getScNO_2() {
		return scNO_2;
	}

	public void setScNO_2(String scNO_2) {
		this.scNO_2 = scNO_2;
	}

	public String getScNO_3() {
		return scNO_3;
	}

	public void setScNO_3(String scNO_3) {
		this.scNO_3 = scNO_3;
	}

}
