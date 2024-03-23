import java.util.Arrays;
import java.util.HashMap;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        
        int[] answer = new int[privacies.length];
        
        int todayDay = getDay(today);
        
        HashMap<String, Integer> termToPeriod = new HashMap<>();
        
        for (String term: terms) {
            String[] tmp = term.split(" ");
            termToPeriod.put(tmp[0], Integer.parseInt(tmp[1]));
        }
        
        int idx = 0;
        for (int i = 1; i <= privacies.length; i++) {
            String[] tmp = privacies[i - 1].split(" ");
            int day = getDay(tmp[0]);
            String term = tmp[1];
            int period = termToPeriod.get(term);
            
            
            if (day + period * 28 <= todayDay) {
                answer[idx++] = i;
            }
        }
        return Arrays.copyOf(answer, idx);
    }
    
    private int getDay(String date) {
        String[] tmp = date.split("\\.");
        int year = Integer.parseInt(tmp[0]);
        int month = Integer.parseInt(tmp[1]);
        int day = Integer.parseInt(tmp[2]);
        return year * 12 * 28 + month * 28 + day;
    }
}