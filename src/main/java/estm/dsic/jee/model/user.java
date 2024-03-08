package estm.dsic.jee.model;

public class User {
    private int id;
    private String email;
    private String password;
    private int isadmin;
    private int isverify;
    

    public User(){
        
    }
    

    public String getemail() {
        return email;
    }
    public void setemail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getid() {
        return id;
    }
    public void setid(int id) {
        this.id = id;
    }
    
    public int getisadmin() {
        return isadmin;
    }
    public void setisadmin(int isadmin) {
        this.isadmin = isadmin;
    }

    public int getisverify() {
        return isverify;
    }
    public void setiisverify(int isverify) {
        this.isverify = isverify;
    }


}