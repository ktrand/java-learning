package day02;

public class Task5 {

    public static void main(String[] args) {
        int total = 7;
        int people = 2;
        System.out.println("Каждому целых: " + total / people);
        System.out.println("Остаток: " + total % people);
        System.out.println("Каждому точно: " + (double) total / people);
    }
}
