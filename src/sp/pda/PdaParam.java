package sp.pda;

import javax.servlet.http.HttpServletRequest;

import kjf.util.KJFUtil;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

@SuppressWarnings("serial")
public class PdaParam extends ActionForm {
    
    /*페이징 관련*/ 
    private String nowPage;
    private String totalCount;
    private String rowPerPage;
    
    private FormFile attach_file ;
    

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
     
     public FormFile getAttach_file() {
 		return attach_file;
 	}

 	public void setAttach_file(FormFile attach_file) {
 		this.attach_file = attach_file;
 	}
}
