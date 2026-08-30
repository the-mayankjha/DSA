# 🟢 Day 1 — Data Structures in Java

## Part 2: `ArrayList`

Now that you know normal arrays, the next thing you should learn is **ArrayList**.

The most important distinction:

> **Array = fixed size**
> **ArrayList = dynamically resizable array**

In competitive programming, `ArrayList` is especially useful when you don't know the number of elements beforehand or when you need a dynamic collection.

---

# 1. Creating an ArrayList

Import:

```java
import java.util.ArrayList;
```

Create:

```java
ArrayList<Integer> list = new ArrayList<>();
```

You can also write:

```java
List<Integer> list = new ArrayList<>();
```

For CP, I recommend becoming comfortable with both, but you'll commonly see:

```java
List<Integer> list = new ArrayList<>();
```

---

# 2. Adding Elements

Use:

```java
list.add(10);
list.add(20);
list.add(30);
```

Now:

```text
[10, 20, 30]
```

Unlike an array, you don't need to specify the final size.

You can keep doing:

```java
list.add(40);
list.add(50);
```

---

# 3. Accessing Elements

Array:

```java
arr[i]
```

ArrayList:

```java
list.get(i)
```

Example:

```java
System.out.println(list.get(1));
```

Output:

```text
20
```

---

# 4. Updating Elements

Array:

```java
arr[i] = value;
```

ArrayList:

```java
list.set(i, value);
```

Example:

```java
list.set(1, 100);
```

Before:

```text
[10, 20, 30]
```

After:

```text
[10, 100, 30]
```

---

# 5. Getting Size

This is important.

For arrays:

```java
arr.length
```

For ArrayList:

```java
list.size()
```

Example:

```java
System.out.println(list.size());
```

---

# 6. Removing Elements

You have two important forms.

### Remove by index

```java
list.remove(2);
```

Removes the element at index `2`.

### Remove by value

This is a **Java trap** when using `Integer`.

```java
list.remove(Integer.valueOf(20));
```

Why?

Because:

```java
list.remove(2);
```

means:

> Remove index 2

not:

> Remove the value 2.

---

# 7. Checking Whether an Element Exists

```java
list.contains(30);
```

Returns:

```text
true
```

or:

```text
false
```

Example:

```java
if (list.contains(50)) {
    System.out.println("Found");
}
```

This is generally **O(n)**.

---

# 8. Finding the Index

```java
int index = list.indexOf(30);
```

If found:

```text
2
```

If not found:

```text
-1
```

You can find the last occurrence using:

```java
list.lastIndexOf(30);
```

---

# 9. Iterating Through ArrayList

### Normal loop

```java
for (int i = 0; i < list.size(); i++) {
    System.out.println(list.get(i));
}
```

### Enhanced loop

```java
for (int x : list) {
    System.out.println(x);
}
```

The enhanced loop is often cleaner when you don't need the index.

---

# 10. ArrayList Input

Suppose input is:

```text
5
10 20 30 40 50
```

You can do:

```java
int n = sc.nextInt();

ArrayList<Integer> list = new ArrayList<>();

for (int i = 0; i < n; i++) {
    list.add(sc.nextInt());
}
```

---

# 11. Initial Capacity

You can optionally specify an initial capacity:

```java
ArrayList<Integer> list = new ArrayList<>(n);
```

This does **not** mean the list already contains `n` elements.

It only tells Java:

> "I expect roughly this many elements."

So this:

```java
ArrayList<Integer> list = new ArrayList<>(5);
```

still has:

```text
size = 0
```

You can verify:

```java
System.out.println(list.size());
```

Output:

```text
0
```

---

# 12. Sorting ArrayList

Use:

```java
Collections.sort(list);
```

Import:

```java
import java.util.Collections;
```

Example:

```java
ArrayList<Integer> list = new ArrayList<>();

list.add(5);
list.add(2);
list.add(8);
list.add(1);

Collections.sort(list);
```

Result:

```text
[1, 2, 5, 8]
```

---

# 13. Reverse

```java
Collections.reverse(list);
```

Example:

```text
[1, 2, 3, 4, 5]
```

becomes:

```text
[5, 4, 3, 2, 1]
```

---

# 14. Maximum and Minimum

You can use:

```java
int max = Collections.max(list);
int min = Collections.min(list);
```

Example:

```java
System.out.println(Collections.max(list));
System.out.println(Collections.min(list));
```

Both are:

```text
O(n)
```

---

# 15. ArrayList ↔ Array

This is important because you'll encounter problems where APIs use arrays but your solution uses lists.

### Array → ArrayList

For `Integer[]`:

```java
Integer[] arr = {1, 2, 3, 4};

ArrayList<Integer> list =
    new ArrayList<>(Arrays.asList(arr));
```

### ArrayList → Array

```java
Integer[] arr = list.toArray(new Integer[0]);
```

But there is an important CP consideration:

If you need a **primitive `int[]`**, you'll generally convert manually:

```java
int[] arr = new int[list.size()];

for (int i = 0; i < list.size(); i++) {
    arr[i] = list.get(i);
}
```

---

# 16. ArrayList of Strings

It isn't restricted to integers.

```java
ArrayList<String> names = new ArrayList<>();

names.add("Mayank");
names.add("Rahul");
names.add("Aman");
```

You can have:

```java
ArrayList<Long>
ArrayList<Double>
ArrayList<String>
ArrayList<Character>
```

But remember:

> Collections cannot directly store primitive types.

So:

```java
ArrayList<int>      // ❌
ArrayList<Integer>  // ✅
```

Similarly:

```java
ArrayList<long>     // ❌
ArrayList<Long>     // ✅
```

Java automatically handles boxing/unboxing in most cases:

```java
list.add(10);       // int → Integer
int x = list.get(0); // Integer → int
```

---

# 17. ArrayList of ArrayLists

This becomes **very important later for graphs**.

```java
ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
```

Initialize:

```java
int n = 5;

for (int i = 0; i < n; i++) {
    graph.add(new ArrayList<>());
}
```

Now you have:

```text
0 → []
1 → []
2 → []
3 → []
4 → []
```

Add edges:

```java
graph.get(0).add(1);
graph.get(0).add(2);
```

Now:

```text
0 → [1, 2]
1 → []
2 → []
3 → []
4 → []
```

This is the beginning of the **adjacency-list representation of graphs**.

We'll revisit this when we reach graphs.

---

# 18. ArrayList Time Complexity

You should know this table.

| Operation       |     Complexity |
| --------------- | -------------: |
| `get(i)`        |           O(1) |
| `set(i,x)`      |           O(1) |
| `add(x)`        | O(1) amortized |
| `remove(index)` |           O(n) |
| `contains(x)`   |           O(n) |
| `indexOf(x)`    |           O(n) |
| `size()`        |           O(1) |
| Sorting         |     O(n log n) |

Why can `add()` sometimes take O(n)?

Because the underlying array may become full.

Java then roughly does:

```text
Old Array
[1 2 3 4]
      ↓
Create larger array
      ↓
Copy elements
      ↓
Add new element
```

But over many additions, the average cost is **amortized O(1)**.

---

# 19. Array vs ArrayList — Remember This

|                 | Array        | ArrayList       |
| --------------- | ------------ | --------------- |
| Size            | Fixed        | Dynamic         |
| Access          | `arr[i]`     | `list.get(i)`   |
| Update          | `arr[i] = x` | `list.set(i,x)` |
| Length          | `.length`    | `.size()`       |
| Add             | Manual       | `.add()`        |
| Remove          | Manual       | `.remove()`     |
| Primitive types | ✅            | ❌               |
| CP performance  | Excellent    | Very good       |

### CP rule

If you know:

```text
N = 100000
```

and you simply need to store those `N` values:

**Prefer:**

```java
int[] arr = new int[n];
```

If you need a dynamically growing collection:

**Use:**

```java
ArrayList<Integer> list = new ArrayList<>();
```

---

# 🧪 Practice — ArrayList

Do these before moving on.

### Level 1

1. Create an ArrayList and add `10` integers.
2. Print all elements.
3. Find the maximum.
4. Find the minimum.
5. Find the sum.
6. Search for an element.
7. Find the first occurrence.
8. Reverse the list.
9. Sort the list.
10. Remove all occurrences of a particular value.

### Level 2

11. Remove all even numbers.
12. Find the second largest element.
13. Remove duplicates.
14. Merge two ArrayLists.
15. Find common elements between two lists.
16. Rotate an ArrayList by `k`.
17. Convert an `int[]` into `ArrayList<Integer>`.
18. Convert `ArrayList<Integer>` into `int[]`.

---

# ⚠️ Three Java traps to remember

### Trap 1

```java
list.size()
```

not:

```java
list.length
```

### Trap 2

```java
list.get(i)
```

not:

```java
list[i]
```

### Trap 3

```java
list.remove(2);
```

means **remove index 2**.

For removing value `2`:

```java
list.remove(Integer.valueOf(2));
```

---

## Your current progression

```text
JAVA CP DATA STRUCTURES

✅ Arrays
   ├── Creation
   ├── Traversal
   ├── Searching
   ├── Sorting
   ├── 2D arrays
   └── Arrays utility

✅ ArrayList
   ├── Add
   ├── Get
   ├── Set
   ├── Remove
   ├── Sort
   ├── Search
   └── Nested lists

⬜ Linked List
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

**Next: Linked List** — but before that, make sure `ArrayList` syntax feels natural. The next structure will introduce the concept of **nodes and references**, which is fundamentally different from arrays.

