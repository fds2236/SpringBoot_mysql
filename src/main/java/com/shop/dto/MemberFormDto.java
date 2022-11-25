package com.shop.dto;

import lombok.Getter;
import lombok.Setter;

// 회원가입화면으로부터 넘어노는 가입 정보를 담을 dto 객체
@Getter @Setter
public class MemberFormDto {
    private String name;
    private String email;
    private String password;
    private String address;

}
