import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 구현
 * - 두 점 사이의 거리가 가중치가 되는 MST 문제이다.
 * - 거리가 최소가 되도록 MST를 만들어야 한다.
 * - 입력으로 주어진 정보를 통해 트리를 만들고 이을 수 있는 간선 중 최소 비용을 구한다.
 * - 두 점 사이의 거리는 유클리드 거리?
 * - 주어진 간선 정보를 통해 하나의 집합을 이루도록 한다. (kruskal)
 *
 * #### M개의 입력 간선을 모두 연결하면 안되고 사이클이 생성되지 않고 중복이 없는 것만 연결해야 한다!
 *
 * 입력
 * - 우주신의 수 N [1, 1,000]
 * - 연결된 통로의 수 M [1, 1,000]
 * - 좌표 X, Y [0, 1,000,000] -> 최대 거리는 (0, 0) ~ (1,000,000, 1,000,000) = 1,000,000 * sqrt(2)
 *
 * 출력
 * - 최소 통로 길이를 소수점 둘째 자리까지 반올림하여 출력.
 *
 * 시간복잡도 O(E * logV), E = N^2, V = N >> O(N^2 * logN)
 *
 * 결과 42,320kb, 600ms
 *
 * */
public class Main {
    static class Edge implements Comparable<Edge> {
        int src, dst;
        double weight;

        Edge(int src, int dst, double weight) {
            this.src = src;
            this.dst = dst;
            this.weight = weight;
        }
        public int compareTo(Edge e) {
            return Double.compare(weight, e.weight);
        }
    }
    static class Pos {
        int x, y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, M, numLinked;
    static PriorityQueue<Edge> edgePQ = new PriorityQueue<>(); // 간선 우선순위 큐
    static boolean[][] adjMatrix; // 인접 행렬
    static Pos[] positions; // 우주신들의 좌표
    static int[] parents; // 서로소 집합에서 자신의 부모 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjMatrix = new boolean[N + 1][N + 1];
        positions = new Pos[N + 1];
        makeSet(); // make-set 연산
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            positions[i] = new Pos(X, Y);
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            if (!adjMatrix[A][B] && union(A, B)) { // 중복 입력이 존재할 수 있음, 사이클을 만들면 안됨
                adjMatrix[A][B] = adjMatrix[B][A] = true;
                numLinked++;
            }
        }

        // 연결되지 않은 모든 간선 생성
        for (int i = 1; i <= N - 1; i++) {
            for (int j = i + 1; j <= N; j++) {
                if (!adjMatrix[i][j]) {
                    edgePQ.offer(new Edge(i, j, getDist(positions[i], positions[j])));
                }
            }
        }

        // kruskal
        int cnt = 0; // 선택한 간선 수
        double cost = 0; // 선택한 간선 비용 합
        while (!edgePQ.isEmpty()) {
            Edge edge = edgePQ.poll();
            if (cnt == N - 1 - numLinked) break; // N - 1개의 간선을 선택해야 하는데 이미 numLinked 개 선택됨

            if (union(edge.src, edge.dst)) { // 선택됨
                cost += edge.weight;
                cnt++;
            }
        }
        System.out.printf("%.2f", cost); // 소수점 둘 째자리
    }

    static double getDist(Pos pos1, Pos pos2) { // 두 점 사이의 거리
        return Math.sqrt(Math.pow(pos1.x - pos2.x, 2) + Math.pow(pos1.y - pos2.y, 2));
    }

    static void makeSet() {
        parents = new int[N + 1];
        for (int v = 1; v <= N; v++) {
            parents[v] = v;
        }
    }

    static int find(int v) {
        if (parents[v] == v) return v;
        return parents[v] = find(parents[v]); // 경로 압축
    }

    static boolean union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) return false; // 이미 연결됨

        parents[p2] = p1; // 부모끼리 연결
        return true;
    }
}