import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김예훈
 * @date 24/02/16
 * @link https://www.acmicpc.net/problem/1987
 * @keyword_solution
 * 문제 요약
 * - RxC 크기의 보드가 있고 각 칸에 대문자 알파벳이 하나씩 적혀있고 (1, 1)에는 말이 있다.
 * - 말은 상하좌우 인접한 네 칸 중 하나로 이동 가능하지만 같은 알파벳을 두 번 지날 수는 없다.
 * - (1, 1)에서 시작해 말이 최대한 몇 칸 지날 수 있는지 구한다. (1, 1)포함.
 *
 * 구현
 * - 각 경로마다 자신이 지나온 알파벳과 방문 위치를 관리해야 하기 때문에 BFS로는 구현이 힘들다
 * - DFS와 백트래킹을 통해 경로 탐색을 해야 한다.
 * - 파라미터로 방문 위치와 방문 알파벳을 받는다.
 *
 * @input
 * - 맵의 크기 R, C [1, 20], 최대 20x20 (399개의 알파벳)
 *
 * @output
 * - 말이 지날 수 있는 최대 칸 수를 출력한다. (1, 1) 포함.
 *
 * @time_complex O(RC) 최대 모든 칸을 한 번씩 탐색해야 한다.
 * @perf
 */

public class Main {
    static int R, C, ans;
    static int[][] map; // 알파벳을 0 ~ 25의 숫자로 표현
    static int[] deltaX = {-1, 0, 1, 0};
    static int[] deltaY = {0, 1, 0, -1};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R + 1][C + 1];
        visited = new boolean[R + 1][C + 1];
        for (int i = 1; i <= R; i++) {
            char[] row = br.readLine().toCharArray();
            for (int j = 1; j <= row.length; j++) {
                map[i][j] = row[j - 1] - 'A';
            }
        }

        // (1, 1) 포함
        ans = 1;

        DFS(1, 1, (1 << map[1][1]), 1);

        System.out.println(ans);
    }

    static void DFS(int x, int y, int alphabetBit, int cnt) {
        for (int i = 0; i < 4; i++) {
            int nx = x + deltaX[i];
            int ny = y + deltaY[i];
            if (isIn(nx, ny) && (alphabetBit & (1 << map[nx][ny])) == 0) {
                visited[nx][ny] = true;
                DFS(nx, ny, alphabetBit | (1 << map[nx][ny]), cnt + 1);
                ans = ans < cnt + 1 ? cnt + 1 : ans;
                visited[nx][ny] = false;
            }
        }
    }

    static boolean isIn(int x, int y) {
        return x > 0 && x <= R && y > 0 && y <= C;
    }
}