package sp.statistics;

import javax.servlet.http.HttpServletRequest;

import kjf.util.KJFUtil;

import org.apache.struts.action.ActionForm;

@SuppressWarnings("serial")
public class StatisticsParam extends ActionForm {
	
	/*페이징 관련*/ 
    private String st_view_area;
    private String st_view_year;
    private String st_sido_code;
    
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

	public String getSt_view_area() {
		return st_view_area;
	}

	public void setSt_view_area(String st_view_area) {
		this.st_view_area = st_view_area;
	}

	public String getSt_view_year() {
		return st_view_year;
	}

	public void setSt_view_year(String st_view_year) {
		this.st_view_year = st_view_year;
	}

    public String getSt_sido_code() {
        return st_sido_code;
    }

    public void setSt_sido_code(String st_sido_code) {
        this.st_sido_code = st_sido_code;
    }
	
}
