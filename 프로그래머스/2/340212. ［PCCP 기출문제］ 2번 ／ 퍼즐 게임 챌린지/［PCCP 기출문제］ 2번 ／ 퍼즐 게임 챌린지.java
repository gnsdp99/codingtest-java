class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 100_000;
        
        int l = 1, r = 100_000;
        while (l <= r) {
            int m = l + (r - l) / 2;
            long sum = calcSum(diffs, times, m);
            if (sum <= limit) {
                answer = Math.min(answer, m);
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        
        return answer;
    }
    
    long calcSum(int[] diffs, int[] times, int lv) {
        long sum = 0;
        for (int i = 0; i < diffs.length; i++) {
            if (diffs[i] > lv) {
                sum += (diffs[i] - lv) * (times[i] + times[i - 1]) + times[i];
            } else {
                sum += times[i];
            }
        }
        return sum;
    }
}