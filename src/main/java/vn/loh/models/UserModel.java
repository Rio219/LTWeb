package vn.loh.models;

import java.io.Serializable;
import java.sql.Date;

public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String username;
    private String password;
    private String email;
    private String fullname;
    private String image;
    private int roleid;
    private Date createdate;
    private String phone;

    public UserModel() {
    }

    public UserModel(int id, String username, String password, String email, String fullname, String image, int roleid, Date createdate, String phone) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullname = fullname;
        this.image = image;
        this.roleid = roleid;
        this.createdate = createdate;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", fullname='" + fullname + '\'' +
                ", image='" + image + '\'' +
                ", roleid='" + roleid + '\'' +
                ", createdate='" + createdate.toString() + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public String getImage() {
        return image;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRoleid() {
        return roleid;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public String getPhone() {
        return phone;
    }
}