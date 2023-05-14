package com.datvutech.ittrainingcenter.service.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.datvutech.ittrainingcenter.exception.ExceptionMessages;
import com.datvutech.ittrainingcenter.persistence.entity.User;
import com.datvutech.ittrainingcenter.persistence.repository.UserRepository;
import com.datvutech.ittrainingcenter.persistence.type.UserRole;
import com.datvutech.ittrainingcenter.service.MailService;
import com.datvutech.ittrainingcenter.service.UserService;
import com.datvutech.ittrainingcenter.service.model.MailDetail;

import net.bytebuddy.utility.RandomString;

@PropertySource("classpath:configs.properties")
@Service
public class UserServiceImpl implements UserService {
    @Value("${app.user.verification_code_length}")
    private int verificationCodeLength;

    @Value("${app.name}")
    private String appName;

    @Value("${app.website}")
    private String appWebsite;
    @Value("${app.mail.server.email}")
    private String serverEmail;

    private UserRepository userRepo;

    private MailService mailService;
    private ExceptionMessages exMessages;

    public UserServiceImpl(UserRepository userRepo, MailService mailService,
            ExceptionMessages exMessages) {
        this.userRepo = userRepo;
        this.mailService = mailService;
        this.exMessages = exMessages;

    }

    @Override
    public User registerLearner(User userReq) {
        Optional<User> exsitence = userRepo.findByEmail(userReq.getEmail());
        if (exsitence.isPresent()) {
            throw new RuntimeException(exMessages.getMessage("EMAIL_WAS_TAKEN"));
        }
        userReq.setUserUuid(UUID.randomUUID().toString());
        userReq.setRole(UserRole.LEARNER);
        userReq.setEmailVerificationCode(RandomString.make(verificationCodeLength));
        User userResp = userRepo.save(userReq);
        sendConfirmationMail(userResp);
        return userResp;
    }

    @Override
    public boolean sendConfirmationMail(User userReq) {
        StringBuilder contentBuilder = new StringBuilder("");
        try {
            Reader contentTemplate = new FileReader(
                    ResourceUtils.getFile("classpath:templates/confirmation-mail.html"));
            BufferedReader br = new BufferedReader(contentTemplate);
            String line = "";
            while ((line = br.readLine()) != null) {
                contentBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String confirmLink = appWebsite + "/verify?code=" + userReq.getEmailVerificationCode();
        String newConfirmLink = appWebsite + "/re-verify?uuid=" + userReq.getUserUuid();
        String content = contentBuilder.toString();
        content = content.replace("${app-name}", appName);
        content = content.replace("${app-website}", appWebsite);
        content = content.replace("${confirm-link}", confirmLink);
        content = content.replace("${new-confirm-link}", newConfirmLink);
        MailDetail mailDetail = new MailDetail(serverEmail, userReq.getEmail(), appName, "Xác thực địa chỉ email!",
                content);
        mailService.sendHtmlContentMail(mailDetail);
        return true;
    }

}
