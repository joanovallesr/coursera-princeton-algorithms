# Princeton Algorithms - Part I

This repository contains my implementations of the assignments from the [Princeton University Algorithms course](https://www.coursera.org/learn/algorithms-part1) on Coursera.

## Assignment 1: Percolation
A model to estimate the value of the percolation threshold via Monte Carlo simulation.

### The Problem
Given a composite system comprised of randomly distributed insulating and metallic materials: what fraction of the materials need to be metallic so that the composite system is an electrical conductor? This is modeled using an n-by-n grid of sites that are either "open" or "blocked."

### Implementation Details
* **Data Structure:** Used `WeightedQuickUnionUF` to maintain connectivity between sites.
* **Optimization:** Introduced "Virtual Top" and "Virtual Bottom" nodes to reduce the complexity of the `percolates()` check to $O(1)$ calls to `find()`.
* **Backwash Solution:** Implemented a dual-Union-Find architecture. One object includes the Virtual Bottom to check for percolation, while the second object excludes it to prevent "Backwash" (the visual bug where sites appear full despite no path to the top).

### Results
* **Percolation Threshold:** $\approx 0.593$
* **Efficiency:** The constructor takes $n^2$ time; all other methods take constant time (plus logarithmic Union-Find overhead).

---

## Assignment 2: Deques and Randomized Queues
A library of generic data structures that are statistically robust and efficient.

### The Problem
Build two generic data structures: a **Deque** (double-ended queue) that allows insertion and deletion from both ends, and a **Randomized Queue** that samples or removes items uniformly at random. Additionally, create a client program `Permutation` that uses these structures to generate random subsets of input data.

### Implementation Details
* **Deque (Doubly Linked List):** Implemented using a custom Node class with `prev` and `next` pointers. This ensures every operation (`addFirst`, `addLast`, `removeFirst`, `removeLast`) runs in constant worst-case time $O(1)$.
* **Randomized Queue (Resizing Array):** Implemented using a dynamic array.
    * **Random Removal:** Used a "Swap-and-Pop" technique (swapping the target item with the last item before removal) to achieve $O(1)$ amortized time for `dequeue()`.
    * **Memory Management:** Implemented explicit nulling of array indices to prevent "loitering" (memory leaks) when items are removed.
    * **Independent Iterators:** Each iterator creates a private, shuffled copy of the internal array to ensure multiple iterators can run simultaneously with unique random orders.

### Efficiency
* **Deque:** Guaranteed constant time $O(1)$ for all operations. Memory usage is proportional to the number of items ($48n$ bytes).
* **Randomized Queue:** Constant amortized time $O(1)$ for all operations. Array resizing ensures memory usage is always within $25\%$ to $100\%$ full.
