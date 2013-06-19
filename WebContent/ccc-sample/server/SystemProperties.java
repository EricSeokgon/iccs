import java.io.*;
import java.util.*;


public class SystemProperties{

	public static void main(String args[]) {

		Properties props = System.getProperties(); 

		for (Enumeration enum = props.propertyNames(); enum.hasMoreElements();) {
			String key = (String)enum.nextElement();
			System.out.println(key + " = " + (String)(props.get(key)));

		}
	}


}

/*

<IfModule mod_weblogic.c>
    WebLogicHost 10.135.1.176
    WebLogicPort 7001
    MatchExpression *.jsp
    MatchExpression /servlet
    ConnectTimeoutSecs 20
    ConnectRetrySecs 2
</IfModule>
*/