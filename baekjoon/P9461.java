/*
1 1 1 2 2 3 4
1 + 1 = 2
1 + 1 = 2
1 + 2 = 3
2 + 2 = 4
2 + 3 = 5
dp[i] = dp[i - 2] + dp[i - 3]
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P9461 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        int N;

        long[] dp = new long[101];
        dp[1] = dp[2] = dp[3] = 1;
        for (int i = 4; i <= 100; i++) {
            dp[i] = dp[i - 2] + dp[i - 3];
        }

        for (int tc = 0; tc < T; tc++) {
            N = Integer.parseInt(br.readLine());
            sb.append(dp[N]).append('\n');
        }
        System.out.println(sb);
    }
}