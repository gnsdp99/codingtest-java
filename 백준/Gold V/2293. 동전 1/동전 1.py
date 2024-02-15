# 백준 2293. 동전 1 (G5)
if __name__ == "__main__":
    n, k = map(int, input().split())
    coins = [int(input()) for _ in range(n)]
 
    dp = [0] * (k + 1)
    dp[0] = 1
    
    for val in coins:
        for i in range(val, k + 1):
            dp[i] = dp[i] + dp[i - val]
    
    result = dp[k]
    print(result)