import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static class Node {
        String name;
        int idx;
        Node next;

        Node(String name, int idx, Node next) {
            this.name = name;
            this.idx = idx;
            this.next = next;
        }
    }

    static class HashTable {
        Node[] table;
        HashTable(int size) {
            this.table = new Node[size];
        }
        int hash(String name) {
            int hash = 5381;
            for (int i = 0; i < name.length(); i++) {
                hash = (((hash << 5) + hash) + name.charAt(i)) % table.length;
            }
            return hash % table.length;
        }

        void put(String name, int idx) {
            int hash = hash(name);
            if (table[hash] == null) {
                table[hash] = new Node(name, idx, null);;
                return;
            }
            Node node = new Node(name, idx, table[hash].next);
            table[hash].next = node;
        }

        int getIndex(String name) {
            int hash = hash(name);
            Node cur = table[hash];
            while (cur != null) {
                if (cur.name.equals(name)) {
                    return cur.idx;
                }
                cur = cur.next;
            }
            return -1;
        }
    }

    static int N, M;
    static final int MAX_SIZE = 10007;
    static HashTable hashTable = new HashTable(MAX_SIZE);
    static String[] nameArr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nameArr = new String[N + 1];
        for (int i = 1; i <= N; i++) {
            String name = br.readLine();
            nameArr[i] = name;
            hashTable.put(name, i);
        }
        for (int i = 0; i < M; i++) {
            String input = br.readLine();
            char firstCh = input.charAt(0);
            if ('1' <= firstCh && firstCh <= '9') {
                sb.append(nameArr[Integer.parseInt(input)]);
            } else {
                sb.append(hashTable.getIndex(input));
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}