import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 요약
 * - 0 ~ n의 원소로 이루어진 n + 1개의 집합이 존재한다.
 * - find 연산과, union연산을 수행하는 프로그램을 작성한다.
 *
 * 구현
 * - 유니온 파인드 알고리즘, 경로 압축
 *
 * 입력
 * - 노드의 개수 n [1, 1,000,000]
 * - 연산의 개수 m [1, 100,000]
 * - union 연산은 0 a b 와 같이 입력.
 * - find 연산은 1 a b 와 같이 입력.
 * - a와 b는 같을 수도 있다.
 *
 * 출력
 * - find 연산 (1로 시작)이 주어질 때 두 노드가 같은 집합에 포함되어 있으면 yes, 아니면 no를 출력한다.
 * - 대소문자 상관없다.
 *
 * 시간복잡도
 * - O(1) or O(logN)
 *
 * 결과
 *
 */

public class Main {

    static int N, M;
    static int[] parents, ranks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // parents 초기화
        parents = new int[N + 1];
        ranks = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (op == 0) union(a, b);
            else if (op == 1) sb.append(find(a) == find(b) ? "YES" : "NO").append("\n");
        }
        System.out.println(sb);
    }

    static int find(int a) { // 같은 집합에 속하면 true
        int pa = parents[a];
        if (pa == a) return pa; // 루트이면 바로 리턴

        parents[a] = find(pa); // 루트 노드를 찾는 과정에서 경로 압축
        return parents[a];
    }

    static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa == pb) return; // 같으면 병합할 필요 X

        if (ranks[pa] == ranks[pb]) ranks[pa]++;
        if (ranks[pa] > ranks[pb]) { // rank가 큰 집합으로 병합
            parents[pb] = pa;
        } else {
            parents[pa] = pb;
        }
    }
}