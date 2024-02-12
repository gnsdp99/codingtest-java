import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 요약
 * - 여행 경로가 주어질 때 여행이 가능한 지 불가능한 지 판단해야 한다.
 * - 다른 도시를 경유해서라도 경로의 모든 도시를 여행할 수 있으면 된다.

 * 구현
 * - 방향이 없는 그래프가 주어지고 경로 상의 모든 노드가 하나의 부분집합을 이루는지 구해야 한다.
 * - 따라서 유니온 파인드 알고리즘으로 구현한다.
 *
 * 입력
 * - 도시의 수 N [1, 200]
 * - 여행 경로에 속한 도시의 수 M [1, 1,000]
 * - 인접행렬이 입력으로 주어진다. i행 j열이 1이면 i번째 도시와 j번째 도시가 연결된 것이다.
 * - 도시의 번호는 1 ~ N이다.

 * 출력
 * - 여행이 가능하면 YES, 불가능하면 NO를 출력한다.

 * 시간복잡도
 * - O(N*logN) 모든 노드에 대해 Find 연산을 진행해야 한다.
 * - 단 입력이 인접 행렬 형태로 주어지기 때문에 O(N^2)이다.
 *
 * 결과
 *
 * */
public class Main {
    static int N, M;
    static int[] parents, ranks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        parents = new int[N + 1]; // 부모 노드를 관리하는 배열
        ranks = new int[N + 1]; // 부분집합의 깊이를 관리하는 배열

        for (int i = 1; i <= N; i++) {
            parents[i] = i; // 처음에는 N개의 분리 집합으로 만들어 자기 자신을 가르킴
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    union(i, j);
                }
            }
        }
        boolean ans = true; // 1: 성공, 0: 실패
        st = new StringTokenizer(br.readLine());
        int prev = Integer.parseInt(st.nextToken()); // 이전 도시
        while (st.hasMoreTokens()) { // 경로에 속한 도시를 하나씩 갈 수 있는지 판별함
            int next = Integer.parseInt(st.nextToken()); // 다음 도시
            if (find(prev) != find(next)) { // 갈 수 없음
                ans = false;
                break;
            }
            prev = next;
        }
        System.out.println(ans ? "YES" : "NO");
    }

    static int find(int node) { // 자신의 루트 노드를 찾음
        if (parents[node] == node) return node;

        parents[node] = find(parents[node]); // 경로 압축
        return parents[node];
    }

    static void union(int a, int b) { // 두 부분집합을 병합함
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB) return; // 이미 같은 집합임

        // union by rank
        if (ranks[rootA] == ranks[rootB]) ranks[rootA]++; // 랭크가 같으면 둘 중 하나 1 증가
        // 랭크가 더 작은 부분집합을 큰 쪽으로 병합함
        if (ranks[rootA] > ranks[rootB]) parents[rootB] = rootA;
        else parents[rootA] = rootB;
    }
}