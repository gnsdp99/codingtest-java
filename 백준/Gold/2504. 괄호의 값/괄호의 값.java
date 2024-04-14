import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        Stack<Character> stack = new Stack<>();

        int ans = 0;
        int val = 1;
        for (int i = 0; i < str.length(); i++) {

            char ch = str.charAt(i);

            if (ch == '(' || ch == '[') {
                stack.push(ch);
                val *= getVal(ch);
            } else {
                if (stack.isEmpty() || !isMatch(stack.peek(), ch)) {
                    ans = 0;
                    break;
                } else if (isMatch(str.charAt(i - 1), ch)) {
                    ans += val;
                }
                val /= getVal(ch);
                stack.pop();
            }
        }
        System.out.println(!stack.isEmpty() ? 0 : ans);
    }

    static int getVal(char ch) {
        return ch == '(' || ch == ')' ? 2 : 3;
    }

    static boolean isMatch(char ch1, char ch2) {
        return (ch1 == '(' && ch2 == ')') || (ch1 == '[' && ch2 == ']');
    }
}