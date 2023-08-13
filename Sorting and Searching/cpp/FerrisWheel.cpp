#include <iostream>
#include <algorithm>
#include <cmath>
using namespace std;

int allocateGondolas(int childrens, long maxWeight, long weights[]) {
    sort(weights, weights + childrens);
    
    int i = 0, j = childrens - 1;
    int count = 0;

    while (i <= j) {
        // calculate the total weight of two childrens
        long totalWeight = weights[i] + weights[j];
        
        if (totalWeight <= maxWeight) {
            // acceptable weight for single gondola
            count++;
            i++;
            j--;
        } else {
            // allocate last child to single gondola
            count++;
            j--;
        }
    }
    
    return count;
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int childrens;
    cin >> childrens;

    long maxWeight;
    cin >> maxWeight;

    long weights[childrens] = { 0 };

    for (int i = 0; i < childrens; i++) {
        cin >> weights[i];
    }

    // solve
    cout << allocateGondolas(childrens, maxWeight, weights);

    return 0;
}
