package kjf.util;

import java.util.StringTokenizer;

public class call_html {
    
    public static String parseHtml(String str,String sParse,String rParse) {
        if (str == null)
            return null;
        int total_length = str.length();
        
        StringBuffer stringbuffer = new StringBuffer();
        
        boolean flag = false;
        boolean flag1 = true;
        
        int sTar = str.indexOf(sParse);
        
        for (int j = sTar; j < total_length; j++)
        {
            String s1 = str.substring(j, j + 1);
            if (s1.equals(sParse)) {
                flag = true;
                flag1 = false;
            }
            if (!flag) {
                stringbuffer.append(s1);             
            }
            
            if (s1.equals(rParse)){
                flag = false;
                stringbuffer.append(s1);
            }
        }
        return stringbuffer.toString();
    }
    
    public static String replaceToBr(String str) {
        return replace(str, "\n", "<br>");
    }

    public static String replace(String str, String findStr, String findEndStr) {
        StringBuffer sb = new StringBuffer();
        try {
            if (str != null) {
                for (StringTokenizer st = new StringTokenizer(str, findStr,true); st.hasMoreTokens();) {
                    String token = st.nextToken();
                    if (token.equals(findStr))
                        sb.append(findEndStr);
                    else
                        sb.append(token);
                }
            }
        } catch (Exception e) {
            System.out.println("¿¡·¯");
        }
        return sb.toString();
    }    
    
}
