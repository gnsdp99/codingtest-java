import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 구현
 * - 매번 N번 탐색을 하면 O(NM)이 되어 시간초과가 된다.
 * - 따라서 DP를 활용해 한 번만 테이블을 만든다.
 * - D(i, j): i ~ j 수의 팰린드롬 여부
 * - D[i, j] = if(D[i + 1, j - 1] and (S[i] == S[j])) T else F
 *
 * 입력
 * - 수열의 크기 N [1, 2,000]
 * - 칠판에 적은 수 [1, 100,000]
 * - 질문의 개수 M [1, 1,000,000]
 *
 * 출력
 * - 질문에 대한 답이 팰린드롬이면 1, 아니면 0 출력
 *
 * 시간복잡도 O(N^2 + M)
 *
 * 결과
 *
 * */
public class Main {
    static int N, M;
    static int[] seq;
    static boolean[][] palindrome;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        seq = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        setPalindrome();

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            sb.append(palindrome[S][E] ? 1 : 0).append("\n");
        }
        System.out.println(sb);
    }

    static void setPalindrome() {
        palindrome = new boolean[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            palindrome[i][i] = true;
            if (i < N && seq[i] == seq[i + 1]) palindrome[i][i + 1] = true;
        }
        for (int k = 3; k <= N; k++) {
            for (int i = 1; i <= N - k + 1; i++) {
                if (palindrome[i + 1][i + k - 2] && seq[i] == seq[i + k - 1]) {
                    palindrome[i][i + k - 1] = true;
                }
            }
        }
    }
}