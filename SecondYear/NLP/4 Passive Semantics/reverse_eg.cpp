#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int  main() {

  vector<string> t = {"a", "b"};

  reverse(t.begin(), t.end());

  // take a look

  for(int i=0; i < t.size(); i++) {
    cout << t[i] << " ";
  }
  cout << endl;


}
