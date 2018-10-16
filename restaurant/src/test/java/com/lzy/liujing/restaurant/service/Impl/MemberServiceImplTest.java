package com.lzy.liujing.restaurant.service.Impl;

import com.lzy.liujing.restaurant.entity.CustomPageInfo;
import com.lzy.liujing.restaurant.entity.Member;
import com.lzy.liujing.restaurant.service.MemberService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberServiceImplTest {
    @Autowired
    private MemberService memberService;
    @Test
    @Ignore
    public void update(){
        Member member = new Member();
        member.setMemberId(5L);
        member.setCreateTime(new Date());
        member.setPhone("17781036105");
        member.setBirthday(new Date());
        member.setGender("女");
        member.setName("刘静");
        memberService.update(member);
    }

    @Test
    @Ignore
    public void addMember(){
        Member member = new Member();
        member.setMemberCode("17781036106");
        member.setCreateTime(new Date());
        member.setPhone("17781036105");
        member.setBirthday(new Date());
        member.setGender("男");
        member.setName("顾客4");
        memberService.addMember(member);
    }

    @Test
    public void findPage() {
        CustomPageInfo<Member> info = new CustomPageInfo<>();
        info.setPageNum(1);
        info.setPageSize(2);
        /*Member member = new Member();
        member.setName("顾客");
        member.setGender("女");
        info.setT(member);*/
        CustomPageInfo<Member> result = memberService.findPage(info);
    }


    @Test
    @Ignore
    public void findMemberById() {
        Member member = new Member();
        member.setMemberId(2L);
        Member me = memberService.findMemberById(member);
    }

    @Test
    @Ignore
    public void deleteByIds(){
        memberService.deleteByIds("4,5,");
    }
}