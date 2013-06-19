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
 * <p>Title       : MyProgressStateCmd Class</p>
 * <p>Description : 나의 민원진행상태 처리 클래스</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : 나의 민원진행상태 정보 처리를 한다. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class MyProgressStateCmd implements KJFCommand {
    
    UserEnt user;
    
    public MyProgressStateCmd() {
        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {        
        
        user = (UserEnt)request.getSession().getAttribute("user");
        
        // 로그인 유무 체크
        if (KJFUtil.isEmpty(user)) {
            throw new LoginException(request, "회원용 페이지 로그인 체크", "../member/login.do");
        }  
        
        // 검색조건 설정및 체크
        MystoreParam pm = checkPm(request, form);
        request.setAttribute("pm", pm);
        
        // 민원진행상태  등록정보
        loadRegCountData(request, pm, "ALL"); // 전체정보 
        loadRegCountData(request, pm, "REC"); // 접수정보
        loadRegCountData(request, pm, "COM"); // 완료 정보 
        
        // 민원진행상태 사용전검사
        loadUseBeforeData(request, pm);
        
        // 민원진행상태 착공전검사
        loadStworkData(request, pm);
        
        // 민원진행상태  위법관리
        loadIllCountData(request, pm, "ALL"); // 전체정보
        loadIllCountData(request, pm, "REC"); // 접수정보 
        loadIllCountData(request, pm, "COM"); // 완료 정보
        
        return request.getParameter("cmd");
    }
    
    
    /**
     * 나의 민원 진행상태 공사업관리 정보를 가져온다.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadRegCountData(HttpServletRequest request, MystoreParam pm, String regDiv) throws Exception {
        
        ReportEntity rEntity = null;
        ReportDAO rDAO = new ReportDAO();      
        
        String regproc = "STD003";
        
        int i = 1;

        StringBuffer sbSQL = new StringBuffer();   
        sbSQL.append("SELECT                                                \n");
        sbSQL.append("       COUNT(PT_R_REGIST.RECV_NUM) AS REGIST_ALL_CNT, \n");
        sbSQL.append("       COUNT(PT_R_BASIC.RECV_NUM) AS BASIC_ALL_CNT,   \n");
        sbSQL.append("       COUNT(PT_R_ASSI.RECV_NUM) AS ASSI_ALL_CNT,     \n");
        sbSQL.append("       COUNT(PT_R_UNION.RECV_NUM) AS UNION_ALL_CNT    \n");

        // 공사업 변경내역
        sbSQL.append("  FROM PT_R_BASIC_CHANGE_HISTORY PT_H                 \n");
        
        // 등록접수
        sbSQL.append("  LEFT JOIN (                                         \n");
        sbSQL.append("              SELECT RECV_NUM                         \n");
        sbSQL.append("                FROM PT_R_REGIST_STATEMENT            \n");
        sbSQL.append("            ) PT_R_REGIST ON PT_H.RECV_NUM = PT_R_REGIST.RECV_NUM \n");
        sbSQL.append("            AND PT_H.MANA_NUM = ?                     \n");
        
        rDAO.setValue(i++, user.getCOM_NUM());
        
        // 접수중
        if (regDiv.equals("REC")) {
            sbSQL.append("            AND PT_H.MOT_STE != ?     \n");
            rDAO.setValue(i++, regproc);
            
        // 등록완료
        } else if (regDiv.equals("COM")) {
            sbSQL.append("            AND PT_H.MOT_STE = ?      \n");
            rDAO.setValue(i++, regproc);
        }
        
        // 기준신고 
        sbSQL.append("  LEFT JOIN (                                                     \n");
        sbSQL.append("              SELECT RECV_NUM                                     \n");
        sbSQL.append("              FROM PT_R_BASIC_STATEMENT PT_R_BASIC                \n");
        sbSQL.append("            ) PT_R_BASIC ON PT_H.RECV_NUM = PT_R_BASIC.RECV_NUM   \n");
        sbSQL.append("            AND PT_H.COI_WRT_NUM = ?                              \n");
        sbSQL.append("            AND PT_H.MANA_NUM = ?                                 \n");
        
        rDAO.setValue(i++, user.getREG_NUM());
        rDAO.setValue(i++, user.getCOM_NUM());
        
        // 접수중
        if (regDiv.equals("REC")) {
            sbSQL.append("            AND PT_H.MOT_STE != ?      \n");
            rDAO.setValue(i++, regproc);
            
        // 등록완료
        } else if (regDiv.equals("COM")) {
            sbSQL.append("            AND PT_H.MOT_STE = ?       \n");
            rDAO.setValue(i++, regproc);
        }
        
        // 양도양수
        sbSQL.append("  LEFT JOIN (                                             \n");
        sbSQL.append("              SELECT RECV_NUM                             \n");
        sbSQL.append("              FROM PT_R_ASSI_TRANS_STATEMENT PT_R_ASSI INNER JOIN PT_R_COMPANY_MASTER PT_M \n");
        sbSQL.append("                ON PT_M.TMP_WRT_NUM = PT_R_ASSI.ASSI_TMP_WRT_NUM                  \n");
        sbSQL.append("             WHERE (PT_M.COI_WRT_NUM = ? AND PT_M.MANA_NUM = ?)                   \n");
        sbSQL.append("                OR TRAN_COMNUM = ?                                                \n");
        sbSQL.append("            ) PT_R_ASSI ON PT_H.RECV_NUM = PT_R_ASSI.RECV_NUM                     \n");
        
        rDAO.setValue(i++, user.getREG_NUM());
        rDAO.setValue(i++, user.getCOM_NUM());
        rDAO.setValue(i++, user.getCOM_NUM());
        
        // 접수중
        if (regDiv.equals("REC")) {
            sbSQL.append("            AND PT_H.MOT_STE != ?     \n");
            rDAO.setValue(i++, regproc);
            
        // 등록완료
        } else if (regDiv.equals("COM")) {
            sbSQL.append("            AND PT_H.MOT_STE = ?      \n");
            rDAO.setValue(i++, regproc);
        }
        
        // 합병
        sbSQL.append("  LEFT JOIN (                                         \n");
        sbSQL.append("              SELECT RECV_NUM                         \n");
        sbSQL.append("               FROM PT_R_UNION_STATEMENT PT_U         \n");
        sbSQL.append("               INNER JOIN PT_R_COMPANY_MASTER PT_M1   \n");
        sbSQL.append("                       ON PT_M1.TMP_WRT_NUM = PT_U.ASC_TMP_WRT_NUM        \n");
        sbSQL.append("               INNER JOIN PT_R_COMPANY_MASTER PT_M2                       \n");
        sbSQL.append("                       ON PT_M2.TMP_WRT_NUM = PT_U.HANDED_TMP_WRT_NUM     \n");
        sbSQL.append("              WHERE (PT_M1.COI_WRT_NUM = ? AND PT_M1.MANA_NUM = ?)        \n");
        sbSQL.append("                 OR (PT_M2.COI_WRT_NUM = ? AND PT_M2.MANA_NUM = ?)        \n");
        sbSQL.append("                 OR (PT_U.ESTA_MANA_NUM = ?)                              \n");
        sbSQL.append("            ) PT_R_UNION ON PT_H.RECV_NUM = PT_R_ASSI.RECV_NUM            \n");
        
        rDAO.setValue(i++, user.getREG_NUM());
        rDAO.setValue(i++, user.getCOM_NUM());
        rDAO.setValue(i++, user.getREG_NUM());
        rDAO.setValue(i++, user.getCOM_NUM());
        rDAO.setValue(i++, user.getCOM_NUM());
        
        // 접수중
        if (regDiv.equals("REC")) {
            sbSQL.append("            AND PT_H.MOT_STE = ?                      \n");
            rDAO.setValue(i++, regproc);
            
        // 등록완료
        } else if (regDiv.equals("COM")) {
            sbSQL.append("            AND PT_H.MOT_STE != ?                     \n");
            rDAO.setValue(i++, regproc);
        }        
        
        rEntity = rDAO.select(sbSQL.toString());

        /****** 검색조건 초기값 ***********/        
        if (regDiv.equals("REC")) {
            request.setAttribute("rRecEntity", rEntity);
            
        } else if (regDiv.equals("COM")) {
            request.setAttribute("rComEntity", rEntity);
            
        } else {
            request.setAttribute("rAllEntity", rEntity);
        }
        
    }
    
    /**
     * 나의 민원 진행상태 착공전설계도확인 정보를 가져온다.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadStworkData(HttpServletRequest request, MystoreParam pm) throws Exception {
        
        ReportEntity rEntity = null;
        ReportDAO rDAO = new ReportDAO();      
        
        String regproc = "3";  // 진행상태
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();   
        sbSQL.append("SELECT                                             \n");
        sbSQL.append("        COUNT(PT_U_ALL.RECV_NUM) AS PT_U_ALL_CNT,  \n");
        sbSQL.append("        COUNT(PT_U_REC.RECV_NUM) AS PT_U_REC_CNT,  \n");
        sbSQL.append("        COUNT(PT_U_COM.RECV_NUM) AS PT_U_COM_CNT   \n");

        // 착공전설계도확인
        sbSQL.append("   FROM PT_HOME_CONSTRUCTION PT_H                 \n");
        
        // ========== 전체 : S ==========
        sbSQL.append("  LEFT JOIN PT_UB_CONSTRUCTION PT_U_ALL               \n");
        sbSQL.append("         ON PT_H.RECV_NUM = PT_U_ALL.RECV_NUM         \n");
        // ========== 전체 : E ==========
        
        // ========== 접수중 : S ==========
        sbSQL.append("  LEFT JOIN PT_UB_CONSTRUCTION PT_U_REC                  \n");
        sbSQL.append("         ON PT_H.RECV_NUM = PT_U_REC.RECV_NUM         \n"); 
        sbSQL.append("        AND PT_U_REC.PROC_STE != ?                    \n");
        
        rDAO.setValue(i++, regproc);
        // ========== 접수중 : E ==========
        
        // ========== 완료 : S ==========
        sbSQL.append("  LEFT JOIN PT_UB_CONSTRUCTION PT_U_COM                  \n");
        sbSQL.append("         ON PT_H.RECV_NUM = PT_U_COM.RECV_NUM         \n");   
        sbSQL.append("        AND PT_U_COM.PROC_STE = ?                     \n");
        
        rDAO.setValue(i++, regproc);
        // ========== 완료 : E ==========
        
        sbSQL.append("  WHERE PT_H.USER_ID = ?                              \n");

        rDAO.setValue(i++, user.getUSER_ID());

        rEntity = rDAO.select(sbSQL.toString());
      
        request.setAttribute("cEntity", rEntity);
    }
    
    
    /**
     * 나의 민원 진행상태 사용전검사 정보를 가져온다.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadUseBeforeData(HttpServletRequest request, MystoreParam pm) throws Exception {
        
        ReportEntity rEntity = null;
        ReportDAO rDAO = new ReportDAO();      
        
        String regproc = "3";  // 진행상태
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();   
        sbSQL.append("SELECT                                            \n");
        sbSQL.append("        COUNT(PT_U_ALL.RECV_NUM) AS PT_U_ALL_CNT,  \n");
        sbSQL.append("        COUNT(PT_U_REC.RECV_NUM) AS PT_U_REC_CNT,  \n");
        sbSQL.append("        COUNT(PT_U_COM.RECV_NUM) AS PT_U_COM_CNT   \n");

        // 행정처분 마스터
        sbSQL.append("   FROM PT_HOME_USEBEFORE PT_H \n");
        
        // ========== 전체 : S ==========
        sbSQL.append("  LEFT JOIN PT_UB_USEBEFORE PT_U_ALL                  \n");
        sbSQL.append("         ON PT_H.RECV_NUM = PT_U_ALL.RECV_NUM         \n");
        // ========== 전체 : E ==========
        
        // ========== 접수중 : S ==========
        sbSQL.append("  LEFT JOIN PT_UB_USEBEFORE PT_U_REC                  \n");
        sbSQL.append("         ON PT_H.RECV_NUM = PT_U_REC.RECV_NUM         \n"); 
        sbSQL.append("        AND PT_U_REC.PROC_STE != ?                    \n");
        
        rDAO.setValue(i++, regproc);
        // ========== 접수중 : E ==========
        
        // ========== 완료 : S ==========
        sbSQL.append("  LEFT JOIN PT_UB_USEBEFORE PT_U_COM                  \n");
        sbSQL.append("         ON PT_H.RECV_NUM = PT_U_COM.RECV_NUM         \n");   
        sbSQL.append("        AND PT_U_COM.PROC_STE = ?                     \n");
        
        rDAO.setValue(i++, regproc);
        // ========== 완료 : E ==========
        
        sbSQL.append("  WHERE PT_H.USER_ID = ?                              \n");

        rDAO.setValue(i++, user.getUSER_ID());

        rEntity = rDAO.select(sbSQL.toString());
      
        request.setAttribute("uEntity", rEntity);
    }
    
    
    /**
     * 나의 민원 진행상태 위법관리 정보를 가져온다.
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadIllCountData(HttpServletRequest request, MystoreParam pm, String regDiv) throws Exception {
        
        ReportEntity rEntity = null;
        ReportDAO rDAO = new ReportDAO();      
        
        String regproc = "C00003";  // 진행상태
        
        int i = 1;
        
        StringBuffer sbSQL = new StringBuffer();   
        sbSQL.append("SELECT                                                \n");
        sbSQL.append("        COUNT(PT_M_SUS.WRT_NUM) AS PT_M_SUS_ALL_CNT,  \n");
        sbSQL.append("        COUNT(PT_M_CAN.WRT_NUM) AS PT_M_CAN_ALL_CNT,  \n");
        sbSQL.append("        COUNT(PT_M_NEG.WRT_NUM) AS PT_M_NEG_ALL_CNT,  \n");
        sbSQL.append("        COUNT(PT_M_WAR.WRT_NUM) AS PT_M_WAR_ALL_CNT,  \n");
        sbSQL.append("        COUNT(PT_M_COR.WRT_NUM) AS PT_M_COR_ALL_CNT,  \n");
        sbSQL.append("        COUNT(PT_M_PRO.WRT_NUM) AS PT_M_PRO_ALL_CNT   \n");

        // 행정처분 마스터
        sbSQL.append("   FROM PT_R_COMPANY_MASTER PT_C \n");
        
        // ========== 영업정지 : S ==========
        sbSQL.append("  LEFT JOIN PT_M_MASTER PT_M_SUS                      \n");
        sbSQL.append("         ON PT_C.TMP_WRT_NUM = PT_M_SUS.TMP_WRT_NUM   \n");
        sbSQL.append("        AND PT_M_SUS.DISPO_CONT = 'M00002'            \n");        
        
        // 접수중
        if (regDiv.equals("REC")) {
            sbSQL.append("            AND PT_M_SUS.MOT_STE != ?     \n");
            rDAO.setValue(i++, regproc);
            
        // 등록완료
        } else if (regDiv.equals("COM")) {
            sbSQL.append("            AND PT_M_SUS.MOT_STE = ?      \n");
            rDAO.setValue(i++, regproc);
        }
        // ========== 영업정지 : E ==========
        
        // ========== 등록취소 : S ==========
        sbSQL.append("  LEFT JOIN PT_M_MASTER PT_M_CAN                      \n");
        sbSQL.append("         ON PT_C.TMP_WRT_NUM = PT_M_CAN.TMP_WRT_NUM   \n");
        sbSQL.append("        AND PT_M_CAN.DISPO_CONT = 'M00001'            \n");
        
        // 접수중
        if (regDiv.equals("REC")) {
            sbSQL.append("            AND PT_M_CAN.MOT_STE != ?      \n");
            rDAO.setValue(i++, regproc);
            
        // 등록완료
        } else if (regDiv.equals("COM")) {
            sbSQL.append("            AND PT_M_CAN.MOT_STE = ?       \n");
            rDAO.setValue(i++, regproc);
        }
        // ========== 등록취소 : E ==========
        
        // ========== 과태료부과 : S ========== 
        sbSQL.append("  LEFT JOIN PT_M_MASTER PT_M_NEG                      \n");
        sbSQL.append("         ON PT_C.TMP_WRT_NUM = PT_M_NEG.TMP_WRT_NUM   \n");
        sbSQL.append("        AND PT_M_NEG.DISPO_CONT = 'M00003'            \n");
        
        // 접수중
        if (regDiv.equals("REC")) {
            sbSQL.append("            AND PT_M_NEG.MOT_STE != ?     \n");
            rDAO.setValue(i++, regproc);
            
        // 등록완료
        } else if (regDiv.equals("COM")) {
            sbSQL.append("            AND PT_M_NEG.MOT_STE = ?      \n");
            rDAO.setValue(i++, regproc);
        }
        // ========== 과태료부과 : E ========== 
        
        // ==========  경고조치 : S ========== 
        sbSQL.append("  LEFT JOIN PT_M_MASTER PT_M_WAR                      \n");
        sbSQL.append("         ON PT_C.TMP_WRT_NUM = PT_M_WAR.TMP_WRT_NUM   \n");
        sbSQL.append("        AND PT_M_WAR.DISPO_CONT = 'M00006'            \n");
        
        // 접수중
        if (regDiv.equals("REC")) {
            sbSQL.append("            AND PT_M_WAR.MOT_STE = ?      \n");
            rDAO.setValue(i++, regproc);
            
        // 등록완료
        } else if (regDiv.equals("COM")) {
            sbSQL.append("            AND PT_M_WAR.MOT_STE != ?     \n");
            rDAO.setValue(i++, regproc);
        }        
        // ==========  경고조치 : E ========== 
        
        // ========== 시정명령 : S ========== 
        sbSQL.append("  LEFT JOIN PT_M_MASTER PT_M_COR                      \n");
        sbSQL.append("         ON PT_C.TMP_WRT_NUM = PT_M_COR.TMP_WRT_NUM   \n");
        sbSQL.append("        AND PT_M_COR.DISPO_CONT = 'M00004'            \n");
        
        // 접수중
        if (regDiv.equals("REC")) {
            sbSQL.append("            AND PT_M_COR.MOT_STE = ?      \n");
            rDAO.setValue(i++, regproc);
            
        // 등록완료
        } else if (regDiv.equals("COM")) {
            sbSQL.append("            AND PT_M_COR.MOT_STE != ?     \n");
            rDAO.setValue(i++, regproc);
        }    
        // ========== 시정명령 : E ========== 
        
        // ========== 형사고발 : S ========== 
        sbSQL.append("  LEFT JOIN PT_M_MASTER PT_M_PRO                      \n");
        sbSQL.append("         ON PT_C.TMP_WRT_NUM = PT_M_PRO.TMP_WRT_NUM   \n");
        sbSQL.append("        AND PT_M_PRO.DISPO_CONT = 'M00005'            \n");
        
        // 접수중
        if (regDiv.equals("REC")) {
            sbSQL.append("            AND PT_M_PRO.MOT_STE = ?      \n");
            rDAO.setValue(i++, regproc);
            
        // 등록완료
        } else if (regDiv.equals("COM")) {
            sbSQL.append("            AND PT_M_PRO.MOT_STE != ?     \n");
            rDAO.setValue(i++, regproc);
        }    
        // ========== 형사고발 : E ========== 
        
        sbSQL.append("  WHERE PT_C.COI_WRT_NUM = ?  \n");
        sbSQL.append("    AND PT_C.MANA_NUM = ?     \n");

        rDAO.setValue(i++, user.getREG_NUM());
        rDAO.setValue(i++, user.getCOM_NUM());

        rEntity = rDAO.select(sbSQL.toString());

        /****** 검색조건 초기값 ***********/        
        if (regDiv.equals("REC")) {
            request.setAttribute("mRecEntity", rEntity);
            
        } else if (regDiv.equals("COM")) {
            request.setAttribute("mComEntity", rEntity);
            
        } else {
            request.setAttribute("mAllEntity", rEntity);
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
