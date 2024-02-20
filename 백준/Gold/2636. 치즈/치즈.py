# 백준 2636. 치즈 (G4)
import sys
input = sys.stdin.readline
from collections import deque

def bfs():
    queue = deque([(0, 0)])
    visited = [[0] * M for _ in range(N)]
    visited[0][0] = 1
    num_melted = 0
    while queue:
        y, x = queue.popleft()
        for dy, dx in delta:
            ny, nx = y + dy, x + dx
            if 0 <= ny < N and 0 <= nx < M and not visited[ny][nx]:
                visited[ny][nx] = 1
                if board[ny][nx] == 0: 
                    queue.append((ny, nx))
                else:
                    board[ny][nx] = 0
                    num_melted += 1
    return num_melted

if __name__ == "__main__":
    N, M = map(int, input().split())
    board = []
    num_cheese = 0
    for _ in range(N):
        line = list(map(int, input().split()))
        board.append(line)
        num_cheese += line.count(1)

    delta = [(1, 0), (-1, 0), (0, 1), (0, -1)]
    
    result_time, result_cheese = 0, 0
    while num_cheese:
        num_melted = bfs()
        result_cheese = num_melted
        num_cheese -= num_melted
        result_time += 1

    print(result_time)
    print(result_cheese)