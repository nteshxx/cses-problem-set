#include <iostream>
#include <algorithm>
#include <cmath>
using namespace std;

int allocateApartments(long diff, long requiredSizes[], long availableSizes[], int applicants, int apartments) {
    sort(requiredSizes, requiredSizes + applicants);
    sort(availableSizes, availableSizes + apartments);
    
    int i = 0, j = 0;
    int count = 0;

    while (i < applicants && j < apartments) {
        // calculate the difference
        long sizeDifference = requiredSizes[i] - availableSizes[j];

        if (abs(sizeDifference) <= diff) {
            // acceptable difference
            count++;
            i++;
            j++;
        } else if (sizeDifference > diff) {
            // positive difference
            j++;
        } else {
            // negative difference
            i++;
        }
    }
    
    return count;
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int applicants = 0;
    cin >> applicants;

    int apartments = 0;
    cin >> apartments;

    long diff = 0;
    cin >> diff;

    long requiredSizes[applicants] = {0};
    for (int i = 0; i < applicants; i++) {
        cin >> requiredSizes[i];
    }

    long availableSizes[apartments] = {0};
    for (int i = 0; i < apartments; i++) {
        cin >> availableSizes[i];
    }

    // solve
    cout << allocateApartments(diff, requiredSizes, availableSizes, applicants, apartments);

    return 0;
}
