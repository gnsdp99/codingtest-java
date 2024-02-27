import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 구현
 * - 하나의 최소 힙과 하나의 최대 힙으로 관리한다.
 * - 입력한 두 값 중 중간값보다 작은 값은 maxHeap에, 큰 값은 minHeap에 넣는다.
 * - 최소 힙의 크기 = 최대 힙 크기 + 1을 유지하면, 최소 힙의 루트가 중간값이 된다.
 *
 * 입력
 * - 테케 수 T [1, 1,000]
 * - 수열의 크기 M [1, 9,999] (한 줄에 10개씩)
 * - 수열의 원소: 32비트 부호있는 정수
 *
 * 출력
 * - 각 테케에 대해 중앙값 개수 출력, 중앙값을 차례대로 출력(한 줄에 10개씩)
 *
 * 시간복잡도 O(N * logN)
 *
 * 결과
 */

public class Main {

    static class Heap {
        int[] heap;
        int size;

        Heap(int capacity) {
            heap = new int[capacity];
            size = 0;
        }

        void push(int key, boolean isMax) {
            heap[++size] = key;

            int cur = size;
            while (cur > 1) {
                if ((isMax && heap[cur] > heap[cur >> 1])
                        || (!isMax && heap[cur] < heap[cur >> 1])) {
                    swap(cur, cur >> 1);
                }
                cur >>= 1;
            }
        }

        int pop(boolean isMax) {
            if (size == 0) return -1;

            int cur = 1;
            int root = heap[cur];
            heap[cur] = heap[size];
            heap[size--] = 0;

            while (true) {
                int child = cur << 1;

                if (child + 1 <= size) {
                    if ((isMax && heap[child] < heap[child + 1])
                            || (!isMax && heap[child] > heap[child + 1])) {
                        child++;
                    }
                }

                if ((isMax && (child > size || heap[cur] > heap[child]))
                        || (!isMax && (child > size || heap[cur] < heap[child]))) {
                    break;
                }

                swap(cur, child);
                cur = child;
            }
            return root;
        }

        int getSize() {
            return size;
        }

        int getRoot() {
            return heap[1];
        }

        void swap(int i, int j) {
            int tmp = heap[i];
            heap[i] = heap[j];
            heap[j] = tmp;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            int M = Integer.parseInt(br.readLine());
            int[] sequence = new int[M];
            int idx = 0;
            while (idx < M) {
                st = new StringTokenizer(br.readLine());
                while (st.hasMoreTokens()) {
                    sequence[idx++] = Integer.parseInt(st.nextToken());
                }
            }

            Heap minHeap = new Heap(M + 1);
            Heap maxHeap = new Heap(M + 1);
            int[] median = new int[(M >> 1) + 1];
            int size = 0;

            minHeap.push(sequence[0], false);
            median[size++] = minHeap.getRoot();

            boolean isOdd = false;
            for (int i = 1; i < M; i++) {
                if (sequence[i] > minHeap.getRoot()) minHeap.push(sequence[i], false);
                else maxHeap.push(sequence[i], true);

                if (minHeap.getSize() < maxHeap.getSize()) {
                    minHeap.push(maxHeap.pop(true), false);
                }
                if (minHeap.getSize() > maxHeap.getSize() + 1) {
                    maxHeap.push(minHeap.pop(false), true);
                }

                if (isOdd) {
                    median[size++] = minHeap.getRoot();
                    isOdd = false;
                } else isOdd = true;
            }
            sb.append(size).append("\n");
            idx = 0;
            while (idx < median.length) {
                sb.append(median[idx++]).append(" ");
                if (idx % 10 == 0) sb.append("\n");
            }
            if (M % 10 != 0) sb.append("\n");
        }
        System.out.println(sb);
    }
}
