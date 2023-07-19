package com.qh.reigi.controller;


import com.qh.reigi.common.R;
import com.qh.reigi.entity.User;
import com.qh.reigi.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public R<String> login(HttpServletRequest request, @RequestBody User user) {
        return userService.login(request, user);
    }

    @PostMapping("/loginout")
    public R<String> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return R.success("已退出");
    }

}
