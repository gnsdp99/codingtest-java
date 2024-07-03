import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static class Node {
        int val;
        Node next;
        Node prev;

        Node(int val, Node next, Node prev) {
            this.val = val;
            this.next = next;
            this.prev = prev;
        }
    }

    static class CircularList {
        Node head;
        Node tail;

        CircularList() {
            Node cur = new Node(1, null, null);
            head = cur;
            for (int i = 2; i <= N; i++) {
                Node next = new Node(i, null, cur);
                cur.next = next;
                cur = next;
            }
            tail = cur;
            tail.next = head;
            head.prev = tail;
        }

        void moveLeft(int count) {
            for (int i = 0; i < count; i++) {
                tail = tail.next;
                head = head.next;
            }
        }

        void moveRight(int count) {
            for (int i = 0; i < count; i++) {
                head = head.prev;
                tail = head.prev;
            }
        }

        void popLeft() {
            head = head.next;
            head.prev = tail;
            tail.next = head;
        }

        int getDistLeft(int target) {
            int dist = 0;
            Node cur = head;
            while (cur.val != target) {
                ++dist;
                cur = cur.next;
            }
            return dist;
        }

        int getDistRight(int target) {
            int dist = 0;
            Node cur = head;
            while (cur.val != target) {
                ++dist;
                cur = cur.prev;
            }
            return dist;
        }

        int getHeadVal() {
            return head.val;
        }
    }

    static int N, M;
    static int[] targetArr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        targetArr = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            targetArr[i] = Integer.parseInt(st.nextToken());
        }

        CircularList list = new CircularList();

        int ans = 0;
        for (int target : targetArr) {
            if (list.getHeadVal() == target) {
                list.popLeft();
                continue;
            }

            int distLeft = list.getDistLeft(target);
            int distRight = list.getDistRight(target);
            if (distLeft <= distRight) {
                ans += distLeft;
                list.moveLeft(distLeft);
            } else if (distLeft > distRight) {
                ans += distRight;
                list.moveRight(distRight);
            }
            list.popLeft();
        }

        System.out.println(ans);
    }
}