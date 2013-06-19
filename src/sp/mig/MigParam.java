/**
 * 파일명   : MigParam.java
 * 설명      : Parameter Bean
 * 이력사항
 *
 */
package sp.mig;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import kjf.util.*;


public class MigParam extends ActionForm{


    /*페이징 관련*/
    private String nowPage;
    private String totalCount;
    private String rowPerPage; 

    
    /*현재모드 설정 */
    private String mode;

	public String getNowPage() {
		return nowPage;
	}

	public String getTotalCount() {
		return totalCount;
	}

	public String getRowPerPage() {
		return rowPerPage;
	}

	
	public String getMode() {
		return mode;
	}

	public void setNowPage(String nowPage) {
		this.nowPage = nowPage;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	public void setRowPerPage(String rowPerPage) {
		this.rowPerPage = rowPerPage;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
}//end class


