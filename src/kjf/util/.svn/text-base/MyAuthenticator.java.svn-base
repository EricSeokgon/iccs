package kjf.util;

public class MyAuthenticator extends javax.mail.Authenticator {

    private String id;
    private String pw;

    public MyAuthenticator(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }

    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
        return new javax.mail.PasswordAuthentication(id, pw);
    }

}
