package sp.cominfo;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;

import kjf.util.KJFUtil;

@SuppressWarnings("serial")
public class ComInfoParam extends ActionForm {
    
    // �˻� ���� - ���λ� sc (serch condition)
    private String scCode;      // �˻� �ڵ�
    private String scText;      // �˻� ��
    private String scSidoCode;  // �õ� ��
    private String scYear;      // �˻��⵵
    private String scFromAsse;  // From �ð��򰡾�
    private String scToAsse;    // To   �ð��򰡾�
    
    private String scFromYear;  // From ó�бⰣ Year
    private String scToYear; 	// To   ó�бⰣ Year
    private String scFromMonth;	// From ó�бⰣ Month
    private String scToMonth;	// To   ó�бⰣ Month
    
    // ����¡ ����
    private String nowPage;
    private String totalCount;
    private String rowPerPage;
    
    /**
     * request�� ���� parameter�� �ش� value�� ���<br>
     * paramName : value �������� ���
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
