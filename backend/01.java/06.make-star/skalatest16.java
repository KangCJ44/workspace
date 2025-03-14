package skalajava;
import java.util.stream.IntStream;

public class skalatest16 {
    public static void main(String[] args) {
        int size = 5;
        int floor = 1;

        IntStream.rangeClosed(floor, size).forEach(i -> {
            // 공백 출력
            System.out.print(" ".repeat(size - i));
            // 별 출력
            System.out.println("*".repeat(2 * i - 1));
        });
    }
}

