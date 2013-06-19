package sp.pda.cmd;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.cfg.Config;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFDBUtil;
import kjf.util.KJFDate;
import kjf.util.KJFFile;
import kjf.util.KJFLog;
import kjf.util.KJFUtil;
import kjf.util.LoginException;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import sp.pda.PdaParam;
/***************************************************************************
 * <p>Title       : MyProgressStateCmd Class</p>
 * <p>Description : 나의 민원진행상태 처리 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : 나의 민원진행상태 정보 처리를 한다. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class PdaSingimgMgrCmd implements KJFCommand {
    
    public PdaSingimgMgrCmd() {
        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {        
        
        
        // 검색조건 설정및 체크
        PdaParam pm = checkPm(request, form);
        
        // 
        loadData(request, pm);
        
        return request.getParameter("cmd");
    }
    
    
    /**
     * 게시물 리스트를 가져온다.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadData(HttpServletRequest request, PdaParam pm) throws Exception {
    	ReportDAO    rDAO        = new ReportDAO();
        ReportEntity rEntity     = null;
        
		String city = request.getParameter("city");
		String gugun = request.getParameter("gugun");
		String id = request.getParameter("id");
		String ub_code = request.getParameter("ub_code");
		String to_data = KJFDate.getCurTime("yyyy.MM.dd");
		String filename = to_data+"_pda_sing.bmp";
		String dirs = "/data/webroot/ICCSM/usebefore/"+city+"/"+gugun+"/"+ub_code+"/";
		//String dirs = "D:\\cytyseal\\";
		 String result= "";
		
		if(!KJFUtil.isEmpty(pm.getAttach_file())){
			FormFile attach_file = pm.getAttach_file();    		
    		String CT_Dir = dirs;
    		
    		try{
            // 파일이 저장 될 디렉토리 생성
            KJFFile.dirMake(CT_Dir);
            // 실제 시스템 저장
            KJFFile.saveFile(CT_Dir + KJFFile.FILE_S, filename, attach_file.getInputStream());
            
            String SEQ = KJFDBUtil.getMaxID("SEQ","PT_UB_FILE");
     	   	String SQL = "INSERT INTO PT_UB_FILE (SEQ,RECV_NUM,SIDO_CODE,SIGUNGU_CODE,FILE_NM,WRT_ID,INS_DT) VALUES ("+SEQ+",'"+ub_code+"','"+city+"','"+gugun+"','"+filename+"','"+id+"','"+to_data+"')";
     	   	rDAO.execute(SQL);
            
            result = "OK|http://net.go.kr/pda/singimg.jsp?city="+city+"&gugun="+gugun+"&ub_code="+ub_code+"&f_name="+filename;
            
    		}catch(Exception e){
    			  System.out.println("sing err : "+e);	  
    			  result = "SIGNERR|";
		    }
		     finally {
		    	 request.getSession().setAttribute("result", result);
		     }
		}
        
    }
    
    
    /**
     * 폼 체크 메소드
     * 
     * @param request
     * @param form
     * @return
     * @throws Exception
     */
    private PdaParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {

    	PdaParam pm = (PdaParam)form;

        // 파라미터 디버깅
        KJFLog.debug(pm.toString(request));
        
        // 페이징 라인
        if (KJFUtil.isEmpty(pm.getRowPerPage())) {            
            pm.setRowPerPage(Config.props.get("ROW_PER_PAGE"));
        }

        return pm;
    }
}
