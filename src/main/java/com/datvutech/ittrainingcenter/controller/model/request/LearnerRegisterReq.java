package com.datvutech.ittrainingcenter.controller.model.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.datvutech.ittrainingcenter.validation.annotation.ConfirmPassword;
import com.datvutech.ittrainingcenter.validation.annotation.ValidPassword;
import com.datvutech.ittrainingcenter.validation.model.ConfirmPasswordModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@ConfirmPassword(message = "Mật khẩu không khớp")
public class LearnerRegisterReq extends ConfirmPasswordModel implements Serializable {
    @NotBlank(message = "Tên không được để trống.")
    @Length(max = 12, message = "Tên chứa tối đa 12 kí tự.")
    private String firstName;

    @Length(max = 36, message = "Họ và tên đệm chứa tối đa 36 kí tự.")
    private String lastName;

    @NotBlank(message = "Email không được để trống.")
    @Length(max = 320)
    private String email;

    /* @NotBlank(message = "Mật khẩu không được để trống.") */
    /* @Length(min = 6, max = 16, message = "Mật khẩu chứa 6-16 kí tự.") */
    @ValidPassword
    private String password;

    private String confirmPassword;
}
