package com.tcp.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity<Long> {
    private String name;
    private String host;
    private Integer port;
}
