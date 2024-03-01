import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 구현
 * - 관리할 대상: 나무, 나무의 나이, 각 땅의 (양분, 나무), 봄에 죽은 나무, 봄에 산 나무, 가을에 추가된 후 나무, SD2D의 양분 배열
 *
 * 입력
 * - 땅의 크기 N [1, 10]
 * - 초기 나무의 수 M [1, N^2=100]
 * - 몇 년 K [1, 1,000]
 * - S2D2가 추가할 각 땅의 양분 [1, 100]
 * - 초기 나무의 나이 [1, 10]
 *
 * 출력
 * - K년 후 살아남은 나무의 수 출력
 *
 * 시간복잡도 O(K * N^2)
 *
 * 결과
 *
 * */
public class Main {
    static class Pos {
        int x, y;
        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Tree implements Comparable<Tree> {
        Pos pos;
        int age;

        Tree(Pos pos, int age) {
            this.pos = pos;
            this.age = age;
        }
        public int compareTo(Tree t) {
            return Integer.compare(age, t.age);
        }
    }
    static int N, M, K;
    static int[][] S2D2; // S2D2가 추가하는 양분
    static int[][] foods; // 현재 양분
    static PriorityQueue<Tree> trees; // 나무
    static Queue<Tree> dead; // 죽은 나무
    static Queue<Tree> alive; // 산 나무
    static Queue<Tree> newTree; // 새로 태어난 나무 및 원래 있던 나무
    static Pos[] delta = {new Pos(-1, 0), new Pos(-1, 1), new Pos(0, 1), new Pos(1, 1)
            , new Pos(1, 0), new Pos(1, -1), new Pos(0, -1), new Pos(-1, -1)};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 초기화
        S2D2 = new int[N + 1][N + 1];
        foods = new int[N + 1][N + 1];
        trees = new PriorityQueue<>();
        dead = new ArrayDeque<>();
        alive = new ArrayDeque<>();
        newTree = new ArrayDeque<>();

        for (int x = 1; x <= N; x++) {
            Arrays.fill(foods[x], 5);
        }

        for (int x = 1; x <= N; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 1; y <= N; y++) {
                S2D2[x][y] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            trees.offer(new Tree(new Pos(x, y), z));
        }

        while (K-- > 0) {
            spring();
            summer();
            fall();
            winter();
        }

        int ans = trees.size(); // 살아 있는 나무의 수
        System.out.println(ans);
    }

    static void spring() {
        while (!trees.isEmpty()) {
            Tree tree = trees.poll();
            int x = tree.pos.x;
            int y = tree.pos.y;
            if (foods[x][y] >= tree.age) {
                foods[x][y] -= tree.age++;
                alive.offer(tree);
            } else dead.offer(tree);
        }
        while (!alive.isEmpty()) {
            trees.offer(alive.poll());
        }
    }

    static void summer() {
        while (!dead.isEmpty()) {
            Tree tree = dead.poll();
            int x = tree.pos.x;
            int y = tree.pos.y;
            int age = tree.age;
            foods[x][y] += age / 2;
        }
    }

    static void fall() {
        while (!trees.isEmpty()) {
            Tree tree = trees.poll();
            int x = tree.pos.x;
            int y = tree.pos.y;
            if (tree.age % 5 == 0) {
                for (int d = 0; d < delta.length; d++) {
                    int nx = x + delta[d].x;
                    int ny = y + delta[d].y;
                    if (!isIn(nx, ny)) continue;
                    newTree.offer(new Tree(new Pos(nx, ny), 1));
                }
            }
            newTree.offer(tree);
        }
        while (!newTree.isEmpty()) {
            Tree tree = newTree.poll();
            trees.offer(tree);
        }
    }

    static void winter() {
        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= N; y++) {
                foods[x][y] += S2D2[x][y];
            }
        }
    }

    static boolean isIn(int x, int y) {
        return x >= 1 && x <= N && y >= 1 && y <= N;
    }
}