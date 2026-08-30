# 🔴 Day 1 — Java CP Data Structures

# Part 8: HashMap

Now we're entering one of the **most important data structures for coding OAs**.

If you become comfortable with `HashMap`, a huge number of problems become much easier.

The core idea is:

> **Store a value associated with a key so you can retrieve it quickly.**

Think of it like a dictionary:

```text
Key          Value
────────────────────
"apple"   →    5
"banana"  →    2
"orange"  →    7
```

---

# 1. What is a HashMap?

Java:

```java

HashMap<Integer, Integer> map = new HashMap<>();
```

The structure is:

```text

Key → Value
```

For example:

```java

map.put(101, 50000);
map.put(102, 60000);
map.put(103, 70000);
```

Conceptually:

```text

101 → 50000
102 → 60000
103 → 70000
```

You can then quickly ask:

```java

map.get(102);
```

and get:

```text

60000
```

---

# 2. Creating a HashMap

Import:

```java

import java.util.HashMap;
```

Or simply:

```java

import java.util.*;
```

Create:

```java

HashMap<Integer, Integer> map = new HashMap<>();
```

You can also use the interface:

```java

Map<Integer, Integer> map = new HashMap<>();
```

For CP, I recommend getting comfortable with:

```java

Map<Integer, Integer> map = new HashMap<>();
```

because it emphasizes the abstraction.

---

# 3. `put()`

Add a key-value pair:

```java

map.put(1, 100);
map.put(2, 200);
map.put(3, 300);
```

Now:

```text

1 → 100
2 → 200
3 → 300
```

---

# 4. `get()`

Retrieve a value:

```java

int value = map.get(2);
```

Result:

```text

200
```

---

# 5. What if the Key Doesn't Exist?

Suppose:

```java

map.get(99);
```

If `99` isn't present, the result is:

```text

null
```

This matters because:

```java

int x = map.get(99);
```

can cause a problem due to `null` being returned.

Instead, you can use:

```java

Integer x = map.get(99);
```

or:

```java

int x = map.getOrDefault(99, 0);
```

---

# 6. `getOrDefault()` ⭐

This is one of the most useful HashMap methods in CP.

```java

map.getOrDefault(key, defaultValue);
```

Example:

```java

int count = map.getOrDefault(10, 0);
```

Meaning:

> If key `10` exists → return its value.
> Otherwise → return `0`.

---

# 7. Frequency Counting ⭐⭐⭐

This is one of the **most important HashMap patterns**.

Suppose:

```text

arr = [1, 2, 1, 3, 2, 1]
```

We want:

```text

1 → 3
2 → 2
3 → 1
```

Code:

```java

Map<Integer, Integer> freq = new HashMap<>();

for (int x : arr) {
    freq.put(x, freq.getOrDefault(x, 0) + 1);
}
```

Let's understand:

First `1`:

```text

freq.getOrDefault(1, 0) → 0
0 + 1 → 1
```

Second `1`:

```text

1 + 1 → 2
```

Third:

```text
2 + 1 → 3
```

This one line:

```java
freq.put(x, freq.getOrDefault(x, 0) + 1);
```

should eventually become **muscle memory**.

---

# 8. Checking Whether a Key Exists

Use:

```java
map.containsKey(key);
```

Example:

```java
if (map.containsKey(10)) {
    System.out.println("Exists");
}
```

Important:

> `containsKey()` checks the **key**, not the value.

---

# 9. Checking Whether a Value Exists

You can use:

```java
map.containsValue(value);
```

Example:

```java
map.containsValue(100);
```

But this is generally **O(n)**, so it is much less useful in CP than `containsKey()`.

---

# 10. Updating a Value

Suppose:

```java
map.put(1, 100);
```

Then:

```java
map.put(1, 500);
```

Now:

```text
1 → 500
```

`put()` with an existing key **replaces the old value**.

---

# 11. Removing a Key

```java
map.remove(1);
```

This removes:

```text
1 → 500
```

---

# 12. Size

```java
map.size();
```

Number of key-value pairs.

---

# 13. Is Empty?

```java
map.isEmpty();
```

---

# 14. Iterating Through a HashMap

This is important.

### Keys

```java
for (int key : map.keySet()) {
    System.out.println(key);
}
```

### Values

```java
for (int value : map.values()) {
    System.out.println(value);
}
```

### Key + Value ⭐

Most useful:

```java
for (Map.Entry<Integer, Integer> entry : map.entrySet()) {

    int key = entry.getKey();
    int value = entry.getValue();

    System.out.println(key + " " + value);
}
```

---

# 15. Shorter Iteration

You can also use:

```java
for (var entry : map.entrySet()) {
    System.out.println(entry.getKey() + " " + entry.getValue());
}
```

`var` is valid in modern Java, but for interview/CP preparation I recommend initially being comfortable with the explicit version:

```java
Map.Entry<Integer, Integer>
```

because it makes the type obvious.

---

# 16. HashMap Does NOT Maintain Order

Suppose:

```java
map.put(5, 50);
map.put(1, 10);
map.put(3, 30);
```

Don't expect iteration to produce:

```text
5
1
3
```

or:

```text
1
3
5
```

The order is **not guaranteed**.

If you need sorted keys:

```text
TreeMap
```

If you need insertion order:

```text
LinkedHashMap
```

We'll cover `TreeMap` later.

---

# 17. HashMap with Strings

Very common.

```java
Map<String, Integer> map = new HashMap<>();
```

Example:

```java
map.put("Mayank", 95);
map.put("Rahul", 88);
map.put("Aman", 92);
```

Then:

```java
System.out.println(map.get("Mayank"));
```

Output:

```text
95
```

---

# 18. Character Frequency

Suppose:

```text
s = "banana"
```

Use:

```java
Map<Character, Integer> freq = new HashMap<>();

for (char c : s.toCharArray()) {
    freq.put(c, freq.getOrDefault(c, 0) + 1);
}
```

Result:

```text
a → 3
b → 1
n → 2
```

This is useful when the character set isn't limited to lowercase English letters.

For lowercase `a-z`, however, an array is often faster and simpler:

```java
int[] freq = new int[26];
```

---

# 19. HashMap vs Frequency Array

Very important CP decision.

Suppose the problem says:

```text
String contains only lowercase English letters
```

Prefer:

```java
int[] freq = new int[26];
```

Instead of:

```java
HashMap<Character, Integer>
```

Why?

Because there are only 26 possible keys.

But if values can be:

```text
1,000,000
-500
99999999
```

you can't reasonably make an array indexed by every possible value.

Use:

```java
HashMap<Integer, Integer>
```

### Rule

```text
Small fixed key range
        ↓
      Array

Large / unknown key range
        ↓
    HashMap
```

---

# 20. Two Sum ⭐⭐⭐

One of the most famous HashMap problems.

Given:

```text
arr = [2, 7, 11, 15]
target = 9
```

Find two numbers whose sum is `9`.

Brute force:

```text
2 + 7
2 + 11
2 + 15
7 + 11
...
```

O(n²).

HashMap approach:

```java
Map<Integer, Integer> map = new HashMap<>();

for (int i = 0; i < arr.length; i++) {

    int complement = target - arr[i];

    if (map.containsKey(complement)) {
        System.out.println(map.get(complement) + " " + i);
        break;
    }

    map.put(arr[i], i);
}
```

For:

```text
2 7 11 15
```

when we're at `7`:

```text
complement = 9 - 7 = 2
```

`2` is already in the map.

So we found the pair.

Complexity:

```text
Time  → O(n) average
Space → O(n)
```

This is a fundamental **lookup optimization**.

---

# 21. HashMap as a "Memory" of Previous Elements

This is the intuition I want you to remember.

When you see a problem asking:

> "Have I seen this before?"

think:

```text
HashSet / HashMap
```

When asking:

> "What was the index/value associated with something I saw earlier?"

think:

```text
HashMap
```

Examples:

```text
Have I seen x?
        ↓
HashSet

Have I seen x and where?
        ↓
HashMap

How many times have I seen x?
        ↓
HashMap

What value belongs to x?
        ↓
HashMap
```

---

# 22. HashMap with Multiple Values

Sometimes one key needs multiple values.

Example:

```text
student → subjects
```

You can use:

```java
Map<String, List<String>> map = new HashMap<>();
```

Initialize:

```java
map.put("Mayank", new ArrayList<>());
map.get("Mayank").add("DSA");
map.get("Mayank").add("DBMS");
```

Or use:

```java
map.computeIfAbsent("Mayank", k -> new ArrayList<>())
   .add("DSA");
```

This becomes useful in graph-like structures and grouping problems.

---

# 23. HashMap with Lists

Another common structure:

```java
Map<Integer, List<Integer>> map = new HashMap<>();
```

Example:

```java
map.computeIfAbsent(1, k -> new ArrayList<>()).add(10);
map.computeIfAbsent(1, k -> new ArrayList<>()).add(20);
```

Result:

```text
1 → [10, 20]
```

This pattern is worth recognizing, although we don't need to master it yet.

---

# 24. Custom Objects as Keys

This is an interview-level concept.

Suppose:

```java
class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
```

You **cannot blindly assume** that two different `Point` objects with the same coordinates will behave as the same HashMap key.

You need proper:

```text
equals()
hashCode()
```

implementations.

We'll cover this when we discuss Java-specific CP/interview concepts.

For now, remember:

> Custom objects used as HashMap keys need correctly implemented equality and hashing.

---

# 25. How HashMap Works Internally

You don't need to implement HashMap for most OAs, but you should understand the concept.

When you do:

```java
map.put(key, value);
```

Java calculates a hash based on the key.

Conceptually:

```text
key
 ↓
hash function
 ↓
bucket
 ↓
stored entry
```

For:

```java
map.get(key);
```

Java hashes the key again to locate the relevant bucket.

That's why lookup is typically:

```text
O(1) average
```

---

# 26. Why Is It Not Always O(1)?

Because collisions can occur.

Different keys can produce locations that map to the same bucket.

Conceptually:

```text
key A ──┐
        ├──→ bucket
key B ──┘
```

Java has mechanisms to handle collisions.

Modern Java implementations can use tree-based structures in heavily collided buckets.

You don't need to reproduce those internals in CP.

Just remember:

```text
Average → O(1)
Worst-case → can degrade
```

---

# 27. HashMap Complexity

| Operation       | Average |
| --------------- | ------: |
| `put()`         |    O(1) |
| `get()`         |    O(1) |
| `containsKey()` |    O(1) |
| `remove()`      |    O(1) |
| `size()`        |    O(1) |

For competitive programming, think:

> **HashMap = fast average lookup.**

---

# 28. Common Java Mistake

Don't do:

```java
if (map.get(key) != null)
```

to check whether a key exists.

Use:

```java
if (map.containsKey(key))
```

Because a key could legitimately map to:

```text
null
```

Also, `getOrDefault()` is usually better when you want a fallback value.

---

# 29. Another Important Mistake

This:

```java
map.put(x, map.get(x) + 1);
```

can fail if `x` doesn't exist because:

```text
map.get(x) → null
```

Instead:

```java
map.put(x, map.getOrDefault(x, 0) + 1);
```

This should become automatic for frequency counting.

---

# 30. HashMap vs ArrayList vs Array

| Requirement                   | Best choice |
| ----------------------------- | ----------- |
| Fixed indexed data            | Array       |
| Dynamic indexed data          | ArrayList   |
| Key → Value                   | HashMap     |
| Frequency of arbitrary values | HashMap     |
| Frequency of `a-z`            | `int[26]`   |
| Fast membership only          | HashSet     |

---

# 🧠 Your HashMap Cheat Sheet

These are the methods you absolutely need:

```java
Map<Integer, Integer> map = new HashMap<>();

map.put(key, value);

map.get(key);

map.getOrDefault(key, 0);

map.containsKey(key);

map.remove(key);

map.size();

map.isEmpty();
```

Iteration:

```java
for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
    int key = entry.getKey();
    int value = entry.getValue();
}
```

Frequency:

```java
map.put(x, map.getOrDefault(x, 0) + 1);
```

---

# 🧪 Practice

### Level 1

1. Create a HashMap and insert 10 key-value pairs.
2. Search for a key.
3. Update a value.
4. Remove a key.
5. Count frequencies of integers.
6. Count character frequencies.
7. Find the most frequent element.
8. Find the least frequent element.
9. Find duplicate elements.
10. Find the first element that appears only once.

### Level 2

11. Two Sum.
12. Find intersection of two arrays.
13. Find union of two arrays.
14. Group anagrams.
15. Find the longest consecutive sequence.
16. Count subarrays with a given sum.
17. Find the longest subarray with sum `K`.
18. Find the first repeating element.
19. Find elements occurring more than `n/2` times.
20. Find elements occurring more than `n/3` times.

Problems **16–20** are particularly important because they lead directly into **prefix sum + HashMap**, one of the most valuable OA patterns.

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
   ├── Iteration
   └── Lookup

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

## Next → 🔴 HashSet

HashSet is closely related to HashMap, but its purpose is simpler:

> **"Have I seen this element before?"**

Once you understand `HashSet`, you'll have the foundation for a huge class of **duplicate detection, membership, intersection, and lookup problems**.

