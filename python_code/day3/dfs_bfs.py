# ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
# SKALA
# 작성 목적 : DFS vs BFS
# 작성자 : 강창진
# 작성일 : 2025-03-06
#
# 1) 탐색 순선 비교
# BFS : A B C D E F G H
# DFS : A B D E H C F G
# 
# 2) 어떤 상황에서 DFS가 유리한지, BFS가 유리한지 정리
# BFS가 유리한 경우 : 특정 목표 노드까지 도달하는 데 필요한 최소 이동 횟수를 구할 때
# DFS가 유리한 경우 : 깊이가 깊은 노드까지 빠르게 도달해야 할 때
#
# 3) 탐색 전략
# BFS는 시작지점에서 가까운 거리의 노드부터 탐색하므로 거리를 구할 때 사용하는 것이 좋다
# DFS는 깊이를 우선적으로 탐색하므로 특정 노드까지 도달해야 할 때 사용하는 것이 좋다
#
# ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

from collections import deque

def bfs(graph, start):
    queue = deque([start])
    visited = set([start])
    while queue:
        node = queue.popleft()
        print(node, end=" ")
        for neighbor in graph[node]:
            queue.append(neighbor)
            visited.add(neighbor)


def dfs(graph, start, visited=None):
    if visited is None:
        visited = set()
    visited.add(start)
    print(start, end=" ")
    for neighbor in graph[start]:
        if neighbor not in visited:
            dfs(graph, neighbor, visited)

graph = {
    'A': ['B', 'C'],
    'B': ['D', 'E'],
    'C': ['F', 'G'],
    'D': [],
    'E': ['H'],
    'F': [],
    'G': [],
    'H': []
}


print("BFS 탐색 순서:")
bfs(graph, 'A')
print("\nDFS 탐색 순서:")
dfs(graph, 'A')