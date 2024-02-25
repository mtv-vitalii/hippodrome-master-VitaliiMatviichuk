import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {
    private  Horse horse;
    /*@BeforeEach
    public*/
    @Test
    public void constructor_ThrowIllegalArgumentException_When_NameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse(null, 10.0, 0.0);
        }, "Name cannot be null.");
    }
    @Test
    public void constructor_ThrowIllegalArgumentException_With_NameCantBeNull_When_NameIsNull() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(null, 10.0, 0.0);
        });

        assertEquals("Name cannot be null.", thrown.getMessage());
    }
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n"})
    public void constructor_ThrowIllegalArgumentException_For_InvalidName(String testName) {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(testName, 10.0, 0.0);
        });
        assertEquals("Name cannot be blank.", thrown.getMessage());
    }
    @Test
    public void constructor_ThrowIllegalArgumentException_For_NegativeSpeed() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("HorseName", -1.0, 0.0);
        });
        assertEquals("Speed cannot be negative.", thrown.getMessage());
    }
    @Test
    public void constructor_ThrowIllegalArgumentException_For_NegativeDistance() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("HorseName", 10.0, -1.0);
        });
        assertEquals("Distance cannot be negative.", thrown.getMessage());
    }
    @Test
    void getName_Returns_Correct_Name() {
        Horse horse = new Horse("Test", 10, 100);
        assertEquals("Test", horse.getName());
    }

    @Test
    void getSpeed_Returns_Correct_Speed() {
        Horse horse = new Horse("Test", 10, 100);
        assertEquals(10, horse.getSpeed());
    }

    @Test
    void getDistance_Returns_Correct_Distance() {
        Horse horse = new Horse("Test", 10, 100);
        assertEquals(100, horse.getDistance());
    }

    @Test
    void getDistance_Returns_Zero_When_Constructed_With_Two_Params() {
        Horse horse = new Horse("Test", 10);
        assertEquals(0, horse.getDistance());
    }
    @Test
    void move_InvokesGetRandom_Double_With_CorrectParams_And_UpdatesDistance() {
        try (MockedStatic<Horse> mocked = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("Test", 10, 100);
            mocked.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);

            horse.move();

            mocked.verify(() -> Horse.getRandomDouble(0.2, 0.9), Mockito.times(1));
            assertEquals(105, horse.getDistance()); // Перевірка з урахуванням, що getRandomDouble повертає 0.5
        }
    }

}