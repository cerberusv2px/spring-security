package com.example.springsecurity.web.api.auth.errors;

import com.example.springsecurity.web.error.BadRequestAlertException;
import com.example.springsecurity.web.error.ErrorConstants;

public class LoginAlreadyUsedException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;

    public LoginAlreadyUsedException() {
        super(ErrorConstants.LOGIN_ALREADY_USED_TYPE, "Login name already used!", "userManagement", "userexists");
    }
}
