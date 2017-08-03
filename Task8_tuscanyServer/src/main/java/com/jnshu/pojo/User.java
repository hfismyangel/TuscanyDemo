package com.jnshu.pojo;

import java.io.Serializable;

/**
 * Created by hfismyangel@163.com on 2017/7/6.
 */
public class User implements Serializable{
    private int id;
    private String userName;
    private String password;
    private Long  telephone;
    private String email;
    private String headpic;
    private String uid;
    private Long login_at;
    private Long create_at;
    private Long update_at;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", telephone=" + telephone +
                ", email='" + email + '\'' +
                ", headpic='" + headpic + '\'' +
                ", uid='" + uid + '\'' +
                ", login_at=" + login_at +
                ", create_at=" + create_at +
                ", update_at=" + update_at +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getTelephone() {
        return telephone;
    }

    public void setTelephone(Long telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeadpic() {
        return headpic;
    }

    public void setHeadpic(String headpic) {
        this.headpic = headpic;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Long getLogin_at() {
        return login_at;
    }

    public void setLogin_at(Long login_at) {
        this.login_at = login_at;
    }

    public Long getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Long create_at) {
        this.create_at = create_at;
    }

    public Long getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Long update_at) {
        this.update_at = update_at;
    }

}
