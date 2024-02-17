import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 요약
 * - NxN 크기의 종이에 각 1x1 정사각형에는 하얀색 or 파란색이 칠해져 있다.
 * - 규칙에 따라 종이를 자른다.
 * 규칙
 * 1. 종이 전체가 같은 색이 아니면 네 개의 N/2xN/2 크기로 나눈다.
 * 2. 나눈 종이 4개에 대해 각각 똑같은 과정을 거친다.
 * 3. 부분 종이 전체가 같은 색이거나 1x1이 될 때까지 반복한다.
 * - 최종적으로 잘라진 하얀색 종이와 파란색 종이의 개수를 구한다.
 *
 * 구현
 * 분할 정복
 * - 자른 부분 종이에 대해 더 잘라야 할 지 그만 잘라도 될 지 판단해야 한다.
 * - 기본적으로 모든 1x1 종이가 무슨 색인지 탐색해야 하므로 N*N번 연산해야 한다.
 * - 최악의 경우 모든 종이를 1x1까지 잘라야 한다.
 * - 그러면 2^0(N*N) + 2^2*(N/2)*(N/2) + 2^4(N/4)(N/4) + ... + 2^k = k * N^2
 *
 * 입력
 * - 종이의 크기 N (N = 2^k, 1 <= k <= 7) 즉 N은 2, 4, 8, 16, 32 , 64, 128 중 하나
 * - 0은 하얀색, 1은 파란색
 *
 * 출력
 * - 첫째 줄: 잘라진 하얀색 색종이의 개수 출력
 * - 둘째 줄: 잘라진 파란색 색종이의 개수 출력
 *
 * 시간복잡도 O(N^2)
 *
 * 결과
 *
 * */

public class Main {
    static int N, cntBlue, cntWhite;
    static int[][] map;
    static int[][] delta = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
    static final int X = 0, Y = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 입력
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // logic
        divide(0, 0, N);
        sb.append(cntWhite).append("\n").append(cntBlue);
        System.out.println(sb);
    }

    static void divide(int sx, int sy, int size) {
        int res = check(sx, sy, size);
        if (res == 0) {
            cntWhite += 1;
            return;
        }
        else if (res == 1) {
            cntBlue += 1;
            return;
        }

        int half = size / 2;
        for (int i = 0; i < 4; i++) {
            divide(sx + delta[i][X] * half, sy + delta[i][Y] * half, half);
        }
    }

    static int check(int sx, int sy, int size) { // 모두 같은 색인지 확인
        int color = map[sx][sy]; // 기준 색상
        for (int i = sx; i < sx + size; i++) {
            for (int j = sy; j < sy + size; j++) {
                if (map[i][j] != color) return -1; // 모두 같은 색 아님
            }
        }
        return color;
    }
}