# 🟢 Day 1 — Java CP Data Structures

# Part 9: HashSet

You've just learned `HashMap`, so `HashSet` should be easy.

The key distinction is:

> **HashMap → Key → Value**
> **HashSet → Just values / unique elements**

Think of a HashSet as a bag that automatically refuses duplicates.

```text
Input:
[10, 20, 10, 30, 20, 40]

HashSet:
{10, 20, 30, 40}
```

---

# 1. Creating a HashSet

```java
Set<Integer> set = new HashSet<>();
```

or:

```java
HashSet<Integer> set = new HashSet<>();
```

For CP, I recommend:

```java
Set<Integer> set = new HashSet<>();
```

Import:

```java
import java.util.*;
```

---

# 2. Adding Elements

Use:

```java
set.add(10);
set.add(20);
set.add(30);
```

Now:

```text
{10, 20, 30}
```

Try:

```java
set.add(20);
```

Nothing changes.

That's the fundamental property:

> **A Set contains unique elements.**

---

# 3. Checking Whether an Element Exists

This is the most important operation:

```java
set.contains(20);
```

Returns:

```text
true
```

or:

```text
false
```

This is generally:

```text
O(1) average
```

---

# 4. Removing an Element

```java
set.remove(20);
```

Now:

```text
{10, 30}
```

Average:

```text
O(1)
```

---

# 5. Size

```java
set.size();
```

Example:

```java
System.out.println(set.size());
```

---

# 6. Empty Check

```java
set.isEmpty();
```

---

# 7. Iterating

```java
for (int x : set) {
    System.out.println(x);
}
```

But remember:

> **HashSet does not guarantee iteration order.**

If you need sorted order, that's where `TreeSet` comes in later.

---

# 8. Duplicate Detection ⭐⭐⭐

This is one of the most common uses.

Given:

```text
[1, 2, 3, 4, 2]
```

Check whether duplicates exist:

```java
Set<Integer> set = new HashSet<>();

boolean duplicate = false;

for (int x : arr) {

    if (set.contains(x)) {
        duplicate = true;
        break;
    }

    set.add(x);
}
```

The logic:

```text
Have I seen x?
       ↓
      YES → duplicate
       ↓
       NO
       ↓
Add x to set
```

This pattern is extremely important.

---

# 9. Even Simpler Duplicate Check

You can use the return value of `add()`.

```java
if (!set.add(x)) {
    System.out.println("Duplicate");
}
```

Why?

`add()` returns:

```text
true  → element was newly added
false → element already existed
```

So:

```java
if (!set.add(x))
```

means:

> "I tried to add this, but it was already there."

Very useful in CP.

---

# 10. Remove Duplicates from an Array

Given:

```text
arr = [1, 2, 2, 3, 3, 4]
```

You can:

```java
Set<Integer> set = new HashSet<>();

for (int x : arr) {
    set.add(x);
}
```

Now:

```text
{1, 2, 3, 4}
```

However, remember:

> HashSet does not preserve order.

If order matters, we'll need a different technique.

---

# 11. HashSet vs HashMap

This distinction should become automatic.

### HashMap

```java
Map<Integer, Integer> map = new HashMap<>();
```

Stores:

```text
key → value
```

Example:

```text
5 → 100
```

Useful for:

```text
Frequency
Index lookup
Mapping
Counting
Associations
```

### HashSet

```java
Set<Integer> set = new HashSet<>();
```

Stores:

```text
5
```

Useful for:

```text
Existence
Duplicates
Unique values
Membership
```

---

# 12. The "Have I Seen This?" Rule

Whenever you see a problem saying:

> Have I seen this element before?

Think:

```text
HashSet
```

If the question says:

> How many times have I seen this?

Think:

```text
HashMap
```

If it says:

> Where did I see it?

Think:

```text
HashMap<value, index>
```

This distinction will save you a lot of time in OAs.

---

# 13. HashSet with Strings

You can store strings:

```java
Set<String> set = new HashSet<>();

set.add("apple");
set.add("banana");
set.add("apple");
```

Result:

```text
{apple, banana}
```

This is useful for:

* Unique words
* Duplicate strings
* Dictionary lookup
* Membership checks

---

# 14. HashSet with Characters

```java
Set<Character> set = new HashSet<>();
```

Example:

```java
String s = "hello";

for (char c : s.toCharArray()) {
    set.add(c);
}
```

The set contains:

```text
{h, e, l, o}
```

So:

```java
set.size();
```

returns:

```text
4
```

This gives a simple way to find the number of **distinct characters**.

---

# 15. Count Distinct Elements

Given:

```text
[1, 2, 2, 3, 4, 4, 5]
```

```java
Set<Integer> set = new HashSet<>();

for (int x : arr) {
    set.add(x);
}

System.out.println(set.size());
```

Output:

```text
5
```

---

# 16. Intersection of Two Arrays ⭐

Suppose:

```text
A = [1, 2, 3, 4]
B = [3, 4, 5, 6]
```

Common elements:

```text
3, 4
```

Use:

```java
Set<Integer> set = new HashSet<>();

for (int x : A) {
    set.add(x);
}

for (int x : B) {
    if (set.contains(x)) {
        System.out.println(x);
    }
}
```

This is the foundation of many lookup-based solutions.

---

# 17. HashSet and Two Sum

Remember Two Sum from HashMap?

If the problem only asks:

> **Does any pair sum to target?**

You don't necessarily need indices.

```java
Set<Integer> set = new HashSet<>();

for (int x : arr) {

    int complement = target - x;

    if (set.contains(complement)) {
        return true;
    }

    set.add(x);
}
```

Example:

```text
arr = [2, 7, 11, 15]
target = 9
```

When we reach `7`:

```text
complement = 9 - 7
           = 2
```

`2` is already in the set.

Therefore:

```text
Pair exists.
```

---

# 18. Longest Consecutive Sequence ⭐⭐⭐

This is a famous HashSet problem.

Given:

```text
[100, 4, 200, 1, 3, 2]
```

Longest consecutive sequence:

```text
1, 2, 3, 4
```

Length:

```text
4
```

Put everything into a set:

```java
Set<Integer> set = new HashSet<>();

for (int x : arr) {
    set.add(x);
}
```

Then:

```java
for (int x : set) {

    if (!set.contains(x - 1)) {

        int current = x;
        int length = 1;

        while (set.contains(current + 1)) {
            current++;
            length++;
        }

        // update answer
    }
}
```

Why check:

```java
set.contains(x - 1)
```

?

Because we only want to start counting from the **beginning** of a sequence.

This produces an average:

```text
O(n)
```

solution instead of sorting first.

This is a great example of how the right data structure changes the algorithm.

---

# 19. HashSet with Arrays

You can technically have:

```java
Set<int[]> set = new HashSet<>();
```

But be careful.

Arrays use **reference-based equality**, so two separate arrays:

```java
new int[]{1, 2}
new int[]{1, 2}
```

are not automatically considered equal in the way you might expect.

For coordinate pairs, it's usually better to encode them or use a custom object with correct `equals()` and `hashCode()`.

We'll cover this later.

---

# 20. HashSet and `null`

Unlike `ArrayDeque`, HashSet **does allow one `null` element**.

```java
set.add(null);
```

is valid.

You won't commonly need this in CP, but it's useful Java knowledge.

---

# 21. HashSet Complexity

| Operation    | Average | Worst-case conceptually |
| ------------ | ------: | ----------------------: |
| `add()`      |    O(1) |             Can degrade |
| `remove()`   |    O(1) |             Can degrade |
| `contains()` |    O(1) |             Can degrade |
| `size()`     |    O(1) |                    O(1) |

For competitive programming:

> **Think of HashSet lookup as O(1) average.**

---

# 22. HashSet vs TreeSet

This distinction is very important.

### HashSet

```java
Set<Integer> set = new HashSet<>();
```

Properties:

```text
Fast lookup
No duplicates
No sorted order
```

Average:

```text
contains → O(1)
```

### TreeSet

```java
Set<Integer> set = new TreeSet<>();
```

Properties:

```text
No duplicates
Sorted
Supports floor/ceiling/etc.
```

Lookup:

```text
O(log n)
```

So:

```text
Need fastest membership?
        ↓
    HashSet

Need sorted unique values?
        ↓
    TreeSet
```

We'll learn `TreeSet` next.

---

# 23. HashSet vs Array

Another important decision.

Suppose:

```text
Values are only 0 to 100
```

You could use:

```java
boolean[] seen = new boolean[101];
```

instead of:

```java
HashSet<Integer> set = new HashSet<>();
```

For a small fixed range, an array is often:

* Faster
* More memory efficient
* Simpler

But if values can be:

```text
-10^9 ... 10^9
```

use:

```java
HashSet<Integer>
```

### Rule

```text
Small fixed range
      ↓
boolean[] / int[]

Large or unknown values
      ↓
HashSet / HashMap
```

---

# 24. CP Decision Tree

When you see a problem:

### "Does this exist?"

```text
HashSet
```

### "How many times?"

```text
HashMap
```

### "Where did it occur?"

```text
HashMap<value, index>
```

### "Need sorted unique elements?"

```text
TreeSet
```

### "Only values from 0...K?"

```text
boolean[] / int[]
```

This is exactly the kind of **data-structure recognition** we want you to develop before moving into DSA patterns.

---

# 🧠 HashSet Cheat Sheet

Memorize:

```java
Set<Integer> set = new HashSet<>();

set.add(x);

set.contains(x);

set.remove(x);

set.size();

set.isEmpty();
```

Very useful:

```java
if (!set.add(x)) {
    // duplicate
}
```

Iteration:

```java
for (int x : set) {
    // use x
}
```

---

# 🧪 Practice

### Level 1

1. Insert 10 integers into a HashSet.
2. Check whether a value exists.
3. Count distinct elements.
4. Detect duplicates.
5. Remove duplicates from an array.
6. Find common elements of two arrays.
7. Find elements present in one array but not another.
8. Find the first duplicate element.
9. Find the first unique element.
10. Check whether two arrays contain the same unique elements.

### Level 2

11. Two Sum — existence version.
12. Longest consecutive sequence.
13. Find missing number using a Set.
14. Find duplicate number.
15. Check if an array contains nearby duplicates.
16. Find the intersection of multiple arrays.
17. Check whether two strings contain the same unique characters.
18. Find the longest substring with no repeating characters.

**#18 is particularly important** because it leads directly into the **Sliding Window + HashSet** pattern we'll study later.

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
   ├── containsKey
   ├── Frequency
   └── Lookup

✅ HashSet
   ├── Uniqueness
   ├── Membership
   ├── Duplicate detection
   └── Lookup

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

# Next → 🟠 TreeMap & TreeSet

These are important because they solve the problem HashMap/HashSet **can't**:

> **"I need fast lookup AND sorted/order-aware operations."**

We'll learn `first`, `last`, `floor`, `ceiling`, `higher`, `lower`, range queries, and when to choose them over HashMap/HashSet.

