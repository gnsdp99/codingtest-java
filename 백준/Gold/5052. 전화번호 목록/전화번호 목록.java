import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());

            ArrayList<String> numbers = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                numbers.add(br.readLine().replace(" ", ""));
            }
            Collections.sort(numbers);

            String ans = "YES";
            for (int i = 0; i < N - 1; i++) {
                if (numbers.get(i + 1).startsWith(numbers.get(i))) {
                    ans = "NO";
                    break;
                }
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
}