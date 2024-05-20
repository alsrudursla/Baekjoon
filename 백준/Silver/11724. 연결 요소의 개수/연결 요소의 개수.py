import sys
sys.setrecursionlimit(10000)
N,M=map(int,sys.stdin.readline().split())
matrix=[[0]*(N+1) for _ in range(N+1)]
vis=[0 for _ in range(N+1)]
cnt=0
for _ in range(M):
    a,b=map(int,sys.stdin.readline().split())
    matrix[a][b]=1
    matrix[b][a]=1
def dfs(v):
    vis[v]=1
    for j in range(1,N+1):
        if matrix[v][j]==1 and vis[j]==0:
            dfs(j)
for i in range(1,N+1):
    if vis[i]==0:
        dfs(i)
        cnt+=1
print(cnt)