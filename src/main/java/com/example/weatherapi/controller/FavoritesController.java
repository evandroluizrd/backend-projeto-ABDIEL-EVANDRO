@RestController
@RequestMapping("/favorites")
public class FavoritesController {

    private final Map<Long, String> favoritos = new HashMap<>();
    private long idCounter = 1;

    // Remova ou comente a injeção do WeatherService e o método que busca clima

    // @Autowired
    // private WeatherService weatherService;

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
        return ResponseEntity.ok(favoritos);  // Retorna só o mapa de favoritos
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

    // Remova ou comente o método abaixo que traz clima para os favoritos
    // @GetMapping("/weather")
    // public ResponseEntity<?> weatherForFavorites() {
    //     List<Map<String, Object>> weatherList = new ArrayList<>();
    //     for (String city : favoritos.values()) {
    //         weatherList.add(weatherService.getWeather(city));
    //     }
    //     return ResponseEntity.ok(weatherList);
    // }
}
