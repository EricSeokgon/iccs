package kjf.util;

import java.sql.SQLException;

import kjf.cfg.Config;
import kjf.util.MsgException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
/**
 * <p>Subsystem		:  </p>
 * <p>Title			: �⺻���� DB ��ƿ�Լ� </p>
 * <p>Description	: �⺻���� DB ��ƿ�Լ��� �����Ͽ� ����. </p>
 * <p>���� TABLE		: </p>
 * @author ����
 * @version 1.0	2006.02.26 <br/>
 */


public class KJFDB2Util{


    public static Connection getConnection(String DB_DRIVER, String DB_URL, String DB_USER, String DB_PASSWORD) throws SQLException {
        
        Connection conn = null;
        try {
            Class.forName(DB_DRIVER);
            conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);            
  
        } catch (ClassNotFoundException ee){
            System.out.println("����̹� �����ϴ�.");
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null)   try{ conn.close(); } catch(Exception ee) {};
        } 
          return conn;
    }

}
