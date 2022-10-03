package com.clover.form.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class AuthRequest {

    private String email;
    private String password;
}
