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
 * <p>Title       : FormSearchCmd Class</p>
 * <p>Description : �����ڷ� �˻� ó�� �Ѵ�.</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : �����ڷ� �˻� ������ ó���� �Ѵ�. 
 * 
 * @version 1.0
 * @author  PKT
 */
public class FormSearchCmd implements KJFCommand {
    
    public FormSearchCmd() {
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {  
        
        // �˻����� ������ üũ
        SearchParam pm = checkPm(request, form);
        
        request.setAttribute("pm", pm);
        
        loadList(request, pm);
        
        return request.getParameter("cmd");
    }
    
    private void loadList(HttpServletRequest request, SearchParam pm)throws Exception {
        
        List<SearchBean> xmllist;
        
        if ( KJFUtil.isEmpty( KJFUtil.print(pm.getScTextValue()) ) ) {
            xmllist = new ArrayList<SearchBean>();
        
        } else {
            
            //��� URL�� ����
            StringBuffer searchUrl = new StringBuffer();
            searchUrl.append("http://99.1.5.81:7578/srch_resultxml?w=form_search&base64=N");
            searchUrl.append("&pg=" + pm.getNowPage());
            searchUrl.append("&q=" + java.net.URLEncoder.encode(pm.getScTextValue().trim(), "utf-8"));
            searchUrl.append("&outmax=" + pm.getRowPerPage());
            
            SearchWorker scWorker = new SearchWorker();            
            xmllist = scWorker.loadXmlList(request, pm, searchUrl.toString(), "form");    
        }       
       
        System.out.println("������" + xmllist.size());
        
        /****** �˻����� �ʱⰪ ***********/
        request.setAttribute("xmllist", xmllist);
    }
    
    /**
     * �� üũ �޼ҵ�
     * 
     * @param request
     * @param form
     * @return
     * @throws Exception
     */
    private SearchParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {

        SearchParam pm = (SearchParam)form;

        // �Ķ���� �����
        KJFLog.debug(pm.toString(request));
        
        // ����¡ ����
        if (KJFUtil.isEmpty(pm.getRowPerPage())) {            
            pm.setRowPerPage(Config.props.get("ROW_PER_PAGE"));
        }

        return pm;
    }

}