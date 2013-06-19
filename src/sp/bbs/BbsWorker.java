/***************************************************************************
 * ���ϸ�   : BbsWorker.java
 * ����	   : bbs ����� ���� �Լ��� ����
 * �̷»���
 * CH00    : 2006/06/05 ���� �����ۼ�
 *           2007/11/30 ������ ����
 **************************************************************************/

package sp.bbs;

import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFUtil;


public class BbsWorker{

    /***************************************************************************
     * �Խ��Ǳ⺻������ �����´�.
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
     * ����� Ư�� ���� �˻�
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
