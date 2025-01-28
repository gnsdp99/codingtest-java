import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    static class Node {
        int screen, time, clip;
        Node(int screen, int time, int clip) {
            this.screen = screen;
            this.time = time;
            this.clip = clip;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int S = Integer.parseInt(br.readLine());
        int answer = 0;

        boolean[][] visited = new boolean[2001][2001];
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(1, 0, 0));
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.screen == S) {
                answer = node.time;
                break;
            }
            int nt = node.time + 1;

            if (node.screen > 0 && !visited[node.screen][node.screen]) {
                queue.offer(new Node(node.screen, nt, node.screen));
                visited[node.screen][node.screen] = true;
            }
            int sum = node.screen + node.clip;
            if (node.clip > 0 && sum < visited.length && !visited[sum][node.clip]) {
                queue.offer(new Node(sum, nt, node.clip));
                visited[sum][node.clip] = true;
            }
            int diff = node.screen - 1;
            if (node.screen > 0 && !visited[diff][node.clip]) {
                queue.offer(new Node(diff, nt, node.clip));
                visited[diff][node.clip] = true;
            }
        }

        System.out.println(answer);
    }
}