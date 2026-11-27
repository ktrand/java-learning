package day03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static support.Console.lines;
import static support.Console.output;

class Day03Test {

    private static final String[] NO_ARGS = {};

    @Test
    @DisplayName("Задача 1: скобки")
    void task1() {
        assertEquals(lines(
                "20",
                "9",
                "20",
                "15",
                "1"
        ), output(() -> Task1.main(NO_ARGS)), "Задача 1 печатает не то");
    }

    @Test
    @DisplayName("Задача 2: скидка")
    void task2() {
        assertEquals(lines(
                "Сумма: 640",
                "Скидка 15%: 96",
                "К оплате: 544"
        ), output(() -> Task2.main(NO_ARGS)), "Задача 2 печатает не то");
    }

    @Test
    @DisplayName("Задача 3: цифры числа")
    void task3() {
        assertEquals(lines(
                "Тысячи: 4",
                "Сотни: 7",
                "Десятки: 2",
                "Единицы: 5",
                "Сумма цифр: 18"
        ), output(() -> Task3.main(NO_ARGS)), "Задача 3 печатает не то");
    }

    @Test
    @DisplayName("Задача 4: температура")
    void task4() {
        assertEquals(lines(
                "Теплее всего: 4",
                "Холоднее всего: -7",
                "Разница: 11"
        ), output(() -> Task4.main(NO_ARGS)), "Задача 4 печатает не то");
    }

    @Test
    @DisplayName("Задача 5: какая пицца больше")
    void task5() {
        assertEquals(lines(
                "Одна пицца 30 см: 706.86",
                "Две пиццы 20 см: 628.32"
        ), output(() -> Task5.main(NO_ARGS)), "Задача 5 печатает не то");
    }

    @Test
    @DisplayName("Задача 6 ★: такси")
    void task6() {
        assertEquals(lines(
                "Утром машин: 6, в последней: 3",
                "Вечером машин: 6, в последней: 4"
        ), output(() -> Task6.main(NO_ARGS)), "Задача 6 печатает не то");
    }

    @Test
    @DisplayName("Задача 7 ★: вклад")
    void task7() {
        assertEquals(lines(
                "Через 3 г.: 133100",
                "Доход: 33100"
        ), output(() -> Task7.main(NO_ARGS)), "Задача 7 печатает не то");
    }
}
