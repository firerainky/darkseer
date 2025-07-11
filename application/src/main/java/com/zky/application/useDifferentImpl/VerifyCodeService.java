package com.zky.application.useDifferentImpl;

public interface VerifyCodeService {

    String sendVerifyCode(String phoneNumber);
    boolean verifyCode(String phoneNumber, String code);
}
