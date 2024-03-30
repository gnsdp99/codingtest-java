import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String regex = "(100+1+|01)+";

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {

            String text = br.readLine();

            sb.append(text.matches(regex) ? "YES" : "NO").append("\n");
        }
        System.out.println(sb);
    }
}