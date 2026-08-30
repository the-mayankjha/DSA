# 🟢 Day 1 — Java CP Data Structures

# Part 4: Linked List

Now we move to the first data structure where you need to understand **references and nodes**, rather than just indexing.

For competitive programming, the most important thing is:

> **Understand how a linked list works from scratch, but know when to use Java's built-in structures instead.**

---

# 1. What is a Linked List?

An array looks like:

```text
[10] [20] [30] [40]
```

Elements are accessed by index.

A linked list looks like:

```text
[10 | next] → [20 | next] → [30 | next] → [40 | null]
```

Each element is a **Node** containing:

```text
data
+
reference to next node
```

---

# 2. Node Implementation

This is the most important code to understand.

```java
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}
```

Create nodes:

```java
Node a = new Node(10);
Node b = new Node(20);
Node c = new Node(30);
```

Connect them:

```java
a.next = b;
b.next = c;
```

Now:

```text
a
↓
[10] → [20] → [30] → null
```

---

# 3. The `head`

The first node is usually called:

```text
head
```

Example:

```java
Node head = new Node(10);

head.next = new Node(20);
head.next.next = new Node(30);
```

Structure:

```text
head
 ↓
[10] → [20] → [30] → null
```

There is no need for a `tail` yet.

---

# 4. Traversing a Linked List

This is essential.

```java
Node current = head;

while (current != null) {
    System.out.println(current.data);
    current = current.next;
}
```

Think:

```text
current
   ↓
[10] → [20] → [30] → null

        ↓

        current = current.next

             ↓

[10] → [20] → [30]
         ↑
      current
```

The key statement:

```java
current = current.next;
```

should become automatic.

---

# 5. Complete Basic Linked List

```java
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
    }
}

public class Main {
    public static void main(String[] args) {

        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);

        Node current = head;

        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
    }
}
```

Output:

```text
10 20 30
```

---

# 6. Why Linked Lists?

Compared with arrays:

| Operation               |      Array |    Linked List |
| ----------------------- | ---------: | -------------: |
| Access by index         |       O(1) |           O(n) |
| Search                  |       O(n) |           O(n) |
| Insert beginning        |       O(n) |           O(1) |
| Delete beginning        |       O(n) |           O(1) |
| Insert after known node |       O(n) |           O(1) |
| Memory                  | Contiguous | Non-contiguous |

The major advantage:

> **Insertion/deletion can be cheap when you already have the required node/reference.**

---

# 7. Insert at Beginning

Suppose:

```text
10 → 20 → 30
```

We want:

```text
5 → 10 → 20 → 30
```

Create:

```java
Node newNode = new Node(5);
```

Then:

```java
newNode.next = head;
head = newNode;
```

That's it.

Visual:

```text
Before:

head
 ↓
10 → 20 → 30

After:

newNode
   ↓
   5 → 10 → 20 → 30
   ↑
 head
```

Time:

```text
O(1)
```

---

# 8. Insert at End

Suppose:

```text
10 → 20 → 30
```

Add `40`.

```java
Node newNode = new Node(40);

Node current = head;

while (current.next != null) {
    current = current.next;
}

current.next = newNode;
```

Result:

```text
10 → 20 → 30 → 40
```

Time:

```text
O(n)
```

unless you maintain a `tail`.

---

# 9. Using a Tail

You can maintain:

```java
Node head;
Node tail;
```

Then:

```java
Node newNode = new Node(40);

tail.next = newNode;
tail = newNode;
```

Insertion becomes:

```text
O(1)
```

---

# 10. Insert After a Node

Suppose:

```text
10 → 20 → 30
```

We want:

```text
10 → 20 → 25 → 30
```

If we already have the node containing `20`:

```java
Node newNode = new Node(25);

newNode.next = current.next;
current.next = newNode;
```

Result:

```text
10 → 20 → 25 → 30
```

The order of these two statements matters.

### Correct

```java
newNode.next = current.next;
current.next = newNode;
```

### Why?

Initially:

```text
current
   ↓
20 → 30
```

First:

```text
newNode.next = current.next;
```

becomes:

```text
25 → 30
```

Then:

```java
current.next = newNode;
```

becomes:

```text
20 → 25 → 30
```

---

# 11. Delete First Node

Suppose:

```text
head
 ↓
10 → 20 → 30
```

Simply:

```java
head = head.next;
```

Now:

```text
head
 ↓
20 → 30
```

Time:

```text
O(1)
```

---

# 12. Delete a Node After a Node

Suppose:

```text
10 → 20 → 30 → 40
```

We want to delete `30`.

If `current` points to `20`:

```java
current.next = current.next.next;
```

Result:

```text
10 → 20 → 40
```

This is a classic linked-list operation.

---

# 13. Searching

```java
int target = 30;

Node current = head;

while (current != null) {

    if (current.data == target) {
        System.out.println("Found");
        break;
    }

    current = current.next;
}
```

Complexity:

```text
O(n)
```

---

# 14. Finding Length

```java
int length = 0;

Node current = head;

while (current != null) {
    length++;
    current = current.next;
}
```

Complexity:

```text
O(n)
```

If you maintain a `size` variable, you can make this O(1).

---

# 15. Reverse a Linked List

This is **one of the most important linked-list algorithms for interviews**.

Given:

```text
10 → 20 → 30 → null
```

We want:

```text
30 → 20 → 10 → null
```

Use three references:

```java
Node prev = null;
Node current = head;
Node next;
```

Then:

```java
while (current != null) {

    next = current.next;

    current.next = prev;

    prev = current;

    current = next;
}

head = prev;
```

Understand this very carefully.

### Initial

```text
prev = null

current
   ↓
10 → 20 → 30 → null
```

### Save next

```text
next = current.next
```

### Reverse pointer

```text
current.next = prev
```

Now:

```text
10 → null
```

### Move

```text
prev = current
current = next
```

Repeat.

Final:

```text
30 → 20 → 10 → null
```

This algorithm is worth memorizing.

---

# 16. The Standard Reverse Template

You'll see this constantly:

```java
Node prev = null;
Node curr = head;

while (curr != null) {
    Node next = curr.next;

    curr.next = prev;

    prev = curr;
    curr = next;
}

head = prev;
```

Keep this template in your mental toolkit.

---

# 17. Find Middle of Linked List

This introduces one of the most important techniques:

> **Slow + Fast pointers**

```java
Node slow = head;
Node fast = head;

while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
}
```

When the loop finishes:

```text
slow = middle
```

For:

```text
10 → 20 → 30 → 40 → 50
```

you get:

```text
slow
 ↓
30
```

This technique will become extremely important later.

---

# 18. Detect a Cycle

Again:

> Slow + Fast pointers

```java
Node slow = head;
Node fast = head;

while (fast != null && fast.next != null) {

    slow = slow.next;
    fast = fast.next.next;

    if (slow == fast) {
        System.out.println("Cycle exists");
        break;
    }
}
```

This is **Floyd's Cycle Detection Algorithm**.

Don't worry about mastering it yet—we'll revisit it when we study patterns.

---

# 19. Doubly Linked List

A singly linked list has:

```text
data → next
```

A doubly linked list has:

```text
prev ← data → next
```

Node:

```java
class Node {
    int data;
    Node prev;
    Node next;

    Node(int data) {
        this.data = data;
    }
}
```

Structure:

```text
null ← 10 ⇄ 20 ⇄ 30 → null
```

You can traverse in both directions.

---

# 20. Java's Built-in LinkedList

Java already provides:

```java
LinkedList<Integer> list = new LinkedList<>();
```

Import:

```java
import java.util.LinkedList;
```

You can:

```java
list.add(10);
list.add(20);
list.add(30);
```

---

# 21. LinkedList Operations

Add beginning:

```java
list.addFirst(5);
```

Add end:

```java
list.addLast(40);
```

Remove beginning:

```java
list.removeFirst();
```

Remove end:

```java
list.removeLast();
```

Get first:

```java
list.getFirst();
```

Get last:

```java
list.getLast();
```

---

# 22. But Should You Use Java LinkedList in CP?

Usually:

> **No.**

For most CP/OA situations, `ArrayDeque` is a better choice for stack/queue operations.

For example, if you need:

```text
Stack
Queue
Deque
```

prefer:

```java
ArrayDeque
```

rather than:

```java
LinkedList
```

We'll cover this in the next sections.

The main reason we're learning linked lists is because **interviews frequently ask you to manipulate the custom Node structure**.

---

# 23. Important Complexity Table

| Operation               | Singly Linked List |
| ----------------------- | -----------------: |
| Access by index         |               O(n) |
| Search                  |               O(n) |
| Insert beginning        |               O(1) |
| Delete beginning        |               O(1) |
| Insert after known node |               O(1) |
| Delete after known node |               O(1) |
| Insert end              |               O(n) |
| Reverse                 |               O(n) |
| Find middle             |               O(n) |

---

# 🧠 What You Should Actually Remember

You don't need to memorize every linked-list method.

These are the **core templates**:

### Node

```java
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
    }
}
```

### Traverse

```java
Node curr = head;

while (curr != null) {
    // curr.data
    curr = curr.next;
}
```

### Insert at beginning

```java
newNode.next = head;
head = newNode;
```

### Delete beginning

```java
head = head.next;
```

### Reverse

```java
Node prev = null;
Node curr = head;

while (curr != null) {
    Node next = curr.next;
    curr.next = prev;
    prev = curr;
    curr = next;
}

head = prev;
```

### Middle

```java
Node slow = head;
Node fast = head;

while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
}
```

---

# 🧪 Practice

Implement these from scratch:

### Level 1

1. Create a singly linked list.
2. Traverse and print it.
3. Find its length.
4. Search for a value.
5. Insert at beginning.
6. Insert at end.
7. Insert after a given value.
8. Delete first node.
9. Delete last node.
10. Delete a node containing a given value.

### Level 2

11. Reverse a linked list.
12. Find the middle node.
13. Find the nth node from the end.
14. Detect a cycle.
15. Find the starting point of a cycle.
16. Remove duplicates from a sorted linked list.
17. Merge two sorted linked lists.
18. Check whether a linked list is a palindrome.

---

## 📍 Progress

```text
JAVA CP DATA STRUCTURES

✅ Arrays
✅ ArrayList
✅ String
   └── char[]
   └── StringBuilder

✅ Linked List
   ├── Singly Linked List
   ├── Doubly Linked List
   ├── Node
   ├── Insertion
   ├── Deletion
   ├── Reversal
   └── Slow/Fast pointers

⬜ Stack
⬜ Queue
⬜ Deque
⬜ HashMap
⬜ HashSet
⬜ TreeMap
⬜ TreeSet
⬜ PriorityQueue
⬜ Heap
⬜ Binary Tree
⬜ BST
⬜ Graph
⬜ Trie
⬜ DSU
```

### Next → **Stack**

And there's an important Java-specific point there: **don't learn `Stack<Integer>` as your primary CP implementation.** We'll learn why `Deque<Integer> + ArrayDeque` is generally the better approach.

