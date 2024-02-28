import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 구현
 * - 완전 탐색 시 O(N^2). 가능은 하지만 비효율적
 * - D(i): i번째 와인까지 마셨을 때의 최대 양
 * - D[i] = max(D[i - 1], D[i - 2] + W[i], D[i - 3] + W[i - 1] + W[i])
 *
 * 입력
 * - 포도주 잔의 수 N [1, 10,000]
 * - 포도주 양 [0, 1,000]
 *
 * 출력
 * - 마실 수 있는 최대 포도주 양 출력
 *
 * 시간복잡도 O(N)
 *
 * 결과
 *
 * */
public class Main {

    static int N;
    static int[] wine, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        wine = new int[N + 1];
        dp = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            wine[i] = Integer.parseInt(br.readLine());
        }
        if (N == 1) {
            System.out.println(wine[1]);
            return;
        }

        dp[2] = (dp[1] = wine[1]) + wine[2];

        for (int i = 3; i <= N; i++) {
            dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 3] + wine[i - 1] + wine[i], dp[i - 2] + wine[i]));
        }
        System.out.println(dp[N]);
    }
}