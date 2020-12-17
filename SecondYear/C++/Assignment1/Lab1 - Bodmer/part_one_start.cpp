/* 
 * this code labels the vowels in an interaction like this
 * enter a word
 * potato
 * p :
 * o :V
 * t :
 * a :V
 * t :
 * o :V
*/

#include <string>
#include <iostream>
using namespace std;

// declaration before use, implementation will actually be later in the file
bool is_vowel(char c);
bool is_cons(char c);

int main() {

  string s;
  cout << "enter a word\n";
  cin >> s;
  // loop thru characters of s 
  // make each output depend on what is_vowel(..) says
  for(int i=0; i < s.size(); i++) {
    char c;
    c = s[i];
    if(is_vowel(c))
      cout << c << ": V" << '\n';
    else if(is_cons(c))
      cout << c << ": C" << '\n';
    else
      cout << "not a letter \n";
  }
}

// write a function which returns true of input char is
// a e i o u 
bool is_vowel(char c) {
  if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
    return true;
  else
    return false;
}

bool is_cons(char c) {
  if(c > 97 && c <= 122 && !is_vowel(c))
    return true;
  else
    return false;
}
