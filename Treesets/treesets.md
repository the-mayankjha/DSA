# 🟠 Day 1 — Java CP Data Structures

# Part 10: TreeSet & TreeMap

We've covered:

```text
Array
ArrayList
String
LinkedList
Stack
Queue
Deque
HashMap
HashSet
```

Now we move into **ordered data structures**.

The key idea:

> **HashMap / HashSet → fast lookup, no ordering**
> **TreeMap / TreeSet → sorted data + powerful order queries**

---

# Part A — TreeSet

## 1. What is TreeSet?

A `TreeSet` stores:

* Unique elements
* In sorted order

Example:

```java
Set<Integer> set = new TreeSet<>();

set.add(50);
set.add(10);
set.add(30);
set.add(20);
```

The structure behaves as:

```text
[10, 20, 30, 50]
```

Even though we inserted:

```text
50 → 10 → 30 → 20
```

the elements are automatically sorted.

---

# 2. Creating TreeSet

```java
TreeSet<Integer> set = new TreeSet<>();
```

Or:

```java
NavigableSet<Integer> set = new TreeSet<>();
```

For now, use:

```java
TreeSet<Integer> set = new TreeSet<>();
```

---

# 3. Basic Operations

### Add

```java
set.add(10);
```

### Remove

```java
set.remove(10);
```

### Search

```java
set.contains(10);
```

### Size

```java
set.size();
```

### Empty

```java
set.isEmpty();
```

These are similar to `HashSet`.

The big difference is **ordering**.

---

# 4. TreeSet Complexity

Unlike HashSet:

```text
HashSet:
contains → O(1) average
```

TreeSet:

```text
contains → O(log n)
```

Most basic operations are:

```text
add       → O(log n)
remove    → O(log n)
contains  → O(log n)
```

Why?

Because TreeSet is based on a balanced tree structure.

Conceptually:

```text
             30
           /    \
         20      50
        /       /
      10       40
```

The tree remains balanced enough to maintain logarithmic operations.

You don't need to implement this tree yourself for CP.

---

# 5. `first()`

Gets the smallest element:

```java
set.first();
```

Example:

```text
[10, 20, 30, 50]
```

```java
set.first();
```

returns:

```text
10
```

---

# 6. `last()`

Gets the largest:

```java
set.last();
```

returns:

```text
50
```

---

# 7. `lower(x)` ⭐

Returns the **largest element strictly smaller than `x`**.

Suppose:

```text
[10, 20, 30, 40]
```

```java
set.lower(30);
```

returns:

```text
20
```

Because:

```text
20 < 30
```

---

# 8. `floor(x)` ⭐

Returns the **largest element ≤ x**.

Suppose:

```text
[10, 20, 30, 40]
```

```java
set.floor(30);
```

returns:

```text
30
```

But:

```java
set.floor(25);
```

returns:

```text
20
```

Remember:

```text
floor(x) = ≤ x
```

---

# 9. `higher(x)` ⭐

Returns the **smallest element strictly greater than `x`**.

```java
set.higher(30);
```

For:

```text
[10, 20, 30, 40]
```

returns:

```text
40
```

---

# 10. `ceiling(x)` ⭐

Returns the **smallest element ≥ x**.

```java
set.ceiling(30);
```

returns:

```text
30
```

But:

```java
set.ceiling(31);
```

returns:

```text
40
```

Remember:

```text
ceiling(x) = ≥ x
```

---

# 11. The Four You MUST Remember

These are extremely useful in OAs:

```text
lower(x)    → < x
floor(x)    → ≤ x
ceiling(x)  → ≥ x
higher(x)   → > x
```

Visualize:

```text
        lower       floor
          ↓           ↓
10 ── 20 ── 30 ── 40 ── 50
                    ↑
                  higher
```

The exact target position determines which one you need.

---

# 12. Example

Suppose:

```java
TreeSet<Integer> set = new TreeSet<>();

set.add(10);
set.add(20);
set.add(30);
set.add(40);
set.add(50);
```

For:

```java
int x = 35;
```

we get:

```text
lower(35)   → 30
floor(35)   → 30
ceiling(35) → 40
higher(35)  → 40
```

For:

```text
x = 30
```

we get:

```text
lower(30)   → 20
floor(30)   → 30
ceiling(30) → 30
higher(30)  → 40
```

This distinction is very important.

---

# 13. Descending Order

By default:

```text
10 20 30 40
```

You can get descending order:

```java
TreeSet<Integer> set =
    new TreeSet<>(Collections.reverseOrder());
```

Now:

```text
40 30 20 10
```

---

# 14. Iterating

Normal:

```java
for (int x : set) {
    System.out.println(x);
}
```

Output:

```text
10
20
30
40
50
```

Descending:

```java
for (int x : set.descendingSet()) {
    System.out.println(x);
}
```

---

# 15. Removing Smallest/Largest

You can use:

```java
set.pollFirst();
set.pollLast();
```

Example:

```text
[10, 20, 30, 40]
```

```java
set.pollFirst();
```

returns:

```text
10
```

and leaves:

```text
[20, 30, 40]
```

---

# 16. When Should You Use TreeSet?

Think:

> **I need unique values AND I care about order.**

Examples:

### Find nearest smaller

```java
set.lower(x);
```

### Find nearest greater

```java
set.higher(x);
```

### Find smallest value ≥ x

```java
set.ceiling(x);
```

### Find largest value ≤ x

```java
set.floor(x);
```

This is where TreeSet becomes extremely powerful.

---

# Part B — TreeMap

Now the same concept, but with:

```text
Key → Value
```

Instead of:

```text
just values
```

---

# 17. Creating TreeMap

```java
TreeMap<Integer, String> map = new TreeMap<>();
```

Example:

```java
map.put(30, "C");
map.put(10, "A");
map.put(20, "B");
```

Keys are automatically sorted:

```text
10 → A
20 → B
30 → C
```

---

# 18. Basic Operations

Same concepts as HashMap:

```java
map.put(key, value);

map.get(key);

map.containsKey(key);

map.remove(key);

map.size();

map.isEmpty();
```

But TreeMap adds **order operations**.

---

# 19. `firstKey()`

```java
map.firstKey();
```

Returns the smallest key.

---

# 20. `lastKey()`

```java
map.lastKey();
```

Returns the largest key.

---

# 21. `firstEntry()`

```java
map.firstEntry();
```

Returns:

```text
key + value
```

Example:

```java
Map.Entry<Integer, String> entry = map.firstEntry();
```

Then:

```java
entry.getKey();
entry.getValue();
```

---

# 22. `lastEntry()`

```java
map.lastEntry();
```

Same idea for the largest key.

---

# 23. TreeMap's Four Most Important Methods

Exactly like TreeSet:

```java
map.lowerKey(x);
map.floorKey(x);
map.ceilingKey(x);
map.higherKey(x);
```

Meaning:

```text
lowerKey(x)    → < x
floorKey(x)    → ≤ x
ceilingKey(x)  → ≥ x
higherKey(x)   → > x
```

---

# 24. Example

Suppose:

```text
10 → A
20 → B
30 → C
40 → D
```

For:

```text
x = 25
```

we get:

```text
lowerKey(25)    → 20
floorKey(25)    → 20
ceilingKey(25)  → 30
higherKey(25)   → 30
```

For:

```text
x = 30
```

```text
lowerKey(30)    → 20
floorKey(30)    → 30
ceilingKey(30)  → 30
higherKey(30)   → 40
```

---

# 25. Why TreeMap Is Useful

Suppose you're processing numbers dynamically.

You need:

> "Among all previously seen numbers, find the smallest number ≥ X."

HashMap can't do this directly.

TreeMap can:

```java
Integer result = map.ceilingKey(x);
```

Similarly:

> "Find the largest previous number ≤ X."

```java
Integer result = map.floorKey(x);
```

This is extremely useful in more advanced OA problems.

---

# 26. TreeMap vs HashMap

This distinction should become automatic.

### HashMap

```java
Map<Integer, Integer> map = new HashMap<>();
```

```text
Fast average lookup
No sorted keys
O(1) average
```

### TreeMap

```java
Map<Integer, Integer> map = new TreeMap<>();
```

```text
Sorted keys
floor
ceiling
lower
higher
O(log n)
```

Decision:

```text
Need just lookup?
      ↓
   HashMap

Need lookup + sorted/order queries?
      ↓
   TreeMap
```

---

# 27. TreeSet vs HashSet

Same idea:

```text
HashSet
 ↓
Unique + fast lookup

TreeSet
 ↓
Unique + sorted + order queries
```

| Feature    |  HashSet |  TreeSet |
| ---------- | -------: | -------: |
| Unique     |        ✅ |        ✅ |
| Sorted     |        ❌ |        ✅ |
| `contains` | O(1) avg | O(log n) |
| `add`      | O(1) avg | O(log n) |
| `floor`    |        ❌ |        ✅ |
| `ceiling`  |        ❌ |        ✅ |
| `lower`    |        ❌ |        ✅ |
| `higher`   |        ❌ |        ✅ |

---

# 28. TreeMap vs TreeSet

Think:

```text
TreeSet
    ↓
[10, 20, 30, 40]
```

TreeMap:

```text
10 → A
20 → B
30 → C
40 → D
```

So:

```text
Need unique values
       ↓
    TreeSet

Need key → value
       ↓
    TreeMap
```

---

# 29. Range Queries

TreeSet/TreeMap also support ranges.

For example:

```java
set.subSet(20, 50);
```

Conceptually:

```text
20 ≤ x < 50
```

You can also specify inclusive boundaries:

```java
set.subSet(20, true, 50, true);
```

Then:

```text
20 ≤ x ≤ 50
```

Similarly:

```java
set.headSet(30);
```

means elements `< 30`.

And:

```java
set.tailSet(30);
```

means elements `≥ 30`.

These are useful, but they're less important initially than:

```text
floor
ceiling
lower
higher
```

---

# 30. `pollFirst()` / `pollLast()`

Both TreeSet and TreeMap support removing extreme entries.

TreeSet:

```java
set.pollFirst();
set.pollLast();
```

TreeMap:

```java
map.pollFirstEntry();
map.pollLastEntry();
```

Useful when you need to repeatedly remove the smallest/largest element.

---

# 31. A Useful CP Example

Suppose you have:

```text
numbers already seen = [10, 20, 40, 50]
```

and you need:

> smallest number ≥ 35

TreeSet:

```java
TreeSet<Integer> set = new TreeSet<>();

set.add(10);
set.add(20);
set.add(40);
set.add(50);

Integer answer = set.ceiling(35);
```

Answer:

```text
40
```

Without TreeSet, you'd often need to sort/search or maintain some other structure.

---

# 32. Null Behavior

`TreeSet` and `TreeMap` generally **do not support null keys/elements** under their natural ordering.

So don't rely on:

```java
set.add(null);
```

for TreeSet.

This differs from HashSet.

---

# 33. Custom Sorting

You can construct TreeSet with a comparator.

For descending integers:

```java
TreeSet<Integer> set =
    new TreeSet<>(Collections.reverseOrder());
```

You can also use:

```java
TreeSet<String> set =
    new TreeSet<>(Comparator.reverseOrder());
```

We'll spend more time on **Comparators** when we reach `PriorityQueue`, because that is where custom ordering becomes essential.

---

# 🧠 The Most Important Mental Model

You now have four major lookup structures:

```text
                    LOOKUP
                       │
          ┌────────────┴────────────┐
          ↓                         ↓
       Unique                    Key → Value
          │                         │
      ┌───┴───┐                 ┌───┴───┐
      ↓       ↓                 ↓       ↓
  HashSet  TreeSet           HashMap  TreeMap
      │       │                 │       │
     O(1)   O(log n)           O(1)    O(log n)
     avg
```

And the decision:

```text
Just existence?
    → HashSet

Existence + sorted/order queries?
    → TreeSet

Key → value?
    → HashMap

Key → value + sorted/order queries?
    → TreeMap
```

---

# 🧠 Cheat Sheet

### TreeSet

```java
TreeSet<Integer> set = new TreeSet<>();

set.add(x);
set.remove(x);
set.contains(x);

set.first();
set.last();

set.lower(x);
set.floor(x);
set.ceiling(x);
set.higher(x);

set.pollFirst();
set.pollLast();

set.size();
set.isEmpty();
```

### TreeMap

```java
TreeMap<Integer, Integer> map = new TreeMap<>();

map.put(key, value);
map.get(key);
map.remove(key);
map.containsKey(key);

map.firstKey();
map.lastKey();

map.lowerKey(x);
map.floorKey(x);
map.ceilingKey(x);
map.higherKey(x);

map.firstEntry();
map.lastEntry();

map.pollFirstEntry();
map.pollLastEntry();
```

---

# 🧪 Practice

### TreeSet

1. Insert numbers and print them sorted.
2. Find minimum and maximum.
3. Find predecessor of `X`.
4. Find successor of `X`.
5. Find floor of `X`.
6. Find ceiling of `X`.
7. Remove smallest element repeatedly.
8. Find unique elements in sorted order.
9. Find the closest value to `X`.
10. Maintain a dynamic set of numbers.

### TreeMap

11. Store student marks sorted by roll number.
12. Find smallest key ≥ X.
13. Find largest key ≤ X.
14. Maintain frequencies using TreeMap.
15. Find the most recent key before X.
16. Process numbers dynamically and answer floor/ceiling queries.

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

⬜ PriorityQueue
⬜ Heap
⬜ Binary Tree
⬜ BST
⬜ Graph
⬜ Trie
⬜ DSU
```

# Next → 🔴 PriorityQueue

This is a **very important CP structure** because it gives you a ready-made **Heap**.

We'll learn:

```text
PriorityQueue
├── Min Heap
├── Max Heap
├── peek()
├── poll()
├── offer()
├── Custom Comparator
├── Pair-like structures
└── Top-K problems
```

And I'll make the distinction between **PriorityQueue vs Heap** very clear, because they're related but not exactly the same concept.

