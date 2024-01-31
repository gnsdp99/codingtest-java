import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김예훈
 * @date 24/01/31
 * @link https://www.acmicpc.net/problem/2961
 * @keyword_solution
 * - 음식의 신맛은 재료의 신맛의 곱
 * - 음식의 쓴맛은 재료의 쓴맛의 합
 * - 음식의 신맛과 쓴맛의 차이를 최대한 작게 만들어야 한다.
 * - 재료는 하나 이상 사용해야 한다.
 * - N개의 재료가 주어졌을 때 신맛과 쓴맛의 차이를 가장 작게 하는 조합을 찾아야 한다.
 *
 * 아이디어 1 - 백트래킹
 * 아이디어 2 - 비트마스킹
 * - 부분 수열을 백트래킹이 아닌 비트마스킹으로 구하는 방법이다.
 * - 각 재료를 0번 ~ N - 1번 비트로 대응 시킨다.
 * - k번째 재료가 포함되면 k번 비트를 1로, 포함하지 않으면 0으로 설정한다.
 * - 부분 수열을 구한 다음에는 0번 ~ N - 1번 비트가 각각 포함되었는지 비교하며 신맛과 쓴맛의 차이를 구한다.
 * - 부분 수열의 개수는 2^N개, 즉 (1 << N)이다.
 * - 이때 1 ~ (1 << N) - 1은 N개의 재료에 대한 모든 부분 수열을 나타낸다.
 *
 * - 신 맛은 곱셈 연산을 반복하므로 처음에 1로 초기화해야 한다.
 *
 * @input
 * - 재료의 개수 N은 [1, 10]
 * >> 백트래킹으로 모든 부분 수열을 구해도 O(N^2)이므로 가능할 것 같다.
 * - 요리의 신맛과 쓴맛은 항상 1_000_000_000보다 작은 양의 정수이다.
 * @output
 * - 신맛과 쓴맛의 차이의 최솟값을 출력한다.
 * @time_complex 
 * @perf
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] sours = new int[N], bitters = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            sours[i] = Integer.parseInt(st.nextToken());
            bitters[i] = Integer.parseInt(st.nextToken());
        }

        int minSub = Integer.MAX_VALUE;
        // logic
        for (int i = 1; i < (1 << N); i++) { // 모든 부분 수열을 비트로 나타냄
            int sour = 1, bitter = 0;
            for (int j = 0; j < N; j++) { // 각 부분 수열에 포함된 재료를 찾음
                if ((i & (1 << j)) == 0) { // 존재하지 않음
                    continue;
                }
                sour *= sours[j];
                bitter += bitters[j];
            }
            minSub = Math.min(minSub, Math.abs(sour - bitter));
        }
        System.out.println(minSub);
    }
}