package day02;

public class Task3 {

    public static void main(String[] args) {
        int coffee = 150;
        int cr = 120;
        System.out.println("Кофе: 2 x " + coffee + " = " + 2 * coffee);
        System.out.println("Круассан: 3 x " + cr + " = " + 3 * cr);
        System.out.println("Итого: " + (coffee * 2 + cr * 3));
        coffee += 20;
        System.out.println("Кофе подорожал до " + coffee);
        System.out.println("Новое итого: " + (coffee * 2 + cr * 3));
    }
}
