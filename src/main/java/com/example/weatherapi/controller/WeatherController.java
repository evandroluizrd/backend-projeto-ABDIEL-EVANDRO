package com.example.weatherapi.controller;

import com.example.weatherapi.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    private List<String> cidadesConsultadas = new ArrayList<>();

    @GetMapping("/weather")
    public Map<String, Object> getWeather(@RequestParam String city) {
        cidadesConsultadas.add(city);
        return weatherService.getWeather(city);
    }

    @PostMapping("/weather")
    public Map<String, Object> postWeather(@RequestBody Map<String, String> body) {
        String city = body.get("city");
        cidadesConsultadas.add(city);
        return weatherService.getWeather(city);
    }

    @GetMapping("/consultas")
    public List<String> getConsultas() {
        return cidadesConsultadas;
    }

    @GetMapping("/sobre")
    public Map<String, Object> sobre() {
        return Map.of(
            "integrantes", List.of("Evandro Luiz", "Abdiel Paulino"),
            "nome_projeto", "Serviço de Meteorologia"
        );
    }


    @GetMapping("/hora")
    public Map<String, String> getHoraAtual() {
        return Map.of("horaAtual", java.time.LocalDateTime.now().toString());
    }

}