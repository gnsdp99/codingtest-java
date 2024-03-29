import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Bridge implements Comparable<Bridge> {

        int landA, landB, dist;

        public Bridge(int landA, int landB, int dist) {
            this.landA = landA;
            this.landB = landB;
            this.dist = dist;
        }

        @Override
        public int compareTo(Bridge bridge) {
            return Integer.compare(dist, bridge.dist);
        }
    }

    static class Pos {
        int r, c;

        Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int N, M, ans, numIsland;
    static int[][] board;
    static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    static ArrayList<HashSet<Pos>> islands = new ArrayList<>();
    static PriorityQueue<Bridge> priorityQueue = new PriorityQueue<>();
    static int[] parents;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        findIsland();
        numIsland = islands.size();
        parents = new int[numIsland + 1];
        for (int i = 0; i < numIsland; i++) {
            parents[i] = i;
        }

        findBridge();

        boolean isAll = kruskal();
        System.out.println(isAll ? ans : -1);
    }

    static boolean kruskal() {

        int cnt = 0;
        while (cnt < numIsland - 1) {

            if (priorityQueue.isEmpty()) {
                return false;
            }

            Bridge bridge = priorityQueue.poll();

            int A = bridge.landA;
            int B = bridge.landB;
            int dist = bridge.dist;

            if (union(A, B)) {
                ans += dist;
                cnt++;
            }
        }
        return true;
    }

    static int find(int x) {

        if (parents[x] == x) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }

    static boolean union(int x, int y) {

        x = find(x);
        y = find(y);

        if (x == y) {
            return false;
        }

        parents[x] = y;
        return true;
    }

    static void findBridge() {

        for (HashSet<Pos> set : islands) {
            for (Pos pos : set) {
                int landA = board[pos.r][pos.c];
                for (int d = 0; d < 4; d++) {
                    int nr = pos.r;
                    int nc = pos.c;
                    int dist = 0;
                    while (true) {
                        nr += delta[d][0];
                        nc += delta[d][1];

                        if (!isIn(nr, nc) || landA == board[nr][nc]) {
                            break;
                        }
                        int landB = board[nr][nc];

                        if (landB > 0) {
                            if (dist < 2) {
                                break;
                            }
                            Bridge bridge = new Bridge(landA, landB, dist);
                            priorityQueue.offer(bridge);
                            break;
                        }
                        dist++;
                    }

                }
            }
        }
    }

    static void findIsland() {

        boolean[][] visited = new boolean[N][M];

        islands = new ArrayList<>();

        int num = 1;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (board[r][c] > 0 && !visited[r][c]) {
                    islands.add(floodfill(r, c, num++, visited));
                }
            }
        }
    }

    static HashSet<Pos> floodfill(int r, int c, int num, boolean[][] visited) {

        Queue<Pos> queue = new ArrayDeque<>();
        Pos start = new Pos(r, c);
        queue.offer(start);
        visited[r][c] = true;

        HashSet<Pos> island = new HashSet<>();
        island.add(start);
        board[r][c] = num;

        while (!queue.isEmpty()) {

            Pos cur = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = cur.r + delta[d][0];
                int nc = cur.c + delta[d][1];

                if (!isIn(nr, nc) || visited[nr][nc] || board[nr][nc] == 0) {
                    continue;
                }

                Pos next = new Pos(nr, nc);
                queue.offer(next);
                island.add(next);
                board[nr][nc] = num;
                visited[nr][nc] = true;
            }
        }
        return island;
    }

    static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}