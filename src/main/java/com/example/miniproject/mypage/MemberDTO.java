package com.example.miniproject.mypage;

public class MemberDto {
    private String name;
    private String email;

    public MemberDto() {}

    public MemberDto(Member member) {
        this.name = member.getName();
        this.email = member.getEmail();
    }

}