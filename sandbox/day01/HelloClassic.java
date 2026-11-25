public class HelloClassic {

    public static void main(String[] args) {
        System.out.println("Привет из классического main!");
        System.out.println("Аргументов: " + args.length);
        for (String arg : args) {
            System.out.println(" - " + arg);
        }
    }
}
