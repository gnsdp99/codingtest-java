/*
* 이전 계단의 최대 점수가 몇 개의 연속된 계단을 밟아서 나온지 알 수 없다. 따라서 2차원 배열을 만들어 1번째 전 계단을 밟았을 때와 2번째 전 계단을 밟았을 때의 경우를 나눠서 저장해야 한다.
* 1번째 전 계단을 밟으려면 1번째 전 계단은 반드시 2번째 전 계단을 밟았어야 한다. 따라서 dp[i][0] = dp[i-1][1] + scores[i]이다.
* 2번째 전 계단을 밟으려면 2번째 전 계단은 어떤 계단을 밟았든 상관없다. 따라서 dp[i][1] = max(dp[i-2][0], dp[i-2][1]) + scores[i]이다.
* */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P2579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] scores = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            scores[i] = Integer.parseInt(br.readLine());
        }
        if (N == 1) {
            System.out.println(scores[1]);
        } else if (N == 2) {
            System.out.println(scores[1] + scores[2]);
        } else if (N == 3) {
            System.out.println(Math.max(scores[1], scores[2]) + scores[3]);
        } else {
            int[][] dp = new int[N + 1][2];
            dp[1][0] = dp[1][1] = scores[1];
            for (int i = 2; i <= N; i++) {
                dp[i][0] = dp[i - 1][1] + scores[i];
                dp[i][1] = Math.max(dp[i - 2][0], dp[i - 2][1]) + scores[i];
            }
            System.out.println(Math.max(dp[N][0], dp[N][1]));
        }
    }
}