import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int totalRemove = 1;
        int curRemove = 0;
        sb.append(curRemove + 1).append(" ");
        while (totalRemove < N) {
            int count = arr[curRemove];
            arr[curRemove] = 0;
            while (count < 0) {
                --curRemove;
                if (curRemove < 0) {
                    curRemove = N - 1;
                }
                if (arr[curRemove] != 0) {
                    ++count;
                }
            }
            while (count > 0) {
                ++curRemove;
                if (curRemove >= N) {
                    curRemove %= N;
                }
                if (arr[curRemove] != 0) {
                    --count;
                }
            }
            ++totalRemove;
            sb.append(curRemove + 1).append(" ");
        }
        System.out.println(sb);
    }
}