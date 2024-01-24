/*
* 1개 = P1
* 2개 = D1+D1, P2
* 3개 = D2+D1, P3
* 4개 = D3+D1, D2+D2, P4
* 5개 = D4+D1, D3+D2, P5
* 6개 = D5+D1, D4+D2, D3+D3, P6
* dp[k / 2] 까지 비교
* */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] prices = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            prices[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            dp[i] = prices[i];
            for (int j = 1; j <= i / 2; j++) {
                dp[i] = Math.max(dp[j] + dp[i - j], dp[i]);
            }
        }
        System.out.println(dp[N]);
    }
}
