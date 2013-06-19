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
 * <p>Description : ���� �ο�������� ó�� Ŭ����</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : ���� �ο�������� ���� ó���� �Ѵ�. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class PdaSingimgMgrCmd implements KJFCommand {
    
    public PdaSingimgMgrCmd() {
        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {        
        
        
        // �˻����� ������ üũ
        PdaParam pm = checkPm(request, form);
        
        // 
        loadData(request, pm);
        
        return request.getParameter("cmd");
    }
    
    
    /**
     * �Խù� ����Ʈ�� �����´�.
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
            // ������ ���� �� ���丮 ����
            KJFFile.dirMake(CT_Dir);
            // ���� �ý��� ����
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
     * �� üũ �޼ҵ�
     * 
     * @param request
     * @param form
     * @return
     * @throws Exception
     */
    private PdaParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {

    	PdaParam pm = (PdaParam)form;

        // �Ķ���� �����
        KJFLog.debug(pm.toString(request));
        
        // ����¡ ����
        if (KJFUtil.isEmpty(pm.getRowPerPage())) {            
            pm.setRowPerPage(Config.props.get("ROW_PER_PAGE"));
        }

        return pm;
    }
}
