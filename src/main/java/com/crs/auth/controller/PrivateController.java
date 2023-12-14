package com.crs.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrivateController {

    @GetMapping("/account")
    public String getAccounts() {
        return "Account 1";
    }
}
