package sp.service;

public class AreaChargeBean {
    
    public String sido_code;        // �õ� �ڵ�
    public String sigungu_code;     // �ñ��� �ڵ�
    public String name;             // �̸� 
    public String offiId;             // ID
	public String tel;              // ��ȭ��ȣ
    public String email;            // �̸���
    public String part;             // �Ҽ�
    
    public AreaChargeBean() {        
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

	public String getoffiId() {
		return offiId;
	}

	public void setOffiId(String offiId) {
		this.offiId = offiId;
	}
}
