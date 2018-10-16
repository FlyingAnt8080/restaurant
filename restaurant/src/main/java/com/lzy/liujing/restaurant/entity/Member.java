package com.lzy.liujing.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
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

    /**
     * 生日
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//用于返回json数据时格式化日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")//用接口传入String类型时间转换成date类型
    private Date birthday;
    /**
     * 电话号
     */
    private String phone;

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
                ", memberCode='" + memberCode + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", phone='" + phone + '\'' +
                ", memberCategory=" + memberCategory +
                ", totalMoney=" + totalMoney +
                '}';
    }
}
