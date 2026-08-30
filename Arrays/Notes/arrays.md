# 🟢 Day 1 — Arrays in Java for Competitive Programming

We'll **not jump into patterns yet**. First, let's make sure you can confidently create, manipulate, search, sort, and pass arrays around in Java.

The target is:

> **Given an array problem in an OA, you should be able to immediately write the required Java code without thinking about syntax.**

---

# 1. What is an Array?

An array stores multiple values of the **same data type** in a fixed-size structure.

```java
int[] arr = {10, 20, 30, 40, 50};
```

Conceptually:

```text
Index:    0    1    2    3    4
          ↓    ↓    ↓    ↓    ↓
Array:   [10] [20] [30] [40] [50]
```

Java arrays are **zero-indexed**.

So:

```java
arr[0] → 10
arr[2] → 30
arr[4] → 50
```

---

# 2. Creating Arrays

There are several forms you should know.

### Direct initialization

```java
int[] arr = {10, 20, 30, 40};
```

### Fixed-size array

```java
int[] arr = new int[5];
```

This creates:

```text
[0, 0, 0, 0, 0]
```

For `int`, the default value is `0`.

Other common defaults:

| Type      | Default    |
| --------- | ---------- |
| `int`     | `0`        |
| `long`    | `0L`       |
| `double`  | `0.0`      |
| `char`    | `'\u0000'` |
| `boolean` | `false`    |
|  `Object`    | `null`     |

---

# 3. Getting Array Size

This is extremely important.

Use:

```java
arr.length
```

**NOT**

```java

arr.length()
```

Example:

```java
int[] arr = {10, 20, 30};

System.out.println(arr.length);
```

Output:

```text

3
```

Remember:

> Array → `.length`
> String → `.length()`
> ArrayList → `.size()`

This is a very common Java mistake.

---

# 4. Traversing an Array

### Standard `for` loop

This is the most important one for CP.

```java
for (int i = 0; i < arr.length; i++) {
    System.out.println(arr[i]);
}
```

Why do we use:

```java
i < arr.length
```

instead of:

```java
i <= arr.length
```

Because the last valid index is:

```text
arr.length - 1
```

Example:

```text
length = 5

valid indexes:
0 1 2 3 4
```

`arr[5]` causes:

```text
ArrayIndexOutOfBoundsException
```

---

# 5. Enhanced For Loop

Java also provides:

```java

for (int x : arr) {
    System.out.println(x);
}
```

This is excellent when you **don't need the index**.

Example:

```java

int sum = 0;

for (int x : arr) {
    sum += x;
}
```

But in competitive programming, you'll frequently need the index, so you'll often use the normal `for` loop.

---

# 6. Taking Array Input

This is where competitive programming starts becoming different from normal Java programming.

For learning:

```java

Scanner sc = new Scanner(System.in);

int n = sc.nextInt();

int[] arr = new int[n];

for (int i = 0; i < n; i++) {
    arr[i] = sc.nextInt();
}
```

For example, input:

```text

5
10 20 30 40 50
```

gives:

```text
arr = [10, 20, 30, 40, 50]
```

### But...

For serious OAs/competitive programming, we'll eventually use a **FastScanner** rather than `Scanner`.

We'll cover that separately because **input handling is part of your Java CP toolkit**.

---

# 7. Printing an Array

This:

```java
System.out.println(arr);
```

does **not** print the elements nicely.

Use:

```java
System.out.println(Arrays.toString(arr));
```

You need:

```java
import java.util.Arrays;
```
this will give `output`

```java
[I@4aa298b7
```

Example:

```java

int[] arr = {10, 20, 30};

System.out.println(Arrays.toString(arr));
```

Output:

```text

[10, 20, 30]
```

---

# 8. Finding Maximum

Without using built-in methods:

```java
int max = arr[0];

for (int i = 1; i < arr.length; i++) {
    if (arr[i] > max) {
        max = arr[i];
    }
}
```

Important idea:

```text
Initialize
     ↓
Scan
     ↓
Compare
     ↓
Update
```

This basic structure will appear **everywhere in DSA**.

---

# 9. Finding Minimum

Same idea:

```java
int min = arr[0];

for (int i = 1; i < arr.length; i++) {
    if (arr[i] < min) {
        min = arr[i];
    }
}
```

### Don't do this:

```java
int min = 0;
```

Why?

Consider:

```text
[-5, -2, -10]
```

Your answer would incorrectly remain `0`.

Instead:

```java
int min = arr[0];
```

---

# 10. Sum of Array

```java
int sum = 0;

for (int x : arr) {
    sum += x;
}
```

But be careful with large constraints.

If:

```text
n = 100000
arr[i] = 1000000000
```

then `int` can overflow.

Use:

```java
long sum = 0;

for (int x : arr) {
    sum += x;
}
```

This is a **very important OA habit**.

---

# 11. Reverse an Array

The basic technique:

```text
i →              ← j
[1  2  3  4  5]
```

Swap:

```text
[5  2  3  4  1]
```

Continue until:

```text
i >= j
```

Code:

```java
int i = 0;
int j = arr.length - 1;

while (i < j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;

    i++;
    j--;
}
```

This is your first exposure to the **Two Pointer technique**.

We aren't studying the pattern yet, but you'll see it repeatedly.

---

# 12. Searching

### Linear Search

Find whether `x` exists:

```java
int target = 30;
boolean found = false;

for (int i = 0; i < arr.length; i++) {
    if (arr[i] == target) {
        found = true;
        break;
    }
}
```

Time:

```text
O(n)
```

---

# 13. Find Index of Element

```java
int target = 30;
int index = -1;

for (int i = 0; i < arr.length; i++) {
    if (arr[i] == target) {
        index = i;
        break;
    }
}
```

Why `-1`?

Because `-1` represents:

> "Element doesn't exist."

This convention appears constantly in DSA.

---

# 14. `Arrays` Utility Class

Java gives you a very useful class:

```java
import java.util.Arrays;
```

### Sort

```java
Arrays.sort(arr);
```

Example:

```text
Before:
[5, 2, 8, 1, 3]

After:
[1, 2, 3, 5, 8]
```

Complexity is generally:

```text
O(n log n)
```

for primitive arrays.

---

### Fill

```java
Arrays.fill(arr, -1);
```

Result:

```text
[-1, -1, -1, -1, -1]
```

Very useful for initialization.

---

### Copy

```java
int[] copy = Arrays.copyOf(arr, arr.length);
```

You can also copy a range:

```java
int[] copy = Arrays.copyOfRange(arr, 1, 4);
```

If:

```text
arr = [10,20,30,40,50]
```

then:

```text
copy = [20,30,40]
```

Notice the ending index `4` is **exclusive**.

---

# 15. Binary Search

Java provides:

```java
Arrays.binarySearch(arr, target);
```

But there is an important condition:

> **The array must be sorted.**

Example:

```java
Arrays.sort(arr);

int index = Arrays.binarySearch(arr, 30);
```

If found, it returns an index.

If not found, it returns a negative value.

We'll later implement binary search ourselves because it is a major DSA pattern.

---

# 16. 2D Arrays

Very important for DSA.

```java
int[][] matrix = new int[3][4];
```

This creates:

```text
0 0 0 0
0 0 0 0
0 0 0 0
```

Access:

```java
matrix[0][0]
matrix[1][2]
matrix[2][3]
```

Traversal:

```java
for (int i = 0; i < matrix.length; i++) {
    for (int j = 0; j < matrix[i].length; j++) {
        System.out.print(matrix[i][j] + " ");
    }
    System.out.println();
}
```

Notice:

```java
matrix.length
```

= number of rows.

While:

```java
matrix[i].length
```

= number of columns in that row.

---

# 17. Jagged Arrays

Java technically allows rows to have different lengths.

```java
int[][] arr = new int[3][];

arr[0] = new int[2];
arr[1] = new int[4];
arr[2] = new int[3];
```

So:

```text
Row 0 → [_, _]
Row 1 → [_, _, _, _]
Row 2 → [_, _, _]
```

This becomes useful later with some graph representations.

---

# 18. Primitive Array Types You Should Know

Most common:

```java
int[] arr;
long[] arr;
double[] arr;
char[] arr;
boolean[] arr;
```

### Important CP rule

If constraints involve potentially large numbers:

```java
long[] arr
```

may be safer than:

```java
int[] arr
```

Remember:

```text
int  → ~2.1 billion maximum
long → ~9.22 × 10^18
```

---

# 19. Array vs ArrayList

This distinction matters enormously.

| Feature           | Array     | ArrayList            |
| ----------------- | --------- | -------------------- |
| Size              | Fixed     | Dynamic              |
| Syntax            | `int[]`   | `ArrayList<Integer>` |
| Primitive storage | Yes       | No, uses wrappers    |
| Access            | O(1)      | O(1)                 |
| Random access     | Excellent | Excellent            |
| CP usage          | Very high | Very high            |

For example:

```java
int[] arr = new int[100];
```

versus:

```java
ArrayList<Integer> list = new ArrayList<>();
```

We'll cover `ArrayList` after arrays.

---

# 🧪 Your First Mini Practice Set

Don't move to the next data structure yet.

Implement these **without looking at solutions**:

### Level 1 — Basic

1. Read `N` integers and print them.
2. Find maximum element.
3. Find minimum element.
4. Calculate sum.
5. Count even numbers.
6. Count positive and negative numbers.
7. Search for a target.
8. Find the first occurrence of a target.
9. Find the last occurrence.
10. Reverse the array.

### Level 2 — Slightly harder

11. Find the second largest element.
12. Find the second smallest element.
13. Check whether the array is sorted.
14. Count duplicates.
15. Remove duplicates from a **sorted** array.
16. Move all zeroes to the end.
17. Rotate array left by one.
18. Rotate array right by one.
19. Rotate array left by `k`.
20. Rotate array right by `k`.

**Don't worry about patterns yet.** We're building your implementation muscle first.

---

# 🎯 What I want you to memorize today

You don't need to memorize 50 methods.

These should become automatic:

```java
int[] arr = new int[n];

arr.length

arr[i]

for (int i = 0; i < n; i++)

for (int x : arr)

Arrays.sort(arr);

Arrays.fill(arr, value);

Arrays.toString(arr);

Arrays.copyOf(arr, size);

Arrays.binarySearch(arr, target);
```

And especially:

```text
Array → length
String → length()
ArrayList → size()
```

---

## 🔥 One more thing

For your placement preparation, we're going to build a **Java CP toolkit** alongside these data structures.

Eventually you'll have a mental template like:

```text
Java CP
│
├── Input
│   └── FastScanner
│
├── Output
│   └── StringBuilder
│
├── Arrays
│
├── Strings
│
├── ArrayList
│
├── HashMap / HashSet
│
├── Stack / Queue / Deque
│
├── PriorityQueue
│
├── TreeMap / TreeSet
│
├── LinkedList
│
├── Trees
│
└── Graphs
```

**For now, focus only on Arrays.**

Start with the 20 exercises above. Once you've attempted them, send me your code—even if it's wrong. I'll review it like an OA/interviewer and then we'll move to **ArrayList**.

