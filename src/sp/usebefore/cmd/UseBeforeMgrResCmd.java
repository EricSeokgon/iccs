package sp.usebefore.cmd;

import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFDBUtil;
import kjf.util.KJFDate;
import kjf.util.KJFLog;
import kjf.util.KJFSelectOPS;
import kjf.util.KJFUtil;
import kjf.util.LoginException;
import kjf.cfg.Config;

import org.apache.struts.action.ActionForm;

import sp.uent.UserEnt;
import sp.usebefore.UseBeforeParam;

/***************************************************************************
 * <p>Title       : UseBeforeCUDCmd Class</p>
 * <p>Description : ������˻� ���, ����, ���� ó�� Ŭ����</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : ������˻� ���, ����, ���� ó���� �Ѵ�. 
 * 
 * @version 1.0
 * @author  PKT
 **************************************************************************/
public class UseBeforeMgrResCmd implements KJFCommand {
    
	UserEnt user;
	
    public UseBeforeMgrResCmd() {
        
    }
    
    public String execute(HttpServletRequest request, ActionForm form) throws Exception {        
        
        String cmd = request.getParameter("cmd");
        
        user = (UserEnt)request.getSession().getAttribute("user");
        
        // �α��� ���� üũ
        if (KJFUtil.isEmpty(user)) {
            throw new LoginException(request, "ȸ���� ������ �α��� üũ", "../member/login.do");
        }  
        
        // �˻����� ������ üũ
        UseBeforeParam pm = checkPm(request, form);
        
//        String UB_SERVER[]= null;
//        String ub_temp = "";
//        ub_temp = Config.props.get("UB_ENDPOINT");
//        
//        if (ub_temp.indexOf("/") >= 0){
//        	UB_SERVER = ub_temp.split("/");
//	    	for (int i =0 ; i < UB_SERVER.length; i++){
//	    		if (UB_SERVER[i].equals(user.getSIGUNGU_CODE())  && user.isUseUbSys() ){
//	    			loadDate(request,pm);
//	    		}
//	    	}
//        } else {
    		//if (ub_temp.equals(user.getSIGUNGU_CODE())  && user.isUseUbSys() ){
    			loadDate(request,pm);
    		//}
//        }
   
        
        request.setAttribute("pm", pm);
        return request.getParameter("cmd");
    }
    
    
    /**
     * ������˻� ��ȸ
     * 
     * @param request
     * @param form
     * @throws Exception
     */
    public void loadDate(HttpServletRequest request, UseBeforeParam pm) throws Exception {
        
    	ReportDAO rDAO 				= new ReportDAO();      
    	ReportEntity rEntity 		= null;
    	ReportEntity sEntity 		= null;
    	
	   	String cmd					= request.getParameter("cmd");
    	String SIDO_CODE 			= user.getSIDO_CODE();
		String SIGUNGU_CODE 		= user.getSIGUNGU_CODE();
		
		String scRECV_ST			= request.getParameter("scRECV_ST");
		String scRECV_ET			= request.getParameter("scRECV_ET");
		String scAPPLPER_NM			= request.getParameter("scAPPLPER_NM");
		String scOPE_NAME			= request.getParameter("scOPE_NAME");
		String scCIV_RECV_NUM		= request.getParameter("scCIV_RECV_NUM");
		String scUSEBEFINSP_DELINUM	= request.getParameter("scUSEBEFINSP_DELINUM");
        
		String scOK					= "null".equals(KJFUtil.print(pm.getScOK(),"0"))?"0":KJFUtil.print(pm.getScOK(),"0");
		String scNO					= "null".equals(KJFUtil.print(pm.getScNO(),"0"))?"0":KJFUtil.print(pm.getScNO(),"0");
		String scNO_2				= "null".equals(KJFUtil.print(pm.getScNO_2(),"0"))?"0":KJFUtil.print(pm.getScNO_2(),"0");
		String scNO_3				= "null".equals(KJFUtil.print(pm.getScNO_3(),"0"))?"0":KJFUtil.print(pm.getScNO_3(),"0");
        
    	String selectSQL=
    		"	SELECT '0' AS CHECKER, RECV_NUM, RECV_DT, APPLPER_NM, APPLPER_TELNUM, OPE_REP, OPE_NAME,  \n" +
    		"   	   OPE_TELNUM, CC.CODE_NAME AS USE, AREA, NUM_FL, WORK_ITEM, DELI_DT,USEBEFINSP_DELINUM,     \n" +
    		"		   CASE NAPPL_YN WHEN '1' THEN '����' WHEN '2' THEN '������' WHEN '3' THEN '����' WHEN '4' THEN '��ø' ELSE ' ' END AS NAPPL_YN,	\n" +
    		"		   INSP_SPOT_ADDR||' '||RTRIM(INSP_SPOT_DETAILADDR) AS ADDR     \n" ;
    	
    	String fromSQL = " FROM PT_UB_USEBEFORE  UB LEFT JOIN PT_COM_CODE CC ON CC.CODE = UB.USE AND CC.P_CODE = 'BLDDIV'  \n";
    	
    	String whereSQL = "WHERE 1 = 1 \n";
    	
    	whereSQL += "AND NAPPL_YN is not null   \n" +
    				"AND SIDO_CODE = '"+SIDO_CODE+"'   \n"+
    				"AND SIGUNGU_CODE = '"+SIGUNGU_CODE+"'  \n";   
    	  	
      	if(!KJFUtil.isEmpty(scCIV_RECV_NUM)){    		
    		whereSQL += "AND CIV_RECV_NUM = '"+scCIV_RECV_NUM+"'  \n";    		
    	}
    	
    	if(!KJFUtil.isEmpty(scAPPLPER_NM)){    		
    		whereSQL += "AND APPLPER_NM LIKE '"+scAPPLPER_NM+"%'  \n";    		
    	}
    	
    	if(!KJFUtil.isEmpty(scUSEBEFINSP_DELINUM)){
    		whereSQL += "AND USEBEFINSP_DELINUM LIKE '"+scUSEBEFINSP_DELINUM+"%'  \n";
    	}
    	
//    	if("0".equals(scOK) && "1".equals(scNO)){    		
//    		whereSQL += "AND NAPPL_YN = '2'  \n";    		
//    	}
//    	
//    	if("1".equals(scOK) && "0".equals(scNO)){        		
//    		whereSQL += "AND NAPPL_YN = '1'  \n";    		
//    	}
    	    	
    	if("1".equals(scOK) && "0".equals(scNO) && "0".equals(scNO_2) && "0".equals(scNO_3)){whereSQL += "AND NAPPL_YN = '1'  \n";
    	} else if("0".equals(scOK) && "1".equals(scNO) && "0".equals(scNO_2) && "0".equals(scNO_3)){whereSQL += "AND NAPPL_YN = '2'  \n";
    	} else if("0".equals(scOK) && "0".equals(scNO) && "1".equals(scNO_2) && "0".equals(scNO_3)){whereSQL += "AND NAPPL_YN = '3'  \n";
    	} else if("0".equals(scOK) && "0".equals(scNO) && "0".equals(scNO_2) && "1".equals(scNO_3)){whereSQL += "AND NAPPL_YN = '4'  \n";
    	} else if("1".equals(scOK) && "1".equals(scNO) && "0".equals(scNO_2) && "0".equals(scNO_3)){whereSQL += "AND ( NAPPL_YN ='1' OR NAPPL_YN='2' ) \n";
		} else if("0".equals(scOK) && "1".equals(scNO) && "1".equals(scNO_2) && "0".equals(scNO_3)){whereSQL += "AND ( NAPPL_YN ='2' OR NAPPL_YN='3' ) \n";
		} else if("0".equals(scOK) && "0".equals(scNO) && "1".equals(scNO_2) && "1".equals(scNO_3)){whereSQL += "AND ( NAPPL_YN ='3' OR NAPPL_YN='4' ) \n";
		} else if("1".equals(scOK) && "0".equals(scNO) && "0".equals(scNO_2) && "1".equals(scNO_3)){whereSQL += "AND ( NAPPL_YN ='1' OR NAPPL_YN='4' ) \n";    	
		} else if("0".equals(scOK) && "1".equals(scNO) && "0".equals(scNO_2) && "1".equals(scNO_3)){whereSQL += "AND ( NAPPL_YN ='2' OR NAPPL_YN='4' ) \n";
		} else if("1".equals(scOK) && "0".equals(scNO) && "1".equals(scNO_2) && "0".equals(scNO_3)){whereSQL += "AND ( NAPPL_YN ='1' OR NAPPL_YN='3' ) \n";    	
		} else if("1".equals(scOK) && "1".equals(scNO) && "1".equals(scNO_2) && "0".equals(scNO_3)){whereSQL += "AND ( NAPPL_YN ='1' OR NAPPL_YN='2' OR NAPPL_YN='3') \n";
		} else if("0".equals(scOK) && "1".equals(scNO) && "1".equals(scNO_2) && "1".equals(scNO_3)){whereSQL += "AND ( NAPPL_YN ='2' OR NAPPL_YN='3' OR NAPPL_YN='4') \n";    	
		} else if("1".equals(scOK) && "1".equals(scNO) && "0".equals(scNO_2) && "1".equals(scNO_3)){whereSQL += "AND ( NAPPL_YN ='1' OR NAPPL_YN='2' OR NAPPL_YN='4') \n";}
		
//    	System.out.println(scOK+"-"+scNO+"-"+scNO_2+"-"+scNO_3);
//    	System.out.println(whereSQL);
    	
    	if(!KJFUtil.isEmpty(scRECV_ST) && !KJFUtil.isEmpty(scRECV_ET)){
    		whereSQL += "AND RECV_DT BETWEEN '"+scRECV_ST.replace("-", "")+"' AND '"+scRECV_ET.replace("-","")+"'  \n";
    	} else {
    		SimpleDateFormat sdf = new SimpleDateFormat ( "yyyyMMdd" );
    		Date toDay = new Date();
    		Date beforeDay = new Date();
    		beforeDay.setTime (toDay.getTime ( ) - ( (long) 1000 * 60 * 60 * (24 * 14) ) );
    		scRECV_ST = sdf.format(beforeDay.getTime());
    		scRECV_ET = sdf.format(toDay.getTime());
    		whereSQL += "AND RECV_DT BETWEEN '"+scRECV_ST+"' AND '"+scRECV_ET+"'  \n";    		
    	}
    	
    	if(!KJFUtil.isEmpty(scOPE_NAME)){
    		whereSQL += "AND OPE_NAME LIKE '%"+scOPE_NAME+"%'  \n";
    	}
    	
    	String orderSQL = " ORDER BY RECV_DT ASC  ";
    	
    	// �������� ǥ��
    	String sidoSQL = " SELECT SIDO_NM, SIGUNGU_NM FROM PT_SIDO_CODE \n" +
					 "  WHERE AREA_CODE = '" + user.getSIGUNGU_CODE()+ "' \n" +
					 "  ORDER BY AREA_CODE";
    	
    	sEntity    = rDAO.select(sidoSQL);
    	
        /* ************************** ����¡ ���� START **************************/
        StringBuffer sbCntSQL = new StringBuffer();
        sbCntSQL.append(" SELECT COUNT(*)  CNT  \n");
        sbCntSQL.append(fromSQL.toString());
        sbCntSQL.append(whereSQL.toString());        
        
        //��ü ��� ��
        String totalCount="";

        //�������� ��� ��
        int rowPerPage = KJFUtil.str2int(pm.getRowPerPage());

        //���� ������ ��ȣ
        int nowPage = 1;
        nowPage = KJFUtil.isEmpty(pm.getNowPage()) ? 1 : Integer.parseInt(pm.getNowPage());
        rEntity = rDAO.select(sbCntSQL.toString());
        
        totalCount = rEntity.getValue(0, "CNT");
        if (rowPerPage == 0) rowPerPage = Integer.parseInt(totalCount);//�߰�
        if ((rowPerPage*nowPage) - Integer.parseInt(totalCount) > rowPerPage) nowPage = 1;

        pm.setTotalCount(totalCount);
        pm.setNowPage(String.valueOf(nowPage));
        /* *************************** ����¡ ����  END **************************/

        rEntity    = rDAO.select(selectSQL+fromSQL+whereSQL+orderSQL, nowPage, rowPerPage);
       
        /****** �˻����� �ʱⰪ ***********/
        request.setAttribute("pm", pm);
        request.setAttribute("sEntity", sEntity);
        request.setAttribute("ListEntity", rEntity);
        
    }
    
    /**
     * �˻����� �� �ʱⵥ��Ÿ �ε�
     * @param HttpServletRequest
     * @return
     */
    private void loadCondition(HttpServletRequest request,UseBeforeParam pm)throws Exception{
       
    }//end loadCondition
    
    /**
     * �� üũ �޼ҵ�
     * 
     * @param request
     * @param form
     * @return
     * @throws Exception
     */
    private UseBeforeParam checkPm(HttpServletRequest request, ActionForm form) throws Exception {

        UseBeforeParam pm = (UseBeforeParam)form;
		
        pm.setScRecv_num(request.getParameter("scRECV_NUM"));
		
        pm.setScOK(KJFUtil.print(request.getParameter("scOK"),"0"));
		pm.setScNO(KJFUtil.print(request.getParameter("scNO"),"0"));
		
        // ����¡ ����
        if (KJFUtil.isEmpty(pm.getRowPerPage())) {
            pm.setRowPerPage("10");
        }
        
        // �Ķ���� �����
        KJFLog.debug(pm.toString(request));
        
        return pm;
    }

}
