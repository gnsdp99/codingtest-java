import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * - 1행과 N행, 1열과 N열이 각각 이어져있음. (순환)
 * - K번 M개의 파이어볼을 이동시키면 최대 1,000 * 50^2 = 2,500,000
 *
 * 파이어볼을 관리할 자료구조
 * - 파이어볼 객체를 리스트로 관리하면 한 좌표에 있는 모든 파이어볼을 찾기 위해 M번 탐색해야 함.
 * - 2차원 배열의 각 좌표마다 파이어볼 객체를 연결리스트로 관리한다.
 * - 순서가 중요하지 않으므로 앞에서부터 삭제하면서 이동하면 된다.
 * - 새로 추가된 파이어볼을 중복해서 움직이지 않기 위해 이전 크기만큼만 이동시킨다.
 * */

public class Main {
    static class FireBall {
        int m, s, d;
        FireBall next;
        FireBall(int m, int s, int d) {
            this.m = m;
            this.s = s;
            this.d = d;
            next = null;
        }
    }
    static class FireBallList {
        int size;
        FireBall head, tail;
        FireBallList() {
            head = null;
            tail = null;
            size = 0;
        }

        void addFirst(FireBall fireBall) {
            if (head == null) {
                tail = fireBall;
            } else {
                fireBall.next = head;
            }
            head = fireBall;
            size++;
        }
        void addLast(FireBall fireball) { // 새로 추가되는 파이어볼들, 중복 이동을 막기 위해 size를 늘리지 않음
            if (head == null) {
                head = fireball;
            } else {
                tail.next = fireball;
            }
            tail = fireball;
        }
        void removeFirst() {
            if (head != null) {
                head = head.next;
            }
            if (head == null) {
                tail = null;
            }
            size--;
        }
        void removeAll() {
            if (head != null) {
                head = null;
                tail = null;
            }
            size = 0;
        }
    }
    static int N, M, K;
    static FireBallList[][] board;
    static int[][] delta = {
            {-1, 0},
            {-1, 1},
            {0, 1},
            {1, 1},
            {1, 0},
            {1, -1},
            {0, -1},
            {-1, -1}
    };
    static boolean[] isEven = {true, false, true, false, true, false, true, false};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new FireBallList[N + 1][N + 1];
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                board[r][c] = new FireBallList();
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            board[r][c].addFirst(new FireBall(m, s, d));
        }

        while (K-- > 0) {
            // 이동
            for (int r = 1; r <= N; r++) {
                for (int c = 1; c <= N; c++) {
                    int size = board[r][c].size;
                    if (size == 0) continue;

                    FireBall cur = board[r][c].head;
                    for (int i = 0; i < size; i++) {
                        int nr = (r - 1 + cur.s * delta[cur.d][0]) % N + 1; // 순환
                        int nc = (c - 1 + cur.s * delta[cur.d][1]) % N + 1;
                        if (nr <= 0) nr += N;
                        if (nc <= 0) nc += N;

                        board[nr][nc].addLast(new FireBall(cur.m, cur.s, cur.d));
                        cur = cur.next;
                        board[r][c].removeFirst();
                    }
                }
            }
            // 이동 후
            for (int r = 1; r <= N; r++) {
                for (int c = 1; c <= N; c++) {
                    int mass = 0, speed = 0, cnt = 0;
                    boolean isAllSame = true;
                    FireBall cur = board[r][c].head;
                    if (cur == null) continue; // 두 개 이상 있을 때만 진행
                    if (cur.next == null) {
                        board[r][c].size = 1;
                        continue;
                    }

                    boolean prev = isEven[cur.d];
                    for (; cur != null; cur = cur.next) {
                        mass += cur.m;
                        speed += cur.s;
                        cnt++;
                        if (isEven[cur.d] != prev) isAllSame = false;
                        else prev = isEven[cur.d];
                    }
                    board[r][c].removeAll(); // 파이어볼을 합친 후 기존 파이어볼 모두 삭제
                    mass = mass / 5;
                    if (mass == 0) continue; // 모두 사라짐
                    speed = speed / cnt;

                    int dir = isAllSame ? 0 : 1;
                    for (int i = 0; i < 4; i++) { // 나누어진 파이어볼 추가
                        board[r][c].addFirst(new FireBall(mass, speed, dir));
                        dir += 2;
                    }
                }
            }
        }
        // 남아있는 파이어볼 질량 합
        int ans = 0;
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                FireBall cur = board[r][c].head;
                if (cur == null) continue;
                for (; cur != null; cur = cur.next) {
                    ans += cur.m;
                }
            }
        }
        System.out.println(ans);
    }
}