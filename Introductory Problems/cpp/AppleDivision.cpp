#include <bits/stdc++.h>
using namespace std;
#define ll long long

int n = 0;
vector<ll> wts;

// bruteforce
int findMinDiff(int n, vector<ll> wts, ll totalWt) {
    ll minDiff = LLONG_MAX, basket1Weight = 0;
    
    for (ll i = 0; i < (ll) pow(2, n); i++) {
        
        string ithPossibilty = bitset<20>(i).to_string();
        ithPossibilty = ithPossibilty.substr(20-n);

        basket1Weight = 0;
        
        for (int j = 0; j < n; j++) {
            if (ithPossibilty[j] == '1') {
                basket1Weight += wts[j];
            }
        
        }
        minDiff = min(abs(totalWt - 2*basket1Weight), minDiff);
    }

    return minDiff;
}

// recursion
ll findMinDiffR(int index, ll crate1, ll crate2) {
    // base case
    if (index == n) {
        return abs(crate1 - crate2);
    }

    // faith
    return min(
        findMinDiffR(index + 1, crate1 + wts[index], crate2),
        findMinDiffR(index + 1, crate1, crate2 + wts[index])
    );
}

// main
int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
 
    int temp;
    ll totalWt = 0;
    
    cin >> n;
    
    for (int i = 0; i < n; i++) {
        cin >> temp;
        wts.push_back(temp);
        totalWt += temp;
    }
    
    // solve: bruteforce
    //cout << findMinDiff(n, wts, totalWt);

    // solve: recursion
    cout << findMinDiffR(0, 0, 0);

    return 0;
}
