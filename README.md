# desafio-dev

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=leonardoscalabrini_desafio-dev&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=leonardoscalabrini_desafio-dev)
[<img src="https://img.shields.io/badge/dockerhub-imagens-orange.svg?logo=DOCKER">][dockerhub]

[dockerhub]: https://hub.docker.com/r/leonardoscalabrini/desafio-dev

## Dev quick start ##

1. Install dependencies
````
mvn clean install
````

2. Install Infraestructure
````
docker-compose up db
````

3. Start locally
````
mvn spring-boot:run
````

## Build and run image ##

1. Install Infraestructure and Image
````
docker-compose up
````

# API Documentation #

POST http://localhost:8080/api/v1/upload/cnab
```JSON
Content-Disposition: form-data; filename="example.txt"
```

GET http://localhost:8080/api/v1/store
```JSON
[
  {
    "storeId": "9cb36ec1-e16d-487c-a1b8-fe59ba72f6d0",
    "storeName": "BAR DO JOÃO",
    "ownerName": "JOÃO MACEDO",
    "storeBalance": -102.0
  }
]
```

GET http://localhost:8080/api/v1/transaction?storeId=9cb36ec1-e16d-487c-a1b8-fe59ba72f6d0

```JSON
[
    {
    "transactionId": "9633630e-c320-4d1b-a916-382398eca093",
    "storeId": "77ad1329-5d36-402d-b0c2-be8aa9dbbef7",
    "type": "FINANCIAMENTO",
    "date": "2019-03-01T12:34:53",
    "transactionValue": 142.0,
    "cpfNumber": "09620676017",
    "creditCardNumber": "4753****3153",
    "storeName": "BAR DO JOÃO",
    "ownerName": "JOÃO MACEDO",
    "storeBalance": -306.0
    }
]
```