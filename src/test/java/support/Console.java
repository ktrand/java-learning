package support;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public final class Console {

    private Console() {
    }

    @FunctionalInterface
    public interface Program {
        void run() throws Exception;
    }

    public static String output(Program program) {
        return output(program, "");
    }

    public static String output(Program program, String input) {
        PrintStream originalOut = System.out;
        InputStream originalIn = System.in;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try (PrintStream capture = new PrintStream(buffer, true, StandardCharsets.UTF_8)) {
            System.setOut(capture);
            System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
            program.run();
        } catch (Exception e) {
            throw new AssertionError("Программа упала с ошибкой: " + e, e);
        } finally {
            System.setOut(originalOut);
            System.setIn(originalIn);
        }
        return normalize(buffer.toString(StandardCharsets.UTF_8));
    }

    public static String lines(String... lines) {
        return String.join("\n", lines);
    }

    private static String normalize(String text) {
        return text.lines()
                .map(String::stripTrailing)
                .collect(Collectors.joining("\n"))
                .stripTrailing();
    }
}
