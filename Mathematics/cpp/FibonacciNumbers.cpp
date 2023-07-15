#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
ll MOD = 1e9+7;

void nthFibonacci(ll n, ll &a , ll &b) {
    // base case
    if (n == 0) {
        a = 0;
        b = 1;
        return;
    }

    ll x, y;

    if (n%2 == 1){
        nthFibonacci(n-1, x, y);
        a = y;
        b = (x+y) % MOD;
        return;
    }

    nthFibonacci(n/2, x, y);
    
    a = (x * (2*y + MOD -x) % MOD) % MOD;
    b = ((x*x) % MOD + (y*y) % MOD) % MOD;
    
    return;
}

// main function
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
 
    ll n, a, b;
    // nth fibonacci number
    cin >> n;
 
    // solve
    nthFibonacci(n, a, b);
    cout << a;

    return 0;
}
