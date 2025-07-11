package com.zky.application.useDifferentImpl;

import org.springframework.stereotype.Component;

@Component
public class VerifyCodeServiceImpl implements VerifyCodeService {

    @Override
    public String sendVerifyCode(String phoneNumber) {
        return "123456";
    }

    @Override
    public boolean verifyCode(String phoneNumber, String code) {
        return true;
    }
}
