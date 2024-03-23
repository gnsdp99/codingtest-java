/*
배달이나 수거가 남은 가장 먼 집을 먼저 처리한다.
*/

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        
        int lastDelv = n - 1;
        int lastPick = n - 1;
        
        long ans = 0;
        
        while (lastDelv >= 0 || lastPick >= 0) {
            
            while (lastDelv >= 0 && deliveries[lastDelv] <= 0) {
                lastDelv--;
            }
            
            while (lastPick >= 0 && pickups[lastPick] <= 0) {
                lastPick--;
            }
            
            ans += (Math.max(lastDelv, lastPick) + 1) * 2;
            
            int restDelv = cap;
            while (lastDelv >= 0 && restDelv > 0) {
                restDelv -= deliveries[lastDelv];
                deliveries[lastDelv--] = 0;
            }
            if (restDelv < 0) deliveries[++lastDelv] = -(restDelv);
            
            int restPick = cap;
            while (lastPick >= 0 && restPick > 0) {
                restPick -= pickups[lastPick];
                pickups[lastPick--] = 0;
            }
            if (restPick < 0) pickups[++lastPick] = -(restPick);
        }
        
        return ans;
    }
}