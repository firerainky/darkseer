package com.zky.application.useDifferentImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerifyCodeController {

    @Autowired
    private VerifyCodeService verifyCodeService;

    @GetMapping("/sendVerifyCode/{phoneNumber}")
    public String sendVerifyCode(@PathVariable String phoneNumber) {
        return "Verify code: " + verifyCodeService.sendVerifyCode(phoneNumber);
    }

    @GetMapping("/verifyCode/{phoneNumber}/{code}")
    public String verifyCode(@PathVariable String phoneNumber, @PathVariable String code) {
        return "Your verify code is " + verifyCodeService.verifyCode(phoneNumber, code);
    }
}
