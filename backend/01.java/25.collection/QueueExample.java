import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueExample {
    public static void main(String[] args) {

        System.out.println("Arraydeque 예제");
        Queue<Integer> aq = new ArrayDeque<>();
        aq.add(4);
        aq.add(5);
        aq.add(1);
        aq.add(3);

        System.out.println("Arraydeque poll 순서:");
        while (!aq.isEmpty()) {
            System.out.println(aq.poll()); // 입력 순서대로 출력됨
        }


        System.out.println("LinkedList 예제");
        Queue<Integer> ll = new LinkedList<>();
        ll.add(5);
        ll.add(1);
        ll.add(3);
        ll.add(7);

        System.out.println("LinkedList poll 순서:");
        while (!ll.isEmpty()) {
            System.out.println(ll.poll()); // 정렬된 순서대로 출력됨
        }

        System.out.println("PriorityQueue 예제");
        Queue<Integer> pq = new PriorityQueue<>(); // 기본 정렬 (오름차순)

        pq.add(3);
        pq.add(6);
        pq.add(7);
        pq.add(1);


        System.out.println("PriorityQueue poll 순서:");
        while (!pq.isEmpty()) {
            System.out.println(pq.poll()); // 정렬된 순서대로 출력됨
        }
    }
}

