package com.example.demo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VaildatorUtil {
    private static final Pattern mobilePattern = Pattern.compile("1\\d{10}");

    public static boolean isMobile(String phone){
        Matcher matcher = mobilePattern.matcher(phone);
        return matcher.matches();
    }

    public static void main(String[] args) {
        System.out.println(VaildatorUtil.isMobile("13888888888"));
        System.out.println(VaildatorUtil.isMobile("1388888888"));
    }
}
