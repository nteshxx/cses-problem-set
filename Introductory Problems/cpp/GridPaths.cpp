#include <bits/stdc++.h>
using namespace std;

int n = 7;
int paths = 0;
char reservedSteps[49];

void traverseGrid(bool grid[7][7], int row, int column, int steps) {

  	// goal
  	// reached the destination after traversing complete grid
  	if (row == n - 1 && column == 0) {
    	if (steps == n * n - 1) {
      		paths++;
    	}

    	// optimization 2
    	// reached the destination without traversing complete grid
    	return;
  	}

  	// optimization 1
  	// if already visited
  	if ((grid[row][column])
    	// optimization 3
    	// hit the wallS with unvisited blocks on both side
    	|| ((row == 0 || row == 6) && column > 0 && column < 6 && !grid[row][column - 1] && !grid[row][column + 1]) // UP DOWN WALLS
    	|| ((column == 0 || column == 6) && row > 0 && row < 6 && !grid[row - 1][column] && !grid[row + 1][column]) // LEFT RIGHT
    	// optimization 4
    	// hit a block where unvisited blocks on both side on block on forward path
    	|| (row > 0 && row < 6 && column > 0 && column < 6 && grid[row - 1][column] && grid[row + 1][column] && !grid[row][column - 1] && !grid[row][column + 1]) // UP DOWN
    	|| (row > 0 && row < 6 && column > 0 && column < 6 && grid[row][column - 1] && grid[row][column + 1] && !grid[row - 1][column] && !grid[row + 1][column])) // LEFT RIGHT
  	{
    	return;
  	}

  	// move as per the instructed path
  	if (reservedSteps[steps] != '?') {
    	
		switch (reservedSteps[steps]) {
    	
		case 'U':
      		if (row > 0 && !grid[row - 1][column]) {
        		steps++;
        		grid[row][column] = true;
        		traverseGrid(grid, row - 1, column, steps);
      		}
      		break;
    	
		case 'D':
      		if (row < n - 1 && !grid[row + 1][column]) {
        		steps++;
        		grid[row][column] = true;
	        	traverseGrid(grid, row + 1, column, steps);
    	  	}
      		break;
    	
		case 'L':
      		if (column > 0 && !grid[row][column - 1]) {
        		steps++;
        		grid[row][column] = true;
        		traverseGrid(grid, row, column - 1, steps);
      		}
      		break;
    
		case 'R':
      		if (column < n - 1 && !grid[row][column + 1]) {
        		steps++;
        		grid[row][column] = true;
        		traverseGrid(grid, row, column + 1, steps);
      		}
      		break;
    	}

    	// backtrack
    	grid[row][column] = false;

    	// reduce the step
    	steps--;

	    return;
  	}

  	// increase the step
  	steps++;

  	// move UP
  	if (row > 0 && !grid[row - 1][column]) {
    	grid[row][column] = true;
    	traverseGrid(grid, row - 1, column, steps);
  	}

  	// move DOWN
  	if (row < n - 1 && !grid[row + 1][column]) {
    	grid[row][column] = true;
    	traverseGrid(grid, row + 1, column, steps);
  	}

  	// move LEFT
  	if (column > 0 && !grid[row][column - 1]) {
    	grid[row][column] = true;
    	traverseGrid(grid, row, column - 1, steps);
  	}

  	// move RIGHT
  	if (column < n - 1 && !grid[row][column + 1]) {
    	grid[row][column] = true;
    	traverseGrid(grid, row, column + 1, steps);
  	}

  	// backtrack
  	grid[row][column] = false;
  	// reduce the step
  	steps--;

  	return;
}

// main
int main() {
  	ios_base::sync_with_stdio(0);
  	cin.tie(0);

  	string path;
  	cin >> path;

  	for (size_t i = 0; i < path.length(); i++) {
    	reservedSteps[i] = path[i];
  	}

	// initialize boolean 2d array
  	bool grid[7][7] = { false };

  	// solve
  	traverseGrid(grid, 0, 0, 0);

  	// print the total paths
  	cout << paths;

  	return 0;
	
}
