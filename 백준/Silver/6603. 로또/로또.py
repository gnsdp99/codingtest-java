# 문제 6603 로또 (S2)
def solve(sequence, arr):
    if len(sequence.split()) == NUM_SELECT:
        print(sequence)
        return
    
    for i, num in enumerate(arr):
        solve(sequence + str(num) + " ", arr[i+1:])

if __name__ == "__main__":
    inputs = list(map(int, input().split()))
    NUM_SELECT = 6
    
    while inputs != [0]:
        k, S = inputs[0], inputs[1:]
        solve("", S)
        print()
        inputs = list(map(int, input().split()))