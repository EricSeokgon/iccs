package kjf.util;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Subsystem     :  util </p>
 * <p>Title         :  메세 지클래스 </p>
 * <p>Description   :  메세지를 나타낸다. </p>
 * @author PKT
 * @version 1.0 <br/>
 */
@SuppressWarnings("serial")
public final class MsgException extends Exception {

    public  String msg       = "";
    private String returnURL = null;
    private String isMSG     = "TRUE";
    private HttpServletRequest beforeRequest = null;
    

    public MsgException(String msg) {
        this.msg = msg;
    }
  
    public MsgException(String msg, String url) {
        this.msg = msg;
        this.returnURL = url.replaceAll(url,"");
    }

    public MsgException(HttpServletRequest request, String msg) {
        
        String serverURL  = request.getContextPath();
        String url        = request.getRequestURI() + "?" + request.getQueryString();
        
        this.returnURL = url.replaceAll(serverURL,"../");
        this.beforeRequest = request;     
        this.msg = msg;
     
        request.getSession().setAttribute("beforeRequest", beforeRequest);
        request.getSession().setAttribute("returnURL", returnURL);
        request.setAttribute("isMSG", isMSG);
    }
  
    public MsgException(HttpServletRequest request, String msg, String isMSG, String URL) {

        this.returnURL = URL;
        this.msg = msg;
        this.isMSG = isMSG; // 메세지 표시여부
        
        request.setAttribute("returnURL", returnURL);
        request.setAttribute("isMSG", isMSG); 
    }  

    public String  getMessage(){
        return this.msg;
    }
}