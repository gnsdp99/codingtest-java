/*
 * [0][1]은 [1][0]을 무조건 뽑아야 하고, [1][0]은 [0][0]을 무조건 뽑아야 한다.
 * i = 0, j >= 2에서는 [1][j-1]과 [1][j-2], [0][j-2] 중 최댓값을 뽑아야 하고,
 * i = 1, j >= 2에서는 [0][j-1]과 [0][j-2], [1][j-2] 중 최댓값을 뽑아야 한다.
 * 이때 행 기준이 아닌 열 기준으로 탐색해야 함.
 *  */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P9465 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N, T;
        int[][] scores = new int[2][100000];
        T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            N = Integer.parseInt(br.readLine());
            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    scores[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            scores[0][1] += scores[1][0];
            scores[1][1] += scores[0][0];

            for (int i = 2; i < N; i++) {
                int tmp1 = Math.max(scores[0][i - 2], scores[1][i - 2]);
                int tmp2 = Math.max(tmp1, scores[0][i - 1]);
                int tmp3 = Math.max(tmp1, scores[1][i - 1]);

                scores[0][i] += tmp3;
                scores[1][i] += tmp2;
            }
            sb.append(Math.max(scores[0][N - 1], scores[1][N - 1])).append("\n");
        }
        System.out.println(sb);
    }
}