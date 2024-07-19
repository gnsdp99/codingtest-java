import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

    static int T, F;
    static Map<String, Integer> stringIntegerMap;
    static int[] parent;
    static int[] rank;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            stringIntegerMap = new TreeMap<>();
            F = Integer.parseInt(br.readLine());
            parent = new int[(F << 1) + 1];
            rank = new int[(F << 1) + 1];
            Arrays.fill(rank, 1);
            for (int i = 0; i < parent.length; i++) {
                parent[i] = i;
            }
            int count = 1;
            for (int i = 0; i < F; i++) {
                st = new StringTokenizer(br.readLine());
                String A = st.nextToken();
                String B = st.nextToken();
                if (!stringIntegerMap.containsKey(A)) {
                    stringIntegerMap.put(A, count++);
                }
                if (!stringIntegerMap.containsKey(B)) {
                    stringIntegerMap.put(B, count++);
                }
                union(stringIntegerMap.get(A), stringIntegerMap.get(B));
                sb.append(rank[parent[stringIntegerMap.get(A)]]).append("\n");
            }
        }
        System.out.println(sb);
    }

    static int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa == pb) {
            return;
        }

        parent[pb] = pa;
        rank[pa] += rank[pb];
    }
}