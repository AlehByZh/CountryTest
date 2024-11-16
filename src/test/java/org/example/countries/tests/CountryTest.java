package org.example.countries.tests;

import io.restassured.response.ValidatableResponse;
import org.example.countries.constants.Constants;
import org.example.countries.steps.Checks;
import org.example.countries.steps.Steps;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CountryTest {

    private static List<String> ourNeighbours;
    private List<String> neighbours;
    private static final Steps step = new Steps();
    private static final Checks check = new Checks();

    @BeforeAll
    @DisplayName("Получение списка пограничных стран по code {Constant.getValue}")
    public static void beforeAll() {
        Steps steps = new Steps();
        Checks check = new Checks();
        ValidatableResponse response = steps.sendGetNeighbours(Constants.BLR_CODE);
        ourNeighbours = check.checkIsNighboursGet(response);
    }

    static Stream<Arguments> countryData() {
        return ourNeighbours.stream()
                .map(ourNeighbours -> Arguments.of(ourNeighbours, Constants.BLR_CODE));
    }

    @ParameterizedTest
    @MethodSource("countryData")
    @DisplayName("Проверка граничности стран")
    public void countryTest(String country, String neighbour) {
        ValidatableResponse testResp = step.sendGetNeighbours(country);
        neighbours = check.containsNeighbours(testResp);
        assertTrue(neighbours.contains(neighbour), "Страна " + country + " должна иметь {Constant} в качестве соседа");
    }
}
