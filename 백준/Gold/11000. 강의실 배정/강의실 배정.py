# 백준 11000. 강의실 배정 (G5)
import sys
input = sys.stdin.readline
import heapq

def assign_classroom(classes):
    num_classroom = 0
    finish_heap = []
    for start, finish in classes:
        if not finish_heap or start < finish_heap[0]:
            num_classroom += 1
        else:
            heapq.heappop(finish_heap)
        heapq.heappush(finish_heap, finish)
        
    return num_classroom
    
if __name__ == "__main__":
    N = int(input())
    classes = []
    for _ in range(N):
        S, T = map(int, input().split())
        classes.append((S, T))
    classes.sort()
        
    result = assign_classroom(classes)
    print(result)