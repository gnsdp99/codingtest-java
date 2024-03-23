/*
- 첫 N / 3장의 카드와 이후 라운드마다 뽑을 카드를 따로 관리한다.
- 라운드가 진행될 때마다 카드 두 장을 뽑아서 배열에 추가한다.
- target을 만드는 경우는 다음과 같다.
- 1. init 배열에서 2개 선택. (coin 0개 소모)
- 2. init 배열에서 1개, candidate 배열에서 1개 선택 (coin 1개 소모)
- 3. candidate 배열에서 2개 선택. (conin 2개 소모)
- target을 만들 수 있으면 다음 라운드로 넘어간다.
*/

import java.util.HashSet;

class Solution {
    
    static int N, target, ans = 0;
    static HashSet<Integer> init = new HashSet<>();
    static HashSet<Integer> candidates = new HashSet<>();
        
    public int solution(int coin, int[] cards) {
        N = cards.length;
        target = N + 1;
        int initSize = N / 3;
        
        int i = 0;
        for (; i < initSize; i++) {
            init.add(cards[i]);
        }
        
        outer: for (; i <= N; i += 2) {
            ans++; 
            if (i == N) break;
            int a = cards[i];
            int b = cards[i + 1];
            candidates.add(a);
            candidates.add(b);
            
            for (int card: init) {
                if (init.contains(target - card)) {
                    init.remove(target - card);
                    init.remove(card);
                    continue outer;
                }
            }
            if (coin >= 1) {
                for (int card: candidates) {
                    if (init.contains(target - card)) {
                        init.remove(target - card);
                        candidates.remove(card);
                        coin--;
                        continue outer;
                    }
                }    
            }
            if (coin >= 2) {
                for (int card: candidates) {
                    if (candidates.contains(target - card)) {
                        candidates.remove(target - card);
                        candidates.remove(card);
                        coin -= 2;
                        continue outer;
                    }
                }
            }
            break;
        }
        
        return ans;
    }
}