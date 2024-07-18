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
            while (left < right) {
                int sum = numbers[left] + numbers[right];
                if (sum > numbers[i]) {
                    --right;
                } else if (sum < numbers[i]) {
                    ++left;
                } else {
                    if (i != left && i != right) { // 하나가 0이고 하나가 자기 자신이면 안됨
                        ++ans;
                        break;
                    } else if (i == left) { // 음수끼리 더하면 더 작은 음수가 될 수 있음
                        ++left;
                    } else {
                        --right;
                    }
                }
            }
        }
        System.out.println(ans);
    }
}