import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 김예훈
 * @date 24/02/14
 * @link https://www.acmicpc.net/problem/6987
 * @keyword_solution
 * 문제 요약
 * - 6개의 팀이 있고, 한 팀당 5번의 경기를 치른다.
 * - 리그가 끝난 후 주어진 각 나라의 승, 무, 패 경우의 수가 가능한 것인지 판별해야 한다.
 *
 * 구현
 * 완전탐색 O(3^15)
 * - 한 팀당 5팀과 한 번씩 경기한다. (총 15경기)
 * - 각 팀별 경기 결과를 모두 조합한다. (승, 무, 패 중 1)
 *
 * 백트래킹
 * - 조합을 구하는 도중 정답임을 발견하면 가지치기 할 수 있다.
 * - 모든 승,무,패의 합이 30이어야 함.
 *
 * @input
 * - 6개국의 승, 무, 패 총 18개의 입력이 주어진다.
 * - 승, 무, 패 [0, 6]
 *
 * @output
 * - 가능하면 1, 불가능하면 0을 한 줄에 출력한다.
 *
 * @time_complex
 * @perf
 */

public class Main {

    static final int W = 0, D = 1, L = 2; // win, draw, lose
    static int[][] table = new int[6][3]; // 경기 결과
    static int[][] matches; // 경기 일정 (15번)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        // 경기 일정
        matches = new int[15][2];
        int idx = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = i + 1; j < 6; j++) {
                matches[idx][0] = i;
                matches[idx++][1] = j;
            }
        }

        for (int tc = 1; tc <= 4; tc++) {
            int total = 0;
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 3; j++) {
                    table[i][j] = Integer.parseInt(st.nextToken());
                    total += table[i][j];
                }
            }
            if (total != 30) sb.append(0).append(" ");
            else sb.append(worldCup(0) ? 1 : 0).append(" ");
        }
        System.out.println(sb);
    }

    static boolean worldCup(int matchIdx) {
        if (matchIdx == 15) return true; // 경기 결과가 모두 맞음

        int team1 = matches[matchIdx][0];
        int team2 = matches[matchIdx][1];

        for (int i = 0; i < 3; i++) {
            int j = i == W ? L : i == L ? W : D;
            if (table[team1][i] > 0 && table[team2][j] > 0) {
                table[team1][i]--;
                table[team2][j]--;
                if (worldCup(matchIdx + 1)) return true;
                table[team1][i]++;
                table[team2][j]++;
            }
        }
        return false; // 경기 결과가 틀림
    }
}