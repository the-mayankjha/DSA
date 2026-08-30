# 🔴 Day 1 — Java CP Data Structures

# Part 12: Heap From Scratch

We just learned Java's `PriorityQueue`. Now let's understand what is happening **underneath it**.

A Heap is one of the few data structures where you should know both:

```text
Java implementation → PriorityQueue
From scratch         → Array + heap operations
```

For interviews, you should be able to explain and implement both.

---

# 1. What Exactly Is a Heap?

A heap is a **complete binary tree** satisfying a heap property.

There are two major types:

### Min Heap

```text
parent ≤ children
```

Example:

```text
          5
       /     \
     10       20
    /  \     /  \
   15  30   25  40
```

The smallest element is always at the root.

### Max Heap

```text
parent ≥ children
```

Example:

```text
          40
       /      \
     30        25
    /  \      /  \
   15  20    10   5
```

The largest element is always at the root.

---

# 2. Why Do We Store a Heap in an Array?

A complete binary tree has a very convenient structure.

For:

```text
          5
       /     \
     10       20
    /  \     /
   15  30   25
```

we store:

```text id="qz7xqs"
index:  0   1   2   3   4   5
value: [5, 10, 20, 15, 30, 25]
```

No explicit `Node` objects are required.

---

# 3. Index Relationships ⭐

For a node at index:

```java id="b4i9fk"
i
```

### Parent

```java id="uh1j2b"
(i - 1) / 2
```

### Left child

```java id="ajz33p"
2 * i + 1
```

### Right child

```java id="v3x7fc"
2 * i + 2
```

This is one of the most important formulas to memorize.

---

# 4. Example

Array:

```text id="u2s1y7"
[5, 10, 20, 15, 30, 25]
```

For index `1`:

```text id="gk7y3b"
value = 10
```

Parent:

```text id="xkj8qj"
(1 - 1) / 2 = 0
```

Left:

```text id="03fkga"
2(1) + 1 = 3
```

Right:

```text id="yefq8g"
2(1) + 2 = 4
```

So:

```text id="t8h4p5"
       5
      /
    10
   /  \
 15    30
```

Exactly right.

---

# 5. Min Heap Implementation

Let's implement a dynamic Min Heap.

```java id="l4v5o5"
class MinHeap {

    private int[] heap;
    private int size;

    MinHeap(int capacity) {
        heap = new int[capacity];
        size = 0;
    }
}
```

We maintain:

```text id="w4bq8a"
heap → actual array
size → number of elements
```

---

# 6. Swap Helper

We'll need swapping constantly:

```java id="e6f2wv"
private void swap(int i, int j) {
    int temp = heap[i];
    heap[i] = heap[j];
    heap[j] = temp;
}
```

---

# 7. Insert

The algorithm:

```text id="qfl2e6"
1. Put new element at the end.
2. Compare with parent.
3. If smaller → swap.
4. Continue upward.
```

Code:

```java id="b1u7eq"
public void offer(int value) {

    heap[size] = value;

    int index = size;
    size++;

    while (index > 0) {

        int parent = (index - 1) / 2;

        if (heap[parent] <= heap[index]) {
            break;
        }

        swap(parent, index);

        index = parent;
    }
}
```

This is called:

> **Heapify Up / Sift Up**

---

# 8. Example of Heapify Up

Start:

```text id="1q9i1r"
[5, 10, 20]
```

Insert:

```text id="3gpr8q"
3
```

Initially:

```text id="v0p0hy"
[5, 10, 20, 3]
```

Tree:

```text id="5ub2n7"
       5
      / \
    10   20
   /
  3
```

Parent of `3`:

```text id="g11g5e"
(3 - 1) / 2 = 1
```

Compare:

```text id="w0lv6x"
3 < 10
```

Swap:

```text id="l5v6fv"
[5, 3, 20, 10]
```

Now `3` is at index `1`.

Parent:

```text id="m5s2fo"
(1 - 1) / 2 = 0
```

Compare:

```text id="hj3w5q"
3 < 5
```

Swap:

```text id="v1bqni"
[3, 5, 20, 10]
```

Done.

---

# 9. `peek()`

Minimum is always at index `0`.

```java id="s3z0mi"
public int peek() {

    if (size == 0) {
        throw new RuntimeException("Heap is empty");
    }

    return heap[0];
}
```

Complexity:

```text id="lqx6aj"
O(1)
```

---

# 10. Remove Minimum

This is slightly more complicated.

Suppose:

```text id="5s8w4m"
[3, 5, 20, 10]
```

We remove:

```text
3
```

Take the last element:

```text id="pj0j4d"
10
```

Move it to root:

```text id="a6w2sh"
[10, 5, 20]
```

Now the heap property is broken.

We need to push `10` downward.

---

# 11. Heapify Down

Algorithm:

```text id="6o8wn8"
1. Start at root.
2. Find left/right children.
3. Find the smaller child.
4. If current > smaller child:
       swap
5. Continue downward.
```

Implementation:

```java id="y1x7td"
private void heapifyDown(int index) {

    while (true) {

        int left = 2 * index + 1;
        int right = 2 * index + 2;

        int smallest = index;

        if (left < size &&
            heap[left] < heap[smallest]) {

            smallest = left;
        }

        if (right < size &&
            heap[right] < heap[smallest]) {

            smallest = right;
        }

        if (smallest == index) {
            break;
        }

        swap(index, smallest);

        index = smallest;
    }
}
```

---

# 12. Complete `poll()`

```java id="2bspg5"
public int poll() {

    if (size == 0) {
        throw new RuntimeException("Heap is empty");
    }

    int result = heap[0];

    heap[0] = heap[size - 1];

    size--;

    heapifyDown(0);

    return result;
}
```

That's essentially the core of a Min Heap.

---

# 13. Complete MinHeap

Here's the complete implementation:

```java id="v43u7a"
class MinHeap {

    private int[] heap;
    private int size;

    MinHeap(int capacity) {
        heap = new int[capacity];
        size = 0;
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public void offer(int value) {

        if (size == heap.length) {
            throw new RuntimeException("Heap is full");
        }

        heap[size] = value;

        int index = size;
        size++;

        while (index > 0) {

            int parent = (index - 1) / 2;

            if (heap[parent] <= heap[index]) {
                break;
            }

            swap(parent, index);

            index = parent;
        }
    }

    public int peek() {

        if (size == 0) {
            throw new RuntimeException("Heap is empty");
        }

        return heap[0];
    }

    public int poll() {

        if (size == 0) {
            throw new RuntimeException("Heap is empty");
        }

        int result = heap[0];

        heap[0] = heap[size - 1];

        size--;

        heapifyDown(0);

        return result;
    }

    private void heapifyDown(int index) {

        while (true) {

            int left = 2 * index + 1;
            int right = 2 * index + 2;

            int smallest = index;

            if (left < size &&
                heap[left] < heap[smallest]) {

                smallest = left;
            }

            if (right < size &&
                heap[right] < heap[smallest]) {

                smallest = right;
            }

            if (smallest == index) {
                break;
            }

            swap(index, smallest);

            index = smallest;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
```

---

# 14. Max Heap

The implementation is almost identical.

Instead of looking for the smallest child:

```java id="guz4ob"
int largest = index;
```

and:

```java id="m5e5ly"
if (heap[left] > heap[largest]) {
    largest = left;
}
```

Likewise for the right child.

The heap property becomes:

```text id="s4iz5o"
parent ≥ children
```

---

# 15. Min Heap vs Max Heap

|                 | Min Heap   | Max Heap   |
| --------------- | ---------- | ---------- |
| Root            | Minimum    | Maximum    |
| Parent relation | ≤ children | ≥ children |
| `peek()`        | Minimum    | Maximum    |
| Insert          | O(log n)   | O(log n)   |
| Remove root     | O(log n)   | O(log n)   |

---

# 16. Heapify Up vs Heapify Down

This is worth memorizing.

### Insertion

```text id="7p2k3k"
Insert at bottom
      ↓
Heapify UP
```

### Removal

```text id="t3zv8u"
Replace root with last
      ↓
Heapify DOWN
```

Remember:

> **Insert → Up**
> **Delete root → Down**

---

# 17. Build Heap From Array

Suppose:

```text id="g4j9p4"
arr = [10, 5, 20, 2, 4]
```

You could insert each element:

```text id="k6rj5h"
offer(10)
offer(5)
offer(20)
offer(2)
offer(4)
```

That costs:

```text id="ez5byq"
O(n log n)
```

But there's a better approach.

Start heapifying from the last non-leaf node.

The last non-leaf index is:

```java id="rbxq26"
n / 2 - 1
```

Then:

```java id="kcl3jv"
for (int i = n / 2 - 1; i >= 0; i--) {
    heapifyDown(i);
}
```

This builds the heap in:

```text id="gq2q6p"
O(n)
```

This is an important interview question.

---

# 18. Why Are Leaves Ignored?

In:

```text id="1m7n0m"
[10, 5, 20, 2, 4]
```

indexes:

```text id="2m0q2u"
0  1  2  3  4
```

Tree:

```text id="gh6q0w"
       10
      /  \
     5    20
    / \
   2   4
```

Nodes `2` and `4` are leaves.

They don't have children, so there's nothing to heapify downward.

That's why we start at:

```text id="49t5fr"
n/2 - 1
```

---

# 19. Heap vs BST

Don't confuse them.

### Heap

Guarantees:

```text id="h5r4xe"
Parent ≤ children   (Min Heap)
```

But:

```text id="8x9t6s"
left subtree isn't necessarily < right subtree
```

### BST

Guarantees:

```text id="y2r5d9"
left < root < right
```

Heap is designed for:

> **Fast min/max access**

BST is designed for:

> **Ordered searching**

---

# 20. Heap vs Sorted Array

| Operation                   |      Heap | Sorted Array |
| --------------------------- | --------: | -----------: |
| Get minimum                 |      O(1) |         O(1) |
| Insert                      |  O(log n) |         O(n) |
| Remove minimum              |  O(log n) |         O(n) |
| Search arbitrary            |      O(n) |     O(log n) |
| Maintain dynamic priorities | Excellent |         Poor |

This is why PriorityQueue is so useful.

---

# 🧠 Heap Cheat Sheet

Memorize these formulas:

```java id="0m3o8c"
parent = (i - 1) / 2;

left = 2 * i + 1;

right = 2 * i + 2;
```

Operations:

```text id="v1h1ng"
Insert
  ↓
Bottom
  ↓
Heapify Up

Remove Root
  ↓
Last element → Root
  ↓
Heapify Down
```

Complexities:

```text id="l0qj0b"
peek       O(1)
insert     O(log n)
remove     O(log n)
build heap O(n)
```

---

# 🧪 Practice

Before moving to trees, try these:

### Level 1

1. Implement Min Heap.
2. Implement Max Heap.
3. Implement `offer()`.
4. Implement `peek()`.
5. Implement `poll()`.
6. Implement `heapifyUp()`.
7. Implement `heapifyDown()`.
8. Build a heap from an array.

### Level 2

9. Heap Sort.
10. Kth largest using heap.
11. Kth smallest using heap.
12. Top K frequent elements.
13. Merge K sorted arrays.
14. Median using two heaps.

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

⬜ Binary Tree
⬜ BST
⬜ Graph
⬜ Trie
⬜ DSU
```

# Next → 🌳 Binary Tree

Now we move into **Trees**, starting with the most fundamental structure: **Binary Tree**.

We'll learn the actual Java `Node` structure and then master the four traversals:

**Preorder → Inorder → Postorder → Level Order**

Those four are absolutely essential before we touch BSTs, recursion, or tree-based interview problems.

