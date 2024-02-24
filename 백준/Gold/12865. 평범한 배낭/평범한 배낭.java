import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 구현
 * - 0-1 knapsack 문제
 * - 1차원 배열의 dp로 해결
 *
 * 입력
 * - 물건의 수 N [1, 100]
 * - 최대 무게 K [1, 100,000]
 * - 각 물건의 무게 W [1, 100,000]
 * - 각 물건의 가치 V [0, 1,000]
 *
 * 출력
 * - 넣을 수 있는 물건 가치 합의 최댓값을 출력
 *
 * 시간복잡도 O(N * K)
 *
 * 결과
 *
 * */
public class Main {
    static int N, K, W, V;
    static int[] bag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        bag = new int[K + 1];

        for (int i = 1; i <= N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            V = Integer.parseInt(st.nextToken());

            for (int k = K; k >= W; k--) {
                if (bag[k] < bag[k - W] + V) bag[k] = bag[k - W] + V;
            }
        }
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        if (K >= W && bag[K] < bag[K - W] + V) bag[K] = bag[K - W] + V;

        System.out.println(bag[K]);
    }
}