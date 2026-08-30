# 🟢 Day 1 — Java CP Data Structures

# Part 3: Strings

Strings are **extremely important** for placement OAs. You'll see them in frequency problems, palindrome problems, parsing, hashing, two pointers, sliding windows, and many implementation questions.

The important thing is to understand **`String` vs `char[]` vs `StringBuilder`**.

---

# 1. Creating a String

```java
String s = "hello";
```

You can also do:

```java
String s = new String("hello");
```

But in competitive programming, prefer:

```java
String s = "hello";
```

A String is a sequence of characters:

```text
Index:  0  1  2  3  4
        ↓  ↓  ↓  ↓  ↓
       [h][e][l][l][o]
```

---

# 2. String Length

Unlike arrays:

```java
s.length()
```

Example:

```java
String s = "hello";

System.out.println(s.length());
```

Output:

```text
5
```

Remember:

```text
Array      → arr.length
String     → s.length()
ArrayList  → list.size()
```

This should become automatic.

---

# 3. Accessing Characters

Use:

```java
s.charAt(index)
```

Example:

```java
String s = "hello";

System.out.println(s.charAt(0));
System.out.println(s.charAt(2));
```

Output:

```text
h
l
```

You **cannot** do:

```java
s[0];   // ❌
```

Unlike C/C++.

---

# 4. Traversing a String

### Normal loop

```java
for (int i = 0; i < s.length(); i++) {
    System.out.println(s.charAt(i));
}
```

### Enhanced loop

You can't directly do:

```java
for (char c : s)   // ❌
```

because `String` isn't directly iterable.

Instead:

```java
for (char c : s.toCharArray()) {
    System.out.println(c);
}
```

---

# 5. Converting String → char[]

This is extremely useful.

```java
char[] chars = s.toCharArray();
```

Example:

```java
String s = "hello";

char[] chars = s.toCharArray();
```

Now:

```text
chars = ['h', 'e', 'l', 'l', 'o']
```

You can modify it:

```java
chars[0] = 'H';
```

Then convert back:

```java
String result = new String(chars);
```

Result:

```text
Hello
```

---

# 6. Why `char[]` is Important

Java Strings are **immutable**.

For example:

```java
String s = "hello";
```

You can't directly modify:

```java
s[0] = 'H'; // ❌
```

But:

```java
char[] arr = s.toCharArray();

arr[0] = 'H';

s = new String(arr);
```

works.

This distinction becomes very important in string manipulation.

---

# 7. String Concatenation

You can concatenate:

```java
String a = "Hello";
String b = "World";

String c = a + " " + b;
```

Result:

```text
Hello World
```

For a few concatenations, that's perfectly fine.

But don't repeatedly do:

```java
s = s + c;
```

inside a huge loop.

That's where `StringBuilder` comes in.

We'll cover that shortly.

---

# 8. Comparing Strings

### ❌ Don't do this

```java
if (s1 == s2)
```

`==` compares object references, not String content.

### ✅ Use

```java
if (s1.equals(s2))
```

Example:

```java
String a = "hello";
String b = "hello";

if (a.equals(b)) {
    System.out.println("Same");
}
```

---

# 9. Case-Insensitive Comparison

```java
s1.equalsIgnoreCase(s2)
```

Example:

```java
"Hello".equalsIgnoreCase("hello");
```

returns:

```text
true
```

---

# 10. Comparing Strings Lexicographically

Use:

```java
s1.compareTo(s2)
```

Example:

```java
System.out.println("apple".compareTo("banana"));
```

The result is negative because:

```text
apple < banana
```

Useful for sorting strings.

You can also use:

```java
s1.compareToIgnoreCase(s2);
```

---

# 11. Searching Inside a String

### `contains()`

```java
s.contains("abc");
```

Returns:

```text
true / false
```

### `indexOf()`

```java
s.indexOf('a');
```

or:

```java
s.indexOf("abc");
```

Example:

```java
String s = "hello world";

System.out.println(s.indexOf('o'));
```

Returns:

```text
4
```

If it doesn't exist:

```text
-1
```

---

# 12. Last Occurrence

```java
s.lastIndexOf('l');
```

Example:

```java
String s = "hello";

System.out.println(s.lastIndexOf('l'));
```

Output:

```text
3
```

---

# 13. Substring

One of the most important String methods.

```java
s.substring(start);
```

Example:

```java
String s = "abcdef";

System.out.println(s.substring(2));
```

Output:

```text
cdef
```

---

### Start and end

```java
s.substring(start, end);
```

Example:

```java
String s = "abcdef";

System.out.println(s.substring(1, 4));
```

Output:

```text
bcd
```

Important:

> **End index is exclusive.**

```text
abcdef
  ↑ ↑
  1 4

Characters:
1 → b
2 → c
3 → d
4 → excluded
```

---

# 14. Convert to Uppercase / Lowercase

```java
s.toUpperCase();
```

```java
s.toLowerCase();
```

Example:

```java
String s = "Hello";

System.out.println(s.toLowerCase());
```

Output:

```text
hello
```

Remember that Strings are immutable, so:

```java
s.toLowerCase();
```

doesn't modify `s`.

You need:

```java
s = s.toLowerCase();
```

if you want to retain the result.

---

# 15. Removing Leading/Trailing Spaces

```java
s.trim();
```

Example:

```java
String s = "   hello   ";

s = s.trim();
```

Result:

```text
"hello"
```

For newer Java versions, you may also encounter:

```java
s.strip();
```

For normal OAs, `trim()` is still common.

---

# 16. Splitting a String

Suppose:

```java
String s = "apple banana mango";
```

Use:

```java
String[] words = s.split(" ");
```

Result:

```text
["apple", "banana", "mango"]
```

Then:

```java
for (String word : words) {
    System.out.println(word);
}
```

---

# 17. Important: `split()` Uses Regex

This can matter.

For example:

```java
s.split("\\.");
```

to split on a literal dot.

For whitespace, you can often use:

```java
s.split("\\s+");
```

This handles multiple spaces.

---

# 18. String → Integer

Suppose:

```java
String s = "12345";
```

Use:

```java
int x = Integer.parseInt(s);
```

Now:

```text
x = 12345
```

For long:

```java
long x = Long.parseLong(s);
```

---

# 19. Integer → String

Use:

```java
int x = 123;

String s = String.valueOf(x);
```

or:

```java
String s = Integer.toString(x);
```

I generally recommend:

```java
String s = String.valueOf(x);
```

because it's convenient for many data types.

---

# 20. Character Operations

This is extremely important in string problems.

Java provides:

```java
Character.isLetter(c)
Character.isDigit(c)
Character.isWhitespace(c)
Character.isUpperCase(c)
Character.isLowerCase(c)
```

Example:

```java
char c = '7';

if (Character.isDigit(c)) {
    System.out.println("Digit");
}
```

---

# 21. Character Conversion

Convert to uppercase:

```java
char c = Character.toUpperCase('a');
```

Convert to lowercase:

```java
char c = Character.toLowerCase('A');
```

---

# 22. ASCII-Based Character Tricks

You'll frequently see:

```java
c - 'a'
```

Suppose:

```java
char c = 'c';
```

Then:

```java
int index = c - 'a';
```

gives:

```text
2
```

Because:

```text
a → 0
b → 1
c → 2
...
z → 25
```

This is the foundation of the famous:

```java
int[] freq = new int[26];
```

technique.

Example:

```java
String s = "banana";

int[] freq = new int[26];

for (char c : s.toCharArray()) {
    freq[c - 'a']++;
}
```

Result conceptually:

```text
a → 3
b → 1
n → 2
```

This is one of the **most important string techniques for OAs**.

---

# 23. String Immutability

Understand this carefully.

```java
String s = "hello";

s.toUpperCase();

System.out.println(s);
```

Still:

```text
hello
```

Because String methods return a **new String**.

Correct:

```java
s = s.toUpperCase();
```

Now:

```text
HELLO
```

This is why repeatedly modifying Strings can be inefficient.

---

# 24. `StringBuilder`

This is the solution for efficient string construction.

Create:

```java
StringBuilder sb = new StringBuilder();
```

Append:

```java
sb.append("Hello");
sb.append(" ");
sb.append("World");
```

Convert to String:

```java
String result = sb.toString();
```

Result:

```text
Hello World
```

---

# 25. StringBuilder Character Operations

You can append:

```java
sb.append('a');
```

Get:

```java
sb.charAt(i);
```

Modify:

```java
sb.setCharAt(i, 'x');
```

Delete:

```java
sb.deleteCharAt(i);
```

Insert:

```java
sb.insert(i, "abc");
```

Reverse:

```java
sb.reverse();
```

---

# 26. Reversing a String

### Simple approach

```java
String reversed = new StringBuilder(s)
        .reverse()
        .toString();
```

Example:

```java
String s = "hello";

String reversed = new StringBuilder(s)
        .reverse()
        .toString();

System.out.println(reversed);
```

Output:

```text
olleh
```

---

# 27. Building Output Efficiently

This is extremely useful in OAs.

Instead of:

```java
for (int i = 0; i < n; i++) {
    System.out.print(arr[i] + " ");
}
```

you can build:

```java
StringBuilder sb = new StringBuilder();

for (int x : arr) {
    sb.append(x).append(' ');
}

System.out.println(sb);
```

For large output, this is usually preferable.

---

# 28. String vs char[] vs StringBuilder

This is the key comparison.

| Feature            | String      | char[]    | StringBuilder |
| ------------------ | ----------- | --------- | ------------- |
| Mutable            | ❌           | ✅         | ✅             |
| Access character   | `charAt()`  | `[i]`     | `charAt()`    |
| Modify character   | ❌           | ✅         | `setCharAt()` |
| Append efficiently | ❌           | Manual    | ✅             |
| Reverse            | Via Builder | Manual    | `.reverse()`  |
| Common CP use      | Very high   | Very high | Very high     |

Think:

```text
Need to READ a string
        ↓
      String

Need to MODIFY characters
        ↓
      char[]

Need to BUILD a string
        ↓
   StringBuilder
```

---

# 29. Common String Patterns You'll Eventually Use

We're **not learning these patterns yet**, but recognize them.

### Frequency

```java
int[] freq = new int[26];
```

### Reverse

```java
new StringBuilder(s).reverse()
```

### Palindrome

```text
left →      ← right
```

### Two pointers

```text
i →       ← j
```

### Sliding window

```text
[left ........ right]
```

### HashMap

```java
Map<Character, Integer> map = new HashMap<>();
```

We'll learn these properly after the data structures phase.

---

# 30. Competitive Programming Cheat Sheet

These should become muscle memory:

```java
String s = "hello";

s.length();

s.charAt(i);

s.substring(l, r);

s.equals(t);

s.contains("abc");

s.indexOf('a');

s.lastIndexOf('a');

s.toCharArray();

s.toLowerCase();

s.toUpperCase();

s.trim();

s.split(" ");

Integer.parseInt(s);

String.valueOf(x);
```

And:

```java
StringBuilder sb = new StringBuilder();

sb.append(x);

sb.charAt(i);

sb.setCharAt(i, c);

sb.deleteCharAt(i);

sb.insert(i, c);

sb.reverse();

sb.toString();
```

---

# 🧪 Practice Set

Before we move to the next data structure, practice these.

### Level 1

1. Count characters in a string.
2. Count vowels.
3. Count consonants.
4. Count digits.
5. Count spaces.
6. Convert lowercase → uppercase.
7. Reverse a string.
8. Check whether a string is a palindrome.
9. Find the first occurrence of a character.
10. Find the frequency of each lowercase character.

### Level 2

11. Find the first non-repeating character.
12. Find the first repeating character.
13. Remove spaces from a string.
14. Remove duplicate characters.
15. Check whether two strings are anagrams.
16. Find the longest word in a sentence.
17. Reverse every word in a sentence.
18. Reverse the order of words.
19. Count frequency of every word.
20. Check whether one string is a rotation of another.

---

# 🧠 One rule to remember

When solving an OA problem, ask:

```text
Do I only need to READ the string?
        ↓
      String

Do I need to CHANGE individual characters?
        ↓
      char[]

Do I need to repeatedly BUILD/APPEND?
        ↓
  StringBuilder
```

Your current roadmap is now:

```text
JAVA CP DATA STRUCTURES

✅ Arrays
✅ ArrayList
✅ String
   └── StringBuilder
   └── char[]

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

**Next logical topic: `Linked List`**, where we'll learn both the custom `Node` implementation and Java's built-in `LinkedList`.

