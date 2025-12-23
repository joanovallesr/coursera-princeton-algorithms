# Princeton Algorithms - Part I

This repository contains my implementations of the assignments from the [Princeton University Algorithms course](https://www.coursera.org/learn/algorithms-part1) on Coursera.

## Assignment 1: Percolation
A model to estimate the value of the percolation threshold via Monte Carlo simulation.

### The Problem
Given a composite system comprised of randomly distributed insulating and metallic materials: what fraction of the materials need to be metallic so that the composite system is an electrical conductor? This is modeled using an $n$-by-$n$ grid of sites that are either "open" or "blocked."

### Implementation Details
* **Data Structure:** Used `WeightedQuickUnionUF` to maintain connectivity between sites.
* **Optimization:** Introduced "Virtual Top" and "Virtual Bottom" nodes to reduce the complexity of the `percolates()` check to $O(1)$ calls to `find()`.
* **Backwash Solution:** Implemented a dual-Union-Find architecture. One object includes the Virtual Bottom to check for percolation, while the second object excludes it to prevent "Backwash" (the visual bug where sites appear full despite no path to the top).

### Results
* **Percolation Threshold:** $\approx 0.593$
* **Efficiency:** The constructor takes $n^2$ time; all other methods take constant time (plus logarithmic Union-Find overhead).
