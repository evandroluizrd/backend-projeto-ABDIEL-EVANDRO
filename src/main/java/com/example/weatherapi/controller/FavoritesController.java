package com.example.weatherapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/favorites")
public class FavoritesController {

    private final Map<Long, String> favoritos = new HashMap<>();
    private long idCounter = 1;

    @PostMapping
    public ResponseEntity<?> addFavorite(@RequestBody Map<String, String> body) {
        String city = body.get("cidade");
        if (city == null || city.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("erro", "Cidade não informada"));
        }
        if (favoritos.containsValue(city)) {
            return ResponseEntity.status(409).body(Map.of("erro", "Cidade já salva"));
        }
        favoritos.put(idCounter++, city);
        return ResponseEntity.status(201).body(Map.of("mensagem", "Cidade salva com sucesso"));
    }

    @GetMapping
    public ResponseEntity<?> listFavorites() {
        return ResponseEntity.ok(favoritos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFavorite(@PathVariable Long id, @RequestBody Map<String, String> body) {
        if (!favoritos.containsKey(id)) {
            return ResponseEntity.status(404).body(Map.of("erro", "ID não encontrado"));
        }
        String city = body.get("cidade");
        if (city == null || city.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("erro", "Cidade não informada"));
        }
        favoritos.put(id, city);
        return ResponseEntity.ok(Map.of("mensagem", "Cidade atualizada com sucesso"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFavorite(@PathVariable Long id) {
        if (!favoritos.containsKey(id)) {
            return ResponseEntity.status(404).body(Map.of("erro", "ID não encontrado"));
        }
        favoritos.remove(id);
        return ResponseEntity.ok(Map.of("mensagem", "Cidade removida com sucesso"));
    }
}
