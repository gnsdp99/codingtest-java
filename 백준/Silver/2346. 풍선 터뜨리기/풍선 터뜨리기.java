import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static class Node {
        int idx;
        int val;
        Node next;
        Node prev;
        Node (int idx, int val, Node next, Node prev) {
            this.idx = idx;
            this.val = val;
            this.next = next;
            this.prev = prev;
        }
    }

    static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        Node head = null;
        Node cur = null;
        for (int i = 1; i <= N; i++) {
            Node node = new Node(i, Integer.parseInt(st.nextToken()), null, null);
            if (cur == null) {
                head = cur = node;
            } else {
                cur.next = node;
                node.prev = cur;
                cur = cur.next;
            }
        }
        head.prev = cur;

        int count = 0;
        while (head.next != head) {
            while (count < 0) {
                head = head.prev;
                ++count;
            }
            while (count > 0) {
                head = head.next;
                --count;
            }
            sb.append(head.idx).append(" ");
            head.prev.next = head.next;
            head.next.prev = head.prev;
            if (head.val > 0) {
                count = head.val - 1;
            } else {
                count = head.val;
            }
            head = head.next;
        }
        sb.append(head.idx).append(" ");
        System.out.println(sb);
    }
}