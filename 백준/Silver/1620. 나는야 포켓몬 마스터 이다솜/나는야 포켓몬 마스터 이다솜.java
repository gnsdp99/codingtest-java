import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int MAX_TABLE = 100_003;
    static final int MAX = 100_000;

    static class Pocketmon {
        int idx;
        String name;
        Pocketmon next;
    }

    static Pocketmon[] hashTable = new Pocketmon[MAX_TABLE]; // 문자열을 숫자로 저장
    static Pocketmon[] pool;
    static int pIdx = 0;
    static String[] names; // 숫자를 문자열로 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        pool = new Pocketmon[MAX + 100];
        names = new String[MAX + 100];

        init();
        for (int i = 1; i <= N; i++) {
            String name = br.readLine();
            int hash = hash(name);
            Pocketmon node = pool[pIdx++];
            node.idx = i;
            node.name = name;
            node.next = hashTable[hash];
            hashTable[hash] = node;

            names[i] = name;
        }

        for (int i = 0; i < M; i++) {
            String name = br.readLine();
            int idx = 0;
            char c = name.charAt(0);
            if ('0' < c && c <= '9') {
                idx = Integer.parseInt(name);
            }
            if (idx == 0) {
                int hash = hash(name);
                Pocketmon node = hashTable[hash];
                while (node != null && !node.name.equals(name)) {
                    node = node.next;
                }
                sb.append(node.idx);
            } else {
                sb.append(names[idx]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void init() {
        pIdx = 0;
        for (int i = 0; i < pool.length; i++) {
            pool[i] = new Pocketmon();
        }
    }


    public static int hash(String str) {
        int hash = 5381;
        for (int i = 0; i < str.length(); i++) {
            int c = str.charAt(i);
            hash = ((hash << 5) + hash + c) % MAX_TABLE;
        }
        return hash % MAX_TABLE;
    }

    public static int findMax(int N) {
        for (int i = 100_000; i <= 200_000; i++) {
            boolean isPrime = true;
            for (int j = 2; j * j <= i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                return i;
            }
        }
        return 0;
    }
}