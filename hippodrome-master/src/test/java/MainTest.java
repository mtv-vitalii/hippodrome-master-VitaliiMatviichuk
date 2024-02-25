import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class MainTest {
    @Test
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    @Disabled("Вимкнено для економії часу під час запуску всіх тестів")
    void mainExecutesWithin22Seconds() {
        assertDoesNotThrow(() -> Main.main(new String[]{}));
    }

}