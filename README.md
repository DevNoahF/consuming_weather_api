# Weather API Consuming

Uma aplicação Spring Boot que consome dados meteorológicos em tempo real através de integração com APIs de clima.

## Requisitos

- Docker & Docker Compose (para rodar a aplicação em containers)
- API KEY do site: https://www.visualcrossing.com/weather-api/

## Como Rodar com Docker

### 1. Clone o repositório

```bash
git clone https://github.com/seu-usuario/WeatherApiConsuming.git
cd WeatherApiConsuming
```

### 2. Configure a chave da API

Copie o arquivo `.env.example` para `.env`:

```bash
cp .env.example .env
```

Edite o arquivo `.env` e adicione sua chave de API:

```env
API_KEY=sua_chave_aqui_do_visualcrossing
```

### 3. Obtenha a Chave da API

1. Acesse: https://www.visualcrossing.com/weather-api/
2. Registre-se e obtenha sua chave de API
3. Adicione a chave no arquivo `.env`

### 4. Execute a aplicação com Docker Compose

```bash
docker-compose up -d
```

A aplicação estará disponível em:
```
http://localhost:8080
```

### 5. Acesse a documentação da API (Swagger)

```
http://localhost:8080/swagger-ui.html
```

## Comandos Úteis

Visualizar logs:
```bash
docker-compose logs -f app
```

Parar a aplicação:
```bash
docker-compose down
```

Recompilar e executar:
```bash
docker-compose up --build
```

## Endpoints Principais

- `GET /swagger-ui.html` - Documentação interativa da API
- `GET /actuator/health` - Health check da aplicação

## Tecnologias

- Java 25
- Spring Boot 4.0.2
- Spring Web MVC
- Lombok
- Jackson
- SpringDoc OpenAPI (Swagger)
- Docker & Docker Compose```bash
docker build -t weather-api-consuming .
docker run -p 8080:8080 weather-api-consuming
```

## Endpoint Principal

**POST** `/weather`

```json
{
  "city": "São Paulo",
  "state": "SP",
  "country": "BR",
  "initialDate": "2026-02-01",
  "finalDate": "2026-02-14"
}
```



### Build with 💙 by Noah Franco :)
