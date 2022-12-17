package com.example.demosecucume.Web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@NoArgsConstructor
@Api(value = "hello", description = "Toutes les opérations concernant la table Région")
public class AuthController {

    @Autowired
    private JwtEncoder jwtEncoder;

    @Autowired
    private JwtDecoder jwtDecoder;


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;



    public AuthController(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder, AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }
    @ApiOperation(value = "Ajout de region en tenant compte du pays et de la population")
    @PostMapping("/token")
    public ResponseEntity<Map< String, String>> jwtToken(
            String grantype, String username, String password, boolean withRefreshToken, String refreshToken,
            HttpServletResponse response){

        String subjecte = null;
        String scope = null;



        if (grantype.equals("password")){
            System.out.println(username +"##########################" + password);
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
             subjecte=authentication.getName();
             scope =  authentication.getAuthorities()
                     .stream().map(auth -> auth.getAuthority())
                     .collect(Collectors.joining(""));

        }else if (grantype.equals("refreshToken")){
            if (refreshToken==null){
                return new ResponseEntity<>(Map.of("errorMessage", "Refresh Token is required"), HttpStatus.UNAUTHORIZED);
            }
            Jwt decodeJWT = null;
            try {
                decodeJWT = jwtDecoder.decode(refreshToken);
            } catch (JwtException e) {
                return new ResponseEntity<>(Map.of("errorMessage", e.getMessage()), HttpStatus.UNAUTHORIZED);
            }
            subjecte= decodeJWT.getSubject();
            UserDetails userDetails = userDetailsService.loadUserByUsername(subjecte);
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            scope=authorities.stream().map(auth->auth.getAuthority()).collect(Collectors.joining(" "));

        }


        Map<String, String> idToken = new HashMap<>();
        Instant instant = Instant.now();
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .subject(subjecte)
                .issuedAt(instant)
                .expiresAt(instant.plus(withRefreshToken?2:168, ChronoUnit.HOURS))
                .issuer("security-service")
                //pour renvoyer le role
                .claim("scope",scope)
                .build();
        String jwtAccessToken=jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
        idToken.put("accessToken",jwtAccessToken);

        // set access token in cookie
        Cookie accessCookie = new Cookie("access_token", jwtAccessToken);
        accessCookie.setMaxAge(7200); // 2 hours in seconds
        accessCookie.setHttpOnly(true);
        accessCookie.setValue(jwtAccessToken);
        response.addCookie(accessCookie);

        if (withRefreshToken){
            JwtClaimsSet jwtClaimsSetRefresh = JwtClaimsSet.builder()
                    .subject(subjecte)
                    .issuedAt(instant)
                    .expiresAt(instant.plus(168, ChronoUnit.HOURS))
                    .issuer("security-service")
                    .build();
            String jwtRefrechToken=jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSetRefresh)).getTokenValue();
            idToken.put("refreshToken", jwtRefrechToken);

            // set refresh token in cookie
            Cookie refreshCookie = new Cookie("refresh_token", refreshToken);
            refreshCookie.setMaxAge(604800); // 7 days in seconds
            refreshCookie.setHttpOnly(true);
           refreshCookie.setValue(jwtRefrechToken);
            response.addCookie(refreshCookie);

        }
        return new ResponseEntity<>(idToken,HttpStatus.OK);
    }





}
