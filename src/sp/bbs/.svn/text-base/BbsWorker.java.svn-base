/***************************************************************************
 * 파일명   : BbsWorker.java
 * 설명	   : bbs 모듈의 공통 함수를 정의
 * 이력사항
 * CH00    : 2006/06/05 김경덕 최초작성
 *           2007/11/30 윤영수 수정
 **************************************************************************/

package sp.bbs;

import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFUtil;


public class BbsWorker{

    /***************************************************************************
     * 게시판기본정보를 가져온다.
     * 
     * @param CT_ID
     * @return
     * @throws Exception
     **************************************************************************/
    public  ReportEntity loadStatusFromDB(String CT_ID) throws  Exception {
        
        ReportDAO    rDAO        = new ReportDAO();
        ReportEntity rEntity     = null;
        
        String sql =
        	" SELECT *        	 			     \n " +
        	" 	FROM PT_BBS_COM_CT   		     \n " +
        	"  WHERE CT_ID = '" + CT_ID + "'     " ;
        
        rEntity = rDAO.select(sql);
        
        return rEntity;
    }


    /***************************************************************************
     * 사용자 특정 권한 검사
     * 
     * @param auth_level
     * @param user_level
     * @return
     **************************************************************************/
	public boolean checkAuth(String auth_level, String user_level) {
		boolean isAuth = false;

		if (user_level != null && !KJFUtil.isEmpty(user_level)) {
			
			String[] str = auth_level.split(",");

			for(int i = 0; i < str.length; i++) {
				if (user_level.equals(str[i])) {
					isAuth = true;
					break;
				}
			}
		}
        
    	return isAuth;
    }

}
