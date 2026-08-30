# 🔤 Part 16 — Trie (Prefix Tree)

A **Trie** is a tree-like data structure specifically designed for **strings**.

It is especially useful when a problem involves:

* Prefixes
* Dictionary words
* Autocomplete
* Word search
* `startsWith()`
* Counting words/prefixes

For normal string lookup, `HashSet<String>` is usually simpler.
A Trie becomes useful when the **structure of the string/prefix matters**.

---

# 1. What Is a Trie?

Suppose we insert:

```text
cat
car
care
dog
```

A Trie can look conceptually like:

```text
              root
             /    \
            c      d
            |      |
            a      o
           / \     |
          t   r    g
              |
              e
```

Notice that:

```text
cat
car
care
```

share:

```text
ca
```

That's the main advantage of a Trie.

---

# 2. Trie vs HashSet

Suppose:

```text
["cat", "car", "care", "dog"]
```

With:

```java
HashSet<String>
```

you can efficiently ask:

```java
set.contains("cat");
```

But suppose you ask:

> Does any word start with `"ca"`?

A Trie is naturally designed for that.

```java
trie.startsWith("ca");
```

returns:

```text
true
```

---

# 3. Trie Node

The basic structure:

```java
class TrieNode {

    TrieNode[] children = new TrieNode[26];

    boolean isEnd;
}
```

Each node contains:

```text
children
   ↓
26 possible letters

isEnd
   ↓
Does a word end here?
```

For lowercase English letters:

```text
'a' → index 0
'b' → index 1
...
'z' → index 25
```

---

# 4. Why `26`?

Because there are 26 lowercase English letters:

```text
a b c d ... z
```

We map:

```java
int index = ch - 'a';
```

Example:

```text
'a' - 'a' = 0
'b' - 'a' = 1
'c' - 'a' = 2
```

So:

```java
children[ch - 'a']
```

directly accesses the appropriate child.

---

# 5. Creating the Trie

```java
class Trie {

    TrieNode root;

    Trie() {
        root = new TrieNode();
    }
}
```

The root doesn't represent a character.

It's simply the starting point.

---

# 6. Insert a Word ⭐⭐⭐

Suppose we insert:

```text
cat
```

Start at:

```text
root
```

Process:

```text
c
a
t
```

Create nodes as necessary:

```text
root
 ↓
 c
 ↓
 a
 ↓
 t
```

Finally:

```java
isEnd = true;
```

This tells us:

> `"cat"` is a complete word.

---

# 7. Insert Implementation

```java
void insert(String word) {

    TrieNode current = root;

    for (char ch : word.toCharArray()) {

        int index = ch - 'a';

        if (current.children[index] == null) {
            current.children[index] = new TrieNode();
        }

        current = current.children[index];
    }

    current.isEnd = true;
}
```

---

# 8. Why `isEnd` Is Necessary ⭐

Suppose we insert:

```text
car
care
```

Trie:

```text
root
 ↓
 c
 ↓
 a
 ↓
 r
 ↓
 e
```

We need to distinguish:

```text
car
```

from:

```text
care
```

So:

```text
car  → isEnd = true
care → isEnd = true
```

Conceptually:

```text
       c
       |
       a
       |
       r ← isEnd = true
       |
       e ← isEnd = true
```

Without `isEnd`, we couldn't tell whether a node represents a complete word or merely a prefix.

---

# 9. Search a Word ⭐⭐⭐

To search:

```text
care
```

follow:

```text
c → a → r → e
```

If any required child is missing:

```text
false
```

If we reach the end:

```text
return current.isEnd;
```

---

# 10. Search Implementation

```java
boolean search(String word) {

    TrieNode current = root;

    for (char ch : word.toCharArray()) {

        int index = ch - 'a';

        if (current.children[index] == null) {
            return false;
        }

        current = current.children[index];
    }

    return current.isEnd;
}
```

---

# 11. `startsWith()` ⭐⭐⭐

This is where Trie becomes particularly useful.

Suppose we have:

```text
cat
car
care
dog
```

Ask:

```text
startsWith("ca")
```

We only need to successfully walk:

```text
c → a
```

We don't care whether `a` is the end of a complete word.

So:

```java
boolean startsWith(String prefix) {

    TrieNode current = root;

    for (char ch : prefix.toCharArray()) {

        int index = ch - 'a';

        if (current.children[index] == null) {
            return false;
        }

        current = current.children[index];
    }

    return true;
}
```

---

# 12. Complete Basic Trie

```java
class TrieNode {

    TrieNode[] children = new TrieNode[26];

    boolean isEnd;
}

class Trie {

    private TrieNode root;

    Trie() {
        root = new TrieNode();
    }

    void insert(String word) {

        TrieNode current = root;

        for (char ch : word.toCharArray()) {

            int index = ch - 'a';

            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }

            current = current.children[index];
        }

        current.isEnd = true;
    }

    boolean search(String word) {

        TrieNode current = root;

        for (char ch : word.toCharArray()) {

            int index = ch - 'a';

            if (current.children[index] == null) {
                return false;
            }

            current = current.children[index];
        }

        return current.isEnd;
    }

    boolean startsWith(String prefix) {

        TrieNode current = root;

        for (char ch : prefix.toCharArray()) {

            int index = ch - 'a';

            if (current.children[index] == null) {
                return false;
            }

            current = current.children[index];
        }

        return true;
    }
}
```

---

# 13. Using the Trie

```java
public class Main {

    public static void main(String[] args) {

        Trie trie = new Trie();

        trie.insert("cat");
        trie.insert("car");
        trie.insert("care");
        trie.insert("dog");

        System.out.println(trie.search("cat"));
        System.out.println(trie.search("can"));

        System.out.println(trie.startsWith("ca"));
        System.out.println(trie.startsWith("do"));
        System.out.println(trie.startsWith("xy"));
    }
}
```

Output:

```text
true
false
true
true
false
```

---

# 14. Trie Complexity ⭐⭐⭐

Let:

```text
L = length of the word
```

Then:

### Insert

```text
O(L)
```

### Search

```text
O(L)
```

### Prefix Search

```text
O(L)
```

Notice something important:

> Complexity depends on **word length**, not the number of stored words.

That's one of the major Trie advantages.

---

# 15. Trie Space Complexity

Each node can contain:

```java
TrieNode[26]
```

So a Trie can use significant memory.

For `N` words with total character count `C`:

```text
Space ≈ O(C × alphabet overhead)
```

The exact practical memory usage can be much larger than a `HashSet<String>`.

So don't automatically use Trie just because it's available.

---

# 16. Trie for Uppercase / Lowercase / Characters

The `26` implementation assumes:

```text
'a' to 'z'
```

If you need uppercase too:

```text
52 possibilities
```

You could use:

```java
TrieNode[] children = new TrieNode[52];
```

Or use a `HashMap<Character, TrieNode>`:

```java
Map<Character, TrieNode> children = new HashMap<>();
```

This saves memory when the alphabet is sparse.

---

# 17. Array vs HashMap Children

### Array

```java
TrieNode[] children = new TrieNode[26];
```

Advantages:

```text
Fast
Simple
O(1) character access
```

Disadvantage:

```text
Can waste memory
```

### HashMap

```java
Map<Character, TrieNode> children = new HashMap<>();
```

Advantages:

```text
Flexible alphabet
Less wasted space for sparse nodes
```

Disadvantage:

```text
More overhead
More code
```

For typical lowercase CP problems:

> **Use the 26-array version.**

---

# 18. Counting Words

Suppose:

```text
insert("apple")
insert("apple")
insert("app")
```

If the problem asks:

> How many times was a word inserted?

Use:

```java
class TrieNode {

    TrieNode[] children = new TrieNode[26];

    boolean isEnd;

    int wordCount;
}
```

At the end:

```java
current.wordCount++;
```

Then:

```java
search("apple")
```

could return:

```text
2
```

---

# 19. Counting Prefixes

A more advanced Trie technique.

Suppose:

```text
apple
app
application
apt
```

How many words start with:

```text
"app"
```

We can store:

```java
int prefixCount;
```

in every node.

During insertion:

```java
current.prefixCount++;
```

Then when we reach:

```text
a → p → p
```

we know exactly how many inserted words pass through that prefix.

---

# 20. Autocomplete

Suppose we have:

```text
apple
application
app
apt
banana
```

Input:

```text
app
```

Trie lets us reach:

```text
a → p → p
```

Then DFS from there can find:

```text
app
apple
application
```

This is the foundation of:

> **Autocomplete systems**

---

# 21. Trie + DFS

This is an important combination.

You can have:

```text
Trie
 ↓
Prefix node
 ↓
DFS
 ↓
All possible words
```

You'll see this in problems such as:

```text
Word Search II
Autocomplete
Search suggestions
Dictionary matching
```

---

# 22. Trie vs HashMap

Suppose you need:

> "Does the exact word exist?"

Use:

```java
HashSet<String>
```

Usually simpler.

Suppose you need:

> "Does any word start with this prefix?"

Trie becomes much more natural.

```text
Exact lookup:
HashSet

Prefix operations:
Trie
```

---

# 23. Trie vs TreeMap

A `TreeMap<String, ...>` can also maintain sorted strings and support ranges/prefix-like queries, but Trie is specifically optimized around **characters and prefixes**.

Think:

```text
TreeMap
 ↓
Ordering

Trie
 ↓
Character-by-character prefixes
```

---

# 24. Common Trie Problems

These are the patterns you should eventually know.

### Level 1

1. Implement Trie.
2. Insert word.
3. Search word.
4. Prefix search.
5. Count words.
6. Count prefixes.

### Level 2

7. Word dictionary.
8. Autocomplete.
9. Longest word with all prefixes.
10. Replace words using prefixes.
11. Maximum XOR using Binary Trie.
12. Word Search II.

The last two are especially interesting.

---

# 25. Binary Trie

A Trie doesn't have to store letters.

It can store **bits**.

For example:

```text
101101
101001
100111
```

Each node has:

```java
TrieNode[] children = new TrieNode[2];
```

This can be used for:

> **Maximum XOR**

This is an advanced OA/interview pattern.

We'll learn it later when we reach **Bit Manipulation + Advanced DSA**.

---

# 🧠 Trie Cheat Sheet

### Node

```java
class TrieNode {

    TrieNode[] children =
        new TrieNode[26];

    boolean isEnd;
}
```

### Insert

```text
word
 ↓
character by character
 ↓
create missing nodes
 ↓
isEnd = true
```

### Search

```text
word
 ↓
follow characters
 ↓
missing node → false
 ↓
end reached → isEnd
```

### Prefix

```text
prefix
 ↓
follow characters
 ↓
missing node → false
 ↓
reached end → true
```

### Complexity

```text
Insert       O(L)
Search       O(L)
startsWith   O(L)
```

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
   ├── Frequency
   └── Lookup

✅ HashSet
   ├── Uniqueness
   └── Membership

✅ TreeSet
   ├── Sorted values
   └── Floor/Ceiling

✅ TreeMap
   ├── Sorted keys
   └── Floor/Ceiling

✅ PriorityQueue
   ├── Min Heap
   ├── Max Heap
   └── Comparator

✅ Heap
   ├── Array representation
   ├── Heapify Up
   ├── Heapify Down
   └── Build Heap

✅ Binary Tree
   ├── Node
   ├── DFS
   ├── BFS
   └── Basic operations

✅ BST
   ├── Insert
   ├── Search
   ├── Delete
   ├── Min / Max
   ├── Floor / Ceiling
   └── Validation

✅ Graph
   ├── Matrix
   ├── Adjacency List
   ├── Directed
   ├── Undirected
   ├── Weighted
   ├── BFS
   └── DFS

✅ Trie
   ├── Node
   ├── Insert
   ├── Search
   ├── Prefix Search
   └── Prefix Counting

⬜ DSU
```

# Next → 🔗 DSU / Union-Find

This is the **last core data structure** in our current sequence.

We'll implement:

```text
DSU
├── parent[]
├── rank[]
├── size[]
├── find()
├── union()
├── Path Compression
└── Union by Rank/Size
```

And then we'll finally have the foundation needed to start the **actual DSA preparation**: patterns, problem recognition, OA strategy, and interview-style problem solving.

