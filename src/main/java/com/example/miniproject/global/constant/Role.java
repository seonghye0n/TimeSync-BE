package com.example.miniproject.global.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {

    USER("일반 회원"), ADMIN("관리자");

    String name = "";

}
