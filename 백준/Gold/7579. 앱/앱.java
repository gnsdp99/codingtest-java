import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static class App {
        int memory, cost;

        App(int memory) {
            this.memory = memory;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        App[] apps = new App[N + 1];

        int MAX_COST = 100 * N;
        int[] knapsack = new int[MAX_COST + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            apps[i] = new App(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            apps[i].cost = Integer.parseInt(st.nextToken());
        }

        // 메모리 합이 M 이상인 것 중 최소 비용
        for (int i = 1; i <= N; i++) {
            int memory = apps[i].memory;
            int cost = apps[i].cost;
            for (int c = MAX_COST; c >= cost; c--) {
                if (knapsack[c] < knapsack[c - cost] + memory) {
                    knapsack[c] = knapsack[c - cost] + memory;
                }
            }
        }
        for (int i = 0; i <= MAX_COST; i++) {
            if (knapsack[i] >= M) {
                System.out.println(i);
                break;
            }
        }
    }
}