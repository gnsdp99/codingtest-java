import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 구현
 * - 방향 그래프에서 정점 사이의 선수 관계를 찾는 문제이므로 위상 정렬을 이용한다.
 * - 부모 -> 자식 방향은 BFS, 자식 -> 부모 방향은 DFS
 *
 * 입력
 * - 간선의 수 N [2, 50]
 * - 왕위 계승 주장하는 사람 수 M [2, 50]
 *
 * 출력
 * - 왕과 혈통이 가장 가까운 사람의 이름을 출력한다.
 *
 * 시간복잡도 O(N)
 *
 * 결과
 *
 * */
public class Main {
    static class Node {
        String name;
        Node next;

        Node(String name, Node next) {
            this.name = name;
            this.next = next;
        }
    }
    static class Pair {
        int numParent;
        double score;

        Pair(int numParent, double score) {
            this.numParent = numParent;
            this.score = score;
        }
    }
    static int N, M;
    static Map<String, Node> adjMap = new HashMap<>();
    static Map<String, Pair> scoreMap = new HashMap<>();
    static String[] candidates;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        candidates = new String[M];
        String king = br.readLine();
        scoreMap.put(king, new Pair(0, 1.0));
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String child = st.nextToken();
            String parent1 = st.nextToken();
            String parent2 = st.nextToken();
            adjMap.put(parent1, adjMap.containsKey(parent1) ? new Node(child, adjMap.get(parent1)) : new Node(child, null));
            adjMap.put(parent2, adjMap.containsKey(parent2) ? new Node(child, adjMap.get(parent2)) : new Node(child, null));

            if (!scoreMap.containsKey(parent1)) scoreMap.put(parent1, new Pair(0, 0.0));
            if (!scoreMap.containsKey(parent2)) scoreMap.put(parent2, new Pair(0, 0.0));
            scoreMap.put(child, new Pair(2, 0.0));
        }

        for (int i = 0; i < M; i++) {
            candidates[i] = br.readLine();
        }

        inherit();

        String heir = "";
        double maxDegree = 0;
        for (String name: candidates) {
            double degree = scoreMap.containsKey(name) ? scoreMap.get(name).score : 0;
            if (maxDegree < degree) {
                maxDegree = degree;
                heir = name;
            }
        }
        System.out.println(heir);
    }

    static void inherit() {
        Queue<String> queue = new ArrayDeque<>();
        for (String name : scoreMap.keySet()) { // 진입 차수(부모) 0인 정점 삽입
            if (scoreMap.get(name).numParent == 0)
                queue.offer(name);
        }
        while (!queue.isEmpty()) {
            String parent = queue.poll();

            for (Node child = adjMap.get(parent); child != null; child = child.next) {
                scoreMap.get(child.name).score += scoreMap.get(parent).score / 2;
                if (--scoreMap.get(child.name).numParent == 0) { // 부모의 점수를 모두 물려 받으면 삽입
                    queue.offer(child.name);
                }
            }
        }
    }
}