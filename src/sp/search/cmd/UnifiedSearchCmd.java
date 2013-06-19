package sp.search.cmd;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.cfg.Config;
import kjf.util.KJFLog;
import kjf.util.KJFUtil;

import org.apache.struts.action.ActionForm;

import sp.search.SearchBean;
import sp.search.SearchParam;
import sp.search.XmlParser;

/**
 * <p>Title       : UnifiedSearchCmd Class</p>
 * <p>Description : 통합검색 처리 한다.</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : 통합검색 정보를 처리를 한다. 
 * 
 * @version 1.0
 * @author  PKT
 */
public class UnifiedSearchCmd implements KJFCommand {
        
    public UnifiedSearchCmd() {        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {  
        
        // 검색조건 설정및 체크
        SearchParam pm = checkPm(request, form);
        
        request.setAttribute("pm", pm);
        
        loadList(request, pm);        
        
        return request.getParameter("cmd");
    }
    
    private void loadList(HttpServletRequest request, SearchParam pm) throws Exception {             
   
       loadBoardXml(request, pm);   // 게시물 검색
       loadFormXml(request, pm);    // 서식자료 검색
       loadWebXml(request, pm);     // 웹페이지 검색
   
    }
    
    /**
     * 게시물 검색
     * 
     * @param request
     * @param pm
     * @return
     * @throws Exception
     */
    private void loadBoardXml(HttpServletRequest request, SearchParam pm) throws Exception {
        
        List<SearchBean> xmllist = new ArrayList<SearchBean>();
        
        if ( KJFUtil.isEmpty(pm.getScTextValue()) ) {
            request.setAttribute("boardXmlList", xmllist);
            request.setAttribute("boardXmlCnt", 0);
            return;
        }
        
        //사용 URL명 정의
        StringBuffer searchUrl = new StringBuffer();
        searchUrl.append("http://99.1.5.81:7578/srch_resultxml?w=board_search&base64=N");
        searchUrl.append("&pg=" + pm.getNowPage());
        searchUrl.append("&q=" + java.net.URLEncoder.encode(pm.getScTextValue().trim(), "utf-8"));
        searchUrl.append("&outmax=" + pm.getRowPerPage());         
        
        System.out.println(searchUrl.toString());
        
        XmlParser section = loadXmlParser(searchUrl.toString());        
        
        xmllist = getXmlList(section, "board"); 
        
        request.setAttribute("boardXmlCnt", loadTotalCnt(section));
        request.setAttribute("boardXmlList", xmllist);
        
    }
    
    /**
     * 서식자료 검색
     * 
     * @param request
     * @param pm
     * @return
     * @throws Exception
     */
    private void loadFormXml(HttpServletRequest request, SearchParam pm) throws Exception {
        
        List<SearchBean> xmllist = new ArrayList<SearchBean>();
        
        if ( KJFUtil.isEmpty(pm.getScTextValue()) ) {
            request.setAttribute("formXmlList", xmllist);
            request.setAttribute("formXmlCnt", 0);
            return;
        }
        
        //사용 URL명 정의
        StringBuffer searchUrl = new StringBuffer();
        searchUrl.append("http://99.1.5.81:7578/srch_resultxml?w=form_search&base64=N");
        searchUrl.append("&pg=" + pm.getNowPage());
        searchUrl.append("&q=" + java.net.URLEncoder.encode(pm.getScTextValue().trim(), "utf-8"));
        searchUrl.append("&outmax=" + pm.getRowPerPage());
        
        System.out.println(searchUrl.toString());
                     
        XmlParser section = loadXmlParser(searchUrl.toString());        
        
        xmllist = getXmlList(section, "form"); 
        
        request.setAttribute("formXmlCnt", loadTotalCnt(section));
        request.setAttribute("formXmlList", xmllist);
        
    }
    
    /**
     * 웹페이지 검색
     * 
     * @param request
     * @param pm
     * @return
     * @throws Exception
     */
    private void loadWebXml(HttpServletRequest request, SearchParam pm) throws Exception {
        
        List<SearchBean> xmllist = new ArrayList<SearchBean>();
        
        if ( KJFUtil.isEmpty(pm.getScTextValue()) ) {
            request.setAttribute("webXmlList", xmllist);
            request.setAttribute("webXmlCnt", 0);
            return;
        }
        
        //사용 URL명 정의
        StringBuffer searchUrl = new StringBuffer();
        searchUrl.append("http://99.1.5.81:7578/srch_resultxml?w=web_search&base64=N");
        searchUrl.append("&pg=" + pm.getNowPage());
        searchUrl.append("&q=" + java.net.URLEncoder.encode(pm.getScTextValue().trim(), "utf-8"));
        searchUrl.append("&outmax=" + pm.getRowPerPage());
        
        System.out.println(searchUrl.toString());
             
        XmlParser section = loadXmlParser(searchUrl.toString());        
        
        xmllist = getXmlList(section, "web"); 
        
        request.setAttribute("webXmlCnt", loadTotalCnt(section));
        request.setAttribute("webXmlList", xmllist);
    }
    
    /**
     * 검색결과 카운트
     * 
     * @param section
     * @return
     * @throws Exception
     */
    private int loadTotalCnt(XmlParser section) throws Exception {
        
        int result = 0;
        
        for (int i = 0; i < section.length(); i++) {   
            result = KJFUtil.str2int(section.getInfoValue(i, "totcnt"));
        }
        
        return result;
    }
    
    
    /**
     * XmlParser
     * 
     * @param searchUrl
     * @return
     * @throws Exception
     */
    private XmlParser loadXmlParser(String searchUrl) throws Exception {
        
        XmlParser section = new XmlParser(searchUrl);
        
        return section;        
    }
    
    
    /**
     * 검색엔진 xml검색
     * 
     * @param section
     * @return
     * @throws Exception
     */
    public List<SearchBean> getXmlList(XmlParser section, String section_nm) throws Exception {
        
        List<SearchBean> xmlList = new ArrayList<SearchBean>();
        
        for (int i = 0; i < section.length(); i++) {         
            
            //  검색 결과 XML 에서 추출 태그 변경
            if(section.getName(i).indexOf(section_nm) != -1  ) {         
                           
            } else {
                continue;
            }

            //doc print
            if ( section.isDocs(i) ) {
                
                for (int j = 0; j < section.docLength(i); j++) {
                    
                    //att node들은 HashMap객체에 담겨져 있다.
                    //사용할때는  att노드의 Attribute name의 value를 key값으로 넣어준다.
                    //xml data 중 <section name="title"> 필드 명
                    //i 값은 section 순서 
                    if(section.getName(i).indexOf(section_nm) != -1){
                                               
                        SearchBean scBean = new SearchBean();
                        
                        // 게시판 관련                                             
                        scBean.setCt_id(section.getDocValue(i, j, "^CT_ID:"));
                        scBean.setBoard_seq(section.getDocValue(i, j, "^BOARD_SEQ:"));
                        scBean.setSubject(section.getDocValue(i, j, "^SUBJECT:"));
                        scBean.setContent(section.getDocValue(i, j, "^CONTENT:"));
                            
                        // 웹페이지 관련
                        scBean.setMenu_id(section.getDocValue(i, j, "^MENU_ID:"));
                        scBean.setM_menu(section.getDocValue(i, j, "^M_MENU:"));
                        scBean.setL_menu(section.getDocValue(i, j, "^L_MENU:"));
                        scBean.setS_munu(section.getDocValue(i, j, "^S_MENU:"));
                        scBean.setTitle(section.getDocValue(i, j, "^TITLE:"));
                        scBean.setBody(section.getDocValue(i, j, "^CONTENT:"));
                       
                        xmlList.add(scBean);                        
                    }
                }//end of  for
            }
        }
        
        return xmlList;        
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
