package sp.search;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import kjf.util.KJFUtil;

/**
 * <p>Title       : SearchWorker Class</p>
 * <p>Description : XmlParser 처리를 한다.</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : 검색된 Xml 처리를 한다. 
 * 
 * @version 1.0
 * @author PKT
 */
public class SearchWorker {
    
    public List<SearchBean> loadXmlList(HttpServletRequest request, SearchParam pm, String searchUrl, String section_nm) throws Exception {
        
        List<SearchBean> xmlList = new ArrayList<SearchBean>();
        
        //xml 파싱 
        XmlParser section = new XmlParser(searchUrl);
                
        System.out.println(section.length());
        for (int i = 0; i < section.length(); i++) {   
                
            // 전체 목록 수
            String totalCount = KJFUtil.print(section.getInfoValue(i, "totcnt"), "0");

            // 페이지별 목록 수
            int rowPerPage = KJFUtil.str2int(pm.getRowPerPage());

            // 현재 페이지 번호
            int nowPage = 1;
            nowPage = KJFUtil.isEmpty(pm.getNowPage()) ? 1 : Integer.parseInt(pm.getNowPage());
            
            if (rowPerPage == 0) rowPerPage = Integer.parseInt(totalCount);
            if ((rowPerPage*nowPage) - Integer.parseInt(totalCount) > rowPerPage) nowPage = 1;

            pm.setTotalCount(totalCount);
            pm.setNowPage(String.valueOf(nowPage));          
      
            //  검색 결과 XML 에서 추출 태그 변경
            if(section.getName(i).indexOf(section_nm) != -1  ) {                
                System.out.println(section.getName(i));      
                System.out.println("에 대한 약 " + totalCount + "개 결과 중 ");   
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
                        
                        System.out.println(j);
                       
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
                        
                        System.out.println(section.getDocValue(i, j, "^tit:"));
                        System.out.println(section.getDocValue(i, j, "^url:"));
                        
                        xmlList.add(scBean);                        
                    }
                }//end of  for
            }
        }
        
        /****** 검색조건 초기값 ***********/
        request.setAttribute("pm", pm);
        
        return xmlList;        
    }
}
