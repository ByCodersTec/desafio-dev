package com.github.filipednb.financialtransactions.api.transaction;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.io.File;
import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TransactionResourceTest {

    @LocalServerPort
    private int port;

    @Autowired
    TransactionRepository repository;

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = this.port;

        final RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setContentType(ContentType.JSON);
        RestAssured.requestSpecification = builder.build();
    }

    @Test
    void shouldCreateTransactions() {

        File file = getFile();

        given()
                .contentType(ContentType.MULTIPART)
                .multiPart("file", file, "plain/text")
        .when()
                .post("/transactions")
        .then()
                .statusCode(HttpStatus.CREATED.value());

        Assertions.assertNotNull(repository.findAll());
    }

    @Test
    void shouldNotCreateTransactionsWhenNoFileIsGiven() {

        when()
                .post("/transactions")
        .then()
                .statusCode(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());

    }


    private File getFile() {
        final ClassLoader loader = getClass().getClassLoader();
        return new File(Objects.requireNonNull(loader.getResource("files/valid_CNAB.txt")).getFile());
    }
}