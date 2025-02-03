import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = 1;
        while (true) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            if (n == 0 && m == 0) {
                break;
            }

            sb.append("Case ").append(T).append(": ");
            int[] parent = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(parent, a, b);
            }

            Set<Integer> rootSet = new HashSet<>();
            for (int i = 1; i <= n; i++) {
                int p = find(parent, i);
                if (p > 0) {
                    rootSet.add(p);
                }
            }

            int numTree = rootSet.size();
            if (numTree == 0) {
                sb.append("No trees.\n");
            } else if (numTree == 1) {
                sb.append("There is one tree.\n");
            } else {
                sb.append("A forest of ").append(numTree).append(" trees.\n");
            }
            ++T;
        }
        System.out.println(sb);
    }

    static int find(int[] parent, int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent, parent[a]);
    }

    static void union(int[] parent, int a, int b) {
        int pa = find(parent, a);
        int pb = find(parent, b);
        if (pa == pb) {
            parent[pa] = 0;
            return;
        }
        if (pa > pb) {
            int tmp = pa;
            pa = pb;
            pb = tmp;
        }
        parent[pb] = pa;
    }
}