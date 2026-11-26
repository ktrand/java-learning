package day02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static support.Console.lines;
import static support.Console.output;

class Day02Test {

    private static final String[] NO_ARGS = {};

    @Test
    @DisplayName("Задача 1: анкета")
    void task1() {
        assertEquals(lines(
                "Название: Кофемашина",
                "Год выпуска: 2024",
                "Работает: true",
                "Рейтинг: 4.5",
                "Класс: A"
        ), output(() -> Task1.main(NO_ARGS)), "Задача 1 печатает не то");
    }

    @Test
    @DisplayName("Задача 2: поменять местами")
    void task2() {
        assertEquals("a = 10, b = 5", output(() -> Task2.main(NO_ARGS)),
                "Задача 2 печатает не то");
    }

    @Test
    @DisplayName("Задача 3: касса")
    void task3() {
        assertEquals(lines(
                "Кофе: 2 x 150 = 300",
                "Круассан: 3 x 120 = 360",
                "Итого: 660",
                "Кофе подорожал до 170",
                "Новое итого: 700"
        ), output(() -> Task3.main(NO_ARGS)), "Задача 3 печатает не то");
    }

    @Test
    @DisplayName("Задача 4: счётчик гостей")
    void task4() {
        assertEquals(lines(
                "Посетителей: 0",
                "Посетителей: 1",
                "Посетителей: 2",
                "Посетителей: 7",
                "Посетителей: 6"
        ), output(() -> Task4.main(NO_ARGS)), "Задача 4 печатает не то");
    }

    @Test
    @DisplayName("Задача 5: делим счёт")
    void task5() {
        assertEquals(lines(
                "Каждому целых: 3",
                "Остаток: 1",
                "Каждому точно: 3.5"
        ), output(() -> Task5.main(NO_ARGS)), "Задача 5 печатает не то");
    }

    @Test
    @DisplayName("Задача 6 ★: переполнение")
    void task6() {
        assertEquals(lines(
                "Максимум int: 2147483647",
                "int + 1: -2147483648",
                "long + 1: 2147483648"
        ), output(() -> Task6.main(NO_ARGS)), "Задача 6 печатает не то");
    }

    @Test
    @DisplayName("Задача 7 ★: часы")
    void task7() {
        assertEquals("2 ч 3 мин 5 с", output(() -> Task7.main(NO_ARGS)),
                "Задача 7 печатает не то");
    }
}
