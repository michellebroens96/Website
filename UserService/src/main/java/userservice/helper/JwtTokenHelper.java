package userservice.helper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.UUID;

public class JwtTokenHelper {
    /**
     * Generates a JWT Token based on the provided subject.
     * @param subject - the subject of the token.
     * @return the generated JWT Token.
     */
    public String generateToken(String subject) {
        SignatureAlgorithm algorithm = SignatureAlgorithm.HS512;
        long currentMillis = System.currentTimeMillis();
        Date date = new Date(currentMillis);

        int timeout = 240000000;
        String issuer = "TBC.Authentication";
        JwtBuilder builder = Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(date)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(getSecretKey(), algorithm)
                .setExpiration(new Date(currentMillis + timeout));

        return builder.compact();
    }

    /**
     * Validates the JWT Token and returns the subject.
     * @param jwtToken - the JWT Token to be validated
     * @return the subject of the token or null.
     */
    public String validateToken(String jwtToken) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(getSecretKey())
                    .parseClaimsJws(jwtToken).getBody();
        }
        catch(Exception exception) {
            return null;
        }

        return claims.getSubject();
    }

    private SecretKeySpec getSecretKey() {
        SignatureAlgorithm algorithm = SignatureAlgorithm.HS512;
        String secret = "really really really really really really long and amazing secret token QWERTYUIOPOIUYTREWQWERTYUIOPOIUYTREWQWERTYUIOPOIUYTREWQWERTYUIOP";
        byte[] keyBytes = DatatypeConverter.parseBase64Binary(secret);
        return new SecretKeySpec(keyBytes, algorithm.getJcaName());
    }
}
