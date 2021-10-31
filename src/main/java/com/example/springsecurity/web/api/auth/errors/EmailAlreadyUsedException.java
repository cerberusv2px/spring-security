package com.example.springsecurity.web.api.auth.errors;

import com.example.springsecurity.web.error.BadRequestAlertException;
import com.example.springsecurity.web.error.ErrorConstants;

public class EmailAlreadyUsedException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;

    public EmailAlreadyUsedException() {
        super(ErrorConstants.EMAIL_ALREADY_USED_TYPE, "Email is already in use!", "userManagement", "emailexists");
    }
}
