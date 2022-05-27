package br.com.application.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.application.model.user.User;
import br.com.application.service.UserService;

import javax.validation.constraints.NotNull;
import java.util.Date;


@Service
public class TokenService {
    @Autowired
    private UserService userService;

    @Value("${authentication.jwtToken.secret}")
    private String secret;
    @Value("${authentication.jwtToken.expiration}")
    private String expiration;

    public String generateAccessToken(User user, String origin, Integer empresaId ) {
        return Jwts.builder().setSubject(user.getEmail())
                .claim("empresaId", empresaId )
                .claim("userId", user.getId())
                .claim("client", origin)
                .claim("email", user.getEmail())
                .setExpiration(getExpirationDate())
                .setIssuedAt(org.joda.time.LocalDate.now().toDate()).signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String generateRefreshToken(User user, String origin) {
        return Jwts.builder().setSubject(user.getEmail())
                .claim("userId", user.getId())
                .claim("client", origin)
                .claim("email", user.getEmail())
                .setIssuedAt(org.joda.time.LocalDate.now().toDate()).signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    @NotNull
    private Date getExpirationDate() {
        Date now = new Date();
        return new Date(now.getTime() + 180000);
    }

    public Claims decodeToken(String token) {
        return Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
    }

    
    public String verifyRefresh(Integer userId, String client, String refreshToken, Integer empresaId ) {
        Claims refreshTokenDecode = this.decodeToken(refreshToken);
        if (this.validRefreshToken(userId, refreshTokenDecode, client)) {
            User user = userService.findByEmail(refreshTokenDecode.get("email").toString());
                return this.generateAccessToken(user, refreshTokenDecode.get("client").toString(), empresaId);

        }
        return null;
    }

    public String verifyRefresh(Integer userId, String client, String refreshToken) {
        Claims refreshTokenDecode = this.decodeToken(refreshToken);
        if (this.validRefreshToken(userId, refreshTokenDecode, client)) {
            User user = userService.findByEmail(refreshTokenDecode.get("email").toString());
                return generateAccessToken(user, refreshTokenDecode.get("client").toString(),user.getCompany().getId());

        }
        return null;
    }


    private boolean validRefreshToken(Integer userId, Claims refreshTokenDecode, String client) {
        if (userId == Integer.parseInt(refreshTokenDecode.get("userId").toString())) {
            return client.equals(refreshTokenDecode.get("client"));
        } else {
            return false;
        }
    }

    public String generateForgotPasswordToken(String email) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 300000);
        return Jwts.builder().setSubject(email)
                .claim("accountEmail", email)
                .claim("type", "FORGOT_PASSWORD")
                .setExpiration(expiration)
                .setIssuedAt(org.joda.time.LocalDate.now().toDate()).signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
