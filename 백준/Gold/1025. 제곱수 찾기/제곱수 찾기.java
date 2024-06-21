import java.io.*;
import java.util.*;

class Main {

    static int N, M, ans = -1;
    static int[][] matrix;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        matrix = new int[N][M];

        for (int i = 0; i < N; i++) {
            String row = br.readLine();
            for (int j = 0; j < M; j++) {
                matrix[i][j] = row.charAt(j) - '0';
            }
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                for (int dr = -N; dr < N; dr++) {
                    for (int dc = -M; dc < M; dc++) {
                        if (dr == 0 && dc == 0) {
                            continue;
                        }

                        int curR = r;
                        int curC = c;
                        int curNum = 0;
                        while (curR >= 0 && curR < N && curC >= 0 & curC < M) {
                            curNum = curNum * 10 + matrix[curR][curC];
                            int sqrt = (int)Math.sqrt(curNum);
                            if (sqrt * sqrt == curNum) {
                                ans = Math.max(ans, curNum);
                            }
                            curR += dr;
                            curC += dc;
                        }
                    }
                }
            }
        }
        System.out.println(ans);
    }
}