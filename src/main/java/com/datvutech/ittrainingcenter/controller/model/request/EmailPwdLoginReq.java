package com.datvutech.ittrainingcenter.controller.model.request;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmailPwdLoginReq implements Serializable {
    private String email;

    private String password;
}
