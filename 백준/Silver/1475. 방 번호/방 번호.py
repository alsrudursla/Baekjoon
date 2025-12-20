import math

N=list(map(int,str(input())))
A=[0]*10

for i in N:
    if i==6 or i==9:
        A[6]+=1
    else:
        A[i]+=1
        
A[6] = math.ceil(A[6]/2)
print(max(A));