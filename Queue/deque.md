# 🟢 Day 1 — Java CP Data Structures

# Part 7: Deque

Now we reach one of the **most useful Java structures for competitive programming**:

# `Deque`

Pronounced:

> **Deck**

It stands for:

> **Double-Ended Queue**

Unlike a normal queue, you can insert and remove elements from **both ends**.

```text
             Deque
        ┌───────────────┐
        ↓               ↓
     FRONT             REAR
        │               │
        ↓               ↓
      [10] [20] [30] [40]
        ↑               ↑
      remove          remove
      /add            /add
```

---

# 1. Why Deque Matters

A Deque can behave like:

### Stack

```text
push → front
pop  → front
```

### Queue

```text
offer → rear
poll  → front
```

### Double-ended structure

```text
addFirst()
addLast()
removeFirst()
removeLast()
```

So:

```text
                    Deque
                      │
             ┌────────┴────────┐
             ↓                 ↓
           Stack             Queue
```

This is why `ArrayDeque` is so useful in Java CP.

---

# 2. Java Deque

Use:

```java
Deque<Integer> deque = new ArrayDeque<>();
```

Imports:

```java
import java.util.*;
```

---

# 3. Add to Front

```java
deque.addFirst(10);
```

Suppose:

```text
[]
```

After:

```java
deque.addFirst(10);
```

you get:

```text
[10]
```

Then:

```java
deque.addFirst(20);
```

becomes:

```text
[20, 10]
```

---

# 4. Add to Back

Use:

```java
deque.addLast(30);
```

Now:

```text
[20, 10, 30]
```

So:

```text
addFirst(x) → front
addLast(x)  → rear
```

---

# 5. Remove from Front

```java
int x = deque.removeFirst();
```

For:

```text
[20, 10, 30]
 ↑
front
```

you get:

```text
20
```

Remaining:

```text
[10, 30]
```

---

# 6. Remove from Back

```java
int x = deque.removeLast();
```

For:

```text
[10, 30]
    ↑
   rear
```

you get:

```text
30
```

Remaining:

```text
[10]
```

---

# 7. `peekFirst()` and `peekLast()`

To inspect without removing:

```java
deque.peekFirst();
```

and:

```java
deque.peekLast();
```

Example:

```java
Deque<Integer> dq = new ArrayDeque<>();

dq.addLast(10);
dq.addLast(20);
dq.addLast(30);

System.out.println(dq.peekFirst());
System.out.println(dq.peekLast());
```

Output:

```text
10
30
```

---

# 8. The Core Deque API

This is what you should memorize:

```java
Deque<Integer> dq = new ArrayDeque<>();

dq.addFirst(x);
dq.addLast(x);

dq.removeFirst();
dq.removeLast();

dq.peekFirst();
dq.peekLast();

dq.isEmpty();
dq.size();
```

---

# 9. `offerFirst()` vs `addFirst()`

You may also see:

```java
dq.offerFirst(x);
dq.offerLast(x);
```

and:

```java
dq.addFirst(x);
dq.addLast(x);
```

Both are commonly used.

For `ArrayDeque`, the practical difference is mainly how failure is reported when capacity restrictions matter. `ArrayDeque` dynamically resizes, so you generally won't encounter that distinction in normal CP.

I recommend remembering:

```java
addFirst()
addLast()
removeFirst()
removeLast()
peekFirst()
peekLast()
```

because the names are intuitive.

---

# 10. Deque as a Stack

Remember our Stack:

```java
Deque<Integer> stack = new ArrayDeque<>();
```

We used:

```java
stack.push(x);
stack.pop();
stack.peek();
```

These are essentially front-oriented Deque operations.

You can also explicitly write:

```java
stack.addFirst(x);
stack.removeFirst();
stack.peekFirst();
```

So:

```text
Stack

push → addFirst
pop  → removeFirst
peek → peekFirst
```

---

# 11. Deque as a Queue

Queue:

```java
Queue<Integer> q = new ArrayDeque<>();
```

uses:

```java
q.offer(x);
q.poll();
q.peek();
```

Equivalent Deque behavior:

```text
Queue

offer → addLast
poll  → removeFirst
peek  → peekFirst
```

Therefore:

```java
Deque<Integer> dq = new ArrayDeque<>();
```

can replace both structures when you need more flexibility.

---

# 12. Why Not Just Always Use Deque?

You *can*.

For example:

```java
Deque<Integer> q = new ArrayDeque<>();
```

can behave exactly like a queue.

But using:

```java
Queue<Integer> q
```

communicates your intent more clearly.

If your algorithm is a BFS, I'd write:

```java
Queue<Integer> q = new ArrayDeque<>();
```

If I need both ends:

```java
Deque<Integer> dq = new ArrayDeque<>();
```

This is good programming practice.

---

# 13. ArrayDeque vs LinkedList

Both can implement `Deque`.

```java
Deque<Integer> dq = new ArrayDeque<>();
```

or:

```java
Deque<Integer> dq = new LinkedList<>();
```

For competitive programming:

> **Prefer `ArrayDeque`.**

It's generally more memory-efficient and avoids the per-node overhead of a linked list.

---

# 14. Important Limitation

`ArrayDeque` does **not allow `null`**.

So:

```java
dq.add(null);
```

is invalid.

This usually isn't an issue in DSA problems.

---

# 15. No Random Access

A Deque isn't an array.

Don't think:

```java
dq[3]       // ❌
dq.get(3)   // ❌
```

The intended operations are at the ends.

```java
dq.peekFirst();
dq.peekLast();
```

---

# 16. Classic Problem — Palindrome

Deque is naturally useful for checking whether a sequence reads the same from both ends.

Example:

```text
racecar
```

Deque:

```text
front → r a c e c a r ← rear
```

Compare:

```text
front == rear
```

Then remove both.

Conceptually:

```java
while (dq.size() > 1) {

    int left = dq.removeFirst();
    int right = dq.removeLast();

    if (left != right) {
        return false;
    }
}
```

This is a good example of why double-ended access matters.

---

# 17. ⭐ The Important One: Sliding Window Maximum

This is where Deque becomes extremely important.

Suppose:

```text
arr = [1, 3, -1, -3, 5, 3, 6, 7]
```

and:

```text
k = 3
```

We want the maximum of every window:

```text
[1, 3, -1] → 3
[3, -1, -3] → 3
[-1, -3, 5] → 5
[-3, 5, 3] → 5
[5, 3, 6] → 6
[3, 6, 7] → 7
```

Answer:

```text
[3, 3, 5, 5, 6, 7]
```

A naive approach checks every window:

```text
O(n × k)
```

A Deque can solve it in:

```text
O(n)
```

The structure maintains **indices** of potentially maximum elements.

This is called a:

# Monotonic Deque

Don't worry about fully learning the algorithm now.

The important takeaway:

```text
Deque
 ↓
Monotonic Deque
 ↓
Sliding Window Maximum
```

We'll study this properly when we start patterns.

---

# 18. Another Important Use — 0-1 BFS

Later in graph algorithms, you'll encounter:

```text
0-1 BFS
```

It uses a Deque.

For an edge with weight:

```text
0 → addFirst()
1 → addLast()
```

Conceptually:

```java
if (weight == 0) {
    dq.addFirst(node);
} else {
    dq.addLast(node);
}
```

This allows shortest-path processing for graphs whose edge weights are only `0` and `1`.

Again, we'll cover it later.

---

# 19. Deque with Characters

You can have:

```java
Deque<Character> dq = new ArrayDeque<>();
```

For example:

```java
dq.addLast('a');
dq.addLast('b');
dq.addLast('c');
```

Then:

```java
dq.removeFirst();
```

returns:

```text
a
```

---

# 20. Deque with Arrays

Very useful for grid/graph problems:

```java
Deque<int[]> dq = new ArrayDeque<>();
```

You can store:

```java
dq.addLast(new int[]{row, col});
```

or:

```java
dq.addFirst(new int[]{row, col});
```

---

# 21. Deque Complexity

For `ArrayDeque`:

| Operation       |     Complexity |
| --------------- | -------------: |
| `addFirst()`    | O(1) amortized |
| `addLast()`     | O(1) amortized |
| `removeFirst()` |           O(1) |
| `removeLast()`  |           O(1) |
| `peekFirst()`   |           O(1) |
| `peekLast()`    |           O(1) |
| `size()`        |           O(1) |
| `isEmpty()`     |           O(1) |

This is why it is such a powerful CP structure.

---

# 🧠 Your Deque Cheat Sheet

Memorize this:

```java
Deque<Integer> dq = new ArrayDeque<>();

// Front
dq.addFirst(x);
dq.removeFirst();
dq.peekFirst();

// Back
dq.addLast(x);
dq.removeLast();
dq.peekLast();

// General
dq.size();
dq.isEmpty();
```

And remember the mapping:

```text
STACK
push  → addFirst
pop   → removeFirst
peek  → peekFirst

QUEUE
offer → addLast
poll  → removeFirst
peek  → peekFirst
```

---

# 🧪 Practice

### Level 1

1. Implement a deque using an array.
2. Add elements from both ends.
3. Remove elements from both ends.
4. Reverse a deque.
5. Check if a string is a palindrome using a deque.
6. Implement stack using a deque.
7. Implement queue using a deque.

### Level 2

8. Sliding Window Maximum.
9. Sliding Window Minimum.
10. First negative number in every window.
11. Maximum of minimums of all windows.
12. 0-1 BFS.

Don't worry about solving 8–12 right now. They're deliberately listed so you know **where this structure eventually leads**.

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

## Next → 🔴 HashMap

This is a **major one for OAs**.

We'll cover:

```text
HashMap
├── Creation
├── put()
├── get()
├── getOrDefault()
├── containsKey()
├── remove()
├── Iteration
├── Frequency counting
├── HashMap with String
├── HashMap with custom objects
└── CP patterns
```

And we'll also understand **why HashMap gives O(1) average lookup**, rather than just memorizing its syntax.

