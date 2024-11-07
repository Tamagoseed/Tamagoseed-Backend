package com.tamagoseed.security;

import com.tamagoseed.model.Cliente;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.security.Key;

@Component
public class JwtTokenProvider {

    private String jwtSecret = "sua_chave_secreta"; // Troque por uma chave secreta mais segura
    private long jwtExpirationMs = 86400000; // 24 horas de validade

    // Método para gerar o token JWT
    public String generateToken(Cliente cliente) {
        Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes()); // Gerar chave de assinatura para maior segurança
        return Jwts.builder()
                .setSubject(cliente.getEmail()) // Usando o email como "subject" do token
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(key, SignatureAlgorithm.HS512) // Assinando o token com a chave secreta
                .compact();
    }

    // Método para validar o token JWT
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder() // Usando parserBuilder em vez de parser() para compatibilidade com versões mais novas
                    .setSigningKey(jwtSecret.getBytes())
                    .build()
                    .parseClaimsJws(token);
            return true; // Token válido
        } catch (Exception e) {
            // Melhor captura de exceções específicas para depuração
            System.out.println("Token inválido: " + e.getMessage());
            return false;
        }
    }

    // Método para extrair o email do token JWT
    public String getEmailFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtSecret.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
