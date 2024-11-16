package org.example.countries.steps;

import groovy.util.logging.Slf4j;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.example.countries.constants.Constants;

import static io.restassured.RestAssured.given;

@Slf4j
public class Client {

    public RequestSpecification spec() {
            return given()
                    .filter(new AllureRestAssured())
                    .contentType(ContentType.JSON)
                    .baseUri(Constants.BASE_URI)
                    .basePath(Constants.BASE_API);
        }
    }
