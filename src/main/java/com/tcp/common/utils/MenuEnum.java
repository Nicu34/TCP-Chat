package com.tcp.common.utils;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
public enum MenuEnum {
    ADD_USER("ADD_USER"), GET_USERS("GET_USERS"), MESSAGE("MESSAGE"),
    OK("OK"), ERROR("ERROR");

    @Getter
    @Setter
    private String action;
}
