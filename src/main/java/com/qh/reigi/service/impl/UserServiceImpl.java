package com.qh.reigi.service.impl;


import com.qh.reigi.common.R;
import com.qh.reigi.dao.mapper.UserMapper;
import com.qh.reigi.entity.User;
import com.qh.reigi.service.UserService;
import com.qh.reigi.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.qh.reigi.common.Constant.INITIAL_STATUS;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public R<String> login(HttpServletRequest request, User user) {
        User userCheck = userMapper.checkUserIsExist(user.getPhone());
        if(userCheck == null){
            User newUser = new User();
            newUser.setPhone(user.getPhone());
            newUser.setPassword(Utils.MD5Process(user.getPassword()));
            newUser.setStatus(INITIAL_STATUS);
            register(newUser);
            newUser.setId(userMapper.getUserId(newUser.getPhone()));
            request.getSession().setAttribute("user",newUser);
            return R.success("注册成功");
        }
        if(userCheck.getPassword().equals(Utils.MD5Process(user.getPassword()))){
            request.getSession().setAttribute("user",userCheck);
            userCheck.setId(userMapper.getUserId(userCheck.getPhone()));
            return R.success("登录成功");
        }
        return R.error("密码错误");

    }


    @Override
    public void register(User user) {
        userMapper.register(user);
    }

    @Override
    public User checkUserIsExist(String phone) {
        return userMapper.checkUserIsExist(phone);
    }
}
