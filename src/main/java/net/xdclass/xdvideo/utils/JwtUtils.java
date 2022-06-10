package net.xdclass.xdvideo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.xdclass.xdvideo.config.JwtConfig;
import net.xdclass.xdvideo.model.Do.AdminsDo;

import java.util.Date;

/**
 * JWT 工具类
 */
public class JwtUtils {
    /**
     * 创建token
     * @param user
     * @return
     */
    public static String createJwtToken(AdminsDo user) {
        String SUBJECT = JwtConfig.subjectConfig;
        long EXPIRE = 1000 * 60 * 60 * 24 * JwtConfig.expiresConfig; // 24小时过期时间
        String APPSECRET = JwtConfig.appSecretConfig;
        if (user == null || user.getId() == null || user.getName() == null) {
            return null;
        }

        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("id", user.getId())
                .claim("name", user.getName())
                .claim("email", user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256, APPSECRET)
                .compact();
        return token;
    }

    /**
     * 解析token
     * @param token
     * @return
     */
    public static Claims parseToken(String token) {
        String APPSECRET = JwtConfig.appSecretConfig;
        try {
            final Claims claim = Jwts.parser().setSigningKey(APPSECRET)
                    .parseClaimsJws(token)
                    .getBody();
            return claim;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 解析token
     * @param token
     * @return
     */
    public static String getUsername(String token) {
        String APPSECRET = JwtConfig.appSecretConfig;
        try {
            final Claims claim = Jwts.parser().setSigningKey(APPSECRET)
                    .parseClaimsJws(token).getBody();
            return claim.get("name").toString();
        } catch (Exception e) {
            return null;
        }
    }


    public static boolean verifyToken(String token) {
        String APPSECRET = JwtConfig.appSecretConfig;
        try {
            Jwts.parser().setSigningKey(APPSECRET).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
