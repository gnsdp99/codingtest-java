/*
 * 1. 둘이 선물을 주고 받았으면 더 많이 준 사람이 1개 받는다.
 * 2. 둘이 주고 받지 않았거나 그 갯수가 같으면 선물 지수가 높은 사람이 1개 받는다.
 * 3. 선물 지수도 같으면 아무도 안받는다.
 * - 선물 지수는 자신이 친구들에게 준 선물 - 받은 선물
 *
 * - friends 배열(친구 이름) [2, 50], 친구 이름은 길이 10이하의 문자열.
 * - 이름이 같은 친구는 없다.
 * - gifts 배열(선물 기록) [1, 10,000], "A, B"에서 A가 선물을 준 사람, B가 받은 사람
 * - A와 B는 모두 friends에 포함되고, A와 B는 같지 않다.
 *
 * - 선물 관계를 인접 행렬로 표현한다. 
 * - 두 친구 관계에서 주고 받은 선물 개수와 선물 지수로 받을 사람을 정한다.
 * - 친구의 이름을 해시테이블로 관리한다. (중복 이름이 없기 때문에)
 */

import java.util.Map;
import java.util.HashMap;

class Solution {
    
    static int[][] givenArr;
    static int[] ansArr, giftRate;
    static int numFriends, numGifts;
    static Map<String, Integer> nameToInt = new HashMap<>();
        
    public int solution(String[] friends, String[] gifts) {
        numFriends = friends.length;
        numGifts = gifts.length;
        givenArr = new int[numFriends][numFriends];
        ansArr = new int[numFriends];
        giftRate = new int[numFriends];
        
        int tmpNum = 0;
        for (int i = 0; i < numFriends; i++) {
            nameToInt.put(friends[i], tmpNum++);
        }
        
        for (int i = 0; i < numGifts; i++) {
            String[] names = gifts[i].split(" ");
            int given = nameToInt.get(names[0]);
            int received = nameToInt.get(names[1]);
            givenArr[given][received]++;
            giftRate[given]++;
            giftRate[received]--;
        }

        for (int i = 0; i < numFriends - 1; i++) {
            for (int j = i + 1; j < numFriends; j++) {
                if (givenArr[i][j] > givenArr[j][i]) {
                    ansArr[i]++;
                } else if (givenArr[i][j] < givenArr[j][i]) {
                    ansArr[j]++;
                } else {
                    if (giftRate[i] > giftRate[j]) {
                        ansArr[i]++;
                    } else if (giftRate[i] < giftRate[j]) {
                        ansArr[j]++;
                    }
                }
            }
        }
        int max = 0;
        for (int i = 0; i < numFriends; i++) {
            if (ansArr[i] > max) max = ansArr[i];
        }
        return max;
    }
}