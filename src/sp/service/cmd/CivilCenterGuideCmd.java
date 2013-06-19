package sp.service.cmd;

import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.cfg.Config;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFLog;
import kjf.util.KJFSelect;
import kjf.util.KJFSelectOPS;
import kjf.util.KJFUtil;

import org.apache.struts.action.ActionForm;

import sp.service.AreaBaseBean;
import sp.service.AreaChargeBean;
import sp.service.AreaInfoBean;
import sp.service.ServiceParam;
import sp.uent.UserEnt;

/***************************************************************************
 * <p>Title       : CivilCenterGuideCmd Class</p>
 * <p>Description : 지역별 민원센터안내 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : 지역별 민원센터안내 정보 처리를 한다. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class CivilCenterGuideCmd implements KJFCommand {
    
	UserEnt user;
	
    public CivilCenterGuideCmd() {        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {        
               
        user = (UserEnt)request.getSession().getAttribute("user");
        
        // 검색조건 설정및 체크
        ServiceParam pm = checkPm(request, form);
        request.setAttribute("pm", pm);
        
        // 검색조건 및 초기데이타 로드
        loadCondition(request, pm);
        
        // 시도 정보 검색
        loadSidoAreaData(request, pm);
        
        // 시군구 정보 검색
        loadSigunguAreaData(request, pm);
        
        return request.getParameter("cmd");
    }
    
    /**
     * 검색조건 및 초기데이타 로드
     * 
     * @param HttpServletRequest
     * @return
     */
    private void loadCondition(HttpServletRequest request, ServiceParam pm) throws Exception {
        
        // 게시판 검색 코드        
        StringBuffer sbSQL = new StringBuffer();
        sbSQL.append(" SELECT AREA_CODE, SIDO_NM    \n");
        sbSQL.append("   FROM PT_SIDO_CODE          \n");
        sbSQL.append("  WHERE SUBSTR(AREA_CODE, 1, 2) = SUBSTR(AREA_CODE, 3, 4)    \n");
        sbSQL.append("  ORDER BY SIDO_NM     \n");           
        
        Vector<KJFSelect> v_scSD_CD = KJFSelectOPS.getSel(sbSQL.toString(), "", "시.도 선택");
        request.setAttribute("v_scSD_CD", v_scSD_CD);
    
        sbSQL = new StringBuffer();
        sbSQL.append(" SELECT AREA_CODE, RTRIM(SIGUNGU_NM, '본청')   \n");
        sbSQL.append("   FROM PT_SIDO_CODE                           \n");
        sbSQL.append("  WHERE SUBSTR(AREA_CODE, 1, 2) = SUBSTR('" + pm.getSido_code() + "', 1, 2) \n");
        sbSQL.append("    AND SUBSTR(AREA_CODE, 1, 2) != SUBSTR(AREA_CODE, 3, 4)                \n");
        sbSQL.append("  ORDER BY SIGUNGU_NM      \n");
                
        Vector<KJFSelect> v_scSGG_CD = KJFSelectOPS.getSel(sbSQL.toString(), "", "시.군.구 선택");
        request.setAttribute("v_scSGG_CD", v_scSGG_CD);            
    }
    
    
    /**
     * 시도 지역 정보를 가져온다.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadSidoAreaData(HttpServletRequest request, ServiceParam pm) throws Exception {
        
        String sido_code = KJFUtil.print(pm.getArea_code());	// 시도 코드
        
        ArrayList<AreaBaseBean>   resultArray     = new ArrayList<AreaBaseBean>();  // 결과 리스트
        ArrayList<AreaInfoBean>   areaInfoArray   = null;                           // 지역 상세 리스트
        ArrayList<AreaChargeBean> areaChargeArray = null;                           // 지역 담당자 리스트
            
        // 시도 정보가 NULL이 아닐 경우
        if (!KJFUtil.isEmpty(sido_code)) {
        
	        ReportEntity areaEntity   = loadSidoData(sido_code);                        // 시도 정보 Load
	        ReportEntity chargeEntity = loadSidoChargeData(sido_code, sido_code);       // 지역 담당자 정보 Load
	        
	        for (int i = 0; i < areaEntity.getRowCnt(); i++) {
	            
	            areaInfoArray   = new ArrayList<AreaInfoBean>();    // 지역 상세정보 List            
	            areaChargeArray = new ArrayList<AreaChargeBean>();  // 지역 담당자 리스트
	            
	            // 지역 기본정보 Bean
	            AreaBaseBean baseBean = new AreaBaseBean();
	            baseBean.setSido_code(KJFUtil.print(areaEntity.getValue(i, "AREA_CODE")));
	            baseBean.setSido_name(KJFUtil.print(areaEntity.getValue(i, "SIDO_NM")));
	            
	            // 지역상세 정보 Bean
	            AreaInfoBean areaBean = new AreaInfoBean();
	            areaBean.setAddress(KJFUtil.print(areaEntity.getValue(i, "ADDR")));
	            areaBean.setHompage(KJFUtil.print(areaEntity.getValue(i, "HOME")));
	            areaBean.setTel(KJFUtil.print(areaEntity.getValue(i, "TEL")));
	            
	            for (int j = 0; j < chargeEntity.getRowCnt(); j++) {
	                
	                // 지역 담당자 빈
	                AreaChargeBean chargeBean = new AreaChargeBean();
	                
	                if (chargeEntity.getObjValue(j, "SIDO_CODE").equals(areaEntity.getObjValue(i, "AREA_CODE"))) {
	                    
	                    chargeBean.setSido_code(KJFUtil.print(chargeEntity.getValue(j, "SIDO_CODE")));
	                    chargeBean.setSigungu_code(KJFUtil.print(chargeEntity.getValue(j, "SIGUNGU_CODE")));
	                    chargeBean.setName(KJFUtil.print(chargeEntity.getValue(j, "NM")));
	                    
	                    chargeBean.setOffiId(KJFUtil.print(chargeEntity.getValue(j, "OFFI_ID"),""));
	                    
	                    chargeBean.setPart(KJFUtil.print(chargeEntity.getValue(j, "PART")));
	                    chargeBean.setTel(KJFUtil.print(chargeEntity.getValue(j, "TEL")));
	                    chargeBean.setEmail(KJFUtil.print(chargeEntity.getValue(j, "E_MAIL")));
	                    
	                    areaChargeArray.add(chargeBean);
	                }               
	            }            
	            
	            // 지역 상세정보Bean에 담당자 리스트 저장
	            areaBean.setChargeArray(areaChargeArray);
	            
	            // 지역상세 List에 지역 상세 Bean 저장
	            areaInfoArray.add(areaBean);
	            
	            // 지역 기본Bean에 지역상세 리스트 저장
	            baseBean.setAreaInfoArray(areaInfoArray);	            
	            
	            resultArray.add(baseBean);
	        }
    	}
        
        request.setAttribute("sidoList", resultArray);
    }
    
    
    /**
     * 시군구 지역 정보 를 가져온다.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadSigunguAreaData(HttpServletRequest request, ServiceParam pm) throws Exception {
        
        String sido_code    = KJFUtil.print(pm.getSido_code());         // 시도 코드
        String sigungu_code = KJFUtil.print(pm.getSigungu_code());      // 시군구 코드
                
        ArrayList<AreaBaseBean> resultArray = new ArrayList<AreaBaseBean>();  // 결과 리스트
        
        // 시도 정보가 NULL이 아닐 경우
        if (!KJFUtil.isEmpty(sido_code)) {        	
	        
	        ReportEntity areaEntity    = loadSidoData(sido_code);                           // 시도      정보 Load
	        ReportEntity sigunguEntity = loadSigunguData(sido_code, sigungu_code);        	// 시군구 정보 Load
	        ReportEntity chargeEntity  = loadSigunguChargeData(sido_code, sigungu_code);    // 지역 담당자 정보 Load
	                        
	        for (int i = 0; i < areaEntity.getRowCnt(); i++) {
	            
	            ArrayList<AreaInfoBean> areaInfoArray   = new ArrayList<AreaInfoBean>();    // 지역 상세정보 List   
	            
	            // 지역 기본정보 Bean
	            AreaBaseBean baseBean = new AreaBaseBean();
	            baseBean.setSido_code(KJFUtil.print(areaEntity.getValue(i, "AREA_CODE")));
	            baseBean.setSido_name(KJFUtil.print(areaEntity.getValue(i, "SIDO_NM")));            
	           
	            for (int j = 0; j < sigunguEntity.getRowCnt(); j++) {
	                
	                // 지역상세 정보 Bean
	                AreaInfoBean areaBean = new AreaInfoBean();
	                
	                if (baseBean.getSido_code().substring(0, 2).equals(sigunguEntity.getValue(j, "AREA_CODE").substring(0, 2))) {
	                     
	                    areaBean.setSido_code(baseBean.getSido_code());
	                    areaBean.setSigungu_code(sigunguEntity.getValue(j, "AREA_CODE"));
	                    areaBean.setSigungu_name(sigunguEntity.getValue(j, "SIGUNGU_NM"));
	                    areaBean.setAddress(KJFUtil.print(sigunguEntity.getValue(j, "ADDR")));
	                    areaBean.setHompage(KJFUtil.print(sigunguEntity.getValue(j, "HOME")));
	                    areaBean.setTel(KJFUtil.print(sigunguEntity.getValue(j, "TEL")));                    
	                    
	                    ArrayList<AreaChargeBean> areaChargeArray = new ArrayList<AreaChargeBean>();  // 지역 담당자 리스트
	                    
	                    for (int k = 0; k < chargeEntity.getRowCnt(); k++) {
	                        
	                        // 지역 담당자 빈
	                        AreaChargeBean chargeBean = new AreaChargeBean();   // 지역 담당자 빈                        
	                        
	                        if (KJFUtil.print(chargeEntity.getObjValue(k, "SIGUNGU_CODE")).equals(KJFUtil.print(areaBean.getSigungu_code()))) {
	                            //System.out.println(areaBean.getSigungu_code() + ": " + chargeEntity.getObjValue(k, "SIGUNGU_CODE"));
	                            chargeBean.setSido_code(KJFUtil.print(chargeEntity.getValue(k, "SIDO_CODE")));
	                            chargeBean.setSigungu_code(KJFUtil.print(chargeEntity.getValue(k, "SIGUNGU_CODE")));
	                            chargeBean.setName(KJFUtil.print(chargeEntity.getValue(k, "NM")));
	                            chargeBean.setPart(KJFUtil.print(chargeEntity.getValue(k, "PART")));
	                            chargeBean.setTel(KJFUtil.print(chargeEntity.getValue(k, "TEL")));
	                            chargeBean.setEmail(KJFUtil.print(chargeEntity.getValue(k, "E_MAIL")));
	                            
	                            areaChargeArray.add(chargeBean);
	                        }               
	                    }          
	
	                    // 지역 상세정보Bean에 담당자 리스트 저장
	                    areaBean.setChargeArray(areaChargeArray);
	                    
	                    // 지역상세 List에 지역 상세 Bean 저장
	                    areaInfoArray.add(areaBean);
	                }
	            }  
	            
	            // 지역 기본Bean에 지역상세 리스트 저장
	            baseBean.setAreaInfoArray(areaInfoArray);            
	            
	            resultArray.add(baseBean);
	        }
        }
        
        request.setAttribute("sigunguList", resultArray);
    }
    
    
    /**
     * 시도 정보 로드
     * 
     * @param request
     * @param pm
     * @return
     * @throws Exception
     */
    public ReportEntity loadSidoData(String sido_code) throws Exception {
        
        ReportDAO rDAO       = new ReportDAO();
        ReportEntity rEntity = null;
        
        int i = 1;
                
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT AREA_CODE,            \n");
        sbSQL.append("        SIDO_NM,              \n");
        sbSQL.append("        HOME,                 \n");
        sbSQL.append("        ADDR,                 \n");
        sbSQL.append("        TEL                   \n");
        sbSQL.append("   FROM PT_SIDO_CODE          \n");
        sbSQL.append("  WHERE SUBSTR(AREA_CODE, 1, 2) = SUBSTR(AREA_CODE, 3, 4)    \n");
    	sbSQL.append("    AND AREA_CODE = ?     	\n");        
        sbSQL.append("   ORDER BY SIDO_NM           \n");
        
        rDAO.setValue(i++, sido_code);
        
        rEntity = rDAO.select(sbSQL.toString());
        
        return rEntity;
    }    
    
    /**
     * 시도 담당자 정보 로드
     * 
     * @param request
     * @param pm
     * @return
     * @throws Exception
     */
    public ReportEntity loadSidoChargeData(String sido_code, String sigungu_code) throws Exception {
        
        ReportDAO rDAO = new ReportDAO();       
        
        ReportEntity rEntity = null;
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT NM,               \n");
       	sbSQL.append("        OFFI_ID,          \n");
        sbSQL.append("        TEL,              \n");
        sbSQL.append("        SIDO_CODE,        \n");
        sbSQL.append("        SIGUNGU_CODE,     \n");
        sbSQL.append("        PART,             \n");
        sbSQL.append("        TEL,              \n");
        sbSQL.append("        E_MAIL            \n");        
        sbSQL.append("   FROM PT_MI_USER        \n");
        sbSQL.append("  WHERE SIDO_CODE = SIGUNGU_CODE \n");
        sbSQL.append("    AND SIDO_CODE = ?     \n");
        
        if (KJFUtil.isEmpty(user)){
	        	sbSQL.append("	  AND (GROUP_CODE = 'B' OR	GROUP_CODE = 'C' )	\n");	// (B->시도업무담당자/C->시군구업무담당자)
    	} else {
	        if ("U".equals(user.getCAPITAL()) || "UE".equals(user.getCAPITAL())){
	        	sbSQL.append("	  AND (GROUP_CODE = 'B' OR	GROUP_CODE = 'C' ) \n");	// (B->시도업무담당자/C->시군구업무담당자)
	        }
    	}
        
        sbSQL.append("  ORDER BY SIDO_CODE      \n");    
        
        rDAO.setValue(i++, sido_code);       
        //System.out.println("###"+user.getCAPITAL());
        //System.out.println("###"+sbSQL.toString());
        rEntity = rDAO.select(sbSQL.toString());
        
        return rEntity;
    }   
    
    /**
     * 시군구 정보 로드
     * 
     * @param request
     * @param pm
     * @return
     * @throws Exception
     */
    public ReportEntity loadSigunguData(String sido_code, String sigungu_code) throws Exception {
        
        ReportDAO rDAO       = new ReportDAO();
        ReportEntity rEntity = null;
        
        int i = 1;
                
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT AREA_CODE,            \n");
        sbSQL.append("        SIDO_NM,              \n"); 
        sbSQL.append("        SIGUNGU_NM,           \n");
        sbSQL.append("        HOME,                 \n");
        sbSQL.append("        ADDR,                 \n");
        sbSQL.append("        TEL                   \n");
        sbSQL.append("   FROM PT_SIDO_CODE          \n");
        sbSQL.append("  WHERE SUBSTR(AREA_CODE, 1, 2) != SUBSTR(AREA_CODE, 3, 4)    \n");
        sbSQL.append("    AND SUBSTR(AREA_CODE, 1, 2) = ?     \n");
        
        rDAO.setValue(i++, sido_code.substring(0, 2));
       
        
        if ( !KJFUtil.isEmpty(sigungu_code) ) {
            sbSQL.append("    AND AREA_CODE = ?     \n");
            rDAO.setValue(i++, sigungu_code);
        }
        
        sbSQL.append("   ORDER BY SIDO_NM, SIGUNGU_NM   \n");
        
        rEntity = rDAO.select(sbSQL.toString());
        
        return rEntity;
    }
    
    
    /**
     * 담당자 정보 로드
     * 
     * @param request
     * @param pm
     * @return
     * @throws Exception
     */
    public ReportEntity loadSigunguChargeData(String sido_code, String sigungu_code) throws Exception {
        
        ReportDAO rDAO = new ReportDAO();       
        
        ReportEntity rEntity = null;
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();        
        sbSQL.append(" SELECT NM,               \n");
        sbSQL.append("        TEL,              \n");
        sbSQL.append("        SIDO_CODE,        \n");
        sbSQL.append("        SIGUNGU_CODE,     \n");
        sbSQL.append("        PART,             \n");
        sbSQL.append("        TEL,              \n");
        sbSQL.append("        E_MAIL            \n");        
        sbSQL.append("   FROM PT_MI_USER        \n");
        sbSQL.append("  WHERE SIDO_CODE = ?     \n");
        
        if ( KJFUtil.isEmpty(user) ){
        	sbSQL.append("	  AND (GROUP_CODE = 'B' OR	GROUP_CODE = 'C' ) 	\n");	// (B->시도업무담당자/C->시군구업무담당자)
    	} else {
	        if ("U".equals(user.getCAPITAL()) || "UE".equals(user.getCAPITAL())){
	        	sbSQL.append("	  AND (GROUP_CODE = 'B' OR	GROUP_CODE = 'C' ) \n");	// (B->시도업무담당자/C->시군구업무담당자)
	        }
    	}
        
        rDAO.setValue(i++, sido_code);
        
        if ( !KJFUtil.isEmpty(sigungu_code) ) {
            sbSQL.append("    AND SIGUNGU_CODE = ?     \n");
            rDAO.setValue(i++, sigungu_code);
        }
        
        sbSQL.append("  ORDER BY SIDO_CODE, SIGUNGU_CODE \n");        
        
        rEntity = rDAO.select(sbSQL.toString());
        
        return rEntity;
    }    
    
    
    /**
     * 폼 체크 메소드
     * 
     * @param request
     * @param form
     * @return
     * @throws Exception
     */
    private ServiceParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {

        ServiceParam pm = (ServiceParam)form;

        // 파라미터 디버깅
        KJFLog.debug(pm.toString(request));
        
        // 로그인 유무 체크
        if (!KJFUtil.isEmpty(user)) {
            
            // 지역코드 체크
            if (KJFUtil.isEmpty(pm.getArea_code())) {
                pm.setArea_code(user.getSIDO_CODE());
            }
            
            // 시도코드 체크
            if (KJFUtil.isEmpty(pm.getSido_code())) {
                pm.setSido_code(user.getSIDO_CODE());
                
                // 시군구 코드 체크
                if (KJFUtil.isEmpty(pm.getSigungu_code())) {
                    pm.setSigungu_code(user.getSIGUNGU_CODE());
                }
            }
        }
        
        // 페이징 라인
        if (KJFUtil.isEmpty(pm.getRowPerPage())) {
            pm.setRowPerPage(Config.props.get("ROW_PER_PAGE"));
        }

        return pm;
    }
}
