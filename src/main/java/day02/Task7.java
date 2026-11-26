package day02;

public class Task7 {

    public static void main(String[] args) {
        int totalSeconds = 7385;

        System.out.println(totalSeconds / 60 / 60 + " ч " + (totalSeconds / 60) % 60 + " мин " + (totalSeconds % 60) + " с");
    }
}
