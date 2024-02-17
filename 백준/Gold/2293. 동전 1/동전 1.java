import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제 요약
 * - N가지 종류의 동전이 있다.
 * - 동전을 이용해 합이 k원이 되는 경우의 수를 구한다.
 * - 순서는 고려하지 않는다.
 *
 * 구현
 * DP
 * - 동전을 오름차순으로 정렬한다.
 * - 가장 작은 동전이 k보다 크면 경우의 수는 0이다.
 * - 가장 작은 단위의 동전부터 1 ~ k까지 경우의 수를 채운다.
 * - 1 ~ x번째 단위의 동전으로 y를 만드는 경우의 수는 1 ~ x-1번째 단위의 동전으로 y를 만드는 경우의 수 + x번째 동전으로 y - x번째 동전을 만드는 경우의 수
 * - dp[coins[i]][j] = dp[coins[i - 1]][j] + dp[i][j - coins[i]]
 *
 * 입력
 * - 동전의 종류 N [1, 100]
 * - 목표 k [1, 10,000]
 * - 각 동전의 가치는 [1, 100,000]
 *
 * 출력
 * - k를 만들 수 있는 경우의 수를 출력한다. [0, 2^31-1] -> int형
 *
 * 시간복잡도 O(NK)
 *
 * 결과
 *
 * */

public class Main {

    static int N, K;
    static int[] coins, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        coins = new int[N];
        dp = new int[K + 1];
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(coins);
        if (coins[0] > K) {
            System.out.println(0);
            return;
        } else if (coins[0] == K) {
            System.out.println(1);
            return;
        }

        // 1번째 동전으로 만들 수 있는 경우의 수
        for (int k = 1; k <= K; k++) {
            dp[k] = k % coins[0] == 0 ? 1 : 0;
        }
        // 2번째 동전부터 1 ~ x번째 동전으로 만들 수 있는 경우의 수
        for (int i = 1; i < N; i++) {
            if (coins[i] > K) break;
            dp[coins[i]] += 1;
            for (int k = coins[i] + 1; k <= K; k++) {
                dp[k] += dp[k - coins[i]];
            }
        }
        System.out.println(dp[K]);
    }
}