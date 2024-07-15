import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static class Node {
        int key, val;
        Node next;

        Node(int key, int val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    static class HashTable {
        Node[] table;
        HashTable(int size) {
            table = new Node[size];
        }

        int getIndex(int key) {
            return Math.abs(key) % table.length;
        }

        void put(int key) {
            int idx = getIndex(key);
            Node cur = table[idx];
            if (cur == null) {
                table[idx] = new Node(key, 1, null);
                return;
            }
            while (cur != null) {
                if (cur.key == key) {
                    ++cur.val;
                    return;
                }
                if (cur.next == null) {
                    cur.next = new Node(key, 1, null);
                    return;
                }
                cur = cur.next;
            }
        }

        int get(int key) {
            int idx = getIndex(key);
            Node cur = table[idx];
            while (cur != null) {
                if (cur.key == key) {
                    return cur.val;
                }
                cur = cur.next;
            }
            return 0;
        }
    }

    static int N, M;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        HashTable hashTable = new HashTable(N);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            hashTable.put(num);
        }
        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int target = Integer.parseInt(st.nextToken());
            sb.append(hashTable.get(target)).append(" ");
        }
        System.out.println(sb);
    }
}