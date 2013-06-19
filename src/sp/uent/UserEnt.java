package sp.uent;

import kjf.cfg.Config;
import kjf.ops.ReportEntity;
import kjf.util.KJFLog;
import kjf.util.KJFUtil;
import sp.login.LoginWorker;
import sp.util.Crypto;

/**
 * <p>Title       : UserEnt Class</p>
 * <p>Description : 사용자 정보를 처리 한다.</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE :사용자 정보 처리를 한다. 
 * 
 * @version 1.0
 * @author PKT
 */
public class UserEnt {

    private String USER_ID;         // 사용자 아이디
    private String USER_PASSWORD;   // 사용자 비번
    private String USER_NAME;       // 사용자 명
    private String USER_TEL;        // 사용자 전화번호
    private String USER_MOBILE;     // 사용자 핸드폰번호
    private String USER_EMAIL;      // 사용자 E-mail
    private String SIDO_CODE;       // 시도코드
    private String SIGUNGU_CODE;    // 시군구 코드
    private String COM_NAME;		// 회사명
    private String COM_NUM;         // 사업자등록번호
    private String REG_NUM;         // 공사업등록번호
    
    private String CAPITAL;         // 사용권한 
    private String LogNum;          // 로그인 번호
    
    private boolean isUseUbSys = false;        // 사용전검사 시스템 사용여부
    private boolean isAdmin;        // 관리자여부
    
	public UserEnt() {
	}
	
	public UserEnt(String strID) throws Exception {
		this(strID, "user");
	}

	public UserEnt(String strID, String type) throws Exception {      
        
		ReportEntity rEntity = new ReportEntity();
		
		LoginWorker worker = new LoginWorker();
		
		if (type.equals("admin")) {
		    
			KJFLog.log("관리자 UserEnt 생성..");
			
			// 관리자
			rEntity = worker.loadUserFromDBAdmin(strID);
			
	    	if (rEntity.getRowCnt() > 0) {
	    	     
	            this.USER_ID       = rEntity.getValue(0, "OFFI_ID");
                this.USER_PASSWORD = Crypto.Decode(rEntity.getValue(0, "PASS"));
	            this.USER_NAME     = rEntity.getValue(0, "NM");
	            this.USER_EMAIL    = rEntity.getValue(0, "E_MAIL");
	            this.USER_TEL      = rEntity.getValue(0, "TEL"); 
                this.USER_MOBILE   = rEntity.getValue(0, "MOBILE");
	            this.CAPITAL	   = rEntity.getValue(0, "HOME_GROUP");	            
	            this.SIDO_CODE     = rEntity.getValue(0, "SIDO_CODE");
                this.SIGUNGU_CODE  = rEntity.getValue(0, "SIGUNGU_CODE");
                this.COM_NAME      = "";
                this.REG_NUM       = "";
                this.COM_NUM       = "";
                                	
	            if(this.CAPITAL.equals("A")) this.isAdmin = true;
	    	}
		} else if(type.equals("public")) {

			// 2009-10-05 추가 공무원용 테이블이 pt_mi_user 사용함으로 추가를 함			
			
			KJFLog.log("공무원 UserEnt 생성..");
			
			// 공무원
			rEntity = worker.loadUserFromDBPublic(strID);
			
	    	if (rEntity.getRowCnt() > 0) {
	    	    
	            this.USER_ID       = rEntity.getValue(0, "OFFI_ID");
                this.USER_PASSWORD = Crypto.Decode(rEntity.getValue(0, "PASS"));
	            this.USER_NAME     = rEntity.getValue(0, "NM");
	            this.USER_EMAIL    = rEntity.getValue(0, "E_MAIL");
	            this.USER_TEL      = rEntity.getValue(0, "TEL"); 
                this.USER_MOBILE   = rEntity.getValue(0, "MOBILE");
	            this.CAPITAL	   = KJFUtil.print(rEntity.getValue(0, "HOME_GROUP"),rEntity.getValue(0, "GROUP_CODE"));	            
	            this.SIDO_CODE     = rEntity.getValue(0, "SIDO_CODE");
                this.SIGUNGU_CODE  = rEntity.getValue(0, "SIGUNGU_CODE");
                this.COM_NAME      = "";
                this.REG_NUM       = "";
                this.COM_NUM       = "";
                                	
	            //if(this.CAPITAL.equals("A")) this.isAdmin = true;
	    	}
		} else if(type.equals("user")) {
	 
			KJFLog.log("사용자 UserEnt 생성..");
	    	System.out.println("사용자 UserEnt 생성..");
			// 사용자
			rEntity = worker.loadUserFromDBUser(strID);
			  
	    	if (rEntity.getRowCnt() > 0) {
                this.USER_ID       = rEntity.getValue(0, "USER_ID");
                this.USER_PASSWORD = Crypto.Decode(rEntity.getValue(0, "USER_PASSWD"));
                this.USER_NAME     = rEntity.getValue(0, "USER_NAME");  
                this.USER_TEL      = rEntity.getValue(0, "USER_TEL1") + rEntity.getValue(0, "USER_TEL2") + rEntity.getValue(0, "USER_TEL3"); 
                this.USER_MOBILE   = rEntity.getValue(0, "USER_MOBILE1") + rEntity.getValue(0, "USER_MOBILE2") + rEntity.getValue(0, "USER_MOBILE3");
                this.USER_EMAIL    = rEntity.getValue(0, "USER_EMAIL"); 
                this.CAPITAL	   = rEntity.getValue(0, "CAPITAL");                
                this.SIDO_CODE     = rEntity.getValue(0, "SIDO_CODE");
                this.SIGUNGU_CODE  = rEntity.getValue(0, "SIGUNGU_CODE");
                this.COM_NAME      = rEntity.getValue(0, "COM_NAME");
                this.REG_NUM       = rEntity.getValue(0, "REG_NUM");
                this.COM_NUM       = rEntity.getValue(0, "COM_NUM");

                KJFLog.log("USER_ID     : " + USER_ID);
                KJFLog.log("USER_NAME   : " + USER_NAME);
                KJFLog.log("USER_TEL    : " + USER_TEL);
                KJFLog.log("USER_MOBILE : " + USER_MOBILE);
                KJFLog.log("USER_EMAIL  : " + USER_EMAIL);
                KJFLog.log("CAPITAL     : " + CAPITAL);
                KJFLog.log("isAdmin     : " + isAdmin);
                KJFLog.log("SIDO_CODE   : " + SIDO_CODE);
                KJFLog.log("SIGUNGU_CODE : " + SIGUNGU_CODE);
                KJFLog.log("COM_NAME    : " + COM_NAME);
                KJFLog.log("REG_NUM     : " + REG_NUM);
                KJFLog.log("COM_NUM     : " + COM_NUM);
	        }		
		}

    
		// 여기까지 왔는데 USER_ID == null 이라면.. pt_hom_user, pt_user 어디에서도 회원정보가 없다는 뜻이다...
		if (KJFUtil.isEmpty(USER_ID)) {
		    
	        if (strID.equals(Config.props.get("SYS_ID"))) {
	        	
	        	KJFLog.log("SYS_ID UserEnt 생성..");
	        	
	            this.USER_ID       = strID;
                this.USER_PASSWORD = Config.props.get("SYS_PASS");
	            this.USER_NAME     = "시스템 관리자";
	            this.USER_TEL      = "";
                this.USER_MOBILE   = "";
                this.USER_EMAIL    = "";
	            this.SIDO_CODE     = "";
                this.SIGUNGU_CODE  = "";
                this.REG_NUM       = "";
	            this.CAPITAL       = "S";
	            this.COM_NAME      = "";
	            this.REG_NUM       = "";
                this.COM_NUM       = "";
	            this.isAdmin       = true;
	        }
        }

//		if (KJFUtil.isEmpty(USER_ID)) {		  
//			throw new MsgException("입력하신 아이디가 존재하지 않거나 사용허가되지 않았습니다.");	        	
//        }
	}

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String user_id) {
        USER_ID = user_id;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(String user_name) {
        USER_NAME = user_name;
    }

    public String getUSER_PASSWORD() {
        return USER_PASSWORD;
    }

    public void setUSER_PASSWORD(String user_password) {
        USER_PASSWORD = user_password;
    }
    
    public String getUSER_TEL() {
        return USER_TEL;
    }

    public void setUSER_TEL(String user_tel) {
        USER_TEL = user_tel;
    }

    public String getUSER_MOBILE() {
        return USER_MOBILE;
    }

    public void setUSER_MOBILE(String user_mobile) {
        USER_MOBILE = user_mobile;
    }

    public String getUSER_EMAIL() {
        return USER_EMAIL;
    }

    public void setUSER_EMAIL(String user_email) {
        USER_EMAIL = user_email;
    }

    public String getSIDO_CODE() {
        return SIDO_CODE;
    }

    public void setSIDO_CODE(String sido_code) {
        SIDO_CODE = sido_code;
    }

    public String getSIGUNGU_CODE() {
        return SIGUNGU_CODE;
    }

    public void setSIGUNGU_CODE(String sigungu_code) {
        SIGUNGU_CODE = sigungu_code;
    }    
    
    public String getCOM_NAME() {
		return COM_NAME;
	}

	public void setCOM_NAME(String com_name) {
		COM_NAME = com_name;
	}

	public String getREG_NUM() {
        return REG_NUM;
    }

    public void setREG_NUM(String reg_num) {
        REG_NUM = reg_num;
    }

    public String getCOM_NUM() {
        return COM_NUM;
    }

    public void setCOM_NUM(String com_num) {
        COM_NUM = com_num;
    }

    public String getCAPITAL() {
        return CAPITAL;
    }

    public void setCAPITAL(String capital) {
        CAPITAL = capital;
    }
    
    public String getLogNum() {
        return LogNum;
    }

    public void setLogNum(String logNum) {
        LogNum = logNum;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

	public boolean isUseUbSys() {
		return isUseUbSys;
	}

	public void setUseUbSys(boolean isUseUbSys) {
		this.isUseUbSys = isUseUbSys;
	} 		
	
}
