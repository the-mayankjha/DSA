# 🌳 Part 13 — Binary Tree

Now we enter **Trees**, one of the most important areas for interviews.

For now, don't worry about solving difficult tree problems.

Our goal is:

> **Learn how to represent, build, traverse, and manipulate a Binary Tree in Java for CP.**

---

# 1. What Is a Binary Tree?

A tree is a hierarchical data structure.

A **Binary Tree** is a tree where each node has **at most two children**:

```text
           10
          /  \
         5    20
        / \     \
       3   7     30
```

Each node can have:

```text
0 children
1 child
2 children
```

---

# 2. Important Terminology

For:

```text
           10
          /  \
         5    20
        / \
       3   7
```

### Root

The top node:

```text
10
```

### Parent

`10` is parent of:

```text
5, 20
```

### Children

`5` and `20` are children of `10`.

### Leaf

A node with no children:

```text
3
7
20
```

### Subtree

The tree rooted at `5`:

```text
      5
     / \
    3   7
```

---

# 3. Java Representation

Unlike arrays, a binary tree isn't normally stored in one simple Java array.

We create a `Node`.

```java
class Node {

    int data;

    Node left;
    Node right;

    Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
```

The structure is:

```text
Node
 ├── data
 ├── left
 └── right
```

---

# 4. Creating a Tree Manually

Suppose we want:

```text
           10
          /  \
         5    20
        / \
       3   7
```

Code:

```java
Node root = new Node(10);

root.left = new Node(5);
root.right = new Node(20);

root.left.left = new Node(3);
root.left.right = new Node(7);
```

Now:

```text
           root
            ↓
           10
          /  \
         5    20
        / \
       3   7
```

---

# 5. Understanding References

This:

```java
root.left = new Node(5);
```

means:

```text
root
 ↓
[10]
 /
↓
[5]
```

And:

```java
root.left.left = new Node(3);
```

means:

```text
       10
       /
      5
     /
    3
```

This is why tree problems heavily use **references**.

---

# 6. Complete Example

```java
class Node {

    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
    }
}

public class Main {

    public static void main(String[] args) {

        Node root = new Node(10);

        root.left = new Node(5);
        root.right = new Node(20);

        root.left.left = new Node(3);
        root.left.right = new Node(7);
    }
}
```

---

# 7. The Most Important Part — Traversals

Once you have a tree:

```text
           10
          /  \
         5    20
        / \
       3   7
```

How do you visit every node?

There are four fundamental traversals:

```text
1. Preorder
2. Inorder
3. Postorder
4. Level Order
```

The first three are **DFS-style traversals**.

Level Order is **BFS-style**.

---

# 8. Preorder Traversal

Rule:

> **Root → Left → Right**

Remember:

```text
PRE = Root comes BEFORE children
```

For:

```text
           10
          /  \
         5    20
        / \
       3   7
```

Preorder:

```text
10 → 5 → 3 → 7 → 20
```

---

# 9. Preorder Implementation

```java
void preorder(Node root) {

    if (root == null) {
        return;
    }

    System.out.print(root.data + " ");

    preorder(root.left);

    preorder(root.right);
}
```

The order of the three operations is:

```text
print(root)
↓
preorder(left)
↓
preorder(right)
```

That's preorder.

---

# 10. Understanding the Recursion

For:

```text
           10
          /  \
         5    20
        / \
       3   7
```

Call:

```java
preorder(10);
```

First:

```text
print 10
```

Then:

```text
preorder(5)
```

Print:

```text
5
```

Then:

```text
preorder(3)
```

Print:

```text
3
```

`3` has no children → return.

Then:

```text
preorder(7)
```

Print:

```text
7
```

Finally:

```text
preorder(20)
```

Print:

```text
20
```

Result:

```text
10 5 3 7 20
```

---

# 11. Inorder Traversal ⭐

Rule:

> **Left → Root → Right**

For the same tree:

```text
           10
          /  \
         5    20
        / \
       3   7
```

Inorder:

```text
3 → 5 → 7 → 10 → 20
```

Implementation:

```java
void inorder(Node root) {

    if (root == null) {
        return;
    }

    inorder(root.left);

    System.out.print(root.data + " ");

    inorder(root.right);
}
```

---

# 12. Inorder Memory Trick

```text
IN
↓
Root is IN between left and right
```

```text
Left
 ↓
Root
 ↓
Right
```

---

# 13. Postorder

Rule:

> **Left → Right → Root**

For:

```text
           10
          /  \
         5    20
        / \
       3   7
```

Result:

```text
3 → 7 → 5 → 20 → 10
```

Implementation:

```java
void postorder(Node root) {

    if (root == null) {
        return;
    }

    postorder(root.left);

    postorder(root.right);

    System.out.print(root.data + " ");
}
```

Root comes **last**.

---

# 14. The Three DFS Traversals

This is worth memorizing permanently:

```text
              ROOT
             /    \
            LEFT  RIGHT
```

### Preorder

```text
ROOT → LEFT → RIGHT
```

### Inorder

```text
LEFT → ROOT → RIGHT
```

### Postorder

```text
LEFT → RIGHT → ROOT
```

Only the **position of ROOT** changes.

```text
PRE       ROOT first
IN        ROOT middle
POST      ROOT last
```

---

# 15. Level Order Traversal ⭐⭐⭐

Now something different.

Instead of going deep first, we process **level by level**.

```text
           10          ← Level 0
          /  \
         5    20        ← Level 1
        / \
       3   7            ← Level 2
```

Level Order:

```text
10 → 5 → 20 → 3 → 7
```

This is essentially **BFS**.

And what did we just learn?

```text
Queue
```

Exactly.

---

# 16. Level Order Using Queue

```java
void levelOrder(Node root) {

    if (root == null) {
        return;
    }

    Queue<Node> queue = new ArrayDeque<>();

    queue.offer(root);

    while (!queue.isEmpty()) {

        Node current = queue.poll();

        System.out.print(current.data + " ");

        if (current.left != null) {
            queue.offer(current.left);
        }

        if (current.right != null) {
            queue.offer(current.right);
        }
    }
}
```

Output:

```text
10 5 20 3 7
```

---

# 17. Why Queue?

Initially:

```text
Queue:
[10]
```

Process `10`.

Add children:

```text
Queue:
[5, 20]
```

Process `5`.

Add children:

```text
Queue:
[20, 3, 7]
```

Process `20`.

Then:

```text
Queue:
[3, 7]
```

So nodes naturally get processed level-by-level.

That's exactly what FIFO gives us.

---

# 18. Level Order by Levels

Sometimes the problem asks:

> Print each level separately.

Example:

```text
[
 [10],
 [5, 20],
 [3, 7]
]
```

Use:

```java
void levelOrder(Node root) {

    if (root == null) {
        return;
    }

    Queue<Node> queue = new ArrayDeque<>();
    queue.offer(root);

    while (!queue.isEmpty()) {

        int size = queue.size();

        for (int i = 0; i < size; i++) {

            Node current = queue.poll();

            System.out.print(current.data + " ");

            if (current.left != null) {
                queue.offer(current.left);
            }

            if (current.right != null) {
                queue.offer(current.right);
            }
        }

        System.out.println();
    }
}
```

Output:

```text
10
5 20
3 7
```

The trick:

```java
int size = queue.size();
```

captures how many nodes belong to the **current level**.

This technique is extremely important for tree BFS problems.

---

# 19. Height of a Binary Tree ⭐

Now we start doing actual tree operations.

For:

```text
           10
          /  \
         5    20
        / \
       3   7
```

The maximum depth is:

```text
10
 ↓
5
 ↓
3
```

Depending on convention, height can be expressed in either **nodes** or **edges**.

We'll use:

> Height = number of nodes on the longest root-to-leaf path.

So:

```text
height = 3
```

Implementation:

```java
int height(Node root) {

    if (root == null) {
        return 0;
    }

    int leftHeight = height(root.left);
    int rightHeight = height(root.right);

    return 1 + Math.max(leftHeight, rightHeight);
}
```

---

# 20. Understanding the Height Recursion

At node `3`:

```text
left = 0
right = 0

height = 1
```

At node `5`:

```text
left = 1
right = 1

height = 2
```

At node `20`:

```text
height = 1
```

At node `10`:

```text
left = 2
right = 1

height = 3
```

The pattern is:

```text
height(node)
=
1 + max(
    height(left),
    height(right)
)
```

This is one of the most important recursive tree formulas.

---

# 21. Count Nodes

```java
int countNodes(Node root) {

    if (root == null) {
        return 0;
    }

    return 1
        + countNodes(root.left)
        + countNodes(root.right);
}
```

For:

```text
10
├── 5
│   ├── 3
│   └── 7
└── 20
```

answer:

```text
5
```

---

# 22. Sum of All Nodes

```java
int sum(Node root) {

    if (root == null) {
        return 0;
    }

    return root.data
        + sum(root.left)
        + sum(root.right);
}
```

For:

```text
10, 5, 20, 3, 7
```

sum:

```text
45
```

---

# 23. Search in a Normal Binary Tree

Because a normal Binary Tree has **no ordering guarantee**, we may need to visit everything.

```java
boolean contains(Node root, int target) {

    if (root == null) {
        return false;
    }

    if (root.data == target) {
        return true;
    }

    return contains(root.left, target)
        || contains(root.right, target);
}
```

Worst case:

```text
O(n)
```

Later, BST will improve certain searches.

---

# 24. Maximum Value

```java
int maxValue(Node root) {

    if (root == null) {
        return Integer.MIN_VALUE;
    }

    int left = maxValue(root.left);
    int right = maxValue(root.right);

    return Math.max(
        root.data,
        Math.max(left, right)
    );
}
```

Similarly, minimum:

```java
int minValue(Node root) {

    if (root == null) {
        return Integer.MAX_VALUE;
    }

    int left = minValue(root.left);
    int right = minValue(root.right);

    return Math.min(
        root.data,
        Math.min(left, right)
    );
}
```

---

# 25. Time Complexity of Traversals

Every node is visited once.

Therefore:

```text
Preorder   → O(n)
Inorder    → O(n)
Postorder  → O(n)
LevelOrder → O(n)
```

Space:

### Recursive DFS

```text
O(h)
```

where `h` = tree height.

Worst case:

```text
O(n)
```

for a completely skewed tree.

### Level Order

Queue can contain up to:

```text
O(n)
```

nodes in the worst case.

---

# 26. Recursive Tree Template ⭐⭐⭐

This pattern will appear everywhere:

```java
int solve(Node root) {

    if (root == null) {
        return BASE_CASE;
    }

    int left = solve(root.left);
    int right = solve(root.right);

    return COMBINE(root, left, right);
}
```

For height:

```java
return 1 + Math.max(left, right);
```

For sum:

```java
return root.data + left + right;
```

For count:

```java
return 1 + left + right;
```

This is the basic **tree recursion template**.

---

# 27. Complete Java Tree Template

For CP, you can keep this handy:

```java
import java.util.*;

class Node {

    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
    }
}

public class Main {

    static void preorder(Node root) {

        if (root == null) return;

        System.out.print(root.data + " ");

        preorder(root.left);
        preorder(root.right);
    }

    static void inorder(Node root) {

        if (root == null) return;

        inorder(root.left);

        System.out.print(root.data + " ");

        inorder(root.right);
    }

    static void postorder(Node root) {

        if (root == null) return;

        postorder(root.left);
        postorder(root.right);

        System.out.print(root.data + " ");
    }

    static void levelOrder(Node root) {

        if (root == null) return;

        Queue<Node> q = new ArrayDeque<>();

        q.offer(root);

        while (!q.isEmpty()) {

            Node current = q.poll();

            System.out.print(current.data + " ");

            if (current.left != null)
                q.offer(current.left);

            if (current.right != null)
                q.offer(current.right);
        }
    }

    public static void main(String[] args) {

        Node root = new Node(10);

        root.left = new Node(5);
        root.right = new Node(20);

        root.left.left = new Node(3);
        root.left.right = new Node(7);

        preorder(root);
        System.out.println();

        inorder(root);
        System.out.println();

        postorder(root);
        System.out.println();

        levelOrder(root);
    }
}
```

Output:

```text
10 5 3 7 20
3 5 7 10 20
3 7 5 20 10
10 5 20 3 7
```

---

# 🧠 The Most Important Thing to Memorize

Given:

```text
           10
          /  \
         5    20
        / \
       3   7
```

### Preorder

```text
ROOT → LEFT → RIGHT

10 5 3 7 20
```

### Inorder

```text
LEFT → ROOT → RIGHT

3 5 7 10 20
```

### Postorder

```text
LEFT → RIGHT → ROOT

3 7 5 20 10
```

### Level Order

```text
LEVEL BY LEVEL

10 5 20 3 7
```

---

# 🧪 Practice

### Level 1 — Must Do

1. Create a binary tree manually.
2. Print preorder.
3. Print inorder.
4. Print postorder.
5. Print level order.
6. Count nodes.
7. Calculate sum.
8. Find maximum.
9. Find minimum.
10. Calculate height.

### Level 2

11. Count leaf nodes.
12. Count nodes with one child.
13. Count nodes with two children.
14. Search for a value.
15. Find the depth of a given node.
16. Mirror/invert a binary tree.
17. Check whether two trees are identical.
18. Print left view.
19. Print right view.
20. Level order traversal by levels.

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

✅ Binary Tree
   ├── Node
   ├── Preorder
   ├── Inorder
   ├── Postorder
   ├── Level Order
   ├── Height
   ├── Count
   └── Search

⬜ BST
⬜ Graph
⬜ Trie
⬜ DSU
```

## Next → 🌲 Binary Search Tree (BST)

This is where trees become much more interesting.

We'll learn the key property:

```text
             50
           /    \
         <50    >50
```

and implement:

* BST insertion
* Search
* Delete
* Minimum / Maximum
* Floor / Ceiling
* Predecessor / Successor
* Inorder → sorted order

**BST + recursion + traversal is a very common interview combination.**

