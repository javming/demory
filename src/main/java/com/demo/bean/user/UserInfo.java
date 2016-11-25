package com.demo.bean.user;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2016-10-11-15:26
 */
public class UserInfo {
    private int id;
    private String openId;
    private String username;
    private String password;
    private String realname;//真实姓名
    private String nickname;//昵称
    private String address;
    private String telhpone;
    private String emal;
    private int age;
    private int sex;//1男 2女
    private String signTime;
    private int role;//0普通用户，1管理员
    private int isDel;//逻辑删除

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelhpone() {
        return telhpone;
    }

    public void setTelhpone(String telhpone) {
        this.telhpone = telhpone;
    }

    public String getEmal() {
        return emal;
    }

    public void setEmal(String emal) {
        this.emal = emal;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", openId='" + openId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", realname='" + realname + '\'' +
                ", nickname='" + nickname + '\'' +
                ", address='" + address + '\'' +
                ", telhpone='" + telhpone + '\'' +
                ", emal='" + emal + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", signTime='" + signTime + '\'' +
                ", role=" + role +
                ", isDel=" + isDel +
                '}';
    }

}
