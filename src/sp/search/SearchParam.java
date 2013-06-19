package sp.search;

import javax.servlet.http.HttpServletRequest;

import kjf.util.KJFUtil;

import org.apache.struts.action.ActionForm;

@SuppressWarnings("serial")
public class SearchParam extends ActionForm {
       
    // 페이징 관련 
    private String nowPage;     // 현제페이지
    private String totalCount;  // 전체값
    private String rowPerPage;  // 목록값
    
    private String scTextValue; // 검색값
    
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
     
     public String getScTextValue() {
         return scTextValue;
     }

     public void setScTextValue(String scTextValue) {
         this.scTextValue = scTextValue;
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
