import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int[] LIS = new int[N];
        Arrays.fill(LIS, 1);

        int length = 1;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (LIS[j] >= LIS[i] && A[i] > A[j]) {
                    LIS[i] = LIS[j] + 1;
                    if (LIS[i] > length) {
                        length = LIS[i];
                    }
                }
            }
        }
        sb.append(length).append("\n");
        // 역추적
        Stack<Integer> stack = new Stack<>();
        for (int i = N - 1; i >= 0; i--) {
            if (LIS[i] == length) {
                stack.push(A[i]);
                length--;
            }
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }
        System.out.println(sb);
    }
}