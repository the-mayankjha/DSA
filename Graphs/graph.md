# 🕸️ Part 15 — Graphs

Graphs are probably the **most important remaining data structure** before we move into actual DSA patterns.

And unlike arrays, stacks, queues, etc., there isn't just one standard way to represent a graph.

For competitive programming, you should be comfortable with:

```text
Graph
├── Adjacency Matrix
├── Adjacency List ⭐
├── Weighted Graph
├── Directed Graph
└── Undirected Graph
```

The **Adjacency List** is the one you'll use most often.

---

# 1. What Is a Graph?

A graph consists of:

```text
Vertices (Nodes)
+
Edges (Connections)
```

Example:

```text id="2w5c9v"
      1
     / \
    2   3
    |   |
    4---5
```

Vertices:

```text id="6z19p1"
1, 2, 3, 4, 5
```

Edges:

```text id="h7f5q9"
(1,2)
(1,3)
(2,4)
(3,5)
(4,5)
```

---

# 2. Graph Terminology

### Vertex / Node

A point:

```text id="p8h1m0"
1
```

### Edge

Connection:

```text id="w4v2y6"
1 —— 2
```

### Degree

Number of edges connected to a vertex.

Example:

```text id="2n7h9c"
    2
    |
1 — 3 — 4
```

Degree of `3`:

```text id="wq4s8y"
3
```

---

# 3. Directed Graph

Edges have a direction.

```text id="k2g6n8"
1 → 2
↓
3
```

`1 → 2` does NOT mean:

```text id="z8x5w1"
2 → 1
```

Example:

```text id="1b4x9a"
1 → 2
2 → 3
3 → 1
```

---

# 4. Undirected Graph

Edges work in both directions.

```text id="9x8q2k"
1 —— 2
```

means:

```text id="5y6n2r"
1 → 2
2 → 1
```

Most graph problems explicitly tell you whether the graph is directed or undirected.

---

# 5. Weighted Graph

Edges have values/costs.

```text id="z4r9m2"
     5
1 -------- 2
 \        /
  2      7
   \    /
      3
```

Edges:

```text id="1l0h4x"
1 --5--> 2
1 --2--> 3
3 --7--> 2
```

Weights commonly represent:

```text
Distance
Cost
Time
Capacity
Risk
```

---

# 6. Unweighted Graph

Edges don't have weights.

```text id="7f5x1j"
1 —— 2
|    |
3 —— 4
```

For unweighted shortest paths:

> **BFS is often the first algorithm to consider.**

---

# 7. Graph Representation #1 — Adjacency Matrix

An adjacency matrix uses a 2D array.

Suppose:

```text id="y0n8ab"
1 —— 2
|
3
```

Matrix:

```text id="d4s8r0"
    1 2 3
1 [ 0 1 1 ]
2 [ 1 0 0 ]
3 [ 1 0 0 ]
```

`matrix[i][j] = 1` means:

> There is an edge from `i` to `j`.

---

# 8. Java Adjacency Matrix

```java id="6h1m1v"
int n = 5;

int[][] graph = new int[n][n];
```

Add undirected edge:

```java id="5y2m6k"
graph[u][v] = 1;
graph[v][u] = 1;
```

For directed:

```java id="x6h8p2"
graph[u][v] = 1;
```

only.

---

# 9. Weighted Adjacency Matrix

Suppose:

```text id="r7d3m1"
1 --5-- 2
```

Then:

```java id="x8z4qk"
graph[1][2] = 5;
graph[2][1] = 5;
```

But there's a problem:

> How do we distinguish "no edge" from an edge whose weight is `0`?

Usually you'd use something like:

```java id="q6m1v9"
Integer.MAX_VALUE
```

or another sentinel value.

For most CP graph problems, adjacency lists are more convenient.

---

# 10. Matrix Complexity

For `V` vertices:

```text id="y6b4x2"
Space = O(V²)
```

Checking whether an edge exists:

```text id="x9f3k8"
O(1)
```

But if the graph has very few edges, the matrix wastes lots of space.

That's why we have:

# Adjacency List ⭐⭐⭐

---

# 11. Adjacency List

Instead of storing every possible connection, we store only the edges that actually exist.

Graph:

```text id="6k7p2m"
1 —— 2
|
3
```

Adjacency list:

```text id="0r8s3d"
1 → [2, 3]
2 → [1]
3 → [1]
```

Much more memory efficient for sparse graphs.

---

# 12. Java Adjacency List

The most common implementation:

```java id="2h4q7w"
List<List<Integer>> graph = new ArrayList<>();
```

Create lists:

```java id="4y7n2m"
int n = 5;

List<List<Integer>> graph = new ArrayList<>();

for (int i = 0; i < n; i++) {
    graph.add(new ArrayList<>());
}
```

Now:

```text id="v6n8q2"
graph
 ├── 0 → []
 ├── 1 → []
 ├── 2 → []
 ├── 3 → []
 └── 4 → []
```

---

# 13. Add an Undirected Edge

Suppose:

```text id="p1j8c4"
1 —— 2
```

Add:

```java id="h6z3x0"
graph.get(1).add(2);
graph.get(2).add(1);
```

Now:

```text id="9c4v7k"
1 → [2]
2 → [1]
```

---

# 14. Add More Edges

Graph:

```text id="s8y2w6"
1 —— 2
|    |
3 —— 4
```

Code:

```java id="j3x8f1"
addEdge(1, 2);
addEdge(1, 3);
addEdge(2, 4);
addEdge(3, 4);
```

Adjacency list:

```text id="b4n7k2"
1 → [2, 3]
2 → [1, 4]
3 → [1, 4]
4 → [2, 3]
```

---

# 15. Create an `addEdge()` Function

For CP, don't repeatedly write the same code.

```java id="n9q2x5"
static void addEdge(
        List<List<Integer>> graph,
        int u,
        int v) {

    graph.get(u).add(v);
    graph.get(v).add(u);
}
```

Then:

```java id="7g5p3r"
addEdge(graph, 1, 2);
addEdge(graph, 1, 3);
addEdge(graph, 2, 4);
```

---

# 16. Directed Graph

For:

```text id="c5r7m2"
1 → 2
```

only:

```java id="0q9w4n"
graph.get(1).add(2);
```

Don't add:

```java id="m1x8v6"
graph.get(2).add(1);
```

So:

```text id="3w7f1z"
Directed:
u → v

Undirected:
u ↔ v
```

---

# 17. Input Format in CP ⭐⭐⭐

You'll frequently receive:

```text id="3j6v8k"
n m
u1 v1
u2 v2
u3 v3
...
```

where:

```text
n = number of vertices
m = number of edges
```

Example:

```text id="7p2s5x"
5 4
1 2
1 3
2 4
3 5
```

Read:

```java id="v8f4m1"
Scanner sc = new Scanner(System.in);

int n = sc.nextInt();
int m = sc.nextInt();

List<List<Integer>> graph = new ArrayList<>();

for (int i = 0; i < n; i++) {
    graph.add(new ArrayList<>());
}

for (int i = 0; i < m; i++) {

    int u = sc.nextInt();
    int v = sc.nextInt();

    graph.get(u).add(v);
    graph.get(v).add(u);
}
```

For serious CP, we'll later replace `Scanner` with a **FastScanner** because `Scanner` can be slow for very large input.

---

# 18. Important Indexing Issue

Problems may use:

```text
0-based
```

vertices:

```text
0, 1, 2, 3, 4
```

or:

```text
1-based
```

vertices:

```text
1, 2, 3, 4, 5
```

If input is:

```text id="j8r4p6"
1 2
```

and you create:

```java id="k4w7m9"
new ArrayList<>(n)
```

valid indices are:

```text
0 ... n-1
```

So either:

### Option 1

Create:

```java id="n1y5q2"
n + 1
```

lists and ignore index `0`.

### Option 2

Convert:

```java id="a4m7z8"
u--;
v--;
```

I generally prefer **`n + 1` for 1-based problem statements** because it keeps the input values unchanged.

---

# 19. Weighted Adjacency List

Now suppose:

```text id="5n8q1r"
1 --5-- 2
1 --2-- 3
```

We need:

```text
neighbor + weight
```

A simple CP approach:

```java id="2w6h9p"
List<List<int[]>> graph = new ArrayList<>();
```

Each:

```java id="r3v7k1"
int[]{neighbor, weight}
```

represents:

```text
[neighbor, weight]
```

---

# 20. Weighted Graph Example

```java id="n7c2w4"
int n = 5;

List<List<int[]>> graph = new ArrayList<>();

for (int i = 0; i < n; i++) {
    graph.add(new ArrayList<>());
}
```

Add:

```java id="4k8z2m"
graph.get(u).add(new int[]{v, weight});
graph.get(v).add(new int[]{u, weight});
```

For:

```text id="s5x1p9"
1 --5-- 2
```

we get:

```text id="2g7v4b"
1 → [ [2,5] ]
2 → [ [1,5] ]
```

---

# 21. Custom Edge Class

For interviews, a class can be cleaner:

```java id="6v9s2x"
class Edge {

    int to;
    int weight;

    Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}
```

Then:

```java id="2y5k8n"
List<List<Edge>> graph = new ArrayList<>();
```

Add:

```java id="q3m7v1"
graph.get(u).add(new Edge(v, weight));
```

For CP, I often prefer:

```java id="r1n5x8"
int[]
```

because it's shorter and faster to write.

For interviews, `Edge` can make the code easier to explain.

---

# 22. Graph + Queue = BFS ⭐⭐⭐

You've already learned:

```text id="5y3x9n"
Queue
```

Now connect it to graphs.

Graph:

```text id="4p8k2m"
      1
     / \
    2   3
    |   |
    4---5
```

Start BFS from `1`.

Use:

```java id="k9x2c5"
Queue<Integer> queue = new ArrayDeque<>();
```

And:

```java id="z5m8q1"
boolean[] visited = new boolean[n];
```

---

# 23. BFS Implementation

```java id="n2v6k8"
static void bfs(
        List<List<Integer>> graph,
        int start) {

    int n = graph.size();

    boolean[] visited = new boolean[n];

    Queue<Integer> queue = new ArrayDeque<>();

    queue.offer(start);
    visited[start] = true;

    while (!queue.isEmpty()) {

        int node = queue.poll();

        System.out.print(node + " ");

        for (int neighbor : graph.get(node)) {

            if (!visited[neighbor]) {

                visited[neighbor] = true;

                queue.offer(neighbor);
            }
        }
    }
}
```

---

# 24. Why `visited[]`?

Graphs can contain cycles.

Example:

```text id="4z6c2m"
1 —— 2
|    |
4 —— 3
```

If we don't track visited nodes:

```text id="a3k7p9"
1 → 2 → 3 → 4 → 1 → 2 → ...
```

We could loop forever.

So:

```java id="7g5x1m"
boolean[] visited
```

is fundamental to graph traversal.

---

# 25. BFS Complexity

For adjacency list:

```text id="w6p3k9"
O(V + E)
```

where:

```text
V = vertices
E = edges
```

Why?

Each vertex is visited once.

Each edge is examined a constant number of times.

Space:

```text id="q8m2v5"
O(V)
```

for visited + queue, excluding the graph itself.

---

# 26. Graph + Stack/Recursion = DFS ⭐⭐⭐

You've already learned Stack.

DFS means:

> **Go as deep as possible before backtracking.**

Graph:

```text id="s3k9w2"
      1
     / \
    2   3
    |
    4
```

DFS might visit:

```text id="h7m1q4"
1 → 2 → 4 → 3
```

---

# 27. DFS Using Recursion

```java id="z9q4k2"
static void dfs(
        List<List<Integer>> graph,
        int node,
        boolean[] visited) {

    visited[node] = true;

    System.out.print(node + " ");

    for (int neighbor : graph.get(node)) {

        if (!visited[neighbor]) {
            dfs(graph, neighbor, visited);
        }
    }
}
```

Call:

```java id="w4m8p6"
boolean[] visited = new boolean[n];

dfs(graph, 0, visited);
```

---

# 28. DFS Using Explicit Stack

Instead of recursion:

```java id="y8k2v4"
Stack<Integer> stack = new Stack<>();
```

But in modern Java CP, use:

```java id="h4n7q1"
Deque<Integer> stack = new ArrayDeque<>();
```

Implementation:

```java id="q2v9m5"
static void dfs(
        List<List<Integer>> graph,
        int start) {

    boolean[] visited =
        new boolean[graph.size()];

    Deque<Integer> stack =
        new ArrayDeque<>();

    stack.push(start);

    while (!stack.isEmpty()) {

        int node = stack.pop();

        if (visited[node]) {
            continue;
        }

        visited[node] = true;

        System.out.print(node + " ");

        for (int neighbor : graph.get(node)) {

            if (!visited[neighbor]) {
                stack.push(neighbor);
            }
        }
    }
}
```

---

# 29. BFS vs DFS

This distinction should become automatic.

### BFS

```text id="x5c9w3"
Queue
 ↓
Level by level
```

Useful for:

```text
Shortest path in unweighted graph
Level traversal
Minimum number of edges
Multi-source BFS
```

### DFS

```text id="r3n7v2"
Stack / Recursion
 ↓
Go deep
```

Useful for:

```text
Connected components
Cycle detection
Topological sorting
Backtracking
Bridges
Articulation points
```

---

# 30. Graph Data Structure Cheat Sheet

### Unweighted Undirected

```java id="b8n2q6"
List<List<Integer>> graph =
    new ArrayList<>();
```

Add:

```java id="j6x4p8"
graph.get(u).add(v);
graph.get(v).add(u);
```

### Directed

```java id="x7m1c5"
graph.get(u).add(v);
```

### Weighted

```java id="v4k9s2"
List<List<int[]>> graph =
    new ArrayList<>();
```

Add:

```java id="p2q7m5"
graph.get(u).add(new int[]{v, weight});
```

Undirected weighted:

```java id="n8x3r6"
graph.get(u).add(new int[]{v, weight});
graph.get(v).add(new int[]{u, weight});
```

---

# 31. Graph Representations Comparison

| Representation |  Space | Edge lookup | Best for        |
| -------------- | -----: | ----------: | --------------- |
| Matrix         |  O(V²) |        O(1) | Dense graphs    |
| Adjacency List | O(V+E) |   O(degree) | Most CP         |
| Edge List      |   O(E) |        O(E) | Some algorithms |

For your OA preparation:

> **Master adjacency lists first.**

---

# 32. Special Graph Structures

You'll encounter:

### Tree

A connected graph with:

```text id="m7p3q9"
V - 1 edges
```

and no cycles.

### DAG

Directed Acyclic Graph:

```text id="q8k2x5"
No directed cycles
```

Important for:

```text
Topological Sort
```

### Complete Graph

Every pair of vertices is connected.

### Bipartite Graph

Vertices can be divided into two groups such that no edge exists within the same group.

We'll study these as **patterns/problems**, not as separate data structures.

---

# 33. Graph + Everything You've Learned

This is where your data structures start connecting.

```text id="3m7x9k"
                 GRAPH
                   │
       ┌───────────┼────────────┐
       ↓           ↓            ↓
     Queue       Stack      HashSet
       │           │            │
      BFS         DFS         Visited
       │           │
       └──────┬────┘
              ↓
        Graph Algorithms
              │
       ┌──────┼───────┐
       ↓      ↓       ↓
    Shortest  Cycle  Components
       │
       ↓
 PriorityQueue
       │
       ↓
    Dijkstra
```

This is why we're learning the structures **before** patterns.

---

# 🧠 Graph Cheat Sheet

For an ordinary undirected graph:

```java id="9x4m2k"
List<List<Integer>> graph =
    new ArrayList<>();

for (int i = 0; i < n; i++) {
    graph.add(new ArrayList<>());
}

for (int i = 0; i < m; i++) {

    int u = sc.nextInt();
    int v = sc.nextInt();

    graph.get(u).add(v);
    graph.get(v).add(u);
}
```

BFS:

```java id="q5n8v3"
Queue<Integer> q = new ArrayDeque<>();
boolean[] visited = new boolean[n];

q.offer(start);
visited[start] = true;

while (!q.isEmpty()) {

    int node = q.poll();

    for (int next : graph.get(node)) {

        if (!visited[next]) {

            visited[next] = true;
            q.offer(next);
        }
    }
}
```

DFS:

```java id="t6k1w8"
void dfs(int node) {

    visited[node] = true;

    for (int next : graph.get(node)) {

        if (!visited[next]) {
            dfs(next);
        }
    }
}
```

---

# 🧪 Practice

### Level 1

1. Implement an adjacency matrix.
2. Implement an adjacency list.
3. Add directed edges.
4. Add undirected edges.
5. Add weighted edges.
6. Print adjacency list.
7. BFS.
8. DFS.
9. Count connected components.
10. Check whether a path exists between two nodes.

### Level 2

11. Detect cycle in an undirected graph.
12. Detect cycle in a directed graph.
13. Find shortest path in an unweighted graph.
14. Check whether graph is bipartite.
15. Number of islands.
16. Flood fill.
17. Topological sorting.
18. Dijkstra.
19. Minimum Spanning Tree.
20. Union-Find / DSU.

Don't try to solve 11–20 yet. These are **algorithm/pattern problems** we'll tackle after finishing the basic data structures.

---

# 📍 Progress

```text id="e8m4p2"
JAVA CP DATA STRUCTURES

✅ Arrays
✅ ArrayList
✅ String
   ├── char[]
   └── StringBuilder

✅ Linked List
   ├── Singly
   └── Doubly

✅ Stack
   ├── Array
   ├── Linked List
   └── ArrayDeque

✅ Queue
   ├── Array
   ├── Circular Queue
   ├── Linked List
   └── ArrayDeque

✅ Deque
   └── ArrayDeque

✅ HashMap
   ├── Frequency
   └── Lookup

✅ HashSet
   ├── Uniqueness
   └── Membership

✅ TreeSet
   ├── Sorted values
   └── Floor/Ceiling

✅ TreeMap
   ├── Sorted keys
   └── Floor/Ceiling

✅ PriorityQueue
   ├── Min Heap
   ├── Max Heap
   └── Comparator

✅ Heap
   ├── Array representation
   ├── Heapify Up
   ├── Heapify Down
   └── Build Heap

✅ Binary Tree
   ├── Node
   ├── DFS
   ├── BFS
   └── Basic operations

✅ BST
   ├── Insert
   ├── Search
   ├── Delete
   ├── Min / Max
   ├── Floor / Ceiling
   └── Validation

✅ Graph
   ├── Matrix
   ├── Adjacency List
   ├── Directed
   ├── Undirected
   ├── Weighted
   ├── BFS
   └── DFS

⬜ Trie
⬜ DSU
```

## Next → 🔤 Trie

We're almost done with the **core data structures**.

Trie is specialized for **strings/prefixes**:

```text
       root
      /    \
     c      d
     |
     a
     |
     t
```

We'll implement:

* `TrieNode`
* `insert()`
* `search()`
* `startsWith()`
* Prefix search
* Word dictionary
* Complexity

Then we'll finish with **DSU (Disjoint Set Union)**.

After that, you'll have a solid **Java CP data-structure toolkit**, and we can move to the much more important phase:

> **DSA Patterns → OA Problems → Interview Preparation.**

