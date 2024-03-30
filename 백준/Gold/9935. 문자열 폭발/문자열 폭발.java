import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String text = br.readLine();
        String pattern = br.readLine();

        int lenT = text.length();
        int lenP = pattern.length();

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < lenT; i++) {

            stack.push(text.charAt(i));

            int lenS = stack.size();
            if (lenS >= lenP) {

                boolean isExploded = true;

                for (int j = 0; j < lenP; j++) {
                    if (stack.get(lenS - lenP + j) != pattern.charAt(j)) {
                        isExploded = false;
                        break;
                    }
                }

                if (isExploded) {
                    for (int j = 0; j < lenP; j++) {
                        stack.pop();
                    }
                }
            }
        }
        for (Character c : stack) {
            sb.append(c);
        }
        System.out.println(sb.length() > 0 ? sb : "FRULA");
    }
}