#include <bits/stdc++.h>
using namespace std;

void calculateMaxDistances(int streetLength, int numberOfTrafficLights, int positionOfTrafficLights[]) {
    // set has default increasing ordering
    set<int> street{0, streetLength};
    // multiset
	multiset<int> passages{streetLength};
    
    for (int i = 0; i < numberOfTrafficLights; i++) {
        int trafficLightPosition = positionOfTrafficLights[i];
        auto nextLightPosition = street.upper_bound(trafficLightPosition);
        auto previousLightPosition = nextLightPosition;
        previousLightPosition--;

        passages.erase(passages.find(*nextLightPosition - *previousLightPosition));
		passages.insert(trafficLightPosition - *previousLightPosition);
		passages.insert(*nextLightPosition - trafficLightPosition);
		
        street.insert(trafficLightPosition);

		cout << *passages.rbegin() << " ";
    }

    return;
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int streetLength = 0;
    cin >> streetLength;

    int numberOfTrafficLights = 0;
    cin >> numberOfTrafficLights;

    int positionOfTrafficLights[numberOfTrafficLights] = {0};
    for (int i = 0; i < numberOfTrafficLights; i++) {
        cin >> positionOfTrafficLights[i];
    }

    // solve
    calculateMaxDistances(streetLength, numberOfTrafficLights, positionOfTrafficLights);

    return 0;
}
