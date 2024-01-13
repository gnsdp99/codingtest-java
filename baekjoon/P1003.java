/*
* 0을 출력한 횟수를 저장한 dp 배열과 1을 출력한 횟수를 저장한 dp 배열을 따로 만든다.
* dpZero[n] = dpZero[n - 1] + dpZero[n - 2]
* dpOne[n] = dpOne[n - 1] + dpOne[n - 2]
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class P1003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        int N;
        List<Integer> numZeros = new ArrayList<>();
        List<Integer> numOnes = new ArrayList<>();
        numZeros.add(1);
        numZeros.add(0);
        numOnes.add(0);
        numOnes.add(1);
        for (int tc = 0; tc < T; tc++) {
            N = Integer.parseInt(br.readLine());
            if (N >= numZeros.size()) {
                for (int i = numZeros.size(); i <= N; i++) {
                    numZeros.add(numZeros.get(i - 1) + numZeros.get(i - 2));
                    numOnes.add(numOnes.get(i - 1) + numOnes.get(i - 2));
                }
            }
            sb.append(numZeros.get(N));
            sb.append(" ");
            sb.append(numOnes.get(N));
            sb.append("\n");
        }
        System.out.println(sb);
    }
}