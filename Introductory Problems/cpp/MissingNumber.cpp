#include <bits/stdc++.h>
using namespace std;
#define ll long long
 
int findMissingNumber(ll n, vector<ll> nums) {
    // sorting the array
    sort(nums.begin(), nums.end());
    // assume
    ll missing = 1;
    for (int i=0; i < n; i++) {
        if (nums[i] != missing) {
            return missing;
        }
        missing++;
    }

    return -1;
}

// main function
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
 
    ll n, temp;
    vector<ll> nums;
    // n number
    cin >> n;
    while (cin>>temp) {
        nums.push_back(temp);
    }
 
    // solve
    cout << findMissingNumber(n, nums);
    
    return 0;
}
