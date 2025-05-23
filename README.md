[# Weather API - Serviço de Meteorologia

Projeto backend para consulta de informações climáticas em tempo real utilizando dados da API pública OpenWeather.

## Integrantes
- Evandro Luiz  
- Abdiel Paulino

## Descrição

O serviço web permite consultar a previsão atual do tempo em qualquer cidade do mundo, além de registrar um histórico local de consultas feitas. Também possui rota para verificar a data e hora atual do servidor.

A API retorna dados meteorológicos completos, incluindo:

- Temperatura atual (°C)  
- Sensação térmica  
- Umidade (%)  
- Velocidade do vento (m/s)  
- Pressão atmosférica (hPa)  
- Temperatura mínima e máxima  
- Horário do nascer e pôr do sol  
- Descrição do clima  
- Nome da cidade  

Além disso, o sistema gerencia uma lista de cidades favoritas, com endpoints para adicionar, listar, atualizar e remover favoritos. O endpoint que lista favoritos retorna apenas o nome e ID das cidades, sem consultar dados climáticos.

## Tecnologias utilizadas

- Java 17  
- Spring Boot 3.2.5  
- Maven  
- OpenWeather API

## Como executar

1. Clone ou baixe o repositório.  
2. Navegue até a pasta do projeto.  
3. No arquivo `WeatherService.java`, configure sua chave da OpenWeather na variável `apiKey`.  
4. Execute o projeto com:

```bash
./mvnw spring-boot:run

ou

mvn spring-boot:run


Endpoints da API
Clima
GET /weather?city=NomeDaCidade
Consulta o clima atual da cidade.

POST /weather
Consulta o clima enviando JSON com { "city": "NomeDaCidade" }.

Histórico
GET /consultas
Lista todas as cidades consultadas até o momento.

Informações do projeto
GET /sobre
Retorna dados da equipe e nome do projeto.

Data e hora
GET /hora
Retorna data e hora atual do servidor.

Favoritos
POST /favorites
Adiciona uma cidade à lista de favoritos.
Exemplo JSON: { "cidade": "Curitiba" }

GET /favorites
Lista as cidades favoritas (ID e nome). Não traz dados do clima.

PUT /favorites/{id}
Atualiza o nome da cidade favorita pelo ID.

DELETE /favorites/{id}
Remove a cidade favorita pelo ID.

Observações
A chave da OpenWeather é necessária para funcionamento.

Consultas meteorológicas são feitas em tempo real via API pública.

Endpoint de favoritos retorna apenas lista simples para otimizar performance.

Licença
Projeto acadêmico com fins exclusivamente educacionais.](https://github.com/evandroluizrd/backend-projeto-ABDIEL-EVANDRO.git)
