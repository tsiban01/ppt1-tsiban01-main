[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/0XTCwzWA)

# PPT: The `ArbitraryTree`

## Overview

Develop a specialized tree structure, `ArbitraryTree`, to represent hierarchical relationships among nodes where each node has a unique identifier (`nodeId`) and a descriptive name (`nodeName`). Every node can have multiple children but only one parent, ensuring a single-rooted structure. The implementation is built in incremental tasks, each introducing functionality to enhance the `ArbitraryTree` class.

## Tasks

### Task 1: Basic Operations

Implement core methods to add nodes, retrieve information, and manage node relationships based on `nodeId`.

#### Methods to Implement

1. **`addRoot(int rootId, String rootName)`**
   - Adds the root node to the tree.
   - **Precondition**: `rootId` is positive, and `rootName` is neither null nor empty.
   - **Postcondition**: If no root exists, adds it and returns `true`. If a root already exists, returns `false`.

2. **`addChildOf(int parentId, int childId, String childName)`**
   - Adds a child node to a specific parent node.
   - **Precondition**: `parentId` and `childId` are positive; `childName` is neither null nor empty.
   - **Postcondition**: Adds `childId` as a child of `parentId` if `parentId` exists and `childId` is unique; returns `true` on success, `false` otherwise.

3. **`contains(int nodeId)`**
   - Checks if a node with `nodeId` exists in the tree.
   - **Precondition**: `nodeId` is positive.
   - **Postcondition**: Returns `true` if `nodeId` exists, `false` otherwise.

4. **`getNodeName(int nodeId)`**
   - Retrieves the name of the node specified by `nodeId`.
   - **Precondition**: `nodeId` is positive.
   - **Postcondition**: Returns the node’s name if it exists, or `null` otherwise.

5. **`getParentId(int nodeId)`**
   - Returns the parent ID of the node with `nodeId`.
   - **Precondition**: `nodeId` is positive and `contains(nodeId) == true`.
   - **Postcondition**: Returns `ArbitraryTree.ROOT` if `nodeId` is the root; otherwise, returns the parent’s ID.

### Task 2: More Operations, by Name

Task 2 extends `ArbitraryTree` to support adding and querying nodes by `nodeName`, which is especially useful when identifiers are unknown.

#### Methods to Implement

1. **`addChildOf(String parentName, int childId, String childName)`**
   - Adds a child to the specified parent, identified by `parentName`.
   - **Precondition**: `parentName` and `childName` are neither null nor non-empty; `childId` is positive and unique.
   - **Postcondition**: If exactly one node matches `parentName`, adds the child and returns `true`. If multiple or no nodes match `parentName`, returns `false`.

2. **`contains(String nodeName)`**
   - Checks if any nodes have the specified `nodeName`.
   - **Precondition**: `nodeName` is neither null nor non-empty.
   - **Postcondition**: Returns `true` if at least one node matches `nodeName`; `false` otherwise.

3. **`getNodeId(String nodeName)`**
   - Returns the `nodeId` for the node with the specified name.
   - **Precondition**: `nodeName` is non-empty and `contains(nodeName) == true`.
   - **Postcondition**: Returns the `nodeId` if exactly one node matches `nodeName`. Throws `AmbiguityException` if multiple nodes match.

4. **`getAllMatches(String nodeName)`**
   - Retrieves all node IDs matching the specified `nodeName`.
   - **Precondition**: `nodeName` is neither null nor non-empty.
   - **Postcondition**: Returns a set of `nodeIds` for nodes matching `nodeName`. Returns an empty set if no matches.

5. **`getParentName(int nodeId)`**
   - Returns the parent’s name of the specified `nodeId`.
   - **Precondition**: `nodeId` is positive and `contains(nodeId) == true`.
   - **Postcondition**: Throws `NoParentException` if `nodeId` is the root. Otherwise, returns the parent’s name.

6. **`getSiblings(int nodeId)`**
   - Retrieves siblings of the node specified by `nodeId`.
   - **Precondition**: `nodeId` is positive.
   - **Postcondition**: Returns a set of sibling `nodeIds` sharing the same parent as `nodeId`, excluding `nodeId`. Returns an empty set if no siblings exist.

### Task 3: Initializing `ArbitraryTree` from Arrays + Ancestors/Descendants

Task 3 involves initializing an `ArbitraryTree` instance from two input arrays—`int[] arbitraryTree` and `String[] nodeNames`. These arrays define the tree structure and node names, enabling tree construction from external data sources. Additionally, implement methods to verify whether one node is an ancestor or descendant of another.

#### Constructor

- **Constructor**: `ArbitraryTree(int[] arbitraryTree, String[] nodeNames)`
- **Input Format**:
   - `int[] arbitraryTree`: Contains node relationships, where each entry has a `nodeId`, the count of children, and references to the indices of the children in the same array. Parents are always listed before their children in this array representation.
   - `String[] nodeNames`: Lists node names by index, so each element `i` represents the name for `nodeId = i`.

Review the following example thoroughly to understand how to interpret the input arguments to this constructor. For more examples to understand the expected behaviour, see `Task3Tests`.

**Example**:

The example below is a sample pair of inputs to the `ArbitraryTree` constructor. Observe how the tree's parent-children relationships are encoded by the input array `arbitraryTree` and how the node name is determined by its index in the input array `nodeNames`.  
In this example, Gauss has two children: Riemann and Germain;
these two children are identified by indices 4 and 8, which refer to
the position in the array where they are defined further. Other nodes
are similarly defined.

```java
// In this example, arbitraryTree is just an array of ints like any other.
// It is just formatted over multiple lines with each line corresponding to one "entry" to help you see the pattern.
int[] arbitraryTree = {
        // index: 0
        /* indices 0-3 */ 0, 2, 4, 8,         // Carl Gauss (nodeId 0) has two children: Bernhard Riemann (at index: 4), Sophie Germain (index: 8)
        // index: 4
        /* indices 4-7 */ 1, 2, 11, 17,       // Bernhard Riemann (1) has two children: Felix Klein (index: 11), Richard Dedekind (index: 17)
        // index: 8
        /* indices 8-10 */ 2, 1, 14,           // Sophie Germain (2) has one child: Joseph Fourier (index: 14)
        // index: 11
        /* indices 11-13 */ 3, 1, 19,           // Felix Klein (3) has one child: David Hilbert (index: 19)
        // index: 14
        /* indices 14-16 */ 5, 1, 23,           // Joseph Fourier (5) has one child: Ada Lovelace (index: 23)
        // index: 17
        /* indices 17-18 */ 4, 0,               // Richard Dedekind (4) has no children
        // index: 19
        /* indices 19-22 */ 6, 2, 25, 27,       // David Hilbert (6) has two children: Emmy Noether (index: 25), John von Neumann (index: 27)
        // index: 23
        /* indices 23-24 */ 9, 0,               // Ada Lovelace (9) has no children
        // index: 25
        /* indices 25-26 */ 7, 0,               // Emmy Noether (7) has no children
        // index: 27
        /* indices 27-29 */ 8, 1, 30,           // John von Neumann (8) has one child: Julia Robinson (index: 30)
        // index: 30
        /* indices 30-31 */ 10, 0               // Julia Robinson (10) has no children
};

String[] nodeNames = {
        "Carl Gauss",
        "Bernhard Riemann",
        "Sophie Germain",
        "Felix Klein",
        "Richard Dedekind",
        "Joseph Fourier",
        "David Hilbert",
        "Emmy Noether",
        "John von Neumann",
        "Ada Lovelace",
        "Julia Robinson"
};
```

#### Ancestors and Descendants

Let $u$, $v$ be any nodes in the tree. If there exists a set of nodes in the tree { $w_1, \dots, w_m$ } such that for every node (except the last one) in the sequence $u,w_1, \dots, w_m, v$ the next node in the sequence is its child, then $u$ is an ancestor of $v$ and $v$ is a descendent of $u$.

1. **`isAncestorOf(int ancestorId, int childId)`**
   - Verifies if `ancestorId` is an ancestor of `childId`.
   - **Precondition**: `ancestorId` and `childId` are positive.
   - **Postcondition**: Returns `true` if `ancestorId` is an ancestor of `childId`; otherwise, returns `false`.

2. **`isDescendantOf(int descendantId, int ancestorId)`**
   - Verifies if `descendantId` is a descendant of `ancestorId`.
   - **Precondition**: `descendantId` and `ancestorId` are positive.
   - **Postcondition**: Returns `true` if `descendantId` is a descendant of `ancestorId`; otherwise, returns `false`.

### Task 4: Family Rewards

**You will get credit for Task 4 only if you have completed Task 3 fully. Do not start Task 4 until you have completed Task 3.**

Task 4 focuses on calculating rewards for each node based on family size (the number of its **parent** and **children** only, not full descendants).

Let $fs(v)$ be the family size of node $v$ and let $c(v)$ be the number of children of $v$. If $v$ is the root node then $fs(v) = c(v)$. If $v$ is not the root node then $fs(v) = 1 + c(v)$.

Given an array, `rewards`, the total reward, $R$, associated with a tree $T$ is $R = \sum_{v \in T} rewards[fs(v)]$. The length of the rewards array will be, at the least, $n$, the number of nodes in the tree. In a tree with $n$ nodes, the maximum family size is $n-1$.

Implement methods to compute total rewards and the maximum increase in total reward that we can obtain by adding a single leaf node to `ArbitraryTree`.
A leaf node is one that has no children but must have a parent (because the tree already has a root).

#### Methods to Implement

1. **`totalReward(double[] rewards)`**
   - Computes the total reward for the tree, using each node's family size.
   - See the test cases for an example.

2. **`rewardIncreaseWithSingleNodeAddition(double[] rewards)`**
   - Given an array of rewards, of size $n+1$, determine the maximum **increase** in total rewards that is possible by adding exactly one additional node to this tree. (This node can be added as child of any existing node in the tree.)
   - See the test cases for an example.

### [BONUS!] Task 5: Maximum Reward Tree

**This task is a bonus. If you complete this task then you will get one bonus point towards your final course grade.**

Given a `rewards` array with $n$ entries, how would you construct an $n$-node `ArbitraryTree` that maximizes total reward?
You need not compute the tree but you should compute the maximum possible reward.

The method to implement is:
- **`maxPossibleReward(double[] rewards)`** (Static Method)
   - Calculates the maximum achievable reward for any tree with `rewards.length` nodes.

#### Examples for `maxPossibleReward`

- For `rewards = {0, 1, 3, 0}`, an optimal 4-node chain would yield a reward of 8.
- For `rewards = {0, 0, 0, 0, 10}`, the reward is 10 in a star tree where a root has four children.
- For `rewards = {0, 7, 0, 0}`, a star tree with three children (each with family size 1) yields 21.

## Grading

| Achievement                                | Grade |
|--------------------------------------------|-------|
| Task 1 doesn’t pass all hidden tests       | F     |
| Task 1 passes all hidden tests             | C     |
| Tasks 1, 2 pass all hidden tests           | B     |
| Tasks 1, 2, 3 pass all hidden tests        | A     |
| Tasks 1 through 4 pass all hidden tests    | A+

**You must complete Task 3 to get an A. Task 5 is a bonus.**

## Submission Instructions

+ Submit your work to the GitHub classroom repository that was created for you.
+ **Do not alter the directory/folder structure. You should retain the structure as in this repository.**
+ Do not wait until the last minute to push your work to Github. It is a good idea to push your work at intermediate points as well. _We would recommend that you get your Git and Github workflow set up at the start._

## What Should You Implement / Guidelines

+ You should implement all the methods that are indicated with `TODO`.
+ Passing the provided tests is the minimum requirement. Use the tests to identify cases that need to be handled. Passing the provided tests is *not sufficient* to infer that your implementation is complete and that you will get full credit. Additional tests will be used to evaluate your work. The provided tests are to guide you.
+ You can implement additional helper methods if you need to but you should keep these methods `private` to the appropriate classes.
+ You **may** implement additional `class`es as helpers.
+ You can use additional **standard** Java libraries by importing them. You should not use **any other** libraries.
+ Do not throw new exceptions unless the specification for the method permits exceptions.


## Honour Code

By submitting your work to GitHub you agree to the following:

+ You did not consult with any other person in completing this activity.
+ **You did not use generative AI tools (such as Copilot and ChatGPT) in completing this activity.**
+ You did not aid any other person in the class in completing their activity.
+ If you consulted any external sources, such as resources available on the World Wide Web, in completing the examination then you have cited the source. (You do not need to cite class notes or Sun/Oracle Java documentation.)
+ You are not aware of any infractions of the honour code for this activity.

## Answers to FAQs

* **Can I consult Java documentation and other Internet-based sources?**

  Yes, you can. The point of this test is not to demonstrate mastery over syntax but that you can solve a problem in a reasonable amount of time with reasonable resources. You cannot communicate with another human or use generative AI tools.

  *If you find useful information online outside the official Java documentation and the course material, you must cite the source. You should do so by adding comments in your source code.*

  Naturally you are expected to adhere to all the course and UBC policies on academic integrity.

* **Isn't 75 minutes insufficient time to produce a working implementation?**

  The questions we ask are comparable to questions one might get during a job interview. Sometimes, one gets less time for such questions during an interview.

* **Why am I not guaranteed full credit if my implementation passes all the provided tests?**

  It is easy to develop an implementation that passes the provided tests and not much else. A good-faith implementation that passes all the provided tests is very likely to pass other tests too.