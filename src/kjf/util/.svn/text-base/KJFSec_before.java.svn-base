package kjf.util;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * <p>Subsystem     :  </p>
 * <p>Title         : 기본적인 암호화 유틸함수 </p>
 * <p>Description   : 기본적인 암호화 유틸함수를 구현하여 제공. </p>
 * @author 김경덕
 * @version 1.0 2003.05.29 <br/>
 */
public class KJFSec_before {
	
	private final String map ="Ra7LMsKR2Sw=";
		
	private final BASE64Decoder s_decoder = new BASE64Decoder();
	
	byte[] raw = null;
	SecretKeySpec skeySpec =null;
	Cipher cipher = null;
	
	/**
	 * 시작함수
	 * @throws Exception
	 */
    protected void init() throws Exception {    	
		raw = s_decoder.decodeBuffer(map);
		skeySpec = new SecretKeySpec(raw, "DES"); 
		cipher = Cipher.getInstance("DES","SunJCE"); 		  
    }	
    
    /**
     * encoding
     * @param data String
     * @return String
     * @throws Exception
     */
    protected String encoding(String data) throws Exception {
		init();
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

		String b64str = new BASE64Encoder().encode(cipher.doFinal(data.getBytes()));
			
		return  b64str;
	}
    
    /**
     * decoding
     * @param b64Str String
     * @return String
     * @throws Exception
     */
    protected String decoding(String b64Str) throws Exception {
    	
    	byte[] data = new BASE64Decoder().decodeBuffer(b64Str);
    	
    	init();
    	cipher.init(Cipher.DECRYPT_MODE, skeySpec);
    	
    	return  new String(cipher.doFinal( data ));      
    }    
    
    /**
     * encode
     * @param data String
     * @return String
     * @throws Exception
     */
    public static  String encode(String data) throws Exception {	
		return new KJFSec().encoding(data);
	}    
    
    /**
     * decode
     * @param data String
     * @return String
     * @throws Exception
     */
    public  static String decode(String data) throws Exception {    	
		return new KJFSec().decoding(data);
	}   
    
  }