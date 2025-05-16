# Weather API - Serviço de Meteorologia

Projeto de backend desenvolvido para consulta de informações climáticas em tempo real usando dados da API pública OpenWeather.

## 👥 Integrantes
- Evandro Luiz
- Abdiel Paulino

## 🎯 Descrição

O serviço web permite consultar a previsão atual do tempo em qualquer cidade do mundo, além de registrar um histórico local de consultas feitas. O sistema também possui uma rota para verificar a data e hora atual do servidor (em tempo real).

A API retorna dados meteorológicos completos, como:

- Temperatura atual (°C)
- Sensação térmica
- Umidade (%)
- Velocidade do vento (m/s)
- Pressão atmosférica (hPa)
- Temperatura mínima e máxima
- Horário do nascer e pôr do sol
- Descrição do clima
- Nome da cidade

## 💻 Tecnologias utilizadas

- Java 17
- Spring Boot 3.2.5
- Maven
- OpenWeather API

## 🚀 Como executar o projeto

1. Baixe ou clone o repositório
2. Navegue até a pasta do projeto
3. No arquivo `WeatherService.java`, atualize o valor da variável `apiKey` com a sua chave da OpenWeather
4. Rode o projeto:

```bash
./mvnw spring-boot:run
```

ou

```bash
mvn spring-boot:run
```

O projeto estará disponível em: [http://localhost:8000](http://localhost:8000)

## 📡 Endpoints da API

### `GET /weather?city=NomeDaCidade`

Consulta o clima atual da cidade informada.

**Exemplo:**
```
GET http://localhost:8000/weather?city=Sao Paulo
```

**Resposta exemplo:**
```json
{
  "cidade": "São Paulo",
  "temperatura": "23.4 °C",
  "descricao": "céu limpo",
  "umidade": "64 %",
  "velocidade_vento": "3.5 m/s",
  "sensacao_termica": "22.8 °C",
  "temp_min": "20.0 °C",
  "temp_max": "26.0 °C",
  "pressao": "1012 hPa",
  "nascer_do_sol": "06:13",
  "por_do_sol": "17:47"
}
```

---

### `POST /weather`

Consulta o clima da cidade informada via corpo da requisição.

**Corpo JSON:**
```json
{
  "city": "Rio de Janeiro"
}
```

---

### `GET /consultas`

Retorna a lista de todas as cidades que já foram consultadas.

---

### `GET /sobre`

Retorna informações da equipe e do projeto.

**Resposta exemplo:**
```json
{
  "integrantes": ["Evandro Luiz", "Abdiel Paulino"],
  "nome_projeto": "Serviço de Meteorologia"
}
```

---

### `GET /hora`

Retorna a data e hora atual do servidor.

**Resposta exemplo:**
```json
{
  "data_hora": "09/05/2025 21:30:15"
}
```

---

## 📋 Observações

- A chave da OpenWeather é obrigatória para funcionamento do projeto.
- As consultas são feitas em tempo real diretamente da API pública.
- Todas as unidades de medida foram formatadas no retorno para facilitar a leitura (°C, %, m/s, etc.).

## 📚 Licença

Projeto acadêmico para fins educacionais.
