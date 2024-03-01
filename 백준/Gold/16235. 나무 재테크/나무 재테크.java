import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 처음에는 우선순위 큐를 사용해서 나무를 관리하려 했는데, 봄에 양분을 먹는 나무나 가을에 새로 태어난 나무를 관리하려면 새로운 큐를 계속 생성해야 함. 그래서 시간과 메모리 복잡도가 증가함.
 * 그래서 연결리스트로 좌표마다 나이별로 관리함.
 * 같은 좌표에 나무가 추가될 때는 무조건 나이가 1인 나무가 추가되기 때문에 리스트의 맨 앞에 추가하면 자동으로 나이순으로 정렬됨.
 *
 * */

public class Main {

    static class Tree {
        int age, cnt;
        Tree next, before;
        Tree(int age, int cnt) {
            this.age = age;
            this.cnt = cnt;
        }
    }

    static class TreeList {
        Tree head;
        TreeList() {
            head = new Tree(0, 0);
        }

        void addFirst(Tree newTree) {
            newTree.before = head;
            if (head.next != null) {
                newTree.next = head.next;
                head.next.before = newTree;
            }
            head.next = newTree;
        }
        void remove(Tree dead) {
            dead.before.next = null;
        }
    }
    static int N, M, K;
    static int[][] S2D2; // S2D2가 추가하는 양분
    static int[][] foods; // 현재 양분
    static TreeList[][] trees;
    static int[] deltaX = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] deltaY = {0, 1, 1, 1, 0, -1, -1, -1};

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
        trees = new TreeList[N + 1][N + 1];

        for (int x = 1; x <= N; x++) {
            Arrays.fill(foods[x], 5);
        }

        for (int x = 1; x <= N; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 1; y <= N; y++) {
                S2D2[x][y] = Integer.parseInt(st.nextToken());
                trees[x][y] = new TreeList();
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());
            trees[x][y].addFirst(new Tree(age, 1));
        }

        while (K-- > 0) {
            springAndSummer();
            fall();
            winter();
        }

        int ans = 0;
        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= N; y++) {
                Tree cur = trees[x][y].head.next;
                while (cur != null) {
                    ans += cur.cnt;
                    cur = cur.next;
                }
            }
        }
        System.out.println(ans);
    }

    static void springAndSummer() {
        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= N; y++) {
                Tree dead = null; // 이 나무 이후로 모두 죽음
                boolean canAlive = true; // 생존 가능 여부
                Tree cur = trees[x][y].head.next;

                while (cur != null) {
                    if (canAlive) {
                        int sum = cur.age * cur.cnt;
                        if (foods[x][y] > sum) { // 전부 먹음
                            foods[x][y] -= sum;
                            cur.age++;
                        } else { // 죽음
                            int num = foods[x][y] / cur.age;
                            foods[x][y] -= cur.age * num;
                            foods[x][y] += (cur.cnt - num) * (cur.age / 2);
                            if (num > 0) { // 다 죽지는 않음
                                cur.cnt = num;
                                cur.age++;
                                dead = cur.next;
                            } else dead = cur;
                            canAlive = false;
                        }
                    } else { // 다 죽음
                        foods[x][y] += cur.cnt * (cur.age / 2);
                    }
                    cur = cur.next;
                }

                // 나무 죽음
                if (dead != null) {
                    trees[x][y].remove(dead);
                }
            }
        }
    }

    static void fall() {
        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= N; y++) {
                Tree cur = trees[x][y].head.next;
                while (cur != null) {
                    if (cur.age % 5 == 0) {
                        for (int d = 0; d < deltaX.length; d++) {
                            int nx = x + deltaX[d];
                            int ny = y + deltaY[d];
                            if (!isIn(nx, ny)) continue;
                            // 새로운 나무는 무조건 나이가 1임
                            Tree newCur = trees[nx][ny].head.next;
                            if (newCur != null && newCur.age == 1) {
                                newCur.cnt += cur.cnt;
                            } else {
                                trees[nx][ny].addFirst(new Tree(1, cur.cnt));
                            }
                        }
                    }
                    cur = cur.next;
                }
            }
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