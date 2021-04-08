package response.entity.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    //questa è la nostra secret key che viene utilizzata per generare i token, solitamente non andrebbe inserita
    //nel codice ma bensì dovrebbe essere presa in modo sicuro esternamente
    private static final String SECRET_KEY = "secret";


    //prende il token e ritorna un username
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    //prende il token e ritorna l'expiration date , entrambi i metodi utilizzano extractClaims per prendere le info
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    //questo metodo prende un token ed estrae i suoi claims attraverso il claimsResolver
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    //metodo che prende il token in input e controlla se quel token è finito quindi lo paragona ad un nuova data
    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    //Genera un token in base ai dati dell'user
    public String generateAccessToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createAccessToken(claims, userDetails.getUsername());
    }

    public String generateRefreshToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createRefreshToken(claims, userDetails.getUsername());
    }

    //metodo utilizzato ^sopra , utilizzando un pattern builder cambia il "claim" e setta un soggetto da analizzare
    //setta la data corrente e quando deve finire(setExpiration) infine si sottoscrive il token con un algoritmo
    //ed una secret key ed infine si chiude il pattern builder con .compact
    private String createAccessToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    private String createRefreshToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    //questo metodo controlla il token, quindi fa un check del username passato e dello username del token e inoltre
    //controlla che il token non sia finito
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


}



