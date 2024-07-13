import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static class Node {
        int r, c;
        Node next;

        Node(int r, int c, Node next) {
            this.r = r;
            this.c = c;
            this.next = next;
        }
    }

    static Node head, tail;

    static int N, K, L;
    static int[][] board; // 1: 사과, 2: 뱀
    static boolean[][] isSnake;
    static int[] rotateTime;
    static char[] rotateDir;

    static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        board = new int[N + 1][N + 1];
        isSnake = new boolean[N + 1][N + 1];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            board[r][c] = 1;
        }

        L = Integer.parseInt(br.readLine());
        rotateTime = new int[L];
        rotateDir = new char[L];
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            char C = st.nextToken().charAt(0);
            rotateTime[i] = X;
            rotateDir[i] = C;
        }

        int time = 0;
        int dir = 1;
        head = tail = new Node(1, 1, null);
        board[head.r][head.c] = 2;

        int rotateIdx = 0;
        while(true) {
            ++time;
            int newR = head.r + delta[dir][0];
            int newC = head.c + delta[dir][1];
            if (isOut(newR, newC) || board[newR][newC] == 2) {
                break;
            }
            head.next = new Node(newR, newC, null);
            if (board[newR][newC] != 1) {
                board[tail.r][tail.c] = 0;
                if (head.r != tail.r || head.c != tail.c) {
                    tail = tail.next;
                } else {
                    tail = head.next;
                }
            }
            head = head.next;
            board[head.r][head.c] = 2;

            if (rotateIdx < L && time == rotateTime[rotateIdx]) {
                if (rotateDir[rotateIdx] == 'D') {
                    dir = (dir + 1) % 4;
                } else if (rotateDir[rotateIdx] == 'L') {
                    dir = (dir + 3) % 4;
                }
                ++rotateIdx;
            }
        }
        System.out.println(time);
    }

    static boolean isOut(int r, int c) {
        return r < 1 || r > N || c < 1 || c > N;
    }
}