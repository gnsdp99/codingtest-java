import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        
        int len = choices.length;
   
        Map<Character, Integer> score = new HashMap<>();
        score.put('A', 0);
        score.put('N', 0);
        score.put('R', 0);
        score.put('T', 0);
        score.put('C', 0);
        score.put('F', 0);
        score.put('J', 0);
        score.put('M', 0);
        
        for (int i = 0; i < len; i++) {
            if (choices[i] < 4) {
                score.put(survey[i].charAt(0), score.get(survey[i].charAt(0)) + 4 - choices[i]);
            } else if (choices[i] > 4) {
                score.put(survey[i].charAt(1), score.get(survey[i].charAt(1)) + choices[i] - 4);
            }
        }
        
        String ans = "";
        ans += score.get('R') >= score.get('T') ? "R" : "T";
        ans += score.get('C') >= score.get('F') ? "C" : "F";
        ans += score.get('J') >= score.get('M') ? "J" : "M";
        ans += score.get('A') >= score.get('N') ? "A" : "N";
        return ans;
    }
}