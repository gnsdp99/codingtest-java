// 백준 11286. 절댓값 힙 (S1)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static class Node {
        int absNum, num;
        Node(int num) {
            this.absNum = Math.abs(num);
            this.num = num;
        }
    }

    static class AbsHeap {
        Node[] heap = new Node[100_010];
        int size = 0;

        void insert(int x) {
            int idx = ++size;
            int pIdx = idx >> 1;
            while (idx > 1 && isSatisfied(Math.abs(x), x, heap[pIdx].absNum, heap[pIdx].num)) {
                heap[idx] = heap[pIdx];
                idx = pIdx;
                pIdx = pIdx >> 1;
            }
            heap[idx] = new Node(x);
        }

        int remove() {
            if (size < 1) {
                return 0;
            }
            int result = heap[1].num;
            Node target = heap[size--];
            int idx = 1;
            int cIdx = idx << 1;
            while (cIdx <= size) {
                if (isSatisfied(heap[cIdx + 1].absNum, heap[cIdx + 1].num, heap[cIdx].absNum, heap[cIdx].num)) {
                    ++cIdx;
                }
                if (isSatisfied(target.absNum, target.num, heap[cIdx].absNum, heap[cIdx].num)) {
                    break;
                }
                heap[idx] = heap[cIdx];
                idx = cIdx;
                cIdx = cIdx << 1;
            }
            heap[idx] = target;
            return result;
        }

        boolean isSatisfied(int smallAbsNum, int smallNum, int bigAbsNum, int bigNum) {
            return smallAbsNum < bigAbsNum ||
                    (smallAbsNum == bigAbsNum && smallNum < bigNum);
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        AbsHeap absHeap = new AbsHeap();
        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());
            if (x != 0) {
                absHeap.insert(x);
            } else {
                sb.append(absHeap.remove()).append("\n");
            }
        }
        System.out.println(sb);
    }
}