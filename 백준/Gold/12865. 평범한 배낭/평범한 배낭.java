import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static class Item {
        int weight, value;

        Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Item[] items = new Item[N + 1];
        int[] knapsack = new int[K + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            items[i] = new Item(W, V);
        }

        for (int i = 1; i <= N; i++) {
            for (int w = K; w >= items[i].weight; w--) {
                if (knapsack[w] < knapsack[w - items[i].weight] + items[i].value) {
                    knapsack[w] = knapsack[w - items[i].weight] + items[i].value;
                }
            }
        }
        System.out.println(knapsack[K]);
    }
}