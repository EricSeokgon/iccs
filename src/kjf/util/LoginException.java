package kjf.util;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title         :  로그인 처리 클래스 </p>
 * <p>Description   :  로그인 처리 여부를 나타낸다. </p>
 * <p>관련 TABLE      : </p>
 * @author  PKT
 * @version 1.0
 */
@SuppressWarnings("serial")
public final class LoginException extends Exception {

    private String msg = "";
    private String returnURL = null;
    private String beforeURL = null;

    public LoginException(HttpServletRequest request, String msg) {
        
        String serverURL = request.getContextPath();
        String url       = request.getRequestURI() + "?" + request.getQueryString();
        
        this.beforeURL = url.replaceAll(serverURL,"");
        this.msg           = msg;
      
        request.getSession().setAttribute("beforeURL", beforeURL);
    }
    
    public LoginException(HttpServletRequest request, String msg, String URL) {
                
        String serverURL = request.getContextPath();
        String url       = request.getRequestURI() + "?" + request.getQueryString();
        
        this.beforeURL = url.replaceAll(serverURL,""); 
        this.returnURL = URL;
        this.msg       = msg;
        
        request.setAttribute("beforeURL", beforeURL);
        request.setAttribute("returnURL", returnURL);
    }

    public String  getMessage() {
        return this.msg;
    }
}