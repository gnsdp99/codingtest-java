// 백준 1874. 스택 수열 (S2)

// 풀이 1
/*
import java.util.Scanner;
import java.util.Stack;

public class P1874 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int N = sc.nextInt();
        Stack<Integer> stack = new Stack<>();
        int[] sequence = new int[N];
        for (int i = 0; i < N; i++) {
            sequence[i] = sc.nextInt();
        }
        int seqIdx = 0;
        int pushNum = 1;

        while (seqIdx < N) {
            if (!stack.isEmpty()) {
                int numToFind = sequence[seqIdx];
                int numTop = stack.peek();
                if (numToFind == numTop) {
                    stack.pop();
                    sb.append("-\n");
                    seqIdx++;
                } else if (numToFind > numTop) {
                    stack.push(pushNum++);
                    sb.append("+\n");
                } else {
                    System.out.println("NO");
                    return;
                }
            } else {
                stack.push(pushNum++);
                sb.append("+\n");
            }
        }
        System.out.println(sb);
    }
}
*/

// 풀이 2
import java.util.Scanner;
import java.util.Stack;

public class P1874 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int N = sc.nextInt();
        int[] sequence = new int[N];
        for (int i = 0; i < N; i++) {
            sequence[i] = sc.nextInt();
        }
        Stack<Integer> stack = new Stack<>();
        int numToPush = 1;

        for (int i = 0; i < N; i++) {
            int numToFind = sequence[i];
            if (numToFind >= numToPush) {
                while (numToFind >= numToPush) {
                    stack.push(numToPush++);
                    sb.append("+\n");
                }
                stack.pop();
                sb.append("-\n");
            } else {
                int numTop = stack.pop();
                if (numTop > numToFind) {
                    System.out.println("NO");
                    return;
                } else {
                    sb.append("-\n");
                }
            }
        }
        System.out.println(sb);
    }
}