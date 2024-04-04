import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N = 10;
    static int num, ans;
    static boolean[][] board;
    static int[] remain;
    static int[] paperSize;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        board = new boolean[N][N];
        remain = new int[6];
        paperSize = new int[6];
        for (int size = 1; size <= 5; size++) {
            paperSize[size] = size * size;
        }

        Arrays.fill(remain, 5);
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                board[r][c] = Integer.parseInt(st.nextToken()) == 1;
                if (board[r][c]) {
                    ++num;
                }
            }
        }

        if (num == 0) {
            System.out.println(0);
            return;
        } else if (num == 100) {
            System.out.println(4);
            return;
        }

        ans = 101;
        backtracking(0, 0, 0);
        System.out.println(ans < 101 ? ans : -1);
    }

    static void backtracking(int r, int c, int cnt) {

        if (num == 0) {
            if (ans > cnt) {
                ans = cnt;
            }
            return;
        }

        if (cnt >= ans) {
            return;
        }

        if (c >= N) {
            backtracking(r + 1, 0, cnt);
            return;
        }

        if (board[r][c]) {
            for (int size = 5; size >= 1; size--) {
                if (remain[size] == 0 || !canAttach(r, c, size)) {
                    continue;
                }
                set(r, c, size, false);
                --remain[size];
                num -= paperSize[size];
                backtracking(r, c + 1, cnt + 1);
                num += paperSize[size];
                ++remain[size];
                set(r, c, size, true);
            }
        } else {
            backtracking(r, c + 1, cnt);
        }
    }

    static void set(int r, int c, int size, boolean val) {
        for (int nr = r; nr < r + size; nr++) {
            for (int nc = c; nc < c + size; nc++) {
                board[nr][nc] = val;
            }
        }
    }

    static boolean canAttach(int r, int c, int size) {

        for (int nr = r; nr < r + size; nr++) {
            for (int nc = c; nc < c + size; nc++) {
                if (isNotIn(nr, nc) || !board[nr][nc]) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean isNotIn(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }
}