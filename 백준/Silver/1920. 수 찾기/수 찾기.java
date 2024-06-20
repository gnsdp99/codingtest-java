import java.io.*;
import java.util.*;

class Main {

    static int N, M;
    static int[] arr;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            int num = Integer.parseInt(st.nextToken());
            sb.append(isExist(num) ? 1 : 0).append("\n");
        }
        System.out.println(sb);
    }

    static boolean isExist(int num) {
        int l = 0, r = N - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (arr[m] < num) {
                l = m + 1;
            } else if (arr[m] > num) {
                r = m - 1;
            } else {
                return true;
            }
        }
        return false;
    }
}