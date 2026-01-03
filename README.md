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

---

## Assignment 3: Collinear Points
A pattern recognition algorithm to identify every line segment connecting 4 or more points in a set.

### The Problem
Given a set of $n$ distinct points in the plane, find every line segment that connects a subset of 4 or more of the points. The solution must handle corner cases (duplicate points, vertical lines) and avoid identifying sub-segments (only "maximal" segments should be returned).

### Implementation Details
* **Point Data Type:** Implemented a `Comparator` to compare points by the slope they make with an invoking point. Handled edge cases for vertical lines ($+\infty$), horizontal lines ($+0.0$), and degenerate segments ($-\infty$).
* **Brute Force Approach:** A naive solution that checks every combination of 4 points using 4 nested loops. Time complexity: $O(N^4)$.
* **Fast Sorting Approach:** An optimized algorithm using a "Lighthouse" strategy:
    * **Sorting by Slope:** For every point $p$, we treat it as an origin and sort all other points by the slope they make with $p$.
    * **Finding Runs:** Because the points are sorted by slope, collinear points appear as contiguous "runs" (adjacent blocks) in the array. We scan the array to identify streaks of 3 or more points with equal slopes.
    * **Maximal Segments:** To prevent duplicates and sub-segments, we enforce a strict ordering rule: a segment is only recorded if point $p$ is strictly smaller (lexicographically) than all other points in the collinear set.

### Efficiency
* **FastCollinearPoints:** Achieves $O(N^2 \log N)$ time complexity. The bottleneck is the sorting operation ($N \log N$) performed for each of the $N$ points.
* **Space Complexity:** Uses $O(N)$ space for auxiliary arrays during sorting.
