# Weather API Consuming

Uma aplicação Spring Boot que consome dados meteorológicos em tempo real através de integração com APIs de clima. Utilizando REDIS para cache.

# Esse projeto é para ser feito deploy no reder, sem a implementação do redis.

## Requisitos

- Docker (para rodar a aplicação)
- API KEY do site: https://www.visualcrossing.com/weather-api/

## Como Rodar

### 1. Clone o repositório

```bash
git clone https://github.com/seu-usuario/WeatherApiConsuming.git
cd WeatherApiConsuming
```

### 2. Configure a chave da API

## Obtendo a Chave da API

1. Acesse : https://www.visualcrossing.com/weather-api/
2. Registre-se e obtenha sua chave de API
3. Adicione a chave no arquivo `application.yaml`


Adicione sua chave de API no arquivo `application.yaml`:

```yaml
# Adicionar sua chave da API Weather
weather:
  api:
    key: sua_chave_aqui
```


### 4. Acesse a documentação da API

Abra em seu navegador:

```
http://localhost:8080/swagger-ui.html
```


## Usando Docker

```bash
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
