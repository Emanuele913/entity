package response.entity.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import response.entity.model.AccountDipendente;
import response.entity.model.Token;
import response.entity.model.authentication.AuthenticationRequest;
import response.entity.model.authentication.AuthenticationResponse;
import response.entity.security.JwtUtil;
import response.entity.security.MyUserDetailsService;
import response.entity.security.SecurityConfigurer;
import response.entity.service.AccountDipendenteService;
import response.entity.service.TokenService;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsService userDetailsService;
    private final AccountDipendenteService accountDipendenteService;
    private final TokenService tokenService;
    private final JwtUtil jwtUtil;

    @Autowired
    private AuthenticationController(AuthenticationManager authenticationManager, MyUserDetailsService myUserDetailsService,
                                     JwtUtil jwtUtil, AccountDipendenteService accountDipendenteService, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = myUserDetailsService;
        this.jwtUtil = jwtUtil;
        this.accountDipendenteService = accountDipendenteService;
        this.tokenService = tokenService;

    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            log.error("Credenziali errate");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        AccountDipendente accountDipendente = accountDipendenteService.findByUserName(authenticationRequest.getUsername());
        String token = tokenService.getAccessToken(accountDipendente);
        log.info("AccessToken : {}",token);
        if (jwtUtil.isTokenExpired(token)) {
            token = tokenService.getRefreshToken(accountDipendente);
            log.warn("AccessToken scaduto... utilizzo RefreshToken :{}", token);
        }

        return ResponseEntity.ok(new AuthenticationResponse(token));
    }
}
