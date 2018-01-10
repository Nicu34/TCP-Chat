package com.tcp.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message extends BaseEntity<Long> {
    private User sender;
    private String text;
    private User receiver;
}
