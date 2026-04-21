package com.sonar.order2;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.HashMap;
import java.util.Map;

@Service
public class SplunkService {

    private static final String URL = "http://localhost:8088/services/collector";
    private static final String TOKEN = "0cb2fc0f-2e88-41fd-850a-8528d1dedede";

    public void sendLog(String message) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Splunk " + TOKEN);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = new HashMap<>();
        body.put("index", "order_index_dev");
        body.put("event", message);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        restTemplate.postForEntity(URL, request, String.class);
    }
}