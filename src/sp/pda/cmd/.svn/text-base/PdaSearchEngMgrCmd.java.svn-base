package sp.pda.cmd;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.cfg.Config;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFLog;
import kjf.util.KJFUtil;

import org.apache.struts.action.ActionForm;

import sp.pda.PdaParam;

public class PdaSearchEngMgrCmd implements KJFCommand{
	
	public PdaSearchEngMgrCmd() {
        
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
        
        String scNM_KOR = request.getParameter("ENG_NM");
        //String GUGUN = request.getParameter("gugun");
        //String id = request.getParameter("id");
        //scNM_KOR= "1012625";
        
        System.out.println(scNM_KOR);
        
        StringBuilder selectSQL = new StringBuilder();
        StringBuilder fromSQL = new StringBuilder();
        StringBuilder whereSQL = new StringBuilder();
        
        //String scSSN = vl.getValueAsString("scSSN");
    	//String scNM_KOR = vl.getValueAsString("scNM_KOR");

    	selectSQL.append(" SELECT EM.NM_KOR, EM.ENGINEER_SSN1 || '*******' AS ENGINEER_SSN, PC.CODE_NAME AS TMP_GRADE, ES.NAME AS CORP_NM "); 
    	selectSQL.append(" FROM PT_R_ENGINEER_MASTER EM LEFT JOIN ");
    	selectSQL.append("  ( ");
    	selectSQL.append("   SELECT AA.*, DECODE(AA.RET_YMD, NULL, BB.NAME, NULL) AS NAME  ");
        //sQuery.append("   FROM PT_R_ENGINEER_CHANGE AA LEFT JOIN PT_R_BASIC_CHANGE_HISTORY BB ");
        //sQuery.append("   ON AA.TMP_WRT_NUM = BB.TMP_WRT_NUM AND AA.CHGBRE_SEQ = BB.CHGBRE_SEQ ");
    	selectSQL.append("   FROM PT_R_ENGINEER_CHANGE AA LEFT JOIN PT_R_COMPANY_MASTER BB ");
        selectSQL.append("   ON AA.TMP_WRT_NUM = BB.TMP_WRT_NUM ");
        selectSQL.append("   WHERE 1 = 1 ");
        //selectSQL.append("   AND ROWNUM = 1 ");
        selectSQL.append("   ORDER BY AA.EMPL_YMD DESC ");
        selectSQL.append("  ) ES ON ES.ENGINEER_SSN1 = EM.ENGINEER_SSN1 AND ES.ENGINEER_SSN2 = EM.ENGINEER_SSN2 ");
        selectSQL.append("  LEFT JOIN (SELECT CODE, CODE_NAME FROM PT_COM_CODE WHERE P_CODE = 'ENGCLASS') PC  ");
        selectSQL.append("  ON EM.ENGINEER_GRADE = PC.CODE  ");
        
        whereSQL.append(" WHERE 1 = 1 ");
        
        if (!KJFUtil.isEmpty(scNM_KOR)){
        	whereSQL.append(" AND   EM.NM_KOR LIKE '%" + scNM_KOR + "%' ");
        	whereSQL.append(" OR EM.ENGINEER_SSN1 || EM.ENGINEER_SSN2 LIKE '%" + scNM_KOR + "%' ");;
        }else{
        	whereSQL.append(" AND   EM.NM_KOR = '111rdfas' ");
        }
        
        rEntity = rDAO.select(selectSQL.toString()+fromSQL.toString()+whereSQL.toString());
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
