# 🟢 Day 1 — Java CP Data Structures

# Part 6: Queue

Now let's learn **Queue**.

If Stack is:

> **LIFO — Last In, First Out**

Queue is:

> **FIFO — First In, First Out**

Think of a line at a counter:

```text
FIRST                              LAST
 ↓                                  ↓
[10] → [20] → [30] → [40]
 ↑
remove                            add
```

`10` entered first, so `10` leaves first.

---

# 1. Core Queue Operations

| Operation   | Meaning            |
| ----------- | ------------------ |
| `offer(x)`  | Add to rear        |
| `poll()`    | Remove from front  |
| `peek()`    | Look at front      |
| `isEmpty()` | Check empty        |
| `size()`    | Number of elements |

Example:

```text
Queue:

front → [10] [20] [30] ← rear
```

After:

```java
queue.poll();
```

we get:

```text
front → [20] [30] ← rear
```

---

# 2. Java Queue

The standard interface is:

```java
Queue<Integer> queue = new ArrayDeque<>();
```

Imports:

```java
import java.util.*;
```

This is the implementation I want you to become comfortable with for CP.

---

# 3. Adding Elements — `offer()`

```java
queue.offer(10);
queue.offer(20);
queue.offer(30);
```

Now:

```text
front
 ↓
10 → 20 → 30
             ↑
            rear
```

You may also see:

```java
queue.add(10);
```

but for queue semantics, I recommend remembering:

```java
queue.offer(x);
```

---

# 4. Removing Elements — `poll()`

```java
int x = queue.poll();
```

If:

```text
10 → 20 → 30
↑
front
```

then:

```java
queue.poll();
```

returns:

```text
10
```

Remaining:

```text
20 → 30
```

---

# 5. Looking at the Front — `peek()`

```java
int x = queue.peek();
```

If:

```text
10 → 20 → 30
↑
front
```

then:

```java
queue.peek();
```

returns:

```text
10
```

but **doesn't remove it**.

---

# 6. Empty Check

Always useful:

```java
if (!queue.isEmpty()) {
    int x = queue.poll();
}
```

---

# 7. Complete Example

```java
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Queue<Integer> queue = new ArrayDeque<>();

        queue.offer(10);
        queue.offer(20);
        queue.offer(30);

        System.out.println(queue.peek());

        System.out.println(queue.poll());

        System.out.println(queue.peek());

        System.out.println(queue.size());
    }
}
```

Output:

```text
10
10
20
3
```

Wait — after `poll()`, the size should actually be **2**, so the correct output is:

```text
10
10
20
2
```

That's a good example of why you should always mentally track state when debugging.

---

# 8. Why `poll()` Instead of `remove()`?

Java provides both:

```java
queue.poll();
queue.remove();
```

The difference appears when the queue is empty.

### `poll()`

Returns:

```text
null
```

if empty.

### `remove()`

Throws an exception if empty.

For CP, I recommend:

```java
queue.poll();
```

because it's safer.

Similarly:

```text
peek()  → null if empty
element() → exception if empty
```

Use:

```java
peek()
```

---

# 9. Queue Using an Array — From Scratch

Now let's understand how a queue works internally.

A naive implementation:

```java
class MyQueue {

    int[] arr;
    int front;
    int rear;

    MyQueue(int size) {
        arr = new int[size];
        front = 0;
        rear = 0;
    }

    void offer(int x) {
        arr[rear++] = x;
    }

    int poll() {
        return arr[front++];
    }

    int peek() {
        return arr[front];
    }

    boolean isEmpty() {
        return front == rear;
    }
}
```

Conceptually:

```text
front → [10] [20] [30] ← rear
```

After removing `10`:

```text
       front
         ↓
[10] [20] [30]
      ↑
```

The space before `front` is wasted.

This leads us to:

# Circular Queue

---

# 10. Circular Queue

Suppose the array has capacity `5`.

Initially:

```text
[ _ ][ _ ][ _ ][ _ ][ _ ]
  ↑
front
```

Add:

```text
10 20 30 40
```

```text
[10][20][30][40][_]
 ↑             ↑
front         rear
```

Remove two:

```text
[10][20][30][40][_]
         ↑
       front
```

Now there is free space at the beginning.

A circular queue allows `rear` to wrap around:

```text
[60][70][30][40][50]
 ↑
rear
```

The key idea is:

```java
index = (index + 1) % capacity;
```

This is important for understanding queues, although in CP you'll usually use `ArrayDeque`.

---

# 11. Circular Queue Implementation

A clean implementation:

```java
class MyCircularQueue {

    int[] arr;
    int front;
    int rear;
    int size;
    int capacity;

    MyCircularQueue(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
        front = 0;
        rear = 0;
        size = 0;
    }

    void offer(int x) {
        if (size == capacity) {
            return;
        }

        arr[rear] = x;
        rear = (rear + 1) % capacity;
        size++;
    }

    int poll() {
        if (size == 0) {
            return -1;
        }

        int value = arr[front];

        front = (front + 1) % capacity;
        size--;

        return value;
    }

    int peek() {
        if (size == 0) {
            return -1;
        }

        return arr[front];
    }

    boolean isEmpty() {
        return size == 0;
    }

    boolean isFull() {
        return size == capacity;
    }
}
```

The most important line:

```java
rear = (rear + 1) % capacity;
```

This creates the circular behavior.

---

# 12. Queue Using Linked List

You can also implement a queue using linked nodes.

Maintain:

```text
front
rear
```

Structure:

```text
front                    rear
 ↓                         ↓
10 → 20 → 30 → 40 → null
```

Node:

```java
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
    }
}
```

Queue:

```java
class MyQueue {

    Node front;
    Node rear;

    void offer(int x) {

        Node newNode = new Node(x);

        if (rear == null) {
            front = rear = newNode;
            return;
        }

        rear.next = newNode;
        rear = newNode;
    }

    int poll() {

        if (front == null) {
            return -1;
        }

        int value = front.data;

        front = front.next;

        if (front == null) {
            rear = null;
        }

        return value;
    }
}
```

This gives:

```text
offer → O(1)
poll  → O(1)
```

---

# 13. Queue Complexity

For:

```java
Queue<Integer> queue = new ArrayDeque<>();
```

| Operation   |     Complexity |
| ----------- | -------------: |
| `offer()`   | O(1) amortized |
| `poll()`    |           O(1) |
| `peek()`    |           O(1) |
| `isEmpty()` |           O(1) |
| `size()`    |           O(1) |

---

# 14. Queue vs Stack

This must be automatic.

### Stack

```text
LIFO

push
 ↓
[10]
[20]
[30] ← pop
```

### Queue

```text
FIFO

10 → 20 → 30
↑           ↑
poll       offer
```

Java:

```java
// Stack
Deque<Integer> stack = new ArrayDeque<>();

// Queue
Queue<Integer> queue = new ArrayDeque<>();
```

---

# 15. Queue and BFS

This is where Queue becomes **very important in DSA**.

Breadth First Search uses a queue.

For example:

```text
        1
       / \
      2   3
     / \
    4   5
```

BFS order:

```text
1 → 2 → 3 → 4 → 5
```

We start:

```java
Queue<Integer> queue = new ArrayDeque<>();
queue.offer(1);
```

Then:

```text
queue
 ↓
[1]
```

Remove `1`:

```java
int node = queue.poll();
```

Then add its children:

```text
[2, 3]
```

Then process `2`:

```text
[3, 4, 5]
```

This is the foundation of:

* Tree BFS
* Graph BFS
* Shortest path in unweighted graphs
* Level-order traversal
* Multi-source BFS

We'll come back to this later.

---

# 16. Multiple Elements in Queue

You can use:

```java
Queue<String> queue = new ArrayDeque<>();
```

or:

```java
Queue<Character> queue = new ArrayDeque<>();
```

or:

```java
Queue<int[]> queue = new ArrayDeque<>();
```

The last one is particularly useful for BFS.

Example:

```java
Queue<int[]> queue = new ArrayDeque<>();

queue.offer(new int[]{0, 0});
```

You can use this to store:

```text
(row, column)
```

for grid BFS problems.

---

# 17. Queue of Custom Objects

You can also store:

```java
class Node {
    int value;
    int distance;

    Node(int value, int distance) {
        this.value = value;
        this.distance = distance;
    }
}
```

Then:

```java
Queue<Node> queue = new ArrayDeque<>();
```

This becomes useful in graph algorithms.

---

# 18. `ArrayDeque` Can Act as Both

This is a very useful Java CP trick.

### As Stack

```java
Deque<Integer> dq = new ArrayDeque<>();

dq.push(10);
dq.push(20);
dq.pop();
```

### As Queue

```java
Deque<Integer> dq = new ArrayDeque<>();

dq.offer(10);
dq.offer(20);
dq.poll();
```

So:

```text
ArrayDeque
    │
    ├── Stack
    │
    ├── Queue
    │
    └── Deque
```

This is why you'll see `ArrayDeque` everywhere in Java CP.

---

# 19. Common Mistake

Don't do:

```java
queue.get(0);
```

A Queue isn't meant to be accessed by index.

Use:

```java
queue.peek();
```

Similarly:

```java
queue[0]; // ❌
```

is invalid.

---

# 20. Don't Use `LinkedList` by Default

You might see:

```java
Queue<Integer> q = new LinkedList<>();
```

This works.

But for CP, prefer:

```java
Queue<Integer> q = new ArrayDeque<>();
```

unless you specifically need behavior that `LinkedList` provides.

---

# 🧠 Queue Cheat Sheet

Memorize:

```java
Queue<Integer> q = new ArrayDeque<>();

q.offer(x);      // add
q.poll();        // remove front
q.peek();        // front
q.isEmpty();     // empty?
q.size();        // size
```

For BFS:

```java
Queue<int[]> q = new ArrayDeque<>();
```

---

# 🧪 Practice

### Level 1

1. Implement a queue using an array.
2. Implement a circular queue.
3. Implement a queue using linked list.
4. Add 10 elements and remove them.
5. Find the front element.
6. Reverse a queue.
7. Generate binary numbers from `1` to `N` using a queue.

### Level 2

8. Implement Queue using two stacks.
9. Implement Stack using two queues.
10. First non-repeating character in a stream.
11. Generate first `N` binary numbers.
12. Sliding window maximum — **we'll revisit this after Deque**.
13. Implement BFS using a queue.

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

### Next → **Deque**

Deque is particularly important because it combines the capabilities of **Stack + Queue**, and it will give you the foundation for problems like **Sliding Window Maximum** later.

