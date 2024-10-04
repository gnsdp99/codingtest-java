import java.util.*;

class Solution {
    public int solution(String numbers) {
        int answer = 0;
        int n = numbers.length();
        Set<Integer> set = new TreeSet();
        
        makePermutation(numbers, n, "", new boolean[n], set);

        for (int num: set) {
            if (isPrime(num)) {
                ++answer;
            }
        }
        
        return answer;
    }
    
    private void makePermutation(String numbers, int n, String permutation, boolean[] selected, Set<Integer> set) {
        if (permutation != "") {
            set.add(Integer.valueOf(permutation));
        }
        for (int i = 0; i < n; i++) {
            if (selected[i]) {
                continue;
            }
            selected[i] = true;
            makePermutation(numbers, n, permutation + numbers.charAt(i), selected, set);
            selected[i] = false;
        }
    }
    
    private boolean isPrime(int number) {
        if (number == 2) {
            return true;
        }
        
        if (number == 0 || number == 1 || number % 2 == 0) {
            return false;
        }
        
        for (int i = 3; i <= (int)Math.sqrt(number); i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}