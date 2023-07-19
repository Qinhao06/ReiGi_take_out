package com.qh.reigi.service;

import com.qh.reigi.common.R;
import com.qh.reigi.entity.User;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {
    R<String> login(HttpServletRequest request, User user);

    void register(User user);

    User checkUserIsExist(String phone);



}
