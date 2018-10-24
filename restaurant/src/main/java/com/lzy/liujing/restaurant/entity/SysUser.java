package com.lzy.liujing.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 基类
 */
public class SysUser extends BaseBean{
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 登录账户
     */
    @NotNull(message = "账号不能为空！")
    private String loginCode;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 电话
     */
    private String phone;
    /**
     * 邮箱
     */
    private String  email;
    /**
     * 性别
     */
    private String gender;
    /**
     * 生日
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    /**
     * 身份证号
     */
    private String idNumber;
    /**
     * 家庭住址
     */
    private String address;
    private String oldPwd;
    private String newPwd;
    /**
     * 用户角色
     */
    public SysRole role;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public SysRole getRole() {
        return role;
    }

    public void setRole(SysRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "userId=" + userId +
                ", loginCode='" + loginCode + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", idNumber='" + idNumber + '\'' +
                ", address='" + address + '\'' +
                ", oldPwd='" + oldPwd + '\'' +
                ", newPwd='" + newPwd + '\'' +
                ", role=" + role +
                '}';
    }
}
