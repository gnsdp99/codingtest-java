import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author 김예훈
 * @date 24/02/21
 * @link https://www.acmicpc.net/problem/4195
 * @keyword_solution
 * - 두 집합의 크기를 구해야 하는 유니온 파인드 문제.
 * - 두 사람이 같은 집합에 속해 있으면 현재 크기, 아니면 합한 크기를 구한다.
 * - 매번 집합의 크기를 구하려면 N^2이 된다.
 * - 따라서 연산할 때마다 크기를 기록해야 한다.
 * - 이름을 숫자로 표시하기 위해 Map을 이용한다.
 *
 * @input
 * - 친구 관계의 수 F [1, 100,000]
 *
 * @output
 * - 친구 관계가 생길 때마다 두 사람의 친구 네트워크에 몇 명이 있는지 출력.
 *
 * @time_complex
 * - O(E)
 *
 * @perf
 */

public class Main {
    static class Subset {
        int parent, rank;

        Subset(int parent, int rank) {
            this.parent = parent;
            this.rank = rank;
        }
    }

    static int T, F;
    static Map<String, Integer> strToInt;
    static Subset[] subsets;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        subsets = new Subset[200_001];
        T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            F = Integer.parseInt(br.readLine());
            strToInt = new HashMap<>();
            for (int i = 0; i < F; i++) {
                st = new StringTokenizer(br.readLine());
                String name1 = st.nextToken();
                String name2 = st.nextToken();
                makeSet(name1);
                makeSet(name2);

                int res = union(strToInt.get(name1), strToInt.get(name2));
                sb.append(res).append("\n");
            }
        }
        System.out.println(sb);
    }

    static void makeSet(String name) {
        if (!strToInt.containsKey(name)) {
            strToInt.put(name, strToInt.size());
            int num = strToInt.get(name);
            subsets[num] = new Subset(num, 1);
        }
    }

    static int findSet(int v) {
        if (subsets[v].parent == v) return v;
        return subsets[v].parent = findSet(subsets[v].parent);
    }

    static int union(int v1, int v2) {
        int p1 = findSet(v1);
        int p2 = findSet(v2);
        if (p1 == p2) return subsets[p1].rank;

        if (subsets[p1].rank >= subsets[p2].rank) {
            subsets[p2].parent = p1;
            subsets[p1].rank += subsets[p2].rank;
            return subsets[p1].rank;
        } else {
            subsets[p1].parent = p2;
            subsets[p2].rank += subsets[p1].rank;
            return subsets[p2].rank;
        }
    }
}