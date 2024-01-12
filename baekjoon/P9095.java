/*
* 4의 경우 1에 3을 더하는 경우 + 2에 2를 더하는 경우 + 3에 1을 더하는 경우이므로, dp[4] = dp[3] + dp[2] + dp[1]
* 5의 경우 2에 3을 더하는 경우 + 3에 2를 더하는 경우 + 4에 1을 더하는 경우이므로, dp[5] = dp[4] + dp[3] + dp[2]
* 따라서 n >= 4에서 dp[n] = dp[n - 1] + dp[n - 2] + dp[n - 3]
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P9095 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        int n;
        int[] base = {0, 1, 2, 4};
        for (int tc = 0; tc < T; tc++) {
            n = Integer.parseInt(br.readLine());
            if (n <= 3) {
                sb.append(base[n]);
                sb.append("\n");
                continue;
            }
            int[] dp = new int[n + 1];
            dp[1] = 1;
            dp[2] = 2;
            dp[3] = 4;
            for (int i = 4; i <= n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
            }
            sb.append(dp[n]);
            sb.append("\n");
        }
        System.out.println(sb);
    }
}