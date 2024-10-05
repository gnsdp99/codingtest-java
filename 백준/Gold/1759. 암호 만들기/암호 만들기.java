import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {

    static int L, C;
    static char[] alphabet;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        alphabet = new char[C];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            alphabet[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(alphabet);

        find(new char[L], 0, 0, 0, 0);
        System.out.println(sb);
    }

    static void find(char[] password, int idx, int start, int cntVowel, int cntConsonant) {
        if (idx == L) {
            if (cntVowel >= 1 && cntConsonant >= 2) {
                sb.append(String.valueOf(password)).append("\n");
            }
            return;
        }

        for (int i = start; i <= C - L + idx; i++) {
            password[idx] = alphabet[i];
            if (isVowel(alphabet[i])) {
                find(password, idx + 1, i + 1, cntVowel + 1, cntConsonant);
            } else {
                find(password, idx + 1, i + 1, cntVowel, cntConsonant + 1);
            }
        }
    }

    static boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }
}