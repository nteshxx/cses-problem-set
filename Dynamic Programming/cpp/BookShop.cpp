#include <bits/stdc++.h>
using namespace std;

int knapsack01(int n, int maxAmount, int prices[], int pages[]) {
    // Initialize dp array
    int dp[n+1][maxAmount+1] = {};

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= maxAmount; j++) {
            // case: we dont pick jth item
            dp[i][j] = dp[i-1][j];
            // case: we pick jth item
            if (j >= prices[i-1]) {
                // jth item prices should be smaller or equal to maxAmount
                dp[i][j] = max(dp[i][j], dp[i-1][j-prices[i-1]] + pages[i-1]);
            }
        }
    }
    
    // return the result
    return dp[n][maxAmount];       
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    
    // no. of books
    int n;
    cin >> n;
    
    // max bag amount
    int maxAmount;
    cin >> maxAmount;
    
    // price of each book
    int booksPrices[n];
    for (int i = 0; i < n; i++) {
        cin >> booksPrices[i];
    }    
    
    // pages of each book
    int booksPages[n];
    for (int i = 0; i < n; i++) {
        cin >> booksPages[i];
    }

    // solve
    cout << knapsack01(n, maxAmount, booksPrices, booksPages);
    return 0;
}
