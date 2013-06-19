package sp.mystore.cmd;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.cfg.Config;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFLog;
import kjf.util.KJFUtil;
import kjf.util.LoginException;

import org.apache.struts.action.ActionForm;

import sp.mystore.MystoreParam;
import sp.uent.UserEnt;

/***************************************************************************
 * <p>Title       : MyInfomationCmd Class</p>
 * <p>Description : MY민원창고 처리 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE :  MY민원창고정보 처리를 한다. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class MyUseStoreCmd implements KJFCommand {
    
	UserEnt user;
	
    public MyUseStoreCmd() {
        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {        
        
        user = (UserEnt)request.getSession().getAttribute("user");
        
        // 로그인 유무 체크
        if (KJFUtil.isEmpty(user)) {
            throw new LoginException(request, "회원용 페이지 로그인 체크", "../member/login.do");
        } 
        
        // 검색조건 설정및 체크
        MystoreParam pm = checkPm(request, form);
        
        // 자주가는 창구 정보 
        loadData(request, pm);
                
        return request.getParameter("cmd");
    }
    
    
    /**
     * 자주가는 창구 정보를 가져온다.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadData(HttpServletRequest request, MystoreParam pm) throws Exception {
        
    	 ReportEntity rEntity = null;        
         
         ReportDAO rDAO = new ReportDAO();    
         
         int i = 1;
       
         StringBuffer sbSQL = new StringBuffer();        
         sbSQL.append(" SELECT                    	\n");        
         sbSQL.append("        USER_ID,           	\n");   // 사용자   ID
         sbSQL.append("        USER_NAME,         	\n");   // 사용자  명
         sbSQL.append("        FAV_NAME,         	\n");   // 자주가는 사이트명
         sbSQL.append("        FAV_URL,          	\n");   // 자주가는 사이트
         sbSQL.append("        ORDER_NUM,         	\n");   // 정렬
         sbSQL.append("        INS_DT              	\n");   // 등록일         
         sbSQL.append("   FROM PT_HOM_FAVORITE    	\n");         
         sbSQL.append("  WHERE USER_ID = ?          \n");   // 사용자ID
         sbSQL.append("  ORDER BY ORDER_NUM         \n");   // 정렬
         
         rDAO.setValue(i++, user.getUSER_ID());
         
         rEntity = rDAO.select(sbSQL.toString());

         /****** 검색조건 초기값 ***********/
         request.setAttribute("rEntity", rEntity);        
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
