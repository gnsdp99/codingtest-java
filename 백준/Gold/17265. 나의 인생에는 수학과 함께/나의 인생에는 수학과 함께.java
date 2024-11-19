import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static char[][] board;
    static int[][] maxBoard;
    static int[][] minBoard;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        board = new char[N][N];
        maxBoard = new int[N][N];
        minBoard = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = st.nextToken().charAt(0);
            }
        }

        maxBoard[0][0] = minBoard[0][0] = board[0][0] - '0';
        findMinMax();

        sb.append(maxBoard[N - 1][N - 1]).append(" ").append(minBoard[N - 1][N - 1]);
        System.out.println(sb);
    }

    static void findMinMax() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if ((r + c) % 2 != 0) {
                    continue;
                }
                if (r == 0 && c == 0) {
                    continue;
                }

                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;

                int cur = board[r][c] - '0';
                if (r >= 1 && c >= 1) {
                    int tmp = minBoard[r - 1][c - 1];
                    min = Math.min(min, Math.min(calc(tmp, cur, board[r][c - 1]), calc(tmp, cur, board[r - 1][c])));

                    tmp = maxBoard[r - 1][c - 1];
                    max = Math.max(max, Math.max(calc(tmp, cur, board[r][c - 1]), calc(tmp, cur, board[r - 1][c])));
                }
                if (r >= 2) {
                    min = Math.min(min, calc(minBoard[r - 2][c], cur, board[r - 1][c]));
                    max = Math.max(max, calc(maxBoard[r - 2][c], cur, board[r - 1][c]));
                }
                if (c >= 2) {
                    min = Math.min(min, calc(minBoard[r][c - 2], cur, board[r][c - 1]));
                    max = Math.max(max, calc(maxBoard[r][c - 2], cur, board[r][c - 1]));
                }

                minBoard[r][c] = min;
                maxBoard[r][c] = max;
            }
        }
    }

    static int calc(int a, int b, char op) {
        if (op == '+') {
            return a + b;
        } else if (op == '-') {
            return a - b;
        } else {
            return a * b;
        }
    }
}