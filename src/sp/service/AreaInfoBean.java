package sp.service;

import java.util.ArrayList;

public class AreaInfoBean {
    
    public String sido_code;        // �õ� �ڵ�
    public String sigungu_code;     // �ñ��� �ڵ�
    public String sigungu_name;     // �ñ��� ��
    public String address;          // ���� �ּ�
    public String hompage;          // ���� Ȩ������ 
    public String tel;              // ���� ��ȭ��ȣ
    
    public ArrayList chargeArray;   // ����� ����
    
    public AreaInfoBean() {        
    }
    
    public String getSido_code() {
        return sido_code;
    }

    public void setSido_code(String sido_code) {
        this.sido_code = sido_code;
    }

    public String getSigungu_code() {
        return sigungu_code;
    }

    public void setSigungu_code(String sigungu_code) {
        this.sigungu_code = sigungu_code;
    }

    public String getSigungu_name() {
        return sigungu_name;
    }

    public void setSigungu_name(String sigungu_name) {
        this.sigungu_name = sigungu_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHompage() {
        return hompage;
    }

    public void setHompage(String hompage) {
        this.hompage = hompage;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public ArrayList getChargeArray() {
        return chargeArray;
    }

    public void setChargeArray(ArrayList chargeArray) {
        this.chargeArray = chargeArray;
    }
}
