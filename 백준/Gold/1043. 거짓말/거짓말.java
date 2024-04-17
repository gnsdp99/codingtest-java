import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int[] parent;
    static ArrayList<Integer> knowList = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());

        if (K == 0) {
            System.out.println(M);
            return;
        }

        for (int i = 0; i < K; i++) {
            knowList.add(Integer.parseInt(st.nextToken()));
        }

        int ans = 0;
        ArrayList<ArrayList<Integer>> partyList = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            partyList.add(new ArrayList<>());
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int prev = Integer.parseInt(st.nextToken());
            partyList.get(i).add(prev);
            for (int j = 0; j < num - 1; j++) {
                int cur = Integer.parseInt(st.nextToken());
                partyList.get(i).add(cur);
                union(prev, cur);
            }
        }

        for (ArrayList<Integer> party : partyList) {
            boolean can = true;
            for (int p : party) {
                if (knowList.contains(find(p))) {
                    can = false;
                    break;
                }
            }
            if (can) {
                ++ans;
            }
        }
        System.out.println(ans);
    }

    static int find(int a) {

        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    static void union(int a, int b) {

        a = find(a);
        b = find(b);

        if (knowList.contains(b)) {
            int tmp = b;
            b = a;
            a = tmp;
        }

        parent[b] = a;
    }
}