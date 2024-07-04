import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, K;

    static class Node {
        int val;
        Node next;

        Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    static class MyLinkedList {
        Node head;
        Node tail;

        MyLinkedList() {
            Node cur = new Node(1, null);
            head = cur;
            for (int i = 2; i <= N; i++) {
                cur.next = new Node(i, null);
                cur = cur.next;
            }
            tail = cur;
            tail.next = head;
        }

        int removeKth() {
            for (int i = 0; i < K - 1; i++) {
                head = head.next;
                tail = tail.next;
            }
            int removeVal = head.val;
            head = head.next;
            tail.next = head;
            return removeVal;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        sb.append("<");
        MyLinkedList list = new MyLinkedList();
        for (int i = 0; i < N - 1; i++) {
            sb.append(list.removeKth()).append(", ");
        }
        sb.append(list.removeKth());
        sb.append(">");

        System.out.println(sb);
    }
}