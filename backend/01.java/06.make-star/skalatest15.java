package skalajava;
import java.util.stream.*;
public class skalatest15 {
    public static void main(String[] args) {
        int size = 5;
        int floor = 1;

        IntStream.rangeClosed(floor, size).forEach(i ->{
            System.out.println("*".repeat(i));
        });
        
        while (floor <= size) {
            for (int i=0; i < floor; i++) {
                System.out.print("*");
            }
            System.out.println();
            floor++;
        }
    }
}
