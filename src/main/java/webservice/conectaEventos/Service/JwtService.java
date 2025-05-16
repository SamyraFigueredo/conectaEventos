package webservice.conectaEventos.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import webservice.conectaEventos.Model.Usuario;

import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expirationTime;

    // Removendo a constante estática, já que estamos injetando via @Value
    // private static final String SECRET_KEY = "MwUrJBae3z8SDmIOj46QIWO6YsjOCZuihjP4keojjczwiBJ0clf7W0ZD81BOW2uK";
    // private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1h

    public String generateToken(Usuario usuario) {
        return Jwts.builder()
                .setSubject(usuario.getEmailUsuario())
                .claim("id", usuario.getIdUsuario())
                .claim("nome", usuario.getNomeUsuario())
                .claim("tipo", usuario.getTipoUsuario().name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public boolean isTokenValid(String token) {
        try {
            return !extractClaims(token).getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
