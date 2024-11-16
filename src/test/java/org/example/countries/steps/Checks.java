package org.example.countries.steps;

import groovy.util.logging.Slf4j;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import java.util.List;

import static java.net.HttpURLConnection.HTTP_OK;

@Slf4j
public class Checks {

    @Step("Проверка ответа со списком стран")
    public List<String> checkIsNighboursGet(ValidatableResponse response) {
        List<List<String>> neighbours = response
                .assertThat()
                .statusCode(HTTP_OK)
                .extract()
                .path("borders");
        return neighbours.get(0);
    }

    @Step("Проверка списка гричных стран")
    public List<String> containsNeighbours(ValidatableResponse testResp) {
        List<List<String>> actualNeighbours = testResp
                .assertThat()
                .statusCode(HTTP_OK)
                .extract()
                .path("borders");
        return actualNeighbours.get(0);
    }
}
