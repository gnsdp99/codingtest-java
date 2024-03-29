import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 김예훈
 * @date 24/02/22 
 * @link https://www.acmicpc.net/problem/17471
 * @keyword_solution
 * - DFS
 * - 하나의 노드를 시작점으로 정하고 DFS를 한다.
 * - 탐색할 때마다 반대편 선거구의 인구수와 우리 선거구의 인구수를 계산한다.
 * - 만약 반대편의 선거구가 연결이 안되어있으면 계산하지 않는다.
 * - 우리 선거구에 포함된 도시들을 비트마스킹으로 표현하고 계산한 인구차를 배열에 저장해 중복 계산을 방지한다. (123 선택하고 반대편이 456일 때 계산하면 우리가 456일 때 다시 계산하지 않아도 된다.)
 *
 * @input
 * - 도시의 수 N [2, 10]
 * - 각 도시의 인구수 [1, 100]
 *
 * @output
 * - 두 선거구의 인구수 차의 최솟값을 출력
 * - 구할 수 없으면 -1 출력
 *
 * @time_complex O(2^N)
 * @perf 12420kb, 88ms
 */

public class Main {

    static int N, totalPops, ans;
    static int[] populations;
    static boolean[][] adjMatrix; // 인접행렬

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 도시의 수
        populations = new int[N]; // 도시 별 인구수
        adjMatrix = new boolean[N][N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            populations[i] = Integer.parseInt(st.nextToken());
            totalPops += populations[i]; // 전체 인구수
        }
        ans = totalPops;

        for (int src = 0; src < N; src++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            for (int j = 0; j < num; j++) {
                int dst = Integer.parseInt(st.nextToken());
                adjMatrix[src][dst - 1] = true;
            }
        }

        getPowerSet();
        System.out.println(ans < totalPops ? ans : -1);
    }

    static void getPowerSet() {
        for (int selectBit = 0; selectBit <= (1 << N - 1); selectBit++) {
            // selectBit: 선택한 선거구 비트
            int nonSelectBit = ~selectBit + (1 << N); // 상대 선거구 비트
            if (isConnected(selectBit) && isConnected(nonSelectBit)) {
                int sumPops = getSumPops(selectBit);
                int sub = Math.abs(sumPops - (totalPops - sumPops));
                ans = ans > sub ? sub : ans;
            }
        }
    }

    static int getSumPops(int selectBit) {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            if ((selectBit & (1 << i)) != 0) sum += populations[i];
        }
        return sum;
    }

    static boolean isConnected(int bits) {
        if (bits == 0) return false;

        int start = 0;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if ((bits & (1 << i)) != 0) {
                start = i;
                cnt++;
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[10];
        queue.offer(start);
        visited[start] = true;
        while (!queue.isEmpty() && cnt > 0) {
            int cur = queue.poll();
            cnt--;
            for (int dst = 0; dst < N; dst++) {
                if (adjMatrix[cur][dst] && !visited[dst] && ((bits & (1 << dst)) != 0)) {
                    queue.offer(dst);
                    visited[dst] = true;
                }
            }
        }
        return cnt == 0;
    }
}