package sp.search.cmd;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;

import sp.search.SearchBean;
import sp.search.SearchParam;
import sp.search.SearchWorker;
import kjf.action.KJFCommand;
import kjf.cfg.Config;
import kjf.util.KJFLog;
import kjf.util.KJFUtil;

/**
 * <p>Title       : WebPageSearchCmd Class</p>
 * <p>Description : 웹페이지검색 처리 한다.</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : 웹페이지검색 정보를 처리를 한다. 
 * 
 * @version 1.0
 * @author  PKT
 */
public class WebPageSearchCmd implements KJFCommand {

    public WebPageSearchCmd() {
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {  
        
        // 검색조건 설정및 체크
        SearchParam pm = checkPm(request, form);
        
        request.setAttribute("pm", pm);
        
        loadList(request, pm);        
        
        return request.getParameter("cmd");
    }
    
    private void loadList(HttpServletRequest request, SearchParam pm) throws Exception {
             
        List<SearchBean> xmllist;
        
        if ( KJFUtil.isEmpty( KJFUtil.print(pm.getScTextValue()) ) ) {
            xmllist = new ArrayList<SearchBean>();
        
        } else {
            
            //사용 URL명 정의
            StringBuffer searchUrl = new StringBuffer();            
            searchUrl.append("http://99.1.5.81:7578/srch_resultxml?w=web_search&base64=N");
            searchUrl.append("&pg=" + pm.getNowPage());
            searchUrl.append("&q=" + java.net.URLEncoder.encode(pm.getScTextValue().trim(), "utf-8"));
            searchUrl.append("&outmax=" + pm.getRowPerPage());                      
            
            SearchWorker scWorker = new SearchWorker();            
            xmllist = scWorker.loadXmlList(request, pm, searchUrl.toString(), "web");    
        }       
       
        /****** 검색조건 초기값 ***********/
        request.setAttribute("xmllist", xmllist);        
    }
    
    /**
     * 폼 체크 메소드
     * 
     * @param request
     * @param form
     * @return
     * @throws Exception
     */
    private SearchParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {

        SearchParam pm = (SearchParam)form;

        // 파라미터 디버깅
        KJFLog.debug(pm.toString(request));
        
        // 페이징 라인
        if (KJFUtil.isEmpty(pm.getRowPerPage())) {            
            pm.setRowPerPage(Config.props.get("ROW_PER_PAGE"));
        }

        return pm;
    }
}
