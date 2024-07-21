import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static class MinHeap {
        int[] heap = new int[100_010];
        int size = 0;
        MinHeap() {
            Arrays.fill(heap, -1);
        }
        void insert(int x) {
            int idx = ++size;
            int pIdx = idx >> 1;
            while (idx > 1 && x < heap[pIdx]) {
                heap[idx] = heap[pIdx];
                idx = pIdx;
                pIdx = pIdx >> 1;
            }
            heap[idx] = x;
        }
        int remove() {
            if (size == 0) {
                return 0;
            }

            int result = heap[1];
            int tmp = heap[size];
            heap[size--] = -1;

            int idx = 1;
            int cIdx = 2;
            while (cIdx <= size) {
                if (cIdx < size && heap[cIdx] > heap[cIdx + 1]) {
                    ++cIdx;
                }
                if (heap[cIdx] > tmp) {
                    break;
                }
                heap[idx] = heap[cIdx];
                idx = cIdx;
                cIdx = cIdx << 1;
            }

            heap[idx] = tmp;
            return result;
        }
    }

    static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        MinHeap minHeap = new MinHeap();
        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());
            if (x == 0) {
                sb.append(minHeap.remove()).append("\n");
            } else {
                minHeap.insert(x);
            }
        }
        System.out.println(sb);
    }
}