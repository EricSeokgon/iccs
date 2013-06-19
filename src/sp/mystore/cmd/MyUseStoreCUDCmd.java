package sp.mystore.cmd;

import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.cfg.Config;
import kjf.ops.ReportDAO;
import kjf.util.KJFDate;
import kjf.util.KJFLog;
import kjf.util.KJFUtil;
import kjf.util.LoginException;

import org.apache.struts.action.ActionForm;

import sp.mystore.MystoreParam;
import sp.uent.UserEnt;

/***************************************************************************
 * <p>Title       : MyUseStoreCUDCmd Class</p>
 * <p>Description : 내가 자주 가는 창고 등록, 수정, 삭제 처리 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : 내가 자주 가는 창고 등록, 수정, 삭제 처리를 한다. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class MyUseStoreCUDCmd implements KJFCommand {
    
	UserEnt user;
	
    public MyUseStoreCUDCmd() {        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {        
        
        user = (UserEnt)request.getSession().getAttribute("user");
        
        // 로그인 유무 체크
        if (KJFUtil.isEmpty(user)) {
            throw new LoginException(request, "회원용 페이지 로그인 체크", "../member/login.do");
        } 
        
        // 검색조건 설정및 체크
        MystoreParam pm = checkPm(request, form);        
        
        insertData(request, pm);	// 자주가는 창구 정보 저장       
                
        return request.getParameter("cmd");
    }
    
    
    /**
     * 자주가는 창구 저장
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void insertData(HttpServletRequest request, MystoreParam pm) throws Exception {
    	
    	 StringTokenizer tokens = new StringTokenizer(request.getParameter("n_favorite") , "," );
         StringTokenizer values = null;
         String rowStr = "";        
         
         deleteDate(request, pm);	// 자주가는 창구 정보 삭제
         
         while (tokens.hasMoreElements()) {
        	 
             rowStr = tokens.nextToken();
             values = new StringTokenizer( rowStr , "|" );            
             
             String fav_name       = values.nextToken().trim();
             String fav_url        = values.nextToken().trim();      
             String order_num      = values.nextToken().trim();  
                          
             System.out.println("fav_name: " + fav_name);
             System.out.println("fav_url: " + fav_url);
             System.out.println("order_num: " + order_num);
             
             ReportDAO rDAO = new ReportDAO();      
             
             StringBuffer sbSQL = new StringBuffer();        
             sbSQL.append(" INSERT INTO PT_HOM_FAVORITE (		\n");
             sbSQL.append("        USER_ID,               		\n");
             sbSQL.append("        USER_NAME,             		\n");
             sbSQL.append("        FAV_NAME,           			\n");
             sbSQL.append("        FAV_URL,             		\n");
             sbSQL.append("        ORDER_NUM,             		\n");
             sbSQL.append("        INS_DT             			\n");
             sbSQL.append("       )                      		\n");
             sbSQL.append("   values (?,?,?,?,?,?)          	\n");   

             int i = 1;
             
             rDAO.setValue(i++, user.getUSER_ID());
             rDAO.setValue(i++, user.getUSER_NAME());
             rDAO.setValue(i++, fav_name);
             rDAO.setValue(i++, fav_url);
             rDAO.setValue(i++, order_num);
             rDAO.setValue(i++, KJFDate.getCurTime("yyyy-MM-dd HH:mm:ss"));

             rDAO.execute(sbSQL.toString());
             
         }
    }
    
    /**
     * 자주가는 창구 삭제
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void deleteDate(HttpServletRequest request, MystoreParam pm) throws Exception {
        
    	 ReportDAO rDAO = new ReportDAO();      
         
         StringBuffer sbSQL = new StringBuffer();        
         sbSQL.append(" DELETE                       \n");
         sbSQL.append("   FROM PT_HOM_FAVORITE     	 \n");
         sbSQL.append("  WHERE USER_ID = ?           \n");
         
         int i = 1;

         rDAO.setValue(i++, user.getUSER_ID());      

         rDAO.execute(sbSQL.toString());
    }
    
    
    /**
     * 폼 체크 메소드
     * 
     * @param request
     * @param form
     * @return
     * @throws Exception
     */
    private MystoreParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {

        MystoreParam pm = (MystoreParam)form;

        // 파라미터 디버깅
        KJFLog.debug(pm.toString(request));
        
        // 페이징 라인
        if (KJFUtil.isEmpty(pm.getRowPerPage())) {            
            pm.setRowPerPage(Config.props.get("ROW_PER_PAGE"));
        }

        return pm;
    }

}
