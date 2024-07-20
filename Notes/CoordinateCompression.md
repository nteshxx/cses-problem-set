# Coordinate Compression

## Introduction

Coordinate compression is a technique used to reduce the range of coordinates (or values) in a problem to a smaller range while preserving their relative order. This is particularly useful in competitive programming and computational geometry where the original range of coordinates can be very large, leading to inefficiencies in data structures like Fenwick Trees or Segment Trees, which are often used for range queries and updates.

## Why Coordinate Compression?

- **Efficiency**: Data structures like Fenwick Trees or Segment Trees work efficiently with smaller ranges. Coordinate compression maps a large range of values to a smaller one, allowing these data structures to operate within feasible time and space constraints.
- **Relative Order Preservation**: The technique maintains the relative order of the original values, ensuring that comparisons and operations based on the order of elements remain valid.

## Steps to Perform Coordinate Compression

1. **Extract Unique Values**: Collect all unique coordinates (or values) that you need to compress.
2. **Sort the Values**: Sort these unique values.
3. **Map Original Values to Compressed Values**: Create a mapping from each original value to its new compressed value, typically based on its position in the sorted list.

## Example

Consider you have an array of intervals defined by their end points, and these end points range from 1 to 1,000,000, but you only have 5 unique end points: [10, 20, 1000000, 50, 100].

1. **Extract Unique Values**:
   - Unique end points: [10, 20, 50, 100, 1000000]

2. **Sort the Values**:
   - Sorted unique end points: [10, 20, 50, 100, 1000000]

3. **Map Original Values to Compressed Values**:
   - 10 -> 1
   - 20 -> 2
   - 50 -> 3
   - 100 -> 4
   - 1000000 -> 5

Now, instead of working with the original end points, you can work with their compressed values [1, 2, 3, 4, 5]. This makes it feasible to use data structures like Fenwick Trees, which can handle this smaller range more efficiently.

## Implementation in Java

Here's an example of how coordinate compression is implemented in Java:

### Intervals

```java
// Interval class to store start, end, and index of each interval
   private static class Interval {
      int start, end, index;

      Interval(int start, int end, int index) {
         this.start = start;
         this.end = end;
         this.index = index;
      }
   }
```

### Extract and Sort Unique End Points

```java
Set<Integer> uniqueEnds = new TreeSet<>();
for (int i = 0; i < n; i++) {
    int x = Integer.parseInt(parts[0]);
    int y = Integer.parseInt(parts[1]);
    intervals[i] = new Interval(x, y, i);
    uniqueEnds.add(y);
}
```

## Create the Mapping

```java
int count = 0;
for (int end : uniqueEnds) {
    map.put(end, ++count);
}
```

## In this code:

- `uniqueEnds` is a `TreeSet` which automatically sorts the unique end points.
- `map` is a `HashMap` that maps each original end point to its compressed value.

## Usage in the Algorithm

After coordinate compression, when updating or querying the Fenwick Tree, the code uses the compressed values instead of the original ones. For example:

```java
update(count, map.get(intervals[n - 1].end), 1);
```

This updates the Fenwick Tree using the compressed value of the end point of the last interval.

## Conclusion

Coordinate compression helps to manage large input ranges efficiently while using range query data structures. By mapping a large range of coordinates to a smaller range, operations become more feasible and efficient without losing the relative order of the original values.
