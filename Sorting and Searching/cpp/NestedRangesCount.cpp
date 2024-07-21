#include<bits/stdc++.h>
using namespace std;

// maps original y-coordinates to compressed coordinates
map<int,int> compressedCoordinates;

// max number of ranges + 1
const int maxN = 200001;

// Fenwick Tree (Binary Indexed Tree)
int BIT[maxN] = {0};

// Fenwick Tree update function
void update(int n, int i, int delta) {
    for (; i <= n; i += i & (-i)) {
        BIT[i] += delta;
    }
}

// Fenwick Tree query function
int query(int i) {
    int prefixSum = 0;
    for(; i > 0; i -= i & (-i)) {
        prefixSum += BIT[i];
    }
    return prefixSum;
}

// Compare function for sorting
bool compareStartAscEndDesc(pair<pair<int,int>,int> a, pair<pair<int,int>,int> b) {
    if (a.first.first == b.first.first) {
        return a.first.second > b.first.second;
    }
    return a.first.first < b.first.first;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    
    int n;
    cin >> n;

    // for storing unique end intervals
    set<int> uniqueEnds;

    // for storing range and its original index
    vector<pair<pair<int,int>, int>> range(n);
    
    for (int index = 0; index < n; index++) {
        int start, end;
        cin >> start >> end;
        range[index].first = {start, end};
        range[index].second = index;
        uniqueEnds.insert(end);
    }

    // assigning each end a compression value for fenwick tree optimization
    int count = 0;
    for (auto end: uniqueEnds) {
        compressedCoordinates[end] = ++count;
    }

    // sorting range vector based on start (ascending) and end (descendong) points
    sort(range.begin(), range.end(), compareStartAscEndDesc);

    // count: n
    // compressedCoordinates[range[n-1].first.second]: compressed value of last end interval
    // delta: 1
    update(count, compressedCoordinates[range[n-1].first.second], 1);

    // 
    int ans[n] = {0};
    
    for (int i = n-2; i >= 0; i--) {
        int index = range[i].second;
        int end = range[i].first.second;
        ans[index] += query(compressedCoordinates[end]);
        update(count, compressedCoordinates[end], 1);
    }

    // print answer
    for (int i = 0; i < n; i++) {
        cout << ans[i] <<' ';
    }

    // reset ans array
    memset(ans, 0, sizeof ans);
    
    // reset BIT tree
    memset(BIT, 0, sizeof BIT);
    
    update(count, 1, 1);
    update(count, compressedCoordinates[range[0].first.second] + 1, -1);
    
    for (int i = 1; i < n; i++) {
        int index = range[i].second;
        int end = range[i].first.second;
        ans[index] += query(compressedCoordinates[end]);
        update(count, 1, 1);
        update(count,  compressedCoordinates[end] + 1, -1);
    }

    // new line
    cout << '\n';

    // print answer
    for (int i = 0; i < n; i++) {
        cout << ans[i] << ' ';
    }

    return 0;
}
