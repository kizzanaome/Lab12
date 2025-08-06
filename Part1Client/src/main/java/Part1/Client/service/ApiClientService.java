package Part1.Client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@Service
public class ApiClientService {

    @Autowired
    private RestTemplate restTemplate;

    public void callEndpointWithAuth(String url, String username, String password) {
        HttpHeaders headers = new HttpHeaders();
        String auth = username + ":" + password;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        headers.add("Authorization", "Basic " + encodedAuth);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    String.class
            );
            System.out.println(" User: " + username + " | URL: " + url +
                    " | Status: " + response.getStatusCode());
            System.out.println("Response: " + response.getBody());
        } catch (Exception e) {
            System.out.println(" User: " + username + " | URL: " + url +
                    " | Access Denied or Error: " + e.getMessage());
        }
    }
}
