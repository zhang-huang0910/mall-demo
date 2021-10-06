package com.mall.authserver.security;

import com.mall.common.utils.MD5;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @auther zhz
 * @Date 2021-08-08 18:19
 */
@Component
public class DefaultPasswordEncoder implements PasswordEncoder {

    public DefaultPasswordEncoder() {
        this(-1);
    }

    public DefaultPasswordEncoder(int strLength) {
    }


    @Override
    public String encode(CharSequence rawPassword) {

        return MD5.encrypt(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {

        return encodedPassword.equals(MD5.encrypt(rawPassword.toString()));
    }
}
