import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 구현
 * 무방향 그래프, 모든 노드를 최소 비용으로 연결 -> MST
 * ! 모든 노드를 연결하는 게 아니라 모든 노드에 물을 대는 것이다. 즉 연결되지 않은 노드가 있어도 된다.
 * 크루스칼 알고리즘을 수행하는데, (i, i)은 가상의 노드 0과 연결하고 우물을 파는 비용을 가중치로 한다.
 *
 * 입력
 * 논의 수 N [1, 300]
 * 우물 파는 비용 W [1, 100,000] 300 * 100,000 = 30,000,000 -> int
 * 두 우물을 연결하는 비용 P [1, 100,000]
 *
 * 출력
 * 모든 논에 물을 대는데 필요한 최소 비용 출력
 *
 * 시간복잡도 O(N^2)
 *
 * 결과
 *
 * */
public class Main {
    static class Edge implements Comparable<Edge> {
        int src, dst, weight;

        public Edge(int src, int dst, int weight) {
            this.src = src;
            this.dst = dst;
            this.weight = weight;
        }

        public int compareTo(Edge e) {
            return Integer.compare(weight, e.weight);
        }
    }

    static int N, ans;
    static int[] cost, parent;
    static PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        cost = new int[N + 1];
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            cost[i] = Integer.parseInt(br.readLine());
            parent[i] = i;
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= i; j++) st.nextToken();
            priorityQueue.offer(new Edge(0, i, cost[i]));
            for (int j = i + 1; j <= N; j++) {
                int weight = Integer.parseInt(st.nextToken());
                priorityQueue.offer(new Edge(i, j, weight));
            }
        }
        int cnt = 0;
        while (!priorityQueue.isEmpty()) {
            if (cnt == N) break;
            Edge edge = priorityQueue.poll();
            if (union(edge.src, edge.dst)) {
                ans += edge.weight;
                cnt++;
            }
        }
        System.out.println(ans);
    }

    static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    static boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa == pb) return false;

        parent[pb] = pa;
        return true;
    }
}