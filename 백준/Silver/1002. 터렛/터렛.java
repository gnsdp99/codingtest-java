import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int r1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());

            double d = dist(x1, y1, x2, y2);
            if (x1 == x2 && y1 == y2 && r1 == r2) {
                sb.append(-1).append("\n");
            } else if (d < Math.abs(r1 - r2) || d > r1 + r2) {
                sb.append(0).append("\n");
            } else if (d == r1 + r2 || d == Math.abs(r1 - r2)) {
                sb.append(1).append("\n");
            } else {
                sb.append(2).append("\n");
            }
        }
        System.out.println(sb);
    }

    static double dist(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
}