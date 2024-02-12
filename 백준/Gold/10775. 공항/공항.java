import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 문제 요약
 * - 공항에는 G개의 게이트가 있고 P개의 비행기가 순서대로 도착한다.
 * - i번째 비행기를 1 ~ g(i) (1 <= g(i) <= G)번 게이트 중 하나에 영구적으로 도킹한다.
 * - 비행기가 어느 게이트에도 도킹할 수 없다면 공항이 폐쇠된다.
 * - 최대한 많은 비행기를 도킹시켜야 한다.
 *
 * 구현
 * - 배열을 이용해 선형 탐색을 한다면 O(N^2)이므로 1e10=100억이 된다.
 * - 따라서 O(N*logN)까지는 줄여야 한다.
 * - 도킹이 완료된 게이트를 유니온 파인드 알고리즘을 이용해 하나의 집합으로 관리한다.
 *
 * - 이 알고리즘의 목표는 g(i)가 주어졌을 때 g(i)의 루트를 찾아서 새로운 루트 게이트에 연결하는 것이다.
 * - 그리고 루트 게이트가 0인 곳에 입력이 주어지면 더 이상 도킹할 수 없는 것이다.

 * 입력
 * - 게이트의 수 G [1, 1e5]
 * - 비행기의 수 [1, 1e5]

 * 출력
 * - 도킹시킬 수 있는 최대 비행기 수를 출력한다.

 * 시간복잡도
 * - O(N * logN)

 * 결과
 *
 * */
public class Main {
    static int G, P;
    static int[] parents, ranks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());
        parents = new int[G + 1];
        ranks = new int[G + 1];
        for (int i = 0; i <= G; i++) {
            parents[i] = i;
        }

        int ans = 0;
        for (int i = 0; i < P; i++) {
            int g = Integer.parseInt(br.readLine());

            int rootG = find(g);
            if (rootG == 0) break; // 도킹 실패

            parents[rootG] = find(rootG - 1); // 1 ~ g까지 중 도킹되지 않은 가장 왼쪽 게이트에 도킹한다.
            ans++;
        }
        System.out.println(ans);
    }

    static int find(int a) {
        if (parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }
}