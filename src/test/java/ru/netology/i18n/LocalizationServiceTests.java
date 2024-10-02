package ru.netology.i18n;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LocalizationServiceTests {

    public static Stream<Arguments> test_localization_service() {
        return Stream.of(
                Arguments.of(Country.RUSSIA, "Добро пожаловать"),
                Arguments.of(Country.USA, "Welcome")
        );
    }

    @MethodSource
    @ParameterizedTest
    public void test_localization_service(Country country, String expected) {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String result = localizationService.locale(country);

        assertThat(result, equalTo(expected));

    }
}
