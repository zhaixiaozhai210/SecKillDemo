package com.example.demo.utils;

import org.apache.commons.codec.cli.Digest;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * MD5 加密
 */
public class MD5Util {
    /**
     * 固定salt
     */
    private static final String salt="1a2b3c4d";

    /**
     * md5加密方法
     *
     * @param src
     * @return
     */
    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    /**
     * 输入密码转换为Form密码
     *
     * @param inputPass
     * @return
     */
    public static String inputPassToFormPass(String inputPass) {
        String str=""+salt.charAt(0)+salt.charAt(2)+inputPass+salt.charAt(5)+salt.charAt(4);
        System.out.println(md5(str));
        return md5(str); 			//char类型计算会自动转换为int类型
    }

    /**
     * 将Form密码转换为数据库密码
     *
     * @param formPass
     * @param salt
     * @return
     */
    public static String formPassToDBPass(String formPass,String salt) {//随机的salt
        String str=""+salt.charAt(0)+salt.charAt(2)+formPass+salt.charAt(5)+salt.charAt(4);
        return md5(str);
    }

    /**
     * @param input
     * @param saltDB
     * @return
     */
    public static String inputPassToDbPass(String input,String saltDB) {
        String formPass=inputPassToFormPass(input);
        System.out.println(formPass);
        String dbPass=formPassToDBPass(formPass,saltDB);
        return dbPass;
    }

    public static void main(String[] args) {
        String a = inputPassToFormPass("111111");
        System.out.println(a);
//        System.out.println("local salt:"+salt);
//        String str1 = ""+salt.charAt(0)+salt.charAt(2)+"123456"+salt.charAt(5)+salt.charAt(4);
//        System.out.println("第一次加密前str："+str1);
//        String str2 = DigestUtils.md5Hex(str1);
//        System.out.println("第一次加密后str："+str2);
//
//        String DbSalt = "9d5b364d";
//        String str3 = ""+DbSalt.charAt(0)+DbSalt.charAt(2)+str2+DbSalt.charAt(5)+DbSalt.charAt(4);
//        System.out.println("第二次加密前str："+str3);
//        String str4 = DigestUtils.md5Hex(str3);
//        System.out.println("第二次加密后str："+str4);
     }
}
