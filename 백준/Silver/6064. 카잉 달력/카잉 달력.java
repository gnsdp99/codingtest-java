import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {

            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if (x == y) {
                sb.append(x).append("\n");
                continue;
            } else if (M == N) {
                sb.append(-1).append("\n");
                continue;
            }

            int minMN = M > N ? N : M;
            int maxMN = minMN == N ? M : N;
            int minXY = M > N ? y : x;
            int maxXY = minXY == y ? x : y;

            if (M == x && N == y) {
                sb.append(lcm(minXY, maxXY)).append("\n");
            } else {
                int cur = minXY;
                boolean isPossible = false;
                for (int i = 1; i <= maxMN; i++) {
                    cur = (cur + minMN) % maxMN;
                    if (cur == 0) {
                        cur = maxMN;
                    }
                    if (cur == maxXY) {
                        sb.append(i * minMN + minXY).append("\n");
                        isPossible = true;
                        break;
                    }
                }
                if (!isPossible) {
                    sb.append(-1).append("\n");
                }
            }
        }
        System.out.println(sb);
    }

    static int lcm(int small, int big) {

        if (big % small == 0) {
            return big;
        }

        int com = findCommon(small, big);
        return com != -1 ? com * (small / com) * (big / com) : small * big;
    }

    static int findCommon(int small, int big) {

        for (int i = 2; i <= Math.sqrt(small); i++) {
            if (small % i == 0 && big % i == 0) {
                return i;
            }
        }
        return -1;
    }
}