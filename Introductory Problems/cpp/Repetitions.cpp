#include <bits/stdc++.h>
using namespace std;
#define ll long long

int findLongestRepetitions(string dna) {
    char current = 'A';
    ll count = 0, longestCount = 0;
    
    for(ll i = 0; i < (ll) dna.length(); i++) {
        if (dna[i] == current ) {
            count++;
        } else {
            current = dna[i];
            count = 1;
        }
        longestCount = max(count, longestCount);
    }
    return longestCount;
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
 
    string dna;
    cin >> dna;

    // solve
    cout << findLongestRepetitions(dna);

    return 0;
}
