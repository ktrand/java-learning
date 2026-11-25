package day01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static support.Console.lines;
import static support.Console.output;

class Day01Test {

    private static final String[] NO_ARGS = {};

    @Test
    @DisplayName("Задача 1: Привет, мир!")
    void task1() {
        assertEquals("Привет, мир!", output(() -> Task1.main(NO_ARGS)),
                "Задача 1 печатает не то");
    }

    @Test
    @DisplayName("Задача 2: меню кафе")
    void task2() {
        assertEquals(lines(
                "Меню кафе",
                "Кофе - 150 руб.",
                "Чай - 90 руб."
        ), output(() -> Task2.main(NO_ARGS)), "Задача 2 печатает не то");
    }

    @Test
    @DisplayName("Задача 3: раз, два, три")
    void task3() {
        assertEquals("Раз, два, три!", output(() -> Task3.main(NO_ARGS)),
                "Задача 3 печатает не то");
    }

    @Test
    @DisplayName("Задача 4: калькулятор")
    void task4() {
        assertEquals(lines(
                "7 + 5 = 12",
                "7 - 5 = 2",
                "7 * 5 = 35"
        ), output(() -> Task4.main(NO_ARGS)), "Задача 4 печатает не то");
    }

    @Test
    @DisplayName("Задача 5: особые символы")
    void task5() {
        assertEquals(lines(
                "Он сказал: \"Привет!\"",
                "Файл лежит в C:\\java\\day01"
        ), output(() -> Task5.main(NO_ARGS)), "Задача 5 печатает не то");
    }

    @Test
    @DisplayName("Задача 6 ★: чек")
    void task6() {
        assertEquals(lines(
                "Кофе x2 = 300",
                "Круассан x3 = 360",
                "Итого: 660"
        ), output(() -> Task6.main(NO_ARGS)), "Задача 6 печатает не то");
    }

    @Test
    @DisplayName("Задача 7 ★: рамка")
    void task7() {
        assertEquals(lines(
                "+--------+",
                "|  Java  |",
                "+--------+"
        ), output(() -> Task7.main(NO_ARGS)), "Задача 7 печатает не то");
    }
}
