import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String T = br.readLine();
        String P = br.readLine();

        int N = T.length();
        int M = P.length();

        int[] table = new int[M + 1];

        int i = 0;
        for (int j = 1; j < M; j++) {

            while (i > 0 && P.charAt(j) != P.charAt(i)) {
                i = table[i - 1];
            }

            if (P.charAt(j) == P.charAt(i)) {
                ++i;
                table[j] = i;
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();

        int begin = 0, matched = 0;
        while (begin <= N - M) {

            if (matched < M && T.charAt(begin + matched) == P.charAt(matched)) {
                ++matched;

                if (matched == M) {
                    ans.add(begin + 1);
                }

            } else {

                if (matched == 0) {

                    ++begin;

                } else {

                    begin += matched - table[matched - 1];
                    matched = table[matched - 1];
                }
            }
        }
        sb.append(ans.size()).append("\n");
        for (int num : ans) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }
}