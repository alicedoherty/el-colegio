// compared to vowel_cons_functions.cpp this does not contain
// the implemenations of is_vowel(..) is_cons(..)

#include <string>
#include <iostream>
using namespace std;

bool is_vowel(char c);
bool is_cons(char c);

int main() {

  string s;
  cout << "enter a word\n";
  cin >> s;
  for(int i=0; i < s.size(); i++) {
    char c;
    c = s[i];
    cout << c << " :";
    if(is_vowel(c)) {
      cout << "V";
    }
    else if(is_cons(c)) {
      cout << "C";
    }
    else { // do nothing
    }
    cout << endl;
  }

}
  

