import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb;
    static int[] lottery;
    static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int prev = 0;

        while (true) {
            sb = new StringBuilder();
            st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            if (k == 0) {
                break;
            }
            if (prev < k) {
                lottery = new int[k];
            }
            for (int i = 0; i < k; i++) {
                lottery[i] = Integer.parseInt(st.nextToken());
            }

            // logic
            combination(new int[6], 0, 0);
            System.out.println(sb);
            prev = k;
        }
    }

    static void combination(int[] selected, int numSelect, int nextIdx) {
        if (numSelect >= 6) {
            for (int num : selected) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = nextIdx; i < k; i++) {
            selected[numSelect] = lottery[i];
            combination(selected, numSelect + 1, i + 1);
            selected[numSelect] = 0;
        }
    }
}