import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();
        long sumQ1 = 0, target = 0;
        for (int n: queue1) {
            q1.offer(n);
            sumQ1 += n;
            target += n;
        }
        for (int n: queue2) {
            q2.offer(n);
            target += n;
        }
        target = target >> 1;
        
        int cnt = 0;
        int max = (q1.size() + q2.size()) << 1;
        while (cnt < max) {
            if (sumQ1 == target) {
                break;
            } else if (sumQ1 > target) {
                int tmp = q1.poll();
                sumQ1 -= tmp;
                q2.offer(tmp);
            } else {
                int tmp = q2.poll();
                sumQ1 += tmp;
                q1.offer(tmp);
            }
            ++cnt;
        }
        return cnt < max ? cnt : -1;
    }
}