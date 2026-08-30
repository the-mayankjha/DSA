# 🌲 Part 14 — Binary Search Tree (BST)

Now we move from a **normal Binary Tree** to a **Binary Search Tree**.

The key difference is huge:

> A Binary Tree has no ordering rule.
> A BST has an ordering rule that lets us search efficiently.

---

# 1. BST's Golden Rule ⭐⭐⭐

For every node:

```text
             Node
            /    \
           ↓      ↓
        Smaller  Larger
```

More formally:

```text
ALL values in LEFT subtree  < node
ALL values in RIGHT subtree > node
```

Example:

```text id="7fdr4k"
             50
           /    \
         30      70
        /  \    /  \
      20   40  60   80
```

Everything left of `50` is smaller.

Everything right of `50` is larger.

---

# 2. BST vs Binary Tree

### Binary Tree

```text id="rj1d6b"
             50
           /    \
         80      20
```

This is completely valid as a Binary Tree.

But:

```text
80 > 50
```

yet `80` is on the left.

Therefore:

> ❌ Not a BST.

### BST

```text id="5ptfgt"
             50
           /    \
         30      70
```

Correct:

```text
30 < 50 < 70
```

---

# 3. Java Node

Same Node structure:

```java id="j9w6fq"
class Node {

    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
    }
}
```

Nothing special is required in the Node.

The **BST property comes from how we insert/search**.

---

# 4. Creating a BST

Suppose we insert:

```text id="1wqj2v"
50
30
70
20
40
60
80
```

The resulting tree:

```text id="s5pj6a"
             50
           /    \
         30      70
        /  \    /  \
      20   40  60   80
```

---

# 5. BST Insertion ⭐⭐⭐

This is the first operation you should master.

Suppose:

```text id="0a4s5m"
        50
       /
     30
```

Insert `20`.

Compare:

```text id="m5qkvl"
20 < 50
```

Go left.

```text id="5z0ew9"
20 < 30
```

Go left again.

```text id="ej3h2u"
        50
       /
     30
    /
   20
```

---

# 6. Recursive Insertion

```java id="2q6lwp"
Node insert(Node root, int value) {

    if (root == null) {
        return new Node(value);
    }

    if (value < root.data) {
        root.left = insert(root.left, value);
    }
    else if (value > root.data) {
        root.right = insert(root.right, value);
    }

    return root;
}
```

This is the standard recursive implementation.

---

# 7. Why Do We Return `root`?

This line:

```java id="z6ezq1"
return root;
```

is extremely important.

Suppose:

```java id="vuk0sj"
root.left = insert(root.left, value);
```

The recursive call may create a new node.

Returning the root lets us reconnect the subtree correctly.

For example:

```text id="l1m8p4"
root
 ↓
50
/
30
```

When inserting `20`:

```text id="z24nyy"
insert(30, 20)
```

eventually creates:

```text id="3lml7s"
20
```

and returns it so:

```text id="6h3f3q"
30.left = 20
```

---

# 8. Complete Insertion Example

```java id="f94l1x"
public class Main {

    static Node insert(Node root, int value) {

        if (root == null) {
            return new Node(value);
        }

        if (value < root.data) {
            root.left = insert(root.left, value);
        }
        else if (value > root.data) {
            root.right = insert(root.right, value);
        }

        return root;
    }

    public static void main(String[] args) {

        Node root = null;

        root = insert(root, 50);
        root = insert(root, 30);
        root = insert(root, 70);
        root = insert(root, 20);
        root = insert(root, 40);
        root = insert(root, 60);
        root = insert(root, 80);
    }
}
```

---

# 9. BST Search ⭐⭐⭐

This is where BST becomes powerful.

Suppose:

```text id="v5a4c7"
             50
           /    \
         30      70
        /  \    /  \
      20   40  60   80
```

Search for `60`.

Start:

```text id="4f0v4a"
60 vs 50
```

Since:

```text id="g7s8qf"
60 > 50
```

go right.

Now:

```text id="3qcxv9"
60 vs 70
```

Since:

```text id="m8iq0v"
60 < 70
```

go left.

Found:

```text id="v5xx8a"
60
```

We didn't inspect:

```text id="1zv2qk"
20, 30, 40, 80
```

That's the power of the BST property.

---

# 10. Recursive Search

```java id="zq8e4y"
boolean search(Node root, int target) {

    if (root == null) {
        return false;
    }

    if (root.data == target) {
        return true;
    }

    if (target < root.data) {
        return search(root.left, target);
    }

    return search(root.right, target);
}
```

---

# 11. Iterative Search

For CP, this version is also useful:

```java id="q9zv9k"
boolean search(Node root, int target) {

    while (root != null) {

        if (root.data == target) {
            return true;
        }

        if (target < root.data) {
            root = root.left;
        }
        else {
            root = root.right;
        }
    }

    return false;
}
```

No recursion stack required.

---

# 12. Search Complexity

### Balanced BST

```text id="tx5qf0"
O(log n)
```

Because every comparison eliminates roughly half the search space.

### Worst Case

Consider:

```text id="w4f2nq"
10
  \
   20
     \
      30
        \
         40
```

This is basically a linked list.

Search becomes:

```text id="6x4s9q"
O(n)
```

So:

> BST is only reliably `O(log n)` when reasonably balanced.

---

# 13. Inorder Traversal ⭐⭐⭐

Remember Binary Tree inorder:

```text id="s2v40e"
LEFT → ROOT → RIGHT
```

For a BST:

```text id="a1tw1v"
             50
           /    \
         30      70
        /  \    /  \
      20   40  60   80
```

Inorder gives:

```text id="l0y8o2"
20 30 40 50 60 70 80
```

**Sorted order.**

This is one of the most important properties of BSTs.

> **Inorder traversal of a valid BST produces sorted values.**

---

# 14. Minimum Value

Where is the smallest value?

Always:

```text id="g0w0d5"
Keep going LEFT.
```

Example:

```text id="9wyk0x"
             50
           /
         30
        /
      20
```

Minimum:

```text id="1d4kq0"
20
```

Implementation:

```java id="m7yplk"
int minValue(Node root) {

    if (root == null) {
        throw new RuntimeException("Empty tree");
    }

    while (root.left != null) {
        root = root.left;
    }

    return root.data;
}
```

---

# 15. Maximum Value

Opposite:

> Keep going right.

```java id="g2g7py"
int maxValue(Node root) {

    if (root == null) {
        throw new RuntimeException("Empty tree");
    }

    while (root.right != null) {
        root = root.right;
    }

    return root.data;
}
```

---

# 16. BST Delete ⭐⭐⭐

This is the most complicated basic BST operation.

There are **three cases**.

---

## Case 1 — Leaf Node

Delete:

```text id="9q4xhe"
20
```

Tree:

```text id="8ol9dc"
      30
     /  \
   20    40
```

Since `20` has no children:

```text id="6v6c4j"
Just remove it.
```

---

## Case 2 — One Child

Suppose:

```text id="1m54k2"
      30
     /
   20
   /
 10
```

Delete `20`.

We connect its parent directly to its child:

```text id="sjm4p8"
      30
     /
   10
```

---

## Case 3 — Two Children ⭐

Suppose:

```text id="49p2jo"
        50
       /  \
     30    70
          /  \
        60    80
```

Delete `70`.

It has two children:

```text id="p5p7bl"
60
80
```

We need a replacement.

A common choice:

> **Inorder successor = smallest value in the right subtree.**

Right subtree:

```text id="wq5t9p"
   80
```

So successor = `80`.

Replace:

```text id="o3qfpp"
70 → 80
```

Then remove the original `80`.

---

# 17. Inorder Successor

For a node:

```text id="5x4r8p"
        50
          \
           70
          /  \
        60    80
```

The inorder successor of `70` is:

```text id="v7n2g9"
80
```

If the node has a right subtree:

> Go right once, then go left as far as possible.

---

# 18. BST Delete Implementation

```java id="w6w4je"
Node delete(Node root, int key) {

    if (root == null) {
        return null;
    }

    if (key < root.data) {

        root.left = delete(root.left, key);

    }
    else if (key > root.data) {

        root.right = delete(root.right, key);

    }
    else {

        // Case 1: no left child
        if (root.left == null) {
            return root.right;
        }

        // Case 2: no right child
        if (root.right == null) {
            return root.left;
        }

        // Case 3: two children
        int successor = minValue(root.right);

        root.data = successor;

        root.right = delete(root.right, successor);
    }

    return root;
}
```

This handles all three cases.

---

# 19. Why Does This Work?

Suppose:

```text id="2zj1js"
        50
       /  \
     30    70
          /  \
        60    80
```

Delete `70`.

Find:

```text id="tx2d58"
minValue(70.right)
```

which gives:

```text id="0a4d2b"
80
```

Set:

```java id="5h4c4n"
root.data = 80;
```

Now:

```text id="xj3w9k"
        50
       /  \
     30    80
          /
        60
```

Then delete the original `80` from the right subtree.

Result is still a valid BST.

---

# 20. Floor in BST ⭐

Suppose:

```text id="c6k3y8"
             50
           /    \
         30      70
        /  \    /  \
      20   40  60   80
```

Find:

> Largest value ≤ `65`

Answer:

```text id="n9i4j6"
60
```

We can exploit the BST structure.

```java id="tdd5di"
Integer floor(Node root, int x) {

    Integer answer = null;

    while (root != null) {

        if (root.data == x) {
            return root.data;
        }

        if (root.data > x) {
            root = root.left;
        }
        else {
            answer = root.data;
            root = root.right;
        }
    }

    return answer;
}
```

---

# 21. Ceiling in BST

Find:

> Smallest value ≥ `65`

For:

```text id="h9m3zq"
20 30 40 50 60 70 80
```

answer:

```text id="dfx1cb"
70
```

Implementation:

```java id="03t2su"
Integer ceiling(Node root, int x) {

    Integer answer = null;

    while (root != null) {

        if (root.data == x) {
            return root.data;
        }

        if (root.data < x) {
            root = root.right;
        }
        else {
            answer = root.data;
            root = root.left;
        }
    }

    return answer;
}
```

---

# 22. BST vs TreeSet

Interesting connection.

You just learned:

```text id="2a2j2u"
TreeSet
```

with:

```java
floor()
ceiling()
lower()
higher()
```

A BST can provide similar operations.

Conceptually:

```text id="k4zyh1"
TreeSet
   ↓
Balanced BST internally
```

That's why TreeSet can perform these operations in:

```text id="hmg3n0"
O(log n)
```

You generally **don't need to implement a balanced BST yourself** for CP unless the problem/interview specifically asks.

---

# 23. Duplicates in BST

What happens if we insert:

```text id="6p4d8z"
50
30
50
```

There are different conventions.

A simple implementation can choose:

```java id="1yq1e5"
if (value < root.data) {
    // left
}
else if (value > root.data) {
    // right
}
// equal → ignore
```

Then duplicates aren't inserted.

Another implementation might consistently put duplicates on one side.

For interview code, **state your convention**.

---

# 24. BST Validation ⭐⭐⭐

A common interview question:

> Is this tree a valid BST?

You cannot simply check:

```text id="0d7u4r"
left < root < right
```

at every node.

Why?

Consider:

```text id="gq4prn"
        50
       /  \
     30    70
          /
        40
```

At `70`:

```text
40 < 70
```

looks valid locally.

But `40` is in the right subtree of `50`, so:

```text
40 < 50
```

violates the BST property.

---

# 25. Correct BST Validation

Use bounds:

```java id="q8y52a"
boolean isValidBST(Node root) {

    return validate(
        root,
        Long.MIN_VALUE,
        Long.MAX_VALUE
    );
}

boolean validate(Node root, long min, long max) {

    if (root == null) {
        return true;
    }

    if (root.data <= min || root.data >= max) {
        return false;
    }

    return validate(root.left, min, root.data)
        && validate(root.right, root.data, max);
}
```

Why `long`?

If node values can be `Integer.MIN_VALUE` or `Integer.MAX_VALUE`, using `int` bounds can cause boundary issues.

---

# 26. Another Way to Validate

Because:

> Inorder traversal of a valid BST is strictly increasing.

You can perform inorder traversal and ensure:

```text id="b7jv3r"
previous < current
```

This is also a common interview technique.

---

# 27. BST Complexity

Let:

```text id="8vryeq"
h = height of tree
```

Then:

| Operation | Complexity |
| --------- | ---------: |
| Search    |       O(h) |
| Insert    |       O(h) |
| Delete    |       O(h) |
| Min       |       O(h) |
| Max       |       O(h) |
| Floor     |       O(h) |
| Ceiling   |       O(h) |

Balanced:

```text id="tq48gt"
h = O(log n)
```

Worst case:

```text id="b5bq0f"
h = O(n)
```

---

# 28. The BST Mental Model

When you search for `X`:

```text id="9c8a6s"
X < current
    ↓
Go LEFT

X > current
    ↓
Go RIGHT

X == current
    ↓
FOUND
```

This should become automatic.

---

# 29. Complete BST Template

For CP, keep this structure:

```java id="k7n1yv"
class Node {

    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
    }
}

class BST {

    Node root;

    Node insert(Node root, int value) {

        if (root == null) {
            return new Node(value);
        }

        if (value < root.data) {
            root.left = insert(root.left, value);
        }
        else if (value > root.data) {
            root.right = insert(root.right, value);
        }

        return root;
    }

    boolean search(Node root, int target) {

        while (root != null) {

            if (root.data == target) {
                return true;
            }

            if (target < root.data) {
                root = root.left;
            }
            else {
                root = root.right;
            }
        }

        return false;
    }

    int minValue(Node root) {

        while (root.left != null) {
            root = root.left;
        }

        return root.data;
    }

    int maxValue(Node root) {

        while (root.right != null) {
            root = root.right;
        }

        return root.data;
    }
}
```

---

# 🧠 BST Cheat Sheet

### Rule

```text
LEFT < ROOT < RIGHT
```

### Search

```text
target < root
    → left

target > root
    → right
```

### Minimum

```text
Keep going LEFT
```

### Maximum

```text
Keep going RIGHT
```

### Inorder

```text
LEFT → ROOT → RIGHT
```

gives:

```text
SORTED ORDER
```

### Delete

```text
0 children → remove
1 child    → replace with child
2 children → replace with inorder successor
```

---

# 🧪 Practice

### Level 1

1. Create a BST.
2. Insert elements.
3. Search for an element.
4. Find minimum.
5. Find maximum.
6. Print inorder.
7. Print preorder.
8. Print postorder.
9. Delete a leaf.
10. Delete a node with one child.

### Level 2

11. Delete a node with two children.
12. Validate BST.
13. Find floor.
14. Find ceiling.
15. Find inorder predecessor.
16. Find inorder successor.
17. Find kth smallest element.
18. Find kth largest element.
19. Lowest Common Ancestor in BST.
20. Convert sorted array → balanced BST.

**17–20 are especially important interview problems.**

---

# 📍 Progress

```text id="b9p3wt"
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
   ├── DFS Traversals
   ├── BFS / Level Order
   ├── Height
   └── Basic operations

✅ BST
   ├── Insert
   ├── Search
   ├── Delete
   ├── Min / Max
   ├── Floor / Ceiling
   └── Validation

⬜ Graph
⬜ Trie
⬜ DSU
```

## Next → 🕸️ Graph

Graphs are the **last major general-purpose data structure** in this sequence.

We'll learn how to implement:

```text
Graph
├── Adjacency Matrix
├── Adjacency List
├── Weighted Graph
├── Directed Graph
├── Undirected Graph
└── Edge representation
```

Then we'll connect the structures you've already learned:

```text
Graph
 ├── Queue → BFS
 ├── Stack/Recursion → DFS
 ├── HashSet → visited
 └── PriorityQueue → Dijkstra
```

That connection is exactly what you want before starting your **DSA patterns + OA preparation**.

