package com.linkhub.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${JWT_SECRET}")
    private String jwtSecret;

    private SecretKey chave;

    @PostConstruct
    public void init() {
        this.chave = Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    private final long EXPIRACAO_MS = 1000 * 60 * 60 * 8; // 8 horas

    // Gera o token contendo o username e a data de expiração
    public String gerarToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRACAO_MS))
                .signWith(chave)
                .compact();
    }

    // Extrai o username de dentro de um token válido
    public String extrairUsername(String token) {
        return Jwts.parser()
                .verifyWith(chave)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    // Verifica se o token é válido (assinatura correta e não expirado)
    public boolean tokenValido(String token) {
        try {
            Jwts.parser().verifyWith(chave).build().parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}