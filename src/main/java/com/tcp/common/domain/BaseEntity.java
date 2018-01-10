package com.tcp.common.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BaseEntity<ID> {
    private ID id;
}
