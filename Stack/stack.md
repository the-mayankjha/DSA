# 🟢 Day 1 — Java CP Data Structures

# Part 5: Stack

Stack is one of the simplest data structures conceptually, but it becomes **extremely powerful** in DSA problems.

The core rule:

> **LIFO — Last In, First Out**

Think of a stack of plates:

```text
        ┌─────┐
        │ 30  │ ← Last inserted / First removed
        ├─────┤
        │ 20  │
        ├─────┤
        │ 10  │
        └─────┘
```

If you insert:

```text
10 → 20 → 30
```

you remove:

```text
30 → 20 → 10
```

---

# 1. Core Stack Operations

A stack mainly has:

| Operation   | Meaning            |
| ----------- | ------------------ |
| `push(x)`   | Add element        |
| `pop()`     | Remove top         |
| `peek()`    | Look at top        |
| `isEmpty()` | Check empty        |
| `size()`    | Number of elements |

Example:

```text
push(10)
push(20)
push(30)

Stack:

30 ← top
20
10
```

Then:

```text
pop()
```

removes `30`.

---

# 2. Java's Old `Stack` Class

Java has:

```java
Stack<Integer> stack = new Stack<>();
```

You may see:

```java
stack.push(10);
stack.pop();
stack.peek();
```

It works.

But for modern competitive programming:

> **Prefer `ArrayDeque` for stack behavior.**

---

# 3. The CP Implementation You Should Use

```java
Deque<Integer> stack = new ArrayDeque<>();
```

Imports:

```java
import java.util.ArrayDeque;
import java.util.Deque;
```

Then:

```java
stack.push(10);
stack.push(20);
stack.push(30);
```

Structure:

```text
30 ← top
20
10
```

---

# 4. `push()`

```java
stack.push(10);
```

Adds `10` to the top.

Example:

```java
Deque<Integer> stack = new ArrayDeque<>();

stack.push(10);
stack.push(20);
stack.push(30);
```

Now:

```text
30
20
10
```

---

# 5. `pop()`

```java
int x = stack.pop();
```

Removes and returns the top.

If:

```text
30
20
10
```

then:

```java
stack.pop();
```

returns:

```text
30
```

Remaining:

```text
20
10
```

---

# 6. `peek()`

`peek()` looks at the top **without removing it**.

```java
int x = stack.peek();
```

Example:

```text
30
20
10
```

After:

```java
stack.peek();
```

you still have:

```text
30
20
10
```

---

# 7. `isEmpty()`

Always useful before popping.

```java
if (!stack.isEmpty()) {
    int x = stack.pop();
}
```

You should especially remember this when implementing algorithms.

---

# 8. `size()`

```java
stack.size();
```

Example:

```java
System.out.println(stack.size());
```

---

# 9. Complete Example

```java
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Deque<Integer> stack = new ArrayDeque<>();

        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println(stack.peek());

        System.out.println(stack.pop());

        System.out.println(stack.peek());

        System.out.println(stack.size());
    }
}
```

Output:

```text
30
30
20
2
```

---

# 10. Why `ArrayDeque`?

This is worth understanding.

You might wonder:

> Why not just use `Stack`?

`Stack` is an older Java class that extends `Vector`.

`ArrayDeque` is generally preferred when you simply need stack/deque behavior.

For CP, remember:

```text
Stack behavior
       ↓
Deque<Integer>
       ↓
ArrayDeque<>()
```

Template:

```java
Deque<Integer> stack = new ArrayDeque<>();
```

This should become your default.

---

# 11. Stack Using an Array — From Scratch

Understanding the implementation is useful for interviews.

```java
class MyStack {

    int[] arr;
    int top;

    MyStack(int size) {
        arr = new int[size];
        top = -1;
    }

    void push(int x) {
        arr[++top] = x;
    }

    int pop() {
        return arr[top--];
    }

    int peek() {
        return arr[top];
    }

    boolean isEmpty() {
        return top == -1;
    }
}
```

---

# 12. Understanding `top`

Initially:

```text
top = -1
```

Stack:

```text
empty
```

Push `10`:

```java
arr[++top] = 10;
```

Now:

```text
top = 0

[10]
 ↑
top
```

Push `20`:

```text
top = 1

[10, 20]
      ↑
     top
```

Push `30`:

```text
top = 2

[10, 20, 30]
          ↑
         top
```

Pop:

```java
return arr[top--];
```

returns:

```text
30
```

and:

```text
top = 1
```

---

# 13. Stack Using Linked List

You can also implement a stack using a linked list.

```java
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
    }
}
```

Stack:

```java
class MyStack {

    Node top;

    void push(int x) {
        Node newNode = new Node(x);

        newNode.next = top;
        top = newNode;
    }

    int pop() {
        int value = top.data;
        top = top.next;
        return value;
    }

    int peek() {
        return top.data;
    }

    boolean isEmpty() {
        return top == null;
    }
}
```

Both array and linked-list implementations can provide:

```text
push → O(1)
pop  → O(1)
peek → O(1)
```

---

# 14. Stack Time Complexity

For `ArrayDeque`:

| Operation   |     Complexity |
| ----------- | -------------: |
| `push()`    | O(1) amortized |
| `pop()`     |           O(1) |
| `peek()`    |           O(1) |
| `isEmpty()` |           O(1) |
| `size()`    |           O(1) |

This makes stacks very efficient.

---

# 15. Stack Is More Than Just a Data Structure

This is where it becomes important for DSA.

Stacks are useful when you need to process things in:

> **Reverse order / most recent unfinished thing first**

Common applications:

```text
Parentheses matching
Undo operations
Expression evaluation
Backtracking
DFS
Monotonic stack
Next greater element
Previous smaller element
Histogram problems
Function call stack
```

---

# 16. Classic Problem — Balanced Parentheses

Input:

```text
{[()]}
```

We want:

```text
true
```

But:

```text
{[(])}
```

should be:

```text
false
```

The idea:

```text
Opening bracket → PUSH
Closing bracket → compare with TOP
```

Example:

```text
(
↓
push

[
↓
push

]
↓
top is [
↓
pop

)
↓
top is (
↓
pop
```

Implementation:

```java
boolean isValid(String s) {

    Deque<Character> stack = new ArrayDeque<>();

    for (char c : s.toCharArray()) {

        if (c == '(' || c == '[' || c == '{') {
            stack.push(c);
        } 
        else {

            if (stack.isEmpty()) {
                return false;
            }

            char top = stack.pop();

            if (c == ')' && top != '(') return false;
            if (c == ']' && top != '[') return false;
            if (c == '}' && top != '{') return false;
        }
    }

    return stack.isEmpty();
}
```

This is one of the first **classic OA stack problems**.

---

# 17. Important Stack Pattern

Consider:

```text
2 1 5 6 2 3
```

Problems may ask:

> Find the next greater element for every element.

A brute-force approach can be O(n²).

Stack allows us to solve many such problems in:

```text
O(n)
```

This is called a:

# **Monotonic Stack**

Don't worry about mastering it now.

We'll learn it as a **DSA pattern** later.

For now, just know:

```text
Stack
 ↓
Previous/Next greater/smaller
 ↓
Monotonic Stack
```

---

# 18. Stack Traversal

You can iterate:

```java
for (int x : stack) {
    System.out.println(x);
}
```

But be careful:

> Don't build algorithm logic around iteration order.

For stack algorithms, normally use:

```java
peek()
pop()
push()
```

---

# 19. Character Stack

Stacks aren't limited to integers.

```java
Deque<Character> stack = new ArrayDeque<>();
```

Useful for:

```text
Parentheses
Expressions
String problems
Backtracking
```

---

# 20. String Stack

You can even have:

```java
Deque<String> stack = new ArrayDeque<>();
```

Although this is less common in basic CP.

---

# 21. A Very Important Java Detail

`ArrayDeque` does **not allow `null` elements**.

So:

```java
stack.push(null);
```

is invalid.

Normally this doesn't matter for CP, but it's good to know.

---

# 22. Stack vs Queue

Don't confuse these.

### Stack

```text
LIFO
```

```text
push → [10 20 30]
              ↑
             pop
```

### Queue

```text
FIFO
```

```text
10 → 20 → 30

↑          ↑
remove    add
```

We'll learn Queue next.

---

# 23. Your Stack Cheat Sheet

This is the one you should memorize:

```java
Deque<Integer> stack = new ArrayDeque<>();

stack.push(x);      // add
stack.pop();        // remove top
stack.peek();       // top
stack.isEmpty();    // empty?
stack.size();       // size
```

Character stack:

```java
Deque<Character> stack = new ArrayDeque<>();
```

---

# 🧪 Practice

### Level 1

1. Implement stack using an array.
2. Implement stack using linked list.
3. Push 10 numbers and pop them.
4. Find the top element.
5. Reverse a string using a stack.
6. Check balanced parentheses.
7. Remove adjacent duplicate characters using a stack.

### Level 2

8. Implement Min Stack.
9. Evaluate postfix expression.
10. Convert infix → postfix.
11. Next Greater Element.
12. Previous Greater Element.
13. Next Smaller Element.
14. Stock Span.
15. Largest Rectangle in Histogram.

Don't worry if **8–15 look difficult**. Those belong to the DSA-pattern phase. Right now, your goal is simply to make `Deque + ArrayDeque` feel natural.

---

## 📍 Current Progress

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

### Next → **Queue**

We'll learn **Queue → Circular Queue → `ArrayDeque` → BFS foundation**, and then move into **Deque**, which is one of the most useful structures for competitive programming.

