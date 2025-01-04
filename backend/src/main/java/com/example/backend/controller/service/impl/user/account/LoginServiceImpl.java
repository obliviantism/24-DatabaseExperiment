package com.example.backend.controller.service.impl.user.account;

import com.example.backend.controller.pojo.User;
import com.example.backend.controller.service.impl.utils.UserDetailsImpl;
import com.example.backend.controller.service.user.account.LoginService;
import com.example.backend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
   @Autowired
   private AuthenticationManager authenticationManager;

    @Override
    public Map<String, String> getToken(String username, String password) throws Exception {
        Map<String, String> map = new HashMap<>();// 存放返回的结果

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);

        try {
            Authentication authenticate = authenticationManager.authenticate(authenticationToken);// 登录失败自动处理

            UserDetailsImpl loginUser = (UserDetailsImpl) authenticate.getPrincipal();

            User user = loginUser.getUser();

            String jwt = JwtUtil.createJWT(user.getId().toString());

            map.put("error_massage", "success");

            map.put("token", jwt);

            return map;
        } catch (Exception e) {
            map.put("error_massage", "账号不存在，请先注册账号");
            return map;
        }

    }
}

