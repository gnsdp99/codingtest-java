import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] numbers;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(numbers);

        int ans = 0;
        for (int i = 0; i < N; i++) {
            int left = 0;
            int right = N - 1;
            while (true) {
                if (i == left) {
                    ++left;
                } else if (i == right) {
                    --right;
                }

                if (left >= right) {
                    break;
                }

                int sum = numbers[left] + numbers[right];
                if (sum > numbers[i]) {
                    --right;
                } else if (sum < numbers[i]) {
                    ++left;
                } else {
                    ++ans;
                    break;
                }
            }
        }
        System.out.println(ans);
    }
}