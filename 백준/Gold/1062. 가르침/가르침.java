import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K, ans;
    static List<Set<Character>> words = new ArrayList<>();
    static boolean[] known = new boolean[26];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            Set<Character> set = new HashSet<>();
            for (int j = 0; j < word.length(); j++) {
                set.add(word.charAt(j));
            }
            words.add(set);
        }

        if (K < 5) {
            System.out.println(0);
            return;
        }

        K -= 5;
        known['a' - 'a'] = true;
        known['c' - 'a'] = true;
        known['i' - 'a'] = true;
        known['n' - 'a'] = true;
        known['t' - 'a'] = true;

        backtracking(0, 1);
        System.out.println(ans);
    }

    static void backtracking(int kth, int start) {

        if (kth == K) {
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                boolean can = true;
                for (char ch : words.get(i)) {
                    if (!known[ch - 'a']) {
                        can = false;
                        break;
                    }
                }
                if (can) {
                    ++cnt;
                }
            }
            if (ans < cnt) {
                ans = cnt;
            }
            return;
        }

        for (int i = start; i < 26; i++) {
            if (known[i]) {
                continue;
            }
            known[i] = true;
            backtracking(kth + 1, i + 1);
            known[i] = false;
        }
    }
}