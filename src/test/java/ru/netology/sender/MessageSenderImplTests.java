package ru.netology.sender;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MessageSenderImplTests {

    static String greetingRus = "Добро пожаловать";
    static String greetingEn = "Welcome";

    @Test
    void test_message_send_moscow_ip() {

        String expected = greetingRus;

        GeoService geoService = Mockito.mock(GeoService.class);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);

        Mockito.when(geoService.byIp(GeoServiceImpl.MOSCOW_IP)).thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn(greetingRus);

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);

        Map<String, String> headers = new HashMap<String, String>();

        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, GeoServiceImpl.MOSCOW_IP);

        assertThat(expected, is(messageSender.send(headers)));
    }

    @Test
    void test_message_send_new_york_ip() {

        String expected = greetingEn;
        GeoService geoService = Mockito.mock(GeoService.class);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);

        Mockito.when(geoService.byIp(GeoServiceImpl.NEW_YORK_IP)).thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));
        Mockito.when(localizationService.locale(Country.USA)).thenReturn(greetingEn);

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);

        Map<String, String> headers = new HashMap<String, String>();

        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, GeoServiceImpl.NEW_YORK_IP);

        assertThat(expected, is(messageSender.send(headers)));
    }

    @Test
    void test_message_send_localhost() {

        String expected = null;
        GeoService geoService = Mockito.mock(GeoService.class);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);

        Mockito.when(geoService.byIp(GeoServiceImpl.LOCALHOST)).thenReturn(new Location(null, null, null, 0));

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);

        Map<String, String> headers = new HashMap<String, String>();

        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, GeoServiceImpl.LOCALHOST);

        assertThat(expected, is(messageSender.send(headers)));
    }

    @Test
    void test_message_send_ip_not_exist() {

        String expected = greetingEn;

        GeoService geoService = Mockito.mock(GeoService.class);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);

        Mockito.when(localizationService.locale(Country.USA)).thenReturn(greetingEn);

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);

        Map<String, String> headers = new HashMap<String, String>();

        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "");

        assertThat(expected, is(messageSender.send(headers)));
    }
}
