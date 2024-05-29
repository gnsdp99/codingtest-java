import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, minScore = Integer.MAX_VALUE;
    static int[] scores;
    static ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        scores = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            adjList.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        int u = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());
        while (u != -1 && v != -1) {
            adjList.get(u).add(v);
            adjList.get(v).add(u);
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < N + 1; i++) {
            minScore = Math.min(minScore, BFS(i));
            scores[i] = BFS(i);
        }
        ArrayList<Integer> candidates = new ArrayList<>();
        for (int i = 1; i < N + 1; i++) {
            if (scores[i] == minScore) {
                candidates.add(i);
            }
        }
        sb.append(minScore).append(" ").append(candidates.size()).append("\n");
        for (int candidate : candidates) {
            sb.append(candidate).append(" ");
        }
        System.out.println(sb);
    }

    static int BFS(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        int[] visited = new int[N + 1];
        Arrays.fill(visited, -1);
        visited[start] = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int adj : adjList.get(node)) {
                if (visited[adj] != -1) {
                    continue;
                }
                queue.offer(adj);
                visited[adj] = visited[node] + 1;
            }
        }
        int max = 0;
        for (int score : visited) {
            if (max < score) {
                max = score;
            }
        }
        return max;
    }
}