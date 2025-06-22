package com.event_registration.lk.service.impl;

import com.event_registration.lk.dto.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class JwtServiceImpl {

//    private Key SECRET_KEY;
//
//    public JwtServiceImpl() {
//        try {
//            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
//            SecretKey secretKey = keyGenerator.generateKey();
//            String stingKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
//            byte[] decode = Decoders.BASE64.decode(stingKey);
//            SECRET_KEY = Keys.hmacShaKeyFor(decode);
//        }catch (Exception e){
//            log.warn("error in generating secret key");
//        }
//
//    }
//
//    public String generateJwtToken(User user) {
//        Map<String, Object> claims = new HashMap<>();
//        return Jwts.builder()
//                .claims()
//                .add(claims)
//                .subject(user.getUsername())
//                .issuedAt(new Date(System.currentTimeMillis()))
//                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
//                .and()
//                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getEncoded()))
//                .compact();
//    }

    private String SECRET;


    public JwtServiceImpl() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk = keyGenerator.generateKey();
            SECRET = Base64.getEncoder().encodeToString(sk.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    private Key getSecretKey() {
        byte[] keyBytes = Base64.getDecoder().decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateJwtToken(User user) {
        String subject = user.getUsername() != null ? user.getUsername() : user.getEmail();
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(user.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000)) // 1 hour
                .and()
                .signWith(getSecretKey())
                .compact();
    }

}
