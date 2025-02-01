import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());

        int[] arr = {1, 7, 4, 2, 0, 8};
        long[] dp = new long[101];
        Arrays.fill(dp, Long.MAX_VALUE);
        dp[2] = 1;
        dp[3] = 7;
        dp[4] = 4;
        dp[5] = 2;
        dp[6] = 6;
        dp[7] = 8;
        dp[8] = 10;

        for (int i = 9; i <= 100; i++) {
            for (int j = 2; j <= 7; j++) {
                String str = String.valueOf(dp[i - j]) + arr[j - 2];
                dp[i] = Math.min(dp[i], Long.parseLong(str));
            }
        }

        while (TC-- > 0) {
            int num = Integer.parseInt(br.readLine());
            sb.append(dp[num]).append(" ");
            if (num % 2 == 0) {
                sb.append(toMax(num >> 1));
            } else {
                sb.append("7").append(toMax((num - 3) >> 1));
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static String toMax(int num) {
        return "1".repeat(num);
    }
}