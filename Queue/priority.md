# 🔴 Day 1 — Java CP Data Structures

# Part 11: PriorityQueue → Heap

This is a **very important structure for OAs**.

If `HashMap` gives you fast lookup, `PriorityQueue` gives you something different:

> **Efficiently access the smallest or largest element while elements are continuously added/removed.**

The underlying concept is a **Heap**.

---

# 1. What is a Priority Queue?

Imagine:

```text
Normal Queue:

10 → 20 → 30 → 40
↑
First comes out
```

A **priority queue** doesn't care who arrived first.

Instead, the element with the highest priority comes out first.

For a **Min Heap**:

```text
5, 10, 20, 30
↑
smallest comes out
```

For a **Max Heap**:

```text
30, 20, 10, 5
 ↑
largest comes out
```

---

# 2. Java PriorityQueue

Java provides:

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();
```

By default:

> **Java PriorityQueue is a Min Heap.**

So:

```java
pq.offer(30);
pq.offer(10);
pq.offer(20);
```

The smallest element is available through:

```java
pq.peek();
```

which returns:

```text
10
```

---

# 3. Core Operations

You should memorize these:

```java
pq.offer(x);    // insert
pq.peek();      // smallest/highest priority
pq.poll();      // remove highest priority
pq.size();      // size
pq.isEmpty();   // empty?
```

Example:

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();

pq.offer(30);
pq.offer(10);
pq.offer(20);

System.out.println(pq.peek());
System.out.println(pq.poll());
System.out.println(pq.poll());
```

Output:

```text
10
10
20
```

---

# 4. Important Difference from Queue

Normal Queue:

```java
Queue<Integer> q = new ArrayDeque<>();
```

```text
FIFO
```

PriorityQueue:

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();
```

```text
Priority-based
```

Example:

```text
Input:
30 10 20

Queue:
30 → 10 → 20

PriorityQueue:
10 → 20 → 30
```

---

# 5. Complexity

This is extremely important.

| Operation  | Complexity |
| ---------- | ---------: |
| `peek()`   |       O(1) |
| `offer()`  |   O(log n) |
| `poll()`   |   O(log n) |
| `remove()` |   O(log n) |
| `size()`   |       O(1) |

Why?

Because the underlying heap maintains its structure after insertion/removal.

---

# 6. What is a Heap?

A Heap is a **complete binary tree** satisfying a heap property.

### Min Heap

Every parent is:

```text
≤ children
```

Example:

```text
        5
       / \
     10   20
    / \   /
   15 30 25
```

The minimum is always at the root:

```text
5
```

### Max Heap

Every parent is:

```text
≥ children
```

Example:

```text
        30
       /  \
     20    25
    / \    /
   10 15  5
```

Maximum is at the root:

```text
30
```

---

# 7. Heap ≠ PriorityQueue

Important distinction:

> **Heap is the data structure/concept.**
> **PriorityQueue is Java's implementation/interface for priority-based access.**

For your CP preparation, you'll usually write:

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();
```

rather than implementing a heap from scratch.

But you **should understand heap implementation**, because heap questions are common in interviews.

We'll do that after learning the Java API.

---

# 8. Max Heap

Java's default is Min Heap.

For Max Heap:

```java
PriorityQueue<Integer> maxHeap =
    new PriorityQueue<>(Collections.reverseOrder());
```

Then:

```java
maxHeap.offer(10);
maxHeap.offer(30);
maxHeap.offer(20);
```

Now:

```java
maxHeap.peek();
```

returns:

```text
30
```

---

# 9. Max Heap — Modern Comparator Syntax

You can also write:

```java
PriorityQueue<Integer> maxHeap =
    new PriorityQueue<>((a, b) -> b - a);
```

But **don't use `b - a` blindly** with potentially large integers because subtraction can overflow.

Safer:

```java
PriorityQueue<Integer> maxHeap =
    new PriorityQueue<>((a, b) -> Integer.compare(b, a));
```

For normal CP constraints, you'll often see:

```java
Collections.reverseOrder()
```

which is simple and safe.

---

# 10. Min Heap

Default:

```java
PriorityQueue<Integer> minHeap =
    new PriorityQueue<>();
```

or explicitly:

```java
PriorityQueue<Integer> minHeap =
    new PriorityQueue<>((a, b) -> Integer.compare(a, b));
```

---

# 11. Min Heap vs Max Heap

Memorize:

```java
// Min Heap
PriorityQueue<Integer> minHeap = new PriorityQueue<>();

// Max Heap
PriorityQueue<Integer> maxHeap =
    new PriorityQueue<>(Collections.reverseOrder());
```

---

# 12. PriorityQueue Doesn't Store Elements in Sorted Order

This is a common misconception.

Suppose:

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();

pq.offer(5);
pq.offer(1);
pq.offer(10);
pq.offer(2);
```

Don't expect iteration to produce:

```text
1 2 5 10
```

The internal structure is a **heap**, not a sorted array.

The guarantee is:

```text
peek() → minimum
poll() → minimum
```

If you repeatedly poll:

```java
while (!pq.isEmpty()) {
    System.out.println(pq.poll());
}
```

you'll get:

```text
1
2
5
10
```

---

# 13. Why Not Just Sort?

Suppose numbers arrive dynamically:

```text
5
1
10
2
7
...
```

If you sort every time:

```text
insert
 ↓
sort
 ↓
insert
 ↓
sort
```

that's inefficient.

PriorityQueue gives:

```text
insert → O(log n)
get min → O(1)
remove min → O(log n)
```

This is the whole point.

---

# 14. Custom Objects

This becomes **very important**.

Suppose:

```java
class Student {
    String name;
    int marks;

    Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }
}
```

We want a PriorityQueue ordered by marks.

```java
PriorityQueue<Student> pq =
    new PriorityQueue<>(
        (a, b) -> Integer.compare(a.marks, b.marks)
    );
```

Now the student with the **lowest marks** has highest priority.

---

# 15. Max PriorityQueue of Objects

Highest marks first:

```java
PriorityQueue<Student> pq =
    new PriorityQueue<>(
        (a, b) -> Integer.compare(b.marks, a.marks)
    );
```

---

# 16. Multiple Conditions

Suppose we want:

1. Higher marks first
2. If marks equal → smaller name lexicographically

You can write:

```java
PriorityQueue<Student> pq =
    new PriorityQueue<>(
        (a, b) -> {
            if (a.marks != b.marks) {
                return Integer.compare(b.marks, a.marks);
            }

            return a.name.compareTo(b.name);
        }
    );
```

This is the beginning of **custom comparator mastery**.

---

# 17. `Comparator.comparing`

You can also write:

```java
PriorityQueue<Student> pq =
    new PriorityQueue<>(
        Comparator.comparingInt(s -> s.marks)
    );
```

For descending:

```java
PriorityQueue<Student> pq =
    new PriorityQueue<>(
        Comparator.comparingInt((Student s) -> s.marks)
                  .reversed()
    );
```

This is elegant, but for CP I recommend first becoming comfortable with:

```java
(a, b) -> Integer.compare(...)
```

because you'll encounter it constantly.

---

# 18. Pair in PriorityQueue

Java doesn't have a built-in general-purpose `Pair` in the standard collections API.

For CP, a common lightweight approach is:

```java
PriorityQueue<int[]> pq =
    new PriorityQueue<>(
        (a, b) -> Integer.compare(a[0], b[0])
    );
```

Suppose each element is:

```text
[value, index]
```

Add:

```java
pq.offer(new int[]{10, 3});
pq.offer(new int[]{5, 7});
pq.offer(new int[]{20, 1});
```

The smallest first element has priority.

So:

```java
int[] top = pq.poll();
```

gives:

```text
[5, 7]
```

---

# 19. Common Graph Usage

You'll eventually use:

```java
PriorityQueue<int[]> pq =
    new PriorityQueue<>(
        (a, b) -> Integer.compare(a[1], b[1])
    );
```

where:

```text
[node, distance]
```

This is the standard foundation for:

> **Dijkstra's Algorithm**

For example:

```text
[node = 5, distance = 12]
```

The queue prioritizes the smallest distance.

---

# 20. Top K Problems ⭐⭐⭐

One of the biggest uses of PriorityQueue is:

> **Find the K largest/smallest elements.**

Suppose:

```text
arr = [10, 5, 20, 8, 15]
k = 3
```

We want:

```text
20, 15, 10
```

A common approach:

### Keep a Min Heap of size K

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();

for (int x : arr) {

    pq.offer(x);

    if (pq.size() > k) {
        pq.poll();
    }
}
```

At the end:

```text
pq = [10, 15, 20]
```

Why?

The smallest among the current top K gets removed whenever size exceeds `K`.

This is a **very important pattern**.

---

# 21. K Smallest

For K smallest, use a **Max Heap**.

```java
PriorityQueue<Integer> pq =
    new PriorityQueue<>(Collections.reverseOrder());

for (int x : arr) {

    pq.offer(x);

    if (pq.size() > k) {
        pq.poll();
    }
}
```

Now the largest among the current K smallest gets removed.

---

# 22. The Top-K Rule

Memorize this:

```text
K largest
    ↓
Min Heap of size K

K smallest
    ↓
Max Heap of size K
```

This looks backwards initially.

Why?

### K largest

We want to eliminate the **smallest** from our current candidates.

So use:

```text
Min Heap
```

### K smallest

We want to eliminate the **largest**.

So use:

```text
Max Heap
```

This becomes extremely useful in OAs.

---

# 23. Find Kth Largest

Example:

```text
arr = [3, 2, 1, 5, 6, 4]
k = 2
```

Answer:

```text
5
```

Use:

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();

for (int x : arr) {

    pq.offer(x);

    if (pq.size() > k) {
        pq.poll();
    }
}

int kthLargest = pq.peek();
```

---

# 24. Merge K Sorted Lists

Another major application:

```text
List 1 → 1 4 7
List 2 → 2 5 8
List 3 → 3 6 9
```

A PriorityQueue can track the smallest current element from each list.

This gives:

```text
1 2 3 4 5 6 7 8 9
```

This is a classic heap problem.

---

# 25. Heap Sort?

A heap can be used for sorting.

But in normal Java CP:

```java
Arrays.sort(arr);
```

is usually simpler if all you need is sorting.

Use PriorityQueue when you need **dynamic priority-based processing**, not simply because you can.

---

# 26. PriorityQueue vs TreeSet

This is an important comparison.

### PriorityQueue

```text
Need:
smallest/largest repeatedly
```

Operations:

```text
peek → O(1)
poll → O(log n)
```

### TreeSet

```text
Need:
sorted unique values
floor
ceiling
lower
higher
```

Operations:

```text
O(log n)
```

Think:

```text
Repeatedly remove minimum?
        ↓
PriorityQueue

Need nearest value / floor / ceiling?
        ↓
TreeSet
```

---

# 27. PriorityQueue vs Array

If you simply need:

```text
maximum of an existing array
```

you don't necessarily need a heap.

You can scan:

```java
int max = arr[0];

for (int x : arr) {
    max = Math.max(max, x);
}
```

That's:

```text
O(n)
```

A heap would unnecessarily introduce:

```text
O(n log n)
```

So choose the data structure based on the operations you need.

---

# 28. Heap Implementation — From Scratch

You should understand this for interviews.

A heap is commonly stored in an **array**.

For a node at index `i`:

### Parent

```java
(i - 1) / 2
```

### Left child

```java
2 * i + 1
```

### Right child

```java
2 * i + 2
```

Example:

```text
          5
        /   \
      10     20
     /  \
   15   30
```

Array:

```text
[5, 10, 20, 15, 30]
```

Indexes:

```text
        0
      /   \
     1     2
    / \
   3   4
```

---

# 29. Min Heap — Insert

Suppose:

```text
[5, 10, 20]
```

Insert:

```text
3
```

Initially:

```text
[5, 10, 20, 3]
```

Tree:

```text
      5
    /   \
   10   20
  /
 3
```

Now `3` violates the heap property.

We repeatedly swap with its parent:

```text
3 ↔ 10
```

then:

```text
3 ↔ 5
```

Final:

```text
      3
    /   \
   5    20
  /
 10
```

Array:

```text
[3, 5, 20, 10]
```

This is called:

> **Heapify Up / Bubble Up / Sift Up**

---

# 30. Remove Minimum

Suppose:

```text
[3, 5, 20, 10]
```

Remove root `3`.

Replace root with last element:

```text
[10, 5, 20]
```

Now:

```text
10
/ \
5 20
```

violates min-heap property.

Swap:

```text
10 ↔ 5
```

Final:

```text
[5, 10, 20]
```

This is:

> **Heapify Down / Sift Down**

---

# 31. Why Heap Operations Are O(log n)

A heap is a complete binary tree.

Its height is:

```text
O(log n)
```

Insertion may move an element from the bottom to the root:

```text
O(log n)
```

Removal may move an element from the root downward:

```text
O(log n)
```

That's the reason.

---

# 32. Build Heap

If you have an array:

```text
[5, 3, 8, 1, 2]
```

you can construct a heap in:

```text
O(n)
```

using bottom-up heapification.

This is an important theoretical fact.

---

# 🧠 PriorityQueue Cheat Sheet

### Min Heap

```java
PriorityQueue<Integer> minHeap =
    new PriorityQueue<>();
```

### Max Heap

```java
PriorityQueue<Integer> maxHeap =
    new PriorityQueue<>(Collections.reverseOrder());
```

### Operations

```java
pq.offer(x);
pq.peek();
pq.poll();
pq.size();
pq.isEmpty();
```

### Custom comparator

```java
PriorityQueue<int[]> pq =
    new PriorityQueue<>(
        (a, b) -> Integer.compare(a[0], b[0])
    );
```

### Top K

```text
K largest → Min Heap
K smallest → Max Heap
```

---

# 🧪 Practice

### Level 1

1. Create a min heap.
2. Create a max heap.
3. Insert 10 numbers and remove them in priority order.
4. Find the minimum using PriorityQueue.
5. Find the maximum using PriorityQueue.
6. Find Kth smallest.
7. Find Kth largest.
8. Sort an array using PriorityQueue.

### Level 2

9. Top K largest elements.
10. Top K smallest elements.
11. K closest elements to X.
12. Merge K sorted arrays.
13. Kth largest element in a stream.
14. Connect ropes with minimum cost.
15. Median of a data stream.
16. Dijkstra's algorithm.

Problems **11–16** are where PriorityQueue starts becoming a major algorithmic weapon.

---

# 📍 Progress

```text
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
   ├── put / get
   ├── getOrDefault
   ├── Frequency
   └── Lookup

✅ HashSet
   ├── Uniqueness
   ├── Membership
   └── Duplicate detection

✅ TreeSet
   ├── Sorted unique values
   ├── floor / ceiling
   └── lower / higher

✅ TreeMap
   ├── Sorted key-value pairs
   ├── floor / ceiling
   └── lower / higher

✅ PriorityQueue
   ├── Min Heap
   ├── Max Heap
   ├── Comparator
   └── Top-K

⬜ Heap (from scratch)
⬜ Binary Tree
⬜ BST
⬜ Graph
⬜ Trie
⬜ DSU
```

## Next → **Heap from Scratch + Binary Tree**

We'll first finish the heap implementation properly, then move into **Binary Trees**, where you'll learn:

```text
Node
 ↓
Tree construction
 ↓
Preorder
Inorder
Postorder
Level Order
 ↓
Height
Diameter
Search
```

Those traversals are foundational for almost every tree interview question.

