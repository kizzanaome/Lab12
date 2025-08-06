package Part5.Part3Client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@Service
public class JwtApiClientService {

        @Autowired
        private RestTemplate restTemplate;

        private String token;

        //  Sign in and get JWT token
        public void signIn(String baseUrl, String username, String password) {
            String loginUrl = baseUrl + "/auth/signin";

            Map<String, String> requestBody = Map.of(
                    "username", username,
                    "password", password
            );

            ResponseEntity<Map> response = restTemplate.postForEntity(loginUrl, requestBody, Map.class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                token = (String) response.getBody().get("token");
                System.out.println(" Logged in successfully. Token: " + token);
            } else {
                System.out.println("Login failed for " + username);
            }
        }

        //  Call a protected endpoint with JWT
        public void callProtectedEndpoint(String url) {
            if (token == null) {
                System.out.println(" No token available. Please login first.");
                return;
            }

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + token);

            HttpEntity<String> entity = new HttpEntity<>(headers);

            try {
                ResponseEntity<String> response = restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        entity,
                        String.class
                );
                System.out.println(" Response from " + url + ": " + response.getBody());
            } catch (Exception e) {
                System.out.println("Error accessing " + url + ": " + e.getMessage());
            }
        }
    }
