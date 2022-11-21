#include <bits/stdc++.h>
using namespace std;
#define ll long long

void towerOfHanoi(ll n, char source, char middle, char destination) {
    // base case
    if (n == 1) {
        cout << source << " " << destination << '\n';
        return;
    }
    towerOfHanoi(n-1, source, destination, middle);
    cout << source << " " << destination << '\n';
    towerOfHanoi(n-1, middle, source, destination);
}

// main function
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
 
    ll n;
    // no. of disks
    cin >> n;
 
    // tower of hanoi has 2^n - 1 steps
    cout << (1<<n)-1 << "\n";
    // solve
    towerOfHanoi(n, '1', '2', '3');
    return 0;
}
