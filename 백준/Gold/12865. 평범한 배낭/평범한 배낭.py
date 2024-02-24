import sys
input = sys.stdin.readline

N, K = map(int, input().rstrip().split(' '))
# things = [tuple(map(int, input().rstrip().split(' '))) for _ in range(N)] # each thing's (weight, value)
weights = [0] * N
values = [0] * N
for i in range(N):
    weights[i], values[i] = map(int, input().rstrip().split(' '))

def dp():
    knapsack = {0: 0} # key: max_value, value: weight of max_value
    for t_w, t_v in zip(weights, values): # thing's weight, value
        tmp = dict()
        for k_v, k_w in knapsack.items(): # knapsack's weight, value
            if knapsack.get(n_v := t_v + k_v, K+1) > (n_w := t_w + k_w): # := means, if condition is satisfied, assign the value to the variable
                tmp[n_v] = n_w
        knapsack.update(tmp)

    return max(knapsack.keys())

print(dp())