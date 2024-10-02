package ru.netology.sender;

import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.Mockito;
import ru.netology.geo.GeoService;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.i18n.LocalizationService;

public class MessageSenderImplTest {


    @MethodSource
    @ParameterizedTest
    static void test_message_sender_send() {
        GeoService geoService = Mockito.mock(GeoService.class);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
    }

}
