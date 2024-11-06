package net.bcsoft.bcosft.controller;

import net.bcsoft.bcosft.utils.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/oauth")
public class GoogleOAuthController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private static final String TOKEN_URL = "https://oauth2.googleapis.com/token";
    private static final String CLIENT_ID = "138654465599-2ii8lam4iqp1mdj0je7sq0kdtcn11680.apps.googleusercontent.com";
    private static final String CLIENT_SECRET = "";
    private static final String REDIRECT_URI = "";

    @PostMapping("/oAuthGoogle/exchange-code/")
    public ResponseEntity<String> exchangeCode(@RequestParam("code") String code) {

        RestTemplate restTemplate = new RestTemplate();

        System.out.println("Authorization Code: " + code + " TERMINA");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", code);
        params.add("client_id", CLIENT_ID);
        params.add("client_secret", CLIENT_SECRET);
        params.add("redirect_uri", REDIRECT_URI);
        params.add("grant_type", "authorization_code");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(TOKEN_URL, HttpMethod.POST, request, String.class);
            return ResponseEntity.ok(response.getBody());
        } catch (HttpClientErrorException e) {
            System.out.println("Errore: " + e.getStatusCode());
            System.out.println("Corpo della risposta: " + e.getResponseBodyAsString());
        }

        return ResponseEntity.badRequest().build();
    }

}
