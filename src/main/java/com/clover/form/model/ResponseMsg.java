package com.clover.form.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class ResponseMsg {

    private Long id;
    private String msg;
}
