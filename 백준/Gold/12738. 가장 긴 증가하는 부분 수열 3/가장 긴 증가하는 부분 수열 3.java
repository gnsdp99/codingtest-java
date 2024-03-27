import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int[] LIS = new int[N];
        int size = 0;
        LIS[size++] = A[0];
        for (int i = 1; i < N; i++) {
            if (A[i] > LIS[size - 1]) {
                LIS[size++] = A[i];
            } else {
                int target = lowerBound(LIS, size, A[i]);
                LIS[target] = A[i];
            }
        }
        System.out.println(size);
    }

    static int lowerBound(int[] LIS, int size, int cur) {
        int left = 0, right = size - 1;
        int target = -1;
        while (left <= right) {

            int mid = left + ((right - left) >> 1);

            if (LIS[mid] >= cur) {
                // 오른쪽은 절대 정답이 아님
                target = mid;
                right = mid - 1;
            } else {
                // 왼쪽은 절대 정답이 아님
                left = mid + 1;
            }
        }
        return target;
    }
}