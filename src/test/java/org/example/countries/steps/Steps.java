package org.example.countries.steps;

import groovy.util.logging.Slf4j;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.example.countries.constants.Constants;

@Slf4j
public class Steps extends Client {

    @Step("Отправка запроса на получение списка стран по code: {countryCode}")
    public ValidatableResponse sendGetNeighbours(String countryCode) {
        return spec()
                .queryParam("codes", countryCode)
                .when()
                .get(Constants.ALPHA_API)
                .then();
    }
}
