package ru.netology.geo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GeoServiceImlTests {

    public static Stream<Arguments> test_geo_service() {
        return Stream.of(
                Arguments.of(GeoServiceImpl.LOCALHOST, new Location(null, null, null, 0)),
                Arguments.of(GeoServiceImpl.MOSCOW_IP, new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of("172.", new Location("Moscow", Country.RUSSIA, null, 0)),
                Arguments.of("96.", new Location("New York", Country.USA, null, 0)),
                Arguments.of("empty", null)
        );
    }

    @MethodSource
    @ParameterizedTest
    public void test_geo_service(String ipAddress, Location expected) {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location result = geoService.byIp(ipAddress);

        if (expected == null) {
            assertThat(result, is(expected));
        } else {
            assertThat(result, samePropertyValuesAs(expected));
        }
    }

    @Test
    public void test_by_coordinates() {
        GeoServiceImpl geoService = new GeoServiceImpl();

        Exception exception = assertThrows(RuntimeException.class, () -> {
            geoService.byCoordinates(00.000, 00.000);
        });

        assertEquals("Not implemented", exception.getMessage());
    }
}
