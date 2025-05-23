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

            Map<String, Object> main = (Map<String, Object>) response.get("main");
            Map<String, Object> wind = (Map<String, Object>) response.get("wind");
            Map<String, Object> sys = (Map<String, Object>) response.get("sys");
            List<Map<String, Object>> weatherList = (List<Map<String, Object>>) response.get("weather");

            Map<String, Object> result = new HashMap<>();
            result.put("cidade", response.get("name"));
            result.put("temperatura", main.get("temp") + " °C");
            result.put("descricao", weatherList.get(0).get("description"));
            result.put("umidade", main.get("humidity") + " %");
            result.put("velocidade_vento", wind.get("speed") + " m/s");
            result.put("sensacao_termica", main.get("feels_like") + " °C");
            result.put("temp_min", main.get("temp_min") + " °C");
            result.put("temp_max", main.get("temp_max") + " °C");
            result.put("pressao", main.get("pressure") + " hPa");

            long nascer = ((Number) sys.get("sunrise")).longValue();
            long por = ((Number) sys.get("sunset")).longValue();
            long timezoneOffset = ((Number) response.get("timezone")).longValue();

            Date nascerDate = new Date((nascer + timezoneOffset) * 1000);
            Date porDate = new Date((por + timezoneOffset) * 1000);

            result.put("nascer_do_sol", new java.text.SimpleDateFormat("HH:mm").format(nascerDate));
            result.put("por_do_sol", new java.text.SimpleDateFormat("HH:mm").format(porDate));

            return result;

        } catch (Exception e) {
            return Map.of("erro", "Erro ao buscar dados do tempo");
        }
    }
}
