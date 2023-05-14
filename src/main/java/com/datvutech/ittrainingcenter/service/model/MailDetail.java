package com.datvutech.ittrainingcenter.service.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MailDetail implements Serializable {
    private String fromAddress;
    private String toAddress;
    private String senderName;
    private String subject;
    private String content;
}
