// 백준 1929. 소수 구하기 (S3)

// 풀이 1
import java.util.Scanner;

public class P1929 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int M = Math.max(2, sc.nextInt());
        int N = sc.nextInt();

        for (int i = M; i <= N; i++) {
            isPrimeNumber(i);
        }
    }

    static void isPrimeNumber(int num) {
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return;
            }
        }
        System.out.println(num);
    }
}

// 풀이 2
//import java.util.Arrays;
//import java.util.Scanner;
//
//public class P1929 {
//    private static boolean[] primeList;
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int M = Math.max(2, sc.nextInt());
//        int N = sc.nextInt();
//        primeList = new boolean[N + 1];
//        Arrays.fill(primeList, true);
//
//        for (int i = 2; i <= Math.sqrt(N); i++) {
//            if (primeList[i]) {
//                for (int j = i * 2; j <= N; j += i) {
//                    primeList[j] = false;
//                }
//            }
//        }
//
//        for (int num = M; num <= N; num++) {
//            if (primeList[num]) {
//                System.out.println(num);
//            }
//        }
//    }
//}