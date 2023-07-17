package com.qh.reigi.utils;

import org.springframework.util.DigestUtils;

public class Utils {
    public static  String MD5Process(String str) {

        return DigestUtils.md5DigestAsHex(str.getBytes());
    }


}
