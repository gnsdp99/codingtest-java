import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author 김예훈
 * @date 24/01/31
 * @link https://www.acmicpc.net/problem/1941
 * @keyword_solution
 * - 규칙
 * 1. 7명의 학생으로만 구성
 * 2. 7명의 자리는 서로 가로 or 세로로 인접해야 함.
 * 3. '이다솜파'가 아니어도 되지만, 최소 4명 이상은 있어야 함.
 *
 * - 우선 25명 중 7명을 뽑는 조합을 구한다.
 * >> 2차원 배열로는 어려우므로 1차원 배열로 늘려서 구한다.
 * >> 각 요소에 임의로 0번부터 24번까지의 번호를 붙여 구분한다.
 * >> arr[row][col]의 임의의 번호는 5 * row + col이다.
 * - 구한 조합 중 서로 연결된 것인지, 이다솜파가 4명 이상 존재하는 지 확인한다.
 * >> 서로 연결되었는 지는 BFS를 활용한다.
 * >> 4방 탐색할 때 앞서 변환한 0 ~ 24의 번호를 기준으로 하지 않도록 주의한다. (ex. 4번과 5번은 이어져 있지 않음)
 *
 * @input
 * S가 '이다솜파'
 * Y가 '임도연파'
 * String형은 기본이 24바이트이므로 char형으로 변환한다.
 * 학생들의 자리는 5x5 행렬.
 * @output
 * 규칙에 맞게 칠공주를 이룰 수 있는 모든 경우의 수 출력.
 * @time_complex
 * @perf
 */

public class Main {

    static char[][] board = new char[5][5];

    static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    static int ans = 0;

    static boolean[] selected = new boolean[25];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 5; i++) {
            String line = br.readLine();
            for (int j = 0; j < 5; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        // logic
		makeCombination(0, 0, 0);
        System.out.println(ans);
    }

    static void makeCombination(int nth, int start, int numLDY) {
        if (numLDY >= 4) {
            return;
        }

        if (nth >= 7) {
            // 조합 검증 (연결된 좌표들인지)
            if (isContinuous()) {
                ans++;
            }
            return;
        }

        for (int i = start; i < 25; i++) {
            int r = i / 5, c = i % 5;
            selected[i] = true;
            makeCombination(nth + 1, i + 1, numLDY + (board[r][c] == 'Y' ? 1 : 0));
            selected[i] = false;
        }
    }

    static boolean isContinuous() {
        boolean[] visited = new boolean[25];
        int start = -1;
        for (int i = 0; i <= 24; i++) {
            if (selected[i]) {
                start = i;
                break;
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start] = true;
        int cnt = 1;

        while (!queue.isEmpty()) {
            int num = queue.poll();
            for (int[] dir : delta) {
                int nr = num / 5 + dir[0];
                int nc = num % 5 + dir[1];
                if (!isIn(nr, nc)) continue;

                int newNum = 5 * nr + nc;
                if (!visited[newNum] && selected[newNum]) {
                    queue.offer(newNum);
                    visited[newNum] = true;
                    cnt++;
                }
            }
        }
        return cnt == 7;
    }

    static boolean isIn(int r, int c) {
        return r >= 0 && r < 5 && c >= 0 && c < 5;
    }
}