package com.tcp.common.domain;


import com.tcp.common.utils.MenuEnum;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@lombok.Data
public class Data implements Serializable {

    private MenuEnum menuEnum;

    private String dataRequest;
}
