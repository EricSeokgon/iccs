package sp.service;

import java.util.ArrayList;

public class AreaBaseBean {
    
    public String sido_code;        // 시도 코드
    public String sido_name;        // 시도 명
    
    public ArrayList areaInfoArray; // 지역 정보
    
    public AreaBaseBean() {        
    }
    
    public String getSido_code() {
        return sido_code;
    }

    public void setSido_code(String sido_code) {
        this.sido_code = sido_code;
    }

    public String getSido_name() {
        return sido_name;
    }

    public void setSido_name(String sido_name) {
        this.sido_name = sido_name;
    }

    public ArrayList getAreaInfoArray() {
        return areaInfoArray;
    }

    public void setAreaInfoArray(ArrayList areaInfoArray) {
        this.areaInfoArray = areaInfoArray;
    }
}
