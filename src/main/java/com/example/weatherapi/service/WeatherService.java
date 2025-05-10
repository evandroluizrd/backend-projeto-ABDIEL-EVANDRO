package com.example.weatherapi.service;

import org.springframework.stereotype.Service;
import java.util.*;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private final String apiKey = "a654c340ca046ad911f2445fe2d7e4dc";

    public Map<String, Object> getWeather(String city) {
        try {
            String url = "https://api.openweathermap.org/data/2.5/weather?q=" +
                         city + "&appid=" + apiKey + "&units=metric&lang=pt_br";

            RestTemplate restTemplate = new RestTemplate();
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);

            Map<String, Object> result = new HashMap<>();
            result.put("cidade", response.get("name"));
            Map<String, Object> main = (Map<String, Object>) response.get("main");
            result.put("temperatura", main.get("temp"));
            List<Map<String, Object>> weatherList = (List<Map<String, Object>>) response.get("weather");
            result.put("descricao", weatherList.get(0).get("description"));
            return result;

        } catch (Exception e) {
            return Map.of("erro", "Erro ao buscar dados do tempo");
        }
    }
}