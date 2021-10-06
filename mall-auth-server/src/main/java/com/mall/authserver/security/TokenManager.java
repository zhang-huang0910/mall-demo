package com.mall.authserver.security;

import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @auther zhz
 * @Date 2021-08-09 00:35
 */
@Component
public class TokenManager {

    //Token有效时长
    private static final long TOKEN_ECPIRATION = 24 * 60 * 60 * 1000;

    //编码的秘钥
    private final String TOKEN_SIGN_KEY = "123456";

    //根据用户名生成Token

    public String createToken(String userName) {
        String token = Jwts.builder()
                .setSubject(userName)
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_ECPIRATION))
                .signWith(SignatureAlgorithm.HS512, TOKEN_SIGN_KEY)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
        return token;
    }

    public String getUserInfoFromToken(String token) {
        String userInfo = Jwts.parser().setSigningKey(TOKEN_SIGN_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return userInfo;

    }

    //删除token
    public void removeToken(String token) {


    }


    //删除token
    public static void main(String[] args) {
        TokenManager tokenManager = new TokenManager();
        String zhagnsan = tokenManager.createToken("zhangsan");
        System.out.println(zhagnsan);
        String userInfoFromToken = tokenManager.getUserInfoFromToken(zhagnsan);
        System.out.println(userInfoFromToken);
    }

}
