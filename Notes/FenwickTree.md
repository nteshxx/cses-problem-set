# Fenwick Tree (Binary Indexed Tree)

## Overview

The Fenwick Tree, also known as a Binary Indexed Tree, is a data structure that supports efficient update and prefix sum operations. It is particularly useful for scenarios where you need to frequently update an array and calculate prefix sums.

## Key Concepts

1. **Prefix Sum**: Sum of elements from the start of the array up to a given index.
2. **Update Operation**: Modify an element of the array and adjust the data structure accordingly.
3. **Query Operation**: Retrieve the prefix sum up to a given index.

## Functions

1. **Update**: Adds a value to a specific index in the array.
2. **Query**: Retrieves the prefix sum up to a specific index.
3. **Range Query**: Retrieves the sum of elements between two indices.

## C++ Implementation

```cpp
#include <iostream>
#include <vector>

class FenwickTree {
private:
    std::vector<int> fenwickTree;
    int n;

public:
    // Constructor to initialize Fenwick Tree with size n
    FenwickTree(int size) {
        n = size;
        fenwickTree.assign(n + 1, 0);
    }

    // Function to update the value at index idx by delta
    void update(int idx, int delta) {
        while (idx <= n) {
            fenwickTree[idx] += delta;
            idx += idx & -idx; // Move to the next index
        }
    }

    // Function to get the prefix sum from 1 to idx
    int query(int idx) {
        int sum = 0;
        while (idx > 0) {
            sum += fenwickTree[idx];
            idx -= idx & -idx; // Move to the parent index
        }
        return sum;
    }

    // Function to get the range sum from left to right
    int rangeQuery(int left, int right) {
        return query(right) - query(left - 1);
    }
};

int main() {
    FenwickTree fenwick(10); // Create a Fenwick Tree with 10 elements

    // Update the Fenwick Tree
    fenwick.update(3, 5);
    fenwick.update(7, 3);

    // Query prefix sums
    std::cout << "Sum of elements from 1 to 3: " << fenwick.query(3) << std::endl;
    std::cout << "Sum of elements from 1 to 7: " << fenwick.query(7) << std::endl;

    // Query range sums
    std::cout << "Sum of elements from 3 to 7: " << fenwick.rangeQuery(3, 7) << std::endl;

    return 0;
}
```
## Explanation

**Constructor**: Initializes the Fenwick Tree with a given size `n`. The internal vector `fenwickTree` is initialized to 0.

**Update Function**:
- `idx` is the 1-based index where the update will be applied.
- `delta` is the value to be added.
- The while loop updates all relevant nodes in the tree.

**Query Function**:
- `idx` is the 1-based index up to which the sum is needed.
- The while loop aggregates the sum from all relevant nodes.

**Range Query Function**:
- Utilizes the `query` function to get the sum between two indices.
