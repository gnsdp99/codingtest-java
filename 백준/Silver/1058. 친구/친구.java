import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public class Main {

    static int N;
    static ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            adjList.add(new ArrayList<>());
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                if (input.charAt(j) == 'Y') {
                    adjList.get(i).add(j);
                }
            }
        }

        int max = 0;
        int[] visited = new int[N];
        for (int i = 0; i < N; i++) {
            int cnt = 0;
            Arrays.fill(visited, -1);
            visited[i] = 0;
            Queue<Integer> queue = new ArrayDeque<>();
            queue.offer(i);
            while (!queue.isEmpty()) {
                int num = queue.poll();
                if (visited[num] >= 2) {
                    break;
                }
                for (int j : adjList.get(num)) {
                    if (visited[j] != -1) {
                        continue;
                    }
                    visited[j] = visited[num] + 1;
                    queue.offer(j);
                    ++cnt;
                }
            }
            if (max < cnt) {
                max = cnt;
            }
        }
        System.out.println(max);
    }
}