import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 문제 요약
 * - 사용하는 전기 용품의 순서가 주어진다. 이를 기반으로 플러그를 빼는 횟수를 최소화한다.
 *
 * 구현
 * - 멀티탭이 가득 찼을 때 누굴 뺄 지 결정하는 것이 중요하다.
 * - 앞으로 남은 플러그 중 한 번도 나오지 않는 것이 가장 우선 순위가 높다.
 * - 그다음 멀티탭에 꽂힌 플러그 중 첫 번째로 나오는 순서가 가장 늦은 플러그의 우선 순위가 높다.
 * - 이렇게 우선 순위가 가장 높은 멀티탭의 플러그를 교체한다.
 *
 * 입력
 * - 구멍의 개수 N [1, 100]
 * - 전기 용품 총 사용 횟수 [1, 100]
 *
 * 출력
 * - 플로그를 빼는 최소 횟수를 출력한다.
 *
 * 시간복잡도 O(K^2)
 *
 * 결과 11540kb, 76ms
 *
 * */
public class Main {
    static int N, K;
    static int[] plugs, multitap;
    static boolean[] isPluged;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        plugs = new int[K];
        multitap = new int[N];
        isPluged = new boolean[K + 1];
        int lastIdx = 0; // 멀티탭 마지막 자리의 인덱스
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            plugs[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;
        for (int i = 0; i < K; i++) {
            if (isPluged[plugs[i]]) continue; // 이미 꽂혀 있음

            if (lastIdx < N) { // 자리 남음
                multitap[lastIdx++] = plugs[i];
                isPluged[plugs[i]] = true;
                continue;
            }

            int target = -1, prevWhen = -1;
            for (int j = 0; j < N; j++) { // 뽑아야 함
                int when = 0; // 멀티탭의 각 요소가 언제 다시 나오는지
                for (int k = i + 1; k < K; k++) {
                    if (multitap[j] == plugs[k]) break;
                    when++;
                }
                if (when > prevWhen) { // 더 늦게 나오면 교체 대상
                    target = j;
                    prevWhen = when;
                }
            }
            // 교체
            isPluged[multitap[target]] = false;
            isPluged[plugs[i]] = true;
            multitap[target] = plugs[i];
            ans++;
        }
        System.out.println(ans);
    }
}
