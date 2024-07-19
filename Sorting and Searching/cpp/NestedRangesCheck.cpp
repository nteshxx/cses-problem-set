#include <bits/stdc++.h>
using namespace std;
 
struct range {
    int start, end, index;
    range(int s, int e, int i) : start(s), end(e), index(i) {}
};

bool compareStart(const range &a, const range &b) {
    if (a.start == b.start) {
        return a.end > b.end;
    }
    return a.start < b.start;
}

int main() {

    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
 
    // Get total customers
    int totalLines;
    cin >> totalLines;
 
    // Array for storing ranges
    vector<range> ranges;
 
    for (int i = 0; i < totalLines; i++) {
        // Get Start and End point of ranges
        int start, end;
        cin >> start >> end;
        ranges.emplace_back(start, end, i);
    }
 
    // Sort ranges by start, and by end descending if starts are equal
    sort(ranges.begin(), ranges.end(), compareStart);
    
    // Arrays for storing results
    vector<int> containsResult(totalLines, 0);
    vector<int> containedByResult(totalLines, 0);
 
    // Sweep line for "contains"
    int maxEnd = INT_MIN;
    for (int i = 0; i < totalLines; i++) {
        if (ranges[i].end <= maxEnd) {
            containsResult[ranges[i].index] = 1;
        }
        maxEnd = max(maxEnd, ranges[i].end);
    }
 
    // Sweep line for "containedBy"
    int minEnd = INT_MAX;
    for (int i = totalLines - 1; i >= 0; i--) {
        if (ranges[i].end >= minEnd) {
            containedByResult[ranges[i].index] = 1;
        }
        minEnd = min(minEnd, ranges[i].end);
    }
 
    // Print results
    for (int value : containedByResult) {
        cout << value << " ";
    }
    cout << "\n";
    for (int value : containsResult) {
        cout << value << " ";
    }

    return 0;    
}
