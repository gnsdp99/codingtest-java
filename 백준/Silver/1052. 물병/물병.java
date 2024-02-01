import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김예훈
 * @date 24/02/01
 * @link https://www.acmicpc.net/problem/1052
 * @keyword_solution
 * - 같은 양의 물이 들어있는 물병만 합칠 수 있다.
 * - N개의 물 병을 K개로 합쳐야 한다.
 * - 물의 양은 2의 제곱수로만 존재함. -> 비트연산 가능
 *
 * - 우선 처음 주어지는 물병으로 비트열을 생성한다. (같은 양의 물병이 없도록)
 * 10 = 1010
 * 15 = 1111
 *
 * - 비트의 개수가 k개 이하가 될 때까지 물병을 새로 사서 연산한다.
 * - 비트가 1인 최상단 비트로부터 k-1번 이하 비트들을 k번 비트로 만들면 된다.
 * - 1번에 1을 더하는 연산을 반복해야 한다.
 * >> 1인 비트 중 가장 최하위 비트를 똑같이 더해주면 연산 횟수를 줄일 수 있다.
 * >>>> (Tip!) 비트열의 음수와 AND 연산하면 해당 비트를 구할 수 있다.
 *
 * @input
 * - N <= 10^7
 * - K <= 1000
 * @output
 * - 상점에서 사야하는 물병의 최솟값 출력.
 * - 만들 수 없는 경우는 -1을 출력.
 * @time_complex O(N)
 * @perf
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // logic
        if (N <= K) {
            System.out.println(0);
            return;
        }

        int bottle = N;
        int ans = 0;
        while (true) {
            if (Integer.bitCount(bottle) <= K) break;

            int i = 0;
            while ((bottle & (1 << i)) == 0) {
                i++;
            }
            bottle += (1 << i);
            ans += (1 << i);
        }
        System.out.println(ans);
    }
}