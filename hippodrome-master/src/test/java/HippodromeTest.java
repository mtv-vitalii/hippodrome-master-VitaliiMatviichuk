import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import java.util.ArrayList;
import java.util.List;

class HippodromeTest {
    @Test
    void constructor_ThrowIllegalArgumentException_When_Horses_IsNull() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(null),
                "Expected constructor to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("Horses cannot be null."));
    }

    @Test
    void constructor_ThrowIllegalArgumentException_When_HorsesIsEmpty() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(Collections.emptyList()),
                "Expected constructor to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("Horses cannot be empty."));
    }

    @Test
    void getHorses_ReturnSameList_As_Passed_To_Constructor() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse" + i, i, i * 10));
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        List<Horse> returnedHorses = hippodrome.getHorses();
        assertArrayEquals(horses.toArray(), returnedHorses.toArray());
    }

    @Test
    void move_All_Horses() {
        List<Horse> mockedHorses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Horse horse = Mockito.mock(Horse.class);
            mockedHorses.add(horse);
        }
        Hippodrome hippodrome = new Hippodrome(mockedHorses);

        hippodrome.move();

        for (Horse horse : mockedHorses) {
            verify(horse).move();
        }
    }

    @Test
    void getWinner_ReturnHorse_With_Biggest_Distance() {
        Horse horse1 = new Horse("Horse1", 3, 100);
        Horse horse2 = new Horse("Horse2", 3, 150); // Переможець
        Horse horse3 = new Horse("Horse3", 3, 120);

        Hippodrome hippodrome = new Hippodrome(List.of(horse1, horse2, horse3));
        Horse winner = hippodrome.getWinner();

        assertEquals(horse2, winner);
    }

}