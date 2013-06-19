package sp.comm;

import javax.servlet.http.HttpServletRequest;

import kjf.util.KJFUtil;

import org.apache.struts.action.ActionForm;

@SuppressWarnings("serial")
public class CommParam extends ActionForm {
    
    // 검색 관련
    private String scSD_CD;         // 시.도 코드
    
    // 페이징 관련
    private String nowPage;
    private String totalCount;
    private String rowPerPage;

    public CommParam() {
        
    }  
    
    /**
     *   request로 부터 parameter의 해당 value를 얻어
     *   paramName : value
     *   형식으로 출력
     */
     public String toString(HttpServletRequest request){
         return KJFUtil.reportParameters(request) ;
     }
     
     public String getScSD_CD() {
         return scSD_CD;
     }

     public void setScSD_CD(String scSD_CD) {
         this.scSD_CD = scSD_CD;
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
