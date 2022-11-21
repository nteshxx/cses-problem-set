#include <bits/stdc++.h>
using namespace std;
#define ll long long
 
void weirdAlgorithm(ll n) {
    while (true) {
        cout << n << " ";
        // base case 
        if (n == 1) {
            break;
        }
        // weird algorithm
        n = (n%2 == 0) ? n/2 : (n*3)+1;
    }
}
 
// main function
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
 
    ll n;
    // number
    cin >> n;
 
    // solve
    weirdAlgorithm(n);
    
    return 0;
}