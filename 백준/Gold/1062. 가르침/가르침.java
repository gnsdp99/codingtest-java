import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K, ans;
    static List<Integer> wordsBit = new ArrayList<>();
    static int known = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        String init = "acint";
        for (int i = 0; i < init.length(); i++) {
            known |= (1 << (init.charAt(i) - 'a'));
        }

        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            int bit = known;
            for (int j = 4; j < word.length() - 4; j++) {
                char ch = word.charAt(j);
                bit |= (1 << (ch - 'a'));
            }
            wordsBit.add(bit);
        }

        if (K < 5) {
            System.out.println(0);
            return;
        } else if (K == 26) {
            System.out.println(N);
            return;
        }

        K -= 5;

        backtracking(0, 0, known);
        System.out.println(ans);
    }

    static void backtracking(int depth, int start, int known) {

        if (depth == K) {
            int cnt = 0;
            for (int bit : wordsBit) {
                if ((known | bit) == known) {
                    ++cnt;
                }
            }
            if (ans < cnt) {
                ans = cnt;
            }
            return;
        }

        for (int i = start; i < 26; i++) {
            if ((known & (1 << i)) != 0) {
                continue;
            }
            backtracking(depth + 1, i + 1, known | (1 << i));
        }
    }
}