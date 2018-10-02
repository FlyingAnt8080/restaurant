package com.lzy.liujing.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created with IDEA
 * author:LiuJing
 * Date:2018/10/1
 * Time:22:41
 * 会员类
 */
public class Member extends BaseBean {
    /**
     * 会员id
     */
    private Long memberId;
    /**
     * 微信小程序openId
     */
    private String openId;
    /**
     * 会员号
     */
    private String memberCode;
    /**
     * 会员名
     */
    private String name;
    /**
     * 性别
     */
    private String gender;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    /**
     * 生日
     */
    private Date birthday;
    /**
     * 电话号
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 会员类型
     */
    private MemberCategory memberCategory;
    /**
     * 总消费金额
     */
    private Double totalMoney;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getOpenId() {
        return openId;
    }


    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public MemberCategory getMemberCategory() {
        return memberCategory;
    }

    public void setMemberCategory(MemberCategory memberCategory) {
        this.memberCategory = memberCategory;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", openId='" + openId + '\'' +
                ", memberCode='" + memberCode + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", memberCategory=" + memberCategory +
                ", totalMoney=" + totalMoney +
                '}';
    }
}
