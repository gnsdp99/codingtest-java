import java.util.*;

// 백준 1260. DFS와 BFS (S2)
public class P1260 {
    static List<Integer>[] adjList; // 각 노드의 연결된 노드 리스트
    static boolean[] visited; // 방문한 노드
    static int N, M, V;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // 정점의 개수
        M = sc.nextInt(); // 간선의 개수
        V = sc.nextInt(); // 시작 정점 번호

        adjList = new ArrayList[N + 1];
        for (int node = 1; node < N + 1; node++) {
            adjList[node] = new ArrayList<Integer>();
        }

        for (int i = 0; i < M; i++) {
            int node1 = sc.nextInt();
            int node2 = sc.nextInt();
            adjList[node1].add(node2);
            adjList[node2].add(node1);
        }

        // 번호가 작은 순으로 탐색하기 위해 정렬
        for (int node = 1; node < N + 1; node++) {
            Collections.sort(adjList[node]);
        }

        visited = new boolean[N + 1];
        dfs(V);
        System.out.println();
        visited = new boolean[N + 1];
        bfs(V);
    }

    private static void dfs(int curr) {
        visited[curr] = true;
        System.out.print(curr + " ");
        for (int next : adjList[curr]) {
            if (!visited[next]) {
                dfs(next);
            }
        }
    }

    private static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            int curr = queue.remove();
            System.out.print(curr + " ");
            for (int next : adjList[curr]) {
                if (!visited[next]) {
                    queue.offer(next);
                    visited[next] = true;
                }
            }
        }
    }
}
