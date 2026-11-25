package day01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BasicsTest {

    @Test
    @DisplayName("greet: приветствие с именем")
    void greet() {
        assertEquals("Привет, Аня!", Basics.greet("Аня"));
        assertEquals("Привет, Java!", Basics.greet("Java"));
    }

    @Test
    @DisplayName("sum: сумма двух чисел")
    void sum() {
        assertEquals(5, Basics.sum(2, 3));
        assertEquals(-1, Basics.sum(2, -3));
    }

    @Test
    @DisplayName("isOdd: положительные числа")
    void isOddPositive() {
        assertTrue(Basics.isOdd(3));
        assertTrue(Basics.isOdd(7));
        assertFalse(Basics.isOdd(4));
        assertFalse(Basics.isOdd(0));
    }

    @Test
    @DisplayName("isOdd: отрицательные числа")
    void isOddNegative() {
        assertTrue(Basics.isOdd(-3), "-3 нечётное");
        assertFalse(Basics.isOdd(-8), "-8 чётное");
    }

    @Test
    @DisplayName("average: дробная часть не теряется")
    void average() {
        assertEquals(2.5, Basics.average(2, 3), 1e-9);
        assertEquals(-0.5, Basics.average(0, -1), 1e-9);
        assertEquals(4.0, Basics.average(4, 4), 1e-9);
    }

    @Test
    @DisplayName("★ average: очень большие числа")
    void averageOfHugeNumbers() {
        assertEquals(2_000_000_000.0, Basics.average(2_000_000_000, 2_000_000_000), 1e-9);
    }
}
