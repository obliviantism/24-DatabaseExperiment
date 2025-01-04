package com.example.backend.controller.user.account;


import com.example.backend.controller.service.user.account.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/user/account/token/")
    public Map<String, String> getToken(@RequestParam Map<String, String> map) throws Exception {
        String username = map.get("username");
        String password = map.get("password");
        System.out.println(username + ' ' + password);
        return loginService.getToken(username, password);
    }


}
