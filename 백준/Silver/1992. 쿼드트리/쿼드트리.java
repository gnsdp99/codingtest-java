import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 요약
 * - 2차원 배열로 이루어진 영상에 흰 점과 검은 점이 있고 이를 쿼드 트리로 만든다.
 * - 주어진 영상이 모두 0으로만 되어 있으면 압축 결과는 0, 모두 1로만 되어 있으면 결과는 1이 된다.
 * - 0과 1이 섞여 있으면 4개의 영상으로 나누어 다시 압축한다.
 * - 4개의 영상의 압축 결과를 괄호 안에 차례대로 표현한다.
 *
 * 구현
 * 분할 정복
 * - N^2의 탐색을 최대 logN번 진행한다.
 * - 부분 영상이 전부 0 또는 1이 될 때까지 4등분한다.
 *
 * 입력
 * - 영상의 크기 N [1, 64] 인 2의 제곱수, 즉 1, 2, 4, 8, 16, 32, 64 중 하나
 *
 * 출력
 * - 영상의 압축 결과를 괄호로 묶어 출력한다.
 *
 * 시간복잡도 O(N^2 * logN)
 *
 * 결과
 *
 * */
public class Main {
    static int N;
    static int[][] map;
    static int[][] delta = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
    static final int X = 0, Y = 1;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        // 입력
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            char[] row = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                map[i][j] = row[j] - '0';
            }
        }

        divide(0, 0, N);
        System.out.println(sb);
    }

    static void divide(int sx, int sy, int size) {
        int res = check(sx, sy, size);
        if (res != -1) {
            sb.append(res == 0 ? 0 : 1);
            return;
        }

        sb.append("(");
        int half = size / 2;
        for (int i = 0; i < 4; i++) {
            divide(sx + delta[i][X] * half, sy + delta[i][Y] * half, half);
        }
        sb.append(")");
    }

    static int check(int sx, int sy, int size) {
        int color = map[sx][sy];
        for (int i = sx; i < sx + size; i++) {
            for (int j = sy; j < sy + size; j++) {
                if (map[i][j] != color) return -1;
            }
        }
        return color;
    }
}