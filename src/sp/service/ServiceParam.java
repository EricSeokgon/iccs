package sp.service;

import javax.servlet.http.HttpServletRequest;

import kjf.util.KJFUtil;

import org.apache.struts.action.ActionForm;

@SuppressWarnings("serial")
public class ServiceParam extends ActionForm {
    
    // ����¡ ����
    private String nowPage;
    private String totalCount;
    private String rowPerPage;
    
    // �˻� ����
    private String area_code;           // �õ� ���� ��.�� �ڵ�
    private String sido_code;           // �ñ��� ���� ��.�� �ڵ�
    private String sigungu_code;        // �ñ��� ���� ��.��.�� �ڵ�
    
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

     
     public String getSido_code() {
         return sido_code;
     }

     public void setSido_code(String sido_code) {
         this.sido_code = sido_code;
     }

     public String getSigungu_code() {
         return sigungu_code;
     }

     public void setSigungu_code(String sigungu_code) {
         this.sigungu_code = sigungu_code;
     }

    public String getArea_code() {
        return area_code;
    }

    public void setArea_code(String area_code) {
        this.area_code = area_code;
    }
     
}
