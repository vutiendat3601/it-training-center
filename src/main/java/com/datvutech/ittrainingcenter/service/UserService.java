
package com.datvutech.ittrainingcenter.service;

import com.datvutech.ittrainingcenter.persistence.entity.User;

public interface UserService {
    User register(User userReq);

    boolean sendConfirmationMail(User userReq);

}
