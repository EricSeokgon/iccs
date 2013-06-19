package sp.cominfo;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;

import kjf.util.KJFUtil;

@SuppressWarnings("serial")
public class ComInfoParam extends ActionForm {
    
    // 검색 조건 - 접두사 sc (serch condition)
    private String scCode;      // 검색 코드
    private String scText;      // 검색 값
    private String scSidoCode;  // 시도 값
    private String scYear;      // 검색년도
    private String scFromAsse;  // From 시공평가액
    private String scToAsse;    // To   시공평가액
    
    private String scFromYear;  // From 처분기간 Year
    private String scToYear; 	// To   처분기간 Year
    private String scFromMonth;	// From 처분기간 Month
    private String scToMonth;	// To   처분기간 Month
    
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

     public String getScText() {
         return scText;
     }

     public String getScSidoCode() {
        return scSidoCode;
    }

    public void setScSidoCode(String scSidoCode) {
        this.scSidoCode = scSidoCode;
    }
    
    public String getScYear() {
        return scYear;
    }

    public void setScYear(String scYear) {
        this.scYear = scYear;
    }

    public String getScFromAsse() {
        return scFromAsse;
    }

    public void setScFromAsse(String scFromAsse) {
        this.scFromAsse = scFromAsse;
    }

    public String getScToAsse() {
        return scToAsse;
    }

    public void setScToAsse(String scToAsse) {
        this.scToAsse = scToAsse;
    }    

	public String getScFromYear() {
		return scFromYear;
	}

	public void setScFromYear(String scFromYear) {
		this.scFromYear = scFromYear;
	}

	public String getScToYear() {
		return scToYear;
	}

	public void setScToYear(String scToYear) {
		this.scToYear = scToYear;
	}

	public String getScFromMonth() {
		return scFromMonth;
	}

	public void setScFromMonth(String scFromMonth) {
		this.scFromMonth = scFromMonth;
	}

	public String getScToMonth() {
		return scToMonth;
	}

	public void setScToMonth(String scToMonth) {
		this.scToMonth = scToMonth;
	}

	public void setScText(String scText) {
         this.scText = scText;
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
}
