package com.datax.plus.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.datax.plus.model.User;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;
import java.util.Map;

public class JwtUtil {
    public static String signJwt(User user, Map<String, String> payLoad){
        Date currentDate = new Date();
        Date currentDate1HourLater = DateUtils.addHours(currentDate, 1);

        String token = JWT.create().withAudience(user.getUsername()).
                withExpiresAt(currentDate1HourLater).
                withIssuedAt(currentDate).withPayload(payLoad).
                sign(Algorithm.HMAC256(user.getPassword()+"testkey..."));
        return token;
        /*
        token := jwt.New(jwt.SigningMethodHS256)
	claims := make(jwt.MapClaims)
	claims["exp"] = time.Now().Add(time.Hour * time.Duration(1)).Unix()
	claims["iat"] = time.Now().Unix()
	claims["sub"] = subject

	fmt.Println(claims["exp"])
	fmt.Println(claims["iat"])
	token.Claims = claims

	tokenString, _ := token.SignedString([]byte("testkey"))
	fmt.Println(tokenString)
	return tokenString
         */
    }
}
