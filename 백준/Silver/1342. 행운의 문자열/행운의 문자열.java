import java.io.*;

public class Main {

    static String input;
    static int length, ans;
    static int[] countArr = new int[26];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();
        length = input.length();
        for (int i = 0; i < length; i++) {
            countArr[input.charAt(i) - 'a']++;
        }

        permutation(0, ' ');
        System.out.println(ans);
    }

     static void permutation(int depth, char prev) {

         if (depth >= length) {
             ++ans;
             return;
         }

         for (int i = 0; i < 26; i++) {
             char ch = (char)(i + 'a');
             if (countArr[i] == 0) {
                 continue;
             }
             if (ch != prev) {
                 countArr[i]--;
                 permutation(depth + 1, ch);
                 countArr[i]++;
             }
         }
     }
}