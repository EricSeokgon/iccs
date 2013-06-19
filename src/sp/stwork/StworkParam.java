package sp.stwork;

import javax.servlet.http.HttpServletRequest;

import kjf.util.KJFUtil;

import org.apache.struts.action.ActionForm;

@SuppressWarnings("serial")
public class StworkParam extends ActionForm {
    
    // 검색 조건 - 접두사 sc (serch condition)
    private String scCode;      // 검색 코드
    private String scStru_Num;  // 건출물 허가번호
    private String recv_num;    // 접수번호
        
	/*페이징 관련*/ 
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

    public String getScStru_Num() {
        return scStru_Num;
    }
    
    public void setScStru_Num(String scStru_Num) {
        this.scStru_Num = scStru_Num;
    }
    
    public String getRecv_num() {
        return recv_num;
    }

    public void setRecv_num(String recv_num) {
        this.recv_num = recv_num;
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
