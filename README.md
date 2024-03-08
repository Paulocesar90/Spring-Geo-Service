# Spring GeoService Project

Bem-vindo ao meu projeto Spring GeoService! 🌍

## Descrição
Este projeto Spring GeoService permite obter coordenadas geográficas e informações de endereço através da integração com a API de Geocodificação do Google Maps. Além disso, utiliza um banco de dados H2 para armazenar consultas realizadas.

## Requisitos
- Java 11
- Maven

## Como Executar
1. Clone o repositório:
    ```bash
    git clone https://github.com/seu-usuario/spring-geoservice.git
    ```

2. Navegue até a pasta do projeto:
    ```bash
    cd spring-geoservice
    ```

3. Execute o aplicativo Spring Boot com Maven:
    ```bash
    mvn spring-boot:run
    ```

4. Acesse o Swagger para testar as requisições:
    [Swagger UI](http://localhost:8080/swagger-ui/index.html)

5. Acesse o console H2 para visualizar o banco de dados:
    [Console H2](http://localhost:8080/h2-console)
    - **JDBC URL:** jdbc:h2:mem:testdb
    - **Username:** sa
    - **Password:** password

## Endpoints

### Obter Coordenadas Geográficas
- **URL:** /coordinates
- **Método:** GET
- **Parâmetros:**
  - latitude (Double): Latitude da localização desejada
  - longitude (Double): Longitude da localização desejada
- **Exemplo de Requisição:**
  ```bash
  curl -X GET "http://localhost:8080/coordinates?latitude=-19.912998&longitude=-43.940933" -H "accept: */*"

### Obter Informações de Endereço

Endpoint para obter informações de endereço com base no endereço fornecido.

- **URL:** `/address`
- **Método:** GET
- **Parâmetros:**
  - `address` (String): Av. do Contorno, 58 - Centro, Belo Horizonte - MG Brazil

#### Exemplo de Requisição

Utilize o comando `curl` ou uma ferramenta similar para fazer a requisição:

```bash
curl -X GET "http://localhost:8080/address?address=Av.%20do%20Contorno,%2058%20-%20Centro,%20Belo%20Horizonte%20-%20MG%20Brazil" -H "accept: */*"
```
#### Acesse o Console H2

Para visualizar o banco de dados H2 utilizado neste projeto, siga as instruções abaixo:

1. Acesse o [Console H2](http://localhost:8080/h2-console).
2. Preencha os campos da página de login com as seguintes informações:
    - **JDBC URL:** `jdbc:h2:mem:testdb`
    - **Username:** `sa`
    - **Password:** `password`
