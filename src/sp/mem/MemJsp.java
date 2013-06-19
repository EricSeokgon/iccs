/**
 * 파일명   : MemJsp.java
 * 설명	    : Mem  모듈의 공통 함수를 정의
 * 이력사항
 * CH00     :2006/07/15 오두식 최초작성 
 */
package sp.mem;

import java.util.Vector;
import java.util.HashMap;
import kjf.util.*;


public class MemJsp{
	
	private String[][] selField = {{"Mem_NAME","성명"},{"Mem_ID","아이디"},{"Mem_TEL","전화번호"},{"Mem_MOBILE","핸드폰"},{"Mem_EMAIL","이메일"}}; // 게시판 타입
	private String[][] selCondition = {{"like","유사검색"},{"=","단어일치"}}; //사용 유무
		
	public Vector getFileld(){
		return KJFUtil.makeSelect(this.selField);
	}
	
	public Vector getCondition(){
		return KJFUtil.makeSelect(this.selCondition);
	}
	
}
