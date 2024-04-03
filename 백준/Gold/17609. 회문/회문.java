import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            char[] str = br.readLine().toCharArray();

            // left를 지울 때
            int res1 = check(str, true);
            // right를 지울 때
            int res2 = check(str, false);
            sb.append(res1 <= res2 ? res1 : res2).append("\n");
        }
        System.out.println(sb);
    }

    static int check(char[] str, boolean isLeft) {
        // two pointer
        int l = 0, r = str.length - 1;
        int cnt = 0;
        while (l <= r) {
            if (str[l] == str[r]) {
                ++l;
                --r;
            } else {
                if (isLeft) {
                    ++l;
                } else {
                    --r;
                }
                if (++cnt > 1) {
                    return 2;
                }
            }
        }
        if (cnt == 1) {
            return 1;
        }
        return 0;
    }
}