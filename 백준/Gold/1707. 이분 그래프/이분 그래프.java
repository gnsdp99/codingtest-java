import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int num;
        Node next;

        Node(int num, Node next) {
            this.num = num;
            this.next = next;
        }
    }
    static int V, E, pIdx;
    static Node[] pool; // 노드 풀
    static Node[] adjList; // 인접 리스트
    static int[] visited; // 0: 미방문, 1:

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        pool = new Node[400_400]; // 간선 최대 개수 * 2 (정점 두 개 주어짐)
        adjList = new Node[20_200]; // 노드 최대 개수

        int K = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < K; tc++) {
            init(); // 초기화

            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            visited = new int[V + 1];

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                makeNode(u, v);
                makeNode(v, u);
            }

            boolean ans = true;
            for (int i = 1; i <= V; i++) {
                if (visited[i] == 0) {
                    if (!DFS(i, 1)) {
                        ans = false;
                        break;
                    }
                }
            }
            sb.append(ans ? "YES" : "NO").append("\n");
        }
        System.out.println(sb);
    }

    static boolean DFS(int node, int color) {
        visited[node] = color;

        for (Node cur = adjList[node]; cur != null; cur = cur.next) {
            if (visited[cur.num] == color) return false; // 인접한 노드는 색이 같으면 안됨
            if (visited[cur.num] == 0) {
                if(!DFS(cur.num, 3 - color)) { // 1이면 2로, 2면 1로
                    return false;
                }
            }
        }
        return true;
    }

    static void makeNode(int u, int v) {
        Node nodeU = new Node(u, adjList[v]);
        adjList[v] = nodeU;
        pool[pIdx++] = nodeU;
    }

    static void init() {
        pIdx = 0; // 노드 풀 인덱스 초기화
        for (int i = 1; i <= V; i++) { // 인접 리스트 초기화
            adjList[i] = null;
        }
    }
}