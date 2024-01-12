import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

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