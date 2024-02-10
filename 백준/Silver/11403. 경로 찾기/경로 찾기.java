import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 요약
 * - 가중치 없는 방향 그래프 G가 있다.
 * - 모든 정점 (i, j)에 대해 i에서 j로 가는 길이가 양수인 경로가 있는지 없는지 구한다.
 *
 * 구현
 * - 모든 노드에 대해 각 노드 사이의 경로가 존재하는지 판단하는 문제이다.
 * - 노드의 개수 100개 이하이므로 O(N^3)인 플로이드 워셜 알고리즘을 사용한다.
 * - 최단 경로를 구하는 문제가 아니므로 연결이 되는지 안되는지만 판단하면 된다.
 *
 * 입력
 * - 정점의 개수 N [1, 100]
 * - 그래프의 인접 행렬이 주어진다.
 *
 * 출력
 * - i에서 j로 가는 길이가 양수인 경로가 있으면 1, 없으면 0으로 인접 행렬 형태로 출력한다.
 *
 * 시간복잡도
 * - O(N^3)
 *
 * 결과
 *
 * */

public class Main {
    static int N;
    static boolean[][] dists; // 최단 거리 인접 행렬

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        dists = new boolean[N][N]; // 0과 1만 존재하면 됨
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                dists[i][j] = st.nextToken().equals("1");
            }
        }

        floydWarshall();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(dists[i][j] ? 1 : 0).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void floydWarshall() {
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (dists[i][k] && dists[k][j]) { // 경로 (i, k)와 (k, j)가 존재하면 (i, j)도 존재하는 것
                        dists[i][j] = true;
                    }
                }
            }
        }
    }
}