package com.mall.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @auther zhz
 * @Date 2021-08-08 18:40
 */
@Slf4j
public final class MD5 {
    public static String encrypt(String strSrc) {
        try {
            char[] hexChars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            byte[] bytes = strSrc.getBytes();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(bytes);
            bytes = md.digest();
            int j = bytes.length;
            char[] chars = new char[j * 2];
            int k = 0;
            for (int i = 0; i < bytes.length; i++) {
                byte b = bytes[i];
                chars[k++] = hexChars[b >>> 4 & 0xf];
                chars[k++] = hexChars[b & 0xf];
            }
            return new String(chars);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("MD5加密出错！");
        }
    }

    public static void main(String[] args) {
        String encrypt = encrypt("123");
        System.out.println(encrypt);
        String encrypt1 = encrypt("abdc123123214124dfewfasgsagaghzhhtjtjedewfewag");
        System.out.println(encrypt1);
        System.out.println(0xf);
    }
}
